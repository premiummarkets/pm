package com.finance.pms.events.operations.nativeops;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TimedMapValue;
import com.finance.pms.events.operations.Value;

public class DoubleArrayMapValue extends Value<java.util.SortedMap<Date, double[]>> implements TimedMapValue<double[]>, Cloneable {

	protected static MyLogger LOGGER = MyLogger.getLogger(DoubleArrayMapValue.class);

	private SortedMap<Date, double[]> map;

	public DoubleArrayMapValue(SortedMap<Date, double[]> map) {
		super();
		this.map = map;
	}

	public DoubleArrayMapValue() {
		super();
		this.map = new TreeMap<Date, double[]>();
	}

	@Override
	public SortedMap<Date, double[]> getValue(TargetStockInfo targetStock) {
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
			DoubleArrayMapValue clone = (DoubleArrayMapValue) super.clone();
			clone.map = (SortedMap<Date, double[]>) ((TreeMap<Date, double[]>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
