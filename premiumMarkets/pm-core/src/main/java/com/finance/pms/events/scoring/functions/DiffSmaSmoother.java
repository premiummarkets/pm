package com.finance.pms.events.scoring.functions;

import java.util.Collection;
import java.util.Date;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;

public class DiffSmaSmoother extends Smoother implements SSmoother {
	
	private int period;
	private int differenciatingPeriod;
	private Stock stock;
	private Collection<QuotationDataType> quotationDataTypes;

	public DiffSmaSmoother(Stock stock, Collection<QuotationDataType> quotationDataTypes, int period, int differenciatingPeriod) {
		super();
		this.stock = stock;
		this.quotationDataTypes = quotationDataTypes;
		this.period = period;
		this.differenciatingPeriod = differenciatingPeriod;
	}

	@Override
	public SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, Boolean fixLag) {
		throw new RuntimeException("");
	}

//    series = data.values
//    # Diff (removes colab_diff_period heading data)
//    diff_series = (series[colab_diff_period:] - series[:-colab_diff_period])
//
//    # removes window_size heading data
//    ma_o_diff = colab_moving_average(diff_series, window_size)
//
//    # removes window_size/3 heading data
//    first_diff_and_ma_header_trim = window_size + colab_diff_period
//    ma_o_past_period = int(window_size / 3)
//    ma_o_past = colab_moving_average(series[first_diff_and_ma_header_trim:-colab_diff_period], ma_o_past_period)
//
//    # removes an other colab_diff_period
//    added_past_series = ma_o_diff[colab_diff_period + ma_o_past_period:] + ma_o_past
//
//    final_period_shift = colab_diff_period + first_diff_and_ma_header_trim + ma_o_past_period

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {

		try {
			LeftShifter<double[]> leftShifter = new LeftShifter<>(stock, quotationDataTypes, -differenciatingPeriod, true);
			CurvesSubtraction curvesSubtraction = new CurvesSubtraction();
			Smoother diffSmoother = new TalibSmaSmoother(period);
			Smoother pastSmoother = new TalibSmaSmoother(period/3);
			CurvesAddition curvesAddition = new CurvesAddition();
			SortedMap<Date, double[]> shifted = leftShifter.shift(data);
			return curvesAddition.operate(diffSmoother.smooth(curvesSubtraction.operate(data, shifted), fixLag), pastSmoother.smooth(shifted, true));
		} catch (NotEnoughDataException e) {
			throw new RuntimeException(e);
		}

	} 

}
