package com.finance.pms;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

public class MainPMScmdTest {
	
	@After
	public void removeTestPrefs() {
		MainPMScmd.getMyPrefs().remove("toto");
		MainPMScmd.getMyPrefs().remove("totob");
	}

	@Test
	public void testGetMyPrefs() {	
		String toto = MainPMScmd.getMyPrefs().get("toto", "tata");
		assertEquals("tata", toto);
		String toto2 = MainPMScmd.getMyPrefs().get("toto", "tutu");
		assertEquals("tutu", toto2);
	}
	
	@Test
	public void testGetMyPrefsB() {	
		Boolean toto = Boolean.valueOf(MainPMScmd.getMyPrefs().get("totob", "false"));
		assertEquals(false, toto);
		Boolean toto2 = Boolean.valueOf(MainPMScmd.getMyPrefs().get("totob", "true"));
		assertEquals(true, toto2);
	}
	
	@Test
	public void testGetMyPrefs2() {	

		String toto = MainPMScmd.getMyPrefs().get("toto", "tata");
		assertEquals("tata", toto);
		
		MainPMScmd.getMyPrefs().put("toto", "tete");
		String toto2 = MainPMScmd.getMyPrefs().get("toto", "tutu");
		assertEquals("tete", toto2);
	
	}
	
	@Test
	public void testGetMyPrefs2B() {	

		Boolean toto = Boolean.valueOf(MainPMScmd.getMyPrefs().get("totob", "false"));
		assertEquals(false, toto);
		
		MainPMScmd.getMyPrefs().putBoolean("totob", true);
		Boolean toto2 = Boolean.valueOf(MainPMScmd.getMyPrefs().get("totob", "false"));
		assertEquals(true, toto2);
	
	}

}
