package com.finance.pms.events.operations.conditional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.quotations.QuotationsFactories;

/**
 * 'spanning' : does not make sense. As this condition is a status check in time not an event check (change of status) in time.
 * 'over'
 * 'for'
 */
@SuppressWarnings("rawtypes")
public abstract class LinearTrendsCondition extends Condition<Comparable> {

	private static MyLogger LOGGER = MyLogger.getLogger(LinearTrendsCondition.class);

	protected enum Direction {up, down, both, flat}

	protected LinearTrendsCondition(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	protected BooleanMultiMapValue getBooleanMultiMapValue(TargetStockInfo targetStock, Integer overPeriod, Integer forPeriod, Direction direction, Double epsilon, List<SortedMap<Date, Double>> inputsOps) {
		SortedSet<Date> fullKeySet = new TreeSet<>();
		if (forPeriod == null || forPeriod < 2) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 2 days for period to draw a linear regression.");
			return new BooleanMultiMapValue(fullKeySet, false);
		}
		inputsOps.stream().forEach(in -> fullKeySet.addAll(in.keySet()));

		String expertTangentLabel = forPeriod + " days regression";
		Map<String, TangentElement> expertTangentsResult = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentLabel;

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : fullKeySet) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -forPeriod).getTime();

			if (inputsOps.stream().allMatch(in -> lookBackPeriodStart.after(in.firstKey()))) {

				List<SortedMap<Date, Double>> opLookBackMaps = inputsOps.stream()
						.map(in -> MapUtils.subMapInclusive(in, lookBackPeriodStart, date))
						.collect(Collectors.toList());
				List<Double[]> slopesAIntersects = opLookBackMaps.stream()
						.map(lkBkMap -> linearReg(lkBkMap))
						.collect(Collectors.toList());

				List<Comparable> conditionCheckParams = slopesAIntersects.stream().map(sAi -> sAi[0]).collect(Collectors.toList());
				conditionCheckParams.add(direction);
				conditionCheckParams.add(epsilon);
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck((Comparable[]) conditionCheckParams.toArray());
				if (conditionCheck != null) {

					if (conditionCheck) {
						currentLabel = expertTangentLabel + " at " + dateFormat.format(date);
						for (int i = 0; i < opLookBackMaps.size(); i++) {
							SortedMap<Date, Double> opLinearAtDate = buildLineFor(opLookBackMaps.get(i), slopesAIntersects.get(i));
							expertTangentsResult.put(currentLabel + " of " + getOperands().get(getFirstDataInputIndex()+i).getReference() + " - slope " + slopesAIntersects.get(i)[0], new TangentElement(opLinearAtDate, date));
						}
					}

					if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
						outputs.getValue(targetStock).put(date, conditionCheck); //We don't have a 'for' reduction here as 'for' is actually the lookBack period for linear regression.
					}

					overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);

				}
			}

		}

		if (!expertTangentsResult.isEmpty()) {
			expertTangentsResult.entrySet().stream().forEach(e -> {
				String key = e.getKey();
				outputs.getAdditionalOutputs().put(key, new DoubleMapValue(e.getValue().getTangent()));
				outputs.getAdditionalOutputsTypes().put(key, Type.MULTI);
			});
		}

		return outputs;
	}

	private SortedMap<Date, Double> buildLineFor(SortedMap<Date, Double> lookBack, Double[] slopeAIntercept) {
		SortedMap<Date, Double> result = lookBack.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> slopeAIntercept[1] + slopeAIntercept[0] * (double) (e.getKey().getTime() - lookBack.firstKey().getTime()), (a, b) -> a, TreeMap::new));
		return result;
	}

	private Double[] linearReg(SortedMap<Date, Double> lookBack) {
		SimpleRegression simpleRegression = new SimpleRegression(true);
		long firstX = lookBack.firstKey().getTime();
		lookBack.keySet().stream().forEach(k -> {
			simpleRegression.addData( k.getTime() - firstX, lookBack.get(k));
		});
		return new Double[] {simpleRegression.getSlope(), simpleRegression.getIntercept()};
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i <= getLastPeriodsIndex(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

	protected abstract int getLastPeriodsIndex();

	protected abstract int getFirstDataInputIndex();

}
