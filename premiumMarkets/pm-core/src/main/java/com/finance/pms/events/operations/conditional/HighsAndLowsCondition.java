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
import java.util.stream.IntStream;

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
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.HighLowSolver.Greed;
import com.finance.pms.events.scoring.functions.Line;


//TODO review grammar 'for' is now surface of change + 'slope within' is tolerance (always positive)

/**
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'spanning'		:	is the period we look back into
 * 'over' 			: 	is remanence/persistence of the tangent from its trigger date
 * 'for' 			: 	is minimum surface of change
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends DiscreteLinearOutputsCondition implements UnaryCondition {

	private static final int THRESHOLDS_IDX = 6;
	private static final int MAIN_POSITION = 13;

	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private HighsAndLowsCondition() {
	}

	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Spanning period as look back period in days"),
				new NumberOperation("Over period as remanence/persistence of the divergence from its trigger date"),
				new NumberOperation("Surface minimum within the divergence line and actual supported knots"),
				new NumberOperation("Smoothing period for sporadic peaks and troughs mitigation"),
				new StringOperation("Greedy, Full or Lazy lookback finds?"),
				new StringOperation("High and Low solver type : Smooth, Trend, ..."),
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

	public HighLowSolver getHighLowSolver(String highLowSolverType) {
		return HighLowSolver.getHighLowSolverFor(highLowSolverType);
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
		Greed greed = Greed.valueOf(((StringValue) inputs.get(4)).getValue(targetStock).toString().toUpperCase());
		String highLowSolverType = ((StringValue) inputs.get(5)).getValue(targetStock);

		Double lowestStart = ((NumberValue) inputs.get(THRESHOLDS_IDX)).getValue(targetStock).doubleValue();
		Double highestStart = ((NumberValue) inputs.get(THRESHOLDS_IDX+1)).getValue(targetStock).doubleValue();
		Double lowestEnd = ((NumberValue) inputs.get(THRESHOLDS_IDX+2)).getValue(targetStock).doubleValue();
		Double highestEnd = ((NumberValue) inputs.get(THRESHOLDS_IDX+3)).getValue(targetStock).doubleValue();

		Double minSlope = ((NumberValue) inputs.get(THRESHOLDS_IDX+4)).getValue(targetStock).doubleValue();
		Double maxSlope = ((NumberValue) inputs.get(THRESHOLDS_IDX+5)).getValue(targetStock).doubleValue();

		Double tolerance = ((NumberValue) inputs.get(THRESHOLDS_IDX+6)).getValue(targetStock).doubleValue();

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
		SortedMap<Integer, Double> fullDataDayNumIndexedMap = fullKeyArray.stream().collect(Collectors.toMap(d -> Integer.valueOf((int) (d.getTime()/DAY_IN_MILLI)), d -> data.get(d), (a, b) -> b, TreeMap::new));
		List<Integer> fullDataDayNumArray = new ArrayList<>(fullDataDayNumIndexedMap.keySet());

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		SortedMap<Date, ArrayList<Line<Integer, Double>>> realRowTangents = new TreeMap<>();
		for (Date date : fullKeyArray) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbDays).getTime();

			if ( lookBackPeriodStart.after(dataFirstKey) ) {

				if (MapUtils.subMapInclusive(data, lookBackPeriodStart, date).size() < 4) continue;

				Comparable dataLookBackTimeCmp =
						new ComparableSortedMap<>(
								MapUtils
								.subMapInclusive(fullDataDayNumIndexedMap, Integer.valueOf((int) (lookBackPeriodStart.getTime()/DAY_IN_MILLI)), Integer.valueOf((int) (date.getTime()/DAY_IN_MILLI)))
								);
				Comparable lookBackSmoothingPeriodCmp = lookBackSmoothingPeriod;
				Comparable minimumSurfaceOfChangeCmp = minimumSurfaceOfChange;
				Comparable greedCmp = greed;
				Comparable _higherHighsCmp = new ComparableSortedMap<Integer, Double>();
				Comparable _expertTangentCmp = new ComparableArray<Line<Integer, Double>>();
				Comparable lowestStartCmp = lowestStart;
				Comparable highestStartCmp = highestStart;
				Comparable lowestEndCmp = lowestEnd;
				Comparable highestEndCmp = highestEnd;
				Comparable minSlopeCmp = minSlope;
				Comparable maxSlopeCmp = maxSlope;
				Comparable toleranceCmp = tolerance;
				Comparable highLowSolverTypeCmp = highLowSolverType;

				Boolean conditionCheck = conditionCheck(
						dataLookBackTimeCmp,
						lookBackSmoothingPeriodCmp, minimumSurfaceOfChangeCmp, greedCmp,
						_higherHighsCmp, _expertTangentCmp,
						lowestStartCmp, highestStartCmp, lowestEndCmp, highestEndCmp,
						minSlopeCmp, maxSlopeCmp, toleranceCmp,
						highLowSolverTypeCmp);

				if (conditionCheck != null) {

					ArrayList<Line<Integer, Double>> expertTangents = (ArrayList<Line<Integer, Double>>) _expertTangentCmp;

					conditionCheck = expertTangents.stream().map( expertTangent -> {
						//Has it been already found as row tangent
						ArrayList<Line<Integer, Double>> realRawTgtsAtDate = realRowTangents.get(date);
						boolean alreadyFoundTangent = realRawTgtsAtDate != null && realRawTgtsAtDate.contains(expertTangent);
						if (expertTangent.isSet() && !alreadyFoundTangent) {
							if (realRawTgtsAtDate == null) realRawTgtsAtDate = new ArrayList<>();
							realRawTgtsAtDate.add(expertTangent);
							realRowTangents.put(date, realRawTgtsAtDate);
						}

						//We don't have a 'for' reduction here as 'for' is actually the surface of change to the tangent.
						//However we may want a confirmation reduction. The latter is check against the 'overPeriodRemanence' history of positive expertTangent and the latest actual one. 
						expertTangent = confirmationReduction(targetStock, realRowTangents, overPeriodRemanence, expertTangent, date, data.get(date), tolerance);
						boolean alreadyFoundReduced = expertTangentsStore.containsKey(expertTangent);

						//Update output if no overPeriodRemanence is running.
						Boolean outputAtDate = outputs.getValue(targetStock).get(date);
						if ( !alreadyFoundReduced && (outputAtDate == null || !outputAtDate ) ) {
							outputs.getValue(targetStock).put(date, expertTangent.isSet());
						}

						//Tangent output (may have been reduced by confirmation so we check now in the output set of tangents if it has been seen already)
						Boolean inConditionCheck = expertTangent.isSet() && !alreadyFoundReduced;
						if (inConditionCheck) {
							String currentLabel =
									expertTangentLabel +
									" at " + dateFormat.format(date) +
									" of " + this.getOperands().get(MAIN_POSITION).getReference() +
									" / slope " + expertTangent.getSlope() + " / afterglow " + overPeriodRemanence +
									//Test
									" / intersect " + expertTangent.getIntersect() +
									" / extremes " + expertTangent.getLowKnot() + "l, " + expertTangent.getHighKnot() + "h, " + expertTangent.getRelativeDifference() + "rd, "+
									" / surface " + expertTangent.getSurfaceOfChange();
							expertTangentsStore.put(expertTangent, new TangentElement(expertTangent, currentLabel));
						}
						return inConditionCheck;
					})
					.reduce((r, e) -> e || r).orElse(false);

					overPeriodFilling(targetStock, fullKeySet, overPeriodRemanence, date, conditionCheck, outputs);

				}

			}

		}

		if (true && !expertTangentsStore.isEmpty()) {
			expertTangentsStore.entrySet().stream().forEach(e -> {
				String label = e.getValue().getLabel(); // + " / ends " + dateFormat.format(e.getValue().getClosingDate());
				Line<Integer, Double> expertTangent = e.getValue().getLine();
				Integer fromElement = fullDataDayNumArray.indexOf(e.getValue().getLine().getxStart());

				//Integer toElement = Math.min(dateTimeKeys.indexOf(e.getValue().getLine().getxEnd()) + 1, dateTimeKeys.size());
				//Now drawing the over period as well.
				Calendar endOverPeriodCal = Calendar.getInstance();
				int endDateIndex = fullDataDayNumArray.indexOf(e.getValue().getLine().getxEnd()) + 1 + 1; //+1 for inclusion +1 for detection lag
				endOverPeriodCal.setTime(fullKeyArray.get((endDateIndex < fullKeyArray.size())?endDateIndex:fullKeyArray.size()-1));
				QuotationsFactories.getFactory().incrementDate(endOverPeriodCal, +overPeriodRemanence);
				Date endOverPeriod = endOverPeriodCal.getTime();
				Integer toElement = fullDataDayNumArray.indexOf(MapUtils.subMapInclusive(fullDataDayNumIndexedMap, 0, Integer.valueOf((int) (endOverPeriod.getTime()/DAY_IN_MILLI))).lastKey());

				SortedMap<Date, Double> expertTangentPoints = buildLineFor(fullKeyArray.subList(fromElement, toElement), expertTangent);
				outputs.getAdditionalOutputs().put(label, new DoubleMapValue(expertTangentPoints));
				outputs.getAdditionalOutputsTypes().put(label, Type.MULTI);
			});
		}

		if (LOGGER.isInfoEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' for '" + this.getOperands().get(MAIN_POSITION).getReference() + "' returns this map \n" +
							outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).orElse("No Data"));
		}

		return outputs;
	}

	protected Line<Integer, Double> confirmationReduction(
			TargetStockInfo targetStock,
			SortedMap<Date, ArrayList<Line<Integer, Double>>> realRowTangents, Integer overPeriodRemanence,
			Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance) {
		return actualTangent;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		
		return IntStream.range(0, THRESHOLDS_IDX)
		.map(i -> {
			Operation numberOperand = getOperands().get(i);
			if (numberOperand instanceof NumberOperation) {
				return  ((NumberValue) numberOperand.getParameter()).getValue(null).intValue();
			} else {
				return getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift);
			}
		})
		.reduce(0, (r, e) -> r + e);
	}

}
