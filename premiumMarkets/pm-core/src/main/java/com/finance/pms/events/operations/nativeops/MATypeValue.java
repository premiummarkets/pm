package com.finance.pms.events.operations.nativeops;

import javax.xml.bind.annotation.XmlElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.tictactec.ta.lib.MAType;

public class MATypeValue extends Value<MAType> implements Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(MATypeValue.class);
	
	@XmlElement
	MAType maTypeValue;

	@SuppressWarnings("unused")
	private MATypeValue() {
		super();
	}
	
	public MATypeValue(String value) {
		super();
		this.maTypeValue = MAType.valueOf((""+value.charAt(0)).toUpperCase() + value.toLowerCase().substring(1));
	}
	

	@Override
	public MAType getValue(TargetStockInfo targetStock) {
		return maTypeValue;
	}
	
	

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : value "+maTypeValue;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
			MATypeValue clone = (MATypeValue) super.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public String getValueAsString() {
		return maTypeValue.name();
	}

}
