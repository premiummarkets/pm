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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.ComparableArray;
import com.finance.pms.datasources.ComparableSortedMap;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.quotations.QuotationsFactories;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 * 'spanning'
 * 
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends Condition<Comparable> implements UnaryCondition {

	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private static final int MAIN_POSITION = 3;

	private HighsAndLowsCondition() {
	}

	//makes a higher high over n days spanning x days smoothed for y days
	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Look back over period in days"),
				new NumberOperation("Minimum days spanning between two extremes"),
				new NumberOperation("Smoothing period for extremes inclusion"),
				new DoubleMapOperation("Historical data input"));
	}

	public HighsAndLowsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}


	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		Integer lookBackNbdays = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer minimumNbDaysBetweenExtremes = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer lookBackSmoothingPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		Date dataFirstKey = data.firstKey();

		if (minimumNbDaysBetweenExtremes < 4) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 3 days span to draw one of these.");
			return new BooleanMultiMapValue(data.keySet(), false);
		}

		if (lookBackNbdays < 4) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 3 days over period to draw one of these.");
			return new BooleanMultiMapValue(data.keySet(), false);
		}
		
		String expertTangentLabel = lookBackNbdays+" days tangent of "+ this.getOperands().get(MAIN_POSITION).getReference() + " at ";
		Map<String, SortedMap<Date, Double>> expertTangentsResult = new HashMap<>();
		String previousLibel = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : data.keySet()) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbdays).getTime();

			if ( lookBackPeriodStart.after(dataFirstKey) ) {

				SortedMap<Date, Double> dataLookBackMap = MapUtils.subMapInclusive(data, lookBackPeriodStart, date);
				if (dataLookBackMap.size() < 4) continue;

				Comparable periodDataOps = new ComparableArray<Double>(dataLookBackMap.values());
				Comparable lookBackSmoothingPeriodCmp = lookBackSmoothingPeriod;
				Comparable minimumNbDaysBetweenExtremesCmp = minimumNbDaysBetweenExtremes;
				Comparable higherHighsCmp = new ComparableSortedMap<Integer, Double>();
				Comparable expertTangentCmp = new ComparableArray<Double>();

				@SuppressWarnings("unchecked")
				//Boolean higherHigh(double[][] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, Map<Integer, double[]> higherHighs, ArrayList<Double> expertTangent);
				Boolean conditionCheck = conditionCheck(periodDataOps, lookBackSmoothingPeriodCmp, minimumNbDaysBetweenExtremesCmp, higherHighsCmp, expertTangentCmp);

				if (conditionCheck != null) {

					outputs.getValue(targetStock).put(date, conditionCheck);

					if ( conditionCheck ) { //map expertTangent to Date Map
						try {
							@SuppressWarnings("unchecked")
							ArrayList<Double> expertTangent = (ArrayList<Double>) expertTangentCmp;
							if (!expertTangent.isEmpty()) {
								ArrayList<Date> lookBackDateList = new ArrayList<Date>(dataLookBackMap.keySet());
								SortedMap<Date, Double> expertTangentsResultAtDate = new TreeMap<>();
								for (int i = 0; i < lookBackDateList.size(); i++) {
									if (!expertTangent.get(i).isNaN()) {
										expertTangentsResultAtDate.put(lookBackDateList.get(i), expertTangent.get(i));
									}
								}
								SortedMap<Date, Double> previousExpertTangent = expertTangentsResult.get(previousLibel);
								if (previousLibel == null || expertTangentsResultAtDate.entrySet().stream().anyMatch(e -> !previousExpertTangent.containsKey(e.getKey()) || !previousExpertTangent.get(e.getKey()).equals(e.getValue()))) {
									String currentLibel = expertTangentLabel+dateFormat.format(date);
									expertTangentsResult.put(currentLibel, expertTangentsResultAtDate);
									previousLibel = currentLibel;
								}
							}

						} catch (Exception e) {
							//Out of range wont be printed
							LOGGER.error(e,e);
						}
					}
				}
			}

		}

		//if (LOGGER.isTraceEnabled()) exportReglines(targetStock, lookBackNbdays, lookBackSmoothingPeriod, data, expertTangentsResult);

		if (!expertTangentsResult.isEmpty()) {
			expertTangentsResult.entrySet().stream().forEach(e -> {
				outputs.getAdditionalOutputs().put(e.getKey(), new DoubleMapValue(e.getValue()));
				outputs.getAdditionalOutputsTypes().put(e.getKey(), Type.MULTI);
			});
		}

		return outputs;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

}
