package com.finance.pms.datasources;

public class QuotatationRefreshException extends RuntimeException {

	private static final long serialVersionUID = 5182777977058348465L;

	public QuotatationRefreshException() {
		super();
	}

	public QuotatationRefreshException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuotatationRefreshException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuotatationRefreshException(String message) {
		super(message);
	}

	public QuotatationRefreshException(Throwable cause) {
		super(cause);
	}
	
	

}
