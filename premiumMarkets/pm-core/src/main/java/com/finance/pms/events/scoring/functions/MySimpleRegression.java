package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class MySimpleRegression implements StatsFunction {

	private static double DAY_IN_MILLI = 24*60*60*1000;

	@Override
	public double mEvaluate(SortedMap<Date, Double> lookBack) {
		
		double firstX = lookBack.firstKey().getTime()/DAY_IN_MILLI;
		double lastX = lookBack.lastKey().getTime()/DAY_IN_MILLI;
		
		SimpleRegression simpleRegression = simpleRegression(lookBack, firstX);
		double intercept = simpleRegression.getIntercept();
		double slope = simpleRegression.getSlope();

		return intercept + slope*(lastX-firstX);
	}

	private SimpleRegression simpleRegression(SortedMap<Date, Double> lookBack, double firstX) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		lookBack.keySet().stream().forEach(k -> {
			simpleRegression.addData(k.getTime()/DAY_IN_MILLI - firstX, lookBack.get(k));
		});
		return simpleRegression;
	}

	@Override
	public SortedMap<Date, Double> evaluate(SortedMap<Date, Double> subMap) {
		
		double firstX = subMap.firstKey().getTime()/DAY_IN_MILLI;
		
		SimpleRegression simpleRegression = simpleRegression(subMap, firstX);
		double intercept = simpleRegression.getIntercept();
		double slope = simpleRegression.getSlope();
		
		TreeMap<Date, Double> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> {
			double currentX = k.getTime()/DAY_IN_MILLI;
			return intercept + slope*(currentX-firstX);
		}, (a, b) -> a, TreeMap<Date,Double>::new));
		
		return collected;
	}

}
