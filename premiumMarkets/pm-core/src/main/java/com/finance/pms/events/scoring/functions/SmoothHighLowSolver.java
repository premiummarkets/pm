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

//TODO unleash tolerance and slope check

/**
 * 
 * @author Guillaume Thoreton
 *
 */
@SuppressWarnings("Duplicates")
public class SmoothHighLowSolver implements HighLowSolver {

	//private static MyLogger LOGGER = MyLogger.getLogger(SmoothHighLowSolver.class);

	private Function<Double, Function<Double, Function<Double, Boolean>>> peak = l -> e -> r -> l < e && e >= r;
	private Function<Double, Function<Double, Function<Double, Boolean>>> trough = l -> e -> r -> l > e && e <= r;
	private BiFunction<Double, Double, Boolean> inferiorOrEqual = (a, b) -> a <= b;
	private BiFunction<Double, Double, Boolean> superiorOrEqual = (a, b) -> a >= b;
	private BiFunction<Double, Double, Boolean> inferior = (a, b) -> a < b;
	private BiFunction<Double, Double, Boolean> superior = (a, b) -> a > b;

	private Function<Double, Function<Double, Function<Double, Boolean>>> isInTolerance = l -> r -> t -> r < l*(1 + t) && l < r*(1 + t);

	@Override
	public Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
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
				peak, superior, inferiorOrEqual, superiorOrEqual, inferior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hh;
	}

	@Override
	public Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
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
				trough, inferior, superiorOrEqual, inferiorOrEqual, superior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return ll;
	}

	@Override
	public Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
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
				trough, superior, inferiorOrEqual, inferiorOrEqual, superior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hl;
	}

	@Override
	public Boolean lowerHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean lh = calculateLHAndHL(
				peak, inferior, superiorOrEqual, superiorOrEqual, inferior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return lh;
	}

	@Override
	public Boolean flatHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fhs = calculateFHAndFL(
				peak, cutsAboveSupport, isInTolerance,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, tolerance);

		return fhs;
	}

	@Override
	public Boolean flatLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fhs = calculateFHAndFL(
				trough, cutsBelowSupport, isInTolerance,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, tolerance);

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

	//HigherHigh LowerLow
	private Boolean calculateHHAndLL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			BiFunction<Double, Double, Boolean> rightMostKnotIsNotToLefts,
			BiFunction<Double, Double, Boolean> leftMostKnotIsToInners,
			BiFunction<Double, Double, Boolean> tangentIsNotToKnots,
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Knots
		SortedMap<Integer, Double> knots = calculateKnots(zEMASmoothed, aKnotIsA);

		//
		if (knots.isEmpty()) return false;

		//check that the last peak is not the last data point and within the required lowest - highest band.
		//check that all previous peaks are strictly lower (HH case) or higher (LL case)
		//check the second highest (HH case) happens minimumNbDaysBetweenExtremes before
		List<Integer> knotsAbs = new ArrayList<>(knots.keySet());
		ListIterator<Integer> knotsAbsIterator = knotsAbs.listIterator(knotsAbs.size());

		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotsAbsIterator, lowestEnd, highestEnd);
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer leftMostKnotAbs = null;
		Integer validLeftMostKnotAbs = null;
		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Checking next left knots against the right most.
			Boolean isNotToRightMost = rightMostKnotIsNotToLefts.apply(rightMostKnot, nextLeftKnot);
			if (isNotToRightMost) {
				return false; //Uncomment to disallow tangent cut through on the left.
				//if (validLeftMostKnotAbs == null) return false; else break; //Uncomment to allow tangent cut through on the left.
			}

			//Checking the selected left knots against the in between ones, band and distance to right most.
			Boolean isWithinBand = lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart;
			Boolean isAwayFromRightMost = (rightMostKnotAbs - nextLeftKnotAbs) >= minimumNbDaysBetweenExtremes;
			Boolean isToRightInners = leftMostKnotIsToInners.apply(nextLeftKnot, knots.get(leftMostKnotAbs));
			if ( leftMostKnotAbs == null || (isWithinBand && isAwayFromRightMost && isToRightInners) ) {
					validLeftMostKnotAbs = nextLeftKnotAbs;
					//leftMostKnotAbs = nextLeftKnotAbs; //Uncomment for test
			}
			leftMostKnotAbs = nextLeftKnotAbs;
		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			boolean isValidSlope =
					isValidSlope(
							zEMASmoothed, leftMostKnotIsToInners, minSlope, maxSlope,
							tangentIsNotToKnots, _higherHighs, _expertTangent);
			return isValidSlope;
		}

		return false;
	}

	//LowerHigh HigherLow
	private Boolean calculateLHAndHL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			BiFunction<Double,Double, Boolean> knotIsToAntiKnot,
			BiFunction<Double,Double, Boolean> leftKnotIsToRight,
			BiFunction<Double,Double, Boolean> tangentIsToKnots,
			BiFunction<Double,Double, Boolean> tangentIsNotToKnots,
			double knotToAntiKnotRatio,
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes,
			SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Knots
		SortedMap<Integer, Double> knots = calculateKnots(zEMASmoothed, aKnotIsA);

		//
		if (knots.isEmpty()) return false;

		//Find the knot further left higher than last (LH case)
		//Check the minimumNbDaysBetweenExtremes distance and exit if not met
		List<Integer> knotsAbs = new ArrayList<>(knots.keySet());
		ListIterator<Integer> knotsAbsIterator = knotsAbs.listIterator(knotsAbs.size());

		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotsAbsIterator, lowestEnd, highestEnd);
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer leftMostKnotAbs = null;
		Integer validLeftMostKnotAbs = null;
		Double mostAntiKnot = Double.MAX_VALUE;
		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Update the most opposite knot value (ie lowest for highest and vice versa) to right most
			mostAntiKnot = (knotIsToAntiKnot.apply(nextLeftKnot, mostAntiKnot))?nextLeftKnot:mostAntiKnot;

			//Check left knot against its right neighbor, against the in between ones, band and distance to right most.
			//For the in between ones, we check that left most and right most are ratio equidistant in value from opposite knot value.
			//Also check that the resulting tangent is above (LH case) or below (HL case).
			Boolean troughRatio = (nextLeftKnot-mostAntiKnot)/(rightMostKnot-mostAntiKnot) <= knotToAntiKnotRatio;
			Boolean isNotToRightMost = !leftKnotIsToRight.apply(nextLeftKnot, rightMostKnot);
			if (isNotToRightMost && !troughRatio) {
				//return false;
				if (validLeftMostKnotAbs == null) return false; else break;
			}
			Boolean isWithinBand = lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart;
			Boolean isAwayFromRightMost = (rightMostKnotAbs - nextLeftKnotAbs) >= minimumNbDaysBetweenExtremes;
			Boolean isToRightInners = validLine(knots, tangentIsToKnots, rightMostKnotAbs, leftMostKnotAbs, nextLeftKnotAbs);
			if ( isWithinBand && isAwayFromRightMost && isToRightInners ) {
				validLeftMostKnotAbs = nextLeftKnotAbs;
				//leftMostKnotAbs = nextLeftKnotAbs; //Uncomment for test
			}
			leftMostKnotAbs = nextLeftKnotAbs;
		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			boolean isValidSlope =
					isValidSlope(
							zEMASmoothed, leftKnotIsToRight, minSlope, maxSlope,
							tangentIsNotToKnots, _higherHighs, _expertTangent);
			return isValidSlope;
		}

		return false;
	}

	private Boolean validLine(
			SortedMap<Integer, Double> knots, BiFunction<Double, Double, Boolean> tangentIsToKnots,
			Integer rightMostKnotAbs, Integer leftMostKnotAbs, Integer nextLeftKnotAbs) {
		Boolean validLine = true;
		if (leftMostKnotAbs != null ) {
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);
			Double rightMostKnot = knots.get(rightMostKnotAbs);
			double slope = (nextLeftKnot - rightMostKnot)/(double)(nextLeftKnotAbs - rightMostKnotAbs);
			double lineY = rightMostKnot + slope*(leftMostKnotAbs - rightMostKnotAbs);
			validLine = tangentIsToKnots.apply(lineY, knots.get(leftMostKnotAbs));
		}
		return validLine;
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
	private boolean isValidSlope(
			SortedMap<Integer, Double> zEMASmoothed,
			BiFunction<Double,Double, Boolean> zeroIsToSlope,
			double lowTolerance, double highTolerance,
			BiFunction<Double, Double, Boolean> tangentIsNotToKnots,
			SortedMap<Integer, Double> higherHighs, Line<Integer, Double> tangent) {

		Integer xStart = higherHighs.firstKey();
		double startPeak = zEMASmoothed.get(xStart);
		Integer xEnd = higherHighs.lastKey();
		double endPeak = zEMASmoothed.get(xEnd);
		double slope = (endPeak - startPeak)/(double)(xEnd - xStart);

		Boolean isRightSign = zeroIsToSlope.apply(0d, slope);
		if (!isRightSign) return false;

//		Boolean isTolerated =
//				(slope >= 0 && startPeak*(1 + lowTolerance/(xEnd-xStart)) <= endPeak && endPeak <= startPeak*(1 + highTolerance/(xEnd-xStart))) ||
//				(slope <= 0 && endPeak*(1 + lowTolerance/(xEnd-xStart)) <= startPeak && startPeak <= endPeak*(1 + highTolerance/(xEnd-xStart)));
//		if (!isTolerated) return false;

		tangent.setSlope(slope);
		tangent.setxEnd(xEnd);

		NavigableSet<Integer> smoothedXes = (NavigableSet<Integer>) MapUtils.subMapInclusive(zEMASmoothed, xStart, xEnd).keySet();
		Iterator<Integer> listIterator = smoothedXes.descendingIterator();
		Integer i = xEnd;
		while(listIterator.hasNext()) {
			i = listIterator.next();
			double lineY = startPeak + slope*(i - xStart);
			if (tangentIsNotToKnots.apply(lineY, zEMASmoothed.get(i))) {
				if (xStart < i) return false;
				break; //Comment out for test
			}
		}

		tangent.setIntersect(i, zEMASmoothed.get(i));
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
