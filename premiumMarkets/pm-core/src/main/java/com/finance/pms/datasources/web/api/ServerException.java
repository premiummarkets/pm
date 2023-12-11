package com.finance.pms.datasources.web.api;

public class ServerException extends Exception {

	private static final long serialVersionUID = -3448281662428866449L;

	public ServerException() {
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(Throwable cause) {
		super(cause);
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
