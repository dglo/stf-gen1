/*
 *
 *
 * copywrite info
 *
 */
#include <stdio.h>

#include "stf/stf.h"

BOOLEAN StfEgInit(STF_DESCRIPTOR *d) {
   printf("StfEgInit\r\n");
   return TRUE;
}

BOOLEAN StfEgEntry(STF_DESCRIPTOR *d) {
   printf("StfEgEntry\r\n");
   return TRUE;
}

