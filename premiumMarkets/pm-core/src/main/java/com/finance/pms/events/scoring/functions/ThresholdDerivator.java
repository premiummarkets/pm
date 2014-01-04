
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.finance.pms.events.scoring.functions;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class ThresholdDerivator implements DiscretDerivator {

	private double lowThreshold;
	private double highThreshold;
	private Boolean buyCrossTowardCenter;
	private Boolean sellCrossTowardCenter;

	public ThresholdDerivator(double lowThreshold, double highThreshold, Boolean buyCrossTowardCenter,  Boolean sellCrossTowardCenter) {
		super();
		if (highThreshold < lowThreshold) throw new InvalidParameterException();
		
		this.lowThreshold = lowThreshold;
		this.highThreshold = highThreshold;
		this.buyCrossTowardCenter = buyCrossTowardCenter;
		this.sellCrossTowardCenter = sellCrossTowardCenter;
	}
	
	public ThresholdDerivator(double lowThreshold, double highThreshold) {
		this(lowThreshold, highThreshold, false, true);
	}

	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 1; i < values.size(); i++) {
			double retValue = 0.5;
			double prevValue = values.get(i-1)[0];
			double currentValue = values.get(i)[0];
			boolean prevToLowTh = (buyCrossTowardCenter)? prevValue < lowThreshold : prevValue > lowThreshold;
			boolean currentToLowTh = (buyCrossTowardCenter)? lowThreshold <= currentValue : lowThreshold >= currentValue;
			if ( prevToLowTh && currentToLowTh) {
				retValue = 0;
			} else {
				boolean prevToHighTh = (sellCrossTowardCenter)? prevValue > highThreshold : prevValue < highThreshold;
				boolean currentToHighTh = (sellCrossTowardCenter)? highThreshold >= currentValue : highThreshold <= currentValue;
				if ( prevToHighTh && currentToHighTh ) {
					retValue = 1;
				} else {
					retValue = 0.5;
				}
			}
			ret.put(keys.get(i), new double[]{retValue});
		}

		return ret;

	}

}
