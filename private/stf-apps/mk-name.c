/* mk-name.c, make a new file name (just the base)
 *
 * 1) grab a lock file
 * 2) get value from number file, increment and put it back in
 * 3) delete lock file
 */
#include <stdio.h>
#include <string.h>

#include <windows.h>

int main() {
  const char *lockFile = "e:\\arthur\\xml\\uniq.lock";
  const char *numFile = "e:\\arthur\\xml\\uniq.txt";
  HANDLE lf, nf;
  int i;
  unsigned v;
  DWORD nr;
  char line[80];

  /* create lock file...
   */
  for (i=0; i<10; i++) {
    if ((lf = CreateFile(lockFile,
			 0, 
			 0,
			 NULL,
			 CREATE_NEW,
			 FILE_ATTRIBUTE_NORMAL,
			 0)) == INVALID_HANDLE_VALUE) {
      Sleep(100);
    }
    else break;
  }

  if (i==10) {
    fprintf(stderr, "can't open lock file!\n");
    return 1;
  }

  CloseHandle(lf);

  if ((nf = CreateFile(numFile,
		       FILE_READ_DATA|FILE_WRITE_DATA,
		       0,
		       NULL,
		       OPEN_EXISTING,
		       FILE_ATTRIBUTE_NORMAL,
		       0))==INVALID_HANDLE_VALUE) {
    fprintf(stderr, "can't open num file!\n");
    DeleteFile(lockFile);
    return 1;
  }

  memset(line, 0, sizeof(line));
  if (!ReadFile(nf, line, sizeof(line)-1, &nr, NULL)) {
    fprintf(stderr, "can't read num file!\n");
    DeleteFile(lockFile);
    return 1;
  }

  if (sscanf(line, "%u", &v)!=1) {
    fprintf(stderr, "can't parse num file!\n");
    DeleteFile(lockFile);
    return 1;
  }
  
  printf("%08u\n", v);

  SetFilePointer(nf, 0, NULL, FILE_BEGIN);

  sprintf(line, "%u", v+1);
  if (!WriteFile(nf, line, strlen(line), &nr, NULL)) {
    fprintf(stderr, "can't write new value to num file...\n");
    DeleteFile(lockFile);
    return 1;
  }

  DeleteFile(lockFile);
  CloseHandle(nf);

  return 0;
}
