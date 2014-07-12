package com.finance.pms;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.MainPMScmd.MyPreference;

@RunWith( PowerMockRunner.class )
@PrepareForTest( MainPMScmd.class )
public class MainPMScmdPartialFailureTest {

	@Mock
	Preferences mock;
	
	@Before 
	public void setUp() throws BackingStoreException {
		expect(mock.node(anyObject(String.class))).andReturn(mock);
		expect(mock.keys()).andReturn(new String[]{"toto","toto2"});
		
		expect(mock.get(eq("toto"), anyObject(String.class))).andReturn("toto");
		expect(mock.get(eq("toto2"), anyObject(String.class))).andReturn("toto2");
		
		mock.put(eq("toto"), anyObject(String.class));
		expectLastCall();
		
		mock.flush();
		expectLastCall().andThrow(new BackingStoreException("")).anyTimes();
	}
	
	@Test
	public void testNobAcking() {
		
		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andReturn(mock).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		myPreference.put("toto", "tete");
		String toto2 = myPreference.get("toto", "tata");
		assertEquals("tete", toto2);
        verifyAll();
		
	}
	
	@Test
	public void testNobAcking2() {
		
		mockStatic( MainPMScmd.class );
        expect( MainPMScmd.prefRoot() ).andReturn(mock).anyTimes();
        expect( MainPMScmd.LOGGER );

        replayAll();
        MyPreference myPreference = new MyPreference();
		myPreference.put("toto", "tete");
		String toto2 = myPreference.get("toto", "tata");
		assertEquals("tete", toto2);
		
		myPreference.flushy();
		
		String toto4 = myPreference.get("toto", "tata");
		assertEquals("tete", toto4);
		
        verifyAll();
		
	}

}
