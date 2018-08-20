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

import com.finance.pms.admin.install.logging.MyLogger;
import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.*;


/**
 * 
 * @author Guillaume Thoreton
 *
 */

public class SmoothHighLowSolver {

	private static MyLogger LOGGER = MyLogger.getLogger(SmoothHighLowSolver.class);

	private int smoothingPeriod;

	public SmoothHighLowSolver(int smoothingPeriod) {
		this.smoothingPeriod = smoothingPeriod;
	}

	public Boolean higherHigh(double[][] data, Map<Integer, double[]> higherHighs) {

		//Calculating Ht(ZeroEMA(data))
		ZeroLagEMASmoother zeroLagEMASmoother = new ZeroLagEMASmoother(smoothingPeriod);
		double[][] zEMASmoothed = zeroLagEMASmoother.smooth(data);

		HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother();
		double[][] htSmoothed = houseTrendSmoother.smooth(zEMASmoothed);

		int shift = smoothingPeriod / 2 + 1;

		//Find peaks
		SortedMap<Integer, double[]> peaks =  new TreeMap<>();
		for(int i = 1; i < htSmoothed.length; i++) {
			if (htSmoothed[i-1][0] > 0 && htSmoothed[i][0] == 0) {
				int peakKeyInDataIdx = i + shift;
				peaks.put(peakKeyInDataIdx, data[peakKeyInDataIdx]);
			}
		}

		//Finding HigherHighs
		List<Integer> pksXs = new ArrayList<>(peaks.keySet());
		for (int searchStart = 0; searchStart < pksXs.size();) {
			for (int pkXsIdx = searchStart + 1; pkXsIdx < pksXs.size(); pkXsIdx++) {
				Integer previousPkX = pksXs.get(pkXsIdx - 1);
				Integer currentPkX = pksXs.get(pkXsIdx);
				double[] previousPkY = peaks.get(previousPkX);
				double[] currentPkY = peaks.get(currentPkX);
				if (previousPkY[0] < currentPkY[0]) {
					higherHighs.put(previousPkX, previousPkY);
					higherHighs.put(currentPkX, currentPkY);
					searchStart = pkXsIdx;
				}
			}
		}

		return higherHighs.size() > 0;
	}

	public Boolean higherLow(double[][] data, Map<Integer, double[]> higherLows) {
		//TODO
		return false;
	}

	public Boolean lowerHigh(double[][] data, Map<Integer, double[]> higherLows) {
		//TODO
		return false;
	}

	public Boolean lowerLow(double[][] data, Map<Integer, double[]> higherLows) {
		//TODO
		return false;
	}
}
