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
package com.finance.pms.datasources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.http.HttpException;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.EventModel.EventDefCacheEntry;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.conditional.EventConditionHolder;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Interface EventRefreshModelDelegate.
 * 
 * @author Guillaume Thoreton
 */
public abstract class EventModelStrategyEngine<X> {

	/**
	 * Sets the last list fetch.
	 * 
	 * @param newLastListFetch the new last list fetch
	 * @param oldLastListFetch the old last list fetch
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastListFetch(Date newLastListFetch, Date oldLastListFetch);
	public abstract Date getLastListFetch(Date oldLastListFetch);

	/**
	 * Sets the last quotation fetch.
	 * 
	 * @param newLastQuotationFetch the new last quotation fetch
	 * @param oldLastQuotationFetch the old last quotation fetch
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastQuotationFetch(Date newLastQuotationFetch,Date oldLastQuotationFetch);
	public abstract Date getLastQuotationFetch(Date oldLastQuotaitonFetch);

	/**
	 * Sets the last analyse.
	 * 
	 * @param newLastAnalyse the new last analyse
	 * @param oldLastAnalyse the old last analyse
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastAnalyse(Date newLastAnalyse,Date oldLastAnalyse);
	public abstract Date getLastAnalyse(Date oldLastAnalyse);


	public abstract void callbackForlastListFetch(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws HttpException;
	public abstract void callbackForlastQuotationFetch(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws QuotationUpdateException;
	public abstract void callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Date endAnalysisDate, Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws NotEnoughDataException;
	public abstract void callbackForAlerts(Set<Observer> engineObservers,  X rootParam,  @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws InterruptedException;
	public abstract void callbackForAnalysisClean(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams);
	public abstract void callbackForReco(Set<Observer> engineObservers);
	
	public abstract int[] otherViewParamPositionsFor(TaskId taskId);
	public abstract int otherViewParamLength();
	
	public abstract boolean allowsTaskReset();
	
	
	public void postCallBackForClean(boolean deleteAll, Stock... cleanedStocks) {
		
		if (deleteAll) {
			EventModel.outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
			
		} else {
			SortedSet<EventInfo> eventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
			for (Object stock : cleanedStocks) {
				Map<EventInfo, EventDefCacheEntry> outCache4Stock = EventModel.outputCache.get(stock);
				
				if (outCache4Stock != null) {
					for (EventInfo eventInfo : eventDefs) {
						outCache4Stock.remove(eventInfo);
					}
				}
			}
		}
	}
	
	public void postCallBackForAnalysis(Map<Stock, Map<EventInfo, EventDefCacheEntry>> callbackForlastAnalyseOutput) {
		
		if (callbackForlastAnalyseOutput != null) {
			
			//Update cache
			for (Stock stock : callbackForlastAnalyseOutput.keySet()) {
				Map<EventInfo, EventDefCacheEntry> map4Stock = EventModel.outputCache.get(stock);
				if (map4Stock == null) {
					EventModel.outputCache.put(stock, callbackForlastAnalyseOutput.get(stock));
				} else {
					map4Stock.putAll(callbackForlastAnalyseOutput.get(stock));
				}
			}
		

			Set<EventInfo> indicators = EventDefinition.loadMaxPassPrefsEventInfo();
			
			Boolean isAllEventsOk = true;
			String msg = "";
			for (Stock stock : callbackForlastAnalyseOutput.keySet()) {
				
				Boolean isAllEventsOkForStock = true;
				for (EventInfo eventDefinition : indicators) {
					
					EventDefCacheEntry evtDefRes = callbackForlastAnalyseOutput.get(stock).get(eventDefinition);
					if (evtDefRes == null || evtDefRes.getUpdateStamp() == null || evtDefRes.getUpdateStamp().isFailing()) {
						msg = msg + "'" + eventDefinition.getEventReadableDef() + "' is failing for " + stock.getFriendlyName() + "\n";
						isAllEventsOkForStock = false;
					} 
					
				}
				isAllEventsOk = isAllEventsOk && isAllEventsOkForStock;
			}
			
			if (!isAllEventsOk) throw new RuntimeException("\nCause :\n"+msg);
			
		}
	}
	
	//At the moment, the event are filter in three Categories only : 1rst Pass 2nd Pass and a special case for Parameterised (with a  filter)
	//The issues :
	//Neural needs the first pass indicators as in the db.props and hence these can't be individually tampered
	//TODO In the same way parameterised is filtered, Neural could be.
	protected void tamperEventConfig(Collection<EventInfo> viewStateParams) {
		
		if (viewStateParams == null) {//A null value means all ie no filter : we don't tamper.
			return;
		}
		
		EventSignalConfig eventConfig = (EventSignalConfig) ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).clone();
	
		Set<String> indepIndicators = new HashSet<String>();
		SortedSet<EventConditionHolder> filteredParameterised = new TreeSet<EventConditionHolder>();
		for (EventInfo eventInfo : viewStateParams) {
			if (eventConfig.getIndepIndicators().contains(eventInfo)) {
				indepIndicators.add(eventInfo.getEventDefinitionRef());
			}
			if (eventInfo.getEventDefId().equals(EventDefinition.PARAMETERIZED.getEventDefId())) {
				indepIndicators.add(EventDefinition.PARAMETERIZED.name());
				filteredParameterised.add((EventConditionHolder) eventInfo);
			}
		}
	
		eventConfig.setIndepIndicators(new ArrayList<String>(indepIndicators));
		if (!filteredParameterised.isEmpty()) eventConfig.setFilteredParameterised(filteredParameterised);
		ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, eventConfig);
	}


}