package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;

/**
 * Evaluate the sign error with also a notion of distance between actual and expected
 */
public class CurvesSignAwareComparator implements CurvesComparator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(CurvesSignAwareComparator.class);

	private double factor;

	public CurvesSignAwareComparator(double factor) {
		super();
		this.factor = factor;
	}
	
	@Override
	public String name() {
		return "signAwareError";
	}

	@Override
	public double compare(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) {
		
		NormalizerMeanStdev<double[]> normalizerMeanStdev = new NormalizerMeanStdev<>();

		final SortedMap<Date, Double> fExpected = normalizerMeanStdev.normalised(trim(actual, expected));
		final SortedMap<Date, Double> fActual = normalizerMeanStdev.normalised(trim(expected, actual));

		//		# Hinge: backend.mean(tf.maximum(1.0 - y_true * y_pred, 0.0), axis=-1)
		//	    # Huber:
		//	    # backend.mean(
		//	    #     tf.where(
		//	    #         abs_error <= delta,
		//	    #         half * tf.square(error),
		//	    #         delta * abs_error - half * tf.square(delta),
		//	    #     ),
		//	    #     axis=-1,
		//	    # )

		final AtomicInteger cpt = new AtomicInteger(0);
		Double collect = expected.keySet().stream()
		.map(k -> {
			Double actualV = fActual.get(k);
			Double expectedV = fExpected.get(k);
			if (actualV != null && expectedV != null && !Double.isNaN(actualV) && !Double.isNaN(expectedV)) {
				double error = (actualV - expectedV)*(actualV- expectedV);
				double sign = actualV*expectedV;
				if (sign == 0) { //actual == expected
					return 0.0;
				} else
				if (sign > 0) { //Same sign
					return error * factor;
				} else {
					cpt.getAndAdd(1);
					return error;
				}
			}
			return 0.0;
		})
		.collect(Collectors.averagingDouble(Double::doubleValue));
		
		double sqrt = Math.sqrt(collect);
		
		LOGGER.info(this.name() + " result: " + sqrt + ", " + cpt.intValue() + 
				". With actual " + fActual.size() + ", " + fActual.firstKey() + "/" + fActual.lastKey() + 
				" and expected " + fExpected.size() + ", " + fExpected.firstKey() + "/" + fExpected.lastKey());
		
		return sqrt;
	}


}
