package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;

public class NumberListValue extends ListValue<Number> {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NumberListValue.class);
	
	List<Number> list;
	
	public NumberListValue(List<Number> list) {
		super();
		this.list = list;
	}

	@Override
	public List<Number> getValue(TargetStockInfo targetStock) {
		return list;
	}

	@Override
	public String toString() {
		return list.toString();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			NumberListValue clone = (NumberListValue) super.clone();
			clone.list = new ArrayList<Number>(this.list);
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}


}
