
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
import java.util.List;
import java.util.SortedMap;

public abstract class Smoother {

	public abstract SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag);
	
	protected int trimStartingNaN(SortedMap<Date, double[]> data, List<Date> keys) {
		//Trim starting NaN
		int startIdx = 0;
		Boolean stop = false;
		for (; startIdx < keys.size(); startIdx++) {
			double[] ds = data.get(keys.get(startIdx));
			for (int j = 0; j < ds.length; j++) {
				if (!Double.isNaN(ds[j])) stop = true;
			}
			if (stop) break;
		}
		return startIdx;
	}
	
	protected int lagCalc(Boolean fixLag, int period) {
		
		int lag = 0;
		if (fixLag) {
			if (period <= 1) {
				lag = 0;
			}
			else if (period <= 3)  {
				lag = period/2;
			}
			else {
				lag = period / 2 + 1;
			}
		}
		
		return lag;
	}

}
