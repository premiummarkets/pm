package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;

public class VolatilityOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityOperation.class);

	private static final int DATA_IDX = 2;

	public VolatilityOperation() {
		super("volatilityCalculator", "Calculate the input volatility",
				new NumberOperation("number", "Basic Period", "Basic period for ln return calculation in days", new NumberValue(1.0)),
				new NumberOperation("number", "Periods in STDev", "Number of return periods in each standard deviation calculation", new NumberValue(63.0)),
				new DoubleMapOperation("Input data"));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"annualisedAtDate","average"})));
	}

	public VolatilityOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public MultiSelectorsValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int basicPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		int returnCalculationNbPeriods = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);

		//Calc
		try {

			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(data, basicPeriod, returnCalculationNbPeriods);

			ArrayList<Date> keys = new ArrayList<>(data.keySet());
			TreeMap<Date, Double> collectedAnnulisedVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedVolatilityAt(d)),TreeMap::putAll);

			ArrayList<Double> collectedAnnualisedVolatilitiesValues = new ArrayList<>(collectedAnnulisedVolatilties.values());
			Double averageAnnualisedVolatility = IntStream
					.range(0, collectedAnnualisedVolatilitiesValues.size())
					.mapToDouble(d -> collectedAnnualisedVolatilitiesValues.get(d))
					.average()
					.getAsDouble();
			TreeMap<Date, Double> averageLine = keys.stream().collect(Collectors.toMap(k -> k, k -> averageAnnualisedVolatility, (v1, v2) -> v1, TreeMap::new));

			Map<String, NumericableMapValue> selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String availOutputSelector : getAvailableOutputSelectors()) {
				if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("annualisedAtDate")) {
					selectorOutputs.put("annualisedAtDate", new DoubleMapValue(collectedAnnulisedVolatilties));
				}
				if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("average")) {
					selectorOutputs.put("average", new DoubleMapValue(averageLine));
				}
			}

			return new MultiSelectorsValue(selectorOutputs, getOutputSelector());

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " calculating " + getReference() + " : " + e, e);
		}

		return new MultiSelectorsValue(getAvailableOutputSelectors(), getOutputSelector());
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
