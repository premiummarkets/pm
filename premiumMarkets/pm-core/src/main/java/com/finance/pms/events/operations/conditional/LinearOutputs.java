package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.scoring.functions.Line;

import java.util.*;
import java.util.stream.Collectors;

public interface LinearOutputs {

	double DAY_IN_MILLI = 24*60*60*1000;

	default SortedMap<Date, Double> buildLineFor(Collection<Date> lookBackDates, Line<Integer, Double> line) {
		double firstX = lookBackDates.iterator().next().getTime() / DAY_IN_MILLI;
		SortedMap<Date, Double> result = lookBackDates.stream()
				.collect(Collectors.toMap(
						e -> e,
						e -> line.getIntersect() + line.getSlope() * (e.getTime()/DAY_IN_MILLI - firstX),
						(a, b) -> a, TreeMap::new));
		return result;
	}

}
