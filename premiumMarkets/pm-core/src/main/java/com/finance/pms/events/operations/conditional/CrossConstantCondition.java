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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.math3.stat.descriptive.rank.Min;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.scoring.functions.CurvesAddition;
import com.finance.pms.events.scoring.functions.CurvesSubtraction;
import com.finance.pms.events.scoring.functions.LeftShifter;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.StatsFunction;

/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'
 * 'over'
 * 'for'. Only makes sense for Down and Up Ratios (status V change of status).
 */
//TODO separate Up/Down from cross Up/Down
@XmlSeeAlso({CrossUpConstantCondition.class, CrossDownConstantCondition.class, DownRatioCondition.class, UpRatioCondition.class})
public abstract class CrossConstantCondition extends Condition<Double> {

	protected static MyLogger LOGGER = MyLogger.getLogger(CrossConstantCondition.class);

	protected static final int CONSTANT_POSITION = 0;
	private static final int OTHER_PARAMS = 4;
	private static final int MAIN_POSITION = 5;

	@SuppressWarnings("unused")
	private CrossConstantCondition() {
		super();
	}

	protected CrossConstantCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("threshold"),
				new NumberOperation("dates comparison span"), new NumberOperation("time period over which it happens"), new NumberOperation("length of time over which it is true"),
				new NumberOperation("minimum epsilon for crossing the condition in %"),
				new DoubleMapOperation("historical data input"));
	}

	public CrossConstantCondition(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double threshold = ((NumberValue) inputs.get(CONSTANT_POSITION)).getValue(targetStock).doubleValue();
		Integer spanningShift = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer overPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(3)).getValue(targetStock).intValue();
		Double epsilon = ((NumberValue) inputs.get(OTHER_PARAMS)).getValue(targetStock).doubleValue()/100;
		SortedMap<Date, Double> data = sanitizedData(targetStock, inputs);

		if (overPeriod > 0 && forPeriod > 0) throw new UnsupportedOperationException("Setting both Over Period "+overPeriod+" and For Period "+forPeriod+" is not supported.");

		BooleanMapValue outputs = new  BooleanMapValue();
		try {
			if (spanningShift == 0) spanningShift = 1;
			LeftShifter<Double> rightShifter = new LeftShifter<Double>(targetStock.getStock(), getRequiredStockData(), -spanningShift.intValue(), false);
			SortedMap<Date, Double> rightShiftedData = rightShifter.shift(data);

			SortedSet<Date> fullKeySet = new TreeSet<>(data.keySet());
			if (Double.isNaN(threshold)) return outputs;

			BooleanMapValue realRowOutputs = new BooleanMapValue();

			for (Date date : fullKeySet) {
				Double current = data.get(date);
				Double previous = rightShiftedData.get(date);
				if (previous != null && !previous.isNaN() && !current.isNaN()) {
					@SuppressWarnings("unchecked")
					Boolean conditionCheck = conditionCheck(previous, current, threshold, epsilon);
					if (conditionCheck != null) {

						if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
							conditionCheck = forPeriodReduction(targetStock, fullKeySet, realRowOutputs, forPeriod, date, conditionCheck, outputs);
						}

						overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);

					}
				}
			}

			if (LOGGER.isDebugEnabled()) {
				SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
				LOGGER.info(
						"Condition '" + this.getReference() + "' returns this map \n" +
						outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
			}
		} catch (NotEnoughDataException e) {
			LOGGER.error(e);
		}

		return outputs;
	}

	protected SortedMap<Date, Double> sanitizedData(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
	}
	
	protected SortedMap<Date, Double> transformPositive(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		
		StatsFunction minFunc = new MyApacheStats(new Min());
		SortedMap<Date, Double> min = minFunc.mdEvaluateMd(data);
		
		CurvesSubtraction curvesSubtraction = new CurvesSubtraction();
		SortedMap<Date, Double> zeroBasedData = curvesSubtraction.sOperate(data, min);
		
		CurvesAddition curvesAddition = new CurvesAddition();
		SortedMap<Date, Double> oneBasedData = curvesAddition.sOperate(zeroBasedData, 1d);
		
		return oneBasedData;
	}

	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override //Adding shift inherent to over, for and spanning
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		
		return IntStream.range(CONSTANT_POSITION + 1, OTHER_PARAMS)
		.map(i -> {
			Operation numberOperand = getOperands().get(i);
			return numberOperand.getOrRunParameter(targetStock, thisCallStack)
					.filter(v -> v instanceof NumberValue)
					.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
					.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisCallStack, thisParentStartShift));
		})
		.reduce(0, (r, e) -> r + e);
		
	}
}
