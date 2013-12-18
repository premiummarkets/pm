package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class CurveFlip implements CurvesConstantOperation {

	@Override
	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> data1, double middle) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		
		for (Date date : data1.keySet()) {
			double[] ds1 = data1.get(date);
			ret.put(date, new double[]{2*middle - ds1[0]});
		}

		return ret;
	}

	@Override
	public SortedMap<Date, Double> sOperate(SortedMap<Date, Double> data1, Double middle) {
		
		SortedMap<Date, Double> ret = new TreeMap<Date, Double>();
		
		for (Date date : data1.keySet()) {
			Double ds1 = data1.get(date);
			ret.put(date, 2*middle - ds1);
		}

		return ret;
	}

}
