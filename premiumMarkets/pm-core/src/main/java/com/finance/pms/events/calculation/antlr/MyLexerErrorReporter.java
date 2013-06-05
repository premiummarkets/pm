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
