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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
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
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpException;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.events.calculation.parametrizedindicators.EventDefDescriptorDynamic;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
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
	
	private static final String OUTPUT_CACHE_PATH = System.getProperty("installdir") + File.separator + ".output_cache";
	static {
		File outputCacheFolder = new File(OUTPUT_CACHE_PATH);
		if(!outputCacheFolder.exists()) outputCacheFolder.mkdirs();
	}

	private OTFTuningFinalizer tuningFinalizer;
	private Observer tuningResObs;

	private ArrayList<String> analysisList;
	private T eventRefreshStrategyEngine;
	private Set<Observer> engineObservers;

	protected X rootViewParam;
	protected Collection<? extends Object>[] otherViewParams;

	static Map<Stock, SoftReference<Map<EventInfo, EventDefCacheEntry>>> outputCache = new HashMap<Stock, SoftReference<Map<EventInfo, EventDefCacheEntry>>>();
	//static Map<Stock, Map<EventInfo, EventDefCacheEntry>> outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
	public static Map<EventInfo, EventDefCacheEntry> getOutputCache(Stock stock) {
		if (outputCache.get(stock) == null) {
			return getFromFileCache(stock);
		} else {
			Map<EventInfo, EventDefCacheEntry> map = outputCache.get(stock).get();
			if (map == null) {
				return getFromFileCache(stock); //XXX This will return all event info, this should return only event info for a required list..
			}
			return map;
		}
		//return outputCache.get(stock);
	}
	private static Map<EventInfo, EventDefCacheEntry> getFromFileCache(Stock stock) {
		String fileNamePattern = stock.getSymbol() + "%%" +  stock.getIsin()  + "%%" + ".*" + "%%" + SelectedIndicatorsCalculationService.getAnalysisName();
		File dir = new File(OUTPUT_CACHE_PATH);
		final File[] dataFiles = dir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(final File dir, final String name) {
		        return name.matches(fileNamePattern + "\\.csv");
		    }
		});
		Map<EventInfo, EventDefCacheEntry> map = new HashMap<>();
		for (final File dataFile : dataFiles) {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile))) {
				String line;
				String[] fileNameSplit = dataFile.getName().split("%%");
				//Stock fileNameSplit[0-1]
				//"%%" + ei.getEventDefinitionRef() + "%%" + ei.getEventDefinitionRef() + "%%" + SelectedIndicatorsCalculationService.getAnalysisName() + 
				String eventDefinitionRef = fileNameSplit[2];
				ParameterizedIndicatorsBuilder parameterizedIndiactorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);
				EventInfo eventInfo = (EventInfoOpsCompoOperation) parameterizedIndiactorsBuilder.getCurrentOperations().get(eventDefinitionRef);
				if (eventInfo != null) {
					//descriptor
					try(
						FileInputStream streamIn = new FileInputStream(dataFile.getAbsolutePath().replaceAll("\\.csv", ".ser"));
						ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);) {
						EventDefDescriptorDynamic evtDesrc = (EventDefDescriptorDynamic) objectinputstream.readObject();
						((EventDefDescriptorDynamic) eventInfo.getEventDefDescriptor()).setChartedOutputGroups(evtDesrc.getChartedOutputGroups(), null);
					} catch (Exception e) {
						LOGGER.warn("Could not read event info descriptor from cache for " + eventInfo + ": " + e);
					}
					//data
					SortedMap<Date, double[]> outputMap = new TreeMap<>();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					while((line = bufferedReader.readLine()) != null) {
						String[] split = line.split(",");
						double[] dbles = Arrays.asList(split).subList(1, split.length).stream().map(s -> Double.valueOf(s)).mapToDouble(Double::doubleValue).toArray();
						outputMap.put(df.parse(split[0]), dbles);
					}
					map.put(eventInfo, new EventDefCacheEntry(outputMap, new UpdateStamp(outputMap.firstKey(), outputMap.lastKey(), false, null)));
				} else {
					throw new RuntimeException("Event info, " + eventDefinitionRef + ", does not exists anymore for cached file: " + dataFile.getAbsolutePath());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (!map.isEmpty()) {
			EventModel.outputCache.put(stock, new SoftReference<>(map));
			return map;
		}
		return null;
	}
	public static void resetOutputCache() {
		EventModel.outputCache = new HashMap<Stock, SoftReference<Map<EventInfo, EventDefCacheEntry>>>();
		resetFileCache();
		//EventModel.outputCache = new HashMap<Stock, Map<EventInfo, EventDefCacheEntry>>();
	}
	private static void resetFileCache() {
		File dir = new File(OUTPUT_CACHE_PATH);
		final File[] files = dir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(final File dir, final String name) {
		        return name.matches(".*\\.csv") || name.matches(".*\\.ser");
		    }
		});
		for (final File file : files) {
		    if (!file.delete()) {
		    	String message = "Can't remove " + file.getAbsolutePath();
				LOGGER.error(message);
		    	throw new RuntimeException(message);
		    }
		}
	}
	public static void putOutputCache(Stock stock, Map<EventInfo, EventDefCacheEntry> eventInfosforStock) {
		EventModel.outputCache.put(stock, new SoftReference<>(eventInfosforStock));
		putInFileCache(stock, eventInfosforStock);
		//EventModel.outputCache.put(stock,callbackForlastAnalyseOutput.get(stock));
	}
	//EVENTDEF, EVENTDEFEXTENSION, ANALYSENAME => eventValue.getEventDef(), eventKey.getEventInfoExtra(), eventValue.getEventListName()
	private static String filename(Stock stock, EventInfo ei) {
		return stock.getSymbol() + "%%" + stock.getIsin() +  "%%" + ei.getEventDefinitionRef() + "%%" + ei.getEventDefinitionRef() + "%%" + SelectedIndicatorsCalculationService.getAnalysisName();
	}
	private static void putInFileCache(Stock stock, Map<EventInfo, EventDefCacheEntry> map) {
		map.keySet().stream()
		.filter(ei -> OutStampState.OK.equals(map.get(ei).getUpdateStamp().getOutputState()))
		.forEach(ei -> {
			String fileName = filename(stock, ei);
			
			String descrFileName = fileName + ".ser";
			try (FileOutputStream fout = new FileOutputStream(OUTPUT_CACHE_PATH + File.separator + descrFileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);) {
				oos.writeObject(ei.getEventDefDescriptor());
			} catch (Exception e) {
				LOGGER.warn("Error writing into cache: " + e);
			}
			
			String dataFileName = fileName + ".csv";
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_CACHE_PATH + File.separator + dataFileName))) {
				map.get(ei).getOutputMap().entrySet().forEach(entry -> {
					try {
						bufferedWriter.write(df.format(entry.getKey()) + Arrays.stream(entry.getValue()).mapToObj(d -> d).reduce("", (a, d) -> a + "," + d, (a, b) -> a + b));
						bufferedWriter.newLine();
					} catch (IOException e) {
						LOGGER.warn("Error writing into cache: " + e);
					}
				});
			} catch (Exception e) {
				LOGGER.warn("Error writing into cache: " + e);
			}
		});
	}
	public static void dirtyCacheFor(EventInfo eventInfo, Boolean clearHardCache) {
		for (Stock stock : outputCache.keySet()) {
			Map<EventInfo, EventDefCacheEntry> cache4Stock = getOutputCache(stock);
			if (cache4Stock != null) {
				EventDefCacheEntry eventDefCacheEntry = cache4Stock.get(eventInfo);
				if (eventDefCacheEntry != null) {
					UpdateStamp updateStamp = eventDefCacheEntry.getUpdateStamp();
					if (updateStamp != null) {
						updateStamp.setDirty();
					}
					if (clearHardCache) removeFromFileCache(stock, eventInfo);
				}
			}
		}
	}
	private static void removeFromFileCache(Stock stock, EventInfo ei) {
		String fileName = filename(stock, ei);
		File descrFile = new File(OUTPUT_CACHE_PATH + File.separator + fileName + ".ser");
		descrFile.delete();
		File dataFile = new File(OUTPUT_CACHE_PATH + File.separator + fileName + ".csv");
		dataFile.delete();
	}

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
		private Optional<String> cause;

		public UpdateStamp(Date start, Date end, Boolean isFailing, String cause) {
			super();
			this.start = start;
			this.end = end;
			this.outputState = (isFailing)?OutStampState.FAILING:OutStampState.OK;
			this.cause = Optional.ofNullable(cause);
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

		public String getCause() {
			return cause.orElse("None");
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
		this.setLastAnalyse(DateFactory.DEFAULT_DATE);
		this.setLastQuotationFetch(DateFactory.DEFAULT_DATE);

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
	public void resetAnalysisList() {
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
		eventRefreshStrategyEngine.setLastAnalyse(DateFactory.DEFAULT_DATE, null);
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
		Map<EventInfo, EventDefCacheEntry> stockMap = getOutputCache(stock);
		if (stockMap != null) {
			EventDefCacheEntry stockNeventDefCacheEntry = stockMap.get(eventDefinition);
			if (stockNeventDefCacheEntry != null) return stockNeventDefCacheEntry.getOutputMap();
		}
		return null;
	}

	public TuningResDTO updateTuningRes(Stock stock, EventInfo eventDefinition, SortedMap<EventKey, EventValue> evtDefEvents, Date start, Date end) {
		try {

			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(start, end, stock, SelectedIndicatorsCalculationService.getAnalysisName(), eventDefinition, evtDefEvents, tuningResObs);
			return tuningRes;

		} catch (NotEnoughDataException e) {
			LOGGER.warn(e);
			return null;
		}

	}

	public Boolean cacheNeedsUpdateCheck(Stock stock, Date start, Date end, Set<EventInfo> notUpToDateEventInfos, Calendar minDate, EventInfo ...eventInfos) throws NotEnoughDataException {

		if (eventInfos.length == 0) return false;

		Map<EventInfo, EventDefCacheEntry> cacheEntry4Stock = getOutputCache(stock);
		if (cacheEntry4Stock == null) {
			notUpToDateEventInfos.addAll(Arrays.asList(eventInfos));
			return true;
		}

		Date adjustStartDate = TunedConfMgr.getInstance().minimumStartDate(stock);

		if (start.before(adjustStartDate)) {
			start = adjustStartDate;
			minDate.setTime(adjustStartDate);
		}

		Boolean needsUpdate = false;
		for (EventInfo eventInfo : eventInfos) {
			EventDefCacheEntry cacheEntry = cacheEntry4Stock.get(eventInfo);
			if (cacheEntry == null || cacheEntry.getOutputMap() == null || cacheEntry.getUpdateStamp().isInvalid() || start.before(cacheEntry.getUpdateStamp().start) || end.after(cacheEntry.getUpdateStamp().end)) {
				LOGGER.info(
						"Events: " + eventInfo.getEventReadableDef() + " needs update: time stamp is " +
								( (cacheEntry == null || cacheEntry.getOutputMap() == null)?
										"null": cacheEntry.getUpdateStamp().start + " to " + cacheEntry.getUpdateStamp().end + " and is " + cacheEntry.getUpdateStamp().getOutputState()
								) +
								". Requested is " + start + " to " + end);
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
