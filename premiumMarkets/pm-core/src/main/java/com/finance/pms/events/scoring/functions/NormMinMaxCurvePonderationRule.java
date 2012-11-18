package com.finance.pms.events.scoring.functions;

import org.apache.commons.lang.NotImplementedException;

class NormMinMaxCurvePonderationRule implements CurvePonderationRule {
	
	double ponderatorCenter;
	double ponderatorMin;
	double ponderatorMax;
	
	public NormMinMaxCurvePonderationRule(double ponderatorMin, double ponderatorCenter, double ponderatorMax) {
		super();
		this.ponderatorMin = ponderatorMin;
		this.ponderatorMax = ponderatorMax;
		this.ponderatorCenter = ponderatorCenter;
	}

	@Override
	public double weight(double curveValue, double ponderatorValue) {
		
		if (ponderatorValue < 0) throw new NotImplementedException("Ponderation values must be normalise between positive min and max.");
		double weight = 1;
		if (ponderatorValue >= ponderatorCenter) {
			weight = ponderatorMax - ponderatorValue;
		} else {
			weight = ponderatorValue - ponderatorMin;
		}
		
		return curveValue*weight;
	}
	
	
}