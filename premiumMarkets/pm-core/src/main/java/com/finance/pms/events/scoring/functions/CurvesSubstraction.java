package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class CurvesSubstraction implements CurvesOperation, CurvesConstantOperation {
	
	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> data1, SortedMap<Date, double[]> data2) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		for (Date date : data1.keySet()) {
			double[] ds2 = data2.get(date);
			if (ds2 != null) {
				double[] ds1 = data1.get(date);
				ret.put(date, new double[]{ds1[0] - ds2[0]});
			}
		}
		
		return ret;
	}

	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> data1, double d) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		for (Date date : data1.keySet()) {
			double[] ds1 = data1.get(date);
			ret.put(date, new double[]{ds1[0] - d});
		}

		return ret;
	}

}
