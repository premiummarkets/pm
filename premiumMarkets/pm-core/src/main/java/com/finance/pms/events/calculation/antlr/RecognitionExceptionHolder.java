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

import java.util.List;

import org.antlr.runtime.RecognitionException;

import com.finance.pms.admin.install.logging.MyLogger;

public class RecognitionExceptionHolder {
	
	private static MyLogger LOGGER = MyLogger.getLogger(RecognitionExceptionHolder.class);
	
	private int index;
	
	private RecognitionException exception;
	
	private EditorOpDescr needsClosing;
	private List<String> ruleStack;

	private String expectedToken;

	private String eToString;
	private String msg;
	

	public RecognitionExceptionHolder(RecognitionException exception, EditorOpDescr needsClosing, List<String> ruleStack, String expectedToken, String eToString, String msg) {
		super();
		this.index = ANTLROperationsParserHelper.exceptionIndex ++;
		
		this.exception = exception;
		
		this.ruleStack = ruleStack;
		this.needsClosing = needsClosing;
		this.expectedToken = expectedToken;
		
		this.eToString = eToString;
		this.msg = msg;
		
	}
	
	
	public String toCsv() {
		try {
			return index+"\\,"+
					exception.getClass().getSimpleName()+"\\,"+(char)exception.c+"\\,"+(char)exception.getUnexpectedType()+"\\,"+exception.line+"\\,"+exception.charPositionInLine+"\\,"+
					eToString+"\\,"+msg+"\\,"+ 
					ruleStack+"\\,"+needsClosing+"\\,"+expectedToken;
		} catch (Exception e) {
			LOGGER.error("Can't extract info from "+exception.toString()+ " : "+e,e);
			return "Error extracting exception information :"+e.toString();
		}
	}

	public int getIndex() {
		return index;
	}


	public RecognitionException getException() {
		return exception;
	}

	public List<String> getRuleStack() {
		return ruleStack;
	}


	public String geteToString() {
		return eToString;
	}


	public String getMsg() {
		return msg;
	}


	public EditorOpDescr getNeedsClosing() {
		return needsClosing;
	}


	public String getExpectedToken() {
		return expectedToken;
	}


	@Override
	public String toString() {
		return "RecognitionExceptionHolder [index=" + index + ", exception=" + exception + 
				", needsClosing=" + needsClosing + ", ruleStack=" + ruleStack + 
				", expectedToken=" + expectedToken + ", eToString=" + eToString + ", msg=" + msg + "]";
	}

}
