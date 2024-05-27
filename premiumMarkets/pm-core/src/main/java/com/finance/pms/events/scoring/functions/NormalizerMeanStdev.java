package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;

public class NormalizerMeanStdev<T> {
	
	private static MyLogger LOGGER = MyLogger.getLogger(NormalizerMeanStdev.class);


	public NormalizerMeanStdev() {
	}
	
	public SortedMap<Date,Double> normalised(SortedMap<Date, T> subMap) {
		
		SortedMap<Date, Double> data = subMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> valueOf(e.getValue()), (a, b) -> a, TreeMap::new));
		
		MyApacheStats meanF = new MyApacheStats(new Mean());
		MyApacheStats stdevF = new MyApacheStats(new StandardDeviation());
		CurvesSubtraction curvesSubtraction = new CurvesSubtraction();
		double mean = meanF.dEvaluateMd(data);
		SortedMap<Date, Double> minusMean = curvesSubtraction.sOperate(data, mean);
		CurvesMultiplication curvesMultiplication = new CurvesMultiplication();
		double stdev = stdevF.dEvaluateMd(data);
		SortedMap<Date, Double> normalized = minusMean;
		if  (stdev != 0) {
			normalized = curvesMultiplication.sOperate(minusMean, 1/stdev);
		}
		
		LOGGER.info(String.format("Normalizing: data length %d, mean %f, stdev %f, result length %d", subMap.size(), mean, stdev, normalized.size()));
		return normalized;
		
	}

	
//	@SuppressWarnings("unchecked")
//	private T tOf(Double destValueAti) {
//
//		if (R.isAssignableFrom(Double.class)) {
//			return (T) destValueAti;
//		} else if (genType.isArray() && genType.getComponentType().equals(Double.TYPE)) {
//			return (T) new double[]{destValueAti};
//		} else throw new NotImplementedException();
//
//	}
	
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

}
