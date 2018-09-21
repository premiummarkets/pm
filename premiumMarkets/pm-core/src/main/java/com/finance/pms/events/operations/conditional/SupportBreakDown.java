package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;

import java.util.ArrayList;
import java.util.SortedMap;

public class SupportBreakDown extends HighsAndLowsCondition {

    HighLowSolver highLowSolver = new SmoothHighLowSolver();

    public SupportBreakDown() {
        super("break down low", "True when the time series breaks down a flat low support line.");
    }

    public SupportBreakDown(ArrayList<Operation> operands, String outputSelector) {
        this();
        setOperands(operands);
    }

    @Override
    public Boolean conditionCheck(Comparable... ops) {
        Double[] lookBackData = ((ArrayList<Double>) ops[0]).toArray(new Double[0]);
        SortedMap<Integer, Double> higherHighs = (SortedMap<Integer, Double>) ops[3];
        ArrayList<Double> tangent = (ArrayList<Double>) ops[4];
        Boolean hasSupport = highLowSolver.higherLow(
                lookBackData, (Integer) ops[1], (Integer) ops[2], higherHighs, tangent,
                (Double) ops[5], (Double) ops[6], (Double) ops[7], (Double) ops[8],
                (Double) ops[9], (Double) ops[10]);

        Boolean isBreakingDown = false;
        for(int i = higherHighs.lastKey(); i < lookBackData.length; i++) {
            isBreakingDown = lookBackData[i] < tangent.get(i);
            if (isBreakingDown) break;
        }

        return hasSupport && isBreakingDown;
    }
}
