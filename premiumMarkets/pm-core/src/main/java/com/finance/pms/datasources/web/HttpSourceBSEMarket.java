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
package com.finance.pms.datasources.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.events.quotations.QuotationsFactories;

public class HttpSourceBSEMarket extends HttpSourceMarket {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceBSEMarket.class);
	
	
	public HttpSourceBSEMarket(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}


	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		
//0
//		String url = "http://bseindia.com/bhavcopy/eq";
//		url += simpleDateFormat.format(calendar.getTime())+"_csv.zip";
		
//2
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// dayOfWeek == Calendar.MONDAY || dayOfWeek == Calendar.TUESDAY && calendar.get(Calendar.HOUR_OF_DAY) < 18
		boolean isWeekEnd = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.MONDAY && calendar.get(Calendar.HOUR_OF_DAY) < 18);
		if (isWeekEnd) {
			//calendar.add(Calendar.DAY_OF_MONTH, -(dayOfWeek+1));
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			calendar.setTime(QuotationsFactories.getFactory().getValidQuotingDateBeforeOrAt(calendar.getTime()));
		} else if (calendar.get(Calendar.HOUR_OF_DAY) < 18) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String url = "http://www.bseindia.com/download/BhavCopy/Equity/eq"+dateFormat.format(calendar.getTime())+"_csv.zip";
		
		if (LOGGER.isDebugEnabled()) LOGGER.debug("BSE list url  :"+url);
		
		return url;
	}

	@Override
	//XXX scrip code don't work with yahoo ...
	public String getStockInfoPageURL(String name) {
		//http://www.bseindia.com/price_finder/stockreach.asp?scripcd=530133
		//return "http://www.bseindia.com/price_finder/stockreach.asp?scripcd="+isin;
		
		//http://in.finance.yahoo.com/lookup?s=A.V.COTTEX&t=S&m=IN
		return "http://in.finance.yahoo.com/lookup?s="+name.replaceAll(" ","+")+"&t=S&m=IN";
	}
}
