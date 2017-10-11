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

import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
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
public class EventModel<T extends EventModelStrategyEngine<X>, X> {

	protected static MyLogger LOGGER = MyLogger.getLogger(PropertyChangeListener.class);
	

	private OTFTuningFinalizer tuningFinalizer;
	private Observer tuningResObs;
	
	private ArrayList<String> analysisList;
	private T eventRefreshStrategyEngine;
	private Set<Observer> engineObservers;
	
	X rootViewParam;
	Collection<? extends Object>[] otherViewParams;
	
	
	static Map<Stock, Map<EventInfo, EventDefCacheEntry>> outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
	static Long eventInfoChangeStamp = 0l;

	static class EventDefCacheEntry {
		
		SortedMap<Date, double[]> outputMap;
		UpdateStamp updateStamp;
		
		public EventDefCacheEntry(SortedMap<Date, double[]> outputMap, UpdateStamp updateStamp) {
			super();
			this.outputMap = outputMap;
			this.updateStamp = updateStamp;
		}

		public SortedMap<Date, double[]> getOutputMap() {
			return outputMap;
		}

		public UpdateStamp getUpdateStamp() {
			return updateStamp;
		}
	}
	
	enum OutStampState {OK,FAILING,DIRTY,EMPTY};
	
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

		public boolean isInvalid() {
			return this.outputState.equals(OutStampState.DIRTY) || this.outputState.equals(OutStampState.EMPTY);
		}

        public OutStampState getOutputState() {
            return outputState;
        }
	}

	public static Date DEFAULT_DATE;
	static {
		DEFAULT_DATE = new Date(0);
		try {
			DEFAULT_DATE = new SimpleDateFormat("yyyy/MM/dd").parse("1970/01/01");
		} catch (ParseException e1) {
			LOGGER.error("Shouldn't be here", e1);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<Class<? extends EventModelStrategyEngine>, EventModel> models = new HashMap<Class<? extends EventModelStrategyEngine>, EventModel>();

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends EventModelStrategyEngine<X>, X> EventModel<T,X> getInstance(T modelStrategyEngine, Observer... modelObserver)  {
		
		try {
			EventModel eventModel = models.get(modelStrategyEngine.getClass());
			if (eventModel == null) {
				eventModel = new EventModel<T,X>(modelStrategyEngine);	
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
		
		for (Stock stock : outputCache.keySet()) {
			Map<EventInfo, EventDefCacheEntry> cache4Stock = outputCache.get(stock);
			if (cache4Stock != null) {
				EventDefCacheEntry eventDefCacheEntry = cache4Stock.get(eventInfo);
				if (eventDefCacheEntry != null) {
					UpdateStamp updateStamp = eventDefCacheEntry.getUpdateStamp();
					if (updateStamp != null) {
						updateStamp.setDirty();
					}
				}
			}
		}
		
	}
	
	public static Long updateEventInfoStamp() {
		eventInfoChangeStamp = new Date().getTime();
		return eventInfoChangeStamp;
	}

	
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
		
		this.resetOtherViewParams();
		
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
	 * Callback for last analyse.
	 * 		
	 * @author Guillaume Thoreton
	 * @param endAnalysisDate 
	 * @throws NotEnoughDataException 
	 * @throws IncompleteDataSetException 
	 */
	//Output can still be available if all events have not been calculated successfully
	//However bounds date won't be valid and recorded until all events are successfully calculated.
	//Hence the user will systematically be asked for recalculation if not all events are valid.
	public synchronized void callbackForlastAnalyse(Date startAnalyseDate, Date endAnalysisDate) throws NotEnoughDataException {
		eventRefreshStrategyEngine.callbackForAnalysis(analysisList, startAnalyseDate, endAnalysisDate, engineObservers, rootViewParam, otherViewParams);
	}
	
	public void callbackForAlerts() throws InterruptedException {
		eventRefreshStrategyEngine.callbackForAlerts(engineObservers, rootViewParam, otherViewParams);
		
	}

	/**
	 * Callback for last list fetch.
	 * 
	 * @author Guillaume Thoreton
	 * @throws HttpException 
	 */
	public synchronized void callbackForlastListFetch() throws HttpException {
		eventRefreshStrategyEngine.callbackForStockListFetch(engineObservers, rootViewParam, otherViewParams);		
	}
	
	public synchronized void callbackForReco() {
		eventRefreshStrategyEngine.callbackForReco(engineObservers);
	}

	/**
	 * Callback forlast quotation fetch.
	 * 
	 * @author Guillaume Thoreton
	 * @param endAnalysisDate 
	 * @param startAnalyseDate 
	 * @throws QuotationUpdateException 
	 */
	public synchronized void callbackForlastQuotationFetch(Date startAnalyseDate, Date endAnalysisDate) throws QuotationUpdateException {
		eventRefreshStrategyEngine.callbackForQuotationFetch(engineObservers, startAnalyseDate, endAnalysisDate, rootViewParam, otherViewParams);
	}
	
	public void callbackForAnalysisClean() {
		eventRefreshStrategyEngine.callbackForAnalysisClean(engineObservers, rootViewParam, otherViewParams);
		
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
	
	public X getViewParamRoot() {
		return this.rootViewParam;
	}
	
	public void setViewParamRoot(X rootParam) {
		this.rootViewParam = rootParam;
	}
	
	public void setViewParam(int paramIdx, Collection<? extends Object> otherParams) {
		if (this.otherViewParams == null) {
			resetOtherViewParams();
		}
		this.otherViewParams[paramIdx] = (otherParams != null)?new ArrayList<Object>(otherParams):null;
	}


	protected void checkNumberOfOtherParams(@SuppressWarnings("unchecked") Collection<? extends Object>... otherViewParams) {
		int otherViewParamLength = eventRefreshStrategyEngine.otherViewParamLength();
		if ( (otherViewParamLength > 0 && otherViewParams == null) || (otherViewParamLength != -1 && otherViewParamLength != otherViewParams.length)) throw new IllegalArgumentException("Expecting "+otherViewParamLength+" parameter  but have "+otherViewParams);
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
		
		SortedMap<Date, double[]> outputMap = new TreeMap<Date, double[]>();
		EventDefCacheEntry eventDefCacheEntry = null;
		if (stockMap != null && (eventDefCacheEntry = stockMap.get(eventDefinition)) != null) {
			outputMap = eventDefCacheEntry.getOutputMap();
		}
		
		try {
			
			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(start, end, stock, IndicatorCalculationServiceMain.UI_ANALYSIS, outputMap, eventDefinition, tuningResObs, true);
			if (LOGGER.isDebugEnabled()) {
				try {
					tuningFinalizer.exportConfigRating(IndicatorCalculationServiceMain.UI_ANALYSIS, tuningRes, start, end, tuningFinalizer.calculateRating(tuningRes));
				} catch (Exception e) {
					LOGGER.warn(e,e);
				}
			}
			return tuningRes;
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn(e, e);
			return null;
		}
		
	}
	
	public Boolean cacheNeedsUpdateCheck(Stock stock, Date start, Date end, Set<EventInfo> notUpToDateEventInfos, Calendar minDate, EventInfo ...eventInfos) {
		
		if (eventInfos.length == 0) return false;
		
		Map<EventInfo, EventDefCacheEntry> cacheEntry4Stock = outputCache.get(stock);
		if (cacheEntry4Stock == null) {
			notUpToDateEventInfos.addAll(Arrays.asList(eventInfos));
			return true;
		}
		
		Date adjustStartDate = TunedConfMgr.getInstance().adjustStartDate(stock);
		
		if (start.before(adjustStartDate)) {
			start = adjustStartDate;
			minDate.setTime(adjustStartDate);
		}
		
		Boolean needsUpdate = false;
		for (EventInfo eventInfo : eventInfos) {
			EventDefCacheEntry cacheEntry = cacheEntry4Stock.get(eventInfo);
			if (cacheEntry == null || cacheEntry.getOutputMap() == null || cacheEntry.getUpdateStamp().isInvalid() || start.before( cacheEntry.getUpdateStamp().start) || end.after(cacheEntry.getUpdateStamp().end)) {
				LOGGER.info(
				        "Events : "+ eventInfo.getEventReadableDef()+" needs update : time stamp is "+
				        ( (cacheEntry == null || cacheEntry.getOutputMap() == null)?
				                "null": 
				                cacheEntry.getUpdateStamp().start+" to "+ cacheEntry.getUpdateStamp().end+" and is "+cacheEntry.getUpdateStamp().getOutputState()
				        )+
				        ". Requested is "+start + " to "+end);
				notUpToDateEventInfos.add(eventInfo);
				needsUpdate = true;
			}
		}
		
		return needsUpdate;
		
	}
	
	
	public int[] viewParamPositionsFor(TaskId taskId) {
		return eventRefreshStrategyEngine.otherViewParamPositionsFor(taskId);
	}

	@SuppressWarnings("unchecked")
	public void resetOtherViewParams() {
		this.otherViewParams = (eventRefreshStrategyEngine.otherViewParamLength() != -1)?new Collection[eventRefreshStrategyEngine.otherViewParamLength()]:null;
	}


	public boolean allowsTaskReset() {
		return eventRefreshStrategyEngine.allowsTaskReset();
	}
	
} 
