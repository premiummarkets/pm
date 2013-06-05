package com.finance.pms.events.calculation.antlr;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class InvalidOperationException extends RecognitionException {

	private static final long serialVersionUID = 7946579634840283318L;
	
	private List<Object> params;

	public InvalidOperationException(TokenStream input, Token token, List<Object> params) {
		super(input);
		this.token= token;
		this.params = params;
	}

	public List<Object> getParams() {
		return params;
	}
	

}
