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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.ApacheHttpClient;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;



/**
 * The Class HttpSourceEuroNext.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class HttpSourceEuroNext extends HttpSource implements SourceConnector {

	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceEuroNext.class);
	@SuppressWarnings("unused")
	private PoolSemaphore threadPool;

	public HttpSourceEuroNext(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
		LOGGER.debug("Number of Http Threads : 1 - hard coded :)");
		threadPool = new PoolSemaphore(1, this, false);
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		throw new RuntimeException("Fecthing stock information is not implemented for Euro Next");
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		throw new RuntimeException("Fecthing stock quotation is not implemented for Euro Next");
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		
//		String url = "http://www.euronext.com/search/download/trapridownloadpopup.jcsv?" +
//				"pricesearchresults=actif&filter=1&mep=8625&belongsToList=market_EURLS&lan=EN" +
//				"&resultsTitle=EurolistbyEuronext&cha=7213&format=txt";
		
		String url = "http://www.euronext.com/search/download/trapridownloadpopup.jcsv?" +
				"pricesearchresults=actif&filter=1&mep=8583&belongsToList=market_EURLS&lan=EN" +
				"&resultsTitle=EurolistbyEuronext&cha=1800&format=txt";
		
		LOGGER.debug("EuroNext list url  :"+url);
		
		return url;
	}

	@Override
	public ApacheHttpClient httpConnect() { 
//		MyHttpClient myHttpClient;
//		try {
//			myHttpClient = this.myHttpConnect();
//		} catch (HttpException e) {
//			LOGGER.error("Can't open http connection",e);
//		} catch (IOException e) {
//			LOGGER.error("Can't open http connection",e);
//		}
//		return myHttpClient;
		return null;
	}

	public HttpClient myHttpConnect() throws HttpException, IOException {

//		int result;
//		MyHttpClient httpclient = new SimpleHttpClient();

//		GetMethod httpget = new GetMethod("http://www.euronext.com/");
//		try {
//			result = httpclient.executeMethod(httpget);
//			assert result == 200;
//		} finally {
//			httpget.releaseConnection();
//		}
//		try {
//			httpget = new GetMethod("http://www.euronext.com/landing/listedcompanies/overview/lc-18912-EN.html");
//			result = httpclient.executeMethod(httpget);
//			assert result == 200;
//		} finally {
//			httpget.releaseConnection();
//		}
//		try {
//			httpget = new GetMethod("http://www.euronext.com/trader/priceslists/priceslists-1800-EN.html?filter=1&eligibilityList=&belongsToList=market_EURLS&mep=8583&economicGroupList=&capitalizationList=&investmentList=invZone_3");
//			result = httpclient.executeMethod(httpget);
//			assert result == 200;
//		} finally {
//			httpget.releaseConnection();
//		}

		return null;
	}

	@Override
	public void stopThreads() {
		LOGGER.info("That's all ... Bye");
	}

	public SourceClient connect(int connectionId) throws RestartServerException {
//		SourceClient retour;
//		retour = this.httpConnect();
//		return retour;
		return null;
	}

	public int crashResart(int connectionId) {
		//silent
		return 0;
	}

	public void shutdownSource(SourceClient sourceClient, int connectionId) {	
		//silent
	}

	@Override
	@SuppressWarnings("unchecked")
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
//		return new GetMethod(url.getUrl());
		return null;
	}
	
	
}
