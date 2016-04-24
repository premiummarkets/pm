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
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

/**
 * The Class HttpSourceNASDAQ.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class HttpSourceNASDAQ extends HttpSource implements SourceConnector {

	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceNASDAQ.class);

	public HttpSourceNASDAQ(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		return "http://www.nasdaq.com//asp/symbols.asp?exchange=Q&start=0";
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		throw new RuntimeException("Fecthing stock information is not implemented for "+this.getClass().getSimpleName());
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		throw new RuntimeException("Fecthing stock quotation is not implemented for "+this.getClass().getSimpleName());
	}

	@Override
	public void stopThreads() {
		LOGGER.info("That's all ... Bye");
	}

	public SourceClient connect(int connectionId) throws RestartServerException {
		SourceClient retour;
		retour = this.httpConnect();
		return retour;
	}

	public int crashResart(int connectionId) {
		//silent
		return 0;
	}

	public void shutdownSource(SourceClient sourceClient, int connectionId) {	
		//silent
	}

	@Override
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new HttpGet(url.getUrl());
	}

	
}
