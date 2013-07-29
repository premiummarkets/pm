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
