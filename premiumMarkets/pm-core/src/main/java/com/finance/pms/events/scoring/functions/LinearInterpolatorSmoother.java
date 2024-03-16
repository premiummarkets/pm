package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class LinearInterpolatorSmoother extends InterpolatorSmoother {

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		Date start = data.firstKey();
		Date end = data.lastKey();
		
		List<Date> calKeys = Stream.iterate(start, d -> d.compareTo(end) <= 0, d -> DateUtils.addDays(d, 1)).collect(Collectors.toList());
		
		List<Double> Xs = data.keySet().stream().map(d -> Double.valueOf(calKeys.indexOf(d))).collect(Collectors.toList());
		List<Double> Ys = data.values().stream().map(da -> da[0]).collect(Collectors.toList());
		LinearInterpolator interpolator = new LinearInterpolator();
		
		PolynomialSplineFunction interpolate = interpolator.interpolate(Xs.stream().mapToDouble(d -> d).toArray(), Ys.stream().mapToDouble(d -> d).toArray());
		
		List<Double> interValues = DoubleStream.iterate(0d, d -> d < calKeys.size(), d -> d + 1d).mapToObj(d -> interpolate.value(d)).collect(Collectors.toList());
		
		TreeMap<Date, double[]> result = IntStream.range(0, calKeys.size())
													.mapToObj(i -> i).collect(Collectors.toMap(i -> calKeys.get(i), i -> new double[] { interValues.get(i) }, (a, b) -> a, TreeMap::new));
		return result;
	}

}
