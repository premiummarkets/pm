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

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;

import com.finance.pms.events.calculation.parametrizedindicators.antlr.ParameterizedIndicatorsLexer;
import com.finance.pms.events.calculation.parametrizedindicators.antlr.ParameterizedIndicatorsParser;

public class IndsParserErrorReporter extends MyErrorReporter {

	private ParameterizedIndicatorsParser parser;

	public IndsParserErrorReporter(ParameterizedIndicatorsParser parser, LinkedList<RecognitionExceptionHolder> exceptions, String parsedLine) {
		super(exceptions, parsedLine);
		this.parser = parser;
	}

	@Override
	public void report(RecognitionException e) {
		
		List<String> stack = Parser.getRuleInvocationStack(e, parser.getClass().getName());
		String expectedTokenName = null;
		String msg = "INDS PARSER Exception : "+e.getClass()+". ";
		
		try {
			
			//Handled
			if ( e instanceof UnwantedTokenException ) {

				UnwantedTokenException mte = (UnwantedTokenException) e;

				//Log
				if ( mte.expecting == Token.EOF ) {
					expectedTokenName = "EOF";
				}
				else {
					expectedTokenName = parser.getTokenNames()[mte.expecting];
				}
				msg = msg + "mismatched input "+parser.getTokenErrorDisplay(mte.token)+" expecting "+expectedTokenName;

			} 
			else if ( e instanceof MissingTokenException ) {
				MissingTokenException mte = (MissingTokenException) e;

				//Log
				if ( mte.expecting== Token.EOF ) {
					expectedTokenName = "EOF";
				}
				else {
					expectedTokenName = parser.getTokenNames()[mte.expecting];
				}

				msg = msg + "missing "+expectedTokenName+" at "+parser.getTokenErrorDisplay(e.token);	
			}
			else if ( e instanceof MismatchedTokenException ) {

					MismatchedTokenException mte = (MismatchedTokenException) e;

					//Log
					if ( mte.expecting == Token.EOF ) {
						expectedTokenName = "EOF";
					}
					else {
						expectedTokenName = parser.getTokenNames()[mte.expecting];
					}
					msg = msg + "mismatched input "+parser.getTokenErrorDisplay(mte.token)+" expecting "+expectedTokenName;

			} 
			else if ( e instanceof UnfinishedNestedCondition ) {

				UnfinishedNestedCondition unce = (UnfinishedNestedCondition) e;

				//Log
				expectedTokenName = unce.getType();
				
				msg = msg + "mismatched input "+parser.getTokenErrorDisplay(unce.token)+"; having  : '"+unce.getParsed()+"' expecting : '"+expectedTokenName+"'";

			}
			else {
					//Log
					msg = msg + " (unmatched exception) "+ e.getMessage();
			}

			
			throw new ExitParsingException();
			
		} finally {
			exceptions.add(new RecognitionExceptionHolder(e, null, stack, (expectedTokenName != null)?expectedTokenName.replaceAll("'", ""):null, logMsg(e), msg));
			log(e, stack, msg);
		}

	}
	
	protected void log(RecognitionException e, List<String> stack, String msg) {

		IntStream exceptionInput =  e.input;
		CommonTokenStream input = (CommonTokenStream) exceptionInput;
		ParameterizedIndicatorsLexer tokenSource = (ParameterizedIndicatorsLexer) input.getTokenSource();
		String lexerMatch = tokenSource.getText();
		String parserMatch = ""+e.c;
		String stateMatch = parser.getParserDelegate().getState().text;
		System.out.println("Parsed : "+parsedLine+", Matched - Lexer - : "+lexerMatch+", Matched - Parser - : "+parserMatch+", Matched - State - : "+stateMatch+", stack : "+stack+", msg : "+msg);


	}

	protected String logMsg(RecognitionException e) {
		String hdr = parser.getErrorHeader(e);
		String err = parser.getErrorMessage(e, parser.getTokenNames());
		String errorMsg = hdr+" "+err;
		return errorMsg;
	}

}
