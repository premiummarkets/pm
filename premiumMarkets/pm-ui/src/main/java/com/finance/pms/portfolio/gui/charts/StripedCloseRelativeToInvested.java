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
import com.finance.pms.events.calculation.InvalidParameterException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

/**
 * The Class StripedCloseInitPriceRelative.
 * 
 * @author Guillaume Thoreton
 */
public class StripedCloseRelativeToInvested extends StripedCloseFunction {

	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseRelativeToInvested.class);

	NumberFormat numberFormat = new DecimalFormat("#0.00 %");

	Boolean includeMoneyOut;


	public StripedCloseRelativeToInvested(Boolean includeMoneyOut, Date startDate, Date endDate) {	
		super(startDate, endDate);
		this.includeMoneyOut = includeMoneyOut;
	}

	@Override
	public SortedMap<Date, Double> targetShareData(SlidingPortfolioShare portfolioShare, Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		if (arbitraryStartDate != null && arbitraryEndDate != null) {

			Date startDate = getStartDate(stockQuotations);
			startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
			Date endDate = getEndDate(stockQuotations);
			endDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(startDateQuotationIndex.value, endDate);
			BigDecimal investPerUnit = BigDecimal.ZERO; 
			if (includeMoneyOut) {
				investPerUnit = portfolioShare.getPriceUnitCost(portfolioShare.calcSlidingEndDate(), portfolioShare.getTransactionCurrency(), false);
			} else {
				investPerUnit = portfolioShare.getPriceAvgBuy(portfolioShare.calcSlidingEndDate(), portfolioShare.getTransactionCurrency(), false, false);
			}

			return relativeCloses(stockQuotations, startDateQuotationIndex, endDateQuotationIndex, investPerUnit);
		}  
		else {
			throw new InvalidParameterException();
		}

	}


	private SortedMap<Date, Double> relativeCloses(Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex, BigDecimal unitCost) {

		SortedMap<Date, Double>  retA = new TreeMap<>();

		for (int i = startDateQuotationIndex.value; i <= endDateQuotationIndex.value; i++) {
			BigDecimal value = BigDecimal.ZERO;
			QuotationUnit quotationUnit = stockQuotations.get(i);
			if (unitCost.compareTo(BigDecimal.ZERO) != 0) {
				BigDecimal close = quotationUnit.getCloseSplit();
				value = (close.subtract(unitCost).divide(unitCost.abs(), 10, RoundingMode.HALF_EVEN));
			} 
			retA.put(quotationUnit.getDate(), value.doubleValue());
		}

		return  retA;
	}

	@Override
	public String lineToolTip() {
		return "change to Invested per unit";
	}

	@Override
	public String formatYValue(Number yValue) {
		return numberFormat.format(yValue);
	}

	@Override
	public Boolean isRelative() {
		return true;
	}

	public Boolean getIncludeMoneyOut() {
		return includeMoneyOut;
	}

	@Override
	public NumberFormat getNumberFormat() {
		return numberFormat;
	}


}
