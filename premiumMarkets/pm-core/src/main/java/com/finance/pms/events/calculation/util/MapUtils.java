package com.finance.pms.events.calculation.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.events.scoring.functions.StatsFunction;

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

	public static <K extends Comparable<K>, V> SortedMap<K,V>
	subMapInclusive(SortedMap<K,V> map, K from, K to) {

		//Bypassing the potential from restrictive range
		from = (map.firstKey().compareTo(from) > 0)? map.firstKey(): from;

		if(to == null) return map.tailMap(from);

		//What appears at key "to" or later?
		Iterator<K> keys = map.tailMap(to).keySet().iterator();

		//Nothing, just take tail map.
		if(!keys.hasNext()) return map.tailMap(from);

		K key = keys.next();

		//The first item found isn't to so regular submap will work
		if(!to.equals(key)) return map.subMap(from, to);

		//to is in the map and here key == to

		//it is not the last key
		if(keys.hasNext()) return map.subMap(from, keys.next());

		//it is the last key
		return map.tailMap(from);
	}

	public static SortedMap<Date, Double> movingStat(SortedMap<Date, Double> map, Date startDate, int period, StatsFunction apacheStats) {

		ArrayList<Date> keySet = new ArrayList<Date>(map.tailMap(startDate).keySet());

		final TreeMap<Date, Double> movingStats =
				IntStream.range(period, keySet.size())
				.mapToObj(i -> i)
				.collect(Collectors.toMap(
						endWindow -> keySet.get(endWindow),
						endWindow -> {
							Integer startWindow = endWindow - period;
							SortedMap<Date,Double> values =
									MapUtils.subMapInclusive(map, keySet.get(startWindow), keySet.get(endWindow)).keySet()
									.stream()
									.filter(k -> !Double.isNaN(map.get(k)))
									.collect(Collectors.toMap(k -> k, k -> map.get(k), (a, b) -> a, TreeMap<Date,Double>::new));
							return (Double) apacheStats.mEvaluate(values);
						},
						(a, b) -> a, TreeMap<Date,Double>::new));


		TreeMap<Date, Double> noNaNMovingStats = movingStats.keySet()
				.stream()
				.filter(k -> !Double.isNaN(movingStats.get(k)))
				.collect(Collectors.toMap(k -> k, k -> movingStats.get(k), (a, b) -> a, TreeMap<Date,Double>::new));
		
		Date firstValidResult = keySet.get(period);
		return subMapInclusive(noNaNMovingStats, firstValidResult, map.lastKey());
	}

}
