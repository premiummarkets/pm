package com.finance.pms.events.operations.nativeops;

import java.util.Map;

public interface MultiValue {
	
	Map<String, ? extends Value<?>> getAdditionalOutputs();

}
