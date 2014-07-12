/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.queue;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;

import com.finance.pms.admin.config.Config;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventMessageObject;
import com.finance.pms.events.EventValue;



// TODO: Auto-generated Javadoc
/**
 * The Class InnerMessage.
 * 
 * @author Guillaume Thoreton
 */
public class SingleEventMessage extends EmailMessage {

	/** The p. */
	private Properties properties = new Properties();
	
	private EventMessageObject eventMessageObject;


	/**
	 * Instantiates a new inner message.
	 * 
	 * @param eventInMess the event in mess
	 * 
	 * @author Guillaume Thoreton
	 * @param ptc 
	 */
	public SingleEventMessage(String analyseName, Date calculationDate, Stock stock, EventValue eventInMess, Map<String, Config> ptc) {
		super(analyseName.hashCode()+stock.hashCode()+eventInMess.hashCode(), ptc);
		this.eventMessageObject = new EventMessageObject(analyseName, calculationDate, eventInMess, stock);
	}
	

	/**
	 * Instantiates a new inner message.
	 * 
	 * @param eventInMess the event in mess
	 * 
	 * @author Guillaume Thoreton
	 * @param ptc 
	 */
	private SingleEventMessage(EventMessageObject eventInMess, Map<String, Config> ptc) {
		super(eventInMess.hashCode(), ptc);
		this.eventMessageObject = eventInMess;
	}
	
	/**
	 * Instantiates a new inner message.
	 * 
	 * @param symbolEvents the event in mess
	 * 
	 * @author Guillaume Thoreton
	 * @param ptc 
	 */
	public SingleEventMessage(Serializable eventMessageObject, Map<String, Config> ptc) {
		this((EventMessageObject) eventMessageObject, ptc);
	}
	
	

	/* (non-Javadoc)
	 * @see javax.jms.ObjectMessage#getObject()
	 */
	public Serializable getObject() throws JMSException {
		return this.eventMessageObject;
	}


	/* (non-Javadoc)
	 * @see javax.jms.ObjectMessage#setObject(java.io.Serializable)
	 */
	public void setObject(Serializable arg0) throws JMSException {
		throw new UnsupportedOperationException();
		
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#acknowledge()
	 */
	public void acknowledge() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#clearBody()
	 */
	public void clearBody() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#clearProperties()
	 */
	public void clearProperties() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getBooleanProperty(java.lang.String)
	 */
	public boolean getBooleanProperty(String arg0) throws JMSException {
		return new Boolean(properties.getProperty(arg0));
		//throw new UnsupportedOperationException();
		//return false;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getByteProperty(java.lang.String)
	 */
	public byte getByteProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getDoubleProperty(java.lang.String)
	 */
	public double getDoubleProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getFloatProperty(java.lang.String)
	 */
	public float getFloatProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getIntProperty(java.lang.String)
	 */
	public int getIntProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSCorrelationID()
	 */
	public String getJMSCorrelationID() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSCorrelationIDAsBytes()
	 */
	public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSDeliveryMode()
	 */
	public int getJMSDeliveryMode() throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSDestination()
	 */
	public Destination getJMSDestination() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSExpiration()
	 */
	public long getJMSExpiration() throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSMessageID()
	 */
	public String getJMSMessageID() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSPriority()
	 */
	public int getJMSPriority() throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSRedelivered()
	 */
	public boolean getJMSRedelivered() throws JMSException {
		throw new UnsupportedOperationException();
		//return false;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSReplyTo()
	 */
	public Destination getJMSReplyTo() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSTimestamp()
	 */
	public long getJMSTimestamp() throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getJMSType()
	 */
	public String getJMSType() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getLongProperty(java.lang.String)
	 */
	public long getLongProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getObjectProperty(java.lang.String)
	 */
	public Object getObjectProperty(String arg0) throws JMSException {
		return properties.get(arg0);
	}


	public Enumeration<?> getPropertyNames() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getShortProperty(java.lang.String)
	 */
	public short getShortProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#getStringProperty(java.lang.String)
	 */
	public String getStringProperty(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#propertyExists(java.lang.String)
	 */
	public boolean propertyExists(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return false;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setBooleanProperty(java.lang.String, boolean)
	 */
	public void setBooleanProperty(String arg0, boolean arg1) throws JMSException {
		properties.setProperty(arg0, (new Boolean(arg1)).toString());
		//throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setByteProperty(java.lang.String, byte)
	 */
	public void setByteProperty(String arg0, byte arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setDoubleProperty(java.lang.String, double)
	 */
	public void setDoubleProperty(String arg0, double arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setFloatProperty(java.lang.String, float)
	 */
	public void setFloatProperty(String arg0, float arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setIntProperty(java.lang.String, int)
	 */
	public void setIntProperty(String arg0, int arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSCorrelationID(java.lang.String)
	 */
	public void setJMSCorrelationID(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSCorrelationIDAsBytes(byte[])
	 */
	public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSDeliveryMode(int)
	 */
	public void setJMSDeliveryMode(int arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSDestination(javax.jms.Destination)
	 */
	public void setJMSDestination(Destination arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSExpiration(long)
	 */
	public void setJMSExpiration(long arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSMessageID(java.lang.String)
	 */
	public void setJMSMessageID(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSPriority(int)
	 */
	public void setJMSPriority(int arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSRedelivered(boolean)
	 */
	public void setJMSRedelivered(boolean arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSReplyTo(javax.jms.Destination)
	 */
	public void setJMSReplyTo(Destination arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSTimestamp(long)
	 */
	public void setJMSTimestamp(long arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setJMSType(java.lang.String)
	 */
	public void setJMSType(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setLongProperty(java.lang.String, long)
	 */
	public void setLongProperty(String arg0, long arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setObjectProperty(java.lang.String, java.lang.Object)
	 */
	public void setObjectProperty(String arg0, Object arg1) throws JMSException {
		properties.put(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setShortProperty(java.lang.String, short)
	 */
	public void setShortProperty(String arg0, short arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Message#setStringProperty(java.lang.String, java.lang.String)
	 */
	public void setStringProperty(String arg0, String arg1) throws JMSException {
		throw new UnsupportedOperationException();
	}
	
	public EventMessageObject getEventMessageObject() {
		return eventMessageObject;
	}


	public void setEventMessageObject(EventMessageObject eventMessageObject) {
		this.eventMessageObject = eventMessageObject;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventMessageObject == null) ? 0 : eventMessageObject.hashCode());
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
		SingleEventMessage other = (SingleEventMessage) obj;
		if (eventMessageObject == null) {
			if (other.eventMessageObject != null)
				return false;
		} else if (!eventMessageObject.equals(other.eventMessageObject))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SingleEventMessage [eventMessageObject=" + eventMessageObject + ", properties=" + properties + ", messageKey="
				+ messageKey + "]";
	}

	
}
