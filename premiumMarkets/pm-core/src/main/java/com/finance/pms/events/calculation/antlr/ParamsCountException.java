/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;



public class ParamsCountException extends RecognitionException {

	private static final long serialVersionUID = 6838195461774245707L;
	
	public enum Qualifier {EMPTY,SYNTAX,TYPE,TOOMANYARGS, NOTENOUGHARGS};
	
	private String parsedTxt;
	private String errorMsg;
	private Boolean needsPop;
	private Qualifier qualifier;
	private EditorOpDescr currentOp;

	public ParamsCountException(IntStream input, EditorOpDescr currentOp, String errorMsg, Boolean needsPop,  Qualifier qualifier, String parsedTxt, int...position) {
		super(input);
		
		this.currentOp = currentOp;
		
		this.errorMsg = errorMsg;
		this.parsedTxt = parsedTxt;
		this.c = (parsedTxt !=null && !parsedTxt.isEmpty())? (int) parsedTxt.charAt(0):Token.EOF;
		if (position != null && position.length == 2) {
			this.line = position[0];
			this.charPositionInLine = position[1];
		}
		
		this.needsPop = needsPop;
		this.qualifier = qualifier;
	}

	public String getParsedTxt() {
		return parsedTxt;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Boolean getNeedsPop() {
		return needsPop;
	}

	@Override
	public String toString() {
		return "ParamsCountException [parsedTxt=" + parsedTxt + ", errorMsg=" + errorMsg + ", needsPop=" + needsPop + "]";
	}

	@Override
	public String getMessage() {
		return toString();
	}

	public Qualifier getQualifier() {
		return qualifier;
	}

	public EditorOpDescr getCurrentOp() {
		return currentOp;
	}
	
}
