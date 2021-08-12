package com.finance.pms.events.operations;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.commons.math3.stat.descriptive.rank.Min;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;


public class RandomOperation extends EventMapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(RandomOperation.class);
	
	private static Double DEFAULT_MEDIAN = 3.0;
	private static Double DEFAULT_MEAN = 8.0;
	private static Double DEFAULT_STD = 15.0;
	
	private static Double DEFAULT_MIN = 1.0;
	private static Double DEFAULT_MAX = 138.0;

	public RandomOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public RandomOperation() {
		this("gx_random", "Random bullish/bearish.",
						new NumberOperation("number","min","Min period length", new NumberValue(DEFAULT_MIN)),
						new NumberOperation("number","max","Max Period length", new NumberValue(DEFAULT_MAX)),
						new NumberOperation("number","mean","Mean of the Period lengths", new NumberValue(DEFAULT_MEAN)),
						new NumberOperation("number","median","Median of the Period lengths", new NumberValue(DEFAULT_MEDIAN)),
						new NumberOperation("number","std","Stdev of the Period lengths", new NumberValue(DEFAULT_STD)));
	}

	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double min = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double max = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Double mean = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
		Double median = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue();
		Double std = ((NumberValue) inputs.get(4)).getValue(targetStock).doubleValue();
		
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
			
		Date startDate = targetStock.getStartDate(thisStartShift);
		Date endDate = targetStock.getEndDate();

		int randomFirstEvtType = new SecureRandom().nextInt(2);
		EventType[] eventTypeHolder = new EventType[1];
		eventTypeHolder[0] = (randomFirstEvtType == 0)? EventType.BEARISH:EventType.BULLISH;
		
		edata.put(
				new ParameterizedEventKey(startDate, EventDefinition.RANDOM, eventTypeHolder[0]), 
				new EventValue(startDate, EventDefinition.RANDOM, eventTypeHolder[0], targetStock.getAnalysisName()));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		
		double gr = 10;
		//DoubleStream randomDoubles = new SecureRandom().doubles(Math.log10(min)/Math.log10(gr), Math.log10(max)/Math.log10(gr));
		DoubleStream randomDoubles = new SecureRandom().doubles(0, 1);
		edata.putAll(
			randomDoubles
				.mapToObj(random -> {
					//x = log(1-u)/(-lambda)
					//int duration = (int) Math.pow(gr, random);
					int duration = (int) (Math.log(1-random)/(-1d/0.5d));
					
					LOGGER.info("duration: " + duration + ". From ln: " + random);
					calendar.add(Calendar.DAY_OF_YEAR, duration);
					Date eventDate = calendar.getTime();
					calendar.setTime(eventDate);
					eventTypeHolder[0] = (eventTypeHolder[0].equals(EventType.BULLISH)) ? EventType.BEARISH : EventType.BULLISH;
					return new ParameterizedEventKey(eventDate, EventDefinition.RANDOM, eventTypeHolder[0]);
				})
				.takeWhile(ek -> ek.getDate().compareTo(endDate) <= 0)
				.collect(Collectors
						.toMap(ek -> ek, ek -> new EventValue(ek.getDate(), ek.getEventInfo(), ek.getEventType(), targetStock.getAnalysisName()), (a, b) -> a, TreeMap<EventKey, EventValue>::new)));

		log(edata.keySet(), endDate);
		
		return new EventMapValue(edata, false);
	}
	
	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	private void log(Set<EventKey> keySet, Date endDate) {
		
		int keySetSize = keySet.size();
		ParameterizedEventKey[] keysArray = Arrays.copyOf(keySet.toArray(new ParameterizedEventKey[0]), keySetSize +1);
		keysArray[keySetSize] = new ParameterizedEventKey(endDate, EventDefinition.RANDOM, EventType.NONE);
		double[] r = new double[keySetSize];
		for (int i = 0; i < keySetSize; i++) {
			Long pd = Duration.between(
					new Date(keysArray[i].getDate().getTime()).toInstant(), 
					new Date(keysArray[i+1].getDate().getTime()).toInstant()).toDays();
			r[i] = pd.doubleValue();
		};
		LOGGER.info("Durations: " + Arrays.toString(r));
		Median m = new Median();
		double evaluate = m.evaluate(r);
		LOGGER.info("Median: " + evaluate);
		Mean m2 = new Mean();
		evaluate = m2.evaluate(r);
		LOGGER.info("Mean: " + evaluate);
		Min m3 = new Min();
		evaluate = m3.evaluate(r);
		LOGGER.info("Min: " + evaluate);
		Max m4 = new Max();
		evaluate = m4.evaluate(r);
		LOGGER.info("Max: " + evaluate);
		StandardDeviation stdev = new StandardDeviation();
		evaluate = stdev.evaluate(r);
		LOGGER.info("StandardDeviation: " + evaluate);
		
	}

}
