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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.scoring.functions.SmaTransformator;

public class SmaTransformOperation extends PMWithDataOperation {
	
	
private static MyLogger LOGGER = MyLogger.getLogger(SmaTransformOperation.class);
	
	public SmaTransformOperation() {
		super("smaTransformator", "Calculate SMA from one period to another", 
				new NumberOperation("Origin period"), new NumberOperation("Destination period"),
				new DoubleMapOperation("SMA data to transform"));
	}
	
	public SmaTransformOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer orgPeriog = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Integer destPeriod = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(2)).getValue(targetStock);
		
		//Calc
		NumericableMapValue ret = new DoubleMapValue();
		try {
			
			SmaTransformator<Double> transformator = new SmaTransformator<Double>(Double.class, orgPeriog, destPeriod);
			SortedMap<Date, Double> transfomed = transformator.transform(data);
			
			ret.getValue(targetStock).putAll(transfomed);
			
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " +e, e);
		}
		return ret;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return IntStream.range(0, 2)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					return numberOperand.getOrRunParameter(targetStock)
							.filter(v -> v instanceof NumberValue)
							.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
							.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift));
				})
				.reduce(0, (r, e) -> r + e);
	}


}
