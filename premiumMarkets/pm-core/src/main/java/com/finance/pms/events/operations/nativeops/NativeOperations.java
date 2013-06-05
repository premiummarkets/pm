package com.finance.pms.events.operations.nativeops;

import java.util.Map;

import com.finance.pms.events.operations.Operation;

public interface NativeOperations {

	public abstract Map<String, Operation> getOperations();

	public abstract void addOperation(Operation operation);

}
