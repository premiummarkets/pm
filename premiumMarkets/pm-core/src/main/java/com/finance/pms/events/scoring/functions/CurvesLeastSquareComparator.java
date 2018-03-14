package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class CurvesLeastSquareComparator {
	
	
	public double compare(SortedMap<Date, double[]> curve, SortedMap<Date, double[]> ideal) {
		
		Number leastSquare = ideal.entrySet().stream()
		.map(e -> {
			Double yIdeal = e.getValue()[0];
			if (yIdeal != Double.NaN) {
				double[] yCurveArray = curve.get(e.getKey());
				Double yCurve;
				if (yCurveArray != null && (yCurve = yCurveArray[0]) != Double.NaN) {
					return (yCurve - yIdeal)*(yCurve - yIdeal);
				}
			}
			return 0;
		})
		.reduce(0, (r, e) -> r.doubleValue() + e.doubleValue());
		
		return leastSquare.doubleValue();
		
	}

}
