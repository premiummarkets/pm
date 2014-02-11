/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.talib.dataresults.StandardEventKey;


/**
 * The Class SymbolEvents.
 * 
 * @author Guillaume Thoreton
 */
public class SymbolEvents implements Serializable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(SymbolEvents.class);
	
	private static final long serialVersionUID = 4488655947887964613L;
	
	private Stock stock;
	private ConcurrentSkipListMap<EventKey, EventValue> dataResultMap;
	private Set<String> eventDefList;
	private EventState eventsState;
	
	private Date latestRelevantEventDate;
	
	private Set<EventInfo> buyTriggeringEvents;
	private Set<EventInfo> sellTriggeringEvents;
	private Float triggeringFinalWeight;
	
	private Map<EventInfo, SortedMap<Date, double[]>> calculationOutput;

	public SymbolEvents(Stock stock) {
		super();
		this.stock = stock;
		this.dataResultMap= new ConcurrentSkipListMap<EventKey, EventValue>();
		this.eventDefList = new HashSet<String>();
		this.eventsState = EventState.STATE_TERMINATED;
	}

	public SymbolEvents(Stock stock, ConcurrentSkipListMap<EventKey, EventValue> dataResultList,Collection<?> eventDefList, EventState state) {	
		this.stock =stock;
		this.dataResultMap = dataResultList;
		this.eventDefList = new HashSet<String>();
		for (Object eventInfo : eventDefList) {
			if (eventInfo instanceof EventInfo) {
				this.eventDefList.add(((EventInfo) eventInfo).getEventDefinitionRef());
			} else {
				this.eventDefList.add((String) eventInfo);
			}
		}
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
		this.dataResultMap = new ConcurrentSkipListMap<EventKey, EventValue>();
		this.eventDefList = new HashSet<String>();
		this.eventsState= eState;
	}
	
	public SymbolEvents(EventMessageObject eventMessageObject) throws NoSuchFieldException {
		
		ConcurrentSkipListMap<EventKey, EventValue> map= new ConcurrentSkipListMap<EventKey, EventValue>();
		EventValue eventValue = eventMessageObject.getEventValue();
		StandardEventKey key = new StandardEventKey(eventValue.getDate(), eventValue.getEventDef().getEventDefinitionRef(), eventValue.getEventType().getEventTypeChar().toString());
		map.put(key, eventValue);
		
		this.stock = eventMessageObject.getStock();
		this.dataResultMap = map;
		this.eventDefList = new HashSet<String>();
		this.eventsState= EventState.STATE_TERMINATED;
		
	}

	/**
	 * Gets the datat result list.
	 * 
	 * @return the datat result list
	 */
	public SortedMap<EventKey, EventValue> getDataResultMap() {
		return dataResultMap;
	}
	
	public SortedMap<EventKey, EventValue> getSortedDataResultMap() {
		return dataResultMap;
	}
	
	public SortedMap<EventKey, EventValue> getSortedDataResultMap(Comparator<EventKey> comparator) {
		TreeMap<EventKey, EventValue> sortedList = new TreeMap<EventKey, EventValue>(comparator);
		sortedList.putAll(dataResultMap);
		return sortedList;
	}
	
	/**
	 * Gets the sorted data result list in date ascending order.
	 * 
	 * @return the sorted datat result list in date ascending order.
	 */
	public ArrayList<EventValue> getSortedDataResultList() {
		return new ArrayList<EventValue>(dataResultMap.values());
	}
	
	public ArrayList<EventValue> getSortedDataResultList(Comparator<EventValue> comparator) {
		
		ArrayList<EventValue> sortedList = new ArrayList<EventValue>(dataResultMap.values());
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
	public Set<String> getEventDefList() {
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
	
	public String toEMail() {
		StringBuffer retour = new StringBuffer();
		List<EventValue> drl = new ArrayList<EventValue>(getSortedDataResultList());
		if (drl.size() > 0 ) {
			Collections.reverse(drl);
			for (EventValue eventValue : drl) {
				retour.append(eventValue.toEmail()+"\n");
			}
		} else {
			retour.append("No events !?\n");
			retour.append("Event state :"+this.eventsState);
		}
		return retour.toString();
	}
	
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
		this.eventDefList.add(eventDefinition);
		this.dataResultMap.put(eventKey, eventValue);
	}
	

	public void addEventResultElement(SortedMap<EventKey,EventValue> evl, Collection<?> edefl) {
		try {
			
			for (Object eventInfo : edefl) {
				if (eventInfo instanceof EventInfo) {
					eventDefList.add(((EventInfo) eventInfo).getEventDefinitionRef());
				} else {
					eventDefList.add((String) eventInfo);
				}
			}
			this.dataResultMap.putAll(evl);
			
		} catch (RuntimeException e) {
			String dataResult = "Current data result : \n";
			for (EventKey key : this.dataResultMap.keySet()) {
				 dataResult = dataResult + key+" ; "+this.dataResultMap.get(key) + "\n";
			}
			LOGGER.error(dataResult);
			String addedEvents = "Added events : \n";
			for (EventKey key : evl.keySet()) {
				addedEvents = addedEvents +key+" ; "+evl.get(key) + "\n";
			}
			LOGGER.error(addedEvents);
			throw e;
		}
	}
	
	public void addEventResultElement(SortedMap<EventKey,EventValue> evl,  EventInfo eventDefinition) {
		this.eventDefList.add(eventDefinition.getEventDefinitionRef());
		this.dataResultMap.putAll(evl);
	}
	
	public void addEventResultElement(SymbolEvents symbolEvents) {
		this.addEventResultElement(symbolEvents.getDataResultMap(),symbolEvents.getEventDefList());
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
		return dataResultMap.lastKey().getDate();
	}
	
	public Date getFirstDate() {
		return dataResultMap.firstKey().getDate();
	}

	//AutoPortfolio stuff //TODO implement a sub class to Symbol Events
	public void setTriggeringInfo(Float finalWeight, Set<EventInfo> parsedEventDefs, Integer sellEventTriggerThreshold, Integer buyEventTriggerThreshold) {
		
		this.triggeringFinalWeight = finalWeight;
		
		if (triggeringFinalWeight >= buyEventTriggerThreshold)  {
			this.buyTriggeringEvents = parsedEventDefs;			
		} else {
			if (triggeringFinalWeight <= sellEventTriggerThreshold) {
				this.sellTriggeringEvents = parsedEventDefs;
			}
		}
	}

	public Set<EventInfo> getBuyTriggeringEvents() {
		return buyTriggeringEvents;
	}

	public Float getTriggeringFinalWeight() {
		return triggeringFinalWeight;
	}

	public Set<EventInfo> getSellTriggeringEvents() {
		return sellTriggeringEvents;
	}
	//End Auto portfolio stuff

	public Map<EventInfo, SortedMap<Date, double[]>> getCalculationOutput() {
		return calculationOutput;
	}

	public void addCalculationOutput(EventInfo eventInfo, SortedMap<Date, double[]> calculationOutput) {
		if (this.calculationOutput == null) {
			this.calculationOutput = new HashMap<EventInfo, SortedMap<Date,double[]>>();
		}
		this.calculationOutput.put(eventInfo, calculationOutput);
		
	}

}
