package com.finance.pms.events.pounderationrules;

import java.util.Set;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;

public class LatestEventsAllIndDefsSignal extends LatestEventsSignal {

	private Set<EventInfo> maxPassPrefsEventDefinitions = EventDefinition.loadMaxPassPrefsEventInfo();

	public LatestEventsAllIndDefsSignal() {
		super(false, true);
	}

	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {
		
		if (eventValue.getEventType().equals(EventType.INFO)) return false;
		if (eventValue.getEventType().equals(EventType.NONE)) return false;
		if (isAlert(eventValue)) return false;

		if (maxPassPrefsEventDefinitions.contains(eventValue.getEventDef())) return true;
		
		return false;
	}
	
	
	

}
