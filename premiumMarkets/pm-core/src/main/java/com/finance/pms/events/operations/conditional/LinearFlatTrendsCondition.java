package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.*;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class LinearFlatTrendsCondition extends LinearTrendsCondition  implements UnaryCondition {

    private static final int LAST_PERIODS_IDX = 1;
    private static final int MAIN_POSITION = 3;

    public LinearFlatTrendsCondition() {
        this("flat trend regression", "Flat linear regression of an input for a defined period.");
    }

    public LinearFlatTrendsCondition(String reference, String description) {
        super(reference, description,
                new NumberOperation("Time OVER which the condition will remain true"),
                new NumberOperation("Look back period FOR which the condition has to be true"),
                new NumberOperation("Max slope epsilon when comparing two trend lines"),
                new DoubleMapOperation("'flat trend regression' left operand (normed data)"));
    }

    @Override
    public BooleanMultiMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

        Integer overPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
        Integer forPeriod = ((NumberValue) inputs.get(getLastPeriodsIndex())).getValue(targetStock).intValue();
        Double epsilon = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
        List<SortedMap<Date, Double>> inputsOps = inputs.subList(getFirstDataInputIndex(), inputs.size()).stream().map(in -> ((NumericableMapValue) in).getValue(targetStock)).collect(Collectors.toList());

        return getBooleanMultiMapValue(targetStock, overPeriod, forPeriod, Direction.flat, epsilon, inputsOps);
    }

    @Override
    public Boolean conditionCheck(Comparable... ops) {

        Double firstSlope = (Double) ops[0];
        Double epsilon = (Double) ops[1];

        Double diff = Math.abs(firstSlope - 0d);
        return (diff <= firstSlope*epsilon);

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
