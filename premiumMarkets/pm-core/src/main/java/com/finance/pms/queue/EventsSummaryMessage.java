package com.finance.pms.queue;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;

import com.finance.pms.admin.config.Config;

public class EventsSummaryMessage  extends IdentifiedObjecMessage implements Serializable {
	
	private static final long serialVersionUID = -1638056015243300764L;
	
	private String messageTxt;
	private Date startDate;
	private Date endDate;
	
	private Map<String,Config> passedThroughConfigs;
	private String signalProcessingName;
	
	private Boolean isEventsPersisted;

	protected EventsSummaryMessage(String messageTxt, Date startDate, Date endDate, String signalProcessingName, Map<String,Config> ptc, Boolean isEventsPersisted) {
		super(messageTxt.hashCode()+startDate.hashCode()+signalProcessingName.hashCode());
		this.messageTxt = messageTxt;
		this.passedThroughConfigs = ptc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.signalProcessingName = signalProcessingName;
		this.isEventsPersisted = isEventsPersisted;

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
	
	
	public Serializable getObject() throws JMSException {
		return this;
	}

	
	public void setObject(Serializable arg0) throws JMSException {
		this.messageTxt = (String)  arg0;
	}

	@Override
	public void acknowledge() throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearBody() throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearProperties() throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getBooleanProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByteProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDoubleProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloatProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSCorrelationID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getJMSDeliveryMode() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Destination getJMSDestination() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getJMSExpiration() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSMessageID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getJMSPriority() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getJMSRedelivered() throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Destination getJMSReplyTo() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getJMSTimestamp() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSType() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLongProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getObjectProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getPropertyNames() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getShortProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStringProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean propertyExists(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBooleanProperty(String arg0, boolean arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setByteProperty(String arg0, byte arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDoubleProperty(String arg0, double arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFloatProperty(String arg0, float arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntProperty(String arg0, int arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSCorrelationID(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSDeliveryMode(int arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSDestination(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSExpiration(long arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSMessageID(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSPriority(int arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSRedelivered(boolean arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSReplyTo(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSTimestamp(long arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJMSType(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLongProperty(String arg0, long arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObjectProperty(String arg0, Object arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShortProperty(String arg0, short arg1) throws JMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
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
		EventsSummaryMessage other = (EventsSummaryMessage) obj;
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
		return "SignalProcessorMessage [messageTxt=" + messageTxt
				+ ", passedThroughConfigs=" + passedThroughConfigs + ", startDate=" + startDate+ ", endDate=" + endDate
				+ ", signalProcessingName=" + signalProcessingName + ", messageKey=" + messageKey + "]";
	}

	public Boolean isEventsPersisted() {
		return isEventsPersisted;
	}

}
