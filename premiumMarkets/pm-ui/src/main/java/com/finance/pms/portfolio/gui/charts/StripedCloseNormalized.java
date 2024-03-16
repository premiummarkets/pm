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
package com.finance.pms.portfolio.gui.charts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;


public class StripedCloseNormalized extends StripedCloseFunction {

	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseNormalized.class);

	private NumberFormat numberFormat = new DecimalFormat("#0.00 %");
	private Boolean rootAtZero = false;

	public StripedCloseNormalized(Date arbitraryStartDate, Date arbitraryEndDate) {
		super(arbitraryStartDate, arbitraryEndDate);
	}


	@Override
	public SortedMap<Date, Double> targetShareData(SlidingPortfolioShare ps, Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		Date startDate = getStartDate(stockQuotations);
		Date endDate = getEndDate(stockQuotations);

		startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		endDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(startDateQuotationIndex.value, endDate);

		SortedMap<Date, Double> data = new TreeMap<Date, Double>();
		List<QuotationUnit> quotationUnits = stockQuotations.getQuotationUnits(startDateQuotationIndex.value, endDateQuotationIndex.value);
		for (QuotationUnit quotationUnit : quotationUnits) {
			data.put(quotationUnit.getDate(), quotationUnit.getCloseSplit().doubleValue());
		}
		
		Mean meanOp = new Mean();
		double mean = meanOp.evaluate(data.values().stream().mapToDouble(d -> d).toArray());
		StandardDeviation standardDeviationOp = new StandardDeviation();
		double stdev = standardDeviationOp.evaluate(data.values().stream().mapToDouble(d -> d).toArray());

		return relativeCloses(startDate, endDate, data, mean, stdev);

	}

	private SortedMap<Date, Double> relativeCloses(Date startDate, Date endDate, SortedMap<Date, Double> data, double mean, double stdev) {

		SortedMap<Date, Double> ret = new TreeMap<>();
		data = data.tailMap(startDate);
		Double pivot = (rootAtZero)?(data.get(startDate)-mean)/stdev:0d;
		Set<Date> keySet = data.keySet();
		for (Iterator<Date> iterator = keySet.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			if (date.after(endDate)) break;
			ret.put(date, (data.get(date)-mean)/stdev - pivot);
		}

		return ret;
	}

	@Override
	public String lineToolTip() {
		return "change to period start";
	}


	@Override
	public String formatYValue(Number yValue) {
		return numberFormat.format(yValue);
	}


	@Override
	public Boolean isRelative() {
		return true;
	}

	@Override
	public NumberFormat getNumberFormat() {
		return numberFormat;
	}

}
