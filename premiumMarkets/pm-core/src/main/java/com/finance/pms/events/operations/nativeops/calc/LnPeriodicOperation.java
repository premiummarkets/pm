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
package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public class LnPeriodicOperation extends DoubleMapOperation {

	public LnPeriodicOperation() {
		this("periodicLn_", "ln(n/(n-p)) ~ House Trend in base 'e'", 
				new NumberOperation("period", "period", "Period", new NumberValue(1.0)), 
				new DoubleMapOperation("data"));
	}

	public LnPeriodicOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer lnPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);

		List<Date> fullKeySet = new ArrayList<>(data.keySet());
		List<Double> fullValuesSet = new ArrayList<>(data.values());

		SortedMap<Date, Double> result = IntStream
				.range(lnPeriod, data.size())
				.mapToObj(i -> i)
				.collect(Collectors.toMap(i -> fullKeySet.get(i), i -> Math.log(fullValuesSet.get(i)/fullValuesSet.get(i-lnPeriod)), (a, b) -> a, TreeMap::new));

		return new DoubleMapValue(result);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {		
		return IntStream.range(0, 1)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					return numberOperand.getOrRunParameter(targetStock, thisCallStack)
							.filter(v -> v instanceof NumberValue)
							.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
							.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisCallStack, thisParentStartShift));
				})
				.reduce(0, (r, e) -> r + e);
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		String thisShortName = "pLn";
		Operation operand0 = getOperands().get(0);
		String shift = ((StringableValue) operand0.getOrRunParameter(targetStock, thisCallStack)
								.orElse(new StringValue(operand0.toFormulaeShort(targetStock, thisCallStack)))).getAsStringable();
		List<Operation> ops = getOperands().subList(1, getOperands().size());
		String opsFormulaeShort = toFormulaeShort(targetStock, thisCallStack, ops);
		return thisShortName + "_" + shift + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}	

}
