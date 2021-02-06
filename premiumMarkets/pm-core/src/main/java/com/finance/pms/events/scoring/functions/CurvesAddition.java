package com.finance.pms.events.scoring.functions;

public class CurvesAddition extends CurvesSubtraction {

	@Override
	protected double operation(double ds2, double ds1) {
		return ds1 + ds2;
	}

}
