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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.LeftShifter;

public class LeftShifterOperation extends PMWithDataOperation implements LaggingOperation {


	private static final int LEFT_SHIFT_AMOUNT_IDX = 0;
	private static MyLogger LOGGER = MyLogger.getLogger(LeftShifterOperation.class);

	public LeftShifterOperation() {
		super("leftShifter", "Left shift", new NumberOperation("left shift span"), new DoubleMapOperation("Data to shift"));
	}

	public LeftShifterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartDateShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int leftShiftSpan = ((NumberValue)inputs.get(LEFT_SHIFT_AMOUNT_IDX)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);

		//Calc
		NumericableMapValue ret = new DoubleMapValue();
		try {

			LeftShifter<Double> leftShifter = new LeftShifter<Double>(targetStock.getStock(), getRequiredStockData(), leftShiftSpan, true);
			SortedMap<Date, Double> shifted = leftShifter.shift(data);
			
			//NaN complement
			data.tailMap(shifted.lastKey()).keySet().stream().forEach(d -> {if (!d.equals(shifted.lastKey())) shifted.put(d, Double.NaN);});
			
			ret.getValue(targetStock).putAll(shifted);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + ": " + e, e);
		}
		return ret;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return ((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue() + 1; // + one to avoid all NaNs in the shifted result.
	}
	
	@Override
	public String toFormulaeShort() {
		String thisShortName = "ls";
		String shift = ((StringableValue) getOperands().get(0).getParameter()).getValueAsString();
		List<Operation> ops = getOperands().subList(1, getOperands().size());
		String opsFormulaeShort = toFormulaeShort(ops);
		return thisShortName + "_" + shift + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

	@Override
	public int rightLagAmount() {
		return ((NumberValue) getOperands().get(LEFT_SHIFT_AMOUNT_IDX).getParameter()).getValue(null).intValue();
	}

}
