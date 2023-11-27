package com.finance.pms.events.operations;

import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.weather.WeatherChecker;

public class WeatherOperation extends EventMapOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(WeatherOperation.class);

	public WeatherOperation() {
		super("gx_Weather","Forecast based on weather condition");
	}

	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		EventMapValue buySellEvents = new EventMapValue();
		try {

			WeatherChecker calculator = new WeatherChecker(targetStock.getStartDate(thisStartShift), targetStock.getEndDate());
			SortedMap<EventKey, EventValue> eventsFor = calculator.calculateEventsFor(null, "inMem"+this.getClass().getSimpleName()+"Operation");
			buySellEvents = new EventMapValue(eventsFor, false);

		}
		catch (Exception e) {
			LOGGER.error(e,e);
		}

		return buySellEvents;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}
}
