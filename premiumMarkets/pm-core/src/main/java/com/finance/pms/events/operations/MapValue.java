package com.finance.pms.events.operations;

import java.util.SortedMap;

public interface MapValue<X, Y> {
	
	public SortedMap<X, Y> getValue(TargetStockInfo targetStock);

}
