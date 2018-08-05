package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.events.operations.TargetStockInfo;

public class DoubleMapValue extends ChartableMapValue {

	private SortedMap<Date, Double> map;

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

	public SortedMap<Date, double[]> getDoubleArrayValue() {
		return map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> new double[] {e.getValue()}, (a,b) -> a, TreeMap::new));
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

	@Override
	public List<Date> getDateKeys() {
		return new ArrayList<>(map.keySet());
	}

}
