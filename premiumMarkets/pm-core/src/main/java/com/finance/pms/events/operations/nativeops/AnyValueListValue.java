package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;

@XmlRootElement
public class AnyValueListValue<T extends Value<?>> extends ListValue<T> implements Cloneable {
	
	private static MyLogger LOGGER = MyLogger.getLogger(AnyValueListValue.class);
	
	private List<T> listOfValues;

	public AnyValueListValue(List<T> listOfValues) {
		this.listOfValues = listOfValues;
	}

	public AnyValueListValue() {
		this.listOfValues = new ArrayList<>();
	}

	@Override
	public List<T> getValue(TargetStockInfo targetStock) {
		return listOfValues;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": value " + listOfValues.toString();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			@SuppressWarnings("unchecked")
			AnyValueListValue<T> clone = (AnyValueListValue<T>) super.clone();
			clone.listOfValues = new ArrayList<T>(this.listOfValues);
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
