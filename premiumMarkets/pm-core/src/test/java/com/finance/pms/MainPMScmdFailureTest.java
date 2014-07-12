package com.finance.pms;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.MainPMScmd.MyPreference;

@RunWith( PowerMockRunner.class )
@PrepareForTest( MainPMScmd.class )
public class MainPMScmdFailureTest {

	
	@Test
	public void testNobAcking() {
		
		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andThrow(new RuntimeException()).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		String toto = myPreference.get("toto", "tata");
        assertEquals("tata", toto);
        String toto2 = myPreference.get("toto", "tutu");
		assertEquals("tutu", toto2);
        verifyAll();
		
	}
	
	
	@Test
	public void testGetMyPrefsB() {	
		
		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andThrow(new RuntimeException()).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		Boolean toto = myPreference.getBoolean("totob", false);
		assertEquals(false, toto);
		Boolean toto2 = myPreference.getBoolean("totob", true);
		assertEquals(true, toto2);
		verifyAll();
	}
	
	@Test
	public void testGetMyPrefs2() {	

		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andThrow(new RuntimeException()).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		String toto = myPreference.get("toto", "tata");
		assertEquals("tata", toto);
		
		myPreference.put("toto", "tete");
		String toto2 = myPreference.get("toto", "tutu");
		assertEquals("tete", toto2);
		verifyAll();
	}
	
	@Test
	public void testGetMyPrefs2B() {	

		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andThrow(new RuntimeException()).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		Boolean toto = myPreference.getBoolean("totob", false);
		assertEquals(false, toto);
		
		myPreference.putBoolean("totob", true);
		Boolean toto2 = myPreference.getBoolean("totob", false);
		assertEquals(true, toto2);
		verifyAll();
	
	}

}
