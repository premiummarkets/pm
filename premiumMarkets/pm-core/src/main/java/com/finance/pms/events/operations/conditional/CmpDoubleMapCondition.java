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
import java.util.Calendar;
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
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationsFactories;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * not implemented : 'for'
 * does not make sense : 'spanning' . As the condition is a status in time not an event in time.
 */
@XmlSeeAlso({SupDoubleMapCondition.class, InfDoubleMapCondition.class, EqualDoubleMapCondition.class})
public abstract class CmpDoubleMapCondition extends Condition<Double> implements OnSignalCondition {

	
	@SuppressWarnings("unused")
	private CmpDoubleMapCondition() {
		super();
	}

	protected CmpDoubleMapCondition(String reference, String description) {
		super(reference, description, new NumberOperation("length of time over which it is true"), new DoubleMapOperation("'"+reference+ "' left operand"), new DoubleMapOperation("'"+reference+ "' right operand"));
	}

	public CmpDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Integer forPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> firstOp = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);
		SortedMap<Date, Double> secondOp = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		fullKeySet.addAll(firstOp.keySet());
		fullKeySet.addAll(secondOp.keySet());
		
		BooleanMapValue outputs = new  BooleanMapValue();
		BooleanMapValue underLyingRealOuts = new BooleanMapValue();

		for (Date date : fullKeySet) {
			Double firstV = firstOp.get(date);
			Double secondV = secondOp.get(date);
			if (firstV != null && !firstV.isNaN() && secondV != null && !secondV.isNaN()) {
				
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(firstV, secondV);
				
				underLyingRealOuts.getValue(targetStock).put(date, conditionCheck);
				
				if (conditionCheck && forPeriod > 0) {
					
					Calendar startForPeriodCal = Calendar.getInstance();
					startForPeriodCal.setTime(date);
					QuotationsFactories.getFactory().incrementDate(startForPeriodCal, -forPeriod-1);
					Date startForPeriod = startForPeriodCal.getTime();
					
					SortedMap<Date, Boolean> forPeriodData = underLyingRealOuts.getValue(targetStock).subMap(startForPeriod, date);
					if (startForPeriod.before(fullKeySet.first())) {
						conditionCheck = null;
					} else {
						for (Boolean previousValue : forPeriodData.values()) {
							conditionCheck = conditionCheck && previousValue;
							if (!previousValue) break;
						}
					}
				}
				
				if (conditionCheck != null) outputs.getValue(targetStock).put(date, conditionCheck);
			}
		}
		
		return outputs;
	}

	@Override
	public int inputSignalPosition() {
		return 2;
	}
	
	@Override
	public int mainInputPosition() {
		return 1;
	}
	
	@Override
	public int operationStartDateShift() {
		int maxDateShift = Math.max(getOperands().get(mainInputPosition()).operationStartDateShift(),getOperands().get(inputSignalPosition()).operationStartDateShift());
		for (int i = 0; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}
}
