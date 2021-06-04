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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * 'for'
 * does not make sense : 'spanning'. As the condition is a status not an event in time.
 */
@XmlSeeAlso({SupDoubleMapCondition.class, InfDoubleMapCondition.class, EqualDoubleMapCondition.class})
public abstract class CmpDoubleMapCondition extends Condition<Double> implements OnSignalCondition {

	private static final int OTHER_PARAMS = 1;
	private static final int MAIN_POSITION = 2;
	private static final int SIGNAL_POSITION = 3;

	@SuppressWarnings("unused")
	private CmpDoubleMapCondition() {
		super();
	}

	protected CmpDoubleMapCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("length of time over which it is true"),
				new NumberOperation("minimum epsilon crossing for inequality or maximum epsilon error for equality in %"),
				new DoubleMapOperation("'" + reference + "' left operand"),
				new DoubleMapOperation("'" + reference + "' right operand"));
	}

	public CmpDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer forPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Double epsilon = ((NumberValue) inputs.get(OTHER_PARAMS)).getValue(targetStock).doubleValue()/100;
		SortedMap<Date, Double> firstOp = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		SortedMap<Date, Double> secondOp = ((NumericableMapValue) inputs.get(SIGNAL_POSITION)).getValue(targetStock);

		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		fullKeySet.addAll(firstOp.keySet());
		fullKeySet.addAll(secondOp.keySet());

		BooleanMapValue outputs = new  BooleanMapValue();
		BooleanMapValue realRowOutputs = new BooleanMapValue();

		for (Date date : fullKeySet) {
			Double firstV = firstOp.get(date);
			Double secondV = secondOp.get(date);
			if (firstV != null && !firstV.isNaN() && secondV != null && !secondV.isNaN()) {
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(firstV, secondV, epsilon);
				conditionCheck = forPeriodReduction(targetStock, fullKeySet, realRowOutputs, forPeriod, date, conditionCheck, outputs);
			}
		}

		return outputs;
	}

	@Override
	public int inputSignalPosition() {
		return SIGNAL_POSITION;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override //Adding shift inherent to over, for and spanning
	public int operandsRequiredStartShift() {
		int maxDateShift = 0;
		for (int i = 0; i < OTHER_PARAMS; i++) { //epsilon is not part of the shift
			maxDateShift = maxDateShift + getOperands().get(i).operandsRequiredStartShift();
		}
		return maxDateShift;
	}
}
