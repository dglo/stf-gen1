/*
 *
 *
 * copywrite info
 *
 */
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "stf/stf.h"

static void getstr(char *str) {
   while (1) {
      const int nr = read(0, str, 1);
      if (nr==1) {
	 if (*str == '\r') {
	    *str = 0;
	    return;
	 }
	 else {
	    write(1, str, 1);
	    str++;
	 }
	 
      }
   }
}

/*------------------------------------------------------------
 */
int  main() 
{
    char selectCmd[128];
    STF_DESCRIPTOR *d=NULL;
    int i;

    printf("--------\r\n");
    printf("stf menu:\r\n\r\n");
    
    for (;;) {
        printf("\r\n\r\nTests present:\r\n");

        d=NULL;
        d=findNextTest(d);
        while(d!=NULL) {
  	    printf("Test name: %s\r\n",getTestName(d));
 	    printf("\tDesc: %s\r\n",getTestDescription(d));
	    d=findNextTest(d);
        }

        printf("\r\nSelect test by name (<cr> exits): ");
	fflush(stdout);

        getstr(selectCmd);
        if(!strcmp(selectCmd,"")) {
  	    printf("exiting.......\r\n");
	    return 1;
        }
   
	d=findTestByName(selectCmd);
	if(d!=NULL) {
	   printf("\r\n\r\nTest: %s\r\n",getTestName(d));
	   printf("Description: %s\r\n",getTestDescription(d));
	   printf("Version Info: %d,%d\r\n",getTestMajorVersion(d),
		  getTestMinorVersion(d));
	   if(d->testRunnable) {
	      printf("Runnable State: true\r\n");
	   }
	   else {
	      printf("Runnable State: false\r\n");
	   }
	   
	   for (i=0; i<d->nParams; i++) {
	      STF_PARAM *p=d->params + i;
	      
	      if (strcmp(p->class, INPUT_PARAM)==0) {
		 printf("\tParam Name: %s\r\n",getParamName(p));
		 printf("\t\tClass: %s\r\n",getParamClass(p));
		 printf("\t\tType: %s\r\n",getParamType(p));
		 printf("\t\tMin/Max Value: %s / %s\r\n",
			getParamMinValue(p),getParamMaxValue(p));
		 printf("\t\tDefault Value: %s\r\n",getParamDefValue(p));
		 if(!strcmp(getParamType(p),CHAR_TYPE)) {
		    printf("\t\tValue: %s\r\n",getParamValueAsString(p));
		 }
		 if(!strcmp(getParamType(p),UINT_TYPE)) {
		    printf("\t\tValue: %u\r\n",getParamValueAsInt(p));
		 }
		 if(!strcmp(getParamType(p),ULONG_TYPE)) {
		    printf("\t\tValue: %lu\r\n",getParamValueAsLong(p));
		 }
		 if(!strcmp(getParamType(p),BOOLEAN_TYPE)) {
		    if(getParamValueAsBool(p)) {
		       printf("\t\tValue: true\r\n");
		    }
		    else {
		       printf("\t\tValue: true\r\n");
		    }
		 }
	      }
	   }
	   
	   printf("\r\n\r\ntesting\r\n");

	   if (!d->isInit) {
	      d->testRunnable = d->initPt(d);
	      d->isInit = 1;
	   }
	   
	   if (d->testRunnable==0) {
	      printf("\r\ntest '%s' is not runnable", d->name);
	   }
	   d->passed = d->entryPt(d);
	   printf("done [%d]!\r\n\r\n", d->passed);
	   
	   for (i=0; i<d->nParams; i++) {
	      STF_PARAM *p=d->params + i;
	      
	      if (strcmp(p->class, OUTPUT_PARAM)==0) {
		 printf("\tParam Name: %s\r\n",getParamName(p));
		 printf("\t\tClass: %s\r\n",getParamClass(p));
		 printf("\t\tType: %s\r\n",getParamType(p));
		 if(!strcmp(getParamType(p),CHAR_TYPE)) {
		    printf("\t\tValue: %s\r\n",
			   getParamValueAsString(p));
		 }
		 if(!strcmp(getParamType(p),UINT_TYPE)) {
		    printf("\t\tValue: %u\r\n",
			   getParamValueAsInt(p));
		 }
		 if(!strcmp(getParamType(p),ULONG_TYPE)) {
		    printf("\t\tValue: %lu\r\n",
			   getParamValueAsLong(p));
		 }
		 if(!strcmp(getParamType(p),BOOLEAN_TYPE)) {
		    if(getParamValueAsBool(p)) {
		       printf("\t\tValue: true\r\n");
		    }
		    else {
		       printf("\t\tValue: true\r\n");
		    }
		 }
	      }
	   }
	}
	else {
	   printf("\r\n\r\nTest not found!\r\n");
	}
    }
    return 0;
}





