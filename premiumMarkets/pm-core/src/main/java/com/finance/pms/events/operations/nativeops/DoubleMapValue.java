package com.finance.pms.events.operations.nativeops;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class DoubleMapValue extends Value<SortedMap<Date, Double>>  implements Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(DoubleMapValue.class);
	
	SortedMap<Date, Double> map;

	public DoubleMapValue(SortedMap<Date, Double> map) {
		super();
		this.map = map;
	}

	public DoubleMapValue() {
		super();
		this.map = new TreeMap<Date, Double>();
	}

	public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {
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
			DoubleMapValue clone = (DoubleMapValue) super.clone();
			clone.map = (SortedMap<Date, Double>) ((TreeMap<Date, Double>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
