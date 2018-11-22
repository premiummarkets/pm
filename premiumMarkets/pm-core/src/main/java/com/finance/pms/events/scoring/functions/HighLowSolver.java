package com.finance.pms.events.scoring.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface HighLowSolver {

	public enum Greed {
		GREEDY, FULL, LAZY;
	}

	public static Map<Type, HighLowSolver> highlowSolverCache = new HashMap<>();
	public enum Type {
		SMOOTH(SmoothHighLowSolver.class),
		TREND(TrendHighLowSolver.class);

		private Class<? extends HighLowSolver> typeClass;

		Type(Class<? extends HighLowSolver> typeClazz) {
			this.typeClass = typeClazz;
		}

		public Class<? extends HighLowSolver> getTypeClass() {
			return typeClass;
		}
	}

	public static HighLowSolver getHighLowSolverFor(String highLowSolverType) {
		Type valueOf = Type.valueOf(highLowSolverType.toUpperCase());

		HighLowSolver highLowSolver = highlowSolverCache.get(valueOf);
		if (highLowSolver == null) {
			try {
				Class<? extends HighLowSolver> implClass = valueOf.getTypeClass();
				highLowSolver = implClass.newInstance();
				highlowSolverCache.put(valueOf, highLowSolver);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return highLowSolver;

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
