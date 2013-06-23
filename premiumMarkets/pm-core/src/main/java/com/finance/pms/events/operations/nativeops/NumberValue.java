package com.finance.pms.events.operations.nativeops;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class NumberValue extends Value<Number> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(NumberValue.class);
	
	@XmlElement
	Double numberValue;

	@SuppressWarnings("unused")
	private NumberValue() {
		super();
	}

	public NumberValue(Double value) {
		super();
		this.numberValue = value;
	}
	
	public NumberValue(String value) {
		super();
		this.numberValue = Double.valueOf(value);
	}
	
	public Number getValue(TargetStockInfo targetStockInfo) {
		return numberValue;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : value "+numberValue;
	}

	public Double getNumberValue() {
		return numberValue;
	}
	
	@Override
	public Object clone() {
		try {
			NumberValue clone = (NumberValue) super.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public String getValueAsString() {
		return numberValue.toString();
	}

}
