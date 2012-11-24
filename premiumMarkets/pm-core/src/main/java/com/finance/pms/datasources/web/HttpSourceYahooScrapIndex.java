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

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.SimpleHttpClient;

public class HttpSourceYahooScrapIndex extends HttpSourceMarket {
	
//	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceYahooScrapIndex.class);
//	
//	private final String CAC_URL = "http://fr.finance.yahoo.com/q/cp?s=";
//	private final String CAC_Param = "^FCHI";
//	
	private final String FTSE_URL = "http://uk.finance.yahoo.com/q/cp?s=";
//	private final String FTSE_Param = "^FTLC";
	
	
	public HttpSourceYahooScrapIndex(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	protected MyHttpClient myHttpConnect() throws HttpException, IOException {
		return new SimpleHttpClient();
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		String ftse = FTSE_URL;
		//FIXME
//		try {
//			ftse = ftse + URLEncoder.encode(FTSE_Param,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.error(e);
//		}
//		String cac = CAC_URL;
//		try {
//			cac = cac + URLEncoder.encode(CAC_Param,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.error(e);
//		}
		return ftse;
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		throw new UnsupportedOperationException();
	}
}
