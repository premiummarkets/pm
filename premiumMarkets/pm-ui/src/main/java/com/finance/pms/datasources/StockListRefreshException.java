package com.finance.pms.datasources;

public class StockListRefreshException extends RuntimeException {

	private static final long serialVersionUID = 5182777977058348465L;

	public StockListRefreshException() {
		super();
	}

	public StockListRefreshException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockListRefreshException(String message) {
		super(message);
	}

	public StockListRefreshException(Throwable cause) {
		super(cause);
	}
	
	

}
