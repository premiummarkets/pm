package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.finance.pms.events.operations.nativeops.DoubleMapValue;

public class MultiSelectorsValue extends DoubleMapValue {
	
	private Map<String, DoubleMapValue> selectorOutputs;
	private String calculationSelector;
	
	public MultiSelectorsValue(String calculationSelector) {
		super();
		this.calculationSelector = calculationSelector;
		this.selectorOutputs = new HashMap<String, DoubleMapValue>();
	}

	public MultiSelectorsValue(Map<String, DoubleMapValue> selectorOutputs, String calculationSelector) {
		super(selectorOutputs.get(calculationSelector).getValue(null));
		this.selectorOutputs = selectorOutputs;
		this.calculationSelector = calculationSelector;
	}
	
	public DoubleMapValue getValue(String selector) {
		return selectorOutputs.get(selector);
	}
	
	public Set<String> getSelectors() {
		return selectorOutputs.keySet();
	}

	@Override
	public String toString() {
		String ret = this.getClass().getSimpleName() + " with calculation selector : "+calculationSelector;
		for (String selector : selectorOutputs.keySet()) {
			SortedMap<Date, Double> selectorOutput = selectorOutputs.get(selector).getValue(null);
			ret = ret +";" + selector + " : size is "+selectorOutput.size() + ((selectorOutput.size() > 0)?", first key "+selectorOutput.firstKey()+ ", last key "+selectorOutput.lastKey():"");
		}
		return ret;
	}


	@Override
	public Object clone() {
		try {
			MultiSelectorsValue clone = (MultiSelectorsValue) super.clone();
			clone.selectorOutputs = new HashMap<String, DoubleMapValue>();
			for (String selector : selectorOutputs.keySet()) {
				clone.selectorOutputs.put(selector, (DoubleMapValue) selectorOutputs.get(selector).clone());
			}
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	public String getCalculationSelector() {
		return calculationSelector;
	}
	

}
