package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class BooleanMapValue extends Value<Map<Date, Boolean>> implements Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(BooleanMapValue.class);
	
	SortedMap<Date, Boolean> map;
	

	public BooleanMapValue() {
		map = new  TreeMap<Date, Boolean>();
	}

	public BooleanMapValue(Set<Date> keySet, boolean initValue) {
		map = new  TreeMap<Date, Boolean>();
		for (Date date : keySet) {
			map.put(date, initValue);
		}
	}

	@Override
	public SortedMap<Date, Boolean> getValue(TargetStockInfo targetStock) {
		return map;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+map.size() + ((map.size() > 0)?", first key "+map.firstKey()+ ", last key "+map.lastKey():"");
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			BooleanMapValue clone = (BooleanMapValue) super.clone();
			clone.map = (SortedMap<Date, Boolean>) ((TreeMap<Date, Boolean>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
