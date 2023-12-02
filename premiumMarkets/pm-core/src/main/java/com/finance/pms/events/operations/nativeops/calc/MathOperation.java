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

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;

@XmlRootElement
public class MathOperation extends PMWithDataOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(MathOperation.class);

	public MathOperation() {
		super("math", "Math calculus", new DoubleMapOperation());
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"exponential", "naturalLogarithm", "rocSinceStart"})));
	}

	public MathOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);

		try {

			TreeMap<Date, Double> collected = new TreeMap<>();
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("exponential")) {
				collected = data.keySet().stream().collect(Collectors.toMap(k -> k, k -> Math.exp(data.get(k)), (a, b) -> a, TreeMap<Date,Double>::new));
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("naturalLogarithm")) {
				collected = data.keySet().stream().collect(Collectors.toMap(k -> k, k -> Math.log(data.get(k)), (a, b) -> a, TreeMap<Date,Double>::new));
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("rocSinceStart")) {
				Double firstValue = data.get(data.firstKey());
				collected = data.keySet().stream().collect(Collectors.toMap(k -> k, k -> data.get(k)/firstValue, (a, b) -> a, TreeMap<Date,Double>::new));
			}
			return new DoubleMapValue(collected);

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new DoubleMapValue();

	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}


}
