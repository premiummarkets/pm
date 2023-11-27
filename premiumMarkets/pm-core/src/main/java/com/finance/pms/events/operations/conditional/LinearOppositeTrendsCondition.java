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
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

public class LinearOppositeTrendsCondition extends LinearTrendsCondition implements OnSignalCondition  {

	private static final int LAST_PERIODS_IDX = 1;
	private static final int MAIN_POSITION = 3;
	private static final int SIGNAL_POSITION = 4;

	private LinearOppositeTrendsCondition() {
		super("unlk trend reg", "Opposite linear regression of two inputs for a defined period.");
	}

	public LinearOppositeTrendsCondition(String reference, String description) {
		super(reference, description,
				new NumberOperation("Time OVER which the condition will remain true"),
				new NumberOperation("Look back period FOR which the condition has to be true"),
				new StringOperation("Direction of the left operand trend"),
				new DoubleMapOperation("'trend regression' left operand (must be normed data)"),
				new DoubleMapOperation("'trend regression' right operand (must be normed data)"));
	}

	public LinearOppositeTrendsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer overPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(getLastPeriodsIndex())).getValue(targetStock).intValue();
		Direction direction = Direction.valueOf(((StringValue)inputs.get(2)).getValue(targetStock).toString().toLowerCase());
		List<SortedMap<Date, Double>> inputsOps = inputs.subList(getFirstDataInputIndex(), inputs.size()).stream().map(in -> ((NumericableMapValue) in).getValue(targetStock)).collect(Collectors.toList());

		return getBooleanMultiMapValue(targetStock, overPeriod, forPeriod, direction, 0.0d, inputsOps);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		Double firstSlope = (Double) ops[0];
		Double secondSlope = (Double) ops[1];
		Direction direction = (Direction) ops[2];
		//Double epsilon = (Double) ops[3];

		switch (direction) {
			case up :
				if (firstSlope < 0) return false;
				break;
			case down :
				if (firstSlope > 0) return false;
				break;
			case both :
				break;
			default:
				throw new RuntimeException(new NotSupportedException(direction.name()));
		}

		Double diff = Math.abs(firstSlope - secondSlope);
		return (diff >= Math.abs(firstSlope));

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

	@Override
	public int inputSignalPosition() {
		return SIGNAL_POSITION;
	}
}
