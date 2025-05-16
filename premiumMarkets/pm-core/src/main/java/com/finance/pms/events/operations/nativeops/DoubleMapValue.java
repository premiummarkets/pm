package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;

public class DoubleMapValue extends NumericableMapValue implements MultiMapValue {

	private SortedMap<Date, Double> map;
	private Map<String, NumericableMapValue> additionalOutputs;
	private Map<String, Type> additionalOutputsTypes;

	public DoubleMapValue(SortedMap<Date, Double> map) {
		super();
		this.map = map;
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public DoubleMapValue() {
		super();
		this.map = new TreeMap<Date, Double>();
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {
		return map;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": size is " + map.size() + ((map.size() > 0)?", first key " + map.firstKey() + ", last key " + map.lastKey():"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			DoubleMapValue clone = (DoubleMapValue) super.clone();
			clone.map = (SortedMap<Date, Double>) ((TreeMap<Date, Double>) this.map).clone();
			clone.additionalOutputs = new HashMap<String, NumericableMapValue>();
			for (String outputKey : additionalOutputs.keySet()) {
				NumericableMapValue  addOutputClone = (NumericableMapValue) (additionalOutputs.get(outputKey)).clone();
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
	public Map<String, NumericableMapValue> getAdditionalOutputs() {
		return additionalOutputs;
	}

	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return additionalOutputsTypes;
	}

	@Override
	public List<String> getReferences() {
		return new ArrayList<String>(getAdditionalOutputs().keySet());
	}

	@Override
	public DoubleMapValue filtered(Date endDate) {
		return new DoubleMapValue() {

			@Override
			public SortedMap<Date, Double> getValue(TargetStockInfo targetStock) {
				return DoubleMapValue.this.getValue(targetStock).headMap(DateUtils.addDays(endDate, 1));
			}

			@Override
			public String toString() {
				return "[" + endDate + "] view of: " + DoubleMapValue.this.toString();
			}

			@Override
			public Object clone() {
				return DoubleMapValue.this.clone();
			}

			@Override
			public List<Date> getDateKeys() {
				return DoubleMapValue.this.getDateKeys().stream().filter(d -> !d.after(endDate)).collect(Collectors.toList());
			}

			@Override
			public Map<String, NumericableMapValue> getAdditionalOutputs() {
				return DoubleMapValue.this.getAdditionalOutputs().entrySet().stream()
						.collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().filtered(endDate)), HashMap::putAll);
			}

			@Override
			public Map<String, Type> getAdditionalOutputsTypes() {
				return DoubleMapValue.this.getAdditionalOutputsTypes();
			}

			@Override
			public List<String> getReferences() {
				return DoubleMapValue.this.getReferences();
			}

			@Override
			public DoubleMapValue filtered(Date endDate) {
				return DoubleMapValue.this.filtered(endDate);
			}
			
		};
	}

}
