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
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;


/**
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'. Does not make sense : As this condition is a status check in time not an event (change of status) check in time.
 * 'over'
 * 'for'
 */
@XmlSeeAlso({EqualConstantCondition.class, InfConstantCondition.class, SupConstantCondition.class})
public abstract class CmpConstantCondition extends Condition<Double> implements OnThresholdCondition {

	private static final int THRESHOLD_POSITION = 0;
	private static final int OTHER_PARAMS = 3;
	private static final int MAIN_POSITION = 4;

	@SuppressWarnings("unused")
	private CmpConstantCondition() {
		super();
	}

	protected CmpConstantCondition(String reference, String description) {
		super(reference, description, new NumberOperation("threshold"),
				new NumberOperation("time period over which it happens"),
				new NumberOperation("length of time over which it is true"),
				//XXX Minimum epsilon crossing does not actually make real sense for constant as the latter can instead be adjusted...
				new NumberOperation("minimum epsilon crossing for inequality or maximum epsilon error for equality in %"),
				new DoubleMapOperation("'" + reference + "' indicator"));
	}

	public CmpConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double threshold = ((NumberValue) inputs.get(THRESHOLD_POSITION)).getValue(targetStock).doubleValue();
		Integer overPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		Double epsilon = ((NumberValue) inputs.get(OTHER_PARAMS)).getValue(targetStock).doubleValue()/100;
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);

		if (overPeriod > 0 && forPeriod > 0) throw new UnsupportedOperationException("Setting both Over Period " + overPeriod + " and For Period " + forPeriod + " is not supported.");

		BooleanMapValue outputs = new  BooleanMapValue();
		if (Double.isNaN(threshold)) return outputs;

		SortedSet<Date> fullKeySet = new TreeSet<>(data.keySet());
		BooleanMapValue realRowOutputs = new BooleanMapValue();

		for (Date date : fullKeySet) {
			Double current = data.get(date);

			@SuppressWarnings("unchecked")
			Boolean conditionCheck = (Double.isNaN(current))?null:conditionCheck(current, threshold, epsilon);
			if (conditionCheck != null) {

				//For
				if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
					conditionCheck = forPeriodReduction(targetStock, fullKeySet, realRowOutputs, forPeriod, date, conditionCheck, outputs);
				}

				//Over
				overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);

			}
		}

		return outputs;
	}

	@Override
	public int inputThresholdPosition() {
		return THRESHOLD_POSITION;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override //Adding shift inherent to over, for and spanning
	public int operandsRequiredStartShift() {		
		return IntStream.range(inputThresholdPosition() + 1, OTHER_PARAMS)
		.map(i -> {
			Operation numberOperand = getOperands().get(i);
			if (numberOperand instanceof NumberOperation) {
				return  ((NumberValue) numberOperand.getParameter()).getValue(null).intValue();
			} else {
				return getOperands().get(i).operandsRequiredStartShift();
			}
		})
		.reduce(0, (r, e) -> r + e);
	}

}
