package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;

public class VolatilityOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityOperation.class);

	public VolatilityOperation() {
		super("volatilityCalculator", "Calculate the input volatility", 
				new NumberOperation("number", "Basic Period", "Basic period for log change calculation in days", new NumberValue(1.0)),
				new NumberOperation("number", "Calculation Periods Number", "Number of basic changes in the standard deviation calculation", new NumberValue(63.0)),
				new NumberOperation("number", "Basic Yearly Periods Number", "Number of basic periods in the year for a yearly basis", new NumberValue(252.0)),
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
		int yearNbPeriods = ((NumberValue)inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(3)).getValue(targetStock);

		//Calc
		try {

			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(data, basicPeriod, returnCalculationNbPeriods, yearNbPeriods);

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
			for (String outputSelector : getAvailableOutputSelectors()) {
				if (outputSelector != null && outputSelector.equalsIgnoreCase("annualisedAtDate")) {
					selectorOutputs.put("annualisedAtDate", new DoubleMapValue(collectedAnnulisedVolatilties));
				}
				if (outputSelector != null && outputSelector.equalsIgnoreCase("average")) {
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
		return (int) TimeUnit.DAYS.convert(DateFactory.midnithDate(new Date()).getTime() - DateFactory.dateAtZero().getTime(), TimeUnit.MILLISECONDS);
	}

}
