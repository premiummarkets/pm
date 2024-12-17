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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.quotations.QuotationsFactories;

/**
 * 
 * @author Guillaume Thoreton
 * Some conditions can (when it makes sense) be followed by key words changing the result (Spanning Over For) :
 * 
 * 'spanning n days' : will be used for condition involving events happening over time like when comparing two status of the data at two point in time t and t-n (change of status).
 * 	For instance 'close crosses up 10 spanning 3 days' means that close was below 10 three days ago and close is now above 10. So basically we ignore what happened in between.
 * 
 * 'over n days' : Time OVER which the condition will remain true.
 * 	Means that the condition happened once over the past n days. It could as well not be fulfilled a the date.
 * 	For a status A to be true, we need that the status was at least once of value A over the past n days.
 * 	For an event (change of status) B to A to be true, we need that the status changed at least once from value B to value A over the past n days.
 *
 * 'for n days' : Look back period FOR which the condition has to be true and remain true.
 * 	Means that the condition was true for the n previous days at the day we check.
 * 	This makes sense only for a check on status value not a change of status.
 * 	In case there be missing values over the last n days, this will still be true if all present values are true.
 * 	These can be combined like for instance :
 *  	close is above 10 over 30 days for 2 days
 *   	close is above 10 over 10 days for 10 days
 *   	goes up for 10 days spanning 2 days
 *		...
 *
 * Note 'days' here mean days calendar open days not quotation days.
 */

@XmlSeeAlso({
	BooleanMapCondition.class,
	CmpConstantCondition.class,
	CmpDoubleMapCondition.class,
	CmpDoubleMapCondition.class,
	CrossConstantCondition.class,
	CrossDoubleMapCondition.class,
	EqualStringConstantCondition.class,
	NullCondition.class,
	PreAndSignalCondition.class,
	ReverseCondition.class,
	DiscreteLinearOutputsCondition.class})
public class Condition<T> extends Operation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(Condition.class);

	protected Condition() {
		super();
	}

	public Condition(String reference) {
		super(reference, reference);
	}

	public Condition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public Condition(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<T> ... ops) {
		return false;
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new BooleanMapValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	/**
	 * We fill in ahead a period of 'overPeriod' length with the actual condition status if it is true.
	 * Does nothing if the actual condition status is false.
	 * @param targetStock
	 * @param fullKeySet data full date set
	 * @param overPeriod
	 * @param actualDate
	 * @param conditionCheck
	 * @param outputs
	 */
	protected void overPeriodFilling(TargetStockInfo targetStock, SortedSet<Date> fullKeySet, Integer overPeriod, Date actualDate, Boolean conditionCheck, BooleanMapValue outputs) {
		if (conditionCheck != null && conditionCheck && overPeriod > 0) {
			try {
				Calendar endOverPeriodCal = Calendar.getInstance();
				endOverPeriodCal.setTime(actualDate);
				QuotationsFactories.getFactory().incrementDate(targetStock.getStock(), targetStock.getQuotationsDataTypes(), endOverPeriodCal, +overPeriod+1);
				Date endOverPeriod = (endOverPeriodCal.after(fullKeySet.last()))?fullKeySet.last():endOverPeriodCal.getTime();
				SortedSet<Date> overPeriodDates = fullKeySet.subSet(actualDate, endOverPeriod);
				for (Date overPeriodDate : overPeriodDates) {
					outputs.getValue(targetStock).put(overPeriodDate, true);
				}
			} catch (NotEnoughDataException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * @param targetStock
	 * @param fullKeySet
	 * @param realRowOutputs
	 * @param forPeriod
	 * @param actualDate
	 * @param conditionCheck
	 * @return
	 */
	//The condition is checked FROM 'actualDate' - forPeriod -1 TO 'actualDate' -1 and against the 'actualDate' latest condition check
	private Boolean reduceRawOutputForPeriod(
			TargetStockInfo targetStock, SortedSet<Date> fullKeySet, BooleanMapValue realRowOutputs, Integer forPeriod, Date actualDate, Boolean conditionCheck) {
		if (conditionCheck && forPeriod > 0) {

			try {
				Calendar startForPeriodCal = Calendar.getInstance();
				startForPeriodCal.setTime(actualDate);
				QuotationsFactories.getFactory().incrementDate(targetStock.getStock(), targetStock.getQuotationsDataTypes(), startForPeriodCal, -forPeriod-1);
				Date startForPeriod = startForPeriodCal.getTime();

				SortedMap<Date, Boolean> previousForPeriodConditionChecks = realRowOutputs.getValue(targetStock).subMap(startForPeriod, actualDate);
				if (startForPeriod.before(fullKeySet.first())) {
					conditionCheck = null;
				} else {
					for (Boolean previousValue : previousForPeriodConditionChecks.values()) {
						conditionCheck = conditionCheck && previousValue;
						if (!previousValue) break;
					}
				}
			} catch (NotEnoughDataException e) {
				LOGGER.error(e);
			}
		}
		return conditionCheck;
	}

	protected Boolean forPeriodReduction(
			TargetStockInfo targetStock, SortedSet<Date> fullKeySet, BooleanMapValue realRowOutputs, Integer forPeriod, Date actualDate, Boolean rawConditionCheck, BooleanMapValue outputs) {
		realRowOutputs.getValue(targetStock).put(actualDate, rawConditionCheck);
		Boolean conditionCheck = reduceRawOutputForPeriod(targetStock, fullKeySet, realRowOutputs, forPeriod, actualDate, rawConditionCheck);
		if (conditionCheck != null) outputs.getValue(targetStock).put(actualDate, conditionCheck);
		return conditionCheck;
	}

	@Override
	public BooleanMapValue emptyValue() {
		return new BooleanMapValue();
	}
}
