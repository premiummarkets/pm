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
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.StockToDB;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.SharesList;


// TODO: Auto-generated Javadoc
/**
 * The Class EventsResources.
 * 
 * @author Guillaume Thoreton
 */
public class EventsResources {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(EventsResources.class);
	
	private static EventsResources singleton;
	
	private ConcurrentHashMap<String, StockEventsCache> EVENTS_CACHE = new ConcurrentHashMap<String, StockEventsCache>();
	private Boolean isEventCached;
	
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
			result = prime * result + ((eventKey.getEventDefExtra() == null) ? 0 : eventKey.getEventDefExtra().hashCode());
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
					!eventKey.getDate().equals(other.eventKey.getDate()) || 
					!eventKey.getEventDefExtra().equals(other.eventKey.getEventDefExtra())) {
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
	}
	
	
	private class EventCacheEntryComparator implements Comparator<EventCacheEntry> {

		@SuppressWarnings("unchecked")
		public int compare(EventCacheEntry o1, EventCacheEntry o2) {
			EventCacheEntry se1 = o1;
			EventCacheEntry se2 = o2;
			int retour = 0;

			int date = (se1.getEventKey().getDate()).compareTo(se2.getEventKey().getDate());
			if (date != 0) {
				retour = date;
			} else {
				int eventdef = (se1.getEventValue().getEventDef()).compareTo(se2.getEventValue().getEventDef());
				if (eventdef != 0) {
					retour = eventdef;
				} else {
					int eventExtraType = (se1.getEventKey().getEventDefExtra()).compareTo(se2.getEventKey().getEventDefExtra());
					retour = eventExtraType;
				}
			}
			return retour;
		}

	}

	
	/**
	 * Instantiates a new events resources.
	 * 
	 * @author Guillaume Thoreton
	 */
	protected EventsResources() {
		super();
		sortedList = new ArrayList<SymbolEvents>();
		midleList = new ArrayList<SymbolEvents>();
		finList = new ArrayList<SymbolEvents>();
		debList = new ArrayList<SymbolEvents>();
		isEventCached = new Boolean(MainPMScmd.getPrefs().get("event.cache","true"));
		singleton = this;

	}
	
	/**
	 * Gets the single instance of EventsResources.
	 * 
	 * @return single instance of EventsResources
	 */
	public static EventsResources getInstance() {
		if (singleton == null) {
			singleton = new EventsResources();
		}
		return singleton;

	}

	public void storeEvents(SymbolEvents symbolEvents, Boolean persist, String analysisName) {
		List<SymbolEvents> eventList = new ArrayList<SymbolEvents>();
		eventList.add(symbolEvents);
		storeEvents(eventList, persist, analysisName);
		
	}
	
	private void addEventsToCache(List<SymbolEvents> events, String eventListName) {

		if (isEventCached) {
			
			synchronized (this) {
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					eventsForName = new StockEventsCache();
					EVENTS_CACHE.put(eventListName, eventsForName);
				}
				for (SymbolEvents symbolEvents : events) {
					ConcurrentSkipListSet<EventCacheEntry> sortedEventsForStock = eventsForName.getEvents(symbolEvents.getStock());
					if (sortedEventsForStock == null) {
						sortedEventsForStock = new ConcurrentSkipListSet<EventCacheEntry>(new EventCacheEntryComparator());
						eventsForName.putEvents(symbolEvents.getStock(), sortedEventsForStock);
					}
					sortedEventsForStock.addAll(buildEventCacheEntries(symbolEvents));
				}
			}
		}
		
	}

	
	private Collection<EventCacheEntry> buildEventCacheEntries(SymbolEvents symbolEvents) {
		
		List<EventCacheEntry> ret = new ArrayList<EventsResources.EventCacheEntry>();
		for (EventKey eventKey : symbolEvents.getDataResultList().keySet()) {
			ret.add(new EventCacheEntry(eventKey, symbolEvents.getDataResultList().get(eventKey)));
		}
		return ret;
		
	}

	public void updateEventsCache(Date startDate, Date endDate, Set<String> eventListNames) {

		for (String eventListName : eventListNames) {
			List<SymbolEvents> eventsForName = DataSource.getInstance().loadEventsByDate(startDate, endDate, eventListName);
			this.addEventsToCache(eventsForName, eventListName);
		}
	}
	
	public void updateEventsCache(Stock stock, Date startDate, Date endDate, Set<String> eventListNames) {

		for (String eventListName : eventListNames) {
			SymbolEvents eventsForName = DataSource.getInstance().loadEventsByDate(stock, startDate, endDate, eventListName);
			this.addEventsToCache(Arrays.asList(new SymbolEvents[]{eventsForName}), eventListName);
		}
	}
	
	public SymbolEvents getListEventsFor(Stock stock, Date startDate, Date endDate, Boolean isPersisted, String... eventListNames) {
		
		if (!isEventCached && !isPersisted) {
			String message = "Inconsitency : Events are neither persited or cached.";
			LOGGER.error(message, new Exception());
			throw new InvalidParameterException(message);
		}
		
		Set<String> eventListNamesSet = disctinctNames(eventListNames);
		
		SymbolEvents retVal =  new SymbolEvents(stock, EventState.STATE_TERMINATED);
		if (isEventCached) {
			
			if (isPersisted) {
				this.updateEventsCache(stock, startDate, endDate, eventListNamesSet);
			}
			
			for (String eventListName : eventListNamesSet) {
				
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					LOGGER.info("No events cached for " + eventListName+ " and "+stock + " from "+startDate+" to "+endDate);
				} else {
					try {
						retVal = subCachedEvents(stock, startDate, endDate, eventsForName, eventListName);
					} catch (Exception e) {
						LOGGER.error(e,e);
					}
				}
			}
			
		} else {
			
			retVal = DataSource.getInstance().loadEventsByDate(stock, startDate, endDate, eventListNamesSet.toArray(new String[eventListNamesSet.size()]));
		}
		
		return retVal;
	}

	
	public List<SymbolEvents> getListEventsFor(Date startDate, Date endDate, Boolean isPersisted, String... eventListNames) {
		
		if (!isEventCached && !isPersisted) {
			String message = "Inconsitency : Events are neither persited or cached.";
			LOGGER.error(message, new Exception());
			throw new InvalidParameterException(message);
		}
		
		List<SymbolEvents> retVal = new ArrayList<SymbolEvents>();
		Set<String> eventListNamesSet = disctinctNames(eventListNames);
		
		if (isEventCached) {
			
			if (isPersisted) {
				this.updateEventsCache(startDate, endDate, eventListNamesSet);
			}
			
			for (String eventListName : eventListNamesSet) {
				
				StockEventsCache eventsForName = EVENTS_CACHE.get(eventListName);
				if (eventsForName == null) {
					LOGGER.info("No events cached for " + eventListName);
				} else {
					for (Stock stock : eventsForName.keySet()) {
						SymbolEvents subCachedEvents;
						try {
							subCachedEvents = subCachedEvents(stock, startDate, endDate, eventsForName, eventListName);
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
			
			retVal = DataSource.getInstance().loadEventsByDate(startDate, endDate, eventListNamesSet.toArray(new String[eventListNamesSet.size()]));
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
	
	private SymbolEvents subCachedEvents(Stock stock, Date startDate, Date endDate, StockEventsCache eventsForName, String eventListName) {

		SymbolEvents subSymbolEvents = new SymbolEvents(stock, EventState.STATE_TERMINATED);
		EventCacheEntry startInfEvent = smallestCacheEntry(startDate);
		EventCacheEntry endSupEvent = bigestCacheEntry(endDate);
		
		SortedSet<EventCacheEntry> eventsForStockAndName = eventsForName.getEvents(stock);
		if (eventsForStockAndName != null && !eventsForStockAndName.isEmpty()) {
			SortedSet<EventCacheEntry> eventSubSet = eventsForStockAndName.subSet(startInfEvent, endSupEvent);
			if (eventSubSet.size() > 0) {
				for (EventCacheEntry eventCacheEntry : eventSubSet) {
					subSymbolEvents.addEventResultElement(eventCacheEntry.getEventKey(), eventCacheEntry.getEventValue(), eventCacheEntry.getEventValue().getEventDef().name());
				}
			} 
		} 
		return subSymbolEvents;
	}
	
	@SuppressWarnings("rawtypes")
	private EventCacheEntry bigestCacheEntry(final Date date) {

		return new EventCacheEntry(new EventKey() {

			private static final long serialVersionUID = -5684675854556973652L;

			public Comparable getEventDefId() {
				return EventDefinition.INFINITE.getEventDefId();
			}

			public Comparable getEventDefExtra() {
				return EventDefinition.INFINITE;
			}

			public Comparable getEvenType() {
				return EventType.NONE;
			}

			public Date getDate() {
				return date;
			}

		}, new EventValue(date, EventDefinition.INFINITE, EventType.NONE, ""));
	}

	@SuppressWarnings("rawtypes")
	private EventCacheEntry smallestCacheEntry(final Date date) {
		
		return new EventCacheEntry(new EventKey() {

			private static final long serialVersionUID = -5684675854556973652L;

			public Comparable getEventDefId() {
				return EventDefinition.ZERO.getEventDefId();
			}
			
			
			public Comparable getEventDefExtra() {
				return EventDefinition.ZERO;
			}
			
			
			public Comparable getEvenType() {
				return EventType.NONE;
			}
			
			
			public Date getDate() {
				return date;
			}
			
		}, 
		new EventValue(date, EventDefinition.ZERO, EventType.NONE,""));
	}
	
	/**
	 * Store events.
	 * 
	 * @param events the events
	 * 
	 * @return the list< object>
	 * 
	 * @author Guillaume Thoreton
	 * @param eventListName 
	 */
	public void storeEvents(List<SymbolEvents> events, Boolean persist, String eventListName) {
		
		if (isEventCached) {
			addEventsToCache(events, eventListName);
		}
		
		if (persist || !isEventCached) {

			ArrayList<Query> qInsert = new ArrayList<Query>();
			ArrayList<Validatable> lqUpdate = new ArrayList<Validatable>();
			buildUpdateValidatableList(events, qInsert, lqUpdate);

			List<Validatable> lqRemainingInsert = new ArrayList<Validatable>();
			int[] updated = new int[0];
			try {

				updated = DataSource.getInstance().executeBlockWithTimeStamp(lqUpdate, DataSource.EVENTS.getUPDATE());
				
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
				
					DataSource.getInstance().executeBlockWithTimeStamp(lqRemainingInsert, DataSource.EVENTS.getINSERT());
					
				} catch (Exception e) {
					LOGGER.error(
							"Pb inserting after updating events :\n" +
							"Update request params :\n"+
								DataSource.printHugeCollection(lqUpdate)+"\n" +
							"Insert request params :\n"+
								DataSource.printHugeCollection(lqRemainingInsert)+"\n" +
							"Update return was " +
									Arrays.toString(updated)
							, e);
				}

			} catch (SQLException e) {
				LOGGER.error("Pb insert/update events :\n" +
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

			Map<EventKey, EventValue> dataResultList = se.getDataResultList();
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
						//qupdate.addValue(new String(""+drv.getEventType().getEventTypeChar()));
						//TODO check consistency between dynamic event list and Enum list !!!
						qupdate.addValue(eventValue.getEventDef().getEventDef());
						qupdate.addValue(eventValue.getEventType().getEventTypeChar().toString());
						qupdate.addValue(eventValue.getMessage());

						//where
						qupdate.addValue(se.getSymbol().toUpperCase());
						qupdate.addValue(se.getIsin());
						qupdate.addValue(eventValue.getDate());
						qupdate.addValue(eventValue.getEventDefId());
						qupdate.addValue(eventKey.getEventDefExtra().toString());
						qupdate.addValue(eventValue.getEventListName());

						return qupdate;
					}

					@Override
					public String toString() {
						//return "["+eventValue.getDate()+","+eventValue.getEventDefId()+","+eventValue.getEventType()+","+eventValue.getEventListName()+","+se.getSymbol().toUpperCase()+","+se.getIsin()+"]";
						return "["+se.getSymbol().toUpperCase()+","+se.getIsin()+","+eventValue.getDate()+","+eventValue.getEventDefId()+","+eventKey.getEventDefExtra().toString()+","+eventValue.getEventListName()+"]";
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
		qinsert.addValue(se.getSymbol().toUpperCase());
		qinsert.addValue(se.getIsin());
		qinsert.addValue(new Integer(se.getEventsState().ordinal()));
		qinsert.addValue(eventValue.getDate());
		qinsert.addValue(eventValue.getEventDefId());
		//qinsert.addValue(se.getEventDefList().get(drv.getEventDefId()));
		//TODO check consistency between dynamic event list and Enum list !!!
		qinsert.addValue(eventValue.getEventDef().getEventDef());
		qinsert.addValue(eventKey.getEventDefExtra().toString());
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
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 * @param eventListNames 
	 */
	public void loadEventsByCriteriaAndDate(Date date, Integer inf, Integer sup, PonderationRule pr, String eventListNames)
			throws InvalidAlgorithmParameterException {
		
		List<SymbolEvents> all = SymbolEvents.sortList(EventsResources.getInstance().getListEventsFor(date, EventSignalConfig.getNewDate(), true, eventListNames), pr);
		int indexSup = 0;
		int indexInf = all.size() - 1;
		
		if (sup < inf) {
			LOGGER.error("Wrong range : Sup =" + sup + " , Inf =" + inf);
			throw new InvalidAlgorithmParameterException("Wrong range : Sup =" + sup + " , Inf =" + inf);
		}
		
		if (all.size() > 0) {
			if (inf != null) {
				while (indexInf > 0 && all.get(indexInf).getWeight(pr) <= inf)
					indexInf--;
				if (indexInf < all.size())
					this.setFinList(all.subList(indexInf + 1, all.size()));
				else
					this.setFinList(new ArrayList<SymbolEvents>());
			}
			if (sup != null) {
				while (indexSup < all.size() && all.get(indexSup).getWeight(pr) >= sup)
					indexSup++;
				if (indexSup > 0)
					this.setDebList(all.subList(0, indexSup));
				else
					this.setDebList(new ArrayList<SymbolEvents>());
			}
			if (indexInf + 1 > indexSup) {
				this.setMidleList(all.subList(indexSup, indexInf + 1));
			} else {
				this.setMidleList(new ArrayList<SymbolEvents>());
			}
			this.setSortedList(all);
		} else {
			sortedList = new ArrayList<SymbolEvents>();
			midleList = new ArrayList<SymbolEvents>();
			finList = new ArrayList<SymbolEvents>();
			debList = new ArrayList<SymbolEvents>();
		}
	}
	
	/**
	 * Filter monitored events.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void filterNonMonitoredEvents() {
		
		List<SymbolEvents> dl = new ArrayList<SymbolEvents>(); 
		List<SymbolEvents> fl = new ArrayList<SymbolEvents>();
		List<SymbolEvents> ml = new ArrayList<SymbolEvents>();
		List<SymbolEvents> sl = new ArrayList<SymbolEvents>();
		
		for (Object o: this.getDebList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (se.isMonitored()) dl.add(se);
		}
		this.setDebList(dl);
		for (Object o: this.getFinList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (se.isMonitored()) fl.add(se);
		}
		this.setFinList(fl);
		for (Object o: this.getMidleList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (se.isMonitored()) ml.add(se);
		}
		this.setMidleList(ml);
		for (Object o: this.getSortedList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (se.isMonitored()) sl.add(se);
		}
		this.setSortedList(sl);
		
	}
	
	/**
	 * Filter category events.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void filterCurrentMarketEvents() {
		
		Providers provider = Providers.getInstance(MainPMScmd.getPrefs().get("quotes.listprovider", SharesListId.YAHOOINDICES.getSharesListCmdParam()));
		SharesList sharesListForThisListProvider = provider.loadSharesListForThisListProvider();
		Collection<Stock> currentMarketStock = sharesListForThisListProvider.getListShares().keySet();
		
		filterOtherThen(currentMarketStock);
		
	}
	
	public void filterNonPortofolioEvents() {
		
		Collection<Stock> portfolioStocks = DataSource.getInstance().getShareDAO().loadAllUserPortoflioShares();
		
		filterOtherThen(portfolioStocks);
		
	}

	@SuppressWarnings("unused")
	private void filterCollection(Collection<Stock> stocksToFilterOut) {
		
		List<SymbolEvents> dl = new ArrayList<SymbolEvents>(); 
		List<SymbolEvents> fl = new ArrayList<SymbolEvents>();
		List<SymbolEvents> ml = new ArrayList<SymbolEvents>();
		List<SymbolEvents> sl = new ArrayList<SymbolEvents>();
		
		for (Object o: this.getDebList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (!stocksToFilterOut.contains(se.getStock())) dl.add(se);
		}
		this.setDebList(dl);
		for (Object o: this.getFinList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (!stocksToFilterOut.contains(se.getStock())) fl.add(se);
		}
		this.setFinList(fl);
		for (Object o: this.getMidleList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (!stocksToFilterOut.contains(se.getStock())) ml.add(se);
		}
		this.setMidleList(ml);
		for (Object o: this.getSortedList()) {
			SymbolEvents se = (SymbolEvents) o;
			if (!stocksToFilterOut.contains(se.getStock())) sl.add(se);
		}
		this.setSortedList(sl);
	}
	
	public void filterOtherEvents() {
		
		Collection<Stock> portfolioStocks = DataSource.getInstance().getShareDAO().loadAllUserPortoflioShares();
		
		Providers provider = Providers.getInstance(MainPMScmd.getPrefs().get("quotes.listprovider", SharesListId.YAHOOINDICES.getSharesListCmdParam()));
		SharesList sharesListForThisListProvider = provider.loadSharesListForThisListProvider();
		Collection<Stock> currentMarketStock = sharesListForThisListProvider.getListShares().keySet();
		
		Collection<Stock> otherEventsComplement = new ArrayList<Stock>();
		otherEventsComplement.addAll(portfolioStocks);
		otherEventsComplement.addAll(currentMarketStock);
		
		filterOtherThen(otherEventsComplement);
		
	}

	private void filterOtherThen(Collection<Stock> stocksToKeep) {
		
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
	public void exportEvents(Collection<? extends Object> events, String file,
			PonderationRule pr) {

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



	/**
	 * Sets the sorted list.
	 * 
	 * @param sortedList the new sorted list
	 */
	public void setSortedList(List<SymbolEvents> sortedList) {
		this.sortedList = sortedList;
	}
	
	public void cleanEventsForAnalysisNameAndStock(Stock stock, String analysisName, Date datedeb, Date datefin, Boolean persist, EventDefinition... indicators) {
		
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				StockEventsCache eventsForAnalysis = EVENTS_CACHE.get(analysisName);
				if (eventsForAnalysis != null) {
					removeEventsFromEventsCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
				}
			}
		}
		
		//DB
		if (persist || !isEventCached) {
			DataSource.getInstance().cleanEventsForAnalysisNameAndStock(stock, analysisName, datedeb, datefin, indicators);
		}
		
	}


	public void cleanEventsForAnalysisName(String analysisName, Date datedeb, Date datefin, Boolean persist, EventDefinition... indicators) {
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				StockEventsCache eventsForAnalysis = EVENTS_CACHE.get(analysisName);
				if (eventsForAnalysis != null) {
					for (Stock stock : eventsForAnalysis.keySet()) {
						removeEventsFromEventsCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
					}
				}
			}
		}
		
		//DB
		if (persist || !isEventCached) {
			DataSource.getInstance().cleanEventsForAnalysisName(analysisName, datedeb, datefin, indicators);
		}
	}
	
	public void cleanEventsForAnalysisName(String analysisName, Boolean persist) {
		
		//Cash
		if (isEventCached) {
			synchronized (this) {
				EVENTS_CACHE.remove(analysisName);
			}
		}
		
		//DB
		if (persist || !isEventCached) {
			DataSource.getInstance().cleanEventsForAnalysisName(analysisName);
		}
	}
	
	/**
	 * @param stock
	 * @param configName
	 * @param startDate
	 * @param endDate
	 */
	private void removeEventsFromEventsCacheFor(Stock stock, StockEventsCache eventsForAnalysis, Date startDate, Date endDate, EventDefinition... indicators) {
		
		List<EventDefinition> indicatorsList = Arrays.asList(indicators);
		if (eventsForAnalysis != null) {
			SortedSet<EventCacheEntry> eventsForStockAndAnalysis = eventsForAnalysis.getEvents(stock);
			if (eventsForStockAndAnalysis != null) {
				SortedSet<EventCacheEntry> dateSubSet = eventsForStockAndAnalysis.subSet(smallestCacheEntry(startDate), bigestCacheEntry(endDate));
				SortedSet<EventCacheEntry> subSetToRemove = new TreeSet<EventCacheEntry>(new EventCacheEntryComparator());
				for (EventCacheEntry eventCacheEntry : dateSubSet) {
					EventDefinition eventDef =  eventCacheEntry.getEventValue().getEventDef();
					if (indicatorsList.contains(eventDef)) {
						subSetToRemove.add(eventCacheEntry);
					}
				}
				eventsForStockAndAnalysis.removeAll(subSetToRemove);
			}
		}
	}
	
	
	private class StockEventsCache  {
		
		ConcurrentHashMap<Stock, SoftReference<ConcurrentSkipListSet<EventCacheEntry>>> underLayingMap;
		
		public StockEventsCache() {
			super();
			underLayingMap = new ConcurrentHashMap<Stock, SoftReference<ConcurrentSkipListSet<EventCacheEntry>>>();
		}
		
		public Set<Stock> keySet() {
			return underLayingMap.keySet();
		}

		public void putEvents(Stock stock, ConcurrentSkipListSet<EventCacheEntry> cacheEntries) {
			
			SoftReference<ConcurrentSkipListSet<EventCacheEntry>> oldValue = underLayingMap.put(stock, new SoftReference<ConcurrentSkipListSet<EventCacheEntry>>(cacheEntries));
			if (oldValue != null) {
				oldValue.clear();
			}
		}
		
		public ConcurrentSkipListSet<EventCacheEntry> getEvents(Stock stock) {
			
			SoftReference<ConcurrentSkipListSet<EventCacheEntry>> softRef = underLayingMap.get(stock);
			if (softRef == null) {
				return null;
			} else {
				return softRef.get();
			}
		}
		
	}
	


}
