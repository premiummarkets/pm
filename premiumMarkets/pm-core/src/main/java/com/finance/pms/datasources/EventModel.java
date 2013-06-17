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
package com.finance.pms.datasources;

import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.scoring.OTFTuningFinalizer;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.events.scoring.dto.TuningResDTO;

/**
 * The Class EventRefreshModel.
 * 
 * @author Guillaume Thoreton
 */
public class EventModel<T extends EventModelStrategyEngine> {

	protected static MyLogger LOGGER = MyLogger.getLogger(PropertyChangeListener.class);
	

	private OTFTuningFinalizer tuningFinalizer;
	private Observer tuningResObs;
	
	private ArrayList<String> analysisList;
	private T eventRefreshStrategyEngine;
	private Set<Observer> engineObservers;
	private Object[] viewStateParams;
	
	private static Map<Stock, Map<EventInfo, EventDefCacheEntry>> outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
	private static Map<Stock, Map<EventInfo, UpdateStamp>> cacheTimeStamps = new HashMap<Stock, Map<EventInfo, UpdateStamp>>();
	public static Long eventInfoChangeStamp = 0l;

	static class EventDefCacheEntry {
		
		SortedMap<Date, double[]> outputMap;
		
		public EventDefCacheEntry(SortedMap<Date, double[]> outputMap) {
			super();
			this.outputMap = outputMap;
		}

		public SortedMap<Date, double[]> getOutputMap() {
			return outputMap;
		}
	}
	
	enum OutStampState {OK,FAILING, DIRTY};
	
	public static class UpdateStamp {
		private Date start;
		private Date end;
		private OutStampState outputState;
		
		public UpdateStamp(Date start, Date end, Boolean isFailing) {
			super();
			this.start = start;
			this.end = end;
			this.outputState = (isFailing)?OutStampState.FAILING:OutStampState.OK;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((end == null) ? 0 : end.hashCode());
			result = prime * result + ((start == null) ? 0 : start.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UpdateStamp other = (UpdateStamp) obj;
			if (end == null) {
				if (other.end != null)
					return false;
			} else if (!end.equals(other.end))
				return false;
			if (start == null) {
				if (other.start != null)
					return false;
			} else if (!start.equals(other.start))
				return false;
			return true;
		}

		public Boolean isDirty() {
			return outputState.equals(OutStampState.DIRTY);
		}
		
		public Boolean isFailing() {
			return outputState.equals(OutStampState.FAILING);
		}

		public void setDirty() {
			this.outputState = OutStampState.DIRTY;
		}
	}

	public static Date DEFAULT_DATE;
	static {
		DEFAULT_DATE = new Date();
		try {
			DEFAULT_DATE = new SimpleDateFormat("yyyy/MM/dd").parse("1970/01/01");
		} catch (ParseException e1) {
			LOGGER.error("Shouldn't be here",e1);
		}
	}
	
	public static Map<Class<? extends EventModelStrategyEngine>, EventModel<? extends EventModelStrategyEngine>> models = new HashMap<Class<? extends EventModelStrategyEngine>, EventModel<? extends EventModelStrategyEngine>>();

	
	@SuppressWarnings("unchecked")
	public static <T extends EventModelStrategyEngine> EventModel<T> getInstance(T modelStrategyEngine, Observer... modelObserver)  {
		
		try {
			EventModel<T> eventModel = (EventModel<T>) models.get(modelStrategyEngine.getClass());
			if (eventModel == null) {
				eventModel = new EventModel<T>(modelStrategyEngine);	
				models.put(modelStrategyEngine.getClass(), eventModel);
			}
			
			if (modelObserver != null) {
				for (Observer observer : modelObserver) {
					eventModel.addEngineObserver(observer);
				}
			}
			
			return eventModel;
			
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw new RuntimeException(e);
		}
		
	}
	
	
	public static void dirtyCacheFor(EventInfo eventInfo) {
		
		for (Stock stock : cacheTimeStamps.keySet()) {
			Map<EventInfo, UpdateStamp> stockTimeStamps = cacheTimeStamps.get(stock);
			if (stockTimeStamps != null) {
				UpdateStamp updateStamp = stockTimeStamps.get(eventInfo);
				if (updateStamp != null) {
					updateStamp.setDirty();
				}
			}
		}
		
	}
	
	/**
	 * Instantiates a new event refresh model.
	 * 
	 * @param startAnalyseDate the start analyse date
	 * 
	 * @author Guillaume Thoreton
	 * @param modelStrategyEngine 
	 */
	private EventModel(T modelStrategyEngine) {
		
		tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
		tuningResObs = new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				//TODO?
			}
		};

		this.analysisList = new ArrayList<String>();	
		this.eventRefreshStrategyEngine = modelStrategyEngine;
		this.engineObservers = new HashSet<Observer>();
		
	}
	
	/**
	 * Hard reset last quotation date.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void hardResetLastQuotationDate() {
		this.setLastAnalyse(EventModel.DEFAULT_DATE);
		this.setLastQuotationFetch(EventModel.DEFAULT_DATE);
	
	}


	/**
	 * Callback forlast analyse.
	 * 		
	 * @author Guillaume Thoreton
	 * @throws NotEnoughDataException 
	 * @throws IncompleteDataSetException 
	 */
	//Output can still be available if all events have not been calculated successfully
	//However bounds date won't be valid and recorded until all events are successfully calculated.
	//Hence the user will systematically be asked for recalculation if all events are not valid.
	public synchronized void callbackForlastAnalyse(Date startAnalyseDate) throws NotEnoughDataException {
		
		Map<Stock, Map<EventInfo, EventDefCacheEntry>> callbackForlastAnalyseOutput = eventRefreshStrategyEngine.callbackForlastAnalyse(analysisList, startAnalyseDate, engineObservers, viewStateParams);
		
		if (callbackForlastAnalyseOutput != null) {
			
			//Update cache
			outputCache.putAll(callbackForlastAnalyseOutput);

			//Update cache bounds records
			Date datedeb = DateFactory.midnithDate(startAnalyseDate);
			Date datefin = DateFactory.midnithDate(EventSignalConfig.getNewDate());
			Set<EventInfo> indicators = EventDefinition.loadMaxPassPrefsEventInfo();
			
			Boolean isAllEventsOk = true;
			String msg = "";
			for (Stock stock : callbackForlastAnalyseOutput.keySet()) {
				//update time stamp cache
				Boolean isAllEventsOkForStock = true;
				for (EventInfo eventDefinition : indicators) {
					EventDefCacheEntry evtDefRes = callbackForlastAnalyseOutput.get(stock).get(eventDefinition);
					Map<EventInfo, UpdateStamp> timeStamps4Stock = cacheTimeStamps.get(stock);
					if (timeStamps4Stock == null) {
						timeStamps4Stock = new HashMap<EventInfo, EventModel.UpdateStamp>();
						cacheTimeStamps.put(stock, timeStamps4Stock);
					}
					Boolean isFailing = false;
					if (evtDefRes == null) {
						msg = msg + "'" + eventDefinition.getEventReadableDef() + "' is failing for " + stock.getFriendlyName() + "\n";
						isAllEventsOkForStock = false;
						isFailing = true;
					} 
					timeStamps4Stock.put(eventDefinition, new UpdateStamp(datedeb, datefin, isFailing));
				}
				isAllEventsOk = isAllEventsOk && isAllEventsOkForStock;
			}
			
			if (!isAllEventsOk) throw new RuntimeException("\nCause : "+msg);
			
		}
	}
	
	public void callbackForAlerts() throws InterruptedException {
		eventRefreshStrategyEngine.callbackForAlerts(engineObservers, viewStateParams);
		
	}

	/**
	 * Callback forlast list fetch.
	 * 
	 * @author Guillaume Thoreton
	 * @throws HttpException 
	 */
	public synchronized void callbackForlastListFetch() throws HttpException {
		eventRefreshStrategyEngine.callbackForlastListFetch(engineObservers, viewStateParams);		
	}
	
	public synchronized void callbackForReco() {
		eventRefreshStrategyEngine.callbackForReco(engineObservers);
	}

	/**
	 * Callback forlast quotation fetch.
	 * 
	 * @author Guillaume Thoreton
	 * @throws StockNotFoundException 
	 */
	public synchronized void callbackForlastQuotationFetch() throws StockNotFoundException {
		eventRefreshStrategyEngine.callbackForlastQuotationFetch(engineObservers, viewStateParams);
	}
	
	public void callbackForAnalysisClean() {
		
		Boolean deleteAll = eventRefreshStrategyEngine.callbackForAnalysisClean(engineObservers, viewStateParams);
		if (deleteAll) {
			outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
			cacheTimeStamps = new HashMap<Stock, Map<EventInfo,UpdateStamp>>();
		} else {
			for (Object stock : viewStateParams) {
				outputCache.remove((Stock)stock);
				cacheTimeStamps.remove((Stock)stock);
			}
		}
		
	}

	/**
	 * Gets the last analyse.
	 * 
	 * @return the last analyse
	 */
	public Date getLastAnalyse() {
		return eventRefreshStrategyEngine.getLastAnalyse(null);
	}

	/**
	 * Gets the last list fetch.
	 * 
	 * @return the last list fetch
	 */
	public Date getLastListFetch() {
		return eventRefreshStrategyEngine.getLastListFetch(null);
	}
	
	/**
	 * Gets the last quotation fetch.
	 * 
	 * @return the last quotation fetch
	 */
	public Date getLastQuotationFetch() {
		return eventRefreshStrategyEngine.getLastQuotationFetch(null);
	}

	/**
	 * Sets the last analyse.
	 * 
	 * @param lastAnalyse the new last analyse
	 */
	public void setLastAnalyse(Date lastAnalyse) {
		eventRefreshStrategyEngine.setLastAnalyse(lastAnalyse, null);
	}

	/**
	 * Sets the last list fetch.
	 * 
	 * @param lastListFetch the new last list fetch
	 */
	public void setLastListFetch(Date lastListFetch) {
		eventRefreshStrategyEngine.setLastListFetch(lastListFetch, null);
	}

	/**
	 * Sets the last quotation fetch.
	 * 
	 * @param lastQuotationFetch the new last quotation fetch
	 */
	public void setLastQuotationFetch(Date lastQuotationFetch) {
		eventRefreshStrategyEngine.setLastQuotationFetch(lastQuotationFetch, null);
	}

	
	/**
	 * Reset analisys list.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void resetAnalisysList() {
		analysisList.clear();
	}

	
	/**
	 * Gets the analisys list.
	 * 
	 * @return the analisys list
	 */
	public ArrayList<String> getAnalysisList() {
		return analysisList;
	}
	

	/**
	 * Reset last analyse.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void resetLastAnalyse() {
		eventRefreshStrategyEngine.setLastAnalyse(EventModel.DEFAULT_DATE, null);
	}
	
	
	/**
	 * Sets the model action engine.
	 * 
	 * @param eventModelEngine the new model action engine
	 */
	public void setModelActionEngine(T eventModelEngine) {
		this.eventRefreshStrategyEngine = eventModelEngine;
	}
	
	public void addEngineObserver(Observer engineObserver) {
		this.engineObservers.add(engineObserver);
	}

	public Set<Observer> getEngineObservers() {
		return engineObservers;
	}

	public synchronized void setViewStateParams(Object... viewStateParams) {
		this.viewStateParams = viewStateParams;	
	}

	public Object[] getViewStateParams() {
		return viewStateParams;
	}
	
	public SortedMap<Date, double[]> getOutputCache(Stock stock, EventInfo eventDefinition) {
		Map<EventInfo, EventDefCacheEntry> stockMap = outputCache.get(stock);
		if (stockMap != null) {
			EventDefCacheEntry stockNeventDefCacheEntry = stockMap.get(eventDefinition);
			if (stockNeventDefCacheEntry != null) return stockNeventDefCacheEntry.getOutputMap();
		}
		return null;
	}
	
	public TuningResDTO updateTuningRes(Stock stock, EventInfo eventDefinition, Date start, Date end) {
		Map<EventInfo, EventDefCacheEntry> stockMap = outputCache.get(stock);
		EventDefCacheEntry eventDefCacheEntry = null;
		if (stockMap != null && (eventDefCacheEntry = stockMap.get(eventDefinition)) != null) {
			try {
				return tuningFinalizer.buildTuningRes(start, end, stock, IndicatorCalculationServiceMain.UI_ANALYSIS, eventDefCacheEntry.getOutputMap(), false, eventDefinition, tuningResObs, true);
			} catch (NotEnoughDataException e) {
				LOGGER.warn(e,e);
				return null;
			}
		}
		return null;
		
	}
	
	public Boolean cacheNeedsUpdateCheck(Stock stock, Date start, Date end, Map<EventInfo, UpdateStamp> notUptoDateStamps, Calendar minDate, EventInfo ...eventInfos) {
		
		if (eventInfos.length == 0) return false;
		
		Map<EventInfo, UpdateStamp> updateStamps = cacheTimeStamps.get(stock);
		if (updateStamps == null) {
			return true;
		}
		
		Date adjustStartDate = TunedConfMgr.adjustStartDate(stock);
		
		if (start.before(adjustStartDate)) {
			start = adjustStartDate;
			minDate.setTime(adjustStartDate);
		}
		
		Boolean needsUpdate = false;
		for (EventInfo eventInfo : eventInfos) {
			UpdateStamp updateStamp = updateStamps.get(eventInfo);
			boolean isOutSidePrevCalcPeriod = start.before(updateStamp.start) || end.after(updateStamp.end);
			if (updateStamp == null || updateStamp.isDirty() || isOutSidePrevCalcPeriod) {
				notUptoDateStamps.put(eventInfo, updateStamp);
				needsUpdate = true;
			}
		}
		
		return needsUpdate;
		
	}
	
	public static Long updateEventInfoStamp() {
		eventInfoChangeStamp = new Date().getTime();
		return eventInfoChangeStamp;
	}
	
	
} 
