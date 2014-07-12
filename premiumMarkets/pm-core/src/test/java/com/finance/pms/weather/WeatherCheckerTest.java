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
package com.finance.pms.weather;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;

public class WeatherCheckerTest extends TestCase {
	
	WeatherChecker testObject;
//	private SimpleDateFormat simpleDateFormat;
//	private Stock refStock;

	@Before
	public void setUp() throws Exception {
		
		SpringContext springContext = new SpringContext("/home/guil/Developpement/Quotes/pms/db.properties");
		//springContext.setDataSource("/home/guil/Developpement/Quotes/pms/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
//		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		refStock = AnalysisClient.REF_STOCK;
		
	}
	
	@Test
	public void test() {
		Assert.assertTrue(true);
	}

	//FIXME
//	@Test
//	public final void testPreCalculateEvents() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("29/08/2011"), simpleDateFormat.parse("01/09/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(4,preCalculateEvents.size());
//		
//	}
//	
//	@Test
//	public final void testPreCalculateEvents2() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("29/08/2011"), simpleDateFormat.parse("02/09/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(4,preCalculateEvents.size());
//		
//	}
//	
//	@Test
//	public final void testPreCalculateEvents3() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("01/08/2011"), simpleDateFormat.parse("05/08/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(2,preCalculateEvents.size());
//		
//	}
//	
//	@Test
//	public final void testPreCalculateEvents4() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("05/08/2011"), simpleDateFormat.parse("05/09/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(4,preCalculateEvents.size());
//		
//	}
//	
//	@Test
//	public final void testPreCalculateEvents5() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("05/07/2011"), simpleDateFormat.parse("01/08/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(4,preCalculateEvents.size());
//		
//	}
//	
//	@Test
//	public final void testPreCalculateEvents6() throws ParseException, NotEnoughDataException {
//		
//		refStock.setLastQuote(simpleDateFormat.parse("01/09/2011"));
//		
//		testObject = new WeatherChecker(refStock, simpleDateFormat.parse("28/09/2011"), simpleDateFormat.parse("01/12/2011"), Currency.EUR);
//	
//		Map<EventKey, EventValue> preCalculateEvents = testObject.preCalculatedEventData;
//		
//		System.out.println(preCalculateEvents);
//		assertEquals(2,preCalculateEvents.size());
//		
//	}
}
