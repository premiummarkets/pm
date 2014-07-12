
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

import java.util.Date;
import java.util.SortedMap;

public class CurvesHouseDevComparator extends CurvesComparator {

	public CurvesHouseDevComparator(Date start, Date end, int maxShift) {
		super(start, end, maxShift);
	}

	@Override
	protected double compareNormalised(Date start, Date end, SortedMap<Date, double[]> normalisedData, SortedMap<Date, double[]> normalisedRefData) {
		
		int nbValues = 0;
		double shiftErr = 0;
		for (Date date : normalisedData.keySet()) {
			if (date.after(start) && date.before(end)) {
				double hdData[] = normalisedData.get(date);
				double hdRefData[] = normalisedRefData.get(date);
				if (hdRefData != null && !Double.isNaN(hdData[0]) && !Double.isNaN(hdRefData[0])) {
					double delta = hdData[0] - hdRefData[0];
					shiftErr = shiftErr + delta * delta;
					nbValues++;
				}
			}
		}
		
		shiftErr = shiftErr / nbValues;
		return shiftErr;
		
	}

	
	protected SortedMap<Date, double[]> normalize(Date start, Date end, SortedMap<Date, double[]> data) {
		ZeroLagEMASmoother emaSmoother = new ZeroLagEMASmoother(9);
		SortedMap<Date, double[]> smoothed = emaSmoother.smooth(data, null);
		Normalizer<double[]> normalizer = new Normalizer<double[]>(double[].class, start, end, 1, 11); //Norm necessary as HouseTrend doesn't support negative or zero values
		SortedMap<Date, double[]> normalisedData = normalizer.normalised(smoothed);
		HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother(1);
		SortedMap<Date, double[]> houseTrend = houseTrendSmoother.smooth(normalisedData, null);
		return houseTrend;
	}

}
