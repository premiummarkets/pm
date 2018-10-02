package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.Line;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.function.Function;

//TODO add to grammar
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
		//return actualData*(1+tolerance) < tangentY;
		//l -> r -> t -> l*(1 + t) < r
		return HighLowSolver.cutsAboveSupport;
	}

}
