package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.*;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class LinearSimilarTrendsCondition extends LinearTrendsCondition  implements OnSignalCondition {

    private static final int LAST_PERIODS_IDX = 1;
    private static final int MAIN_POSITION = 4;
    private static final int SIGNAL_POSITION = 5;

    public LinearSimilarTrendsCondition() {
        this("like trend regression", "Similar linear regression of two inputs for a defined period.");
    }

    public LinearSimilarTrendsCondition(String reference, String description) {
        super(reference, description,
                new NumberOperation("Time OVER which the condition will remain true"),
                new NumberOperation("Look back period FOR which the condition has to be true"),
                new StringOperation("Direction of the trend"),
                new NumberOperation("Max slope epsilon when comparing two trend lines"),
                new DoubleMapOperation("'trend regression' left operand (normed data)"),
                new DoubleMapOperation("'trend regression' right operand (normed data)"));
    }

    @Override
    public BooleanMultiMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

        Integer overPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
        Integer forPeriod = ((NumberValue) inputs.get(getLastPeriodsIndex())).getValue(targetStock).intValue();
        Direction direction = Direction.valueOf(((StringValue)inputs.get(2)).getValue(targetStock));
        Double epsilon = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue();
        List<SortedMap<Date, Double>> inputsOps = inputs.subList(getFirstDataInputIndex(), inputs.size()).stream().map(in -> ((NumericableMapValue) in).getValue(targetStock)).collect(Collectors.toList());

        return getBooleanMultiMapValue(targetStock, overPeriod, forPeriod, direction, epsilon, inputsOps);
    }

    @Override
    public Boolean conditionCheck(Comparable... ops) {

        Double firstSlope = (Double) ops[0];
        Double secondSlope = (Double) ops[1];
        Direction direction = (Direction) ops[2];
        Double epsilon = (Double) ops[3];

        switch (direction) {
            case up :
                if (firstSlope < 0) return false;
                break;
            case down :
                if (firstSlope > 0) return false;
                break;
            case both :
                break;
        }

        Double diff = Math.abs(firstSlope-secondSlope);
        Double largest = Math.max(Math.abs(firstSlope), Math.abs(secondSlope));
        return (diff <= largest*epsilon);

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