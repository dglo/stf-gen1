/*
 *
 *
 * copywrite info
 *
 */
#include <stdio.h>

#include "stf/stf.h"

void StfEgInit(STF_DESCRIPTOR *d) {
   printf("memoryInit\r\n");
    d->testRunnable=TRUE;
}

void StfEgEntry(STF_DESCRIPTOR *d) {
   printf("memoryEntry\r\n");
}

