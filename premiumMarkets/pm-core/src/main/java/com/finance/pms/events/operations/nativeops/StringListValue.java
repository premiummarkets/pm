package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;

public class StringListValue extends ListValue<String> {
	
	private static MyLogger LOGGER = MyLogger.getLogger(StringListValue.class);
	
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			StringListValue clone = (StringListValue) super.clone();
			clone.list = new ArrayList<String>(this.list);
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}


}
