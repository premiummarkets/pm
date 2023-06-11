package com.finance.pms.datasources.files;

import java.io.IOException;
import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.threads.ConfigThreadLocal;

public class InputFileCheckerTest {
	
	private static SpringContext springContext;
	
	@BeforeClass
	public static void oneTimeSetup() throws IOException {
		springContext = new SpringContext("/home/guil/Developpement/git/forecast/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
		//springContext.optionalPostInit();
	}

	@AfterClass
	public static void onTimeTearDown() {
		springContext.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckInputAgainstQuotations() throws NotEnoughDataException, NoQuotationsException {
		Stock tell = DataSource.getInstance().loadStockBySymbol("TELL");
		InputFileChecker.checkInputAgainstQuotations(
				"/home/guil/Prgs/PremiumMarkets/autoPortfolioLogs/gwtk_predict_61ec6a51-90cb-4137-9ba6-3c8bb091a224_TELL_k_training_TELL.csv", 
				tell, ValidityFilter.OHLCV, 0, 211, new HashSet<>());
	}

}
