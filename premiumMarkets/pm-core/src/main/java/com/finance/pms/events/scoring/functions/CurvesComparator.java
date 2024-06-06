package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public interface CurvesComparator {
	
	public String name();

	public Map<String, Double> compare(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) throws CannotCompareException;

	public default TreeMap<Date, double[]> trim(SortedMap<Date, double[]> trimmer, SortedMap<Date, double[]> trimmed)   {
		return trimmed.entrySet().stream()
				.filter(e -> e.getValue() != null && !Double.isNaN(e.getValue()[0]) && trimmer.get(e.getKey()) != null && !Double.isNaN(trimmer.get(e.getKey())[0]))
				.collect(Collectors.toMap(es -> es.getKey(), es ->  es.getValue(), (a, b) -> a, TreeMap::new));
	}
}
