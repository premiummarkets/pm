package com.finance.pms.events.pounderationrules;

import java.util.Date;
import java.util.SortedMap;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;

public class SimpleIndicatorPondertionRule extends PonderationRule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Float finalWeight(SymbolEvents symbolEvents) {
		
		SortedMap<EventKey, EventValue> naturalOrderEvents = symbolEvents.getDataResultMap();
		if (naturalOrderEvents.isEmpty()) {
			return 0f;
		}
		EventKey lastKey = naturalOrderEvents.lastKey();
		EventValue lastEvent = naturalOrderEvents.get(lastKey);
		
		DefaultSignal signal = new DefaultSignal(symbolEvents.getEventDefList());
		signal.addEvent(lastKey, lastEvent);
		symbolEvents.setSignal(signal);
		
		Date lastDate = lastKey.getDate();
		Integer weight = naturalOrderEvents.values().stream()
			.filter(v -> v.getDate().equals(lastDate) && !isAlert(v))
			.map(v -> {
				switch (v.getEventType()) {
				case BEARISH:
					return -1;
				case BULLISH:
					return 1;
				default:
					return 0;
				}
			})
			.reduce(0, (a, e) -> a + e);
		
		symbolEvents.getSignal().setTriggeringInfo(((DefaultSignal) symbolEvents.getSignal()).getParsedEventDefs(), -1, 1);
		Integer signalWeight = symbolEvents.getSignal().getSignalWeight();
		
		assert weight == signalWeight;
		return weight.floatValue();
	}
	
	protected boolean isAlert(EventValue eventValue) {
		return (eventValue.getEventDef().equals(EventDefinition.ALERTTHRESHOLD) || eventValue.getEventDef().equals(EventDefinition.SCREENER));
	}

	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		PonderationRule p1 = new SimpleIndicatorPondertionRule();
		PonderationRule p2 = new SimpleIndicatorPondertionRule();
		//!!The compareCal is in inverse order
		return super.compareCal(o1, o2, p1, p2);
	}

	@Override
	protected void postCondition(Signal signal) {
	}

	@Override
	protected Boolean shallExit() {
		return null;
	}

	@Override
	protected Signal initSignal(SymbolEvents symbolEvents) {
		return null;
	}

}
