package com.finance.pms.events.scoring.functions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class MySimpleRegression implements StatsFunction {

	private static double DAY_IN_MILLI = 24*60*60*1000;

	@Override
	public double dEvaluateMd(SortedMap<Date, Double> lookBack) {
		
		double firstX = lookBack.firstKey().getTime()/DAY_IN_MILLI;
		double lastX = lookBack.lastKey().getTime()/DAY_IN_MILLI;
		
		SimpleRegression simpleRegression = simpleRegression(lookBack, firstX);
		double intercept = simpleRegression.getIntercept();
		double slope = simpleRegression.getSlope();

		return intercept + slope * (lastX - firstX);
	}

	private SimpleRegression simpleRegression(SortedMap<Date, Double> lookBack, double firstX) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		lookBack.keySet().stream().forEach(k -> {
			Double y = lookBack.get(k);
			if (!Double.isNaN(y)) {
				simpleRegression.addData(k.getTime()/DAY_IN_MILLI - firstX, y);
			}
		});
		return simpleRegression;
	}

	@Override
	public SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap) {
		
		double firstX = subMap.firstKey().getTime()/DAY_IN_MILLI;
		
		SimpleRegression simpleRegression = simpleRegression(subMap, firstX);
		double intercept = simpleRegression.getIntercept();
		double slope = simpleRegression.getSlope();
		
		TreeMap<Date, Double> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> {
			double currentX = k.getTime()/DAY_IN_MILLI;
			return intercept + slope * (currentX - firstX);
		}, (a, b) -> a, TreeMap<Date,Double>::new));
		
		return collected;
	}
	
	@Override
	public double[] adEvaluateMd(SortedMap<Date, Double> subMap) {
		return new double[] {this.dEvaluateMd(subMap)};
	}
	
	@Override
	public SortedMap<Date, double[]> madEvaluateMd(SortedMap<Date, Double> subMap) {
		SortedMap<Date, Double> mdEvaluateMd = this.mdEvaluateMd(subMap);
		TreeMap<Date, double[]> collected = mdEvaluateMd.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> new double[] {e.getValue()}, (a, b) -> a, TreeMap<Date,double[]>::new));
		return collected;
	}

	@Override
	public List<String> getOutputsRefs() {
		return Arrays.asList("mysimpleregression");
	}

	@Override
	public int getMinPeriod() {
		return 2;
	}

	@Override
	public String getName() {
		return "MySimpleRegression";
	}

}
