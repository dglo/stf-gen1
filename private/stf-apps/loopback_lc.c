/* loopback_lc.c, skeleton file created by gendir
 */
#include "stf/stf.h"
#include "stf-apps/loopback_lc.h"

BOOLEAN loopback_lcInit(STF_DESCRIPTOR *d) { return TRUE; }

BOOLEAN loopback_lcEntry(STF_DESCRIPTOR *d,
                    unsigned loop_count,
                    unsigned *lc_tx_quiet,
                    unsigned *lc_tx_dwn_hi,
                    unsigned *lc_tx_dwn_lo,
                    unsigned *lc_tx_up_hi,
                    unsigned *lc_tx_up_lo,
                    unsigned *lc_tx_up_hi_dwn_high,
                    unsigned *lc_tx_up_hi_dwn_lo,
                    unsigned *lc_tx_up_lo_dwn_lo,
                    unsigned *lc_tx_up_lo_dwn_hi) {
#if 0
: lcrs         $0         $90081018 !
: lcactlatch   $0000f000  $90081018 !
: lcrd      &16 base ! $9008101c @ . drop &10 base !
: lctxdwhi     $0000f100  $90081018 !
: lctxdwlo     $0000f200  $90081018 !
: lctxuphi     $0000f400  $90081018 !
: lctxuplo     $0000f800  $90081018 !
: lctxhihi     $0000f500  $90081018 !
: lctxhilo     $0000f900  $90081018 !
: lctxlolo     $0000fA00  $90081018 !
: lctxlohi     $0000f600  $90081018 !

: lctestone  lcrs lcrd lcrs lctxdwhi lcrd lcrs lctxdwlo lcrd lcrs lctxuphi lcrd lcrs lctxuplo lcrd
: lctestboth lcrs lctxhihi lcrd lcrs lctxhilo lcrd lcrs lctxlolo lcrd lcrs lctxlohi lcrd
#endif

   unsigned i;
   unsigned pulses[] = {
      0x100, 0x200, 0x400, 0x800, /* each by itself */
      0x500, 0x900, 0xa00, 0x600  /* combination of two */
   };
   unsigned answers[] = {
      0x400, 0x800, 0x100, 0x200,
      0x500, 0x600, 0xa00, 0x900
   };

   for (i=0; i<loop_count; i++) {
      int j;
      const int npulses = sizeof(pulses)/sizeof(pulses[0]);

      for (j=0; j<npulses; j++) {
         /* reset */
         *(volatile unsigned *)0x90081018 = 0;

         /* wait a bit for line to be quiet... */
         halUSleep(2);

         /* send a high pulse down -- and latch... */
         *(volatile unsigned *) 0x90081018 = 0xf000 | pulses[j];

         /* wait for it to arrive... */
         halUSleep(2);

         /* did it arrive correctly? */
         {  const unsigned answer = *(volatile unsigned *) 0x9008101c;
            if (answer!=answers[j]) return FALSE;
         }
      }
   }

   return TRUE;
}


