package com.finance.pms.events.operations.nativeops;

public interface CachableOperation {
	
	//The shift left from the start date from which the data will naturally be calculated and provided.
	public Integer operationNaturalShift();

}
