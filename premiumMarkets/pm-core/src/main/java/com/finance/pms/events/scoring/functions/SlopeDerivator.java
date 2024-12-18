
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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SlopeDerivator implements DiscretDerivator, SDiscretDerivator {
	
	private int period;
	private double slope;
	private Boolean buyCrossTowardCenter;
	private Boolean sellCrossTowardCenter;

	public SlopeDerivator(int period, double slope, Boolean buyCrossTowardCenter, Boolean sellCrossTowardCenter) {
		super();
		this.period = period;
		this.slope = slope;
		this.buyCrossTowardCenter = buyCrossTowardCenter;
		this.sellCrossTowardCenter = sellCrossTowardCenter;
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		if (data.size() < 1) return ret;

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 0; i < period; i++) {
			ret.put(keys.get(i), new double[]{0.5});
		}
		
		for (int i = period; i < values.size(); i++) {
			
			double drv = 0.5;
			double prev = values.get(i-period)[0];
			double current = values.get(i)[0];
			double percentDiff = (current - prev)/Math.abs(prev);
			
			boolean isBuySlope = (buyCrossTowardCenter)? percentDiff > (slope * Double.valueOf(period)) :  percentDiff < (-slope * Double.valueOf(period));
			if ( isBuySlope ) {
				drv = 0;
			} else {
				boolean isSellSlope = (sellCrossTowardCenter)?  percentDiff < (-slope * Double.valueOf(period))  : percentDiff > (slope * Double.valueOf(period));
				if ( isSellSlope ) {
					drv = 1;
				}
			} 
			ret.put(keys.get(i), new double[]{drv});
		}

		return ret;
	}

	@Override
	public SortedMap<Date, Double> sDerivateDiscret(SortedMap<Date, Double> data) {
		
		SortedMap<Date, Double> ret = new TreeMap<Date,Double>();

		List<Double> values = new ArrayList<Double>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 0; i < period; i++) {
			ret.put(keys.get(i), 0.5);
		}
		
		for (int i = period; i < values.size(); i++) {
			
			double drv = 0.5;
			double prev = values.get(i-period);
			double current = values.get(i);
			double percentDiff = (current - prev)/Math.abs(prev);
		
			boolean isBuySlope = (buyCrossTowardCenter)? percentDiff > (slope * Double.valueOf(period)) :  percentDiff < (-slope * Double.valueOf(period));
			if ( isBuySlope ) {
				drv = 0;
			} else {
				boolean isSellSlope = (sellCrossTowardCenter)?  percentDiff < (-slope * Double.valueOf(period))  : percentDiff > (slope * Double.valueOf(period));
				if ( isSellSlope ) {
					drv = 1;
				}
			} 
			ret.put(keys.get(i), drv);
		}

		return ret;
	}

}
