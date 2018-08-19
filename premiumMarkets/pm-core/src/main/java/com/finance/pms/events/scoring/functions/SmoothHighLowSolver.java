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


/**
 * 
 * @author Guillaume Thoreton
 *
 */

public class SmoothHighLowSolver implements HighLowSolver {

	//private static MyLogger LOGGER = MyLogger.getLogger(SmoothHighLowSolver.class);

	@Override
	public Boolean higherHigh(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent) {

		//Smooth
		ZeroLagEMASmoother zeroLagEMASmoother = new ZeroLagEMASmoother(smoothingPeriod);
		double[][] arrayArrayData = Arrays.stream(data).map(d -> new double[]{d}).toArray(double[][]::new);
		double[][] zEMASmoothed = zeroLagEMASmoother.smooth(arrayArrayData);

		//Peaks
		SortedMap<Integer, Double> peaks =  new TreeMap<>();
		for(int i = 1; i < zEMASmoothed.length-1; i++) {
			if (zEMASmoothed[i-1][0] < zEMASmoothed[i][0] && zEMASmoothed[i][0] >= zEMASmoothed[i+1][0]) {
				int peakKeyInDataIdx = i + smoothingPeriod/2;
				peaks.put(peakKeyInDataIdx, data[peakKeyInDataIdx]);
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
		Integer secondHighestPeakKnot = null;
		while (peaksKnotsIterator.hasPrevious()) {
			Integer previousPeaksKnot = peaksKnotsIterator.previous();
			if (peaks.get(lastPeakKnot) <= peaks.get(previousPeaksKnot)) return false;
			if ( (secondHighestPeakKnot == null || peaks.get(previousPeaksKnot) >= peaks.get(secondHighestPeakKnot)) && (lastPeakKnot - previousPeaksKnot) >= minimumNbDaysBetweenExtremes ) secondHighestPeakKnot = previousPeaksKnot;
		}
		if (secondHighestPeakKnot == null) return false;
		higherHighs.put(secondHighestPeakKnot, peaks.get(secondHighestPeakKnot));
		higherHighs.put(lastPeakKnot, peaks.get(lastPeakKnot));

		//Slope
		if (higherHighs.size() >= 2) {

			int start = higherHighs.firstKey();
			double startPeak = data[start];
			int end = higherHighs.lastKey();
			double endPeak = data[end];
			double slope = (endPeak - startPeak)/(double)(end - start);
			boolean crossed = false;
			for(double i = data.length-1; i >= 0; i--) {

				if (i <= end && !crossed) {
					double lineY = startPeak + slope*(i - start);
					expertTangent.add(lineY);
					if (lineY < data[(int)i]) {
						crossed = true;
					}
				}
				else expertTangent.add(Double.NaN);

			}
			Collections.reverse(expertTangent);
			return true;

		}

		return false;
	}


}
