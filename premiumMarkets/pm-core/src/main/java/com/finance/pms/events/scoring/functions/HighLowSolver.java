package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.SortedMap;

public interface HighLowSolver {

	Boolean higherHigh(
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd);

	Boolean lowerHigh(
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd);

	Boolean higherLow(
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd);

	Boolean lowerLow(
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd);

}
