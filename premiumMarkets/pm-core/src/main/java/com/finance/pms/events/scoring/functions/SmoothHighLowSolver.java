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
import java.util.stream.Collectors;

import com.finance.pms.events.calculation.util.MapUtils;

//TODO review grammar 'for' is now surface of change
//TODO review flat in light of latest changes
	//=> test against previous version??
	//=> review support break tolerance?

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

	//private Function<Double, Function<Double, Function<Double, Boolean>>> isInTolerance = l -> r -> t -> r < l*(1 + t) && l < r*(1 + t);
	//private Function<Double, BiFunction<Double, Double, Boolean>> isInTolerance = tolerance -> (reference, value) -> reference - tolerance <= value && value <= reference - tolerance;
	private BiFunction<Double, Double, Boolean> isOk = (a, b) -> true;

	@Override
	public Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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
				smoothingPeriod, minimumSurfaceOfChange, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hh;
	}

	@Override
	public Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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
				smoothingPeriod, minimumSurfaceOfChange, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return ll;
	}

	@Override
	public Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> higherHighs, Line<Integer, Double> _expertTangent,
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
				smoothingPeriod, minimumSurfaceOfChange, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hl;
	}

	@Override
	public Boolean lowerHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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
				smoothingPeriod, minimumSurfaceOfChange, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return lh;
	}

	@Override
	public Boolean flatHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

//		Boolean fhs = calculateFHAndFL(
//				peak, cutsAboveSupport, isInTolerance,
//				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
//				lowestStart, highestStart, tolerance);
		Boolean fhs = calculateLHAndHL(
				peak, isOk, inferior, data,
				smoothingPeriod, minimumSurfaceOfChange, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestStart, highestStart,
				tolerance, tolerance);

		return fhs;
	}

	@Override
	public Boolean flatLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

//		Boolean fhs = calculateFHAndFL(
//				trough, cutsBelowSupport, isInTolerance,
//				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
//				lowestStart, highestStart, tolerance);
		Boolean fhs = calculateLHAndHL(
				trough, isOk, superior, data,
				smoothingPeriod, minimumSurfaceOfChange, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestStart, highestStart,
				tolerance, tolerance);

		return fhs;
	}

	//Flats
	private Boolean calculateFHAndFL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			Function<Double, Function<Double, Function<Double, Boolean>>> leftKnotCutsSupports,
			Function<Double, Function<Double, Function<Double, Boolean>>> leftKnotIsInTolerance,
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart,
			Double tolerance) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Knots
		SortedMap<Integer, Double> knots = calculateKnots(zEMASmoothed, aKnotIsA);

		//Knots in band
		SortedMap<Integer, Double> knotsInBand = knots.entrySet().stream()
				.filter( e -> lowestStart <= e.getValue() && e.getValue() <= highestStart)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
		if (knotsInBand.isEmpty()) return false;

		//check that all previous knot are within tolerance to the most extreme knot.
		List<Integer> knotsAbs = new ArrayList<>(knotsInBand.keySet());

		ListIterator<Integer> knotsAbsIterator = knotsAbs.listIterator(knotsAbs.size());
		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotsAbsIterator, lowestStart, highestStart); //start and end are the same as it is flat..
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer validLeftMostKnotAbs = null;
		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knotsInBand.get(nextLeftKnotAbs);

			//Checking the selected left knots against break through, tolerance and distance to most extreme.
			if (leftKnotCutsSupports.apply(nextLeftKnot).apply(rightMostKnot).apply(tolerance)) {//left break through met.
				if (validLeftMostKnotAbs == null) return false; else break;
			}
			if ( rightMostKnotAbs - nextLeftKnotAbs >= minimumNbDaysBetweenExtremes && leftKnotIsInTolerance.apply(nextLeftKnot).apply(rightMostKnot).apply(tolerance) ) {
				validLeftMostKnotAbs = nextLeftKnotAbs;
			}

		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knotsInBand.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			_expertTangent.setSlope(0d);
			_expertTangent.setIntersect(validLeftMostKnotAbs, knotsInBand.get(validLeftMostKnotAbs));
			_expertTangent.setxEnd(rightMostKnotAbs);
			return true;
		}

		return false;
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
			BiFunction<Double,Double, Boolean> tangentIsNotToKnots,
			SortedMap<Integer, Double> data,
			int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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

		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Check band and distance to right most and tangent slope, surface and intersections and position against knots
			Boolean isWithinBand = lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart;
			Boolean isValidTangent =
					isValidTangent(
							zEMASmoothed, zeroIsToSlope,
							minSlope, maxSlope, tangentIsNotToKnots, minimumSurfaceOfChange,
							nextLeftKnotAbs, rightMostKnotAbs, _expertTangent);
			if ( isWithinBand && isValidTangent ) {
				validLeftMostKnotAbs = nextLeftKnotAbs;
			}

		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

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
			SortedMap<Integer, Double> zEMASmoothed,
			BiFunction<Double,Double, Boolean> zeroIsToSlope,
			double lowSlopeTolerance, double highSlopeTolerance,
			BiFunction<Double, Double, Boolean> tangentIsNotToKnots,
			double minSurface,
			Integer xStart, Integer xEnd, Line<Integer, Double> _tangent) {

		double yKnotStart = zEMASmoothed.get(xStart);
		double yKnotEnd = zEMASmoothed.get(xEnd);
		double slope = (yKnotEnd - yKnotStart)/(double)(xEnd - xStart);
		double pSlope = -1/slope;

		Boolean isRightSign = zeroIsToSlope.apply(0d, slope);
		if (!isRightSign) return false;

		Double lowestKnot = (slope >= 0)?yKnotStart:yKnotEnd;

		//Daily % of change tolerance
		double slopeRateOfChange = Math.abs(slope/lowestKnot);
		Boolean isTolerated = lowSlopeTolerance <= slopeRateOfChange && slopeRateOfChange <= highSlopeTolerance;
		if (!isTolerated) return false;

		NavigableSet<Integer> smoothedXes = (NavigableSet<Integer>) MapUtils.subMapInclusive(zEMASmoothed, xStart, xEnd).keySet();
		Iterator<Integer> listIterator = smoothedXes.descendingIterator();
		Integer xKnot = xEnd;
		//Daily % of change surface
		Double surfaceOfChange = 0d;
		while(listIterator.hasNext()) {

			xKnot = listIterator.next();
			Double yKnot = zEMASmoothed.get(xKnot);

			double yLine = yKnotStart + slope*(xKnot - xStart);
			if (tangentIsNotToKnots.apply(yLine, yKnot)) {
				if (xStart < xKnot) return false;
				break; //Comment out for test
			}

			//Surface : shortest distance from knot to tangent.
			Double xIntersection = (Double.isFinite(pSlope))?xKnot:(yKnot - yKnotStart + slope*xStart - pSlope*xKnot)/(slope - pSlope);
			//yKnotStart == yLineStart as it the left most not and intersect with it tangent.
			Double yIntersection = slope*(xIntersection-xStart) + yKnotStart;
			Double distance = Math.sqrt((xKnot-xIntersection)*(xKnot-xIntersection)+(yKnot-yIntersection)*(yKnot-yIntersection));
			surfaceOfChange = surfaceOfChange + distance/lowestKnot;

		}

		if (surfaceOfChange < minSurface) return false;

		_tangent.setSlope(slope);
		_tangent.setxEnd(xEnd);
		_tangent.setIntersect(xKnot, zEMASmoothed.get(xKnot));

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

}
