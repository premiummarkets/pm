package com.finance.pms.datasources.web.formaters;

public class StopParseEmptyException extends StopParseException {
	
	private static final long serialVersionUID = 8533243625146708892L;
	
	private String message;
	private String reason;

	public StopParseEmptyException(String message, String reason) {
		super(null);
		this.message = message;
		this.reason = reason;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getReason() {
		return reason;
	}
	
	


}
