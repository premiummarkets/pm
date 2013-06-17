package com.finance.pms.events;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmailSendingFilter {
	
	private Date filterDate;
	private Set<String> filtered;

	public EmailSendingFilter(Date filterDate, Set<EventInfo> eventDefs) {
		this.filtered = new HashSet<String>();
		this.filterDate = filterDate;
		for (EventInfo eventInfo : eventDefs) {
			filtered.add(eventInfo.getEventDefinitionRef());
		}
	}

	public Set<String> filtered() {
		return filtered;
	}

	public long filterDate() {
		return filterDate.getTime();
	}
	
	

}
