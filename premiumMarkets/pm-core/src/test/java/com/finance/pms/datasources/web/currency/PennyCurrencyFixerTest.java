package com.finance.pms.datasources.web.currency;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class PennyCurrencyFixerTest {

	private static SpringContext springContext;

	@BeforeClass
	public static void oneTimeSetup() {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();

	}

	@AfterClass
	public static void ontTimeTearDown() {
		springContext.close();
	}

	@Test
	public void testCheck() {
		PennyCurrencyFixer currencyFixer = new PennyCurrencyFixer();
		currencyFixer.check();
	}

	//@Test
	public void testFix() {
		PennyCurrencyFixer currencyFixer = new PennyCurrencyFixer();
		currencyFixer.fix();
	}
	
	//@Test
	public void split() throws NoQuotationsException {
		List<Stock> stocks = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol("AAPL");
		Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stocks.get(0), DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);
	}

}
