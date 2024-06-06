package com.finance.pms.events.operations.nativeops;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@XmlRootElement
public class NamedListValue extends Value<Object> implements MultiValue, StringableValue {
	
	private static final long serialVersionUID = 5198078574592679752L;

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
		 GsonBuilder gsonBuilder = new GsonBuilder();
		 	//Exp to fraction representation
		    gsonBuilder.registerTypeAdapter(Double.class,  new JsonSerializer<Double>() {
		        @Override
		        public JsonElement serialize(final Double src, final Type typeOfSrc, final JsonSerializationContext context) {
		            BigDecimal value = BigDecimal.valueOf(src);

		            return new JsonPrimitive(value);
		        }
		    });
		    //.replaceAll("true", "\"TRUE\"").replaceAll("false", "\"FALSE\"");
		    gsonBuilder.registerTypeAdapter(Boolean.class,  new JsonSerializer<Boolean>() {
		        @Override
		        public JsonElement serialize(final Boolean src, final Type typeOfSrc, final JsonSerializationContext context) {
		            String value = src.toString().toUpperCase();
		            return new JsonPrimitive(value);
		        }
		    });

		Gson gson = gsonBuilder.create();
		return gson.toJson(values, HashMap.class); 
	}

	@Override
	public String getAsPrettyStringable() {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();
		return gson.toJson(values, HashMap.class).replaceAll("true", "\"TRUE\"").replaceAll("false", "\"FALSE\"");
	}
	
	


}
