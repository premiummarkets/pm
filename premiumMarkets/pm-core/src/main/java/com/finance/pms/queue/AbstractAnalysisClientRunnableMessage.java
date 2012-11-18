/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.threads.ConfigThreadLocal;

public abstract class AbstractAnalysisClientRunnableMessage extends IdentifiedObjecMessage implements Serializable, Runnable {
	
	private static final long serialVersionUID = 6542921302579352482L;
	
	protected JmsTemplate jmsTemplate;
	protected Queue eventQueue;
	protected SpringContext springContext;
	protected AnalysisClient indicatorAnalysis;
	protected Map<String,Config> configs;

	protected String analysisName;
	protected Object syncObject;
	
	public AbstractAnalysisClientRunnableMessage(Integer key, SpringContext springContext, String analysisName) {
		super(key);
		
		this.springContext = springContext;
		indicatorAnalysis = (AnalysisClient) springContext.getBean("indicatorAnalysis");
		eventQueue = (InnerQueue) springContext.getBean("eventqueue");
		jmsTemplate = (JmsTemplate) springContext.getBean("jmsTemplate");
		
		this.configs = ConfigThreadLocal.getAll();
		this.analysisName = analysisName;
		
		this.syncObject = new Object();
		
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public AbstractAnalysisClientRunnableMessage(Integer key, String analysisName) {
		super(key);	
		this.analysisName = analysisName;
	}

	//TODO factorise as well ...
	protected void sendRunnableStartProcessingEvent(final String analyseName,final AbstractAnalysisClientRunnableMessage objectMessage) {	
		
		jmsTemplate.send(eventQueue, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				return objectMessage;
			}
		});
	}
	
	//TODO factorise with IndicatorsCalculationService to create a specific service.
	//Analyse name : the name of the auto portfolio.
	//Event list name : event list to process. It will be added to an event list with the portfolio name at processing time
	public void sendAutoportfolioSignalProcessingEvent(final String signalProcessingPortfolioName, final Date startDate, final Date endDate, final String... additionalEventListName) {

		this.jmsTemplate.send(this.eventQueue, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				Map<String, Config> signalConfig = new HashMap<String, Config>();
				signalConfig.put(Config.EVENT_SIGNAL_NAME, ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME));
				Message message = new BuySellSignalCalculatorMessage("Run Auto portfolios Calculation from " + startDate + " to "+endDate, startDate, endDate, signalProcessingPortfolioName, signalConfig, additionalEventListName);
				return message;
			}
		});
	}
	
	protected void closeSpringContext() {
		indicatorAnalysis.close();
		springContext.close();
	}

	
	public Serializable getObject() throws JMSException {
		return this;
	}

	
	public void setObject(Serializable arg0) throws JMSException {
		// TODO Auto-generated method stub
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

	public String getAnalysisName() {
		return analysisName;
	}

	
	public String toString() {
		return "AbstractAnalyseClientRunnableMessage [analysisName=" + analysisName + ", configs=" + configs + ", messageKey="
				+ messageKey + "]";
	}



	
}
