package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.ApacheStats;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;

public class VolatilityRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityRationaliserOperation.class);

	private static final int DATA_IDX = 2;
	private static final int YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS = 250;

	public VolatilityRationaliserOperation() {
		super("volatilityRationaliser", "Apply an input volatility calculation fix to the input and the standard stock OCHL outputs",
				new NumberOperation("number", "Basic Period", "Basic period for ln return calculation in days", new NumberValue(1.0)),
				new NumberOperation("number", "Periods in STDev", "Number of return periods in each standard deviation calculation", new NumberValue(21.0)),
				new DoubleMapOperation("Input data base for volatility calculation"),
				new DoubleMapOperation("Input data for which the fix applies"));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"straight", "walk", "imbalanced"})));
	}

	public VolatilityRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int basicPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		int returnCalculationNbPeriods = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> volatilityBase = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);
		SortedMap<Date, Double> dataOperand = ((NumericableMapValue) inputs.get(DATA_IDX + 1)).getValue(targetStock);

		//Calc
		try {
			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(volatilityBase, basicPeriod, returnCalculationNbPeriods, false);

			ArrayList<Date> keys = new ArrayList<>(volatilityBase.keySet());
			ApacheStats mean = new ApacheStats(new Mean());

			SortedMap<Date, Double> skewFixedOpen = new TreeMap<>();
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("walk")) {//Does not reflect the skew (Neg/Pos)
				//Init
				SortedMap<Date, Double> volatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingVolatiltityAt(d)),TreeMap::putAll);
				SortedMap<Date, Double> movingAverageVolatility = MapUtils.movingStat(volatilities, YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS, mean);
				//Calc
				skewFixedOpen.putAll(walkRationalisation(dataOperand, volatilities, movingAverageVolatility));
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("imbalanced")) {
				//Init
				SortedMap<Date, Double> positiveVolatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingSignedVolatilityAt(d, +1)),TreeMap::putAll);
				SortedMap<Date, Double> movingAveragePositiveVolatility = MapUtils.movingStat(positiveVolatilities, YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS, mean);
				SortedMap<Date, Double> negativeVolatilties = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingSignedVolatilityAt(d, -1)),TreeMap::putAll);
				SortedMap<Date, Double> movingAverageNegativeVolatility = MapUtils.movingStat(negativeVolatilties, YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS, mean);
				//Calc
				skewFixedOpen.putAll(quoteImbalancedRationalisation(dataOperand,
						positiveVolatilities, movingAveragePositiveVolatility,
						negativeVolatilties, movingAverageNegativeVolatility));
			}
			else {//"straight" Wrong as it applies a variation (stdev) to the full value (quote)
				//Init
				SortedMap<Date, Double> volatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.movingVolatiltityAt(d)),TreeMap::putAll);
				SortedMap<Date, Double> movingAverageVolatility = MapUtils.movingStat(volatilities, YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS, mean);
				//Calc
				skewFixedOpen.putAll(quoteStraightRationalisation(dataOperand, volatilities, movingAverageVolatility));
			}

			return new DoubleMapValue(skewFixedOpen);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}

		return new DoubleMapValue();
	}

	private SortedMap<Date, Double> quoteStraightRationalisation(SortedMap<Date, Double> q, SortedMap<Date, Double> volatilities, SortedMap<Date, Double> movingAverageVolatility) throws NotEnoughDataException {

		List<Date> keys = volatilities.keySet().stream().filter(vk -> q.containsKey(vk) && movingAverageVolatility.containsKey(vk)).collect(Collectors.toList());
		TreeMap<Date, Double> rationalisedMap = keys.stream()
				.collect(Collectors.toMap(
						k -> k,
						k -> q.get(k)*(movingAverageVolatility.get(k)/volatilities.get(k)),
						(a, b) -> a, TreeMap::new));

		return rationalisedMap;

	}

	private SortedMap<Date, Double> walkRationalisation(
			SortedMap<Date, Double> q, SortedMap<Date, Double> volatilities, SortedMap<Date, Double> averageAnnualisedVolatility)
			throws NotEnoughDataException {

		List<Date> keys = volatilities.keySet().stream().filter(vk -> q.containsKey(vk) && averageAnnualisedVolatility.containsKey(vk)).collect(Collectors.toList());

		AtomicInteger i = new AtomicInteger(0);
		Stream<Double> nextVolatilityIteration = Stream.iterate(q.get(keys.get(0)), u -> {
			Date pk = keys.get(i.get());
			Date k = keys.get(i.incrementAndGet());
			return u + (q.get(k) - q.get(pk)) * (averageAnnualisedVolatility.get(k)/volatilities.get(k));
		});

		AtomicInteger ia = new AtomicInteger(0);
		SortedMap<Date, Double> rationalisedMap = nextVolatilityIteration
				.limit(keys.size()-1)
				.collect(Collectors.toMap(
						v -> keys.get(ia.getAndIncrement()),
						v -> v,
						(a, b) -> a, TreeMap::new));

		return rationalisedMap;

	}

	private SortedMap<Date, Double> quoteImbalancedRationalisation(
			SortedMap<Date, Double> dataOperand,
			SortedMap<Date, Double> annualisedPositiveVolatilities, SortedMap<Date, Double> averageAnnualisedPositiveVolatility,
			SortedMap<Date, Double> annualisedNegativeVolatilities, SortedMap<Date, Double> averageAnnualisedNegativeVolatility) {

		List<Date> positiveKeys = annualisedPositiveVolatilities.keySet().stream()
				.filter(vk -> dataOperand.containsKey(vk) && averageAnnualisedPositiveVolatility.containsKey(vk)).collect(Collectors.toList());
		List<Date> negativeKeys = annualisedNegativeVolatilities.keySet().stream()
				.filter(vk -> dataOperand.containsKey(vk) && averageAnnualisedNegativeVolatility.containsKey(vk)).collect(Collectors.toList());
		List<Date> keys = new ArrayList<>();
		keys.addAll(positiveKeys);
		keys.addAll(negativeKeys);

		AtomicInteger i = new AtomicInteger(0);
		Stream<Double> nextVolatilityIteration = Stream.iterate(dataOperand.get(dataOperand.firstKey()), u -> {
			Date previousK = keys.get(i.get());
			Date k = keys.get(i.incrementAndGet());
			double change = dataOperand.get(k) - dataOperand.get(previousK);
			if (change > 0) {
				return u + change * (averageAnnualisedPositiveVolatility.get(k)/annualisedPositiveVolatilities.get(k));
			}
			else if (change < 0) {
				return u + change * (averageAnnualisedNegativeVolatility.get(k)/annualisedNegativeVolatilities.get(k));
			} else {
				return u;
			}
		});

		AtomicInteger ia = new AtomicInteger(0);
		SortedMap<Date, Double> rationalisedMap = nextVolatilityIteration
				.limit(keys.size()-1)
				.collect(Collectors.toMap(
						v -> keys.get(ia.getAndIncrement()),
						v -> v,
						(a, b) -> a, TreeMap::new));

		return rationalisedMap;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < DATA_IDX; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift + 1 + YEAR_SLIDING_WINDOW_PERIOD_FOR_AGS;
	}

}
