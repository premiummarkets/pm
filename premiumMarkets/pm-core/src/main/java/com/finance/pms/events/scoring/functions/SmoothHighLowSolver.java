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
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.finance.pms.events.calculation.util.MapUtils;

/**
 * 
 * @author Guillaume Thoreton
 *
 */
//@SuppressWarnings("Duplicates")
public class SmoothHighLowSolver implements HighLowSolver {

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

	//Pick up right most knot within the required lowest - highest band.
	private Integer pickRightMostKnotInBand(SortedMap<Integer, Double> knots, ListIterator<Integer> knotsAbsIterator, Double lowestEnd, Double highestEnd) {
		Integer rightMostKnotAbs = null;
		while(knotsAbsIterator.hasPrevious()) {
			Integer previousKnotAbs = knotsAbsIterator.previous();
			Double previousKnot = knots.get(previousKnotAbs);
			if (lowestEnd <= previousKnot && previousKnot <= highestEnd) {
				rightMostKnotAbs = previousKnotAbs;
				break;
			}
		}
		return rightMostKnotAbs;
	}

	//LowerHigh HigherLow
	private Boolean calculateLHAndHL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			BiFunction<Double,Double, Boolean> zeroIsToSlope,
			BiFunction<Double,Double, Boolean> tangentIsNotToData,
			SortedMap<Integer, Double> data,
			int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Knots
		SortedMap<Integer, Double> knots = calculateKnots(zEMASmoothed, aKnotIsA);
		if (knots.isEmpty()) return false;

		List<Integer> knotsAbs = new ArrayList<>(knots.keySet());
		ListIterator<Integer> knotsAbsIterator = knotsAbs.listIterator(knotsAbs.size());

		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotsAbsIterator, lowestEnd, highestEnd);
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer validLeftMostKnotAbs = null;
		Line<Integer, Double> _tangent = new Line<>();
		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Check band and distance to right most and tangent slope, surface and intersections and position against knots
			Boolean isWithinBand = lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart;
			Boolean isValidTangent =
					isValidTangent(
							knots, zEMASmoothed,
							zeroIsToSlope, minSlope, maxSlope, 
							tangentIsNotToData, minimumSurfaceOfChange,
							nextLeftKnotAbs, rightMostKnotAbs, _tangent);
			if ( isWithinBand && isValidTangent ) {
				validLeftMostKnotAbs = nextLeftKnotAbs;
				if (Greed.LAZY.equals(greed)) break; //exit at first finding if lazy
				if (Greed.FULL.equals(greed)) { //Update output map for each finding (if full)
					_expertTangent.add(_tangent);
					_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
					_higherHighs.put(rightMostKnotAbs, rightMostKnot);
					_tangent = new Line<>();
				}
			}

		}

		if (validLeftMostKnotAbs == null) return false;

		//Update output map (greedy && lazy)
		if(Greed.GREEDY.equals(greed) || Greed.LAZY.equals(greed)) {
			_expertTangent.add(_tangent);
			_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
			_higherHighs.put(rightMostKnotAbs, rightMostKnot);
		}

		return true;
	}

	private SortedMap<Integer, Double> calculateKnots(
			SortedMap<Integer, Double> zEMASmoothed,
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA) {

		SortedMap<Integer, Double> peaks = new TreeMap<>();
		ArrayList<Integer> smoothedKeys = new ArrayList<>(zEMASmoothed.keySet());
		for (int i = 1; i < smoothedKeys.size() - 1; i++) {
			if (aKnotIsA.apply(zEMASmoothed.get(smoothedKeys.get(i-1))).apply(zEMASmoothed.get(smoothedKeys.get(i))).apply(zEMASmoothed.get(smoothedKeys.get(i+1)))) {
				Integer peakKeyInDataIdx = smoothedKeys.get(i); //+ smoothingPeriod/2;
				peaks.put(peakKeyInDataIdx, zEMASmoothed.get(smoothedKeys.get(i)));
			}
		}

		return peaks;
	}

	//Check that the slope does not cross inner knots and calculate line intersect, slope and boundaries
	private boolean isValidTangent(
			SortedMap<Integer, Double> knots, SortedMap<Integer, Double> zEMASmoothed,
			BiFunction<Double,Double, Boolean> zeroIsToSlope, double lowSlopeTolerance, double highSlopeTolerance,
			BiFunction<Double, Double, Boolean> tangentIsNotToData, double minSurface,
			Integer xStart, Integer xEnd, Line<Integer, Double> _tangent) {

		double yKnotStart = zEMASmoothed.get(xStart);
		double yKnotEnd = zEMASmoothed.get(xEnd);
		double slope = (yKnotEnd - yKnotStart)/(double)(xEnd - xStart);

		Boolean isRightSign = zeroIsToSlope.apply(0d, slope);
		if (!isRightSign) return false;

		//Daily relative difference tolerance
		double maxAbsYknot = Math.max(Math.abs(yKnotStart), Math.abs(yKnotEnd));
		Double relativeDifference = (yKnotStart == 0 && yKnotEnd == 0)?0d:Math.abs(yKnotEnd - yKnotStart)/maxAbsYknot;
		Boolean isTolerated = highSlopeTolerance >= relativeDifference && relativeDifference >= lowSlopeTolerance;
		if (!isTolerated) return false;

		SortedMap<Integer, Double> dataSubMap = MapUtils.subMapInclusive(zEMASmoothed, xStart, xEnd);
		NavigableSet<Integer> xes = (NavigableSet<Integer>) dataSubMap.keySet();
		Iterator<Integer> listIterator = xes.descendingIterator();
		Integer xData = xEnd;

		//Tangent left cut
		while(listIterator.hasNext()) {

			xData = listIterator.next();
			Double yData = zEMASmoothed.get(xData);

			double yLine = yKnotStart + slope*(xData - xStart);
			if (tangentIsNotToData.apply(yLine, yData)) {//left cut knot reached
				//We check further left up to, potentially, xStart (left most extreme). If xData is not a knot and no other knot is met in between XSart and xData, it is making a valid tangent.
				while(listIterator.hasNext()) {
					if (xData <= xStart) break; //We have reach leftMost : ok
					if (knots.containsKey(xData)) return false; //An other knot is met in between : nok
					xData = listIterator.next();
				}
				if (xData <= xStart) break;
			}

		}

		//Daily % of change surface
		Double surfaceOfChange = surfaceOfPolygone(dataSubMap)/maxAbsYknot;
		if (surfaceOfChange < minSurface) return false;

		_tangent.setSlope(slope);
		_tangent.setxEnd(xEnd);
		_tangent.setIntersect(xData, yKnotStart + slope*(xData - xStart));

		//Test
		Double lowKnot = Math.min(yKnotStart, yKnotEnd);
		Double highKnot = Math.max(yKnotStart, yKnotEnd);
		_tangent.setToleranceCriterias(lowKnot, highKnot, relativeDifference);
		_tangent.setSurface(surfaceOfChange);

		return true;
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

	private Double surfaceOfPolygone(SortedMap<Integer, Double> vertices) {
		//Shoelace formula
		Double surface = 0d;
		List<Integer> xs = new ArrayList<>(vertices.keySet());
		List<Double> ys = new ArrayList<>(vertices.values());
		int n = vertices.size()-1;
		for (int i = 0; i <= n-1; i++) {
			surface = surface + xs.get(i)*ys.get(i+1);
		}
		surface = surface + xs.get(n)*ys.get(0);
		for (int i = 0; i <= n-1; i++) {
			surface = surface - xs.get(i+1)*ys.get(i);
		}
		surface = surface - xs.get(0)*ys.get(n);

		return Math.abs(surface)/2;
	}

}
