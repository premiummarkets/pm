package com.finance.pms.events.operations.nativeops;

import org.junit.Test;

import com.finance.pms.events.NativesXmlManager;
import com.finance.pms.events.operations.Operation;

public class NativeOperationsTest {

	private String xmlfile = "nativeOps";

	@Test
	public void test() throws Exception {
		
		
		NativesXmlManager nativesXmlManager = new NativesXmlManager(xmlfile);
		
		NativeOperations nativeOperations = nativesXmlManager.initNativeOperations();
		nativesXmlManager.saveNativeOperations(nativeOperations);
		
		NativeOperations nativeOperationsReloded = nativesXmlManager.loadNativeOperations();
		
		for (Operation operation : nativeOperationsReloded.getOperations().values()) {
			System.out.println(operation.getDescription());
		}
	}

}
