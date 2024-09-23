package com.finance.pms.events.calculation;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.QuotationsFactories;
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
		
		System.out.println("End date US Localfixed: " + endDateFix);
		
	}
	
	@Test
	public void testEndDateNonStopFix() {
		Date actualDateTime = DateFactory.getNowEndTime();
		Stock stock = DataSource.getInstance().loadStockBySymbol("BTC-USD");
		
		Date endDateFix = DateFactory.endDateFix(actualDateTime, stock.getMarket().getUTCTimeLag(), stock.getTradingMode());
		
		System.out.println("End date US Localfixed: " + endDateFix);
		
	}
	
	@Test
	public void testRolling() throws ParseException {
		
		Stock aapl = DataSource.getInstance().loadStockBySymbol("AAPL");
		Stock fchi = DataSource.getInstance().loadStockBySymbol("FCHI");
		Stock btc = DataSource.getInstance().loadStockBySymbol("BTC-USD");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = dateFormat.parse("2022/09/19"); 
		
		Calendar rollingHourCal = Calendar.getInstance();
		rollingHourCal.setTime(startDate);
		rollingHourCal.set(Calendar.HOUR_OF_DAY, 0);
		rollingHourCal.set(Calendar.MINUTE, 0);
		rollingHourCal.set(Calendar.SECOND, 0);
		rollingHourCal.set(Calendar.MILLISECOND, 0);//Monday the 19th at 00:00
		System.out.println("rolling, Monday the 19th at 00:00 " + rollingHourCal.getTime());
		
		Calendar expectedCal = Calendar.getInstance();
		
		//aapl
		expectedCal.setTime(startDate);
		expectedCal.add(Calendar.DAY_OF_YEAR, -3);//Friday the 16th at 00:00
		expectedCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedCal.set(Calendar.MINUTE, 0);
		expectedCal.set(Calendar.SECOND, 0);
		expectedCal.set(Calendar.MILLISECOND, 0);
		System.out.println("expected, Friday the 16th at 00:00 " + expectedCal.getTime());
		
		for (int day = 0; day < 7; day++) {
			for (int hour = 0; hour < 24; hour++ ) {
				if (day < 5 && hour == 17 - aapl.getMarket().getUTCTimeLag()) {
					expectedCal.add(Calendar.DAY_OF_YEAR, 1);
					expectedCal.setTime(QuotationsFactories.getFactory().getValidQuotingDateAfterOrAt(expectedCal.getTime()));
				} else {
					expectedCal.setTime(QuotationsFactories.getFactory().getValidQuotingDateBeforeOrAt(expectedCal.getTime()));
				}
				rollingHourCal.add(Calendar.HOUR_OF_DAY, 1);
				Date applDate = DateFactory.endDateFix(rollingHourCal.getTime(), aapl.getMarket().getUTCTimeLag(), aapl.getTradingMode());
				System.out.println(String.format("AAPL On the " + rollingHourCal.getTime() + ": expected %s, real %s ", expectedCal.getTime(), applDate));
				assertEquals("AAPL On the " + rollingHourCal.getTime(), expectedCal.getTime(), applDate);
			}
		}
		
		//fchi
		rollingHourCal.setTime(startDate);
		rollingHourCal.set(Calendar.HOUR_OF_DAY, 0);
		rollingHourCal.set(Calendar.MINUTE, 0);
		rollingHourCal.set(Calendar.SECOND, 0);
		rollingHourCal.set(Calendar.MILLISECOND, 0);//Monday the 19th at 00:00
		
		expectedCal.setTime(startDate);
		expectedCal.add(Calendar.DAY_OF_YEAR, -3);//Friday the 16th at 00:00
		expectedCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedCal.set(Calendar.MINUTE, 0);
		expectedCal.set(Calendar.SECOND, 0);
		expectedCal.set(Calendar.MILLISECOND, 0);
		
		for (int day = 0; day < 7; day++) {
			for (int hour = 0; hour < 24; hour++ ) {
				if (day < 5 && hour == 17 - fchi.getMarket().getUTCTimeLag()) {
					expectedCal.add(Calendar.DAY_OF_YEAR, 1);
					expectedCal.setTime(QuotationsFactories.getFactory().getValidQuotingDateAfterOrAt(expectedCal.getTime()));
				} else {
					expectedCal.setTime(QuotationsFactories.getFactory().getValidQuotingDateBeforeOrAt(expectedCal.getTime()));
				}
				rollingHourCal.add(Calendar.HOUR_OF_DAY, 1);
				Date fchiDate = DateFactory.endDateFix(rollingHourCal.getTime(), fchi.getMarket().getUTCTimeLag(), fchi.getTradingMode());
				System.out.println(String.format("FCHI On the " + rollingHourCal.getTime() + ": expected %s, real %s ", expectedCal.getTime(), fchiDate));
				assertEquals("FCHI On the " + rollingHourCal.getTime(), expectedCal.getTime(), fchiDate);
			}
		}
		
		//btc no lag
		System.out.println("btc no lag");
		rollingHourCal.setTime(startDate);
		rollingHourCal.set(Calendar.HOUR_OF_DAY, 0);
		rollingHourCal.set(Calendar.MINUTE, 0);
		rollingHourCal.set(Calendar.SECOND, 0);
		rollingHourCal.set(Calendar.MILLISECOND, 0);//Monday the 19th at 00:00
		
		expectedCal.setTime(startDate);
		expectedCal.add(Calendar.DAY_OF_YEAR, -2);//Saturday the 18th at 00:00
		expectedCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedCal.set(Calendar.MINUTE, 0);
		expectedCal.set(Calendar.SECOND, 0);
		expectedCal.set(Calendar.MILLISECOND, 0);
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 24; j++ ) {
				int utcTimeLag = 0;
				if (j == 0 - utcTimeLag) {
					expectedCal.add(Calendar.DAY_OF_YEAR, 1);
				}
				Date btcDate = DateFactory.endDateFix(rollingHourCal.getTime(), utcTimeLag, btc.getTradingMode());
				System.out.println(String.format("BTC On the " + rollingHourCal.getTime() + ": expected %s, real %s ", expectedCal.getTime(), btcDate));
				assertEquals("BTC On the " + rollingHourCal.getTime(), expectedCal.getTime(), btcDate);
				rollingHourCal.add(Calendar.HOUR_OF_DAY, 1);
			}
		}
		
		//btc
		System.out.println("btc");
		rollingHourCal.setTime(startDate);
		rollingHourCal.set(Calendar.HOUR_OF_DAY, 0);
		rollingHourCal.set(Calendar.MINUTE, 0);
		rollingHourCal.set(Calendar.SECOND, 0);
		rollingHourCal.set(Calendar.MILLISECOND, 0);//Monday the 19th at 00:00
		
		expectedCal.setTime(startDate);
		expectedCal.add(Calendar.DAY_OF_YEAR, -2);//Saturday the 18th at 00:00
		expectedCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedCal.set(Calendar.MINUTE, 0);
		expectedCal.set(Calendar.SECOND, 0);
		expectedCal.set(Calendar.MILLISECOND, 0);
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 24; j++ ) {
				int utcTimeLag = btc.getMarket().getUTCTimeLag();
				if (j == 0 - utcTimeLag) {
					expectedCal.add(Calendar.DAY_OF_YEAR, 1);
				}
				Date btcDate = DateFactory.endDateFix(rollingHourCal.getTime(), utcTimeLag, btc.getTradingMode());
				System.out.println(String.format("BTC On the " + rollingHourCal.getTime() + ": expected %s, real %s ", expectedCal.getTime(), btcDate));
				assertEquals("BTC On the " + rollingHourCal.getTime(), expectedCal.getTime(), btcDate);
				rollingHourCal.add(Calendar.HOUR_OF_DAY, 1);
			}
		}

	}
	
	@Test
	public void USTimeZonelagTest() {
		System.out.println("Us lag: " + DateFactory.UStoGBUTCTimeLag());
	}
	
	@Test
	public void tzTest() throws ParseException {
		Stock btc = DataSource.getInstance().loadStockBySymbol("BTC-USD");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:hhmmss");
		
		//After close
		Date date = dateFormat.parse("2022/09/27:061000"); 
		Date expceted = dateFormat.parse("2022/09/26:000000"); 
		Date endDateFix = DateFactory.endDateFix(date, btc.getMarket().getUTCTimeLag(), btc.getTradingMode());
		assertEquals(expceted, endDateFix);
		
		//Before close
		date = dateFormat.parse("2022/09/27:000100"); 
		expceted = dateFormat.parse("2022/09/25:000000"); 
		endDateFix = DateFactory.endDateFix(date, btc.getMarket().getUTCTimeLag(), btc.getTradingMode());
		assertEquals(expceted, endDateFix);
		
		date = dateFormat.parse("2022/09/26:115900"); 
		expceted = dateFormat.parse("2022/09/25:000000"); 
		endDateFix = DateFactory.endDateFix(date, btc.getMarket().getUTCTimeLag(), btc.getTradingMode());
		assertEquals(expceted, endDateFix);
		
		//At close
		date = dateFormat.parse("2022/09/27:000000"); 
		expceted = dateFormat.parse("2022/09/25:000000"); 
		endDateFix = DateFactory.endDateFix(date, btc.getMarket().getUTCTimeLag(), btc.getTradingMode());
		assertEquals(expceted, endDateFix);
		
	}

}
