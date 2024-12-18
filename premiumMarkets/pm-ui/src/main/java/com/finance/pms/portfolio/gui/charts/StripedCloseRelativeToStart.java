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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;


public class StripedCloseRelativeToStart extends StripedCloseFunction {

	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseRelativeToStart.class);

	NumberFormat numberFormat = new DecimalFormat("#0.00 %");

	public StripedCloseRelativeToStart(Date arbitraryStartDate, Date arbitraryEndDate) {
		super(arbitraryStartDate, arbitraryEndDate);
	}


	@Override
	public SortedMap<Date, Double> targetShareData(SlidingPortfolioShare ps, Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		Date startDate = getStartDate(stockQuotations);
		startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0,startDate);

		Date endDate = getEndDate(stockQuotations);
		endDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(startDateQuotationIndex.value, endDate);

		return relativeCloses(stockQuotations, startDateQuotationIndex, endDateQuotationIndex);

	}

	private SortedMap<Date, Double> relativeCloses(Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		SortedMap<Date, Double>  retA = new TreeMap<>();

		BigDecimal realCloseRoot = stockQuotations.get(startDateQuotationIndex.value).getCloseSplit();
		if (realCloseRoot != null && realCloseRoot.compareTo(BigDecimal.ZERO) != 0) {
			for (int i = startDateQuotationIndex.value; i <= endDateQuotationIndex.value; i++) {
				BigDecimal relatedCloseValue = stockQuotations.get(i).getCloseSplit().subtract(realCloseRoot).divide(realCloseRoot, 10, RoundingMode.HALF_EVEN);
				retA.put(stockQuotations.get(i).getDate(), relatedCloseValue.doubleValue());
			}
		}

		return  retA;
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
