/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.StockToDB;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.PortfolioMgr;

/**
 * The Class EventsResources.
 * 
 * @author Guillaume Thoreton
 */
public class EventsResources {
	
	public static final String EVENTSTABLE = "EVENTS";
	public static final String EVENTSCACHETABLE = "EVENTSCACHE";

	private static MyLogger LOGGER = MyLogger.getLogger(EventsResources.class);
	
	private static EventsResources singleton;
	
	private ConcurrentHashMap<String, StockEventsCache> EVENTS_CACHE = new ConcurrentHashMap<String, StockEventsCache>();
	private Boolean isEventCached;
	private Boolean isCachePersistent;
	
	public List<SymbolEvents> sortedList;
	public List<SymbolEvents> midleList;
	public List<SymbolEvents> finList;
	public List<SymbolEvents> debList;
	
	//"EVENTS_PKEY" UNIQUE ( "SYMBOL", "ISIN",  "ANALYSENAME", "DATE", "EVENTDEFID", "EVENTDEFEXTENSION");
	private class EventCacheEntry {
		
		EventKey eventKey;
		EventValue eventValue;
		
		public EventCacheEntry(EventKey eventKey, EventValue eventValue) {
			super();
			
			this.eventKey = eventKey;
			this.eventValue = eventValue;
		}

		public EventKey getEventKey() {
			return eventKey;
		}

		public EventValue getEventValue() {
			return eventValue;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((eventKey.getDate() == null) ? 0 : eventKey.getDate().hashCode());
			result = prime * result + ((eventKey.getEventInfoExtra().toString() == null) ? 0 : eventKey.getEventInfoExtra().toString().hashCode());
			result = prime * result + ((eventValue.getEventDef() == null) ? 0 : eventValue.getEventDef().hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			
			EventCacheEntry other = (EventCacheEntry) obj;
			
			if (!getOuterType().equals(other.getOuterType())) return false;
			
			if (eventKey == null) {
				if (other.eventKey != null) return false;
			} else if (
					other.eventKey == null || 
					eventKey.getDate().compareTo(other.eventKey.getDate()) != 0 || 
					!eventKey.getEventInfoExtra().toString().equals(other.eventKey.getEventInfoExtra().toString())) {
				return false;
			}
			
			if (eventValue == null) {
				if (other.eventValue != null) return false;
			} else if (
					other.eventValue == null || 
					!eventValue.getEventDef().equals(other.eventValue.getEventDef())) {
				return false;
			}
			
			return true;
		}

		private EventsResources getOuterType() {
			return EventsResources.this;
		}

		@Override
		public String toString() {
			return "EventCacheEntry [eventKey=" + eventKey + ", eventValue=" + eventValue + "]";
		}
	}
	
	
	private class EventCacheEntryComparator implements Comparator<EventCacheEntry> {

		public int compare(EventCacheEntry se1, EventCacheEntry se2) {

			EventInfo se1EventInfo = se1.getEventKey().getEventInfo();
			EventInfo se2EventInfo = se2.getEventKey().getEventInfo();

			int cmp = (se1.getEventKey().getDate()).compareTo(se2.getEventKey().getDate());
			if (cmp == 0) {
				cmp = (se1.getEventValue().getEventDef()).compareTo(se2.getEventValue().getEventDef());
				if (cmp != 0 && (se1EventInfo.equals(EventDefinition.INFINITE) || se2EventInfo.equals(EventDefinition.ZERO)) ) cmp = -1;
				if (cmp != 0 && (se1EventInfo.equals(EventDefinition.ZERO) || se2EventInfo.equals(EventDefinition.INFINITE)) ) cmp = +1;
				if (cmp == 0) {
					cmp = (se1.getEventKey().getEventInfoExtra().toString()).compareTo(se2.getEventKey().getEventInfoExtra().toString());
				}
			}
			return cmp;
		}

	}
	
	class EventCacheEntryList extends ConcurrentSkipListSet<EventCacheEntry> {
		
		private static final long serialVersionUID = 2661601346495228458L;

		public EventCacheEntryList(Comparator<? super EventCacheEntry> comparator) {
			super(comparator);
		}
		
	}
	
	private class StockEventsCache  {
		
		ConcurrentHashMap<Stock, SoftReference<EventCacheEntryList>> underLayingSoftMap;

		public StockEventsCache() {
			super();
			underLayingSoftMap = new ConcurrentHashMap<Stock, SoftReference<EventCacheEntryList>>();
		}

		public Set<Stock> keySet() {
			return underLayingSoftMap.keySet();
		}
		

		public void createEventsInStockCache(List<SymbolEvents> events) {

			addEventsInStockSoftCache(events);
		}

		public EventCacheEntryList readEventsInStockCache(Stock stock) {

			SoftReference<EventCacheEntryList> softRef = underLayingSoftMap.get(stock);
			if (softRef == null) {
				return null;
			} else {
				return softRef.get();
			}
		}

		public void deleteEventsInStockCache(Stock stock, Date startDate, Date endDate, List<EventInfo> indicatorsList) {
						
			deleteEventsInStockSoftCache(stock, startDate, endDate, indicatorsList);
		}

		private void deleteEventsInStockSoftCache(Stock stock, Date startDate, Date endDate, List<EventInfo> indicatorsList) {
			
			SortedSet<EventCacheEntry> eventsForStockAndAnalysis = this.readEventsInStockCache(stock);
			if (eventsForStockAndAnalysis != null) {
				SortedSet<EventCacheEntry> dateSubSet = eventsForStockAndAnalysis.subSet(smallestCacheEntry(startDate), bigestCacheEntry(endDate));
				SortedSet<EventCacheEntry> subSetToRemove = new TreeSet<EventCacheEntry>(new EventCacheEntryComparator());
				for (EventCacheEntry eventCacheEntry : dateSubSet) {
					EventInfo eventDef =  eventCacheEntry.getEventValue().getEventDef();
					if (indicatorsList.contains(eventDef)) {
						subSetToRemove.add(eventCacheEntry);
					}
				}
				eventsForStockAndAnalysis.removeAll(subSetToRemove);
			}
		}

		private void addEventsInStockSoftCache(List<SymbolEvents> events) {
			
			for (SymbolEvents symbolEvents : events) {
				EventCacheEntryList sortedEventsForStock = this.readEventsInStockCache(symbolEvents.getStock());
				if (sortedEventsForStock == null) {
					sortedEventsForStock = new EventCacheEntryList(new EventCacheEntryComparator());
					this.createEventsInSoftStockCache(symbolEvents.getStock(), sortedEventsForStock);
				}
				sortedEventsForStock.addAll(buildCacheEntriesFromSymbolEvents(symbolEvents));
			}
		}
		
		private void createEventsInSoftStockCache(Stock stock, EventCacheEntryList cacheEntries) {

			SoftReference<EventCacheEntryList> oldValue = underLayingSoftMap.put(stock, new SoftReference<EventCacheEntryList>(cacheEntries));
			if (oldValue != null) {
				oldValue.clear();
			}
		}
	}


	protected EventsResources() {
		super();
		
		resetSortLists();
		String eventCacheProp = MainPMScmd.getPrefs().get("event.cache","true");
		LOGGER.info("Event cache is set to "+eventCacheProp);
		
		isEventCached = new Boolean(eventCacheProp);
		isCachePersistent = false;
		
		singleton = this;
		
	}

	public void resetSortLists() {
		sortedList = new ArrayList<SymbolEvents>();
		midleList = new ArrayList<SymbolEvents>();
		finList = new ArrayList<SymbolEvents>();
		debList = new ArrayList<SymbolEvents>();
	}
	
	public static EventsResources getInstance() {
		if (singleton == null) {
			singleton = new EventsResources();
		}
		return singleton;

	}

	public void crudCreateEvents(SymbolEvents symbolEvents, Boolean persist, String analysisName) {
		
		List<SymbolEvents> eventList = new ArrayList<SymbolEvents>();
		eventList.add(symbolEvents);
		crudCreateEvents(eventList, persist, analysisName, false, null);
		
	}
	
	private void addEventsToCache(List<SymbolEvents> events, String eventListName) {

		if (isEventCached) {
			
			synchronized (this) {
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					eventsForName = new StockEventsCache();
					EVENTS_CACHE.put(eventListName, eventsForName);
				}
				eventsForName.createEventsInStockCache(events);
			}
		}
		
	}
	
	private void addEventsToSoftCache(List<SymbolEvents> events, String eventListName) {

		if (isEventCached) {

			synchronized (this) {
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					eventsForName = new StockEventsCache();
					EVENTS_CACHE.put(eventListName, eventsForName);
				}
				eventsForName.addEventsInStockSoftCache(events);
			}
		}

	}

	
	private Collection<EventCacheEntry> buildCacheEntriesFromSymbolEvents(SymbolEvents symbolEvents) {
		
		List<EventCacheEntry> ret = new ArrayList<EventsResources.EventCacheEntry>();
		for (EventKey eventKey : symbolEvents.getDataResultMap().keySet()) {
			ret.add(new EventCacheEntry(eventKey, symbolEvents.getDataResultMap().get(eventKey)));
		}
		return ret;
	}
	
	private SymbolEvents buildSymbolEventsFromCacheEntries(Stock stock, SortedSet<EventCacheEntry> eventSubSet, Set<EventInfo> eventDefinitions) {
		SymbolEvents subSymbolEvents = new SymbolEvents(stock, EventState.STATE_TERMINATED);
		if (eventSubSet.size() > 0) {
			for (EventCacheEntry eventCacheEntry : eventSubSet) {
				if (eventDefinitions == null || eventDefinitions.contains(eventCacheEntry.getEventValue().getEventDef())) {
					subSymbolEvents.addEventResultElement(eventCacheEntry.getEventKey(), eventCacheEntry.getEventValue(), eventCacheEntry.getEventValue().getEventDef().getEventDefinitionRef());
				}
			}
		}
		return subSymbolEvents;
	}

	private void updateEventsCache(Date startDate, Date endDate,Set<EventInfo> eventDefinitions, Set<String> eventListNames, Boolean isFromHardCache) {
		
		String eventsTableName = (isFromHardCache)?EVENTSCACHETABLE:EVENTSTABLE;
		for (String eventListName : eventListNames) {		
			List<SymbolEvents> eventsForName = DataSource.getInstance().loadEventsByDate(eventsTableName, startDate, endDate, eventDefinitions, eventListName);
			this.addEventsToSoftCache(eventsForName, eventListName);
		}
		
	}

	private void updateEventsCache(Stock stock, Date startDate, Date endDate, Set<EventInfo> eventDefinitions, Set<String> eventListNames, Boolean isFromHardCache) {

		String eventsTableName = (isFromHardCache)?EVENTSCACHETABLE:EVENTSTABLE;
		for (String eventListName : eventListNames) {
			SymbolEvents eventsForName = DataSource.getInstance().loadEventsByDate(eventsTableName, stock, startDate, endDate, eventDefinitions, eventListName);
			this.addEventsToSoftCache(Arrays.asList(new SymbolEvents[]{eventsForName}), eventListName);
		}
	}
	
	public SymbolEvents crudReadEventsForStock(Stock stock, Date startDate, Date endDate, Boolean isPersisted, Set<EventInfo> eventDefinitions, String... eventListNames) {
		
		if (!isEventCached && !isPersisted) {
			String message = "Inconsistency : Events are neither persisted or cached.";
			LOGGER.error(message, new Exception());
			throw new InvalidParameterException(message);
		}
		
		Set<String> eventListNamesSet = disctinctNames(eventListNames);
		
		SymbolEvents retVal =  new SymbolEvents(stock, EventState.STATE_TERMINATED);
		if (isEventCached) {
			
			if (isPersisted) {
				this.updateEventsCache(stock, startDate, endDate, eventDefinitions, eventListNamesSet, false);
			}
			
			if (isCachePersistent) {
				this.updateEventsCache(stock, startDate, endDate, eventDefinitions, eventListNamesSet, true);
			}
			
			for (String eventListName : eventListNamesSet) {
				
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					LOGGER.info("No events cached for " + eventListName+ " and "+stock + " from "+startDate+" to "+endDate);
				} else {
					try {
						retVal = subSoftCachedEvents(stock, startDate, endDate, eventsForName, eventListName, eventDefinitions);
					} catch (Exception e) {
						LOGGER.error("Stock "+stock+" event retrieval pb : "+e, e);
					}
				}
			}
			
		} else {
			
			retVal = DataSource.getInstance().loadEventsByDate(EVENTSTABLE, stock, startDate, endDate, eventDefinitions, eventListNamesSet.toArray(new String[eventListNamesSet.size()]));
		}
		
		return retVal;
	}

	
	public List<SymbolEvents> crudReadEvents(Date startDate, Date endDate, Boolean isPersisted, Set<EventInfo> eventDefinitions, String... eventListNames) {
		
		if (!isEventCached && !isPersisted) {
			String message = "Inconsistency : Events are neither persisted or cached.";
			LOGGER.error(message, new Exception());
			throw new InvalidParameterException(message);
		}
		
		List<SymbolEvents> retVal = new ArrayList<SymbolEvents>();
		Set<String> eventListNamesSet = disctinctNames(eventListNames);
		
		if (isEventCached) {
			
			if (isPersisted) {
				this.updateEventsCache(startDate, endDate, eventDefinitions, eventListNamesSet, false);
			}
			
			if (isCachePersistent) {
				this.updateEventsCache(startDate, endDate, eventDefinitions, eventListNamesSet, true);
			}
			
			for (String eventListName : eventListNamesSet) {
				
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					LOGGER.info("No events cached for " + eventListName);
				} else {
					for (Stock stock : eventsForName.keySet()) {
						SymbolEvents subCachedEvents;
						try {
							subCachedEvents = subSoftCachedEvents(stock, startDate, endDate, eventsForName, eventListName, eventDefinitions);
							if (subCachedEvents != null) {
								if (retVal.contains(subCachedEvents)) {
									retVal.get(retVal.indexOf(subCachedEvents)).addEventResultElement(subCachedEvents);
								} else {
									retVal.add(subCachedEvents);
								}
							}
						} catch (Exception e) {
							LOGGER.error(e,e);
						}
					}
				}
			}
			
		} else {
			retVal = DataSource.getInstance().loadEventsByDate(EVENTSTABLE, startDate, endDate, eventDefinitions, eventListNamesSet.toArray(new String[eventListNamesSet.size()]));
		}
		
		return retVal;
	}

	/**
	 * @param eventListNames
	 * @return
	 */
	private Set<String> disctinctNames(String... eventListNames) {
		Set<String> eventListNamesSet = new HashSet<String>();
		eventListNamesSet.addAll(Arrays.asList(eventListNames));
		return eventListNamesSet;
	}
	
	private SymbolEvents subSoftCachedEvents(Stock stock, Date startDate, Date endDate, StockEventsCache eventsForName, String eventListName, Set<EventInfo> eventDefinitions) {

		SymbolEvents subSymbolEvents = new SymbolEvents(stock, EventState.STATE_TERMINATED);
		EventCacheEntry startInfEvent = smallestCacheEntry(startDate);
		EventCacheEntry endSupEvent = bigestCacheEntry(endDate);
		
		SortedSet<EventCacheEntry> eventsForStockAndName = eventsForName.readEventsInStockCache(stock);
		if (eventsForStockAndName != null && !eventsForStockAndName.isEmpty()) {
			try {
				SortedSet<EventCacheEntry> eventSubSet = eventsForStockAndName.subSet(startInfEvent, endSupEvent);
				subSymbolEvents = buildSymbolEventsFromCacheEntries(stock, eventSubSet, eventDefinitions);
			} catch (IllegalArgumentException e) {
				LOGGER.warn("No events in cache for "+stock+" and "+eventListName+" from "+startDate+" to "+endDate+". Available first, last events : "+eventsForStockAndName.first()+" to "+eventsForStockAndName.last(), e);
			} 
		} 
		return subSymbolEvents;
	}
	
	private EventCacheEntry bigestCacheEntry(final Date date) {

		return new EventCacheEntry(new EventKey() {

			private static final long serialVersionUID = -5684675854556973652L;

			public EventInfo getEventInfo() {
				return EventDefinition.ZERO;
			}

			public String getEventInfoExtra() {
				return "z";
			}

			public EventType getEventType() {
				return EventType.NONE;
			}

			public Date getDate() {
				return DateFactory.midnithDate(date);
			}

		}, new EventValue(DateFactory.midnithDate(date), EventDefinition.ZERO, EventType.NONE, ""));
	}

	private EventCacheEntry smallestCacheEntry(final Date date) {
		
		return new EventCacheEntry(new EventKey() {

			private static final long serialVersionUID = -5684675854556973652L;

			public EventInfo getEventInfo() {
				return EventDefinition.INFINITE;
			}
			
			
			public String getEventInfoExtra() {
				return "A";
			}
			
			
			public EventType getEventType() {
				return EventType.NONE;
			}
			
			
			public Date getDate() {
				return DateFactory.midnithDate(date);
			}
			
		}, 
		new EventValue(DateFactory.midnithDate(date), EventDefinition.INFINITE, EventType.NONE,""));
	}
	

	public void crudCreateEvents(List<SymbolEvents> events, Boolean isDataPersisted, String eventListName, boolean preLockRequiered, String tableToLock) {

		if (isEventCached) {
			if (isCachePersistent && !isDataPersisted) {
				persitEvents(events, eventListName, EVENTSCACHETABLE, false, "");
			}

			addEventsToCache(events, eventListName);
		}
	
		if (isDataPersisted || !isEventCached) {
			
			LOGGER.info("Storing Events in db cached is "+isEventCached+", persist is "+isDataPersisted+", other params "+eventListName);
			persitEvents(events, eventListName, EVENTSTABLE, preLockRequiered, tableToLock);

		} 
	}

	private void persitEvents(List<SymbolEvents> events, String eventListName, String eventTableName, boolean preLockRequiered, String tableToLock) {
		
		ArrayList<Query> qInsert = new ArrayList<Query>();
		ArrayList<Validatable> lqUpdate = new ArrayList<Validatable>();
		buildUpdateValidatableList(events, qInsert, lqUpdate);

		List<Validatable> lqRemainingInsert = new ArrayList<Validatable>();
		int[] updated = new int[0];
		try {

			updated = DataSource.getInstance().executeBlockWithTimeStamp(lqUpdate, DataSource.EVENTS.getUPDATE(eventTableName), preLockRequiered, tableToLock);
			
			//Insert the raws not updated
			for (int i=0;i<updated.length;i++) {
				final Query query = qInsert.get(i);

				if (updated[i] == 0) { //Raw not updated //XXX is 0 returned if the line is present but the update values are the same as the existing line?
					
					lqRemainingInsert.add(new StockToDB() {

						private static final long serialVersionUID = -1418476918112988888L;

						@Override
						public Query toDataBase() {
							return query;
						}

						@Override
						public String toString() {
							return query.toString();
						}
					});
					
				} else if (updated[i] != 1) {
					LOGGER.error(
							"Strange return from events update detected :\n" +
							"Update request params :\n"+
								DataSource.printHugeCollection(lqUpdate)+"\n" +
							"Insert request params :\n"+
								DataSource.printHugeCollection(lqRemainingInsert)+"\n" +
							"Update return was " +
									Arrays.toString(updated)
							);
				}
			}

			try {
			
				DataSource.getInstance().executeBlockWithTimeStamp(lqRemainingInsert, DataSource.EVENTS.getINSERT(eventTableName), preLockRequiered, tableToLock);
				
			} catch (Exception e) {
				LOGGER.error(
						"Pb inserting after updating events :\n" +
						"Update request params :\n"+
							DataSource.printHugeCollection(lqUpdate)+"\n" +
						"Insert request params :\n"+
							DataSource.printHugeCollection(lqRemainingInsert)+"\n" +
						"Update return was " + 
							Arrays.toString(updated), e);
			}

		} catch (SQLException e) {
			LOGGER.error("Pb update/insert events :\n" +
						"Update request params :\n"+
							DataSource.printHugeCollection(lqUpdate)+"\n" +
						"Insert request params :\n"+
							DataSource.printHugeCollection(lqRemainingInsert)+"\n" +
						"Update return was " +
							Arrays.toString(updated)
						,e);
			LOGGER.debug(e.getCause());
			LOGGER.debug(e.getNextException());
		}
	}

	/**
	 * @param events
	 * @param lqinsert
	 * @param lqupdate
	 */
	private void buildUpdateValidatableList(Collection<SymbolEvents> events, ArrayList<Query> lqinsert, ArrayList<Validatable> lqupdate) {

		Iterator<SymbolEvents> sortedListIt = events.iterator();
		while (sortedListIt.hasNext()) {
			final SymbolEvents se = sortedListIt.next();

			Map<EventKey, EventValue> dataResultList = se.getDataResultMap();
			for (final EventKey eventKey : dataResultList.keySet()) {

				final EventValue eventValue = dataResultList.get(eventKey);

				//update
				lqupdate.add(new StockToDB() {

					private static final long serialVersionUID = -6070819149980113006L;
					
					@Override
					public Query toDataBase() {
						//set
						Query qupdate = new Query();
						qupdate.addValue(new Integer(se.getEventsState().ordinal()));
						qupdate.addValue(eventValue.getEventDef().getEventDefId());
						qupdate.addValue(new String(""+eventValue.getEventType().getEventTypeChar().toString()));
						qupdate.addValue(eventValue.getMessage());

						//where
						qupdate.addValue(se.getSymbol());
						qupdate.addValue(se.getIsin());
						qupdate.addValue(eventValue.getDate());
						qupdate.addValue(eventValue.getEventDef().getEventDefinitionRef());
						qupdate.addValue(eventKey.getEventInfoExtra().toString());
						qupdate.addValue(eventValue.getEventListName());

						return qupdate;
					}

					@Override
					public String toString() {
						return "["+se.getSymbol().toUpperCase()+","+se.getIsin()+","+eventValue.getDate()+","+eventValue.getEventDefId()+","+eventKey.getEventInfoExtra().toString()+","+eventValue.getEventListName()+"]";
					}
				});

				//insertion
				addInsertQuery(lqinsert, se, eventValue, eventKey);
			}
		}
	}

	/**
	 * @param lqinsert
	 * @param se
	 * @param eventValue
	 */
	private void addInsertQuery(ArrayList<Query> lqinsert, SymbolEvents se, EventValue eventValue, EventKey eventKey) {
		
		Query qinsert = new Query();
		qinsert.addValue(se.getSymbol());
		qinsert.addValue(se.getIsin());
		qinsert.addValue(new Integer(se.getEventsState().ordinal()));
		qinsert.addValue(eventValue.getDate());
		qinsert.addValue(eventValue.getEventDefId());
		qinsert.addValue(eventValue.getEventDef().getEventDefinitionRef());
		qinsert.addValue(eventKey.getEventInfoExtra().toString());
		qinsert.addValue(new String(""+eventValue.getEventType().getEventTypeChar()));
		qinsert.addValue(eventValue.getMessage());
		qinsert.addValue(eventValue.getEventListName());
		
		lqinsert.add(qinsert);
	}
	
	/**
	 * Load events by criteria and date.
	 *
	 * @param date the date
	 * @param inf the inf
	 * @param sup the sup
	 * @param pr the pr
	 * @param indicators 
	 * @param eventListNames 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @author Guillaume Thoreton
	 */
	public void updateEventsTabsByCriteriaAndDate(Date date, Integer inf, Integer sup, PonderationRule pr, Set<EventInfo> indicators, String eventListNames) throws InvalidAlgorithmParameterException {
		
		List<SymbolEvents> all = SymbolEvents.sortList(EventsResources.getInstance().crudReadEvents(date, EventSignalConfig.getNewDate(), true, indicators, eventListNames), pr);
		int indexSup = 0;
		int indexInf = all.size() - 1;
		
		if (sup < inf) {
			LOGGER.error("Wrong range : Sup =" + sup + " , Inf =" + inf);
			throw new InvalidAlgorithmParameterException("Wrong range : Sup =" + sup + " , Inf =" + inf);
		}
		
		if (all.size() > 0) {

			//fin
			while (indexInf >= 0 && all.get(indexInf).getWeight(pr) <= inf) indexInf--;
			if (indexInf < all.size())
				this.setFinList(all.subList(indexInf + 1, all.size()));
			else
				this.setFinList(new ArrayList<SymbolEvents>());

			//deb
			while (indexSup < all.size() && all.get(indexSup).getWeight(pr) >= sup) indexSup++;
			if (indexSup > 0)
				this.setDebList(all.subList(0, indexSup));
			else
				this.setDebList(new ArrayList<SymbolEvents>());

			//middle
			if (indexInf + 1 > indexSup) {
				this.setMidleList(all.subList(indexSup, indexInf + 1));
			} else {
				this.setMidleList(new ArrayList<SymbolEvents>());
			}
			
			//all
			this.setSortedList(all);

		} else {
			resetSortLists();
		}
	}
	
	public void filterOutNonMonitoredEvents() {
		
		Collection<Stock> portfolioStocks = PortfolioMgr.getInstance().getUserMonitoredStocks();
		dispatch(portfolioStocks);
		
	}
	
	public void filterOutCurrentMarketEvents(Object... shareLists) {
		
		Collection<Stock> selectedSharelists = new ArrayList<Stock>();
		for (Object shareList : shareLists) {
			Set<Stock> stocksList = DataSource.getInstance().loadStocksList(((ShareListInfo) shareList).info());
			selectedSharelists.addAll(stocksList);
		}
		
		dispatch(selectedSharelists);
		
	}
	
	public void filterOutNonPortofolioEvents() {
		
		Collection<Stock> portfolioStocks = PortfolioMgr.getInstance().getPortfolioStocks();
		dispatch(portfolioStocks);
		
	}
	
	public void filterOutNone() {
		
		Collection<Stock> portfolioStocks = DataSource.getInstance().getShareDAO().loadAllStocks();
		dispatch(portfolioStocks);
		
	}
	

	private void dispatch(Collection<Stock> stocksToKeep) {
		
		List<SymbolEvents> dl = new ArrayList<SymbolEvents>(); 
		List<SymbolEvents> fl = new ArrayList<SymbolEvents>();
		List<SymbolEvents> ml = new ArrayList<SymbolEvents>();
		List<SymbolEvents> sl = new ArrayList<SymbolEvents>();
		
		for (Object o: this.getDebList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (stocksToKeep.contains(se.getStock())) dl.add(se);
		}
		this.setDebList(dl);
		
		for (Object o: this.getFinList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (stocksToKeep.contains(se.getStock())) fl.add(se);
		}
		this.setFinList(fl);
		
		for (Object o: this.getMidleList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (stocksToKeep.contains(se.getStock())) ml.add(se);
		}
		this.setMidleList(ml);
		
		for (Object o: this.getSortedList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (stocksToKeep.contains(se.getStock())) sl.add(se);
		}
		this.setSortedList(sl);
	}
	
	/**
	 * Export events.
	 * 
	 * @param events the events
	 * @param file the file
	 * @param pr the pr
	 * 
	 * @author Guillaume Thoreton
	 */
	public void exportEvents(Collection<? extends Object> events, String file, PonderationRule pr) {

		try {
			File fichier = new File(file);
			FileWriter fw = new FileWriter(fichier);
			fw.write("Symbol;Result Accuracy;Date;Event Definition;Event Type;Weigth\n");
			Iterator<? extends Object> sortedListIt = events.iterator();
			while (sortedListIt.hasNext()) {
				StringBuffer oneline = new StringBuffer();
				SymbolEvents se = (SymbolEvents) sortedListIt.next();
				oneline.append(se.toExport(pr));
				oneline.append("\n");
				fw.write(oneline.toString());
				fw.flush();
			}
			fw.close();
		} catch (IOException e) {
			LOGGER.error("",e);
		}
	}
	
	

	/**
	 * Gets the deb list.
	 * 
	 * @return the deb list
	 */
	public List<SymbolEvents> getDebList() {
		return debList;
	}



	/**
	 * Sets the deb list.
	 * 
	 * @param debList the new deb list
	 */
	public void setDebList(List<SymbolEvents> debList) {
		this.debList = debList;
	}



	/**
	 * Gets the fin list.
	 * 
	 * @return the fin list
	 */
	public List<SymbolEvents> getFinList() {
		return finList;
	}



	/**
	 * Sets the fin list.
	 * 
	 * @param finList the new fin list
	 */
	public void setFinList(List<SymbolEvents> finList) {
		this.finList = finList;
	}



	/**
	 * Gets the midle list.
	 * 
	 * @return the midle list
	 */
	public List<SymbolEvents> getMidleList() {
		return midleList;
	}



	/**
	 * Sets the midle list.
	 * 
	 * @param midleList the new midle list
	 */
	public void setMidleList(List<SymbolEvents> midleList) {
		this.midleList = midleList;
	}



	/**
	 * Gets the sorted list.
	 * 
	 * @return the sorted list
	 */
	public List<SymbolEvents> getSortedList() {
		return sortedList;
	}


	public void setSortedList(List<SymbolEvents> sortedList) {
		this.sortedList = sortedList;
	}
	
	public void crudDeleteEventsForStock(Stock stock, String analysisName, Date datedeb, Date datefin, Boolean isDataPersisted, EventInfo... indicators) {
		
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				StockEventsCache eventsForAnalysis = EVENTS_CACHE.get(analysisName);
				if (eventsForAnalysis != null) {
					removeEventsFromEventsSoftCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
				}
			}
			if (isCachePersistent && !isDataPersisted) {
				DataSource.getInstance().cleanEventsForAnalysisNameAndStock(EVENTSCACHETABLE, stock, analysisName, datedeb, datefin, indicators);
			}
		}
		
		//DB
		if (isDataPersisted || !isEventCached) {
			LOGGER.info("Cleaning Events in db cached is "+isEventCached+", persist is "+isDataPersisted+" other params "+stock+", "+analysisName+", "+datedeb+", "+datefin+", "+EventDefinition.getEventDefArrayAsString(" ",indicators));
			DataSource.getInstance().cleanEventsForAnalysisNameAndStock(EVENTSTABLE, stock, analysisName, datedeb, datefin, indicators);
		}
		
	}


	public void crudDeleteEventsForIndicators(String analysisName, Date datedeb, Date datefin, Boolean isDataPersisted, EventInfo... indicators) {
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				StockEventsCache eventsForAnalysis = EVENTS_CACHE.get(analysisName);
				if (eventsForAnalysis != null) {
					for (Stock stock : eventsForAnalysis.keySet()) {
						removeEventsFromEventsSoftCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
					}
				}
			}
			if (isCachePersistent && !isDataPersisted) {
				DataSource.getInstance().cleanEventsForIndicators(EVENTSCACHETABLE, analysisName, datedeb, datefin, indicators);
			}
		}
		
		//DB
		if (isDataPersisted || !isEventCached) {
			LOGGER.info("Cleaning Events in db cached is "+isEventCached+", persist is "+isDataPersisted+" other params "+analysisName+", "+datedeb+", "+datefin+", "+EventDefinition.getEventDefArrayAsString(" ",indicators));
			DataSource.getInstance().cleanEventsForIndicators(EVENTSTABLE, analysisName, datedeb, datefin, indicators);
		}
	}
	
	public void crudDeleteEventsForAnalysisName(String analysisName, Boolean isDataPersisted) {
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				EVENTS_CACHE.remove(analysisName);
			}
			
			if (isCachePersistent && !isDataPersisted) {
				DataSource.getInstance().cleanEventsForAnalysisName(EVENTSCACHETABLE, analysisName);
			}
		}
		
		//DB
		if (isDataPersisted || !isEventCached) {
			LOGGER.info("Cleaning Events in db cached is "+isEventCached+", persist is "+isDataPersisted);
			DataSource.getInstance().cleanEventsForAnalysisName(EVENTSTABLE, analysisName);
		}
	}
	
	public void cleanPersistedEventsCache() throws SQLException {
		
		Query drop = new Query("drop table IF EXISTS "+EVENTSCACHETABLE);
		DataSource.getInstance().executeUpdate(drop, 600);
		
		Query create = new Query(
				"CREATE TABLE `"+EVENTSCACHETABLE+"` ("+
				  "`DATE` datetime NOT NULL,"+
				  "`SYMBOL` varchar(20) NOT NULL,"+
				  "`ISIN` varchar(20) NOT NULL,"+
				  "`EVENTDEF` varchar(100) DEFAULT NULL,"+
				  "`EVENTTYPE` varchar(1) NOT NULL,"+
				  "`ACCURACY` smallint(6) DEFAULT NULL,"+
				  "`EVENTDEFID` smallint(6) NOT NULL,"+
				  "`EVENTDEFEXTENSION` varchar(100) NOT NULL DEFAULT '',"+
				  "`ANALYSENAME` varchar(256) NOT NULL,"+
				  "`MESSAGE` mediumtext,"+
				  "PRIMARY KEY (`SYMBOL`, `ISIN`, `ANALYSENAME`, `DATE`, `EVENTDEF`, `EVENTDEFEXTENSION`),"+
				  "KEY `EVENTS_ANAME_DATE` (`ANALYSENAME`,`DATE`),"+
				  "KEY `EVENTS_STOCK_ANAME_DATE_DEF` (`SYMBOL`,`ISIN`,`ANALYSENAME`,`DATE`,`EVENTDEF`)"+
				")");
//		ALTER TABLE EVENTS  ADD PRIMARY KEY ( `ANALYSENAME`, `SYMBOL`, `ISIN`, `DATE`, `EVENTDEF`, `EVENTDEFEXTENSION`);
//		ALTER TABLE EVENTS  ADD INDEX `EVENTS_ANAME` (`ANALYSENAME`);
//		ALTER TABLE EVENTS  ADD INDEX `EVENTS_ANAME_DATE` (`ANALYSENAME`,`DATE`);
//		ALTER TABLE EVENTS  ADD INDEX `EVENTS_STOCK_ANAME_DATE_DEF` (`ANALYSENAME`,`SYMBOL`,`ISIN`,`DATE`,`EVENTDEF`);
//		ALTER TABLE EVENTS  ADD INDEX `EVENTS_STOCK_ANAME_DATE_DEF_TYPE` (`ANALYSENAME`,`SYMBOL`,`ISIN`,`DATE`,`EVENTDEF`,`EVENTTYPE`);
		DataSource.getInstance().executeUpdate(create, 600);
		
	}
	
	/**
	 * @param stock
	 * @param configName
	 * @param startDate
	 * @param endDate
	 * @param persistChache 
	 */
	private void removeEventsFromEventsSoftCacheFor(Stock stock, StockEventsCache eventsForAnalysis, Date startDate, Date endDate, EventInfo... indicators) {
		
		List<EventInfo> indicatorsList = Arrays.asList(indicators);
		if (eventsForAnalysis != null) {
			eventsForAnalysis.deleteEventsInStockCache(stock, startDate, endDate, indicatorsList);
		}
	}


	public void setIsCachePersistent(Boolean isCachePersistent) {
		this.isCachePersistent = isCachePersistent;
	}


}
