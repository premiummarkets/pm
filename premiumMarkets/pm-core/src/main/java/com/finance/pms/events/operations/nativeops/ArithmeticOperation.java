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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

@XmlSeeAlso({Product.class, Sum.class, Division.class, Subtraction.class})
public abstract class ArithmeticOperation extends DoubleMapOperation {

	protected ArithmeticOperation() {
		super();
	}

	public ArithmeticOperation(String reference, String description, ArrayList<DoubleMapOperation> operands) {
		super(reference, description, operands);
	}

	public ArithmeticOperation(String reference, String description, DoubleMapOperation ...operands) {
		this(reference, description, new ArrayList<DoubleMapOperation>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		if (inputs.size() == 0) return new DoubleMapValue();
		if (inputs.size() == 1) return (NumericableMapValue) inputs.get(0);
		
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> checkedInputs = (List<NumericableMapValue>) inputs;

		ValueManipulator.InnerCalcFunc innerCalcFunc = data -> innerCalc(targetStock, data);
		return ValueManipulator.doubleArrayExpender(this, 0, targetStock, parentRequiredStartShift, innerCalcFunc, checkedInputs);

	}

	private NumericableMapValue innerCalc(TargetStockInfo targetStock, List<NumericableMapValue> checkedInputs) {
		
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (NumericableMapValue input : checkedInputs) {
			fullKeySet.addAll(input.getValue(targetStock).keySet());
		}
		
		NumericableMapValue outputs = new DoubleMapValue();
		SortedMap<Date, Double> valuesOp0 = checkedInputs.get(0).getValue(targetStock);
		for (Date date : fullKeySet) {
			Double leftOperand = valuesOp0.get(date);
			for (int i=1; i < checkedInputs.size(); i++) {
				NumericableMapValue input = checkedInputs.get(i);
				Double rightOperand = input.getValue(targetStock).get(date);
				leftOperand = ( (leftOperand == null || leftOperand.isNaN()) || (rightOperand == null || rightOperand.isNaN()) )?Double.NaN:twoOperandsOp(leftOperand, rightOperand);
			}
			if (!leftOperand.isNaN()) { //!lenient
				outputs.getValue(targetStock).put(date, leftOperand);
			}
		}
		return outputs;
	}

	public abstract Double twoOperandsOp(Double op0, Double op1);

}
