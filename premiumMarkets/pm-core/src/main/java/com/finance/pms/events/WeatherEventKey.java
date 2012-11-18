package com.finance.pms.events;

import java.util.Date;

public class WeatherEventKey implements EventKey {

	private static final long serialVersionUID = 1806331274610032030L;

	private Date date;
	private EventDefinition eventdef;
	private EventType eventType;
	private String hint;
	
	

	public WeatherEventKey(Date date, EventDefinition eventdef, EventType eventType, String hint) {
		super();
		this.date = date;
		this.eventdef = eventdef;
		this.eventType = eventType;
		this.hint = hint;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEventDefId() {
		return eventdef;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEvenType() {
		return eventType;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEventDefExtra() {
		return hint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((eventdef == null) ? 0 : eventdef.hashCode());
		result = prime * result + ((hint == null) ? 0 : hint.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherEventKey other = (WeatherEventKey) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventType != other.eventType)
			return false;
		if (eventdef != other.eventdef)
			return false;
		if (hint == null) {
			if (other.hint != null)
				return false;
		} else if (!hint.equals(other.hint))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeatherEventKey [date=" + date + ", eventdef=" + eventdef + ", eventType=" + eventType + ", hint=" + hint + "]";
	}
	

}
