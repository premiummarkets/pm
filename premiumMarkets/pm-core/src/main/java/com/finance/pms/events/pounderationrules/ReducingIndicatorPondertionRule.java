package com.finance.pms.events.pounderationrules;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;

public class ReducingIndicatorPondertionRule extends PonderationRule {
	
	private static final long serialVersionUID = 1L;
	
	private Integer buySellTriggerSpan;
	int ifCount;
	int thenCount;
	
	public ReducingIndicatorPondertionRule() {
		this.buySellTriggerSpan = 2; //4;
		this.ifCount = 1; //2;
		this.thenCount = 1; //2;
	}

	public ReducingIndicatorPondertionRule(int buySellTriggerSpan, int ifCount, int thenCount) {
		this.buySellTriggerSpan = buySellTriggerSpan;
		if (this.buySellTriggerSpan < 2) throw new RuntimeException("Invalid parameter: buySellTriggerSpan must be >= 2");
		this.ifCount = ifCount;
		this.thenCount = thenCount;
	}

	@Override
	public Float finalWeight(SymbolEvents symbolEvents) {
		
		int weight = 0;

		SortedMap<EventKey, EventValue> originalMap = symbolEvents.getSortedDataResultMap();
		int skip = originalMap.size() - buySellTriggerSpan;
		SortedMap<EventKey, EventValue> naturalOrderEvents = originalMap
				.entrySet().stream()
				.skip(Math.max(0, skip)) //last evtsCalcsParams.getBuySellTriggerSpan()
				.filter(e -> isRelevant(e.getValue()))
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
		
		if (!naturalOrderEvents.isEmpty()) {
			
			EventKey lastKey = naturalOrderEvents.lastKey();
			EventValue lastEvent = naturalOrderEvents.get(lastKey);
			
			// ssbb -> 1
			// bbss -> -1
			// other -> 0
			//Only a sequence s0..snb0..bm with m>=2 should return 1
			//Only a sequence b0..bns0..sm with m>=2 should return -1
	        EventValue initialEvent = null;
	        int firstEventTypeCount = 0;
			int lastEvtTypeCount = 0;
			for (EventValue ev : naturalOrderEvents.values()) {
				if (initialEvent == null) initialEvent = ev;
				if (ev.getEventType().equals(initialEvent.getEventType())) {
					if (lastEvtTypeCount == 0) {//first event continuity
						firstEventTypeCount++;
						continue; 
					} else { //s..sb..bs..s jitter like encountered
						weight = 0;
						break;
					}
				}
				if (!ev.getEventType().equals(initialEvent.getEventType())) { //break continuity
					lastEvtTypeCount++;
				}
			}
			
			if (firstEventTypeCount >= ifCount && lastEvtTypeCount >= thenCount) {
				switch (lastEvent.getEventType()) {
					case BEARISH:
						weight = -1;
						break;
					case BULLISH:
						weight = 1;
						break;
					default:
						weight = 0;
					}
			} else {
				weight = 0;
			}
			
			final int fw = weight;
			Signal signal = new Signal(symbolEvents.getEventDefList()) {
				@Override
				public Integer addEvent(EventKey eventKey, EventValue eventValue) {
					if (fw != 0) {
						latestRelevantEventDate = eventValue.getDate();
						parsedEventDefs.add(eventValue.getEventDef());
					}
					this.signalWeight = fw;
					return 0;
				}
			};
			
			symbolEvents.setSignal(signal);
			signal.addEvent(lastKey, lastEvent);
			signal.setLatestRelevantEventDate(lastKey.getDate());
			signal.setTriggeringInfo(symbolEvents.getSignal().getParsedEventDefs(), -1, 1);
		
		} else {
			symbolEvents.setSignal(new Signal() {
				@Override
				public Integer addEvent(EventKey eventKey, EventValue eventValue) {
					throw new NotImplementedException();
				}
			});
		}
		
		int signalWeight = symbolEvents.getSignal().getSignalWeight();
		assert weight == signalWeight;
		
		return Float.valueOf(weight);
	}
	
	private boolean isRelevant(EventValue ev) {
		return !isAlert(ev) && (ev.getEventType().equals(EventType.BEARISH) || ev.getEventType().equals(EventType.BULLISH));
	}
	
	private boolean isAlert(EventValue eventValue) {
		return (eventValue.getEventDef().equals(EventDefinition.ALERTTHRESHOLD) || eventValue.getEventDef().equals(EventDefinition.SCREENER));
	}

	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		PonderationRule p1 = new ReducingIndicatorPondertionRule();
		PonderationRule p2 = new ReducingIndicatorPondertionRule();
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
