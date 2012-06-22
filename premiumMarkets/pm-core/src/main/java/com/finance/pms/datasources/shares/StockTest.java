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
package com.finance.pms.datasources.shares;

import java.security.InvalidAlgorithmParameterException;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class StockTest.
 * 
 * @author Guillaume Thoreton
 */
@SuppressWarnings("all")
public class StockTest extends TestCase {

	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		
		//FIXME fix test Providers.getInstance( "boursorama");
	
	}

	
	/**
	 * Test stock string string.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testStockStringString() {
		
		System.out.println("s1");
		Stock s1 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
		assertEquals("FTE.PA",s1.getSymbol());
		
		System.out.println("s2");
		Stock s2 = new Stock(null,null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		assertEquals(null,s2.getSymbol());
		
		System.out.println("s3");
		Stock s3 = new Stock(Stock.MISSINGCODE,Stock.MISSINGCODE,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		assertEquals(Stock.MISSINGCODE,s3.getIsin());
		
		System.out.println("s4");
		Stock s4 = new Stock("FR0000",Stock.MISSINGCODE,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		assertEquals(null,s4.getSymbol());
		assertEquals("FR0000",s4.getIsin());

	}

	
	/**
	 * Test stock string string string string boolean stock categories.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testStockStringStringStringStringBooleanStockCategories() {
		//fail("Not yet implemented");
	}

	
	/**
	 * Test stock string string string string boolean stock categories date.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testStockStringStringStringStringBooleanStockCategoriesDate() {
		//fail("Not yet implemented");
	}

	
	/**
	 * Test set isin.
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testSetIsin() throws InvalidAlgorithmParameterException  {
		
		System.out.println("s1");
		Stock s1 = new Stock(null,"FTE.FR",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		assertEquals("FTE.FR",s1.getSymbol());
		
		System.out.println("s2");
		Stock s2 = new Stock(null,"FTE.FR",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		s2.setIsin("FR000");
		assertEquals("FTE.FR",s2.getSymbol());
		
		System.out.println("s3");
		Stock s3 = new Stock(null,"FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		try {
			s3.setIsin("GB000");
		} catch (InvalidAlgorithmParameterException e) {
			assertEquals(
					"WARN : Invalid match between isin :GB000 and symbol :FTE.FR while setting isin",
					e.getMessage());
		}
		assertEquals("FTE",s3.getSymbol());
		
		System.out.println("s4");
		Stock s4 = new Stock(null,Stock.MISSINGCODE,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		s4.setIsin("FR000");
		assertEquals(null,s4.getSymbol());
		
	}

	
	/**
	 * Test set symbol.
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testSetSymbol() throws InvalidAlgorithmParameterException {
		
		System.out.println("s1");
		
		System.out.println("s2");
		Stock s2 = new Stock("FR000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		s2.setSymbol("FTE");
		assertEquals("FTE",s2.getSymbol());
		
		System.out.println("s3");
		Stock s3 = new Stock("FR000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		s3.setSymbol("FTE.FR");
		assertEquals("FTE.FR",s3.getSymbol());
		
		System.out.println("s4");
		Stock s4 = new Stock("FR000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		try {
			s4.setSymbol("FTE.GB");
		} catch (InvalidAlgorithmParameterException e) {
			//e.printStackTrace();
			assertEquals(e.getMessage(), "WARN : Invalid match between symbol :FTE.GB and isin :FR000 while setting symbol");
		}
		assertEquals("FTE.GB",s4.getSymbol());
		
		System.out.println("s5");
		Stock s5 = new Stock("FR000","FTE.GB",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);	
		assertEquals("FTE.GB",s5.getSymbol());
		
		System.out.println("s6");
		Stock s6 = new Stock(null,"FTE.FR",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);	
		assertEquals("FTE.FR",s6.getSymbol());
		
		System.out.println("s7");
		Stock s7 = new Stock(null,"FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.EURONEXT);
		try {
			s7.getSymbol();
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(),
					"ERROR :Try to read an incomplete symbol : "+"FTE");
			//e.printStackTrace();
		}
		//assertEquals("FTE",s7.getSymbol());

//		System.out.println("s8");
//		Stock s8 = new Stock("FR000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),MarketListProviders.EURONEXT);
//		s8.setSymbol(Stock.MISSINGCODE);
//		assertEquals(Stock.MISSINGCODE,s8.getSymbol());
		
//		System.out.println("s9");
//		Stock s9 = new Stock(null,null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),MarketListProviders.EURONEXT);
//		s9.setSymbol(Stock.MISSINGCODE);
//		assertEquals(Stock.MISSINGCODE,s9.getSymbol());
		
//		System.out.println("s10");
//		Stock s10 = new Stock("FR0000",Stock.MISSINGCODE,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),MarketListProviders.EURONEXT);
//		s10.setSymbol(Stock.MISSINGCODE);
//		assertEquals(Stock.MISSINGCODE,s10.getSymbol());
//		assertEquals("FR0000",s10.getIsin());
		
//		System.out.println("s11");
//		Stock s11 = new Stock(null,Stock.MISSINGCODE,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),MarketListProviders.EURONEXT);
//		s11.setSymbol(Stock.MISSINGCODE);
//		assertEquals(Stock.MISSINGCODE,s11.getSymbol());
//		assertEquals(null,s11.getIsin());

	}
	
	
	/**
	 * Test get stock ref name.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testGetStockRefName() {
		//fail("Not yet implemented");
	}

	public void testEquals() {
		
		{
			Stock s1 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s2 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(true,s1.equals(s2));
		}
		{
			Stock s3 = new Stock("FR0000","PAJ",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock("BLABLA","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock("BLABLA",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("FR0000","FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock("FR0000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("FR0000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(true,s3.equals(s4));
		}
		{
			Stock s3 = new Stock("FR0000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("BLABLA",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock(null,null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock("FR0000",null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock(null,null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock(null,null,StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
		{
			Stock s3 = new Stock(null,"FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock(null,"FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(true,s3.equals(s4));
		}
		{
			Stock s3 = new Stock(null,"FTE",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			Stock s4 = new Stock(null,"PAJ",StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,"PAR"),Market.EURONEXT);
			assertEquals(false,s3.equals(s4));
		}
	}

}
