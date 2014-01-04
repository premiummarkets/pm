/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SimpleHttpClient;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;



// TODO: Auto-generated Javadoc
/**
 * The Class HttpSourceEuroNext.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceEuroNext extends HttpSource implements SourceConnector {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceEuroNext.class);
	
	/** The thread pool. */
	private PoolSemaphore threadPool;

	/**
	 * Instantiates a new http source euro next.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 * @param beanFactory 
	 */
	public HttpSourceEuroNext(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
		LOGGER.debug("Number of Http Threads : 1 - hard coded :)");
		threadPool = new PoolSemaphore(1, this, false);
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getStockInfoPageURL(java.lang.String)
	 */
	@Override
	public String getStockInfoPageURL(String isin) {
		throw new RuntimeException("Fecthing stock information is not implemented for Euro Next");
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getStockQuotationURL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		throw new RuntimeException("Fecthing stock quotation is not implemented for Euro Next");
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getMarketStockListURL(java.lang.String, java.lang.String, java.lang.String, com.finance.pms.datasources.shares.StockCategories)
	 */
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

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getThreadPool()
	 */
	@Override
	public PoolSemaphore getThreadPool() {
		return this.threadPool;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#httpConnect()
	 */
	@Override
	public MyHttpClient httpConnect() { 
		MyHttpClient myHttpClient = null;
		try {
			myHttpClient = this.myHttpConnect();
		} catch (HttpException e) {
			LOGGER.error("Can't open http connection",e);
		} catch (IOException e) {
			LOGGER.error("Can't open http connection",e);
		}
		return myHttpClient;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#httpConnect()
	 */
	/**
	 * Myhttp connect.
	 * 
	 * @return the my http client
	 * 
	 * @throws HttpException the http exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	public MyHttpClient myHttpConnect() throws HttpException, IOException {

		int result;
		MyHttpClient httpclient = new SimpleHttpClient();

		GetMethod httpget = new GetMethod("http://www.euronext.com/");
		try {
			result = httpclient.executeMethod(httpget);
			assert result == 200;
		} finally {
			httpget.releaseConnection();
		}
		try {
			httpget = new GetMethod("http://www.euronext.com/landing/listedcompanies/overview/lc-18912-EN.html");
			result = httpclient.executeMethod(httpget);
			assert result == 200;
		} finally {
			httpget.releaseConnection();
		}
		try {
			httpget = new GetMethod("http://www.euronext.com/trader/priceslists/priceslists-1800-EN.html?filter=1&eligibilityList=&belongsToList=market_EURLS&mep=8583&economicGroupList=&capitalizationList=&investmentList=invZone_3");
			result = httpclient.executeMethod(httpget);
			assert result == 200;
		} finally {
			httpget.releaseConnection();
		}

		return httpclient;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#stopThreads()
	 */
	@Override
	public void stopThreads() {
		LOGGER.info("That's all ... Bye");
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#connect(int)
	 */
	public SourceClient connect(int connectionId) throws RestartServerException {
		SourceClient retour;
		retour = this.httpConnect();
		return retour;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#restartSource(int)
	 */
	public int crashResart(int connectionId) {
		//silent
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#shutdownSource(com.finance.pms.threads.SourceClient, int)
	 */
	public void shutdownSource(SourceClient sourceClient, int connectionId) {	
		//silent
	}

	@Override
	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new GetMethod(url.getUrl());
	}
	
	
}
