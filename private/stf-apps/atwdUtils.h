#include "hal/DOM_MB_hal.h"
#include "hal/DOM_MB_fpga.h"

void prescanATWD(unsigned trigger_mask);

void lookupPulserRate(int repetition_rate, DOM_HAL_FPGA_PULSER_RATES *rate, 
		      unsigned *hz);
