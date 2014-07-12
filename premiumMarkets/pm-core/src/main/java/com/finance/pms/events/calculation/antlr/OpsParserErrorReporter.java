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
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedNotSetException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;

import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsLexer;
import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsParser;

class TokensForExcp {
	
	RecognitionException exception;
	NextToken nextToken;
	
	public TokensForExcp(RecognitionException exception, NextToken nextToken) {
		super();
		this.exception = exception;
		this.nextToken = nextToken;
	}
	
	@Override
	public String toString() {
		return "TokensForExcp [nextToken=" + nextToken.getAlternatives() + "]";
	}
}

public class OpsParserErrorReporter  extends MyErrorReporter {
	
	private ParameterizedOperationsParser parser;
	private OpsParserDelegate parserDelegate;
	
	public OpsParserErrorReporter(ParameterizedOperationsParser parser, LinkedList<RecognitionExceptionHolder> exceptions, String parsedLine) {
		super(exceptions, parsedLine);
		this.parser = parser;
		this.parserDelegate = parser.getParserDelegate();
	}

	public void report(RecognitionException e) {

		List<String> stack = Parser.getRuleInvocationStack(e, parser.getClass().getName());
		String expectedTokenName = null;
		
		String msg = "PARSER Exception : "+e.getClass()+". ";
		
		try {
			//Unhandled
			if ( e instanceof UnwantedTokenException ) {
				UnwantedTokenException ute = (UnwantedTokenException)e;
				//Log
				if ( ute.expecting== Token.EOF ) {
					expectedTokenName = "EOF";
				}
				else {
					expectedTokenName = parser.getTokenNames()[ute.expecting];
				}
				msg = msg + "extraneous input "+parser.getTokenErrorDisplay(ute.getUnexpectedToken())+" expecting "+expectedTokenName;
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
			else if ( e instanceof EarlyExitException ) {

				//Log
				msg = msg + "required (...)+ loop did not match anything at input "+ parser.getTokenErrorDisplay(e.token);
			}
			else if ( e instanceof MismatchedTreeNodeException ) {
				MismatchedTreeNodeException mtne = (MismatchedTreeNodeException)e;

				//Log
				if ( mtne.expecting==Token.EOF ) {
					expectedTokenName = "EOF";
				}
				else {
					expectedTokenName = parser.getTokenNames()[mtne.expecting];
				}

				msg = msg + "mismatched tree node: "+mtne.node+" expecting "+expectedTokenName;

			}
			else if ( e instanceof MismatchedSetException ) {
				MismatchedSetException mse = (MismatchedSetException) e;

				//Log
				msg = msg + "mismatched input "+parser.getTokenErrorDisplay(e.token)+" expecting set "+mse.expecting;
			}
			else if ( e instanceof MismatchedNotSetException ) {
				MismatchedNotSetException mse = (MismatchedNotSetException) e;

				//Log
				msg = msg + " (Unhandled) ";
				msg = msg + "mismatched input "+parser.getTokenErrorDisplay(e.token)+" expecting set "+mse.expecting;
			}

			//Handled
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
			else if ( e instanceof NoViableAltException ) {
				NoViableAltException nvae = (NoViableAltException) e;
				
				//Log
				msg = msg + "no viable alternative at input "+((nvae.token != null)?parser.getTokenErrorDisplay(nvae.token):"") +
						" (decision="+nvae.decisionNumber+
						" state "+nvae.stateNumber+")"+
						" decision=<<"+nvae.grammarDecisionDescription+">>";		
			} 
		
			else if ( e instanceof FailedPredicateException ) {
				FailedPredicateException fpe = (FailedPredicateException) e;

				//Log
				msg = msg + "rule "+fpe.ruleName+" failed predicate: {"+fpe.predicateText+"}?";

			}

			//Custom
			else if ( e instanceof ParamsCountException ) {
				ParamsCountException ppe = (ParamsCountException) e;

				//Log
				msg = msg + "error "+ppe;

			} 	
			else if ( e instanceof UnfinishedParameterException ) {
				UnfinishedParameterException upe = (UnfinishedParameterException) e;

				//Log
				msg = msg + "Param "+upe.getParamString()+", param type "+upe.getParamType();

			} 
			
			//Unmatched
			else {			
				//Log
				msg = msg + " (unmatched exception) "+ e.getMessage();
			}

		} finally {
			exceptions.add(new RecognitionExceptionHolder(e, parserDelegate.doesNeedClosing(), stack, (expectedTokenName != null)?expectedTokenName.replaceAll("'", ""):null, logMsg(e), msg));
			log(e, stack, msg);
		}
	
	}

	protected void log(RecognitionException e, List<String> stack, String msg) {

		IntStream exceptionInput =  e.input;
		CommonTokenStream input = (CommonTokenStream) exceptionInput;
		ParameterizedOperationsLexer tokenSource = (ParameterizedOperationsLexer) input.getTokenSource();
		String matched = tokenSource.getText();
		System.out.println("Parsed : "+parsedLine+", Matched : "+matched+", stack : "+stack+", msg : "+msg); // + ", "+ following);


	}

	protected String logMsg(RecognitionException e) {
		String hdr = parser.getErrorHeader(e);
		String err = parser.getErrorMessage(e, parser.getTokenNames());
		String errorMsg = hdr+" "+err;
		return errorMsg;
	}
}
