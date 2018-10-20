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
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.admin.install.logging.MyLogger;
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
import com.finance.pms.events.scoring.functions.Line;


//TODO review grammar 'for' is now surface of change + 'slope within' is tolerance (always positive)

/**
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'		:	is the period we look back into
 * 'over' 			: 	is remanence/persistence of the tangent from its trigger date
 * 'for' 			: 	is minimum distance between knots
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends Condition<Comparable> implements UnaryCondition, LinearOutputs {

	private static final int THRESHOLDS_IDX = 4;
	private static final int MAIN_POSITION = 11;

	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private HighsAndLowsCondition() {
	}

	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Spanning period as look back period in days"),
				new NumberOperation("Over period as remanence/persistence of the divergence from its trigger date"),
				new NumberOperation("Surface minimum within the divergence line and actual supported knots"),
				new NumberOperation("Smoothing period for sporadic peaks and troughs mitigation"),
				new NumberOperation("Lowest knot start (can be NaN)"),
				new NumberOperation("Highest knot start (can be NaN)"),
				new NumberOperation("Lowest knot end (can be NaN)"),
				new NumberOperation("Highest knot end (can be NaN)"),
				new NumberOperation("Slope minimum daily % of change tolerance (can be NaN)"),
				new NumberOperation("Slope maximum daily % of change tolerance (can be NaN)"),
				new NumberOperation("Knots Tolerance (can be NaN)"),
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

	@SuppressWarnings("unchecked")
	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		Integer lookBackNbDays = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue(); //s
		Integer overPeriodRemanence = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue(); //o
		Double minimumSurfaceOfChange = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue(); //f
		Integer lookBackSmoothingPeriod = ((NumberValue) inputs.get(3)).getValue(targetStock).intValue();

		Double lowestStart = ((NumberValue) inputs.get(THRESHOLDS_IDX)).getValue(targetStock).doubleValue();
		Double highestStart = ((NumberValue) inputs.get(5)).getValue(targetStock).doubleValue();
		Double lowestEnd = ((NumberValue) inputs.get(6)).getValue(targetStock).doubleValue();
		Double highestEnd = ((NumberValue) inputs.get(7)).getValue(targetStock).doubleValue();

		Double minSlope = ((NumberValue) inputs.get(8)).getValue(targetStock).doubleValue();
		Double maxSlope = ((NumberValue) inputs.get(9)).getValue(targetStock).doubleValue();

		Double tolerance = ((NumberValue) inputs.get(10)).getValue(targetStock).doubleValue();

		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		Date dataFirstKey = data.firstKey();

		if (minimumSurfaceOfChange == null || minimumSurfaceOfChange < 0) {
			minimumSurfaceOfChange = 0d;
		}

		if (lookBackNbDays == null || lookBackNbDays < 4) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 3 days over period to draw an HL condition check.");
			return new BooleanMultiMapValue(data.keySet(), false);
		}

		String expertTangentLabel = lookBackNbDays + " days tangent";
		Map<Line<Integer, Double>, TangentElement> expertTangentsStore = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		NavigableSet<Date> fullKeySet = new TreeSet<>(data.keySet());
		List<Date> fullKeyArray = new ArrayList<>(fullKeySet);
		SortedMap<Integer, Double> dataTimeMap = fullKeyArray.stream().collect(Collectors.toMap(d -> new Integer((int) (d.getTime()/DAY_IN_MILLI)), d -> data.get(d), (a, b) -> b, TreeMap::new));
		List<Integer> dateTimeKeys = new ArrayList<>(dataTimeMap.keySet());

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		SortedMap<Date, Line<Integer, Double>> realRowTangents = new TreeMap<>();
		for (Date date : fullKeyArray) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbDays).getTime();

			if ( lookBackPeriodStart.after(dataFirstKey) ) {

				if (MapUtils.subMapInclusive(data, lookBackPeriodStart, date).size() < 4) continue;

				Comparable dataLookBackTimeCmp = 
						new ComparableSortedMap<>(
								MapUtils
								.subMapInclusive(dataTimeMap, new Integer((int) (lookBackPeriodStart.getTime()/DAY_IN_MILLI)), new Integer((int) (date.getTime()/DAY_IN_MILLI)))
								);
				Comparable lookBackSmoothingPeriodCmp = lookBackSmoothingPeriod;
				Comparable minimumSurfaceOfChangeCmp = minimumSurfaceOfChange;
				Comparable _higherHighsCmp = new ComparableSortedMap<Integer, Double>();
				Comparable _expertTangentCmp = new Line<Integer, Double>();
				Comparable lowestStartCmp = lowestStart;
				Comparable highestStartCmp = highestStart;
				Comparable lowestEndCmp = lowestEnd;
				Comparable highestEndCmp = highestEnd;
				Comparable minSlopeCmp = minSlope;
				Comparable maxSlopeCmp = maxSlope;
				Comparable toleranceCmp = tolerance;

				Boolean conditionCheck = conditionCheck(
						dataLookBackTimeCmp,
						lookBackSmoothingPeriodCmp, minimumSurfaceOfChangeCmp, _higherHighsCmp, _expertTangentCmp,
						lowestStartCmp, highestStartCmp, lowestEndCmp, highestEndCmp,
						minSlopeCmp, maxSlopeCmp, toleranceCmp);

				if (conditionCheck != null) {

					Line<Integer, Double> expertTangent = (Line<Integer, Double>) _expertTangentCmp;

					//Has it been already found as row tangent
					boolean alreadyFoundTangent = realRowTangents.containsValue(expertTangent);
					if (expertTangent.isSet() && !alreadyFoundTangent) realRowTangents.put(date, expertTangent);

					//We don't have a 'for' reduction here as 'for' is actually the distance between extreme knots.
					//However we may want a confirmation reduction. The latter is check against the 'overPeriodRemanence' history of positive expertTangent and the latest actual one
					expertTangent = confirmationReduction(targetStock, realRowTangents, overPeriodRemanence, expertTangent, date, data.get(date), tolerance, outputs);
					boolean alreadyFoundReduced = expertTangentsStore.containsKey(expertTangent);

					//Update output if no overPeriodRemanence is running.
					if ((overPeriodRemanence == 0 || outputs.getValue(targetStock).get(date) == null)) {
						if (!alreadyFoundReduced) outputs.getValue(targetStock).put(date, expertTangent.isSet());
					}

					//Tangent output (may have been reduced by confirmation so we check now in the output set of tangents if it has been seen already)
					conditionCheck = expertTangent.isSet() && !alreadyFoundReduced;
					if (conditionCheck) {
						//Will map tangent to date for return if new knots are involved

						String currentLabel =
								expertTangentLabel +
								" at " + dateFormat.format(date) +
								" of " + this.getOperands().get(MAIN_POSITION).getReference() +
								" / slope " + expertTangent.getSlope() + " / afterglow " + overPeriodRemanence +
								//Test
								" / intersect " + expertTangent.getIntersect() +
								" / extremes " + expertTangent.getLowKnot() + "l, " + expertTangent.getHighKnot() + "h, " + expertTangent.getChange() + "ch, "+
								" / surface " + expertTangent.getSurfaceOfChange();

						expertTangentsStore.put(expertTangent, new TangentElement(expertTangent, currentLabel));
					}

					overPeriodFilling(targetStock, fullKeySet, overPeriodRemanence, date, conditionCheck, outputs);

				}

			}

		}

		if (true && !expertTangentsStore.isEmpty()) {
			expertTangentsStore.entrySet().stream().forEach(e -> {
				String label = e.getValue().getLabel(); // + " / ends " + dateFormat.format(e.getValue().getClosingDate());
				Line<Integer, Double> expertTangent = e.getValue().getLine();
				Integer fromElement = dateTimeKeys.indexOf(e.getValue().getLine().getxStart());
				Integer toElement = Math.min(dateTimeKeys.indexOf(e.getValue().getLine().getxEnd()) + 1, dateTimeKeys.size());
				SortedMap<Date, Double> expertTangentPoints = buildLineFor(fullKeyArray.subList(fromElement, toElement), expertTangent);
				outputs.getAdditionalOutputs().put(label, new DoubleMapValue(expertTangentPoints));
				outputs.getAdditionalOutputsTypes().put(label, Type.MULTI);
			});
		}

		if (LOGGER.isDebugEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.debug(
					"Condition '" + this.getReference() + "' returns this map " +
							outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b));
		}

		return outputs;
	}

	protected Line<Integer, Double> confirmationReduction(
			TargetStockInfo targetStock,
			SortedMap<Date, Line<Integer, Double>> realRowTangents, Integer overPeriodRemanence,
			Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance,
			BooleanMultiMapValue outputs) {
		return actualTangent;
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
