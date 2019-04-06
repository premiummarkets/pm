package com.finance.pms.events.calculation.util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.finance.pms.events.scoring.functions.ApacheStats;

public class MapUtils {

	public static double[] calculateMinMax(SortedMap<Date, double[]> subD, int outputIndex) {

		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;

		for (Date date : subD.keySet()) {
			double value = subD.get(date)[outputIndex];
			if (value >= max) max = value;
			if (value <= min) min = value;
		}

		return new double[] {min,max};
	}

	public static <K, V> SortedMap<K,V>
	subMapInclusive(SortedMap<K,V> map, K from, K to) {
		if(to == null) return map.tailMap(from);

		//What appears at key "to" or later?
		Iterator<K> keys = map.tailMap(to).keySet().iterator();

		//Nothing, just take tail map.
		if(!keys.hasNext()) return map.tailMap(from);

		K key = keys.next();

		//The first item found isn't to so regular submap will work
		if(!to.equals(key)) return map.subMap(from, to);

		//to is in the map

		//it is not the last key
		if(keys.hasNext()) return map.subMap(from, keys.next());

		//it is the last key
		return map.tailMap(from);
	}

	public static <KEY> SortedMap<KEY, Double> movingStat(SortedMap<KEY, Double> map, Function<KEY, KEY> startWindowKFunc, ApacheStats apacheStats) {

		TreeMap<KEY, Double> averageAnnualisedVolatility =
				map.keySet().stream()
				.collect(Collectors.toMap(
						endWindowK -> endWindowK,
						endWindowK -> {
							KEY startWindowK = startWindowKFunc.apply(endWindowK);
							Collection<Double> values = 
									MapUtils.subMapInclusive(map, startWindowK, endWindowK).values()
									.stream()
									.filter(v -> !Double.isNaN(v)).collect(Collectors.toList());
							return (Double) apacheStats.sEvaluate(values);
						},
						(a, b) -> a, TreeMap<KEY,Double>::new));

		return averageAnnualisedVolatility;
	}

}
