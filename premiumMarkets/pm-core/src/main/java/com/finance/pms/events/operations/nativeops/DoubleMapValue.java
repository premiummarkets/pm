package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;

public class DoubleMapValue extends UnarableMapValue implements MultiMapValue {

	private SortedMap<Date, Double> map;
	private Map<String, UnarableMapValue> additionalOutputs;
	private Map<String, Type> additionalOutputsTypes;

	public DoubleMapValue(SortedMap<Date, Double> map) {
		super();
		this.map = map;
		additionalOutputs = new HashMap<String, UnarableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public DoubleMapValue() {
		super();
		this.map = new TreeMap<Date, Double>();
		additionalOutputs = new HashMap<String, UnarableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
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
			clone.additionalOutputs = new HashMap<String, UnarableMapValue>();
			for (String outputKey : additionalOutputs.keySet()) {
				UnarableMapValue  addOutputClone = (UnarableMapValue) (additionalOutputs.get(outputKey)).clone();
				clone.additionalOutputs.put(outputKey, addOutputClone);
				clone.additionalOutputsTypes.put(outputKey, additionalOutputsTypes.get(outputKey));
			}
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

	@Override
	public Map<String, UnarableMapValue> getAdditionalOutputs() {
		return additionalOutputs;
	}

	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return additionalOutputsTypes;
	}

}
