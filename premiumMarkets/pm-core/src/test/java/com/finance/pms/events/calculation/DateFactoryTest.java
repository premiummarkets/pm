package com.finance.pms.events.calculation;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.threads.ConfigThreadLocal;

public class DateFactoryTest {
	
	private static SpringContext springContext;
	
	@BeforeClass
	public static void oneTimeSetup() {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();

		EventSignalConfig config = new EventSignalConfig();
		ConfigThreadLocal.set("eventSignal", config);

	}

	@AfterClass
	public static void ontTimeTearDown() {
		springContext.close();
	}

	@Test
	public void testEndDateFix() {
		Date actualDateTime = DateFactory.getNowEndTime();
		Stock stock = DataSource.getInstance().loadStockBySymbol("DFFN");
		
		Date endDateFix = DateFactory.endDateFix(actualDateTime, stock.getMarket().getUTCTimeLag(), stock.getTradingMode());
		
		System.out.println("End date US Localfixed:" + endDateFix);
		
	}
	
	@Test
	public void testEndDateNonStopFix() {
		Date actualDateTime = DateFactory.getNowEndTime();
		Stock stock = DataSource.getInstance().loadStockBySymbol("BTC-USD");
		
		Date endDateFix = DateFactory.endDateFix(actualDateTime, stock.getMarket().getUTCTimeLag(), stock.getTradingMode());
		
		System.out.println("End date US Localfixed:" + endDateFix);
		
	}

}
