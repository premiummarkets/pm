package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.transaction.NotSupportedException;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

public class LinearDirectedTrendsCondition extends LinearTrendsCondition implements UnaryCondition {

	private static final int LAST_PERIODS_IDX = 1;
	private static final int MAIN_POSITION = 4;

	private LinearDirectedTrendsCondition() {
		this("trend reg", "Linear regression of an input for a defined period.");
	}

	public LinearDirectedTrendsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Time OVER which the condition will remain true"),
				new NumberOperation("Look back period FOR which the condition has to be true"),
				new StringOperation("Direction of the left operand trend"),
				new NumberOperation("Max slope epsilon error when comparing two trend lines"),
				new DoubleMapOperation("'trend regression' left operand"));
	}

	public LinearDirectedTrendsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer overPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(getLastPeriodsIndex())).getValue(targetStock).intValue();
		Direction direction = Direction.valueOf(((StringValue)inputs.get(2)).getValue(targetStock).toString().toLowerCase());
		Double epsilon = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue();
		List<SortedMap<Date, Double>> inputsOps = inputs.subList(getFirstDataInputIndex(), inputs.size())
				.stream().map(in -> ((NumericableMapValue) in).getValue(targetStock))
				.collect(Collectors.toList());

		return getBooleanMultiMapValue(targetStock, overPeriod, forPeriod, direction, epsilon, inputsOps);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		Double firstSlope = (Double) ops[0];
		Direction direction = (Direction) ops[1];
		Double epsilon = (Double) ops[2];

		Double slopeAbs = Math.abs(firstSlope);
		switch (direction) {
			case up :
				return (firstSlope >= epsilon);
			case down :
				return (firstSlope <= epsilon);
			case flat :
				return slopeAbs <= epsilon;
			case both :
				return slopeAbs >= epsilon;
			default:
				throw new RuntimeException(new NotSupportedException(direction.name()));
		}

	}

	@Override
	protected int getLastPeriodsIndex() {
		return LAST_PERIODS_IDX;
	}

	@Override
	protected int getFirstDataInputIndex() {
		return MAIN_POSITION;
	}

	@Override
	public int mainInputPosition() {
		return MAIN_POSITION;
	}
}
