package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
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
		setAvailableOutputSelectors(
				new ArrayList<String>(Arrays.asList(new String[]{
				"annualisedAtDate","average",
				"annualisedPositiveAtDate","averagePosistive",
				"annualisedNegativeAtDate","averageNegative"
				})));
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
			Function<Date, Date> startWindowKFunc = k -> new Date(k.getTime() - 250l*(1000l * 60l * 60l * 24l));

			TreeMap<Date, Double> annulisedVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedVolatilityAt(d)),TreeMap::putAll);
			TreeMap<Date, Double> annulisedPosVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedSignedVolatilityAt(d, +1)),TreeMap::putAll);
			TreeMap<Date, Double> annulisedNegVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedSignedVolatilityAt(d, -1)),TreeMap::putAll);

			Map<String, NumericableMapValue> selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String availOutputSelector : getAvailableOutputSelectors()) {
				if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("annualisedAtDate")) {
					selectorOutputs.put("annualisedAtDate", new DoubleMapValue(annulisedVolatilties));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("annualisedPositiveAtDate")) {
					selectorOutputs.put("annualisedPositiveAtDate", new DoubleMapValue(annulisedPosVolatilties));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("annualisedNegativeAtDate")) {
					selectorOutputs.put("annualisedNegativeAtDate", new DoubleMapValue(annulisedNegVolatilties));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("average")) {
					SortedMap<Date, Double> averageAnnualisedVolatility = MapUtils.slidingAvarage(annulisedVolatilties, startWindowKFunc);
					selectorOutputs.put("average", new DoubleMapValue(averageAnnualisedVolatility));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("averagePosistive")) {
					SortedMap<Date, Double> averageAnnualisedVolatility = MapUtils.slidingAvarage(annulisedPosVolatilties, startWindowKFunc);
					selectorOutputs.put("averagePosistive", new DoubleMapValue(averageAnnualisedVolatility));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("averageNegative")) {
					SortedMap<Date, Double> averageAnnualisedVolatility = MapUtils.slidingAvarage(annulisedNegVolatilties, startWindowKFunc);
					selectorOutputs.put("averageNegative", new DoubleMapValue(averageAnnualisedVolatility));
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
