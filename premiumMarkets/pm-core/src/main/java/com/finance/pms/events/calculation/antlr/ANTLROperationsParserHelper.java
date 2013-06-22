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
	
	static MyLogger LOGGER = MyLogger.getLogger(ANTLROperationsParserHelper.class);
	
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
			OpsLexerDelegate editorLexerDelegate = new EditorOpsLexerDelegate(in, state,  nativeOpEditorDescrs, userCurrentOpEditorDescrs);
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
			
			LOGGER.debug("Exception stack :"+ exceptions);
			
			System.out.println("---------------------------------------------------");
			for (RecognitionExceptionHolder exceptionHolder : exceptions) {
					System.out.println(parsedLine+"\\,"+exceptionHolder.toCsv());
			}
			System.out.println("---------------------------------------------------");
			
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
			
			
			SortedMap<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList = new TreeMap<AltType, SortedMap<Integer,LinkedList<Alternative>>>(new Comparator<AltType>() {

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
					if (exception.c != Token.EOF && !EditorOpsLexerDelegate.SYNTAX_TOKENS.contains((exception.c+"")) && exception.token == null) {

						if (startTPosition == -1) {
							filterPosition = new int[]{exception.line, exception.charPositionInLine};
							startTPosition = translatePositionToCaret(parsedLine, filterPosition[0], filterPosition[1]);
							suggFilter = ""+(char)exception.c;
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
				if (exception.c == Token.EOF) { 
					if (exception instanceof NoViableAltException) {//NoViableAltException + EOF : FILTER
						if (exceptionHolder.getRuleStack().size() == 2) {//No operation yet, first typing : fill up all ops
							fillUpOps = true;
							deleteFilter = false;
						}
					} 
					else if (exception instanceof MismatchedTokenException) {
						int[] position = new int[]{exception.line, exception.charPositionInLine-1};
						if (exceptionHolder.getExpectedToken() != null && exceptionHolder.getExpectedToken().equals(")")) {// ")"
							if (exceptionHolder.getNeedsClosing() != null) {
								altPrioListForTokType(priorityList, AltType.SUGGESTION, 10)
								.add(new Alternative(
										AltType.SUGGESTION,TokenType.SYNTAX, exceptionHolder.getExpectedToken()+"" , "Syntax suggestion", 
										"Insert a '"+exceptionHolder.getExpectedToken()+"' to close "+exceptionHolder.getNeedsClosing().getName()+" statement.", null, position));
								if (exceptionHolder.getNeedsClosing().undeterministicParamCount()) {
									altPrioListForTokType(priorityList, AltType.SUGGESTION, 10).add(new Alternative(AltType.SUGGESTION,TokenType.SYNTAX, ",", "Syntax suggestion", "Insert a comma to add up arguments to "+exceptionHolder.getNeedsClosing().getName(), null, position));
								}
							}
						} else {//Other syntax
							altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION,TokenType.SYNTAX, exceptionHolder.getExpectedToken() , "Syntax suggestion", "Insert a '"+exceptionHolder.getExpectedToken()+"'", null, position));
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
						}
					} 
					else if  (exception instanceof InvalidOperationException)  {
						//String currentOpStr =  ((InvalidOperationException) exception).getCurrentToken();
						String currentOpStr =  ((InvalidOperationException) exception).token.getText();
						currentOpStr = (currentOpStr !=null)?currentOpStr: " ";
						int[] position = new int[]{exception.line, exception.charPositionInLine + currentOpStr.length()-1-1};
						altPrioListForTokType(priorityList, AltType.DELETE, 5).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry",  " Invalid operation eof", null, position));
					}
					else if (exception instanceof MissingOutputSelectorException) {
						List<Alternative> alternatives = new ArrayList<Alternative>();
						MissingOutputSelectorException missingOutputSelectorException = (MissingOutputSelectorException) exception;
						String[] expected = missingOutputSelectorException.getExpected();
						currentOp = missingOutputSelectorException.getCurrentOp();
						//String currentOutput = missingOutputSelectorException.getCurrentOutput();
						String currentOutput = missingOutputSelectorException.token.getText();
						currentOutput = (currentOutput == null || currentOutput.equals("<EOF>"))?"":currentOutput;
						if (currentOp == null) {
							int[] position = new int[]{exception.line, exception.charPositionInLine -1};
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry",  "Invalid entry", null, position));
						} else
						if (expected != null && expected.length > 0) {
							
							int[] position = new int[]{exception.line, exception.charPositionInLine -1};
							Boolean foundMatching =addSuggsAsAlts(alternatives, currentOutput , position, "Output selector option required for "+currentOp.getName()+" : ", expected);
							if (!foundMatching) {
								//int[] deletePosition = new int[]{exception.line,exception.charPositionInLine+currentOutput.length()-1};
								altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry", "Condition on historical output excepted.", null, position));
							}
							if (!alternatives.isEmpty()) altPrioListForTokType(priorityList, AltType.SUGGESTION, 0).addAll(alternatives);
						} 
						else {
							int[] position = new int[]{exception.line, exception.charPositionInLine -1};
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry",  currentOp.getName() + " doesn't support output selectors", null, position));
						}
					}
					else {
						LOGGER.warn("Unmatched exception for suggestions "+exceptionHolder);
					}
					
				//Errors
				} else {
					if  (exception instanceof InvalidOperationException)  {
						//String currentOpStr =  ((InvalidOperationException) exception).getCurrentToken();
						String currentOpStr =  ((InvalidOperationException) exception).token.getText();
						currentOpStr = (currentOpStr !=null)?currentOpStr: " ";
						int[] position = new int[]{exception.line, exception.charPositionInLine - (currentOpStr.length() + 1)};
						altPrioListForTokType(priorityList, AltType.DELETE, 5).add(new Alternative(AltType.DELETE,TokenType.DELETE, " ", "Invalid entry", " Invalid operation", null, position));
					}
					else if (exception instanceof NoViableAltException) {///NoViableAltException + !EOF  : DELETE FILTER (-SYNTAX)deleteFilter
						//Nothing
					} 
					else if (exception instanceof ParamsCountException) {
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
							altPrioListForTokType(
									priorityList, AltType.SUGGESTION, 0).add(new Alternative(AltType.SUGGESTION,TokenType.SYNTAX, ",",  "Syntax suggestion", "Insert a Comma to add up arguments to "+currentOp.getName(), null, commaPosition));	
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
						//String currentOutput = missingOutputSelectorException.getCurrentOutput();
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
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry","Invalid output selector for "+ currentOp.getName(), null, position));
						} else {
							altPrioListForTokType(priorityList, AltType.DELETE, 0).add(new Alternative(AltType.DELETE,TokenType.DELETE, currentOutput, "Invalid entry",  currentOp.getName() + " doesn't support output selectors", null, position));
						}
	
					}
					else {
						LOGGER.warn("Unmatched exception for errors "+exceptionHolder);
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
				} catch (IllegalArgumentException e) {
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
			LOGGER.debug("Next token alt : "+nextToken);
			
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

	protected List<Alternative> addParamsAsAlts(EditorOpDescr currentOp, int commaPosition, String currentTyping, int[] position)  throws IllegalArgumentException  {

		int[] highLighPosition = new int[]{position[0],position[1]};
		
		List<Alternative> alternatives = new ArrayList<Alternative>();
		if ((currentOp.getParams().size() > commaPosition || currentOp.undeterministicParamCount())) {
			
			Param param = (currentOp.undeterministicParamCount())?currentOp.getParams().get(0):currentOp.getParams().get(commaPosition);
			String parsedParam =  currentOp.getParsedParma(commaPosition);
			
			//parsedParam = (parsedParam != null && !parsedParam.isEmpty())?parsedParam:currentTyping;
			parsedParam = (currentTyping != null && !currentTyping.isEmpty())?currentTyping:parsedParam;
			parsedParam = (parsedParam == null)?"":parsedParam;
			
			Boolean foundMatch = false;
			switch (param.getParamType()) {
			case CONSTANT:
				if (!parsedParam.isEmpty()) {
					Double.valueOf(parsedParam);//Test the typing as double
					foundMatch = true;
				} else {
					String paramAltStr = (param.getParamName() == null)?"Number Argument":param.getParamName();
					alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.CONSTANT, paramAltStr, param.getParamDescription(),param.getParamSynoptic(), param.getParamDefault(), highLighPosition));
					foundMatch = true;
				}
				break;
			case DOUBLEMAP:
				//stock outputs
				foundMatch = addSuggsAsAlts(alternatives, parsedParam, highLighPosition, EditorLexerDelegate.HISTORICALDATA_TOKENS, "stock historical ");
				//ops outputs
				try {
					alternatives.addAll(addAllOpsAsAlts(parsedParam, position));
					foundMatch = true; // no exception was raised meaning match was found
					//break;
				} catch (Exception e) {
					LOGGER.debug("No matching op for "+parsedParam);
				}
				break;
			case MATYPE: //TODO Generalise to String parameter
				foundMatch = addSuggsAsAlts(alternatives, parsedParam, highLighPosition, EditorIndsLexerDelegate.MATYPES_TOKENS, "moving average type ");
				break;
			}
			if (!foundMatch) throw new IllegalArgumentException();
		}
		return alternatives;
	}
}
