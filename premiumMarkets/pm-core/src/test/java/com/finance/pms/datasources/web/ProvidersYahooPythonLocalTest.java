package com.finance.pms.datasources.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;

public class ProvidersYahooPythonLocalTest {
	
	private SpringContext springContext;
	private ProvidersYahooPythonLocal providersYahoo;

	@Before
	public void setUp() throws Exception {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
        springContext.loadBeans("connexions.xml", "swtclients.xml","talibanalysisservices.xml");
        springContext.refresh();

        providersYahoo = springContext.getBean("yahooProviderSource", ProvidersYahooPythonLocal.class);
	}

	@After
	public void tearDown() throws Exception {
		springContext.close();
	}

	@Test
	public void testGetQuotes() throws Exception {
		
		Stock stock = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol("BTC-USD").get(0);
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2024-10-09");
        Date end = DateFactory.getNowEndDate();
        
		providersYahoo.getQuotes(stock, start, end);
		
	}

}
