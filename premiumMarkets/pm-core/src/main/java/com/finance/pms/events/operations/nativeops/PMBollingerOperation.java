package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.StatsFunction;

public class PMBollingerOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(PMBollingerOperation.class);

	enum Type { DAILY, CHANGE, LN };

	public PMBollingerOperation() {
		super("bollinger_", "bollinger bands",
				new NumberOperation("number", "period", "SMA period", new NumberValue(20.0)),
				new NumberOperation("number", "nbDevUp", "Lower band dev factor", new NumberValue(2.0)),
				new NumberOperation("number", "nbDevDown", "Upper band dev factor", new NumberValue(2.0)),
				new StringOperation("String","type","Daily quote (Daily), Change or Ln", new StringValue("Daily")),
				new DoubleMapOperation("input data"));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"middle", "lower", "upper"})));
	}

	public PMBollingerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public PMBollingerOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		int nbDevUp = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		int nbDevDown = ((NumberValue)inputs.get(2)).getValue(targetStock).intValue();
		Type type = Type.valueOf(((StringValue)inputs.get(3)).getValue(targetStock).toUpperCase());
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(4)).getValue(targetStock);

		//Calc
		try {
			StatsFunction mean = new MyApacheStats(new Mean());
			SortedMap<Date, Double> sma = MapUtils.movingStat(data, targetStock.getStartDate(thisStartShift), period, mean);

			StatsFunction std = new MyApacheStats(new StandardDeviation());
			ArrayList<Date> keys = new ArrayList<>(data.keySet());
			SortedMap<Date, Double> bars;
			final BiFunction<Double, Double, Double> lowerFunc;
			final BiFunction<Double, Double, Double> upperFunc;
			switch (type) {
			case CHANGE: 
				bars = IntStream
				.range(period, data.size())
				.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), data.get(keys.get(d)) - data.get(keys.get(d-1))),TreeMap::putAll);
				lowerFunc = (t, u) -> t - u*nbDevDown;
				upperFunc = (t, u) -> t + u*nbDevUp;
				break;
			case LN:
				bars = IntStream
				.range(1, data.size())
				.collect(TreeMap::new, (r, d) -> r.put(keys.get(d), Math.log(data.get(keys.get(d))/data.get(keys.get(d-1)))),TreeMap::putAll);
				lowerFunc = (t, u) -> t - Math.exp(u)*nbDevDown;
				upperFunc = (t, u) -> t + Math.exp(u)*nbDevUp;
				break;
			default:
				bars = data;
				lowerFunc = (t, u) -> t - u*nbDevDown;
				upperFunc = (t, u) -> t + u*nbDevUp;
			}
			SortedMap<Date, Double> mstd = MapUtils.movingStat(bars, targetStock.getStartDate(thisStartShift), period, std);

			SortedMap<Date, Double> lower = mstd.keySet().stream()
					.collect(TreeMap::new, (r, k) -> r.put(k, lowerFunc.apply(sma.get(k), mstd.get(k))), TreeMap::putAll);

			SortedMap<Date, Double> upper = mstd.keySet().stream()
					.collect(TreeMap::new, (r, k) -> r.put(k, upperFunc.apply(sma.get(k), mstd.get(k))), TreeMap::putAll);

			Map<String, NumericableMapValue> selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String availOutputSelector : getAvailableOutputSelectors()) {
				if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("middle")) {
					selectorOutputs.put("middle", new DoubleMapValue(sma));
				} else 
					if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("lower")) {
						selectorOutputs.put("lower", new DoubleMapValue(lower));
					} else 
						if (availOutputSelector != null && availOutputSelector.equalsIgnoreCase("upper")) {
							selectorOutputs.put("upper", new DoubleMapValue(upper));
						}
			}

			return new MultiSelectorsValue(selectorOutputs, getOutputSelector());
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}
		return new DoubleMapValue();
	}

	@Override
	public int operandsRequiredStartShift() {
		int reducedShift = IntStream.range(0, 1)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					if (numberOperand instanceof NumberOperation) {
						return ((NumberValue) numberOperand.getParameter()).getValue(null).intValue();
					} else {
						return getOperands().get(i).operandsRequiredStartShift();
					}
				})
				.reduce(0, (r, e) -> r + e);
		return reducedShift + 1;
	}

}
