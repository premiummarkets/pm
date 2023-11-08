package com.finance.pms.events.operations.nativeops;

import java.util.Map;

import com.finance.pms.events.operations.Value;

public interface MultiValue {
	
	Map<String, ? extends Value<?>> getAdditionalOutputs();

}
