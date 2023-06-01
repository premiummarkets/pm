package com.finance.pms.events.operations;

public class StackException extends RuntimeException {

	public StackException(String string, Exception e) {
		super(string, e);
	}

	private static final long serialVersionUID = -7116858626777672210L;

}
