/*
 *
 *
 * copywrite info
 *
 */
#include <stdio.h>

#include "stf/stf.h"

BOOLEAN memoryInit(STF_DESCRIPTOR *d) {
   printf("memoryInit\r\n");
    return TRUE;
}

BOOLEAN memoryEntry(STF_DESCRIPTOR *d) {
   printf("memoryEntry\r\n");
}

