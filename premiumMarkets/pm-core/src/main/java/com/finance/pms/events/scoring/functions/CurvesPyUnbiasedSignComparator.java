package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;

public class CurvesPyUnbiasedSignComparator implements CurvesComparator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(CurvesPyUnbiasedSignComparator.class);

	private double threshold;

	public CurvesPyUnbiasedSignComparator(double threshold) {
		super();
		this.threshold = threshold;
	}
	
	@Override
	public String name() {
		return "signBiasPy";
	}

	@Override
	public double compare(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) {
		
		expected = trim(actual, expected);
		actual = trim(expected, actual);
		
//        bias = self.bias_func(y_true_series, y_pred_series)
//        signal_accuracy = self.signal_accuracy_func(y_true_series, y_pred_series)
//
//        print(f"signal accuracy: {signal_accuracy}, bias: {bias}")
//        return signal_accuracy - bias  # / trigger_error if trigger_error != 0 else float('inf')
		
		double bias = signBias(actual, expected);
		double signalAcc = zeroCrossAccuracy(actual, expected);
		//double result = 1 / (signalAcc - bias);
		//double result = Math.max(0, 1 - (signalAcc - bias));
		double result =  1 - (signalAcc - bias);
		
		LOGGER.info(String.format("Returned cmp values:  Math.max(0, 1 - (signalAcc %f - bias %f))  = %f ", signalAcc, bias, result));
		return result;
	}
	
	private double signBias(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) {
	
//	def bias_sign_func(y_true_series=[], y_pred_series=[], threshold=0):
//	    true_bias = (len(list(filter(lambda y: y >= threshold, y_true_series))) - len(list(filter(lambda y: y <= threshold, y_true_series)))) / len(y_true_series)
//	    pred_bias = (len(list(filter(lambda y: y >= threshold, y_pred_series))) - len(list(filter(lambda y: y <= threshold, y_pred_series)))) / len(y_pred_series)
//	    print(f"true_bias: {true_bias}, pred_bias: {pred_bias}")
//	    bias = math.fabs(true_bias - pred_bias)
//	    return bias
		
		Date firstKey = expected.firstKey();
		Date lastKey = expected.lastKey();
		List<double[]> expectedFiltered = expected.values().stream().filter(v -> v != null && !Double.isNaN(v[0])).collect(Collectors.toList());
		double expectedBias = expectedFiltered.stream().map(v -> (v[0] > threshold)?+1d:(v[0] < threshold)?-1d:0d).reduce((a, e) -> a + e).orElse(0d) / expectedFiltered.size();
		SortedMap<Date, double[]> actualSubMapInclusive = MapUtils.subMapInclusive(actual, firstKey, lastKey);
		List<double[]> actualFiltered = actualSubMapInclusive.values().stream().filter(v -> v != null && !Double.isNaN(v[0])).collect(Collectors.toList());
		double actualBias = actualFiltered.stream().map(v -> (v[0] > threshold)?+1d:(v[0] < threshold)?-1d:0d).reduce((a, e) -> a + e).orElse(0d)  / actualFiltered.size();
		double bias = Math.abs(expectedBias - actualBias);
		return bias;
		
	}
	
	
	private double zeroCrossAccuracy(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) {
		
//	def zero_cross_accuracy_func(y_true_series, y_pred_series, threshold=0):
//	    # if len(y_true_series.shape) != 1 or len(y_pred_series.shape) != 1:
//	    #     raise Exception("Shape not supported yet. Please fix.")
//	    count_za = 0
//	    for y_true, y_pred in zip(y_true_series, y_pred_series):
//	        # 0.5193579 == 0.5193579000000001
//	        if math.isclose(
//	                math.fabs(y_true - threshold) + math.fabs(y_pred - threshold),
//	                math.fabs(y_true + y_pred - 2*threshold),
//	                abs_tol=0.00000001):
//	            count_za = count_za + 1
//	    return count_za / len(y_true_series)
		
		double[] count_za = expected.keySet().stream()
			.map(k -> {
				double[] actualV = actual.get(k);
				double[] expectedV = expected.get(k);
				if (actualV != null && expectedV != null && !Double.isNaN(actualV[0]) && !Double.isNaN(expectedV[0]) && !(actualV[0] == threshold || expectedV[0] == threshold)) {
					double actualD = actualV[0];
					double expectedD = expectedV[0];
					int cmpSigns = Precision.compareTo(Math.abs(actualD - threshold) + Math.abs(expectedD - threshold), Math.abs(actualD + expectedD - 2*threshold), 0.00000001);
					if (cmpSigns == 0) {
						return new double[] {1d, 1d};
					} else {
						return new double[] {0d, 1d};
					}
				}
				return new double[] {0d, 0d};
			})
			.reduce((a, e) -> {
				a[0] = a[0] +  e[0];
				a[1] = a[1] +  e[1];
				return a;
			})
		.orElse(new double[] {Double.NEGATIVE_INFINITY, 0d});
		
		return (count_za[1] == 0)?Double.NEGATIVE_INFINITY:count_za[0]/count_za[1];
		
	}

}
