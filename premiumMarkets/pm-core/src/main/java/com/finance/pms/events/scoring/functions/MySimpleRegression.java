package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class MySimpleRegression implements StatsFunction {

	private static double DAY_IN_MILLI = 24*60*60*1000;

	@Override
	public double mEvaluate(SortedMap<Date, Double> lookBack) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		double firstX = lookBack.firstKey().getTime()/DAY_IN_MILLI;
		lookBack.keySet().stream().forEach(k -> {
			simpleRegression.addData(k.getTime()/DAY_IN_MILLI - firstX, lookBack.get(k));
		});

		double lastX = lookBack.lastKey().getTime()/DAY_IN_MILLI;
		return simpleRegression.getIntercept() + lastX*simpleRegression.getSlope();
	}

}
