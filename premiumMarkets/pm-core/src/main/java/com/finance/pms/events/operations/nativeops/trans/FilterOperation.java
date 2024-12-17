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
package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

//TODO Refactor Condition<T> to use it as a parameter instead of using outputSelectors
//TODO Also this would have to take any kind of parameter dependent on the chosen Condition<T> ..
public class FilterOperation extends DoubleMapOperation {

	public FilterOperation() {
		this("filter_", "Filter",
				new DoubleMapOperation("filtered series (given)"),
				new DoubleMapOperation("filtering series on which applies the filter (where x eq/gt/lt .. than threshold)"),
				new NumberOperation("number", "filterShade", "Filter shade value", new NumberValue(Double.NaN)),
				new NumberOperation("number", "filterThreshold", "Filter threshold", new NumberValue(0.0)),
				new StringOperation("boolean", "fillGapsWithShade", "When TRUE the data points gaps in the filtered series are filled with the shade to match the filtering series in data points.", new StringValue("TRUE")));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"eq", "gt", "lt"})));
	}

	public FilterOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		SortedMap<Date, Double> filteredData = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		SortedMap<Date, Double> filteringData = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		Double shade = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
		Double threshold = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue();
		Boolean fillGapsWithShade = Boolean.valueOf(((StringValue) inputs.get(4)).getValue(targetStock));

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

		List<Date> filterDates = mapFilter(filteringData, threshold, func);
		SortedMap<Date, Double> result;
		if (fillGapsWithShade) {
			result = filterDates.stream()
					.collect(Collectors.toMap(k -> k, k -> (filteredData.keySet().contains(k))?filteredData.get(k):shade, (a, b) -> a, TreeMap<Date,Double>::new));
		} else {
			result = filteredData.keySet().stream()
					.collect(Collectors.toMap(k -> k, k -> (filterDates.contains(k))?filteredData.get(k):shade, (a, b) -> a, TreeMap<Date,Double>::new));
		}
		
		return new DoubleMapValue(result);
	}

	private List<Date> mapFilter(SortedMap<Date, Double> data, Double constant, BiFunction<Double, Double, Boolean> func) {
		List<Date> fullKeySet = new ArrayList<>(data.keySet());
		List<Date> filter = fullKeySet.stream()
				.filter(k -> func.apply(data.get(k), constant))
				.collect(Collectors.toList());
		return filter;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

}
