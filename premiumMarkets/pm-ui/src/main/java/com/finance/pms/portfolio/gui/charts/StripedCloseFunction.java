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

import java.text.NumberFormat;
import java.util.Date;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

/**
 * The Interface StripedCloseFunction.
 * 
 * @author Guillaume Thoreton
 */
public abstract class StripedCloseFunction {

	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseFunction.class);

	protected Date arbitraryStartDate;
	protected Date arbitraryEndDate;


	protected StripedCloseFunction(Date arbitraryStartDate, Date arbitraryEndDate) {
		super();
		this.arbitraryStartDate = arbitraryStartDate;
		this.arbitraryEndDate = arbitraryEndDate;
	}

	public abstract SortedMap<Date,Double> targetShareData(SlidingPortfolioShare ps, Quotations splitFreeQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex);

	public void updateEndDate(Date date) {
		this.arbitraryEndDate = date;
	}

	public void updateStartDate(Date date) {
		this.arbitraryStartDate = date;
	}

	protected Date getStartDate(Quotations stockQuotations) {
		Date startDate = this.arbitraryStartDate;
		startDate = (startDate.before(stockQuotations.getDate(0)))? stockQuotations.getDate(0) : startDate;
		if (LOGGER.isDebugEnabled()) LOGGER.debug("The start date is: " + startDate);

		return startDate;
	}

	protected Date getEndDate(Quotations stockQuotations) {

		Date endDate = this.arbitraryEndDate;
		Integer lastQuoteI = stockQuotations.size()-1;
		endDate = (endDate.after(stockQuotations.getDate(lastQuoteI)))? stockQuotations.getDate(lastQuoteI) : endDate;
		if (LOGGER.isDebugEnabled()) LOGGER.debug("The end date is: " + endDate);

		return endDate;
	}

	public Date getArbitraryStartDateForChart() {
		return arbitraryStartDate;
	}

	public Date getArbitraryStartDateForCalculation(Stock stock) throws NoQuotationsException, NotEnoughDataException {
		return this.getArbitraryStartDateForChart();
	}

	public Date getArbitraryEndDate() {
		return arbitraryEndDate;
	}

	public abstract String lineToolTip();

	public abstract String formatYValue(Number yValue);

	public abstract Boolean isRelative();

	public abstract NumberFormat getNumberFormat();

}
