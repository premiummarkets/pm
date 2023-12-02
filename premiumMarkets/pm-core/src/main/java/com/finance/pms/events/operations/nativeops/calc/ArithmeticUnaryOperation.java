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

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

@XmlSeeAlso({UnaryProduct.class, UnarySum.class, UnaryDivision.class})
public abstract class ArithmeticUnaryOperation extends DoubleMapOperation {
	
	protected ArithmeticUnaryOperation() {
		super();
	}

	public ArithmeticUnaryOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public ArithmeticUnaryOperation(String reference, String description, Operation ...operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(0, 1);
		Double unaryOperand = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();
		
		//NumericableMapValue outputs = innerCalc(targetStock, unaryOperand, data);
		ValueManipulator.InnerCalcFunc innerCalcFunc = data -> innerCalc(targetStock, unaryOperand, data);
		return ValueManipulator.doubleArrayExpender(this, 0, targetStock, parentRequiredStartShift, innerCalcFunc, numericableMapValue);
		
	}

	private NumericableMapValue innerCalc(TargetStockInfo targetStock, Double unaryOperand, List<NumericableMapValue> data) {
		NumericableMapValue outputs = new DoubleMapValue();
		SortedMap<Date, Double> value = data.get(0).getValue(targetStock);
		for (Date date : value.keySet()) {
			Double leftOperand = data.get(0).getValue(targetStock).get(date);
			outputs.getValue(targetStock).put(date, (leftOperand == null || leftOperand.isNaN())?Double.NaN:twoOperandsOp(leftOperand, unaryOperand));
		}
		return outputs;
	}
	
	public abstract Double twoOperandsOp(Double op0, Double op1);
	
	@Override
	public void interrupt() throws Exception {
		CalculateThreadExecutor.getRandomInfiniteExecutorInstance().shutdownNow();
	}

}
