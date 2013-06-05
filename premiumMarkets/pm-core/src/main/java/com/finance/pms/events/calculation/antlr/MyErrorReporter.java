package com.finance.pms.events.calculation.antlr;

import java.util.LinkedList;

import org.antlr.runtime.RecognitionException;

public abstract class MyErrorReporter {

	protected LinkedList<RecognitionExceptionHolder> exceptions;
	protected String parsedLine;


	public MyErrorReporter(LinkedList<RecognitionExceptionHolder> exceptions, String parsedLine) {
		super();
		this.parsedLine = parsedLine;
		this.exceptions = exceptions;
	}

	public abstract void report(RecognitionException e);
	
	public LinkedList<RecognitionExceptionHolder> getNextPotentialAlternatives() {
		return exceptions;
	}

}
