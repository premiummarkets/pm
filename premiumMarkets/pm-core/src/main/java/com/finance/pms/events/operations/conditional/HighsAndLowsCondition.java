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
 * 'spanning'		:	is the period we look back into
 * 'over' 			: 	is remanence/persistence of the tangent from its starting knot
 * 'for' 			: 	is minimum distance between knots
 * 
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends Condition<Comparable> implements UnaryCondition {

	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private static final int MAIN_POSITION = 8;

	private HighsAndLowsCondition() {
	}

	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Spanning period as look back period in days"),
				new NumberOperation("Over period as remanence/persistence of the tangent from its starting knot"),
				new NumberOperation("For period as minimum number of days between two extreme knots"),
				new NumberOperation("Smoothing period for peaks and troughs inclusion"),
				new NumberOperation("Lowest knot start"),
				new NumberOperation("Highest knot start"),
				new NumberOperation("Lowest knot end"),
				new NumberOperation("Highest knot end"),
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

		Integer lookBackNbDays = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer overPeriodRemanence = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer minimumNbDaysBetweenExtremes = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		Integer lookBackSmoothingPeriod = ((NumberValue) inputs.get(3)).getValue(targetStock).intValue();

		Double lowestStart = ((NumberValue) inputs.get(4)).getValue(targetStock).doubleValue();
		Double highestStart = ((NumberValue) inputs.get(5)).getValue(targetStock).doubleValue();
		Double lowestEnd = ((NumberValue) inputs.get(6)).getValue(targetStock).doubleValue();
		Double highestEnd = ((NumberValue) inputs.get(7)).getValue(targetStock).doubleValue();

		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		Date dataFirstKey = data.firstKey();

		if (minimumNbDaysBetweenExtremes == null || minimumNbDaysBetweenExtremes < 4) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 3 days over period to draw an HL condition check.");
			return new BooleanMultiMapValue(data.keySet(), false);
		}

		if (lookBackNbDays == null || lookBackNbDays < 4) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 5 days over period to draw an HL condition check.");
			return new BooleanMultiMapValue(data.keySet(), false);
		}

		String expertTangentLabel = lookBackNbDays + " days tangent of " + this.getOperands().get(MAIN_POSITION).getReference() + " at ";
		Map<String, SortedMap<Date, Double>> expertTangentsResult = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		List<Double> previousSelectedKnotsValues = new ArrayList<>();

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : data.keySet()) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbDays).getTime();

			if ( lookBackPeriodStart.after(dataFirstKey) ) {

				SortedMap<Date, Double> dataLookBackMap = MapUtils.subMapInclusive(data, lookBackPeriodStart, date);
				if (dataLookBackMap.size() < 4) continue;

				Comparable periodDataOps = new ComparableArray<Double>(dataLookBackMap.values());
				Comparable lookBackSmoothingPeriodCmp = lookBackSmoothingPeriod;
				Comparable minimumNbDaysBetweenExtremesCmp = minimumNbDaysBetweenExtremes;
				Comparable selectedKnotsCmp = new ComparableSortedMap<Integer, Double>();
				Comparable expertTangentCmp = new ComparableArray<Double>();
				Comparable lowestStartCmp = lowestStart;
				Comparable highestStartCmp = highestStart;
				Comparable lowestEndCmp = lowestEnd;
				Comparable highestEndCmp = highestEnd;

				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(
						periodDataOps, lookBackSmoothingPeriodCmp, minimumNbDaysBetweenExtremesCmp, selectedKnotsCmp, expertTangentCmp,
						lowestStartCmp, highestStartCmp, lowestEndCmp, highestEndCmp);

				if (conditionCheck != null) {

					outputs.getValue(targetStock).put(date, conditionCheck);

					if ( conditionCheck ) { //Will Map tangent to date for return if new knots involved
						
						//Tangent output
						try {

							@SuppressWarnings("unchecked")
							ArrayList<Double> expertTangent = (ArrayList<Double>) expertTangentCmp;
							if (!expertTangent.isEmpty()) {

								//Check if already printed
								@SuppressWarnings("unchecked")
								ArrayList<Double> selectedKnotsValues = new ArrayList<>(((SortedMap<Integer, Double>) selectedKnotsCmp).values());
								Boolean sameKnots = previousSelectedKnotsValues.size() == selectedKnotsValues.size();
								if (sameKnots) {
									for (int i = 0; i < selectedKnotsValues.size(); i++) {
										if (!previousSelectedKnotsValues.get(i).equals(selectedKnotsValues.get(i))){
											sameKnots = false;
										}
									}
								}
								//If New : We record for return
								if (!sameKnots) {
									String currentLibel = expertTangentLabel + dateFormat.format(date);

									//Removing NaN values from tangent
									ArrayList<Date> lookBackDateList = new ArrayList<Date>(dataLookBackMap.keySet());
									SortedMap<Date, Double> expertTangentsResultAtDate = new TreeMap<>();
									for (int i = 0; i < lookBackDateList.size(); i++) {
										if (!expertTangent.get(i).isNaN()) {
											expertTangentsResultAtDate.put(lookBackDateList.get(i), expertTangent.get(i));
										}
									}

									expertTangentsResult.put(currentLibel, expertTangentsResultAtDate);
									previousSelectedKnotsValues = selectedKnotsValues;
								}

							}

						} catch (Exception e) {
							//Out of range wont be printed
							LOGGER.error(e,e);
						}
						
						
						//TODO over ...
//						if ((overPeriodRemanence == 0 || outputs.getValue(targetStock).get(date) == null)) {
//
//							realRowOutputs.getValue(targetStock).put(date, conditionCheck);
//
//							conditionCheck = checkRawOutputAgainstForPeriod(targetStock, forPeriod, fullKeySet, realRowOutputs, date, conditionCheck);
//
//							if (conditionCheck != null) outputs.getValue(targetStock).put(date, conditionCheck);
//
//						}
//
//						fillInOverPeriod(targetStock, overPeriodRemanence, fullKeySet, date, conditionCheck, outputs);
					}
				}
			}

		}

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
