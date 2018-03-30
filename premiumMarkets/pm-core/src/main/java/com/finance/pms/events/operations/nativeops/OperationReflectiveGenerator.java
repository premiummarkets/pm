package com.finance.pms.events.operations.nativeops;

import java.util.Map;

import com.finance.pms.events.operations.Operation;

public interface OperationReflectiveGenerator {

	Map<String, ? extends Operation> generate();

}
