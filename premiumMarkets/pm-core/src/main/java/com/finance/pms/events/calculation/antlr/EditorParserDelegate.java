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
package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class EditorParserDelegate {

	protected Set<EditorOpDescr> allNativeOps;
	TokenStream input;

	public EditorParserDelegate(TokenStream input, Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		this.allNativeOps =  aggAllOperations(runtimeNativeOps, runtimeUserOps);
		this.input = input;
	}

	public EditorParserDelegate(CommonTokenStream input, Set<EditorOpDescr> allOps) {
		this.allNativeOps =  allOps;
		this.input = input;
	}

	protected EditorOpDescr grabOpForToken(String parsedOp) {
		for(EditorOpDescr op : allNativeOps) {
			if (op.getName().equals(parsedOp)) {
				try {
					return (EditorOpDescr) op.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	protected EditorOpDescr grabOpForToken(Token parsedOp) {
		return grabOpForToken(parsedOp.getText());
	}

	protected Set<EditorOpDescr> aggAllOperations(Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		Set<EditorOpDescr> allOps = new TreeSet<EditorOpDescr>();
		allOps.addAll(runtimeNativeOps);
		allOps.addAll(runtimeUserOps);
		return allOps;
	}

	public void outputSelectorHint(Token opName, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException, NoViableAltException {
		
	
		EditorOpDescr currentOp = grabOpForToken(opName);
		String outputSelectorStr = (outputSelector != null)? outputSelector.getText():null;
		
		if (currentOp == null) {
			//Partial op matching
			throw new InvalidOperationException(input, opName, null);
		}
		
		List<String> outputSelectors = currentOp.getOutputSelectors();
		if (outputSelectors != null && !outputSelectors.isEmpty()) {//output selector needed
			List<String> editSelector = new ArrayList<String>();
			for (String selector : outputSelectors) {
				editSelector.add(":"+selector);
			}
			if (!editSelector.contains(outputSelectorStr)) {
				throw new MissingOutputSelectorException(input, currentOp, outputSelector, editSelector.toArray(new String[0]));
			}
		} else {//no output selector needed
			if (outputSelectorStr != null) {
				throw new MissingOutputSelectorException(input, currentOp, outputSelector);
			}
		}
		
	}

}
