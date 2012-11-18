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
		houseTrendDerivation = new HouseTrendDerivation(stock, firstDate, lastDate, stock.getMarket().getCurrency(), 84);
		
		calculatedEvts = houseTrendDerivation.calculateEventsFor(EVTLISTNAME);
		
	}

	@Test
	public void test() {
		houseTrendDerivation.exportToCSV(calculatedEvts, EVTLISTNAME);
	}

}
