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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.UnarableMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.scoring.functions.LeftShifter;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 * 'spanning'
 * 
 */
public class ReverseCondition extends Condition<Boolean>  implements UnaryCondition {


	private static final int MAIN_POSITION = 3;

	private ReverseCondition() {
		super("historical data reverse", "True when a time series reverses up or down", 
				new NumberOperation("direction"), new NumberOperation("change ratio"), new NumberOperation("dates comparison span"), new DoubleMapOperation("historical data input"));
	}

	public ReverseCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}

	@Override

	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double direction = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double changeRatio = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Integer spanningShift = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((UnarableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);

		if (spanningShift == 0) spanningShift = 1;
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-spanningShift.intValue(), false, false);
		SortedMap<Date, Double> rightShiftedData = rightShifter.shift(data);

		BooleanMapValue outputs = new  BooleanMapValue();
		Boolean isUp = null;
		Boolean wasDown = null;
		Boolean isDown = null;
		Boolean wasUp = null;
		for (Date date : data.keySet()) {
			Double current = data.get(date);
			Double previous = rightShiftedData.get(date);
			if (previous != null && !previous.isNaN()) {
				isUp = (current - previous)/Math.abs(previous) > changeRatio/100;
				isDown = (current - previous)/Math.abs(previous) < -changeRatio/100;
				if (wasDown != null) {
					Boolean reversalUp = wasDown && isUp;
					Boolean reversalDown = wasUp && isDown;
					if ((direction >= 0)) {
						outputs.getValue(targetStock).put(date, reversalUp);
					} else {
						outputs.getValue(targetStock).put(date, reversalDown);
					}
				}
				wasDown = isDown;
				wasUp = isUp;
			}
		}

		return outputs;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 2; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

}
