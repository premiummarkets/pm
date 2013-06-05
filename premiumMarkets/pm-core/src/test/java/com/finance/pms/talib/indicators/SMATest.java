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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;

public class SMATest {
	@Before
	public void setUp() throws Exception {
		
		SpringContext springContext = new SpringContext("/home/guil/Developpement/Quotes/pms/db.properties");
		springContext.standardInit(new String[]{"/home/guil/Developpement/Quotes/pms/db.properties"});
	}

	//@Test
	public final void test() throws TalibException, NoQuotationsException, ParseException {
		//2008-Jun-02	2010-Jun-02
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
		Stock stock = new Stock("BARC.L","GB0031348658");
		stock.setLastQuote(dateFormat.parse("2010-Jun-02"));
		stock.setMarketValuation(new MarketValuation(Market.LSE));
		SMA sma = new SMA(stock, 200, dateFormat.parse("2008-Jun-02"), dateFormat.parse("2010-Jun-02"), stock.getMarketValuation().getCurrency());
		//Double smaTrend = sma.getSma()[sma.getOutNBElement().value -1] - sma.getSma()[sma.getOutBegIdx().value];
		int i = sma.getOutNBElement().value - 1;
//		int j = sma.getOutBegIdx().value;
//		int i = sma.getOutNBElement().value - sma.getOutBegIdx().value;
		int j = 0;
//		int i = sma.getOutNBElement().value - sma.getOutBegIdx().value;
//		int j = 0;
		Double smaTrend = sma.getSma()[i] - sma.getSma()[j];
		System.out.println(sma.getSma()[i]+" - "+sma.getSma()[j]);
		System.out.println(smaTrend);
	}
	
	
	@Test
	public void test2() throws Exception {
		
//		SpringContext springContext = new SpringContext();
//		springContext.setDataSource("/home/guil/Developpement/Quotes/pms/db.properties");
//		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
//		springContext.refresh();
		
		Stock stock = DataSource.getInstance().loadStockBySymbol("AKA.PA");
		SMA sma = new SMA(stock, 12, new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("05/03/12 09:00:00"), new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("06/03/12 17:35:00"), stock.getMarketValuation().getCurrency());
		sma.exportToCSV();
	}


}
