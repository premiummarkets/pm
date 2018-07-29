package com.finance.pms.events.operations;

import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.weather.WeatherChecker;

public class WeatherOperation extends EventMapOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(WeatherOperation.class);

	public WeatherOperation() {
		super("gx_Weather","Forecast based on weather condition");
	}

	@Override
	public EventDataValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		EventDataValue buySellEvents = new EventDataValue();
		try {

			WeatherChecker calculator = new WeatherChecker(getStartDate(targetStock.getStartDate(), thisStartShift), targetStock.getEndDate());
			SortedMap<EventKey, EventValue> eventsFor = calculator.calculateEventsFor(null, "inMem"+this.getClass().getSimpleName()+"Operation");
			buySellEvents = new EventDataValue(eventsFor);

		}
		catch (Exception e) {
			LOGGER.error(e,e);
		}

		return buySellEvents;
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}
}
