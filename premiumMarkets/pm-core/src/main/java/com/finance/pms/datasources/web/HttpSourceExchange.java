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
import java.util.Date;

import com.finance.pms.datasources.shares.Currency;


public class HttpSourceExchange extends HttpSourceYahoo {
	
	public HttpSourceExchange(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
	}
	
	public String getUrl(Currency currency) {
		//String url = "http://www.xe.com/ucc/convert.cgi?Amount=1&From=%S&To=%S&image.x=51&image.y=10&image=Submit";
		//String url = "http://uk.finance.yahoo.com/currencies/converter/#from=%S;to=EUR;amt=1"
		String url = "http://www.gocurrency.com/v2/dorate.php?inV=1&from=%S&to=%S&Calculate=Convert";
		return String.format(url,currency.toString(),"EUR");
	}

	public String getOandaHistoryUrl(Currency fromCurrency, Currency toCurrency, Date start, Date end) {
		//String url="http://www.oanda.com/transactionCurrency/historical-rates?date_fmt=us&date=%s&date1=%s&exch=%S&expr=%S&margin_fixed=0&format=CSV&redirected=1";
		//http://www.oanda.com/currency/historical-rates-classic?date_fmt=normal&date=08/06/14&date1=01/06/14&exch=GBP&expr=EUR&margin_fixed=0&format=CSV&redirected=1
		String url="http://www.oanda.com/currency/historical-rates-classic?date_fmt=normal&date=%s&date1=%s&exch=%S&expr=%S&margin_fixed=0&format=CSV&redirected=1";
		return String.format(url,new SimpleDateFormat("dd/MM/yy").format(end),new SimpleDateFormat("dd/MM/yy").format(start),fromCurrency.toString(),toCurrency.toString());

	}
	
	public String getImfHistoryUrl(Date date) {
		String url="http://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=%s&reportType=REP&tsvflag=Y";
		//String url="http://www.imf.org/external/np/fin/data/rms_mth.aspx?SelectDate=%s";
		return String.format(url,new SimpleDateFormat("yyyy-MM-dd").format(date));
	}
	
	public String getXRatesHistoryUrl(Date date) {
		String url="http://www.x-rates.com/historical/?from=USD&amount=1.00&date=%s";
		return String.format(url,new SimpleDateFormat("yyyy-MM-dd").format(date));
	}
	
}
