package com.finance.pms.events.scoring.functions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class MyPolynomialRegression implements StatsFunction {

	private static double DAY_IN_MILLI = 24*60*60*1000;
	private int degree = 2;

	@Override
	public double dEvaluateMd(SortedMap<Date, Double> lookBack) {
		
		double firstX = lookBack.firstKey().getTime()/DAY_IN_MILLI;
		double lastX = lookBack.lastKey().getTime()/DAY_IN_MILLI;
		
		WeightedObservedPoints obs = polynomialRegression(lookBack, firstX);
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
        double[] coeff = fitter.fit(obs.toList());
		PolynomialFunction polynomialFunction = new PolynomialFunction(coeff);
		return polynomialFunction.value(lastX);
	}

	private WeightedObservedPoints polynomialRegression(SortedMap<Date, Double> lookBack, double firstX) {
		WeightedObservedPoints obs = new WeightedObservedPoints();
		lookBack.keySet().stream().forEach(k -> {
			Double y = lookBack.get(k);
			if (!Double.isNaN(y)) {
				obs.add(k.getTime()/DAY_IN_MILLI - firstX, y);
			}
		});
		return obs;
	}

	@Override
	public SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap) {
		
		double firstX = subMap.firstKey().getTime()/DAY_IN_MILLI;
		
		WeightedObservedPoints obs = polynomialRegression(subMap, firstX);
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
        double[] coeff = fitter.fit(obs.toList());
		PolynomialFunction polynomialFunction = new PolynomialFunction(coeff);
		
		TreeMap<Date, Double> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> {
			double currentX = k.getTime()/DAY_IN_MILLI;
			return polynomialFunction.value(currentX);
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
		return Arrays.asList("mypolynomialregression");
	}

	@Override
	public int getMinPeriod() {
		return 2;
	}

	@Override
	public String getName() {
		return "My Polynomial Regression";
	}

}
