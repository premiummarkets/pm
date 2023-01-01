
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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;

public class FlipNormalizer {
	
	private static MyLogger LOGGER = MyLogger.getLogger(FlipNormalizer.class);
	
	private Date start;
	private Date end;
	private double maxNorm;
	private double minNorm;
	
	

	public FlipNormalizer(Stock stock, Date start, Date end, double minNorm, double maxNorm) throws NotEnoughDataException {
		super();
		this.start = start;
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), endCal, 1);
		this.end = endCal.getTime();

		this.minNorm = minNorm;
		this.maxNorm = maxNorm;
	}



	public SortedMap<Date, double[]> normalised(SortedMap<Date, double[]> data) {
		
		//b = [(a - minA) / (maxA - minA)] * (maxNorm - minNorm) + minNorm 
		//with maxNorm = 1 and minNorm = 0
		//b = [(a - minA) / (maxA - minA)]
		
		//if (data.get(data.firstKey()).length > 1) throw new NotImplementedException();
		if (data.get(data.firstKey()).length > 1) {
			LOGGER.warn("InvNormalised data contains element value size > 1 is not supported. Only the first series will be InvNormalised.");
		}
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;
		SortedMap<Date, double[]> subD = data.subMap(start, end);
		for (Date date : subD.keySet()) {
			double value = data.get(date)[0];
			if (value >= max) max = value;
			if (value <= min) min = value;
		}
		for (Date date : subD.keySet()) {
			double value = data.get(date)[0];
			ret.put(date, new double[]{ ((max-value)/(max-min)) * (maxNorm - minNorm) + minNorm});
		}
		
		return ret;
		
	}
	

}
