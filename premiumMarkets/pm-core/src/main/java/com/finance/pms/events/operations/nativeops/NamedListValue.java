package com.finance.pms.events.operations.nativeops;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;

public class NamedListValue extends Value<Object> implements MultiValue {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NamedListValue.class);
	
	Map<String, ? extends Object> values;
	
	public NamedListValue(Map<String, ? extends Object> values) {
		super();
		this.values = values;
	}

	public NamedListValue() {
		this.values = new HashMap<>();
	}

	@Override
	public Map<String, ? extends Value<?>> getAdditionalOutputs() {
		return values.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
			Object value = e.getValue();
			if (value instanceof Number) return new NumberValue(((Number) value).doubleValue());
			return new StringValue(value.toString());
		}));
	}

	@Override
	public Object getValue(TargetStockInfo targetStock) {
		return values;
	}

	@Override
	public NamedListValue filterToParentRequirements(TargetStockInfo targetStock, int startShift, Operation parent) {
		return this;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + values;
	}

	@Override
	public Object clone() {
		try {
			NamedListValue clone = (NamedListValue) super.clone();
			clone.values = values.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue())); //FIXME Value.clone()??
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}


}