package com.finance.pms.events.calculation.antlr;

import java.util.LinkedList;

import org.antlr.runtime.RecognitionException;

public class SimpleErrorReporter extends MyErrorReporter {
	
	public SimpleErrorReporter(LinkedList<RecognitionExceptionHolder> exceptions, String parsedLine) {
		super(exceptions, parsedLine);
	}

	@Override
	public void report(RecognitionException e) {
		throw new ExitParsingException(e.getMessage(),e);
	}

}
