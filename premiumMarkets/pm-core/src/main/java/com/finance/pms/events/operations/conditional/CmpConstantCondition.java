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
package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

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
 * 'over'
 * not implemented : 'for'
 * does not make sense : 'spanning' . As the condition is a status in time not an event in time.
 */
public abstract class CmpConstantCondition extends Condition<Double> implements OnThresholdCondition {
	
	@SuppressWarnings("unused")
	private CmpConstantCondition() {
		super();
	}

	
	protected CmpConstantCondition(String reference, String description) {
		super(reference, description, new NumberOperation("threshold"), new NumberOperation("time period over which it happens"), new NumberOperation("length of time over which it is true"), new DoubleMapOperation("historical data input"));
	}

	public CmpConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double threshold = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Integer overPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);
		
		BooleanMapValue outputs = new  BooleanMapValue();
		BooleanMapValue underLyingRealOuts = new BooleanMapValue();

		for (Date date : data.keySet()) {
			Double current = data.get(date);
			
			@SuppressWarnings("unchecked")
			Boolean conditionCheck = conditionCheck(current, threshold);
			if (conditionCheck != null) {
				
				if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
					
					underLyingRealOuts.getValue(targetStock).put(date, conditionCheck);
					
					if (conditionCheck && forPeriod > 0) {
						
						Calendar startForPeriodCal = Calendar.getInstance();
						startForPeriodCal.setTime(date);
						QuotationsFactories.getFactory().incrementDate(startForPeriodCal, -forPeriod-1);
						Date startForPeriod = startForPeriodCal.getTime();
						
						SortedMap<Date, Boolean> forPeriodData = underLyingRealOuts.getValue(targetStock).subMap(startForPeriod, date);
						if (startForPeriod.before(data.firstKey())) {
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

				if (conditionCheck != null && conditionCheck && overPeriod > 0) {
					Calendar endOverPeriodCal = Calendar.getInstance();
					endOverPeriodCal.setTime(date);
					QuotationsFactories.getFactory().incrementDate(endOverPeriodCal, +overPeriod+1);
					Date endOverPeriod = (endOverPeriodCal.after(data.lastKey()))?data.lastKey():endOverPeriodCal.getTime();
					SortedMap<Date, Double> overPeriodData = data.subMap(date, endOverPeriod);
					for (Date overPeriodDate : overPeriodData.keySet()) {
						outputs.getValue(targetStock).put(overPeriodDate, conditionCheck);
					}
				}
				
			}
		}
		
		return outputs;
	}

	@Override
	public int inputThresholdPosition() {
		return 0;
	}
	
	@Override
	public int mainInputPosition() {
		return 3;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = getOperands().get(mainInputPosition()).operationStartDateShift();
		for (int i = inputThresholdPosition()+1; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}	

}
