package com.finance.pms.events.operations;

import java.util.List;

public class StackException extends RuntimeException {
	
	private static final long serialVersionUID = -7116858626777672210L;
	
	public StackException(Exception e) {
		super(e);
	}

	public StackException(String string, List<StackElement> callStack, Exception e) {
		super(string + "\n" + StackElement.toString(callStack), e);
	}

}
