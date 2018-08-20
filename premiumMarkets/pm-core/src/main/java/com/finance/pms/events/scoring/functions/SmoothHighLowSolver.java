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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;


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
	public Boolean higherHigh(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {
		return hls(
				peak, inferiorOrEqual, superiorOrEqual, inferior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, expertTangent);
	}
	
	@Override
	public Boolean lowerHigh(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {
		return hls(
				peak, superiorOrEqual, superiorOrEqual, inferior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, expertTangent);
	}

	@Override
	public Boolean higherLow(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {
		return hls(
				trough, inferiorOrEqual, inferiorOrEqual, superior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, expertTangent);
	}

	@Override
	public Boolean lowerLow(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {
		return hls(
				trough, superiorOrEqual, inferiorOrEqual, superior,
				data, smoothingPeriod, minimumNbDaysBetweenExtremes, higherHighs, expertTangent);
	}

	private Boolean hls(
			Function<Double, Function<Double, Function<Double, Boolean>>> aKnotIsA,
			BiFunction<Double,Double, Boolean> rightMostKnotIsNotToLefts,
			BiFunction<Double,Double, Boolean> leftMostKnotIsToInners,
			BiFunction<Double,Double, Boolean> tangentIsNotToKnots,
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {

		int lookBackSize = data.length;

		//Smooth
		ZeroLagEMASmoother zeroLagEMASmoother = new ZeroLagEMASmoother(smoothingPeriod);
		double[][] arrayArrayData = Arrays.stream(data).map(d -> new double[]{d}).toArray(double[][]::new);
		double[][] zEMASmoothed = zeroLagEMASmoother.smooth(arrayArrayData);

		//Peaks
		SortedMap<Integer, Double> peaks =  new TreeMap<>();
		for(int i = 1; i < zEMASmoothed.length-1; i++) {
			//if (zEMASmoothed[i-1][0] < zEMASmoothed[i][0] && zEMASmoothed[i][0] >= zEMASmoothed[i+1][0]) { HigherHigh && LowerHigh <= i is high to i-1 strict and i+1 loose	A:peak
			//if (zEMASmoothed[i-1][0] > zEMASmoothed[i][0] && zEMASmoothed[i][0] <= zEMASmoothed[i+1][0]) { HigherLow && LowerLow <= i is low to i-1 strict and i+1 loose		A':trough
			if ( aKnotIsA.apply(zEMASmoothed[i-1][0]).apply(zEMASmoothed[i][0]).apply(zEMASmoothed[i+1][0]) ) {
				int peakKeyInDataIdx = i; //+ smoothingPeriod/2;
				peaks.put(peakKeyInDataIdx, zEMASmoothed[peakKeyInDataIdx][0]);
			}
		}

		//HigherHighs
		if (peaks.isEmpty()) return false;

		//check the last peak not the last data point
		//check check that all previous peaks are strictly lower
		//check the second highest happens minimumNbDaysBetweenExtremes before
		List<Integer> peaksKnots = new ArrayList<>(peaks.keySet());
		ListIterator<Integer> peaksKnotsIterator = peaksKnots.listIterator(peaksKnots.size());
		Integer lastPeakKnot = peaksKnotsIterator.previous();
		Integer leftMostKnot = null;
		Integer validLeftMostKnot = null;
		while (peaksKnotsIterator.hasPrevious()) {
			Integer previousPeaksKnot = peaksKnotsIterator.previous();
			//if (peaks.get(lastPeakKnot) <= peaks.get(previousPeaksKnot)) return false; HigherHigh && HigherLow => last is below left peaks (exit)		B:smallerThanOrEqual
			//if (peaks.get(lastPeakKnot) >= peaks.get(previousPeaksKnot)) return false; LowerHigh && LowerLow => last is above left peaks (exit)		B':greaterThanOrEqual
			if (rightMostKnotIsNotToLefts.apply(peaks.get(lastPeakKnot), peaks.get(previousPeaksKnot))) return false;
			//peaks.get(previousPeaksKnot) >= peaks.get(leftMostExtrem)) HigherHigh && LowerHigh => left peak is above leftMostExtrem		C:greaterThanOrEqual
			//peaks.get(previousPeaksKnot) <= peaks.get(leftMostExtrem)) HigherLow && LowerLow => left peak is below leftMostExtrem			C':smallerThanOrEqual
			if ( leftMostKnot == null || leftMostKnotIsToInners.apply(peaks.get(previousPeaksKnot), peaks.get(leftMostKnot)) ) {
				if ((lastPeakKnot - previousPeaksKnot) >= minimumNbDaysBetweenExtremes) {
					validLeftMostKnot = previousPeaksKnot;
					//leftMostKnot = previousPeaksKnot; //Uncomment for test
				}
				leftMostKnot = previousPeaksKnot;
			}
		}
		if (validLeftMostKnot == null) return false;
		higherHighs.put(validLeftMostKnot, peaks.get(validLeftMostKnot));
		higherHighs.put(lastPeakKnot, peaks.get(lastPeakKnot));

		//Slope
		if (higherHighs.size() >= 2) {

			for(int i = (smoothingPeriod/2); i >= 0; i--) {
				expertTangent.add(Double.NaN);
			}

			int start = higherHighs.firstKey();
			double startPeak = zEMASmoothed[start][0];
			int end = higherHighs.lastKey();
			double endPeak = zEMASmoothed[end][0];
			double slope = (endPeak - startPeak)/(double)(end - start);
			boolean crossed = false;
			for(double i = zEMASmoothed.length-1; i >= 0; i--) {

				if (i <= end && !crossed) {
					double lineY = startPeak + slope*(i - start);
					expertTangent.add(lineY);
					//if (lineY < zEMASmoothed[(int)i][0]) { HigherHigh && LowerHigh => line is below peak strict (exit)		D:smallerThan
					//if (lineY > zEMASmoothed[(int)i][0]) { HigherLow && LowerLow => line is above peak strict	 (exit)			D':greaterThan
					if (tangentIsNotToKnots.apply(lineY, zEMASmoothed[(int)i][0])) {
						if (start < i) return false;
						crossed = true; //Comment for test
					}
				}
				else expertTangent.add(Double.NaN);

			}

			int epxertTangentSize = expertTangent.size();
			for(int i = lookBackSize - epxertTangentSize; i >= 0; i--) {
				expertTangent.add(Double.NaN);
			}

			Collections.reverse(expertTangent);
			return true;

		}

		return false;
	}


}
