/* simple stf server -- we use xml to send test parameters and
 * test results back and forth between server (arm board) and 
 * client (PC)...
 *
 * protocol:
 *
 *    Server (ARM)                      Client (PC)
 *
 * 1  OK\r\n
 *                                      SEND nbytes\r\n
 * 2  OK\r\n
 *                                      [nbytes of 8 bit data]
 *    OK\r\n
 *                                      GO TestName\r\n
 *    (if error goto 6)
 * 3  OK\r\n
 *                                      RCV TestName\r\n
 *    (if error goto 6)
 * 4  SEND nbytes\r\n
 *                                      OK\r\n
 *
 *                                      OK\r\n
 *    (goto state 1)
 * 5  [nbytes of 8 bit data]
 *                                      OK\r\n
 *    goto state 1
 *
 * 6  ERR msg\r\n
 *    (goto 1)
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "xmlparse/xmlparse.h"

#include "stf/stf.h"
#include "hal/DOM_MB_hal.h"

extern int read(int , void *, int);
extern int write(int , void *, int);

#define talloc(a) ((a) *) malloc(sizeof(a))

static STF_DESCRIPTOR *desc = NULL;
static STF_PARAM *param = NULL;
static const char *xmlTag = NULL;
static char *parmType = NULL;

/* when we get a new element...
 */
static void startElement(void *userData, const char *name, const char **atts) {
  int *depth = userData;

  xmlTag = name;

  /* FIXME: put these at the proper depth...
   */
  if (strcmp(xmlTag, "version")==0) {
     const int major = atoi(atts[0]);
     const int minor = atoi(atts[1]);
     const int err = 
	major!=desc->majorVersion || minor!=desc->minorVersion;
     
     /* FIXME: what do we do now?!?!
      */
  }
  else if (strcmp(xmlTag, "inputParameter")==0) {
     parmType = INPUT_PARAM;
     param = NULL;
  }
  else if (strcmp(xmlTag, "outputParameter")==0) {
     parmType = OUTPUT_PARAM;
     param = NULL;
  }

  if (*depth == 0) {
     if (strcmp(xmlTag, "test")) {
	printf("invalid top level object '%s', expecting 'test'\r\n",
	       xmlTag);
     }
  }

  *depth = *depth + 1;
}

/* when an element is done...
 */
static void endElement(void *userData, const char *name) {
  int *depthPtr = userData;
  *depthPtr = *depthPtr - 1;
}

/* s is not 0 terminated. */
static void characterData(void *userData, const XML_Char *s, int len) {
   const int *depth = userData;
   char str[64];
   
   memcpy(str, s, len);
   str[len] = 0;

   /* remove trailing whitespace...
    */
   while (len>0 && 
	  (str[len-1]==' ' || str[len-1]=='\r' || str[len-1]=='\n' ||
	   str[len-1]=='\t')) { 
      str[len-1] = 0; len--; 
   }

   if (len==0) return;

#if 0   
   printf("xmlTag = %s, desc = %p, param = %p, depth = %d, s = '%s'\r\n",
	  (xmlTag==NULL) ? "NULL" : xmlTag, desc, param, *depth, str);
#endif

   if (*depth==2) {
      if (strcmp(xmlTag, "name")==0) {
	 if ((desc = findTestByName(str))==NULL) {
	    fprintf(stderr, "can't get descriptor name '%s'\r\n", str);
	 }
      }
   }
   else if (*depth == 3) {
      if (strcmp(xmlTag, "name")==0) {
	 if ((param = getParamByName(desc, str))==NULL) {
	    fprintf(stderr, "can't get param name '%s'\r\n", s);
	 }
	 param->class = parmType;
      }
      if (strcmp(xmlTag, "value")==0) {
	 if (strcmp(param->type, CHAR_TYPE)==0) {
	    param->value.charValue = strdup(str);
	 }
	 else if (strcmp(param->type, UINT_TYPE)==0) {
	    param->value.intValue = atoi(str);
	 }
	 else if (strcmp(param->type, ULONG_TYPE)==0) {
	    param->value.longValue = strtol(str, 0, NULL);
	 }
	 else if (strcmp(param->type, BOOLEAN_TYPE)==0) {
	    param->value.boolValue = atoi(str);
	 }
      }
      else if (strcmp(xmlTag, "arraySize")==0) {
	 param->arraySize = strdup(str);
	 param->arrayLength = atoi(param->arraySize);
      }
   }
}

/* turn a directory entry into a xml file...
 *
 * returns number of bytes written to buf, or:
 *
 * -1 : buf is too small.
 *
 * FIXME: check max...
 */
static int dirToXML(char *buf, int max, STF_DESCRIPTOR *stf) {
   int idx = 0;
   int i;

   idx += sprintf(buf+idx, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
   idx += sprintf(buf+idx, "<test-results>\r\n");
   idx += sprintf(buf+idx, " <name>%s</name>\r\n", stf->name);
   idx += sprintf(buf+idx, " <description>%s</description>\r\n", stf->desc);
   idx += sprintf(buf+idx, " <version major=\"%d\" minor=\"%d\"/>\r\n", 
		  stf->majorVersion, stf->minorVersion);

   for (i=0; i<stf->nParams; i++) {
      int j;
      
      idx += sprintf(buf+idx, "  <%sParameter>\r\n", stf->params[i].class);
      idx += sprintf(buf+idx, "   <name>%s</name>\r\n", stf->params[i].name);

      idx += sprintf(buf+idx, "   <value>");
      if (strcmp(stf->params[i].type, CHAR_TYPE)==0) {
	 idx += sprintf(buf+idx, "%s", stf->params[i].value.charValue);
      }
      else if (strcmp(stf->params[i].type, UINT_TYPE)==0) {
	 idx += sprintf(buf+idx, "%u", stf->params[i].value.intValue);
      }
      else if (strcmp(stf->params[i].type, ULONG_TYPE)==0) {
	 idx += sprintf(buf+idx, "%lu", stf->params[i].value.longValue);
      }
      else if (strcmp(stf->params[i].type, BOOLEAN_TYPE)==0) {
	 idx += sprintf(buf+idx, "%s", 
			stf->params[i].value.boolValue ? 
			BOOLEAN_TRUE : BOOLEAN_FALSE);
      }
      else if (strcmp(stf->params[i].type, UINT_ARRAY_TYPE)==0) {
	 for (j=0; j<stf->params[i].arrayLength; j++) {
	    idx += sprintf(buf+idx, "%u ", 
			   stf->params[i].value.intArrayPtr[j]);
	 }
      }
      else if (strcmp(stf->params[i].type, ULONG_ARRAY_TYPE)==0) {
	 for (j=0; j<stf->params[i].arrayLength; j++) {
	    idx += sprintf(buf+idx, "%lu ", 
			   stf->params[i].value.longArrayPtr[j]);
	 }
      }
      else {
	 idx += sprintf(buf+idx, "?");
      }
      idx += sprintf(buf+idx, "</value>\r\n");
      idx += sprintf(buf+idx, "   <arraySize>%s</arraySize>\r\n",
		     stf->params[i].arraySize);
      idx += sprintf(buf+idx, "  </%sParameter>\r\n", stf->params[i].class);
   }

   sprintf(buf+idx, "  <outputParameter>\r\n");
   sprintf(buf+idx, "    <name>passed</name>\r\n");
   sprintf(buf+idx, "    <value>%s</value>\r\n", 
	   stf->passed ? BOOLEAN_TRUE : BOOLEAN_FALSE);
   sprintf(buf+idx, "  </outputParameter>\r\n");
   
   sprintf(buf+idx, "  <outputParameter>\r\n");
   sprintf(buf+idx, "    <name>testRunnable</name>\r\n");
   sprintf(buf+idx, "    <value>%s</value>\r\n", 
	   stf->testRunnable ? BOOLEAN_TRUE : BOOLEAN_FALSE);
   sprintf(buf+idx, "  </outputParameter>\r\n");
   
   sprintf(buf+idx, "  <outputParameter>\r\n");
   sprintf(buf+idx, "    <name>boardID</name>\r\n");
   sprintf(buf+idx, "    <value>%s</value>\r\n", getBoardID());
   sprintf(buf+idx, "  </outputParameter>\r\n");
   
   idx += sprintf(buf+idx, "</test-results>\r\n");
   return idx;
}

/* returns: zero OK, non-zero error, empty lines are ignored...
 */
static int getLine(char *line, int max) {
   int idx = 0;

   memset(line, 0, max);
   while (idx<max-1) {
      /* int i;*/
      int nr;
      char *t;

      if ((nr=read(0, line+idx, max-1-idx))<=0) {
	 return 1;
      }

#if 0
      for (i=0; i<idx+nr; i++) printf("%02x ", line[i]);
      printf("\r\n");
#endif

      /* look for '\r'...
       */
      if ((t=strchr(line, '\r'))!=NULL) {
	 *t = 0;
	 if (t!=line) return 0;
      }
      else {
	 idx += nr;
      }
   }
   return 1;
}

static int readall(int fd, char *buf, int nb) {
   int idx=0;
   
   while (idx<nb) {
      const int nr = read(fd, buf+idx, nb-idx);
      if (nr<0) {
	 fprintf(stderr, "unable to read\n");
	 return -1;
      }
      else if (nr==0) {
	 fprintf(stderr, "eof!\n");
	 return -1;
      }
      idx+=nr;
   }
   return idx;
}

int main() {
   char msg[512];
   char name[64];
   char line[256];
   int  nbytes, needAck = 0;
   char *buf = NULL;
   int buflen = 0;
   int state = 1;
   int depth = 0;

   while (1) {
      /* printf("state: %d, needAck: %d\r\n", state, needAck);*/
      
      if (needAck) {
	 if (getLine(line, sizeof(line))) {
	    strcpy(msg, "unable to read ack line!");
	    state = 6;
	 }
	 else if (strcmp(line, "OK")!=0) {
	    sprintf(msg, "invalid Ack command! '%s'", line);
	    state = 6;
	 }
	 needAck = 0;
       }

      if (state==1) {
	 printf("OK\r\n");

	 if (getLine(line, sizeof(line))) {
	    strcpy(msg, "unable to read line!");
	    state = 6;
	 }
	 else {
	    if (strcmp(line, "EXIT")==0) {
	       printf("exiting...\r\n");
	       return 0;
	    }
	    else if (sscanf(line, "SEND %d", &nbytes)==1) {
	       state = 2;
	    }
	    else if (sscanf(line, "GO %s", name)==1) {
	       state = 3;
	    }
	    else if (sscanf(line, "RCV %s", name)==1) {
	       state = 4;
	    }
	    else if (strcmp(line, "OK")==0) {
	       state = 1;
	    }
	    else {
	       sprintf(msg, "invalid state 1 command!: '%s'", line);
	       state = 6;
	    }
	 }
      }
      else if (state==2) {
	 int nr;
	 
	 /* realloc buf if necessary...
	  */
	 if (buflen<nbytes+1) {
	    void *nb = malloc(nbytes+1);
	    if (nb==NULL) {
	       sprintf(msg, "unable to alloc %d bytes\r\n", nbytes);
	       state = 6;
	       continue;
	    }
	    buf = nb;
	    buflen = nbytes+1;
	 }
	 printf("OK\r\n");

	 /* read xml file
	  */
	 if ((nr=readall(0, buf, nbytes))!=nbytes) {
	    sprintf(msg, "can't read data, got: %d, expecting: %d\r\n", 
		    nr, nbytes);
	    state = 6;
	 }
	 else {
	    XML_Parser parser = XML_ParserCreate(NULL);

	    buf[nbytes] = 0;
	    printf("parse: '%s'\r\n", buf);
	    
	    /* now parse data...
	     */
	    XML_SetUserData(parser, &depth);
	    XML_SetElementHandler(parser, startElement, endElement);
	    XML_SetCharacterDataHandler(parser, characterData);
	    if (!XML_Parse(parser, buf, nbytes, 1)) {
	       sprintf(msg,
		       "%s at line %d\r\n",
		       XML_ErrorString(XML_GetErrorCode(parser)),
		       XML_GetCurrentLineNumber(parser));
	       state = 6;
	    }
	    else {
	       /* done parsing...
		*/
	       state = 1;
	    }
	    XML_ParserFree(parser);
	 }
      }
      else if (state==3) {
	 STF_DESCRIPTOR *sd;
	 
	 /* name now has testname to go -- so go...
	  *
	  * 1) lookup test name...
	  * 2) exec test...
	  */
	 if ((sd = findTestByName(name))==NULL) {
	    sprintf(msg, "can not find test '%s'", name);
	    state = 6;
	 }
	 else {
	    if (!sd->isInit) {
	       sd->testRunnable = sd->initPt(sd);
	       sd->isInit = 1;
	    }
	    
	    if (!sd->testRunnable) {
	       sprintf(msg, "test '%s' is not runnable", name);
	       state = 6;
	    }
	    else {
	       sd->passed = sd->entryPt(sd);
	       state = 1;
	    }
	 }
      }
      else if (state==4) {
	 STF_DESCRIPTOR *sd;
	 const int minbl = 32*1024;
	 
	 /* test is now done
	  *
	  * 1) format results to buffer...
	  * 2) request to client to send buffer...
	  */
	 if (buflen<minbl) {
	    void *nb = malloc(minbl);
	    buf = nb;
	    buflen = minbl;
	 }

	 if ((sd = findTestByName(name))==NULL) {
	    sprintf(msg, "can't find test '%s'\n", name);
	    state = 6;
	 }
	 else {
	    if ((nbytes=dirToXML(buf, buflen, sd))<0) {
	       sprintf(msg, "can't create xml file\n");
	       state = 6;
	    }
	    else {
	       printf("SEND %d\r\n", nbytes);
	       needAck = 1;
	       state = 5;
	    }
	 }
      }
      else if (state==5) {
	 /* client is ready for the results...
	  *
	  * 1) write file
	  * 2) wait for client ack
	  */
	 write(1, buf, nbytes);

	 /* wait for ack from client...
	  */
         needAck = 1;
	 state = 1;
      }
      else if (state==6) {
	 printf("ERR '%s'\r\n", msg);
	 strcpy(msg, "");
	 state = 1;
      }
   }

   return 0;
}
