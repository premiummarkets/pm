package com.finance.pms.events.operations.conditional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.Line;

/**
 * 'spanning' : does not make sense. As this condition is a status check in time not an event check (change of status) in time.
 * 'over'
 * 'for'
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({LinearDirectedTrendsCondition.class, LinearOppositeTrendsCondition.class, LinearSimilarTrendsCondition.class})
public abstract class LinearTrendsCondition extends DiscreteLinearOutputsCondition {

	private static MyLogger LOGGER = MyLogger.getLogger(LinearTrendsCondition.class);

	protected enum Direction {up, down, both, flat}

	protected LinearTrendsCondition(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	protected BooleanMultiMapValue getBooleanMultiMapValue(TargetStockInfo targetStock, Integer overPeriod, Integer forPeriod, Direction direction, Double epsilon, List<SortedMap<Date, Double>> inputsOps) {
		NavigableSet<Date> fullKeySet = new TreeSet<>();
		if (forPeriod == null || forPeriod < 2) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 2 days for period to draw a linear regression.");
			return new BooleanMultiMapValue(fullKeySet, false);
		}

		inputsOps.stream().forEach(in -> fullKeySet.addAll(in.keySet()));
		List<Date> fullKeyArray = new ArrayList<>(fullKeySet);
		List<Integer> dateTimeKeys = fullKeySet.stream().map(d -> Integer.valueOf((int) (d.getTime()/DAY_IN_MILLI))).collect(Collectors.toList());

		//		//Normalizing of Y to X //FIXME auto normalizing would require putting the inputs in different groups for charting as they may have different magnitude
		//		Normalizer<Double> normalizer = new Normalizer<>(Double.class, fullKeySet.first(), fullKeySet.last(), 0, forPeriod);
		//		List<SortedMap<Date, Double>> normInputsOps = inputsOps.stream().map(in -> normalizer.normalised(in)).collect(Collectors.toList());
		List<SortedMap<Date, Double>> normInputsOps = inputsOps;

		//Main loop
		String expertTangentLabel = forPeriod + " days " + direction + " regression";
		Map<Line<Integer, Double>, TangentElement> expertTangentsResult = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : fullKeySet) {

			try {
				Calendar currentDate = Calendar.getInstance();
				currentDate.setTime(date);
				Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(targetStock.getStock(), targetStock.getQuotationsDataTypes(), currentDate, -forPeriod).getTime();

				//Calculating trends for each inputs
				if (normInputsOps.stream().allMatch(in -> lookBackPeriodStart.after(in.firstKey()))) {

					List<SortedMap<Date, Double>> opLookBackMaps = normInputsOps.stream()
							.map(in -> MapUtils.subMapInclusive(in, lookBackPeriodStart, date))
							.collect(Collectors.toList());
					List<Line<Integer, Double>> tangentLines = opLookBackMaps.stream()
							.map(lkBkMap -> linearReg(lkBkMap))
							.collect(Collectors.toList());

					List<Comparable> conditionCheckParams = tangentLines.stream().map(line -> line.getSlope()).collect(Collectors.toList());
					conditionCheckParams.add(direction);
					conditionCheckParams.add(epsilon);
					@SuppressWarnings("unchecked")
					Boolean conditionCheck = conditionCheck((Comparable[]) conditionCheckParams.toArray(new Comparable[conditionCheckParams.size()]));
					if (conditionCheck != null) {

						if (conditionCheck) {
							for (int i = 0; i < opLookBackMaps.size(); i++) {
								String currentLabel =
										expertTangentLabel +
												" at " + dateFormat.format(date) +
												" of " + getOperands().get(getFirstDataInputIndex()+i).getReference() +
												" / slope " + tangentLines.get(i).getSlope() + " / afterglow " + overPeriod;
								expertTangentsResult.put(tangentLines.get(i), new TangentElement(tangentLines.get(i), currentLabel));
							}
						}

						if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
							outputs.getValue(targetStock).put(date, conditionCheck); //We don't have a 'for' reduction here as 'for' is actually the lookBack period for linear regression.
						}

						overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);

					}
				}
			} catch (NotEnoughDataException e) {
				LOGGER.error(e);
			}

		}

		if (!expertTangentsResult.isEmpty()) {
			expertTangentsResult.entrySet().stream().forEach(e -> {
				String key = e.getValue().getLabel();
				Integer fromElement = dateTimeKeys.indexOf(e.getValue().getLine().getxStart());
				Integer toElement = dateTimeKeys.indexOf(e.getValue().getLine().getxEnd());
				SortedMap<Date, Double> expertTangentPoints = buildLineFor(fullKeyArray.subList(fromElement, toElement), e.getValue().getLine());
				outputs.getAdditionalOutputs().put(key, new DoubleMapValue(expertTangentPoints));
				outputs.getAdditionalOutputsTypes().put(key, Type.MULTI);
			});
		}

		return outputs;
	}

	private Line<Integer, Double> linearReg(SortedMap<Date, Double> lookBack) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		double firstX = lookBack.firstKey().getTime()/DAY_IN_MILLI;
		lookBack.keySet().stream().forEach(k -> {
			simpleRegression.addData(k.getTime()/DAY_IN_MILLI - firstX, lookBack.get(k));
		});

		Line<Integer, Double> line = new Line<>();
		line.setIntersect((int) firstX, simpleRegression.getIntercept());
		line.setSlope(simpleRegression.getSlope());
		line.setxEnd((int) (lookBack.lastKey().getTime()/DAY_IN_MILLI));
		return line;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		
		return IntStream.range(0, getLastPeriodsIndex() +1 )
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

	protected abstract int getLastPeriodsIndex();

	protected abstract int getFirstDataInputIndex();

}
