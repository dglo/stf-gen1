/* memory tests.
 *
 * FIXME:
 *  test sdram, sram, spsram separately...
 *
 * copywrite info
 *
 */
#include <stdio.h>
#include <stdlib.h>

#include "stf/stf.h"
#include "stf/memtests.h"

#include "stf-apps/memory.h"

BOOLEAN memoryInit(STF_DESCRIPTOR *d) {
   return TRUE;
}

BOOLEAN memoryEntry(STF_DESCRIPTOR *d,
                    unsigned loopCount,
                    unsigned bufferSize) {
   const int bsz = bufferSize;
   ui32 *buf = (ui32 *) malloc(bsz);
   int ret = test_stuck_address(buf, buf, bsz);
   free(buf);
   return (ret==OK) ? TRUE : FALSE;
}

