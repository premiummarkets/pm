package com.finance.pms.events.scoring.functions;

import java.util.Date;

import com.finance.pms.events.scoring.functions.CurveTransformator.Extremity;

public class OutputCalculatorNull implements OutputCalculator {

	public int noticablePeriodBand;

	public OutputCalculatorNull(int noticablePeriodBand) {
		super();
		this.noticablePeriodBand = noticablePeriodBand;
	}

	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {

		if (currentTime.equals(prevExtremDate)) {
			return prevValue;
		} else 
			if (currentTime.equals(nextExtremDate)) {
				return nextValue;
			} else {
				return Extremity.UNKNOWN.getExValue();
			}
	}

}
