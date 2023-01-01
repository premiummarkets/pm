package com.finance.pms.events.operations.conditional;

import static com.finance.pms.events.operations.conditional.DiscreteLinearOutputsCondition.DAY_IN_MILLI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.TargetStockInfo;
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
    		TargetStockInfo targetStock,
            SortedMap<Date, ArrayList<Line<Integer, Double>>> realRowTangents, Integer overPeriodRemanence,
            Line<Integer, Double> actualTangent, Date actualDate, Double actualData, Double tolerance) throws NotEnoughDataException {
        if (overPeriodRemanence > 0) {

            Calendar startRemananceCal = Calendar.getInstance();
            startRemananceCal.setTime(actualDate);
            QuotationsFactories.getFactory().incrementDate(targetStock.getStock(), targetStock.getQuotationsDataTypes(), startRemananceCal, -overPeriodRemanence-1);
            Date startRemanance = startRemananceCal.getTime();

            SortedMap<Date, ArrayList<Line<Integer, Double>>> remananceLookBack = MapUtils.subMapInclusive(realRowTangents, startRemanance, actualDate);
            ListIterator<Line<Integer, Double>> remananceLookBackExpTangs = new ArrayList<>(remananceLookBack.values().stream().flatMap(a -> a.stream()).collect(Collectors.toList())).listIterator(remananceLookBack.size());
            while (remananceLookBackExpTangs.hasPrevious()) {
                Line<Integer, Double> previousTangent = remananceLookBackExpTangs.previous();
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
