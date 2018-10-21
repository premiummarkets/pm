package com.finance.pms.events.operations.conditional;

import static com.finance.pms.events.operations.conditional.LinearOutputs.DAY_IN_MILLI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.function.Function;

import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.Line;

public interface SupportBreak {

	Function<Double, Function<Double, Function<Double, Boolean>>> cutsAboveSupport = actual -> threshold -> tolerance -> {
		return actual > threshold && ((actual == 0 && threshold == 0)?0d:Math.abs(actual - threshold)/Math.max(Math.abs(actual), Math.abs(threshold))) > tolerance;
	};
	Function<Double, Function<Double, Function<Double, Boolean>>> cutsBelowSupport = actual -> threshold -> tolerance -> {
		return actual < threshold && ((actual == 0 && threshold == 0)?0d:Math.abs(actual - threshold)/Math.max(Math.abs(actual), Math.abs(threshold))) > tolerance;
	};

    default Line<Integer, Double> reduceRawOutputConfirmation(
            SortedMap<Date, Line<Integer, Double>> realRowTangents, Integer overPeriodRemanence,
            Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance) {
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
                if (breakThroughCondition().apply(actualData).apply(tangentY).apply(tolerance)) return previousTangent;
            }

            return new Line<>();

        } else {
            return actualTangent;
        }
    }

    Function<Double, Function<Double, Function<Double, Boolean>>> breakThroughCondition();

}
