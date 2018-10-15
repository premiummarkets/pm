package com.finance.pms.events.scoring.functions;

import java.util.SortedMap;
import java.util.function.Function;

public interface HighLowSolver {

	Function<Double, Function<Double, Function<Double, Boolean>>> cutsAboveSupport = actual -> threshold -> tolerance -> actual > threshold*(1 + tolerance);
	Function<Double, Function<Double, Function<Double, Boolean>>> cutsBelowSupport = actual -> threshold -> tolerance -> actual*(1 + tolerance) < threshold;

	Boolean higherHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean lowerHigh(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean higherLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean lowerLow(
			SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope);

	Boolean flatHigh(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance);

	Boolean flatLow(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange, SortedMap<Integer, Double> _higherHighs, Line<Integer, Double> _expertTangent,
			Double lowestStart, Double highestStart, Double tolerance);

}
