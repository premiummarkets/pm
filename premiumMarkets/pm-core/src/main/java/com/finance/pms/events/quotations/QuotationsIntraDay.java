/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.events.quotations;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

public abstract class QuotationsIntraDay extends Quotations {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(QuotationsIntraDay.class);

	QuotationsIntraDay(Stock stock, Date firstDate, Date lastDate, Currency targetCurrency, Integer firstIndexShift, Integer lastIndexShift) throws NoQuotationsException {
		super(stock, firstDate, lastDate, true, targetCurrency, firstIndexShift, lastIndexShift);
	}

	QuotationsIntraDay(Stock stock, QuotationData quotationData, Currency targetCurrency) {
		super(stock, quotationData, targetCurrency);
	}
	
	@Override
	protected boolean isAllCached(Stock stock, Date firstDate, Date lastDate, Integer indexShift) {
		//QuotationData quotationData = QUOTATIONS_CACHE.get(stock);
		QuotationData quotationData = Quotations.getCashedStock(stock);
		
		if (quotationData == null || quotationData.size() == 0) {
			return false;
		}
		
		Boolean inOpenHours = QuotationsFactories.getFactory().isInOpenHours(lastDate);
		if (!inOpenHours) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(lastDate);
			calendar.setTime(QuotationsFactories.getFactory().getValidQuotationDateBefore(calendar.getTime()));
			lastDate = calendar.getTime();
		}
		
		boolean limSup = quotationData.get(quotationData.size()-1).getDate().compareTo(lastDate) >= 0;
		boolean limInf = quotationData.getClosestIndexForDate(0, firstDate) >= indexShift;
		//System.out.println("inf : "+limInf+" sup : "+limSup+" indexShift "+indexShift+ " lastDate "+lastDate+" qLastDate "+quotationData.get(quotationData.size()-1).getDate()+" firstDate "+firstDate+" qfirstDate "+quotationData.get(0));
		return limSup && limInf;
				
	}
	
	@Override
	protected abstract QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore);

}
