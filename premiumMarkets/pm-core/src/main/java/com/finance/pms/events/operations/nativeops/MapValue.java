package com.finance.pms.events.operations.nativeops;

import java.util.Date;
import java.util.SortedMap;

import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public abstract class MapValue<X> extends Value<SortedMap<Date, X>> {
	
	public abstract SortedMap<Date, X> getValue(TargetStockInfo targetStock);
	
}
