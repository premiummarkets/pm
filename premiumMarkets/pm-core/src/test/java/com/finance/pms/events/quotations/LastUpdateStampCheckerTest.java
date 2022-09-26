/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.quotations;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.events.calculation.DateFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DateFactory.class, QuotationsFactories.class})
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*" })
public class LastUpdateStampCheckerTest {

	private String ASSET;
	LastUpdateStampChecker checker;

	@Before
	public void setUp() {
		checker = new LastUpdateStampChecker();
		ASSET = "BLABLAStock" + UUID.randomUUID().toString();
	}
	
	@After
	public void tearDown() {
		checker.resetAsset(ASSET);
	}

	@Test
	public void testIsUpdateGrantedContinuous() {

		checker.resetAsset(ASSET);

		PowerMock.mockStaticPartial(DateFactory.class, "getNowEndDateTime");
		
		Calendar todayCal = Calendar.getInstance(Locale.US); // on the Wed 2020/01/01 at 12.00 US (before market close)
		todayCal.set(Calendar.HOUR_OF_DAY, 12);
		todayCal.set(2020, 00, 01);
		expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
		
		PowerMock.mockStatic(QuotationsFactories.class);
		expect(QuotationsFactories.getFactory()).andReturn(new ClosedDayQuotationsFactory()).anyTimes();
	
		PowerMock.replayAll();
		
		Calendar expectedLastMarketCloseCal = Calendar.getInstance(Locale.US); // on the Tue 2019/12/31 at 00.00 US
		expectedLastMarketCloseCal.setTime(todayCal.getTime());
		expectedLastMarketCloseCal.add(Calendar.DAY_OF_YEAR, -1);
		expectedLastMarketCloseCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedLastMarketCloseCal.set(Calendar.MINUTE, 0);
		expectedLastMarketCloseCal.set(Calendar.SECOND, 0);
		expectedLastMarketCloseCal.set(Calendar.MILLISECOND, 0);

		Calendar lastStockQuoteCal = Calendar.getInstance(Locale.US); // on the Mon 2019/12/30 at 00.00 US
		lastStockQuoteCal.setTime(todayCal.getTime());
		lastStockQuoteCal.add(Calendar.DAY_OF_YEAR, -2);
		lastStockQuoteCal.set(Calendar.HOUR_OF_DAY, 0);
		lastStockQuoteCal.set(Calendar.MINUTE, 0);
		lastStockQuoteCal.set(Calendar.SECOND, 0);
		lastStockQuoteCal.set(Calendar.MILLISECOND, 0);
		
		{
			// First attempt works (Last Market close should be Tue 2019/12/31) as the entry is not created yet in the json
			Boolean firstAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
			assertTrue(firstAttempt);
			assertEquals(Integer.valueOf(0), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());

			// Quote is D-2, //last Market is D-0 => if no Quote is Not updated, we expect
			// MAXATTEMPTS and fatalThreshold inc to 1 after this
			// MAXATTEMPTS are granted
			for (int i = 1; i <= LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(0, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());

			// MAXATTEMPTS + 1 without quotation won't be granted
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
			assertFalse(secondAttempt);
			assertEquals((int) LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			assertEquals(1, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}
		
		PowerMock.reset(DateFactory.class);

		//Today After market close
		// Quote is D-2, //last Market is D-0 => if no Quote is Not updated, we expect
		// again MAXATTEMPTS and fatalThreshold inc to 2 after this
		// One hour later (New Last Market close data)
		todayCal.set(Calendar.HOUR_OF_DAY, 23);// 18 + 5 US closure time
		expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
		PowerMock.replayAll();
		
		expectedLastMarketCloseCal.add(Calendar.DAY_OF_YEAR, +1);
		{
			// MAXATTEMPTS are granted
			for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(1, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());

			// MAXATTEMPTS + 1 without quotation won't be granted
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
			assertFalse(secondAttempt);
			assertEquals((int) LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}
		
		PowerMock.reset(DateFactory.class);
		
		//Next Day After close
		// Quote is D-3, //last Market is D-1 => if no Quote is Not updated, we expect
		// again MAXATTEMPTS and fatalThreshold inc to 2 after this
		// One hour later (New Last Market close data)
		addOneOpenDay(todayCal, expectedLastMarketCloseCal);
		expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
		PowerMock.replayAll();
		{
			// MAXATTEMPTS -1 are granted
			for (int i = 0; i < LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			
			//Then if we inc the quote at MAXATTEMPTS
			lastStockQuoteCal.add(Calendar.DAY_OF_YEAR, +3);
			Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
			assertFalse(inRowAttempt);
			assertEquals((int) 0, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());

			// MAXATTEMPTS + 1 without quotation won't be granted so no Attempt inc is made
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
			assertFalse(secondAttempt);
			assertEquals((int) 0, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			//Does not move in this test has it should be reset from quotation when new are available
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}
		
		
		//To death
		//Quote is D-1, //last Market is D-1 => if no Quote is Not updated, we expect
		//The quotation asset to its death
		for (int k = 3; k <= LastUpdateStampChecker.MAXATTEMPTSFATAL; k++) {
			System.out.println("k:" + k);
			PowerMock.reset(DateFactory.class);
			addOneOpenDay(todayCal, expectedLastMarketCloseCal);
			expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
			PowerMock.replayAll();
			{
				// MAXATTEMPTS are granted
				for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
					System.out.println("i:" + i);
					Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
					assertTrue(inRowAttempt);
					assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
					assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				}
				//Next one is not
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
				assertFalse(inRowAttempt);
				assertEquals((int)LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertEquals(k, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			}
		}
		
		//After death (always false)
		for (int k = 1; k < 10; k++) {
			{
				PowerMock.reset(DateFactory.class);
				addOneOpenDay(todayCal, expectedLastMarketCloseCal);
				expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
				PowerMock.replayAll();
				// MAXATTEMPTS are granted
				for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
					Boolean inRowAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.CONTINUOUS);
					assertFalse(inRowAttempt);
					assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
					assertEquals(LastUpdateStampChecker.MAXRETRY +1, (int)checker.getLastUpdateStampRecord(ASSET).getNbAttempts()); //Will never reset from there
					System.out.println("getNbAttempts: " + checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				}
				System.out.println("getFatalThreshold: " + checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
				assertEquals((int)LastUpdateStampChecker.MAXATTEMPTSFATAL, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			}
		}
		
		
		PowerMock.verifyAll();

	}
	
	@Test
	public void testIsUpdateGrantedNonStop() {

		checker.resetAsset(ASSET);

		PowerMock.mockStaticPartial(DateFactory.class, "getNowEndDateTime");
		
		Calendar todayCal = Calendar.getInstance(Locale.UK); // on the Wed 2020/01/01 at 21 UK (before market close: 5 AM the next day)
		todayCal.set(Calendar.HOUR_OF_DAY, 21);
		todayCal.set(2020, 00, 01);
		expect(DateFactory.getNowEndTime()).andReturn(todayCal.getTime()).anyTimes();
		
		PowerMock.mockStatic(QuotationsFactories.class);
		expect(QuotationsFactories.getFactory()).andReturn(new ClosedDayQuotationsFactory()).anyTimes();
	
		PowerMock.replayAll();
		
		Calendar expectedLastMarketCloseCal = Calendar.getInstance(Locale.UK); // on the Tue 2019/12/31 at 00.00 UK
		expectedLastMarketCloseCal.setTime(todayCal.getTime());
		expectedLastMarketCloseCal.add(Calendar.DAY_OF_YEAR, -1);
		expectedLastMarketCloseCal.set(Calendar.HOUR_OF_DAY, 0);
		expectedLastMarketCloseCal.set(Calendar.MINUTE, 0);
		expectedLastMarketCloseCal.set(Calendar.SECOND, 0);
		expectedLastMarketCloseCal.set(Calendar.MILLISECOND, 0);

		Calendar lastStockQuoteCal = Calendar.getInstance(Locale.UK); // on the Mon 2019/12/31 at 00.00 UK
		lastStockQuoteCal.setTime(todayCal.getTime());
		lastStockQuoteCal.add(Calendar.DAY_OF_YEAR, -1);
		lastStockQuoteCal.set(Calendar.HOUR_OF_DAY, 0);
		lastStockQuoteCal.set(Calendar.MINUTE, 0);
		lastStockQuoteCal.set(Calendar.SECOND, 0);
		lastStockQuoteCal.set(Calendar.MILLISECOND, 0);
		
		{
			// First attempt works (Last Market close should be Tue 2019/12/31) as the entry is not created yet in the json
			Boolean firstAttempt = checker.isUpdateGranted(ASSET, lastStockQuoteCal.getTime(), DateFactory.UStoGBUTCTimeLag(), TradingMode.NON_STOP);
			assertEquals(Integer.valueOf(0), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(expectedLastMarketCloseCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			assertFalse(firstAttempt);
		}
		
		
	}

	private void addOneOpenDay(Calendar todayCal, Calendar lastMarketCal) {
		do {
			todayCal.add(Calendar.DAY_OF_YEAR, +1);
			lastMarketCal.add(Calendar.DAY_OF_YEAR, +1);
		} while(todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

}
