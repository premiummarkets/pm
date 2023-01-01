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
package com.finance.pms.events.quotations;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

public abstract class QuotationsIntraDay extends Quotations {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(QuotationsIntraDay.class);

	QuotationsIntraDay(Stock stock, Date firstDate, Date lastDate, Currency targetCurrency, Integer firstIndexShift, ValidityFilter validityFilter, ValidityFilter ... otherValidityFilters ) throws NoQuotationsException {
		super(stock, firstDate, lastDate, true, targetCurrency, firstIndexShift, validityFilter, otherValidityFilters);
	}

	//FIXME
//	QuotationsIntraDay(Stock stock, QuotationData quotationData, Currency targetCurrency, ValidityFilter validityFilter, ValidityFilter ... otherValidityFilters) {
//		super(stock, quotationData, targetCurrency, validityFilter, otherValidityFilters);
//	}
	
	@Override
	protected QuotationData isAllCached(Stock stock, Date firstDate, Date lastDate, Integer indexShift) {
	
		QuotationData quotationData = Quotations.getCachedStock(stock, ValidityFilter.ALLVALID.name());
		
		if (quotationData == null || quotationData.size() == 0) {
			return null;
		}
		
		Boolean inOpenHours = QuotationsFactories.getFactory().isInOpenHours(lastDate);
		if (!inOpenHours) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(lastDate);
			calendar.setTime(QuotationsFactories.getFactory().getValidQuotingDateBeforeOrAt(calendar.getTime()));
			lastDate = calendar.getTime();
		}
		
		boolean limSup = quotationData.get(quotationData.size()-1).getDate().compareTo(lastDate) >= 0;
		boolean limInf = quotationData.getClosestIndexBeforeOrAtDate(0, firstDate) >= indexShift;
		//System.out.println("inf : "+limInf+" sup : "+limSup+" indexShift "+indexShift+ " lastDate "+lastDate+" qLastDate "+quotationData.get(quotationData.size()-1).getDate()+" firstDate "+firstDate+" qfirstDate "+quotationData.get(0));
		boolean isCached = limSup && limInf;
		
		if (isCached) {
			return quotationData;
		} else {
			return null;
		}
		
	}
	
	@Override
	protected abstract QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore);

}
