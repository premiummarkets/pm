package com.finance.pms.events.operations.nativeops;

import com.finance.pms.events.operations.TargetStockInfo;

public interface StringableValue {
	
	//Default value as a string that would make sense as input to the String constructor (if any as this is valid only for values from leaf operations!)
	String getValueAsString();

	Object getValue(TargetStockInfo targetStock);

	Object clone() throws CloneNotSupportedException;

}
