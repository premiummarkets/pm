package com.finance.pms.events.scoring.functions;

import java.util.Date;

public class OutputCalculatorLinear implements OutputCalculator {

	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {
		
		long t0 = prevExtremDate.getTime();
		long t1 = nextExtremDate.getTime();
		long t = currentTime.getTime();
		double m = (nextValue - prevValue) / (t1- t0);
		double b = prevValue;
		return (t - t0)*m+b;
	}

}
