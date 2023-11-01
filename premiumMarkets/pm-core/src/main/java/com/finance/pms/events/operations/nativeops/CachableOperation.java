package com.finance.pms.events.operations.nativeops;

public interface CachableOperation {
	
	//The shift left from the start date from which the data will naturally be calculated and provided.
	//FIXME this is a short cut: there should actually be a first pass to find out the longest shift before the calculation occurs.
	public Integer operationNaturalShift();
	
	public boolean isParameterDataSensitive();
}
