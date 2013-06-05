package com.finance.pms.events.operations;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
@XmlSeeAlso({DoubleValue.class, DoubleMapValue.class, EventDataValue.class, StringValue.class})
public abstract class Value<T> {
	
	public Value() {
		super();
	}
	
	public abstract T getValue(TargetStockInfo targetStock);
	
	public abstract String toString();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
