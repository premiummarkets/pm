package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;

public class BooleanMultiMapValue extends BooleanMapValue implements  MultiMapValue {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(BooleanMapValue.class);

	private Map<String, DoubleMapValue> additionalOutputs;
	private Map<String, Type> additionalOutputsTypes;
	
	public BooleanMultiMapValue() {
		super();
		additionalOutputs = new HashMap<String, DoubleMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}


	public BooleanMultiMapValue(Set<Date> keySet, boolean initValue) {
		super(keySet, initValue);
		additionalOutputs = new HashMap<String, DoubleMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}


	@Override
	public SortedMap<Date, Boolean> getValue(TargetStockInfo targetStock) {
		return map;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+map.size() + ((map.size() > 0)?", first key "+map.firstKey()+ ", last key "+map.lastKey():"");
	}

	@Override
	public Object clone() {
		try {
			BooleanMultiMapValue clone = (BooleanMultiMapValue) super.clone();
			clone.additionalOutputs = new HashMap<String, DoubleMapValue>();
			for (String outputKey : additionalOutputs.keySet()) {
				DoubleMapValue  addOutputClone = (DoubleMapValue) (additionalOutputs.get(outputKey)).clone();
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
	public Map<String, DoubleMapValue> getAdditionalOutputs() {
		return additionalOutputs;
	}


	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return additionalOutputsTypes;
	}
	
}
