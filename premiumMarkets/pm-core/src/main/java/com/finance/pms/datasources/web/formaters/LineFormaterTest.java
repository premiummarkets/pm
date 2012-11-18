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
