/*
 */
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
  if (argc==2) {
    extern int parseQuery(const char *, char **, char **, int );
    char *nms[256], *vals[256];
    int i, n = parseQuery(argv[1], nms, vals, 256);
    for (i=0; i<n; i++) {
      if (strcmp(nms[i], "test")==0) {
	 printf("%s", vals[i]+4 /* skip "Run " */);
      return 0;
      }
    }
  }
  return 1;
}
