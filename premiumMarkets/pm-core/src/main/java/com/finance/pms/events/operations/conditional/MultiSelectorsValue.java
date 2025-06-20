/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.google.common.collect.Lists;

//Used as a cache for GraphableMapValue to avoid multiple recalculations when several outputs are available (via selectors) for the same operation.
public class MultiSelectorsValue extends DoubleMapValue {

	private Map<String, NumericableMapValue> selectorOutputs;
	private String calculationSelector;
	
	private MultiSelectorsValue() {
	}

	public MultiSelectorsValue(List<String> availableOutputSelectors, String outputSelector) {
		super(new TreeMap<>());
		this.selectorOutputs = availableOutputSelectors.stream().collect(Collectors.toMap(s -> s, s -> new DoubleMapValue()));
		this.calculationSelector = outputSelector;
	}

	public MultiSelectorsValue(Map<String, NumericableMapValue> selectorOutputs, String calculationSelector) {
		super(selectorOutputs.get(calculationSelector).getValue(null));
		this.selectorOutputs = selectorOutputs;
		this.calculationSelector = calculationSelector;
	}

	public NumericableMapValue getValue(String selector) {
		return selectorOutputs.get(selector);
	}

	public Set<String> getSelectors() {
		return selectorOutputs.keySet();
	}

	@Override
	public String toString() {
		String ret = this.getClass().getSimpleName() + " with calculation selector: " + calculationSelector;
		for (String selector : selectorOutputs.keySet()) {
			SortedMap<Date, Double> selectorOutput = selectorOutputs.get(selector).getValue(null);
			ret = ret + ";" + selector + ": size is " + selectorOutput.size() + ((selectorOutput.size() > 0)?", first key " + selectorOutput.firstKey() + ", last key " + selectorOutput.lastKey():"");
		}
		return ret;
	}

	@Override
	public Object clone() {
		try {
			MultiSelectorsValue clone = (MultiSelectorsValue) super.clone();
			clone.selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String selector : selectorOutputs.keySet()) {
				clone.selectorOutputs.put(selector, (NumericableMapValue) selectorOutputs.get(selector).clone());
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

	@Override
	public List<String> getReferences() {
		return Lists.asList(getCalculationSelector(), new String[] {});
	}
	

	@Override
	public MultiSelectorsValue filtered(Date endDate) {
		return new MultiSelectorsValue() {
			
			@Override
			public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {
				return MultiSelectorsValue.this.getValue(targetStockInfo).headMap(DateUtils.addDays(endDate, 1));
			}

			@Override
			public List<Date> getDateKeys() {
				return MultiSelectorsValue.this.getDateKeys().stream().filter(d -> !d.after(endDate)).collect(Collectors.toList());
			}

			@Override
			public Map<String, NumericableMapValue> getAdditionalOutputs() {
				return MultiSelectorsValue.this.getAdditionalOutputs().entrySet().stream()
						.collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().filtered(endDate)), HashMap::putAll);
			}

			@Override
			public Map<String, Type> getAdditionalOutputsTypes() {
				return MultiSelectorsValue.this.getAdditionalOutputsTypes();
			}

			@Override
			public MultiSelectorsValue getValue(String selector) {
				return (MultiSelectorsValue) MultiSelectorsValue.this.getValue(selector).filtered(endDate);
			}

			@Override
			public Set<String> getSelectors() {
				return MultiSelectorsValue.this.getSelectors();
			}

			@Override
			public String toString() {
				return "[" + endDate + "] view of: " + MultiSelectorsValue.this.toString();
			}

			@Override
			public Object clone() {
				return MultiSelectorsValue.this.clone();
			}

			@Override
			public String getCalculationSelector() {
				return MultiSelectorsValue.this.getCalculationSelector();
			}

			@Override
			public List<String> getReferences() {
				return MultiSelectorsValue.this.getReferences();
			}

			@Override
			public MultiSelectorsValue filtered(Date endDate) {
				return MultiSelectorsValue.this.filtered(endDate);
			}

		};
	}

}
