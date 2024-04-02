package com.finance.pms.events.operations.nativeops;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.google.gson.Gson;

@XmlRootElement
public class NamedListValue extends Value<Object> implements MultiValue, StringableValue {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NamedListValue.class);
	
	@XmlElement
	Map<String, ? extends Object> values;
	
	public NamedListValue(Map<String, ? extends Object> values) {
		super();
		this.values = values;
	}

	public NamedListValue() {
		this.values = new HashMap<>();
	}
	
	public NamedListValue(String value) {
		super();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> fromJson = new Gson().fromJson(value, HashMap.class);
		this.values = fromJson;
	}

	@Override
	public Map<String, ? extends Value<?>> getAdditionalOutputs() {
		return values.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
			Object value = e.getValue();
			if (value instanceof Number) return new NumberValue(((Number) value).doubleValue());
			if (e instanceof StringValue && ((StringValue) e).isBoolean()) return new BooleanValue(Boolean.valueOf(value.toString()));
			return new StringValue(value.toString());
		}));
	}

	@Override
	public Object getValue(TargetStockInfo targetStock) {
		return values;
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

	@Override
	public String getAsStringable() {
		return new Gson().toJson(values, HashMap.class);
	}


}
