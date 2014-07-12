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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.events.calculation.DateFactory;

@RunWith( PowerMockRunner.class )
@PrepareForTest( DateFactory.class )
public class LastUpdateStampCheckerTest {
	
	LastUpdateStampChecker checker;
	
	@Before
	public void setUp() {
		checker = new LastUpdateStampChecker();
	}

	@Test
	public void testIsUpdateGranted() {
		
		mockStatic( DateFactory.class );
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 5);
		expect( DateFactory.now() ).andReturn( calendar ).anyTimes();
		replayAll();
		
		Boolean firstAttempt = checker.isUpdateGranted();
		assertTrue(firstAttempt);
		
		for (int i = 1; i <= LastUpdateStampChecker.MAXATTEMPTSINROW; i++) {
			Boolean inRowAttempt = checker.isUpdateGranted();
			assertEquals(new Integer(i), checker.nbAttempts);
			assertTrue(inRowAttempt);
		}
		
		Boolean secondAttempt = checker.isUpdateGranted();
		assertFalse(secondAttempt);
		assertTrue(checker.nbAttempts == LastUpdateStampChecker.MAXATTEMPTSINROW);
		
		//One hours later loop
		for (int i = LastUpdateStampChecker.MAXATTEMPTSINROW+1; i <= LastUpdateStampChecker.MAXATTEMPTS; i++) {
			calendar.add(Calendar.HOUR_OF_DAY, +1);
			Boolean twoHoursAfterFirstAttempt = checker.isUpdateGranted();
			assertTrue(twoHoursAfterFirstAttempt);
			assertEquals(new Integer(i), checker.nbAttempts);
			
			Boolean twoHoursAfterSndtAttempt = checker.isUpdateGranted();
			assertFalse(twoHoursAfterSndtAttempt);
			assertEquals(new Integer(i), checker.nbAttempts);
			
			System.out.println(checker.nbAttempts+" "+calendar.getTime());
			System.out.println("checker.lastMarketCloseUpdate "+checker.lastMarketCloseUpdate);
		}
		
		//Max attemps reached
		calendar.add(Calendar.HOUR_OF_DAY, +1);
		Boolean twoHoursAfterFirstAttempt = checker.isUpdateGranted();
		assertFalse(twoHoursAfterFirstAttempt);
		assertEquals(new Integer(LastUpdateStampChecker.MAXATTEMPTS), checker.nbAttempts);
		
		Boolean twoHoursAfterSndtAttempt = checker.isUpdateGranted();
		assertFalse(twoHoursAfterSndtAttempt);
		assertEquals(new Integer(LastUpdateStampChecker.MAXATTEMPTS), checker.nbAttempts);
		
		System.out.println(checker.nbAttempts+" "+calendar.getTime());
		System.out.println("checker.lastMarketCloseUpdate "+checker.lastMarketCloseUpdate);
		
		//One day later
		calendar.add(Calendar.DAY_OF_YEAR, +1);
		Boolean dayAfterAttempt = checker.isUpdateGranted();
		assertEquals(new Integer(0), checker.nbAttempts);
		assertTrue(dayAfterAttempt);
		 
		System.out.println(checker.nbAttempts+" "+calendar.getTime());
		System.out.println("checker.lastMarketCloseUpdate "+checker.lastMarketCloseUpdate);
		
		verifyAll();
		 
		
	}

}
