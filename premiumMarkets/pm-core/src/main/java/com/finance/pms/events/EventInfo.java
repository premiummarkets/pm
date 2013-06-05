package com.finance.pms.events;

import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.portfolio.InfoObject;

public interface EventInfo extends InfoObject {
	
	public String info();
	
	public String getEventDefinitionRef();
	
	public EventDefDescriptor getEventDefDescriptor();

	public String getEventReadableDef();

	public Boolean getIsContinous();
	
	public boolean equals(EventInfo obj);

	public int compareTo(EventInfo eventInfo);

	public Integer getEventDefId();

}
