/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
import java.net.URLEncoder;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.SimpleHttpClient;

public class HttpSourceNSEMarket extends HttpSourceMarket {	
	
	public HttpSourceNSEMarket(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	protected MyHttpClient myHttpConnect() throws HttpException, IOException {
		return new SimpleHttpClient();
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
			
		String urlRet = "http://www.nseindia.com/content/indices/ind_%slist.csv";
		urlRet = String.format(urlRet,params[0].toLowerCase().replace(" ", ""));
		
		return urlRet;
		
	}

	@Override
	public String getStockInfoPageURL(String isin) throws UnsupportedEncodingException {
		return "http://in.finance.yahoo.com/lookup?s="+ URLEncoder.encode(isin,"UTF-8");
	}
	
	@Override
	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
//		Accept	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//		Accept-Encoding	gzip, deflate
//		Accept-Language	en-US,en;q=0.5
//		Connection	keep-alive
//		Host	www.nseindia.com
//		User-Agent	Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:18.0) Gecko/20100101 Firefox/18.0
		GetMethod getMethod = new GetMethod(url.getUrl());
		getMethod.setRequestHeader(new Header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		getMethod.setRequestHeader(new Header("Accept-Encoding", "gzip, deflate"));
		getMethod.setRequestHeader(new Header("Accept-Language", "en-US,en;q=0.5"));
		getMethod.setRequestHeader(new Header("Connection", "keep-alive"));
		getMethod.setRequestHeader(new Header("Host", "www.nseindia.com"));
		getMethod.setRequestHeader(new Header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:18.0) Gecko/20100101 Firefox/18.0"));
		return getMethod;
	}
}
