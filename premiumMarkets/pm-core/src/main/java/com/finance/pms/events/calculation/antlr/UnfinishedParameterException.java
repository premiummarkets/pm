package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

import com.finance.pms.events.calculation.antlr.EditorOpDescr.ParamType;

public class UnfinishedParameterException extends RecognitionException {

	private static final long serialVersionUID = -8806304092460174723L;
	
	private ParamType paramType;
	private String paramString;
	private EditorOpDescr currentOp;

	public UnfinishedParameterException(IntStream input, EditorOpDescr currentOp, ParamType paramType, String paramString) {
		super(input);
		this.currentOp = currentOp;
		this.paramType = paramType;
		this.paramString = paramString;
	}

	public ParamType getParamType() {
		return paramType;
	}

	public String getParamString() {
		return paramString;
	}

	@Override
	public String toString() {
		return "UnfinishedParameterException [paramType=" + paramType + ", paramString=" + paramString + "]";
	}
	
	public EditorOpDescr getCurrentOp() {
		return currentOp;
	}
	
	@Override
	public String getMessage() {
		return toString();
	}


}
