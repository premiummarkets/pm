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
package com.finance.pms.talib.indicators;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;

public class LinearRegressionTest {
	
	LinearRegression linearRegression;
	
	@Before
	public void setUp() throws Exception {
		
		SpringContext springContext = new SpringContext("/home/guil/Developpement/Quotes/pms/db.properties");
		//springContext.setDataSource("/home/guil/Developpement/Quotes/pms/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
		//Stock stock = DataSource.getInstance().loadStockBySymbol("LYXHEA.XPAR");
		Stock stock = DataSource.getInstance().loadStockBySymbol("DJI");
		linearRegression = new LinearRegression(stock, new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01"), new Date(), stock.getMarketValuation().getCurrency());
	}

	@Test
	public final void testTalibCall() {
		linearRegression.exportToCSV();
	}
}
