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
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.StockToDB;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.scoring.TunedConfMgr;
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

    private ConcurrentHashMap<String, EventsForAnalisysNameCacheHolder> EVENTS_CACHE = new ConcurrentHashMap<String, EventsForAnalisysNameCacheHolder>();
    private Boolean isEventCached;
    private Boolean isCachePersistent;
    private Boolean isEventPersisted;

    public List<SymbolEvents> sortedList;
    public List<SymbolEvents> midleList;
    public List<SymbolEvents> finList;
    public List<SymbolEvents> debList;

    ConcurrentHashMap<Stock, Lock> locks;

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

            int cmp = (se1.getEventKey().getDate()).compareTo(se2.getEventKey().getDate());
            if (cmp == 0) {
                cmp = (se1.getEventKey().getEventInfo().getEventDefId()).compareTo(se2.getEventKey().getEventInfo().getEventDefId());
                if (cmp == 0) {
                    cmp = (se1.getEventKey().getEventInfoExtra()).compareTo(se2.getEventKey().getEventInfoExtra());
                    if (cmp == 0) {
                        cmp = (se1.getEventKey().getEventType()).compareTo(se2.getEventKey().getEventType());
                    }
                }
            }

            return cmp;
        }

    }

    class EventCacheEntryList extends ConcurrentSkipListSet<EventCacheEntry> {

        private static final long serialVersionUID = 2661601346495228458L;
        private final UUID uuid = UUID.randomUUID();

        public EventCacheEntryList(Comparator<? super EventCacheEntry> comparator) {
            super(comparator);
        }

        public UUID getUuid() {
            return uuid;
        }

    }

    private class EventsForAnalisysNameCacheHolder {

        private ConcurrentHashMap<Stock, Map<EventInfo, SoftReference<EventCacheEntryList>>> underLayingSoftMap;

        private EventsForAnalisysNameCacheHolder() {
            super();
            underLayingSoftMap = new ConcurrentHashMap<Stock, Map<EventInfo, SoftReference<EventCacheEntryList>>>();
        }

        private Set<Stock> keySet() {
            return underLayingSoftMap.keySet();
        }

        private Map<EventInfo, SoftReference<EventCacheEntryList>> get(Stock stock) {
            return underLayingSoftMap.get(stock);
        }
        private EventCacheEntryList readEventsFromStockCache(Stock stock, EventInfo eventDef) {

            Map<EventInfo, SoftReference<EventCacheEntryList>> map = underLayingSoftMap.get(stock);
            if (map == null) return null; //No cache for this stock
            SoftReference<EventCacheEntryList> softRef = map.get(eventDef);
            if (softRef == null) {//No cache for this eventDef
                return null;
            } else {
                return softRef.get();
            }
        }

        private void deleteEventsFromStockSoftCache(Stock stock, Date startDate, Date endDate, List<EventInfo> indicatorsList) {

            indicatorsList.stream().forEach(ei -> {
                SortedSet<EventCacheEntry> eventsForStockAndAnalysis = this.readEventsFromStockCache(stock, ei);
                if (eventsForStockAndAnalysis != null && !eventsForStockAndAnalysis.isEmpty()) {
                    SortedSet<EventCacheEntry> subSetToRemove = eventsForStockAndAnalysis.subSet(smallestCacheEntry(startDate), bigestCacheEntry(endDate));
                    eventsForStockAndAnalysis.removeAll(subSetToRemove);
                }
            });
        }

        private void addEventsToStockSoftCache(List<SymbolEvents> symbolEventsList, EventInfo eventInfo) {

            for (SymbolEvents symbolEvents : symbolEventsList) {

                EventCacheEntryList sortedEventsForStockNEventInfo = this.readEventsFromStockCache(symbolEvents.getStock(), eventInfo);
                if (sortedEventsForStockNEventInfo == null) {
                    sortedEventsForStockNEventInfo = this.createEventsEntryInSoftStockCache(symbolEvents.getStock(), eventInfo);
                }

                Collection<EventCacheEntry> builtCacheEntriesFromSymbolEvents = buildCacheEntriesFromSymbolEvents(symbolEvents, eventInfo);
                sortedEventsForStockNEventInfo.addAll(builtCacheEntriesFromSymbolEvents);

            }
        }

        private EventCacheEntryList createEventsEntryInSoftStockCache(Stock stock, EventInfo eventInfo) {

            EventCacheEntryList cacheEntries = new EventCacheEntryList(new EventCacheEntryComparator());
            SoftReference<EventCacheEntryList> value = new SoftReference<EventCacheEntryList>(cacheEntries);

            Map<EventInfo, SoftReference<EventCacheEntryList>> map = underLayingSoftMap.get(stock);
            if (map == null) {//New stock entry
                map = new ConcurrentHashMap<>();
                underLayingSoftMap.put(stock, map);
            }

            SoftReference<EventCacheEntryList> oldValue = map.put(eventInfo, value);//New EventDef entry
            if (oldValue != null) {
                oldValue.clear();
            }

            return cacheEntries;

        }

    }


    private EventsResources() {
        super();

        String eventCacheProp = MainPMScmd.getMyPrefs().get("event.cache","true");
        LOGGER.info("Event cache is set to "+eventCacheProp);

        isEventCached = new Boolean(eventCacheProp);

        //FIXME At the moment, we can either use cache persistence or effectively persist in EVENTS table but not both together.
        //TODO use an of shelf cache system.
        isCachePersistent = false;
        isEventPersisted = true;

        locks = new ConcurrentHashMap<Stock, Lock>();

        resetSortLists();

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

    public void crudCreateEvents(SymbolEvents symbolEvents, String analysisName) {

        List<SymbolEvents> eventList = new ArrayList<SymbolEvents>();
        eventList.add(symbolEvents);
        crudCreateEvents(eventList, analysisName);

    }

    private void addEventsToSoftCache(List<SymbolEvents> symbolEventsList, String eventListName, EventInfo eventInfo) {

        if (isEventCached) {

            synchronized (this) {
                //No Cache holder for this eventListName is present, we add it.
                EventsForAnalisysNameCacheHolder eventsForEventListName = EVENTS_CACHE.get(eventListName);
                if (eventsForEventListName == null) {
                    eventsForEventListName = new EventsForAnalisysNameCacheHolder();
                    EVENTS_CACHE.put(eventListName, eventsForEventListName);
                }
                //
                eventsForEventListName.addEventsToStockSoftCache(symbolEventsList, eventInfo);
            }
        }

    }

    private Collection<EventCacheEntry> buildCacheEntriesFromSymbolEvents(SymbolEvents symbolEvents, EventInfo eventInfo) {

        SortedMap<EventKey, EventValue> sortedDataResultMap = symbolEvents.getSortedDataResultMap();
        //We filter out the eventDef
        List<EventCacheEntry> ret = sortedDataResultMap.keySet().stream()
                .filter(ek -> ek.getEventInfo().equals(eventInfo))
                .map(ek -> new EventCacheEntry(ek, sortedDataResultMap.get(ek)))
                .collect(Collectors.toList());
        return ret;

    }

    private SymbolEvents buildSymbolEventsFromCacheEntries(Stock stock, SortedSet<EventCacheEntry> eventSubSet, Set<EventInfo> eventDefinitions) {
        SymbolEvents subDateRangeSymbolEvents = new SymbolEvents(stock, EventState.STATE_TERMINATED);
        if (eventSubSet.size() > 0) {
            for (EventCacheEntry eventCacheEntry : eventSubSet) {
                if (eventDefinitions == null || eventDefinitions.contains(eventCacheEntry.getEventValue().getEventDef())) {
                    subDateRangeSymbolEvents.addEventResultElement(eventCacheEntry.getEventKey(), eventCacheEntry.getEventValue(), eventCacheEntry.getEventValue().getEventDef().getEventDefinitionRef());
                }
            }
        }
        return subDateRangeSymbolEvents;
    }

    private void updateEventsCache(Date startDate, Date endDate, Set<EventInfo> eventDefinitions, Set<String> eventListNames, String eventsTableName) {

        for (String eventListName : eventListNames) {
            //TODO : check if cache is up to date
            LOGGER.info("Updating soft cache from " + startDate + " to " + endDate + " for "+ eventDefinitions.stream().map(e-> e.getEventDefinitionRef()).reduce((r,e) -> r +", "+ e) + " and " + eventListName + " from "+ eventsTableName);
            List<SymbolEvents> eventsForEventListName = DataSource.getInstance().loadEventsByDate(eventsTableName, startDate, endDate, eventDefinitions, eventListName);
            eventDefinitions.stream().forEach(eventInfo -> this.addEventsToSoftCache(eventsForEventListName, eventListName, eventInfo));
        }

    }

    private void updateEventsCache(Stock stock, Set<EventInfo> eventDefinitions, Set<String> eventListNames, String eventsTableName) {

        //We load up the full date range and this is to simplify the cache consistency management.
        Date startDate = EventModel.DEFAULT_DATE;
        Date endDate = EventSignalConfig.getNewDate();

        //
        for (String eventListName : eventListNames) {
            EventsForAnalisysNameCacheHolder eventListNameEventsCache = EVENTS_CACHE.get(eventListName);
            if (eventListNameEventsCache == null || eventListNameEventsCache.get(stock) == null) { //New analyses entry or stock entry for this analyses
                LOGGER.debug("Events not found in cache for : "+eventListName+ " and incidentally, "+stock + " from "+startDate+" to "+endDate);
                SymbolEvents eventsForEventListNameNStockNEventDefs = DataSource.getInstance().loadEventsByDate(eventsTableName, stock, startDate, endDate, eventDefinitions, eventListName);
                eventDefinitions.stream().forEach(eventInfo -> this.addEventsToSoftCache(Arrays.asList(new SymbolEvents[]{eventsForEventListNameNStockNEventDefs}), eventListName, eventInfo));
            } else { //Existing analyses and stock
                LOGGER.debug(eventListName + " has had been cached but needs check for potential clearance.");
                Set<EventInfo> newEvDefsEntries = eventDefinitions.stream()
                        .filter(eventInfo -> eventListNameEventsCache.readEventsFromStockCache(stock, eventInfo) == null) //New EventDef entry
                        .collect(Collectors.toSet());
                if (newEvDefsEntries.isEmpty()) {
                    LOGGER.debug("All events are up to date in cache for : "+eventListName+ " and "+ eventDefinitions.stream().map(e-> e.getEventDefinitionRef()).reduce((r,e) -> r +", "+ e) + " and " + stock + " and incidentally from "+startDate+" to "+endDate);
                    return;
                }
                LOGGER.info("Events not found in cache for : " + eventListName + " and "+ newEvDefsEntries.stream().map(e-> e.getEventDefinitionRef()).reduce((r,e) -> r +", "+ e) + " and "+stock + ", incidentally from "+startDate+" to "+endDate);
                SymbolEvents eventsForName = DataSource.getInstance().loadEventsByDate(eventsTableName, stock, startDate, endDate, newEvDefsEntries, eventListName);
                newEvDefsEntries.stream().forEach(eventInfo -> this.addEventsToSoftCache(Arrays.asList(new SymbolEvents[]{eventsForName}), eventListName, eventInfo));
            }
        }

    }

    public SymbolEvents crudReadEventsForStock(Stock stock, Date startDate, Date endDate, Set<EventInfo> eventDefinitions, String... eventListNames) {

        //Consistency checks
        if (eventDefinitions != null && eventDefinitions.contains(EventDefinition.PARAMETERIZED)) throw new IllegalArgumentException("Can't directly deal with PARAMETERIZED. Use EventInfo Sub set instead");

        if (!isEventCached && !isEventPersisted) {
            String message = "Inconsistency : Events are neither persisted or cached.";
            LOGGER.error(message, new Exception());
            throw new InvalidParameterException(message);
        }

        Set<String> eventListNamesSet = disctinctNames(eventListNames);

        //Result building
        SymbolEvents retVal =  new SymbolEvents(stock, EventState.STATE_TERMINATED);
        if (isEventCached) {

            //Updating soft cache (if not present for eventlistName, stock and eventDefinition)
            if (isEventPersisted) {
                this.updateEventsCache(stock, eventDefinitions, eventListNamesSet, EVENTSTABLE);
            }
            else if (isCachePersistent) {
                this.updateEventsCache(stock, eventDefinitions, eventListNamesSet, EVENTSCACHETABLE);
            }

            //Reading soft cache and building results
            for (String eventListName : eventListNamesSet) {

                EventsForAnalisysNameCacheHolder eventsForEventListName = EVENTS_CACHE.get(eventListName);
                if (eventsForEventListName == null) {
                    LOGGER.info("No events cached for " + eventListName+ " and "+stock + " from "+startDate+" to "+endDate);
                } else {
                    try {
                        retVal = readDateRangeSubSoftCachedEvents(stock, startDate, endDate, eventsForEventListName, eventListName, eventDefinitions);
                    } catch (Exception e) {
                        LOGGER.error("Stock "+stock+" event retrieval pb : "+e, e);
                    }
                }
            }

        } else {//No cache
            retVal = DataSource.getInstance().loadEventsByDate(EVENTSTABLE, stock, startDate, endDate, eventDefinitions, eventListNamesSet.toArray(new String[eventListNamesSet.size()]));
        }

        return retVal;
    }


    public List<SymbolEvents> crudReadEvents(Date startDate, Date endDate, Set<EventInfo> eventDefinitions, String... eventListNames) {

        if (eventDefinitions != null && eventDefinitions.contains(EventDefinition.PARAMETERIZED)) throw new IllegalArgumentException("Can't directly deal with PARAMETERIZED. Use EventInfo Sub set instead");

        if (!isEventCached && !isEventPersisted) {
            String message = "Inconsistency : Events are neither persisted or cached.";
            LOGGER.error(message, new Exception());
            throw new InvalidParameterException(message);
        }

        Set<String> eventListNamesSet = disctinctNames(eventListNames);

        List<SymbolEvents> retVal = new ArrayList<SymbolEvents>();
        if (isEventCached) {

            if (isEventPersisted) { //Update the soft cache from events table
                this.updateEventsCache(startDate, endDate, eventDefinitions, eventListNamesSet, EVENTSTABLE);
            }
            else if (isCachePersistent) { //Update the soft cache from the hard cache
                this.updateEventsCache(startDate, endDate, eventDefinitions, eventListNamesSet, EVENTSCACHETABLE);
            }

            //Build response from soft cache
            for (String eventListName : eventListNamesSet) {

                EventsForAnalisysNameCacheHolder eventsForEventListName = EVENTS_CACHE.get(eventListName);
                if (eventsForEventListName == null) {
                    LOGGER.info("No events cached for " + eventListName);
                } else {
                    for (Stock stock : eventsForEventListName.keySet()) {//We assume all stocks have been uploaded for this eventListName <- size issue ??
                        SymbolEvents subDateRangeCachedEvents;
                        try {
                            subDateRangeCachedEvents = readDateRangeSubSoftCachedEvents(stock, startDate, endDate, eventsForEventListName, eventListName, eventDefinitions);
                            if (subDateRangeCachedEvents != null) {
                                if (retVal.contains(subDateRangeCachedEvents)) {
                                    retVal.get(retVal.indexOf(subDateRangeCachedEvents)).addEventResultElement(subDateRangeCachedEvents);
                                } else {
                                    retVal.add(subDateRangeCachedEvents);
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


    private Set<String> disctinctNames(String... eventListNames) {
        Set<String> eventListNamesSet = new HashSet<String>();
        eventListNamesSet.addAll(Arrays.asList(eventListNames));
        return eventListNamesSet;
    }

    private SymbolEvents readDateRangeSubSoftCachedEvents(Stock stock, Date startDate, Date endDate, EventsForAnalisysNameCacheHolder eventsForName, String eventListName, Set<EventInfo> eventDefinitions) {

        EventCacheEntry startInfEvent = smallestCacheEntry(startDate);
        EventCacheEntry endSupEvent = bigestCacheEntry(endDate);

        EventCacheEntryList eventsForStockAndName = new EventCacheEntryList(new EventCacheEntryComparator());
        //FIXME too slow ... Cache should be stored in date order and cummulated using sub lists
        eventsForStockAndName.addAll(eventDefinitions.stream().flatMap(ei -> eventsForName.readEventsFromStockCache(stock, ei).stream()).collect(Collectors.toSet()));
        if (eventsForStockAndName != null && !eventsForStockAndName.isEmpty()) {
            try {
                SortedSet<EventCacheEntry> eventsDateRangeSubSet = eventsForStockAndName.subSet(startInfEvent, true, endSupEvent, true);
                return buildSymbolEventsFromCacheEntries(stock, eventsDateRangeSubSet, eventDefinitions);
            } catch (IllegalArgumentException e) {
                LOGGER.warn("No events in cache for "+stock+" and "+eventListName+" from "+startDate+" to "+endDate+". Available first, last events : "+eventsForStockAndName.first()+" to "+eventsForStockAndName.last(), e);
            } 
        } 
        return new SymbolEvents(stock, EventState.STATE_TERMINATED);
    }

    private EventCacheEntry bigestCacheEntry(final Date date) {

        Date midnithDate = DateFactory.midnithDate(date);
        EventDefinition infinite = EventDefinition.INFINITE;
        EventType none = EventType.NONE;
        return new EventCacheEntry(new EventKey() {

            private static final long serialVersionUID = 1087077818535168128L;

            public EventInfo getEventInfo() {
                return infinite;
            }

            public String getEventInfoExtra() {
                return "z";
            }

            public EventType getEventType() {
                return none;
            }

            public Date getDate() {
                return midnithDate;
            }

        }, new EventValue(midnithDate, infinite, none, ""));
    }

    private EventCacheEntry smallestCacheEntry(final Date date) {

        Date midnithDate = DateFactory.midnithDate(date);
        EventDefinition zero = EventDefinition.ZERO;
        EventType none = EventType.NONE;
        return new EventCacheEntry(new EventKey() {

            private static final long serialVersionUID = 4155656864927650654L;

            public EventInfo getEventInfo() {
                return zero;
            }

            public String getEventInfoExtra() {
                return "A";
            }

            public EventType getEventType() {
                return none;
            }

            public Date getDate() {
                return midnithDate;
            }

        }, new EventValue(midnithDate, zero, none, ""));
    }


    public void crudCreateEvents(List<SymbolEvents> symbolEventsList, String eventListName) {

        if (isEventCached) {

            String eventsTableName = EVENTSTABLE;
            if (isCachePersistent && !isEventPersisted) {
                persitEvents(symbolEventsList, eventListName, EVENTSCACHETABLE);
                eventsTableName = EVENTSCACHETABLE;
            }

            final String fEventsTableName = eventsTableName;
            //We first need to update the soft cache with existing persisted (potentially wiped off or not loaded yet) events in order to keep consistency
            symbolEventsList.stream().forEach(se -> {
                Set<EventInfo> eventDefList = se.getEventDefList().stream().map(eis -> {
                    try {
                        EventInfo valueOfEventInfo = EventDefinition.valueOfEventInfo(eis);
                        return valueOfEventInfo;
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet());
                Set<String> eventListNameSet = new HashSet<String>();
                eventListNameSet.add(eventListName);
                updateEventsCache(se.getStock(), eventDefList, eventListNameSet, fEventsTableName);

                //adding new events to soft cache
                eventDefList.stream().forEach(ei -> addEventsToSoftCache(Arrays.asList(new SymbolEvents[]{se}), eventListName, ei));
            });

        }

        if (isEventPersisted || !isEventCached) {

            LOGGER.info("Storing Events in db cached is "+isEventCached+", persist is "+isEventPersisted+", other params "+eventListName);
            persitEvents(symbolEventsList, eventListName, EVENTSTABLE);

        } 
    }

    private void persitEvents(List<SymbolEvents> events, String eventListName, String eventTableName) {

        LOGGER.info("INSERT/UPDATE "+events.stream().map(e -> e.getDataResultMap().size()).reduce((r,e) -> r+e)+" events for "+eventListName+" into " + eventTableName);

        SortedSet<Stock> lockIds = new TreeSet<Stock>();
        for (SymbolEvents se : events) {
            lockIds.add(se.getStock());
        }

        for (Stock stock : lockIds) {
            Lock stockLock = locks.get(stock);
            if (stockLock == null) {
                stockLock = new ReentrantLock();
                locks.put(stock, stockLock);
            } 
            stockLock.lock();
        }

        try {

            DataSourceTransactionManager txManager = (DataSourceTransactionManager) SpringContext.getSingleton().getBean("txManager");

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            TransactionStatus status = txManager.getTransaction(def);

            ArrayList<Query> qInsert = new ArrayList<Query>();
            ArrayList<Validatable> lqUpdate = new ArrayList<Validatable>();
            buildUpdateValidatableList(events, qInsert, lqUpdate);

            List<Validatable> lqRemainingInsert = new ArrayList<Validatable>();
            int[] updated = new int[0];

            try {

                updated = DataSource.getInstance().executeBlockSerializedWithTimeStamp(lqUpdate, DataSource.EVENTS.getUPDATE(eventTableName));

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
                            public int hashCode() {
                                return System.identityHashCode(this);
                            }

                            @Override
                            public boolean equals(Object obj) {
                                return (this == obj);
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

                    DataSource.getInstance().executeBlockSerializedWithTimeStamp(lqRemainingInsert, DataSource.EVENTS.getINSERT(eventTableName));

                } catch (SQLException e) {
                    LOGGER.error(
                            "Pb inserting after updating events :\n" +
                                    "Update request params :\n"+
                                    DataSource.printHugeCollection(lqUpdate)+"\n" +
                                    "Insert request params :\n"+
                                    DataSource.printHugeCollection(lqRemainingInsert)+"\n" +
                                    "Update return was " + 
                                    Arrays.toString(updated), e);
                    throw e;
                }

                txManager.commit(status);

            } catch (SQLException e) {

                txManager.rollback(status);

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

        } finally {

            for (Stock stock : lockIds) {
                Lock stockLock = locks.get(stock);
                if (stockLock != null) stockLock.unlock(); else LOGGER.error(stock+ "is does have a lock!");
            }

        }

        //        //Updating tuned confs ...
        //        lockIds.stream().forEach(s -> {
        //            Set<EventInfo> eventInfos = events.stream()
        //                .filter(es -> es.getStock().equals(s))
        //                .map(es -> es.getDataResultMap().keySet())
        //                .flatMap(rmks -> rmks.stream().map(k -> k.getEventInfo()))
        //                .distinct()
        //                .collect(Collectors.toSet());
        //            eventInfos.stream().forEach(ei -> {
        //                Optional<Date> reduce = events.stream()
        //                .filter(es -> es.getStock().equals(s))
        //                .map(es -> es.getDataResultMap().keySet())
        //                .flatMap(rmks -> rmks.stream().filter(k -> k.getEventInfo().equals(ei)))
        //                .map(ek -> ek.getDate())
        //                .reduce( (rd, ed) -> rd = (rd.compareTo(ed) < 0)?ed:rd);
        //                TunedConf retuneConfig = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(s, eventListName, ei.getEventDefinitionRef());
        //                //TODO update start and end consistently to existing cacls (considering it is a consistent block) and throw errors if not consistent?
        //                retuneConfig.setLastCalculationStart(lastCalculationStart);
        //                retuneConfig.setLastCalculationEnd(lastCalculationEnd);
        //                TunedConfMgr.getInstance().updateConf(retuneConfig, reduce.orElseThrow(() -> new RuntimeException()));
        //            });
        //        });
    }


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

                    @Override
                    public int hashCode() {
                        return System.identityHashCode(this);
                    }

                    @Override
                    public boolean equals(Object obj) {
                        return (this == obj);
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


        List<SymbolEvents> all = (indicators != null)?SymbolEvents.sortList(EventsResources.getInstance().crudReadEvents(date, EventSignalConfig.getNewDate(), indicators, eventListNames), pr):new ArrayList<SymbolEvents>();
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

    public void filterOutCurrentMarketEvents(ShareListInfo... shareLists) {

        Collection<Stock> selectedSharelists = new ArrayList<Stock>();
        for (ShareListInfo shareList : shareLists) {
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

    /**
     * It is assume that if no start and end dates are supplied, this means all events are deleted from the first date to today's date.
     * To bypass TunedConf see {@link #crudDeleteEventsForStockNoTunedConfHandling(Stock, String, Date, Date, EventInfo...) 
     * @param stock
     * @param analysisName
     * @param indicators
     */
    public void crudDeleteEventsForStock(Stock stock, String analysisName, EventInfo... indicators) {
        
        LOGGER.info("DELETE events "+ Arrays.stream(indicators).map(e -> e.getEventDefinitionRef()).reduce((r,e) -> r+","+e)+" for "+analysisName + " and "+stock.getFriendlyName());

        for (EventInfo eventInfo : indicators) {
            if (eventInfo.equals(EventDefinition.PARAMETERIZED)) throw new IllegalArgumentException("Can't directly deal with PARAMETERIZED. Use EventInfo Sub set instead");
        }

        Date datedeb = EventModel.DEFAULT_DATE;
        Date datefin = EventSignalConfig.getNewDate();

        //Cache
        if (isEventCached) {
            synchronized (this) {
                EventsForAnalisysNameCacheHolder eventsForAnalysis = EVENTS_CACHE.get(analysisName);
                if (eventsForAnalysis != null) {
                    removeEventsFromEventsSoftCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
                }
            }
            if (isCachePersistent && !isEventPersisted) {
                DataSource.getInstance().cleanEventsForAnalysisNameNStockNIndicators(EVENTSCACHETABLE, stock, analysisName, datedeb, datefin, indicators);
            }
        }

        //DB
        if (isEventPersisted || !isEventCached) {
            LOGGER.info("Cleaning Events in db, cached is "+isEventCached+", persist is "+isEventPersisted+" other params "+stock+", "+analysisName+", "+datedeb+", "+datefin+", "+EventDefinition.getEventDefArrayAsString(",", indicators));
            DataSource.getInstance().cleanEventsForAnalysisNameNStockNIndicators(EVENTSTABLE, stock, analysisName, datedeb, datefin, indicators);
        }

        //Tuned config
        TunedConfMgr.getInstance().resetTunedConfFor(stock, analysisName, indicators);
    }

    public void crudDeleteEventsForIndicators(String analysisName, EventInfo... indicators) {
        
        LOGGER.info("DELETE events "+ Arrays.stream(indicators).map(e -> e.getEventDefinitionRef()).reduce((r,e) -> r+","+e)+" for "+analysisName);

        Date datedeb = EventModel.DEFAULT_DATE;
        Date datefin = EventSignalConfig.getNewDate();

        for (EventInfo eventInfo : indicators) {
            if (eventInfo.equals(EventDefinition.PARAMETERIZED)) throw new IllegalArgumentException("Can't directly deal with PARAMETERIZED. Use EventInfo Sub set instead");
        }

        //Cache
        if (isEventCached) {
            synchronized (this) {
                EventsForAnalisysNameCacheHolder eventsForAnalysis = EVENTS_CACHE.get(analysisName);
                if (eventsForAnalysis != null) {
                    for (Stock stock : eventsForAnalysis.keySet()) {
                        removeEventsFromEventsSoftCacheFor(stock, eventsForAnalysis, datedeb, datefin, indicators);
                    }
                }
            }
            if (isCachePersistent && !isEventPersisted) {
                DataSource.getInstance().cleanEventsForAnalysisNameNIndicators(EVENTSCACHETABLE, analysisName, datedeb, datefin, indicators);
            }
        }

        //DB
        if (isEventPersisted || !isEventCached) {
            LOGGER.info("Cleaning Events in db, cached is "+isEventCached+", persist is "+isEventPersisted+" other params "+analysisName+", "+datedeb+", "+datefin+", "+EventDefinition.getEventDefArrayAsString(",",indicators));
            DataSource.getInstance().cleanEventsForAnalysisNameNIndicators(EVENTSTABLE, analysisName, datedeb, datefin, indicators);
        }

        TunedConfMgr.getInstance().resetTunedConfFor(analysisName, indicators);
    }

    public void crudDeleteEventsForAnalysisName(String analysisName) {
        
        LOGGER.info("DELETE events for "+analysisName);

        //Cache
        if (isEventCached) {
            synchronized (this) {
                EVENTS_CACHE.remove(analysisName);
            }
            if (isCachePersistent && !isEventPersisted) {
                DataSource.getInstance().cleanEventsForAnalysisName(EVENTSCACHETABLE, analysisName);
            }
        }

        //DB
        if (isEventPersisted || !isEventCached) {
            LOGGER.info("Cleaning Events in db, cached is "+isEventCached+", persist is "+isEventPersisted);
            DataSource.getInstance().cleanEventsForAnalysisName(EVENTSTABLE, analysisName);
        }

        TunedConfMgr.getInstance().resetTunedConfFor(analysisName);
    }

    public void cleanPersistedEventsCache() throws SQLException {

        Query drop = new Query("drop table IF EXISTS "+EVENTSCACHETABLE);
        DataSource.getInstance().executeUpdate(drop, 600);

        Query create = new Query(
                "CREATE TABLE `"+EVENTSCACHETABLE+"` ("+
                        "`DATE` datetime NOT NULL,"+
                        "`SYMBOL` varchar(20) NOT NULL,"+
                        "`ISIN` varchar(20) NOT NULL,"+
                        "`EVENTDEF` varchar(100) NOT NULL,"+
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

    private void removeEventsFromEventsSoftCacheFor(Stock stock, EventsForAnalisysNameCacheHolder eventsForAnalysis, Date startDate, Date endDate, EventInfo... indicators) {

        List<EventInfo> indicatorsList = Arrays.asList(indicators);
        if (eventsForAnalysis != null) {
            eventsForAnalysis.deleteEventsFromStockSoftCache(stock, startDate, endDate, indicatorsList);
        }
    }


    public void setIsCachePersistent(Boolean isCachePersistent) {
        this.isCachePersistent = isCachePersistent;
    }


}
