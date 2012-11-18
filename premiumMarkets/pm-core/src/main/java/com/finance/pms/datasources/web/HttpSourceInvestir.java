/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SimpleHttpClient;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

public class HttpSourceInvestir extends HttpSource implements SourceConnector {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceInvestir.class);
	
	/** The thread pool. */
	private PoolSemaphore threadPool;
	
	public HttpSourceInvestir(String pathToprops, MyBeanFactoryAware beanFActoryAware) {
		super(pathToprops, beanFActoryAware);
		LOGGER.debug("Number of Http Threads : "+this.nbHttpThreads);
		threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);
	}

	@Override
	public MyHttpClient httpConnect() {
		return new SimpleHttpClient() {
			
			@Override
			public void preExecuteMethod(HttpMethod method) {
				setHeader(method);
			}
			
			/**
			 * @param httppost
			 */
			private void setHeader(HttpMethod httppost) {
				httppost.setRequestHeader("Host","www.boursorama.com");
				httppost.setRequestHeader("User-Agent","Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.9.2.22) Gecko/20110905 Ubuntu/10.04 (lucid) Firefox/3.6.22");
				httppost.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				httppost.setRequestHeader("Accept-Language","en-us,en;q=0.5");
				httppost.setRequestHeader("Accept-Encoding","gzip,deflate");
				httppost.setRequestHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
				httppost.setRequestHeader("Keep-Alive","115");
				httppost.setRequestHeader("Connection","keep-alive");
			}

		};
	}

	@Override
	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return new GetMethod(url.getUrl());
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay) {
		throw new NotImplementedException();
	}
	
	public String getStockInvestirURL() {
		return "http://bourse.investir.fr/bourse/cotations/fiche/historique/donnees.jsp?code=%s&place=%s&codif=ISIN&range=%d&period=DAY&offset=";
	}

	@Override
	public String getStockInfoPageURL(String refName) throws UnsupportedEncodingException {
		throw new NotImplementedException();
	}

	@Override
	public String getCategoryStockListURL(StockCategories marche, String... params) {
		throw new NotImplementedException();
	}

	@Override
	public PoolSemaphore getThreadPool() {
		return threadPool;
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
		return 0;
	}

	
	public void shutdownSource(SourceClient sourceClient, int connectionId) {
		LOGGER.info("Nothing to do for now here ...");
		
	}
}
