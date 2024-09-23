package com.finance.pms.events.scoring.functions;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import com.finance.pms.admin.install.logging.MyLogger;
import com.google.common.math.Quantiles;

public class Trimmer<T> {
	
	private static MyLogger LOGGER = MyLogger.getLogger(Trimmer.class);
	
	public static enum TrimType {
		Stdev,
		ModZscore,
		Zscore,
		Quantile
	}
	
	public static interface Filter {
		public Boolean above(Double value);
		public Boolean below(Double value);
	}
	
	private final Class<T> genType;

	private Filter filter;


	public static <T> Trimmer<T> build(Class<T> genType, TrimType trimType, Double trimFactor, SortedMap<Date, Double> data) {
		
		Filter filter;
		switch (trimType) {
		case Stdev: {
			double[] array = data.values().stream().mapToDouble(e -> e).filter(e -> !Double.isNaN(e)).toArray();
			Mean meanF = new Mean();
			Double mean = meanF.evaluate(array);
			StandardDeviation stdevF = new StandardDeviation();
			Double stdev = stdevF.evaluate(array);
			filter = new Filter () {

				@Override
				public Boolean above(Double value) {
					return value > (mean + trimFactor * stdev);
				}

				@Override
				public Boolean below(Double value) {
					return value < (mean - trimFactor * stdev);
				}
				
			};
		}
			break;
		case ModZscore: {
			MyApacheStats medianF = new MyApacheStats(new Median());
			double median = medianF.dEvaluateMd(data);
			CurvesSubtraction curvesSubtraction = new CurvesSubtraction();
			SortedMap<Date, Double> distMedianAbs = curvesSubtraction.sOperate(data, median)
														.entrySet().stream()
														.collect(Collectors.toMap(e -> e.getKey(), e -> Math.abs(e.getValue()), (a, b) -> a, TreeMap::new));
			double mad = medianF.dEvaluateMd(distMedianAbs);
			filter = new Filter() {							
				@Override
				public Boolean above(Double value) {
					return 0.6745 * (value - median) / mad > trimFactor;//3.5;
				}
				@Override
				public Boolean below(Double value) {
					return 0.6745 * (value - median) / mad < -trimFactor; //-3.5;
				}
			};
		}
			break;
		case Zscore: {
			double[] array = data.values().stream().mapToDouble(e -> e).filter(e -> !Double.isNaN(e)).toArray();
			Mean meanF = new Mean();
			Double mean = meanF.evaluate(array);
			StandardDeviation stdevF = new StandardDeviation();
			Double stdev = stdevF.evaluate(array);
			filter = new Filter() {

				@Override
				public Boolean above(Double value) {
					return (value - mean) / stdev > trimFactor;//3; //2;
				}
				@Override
				public Boolean below(Double value) {
					return (value - mean) / stdev < -trimFactor;//-3; //-2;
				}
				
			};
		}
			break;
		case Quantile: {
			Collection<Double> values = data.values().stream().filter(e -> !Double.isNaN(e)).collect(Collectors.toList());
			Map<Integer, Double> quantiles = Quantiles.percentiles().indexes(25, 75).compute(values);
			Double IQR = quantiles.get(75) - quantiles.get(25);
			Double higherBound = quantiles.get(75) + 1.5 * IQR;
			Double lowerBound = quantiles.get(25) - 1.5 * IQR;
			filter = new Filter() {

				@Override
				public Boolean above(Double value) {
					return value > higherBound;
				}

				@Override
				public Boolean below(Double value) {
					return value < lowerBound;
				}
				
			};
		}
			break;
		default:
			throw new NotImplementedException();
		}
		
		return new Trimmer<T>(genType, filter);
	}

	public Trimmer(Class<T> genType, Filter filter) {
		this.genType = genType;
		this.filter = filter;
	}
	
	public SortedMap<Date, T> trim(SortedMap<Date, T> subMap) {
				
		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;

		for (Date date : subMap.keySet()) {
			double value = valueOf(subMap.get(date));
			if (value >= max && !filter.above(value)) max = value;
			if (value <= min && !filter.below(value)) min = value;
		}

		double fMax = max;
		double fMin = min;
		SortedMap<Date, T> trimmed = subMap.entrySet().stream()
				.collect(Collectors.toMap(
					e -> e.getKey(), 
					e -> {
						Double value = valueOf(e.getValue());
						if (value > fMax) {
							return tOf(fMax);
						}
						
						if (value < fMin) {
							return tOf(fMin);
						}
						
						return tOf(value);
						
					}, (a, b) -> a, TreeMap::new));
		
		return trimmed;
		
	}
	
	private Double valueOf(T t) {

		if (t instanceof Double) {
			return (Double) t;
		} else if (t.getClass().isArray() &&t.getClass().getComponentType().equals(Double.TYPE)){
			if (((double[]) t).length > 1) {
				LOGGER.warn("Normalised data contains element value size > 1 is not supported. Only the first series will be normalised.");
			}
			return ((double[]) t)[0];
		} else throw new NotImplementedException();

	}
	
	@SuppressWarnings("unchecked")
	private T tOf(Double destValueAti) {

		if (genType.isAssignableFrom(Double.class)) {
			return (T) destValueAti;
		} else if (genType.isArray() && genType.getComponentType().equals(Double.TYPE)) {
			return (T) new double[]{destValueAti};
		} else throw new NotImplementedException();

	}
	
	public Filter getFilter() {
		return filter;
	}

}
