package com.finance.pms.events.scoring.functions;

import java.util.Date;

//XXX
public class OutputCalculatorDiscret implements OutputCalculator {

	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {
		return prevValue;
	}

}
