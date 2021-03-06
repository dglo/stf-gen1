/* atwd_sample_periode.c, skeleton file created by gendir
 */

#include <stddef.h>
#include <stdlib.h>

#include "stf/stf.h"
#include "stf-apps/atwd_sample_periode.h"

#include "stf-apps/atwdUtils.h"

#include "hal/DOM_MB_hal.h"
#include "hal/DOM_MB_fpga.h"

static float findFirstPeak(unsigned *w,int N);


BOOLEAN atwd_sample_periodeInit(STF_DESCRIPTOR *d) {
   return TRUE;
}

/* find period in an atwd waveform
 */
static unsigned findPeriod(unsigned *w,int N)
 {
  
   int *mn = (unsigned *) calloc(N, sizeof(unsigned));
   unsigned *cg = (unsigned *) calloc(N, sizeof(unsigned));
   int i, j;
   double sum = 0;
   unsigned period;
   
   /* first subtract mean...*/
   
     for (i=0; i<N; i++) sum += w[i];
     
     sum/=N; 

     for (i=0; i<N; i++) mn[i] = (int) w[i] - (int) sum;
   
     
   /* now compute autocorrelagram */ 
   for (i=0; i<N; i++)
     {  

     int val = 0;            /* j = is the time, i = is the lag time, mn = mean value */
    
     for (j=0; j<N; j++) val += mn[j]* mn[(j+i)&0x7f];
           
      
     cg += val;	
      
     }
     
  
   
   period = (unsigned) (findFirstPeak (cg,N)*40e6); 
     
   free(mn);
   return cg;
  
}

/* find the period of a signal given
 * the (positive only) autocorrelegram...
 *
 * 1) go till we have a zero
 * 2) go while we're increasing...
 * 3) return peak value...  
 *int 
 * return index or -1 on error...
 */
static float findFirstPeak(unsigned *w,int N) {
   int i, prev=0;

   for (i=0; i<N; i++) {
      if (w[i]==0) break;
   }

   if (i==0 || i==N) return -1;
   
   while (i<N && w[i]>=prev) {
      prev = w[i];
      i++;
   }

   if (i>=(N-1)) return -1;

   /* fit a parabola here...
    */
   {  const float y1 = w[i-2];
      const float y2 = w[i-1];
      const float y3 = w[i];
      const float b = (4*y2 - y3 - 3*y1)/2;
      const float a = (-2*y2 + y3 + y1)/2;
      return (i-2) - b/(2*a);
   }
}

BOOLEAN atwd_sample_periodeEntry(STF_DESCRIPTOR *d,
                    unsigned atwd_sampling_speed_dac,
                    unsigned atwd_ramp_top_dac,
                    unsigned atwd_ramp_bias_dac,
                    unsigned atwd_analog_ref_dac,
                    unsigned atwd_pedestal_dac,
                    unsigned atwd_chip_a_or_b,
                    unsigned loop_count,
		    unsigned window_width,/*add new input*/
                    unsigned *atwd_clock1x_amplitude,
                    unsigned *atwd_sampling_speed_MHz,
                    unsigned *atwd_sampling_speed_dac_300MHz,
                    unsigned *atwd_clock1x_waveform,
	            unsigned *sample_per_periode) /*add new array*/
   
{

   int i;
   const int ch = (atwd_chip_a_or_b) ? 0 : 4;
   const int cnt = 128;
   unsigned *waveform = (unsigned *) calloc(cnt, sizeof(unsigned));
   int trigger_mask = (atwd_chip_a_or_b) ? 
      HAL_FPGA_TEST_TRIGGER_ATWD0 : HAL_FPGA_TEST_TRIGGER_ATWD1;
   unsigned mhz, minv, maxv;
   
   /* pretest checks: */

   /* 1) all five atwd dac settings are programmed...
    */
   halWriteDAC(ch, atwd_sampling_speed_dac);
   halWriteDAC(ch+1, atwd_ramp_top_dac);
   halWriteDAC(ch+2, atwd_ramp_bias_dac);
   halWriteDAC(DOM_HAL_DAC_ATWD_ANALOG_REF, atwd_analog_ref_dac);
   halWriteDAC(DOM_HAL_DAC_PMT_FE_PEDESTAL, atwd_pedestal_dac);

   /* 2) select clock1x on analog_mux...
    */

   prescanATWD(trigger_mask);
   
   halSelectAnalogMuxInput(DOM_HAL_MUX_40MHZ_SQUARE);/*run test with 40MHz*/

   halUSleep(100);
   
   /* test algorithm... */
   getSummedWaveform(loop_count, trigger_mask, 3, atwd_clock1x_waveform);

   /* find sampling rate at current dac setting...
    */
   *atwd_sampling_speed_MHz = findPeriod(atwd_clock1x_waveform,128);

   /* get min and max */
   minv = atwd_clock1x_waveform[0];
   maxv = atwd_clock1x_waveform[0];

   for (i=1; i<cnt; i++) {
      if (atwd_clock1x_waveform[i]>maxv) {
	 maxv = atwd_clock1x_waveform[i];
      }
      else if (atwd_clock1x_waveform[i]<minv) {
	 minv = atwd_clock1x_waveform[i];
      }
   }
   *atwd_clock1x_amplitude = maxv-minv;

   /* add 500 counts to current dac setting and try again...
    */
   halWriteDAC(ch, atwd_sampling_speed_dac);
   halUSleep(1000*10);
   getSummedWaveform(loop_count, trigger_mask, 3, waveform);

   /* mhz = findPeriod(waveform,window_width);*/
   

   for (i=0;i<128-window_width;i++)
     {
       sample_per_periode[i]=findPeriod(waveform+i,window_width);
     }


   /* compute line through these points...
    */
   {  const float x1 = atwd_sampling_speed_dac;
      const float y1 = *atwd_sampling_speed_MHz;
      const float x2 = atwd_sampling_speed_dac + 500;
      const float y2 = mhz;
      const float m = (y2-y1)/(x2-x1);
      /* y - y1 = m(x - x1) 
       *
       * we want to know x where y==300MHz...
       *
       * (300e6 - y1)/m = x - x1, or:
       * x = (300e6 - y1)/m + x1
       *
       * 0.5 is for rounding...
       */
      *atwd_sampling_speed_dac_300MHz = (unsigned) 
	 ( (300e6-y1)/m + x1 + 0.5);
   }

   free(waveform);

   /* report sampling speed in MHz... */
   *atwd_sampling_speed_MHz /= 1000000;
   

   return 
      *atwd_clock1x_amplitude >= 250 &&
      *atwd_clock1x_amplitude <= 800 &&
      *atwd_sampling_speed_MHz <= 350 &&
      *atwd_sampling_speed_MHz >= 250 &&
      *atwd_sampling_speed_dac_300MHz < 4095;
}
