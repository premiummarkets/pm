package com.finance.pms.events.operations;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
@XmlSeeAlso({NumberValue.class, DoubleMapValue.class, EventDataValue.class, StringValue.class})
public abstract class Value<T> {
	
	public Value() {
		super();
	}
	
	public abstract T getValue(TargetStockInfo targetStock);
	
	//Default value as a string that would make sense as input to the String constructor (if any as this is valid only for values from leaf operations!)
	public String getValueAsString() {
		throw new NotImplementedException();
	}
	
	public abstract String toString();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
