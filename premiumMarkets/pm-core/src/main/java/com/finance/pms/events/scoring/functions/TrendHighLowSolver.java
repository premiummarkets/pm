/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.events.calculation.util.MapUtils;

/**
 * 
 * @author Guillaume Thoreton
 *
 */
//@SuppressWarnings("Duplicates")
public class TrendHighLowSolver implements HighLowSolver {

	//private static MyLogger LOGGER = MyLogger.getLogger(SmoothHighLowSolver.class);

	private Function<Double, Function<Double, Function<Double, Boolean>>> peak = l -> e -> r -> l < e && e >= r;
	private Function<Double, Function<Double, Function<Double, Boolean>>> trough = l -> e -> r -> l > e && e <= r;
	private BiFunction<Double, Double, Boolean> inferiorOrEqual = (a, b) -> a <= b;
	private BiFunction<Double, Double, Boolean> superiorOrEqual = (a, b) -> a >= b;
	private BiFunction<Double, Double, Boolean> inferior = (a, b) -> a < b;
	private BiFunction<Double, Double, Boolean> superior = (a, b) -> a > b;

	private BiFunction<Double, Double, Boolean> isOk = (a, b) -> true;

	@Override
	public Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean hh = calculateLHAndHL(
				peak, inferiorOrEqual, inferior, data,
				smoothingPeriod, minimumSurfaceOfChange, greed, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hh;
	}

	@Override
	public Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean ll = calculateLHAndHL(
				trough, superiorOrEqual, superior, data,
				smoothingPeriod, minimumSurfaceOfChange, greed, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return ll;
	}

	@Override
	public Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean hl = calculateLHAndHL(
				trough, inferiorOrEqual, superior, data,
				smoothingPeriod, minimumSurfaceOfChange, greed, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hl;
	}

	@Override
	public Boolean lowerHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean lh = calculateLHAndHL(
				peak, superiorOrEqual, inferior, data,
				smoothingPeriod, minimumSurfaceOfChange, greed, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return lh;
	}

	@Override
	public Boolean flatHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fhs = calculateLHAndHL(
				peak, isOk, inferior, data,
				smoothingPeriod, minimumSurfaceOfChange, Greed.GREEDY, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestStart, highestStart,
				-tolerance, tolerance);

		return fhs;
	}

	@Override
	public Boolean flatLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fls = calculateLHAndHL(
				trough, isOk, superior, data,
				smoothingPeriod, minimumSurfaceOfChange, Greed.GREEDY, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestStart, highestStart,
				-tolerance, tolerance);

		return fls;
	}

	//LowerHigh HigherLow
	private Boolean calculateLHAndHL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			BiFunction<Double,Double, Boolean> zeroIsToSlope, //for up <=, for down >=
			BiFunction<Double,Double, Boolean> linearRegIsToData, //for highs <, for lows >
			SortedMap<Integer, Double> data,
			int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _tangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Linear reg
		Line<Integer, Double> lookBackLinearReg = linearReg(zEMASmoothed);

		//Relevant points filter (~knots)
		SortedMap<Integer, Double> filteredRelevantPoints = filterRelevantPoints(zEMASmoothed, lookBackLinearReg, linearRegIsToData);

		//Linear reg of knots
		Line<Integer, Double> knotsLinearReg = linearReg(filteredRelevantPoints);

		//Tests
		Boolean isRightSign = zeroIsToSlope.apply(0d, knotsLinearReg.getSlope());
		if (!isRightSign) return false;

		//Return
		_tangent.add(knotsLinearReg);
		return true;
	}

	private SortedMap<Integer, Double> filterRelevantPoints(SortedMap<Integer, Double> zEMASmoothed, Line<Integer, Double> linearReg, BiFunction<Double,Double, Boolean> linearRegIsToData) {

		SortedMap<Integer, Double> relevantPoints = new TreeMap<>();

		Integer xStart = linearReg.getxStart();
		Integer xEnd = linearReg.getxEnd();
		double slope = linearReg.getSlope();
		double intersect = linearReg.getIntersect();

		SortedMap<Integer, Double> dataSubMap = MapUtils.subMapInclusive(zEMASmoothed, xStart, xEnd);
		NavigableSet<Integer> xes = (NavigableSet<Integer>) dataSubMap.keySet();
		Iterator<Integer> listIterator = xes.descendingIterator();

		Integer xData = xEnd;
		while(listIterator.hasNext()) {

			xData = listIterator.next();
			Double yData = zEMASmoothed.get(xData);

			double yLine = intersect + slope*(xData - xStart);

			if (linearRegIsToData.apply(yLine, yData)) {
				relevantPoints.put(xData, yData);
			}

		}

		return relevantPoints;
	}

	private Line<Integer, Double> linearReg(SortedMap<Integer, Double> lookBack) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		double firstX = lookBack.firstKey();
		lookBack.keySet().stream().forEach(k -> {
			simpleRegression.addData(k - firstX, lookBack.get(k));
		});
		Line<Integer, Double> line = new Line<>();
		line.setIntersect((int) firstX, simpleRegression.getIntercept());
		line.setSlope(simpleRegression.getSlope());
		line.setxEnd((int) (lookBack.lastKey()));
		return line;
	}

	private SortedMap<Integer, Double> calculateSmooth(SortedMap<Integer, Double> data, int smoothingPeriod) {

		ArrayList<Integer> dataKeys = new ArrayList<>(data.keySet());

		double[][] arr = new double[data.size()][2];
		int i = 0;
		for(Integer l : dataKeys) arr[i++] = new double[] {data.get(l)};

		ZeroLagEMASmoother zeroLagEMASmoother = new ZeroLagEMASmoother(smoothingPeriod);
		double[][] smoothed = zeroLagEMASmoother.smooth(arr);
		int firstX = data.size() - smoothed.length;

		SortedMap<Integer, Double> result = new TreeMap<>();
		for(int j = 0; j < smoothed.length; j ++) result.put(dataKeys.get(j+firstX), smoothed[j][0]);
		return result;
	}

}
