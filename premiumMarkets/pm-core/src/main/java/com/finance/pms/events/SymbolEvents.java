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
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.Signal;


/**
 * The Class SymbolEvents.
 * 
 * @author Guillaume Thoreton
 */
public class SymbolEvents implements Serializable {

	protected static MyLogger LOGGER = MyLogger.getLogger(SymbolEvents.class);

	private static final long serialVersionUID = 4488655947887964613L;

	private SymbolEventsWeightData weightData;
	
	private Stock stock;
	private ConcurrentSkipListMap<EventKey, EventValue> dataResultMap;
	private Set<String> eventDefList;
	private EventState eventsState;

	private Map<EventInfo, SortedMap<Date, double[]>> calculationOutputs;

	public SymbolEvents(Stock stock) {
		this.weightData = null;
		
		this.stock = stock;
		this.dataResultMap = new ConcurrentSkipListMap<EventKey, EventValue>();
		this.eventDefList = new HashSet<String>();
		this.eventsState = EventState.STATE_TERMINATED;
		
		this.calculationOutputs = new HashMap<>();
	}

	public SymbolEvents(Stock stock, ConcurrentSkipListMap<EventKey, EventValue> dataResultList, Collection<?> eventDefList, EventState state) {
		this.weightData = null;

		this.stock = stock;
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
		
		this.calculationOutputs = new HashMap<>();
	}

	public SymbolEvents(Stock stock, EventState eState) {
		this.weightData = null;
		
		this.stock = stock;
		this.dataResultMap = new ConcurrentSkipListMap<EventKey, EventValue>();
		this.eventDefList = new HashSet<String>();
		this.eventsState= eState;
		
		this.calculationOutputs = new HashMap<>();
	}

	public SymbolEvents(EventMessageObject eventMessageObject) throws NoSuchFieldException {
		this.weightData = null;

		ConcurrentSkipListMap<EventKey, EventValue> map= new ConcurrentSkipListMap<EventKey, EventValue>();
		EventValue eventValue = eventMessageObject.getEventValue();
		EventKey eventKey = eventMessageObject.getEventKey();
		map.put(eventKey, eventValue);

		this.stock = eventMessageObject.getStock();
		this.dataResultMap = map;
		this.eventDefList = new HashSet<String>();
		this.eventDefList.add(eventValue.getEventDef().getEventDefinitionRef());
		this.eventsState = EventState.STATE_TERMINATED;
		
		this.calculationOutputs = new HashMap<>();

	}

	/**
	 * Natural sorting in accordance with the EventKey compareTo.
	 * @return
	 */
	public SortedMap<EventKey, EventValue> getDataResultMap() {
		return dataResultMap;
	}

	/**
	 * Natural sorting in accordance with the EventKey compareTo.
	 * @return
	 */
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
	 * @return the sorted data result list in date ascending order.
	 */
	public ArrayList<EventValue> getSortedDataResultList() {
		return new ArrayList<EventValue>(dataResultMap.values());
	}

	public ArrayList<EventValue> getSortedDataResultList(Comparator<EventValue> comparator) {

		ArrayList<EventValue> sortedList = new ArrayList<EventValue>(dataResultMap.values());
		Collections.sort(sortedList, comparator);
		return sortedList;
	}

	public String getSymbol() {
		return this.stock.getSymbol();
	}

	public String getIsin() {
		return this.stock.getIsin();
	}

	public Set<String> getEventDefList() {
		return eventDefList;
	}

	public float getWeight(PonderationRule ponderationRule) {
		if (weightData == null || !weightData.isSamePonderation(ponderationRule)) {
			//LOGGER.info("Different ponderation recalculation needed.");
			this.weightData = new SymbolEventsWeightData(ponderationRule);
			return ponderationRule.finalWeight(this);
		} else {
			//LOGGER.info("Same ponderation no recalculation needed.");
			return weightData.getFinalWeight();
		}
	}

	@Override
	public String toString() {
		StringBuffer retour = new StringBuffer();
		retour.append(stock.getSymbol());
		retour.append(";");
		retour.append(stock.getName());
		retour.append(";");
		retour.append(" triggering weight: " + getTriggeringFinalWeight());
		retour.append(";");
		retour.append(" buy triggering events: " + ((getBuyTriggeringEvents() != null)?getBuyTriggeringEvents().stream().map(e -> e.getEventDefinitionRef()).reduce((r, e) -> r + " " + e).orElse("none"):"none"));
		retour.append(";");
		retour.append(" sell triggering events: " + ((getSellTriggeringEvents() != null)?getSellTriggeringEvents().stream().map(e -> e.getEventDefinitionRef()).reduce((r, e) -> r + " " + e).orElse("none"):"none"));
		retour.append(";[");
		Collection<EventValue> drl = this.getSortedDataResultList();
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
		if (drl.size() > 0) {
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
		Collection<EventValue> drl = this.getSortedDataResultList();
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
		return
		"Events\t\t:\n\t\t" + getSortedDataResultList().stream().map(e -> e.toString()).reduce((r, e) -> r + "\n\t\t" + e).orElse("none")
		+ "\n Buy Events\t: " + ((getBuyTriggeringEvents() != null)? getBuyTriggeringEvents().stream().map(e -> e.getEventDefinitionRef()).reduce((r, e) -> r + " " + e).orElse("none") : "none")
		+ "\n Sell Events\t: " + ((getSellTriggeringEvents() != null)? getSellTriggeringEvents().stream().map(e -> e.getEventDefinitionRef()).reduce((r, e) -> r + " " + e).orElse("none") : "none")
		+ "\n Weight\t\t: " + getTriggeringFinalWeight();
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
		this.weightData = null;
		this.eventDefList.add(eventDefinition);
		this.dataResultMap.put(eventKey, eventValue);
	}


	public void addEventResultElement(SortedMap<EventKey,EventValue> evl, Collection<?> edefl) {
		this.weightData = null;
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
			String dataResult = "Current data result: \n";
			for (EventKey key : this.dataResultMap.keySet()) {
				dataResult = dataResult + key+" ; "+this.dataResultMap.get(key) + "\n";
			}
			LOGGER.error(dataResult);
			String addedEvents = "Added events: \n";
			for (EventKey key : evl.keySet()) {
				addedEvents = addedEvents +key+" ; "+evl.get(key) + "\n";
			}
			LOGGER.error(addedEvents);
			throw e;
		}
	}

	public void addEventResultElement(SortedMap<EventKey,EventValue> evl, EventInfo eventDefinition) {
		this.weightData = null;
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

	//Weight stuff
	public Date getLatestRelevantEventDate() {
		try {
			return getWeightData().getLatestRelevantEventDate();
		} catch (Exception e) {
			//LOGGER.warn("No weigth was calculated :" + e);
			return DateFactory.dateAtZero();
		}
	}

	public Set<EventInfo> getBuyTriggeringEvents() {
		try {
			return getWeightData().getBuyTriggeringEvents();
		} catch (Exception e) {
			//LOGGER.warn("No weigth was calculated :" + e);
			return new HashSet<>();
		}
	}

	public Float getTriggeringFinalWeight() {
		try {
			return getWeightData().getFinalWeight();
		} catch (Exception e) {
			//LOGGER.warn("No weigth was calculated :" + e);
			return 0f;
		}
	}

	public Set<EventInfo> getSellTriggeringEvents() {
		try {
			return getWeightData().getSellTriggeringEvents();
		} catch (Exception e) {
			//LOGGER.warn("No weigth was calculated :" + e);
			return new HashSet<>();
		}
	}
	
	private SymbolEventsWeightData getWeightData() {
		if (weightData == null) throw new UnsupportedOperationException("Weight not initialised for this " + this.getSymbol() + " and " + this.getEventDefList());
		return weightData;
	}

	public void setSignal(Signal initSignal) {
		getWeightData().setSignal(initSignal);
	}

	public Signal getSignal() {
		return getWeightData().getSignal();
	}
	//End Weight stuff

	//Calculated output stuff //TODO reset when this changes.
	public Map<EventInfo, SortedMap<Date, double[]>> getCalculationOutputs() {
		return calculationOutputs;
	}

	public void addCalculationOutput(EventInfo eventInfo, SortedMap<Date, double[]> calculationOutput) {
		this.calculationOutputs.put(eventInfo, calculationOutput);

	}

	public void addAllCalculationOutput(Map<EventInfo, SortedMap<Date,double[]>> calculationOutputs) {
		this.calculationOutputs.putAll(calculationOutputs);

	}
	//End calculated output stuff

}
