package com.finance.pms.events.operations.nativeops;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class DoubleValue extends Value<Number> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(DoubleValue.class);
	
	@XmlElement
	Double numberValue;

	@SuppressWarnings("unused")
	private DoubleValue() {
		super();
	}

	public DoubleValue(Double value) {
		super();
		this.numberValue = value;
	}
	
	public DoubleValue(String value) {
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
			DoubleValue clone = (DoubleValue) super.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
