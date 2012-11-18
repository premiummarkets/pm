/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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



public class HttpSourceInflation extends HttpSourceQuotation {

//	/** The LOGGER. */
//	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceInflation.class);
//	private DateFormat dateFormat = new SimpleDateFormat("yyyy");

	
	public HttpSourceInflation(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}


	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear,
			String startMonth, String startDay, String endYear,
			String endMonth, String endDay) {
		
		//return new MyUrl("http://inflationdata.com/inflation/Consumer_Price_Index/HistoricalCPI.aspx?rsCPI_currentPage=");
		return new MyUrl("http://inflationdata.com/Inflation/Consumer_Price_Index/HistoricalCPI.aspx");
	}

	
//	@Override
//	public MyUrl getStockQuotationURL(String ticker, String startYear,
//			String startMonth, String startDay, String endYear,
//			String endMonth, String endDay) {
//
//		String[] dates ={"1913-1914","1915-1926","1927-1938","1939-1950","1951-1960","1961-1974","1975-1986","1987-1998","1999-2010"};
//		int PageNum=0;
//		Date startY;
//		Date endY;
//
//		try {
//			startY = dateFormat.parse(startYear);
//			endY  = dateFormat.parse(endYear);
//
//			for (String date : dates) {
//				String[] band = date.split("-");
//
//				Date first = dateFormat.parse(band[0]);
//				Date last =  dateFormat.parse(band[1]);
//				if (startY.compareTo(first) >=0 && startY.compareTo(last) <=0) {
//					if (endY.compareTo(last) > 0) throw new InvalidParameterException();
//					break;
//				}
//				PageNum++;
//			}
//
//		} catch (ParseException e) {
//			LOGGER.fatal("Shouldn't be here",e);
//		}
//		return new MyUrl("http://inflationdata.com/inflation/Consumer_Price_Index/HistoricalCPI.aspx?rsCPI_currentPage="+ (dates.length - PageNum));
//	}
	

}
