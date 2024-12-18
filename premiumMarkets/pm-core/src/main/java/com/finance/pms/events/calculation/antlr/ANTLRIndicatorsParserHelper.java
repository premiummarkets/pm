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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;
import org.antlr.runtime.tree.CommonTree;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;
import com.finance.pms.events.calculation.parametrizedindicators.antlr.ParameterizedIndicatorsLexer;
import com.finance.pms.events.calculation.parametrizedindicators.antlr.ParameterizedIndicatorsParser;
import com.finance.pms.events.operations.Operation;

public class ANTLRIndicatorsParserHelper extends ANTLRParserHelper {

	private static MyLogger LOGGER = MyLogger.getLogger(ANTLRIndicatorsParserHelper.class);

	public static final List<String> CONDITION_ONOPS_TOKENS = 
			new ArrayList<String>(
					Arrays.asList(
							"reverses up" ,"reverses down" ,
							"goes up more than" ,"goes down more than" ,
							"crosses up threshold" ,"crosses down threshold", "equals trend", "equals threshold", "is above threshold", "is below threshold",
							"makes a higher high spanning", "makes a higher low spanning", "makes a lower high spanning", "makes a lower low spanning",
							"makes a support break down spanning", "makes a support break up spanning",
							"crosses up historical", "crosses down historical", "equals historical", "is above historical","is below historical",
							"trends like", "trends unlike", "trends flat"
							));
	public static final List<String> HISTORICALDATA_TOKENS = new ArrayList<String>(Arrays.asList("close","open","high","low","volume"));
	//TODO name tokens should be an enum and have a TokenType for each value.
	//TODO merge syntax in LexerDelegate and this?
	public static final List<String> NAMED_TOKENS = 
			new ArrayList<String>(
					Arrays.asList(
							"WhiteChar",
							"SEMICOLUMN", "COMMA", "OPENSQRT", "CLOSESQRT", "PERCENT", "DAYS", "OPENPARENTEHSIS", "CLOSEPARENTEHSIS",
							"NumberToken", "HistoricalData",
							"AND", "OR", "MATCHING", "NOT", "TRUTHOF"));

	private static final String WHITECHARVALUE = " ";

	public static int exceptionIndex;

	public CommonTree buildTree(InputStream inputStream) throws Exception {

		String parsedLine = ANTLROperationsParserHelper.parsedLine(inputStream);

		try {

			LinkedList<RecognitionExceptionHolder> exceptions = new LinkedList<RecognitionExceptionHolder>();
			MyErrorReporter errorReporter = new SimpleErrorReporter(exceptions, null);

			ANTLRInputStream in = new ANTLRInputStream(inputStream);
			ParameterizedIndicatorsLexer lexer = new ParameterizedIndicatorsLexer(in);
			lexer.setLexerDelegate(new IndsBlindLexerDelegate(in));
			lexer.setMyErrorReporter(errorReporter);

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ParameterizedIndicatorsParser parser = new ParameterizedIndicatorsParser(tokens);
			parser.setParserDelegate(new IndsBlindParserDelegate());
			parser.setMyErrorReporter(errorReporter);

			return (CommonTree) parser.complete_expression().getTree();

		} catch (ExitParsingException e) {
			LOGGER.warn("Indicator " + parsedLine + " early exit : " + "cause : " + e.getCause());
			throw new ExitParsingException("Indicator " + parsedLine + " early exit : " + "cause : " + e.getCause(), e);

		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;

		} 

	}


	@Override
	public NextToken checkNextToken(InputStream inputStream) {

		String parsedLine = ANTLROperationsParserHelper.parsedLine(inputStream);

		LinkedList<RecognitionExceptionHolder> exceptions = new LinkedList<RecognitionExceptionHolder>();

		try {

			ANTLRInputStream in = new ANTLRInputStream(inputStream);

			RecognizerSharedState state = new RecognizerSharedState();
			ParameterizedIndicatorsLexer lexer = new ParameterizedIndicatorsLexer(in, state);

			Set<EditorOpDescr> allOps = new TreeSet<EditorOpDescr>();
			allOps.addAll(nativeOpEditorDescrs);
			allOps.addAll(userCurrentOpEditorDescrs);
			IndsLexerDelegate editorLexerDelegate = new EditorIndsLexerDelegate(in, state, allOps);

			lexer.setLexerDelegate(editorLexerDelegate);
			MyLexerErrorReporter lexerErrorReport = new MyLexerErrorReporter(lexer, exceptions, parsedLine);
			lexer.setMyErrorReporter(lexerErrorReport);


			CommonTokenStream tokens = new  CommonTokenStream(lexer);
			ParameterizedIndicatorsParser parser = new ParameterizedIndicatorsParser(tokens, state);
			IndsParserDelegate editorParserDelegate = new EditorIndsParserDelegate(tokens, state,  allOps);
			parser.setParserDelegate(editorParserDelegate);
			IndsParserErrorReporter parserErrorReport = new IndsParserErrorReporter(parser, exceptions, parsedLine);
			parser.setMyErrorReporter(parserErrorReport);

			try {
				exceptionIndex = 0;
				parser.complete_expression();
			} catch (ExitParsingException e) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Early exit : " + e);
			}

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Exception stack :" + exceptions);

			if (LOGGER.isDebugEnabled()) LOGGER.debug("---------------------------------------------------");
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {
				if (LOGGER.isDebugEnabled()) if (LOGGER.isDebugEnabled()) LOGGER.debug(parsedLine + "\\," + exceptionHolder.toCsv());
			}
			if (LOGGER.isDebugEnabled()) LOGGER.debug("---------------------------------------------------");

			SortedMap<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList = new TreeMap<AltType, SortedMap<Integer,LinkedList<Alternative>>>(new Comparator<AltType>() {

				@Override
				public int compare(AltType o1, AltType o2) {
					if (o1.equals(o2)) return 0;
					return (o1.equals(AltType.DELETE))?-1:1;
				}
			});

			//Filter
			Boolean deleteFilter = true;
			List<String> longestStack = null;

			//Pre stuff
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {

				RecognitionException exception = exceptionHolder.getException();

				if (exception instanceof NoViableAltException) {
					if (exception.c == Token.EOF) {
						deleteFilter = false;
					}
				} 
				if (exception.input instanceof CommonTokenStream  && (longestStack == null || exceptionHolder.getRuleStack().size() > longestStack.size())) longestStack=exceptionHolder.getRuleStack();
			}

			//Process
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {
				RecognitionException exception = exceptionHolder.getException();

				if (exception.input instanceof CommonTokenStream && exceptionHolder.getRuleStack().size() < longestStack.size()) continue;
				//if (exception.input instanceof ANTLRInputStream) continue;

				LOGGER.info("Call stack : " + longestStack);
				String lastStack = (longestStack != null)?longestStack.get(longestStack.size()-1):"unknown";
				String expectedTxt = "";
				int[] deletePosition =  null; //new int[]{exception.line,exception.charPositionInLine+tokenTxt.length()-1};

				//Tell apart Lexer and Parser errors at this point to avoid having to do it later :
				//Exception 1. coming for Lexer => the 'token' or analysed char is in exception.c 
				//Exception 2. coming from parser => the 'token' is in exception.token.getText()
				String tokenTxt = "";
				boolean eofToken = false;
				if (exception.input instanceof CommonTokenStream) {//Parser
					if (exception.token != null) {
						tokenTxt = exception.token.getText();
						if (tokenTxt.equals("<EOF>")) {
							tokenTxt="";
							eofToken=true;
						}
						deletePosition = new int[]{exception.line,exception.charPositionInLine+tokenTxt.length()-1};
						expectedTxt = (exceptionHolder.getExpectedToken() != null)?exceptionHolder.getExpectedToken():"";
					}
				} 
				else if (exception.input instanceof ANTLRInputStream) { //Lexer
					int inputCharInt = exception.c;
					if (Character.isDefined((char)inputCharInt)) {
						tokenTxt=""+(char)inputCharInt;
					}
					if (inputCharInt == Token.EOF) {
						eofToken = true;
						deletePosition = new int[]{exception.line,exception.charPositionInLine-1};
					} else {
						deletePosition = new int[]{exception.line,exception.charPositionInLine+tokenTxt.length()-1};
					}
				}
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Expected : " + expectedTxt);

				//TokenTxt
				if (tokenTxt.isEmpty() && !parsedLine.isEmpty()) {
					Boolean trailiWhites = true;
					int i = parsedLine.length()-1;
					if (parsedLine.charAt(i) != ' ') {
						for (; i >= 0 ; i--) {
							char charAt = parsedLine.charAt(i);
							if (trailiWhites && charAt != ' ') trailiWhites = false;
							if (!trailiWhites && charAt != '.' && !Character.isLetterOrDigit(charAt)){
								i--;
								break;
							}
						}
						tokenTxt = parsedLine.substring(i+1, parsedLine.length()).trim();
					}
					if (!eofToken) deletePosition = new int[]{exception.line,exception.charPositionInLine+tokenTxt.length()-1};
				}
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Token : " + tokenTxt);

				//Positions
				int[] eofPosition =  ANTLRParserHelper.translateCaretToPosition(parsedLine, parsedLine.length()-1);
				if (eofPosition[0] == exception.line && (eofPosition[1] == exception.charPositionInLine || eofPosition[1] == (exception.charPositionInLine+tokenTxt.length()-1)) ) {
					eofToken = true;
				}
				if (deletePosition == null) {
					deletePosition = new int[]{eofPosition[0], eofPosition[1]-1};
				}
				//int[] tokenPosition = new int[]{deletePosition[0], deletePosition[1]+1};

				if (LOGGER.isDebugEnabled()) LOGGER.debug("Delete position : " + deletePosition[0] + ", " + deletePosition[1]);
				//if (LOGGER.isDebugEnabled()) LOGGER.debug("Token position : " + tokenPosition[0] + ", " + tokenPosition[1]);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Exception position : " + exception.line + ", " + exception.charPositionInLine);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("EOF Position : " + eofPosition[0] + ", " + eofPosition[1]);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("is EOFToken : " + eofToken);

				//Expected
				if (expectedTxt != null && expectedTxt.equals("WhiteChar")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, WHITECHARVALUE, "Insert", "White Space expected", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "White Space expected.", null, deletePosition));
					}
					break;
				}
				if (expectedTxt != null && expectedTxt.equals("PERCENT")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, "%", "Insert", "'%' expected.", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'%' expected.", null, deletePosition));
					}
					break;
				}
				if (expectedTxt != null && expectedTxt.equals("DAYS")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "days", "Insert", "'days' expected.", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'days' expected.", null, deletePosition));
					}
					break;
				}
				if (expectedTxt != null && expectedTxt.equals("OPENSQRT")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "[", "Insert", "'[' expected.", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'[' expected.", null, deletePosition));
					}
					break;
				}
				if (expectedTxt != null && expectedTxt.equals("CLOSESQRT")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "]", "Insert", "']' expected.", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "']' expected.", null, deletePosition));
					}
					break;
				}
				if (expectedTxt != null && expectedTxt.equals("COMMA")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, -1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, ",", "Insert", "',' expected.", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "',' expected.", null, deletePosition));
					}
					break;
				}
				else if (expectedTxt != null && expectedTxt.equals("NumberToken")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1)
						.add(new Alternative(AltType.SUGGESTION, TokenType.CONSTANTTOKEN, "Number", "Insert", "Condition threshold expected", "0.0", eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "Number expected.", null, deletePosition));
					}
				}
				else if (expectedTxt != null && expectedTxt.equals("OPENPARENTEHSIS")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, "(", "Nested expression", "( A and B and (C or D) , A or (C and D) ...)", null, eofPosition));
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "not(", "Negation", "not( A ), not( B and ( C or D ) ...)", null, eofPosition));
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "truth of", "Conjunction range", "truth of A, B, C .. is within [a,b]", null, eofPosition));
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'" +"("+ "' expected.", null, deletePosition));
					}
					break;
				}
				else if (expectedTxt != null && expectedTxt.equals("CLOSEPARENTEHSIS")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, ")", "Nested expression closure", "To end the nested expression", null, eofPosition));
						addUsualSuspectSuggestions(" ", " ", priorityList, eofPosition);
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'" +")"+ "' expected.", null, deletePosition));
					}
					break;
				}
				else if (expectedTxt != null && expectedTxt.equals("SEMICOLUMN")) {
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, ";", "Insert", "End of the bullish/bearish expression", null, eofPosition));
						addUsualSuspectSuggestions(" ", " ", priorityList, eofPosition);
					} else {
						altPrioListForTokType(priorityList, AltType.DELETE, -1)
						.add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid or Incomplete Entry", "'" + ";" + "' expected.", null, deletePosition));
					}
					break;
				}
				//TODO enum of usual expressions : OR, AND, MATCHING and iterate through
				else if (expectedTxt != null && ( expectedTxt.equals("OR") || expectedTxt.equals("AND") || expectedTxt.equals("MATCHING") ) && tokenTxt.isEmpty()) {
					addUsualSuspectSuggestions("", " ", priorityList, eofPosition);
					break;
				}
				else if (expectedTxt != null && expectedTxt.equals("HistoricalData")) {
					//All ops
					List<Alternative> allOpsAndStockDataAsAlts = new ArrayList<Alternative>();
					Boolean foundMatching = addAllOps(allOpsAndStockDataAsAlts, tokenTxt, eofPosition);
					if (!foundMatching && deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(
								new Alternative(AltType.DELETE, TokenType.DELETE, tokenTxt, "Invalid operation", "Operation or stock historical output expected.", null, deletePosition));
					} 
					else if (!allOpsAndStockDataAsAlts.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allOpsAndStockDataAsAlts);
					}
				}

				//Short cuts on expected
				if (expectedTxt != null && !expectedTxt.isEmpty() &&
						(exception instanceof MismatchedTokenException || exception instanceof UnwantedTokenException || exception instanceof MissingTokenException)) {

					boolean isWhiteChar = expectedTxt.equals(WHITECHARVALUE);
					//This is because 'primary_expression' and WhiteChar are different from the grammar point of view. ' ' are the white space in between grammar key word tokens and WhiteChar is a token (used inside rules)
					//' ' is actually KEYWORDS or part of it but has to be seen as SYNTAX from the UI point of view
					//					if ( exception instanceof MismatchedTokenException  && !NAMED_TOKENS.contains(expectedTxt) && !eofToken) {
					//						altPrioListForTokType(priorityList, AltType.DELETE, 1)
					//						.add(new Alternative(AltType.DELETE, TokenType.DELETE, tokenTxt, "Invalid entry", (isWhiteChar?"White Space":"'" + expectedTxt + "'")+" expected.", null, deletePosition));
					//						//break;
					//					}
					if (exception instanceof UnwantedTokenException  && !NAMED_TOKENS.contains(expectedTxt) ) {
						if (!eofToken) {
							altPrioListForTokType(priorityList, AltType.DELETE, 1)
							.add(new Alternative(AltType.DELETE, TokenType.DELETE, tokenTxt, "Invalid character", (isWhiteChar?"White Space":"'" + expectedTxt + "'") + " expected.", null, deletePosition));
						} else {
							altPrioListForTokType(priorityList, AltType.SUGGESTION, 1)
							.add(new Alternative(AltType.SUGGESTION,  (isWhiteChar?TokenType.SYNTAX:TokenType.KEYWORDS), expectedTxt, "Insert", (isWhiteChar?"White Space":"'" + expectedTxt + "'")+" expected.", null, eofPosition));
						}
						break;
					}
					if (exception instanceof MissingTokenException) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(
								new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Missing entry", (isWhiteChar?"White Space":"'" + expectedTxt + "'")+" expected.", null, deletePosition));
						break;
					}
				}

				//Stack
				if (longestStack != null && longestStack.contains("conjunctiontruthof") && lastStack.equals("lenient")) { //"and_expression" within a "conjunctiontruthof"
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, ", ", "Insert", "To set the next conjunction", null, eofPosition));
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, " is within ", "Insert", "Specify the conjunction truth range", null, eofPosition));
					}
				}
				else if (lastStack.equals("lenient")) { //"and_expression"
					if (eofToken) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, ";", "Insert", "End of the bullish/bearish expression", null, eofPosition));
						addUsualSuspectSuggestions(" ", " ", priorityList, eofPosition);
					}
				}
				else if (lastStack.equals("bearish_condition")) {
					List<Alternative> allBoolTokens = new ArrayList<Alternative>();
					Boolean foundMatch = addSuggsAsAltsContainsMatch(allBoolTokens, tokenTxt.equals(";")?"":tokenTxt, parsedLine, deletePosition, new String[]{"is bearish when", "is bearish if not bullish;", "is bearish if not bullish and", "is bearish if not bullish or"}, "To set the bearish expression : ", TokenType.KEYWORDS);
					if (!foundMatch) {//&& deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid condition", "'is bearish when/if' expected", null, deletePosition));
					} 
					else if (!allBoolTokens.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allBoolTokens);
				}
				else if (lastStack.equals("bearish_not_bullish")) {

					List<Alternative> allPresetConditions = new ArrayList<Alternative>();
					Boolean foundMatching = addSuggsAsAltsContainsMatch(allPresetConditions, tokenTxt, parsedLine, deletePosition, new String[]{"is bearish if not bullish;", "is bearish if not bullish and ", "is bearish if not bullish or "}, "Condition : ", TokenType.KEYWORDS);
					if (!foundMatching) { //&& deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid condition", "is bearish when/if' expected", null, deletePosition));
					}
					if (!allPresetConditions.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allPresetConditions);
					break;
				}
				else if (lastStack.equals("bullish_condition")) {
					List<Alternative> allBoolTokens = new ArrayList<Alternative>();
					Boolean foundMatch = addSuggsAsAltsContainsMatch(allBoolTokens, tokenTxt, parsedLine, deletePosition, new String[]{"is bullish when"}, "To set the bullish expression : ", TokenType.KEYWORDS);
					if (!foundMatch) { // && deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid condition", "'is bullish when' expected", null, deletePosition));
					} 
					else if (!allBoolTokens.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allBoolTokens);
				}
				else if (lastStack.equals("also_display")) {
					List<Alternative> allBoolTokens = new ArrayList<Alternative>();
					Boolean foundMatch = addSuggsAsAltsContainsMatch(allBoolTokens, tokenTxt, parsedLine, deletePosition, new String[]{"also display"}, "To set the additional display : ", TokenType.KEYWORDS);
					if (!foundMatch) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid statement", "'also display' expected", null, deletePosition));
					} 
					else if (!allBoolTokens.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allBoolTokens);
				}
				else if (lastStack.equals("fixed_start_shift")) {
					List<Alternative> allBoolTokens = new ArrayList<Alternative>();
					Boolean foundMatch = addSuggsAsAltsContainsMatch(allBoolTokens, tokenTxt, parsedLine, deletePosition, new String[]{"override start shift with"}, "To set a constant calculation start date shift : ", TokenType.KEYWORDS);
					if (!foundMatch) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid override", "'also display' expected", null, deletePosition));
					} 
					else if (!allBoolTokens.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allBoolTokens);
				}
				else if (lastStack.equals("primary_expression") || lastStack.equals("atom")) {

					//Open a new nested expression
					if (tokenTxt.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "(", "Nested expression", "( A and B and (C or D) , A or (C and D) ...)", null, eofPosition));
					}

					//Start a not condition 
					if (tokenTxt.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "not(", "Negation", "not( A ), not( B and ( C or D ) ...)", null, eofPosition));
					}

					//Start a conjunction range expression
					if (tokenTxt.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, "truth of", "Conjunction range", "truth of A, B, C .. is within [a,b]", null, eofPosition));
					}

					//All ops
					List<Alternative> allOpsAndStockDataAsAlts = new ArrayList<Alternative>();
					Boolean foundMatching = addAllOps(allOpsAndStockDataAsAlts, tokenTxt.equals("(")?"":tokenTxt, eofPosition);
					if (!foundMatching && deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid operation", "Operation or stock historical output expected.", null, deletePosition));
					} 
					else if (!allOpsAndStockDataAsAlts.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allOpsAndStockDataAsAlts);
					}

				}
				else if (lastStack.equals("booleanhistory")) {
					//Conditions on ops
					List<Alternative> allPresetConditions = new ArrayList<Alternative>();

					SortedSet<String> sortedConditionOnopsTokens = new TreeSet<String>();
					sortedConditionOnopsTokens.addAll(CONDITION_ONOPS_TOKENS);

					Boolean foundMatching = addSuggsAsAltsContainsMatch(allPresetConditions, tokenTxt, parsedLine, deletePosition, sortedConditionOnopsTokens, "Condition : ", TokenType.KEYWORDS);
					if (!foundMatching) { //&& deleteFilter) {
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid condition", "Condition on historical output excepted.", null, deletePosition));
					}
					if (!allPresetConditions.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allPresetConditions);
					//break;
				}
				else if (lastStack.equals("opcmpcondition") || lastStack.equals("operand") || lastStack.equals("checkOperationValidity")) {

					//All ops
					List<Alternative> allOpsAndStockDataAsAlts = new ArrayList<Alternative>();
					Boolean foundMatching = addAllOps(allOpsAndStockDataAsAlts, tokenTxt, eofPosition);
					if (!foundMatching &&  deleteFilter) { //|| (!allOpsAndStockDataAsAlts.isEmpty() && !eofToken)) {
						if (lastStack.equals("checkOperationValidity") && exception instanceof InvalidOperationException) {
							deletePosition =  new int[]{exception.line,exception.charPositionInLine-1};
						}
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, tokenTxt, "Invalid operation",  "Operation or stock historical output expected.", null, deletePosition));
					} 
					else if (!allOpsAndStockDataAsAlts.isEmpty()) {
						altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allOpsAndStockDataAsAlts);
					}
				}
				else if (lastStack.equals("unknown")) {
					boolean isWhiteChar = expectedTxt.equals(WHITECHARVALUE) || expectedTxt.isEmpty();
					altPrioListForTokType(priorityList, AltType.DELETE, 1)
					.add(new Alternative(AltType.DELETE, TokenType.DELETE, tokenTxt, "Invalid entry", (isWhiteChar?"White Space":"'" + expectedTxt + "'")+" expected.", null, deletePosition));
				}

			}

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Final position : " + state.tokenStartLine + ", " + state.tokenStartCharPositionInLine);
			if (priorityList.isEmpty()) {
				int finalCaretPosition = translatePositionToCaret(parsedLine, state.tokenStartLine, state.tokenStartCharPositionInLine);
				if (parsedLine.length() > finalCaretPosition) {
					String excedent = parsedLine.substring(finalCaretPosition, parsedLine.length());
					altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, excedent, "Invalid entry", "Please delete", null, new int[]{state.tokenStartLine, state.tokenStartCharPositionInLine+excedent.length()}));
				}
			}

			NextToken nextToken= null;
			if (!priorityList.isEmpty()) {
				nextToken = new NextToken();
				for (SortedMap<Integer, LinkedList<Alternative>> altTypePList : priorityList.values()) {
					if (!altTypePList.isEmpty()) {
						nextToken.addAllAlternative(altTypePList.get(altTypePList.firstKey()));
					}
				}
			}


			if (LOGGER.isDebugEnabled()) LOGGER.debug("Priority list : " + priorityList);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Next token alt : " + nextToken);

			return nextToken;


		} catch (IOException e) {
			LOGGER.error(e,e);
		} catch (RecognitionException e) {
			LOGGER.error("This should have been caught : "+e, e);

		}

		return null;
	}


	private void addUsualSuspectSuggestions(String prefix, String suffix, SortedMap<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList, int[] eofPosition) {
		altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, prefix + "with" + suffix, "Insert", "To add a 'precondition' expression", null, eofPosition));
		altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, prefix + "matching" + suffix, "Insert", "To add a 'matching' expression", null, eofPosition));
		altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, prefix + "or" + suffix, "Insert", "To add an 'or' expression", null, eofPosition));
		altPrioListForTokType(priorityList, AltType.SUGGESTION, 1).add(new Alternative(AltType.SUGGESTION, TokenType.KEYWORDS, prefix + "and" + suffix, "Insert", "To  add an 'and' expression", null, eofPosition));
	}


	protected Boolean addAllOps(List<Alternative> allOpsAndStockDataAsAlts, String expectedTxt, int[] position) {
		Boolean foundMatching = addSuggsAsAlts(allOpsAndStockDataAsAlts, expectedTxt, position, HISTORICALDATA_TOKENS, "For a expression on stock historical ");
		try {
			allOpsAndStockDataAsAlts.addAll(addAllOpsAsAlts(expectedTxt, position));
			foundMatching = true;
		} catch (IllegalArgumentException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("No operation matching" + e);
		}
		return foundMatching;
	}

	private Boolean addSuggsAsAltsContainsMatch(List<Alternative> alternatives, String token, String parsedLine, int[] highLighPosition, Collection<String> tokens, String descreptionPrefix, TokenType tokenType) {

		boolean foundMatch = false;
		SortedMap<Integer, List<Alternative>> matchings = new TreeMap<Integer, List<Alternative>>();

		String subParsedLine = " "+parsedLine.substring(0, ANTLRParserHelper.translatePositionToCaret(parsedLine, highLighPosition[0], highLighPosition[1])); 

		matchings.put(subParsedLine.length(), new ArrayList<Alternative>());

		for (String htoken : tokens) {
			boolean equalsMatch = subParsedLine.endsWith(" "+htoken);
			if (equalsMatch) {//exact match : counting but no adding
				foundMatch = true;
			} 
			else {

				//Empty fall back matching
				if (token.isEmpty()) {
					matchings.get(subParsedLine.length()).add(new Alternative(AltType.SUGGESTION, tokenType, htoken, descreptionPrefix + htoken, null, null, highLighPosition));
					foundMatch = true;
				}

				//Partial matching
				boolean partialMatch = false;
				int i = htoken.length();
				for (; i > 0 ; i--) {
					for (String syntaxTok : EditorIndsLexerDelegate.SYNTAX_TOKENS) {
						partialMatch = subParsedLine.endsWith(syntaxTok+htoken.substring(0,i));
						if (partialMatch) break;
					}
					if (partialMatch) break;
				}
				if (partialMatch && !equalsMatch) {//partial match : adding
					int pos = subParsedLine.length()-htoken.substring(0,i).length();
					List<Alternative> matching = matchings.get(pos);
					if (matching == null) {
						matching = new ArrayList<Alternative>();
						matchings.put(pos, matching);
					}
					matching.add(new Alternative(AltType.SUGGESTION, tokenType, htoken, descreptionPrefix + htoken, null, null, highLighPosition));
					foundMatch = true;
				}

			}
		}
		if (foundMatch && !matchings.isEmpty()) {
			alternatives.addAll(matchings.get(matchings.firstKey()));
		}
		return foundMatch;
	}

	private Boolean addSuggsAsAltsContainsMatch(List<Alternative> alternatives, String token, String parsedLine, int[] highLighPosition, String[] tokens, String descreptionPrefix, TokenType tokenType) {
		return addSuggsAsAltsContainsMatch(alternatives, token, parsedLine, highLighPosition, Arrays.asList(tokens), descreptionPrefix, tokenType);
	}



	@Override
	public void updateEditableOperationLists(Map<String, Operation> nativeOperations, Map<String, Operation> userCurrentOperations) {
		super.updateEditableOperationLists(nativeOperations, userCurrentOperations);
		this.nativeOpEditorDescrs = filterParamLessOps(this.nativeOpEditorDescrs);
		this.userCurrentOpEditorDescrs = filterParamLessOps(this.userCurrentOpEditorDescrs);
	}


	private ConcurrentSkipListSet<EditorOpDescr> filterParamLessOps(Set<EditorOpDescr> ops) {

		ConcurrentSkipListSet<EditorOpDescr>  paramLessOps = new ConcurrentSkipListSet<EditorOpDescr>(new Comparator<EditorOpDescr>() {
			@Override
			public int compare(EditorOpDescr o1, EditorOpDescr o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (EditorOpDescr editorOpDescr : ops) {
			if (editorOpDescr.getParams().size() == 0) paramLessOps.add(editorOpDescr);
		}
		return paramLessOps;

	}

}
