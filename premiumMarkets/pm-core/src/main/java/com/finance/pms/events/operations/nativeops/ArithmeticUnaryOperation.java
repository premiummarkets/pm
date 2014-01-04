/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

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
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(0)).getValue(targetStock);
		Double unaryOperand = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();
		
		DoubleMapValue outputs = new  DoubleMapValue();
		for (Date date : data.keySet()) {
			outputs.getValue(targetStock).put(date, twoOperandsOp(data.get(date), unaryOperand));
		}
		
		return outputs;
	}
	
	public abstract Double twoOperandsOp(Double op0, Double op1);
	
	@Override
	public int operationStartDateShift() {
		return getOperands().get(0).operationStartDateShift();
	}

}
