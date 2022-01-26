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
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.Calendar;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.events.calculation.DateFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateFactory.class)
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*" })
//FIXME rewrite as LastUpdateStampChecker has been rewritten.
public class LastUpdateStampCheckerTest {

	private static final String ASSET = "BLABLAStock";
	LastUpdateStampChecker checker;

	@Before
	public void setUp() {
		checker = new LastUpdateStampChecker();
	}

	@Test
	public void testIsUpdateGranted() {

		checker.resetAsset(ASSET);

		mockStatic(DateFactory.class); // on the 2020/01/01 at 5PM US
		Calendar todayCal = Calendar.getInstance(Locale.US);
		todayCal.set(Calendar.HOUR_OF_DAY, 17);
		todayCal.set(2020, 00, 01);
		expect(DateFactory.getNowEndDateCalendar()).andReturn(todayCal).anyTimes();
		Calendar zeroCal = Calendar.getInstance();
		zeroCal.set(1970, 0, 1, 0, 0, 0);
		zeroCal.set(Calendar.MILLISECOND, 0);
		expect(DateFactory.dateAtZero()).andReturn(zeroCal.getTime()).anyTimes();
		replayAll();

		Calendar blablaStockQuoteCal = Calendar.getInstance(Locale.US); // on the 2019/12/30 at 00.00 US
		blablaStockQuoteCal.setTime(todayCal.getTime());
		blablaStockQuoteCal.add(Calendar.DAY_OF_YEAR, -2);
		blablaStockQuoteCal.set(Calendar.HOUR_OF_DAY, 0);
		blablaStockQuoteCal.set(Calendar.MINUTE, 0);
		blablaStockQuoteCal.set(Calendar.SECOND, 0);
		blablaStockQuoteCal.set(Calendar.MILLISECOND, 0);
		
		Calendar lastMarketCal = Calendar.getInstance(Locale.US); // on the 2019/12/30 at 00.00 US
		lastMarketCal.setTime(todayCal.getTime());
		lastMarketCal.add(Calendar.DAY_OF_YEAR, -1);
		lastMarketCal.set(Calendar.HOUR_OF_DAY, 0);
		lastMarketCal.set(Calendar.MINUTE, 0);
		lastMarketCal.set(Calendar.SECOND, 0);
		lastMarketCal.set(Calendar.MILLISECOND, 0);
		{
			// First attempt works (Last Market close should be 2019/12/31) as the entry is
			// not created yet in the json
			Boolean firstAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
			assertTrue(firstAttempt);
			assertEquals(Integer.valueOf(0), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());

			// Quote is D-2, //last Market is D-0 => if no Quote is Not updated, we expect
			// MAXATTEMPTS and fatalThreshold inc to 1 after this
			// MAXATTEMPTS are granted
			for (int i = 1; i <= LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(0, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());

			// MAXATTEMPTS + 1 without quotation won't be granted
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
			assertFalse(secondAttempt);
			assertEquals((int) LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			assertEquals(1, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}

		//Next hour
		// Quote is D-3, //last Market is D-1 => if no Quote is Not updated, we expect
		// again MAXATTEMPTS and fatalThreshold inc to 2 after this
		// One hour later (New Last Market close data)
		todayCal.add(Calendar.HOUR_OF_DAY, +1);
		lastMarketCal.add(Calendar.DAY_OF_YEAR, +1);
		{
			// MAXATTEMPTS are granted
			for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(1, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());

			// MAXATTEMPTS + 1 without quotation won't be granted
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
			assertFalse(secondAttempt);
			assertEquals((int) LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}
		
		//Next Day
		// Quote is D-4, //last Market is D-1 => if no Quote is Not updated, we expect
		// again MAXATTEMPTS and fatalThreshold inc to 2 after this
		// One hour later (New Last Market close data)
		addOneOpenDay(todayCal, lastMarketCal);
		{
			// MAXATTEMPTS -1 are granted
			for (int i = 0; i < LastUpdateStampChecker.MAXRETRY; i++) {
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
				assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertTrue(inRowAttempt);
			}
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			
		//Then if we inc the quote at MAXATTEMPTS
			blablaStockQuoteCal.add(Calendar.DAY_OF_YEAR, +3);
			Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
			assertFalse(inRowAttempt);
			assertEquals((int) 0, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());

			// MAXATTEMPTS + 1 without quotation won't be granted so no Attempt inc is made
			Boolean secondAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
			assertFalse(secondAttempt);
			assertEquals((int) 0, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
			assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
			//Does not move in this test has it should be reset from quotation when new are available
			assertEquals(2, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
		}
		
		
		//To death
		//Quote is D-1, //last Market is D-1 => if no Quote is Not updated, we expect
		//The quotation asset to its death
		for (int k = 3; k <= LastUpdateStampChecker.MAXATTEMPTSFATAL; k++) {
			System.out.println("k:" + k);
			addOneOpenDay(todayCal, lastMarketCal);
			{
				// MAXATTEMPTS are granted
				for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
					System.out.println("i:" + i);
					Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
					assertTrue(inRowAttempt);
					assertEquals(Integer.valueOf(i), checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
					assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				}
				//Next one is not
				Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
				assertFalse(inRowAttempt);
				assertEquals((int)LastUpdateStampChecker.MAXRETRY + 1, (int) checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
				assertEquals(k, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			}
		}
		
		//After death (always false)
		for (int k = 1; k < 10; k++) {
			{
				addOneOpenDay(todayCal, lastMarketCal);
				// MAXATTEMPTS are granted
				for (int i = 0; i <= LastUpdateStampChecker.MAXRETRY; i++) {
					Boolean inRowAttempt = checker.isUpdateGranted(ASSET, blablaStockQuoteCal.getTime());
					assertFalse(inRowAttempt);
					assertEquals(lastMarketCal.getTime(), checker.getLastUpdateStampRecord(ASSET).getLastAttemptDate());
					assertEquals(LastUpdateStampChecker.MAXRETRY +1, (int)checker.getLastUpdateStampRecord(ASSET).getNbAttempts()); //Will never reset from there
					System.out.println("getNbAttempts: " + checker.getLastUpdateStampRecord(ASSET).getNbAttempts());
				}
				System.out.println("getFatalThreshold: " + checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
				assertEquals((int)LastUpdateStampChecker.MAXATTEMPTSFATAL, (int) checker.getLastUpdateStampRecord(ASSET).getFatalThreshold());
			}
		}
		
		
		verifyAll();

	}

	private void addOneOpenDay(Calendar todayCal, Calendar lastMarketCal) {
		do {
			todayCal.add(Calendar.DAY_OF_YEAR, +1);
			lastMarketCal.add(Calendar.DAY_OF_YEAR, +1);
		} while(todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

}
