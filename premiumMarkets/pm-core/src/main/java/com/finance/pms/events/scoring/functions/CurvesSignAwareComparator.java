package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class CurvesSignAwareComparator implements CurvesComparator {
	
	//private static MyLogger LOGGER = MyLogger.getLogger(CurvesSignAwareComparator.class);

	private double threshold;

	public CurvesSignAwareComparator(double threshold) {
		super();
		this.threshold = threshold;
	}

	@Override
	public double compare(SortedMap<Date, double[]> actual, SortedMap<Date, double[]> expected) throws CannotCompareException {

		final SortedMap<Date, double[]> fExpected = trim(actual, expected);
		final SortedMap<Date, double[]> fActual = trim(expected, actual);

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
		
		// My: mean(
		//		where(
		//			0 < y_true*y-pred,
		//			0, 	# pos_error * fact
		//			sqrt(abs(y_true*y-pred)) # neg_error

		Double collect = expected.keySet().stream()
		.map(k -> {
			double[] actualV = fActual.get(k);
			double[] expectedV = fExpected.get(k);
			if (actualV != null && expectedV != null && !Double.isNaN(actualV[0]) && !Double.isNaN(expectedV[0])) {
				double actualD = actualV[0] - threshold;
				double expectedD = expectedV[0] - threshold;
				double product = actualD*expectedD;
				if (product == 0) {
					return Math.abs((actualD == 0)?expectedD:actualD);
				} else
				if (product > 0) { //Same sign
					return 0.0;
				} else {
					return Math.sqrt(Math.abs(product));
				}
			}
			return 0.0;
		})
		.collect(Collectors.averagingDouble(Double::doubleValue));


		return collect;
	}


}
