/* mk-test-xml, make an xml file from
 * a query string...
 */
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
	printf("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
	printf("<test>\n");
	break;
      }
    }
    for (i=0; i<n; i++) {
      if (strcmp(nms[i], "test")!=0) {
	printf("  <inputParameter>\n    <name>%s</name>\n", nms[i]);
	printf("    <value>%s</value>\n", vals[i]);
	printf("  </inputParameter>\n");
      }
    }

    printf("</test>\n");
    return 0;
  }
  return 1;
}

