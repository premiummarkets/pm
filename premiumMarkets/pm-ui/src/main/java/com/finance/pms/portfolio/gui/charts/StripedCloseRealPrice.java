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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

public class StripedCloseRealPrice extends StripedCloseFunction {

	private NumberFormat numberFormat = new DecimalFormat("0.####");

	public StripedCloseRealPrice() {
	}

	@Override
	public Number[] targetShareData(SlidingPortfolioShare ps, Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		Date startDate = getStartDate(stockQuotations);
		Date endDate = getEndDate(stockQuotations);
		
		startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		endDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(startDateQuotationIndex.value, endDate);

		return relativeCloses(stockQuotations, startDateQuotationIndex, endDateQuotationIndex);
	}

	private Number[] relativeCloses(Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {
		
		ArrayList<BigDecimal>  retA = new ArrayList<BigDecimal>();
		for (int i = startDateQuotationIndex.value; i <= endDateQuotationIndex.value; i++) {
			retA.add(stockQuotations.get(i).getCloseSplit());
		}

		return  retA.toArray(new BigDecimal[0]);
	}

	@Override
	public String lineToolTip() {
		return "real price";
	}

	@Override
	public String formatYValue(Number yValue) {
		return numberFormat.format(yValue);
	}

	@Override
	public Boolean isRelative() {
		return false;
	}

	@Override
	public NumberFormat getNumberFormat() {
		return numberFormat;
	}

}
