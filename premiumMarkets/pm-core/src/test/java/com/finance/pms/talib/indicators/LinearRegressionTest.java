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
package com.finance.pms.talib.indicators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;

@SuppressWarnings("unused")
public class LinearRegressionTest {
	
	LinearRegression linearRegression;
	private SpringContext springContext;
	
	@Before
	public void setUp() throws Exception {
		
		springContext = new SpringContext("/home/guil/Developpement/Quotes/pms/db.properties");
		//springContext.setDataSource("/home/guil/Developpement/Quotes/pms/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
		//Stock stock = DataSource.getInstance().loadStockBySymbol("LYXHEA.XPAR");
		Stock stock = DataSource.getInstance().loadStockBySymbol("DJI");
		//linearRegression = new LinearRegression(stock, new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01"), new Date(), stock.getMarketValuation().getCurrency());
		linearRegression = new LinearRegression(); //FIXME
	}
	
	@After
	public void tearDown() {
		springContext.close();
	}

	@Test
	public final void testTalibCall() {
		//linearRegression.exportToCSV();
	}
}
