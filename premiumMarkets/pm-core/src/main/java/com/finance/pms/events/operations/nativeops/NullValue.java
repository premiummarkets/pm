package com.finance.pms.events.operations.nativeops;

import com.finance.pms.events.operations.TargetStockInfo;

public class NullValue extends Value<Object> {

	public NullValue() {
	}

	@Override
	public Object getValue(TargetStockInfo targetStock) {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

}
