package com.finance.pms.events.operations.nativeops;

import java.lang.reflect.Constructor;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

import eu.verdelhan.ta4j.BaseTick;
import eu.verdelhan.ta4j.BaseTimeSeries;
import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.CachedIndicator;
import eu.verdelhan.ta4j.indicators.helpers.ClosePriceIndicator;

//TODO integrate as talib Generator including the data as inputs
public class Ta4jOperation extends DoubleMapOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(Ta4jOperation.class);

	public Ta4jOperation() {
		this("ta4jOperation", "Indicator using the Ta4j library",
				new StringOperation("string","ta4jClassName", "Ta4j class name", new StringValue("RSIIndicator")),
				new StringOperation("string","validityFilter", "Indicator validity requirements: CLOSE, OHLC, VOLUME, OHLCV", new StringValue("CLOSE")),
				new NumberOperation("number","indicatiorParameter", "Periods and other parameters for the indicator", new NumberValue(14.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public Ta4jOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public Ta4jOperation(String reference, String description, Operation ... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public Ta4jOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@SuppressWarnings("unchecked")
	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String ta4jClassName = ((StringValue) inputs.get(0)).getValue(targetStock);
		ValidityFilter validityFilter = ValidityFilter.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));
		List<Integer> inputParameters = inputs.subList(2, inputs.size()).stream().map(v -> ((NumberValue) v).getNumberValue().intValue()).collect(Collectors.toList());
		
		try {
			
			//Init new ClosePriceIndicator(timeSeries)
			Date shiftedStartDate = targetStock.getStartDate(thisStartShift + inputParameters.stream().reduce(0, (a, e) -> a + e));
			Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(
					targetStock.getStock(), shiftedStartDate, targetStock.getEndDate(),
					true, targetStock.getStock().getMarketValuation().getCurrency(), 0, validityFilter);
			SortedMap<Date, double[]> exactMapFromQuotations = 
					QuotationsFactories.getFactory().buildExactMapFromQuotations(quotations, 0, quotations.size()-1, QuotationDataType.values());
			List<Tick> ticksList = exactMapFromQuotations.entrySet().stream()
					.map(q -> new BaseTick(ZonedDateTime
							.ofInstant(new Date(q.getKey().getTime()).toInstant(), ZoneId.systemDefault()), 
							q.getValue()[0], q.getValue()[1], q.getValue()[2], q.getValue()[3], q.getValue()[4]))
					.collect(Collectors.toList());
			TimeSeries timeSeries = new BaseTimeSeries(ticksList); 
			
			//Init ta4jIndicator
			Class<CachedIndicator<Decimal>> ta4jClass;
			try {
				ta4jClass = (Class<CachedIndicator<Decimal>>) Class.forName("eu.verdelhan.ta4j.indicators." + ta4jClassName);
			} catch (ClassNotFoundException e2) {
				ta4jClass = (Class<CachedIndicator<Decimal>>) Class.forName("eu.verdelhan.ta4j.indicators.volume." + ta4jClassName);
			}

			CachedIndicator<Decimal> ta4jInstance;
			try {
				List<Class<?>> parameterClasses = new ArrayList<>();
				parameterClasses.add(Indicator.class);
				parameterClasses.addAll(inputParameters.stream().map(ip -> int.class).collect(Collectors.toList())); //TODO condition on primitive types
				Constructor<CachedIndicator<Decimal>> constructor = ta4jClass.getConstructor(parameterClasses.toArray(new Class<?>[0]));
				List<Object> parameters = new ArrayList<>();
				parameters.add(new ClosePriceIndicator(timeSeries));
				parameters.addAll(inputParameters);
				ta4jInstance = constructor.newInstance(parameters.toArray(new Object[0]));
			} catch (NoSuchMethodException e1) {
				List<Class<?>> parameterClasses = new ArrayList<>();
				parameterClasses.add(TimeSeries.class);
				parameterClasses.addAll(inputParameters.stream().map(ip -> int.class).collect(Collectors.toList())); //TODO condition on primitive types
				Constructor<CachedIndicator<Decimal>> constructor = ta4jClass.getConstructor(parameterClasses.toArray(new Class<?>[0]));
				List<Object> parameters = new ArrayList<>();
				parameters.add(timeSeries);
				parameters.addAll(inputParameters);
				ta4jInstance = constructor.newInstance(parameters.toArray(new Object[0]));
			}

			
			//Iterate through getValue
			List<Date> keysSet = new ArrayList<>(exactMapFromQuotations.keySet());
			final CachedIndicator<Decimal> fTa4jInstance = ta4jInstance;
			SortedMap<Date, Double> result = 
					IntStream.range(0, keysSet.size()).boxed().collect(Collectors.toMap(i -> keysSet.get(i), i -> fTa4jInstance.getValue(i).toDouble(), (a, b) -> a, TreeMap::new));
			return new DoubleMapValue(result);
		
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return new DoubleMapValue();
	}

	@Override
	public String toFormulaeShort() {
		Operation operand0 = getOperands().get(0);
		String ta4jClass = ((StringValue) operand0.getOrRunParameter(null).orElse(new StringValue(operand0.toFormulaeShort()))).getValue(null).replace("Indicator", "");
		String refa24z = "ta4j" + ta4jClass.substring(0,1) + (ta4jClass.length() -2) + ta4jClass.substring(ta4jClass.length() -1); 
		int opsSize = getOperands().size();
		String params = (opsSize > 2)
					?getOperands().subList(2, opsSize).stream().reduce("", (r, e) -> r + "_" + e.toFormulae(), (a, b) -> a + b)
					:"";
		return refa24z + params;
	}
	
	
	

}
