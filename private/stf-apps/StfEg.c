/*
 *
 *
 * copywrite info
 *
 */
#include <stdio.h>

#include "stf/stf.h"

BOOLEAN StfEgInit(STF_DESCRIPTOR *d) {
   printf("memoryInit\r\n");
    return TRUE;
}

BOOLEAN StfEgEntry(STF_DESCRIPTOR *d) {
   printf("memoryEntry\r\n");
   return TRUE;
}

