/* STFParameterLookup.java, autogenerated by genspl.awk */

package icecube.daq.stf.autogen;

import icecube.daq.db.stftest.STFParameter;
import icecube.daq.db.stftest.STFParameterType;
import icecube.daq.db.domprodtest.DOMProdTestException;
import java.util.HashMap;
import java.io.IOException;

public class STFParameterLookup {
  private static HashMap lookup;
  public static final STFParameter lookup(String test,
                                          String name)
    throws DOMProdTestException, IOException {
    if (lookup==null) lookup = mkLookup(); /* FIXME: lock here? above? */
    HashMap pm = (HashMap) lookup.get(test);
    if (pm==null) return null;
    return (STFParameter) pm.get(name);
  }

  private static final HashMap mkLookup() 
    throws DOMProdTestException, IOException {
    HashMap ret = new HashMap(), hm;
    STFParameter p;

    ret.put("ADC", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("0");
    hm.put("adc_chip", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("7");
    hm.put("adc_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_mean_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_rms_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_max_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_min_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_mean_mvolt_or_mamp", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_rms_mvolt_or_mamp", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_max_mvolt_or_mamp", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_min_mvolt_or_mamp", p);

    ret.put("memory", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("1");
    p.setMaxValue("1000");
    hm.put("loopCount", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1024");
    p.setMinValue("1");
    hm.put("count", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    hm.put("address", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("string"));
    p.setDefaultValue("stuck-address");
    hm.put("testName", p);

    ret.put("StfEg", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("boolean"));
    p.setDefaultValue("false");
    hm.put("Para1", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("string"));
    p.setDefaultValue("bananas");
    hm.put("Para2", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1000");
    hm.put("Para3", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("281474976710656");
    hm.put("Para4", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("boolean"));
    hm.put("Para5", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("string"));
    hm.put("Para6", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("Para7", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedLong"));
    hm.put("Para8", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("Para9", p);

    ret.put("atwd_pedestal", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_trig_forced_or_spe", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("-5000");
    p.setMaxValue("5000");
    hm.put("spe_descriminator_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_pedestal_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_pedestal_pattern", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_spe_disc_threshold_dac", p);

    ret.put("atwd_baseline", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_trig_forced_or_spe", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("-5000");
    p.setMaxValue("5000");
    hm.put("spe_discriminator_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("2");
    p.setMaxValue("100000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_baseline_mean", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_baseline_rms", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_baseline_min", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_baseline_max", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_baseline_histogram", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_spe_disc_threshold_dac", p);

    ret.put("pmt_hv_ramp", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("800");
    p.setMinValue("0");
    p.setMaxValue("2047");
    hm.put("hv_min_volt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2047");
    p.setMinValue("0");
    p.setMaxValue("2047");
    hm.put("hv_max_volt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("50");
    p.setMinValue("10");
    p.setMaxValue("300");
    hm.put("hv_step_volt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_worst_set_mvolt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_worst_read_mvolt", p);

    ret.put("pmt_hv_stability", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1500");
    p.setMinValue("0");
    p.setMaxValue("2047");
    hm.put("hv_set_volt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("300");
    p.setMinValue("5");
    p.setMaxValue("3600");
    hm.put("run_time_sec", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("10");
    p.setMaxValue("10000");
    hm.put("sample_time_ms", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("15");
    p.setMinValue("0");
    p.setMaxValue("600");
    hm.put("init_wait_sec", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_read_mean_mvolt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_read_min_mvolt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_read_max_mvolt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_read_rms_mvolt", p);

    ret.put("pressure", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("boolean"));
    p.setDefaultValue("True");
    hm.put("dom_sealed", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("boolean"));
    p.setDefaultValue("False");
    hm.put("use_temperature", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_5v_mean_mvolts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_mean_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_rms_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_max_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_min_counts", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_mean_kpascal", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_rms_kpascal", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_max_kpascal", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("adc_min_kpascal", p);

    ret.put("pulser_spe_mpe", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    hm.put("pulse_height", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("500");
    hm.put("discriminator_level", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("boolean"));
    p.setDefaultValue("False");
    hm.put("mpe_discriminator", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1200");
    hm.put("repetition_rate", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("measured_rate", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("actual_rate", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("discriminator_dac", p);

    ret.put("atwd_pedestal_sweep_forced", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ch0_clamp", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1500");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("full_range_mv", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("half_range_counts_per_volt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("full_range_counts_per_volt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("linearity_pedestal_percent", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_pedestal_sweep_forced", p);

    ret.put("atwd_pulser_spe", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ch0_clamp", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("5000");
    p.setMinValue("0");
    p.setMaxValue("5000000");
    hm.put("pulser_amplitude_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("boolean"));
    p.setDefaultValue("true");
    hm.put("pedestal_subtraction", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("50");
    hm.put("n_pedestal_waveforms", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("triggerable_spe_dac", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_baseline_waveform", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_waveform_width", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_waveform_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_waveform_position", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_expected_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_waveform_pulser_spe", p);

    ret.put("atwd_pedestal_spe", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ch0_clamp", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_pedestal_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_pedestal_pattern", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_spe_disc_threshold_dac", p);

    ret.put("atwd_clock1x_forced", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_clock1x_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_sampling_speed_MHz", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("atwd_sampling_speed_dac_300MHz", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("atwd_clock1x_waveform", p);

    ret.put("pmt_pv_ratio", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("60");
    p.setMinValue("5");
    p.setMaxValue("500");
    hm.put("atwd_spe_height", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("10000");
    p.setMinValue("2000");
    p.setMaxValue("20000");
    hm.put("pv_trig_cnt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("pv_ratio_10", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hist_spe_peak", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("hist_array", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("spe_disc_dac", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("hv_set_volt", p);

    ret.put("flasher_current", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("127");
    p.setMinValue("1");
    p.setMaxValue("255");
    hm.put("flasher_brightness", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("127");
    p.setMinValue("1");
    p.setMaxValue("127");
    hm.put("flasher_pulse_width", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("100");
    p.setMinValue("1");
    p.setMaxValue("5000");
    hm.put("led_trig_cnt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("predicted_current", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("max_current_err", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("worst_led", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("led_avg_current", p);

    ret.put("fadc_fe_pulser", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("700");
    p.setMinValue("0");
    p.setMaxValue("1023");
    hm.put("fadc_reference_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("5000");
    p.setMinValue("0");
    p.setMaxValue("5000000");
    hm.put("pulser_amplitude_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("100");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("triggerable_spe_dac", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_mean", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_width", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_position", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("fadc_fe_pulser_waveform", p);

    ret.put("fadc_baseline", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("700");
    p.setMinValue("0");
    p.setMaxValue("1023");
    hm.put("fadc_reference_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("110");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_mean", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_rms", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_min", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_max", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("fadc_baseline_histogram", p);

    ret.put("fadc_fe_forced", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("700");
    p.setMinValue("0");
    p.setMaxValue("1023");
    hm.put("fadc_reference_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("5000");
    p.setMinValue("0");
    p.setMaxValue("5000000");
    hm.put("pulser_amplitude_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedLong"));
    p.setDefaultValue("100");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_baseline_mean", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_amplitude", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_width", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("fadc_fe_pulser_position", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("fadc_fe_pulser_waveform", p);

    ret.put("disc_scan", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("5000");
    p.setMinValue("0");
    hm.put("disc_scan_window_uvolts", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("disc_spe_or_mpe", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("5000");
    p.setMinValue("0");
    p.setMaxValue("5000000");
    hm.put("pulser_amplitude_uvolt", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("78000");
    p.setMinValue("600");
    p.setMaxValue("80000");
    hm.put("pulser_rate_hz", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_edge_pos", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_begin_flat_range", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_end_flat_range", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_flat_range_rate", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_noise_band", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("disc_scan_noise_uvolt", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("disc_sum_waveform", p);

    ret.put("loopback_lc", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_quiet", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_dwn_hi", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_dwn_lo", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_hi", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_lo", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_hi_dwn_high", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_hi_dwn_lo", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_lo_dwn_lo", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("lc_tx_up_lo_dwn_hi", p);

    ret.put("atwd_pedestal_noise", hm=new HashMap());
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("850");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_sampling_speed_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2097");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_top_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("3000");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_ramp_bias_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("2048");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_analog_ref_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1925");
    p.setMinValue("0");
    p.setMaxValue("4095");
    hm.put("atwd_pedestal_dac", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1");
    p.setMinValue("0");
    p.setMaxValue("1");
    hm.put("atwd_chip_a_or_b", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("0");
    p.setMinValue("0");
    p.setMaxValue("2");
    hm.put("atwd_channel", p);
    p = new STFParameter( true );
    p.setType(STFParameterType.getType("unsignedInt"));
    p.setDefaultValue("1000");
    p.setMinValue("1");
    p.setMaxValue("1000000");
    hm.put("loop_count", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("noise_positive_max", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("noise_negative_max", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("noise_rms", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("noise_mean", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("error_histogram", p);

    ret.put("tcal", hm=new HashMap());
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("round_trip_time", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedInt"));
    hm.put("round_trip_error", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("dom_waveforms", p);
    p = new STFParameter( false );
    p.setType(STFParameterType.getType("unsignedIntArray"));
    hm.put("dor_waveforms", p);

    return ret;
  } /* mkLookup */
} /* STFParameterLookup */
