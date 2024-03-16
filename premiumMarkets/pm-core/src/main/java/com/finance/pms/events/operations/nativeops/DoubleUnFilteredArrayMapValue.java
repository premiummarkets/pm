package com.finance.pms.events.operations.nativeops;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;

public class DoubleUnFilteredArrayMapValue extends DoubleArrayMapValue {

	public DoubleUnFilteredArrayMapValue() {
		super();
	}

	public DoubleUnFilteredArrayMapValue(SortedMap<Date, double[]> map, List<String> columnsReferences, int mainIdx) {
		super(map, columnsReferences, mainIdx);
	}
	
	
	public DoubleUnFilteredArrayMapValue(DoubleArrayMapValue mapValue) {
		super(mapValue.getDoubleArrayValue(), mapValue.getColumnsReferences(), 0);
	}
	
	@Override
	public DoubleArrayMapValue filterToParentRequirements(TargetStockInfo targetStock, int startShift, Operation parent) {
		return this;
	}

}
