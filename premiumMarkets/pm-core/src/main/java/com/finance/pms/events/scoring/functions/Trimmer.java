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

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationsFactories;

public class Trimmer {
	
	private static MyLogger LOGGER = MyLogger.getLogger(Trimmer.class);
	
	private ApacheStats stdev;
	
	private int trimFactor;
	private Date start;
	private Date end;
	
	
	public Trimmer(int trimFactor, Date start, Date end) {
		super();
		stdev = new ApacheStats(new StandardDeviation());
		
		this.trimFactor = trimFactor;
		
		this.start = start;
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(endCal, 1);
		this.end = endCal.getTime();
	}


	public SortedMap<Date, double[]> trimmed(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> subMap = data.subMap(start, end);
		
		double allStdev = stdev.evaluate(subMap.values());
		LOGGER.info("Overall stdev : "+allStdev);
		
		SortedMap<Date, double[]> trimedHouseTrend = new TreeMap<Date, double[]>();
		
		double positiveMax = trimFactor*allStdev;
		double negativeMax = trimFactor*allStdev;

		for (Date date : subMap.keySet()) { 

			double[] ds = subMap.get(date);
			if (ds[0] > positiveMax) {
				trimedHouseTrend.put(date, new double[]{positiveMax});
			}
			else if (ds[0] < -negativeMax) {
				trimedHouseTrend.put(date, new double[]{-negativeMax});
			} else {
				trimedHouseTrend.put(date, ds);
			}
			
		}
		
		return trimedHouseTrend;
	}

}
