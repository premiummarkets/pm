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
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;
import org.antlr.runtime.tree.CommonTree;
import org.apache.tools.ant.filters.StringInputStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.EditorOpDescr.Param;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;
import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsLexer;
import com.finance.pms.events.operations.parameterized.antlr.ParameterizedOperationsParser;

public class ANTLROperationsParserHelper extends ANTLRParserHelper {

	private static MyLogger LOGGER = MyLogger.getLogger(ANTLROperationsParserHelper.class);

	public static int exceptionIndex;

	public CommonTree buildTree(InputStream inputStream) throws Exception {

		String parsedLine = parsedLine(inputStream);

		CommonTree tree = null;
		try {

			LinkedList<RecognitionExceptionHolder> exceptions = new LinkedList<RecognitionExceptionHolder>();
			MyErrorReporter errorReporter = new SimpleErrorReporter(exceptions, null);

			ANTLRInputStream in = new ANTLRInputStream(inputStream);
			ParameterizedOperationsLexer lexer = new ParameterizedOperationsLexer(in);
			lexer.setLexerDelegate(new OpsBlindLexerDelegate());
			lexer.setMyErrorReporter(errorReporter);

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ParameterizedOperationsParser parser = new ParameterizedOperationsParser(tokens);
			parser.setParserDelegate(new OpsBlindParserDelegate());
			parser.setMyErrorReporter(errorReporter);


			//tree
			tree = (CommonTree) parser.indicatorexpr().getTree();


		} catch (ExitParsingException e) {
			LOGGER.debug("Operation "+parsedLine+" early exit : "+e + "cause : "+e.getCause());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;
		} 

		return tree;
	}

	@Override
	public NextToken checkNextToken(InputStream inputStream) {

		String parsedLine = parsedLine(inputStream);

		LinkedList<RecognitionExceptionHolder> exceptions = new LinkedList<RecognitionExceptionHolder>();

		try {
			ANTLRInputStream in = new ANTLRInputStream(inputStream);

			RecognizerSharedState state = new RecognizerSharedState();
			ParameterizedOperationsLexer lexer = new ParameterizedOperationsLexer(in, state);
			OpsLexerDelegate editorLexerDelegate = new EditorOpsLexerDelegate(in, state, nativeOpEditorDescrs, userCurrentOpEditorDescrs);
			lexer.setLexerDelegate(editorLexerDelegate);
			MyLexerErrorReporter lexerErrorReport = new MyLexerErrorReporter(lexer, exceptions, parsedLine);
			lexer.setMyErrorReporter(lexerErrorReport);


			CommonTokenStream tokens = new  CommonTokenStream(lexer);
			ParameterizedOperationsParser parser = new ParameterizedOperationsParser(tokens, state);
			EditorOpsParserDelegate editorParserDelegate = new EditorOpsParserDelegate(tokens, state,  nativeOpEditorDescrs, userCurrentOpEditorDescrs);
			parser.setParserDelegate(editorParserDelegate);
			OpsParserErrorReporter parserErrorReport = new OpsParserErrorReporter(parser, exceptions, parsedLine);
			parser.setMyErrorReporter(parserErrorReport);

			try {
				exceptionIndex = 0;
				parser.indicatorexpr();
			} catch (ExitParsingException e) {
				LOGGER.debug("Early exit : "+e);
			}

			LOGGER.debug("Exception stack :" + exceptions);

			LOGGER.debug("---------------------------------------------------");
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug(parsedLine+"\\,"+exceptionHolder.toCsv());
			}
			LOGGER.debug("---------------------------------------------------");

			//Filter
			String suggFilter = "";
			Boolean deleteFilter = true;
			int[] filterPosition = new int[]{-1,-1};

			int startTPosition = -1;

			//Fillup Suggs
			Boolean fillUpParams = false;
			Boolean fillUpOps = false;
			int[] suggsPosition = new int[]{-1,-1};

			//Replay atts
			Boolean replay = false;
			int[] replayAt = new int[]{-1,-1};

			EditorOpDescr currentOp = null;

			SortedMap<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList =
					new TreeMap<AltType, SortedMap<Integer, LinkedList<Alternative>>>(new Comparator<AltType>() {
						@Override
						public int compare(AltType o1, AltType o2) {
							if (o1.equals(o2)) return 0;
							return (o1.equals(AltType.DELETE))?-1:1;
						}
					});

			//NoViableAltException filter cumul
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {

				RecognitionException exception = exceptionHolder.getException();

				if (exception instanceof NoViableAltException) {
					if (exception.c != Token.EOF && !EditorOpsLexerDelegate.SYNTAX_TOKENS.contains((exception.c + "")) && exception.token == null) {

						if (startTPosition == -1) {
							filterPosition = new int[]{exception.line, exception.charPositionInLine};
							startTPosition = translatePositionToCaret(parsedLine, filterPosition[0], filterPosition[1]);
							suggFilter = "" + (char)exception.c;
						} else {
							filterPosition = new int[]{exception.line, exception.charPositionInLine};
							int endTPosition = translatePositionToCaret(parsedLine, filterPosition[0], filterPosition[1]);
							suggFilter = parsedLine.substring(startTPosition-1, endTPosition);
						}

					} 
					else if (exception.c == Token.EOF) {
						deleteFilter = false;
					}
				}
			}

			for (RecognitionExceptionHolder exceptionHolder : exceptions) {

				RecognitionException exception = exceptionHolder.getException();

				//Alt options
				//Suggestions
				boolean isInputNumber = exception.token !=null && exception.token.getText() != null && !exception.token.getText().isEmpty() && Character.isDigit(exception.token.getText().charAt(0));
				if (exception.c == Token.EOF || isInputNumber ) { 
					if (exception instanceof NoViableAltException) {//NoViableAltException + EOF : FILTER
						if (exceptionHolder.getRuleStack().size() == 2) {//No operation yet, first typing : fill up all ops
							fillUpOps = true;
							deleteFilter = false;
						}
					} 
					else if (exception instanceof MismatchedTokenException) {
						int[] position = new int[]{exception.line, exception.charPositionInLine-1};
						if (exceptionHolder.getExpectedToken() != null && exceptionHolder.getExpectedToken().equals(")")) {
							if (exceptionHolder.getNeedsClosing() != null) {
								altPrioListForTokType(priorityList, AltType.SUGGESTION, 10)
								.add(new Alternative(
										AltType.SUGGESTION,TokenType.SYNTAX, exceptionHolder.getExpectedToken()+"" , "Syntax suggestion", 
										"Insert a '" + exceptionHolder.getExpectedToken() + "' to close " + exceptionHolder.getNeedsClosing().getName() + " statement.", null, position));
								if (exceptionHolder.getNeedsClosing().undeterministicParamCount()) {
									altPrioListForTokType(priorityList, AltType.SUGGESTION, 10)
									.add(new Alternative(
											AltType.SUGGESTION,TokenType.SYNTAX, ",", "Syntax suggestion", 
											"Insert a comma to add up arguments to " + exceptionHolder.getNeedsClosing().getName(), null, position));
								}
							}
						} else {//Other syntax
							altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).
							add(new Alternative(
									AltType.SUGGESTION,TokenType.SYNTAX, exceptionHolder.getExpectedToken() , 
									"Syntax suggestion", "Insert a '" + exceptionHolder.getExpectedToken() + "'", null, position));
						}
					} 
					else if (exception instanceof EarlyExitException) {
						replayAt = new int[]{exception.line, exception.charPositionInLine-1};
						replay = true;
						break;
					}  
					else if (exception instanceof UnfinishedParameterException) {
						UnfinishedParameterException unfinishedParameterException = (UnfinishedParameterException)exception;
						if (unfinishedParameterException.getParamType() == null) {
							suggsPosition = new int[]{exception.line, exception.charPositionInLine-1};
							currentOp = unfinishedParameterException.getCurrentOp();
							fillUpParams = true;
							deleteFilter = false;
						}
					}
					else if (exception instanceof ParamsCountException) {
						ParamsCountException pce = (ParamsCountException)exception;
						int[] position = new int[]{exception.line, exception.charPositionInLine-1+ pce.getParsedTxt().length()-1};
						switch (pce.getQualifier()) {
						case TYPE:
							altPrioListForTokType(priorityList, AltType.DELETE, 2).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;
						case SYNTAX:
							altPrioListForTokType(priorityList, AltType.DELETE, 4).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;
						case TOOMANYARGS:
							altPrioListForTokType(priorityList, AltType.DELETE, 2).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;
						case NOTENOUGHARGS :
						{
							if (currentOp == null) {
								currentOp = pce.getCurrentOp();
								suggsPosition = new int[]{pce.line, pce.charPositionInLine-1};
								fillUpParams = true;
								deleteFilter = false;
							}
						} 
						default:
						{}
						}
					} 
					else if  (exception instanceof InvalidOperationException)  {
						if (exceptionHolder.getRuleStack().size() == 4 && exceptionHolder.getRuleStack().get(3).equals("outputSelectorHint") ) {//No operation yet, first typing : fill up all ops
							suggFilter = (((InvalidOperationException) exception).token != null)?((InvalidOperationException) exception).token.getText():"";
							fillUpOps = true;
							deleteFilter = false;
						} else {
							String currentOpStr = (((InvalidOperationException) exception).token != null)?((InvalidOperationException) exception).token.getText():null;
							currentOpStr = (currentOpStr != null)?currentOpStr: " ";
							int[] position = new int[]{exception.line, exception.charPositionInLine + currentOpStr.length()-1-1};
							altPrioListForTokType(priorityList, AltType.DELETE, 5).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry",  " Invalid operation EOF", null, position));
						}
					}
					else if (exception instanceof MissingOutputSelectorException) {
						List<Alternative> alternatives = new ArrayList<Alternative>();
						MissingOutputSelectorException missingOutputSelectorException = (MissingOutputSelectorException) exception;
						String[] expected = missingOutputSelectorException.getExpected();
						currentOp = missingOutputSelectorException.getCurrentOp();
						String currentOutput = missingOutputSelectorException.token.getText();
						currentOutput = (currentOutput == null || currentOutput.equals("<EOF>"))?"":currentOutput;
						if (currentOp == null) {
							int[] position = new int[]{exception.line, exception.charPositionInLine -1};
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry",  "Invalid entry", null, position));
						} else
							if (expected != null && expected.length > 0) {

								int[] position = new int[]{exception.line, exception.charPositionInLine -1};
								Boolean foundMatching = addSuggsAsAlts(alternatives, currentOutput , position, "Output selector option required for "+currentOp.getName()+" : ", expected);
								if (!foundMatching) {
									altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry", "Condition on historical output excepted.", null, position));
								}
								if (!alternatives.isEmpty()) {
									altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(alternatives);
								}
							} 
							else {
								int[] position = new int[]{exception.line, exception.charPositionInLine -1};
								altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry",  currentOp.getName() + " doesn't support output selectors", null, position));
							}
					}
					else {
						LOGGER.warn("Unmatched exception for suggestions " + exceptionHolder);
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry", (exception != null)?exception.toString():" ", null, new int[]{0, 0}));
					}

				//Errors
				} else {
					if  (exception instanceof InvalidOperationException)  {
						Token token = ((InvalidOperationException) exception).token;
						String currentOpStr = (token !=null && token.getText() != null)? token.getText() : " ";
						int[] position = new int[]{exception.line, exception.charPositionInLine - (currentOpStr.length() + 1)};
						altPrioListForTokType(priorityList, AltType.DELETE, 5).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry", " Invalid operation", null, position));
					}
					else if (exception instanceof NoViableAltException) {///NoViableAltException + !EOF  : DELETE FILTER (-SYNTAX)deleteFilter
						//Nothing
					} 
					else if (exception instanceof ParamsCountException) {
						//FIXME the position is wrong when the param is a sub op (it doesn't take in account the params of the sub op them selves)
						ParamsCountException pce = (ParamsCountException)exception;
						int[] position = new int[]{exception.line, exception.charPositionInLine + pce.getParsedTxt().length()-1};
						switch (pce.getQualifier()) {
						case TYPE:
							altPrioListForTokType(priorityList, AltType.DELETE, 2).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;
						case SYNTAX:
							altPrioListForTokType(priorityList, AltType.DELETE, 4).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;
						case TOOMANYARGS:
							altPrioListForTokType(priorityList, AltType.DELETE, 2).add(new Alternative(AltType.DELETE,TokenType.DELETE, pce.getParsedTxt(), "Invalid entry", pce.getErrorMsg(), null, position));
							break;	
							//end of line cases but doesn't show as such as the position is set at the start of the first arg
						case NOTENOUGHARGS :
						{
							if (currentOp == null) {
								currentOp = pce.getCurrentOp();
								suggsPosition = new int[]{pce.line, pce.charPositionInLine-1};
								fillUpParams = true;
								deleteFilter = false;
								int[] commaPosition = new int[]{exception.line, exception.charPositionInLine + pce.getParsedTxt().length() -1};
								altPrioListForTokType(priorityList, AltType.SUGGESTION, 0)
								.add(
										new Alternative(AltType.SUGGESTION,TokenType.SYNTAX, ",",  "Syntax suggestion", "Insert a Comma to add up arguments to "+currentOp.getName(), null, commaPosition)
										);	
							}
							break;
						}  
						default:
							break;
						}
					} 
					else if  (exception instanceof MissingTokenException)  {
						int[] position = new int[]{exception.line, exception.charPositionInLine};
						altPrioListForTokType(priorityList, AltType.DELETE, 1).add(new Alternative(AltType.DELETE,TokenType.DELETE, exceptionHolder.getExpectedToken(), "Invalid entry",  "'"+exceptionHolder.getExpectedToken()+"' expected.", null, position));
					}
					else if (exception instanceof UnwantedTokenException) {
						int[] position = new int[]{exception.line, exception.charPositionInLine};
						altPrioListForTokType(priorityList, AltType.DELETE, 1).add(new Alternative(AltType.DELETE,TokenType.DELETE, exception.c+"", "Invalid entry",  "'"+exceptionHolder.getExpectedToken()+"' expected.", null, position));
					} 
					else if (exception instanceof EarlyExitException) {
						int[] position = new int[]{exception.line, exception.charPositionInLine};
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, ""+(char)exception.c, "Invalid entry", "", null, position));
					}  
					else if (exception instanceof MissingOutputSelectorException) {
						MissingOutputSelectorException missingOutputSelectorException = (MissingOutputSelectorException) exception;
						String[] expected = missingOutputSelectorException.getExpected();
						currentOp = missingOutputSelectorException.getCurrentOp();
						String currentOutput = missingOutputSelectorException.token.getText();
						int[] position =  new int[]{exception.line, exception.charPositionInLine -1};
						if ( currentOp != null && (currentOutput == null || currentOutput.isEmpty()) ) {
							currentOutput = "";
							position =  new int[]{exception.line, exception.charPositionInLine};
						} 
						if (currentOp == null) {
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry", "Invalid entry ", null, position));
						} else
							if (expected != null && expected.length > 0) {
								altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry","Invalid output selector for " + currentOp.getName(), null, position));
							} else {
								altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry",  currentOp.getName() + " doesn't support output selectors", null, position));
							}

					}
					else {
						LOGGER.warn("Unmatched exception for errors " + exceptionHolder);
						altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry", (exception != null)?exception.toString():" ", null, new int[]{0, 0}));
					}

				}		
			}

			//Cumulated
			List<Alternative> allOpsAsAlts = null;
			List<Alternative> paramsAlts = null;

			if (fillUpOps) {
				try {
					allOpsAsAlts = addAllOpsAsAlts(suggFilter, suggsPosition);
					if (allOpsAsAlts != null && !allOpsAsAlts.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(allOpsAsAlts);
				} catch (IllegalArgumentException e) {
					deleteFilter = true;
				}
			}
			if (fillUpParams) {
				try {
					paramsAlts = addParamsAsAlts(currentOp, currentOp.getNbCommasParsed(), suggFilter, suggsPosition);
					if (paramsAlts != null && !paramsAlts.isEmpty()) altPrioListForTokType(priorityList,AltType.SUGGESTION, 0).addAll(paramsAlts);
				} catch (Exception e) {
					deleteFilter = true;
				}
			}

			if (deleteFilter && !suggFilter.isEmpty()) {
				altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, suggFilter, "Invalid entry", "Please delete", null, filterPosition));
			}

			LOGGER.debug("Final position : "+state.tokenStartLine+", "+state.tokenStartCharPositionInLine);
			int finalCaretPosition = translatePositionToCaret(parsedLine, state.tokenStartLine, state.tokenStartCharPositionInLine);
			if (parsedLine.length() > finalCaretPosition) {
				String excedent = parsedLine.substring(finalCaretPosition, parsedLine.length());
				altPrioListForTokType(priorityList, AltType.DELETE, 0)
				.add(new Alternative(AltType.DELETE,TokenType.DELETE, excedent, "Invalid entry", "Please delete", null, new int[]{state.tokenStartLine, state.tokenStartCharPositionInLine+excedent.length()}));
			}

			//NextToken
			NextToken nextToken= null;
			if (replay) {
				nextToken = checkNextToken(new StringInputStream(parsedLine.substring(0, parsedLine.length()-1)));
				if (nextToken != null) {
					for (Alternative alternative : nextToken.getAlternatives()) {
						if (alternative.getHighLighPosition()[0] == replayAt[0] && alternative.getHighLighPosition()[1] == replayAt[1] -1) {
							alternative.setHighLighPosition(replayAt);
						}
					}
				}
			} else {
				if (!priorityList.isEmpty()) {
					nextToken = new NextToken();
					for (SortedMap<Integer, LinkedList<Alternative>> altTypePList : priorityList.values()) {
						if (!altTypePList.isEmpty()) nextToken.addAllAlternative(altTypePList.get(altTypePList.firstKey()));
					}
				}
			}

			LOGGER.debug("Priority list : "+priorityList);
			LOGGER.debug("Next token alternatives : "+nextToken);

			return nextToken;

		} catch (IOException e) {
			LOGGER.error(e,e);
		} catch (RecognitionException e) {
			LOGGER.error("This should have been caught : "+e, e);
		} 

		return null;
	}



	protected Boolean checkHasDelete(Map<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList, int[] cumulPosition) {
		boolean hasDelete = false;
		SortedMap<Integer, LinkedList<Alternative>> altTypePList = priorityList.get(AltType.DELETE);
		if (altTypePList != null && !altTypePList.isEmpty()) {
			LinkedList<Alternative> linkedList = altTypePList.get(altTypePList.firstKey());
			if (linkedList != null) {
				for (Alternative alternative : linkedList) {
					if (alternative.getHighLighPosition()[0] == cumulPosition[0] && (alternative.getHighLighPosition()[1] == cumulPosition[1]+1)) {
						hasDelete = true;
						break;
					}
				}
			}
		}
		return hasDelete;
	}

	protected NextToken extractFirstTokenAlt(Map<Class<? extends RecognitionException>, List<TokensForExcp>> nextPotentialTokens, Class<? extends RecognitionException> exceptionClass) {
		NextToken nextToken = nextPotentialTokens.get(exceptionClass).get(0).nextToken;
		return nextToken;
	}

	protected List<Alternative> addParamsAsAlts(EditorOpDescr currentOp, int commaPosition, String currentTyping, int[] position) throws Exception  {

		int[] highLighPosition = new int[]{position[0],position[1]};

		List<Alternative> alternatives = new ArrayList<Alternative>();
		if ((currentOp.getParams().size() > commaPosition || currentOp.undeterministicParamCount())) {

			Param param = (currentOp.undeterministicParamCount())?currentOp.getCurrentParamOrVarArg(commaPosition):currentOp.getParams().get(commaPosition);
			String parsedParam =  currentOp.getParsedParma(commaPosition);

			parsedParam = (currentTyping != null && !currentTyping.isEmpty())?currentTyping:parsedParam;
			parsedParam = (parsedParam == null)?"":parsedParam;

			Boolean foundMatch = false;
			switch (param.getParamType()) {
			case NUMBER:
				if (!parsedParam.isEmpty()) {
					Double.valueOf(parsedParam);//Test the typing as double
					foundMatch = true;
				} else {
					String paramAltStr = (param.getParamName() == null)?"Number Argument":param.getParamName();
					String sringParamDefault = (param.getParamDefault() == null)?"0.0":param.getParamDefault();
					alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.CONSTANTTOKEN, paramAltStr, param.getParamDescription(),param.getParamSynoptic(), sringParamDefault, highLighPosition));
					foundMatch = true;
				}
				break;
			case STRING:
				if (!parsedParam.isEmpty()) {//Test typing as valid string
					if (!parsedParam.matches("\"[a-zA-Z](\")?")) throw new IllegalArgumentException(parsedParam + " is not a string of letters");
					foundMatch = true;
				} else {
					String paramAltStr = (param.getParamName() == null)?"String of Letters Argument":param.getParamName();
					String sringParamDefault = (param.getParamDefault() == null)?"\"\"" :param.getParamDefault();
					alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.CONSTANTTOKEN, paramAltStr, param.getParamDescription(),param.getParamSynoptic(), sringParamDefault, highLighPosition));
					foundMatch = true;
				}
				break;
			case DATA:
				//stock outputs
				foundMatch = addSuggsAsAlts(alternatives, parsedParam, highLighPosition, EditorLexerDelegate.HISTORICALDATA_TOKENS, "stock historical ");
				//ops outputs
				try {
					alternatives.addAll(addAllOpsAsAlts(parsedParam, position));
					foundMatch = true; // no exception was raised meaning match was found
					//break;
				} catch (Exception e) {
					LOGGER.debug("No matching op for " + parsedParam);
				}
				break;
			case MATYPE:
				foundMatch = addSuggsAsAlts(alternatives, parsedParam, highLighPosition, EditorIndsLexerDelegate.MATYPES_TOKENS, "moving average type ");
				break;
			case LIST:
				alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, "[]", param.getParamDescription(), param.getParamSynoptic(), "[]", highLighPosition));
				foundMatch = true;
				break;
			case OPREF:
				alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.SYNTAX, "$", param.getParamDescription(), param.getParamSynoptic(), "$", highLighPosition));
				foundMatch = true;
				break;
			}
			if (!foundMatch) throw new IllegalArgumentException();
		}
		return alternatives;
	}
}
