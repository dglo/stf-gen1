/* parse-qry.c, parse a cgi query string...
 *
 * %xx <- hex digit
 * & separator
 * = assignment
 *
 * return number of parameters parsed...
 *  nm has the names, val has the values...
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* this thing leaks at least strlen(q)+1 bytes...
 */
int parseQuery(const char *q,
	       char **nm,
	       char **val, int max) {
  int np = 1;
  const int len = strlen(q);
  char *qq = (char *) malloc(len+1);
  char *t = qq;
  int i;

  strcpy(qq, q);

  /* replace + with ' '
   */
  t = qq;
  while ((t = strchr(t, '+'))!=NULL) {
    *t = ' ';
    t = t+1;
  }

  /* replace '&' with 0 set np
   */
  np = 1;
  t = qq;
  while ((t=strchr(t, '&'))!=NULL) {
    *t = 0;
    t = t+1;
    np++;
  }

  if (np>max) {
    fprintf(stderr, "too many parameters (%d expecting at most %d)\n",
	    np, max);
    free(qq);
    return -1;
  }

  /* now point to proper places...
   */
  t = qq;
  for (i=0; i<np; i++) {
    nm[i] = t;
    t = strchr(t, '=');
    if (t==NULL) {
      fprintf(stderr, "invalid name=val pair!\n");
      free(qq);
      return -1;
    }
    *t = 0;
    val[i] = t+1;
    t += strlen(t+1)+2;
  }

  return np;
}

#if defined(TESTING)

int main(int argc, char *argv[]) {
  int i, n;
  char *nms[256], *vals[256];

  if (argc!=2) {
    fprintf(stderr, "usage: parse-qry qry\n");
    return 1;
  }

  if ((n=parseQuery(argv[1], nms, vals, 256))<0) {
    fprintf(stderr, "can't parse query!\n");
    return 1;
  }

  for (i=0; i<n; i++) {
    printf("[%d] %s = %s\n", i, nms[i], vals[i]);
  }

  return 0;
}

#endif
