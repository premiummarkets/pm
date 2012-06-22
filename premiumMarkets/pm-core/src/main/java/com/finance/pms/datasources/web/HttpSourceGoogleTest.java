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

import org.junit.Before;
import org.junit.Test;

public class HttpSourceGoogleTest {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void getMarketStockListBaseURLTest() throws Exception {
		
	//	HttpSourceGoogle httpSourceGoogle = new HttpSourceGoogle("");
		
//		GetMethod httpGetMethod = httpSourceGoogle.getStockListRequest(20, Market.NYSE);
		
		//FIXME test int result = httpSourceGoogle.getHttpClient().executeMethod(httpGetMethod);
		// Display status code
//		if (result != 200) {
//			System.out.println("Http Request status : " + result);
//			System.out.println("Request : " + httpGetMethod.getURI());
//		} else {
//			String line;
//			BufferedReader dis = new BufferedReader(new InputStreamReader(httpGetMethod.getResponseBodyAsStream(), "utf-8"));
//			for (int count = 0; (line = dis.readLine()) != null; count++) {
//				if (line.length() > 0) {
//					System.out.print(line);
//				}
//			}
//			dis.close();
//		}
	}
	
	@Test
	public void readURLTest() throws UnsupportedEncodingException {
		
	//	HttpSourceGoogle httpSourceGoogle = new HttpSourceGoogle("");
		
//		InputStreamReader fileReader = new InputStreamReader(this.getClass().getResourceAsStream("google.json"),"utf-8");
////		Charset charset = Charset.forName(fileReader.getEncoding());
////		CharsetDecoder charsetDecoder = charset.newDecoder();
////		charsetDecoder.
//		MarketList list = httpSourceGoogle.readUrl(fileReader);
//		System.out.println(list);
//		
//		GetMethod httpGetMethod = httpSourceGoogle.getStockListRequest(1602, Market.NYSE);
//		MarketList list1 = httpSourceGoogle.readURL(httpGetMethod);
//		System.out.println(list1);
//		
//		
//		List<Validatable> list2 = list1.getStockList(Market.NYSE, StockCategories.DEFAULT_CATEGORY,MarketQuotationProviders.DEFAULT);
//		System.out.println(list2);
//		System.out.println("Res size : "+list2.size());
	}
}
