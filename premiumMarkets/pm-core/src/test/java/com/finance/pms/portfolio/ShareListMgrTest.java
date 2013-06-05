package com.finance.pms.portfolio;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;

public class ShareListMgrTest {
	
	ShareListMgr shareListMgr;
	
	@Before
	public void setUp() {
		
		SpringContext springContext = new SpringContext(System.getProperty("installdir") + File.separator +"dbcopy.properties");
		//springContext.setDataSource(System.getProperty("installdir") + File.separator +"dbcopy.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
		shareListMgr = springContext.getBean(ShareListMgr.class);
	}

	@Test
	public void testUpdateShareList() throws Exception {
		shareListMgr.updateShareLists();
	}

}
