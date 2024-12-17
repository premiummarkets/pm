package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.StatsFunction;

public class VolatilityOperation extends PMWithDataOperation implements MultiValuesOutput {

	private static final int YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS = 254;

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityOperation.class);

	private static final int DATA_IDX = 2;

	/**
	 * atDate ~ stdDev(21,1,periodicLn_(1.0,close)), positiveAtDate and negativeAtDate : moving standard deviations of logarithmic returns.
	 * logRetnAtDate ~ sma(21,periodicLn_(1.0,close)) : moving mean of logarithmic return.
	 * average, averagePosistive, and averageNegative : means of standard deviation of logarithmic returns.
	 */
	public VolatilityOperation() {
		super("volatilityCalculator", "Calculate the input volatility",
				new NumberOperation("number", "Basic Period", "Basic period for ln return calculation in days", new NumberValue(1.0)),
				new NumberOperation("number", "Periods in STDev", "Number of return periods in each standard deviation calculation", new NumberValue(63.0)),
				new DoubleMapOperation("Input data"));
		setAvailableOutputSelectors(
				new ArrayList<String>(Arrays.asList(new String[]{
						"atDate","average",
						"positiveAtDate","averagePosistive",
						"negativeAtDate","averageNegative",
						"logRetnAtDate","all"
				})));
	}

	public VolatilityOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public MultiSelectorsValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int basicPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		int returnCalculationNbPeriods = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);

		//Calc
		try {
			
			Date startDateShift = targetStock.getStartDate(thisStartShift);

			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(data, basicPeriod, returnCalculationNbPeriods, false);

			ArrayList<Date> keys = new ArrayList<>(data.keySet());
			StatsFunction mean = new MyApacheStats(new Mean());

			TreeMap<Date, Double> sVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingVolatiltityAt(d)),TreeMap::putAll);
			TreeMap<Date, Double> sPosVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingSignedVolatilityAt(d, +1)),TreeMap::putAll);
			TreeMap<Date, Double> sNegVolatilties = IntStream
					.range(basicPeriod + returnCalculationNbPeriods, data.size())
					.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingSignedVolatilityAt(d, -1)),TreeMap::putAll);

			Map<String, NumericableMapValue> selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String availOutputSelector : getAvailableOutputSelectors()) {
				if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("atDate")) {
					selectorOutputs.put("atDate", new DoubleMapValue(sVolatilties));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("positiveAtDate")) {
					selectorOutputs.put("positiveAtDate", new DoubleMapValue(sPosVolatilties));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("negativeAtDate")) {
					selectorOutputs.put("negativeAtDate", new DoubleMapValue(sNegVolatilties));
				} 
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("average")) {
					SortedMap<Date, Double> averageVolatility = MapUtils.movingStat(sVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean);
					selectorOutputs.put("average", new DoubleMapValue(averageVolatility));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("averagePosistive")) {
					SortedMap<Date, Double> averageVolatility = MapUtils.movingStat(sPosVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean);
					selectorOutputs.put("averagePosistive", new DoubleMapValue(averageVolatility));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("averageNegative")) {
					SortedMap<Date, Double> averageVolatility = MapUtils.movingStat(sNegVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean);
					selectorOutputs.put("averageNegative", new DoubleMapValue(averageVolatility));
				} 
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("logRetnAtDate")) {
					TreeMap<Date, Double> meanD2Dretrun = IntStream
							.range(basicPeriod + returnCalculationNbPeriods, data.size())
							.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingMeanOfReturnsAt(d)),TreeMap::putAll);
					selectorOutputs.put("logRetnAtDate", new DoubleMapValue(meanD2Dretrun));
				}
				else if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("all")) {
					Map<String, NumericableMapValue> ouputs = new TreeMap<>();
					ouputs.put("atDate", new DoubleMapValue(sVolatilties));
					ouputs.put("positiveAtDate", new DoubleMapValue(sPosVolatilties));
					ouputs.put("negativeAtDate", new DoubleMapValue(sNegVolatilties));
					ouputs.put("average", new DoubleMapValue(MapUtils.movingStat(sVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean)));
					ouputs.put("averagePosistive", new DoubleMapValue(MapUtils.movingStat(sPosVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean)));
					ouputs.put("averageNegative", new DoubleMapValue(MapUtils.movingStat(sNegVolatilties, startDateShift, YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS, mean)));
					ouputs.put("logRetnAtDate", new DoubleMapValue( IntStream
							.range(basicPeriod + returnCalculationNbPeriods, data.size())
							.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingMeanOfReturnsAt(d)),TreeMap::putAll)));
					SortedMap<Date, double[]> allMap = ValueManipulator.inputListToArray(targetStock, ouputs.values(), true, true).get(InputToArrayReturn.RESULTS);
					selectorOutputs.put("all", new DoubleArrayMapValue(allMap, new ArrayList<>(ouputs.keySet()), 0));
				}
			}

			return new MultiSelectorsValue(selectorOutputs, getOutputSelector());

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " calculating " + getReference() + ": " + e, e);
		}

		return new MultiSelectorsValue(getAvailableOutputSelectors(), getOutputSelector());
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		int reducedShift = IntStream.range(0, DATA_IDX)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					return numberOperand.getOrRunParameter(targetStock, thisCallStack)
							.filter(v -> v instanceof NumberValue)
							.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
							.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisCallStack, thisParentStartShift));
				})
				.reduce(0, (r, e) -> r + e);
		return reducedShift + YEAR_SLIDING_WINDOW_PERIOD_FOR_AVGS;
	}

	@Override
	public int mainInputPosition() {
		return 0;
	}

}
