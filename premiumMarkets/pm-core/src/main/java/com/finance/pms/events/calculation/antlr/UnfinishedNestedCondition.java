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

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class UnfinishedNestedCondition extends RecognitionException {

	private static final long serialVersionUID = -2641005753375489777L;
	
	private String type;
	private String parsed;
	private Boolean wrongToken;
	private Boolean unfinishToken;
	
	public UnfinishedNestedCondition(IntStream input, String type, String parsed, Boolean wrongToken, Boolean unfinishToken) {
		super(input);
		this.type = type;
		this.parsed = parsed;
		this.wrongToken = wrongToken;
		this.unfinishToken = unfinishToken;
		
	}

	public String getType() {
		return type;
	}
	public String getParsed() {
		return parsed;
	}

	public Boolean getWrongToken() {
		return wrongToken;
	}

	public Boolean getUnfinishToken() {
		return unfinishToken;
	}

}
