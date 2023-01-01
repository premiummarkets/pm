package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.function.Function;

import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.scoring.functions.Line;

public class SupportBreakDown extends HighsAndLowsCondition implements SupportBreak {

	public SupportBreakDown() {
		super("break down low", "True when the time series breaks down a flat low support line.");
	}

	public SupportBreakDown(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		@SuppressWarnings("unchecked")
		SortedMap<Integer, Double> lookBackData = ((SortedMap<Integer, Double>) ops[0]);

		@SuppressWarnings("unchecked")
		Boolean hasSupport = getHighLowSolver((String) ops[13]).flatLow(
				lookBackData, (Integer) ops[1], (Double) ops[2],
				(SortedMap<Integer, Double>) ops[3], (List<Line<Integer, Double>>) ops[4],
				(Double) ops[5], (Double) ops[6], (Double) ops[12]);

		return hasSupport;
	}

	@Override
	public Function<Double, Function<Double, Function<Double, Boolean>>> breakThroughCondition() {
		return cutsBelowSupport;
	}

	@Override
	protected Line<Integer, Double> confirmationReduction(
			TargetStockInfo targetStock,
			SortedMap<Date, ArrayList<Line<Integer, Double>>> realRowTangents, Integer overPeriodRemanence,
			Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance) throws NotEnoughDataException {
		Line<Integer, Double> reducedTangent = reduceRawOutputConfirmation(targetStock, realRowTangents, overPeriodRemanence, actualTangent, actualDate, actualData, tolerance);
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
