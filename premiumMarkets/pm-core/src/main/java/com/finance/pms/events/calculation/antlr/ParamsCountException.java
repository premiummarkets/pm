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
