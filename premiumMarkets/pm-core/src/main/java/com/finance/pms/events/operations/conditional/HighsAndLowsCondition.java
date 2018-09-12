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
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'		:	is the period we look back into
 * 'over' 			: 	is remanence/persistence of the tangent from its trigger date
 * 'for' 			: 	is minimum distance between knots
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends Condition<Comparable> implements UnaryCondition {

	private static final int THRESHOLDS_IDX = 4;
	private static final int MAIN_POSITION = 8;

	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private HighsAndLowsCondition() {
	}

	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Spanning period as look back period in days"),
				new NumberOperation("Over period as remanence/persistence of the divergence from its trigger date"),
				new NumberOperation("For period as minimum number of days between two extreme knots"),
				new NumberOperation("Smoothing period for sporadic peaks and troughs mitigation"),
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

		Double lowestStart = ((NumberValue) inputs.get(THRESHOLDS_IDX)).getValue(targetStock).doubleValue();
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
		Map<String, TangentElement> expertTangentsResult = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		List<Double> previousSelectedKnotsValues = new ArrayList<>();
		String currentLabel = null;

		SortedSet<Date> fullKeySet = new TreeSet<>(data.keySet());
		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : data.keySet()) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbDays).getTime();

			if ( lookBackPeriodStart.after(dataFirstKey) ) {

				SortedMap<Date, Double> dataLookBackMap = MapUtils.subMapInclusive(data, lookBackPeriodStart, date);
				if (dataLookBackMap.size() < 4) continue;

				Comparable dataLookBackCmp = new ComparableArray<>(dataLookBackMap.values());
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
						dataLookBackCmp,
						lookBackSmoothingPeriodCmp, minimumNbDaysBetweenExtremesCmp, selectedKnotsCmp, expertTangentCmp,
						lowestStartCmp, highestStartCmp, lowestEndCmp, highestEndCmp);

				if (conditionCheck != null) {

					if (conditionCheck) { //Will Map tangent to date for return if new knots involved

						//Tangent output
						try {

							@SuppressWarnings("unchecked")
							ArrayList<Double> expertTangent = (ArrayList<Double>) expertTangentCmp;
							if (!expertTangent.isEmpty()) {

								ArrayList<Double> dataLookBackArray = new ArrayList<>(dataLookBackMap.values());

								//Check if already charted
								@SuppressWarnings("unchecked")
								ArrayList<Integer> selectedKnotsKeys = new ArrayList<>(((SortedMap<Integer, Double>) selectedKnotsCmp).keySet());
								Boolean sameKnots = previousSelectedKnotsValues.size() == selectedKnotsKeys.size();
								if (sameKnots) {
									for (int i = 0; i < selectedKnotsKeys.size(); i++) {
										if (!previousSelectedKnotsValues.get(i).equals(dataLookBackArray.get(selectedKnotsKeys.get(i)))) {
											sameKnots = false;
										}
									}
								}
								//If New : We record for return
								if (!sameKnots) {

									//Tangent for charting
									ArrayList<Date> dataLookBackDatesArray = new ArrayList<>(dataLookBackMap.keySet());
									SortedMap<Date, Double> expertTangentsResultAtDate = new TreeMap<>();
									for (int i = 0; i < dataLookBackDatesArray.size(); i++) {
										if (!expertTangent.get(i).isNaN()) {
											expertTangentsResultAtDate.put(dataLookBackDatesArray.get(i), expertTangent.get(i));
										}
									}

									currentLabel = expertTangentLabel + dateFormat.format(date);
									expertTangentsResult.put(currentLabel, new TangentElement(expertTangentsResultAtDate, date));
									previousSelectedKnotsValues = selectedKnotsKeys.stream().map(kIdx -> dataLookBackArray.get(kIdx)).collect(Collectors.toList());

								} else {
									expertTangentsResult.get(currentLabel).setClosingDate(date);
								}

							}

						} catch (Exception e) {
							//Out of range wont be printed
							LOGGER.error(e,e);
						}
					}

					if ((overPeriodRemanence == 0 || outputs.getValue(targetStock).get(date) == null)) {
						outputs.getValue(targetStock).put(date, conditionCheck); //We don't have a 'for' reduction here as 'for' is actually the distance between extreme knots.
					}

					overPeriodFilling(targetStock, fullKeySet, overPeriodRemanence, date, conditionCheck, outputs);

				}

			}

		}

		if (true && !expertTangentsResult.isEmpty()) {
			expertTangentsResult.entrySet().stream().forEach(e -> {
				String key = e.getKey() + "-" + dateFormat.format(e.getValue().getClosingDate());
				outputs.getAdditionalOutputs().put(key, new DoubleMapValue(e.getValue().getTangent()));
				outputs.getAdditionalOutputsTypes().put(key, Type.MULTI);
			});
		}

		if (LOGGER.isInfoEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map " +
							outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b));
		}

		return outputs;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < THRESHOLDS_IDX; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

}
