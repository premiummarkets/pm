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
package com.finance.pms.datasources.db;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.portfolio.PortfolioShare;
import com.tictactec.ta.lib.MInteger;

public class StripedCloseLogRoc extends StripedCloseFunction {
	
	public static final int DEFAULTLOGROCSMTH = 5;
	
	private NumberFormat nf = new DecimalFormat("0.############ \u2030");
	
//	private SortedMap<Date, double[]> houseDerivation;
//	private Date startDate;
//	private Date endDate;
	
	private Boolean rootAtZero;
	private int period;
	
	public StripedCloseLogRoc(Boolean rootAtZero, int period) {
		this.rootAtZero = rootAtZero;
		this.period = period;
	}

	@Override
	public Number[] targetShareData(PortfolioShare ps,  Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		Date startDate = getStartDate(stockQuotations);
		Date endDate = getEndDate(stockQuotations);

		startDateQuotationIndex.value = stockQuotations.getClosestIndexForDate(0,startDate);
		endDateQuotationIndex.value = stockQuotations.getClosestIndexForDate(startDateQuotationIndex.value, endDate);

		SortedMap<Date, double[]> data = new TreeMap<Date, double[]>();
		List<QuotationUnit> quotationUnits = stockQuotations.getQuotationUnits(startDateQuotationIndex.value, endDateQuotationIndex.value);
		for (QuotationUnit quotationUnit : quotationUnits) {
			data.put(quotationUnit.getDate(), new double[]{quotationUnit.getClose().doubleValue()});
		}

		TalibSmaSmoother smaSmoother = new TalibSmaSmoother(period);
		SortedMap<Date, double[]> smoothed = smaSmoother.smooth(data, false);

		try {
			
			HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother();
			SortedMap<Date, double[]> houseDerivation = houseTrendSmoother.smooth(smoothed, false);
			
			startDate = (startDate.before(houseDerivation.firstKey()))? houseDerivation.firstKey() : startDate;
			startDateQuotationIndex.value = stockQuotations.getClosestIndexForDate(0, startDate);
			
			return relativeCloses(startDate, endDate, houseDerivation);
			
		} catch (Exception e) {
			LOGGER.warn("Not enough data to calculate : "+stockQuotations.getStock());
			return new Double[0];
		}

	}

	private Double[] relativeCloses(Date startDate, Date endDate, SortedMap<Date, double[]> houseDerivation) {
		
		List<Double> ret = new ArrayList<Double>();
			
			SortedMap<Date, double[]> tailMap = houseDerivation.tailMap(startDate);
			
			double root = (rootAtZero)? tailMap.get(tailMap.firstKey())[0] : 0d;
			Set<Date> keySet = tailMap.keySet();
			for (Iterator<Date> iterator = keySet.iterator(); iterator.hasNext();) {
				Date date = iterator.next();
				if (date.after(endDate)) break;
				ret.add(houseDerivation.get(date)[0] - root);
			}
	
		return ret.toArray(new Double[0]);
	}

	@Override
	public String lineToolTip() {
		return "change to previous day (log ROC)";
	}

	@Override
	public String formatYValue(Number yValue) {
		return nf.format(yValue);
	}

	@Override
	public Boolean isRelative() {
		return true;
	}

}
