package com.finance.pms.events.quotations;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.threads.ConfigThreadLocal;

public class QuotationsFactoryTest {
	
	private static SpringContext springContext;
	
	@BeforeClass
	public static void oneTimeSetup() {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
		springContext.optionalPostInit();
	}

	@AfterClass
	public static void ontTimeTearDown() {
		springContext.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSpliFreeQuotationsInstance() throws NoQuotationsException, ParseException {
		Stock aapl = DataSource.getInstance().loadStockBySymbol("INXG.L");
		Date start = DateFactory.midnithDate(new Date()); //new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-06");
		Date end = DateUtils.addDays(start, 1); //new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-07");
		//QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(portfolioShare.getStock(), startDate, endDate, true, calculationCurrency, 1, ValidityFilter.CLOSE);
		Quotations aaplQs = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(aapl, start, end, true, Currency.GBP, 1, Quotations.ValidityFilter.CLOSE);
		System.out.println(aaplQs);
		System.out.println(Arrays.toString(aaplQs.getQuotationData().toArray()));
		
		Quotations aaplQsEur = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(aapl, start, end, true, Currency.EUR, 1, Quotations.ValidityFilter.CLOSE);
		System.out.println(aaplQsEur);
		System.out.println(Arrays.toString(aaplQsEur.getQuotationData().toArray()));
	}

}
