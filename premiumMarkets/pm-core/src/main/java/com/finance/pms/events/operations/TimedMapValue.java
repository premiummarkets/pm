package com.finance.pms.events.operations;

import java.util.Date;
import java.util.SortedMap;

public interface TimedMapValue<Y> extends MapValue<Date, Y> {
	
	public SortedMap<Date, Y> getValue(TargetStockInfo targetStock);

}
