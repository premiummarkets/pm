package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeSet;

import com.google.common.util.concurrent.AtomicDouble;

public class CurvesSignComparator extends CurvesComparatorOldBase {

	public CurvesSignComparator(Date start, Date end, int maxShift) {
		super(start, end, maxShift);
	}

	@Override
	protected double compareNormalised(Date slotStart, Date slotEnd, SortedMap<Date, double[]> normalisedData, SortedMap<Date, double[]> normalisedRefData) {
		
		AtomicDouble cpt = new AtomicDouble(0);
		Optional<Double> reduced = normalisedData.entrySet().stream()
		.map(e -> {
			double[] refValue = normalisedRefData.get(e.getKey());
			if (refValue == null) return 0d;
			cpt.addAndGet(1);
			double[] value = e.getValue();
			if ((refValue[0] < 0 && value[0] > 0) || refValue[0] > 0 && value[0] < 0) return 1d;
			return 0d;
		})
		.reduce((r,i) -> r + i);

		return reduced.get()/cpt.doubleValue();
	}
	

	@Override
	public CurveErr compare(SortedMap<Date, double[]> data, SortedMap<Date, double[]> refData) {
		double compareNormalised = this.compareNormalised(data.firstKey(),data.lastKey(), data, refData);
		return new CurveErr(0, compareNormalised, compareNormalised, new TreeSet<>());
	}
	

	@Override
	protected SortedMap<Date, double[]> normalize(Date slotStart, Date slotEnd, SortedMap<Date, double[]> data) {
		return data;
	}

}
