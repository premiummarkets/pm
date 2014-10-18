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

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;


public abstract class HttpSourceQuotation extends HttpSource implements SourceConnector {

	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceQuotation.class);
	private PoolSemaphore threadPool;

	public HttpSourceQuotation(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops,beanFactory);
		LOGGER.debug("Number of Http Threads : " + this.nbHttpThreads);
		threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);
	}


	@Override
	public abstract MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay,
			String endYear, String endMonth, String endDay);

	@Override
	public String getStockInfoPageURL(String isin) {//throws ToDoYahooException {
		LOGGER.debug("Fecthing complementary stock information is not implemented by default");
		throw new RuntimeException("Fecthing complementary stock information is not implemented by default");
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		throw new RuntimeException("Stock list fetching is not implemented for Quotation only http source");
	}

	@Override
	public PoolSemaphore getThreadPool() {
		return this.threadPool;
	}

	@Override
	public void stopThreads() {
		LOGGER.info("That's all ... Bye");
	}

	public int crashResart(int connectionId) {
		return 0;
	}

	public void shutdownSource(SourceClient c, int connectionId) {
		//silent
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
