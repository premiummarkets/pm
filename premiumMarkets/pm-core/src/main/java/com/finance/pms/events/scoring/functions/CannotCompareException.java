package com.finance.pms.events.scoring.functions;

public class CannotCompareException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CannotCompareException(Exception e) {
		super(e);
	}

	public CannotCompareException(String string) {
		super(string);
	}

}
