package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;

public class SkewRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(SkewRationaliserOperation.class);

	private static final int DATA_IDX = 0;

	public SkewRationaliserOperation() {
		super("skewRationaliser", "Transform the input removing volatility skewness", new DoubleMapOperation("Input data"));
	}

	public SkewRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Param check
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);

		//Calc
		try {
			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(data);
			//Double volatilityLnBiasRatio = calculator.volatilityLnBiasRatio(0, data.size());

			ArrayList<Double> dataValues = new ArrayList<>(data.values());
			ArrayList<Date> dataKeys = new ArrayList<>(data.keySet());

			TreeMap<Date, Double> rationalisedMap = IntStream.range(1, data.size())
					.mapToObj(i -> i)
					.collect(Collectors.toMap(
							i -> dataKeys.get(i),
							i -> dataValues.get(i) - dataValues.get(i-1) * Math.exp(calculator.volatilityLnSkewRatio(0, i)),
							(a, b) -> a, TreeMap::new));

			return new DoubleMapValue(rationalisedMap);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : "  + e, e);
		}

		return new DoubleMapValue();
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < DATA_IDX; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

}
