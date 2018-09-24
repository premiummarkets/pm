package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public interface LinearOutputs {

	public static double DAY_IN_MILLI = 24*60*60*1000;

	public default SortedMap<Date, Double> buildLineFor(SortedMap<Date, Double> lookBack, Double[] slopeAIntercept) {
		SortedMap<Date, Double> result = lookBack.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> slopeAIntercept[1] + slopeAIntercept[0] * (double) (e.getKey().getTime()/DAY_IN_MILLI - lookBack.firstKey().getTime()/DAY_IN_MILLI), (a, b) -> a, TreeMap::new));
		return result;
	}

}
