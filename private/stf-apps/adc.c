#include "stf/stf.h"

BOOLEAN ADCInit(STF_DESCRIPTOR *desc) { return TRUE; }
BOOLEAN ADCEntry(STF_DESCRIPTOR *desc,
		 unsigned adc_chip,
		 unsigned adc_channel,
		 unsigned loop_count,
		 unsigned *adc_mean_counts,
		 unsigned *adc_rms_counts,
		 unsigned *adc_max_counts,
		 unsigned *adc_min_counts,
		 unsigned *adc_mean_mvolt_or_mamp,
		 unsigned *adc_rms_mvolt_or_mamp,
		 unsigned *adc_max_mvolt_or_mamp,
		 unsigned *adc_min_mvolt_or_mamp) {
   /* fill in details from azriels document...
    */
   *adc_mean_counts = 100;
   return TRUE;
}
