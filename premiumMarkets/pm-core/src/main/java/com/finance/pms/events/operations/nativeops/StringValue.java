package com.finance.pms.events.operations.nativeops;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class StringValue extends Value<String> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(StringValue.class);
	
	@XmlElement
	String stringValue;
	
	@SuppressWarnings("unused")
	private StringValue() {
		super();
	}

	public StringValue(String value) {
		super();
		this.stringValue = value.replaceAll("\"", "");
	}


	@Override
	public String getValue(TargetStockInfo targetStock) {
		return stringValue;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : value \""+stringValue+"\"";
	}
	
	@Override
	public Object clone() {
		try {
			StringValue clone = (StringValue) super.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public String getValueAsString() {
		return "\""+stringValue+"\"";
	}

}
