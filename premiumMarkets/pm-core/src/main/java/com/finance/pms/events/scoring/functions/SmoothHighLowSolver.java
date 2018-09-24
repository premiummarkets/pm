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

public class SmoothHighLowSolver implements HighLowSolver {

	//private static MyLogger LOGGER = MyLogger.getLogger(SmoothHighLowSolver.class);

	Function<Double, Function<Double, Function<Double, Boolean>>> peak = l -> e -> r -> l < e && e >= r;
	Function<Double, Function<Double, Function<Double, Boolean>>> trough = l -> e -> r -> l > e && e <= r;
	BiFunction<Double, Double, Boolean> inferiorOrEqual = (a, b) -> a <= b;
	BiFunction<Double, Double, Boolean> superiorOrEqual = (a, b) -> a >= b;
	BiFunction<Double, Double, Boolean> inferior = (a, b) -> a < b;
	BiFunction<Double, Double, Boolean> superior = (a, b) -> a > b;

	@Override
	public Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean hls = calculateHHAndLL(
				peak, inferiorOrEqual, superiorOrEqual, inferior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);

		return hls;
	}

	@Override
	public Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = -Double.MAX_VALUE;
		if (maxSlope.isNaN()) maxSlope = 0d;

		Boolean hls = calculateHHAndLL(
				trough, superiorOrEqual, inferiorOrEqual, superior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return hls;
	}

	@Override
	public Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (minSlope.isNaN()) minSlope = 0d;
		if (maxSlope.isNaN()) maxSlope = Double.MAX_VALUE;

		Boolean calculateLHAndHL = calculateLHAndHL(
				trough, superior, inferiorOrEqual, inferiorOrEqual, superior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return calculateLHAndHL;
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
		if (minSlope.isNaN()) minSlope = -Double.MAX_VALUE;
		if (maxSlope.isNaN()) maxSlope = 0d;

		Boolean calculateLHAndHL = calculateLHAndHL(
				peak, inferior, superiorOrEqual, superiorOrEqual, inferior, 3,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				minSlope, maxSlope);
		return calculateLHAndHL;
	}

	@Override
	public Boolean flatHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fhs = calculateFHAndFL(
				peak,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				tolerance);

		return fhs;
	}
	
	@Override
	public Boolean flatLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double tolerance
			) {

		if (lowestStart.isNaN()) lowestStart = -Double.MAX_VALUE;
		if (highestStart.isNaN()) highestStart = Double.MAX_VALUE;
		if (lowestEnd.isNaN()) lowestEnd = -Double.MAX_VALUE;
		if (highestEnd.isNaN()) highestEnd = Double.MAX_VALUE;
		if (tolerance.isNaN()) tolerance = 0d;

		Boolean fhs = calculateFHAndFL(
				trough,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent,
				lowestStart, highestStart, lowestEnd, highestEnd,
				tolerance);

		return fhs;
	}

	//Flats
	private Boolean calculateFHAndFL(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double tolerance) {

		//Smooth
		SortedMap<Integer, Double> zEMASmoothed = calculateSmooth(data, smoothingPeriod);

		//Knots
		SortedMap<Integer, Double> knots = calculateKnots(zEMASmoothed, aKnotIsA);

		//
		if (knots.isEmpty()) return false;

		//check that the last knot is not the last data point and within the required lowest - highest band.
		//check that all previous knot are within tolerance to the right most.
		List<Integer> knotsAbs = new ArrayList<>(knots.keySet());
		ListIterator<Integer> knotsAbsIterator = knotsAbs.listIterator(knotsAbs.size());

		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotsAbsIterator, lowestEnd, highestEnd);
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer validLeftMostKnotAbs = null;
		while (knotsAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotsAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Checking next left knots against the right most.
			Double diff = Math.abs(nextLeftKnot - rightMostKnot);
			Double largest = Math.max(Math.abs(nextLeftKnot), Math.abs(rightMostKnot));
			if ( diff > largest*tolerance) return false;

			//Checking the selected left knots against band and distance to right most.
			if ((rightMostKnotAbs - nextLeftKnotAbs) >= minimumNbDaysBetweenExtremes && lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart) {
				validLeftMostKnotAbs = nextLeftKnotAbs;
			}

		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			_expertTangent.setSlope(0d);
			_expertTangent.setIntersect(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
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
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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
			if (rightMostKnotIsNotToLefts.apply(rightMostKnot, nextLeftKnot)) return false;

			//Checking the selected left knots against the in between ones, band and distance to right most.
			if ( leftMostKnotAbs == null || leftMostKnotIsToInners.apply(nextLeftKnot, knots.get(leftMostKnotAbs)) ) {
				if ((rightMostKnotAbs - nextLeftKnotAbs) >= minimumNbDaysBetweenExtremes && lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart) {
					validLeftMostKnotAbs = nextLeftKnotAbs;
					//leftMostKnotAbs = nextLeftKnotAbs; //Uncomment for test
				}
				leftMostKnotAbs = nextLeftKnotAbs;
			}
		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			boolean isValidSlope =
					isValidSlope(
							zEMASmoothed, minSlope, maxSlope,
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
			SortedMap<Integer, Double> data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
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
		ListIterator<Integer> knotAbsIterator = knotsAbs.listIterator(knotsAbs.size());

		Integer rightMostKnotAbs = pickRightMostKnotInBand(knots, knotAbsIterator, lowestEnd, highestEnd);
		if (rightMostKnotAbs == null) return false;
		Double rightMostKnot = knots.get(rightMostKnotAbs);

		Integer leftMostKnotAbs = null;
		Integer validLeftMostKnotAbs = null;
		Double mostAntiKnot = Double.MAX_VALUE;
		while (knotAbsIterator.hasPrevious()) {

			Integer nextLeftKnotAbs = knotAbsIterator.previous();
			Double nextLeftKnot = knots.get(nextLeftKnotAbs);

			//Update the most opposite knot value (ie lowest for highest and vice versa) to right most
			mostAntiKnot = (knotIsToAntiKnot.apply(nextLeftKnot, mostAntiKnot))?nextLeftKnot:mostAntiKnot;

			//Check left knot against its right neighbor, against the in between ones, band and distance to right most.
			//For the in between ones, we check that left most and right most are ratio equidistant in value from opposite knot value.
			//Also check that the resulting tangent is above (LH case) or below (HL case).
			if ( (leftMostKnotAbs == null && leftKnotIsToRight.apply(nextLeftKnot, rightMostKnot)) || (leftMostKnotAbs != null && leftKnotIsToRight.apply(nextLeftKnot, knots.get(leftMostKnotAbs))) ) {
				Boolean troughRatio = (nextLeftKnot-mostAntiKnot)/(rightMostKnot-mostAntiKnot) <= knotToAntiKnotRatio;
				if (
						lowestStart <= nextLeftKnot && nextLeftKnot <= highestStart && (rightMostKnotAbs - nextLeftKnotAbs) >= minimumNbDaysBetweenExtremes && troughRatio &&
						validLine(knots, tangentIsToKnots, rightMostKnotAbs, leftMostKnotAbs, nextLeftKnotAbs)
						) {
					validLeftMostKnotAbs = nextLeftKnotAbs;
					//leftMostKnotAbs = nextLeftKnotAbs; //Uncomment for test
				}
				leftMostKnotAbs = nextLeftKnotAbs;
			}
		}

		//Update output map
		if (validLeftMostKnotAbs == null) return false;
		_higherHighs.put(validLeftMostKnotAbs, knots.get(validLeftMostKnotAbs));
		_higherHighs.put(rightMostKnotAbs, rightMostKnot);

		//Slope
		if (_higherHighs.size() >= 2) {
			boolean isValidSlope =
					isValidSlope(
							zEMASmoothed, minSlope, maxSlope,
							tangentIsNotToKnots, _higherHighs, _expertTangent);
			return isValidSlope;
		}

		return false;
	}

	private Boolean validLine(SortedMap<Integer, Double> knots, BiFunction<Double, Double, Boolean> tangentIsToKnots, Integer rightMostKnotAbs, Integer leftMostKnotAbs, Integer nextLeftKnotAbs) {
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
			double minSlope, double maxSlope,
			BiFunction<Double, Double, Boolean> tangentIsNotToKnots,
			SortedMap<Integer, Double> higherHighs, Line<Integer, Double> tangent) {

		Integer xStart = higherHighs.firstKey();
		double startPeak = zEMASmoothed.get(xStart);
		Integer xEnd = higherHighs.lastKey();
		double endPeak = zEMASmoothed.get(xEnd);
		double slope = (endPeak - startPeak)/(double)(xEnd - xStart);
		if (minSlope > slope || slope > maxSlope) return false;

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
