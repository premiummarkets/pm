package com.finance.pms.datasources;

public class EventRefreshException extends RuntimeException {

	private static final long serialVersionUID = 5182777977058348465L;

	public EventRefreshException() {
		super();
	}

	public EventRefreshException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventRefreshException(String message) {
		super(message);
	}

	public EventRefreshException(Throwable cause) {
		super(cause);
	}
	
	

}
