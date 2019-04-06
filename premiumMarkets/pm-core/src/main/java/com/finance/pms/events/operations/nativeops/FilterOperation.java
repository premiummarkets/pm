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
package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

//TODO Refactor Condition<T> to use it as a parameter instead of using outputSelectors
//TODO Also this would have to take any kind of parameter dependent on the chosen Condition<T> ..
public class FilterOperation extends DoubleMapOperation {

	public FilterOperation() {
		this("filter_", "Filter",
				new DoubleMapOperation("data"), 
				new NumberOperation("number", "constant", "Filter input", new NumberValue(0.0)));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"eq", "gt", "lt"})));
	}

	public FilterOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		Double constant = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();

		BiFunction<Double, Double, Boolean> func = (t,u) -> t == u;

		String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
		if (outputSelector != null && outputSelector.equalsIgnoreCase("equals")) {
			func = (t,u) -> t == u;
		}
		else if (outputSelector != null && outputSelector.equalsIgnoreCase("gt")) {
			func = (t,u) -> t > u;
		}
		else if (outputSelector != null && outputSelector.equalsIgnoreCase("lt")) {
			func = (t,u) -> t < u;
		}

		SortedMap<Date, Double> result = mapFilter(data, constant, func);
		return new DoubleMapValue(result);
	}

	private SortedMap<Date, Double> mapFilter(SortedMap<Date, Double> data, Double constant, BiFunction<Double, Double, Boolean> func) {
		List<Date> fullKeySet = new ArrayList<>(data.keySet());
		List<Date> filter = fullKeySet.stream()
				.filter(k -> func.apply(data.get(k), constant))
				.collect(Collectors.toList());
		SortedMap<Date, Double> result = fullKeySet.stream()
				.collect(Collectors.toMap(k -> k, k -> filter.contains(k)?data.get(k):Double.NaN, (a, b) -> a, TreeMap::new));
		return result;
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}

}
