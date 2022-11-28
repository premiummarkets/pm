package com.finance.pms.events.operations.nativeops;

import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public abstract class ListValue<X> extends Value<List<X>> {
	
	@Override
	public ListValue<X> filterToParentRequirements(TargetStockInfo targetStock, int startShift, Operation parent) {
		return this;
	}

}
