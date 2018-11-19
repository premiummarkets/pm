package com.finance.pms.events.scoring.functions;

import java.util.List;
import java.util.SortedMap;

public interface HighLowSolver {

	public enum Greed {
		GREEDY, FULL, LAZY;
	}

	Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean lowerHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, Greed greed,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean flatHigh(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>>  _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance);

	Boolean flatLow(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance);

}
