/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.queue;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;

import com.finance.pms.admin.config.Config;

public class SignalProcessorMessage extends IdentifiedObjecMessage implements Serializable {
	
	private static final long serialVersionUID = -4542088662852292807L;
	
	private String messageTxt;
	private Map<String,Config> passedThroughConfigs;
	
	private Date startDate;
	private Date endDate;
	
	private String signalProcessingName;
	private String[] additionalEventListNames;
	
	public SignalProcessorMessage(String messageTxt, Date startDate, Date endDate, String signalProcessingName, Map<String,Config> ptc, String... eventListName) {
		super(messageTxt.hashCode()+startDate.hashCode()+signalProcessingName.hashCode());
		this.messageTxt = messageTxt;
		this.passedThroughConfigs = ptc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.signalProcessingName = signalProcessingName;
		this.additionalEventListNames = eventListName;
	}
	
	public String getMessageTxt() {
		return messageTxt;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public String getSignalProcessingName() {
		return signalProcessingName;
	}
	
	public Map<String,Config> getPassedThroughConfigs() {
		return this.passedThroughConfigs;
	}


	public String[] getAdditionalEventListNames() {
		return additionalEventListNames;
	}

	
	
	public Serializable getObject() throws JMSException {
		return this;
	}

	
	public void setObject(Serializable arg0) throws JMSException {
		this.messageTxt = (String)  arg0;
	}

	
	public void acknowledge() throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void clearBody() throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void clearProperties() throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public boolean getBooleanProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public byte getByteProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public double getDoubleProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public float getFloatProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getIntProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getJMSCorrelationID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getJMSDeliveryMode() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Destination getJMSDestination() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getJMSExpiration() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getJMSMessageID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getJMSPriority() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean getJMSRedelivered() throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Destination getJMSReplyTo() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getJMSTimestamp() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getJMSType() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getLongProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Object getObjectProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration<?> getPropertyNames() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public short getShortProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getStringProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean propertyExists(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setBooleanProperty(String arg0, boolean arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setByteProperty(String arg0, byte arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setDoubleProperty(String arg0, double arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setFloatProperty(String arg0, float arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setIntProperty(String arg0, int arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSCorrelationID(String arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSDeliveryMode(int arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSDestination(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSExpiration(long arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSMessageID(String arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSPriority(int arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSRedelivered(boolean arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSReplyTo(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSTimestamp(long arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setJMSType(String arg0) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setLongProperty(String arg0, long arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setObjectProperty(String arg0, Object arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setShortProperty(String arg0, short arg1) throws JMSException {
		// TODO Auto-generated method stub
	}

	
	public void setStringProperty(String arg0, String arg1) throws JMSException {
		// TODO Auto-generated method stub
	}



	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageTxt == null) ? 0 : messageTxt.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((signalProcessingName == null) ? 0 : signalProcessingName.hashCode());
		return result;
	}



	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignalProcessorMessage other = (SignalProcessorMessage) obj;
		if (messageTxt == null) {
			if (other.messageTxt != null)
				return false;
		} else if (!messageTxt.equals(other.messageTxt))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (signalProcessingName == null) {
			if (other.signalProcessingName != null)
				return false;
		} else if (!signalProcessingName.equals(other.signalProcessingName))
			return false;
		return true;
	}


	
	public String toString() {
		return "SignalProcessorMessage [eventListName=" + additionalEventListNames + ", messageTxt=" + messageTxt
				+ ", passedThroughConfigs=" + passedThroughConfigs + ", startDate=" + startDate+ ", endDate=" + endDate
				+ ", signalProcessingName=" + signalProcessingName + ", messageKey=" + messageKey + "]";
	}
	
}
