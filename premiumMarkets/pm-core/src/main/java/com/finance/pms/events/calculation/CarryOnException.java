package com.finance.pms.events.calculation;

import com.finance.pms.datasources.shares.Stock;

public class CarryOnException extends NotEnoughDataException {
	
	private static final long serialVersionUID = -319788235705110130L;
	
	
	private Object returnedResult = null;
	private Object errorResult = null;

	public <T, X> CarryOnException(Stock stock, String message, T result, X error) {
		super(stock, message, null);
		this.returnedResult = result;
		this.errorResult = error;
	}

	@SuppressWarnings("unchecked")
	public <T> T getReturnedResult() {
		return (T) returnedResult;
	}
	
	@SuppressWarnings("unchecked")
	public <X> X getErrorResult() {
		return (X) errorResult;
	}

}
