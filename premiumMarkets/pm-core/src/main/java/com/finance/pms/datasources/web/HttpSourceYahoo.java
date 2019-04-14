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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

/**
 * The Class HttpSourceYahoo.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceYahoo extends HttpSource implements SourceConnector {

	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceYahoo.class);


	public HttpSourceYahoo(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, 
			String startYear, String startMonth, String startDay, 
			String endYear, String endMonth, String endDay){

		try {
			ticker = URLEncoder.encode(ticker,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.debug("",e);
		}
		return new MyUrl(this.getYahooQuoteURL(ticker, startYear,startMonth, startDay, endYear,endMonth, endDay));
	}

	public String getYahooQuoteURL(
			String ticker, 
			String startYear, String startMonth, String startDay, 
			String endYear, String endMonth, String endDay)
	{
		//new url?? : http://download.finance.yahoo.com/d/quotes.csv?s=NAN.AX&f=sl1d1t1c1ohgv&e=.csv
		//http://ichart.yahoo.com/table.csv?s=ATR.AX&d=0&e=24&f=2010&g=d&a=0&b=1&c=2003&ignore=.csv
		//http://ichart.yahoo.com/table.csv?s=ATR.AX&d=0&e=24&f=2010&a=0&b=1&c=2003&ignore=.csv

		String url = "http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a="
				+ startMonth + "&b=" + startDay + "&c=" + startYear + "&d="
				//+ endMonth + "&e=" + endDay + "&f=" + endYear + "&g=d&ignore=.csv";
				+ endMonth + "&e=" + endDay + "&f=" + endYear + "&ignore=.csv";

		LOGGER.debug(url);
		return url;
	}


	@Override
	public String getStockInfoPageURL(String isin) {//throws ToDoYahooException {
		// TODO Completer la r�cup�ration des compl�ments de stocks sur Yahoo
		LOGGER.debug("Fetching stock information is not implemented for yahoo");
		throw new RuntimeException("Fetching stock information is not implemented for yahoo");
	}


	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		throw new RuntimeException("Fetching stock list is not implemented for yahoo");
	}

	@Override
	public void stopThreads() {
		LOGGER.info("That's all ... Bye");
	}

	public int crashResart(int connectionId) {
		return 0;
	}

	public void shutdownSource(SourceClient c, int connectionId) {
		LOGGER.info("Nothing to do for now here ...");
	}

	public SourceClient connect(int connectionId) throws RestartServerException {
		SourceClient retour;
		retour = this.httpConnect();
		return retour;
	}

	@Override
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new HttpGet(url.getUrl());
	}


}
