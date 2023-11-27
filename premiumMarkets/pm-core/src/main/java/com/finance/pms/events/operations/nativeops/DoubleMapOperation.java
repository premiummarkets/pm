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
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({
	ArithmeticOperation.class, ArithmeticUnaryOperation.class,
	PMIndicatorOperation.class, TalibOperation.class,
	StockOperation.class,
	LnPeriodicOperation.class, FilterOperation.class, RecursiveOperation.class, RefiterOperation.class, DataTypeCheckOperation.class,
	Ta4jOperation.class
	})
public class DoubleMapOperation extends MapOperation {

	public DoubleMapOperation() {
		super("historical data", "Time series of real historical data or resulting of calculations");
	}

	public DoubleMapOperation(String reference) {
		super(reference, reference);
	}

	public DoubleMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public DoubleMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public DoubleMapOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((NumericableMapValue)inputs.get(0));
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
		//Nothing
	}

}
