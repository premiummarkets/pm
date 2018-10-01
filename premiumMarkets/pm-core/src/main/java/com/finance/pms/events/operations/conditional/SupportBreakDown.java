package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.SortedSet;

import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.Line;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;

//TODO add to grammar
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
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		@SuppressWarnings("unchecked")
		SortedMap<Integer, Double> lookBackData = ((SortedMap<Integer, Double>) ops[0]);

		@SuppressWarnings("unchecked")
		Boolean hasSupport = highLowSolver.flatLow( //data, smoothingPeriod, minimumNbDaysBetweenExtremes, _higherHighs, _expertTangent, lowestStart, highestStart, lowestEnd, highestEnd, tolerance)(
				lookBackData, (Integer) ops[1], (Integer) ops[2], (SortedMap<Integer, Double>) ops[3], (Line<Integer, Double>) ops[4],
				(Double) ops[5], (Double) ops[6], (Double) ops[11]);

		return hasSupport;
	}

	private Line<Integer, Double> reduceRawOutputConfirmation(
			SortedMap<Date,Line<Integer,Double>> realRowTangents, Integer overPeriodRemanence,
			Line<Integer,Double> actualTangent, Date actualDate, Double actualData, Double tolerance) {

		if (overPeriodRemanence > 0) {

			Calendar startRemananceCal = Calendar.getInstance();
			startRemananceCal.setTime(actualDate);
			QuotationsFactories.getFactory().incrementDate(startRemananceCal, -overPeriodRemanence-1);
			Date startRemanance = startRemananceCal.getTime();

			SortedMap<Date, Line<Integer, Double>> remananceLookBack = MapUtils.subMapInclusive(realRowTangents, startRemanance, actualDate);
			ListIterator<Line<Integer, Double>> values = new ArrayList<>(remananceLookBack.values()).listIterator(remananceLookBack.size());
			while (values.hasPrevious()) {
				Line<Integer, Double> previousTangent = values.previous();
				double tangentY = previousTangent.getIntersect() + previousTangent.getSlope() * (actualDate.getTime()/DAY_IN_MILLI - previousTangent.getxStart());
				if (actualData < tangentY*(1-tolerance)) return previousTangent;
			}

			return new Line<>();

		} else {
			return actualTangent;
		}

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
