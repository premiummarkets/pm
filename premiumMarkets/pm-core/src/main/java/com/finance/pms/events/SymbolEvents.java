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
package com.finance.pms.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.pounderationrules.DataResultComparator;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.talib.dataresults.StandardEventKey;


// TODO: Auto-generated Javadoc
/**
 * The Class SymbolEvents.
 * 
 * @author Guillaume Thoreton
 */
public class SymbolEvents implements Serializable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(SymbolEvents.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4488655947887964613L;
	
	private Stock stock;
	private Map<EventKey, EventValue> dataResultList;
	private Map<Integer, String> eventDefList;
	private EventState eventsState;
	
	private Date latestRelevantEventDate;
	private Boolean isMonitored;
	
	private Set<EventDefinition> buyTriggeringEvents;
	private Set<EventDefinition> sellTriggeringEvents;
	private Float triggeringFinalWeight;
	
	private Map<EventDefinition, SortedMap<Date, double[]>> calculationOutput;

	public SymbolEvents(Stock stock) {
		super();
		this.stock = stock;
		this.dataResultList= new HashMap<EventKey, EventValue>();
		this.eventDefList = new HashMap<Integer,String>();
		this.eventsState = EventState.STATE_TERMINATED;
	}

	/**
	 * Instantiates a new symbol events.
	 * 
	 * @param stock the stock
	 * @param eventDefList the event def list
	 * @param state the state
	 * @param datatResultList the datat result list
	 * @author Guillaume Thoreton
	 */
	public SymbolEvents(Stock stock, Map<EventKey, EventValue> dataResultList, Map<Integer, String> eventDefList, EventState state) {	
		this.stock =stock;
		this.dataResultList = dataResultList;
		this.eventDefList = eventDefList;
		this.eventsState = state;
	}
	
	/**
	 * Instantiates a new symbol events.
	 * 
	 * @param stock the stock
	 * @param eState the e state
	 * @author Guillaume Thoreton
	 */
	public SymbolEvents(Stock stock,EventState eState) {
		this.stock = stock;
		this.dataResultList = new HashMap<EventKey, EventValue>();
		this.eventDefList = new HashMap<Integer,String>();
		this.eventsState= eState;
	}
	
	public SymbolEvents(EventMessageObject eventMessageObject) {
		
		Map<EventKey, EventValue> map= new HashMap<EventKey, EventValue>();
		EventValue eventValue = eventMessageObject.getEventValue();
		StandardEventKey key = new StandardEventKey(eventValue.getDate(), eventValue.getEventDefId(), eventValue.getEventType().getEventTypeChar().toString());
		map.put(key, eventValue);
		
		this.stock = eventMessageObject.getStock();
		this.dataResultList = map;
		this.eventDefList = EventDefinition.getEventDefList();
		this.eventsState= EventState.STATE_TERMINATED;
		
	}

	/**
	 * Gets the datat result list.
	 * 
	 * @return the datat result list
	 */
	public Map<EventKey, EventValue> getDataResultList() {
		return dataResultList;
	}
	
	/**
	 * Gets the sorted data result list in date ascending order.
	 * 
	 * @return the sorted datat result list in date ascending order.
	 */
	public ArrayList<EventValue> getSortedDataResultList() {
		
		DataResultComparator drcomp = new DataResultComparator();
		ArrayList<EventValue> sortedList = new ArrayList<EventValue>(dataResultList.values());
		Collections.sort(sortedList,drcomp);
		return sortedList;
	}
	
	public ArrayList<EventValue> getSortedDataResultList(Comparator<EventValue> comparator) {
		
		ArrayList<EventValue> sortedList = new ArrayList<EventValue>(dataResultList.values());
		Collections.sort(sortedList,comparator);
		return sortedList;
	}
	
	/**
	 * Gets the symbol.
	 * 
	 * @return the symbol
	 */
	public String getSymbol() {
		return this.stock.getSymbol();
	}
	
	/**
	 * Gets the isin.
	 * 
	 * @return the isin
	 */
	public String getIsin() {
		return this.stock.getIsin();
	}
	
	/**
	 * Gets the event def list.
	 * 
	 * @return the event def list
	 */
	public Map<Integer,String> getEventDefList() {
		return eventDefList;
	}
	
	/**
	 * Gets the weight.
	 * 
	 * @param ponderationRule the pr
	 * 
	 * @return the weight
	 */
	public float getWeight(PonderationRule ponderationRule) {
			return ponderationRule.finalWeight(this);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer retour = new StringBuffer();
		Collection<EventValue> drl=this.getSortedDataResultList();
		retour.append(stock.getSymbol());
		retour.append(";");
		retour.append(stock.getName());
		retour.append(";");
		retour.append(" triggering weight : "+this.triggeringFinalWeight);
		retour.append(";");
		retour.append(" buy triggering events : "+this.buyTriggeringEvents);
		retour.append(";");
		retour.append(" sell triggering events : "+this.sellTriggeringEvents);
		retour.append(";[");
		Iterator<EventValue> drlIt = drl.iterator();
		while (drlIt.hasNext()) {
				EventValue drv = drlIt.next();
				retour.append(drv.toString());
				if (drlIt.hasNext()) retour.append(",");
		}
		retour.append("]");
		return retour.toString();
	}
	
	/**
	 * To e mail.
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	public String toEMail() {
		StringBuffer retour = new StringBuffer();
		Collection<EventValue> drl = this.getSortedDataResultList();
		if (drl.size() > 0 ) {
			for (EventValue eventValue : drl) {
				retour.append(eventValue.toEmail()+"\n\n");
			}
		} else {
			retour.append("No signal events ?!\n");
			retour.append("Event state :"+this.eventsState);
		}
		return retour.toString();
	}
	
	/**
	 * To export.
	 * 
	 * @param pr the pr
	 * 
	 * @return the string buffer
	 * 
	 * @author Guillaume Thoreton
	 */
	public StringBuffer toExport(PonderationRule pr) {
		
		StringBuffer retour = new StringBuffer();
		Collection<EventValue> drl=this.getSortedDataResultList();
		if (drl.size() > 0 ) {
			Iterator<EventValue> drlIt = drl.iterator();
			while (drlIt.hasNext()) {
				EventValue drv = drlIt.next();
				retour.append(stock.getSymbol());
				retour.append(";");
				retour.append(this.eventsState);
				retour.append(";");
				retour.append(drv.toExport(this.eventDefList));
				retour.append(";");
				retour.append(this.getWeight(pr));
				if (drlIt.hasNext()) retour.append("\n");
			}
		} else {
			retour.append(stock.getSymbol());
			retour.append(";");
			retour.append(this.eventsState);
			retour.append(";");
			retour.append("null;null;null;0");
		}
		return retour;
	}
	
	public String toAutoPortfolioLog()  {
		StringBuilder stringBuilder = new StringBuilder();
		Collection<EventValue> sortedEventValues=this.getSortedDataResultList();
		if (sortedEventValues.size() > 0 ) {
			stringBuilder.append("[");
			Iterator<EventValue> sortedEventValuesIt = sortedEventValues.iterator();
			while (sortedEventValuesIt.hasNext()) {
					EventValue eventValue = sortedEventValuesIt.next();
					stringBuilder.append(eventValue.toString());
					if (sortedEventValuesIt.hasNext()) stringBuilder.append(",");
			}
			stringBuilder.append("]");
		} else {
			stringBuilder.append("No signal events?!");
			stringBuilder.append(" Event state :"+this.eventsState);
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Gets the events state.
	 * 
	 * @return the events state
	 */
	public EventState getEventsState() {
		return eventsState;
	}

	/**
	 * Adds the event result element.
	 * 
	 * @param eventKey the ek
	 * @param eventValue the ev
	 * @param eventDefinition the edef
	 * 
	 * @author Guillaume Thoreton
	 */
	public void addEventResultElement(EventKey eventKey, EventValue eventValue, String eventDefinition) {
		this.eventDefList.put(eventValue.getEventDefId(), eventDefinition);
		this.dataResultList.put(eventKey, eventValue);
	}
	
	public void addEventResultElement(Map<EventKey,EventValue> evl, Map<Integer, String> edefl) {
		this.eventDefList.putAll(edefl);
		this.dataResultList.putAll(evl);
	}
	
	public void addEventResultElement(SymbolEvents symbolEvents) {
		this.addEventResultElement(symbolEvents.getDataResultList(),symbolEvents.getEventDefList());
	}
	
	/**
	 * Sort list by symbols.
	 * 
	 * @param events the events
	 * @param secomp the secomp
	 * 
	 * @return the list< symbol events>
	 * 
	 * @author Guillaume Thoreton
	 */
	public static List<SymbolEvents> sortList(List<SymbolEvents> events, Comparator<SymbolEvents> secomp) {
		Collections.sort(events,secomp);
		return events;
	}
	
	
	/**
	 * Gets the symbol name.
	 * 
	 * @return the symbol name
	 */
	public String getSymbolName() {
		return this.stock.getName();
	}

	/**
	 * Gets the monitor level.
	 * 
	 * @return the monitor level
	 */
	public Boolean isMonitored() {
		if (isMonitored == null) {
			isMonitored = PortfolioMgr.getInstance().isMonitored(stock);
		}
		return isMonitored;
	}

	/**
	 * Gets the stock.
	 * 
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	
	public Date getLatestRelevantEventDate() {
		return latestRelevantEventDate;
	}

	public void setLatestRelevantEventDate(Date lastRelevantEvent) {
		this.latestRelevantEventDate = lastRelevantEvent;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	//TODO add analyse name
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SymbolEvents other = (SymbolEvents) obj;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	
	public Date getLastDate() {
		Integer nbEvents = this.getDataResultList().size();
		return getDateForResIndex(nbEvents-1);
	}
	
	public Date getFirstDate() {
		return getDateForResIndex(0);
	}

	/**
	 * @return
	 */
	private Date getDateForResIndex(Integer index) {
		Integer nbEvents = this.getDataResultList().size();
		if (nbEvents > 0) {
			Date date = this.getSortedDataResultList().get(index).getDate();
			Date updatedDate = setHoursMinAndCo(date);
			return updatedDate;
		} else {
			return new Date();
		}
	}

	/**
	 * @param date
	 * @return
	 */
	private Date setHoursMinAndCo(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int millisec = calendar.get(Calendar.MILLISECOND);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, sec);
		calendar.set(Calendar.MILLISECOND, millisec);

		return  calendar.getTime();
	}

	//AutoPortfolio stuff //TODO implement a sub class to Symbol Events
	public void setTriggeringInfo(Float finalWeight, Set<EventDefinition> parsedEventDefs, Integer sellEventTriggerThreshold, Integer buyEventTriggerThreshold) {
		
		this.triggeringFinalWeight = finalWeight;
		
		if (triggeringFinalWeight >= buyEventTriggerThreshold)  {
			this.buyTriggeringEvents = parsedEventDefs;			
		} else {
			if (triggeringFinalWeight <= sellEventTriggerThreshold) {
				this.sellTriggeringEvents = parsedEventDefs;
			}
		}
	}

	public Set<EventDefinition> getBuyTriggeringEvents() {
		return buyTriggeringEvents;
	}

	public Float getTriggeringFinalWeight() {
		return triggeringFinalWeight;
	}

	public Set<EventDefinition> getSellTriggeringEvents() {
		return sellTriggeringEvents;
	}
	//End Auto portfolio stuff

	public Map<EventDefinition, SortedMap<Date, double[]>> getCalculationOutput() {
		return calculationOutput;
	}

	public void addCalculationOutput(EventDefinition eventDefinition, SortedMap<Date, double[]> calculationOutput) {
		if (this.calculationOutput == null) {
			this.calculationOutput = new HashMap<EventDefinition, SortedMap<Date,double[]>>();
		}
		this.calculationOutput.put(eventDefinition, calculationOutput);
		
	}

}
