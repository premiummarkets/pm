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
 * The Class HttpSourceNASDAQ.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceNASDAQ extends HttpSource implements SourceConnector {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceNASDAQ.class);
	
	/** The thread pool. */
	private PoolSemaphore threadPool;
	
	/**
	 * Instantiates a new http source nasdaq.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 * @param beanFactory 
	 */
	public HttpSourceNASDAQ(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
		LOGGER.debug("Number of Http Threads :" + this.nbHttpThreads);
		threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getMarketStockListURL(java.lang.String, java.lang.String, java.lang.String, com.finance.pms.datasources.shares.StockCategories)
	 */
	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		return "http://www.nasdaq.com//asp/symbols.asp?exchange=Q&start=0";
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getStockInfoPageURL(java.lang.String)
	 */
	@Override
	public String getStockInfoPageURL(String isin) {
		throw new RuntimeException("Fecthing stock information is not implemented for "+this.getClass().getSimpleName());
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getStockQuotationURL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		throw new RuntimeException("Fecthing stock quotation is not implemented for "+this.getClass().getSimpleName());
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
	public MyHttpClient httpConnect() { //throws IOException, HttpException {
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
		
		//MyHttpClient httpclient = getHttpClient();		
		return new SimpleHttpClient();
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
