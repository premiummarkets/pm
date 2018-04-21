package com.finance.pms.events.operations;

public interface StringableValue {
	
	//Default value as a string that would make sense as input to the String constructor (if any as this is valid only for values from leaf operations!)
	public String getValueAsString();

	public Object clone() throws CloneNotSupportedException;

}
