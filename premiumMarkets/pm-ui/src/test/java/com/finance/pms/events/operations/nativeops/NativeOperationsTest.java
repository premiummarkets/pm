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
