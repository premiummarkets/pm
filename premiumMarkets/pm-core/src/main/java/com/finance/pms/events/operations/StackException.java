package com.finance.pms.events.operations;

public class StackException extends RuntimeException {
	
	private static final long serialVersionUID = -7116858626777672210L;
	
	public StackException(Exception e) {
		super(e);
	}

	public StackException(String string, Exception e) {
		super(string, e);
	}

}
