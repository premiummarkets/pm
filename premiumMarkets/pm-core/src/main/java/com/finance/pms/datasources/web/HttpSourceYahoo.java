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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

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
 * The Class HttpSourceYahoo.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceYahoo extends HttpSource implements SourceConnector {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceYahoo.class);
	
	/** The thread pool. */
	private PoolSemaphore threadPool;

	/**
	 * Instantiates a new http source yahoo.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 * @param beanFactory 
	 */
	public HttpSourceYahoo(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
		LOGGER.debug("Number of Http Threads : "+this.nbHttpThreads);
		threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);
	}
	
	@Override
	public MyHttpClient httpConnect() {
		SimpleHttpClient simpleHttpClient = new SimpleHttpClient();
		
		HttpConnectionManagerParams connectionManagerParams = new HttpConnectionManagerParams();
		connectionManagerParams.setSoTimeout(30000);
		connectionManagerParams.setConnectionTimeout(30000);
		MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(connectionManagerParams);
		simpleHttpClient.setHttpConnectionManager(manager);
		
		return simpleHttpClient;
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


	/**
	 * Gets the yahoo quote url.
	 * 
	 * @param ticker the ticker
	 * @param startYear the start year
	 * @param startMonth the start month
	 * @param startDay the start day
	 * @param endYear the end year
	 * @param endMonth the end month
	 * @param endDay the end day
	 * 
	 * @return the yahoo quote url
	 */
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

    /* (non-Javadoc)
     * @see com.finance.pms.datasources.web.HttpSource#getStockInfoPageURL(java.lang.String)
     */
    @Override
	public String getStockInfoPageURL(String isin) {//throws ToDoYahooException {
        // TODO Completer la r�cup�ration des compl�ments de stocks sur Yahoo
        LOGGER.debug("Fecthing stock information is not implemented for yahoo");
        throw new RuntimeException("Fecthing stock information is not implemented for yahoo");
    }
    
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getMarketStockListURL(java.lang.String, java.lang.String, java.lang.String, com.finance.pms.datasources.shares.StockCategories)
	 */
	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		throw new RuntimeException("Fecthing stock list is not implemented for yahoo");
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.HttpSource#getThreadPool()
	 */
	@Override
	public PoolSemaphore getThreadPool() {
		return this.threadPool;
	}
	
	
    /* (non-Javadoc)
     * @see com.finance.pms.db.HttpSource#stopThreads()
     */
    @Override
	public void stopThreads() {
        LOGGER.info("That's all ... Bye");
    }
    
	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#restartSource(int)
	 */
	public int crashResart(int connectionId) {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#shutdownSource(com.finance.pms.threads.SourceClient, int)
	 */
	public void shutdownSource(SourceClient c, int connectionId) {
		LOGGER.info("Nothing to do for now here ...");
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#connect(int)
	 */
	public SourceClient connect(int connectionId) throws RestartServerException {
		SourceClient retour;
		retour = this.httpConnect();
		return retour;
	}

	@Override
	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new GetMethod(url.getUrl());
	}

	
}
