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

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;

import com.finance.pms.admin.install.logging.MyLogger;


public class MyLexerErrorReporter extends MyErrorReporter {
	
	public static MyLogger LOGGER = MyLogger.getLogger(MyLexerErrorReporter.class);
	
	private Lexer lexer;
	
	public MyLexerErrorReporter(Lexer lexer, LinkedList<RecognitionExceptionHolder> exceptions, String parsedLine) {
		super(exceptions, parsedLine);
		this.lexer = lexer;
	}

	public void report(RecognitionException e) {

		List<String> stack = BaseRecognizer.getRuleInvocationStack(e, lexer.getClass().getName());
		String expectedTokenName = null;
		String msg = "LEXER Exception : "+e.getClass()+". ";
		
//		try {
			
			if ( e instanceof MismatchedTokenException ) {
				
				MismatchedTokenException mte = (MismatchedTokenException) e;
				//Log
				msg = msg + "lexer mismatched input "+ ((mte.token != null)?lexer.getTokenErrorDisplay(mte.token):"No token");
				expectedTokenName=""+(char)+mte.expecting;

			} else if ( e instanceof NoViableAltException ) {

				
			} else if ( e instanceof EarlyExitException ) {
				//Log
				msg = msg + " (lexer unmatched exception) "+ e.getMessage();
				
			}
			
//			throw new GetMeOutOfHereException();		
//		} finally  {
			
			exceptions.add(new RecognitionExceptionHolder(e, null, stack, (expectedTokenName != null)?expectedTokenName.replaceAll("'", ""):null, logMsg(e), msg));
			log(e, stack, msg);
//		}
		
	
	}

	protected void log(RecognitionException e, List<String> stack, String msg) {
		LOGGER.info("Lexer error : Parsed : "+parsedLine + ", invalid char : "+(char)e.c+" at "+e.charPositionInLine+", token "+e.token+", stack : "+stack+", msg : "+msg);
	}
	
	protected String logMsg(RecognitionException e) {
		String hdr = lexer.getErrorHeader(e);
		String err = lexer.getErrorMessage(e, lexer.getTokenNames());
		String errorMsg = hdr+" "+err;
		return errorMsg;
	}
}
