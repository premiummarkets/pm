package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

import com.google.common.util.concurrent.AtomicDouble;

//This is not extracting any best fit but just does a row calculation see CurvesSignComparatorExtended for iterative fit.
public class CurvesSignComparator implements CurvesComparator {

	private int actualZero;
	private int idealZero;

	public CurvesSignComparator() {
		super();
		actualZero = 0;
	}
	
	
	@Override
	public String name() {
		return "sign";
	}

	public CurvesSignComparator(int actualZero, int idealZero) {
		super();
		this.actualZero = actualZero;
		this.idealZero = idealZero;
	}

	private double compareNormalised(SortedMap<Date, double[]> normalisedActualData, SortedMap<Date, double[]> normalisedIdealData) {
		AtomicDouble cpt = new AtomicDouble(0);
		Optional<Double> reduced = normalisedIdealData.entrySet().stream()
				.map(e -> {
					double[] actualArray = normalisedActualData.get(e.getKey());
					double[] idealArray = e.getValue();
					if (actualArray == null) return 0d;

					double actual = actualArray[0];
					double ideal = idealArray[0];
					if (Double.isNaN(ideal) || Double.isNaN(actual)) return 0d;

					cpt.getAndAdd(1);
					if ((ideal < idealZero && actual > actualZero) || ideal > idealZero && actual < actualZero) return 1d;
					return 0d;
				})
				.reduce((r,i) -> r + i);

		return reduced.get()/cpt.doubleValue();
	}

	public Map<String, Double> compare(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) {
		expected = trim(actual, expected);
		actual = trim(expected, actual);
		return  Map.ofEntries(java.util.Map.entry("default", this.compareNormalised(actual, expected)));
	}

}
