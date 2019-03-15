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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;

public class VolatilityRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityRationaliserOperation.class);

	private static final int DATA_IDX = 2;

	public VolatilityRationaliserOperation() {
		super("volatilityRationaliser", "Apply an input volatility calculation fix to the input and the standard stock OCHL outputs",
				new NumberOperation("number", "Basic Period", "Basic period for ln return calculation in days", new NumberValue(1.0)),
				new NumberOperation("number", "Periods in STDev", "Number of return periods in each standard deviation calculation", new NumberValue(63.0)),
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
			HistoricalVolatilityCalculator calculator = new HistoricalVolatilityCalculator(volatilityBase, basicPeriod, returnCalculationNbPeriods);
			ArrayList<Date> keys = new ArrayList<>(volatilityBase.keySet());

			SortedMap<Date, Double> skewFixedOpen = new TreeMap<>();
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("walk")) {
				//Init
				TreeMap<Date, Double> annulisedVolatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedVolatilityAt(d)),TreeMap::putAll);
				Double averageAnnualisedVolatility = average(annulisedVolatilities);
				//Calc
				skewFixedOpen.putAll(walkRationalisation(dataOperand, annulisedVolatilities, averageAnnualisedVolatility));
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("imbalanced")) {
				//Init
				TreeMap<Date, Double> annualisedPositiveVolatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedSignedVolatilityAt(d, +1)),TreeMap::putAll);
				Double averageAnnualisedPositiveVolatility = average(annualisedPositiveVolatilities);
				TreeMap<Date, Double> annualisedNegativeVolatilties = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedSignedVolatilityAt(d, -1)),TreeMap::putAll);
				Double averageAnnualisedNegativeVolatility = average(annualisedNegativeVolatilties);
				//Calc
				skewFixedOpen.putAll(quoteImbalancedRationalisation(dataOperand,
						annualisedPositiveVolatilities, averageAnnualisedPositiveVolatility,
						annualisedNegativeVolatilties, averageAnnualisedNegativeVolatility));
			}
			else {
				//Init
				TreeMap<Date, Double> annulisedVolatilities = IntStream
						.range(basicPeriod + returnCalculationNbPeriods, volatilityBase.size())
						.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), calculator.annualisedVolatilityAt(d)),TreeMap::putAll);
				Double averageAnnualisedVolatility = average(annulisedVolatilities);
				//Calc
				skewFixedOpen.putAll(quoteStraightRationalisation(dataOperand, annulisedVolatilities, averageAnnualisedVolatility));
			}

			return new DoubleMapValue(skewFixedOpen);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}

		return new DoubleMapValue();
	}

	private Double average(TreeMap<Date, Double> annulisedVolatilties) {
		ArrayList<Double> annualisedVolatilitiesValues = new ArrayList<>(annulisedVolatilties.values());
		Double averageAnnualisedVolatility = IntStream
				.range(0, annualisedVolatilitiesValues.size())
				.mapToDouble(d -> annualisedVolatilitiesValues.get(d))
				.average()
				.getAsDouble();
		return averageAnnualisedVolatility;
	}

	private SortedMap<Date, Double> quoteStraightRationalisation(SortedMap<Date, Double> q, SortedMap<Date, Double> volatilities, Double averageVolatility) throws NotEnoughDataException {

		List<Date> keys = volatilities.keySet().stream().filter(vk -> q.containsKey(vk)).collect(Collectors.toList());
		TreeMap<Date, Double> rationalisedMap = keys.stream()
				.collect(Collectors.toMap(
						k -> k,
						k -> averageVolatility*(q.get(k)/volatilities.get(k)),
						(a, b) -> a, TreeMap::new));

		return rationalisedMap;

	}

	private SortedMap<Date, Double> walkRationalisation(SortedMap<Date, Double> q, SortedMap<Date, Double> volatilities, Double averageVolatility) throws NotEnoughDataException {

		List<Date> keys = volatilities.keySet().stream().filter(vk -> q.containsKey(vk)).collect(Collectors.toList());

		AtomicInteger i = new AtomicInteger(0);
		Stream<Double> nextVolatilityIteration = Stream.iterate(q.get(keys.get(0)), u -> {
			Date pk = keys.get(i.get());
			Date k = keys.get(i.incrementAndGet());
			return u + (q.get(k) - q.get(pk)) * (averageVolatility/volatilities.get(k));
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
			SortedMap<Date, Double> annualisedPositiveVolatilities, Double averageAnnualisedPositiveVolatility,
			SortedMap<Date, Double> annualisedNegativeVolatilities, Double averageAnnualisedNegativeVolatility) {

		List<Date> positiveKeys = annualisedPositiveVolatilities.keySet().stream().filter(vk -> dataOperand.containsKey(vk)).collect(Collectors.toList());
		List<Date> negativeKeys = annualisedNegativeVolatilities.keySet().stream().filter(vk -> dataOperand.containsKey(vk)).collect(Collectors.toList());
		List<Date> keys = new ArrayList<>();
		keys.addAll(positiveKeys);
		keys.addAll(negativeKeys);

		AtomicInteger i = new AtomicInteger(0);
		Stream<Double> nextVolatilityIteration = Stream.iterate(dataOperand.get(dataOperand.firstKey()), u -> {
			Date previousK = keys.get(i.get());
			Date k = keys.get(i.incrementAndGet());
			double change = dataOperand.get(k) - dataOperand.get(previousK);
			if (change > 0) {
				return u + change * (averageAnnualisedPositiveVolatility/annualisedPositiveVolatilities.get(k));
			}
			else if (change < 0) {
				return u + change * (averageAnnualisedNegativeVolatility/annualisedNegativeVolatilities.get(k));
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
		return maxDateShift + 1;
	}

}
