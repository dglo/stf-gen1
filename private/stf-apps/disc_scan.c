/* disc_scan.c, skeleton file created by gendir
 */
#include <string.h>

#include "stf/stf.h"
#include "stf-apps/disc_scan.h"

#include "stf-apps/atwdUtils.h"

#include "hal/DOM_MB_hal.h"
#include "hal/DOM_MB_fpga.h"

BOOLEAN disc_scanInit(STF_DESCRIPTOR *d) { return TRUE; }

BOOLEAN disc_scanEntry(STF_DESCRIPTOR *d,
		       unsigned disc_scan_window_uvolts,
		       unsigned disc_spe_or_mpe,
		       unsigned pulser_amplitude_uvolt,
		       unsigned pulser_rate_hz,
		       unsigned atwd_pedestal_dac,
		       unsigned loop_count,
		       unsigned *disc_scan_edge_pos,
		       unsigned *disc_scan_begin_flat_range,
		       unsigned *disc_scan_end_flat_range,
		       unsigned *disc_scan_flat_range_rate,
		       unsigned *disc_scan_noise_band,
		       unsigned *disc_scan_noise_uvolt,
		       unsigned *disc_sum_waveform) {
   int zero_dac = speUVoltToDAC(0, atwd_pedestal_dac);
   int window_dac = speUVoltToDAC(disc_scan_window_uvolts, 0);
   int i;
   DOM_HAL_FPGA_PULSER_RATES rate;
   static const unsigned nominalRate = (1000*78)/100;
   unsigned sum;

   /* set pulser amplitude... */

   /* pretest 4) turn on fe pulser */
   lookupPulserRate(78e3, &rate, NULL);
   hal_FPGA_TEST_set_pulser_rate(rate);
   hal_FPGA_TEST_enable_pulser();

   /* clear waveform */
   memset(disc_sum_waveform, 0, sizeof(unsigned)*4096);
   
   for (i=0; i<loop_count; i++) {
      int j, n;
      for (j=zero_dac-window_dac; j<=zero_dac+window_dac; j++, n++) {
         /* set the new dac value... */
         halWriteDAC(DOM_HAL_DAC_SINGLE_SPE_THRESH, j);
         
         /* wait 200ms for counts to show up */
         halUSleep(200*1000);

         /* readout the rate... */
         disc_sum_waveform[n] = hal_FPGA_TEST_get_spe_rate();
      }
  }

   /* average... */
   for (i=0; i<4096; i++) disc_sum_waveform[i] /= loop_count;

   /* find first dac value at >50% of nominal (save position) */
   for (i=4095; i>0; i--) 
      if (disc_sum_waveform[i]>(unsigned) (0.5*nominalRate)) 
         break;
   *disc_scan_edge_pos = zero_dac - window_dac + i;
   
   /* find the flat range, the maximum contiguous set of samples +-5% from
    * nominal rate...
    */
   *disc_scan_begin_flat_range = *disc_scan_end_flat_range = 0;
   {  int bfr = 0, efr = 0;
      int j, active = 0;

      for (j=0; j<4096; j++) {
         const unsigned top = (unsigned) (1.05 * nominalRate);
         const unsigned bot = (unsigned) (0.95 * nominalRate);
         
         if (disc_sum_waveform[j]<=top && disc_sum_waveform[j]>=bot) {
            /* is this the first one?
             */
            if (!active) {
               active = 1;
               bfr = j;
            }
            else efr = j;
         }
         else {
            if (active) {
               if (efr-bfr+1 >= *disc_scan_end_flat_range - 
                                 *disc_scan_begin_flat_range + 1) {
                  *disc_scan_end_flat_range = zero_dac - window_dac + efr;
                  *disc_scan_begin_flat_range = zero_dac - window_dac + bfr;
               }
               efr = bfr = 0;
               active = 0;
            }
         }
      }

      if (active) {
         if (efr-bfr+1 >= *disc_scan_end_flat_range -
             *disc_scan_begin_flat_range + 1) {
            *disc_scan_end_flat_range = zero_dac - window_dac + efr;
            *disc_scan_begin_flat_range = zero_dac - window_dac + bfr;
         }
      }
   }

   /* now calculate the average rate... */
   for (i=*disc_scan_begin_flat_range; i<=*disc_scan_end_flat_range; i++) {
      sum += disc_sum_waveform[i];
   }
   sum /= *disc_scan_end_flat_range - *disc_scan_begin_flat_range + 1;
   *disc_scan_flat_range_rate = sum;

   /* maximum is noise band */
   {  unsigned max = disc_sum_waveform[0];
      int maxi = 0;
      for (i=1; i<4096; i++) {
         if (disc_sum_waveform[i]>max) {
            max = disc_sum_waveform[i];
            maxi = i;
         }
      }
      *disc_scan_noise_band = zero_dac - window_dac + maxi;
   }
   
   /* get the 95% -> 50% delta uVolt */
   {  int start=*disc_scan_edge_pos - (zero_dac - window_dac);
      i = start;
      while (i>=0 && disc_sum_waveform[i]<(unsigned) (0.95*nominalRate)) i--;
      *disc_scan_noise_uvolt = 
         (unsigned) speDACToUVolt(start - i);
   }

   return 
      (*disc_scan_end_flat_range - *disc_scan_begin_flat_range + 1) > 2 &&
      *disc_scan_noise_uvolt < 150;
}
