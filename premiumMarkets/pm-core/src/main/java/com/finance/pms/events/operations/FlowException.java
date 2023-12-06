package com.finance.pms.events.operations;

public class FlowException extends RuntimeException implements AndThenException {

	private static final long serialVersionUID = -5834716594489292705L;

	public FlowException() {
	}

	public FlowException(String message) {
		super(message);
	}

	public FlowException(Throwable cause) {
		super(cause);
	}

	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
