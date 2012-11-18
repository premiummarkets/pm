package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;


public class CurvePonderator implements CurvesOperation {
	
	NormMinMaxCurvePonderationRule curvePonderationRule;	

	public CurvePonderator(NormMinMaxCurvePonderationRule curvePonderationRule) {
		super();
		this.curvePonderationRule = curvePonderationRule;
	}


	@Override
	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> curve, SortedMap<Date, double[]> ponderator) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		for (Date date : curve.keySet()) {
			double[] ponderatorValue = ponderator.get(date);
			if (ponderatorValue != null) {
				double[] curveValue = curve.get(date);
				ret.put(date, new double[]{curvePonderationRule.weight(curveValue[0], ponderatorValue[0])});
			}
		}
		
		return ret;
	}

}
