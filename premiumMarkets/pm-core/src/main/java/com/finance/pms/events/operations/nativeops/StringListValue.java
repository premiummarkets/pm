package com.finance.pms.events.operations.nativeops;

import java.util.List;

import com.finance.pms.events.operations.TargetStockInfo;

public class StringListValue extends ListValue<String> {
	
	List<String> list;

	public StringListValue(List<String> list) {
		super();
		this.list = list;
	}

	@Override
	public List<String> getValue(TargetStockInfo targetStock) {
		return list;
	}

	@Override
	public String toString() {
		return list.toString();
	}

}
