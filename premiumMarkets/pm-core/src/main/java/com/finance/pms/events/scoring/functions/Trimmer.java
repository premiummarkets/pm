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
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.events.quotations.QuotationsFactories;

public class Trimmer {

//	private static MyLogger LOGGER = MyLogger.getLogger(Trimmer.class);

	private MyApacheStats stdev;

	private int trimFactor;
	private Date start;
	private Date end;

	private int slidingPeriod;

	public Trimmer(int slidingPeriod, int trimFactor, Date start, Date end) {
		super();
		this.stdev = new MyApacheStats(new StandardDeviation());
		
		this.slidingPeriod = slidingPeriod;
		this.trimFactor = trimFactor;
		this.start = start;

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(endCal, 1);
		this.end = endCal.getTime();
	}
	
	public  SortedMap<Date, Double> sTrimmed(SortedMap<Date, Double> data) {
		SortedMap<Date, double[]> doubleArrayMap = data.entrySet().stream()
			.map(e -> Map.<Date, double[]>entry(e.getKey(), new double[] {e.getValue()}))
			.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap<Date,double[]>::new));
		SortedMap<Date, double[]> trimmed = trimmed(doubleArrayMap);
		return trimmed.entrySet().stream()
				.map(e -> Map.<Date, Double>entry(e.getKey(), e.getValue()[0]))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap<Date,Double>::new));
	}

	public SortedMap<Date, double[]> trimmed(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> trimedHouseTrend = new TreeMap<Date, double[]>();

		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(data.firstKey());
		QuotationsFactories.getFactory().incrementDate(startDateCal, +slidingPeriod);
		Date startDate = (startDateCal.getTime().compareTo(start) > 0)?startDateCal.getTime():start;
		SortedMap<Date, double[]> subMap = data.subMap(startDate, end);
		
		for (Date date : subMap.keySet()) {

			Calendar currentDateCal = Calendar.getInstance();
			currentDateCal.setTime(date);
			QuotationsFactories.getFactory().incrementDate(currentDateCal, -slidingPeriod);
			Date periodStart = currentDateCal.getTime();
			double periodStdev = stdev.evaluate(data.subMap(periodStart, date).values());
			double periodMax = trimFactor*periodStdev;

			double[] ds = data.get(date);
			if (Math.abs(ds[0]) <= periodMax) {
				trimedHouseTrend.put(date, ds);
			} else {
				if (ds[0] > 0) {
					trimedHouseTrend.put(date, new double[] { periodMax });
				} else {
					trimedHouseTrend.put(date, new double[] { -periodMax });
				}
			}

		}

		return trimedHouseTrend;
	}

}
