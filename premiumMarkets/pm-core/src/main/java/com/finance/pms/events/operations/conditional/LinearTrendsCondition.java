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

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;
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

/**
 * 'spanning' : does not make sense. As this condition is a status check in time not an event check (change of status) in time.
 * 'over'
 * 'for'
 */
@SuppressWarnings("rawtypes")
public class LinearTrendsCondition extends Condition<Comparable> implements OnSignalCondition {

	private static MyLogger LOGGER = MyLogger.getLogger(LinearTrendsCondition.class);

	private static final int MAIN_POSITION = 4;
	private static final int SIGNAL_POSITION = 5;

	public LinearTrendsCondition() {
		super("trend regression", "Compares the linear regression of two inputs for a defined period.",
				new NumberOperation("Time OVER which the condition will remain true"),
				new NumberOperation("Look back period FOR which the condition has to be true"),
				new StringOperation("Should both trend up"),
				new StringOperation("Should both trend down"),
				new DoubleMapOperation("'trend regression' left operand (normed data)"),
				new DoubleMapOperation("'trend regression' right operand (normed data)"));
	}

	public LinearTrendsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		Integer overPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Boolean up = Boolean.valueOf(((StringValue)inputs.get(2)).getValue(targetStock));
		Boolean down = Boolean.valueOf(((StringValue)inputs.get(3)).getValue(targetStock));
		SortedMap<Date, Double> firstOp = ((NumericableMapValue) inputs.get(MAIN_POSITION)).getValue(targetStock);
		SortedMap<Date, Double> secondOp = ((NumericableMapValue) inputs.get(SIGNAL_POSITION)).getValue(targetStock);

		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		if (forPeriod == null || forPeriod < 2) {
			LOGGER.warn(this.getReference() + " can't be calculated, we need a minimum of 2 days for period to draw a linear regression.");
			return new BooleanMultiMapValue(fullKeySet, false);
		}
		fullKeySet.addAll(firstOp.keySet());
		fullKeySet.addAll(secondOp.keySet());

		String expertTangentLabel = forPeriod + " days regression";
		Map<String, TangentElement> expertTangentsResult = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentLabel = null;

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		for (Date date : fullKeySet) {

			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -forPeriod).getTime();

			if ( lookBackPeriodStart.after(firstOp.firstKey()) && lookBackPeriodStart.after(secondOp.firstKey()) ) {

				SortedMap<Date, Double> firstOpLookBackMap = MapUtils.subMapInclusive(firstOp, lookBackPeriodStart, date);
				Double[] firstOpSlopeAIntersect = linearReg(firstOpLookBackMap);

				SortedMap<Date, Double> secondOpLookBackMap = MapUtils.subMapInclusive(secondOp, lookBackPeriodStart, date);
				Double[] secondOpSlopeAIntersect = linearReg(secondOpLookBackMap);

				Boolean conditionCheck = conditionCheck(firstOpSlopeAIntersect[0], secondOpSlopeAIntersect[0], 0.5d, up, down);

				if (conditionCheck != null) {

					if (conditionCheck) {
						currentLabel = expertTangentLabel + " at " + dateFormat.format(date);
						SortedMap<Date, Double> firstOpLinearAtDate = buildLineFor(firstOpLookBackMap, firstOpSlopeAIntersect);
						expertTangentsResult.put(currentLabel + " of " + getOperands().get(MAIN_POSITION).getReference() + " - slope " + firstOpSlopeAIntersect[0], new TangentElement(firstOpLinearAtDate, date));
						SortedMap<Date, Double> secondOpLinearAtDate = buildLineFor(secondOpLookBackMap, secondOpSlopeAIntersect);
						expertTangentsResult.put(currentLabel + " of " + getOperands().get(SIGNAL_POSITION).getReference() + " - slope " + secondOpSlopeAIntersect[0], new TangentElement(secondOpLinearAtDate, date));
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

	@Override
	public Boolean conditionCheck(Comparable... ops) {

		Double firstSlope = (Double) ops[0];
		Double secondSlope = (Double) ops[1];
		Double epsilon = (Double) ops[2];

		Boolean checkUp = (Boolean) ops[3];
		Boolean checkDown = (Boolean) ops[4];

		if (!checkUp && (firstSlope > 0 || secondSlope > 0)) return false;
		if (!checkDown && (firstSlope < 0 || secondSlope < 0)) return false;

		Double diff = Math.abs(firstSlope-secondSlope);
		Double largest = Math.max(Math.abs(firstSlope), Math.abs(secondSlope));
		return (diff <= largest*epsilon);
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
		for (int i = 0; i < MAIN_POSITION; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}

	@Override
	public int inputSignalPosition() {
		return SIGNAL_POSITION;
	}
}
