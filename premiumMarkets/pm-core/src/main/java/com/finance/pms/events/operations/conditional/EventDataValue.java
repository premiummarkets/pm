package com.finance.pms.events.operations.conditional;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class EventDataValue extends Value<SortedMap<EventKey, EventValue>> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventDataValue.class);
	
	private SortedMap<EventKey, EventValue> eventData;


	public EventDataValue() {
		super();
		eventData = new TreeMap<EventKey, EventValue>();
	}

	public EventDataValue(SortedMap<EventKey, EventValue> eventData) {
		super();
		this.eventData = eventData;
	}


	@Override
	public SortedMap<EventKey, EventValue> getValue(TargetStockInfo targetStock) {
		return eventData;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+eventData.size() + ((eventData.size() > 0)?", first key "+eventData.firstKey()+ ", last key "+eventData.lastKey():"");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			EventDataValue clone = (EventDataValue) super.clone();
			clone.eventData = (SortedMap<EventKey, EventValue>) ((TreeMap<EventKey, EventValue>)this.eventData).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
