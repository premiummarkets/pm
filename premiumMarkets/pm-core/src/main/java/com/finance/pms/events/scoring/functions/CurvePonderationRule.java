package com.finance.pms.events.scoring.functions;

public interface CurvePonderationRule {
	
	public double weight(double curveValue, double ponderatorValue);

}
