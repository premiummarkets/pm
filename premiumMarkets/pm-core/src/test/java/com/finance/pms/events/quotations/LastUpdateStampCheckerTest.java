package com.finance.pms.events.quotations;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
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
