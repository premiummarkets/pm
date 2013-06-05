package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventDataValue;

public class OperationValue<T> extends Value<T> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventDataValue.class);
	
	Operation operation;

	public OperationValue(Operation operation) {
		super();
		this.operation = operation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue(TargetStockInfo targetStock) {
		ArrayList<Value<T>> emptyInputs = new ArrayList<Value<T>>(); //parameterised operation has no input params
		return (T) operation.calculate(targetStock, emptyInputs);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : operation "+operation.toString();
	}
	
	@Override
	public Object clone() {
		try {
			@SuppressWarnings("unchecked")
			OperationValue<T> clone = (OperationValue<T>) super.clone();
			clone.operation = (Operation) this.operation.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}


}
