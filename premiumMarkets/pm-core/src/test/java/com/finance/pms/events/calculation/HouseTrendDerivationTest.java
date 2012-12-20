
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


package com.finance.pms.events.calculation;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.indicators.TalibException;

public class HouseTrendDerivationTest {
	
	private static final String EVTLISTNAME = "HTTest";
	HouseTrendDerivation houseTrendDerivation;
	private Map<EventKey, EventValue> calculatedEvts;
	
	@Before
	public void setUp() throws NotEnoughDataException, TalibException, NoQuotationsException, ParseException {
		
		SpringContext springContext = new SpringContext();
		springContext.setDataSource(System.getProperty("installdir") + File.separator +"db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
		Stock stock = DataSource.getInstance().loadStockBySymbol("GSPC");
		Date firstDate = new SimpleDateFormat("yyyy MM dd").parse("1999 11 08");
		Date lastDate =  new SimpleDateFormat("yyyy MM dd").parse("2012 10 24");
		houseTrendDerivation = new HouseTrendDerivation(stock, firstDate, lastDate, stock.getMarketValuation().getCurrency(), 84);
		
		calculatedEvts = houseTrendDerivation.calculateEventsFor(EVTLISTNAME);
		
	}

	@Test
	public void test() {
		houseTrendDerivation.exportToCSV(calculatedEvts, EVTLISTNAME);
	}

}
