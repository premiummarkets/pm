package com.finance.pms.events.operations.nativeops;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.ApacheStats;
import com.finance.pms.events.scoring.functions.Normalizer;

public class SkewRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(SkewRationaliserOperation.class);

	private static final int DATA_IDX = 0;

	public SkewRationaliserOperation() {
		super("skewRationaliser", "Apply an input skewness calculation fix to the input and the standard stock OCHL outputs",
				new DoubleMapOperation("Input data base for skewness calculation"),
				new DoubleMapOperation("Input data for which the fix applies"));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"data", "skewSlope"})));
	}

	public SkewRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Param check
		SortedMap<Date, Double> skewBase = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);
		SortedMap<Date, Double> dataOperand = ((NumericableMapValue) inputs.get(DATA_IDX + 1)).getValue(targetStock);

		//Calc
		try {

			//The skew fix calculation is base on the input : skewBase
			Double[] interceptNSlope = linearRegression(skewBase);

			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("skewSlope")) {
				ArrayList<Date> keys = new ArrayList<>(skewBase.keySet());
				Date startDate = new Date(keys.get(0).getTime());
				TreeMap<Date, Double> skewSlope = keys.stream().collect(Collectors.toMap(k -> k, k -> {
					double x = new Double(ChronoUnit.DAYS.between(startDate.toInstant(), new Date(k.getTime()).toInstant())+1);
					return x*interceptNSlope[1] + interceptNSlope[0];
				}, (v1, v2) -> v1, TreeMap::new));
				return new DoubleMapValue(skewSlope);
			} else {//"data" or null
				SortedMap<Date, Double> skewFixedData = calculateOutputMap(dataOperand, interceptNSlope);
				return new DoubleMapValue(skewFixedData);
			}

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}

		return new DoubleMapValue();
	}

	private SortedMap<Date, Double> calculateOutputMap(SortedMap<Date, Double> q, Double[] interceptNSlope) throws NotEnoughDataException {

		ArrayList<Date> qKeys = new ArrayList<>(q.keySet());
		Date startDate = new Date(qKeys.get(0).getTime());
		double slope = interceptNSlope[1];

		ApacheStats mean = new ApacheStats(new Mean());
		double qMean = mean.sEvaluate(q.values());

		TreeMap<Date, Double> rationalisedMap = qKeys.stream()
		.collect(Collectors.toMap(
				k -> k,
				k -> {
					double x = new Double(ChronoUnit.DAYS.between(startDate.toInstant(), new Date(k.getTime()).toInstant())+1);
					double y = qMean + q.get(k) - (x*slope);
					return y;
				},
				(a, b) -> a, TreeMap::new));

		Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, q.firstKey(), q.lastKey(), 1, 101, false);
		return normalizer.normalised(rationalisedMap);
		//return rationalisedMap;
	}

	private Double[] linearRegression(SortedMap<Date, Double> data) {

		SimpleRegression regression = new SimpleRegression(true);

		ArrayList<Date> qKeys = new ArrayList<>(data.keySet());
		Date startDate = new Date(qKeys.get(0).getTime());
		ArrayList<Double> qValues = new ArrayList<>(data.values());

		IntStream.range(1, data.size()-1)
		.forEachOrdered(i -> {
			regression.addData(new Double(ChronoUnit.DAYS.between(startDate.toInstant(), new Date(qKeys.get(i).getTime()).toInstant())+1), qValues.get(i));
		});

		return new Double[] {regression.getIntercept(), regression.getSlope()};

	}


	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < DATA_IDX; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift + 1;
	}

}
