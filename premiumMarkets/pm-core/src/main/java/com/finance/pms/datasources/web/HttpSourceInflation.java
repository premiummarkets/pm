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

import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

public class HttpSourceInflation extends HttpSource implements SourceConnector {
	
	public HttpSourceInflation(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}


	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay) {
		return new MyUrl("http://inflationdata.com/Inflation/Consumer_Price_Index/HistoricalCPI.aspx?reloaded=true");
	}


	@Override
	public SourceClient connect(int connectionId) throws RestartServerException {
		SourceClient retour;
		retour = this.httpConnect();
		return retour;
	}


	@Override
	public int crashResart(int connectionId) {
		return 0;
	}


	@Override
	public void shutdownSource(SourceClient sourceClient, int connectionId) {
		//silent
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		throw new RuntimeException("Fetching complementary stock information is not implemented by default");
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		throw new RuntimeException("Stock list fetching is not implemented for Quotation only HTTP source");
	}


	@Override
	@SuppressWarnings("unchecked")
	protected HttpGet getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new HttpGet(url.getUrl());
	}

}
