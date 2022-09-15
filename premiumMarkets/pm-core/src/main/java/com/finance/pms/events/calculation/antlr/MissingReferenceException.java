package com.finance.pms.events.calculation.antlr;

public class MissingReferenceException extends RuntimeException {

	private static final long serialVersionUID = -1372591999412728683L;
	
	private String missingReference;

	public MissingReferenceException(String missingReference, String message) {
		super(message);
		this.missingReference = missingReference;
	}

	public String getMissingReference() {
		return missingReference;
	}
	
	

}
