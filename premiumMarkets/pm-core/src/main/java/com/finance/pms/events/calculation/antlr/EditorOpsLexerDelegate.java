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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognizerSharedState;

public class EditorOpsLexerDelegate extends EditorLexerDelegate implements OpsLexerDelegate {
	
	public static final List<String> SYNTAX_TOKENS = new ArrayList<String>(Arrays.asList("(",")",",",":"));


	private Set<EditorOpDescr> runtimeNativeOps;
	private Set<EditorOpDescr> runtimeUserOps;

	
	public EditorOpsLexerDelegate(CharStream input, RecognizerSharedState state, Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		super(state, input);
		
		this.runtimeNativeOps = runtimeNativeOps;
		this.runtimeUserOps = runtimeUserOps;

	}
	
	public boolean runtimeOpRefOpAhead() {
		//return lookAheadFor(runtimeUserOps, "$"); //FIXME
		return true;
	}

	public boolean runtimeUserOpAhead() {
		return lookAheadFor(runtimeUserOps, "");
	}

	public boolean runtimeNativeOpAhead() {
		return lookAheadFor(runtimeNativeOps, "");
	}

	public PartialTokenStatus getPartialTokenStatus() {
		return partialTokenStatus;
	}

}
