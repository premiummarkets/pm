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
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.scoring.functions.LeftShifter;

/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'
 * 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 */

@XmlSeeAlso({CrossUpDoubleMapCondition.class, CrossDownDoubleMapCondition.class})
public abstract class CrossDoubleMapCondition extends Condition<Double> implements OnSignalCondition {

	private static MyLogger LOGGER = MyLogger.getLogger(CrossDoubleMapCondition.class);

	private static final int OTHER_PARAMS = 2;
	private static final int MAIN_POSITION = 5;
	private static final int SIGNAL_POSITION = 6;

	@SuppressWarnings("unused")
	private CrossDoubleMapCondition() {
		super();
	}

	public CrossDoubleMapCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("dates comparison span"),
				new NumberOperation("time period over which it happens"),
				new NumberOperation("minimum epsilon for fulfilling the condition in %"),
				new NumberOperation("adaptation rate"),
				new Condition<>("adaptor"),
				new DoubleMapOperation("'"+reference+ "' left operand (data)"),
				new DoubleMapOperation("'"+reference+ "' right operand (signal)"));
	}

	public CrossDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer spanningShift = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer overPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();

		Double epsilon = ((NumberValue) inputs.get(OTHER_PARAMS)).getValue(targetStock).doubleValue()/100;
		Double adaptationRate = ((NumberValue) inputs.get(OTHER_PARAMS+1)).getValue(targetStock).doubleValue()/100;
		SortedMap<Date, Boolean> adaptor = ((BooleanMapValue) inputs.get(OTHER_PARAMS+2)).getValue(targetStock);

		SortedMap<Date, Double> firstOp = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		SortedMap<Date, Double> secondOp = ((NumericableMapValue) inputs.get(SIGNAL_POSITION)).getValue(targetStock);

		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		fullKeySet.addAll(firstOp.keySet());
		fullKeySet.addAll(secondOp.keySet());

		BooleanMapValue outputs = new  BooleanMapValue();
		
		try {
			if (spanningShift == 0) spanningShift = 1;
			LeftShifter<Double> rightShifter = new LeftShifter<Double>(targetStock.getStock(), getRequiredStockData(), -spanningShift.intValue(), false);
			SortedMap<Date, Double> rightShiftedFirstOp = rightShifter.shift(firstOp);
			SortedMap<Date, Double> rightShiftedSecondOp = rightShifter.shift(secondOp);

			Boolean isNotAdaptive = adaptationRate == 0;
			for (Date date : fullKeySet) {
				Double firstV = firstOp.get(date);
				Double secondV = secondOp.get(date);
				Double previousFirstV = rightShiftedFirstOp.get(date);
				Double previousSecondV = rightShiftedSecondOp.get(date);
				if (previousFirstV != null && !previousFirstV.isNaN() && previousSecondV != null && !previousSecondV.isNaN() && !firstV.isNaN() && !secondV.isNaN()) {

					Double alphaAdaptation = (isNotAdaptive)?0:calculateAlphaAdaptation(date, adaptationRate, adaptor);

					@SuppressWarnings("unchecked")
					Boolean conditionCheck = conditionCheck(previousFirstV, firstV, previousSecondV, secondV, epsilon, alphaAdaptation);

					if (conditionCheck != null) {

						if (overPeriod == 0 || outputs.getValue(targetStock).get(date) == null) {
							outputs.getValue(targetStock).put(date, conditionCheck);
						}

						overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);
					}
				}
			}
		} catch (NotEnoughDataException e) {
			LOGGER.error(e);
		}

		return outputs;
	}

	private Double calculateAlphaAdaptation(Date date, Double adaptationRate, SortedMap<Date, Boolean> adaptor) {
		ArrayList<Boolean> arrayList = new ArrayList<>(adaptor.headMap(date).values());
		ListIterator<Boolean> listIterator = arrayList.listIterator(arrayList.size());
		Double n = 1d;
		Double alphaAdaptation = adaptationRate;
		while (listIterator.hasPrevious() && (alphaAdaptation = adaptationRate * 2d/(1d+n)) > 0.001) {
			if (listIterator.previous()) {
				LOGGER.info("aplhaAdapation:" + date + ", " + alphaAdaptation);
				return alphaAdaptation;
			}
			n++;
		}
		return 0d;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override
	public int inputSignalPosition() {
		return SIGNAL_POSITION;
	}

	@Override //Adding shift inherent to over, for and spanning
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		
		return IntStream.range(0, OTHER_PARAMS)
		.map(i -> {
			Operation numberOperand = getOperands().get(i);
			if (numberOperand instanceof NumberOperation) {
				return  ((NumberValue) numberOperand.getOrRunParameter(targetStock).orElse(new NumberValue(0.0))).getValue(targetStock).intValue();
			} else {
				return getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift);
			}
		})
		.reduce(0, (r, e) -> r + e);
	}
}
