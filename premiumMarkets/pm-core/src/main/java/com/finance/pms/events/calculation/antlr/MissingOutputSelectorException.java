package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public class MissingOutputSelectorException extends RecognitionException {

	private static final long serialVersionUID = -2331950534144641509L;
	
	private EditorOpDescr currentOp;
//	private String currentOutput;
	private String[] expected;

	public MissingOutputSelectorException(IntStream input, EditorOpDescr currentOp, Token unwantedOutputSelector, String... expected) {
		super(input);
		this.currentOp = currentOp;
		this.expected = expected;
//		this.currentOutput = currentOutputSelector;
		if (unwantedOutputSelector != null) {
			this.token = unwantedOutputSelector;
		}
	}

	public EditorOpDescr getCurrentOp() {
		return currentOp;
	}

	public String[] getExpected() {
		return expected;
	}

//	public String getCurrentOutput() {
//		return currentOutput;
//	}

}
