package com.finance.pms.events.operations.conditional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class BooleanValue extends Value<Boolean> implements Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(BooleanValue.class);
	
	Boolean value;

	public BooleanValue(Boolean value) {
		super();
		this.value = value;
	}
	
	public BooleanValue(String value) {
		this.value = Boolean.valueOf(value);
	}

	@Override
	public Boolean getValue(TargetStockInfo targetStock) {
		return value;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : value "+value;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}
	

}
