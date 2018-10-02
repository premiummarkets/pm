package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.Line;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.function.Function;

public class SupportBreakUp extends HighsAndLowsCondition implements SupportBreak {

	HighLowSolver highLowSolver = new SmoothHighLowSolver();

	public SupportBreakUp() {
		super("break up high", "True when the time series breaks up a flat high support line.");
	}

	public SupportBreakUp(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		@SuppressWarnings("unchecked")
		SortedMap<Integer, Double> lookBackData = ((SortedMap<Integer, Double>) ops[0]);

		@SuppressWarnings("unchecked")
		Boolean hasSupport = highLowSolver.flatHigh( //data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent, lowestStart, highestStart, lowestEnd, highestEnd, tolerance)(
				lookBackData, (Integer) ops[1], (Integer) ops[2], (SortedMap<Integer, Double>) ops[3], (Line<Integer, Double>) ops[4],
				(Double) ops[5], (Double) ops[6], (Double) ops[11]);

		return hasSupport;
	}

	@Override
	public Function<Double, Function<Double, Function<Double, Boolean>>> breakThroughCondition() {
		return HighLowSolver.cutsAboveSupport;
	}

	@Override
    protected Line<Integer, Double> confirmationReduction(
            TargetStockInfo targetStock,
            SortedMap<Date, Line<Integer, Double>> realRowTangents, Integer overPeriodRemanence,
            Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance,
            BooleanMultiMapValue outputs) {
        Line<Integer, Double> reducedTangent = reduceRawOutputConfirmation(realRowTangents, overPeriodRemanence, actualTangent, actualDate, actualData, tolerance);
        //TODO fill in remaining remanance period??
        return reducedTangent;
    }

	@Override
    protected void overPeriodFilling(
            TargetStockInfo targetStock, SortedSet<Date> fullKeySet,
            Integer overPeriod, Date actualDate, Boolean conditionCheck, BooleanMapValue outputs) {
        //Nothing as the remanance period is used for confirmation
    }

}
