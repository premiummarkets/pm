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
package com.finance.pms.datasources.web.formaters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.datasources.db.Validatable;

public class LineFormaterTest extends TestCase {
	
	
//	MyFormater formater;
	
//	class MyFormater extends StockListASXFormater {
//		public MyFormater() {
//			super("",StockCategories.DEFAULT_CATEGORY,MarketQuotationProviders.DEFAULT);
//		}
//		
//		public Stock getStock() {
//			Stock stock = null;
//			if (this.params.size() != 0) stock = (Stock) this.params.get(0);
//			return stock;
//		}
//	}
	
	LineFormater formater;
	
	@Before
	public void setUp() throws Exception {
		
		//formater = new MyFormater();
		//formater = new StockListBSEFormater("",StockCategories.DEFAULT_CATEGORY,MarketQuotationProviders.DEFAULT);
		//formater = new StockListYahooScratchIndexFormater("http://uk.finance.yahoo.com/q/cp?s="+ URLEncoder.encode("^FTLC","UTF-8"));
		//FIXME //formater = new StockListYahooIndexFormater("",Market.LSE);
	}

	@Test
	public final void testFormatLine() {
		
		try {
			BufferedReader reader = new BufferedReader(
					//new FileReader("/home/guil/Developpement/Quotes/pms/tmp/ASXListedCompanies.csv"));
					//new FileReader("/home/guil/Developpement/Quotes/pms/tmp/EQ230109.CSV"));
					//new FileReader("/home/guil/Developpement/Quotes/pms/tmp/cp.html"));
					new  FileReader("/home/guillaumet/Desktop/quotes.csv"));
			String line;
			for (;(line=reader.readLine()) != null; ) {
				List<Validatable> stock = formater.formatLine(line);
				System.out.println(stock);
				//System.out.println(stock.size());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StopParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
