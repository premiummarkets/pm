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

import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.google.MarketList;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;
import com.google.gson.Gson;

//FIXME
public class HttpSourceGoogle extends HttpSource implements SourceConnector {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceGoogle.class);
	/** The thread pool. */
	private PoolSemaphore threadPool;
	
	public HttpSourceGoogle(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		
		LOGGER.debug("Number of Http Threads : "+this.nbHttpThreads);
		threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);
	}
	
	@Override
	public String getCategoryStockListURL(StockCategories marche, String ...params) {
		
		//http://finance.google.com/finance/stockscreener
		//#c0=MarketCap&min0=4989&max0=2270000000000&c1=PE&min1=0.00&max1=4663&c2=DividendYield&min2=0&max2=328&c3=Price52WeekPercChange&min3=-99.68&max3=130&region=us&sector=AllSectors&exchange=NYSE&sort=&sortOrder=
		//#c0=MarketCap&min0=150000000&max0=2270000000000&region=us&sector=AllSectors&exchange=NYSE&sort=&sortOrder=
		
		//http://finance.google.com/finance/
		//gl=us&hl=en&output=json&start=0&num=20&noIL=1
		//&q=((exchange:NYSE) OR (exchange:NASDAQ) OR (exchange:AMEX)) [(MarketCap > 4989 | MarketCap = 4989) & (MarketCap < 2270000000000 | MarketCap = 2270000000000) & (PE > 0.21 | PE = 0.21) & (PE < 4663 | PE = 4663) & (DividendYield > 0 | DividendYield = 0) & (DividendYield < 328 | DividendYield = 328) & (Price52WeekPercChange > -99.81 | Price52WeekPercChange = -99.81) & (Price52WeekPercChange < 781 | Price52WeekPercChange = 781)]
		//restype=company
		
//		return getStockListRequest(20,null).getQueryString();
		return null;
		
	}

	private String getMarketStockListBaseURL() {
		StringBuilder httpq = new StringBuilder("http://finance.google.com/finance?");
		httpq.append("gl=us&hl=en&output=json&start=0&noIL=1&restype=company");
		return httpq.toString();
	}
	
//	public GetMethod getStockListRequest(Integer numberStocksElements,Market market) {
//
//		String httpgetStr = this.getMarketStockListBaseURL();
//		httpgetStr = httpgetStr.concat("&num="+numberStocksElements);
//		try {
//			//String Q = "((exchange:NYSE) OR (exchange:NASDAQ) OR (exchange:AMEX)) [(MarketCap > 4989 | MarketCap = 4989) & (MarketCap < 2270000000000 | MarketCap = 2270000000000) & (PE > 0.21 | PE = 0.21) & (PE < 4663 | PE = 4663) & (DividendYield > 0 | DividendYield = 0) & (DividendYield < 328 | DividendYield = 328) & (Price52WeekPercChange > -99.81 | Price52WeekPercChange = -99.81) & (Price52WeekPercChange < 781 | Price52WeekPercChange = 781)]";
//			String Q = "(exchange:"+market.getMarketName()+") [(MarketCap > 4989 | MarketCap = 4989) & (MarketCap < 2270000000000 | MarketCap = 2270000000000) & (PE > 0.21 | PE = 0.21) & (PE < 4663 | PE = 4663) & (DividendYield > 0 | DividendYield = 0) & (DividendYield < 328 | DividendYield = 328) & (Price52WeekPercChange > -99.81 | Price52WeekPercChange = -99.81) & (Price52WeekPercChange < 781 | Price52WeekPercChange = 781)]";
//			httpgetStr  = httpgetStr.concat("&q=").concat(URLEncoder.encode(Q,"utf-8"));
//			
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.debug(e);
//		}
//		
//		LOGGER.debug(httpgetStr);
//		
//		GetMethod httpget = new GetMethod(httpgetStr);
//		return httpget;
//		
//	}
	
//	public MarketList readURL(GetMethod getMethod) {
//
//		MarketList ml = new MarketList();
//
//		try {
//			int result = this.getConnectionFromPool().executeMethod(getMethod);
//			// Display status code
//			if (result != 200) {
//				System.out.println("Http Request status : " + result);
//				System.out.println("Request : " + getMethod.getURI());
//			} else {
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
//				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(arrayOutputStream));
//				String line;
//				while ((line = bufferedReader.readLine()) != null) {
//					bufferedWriter.write(line.replace("\\x", "\\\\x"));
//				}
//				bufferedWriter.close();
//				ml = this.readUrl(new InputStreamReader(new ByteArrayInputStream(arrayOutputStream.toByteArray())));
//			}
//		} catch (URIException e) {
//			LOGGER.debug(e);
//		} catch (HttpException e) {
//			LOGGER.debug(e);
//		} catch (IOException e) {
//			LOGGER.debug(e);
//		} finally {
//			getMethod.releaseConnection();
//		}
//
//		return ml;
//
//	}
	
	MarketList readUrl(Reader reader) {
		
		MarketList list;
		Gson gs = new Gson();
		list = gs.fromJson(reader,MarketList.class);
		
		return list;
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		LOGGER.debug("Fecthing stock information is not implemented for google");
        throw new RuntimeException("Fecthing stock information is not implemented for google");
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		//http://finance.google.com/finance/historical?q=NYSE:IBM&output=csv
		//http://finance.google.com/finance/historical?cid=18241&startdate=Oct+6%2C+1970&enddate=Oct+4%2C+2008&output=csv
		LOGGER.debug("http://finance.google.com/finance/historical?q=" +ticker+
				"&startdate="+ startMonth + "+" + startDay + ",+" + startYear +
				"&enddate="+ endMonth + "+" + endDay + ",+" + endYear + "&output=csv");
		
		return new MyUrl(
				"http://finance.google.com/finance/historical?q=" +ticker+
			"&startdate="+ startMonth + "+" + startDay + ",+" + startYear +
			"&enddate="+ endMonth + "+" + endDay + ",+" + endYear + "&output=csv");
	}

	@Override
	public PoolSemaphore getThreadPool() {
		return this.threadPool;
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

	@Override
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	
//	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
//		return new GetMethod(url.getUrl());
//	}
	
	
}
