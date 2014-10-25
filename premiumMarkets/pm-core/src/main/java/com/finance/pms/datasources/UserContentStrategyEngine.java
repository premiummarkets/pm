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
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel.EventDefCacheEntry;
import com.finance.pms.datasources.EventModel.UpdateStamp;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;

public abstract class UserContentStrategyEngine<X> extends EventModelStrategyEngine<X> {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(UserContentStrategyEngine.class);

	public void callbackForlastListFetch(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) {
		LOGGER.debug("No list update available for this model.");
	}

	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws QuotationUpdateException {
		
		LOGGER.guiInfo("Running task : Updating quotations");
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		LOGGER.debug("Fetching monitored quotations");
		quotationUpdate.addObservers(engineObservers);

		updateQuotations(quotationUpdate, buildStockListFrom(rootParam));
	}

	private void updateQuotations(QuotationUpdate quotationUpdate, List<Stock> stocks) throws QuotationUpdateException {
		quotationUpdate.getQuotesFor(stocks);
	};

	public void callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate, Date endAnalysisDate, Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>...viewStateParams) throws NotEnoughDataException {

		String periodType = MainPMScmd.getMyPrefs().get("events.periodtype", "daily");
		String[] analysers = new String[analysisList.size()];
		for (int j = 0; j < analysers.length; j++) {
			analysers[j] = analysisList.get(j);
		}

		Date datefin = DateFactory.midnithDate(endAnalysisDate);
		Date datedeb = DateFactory.midnithDate(startAnalyseDate);

		List<Stock> stockList = buildStockListFrom(rootParam);

		Map<Stock, Map<EventInfo, EventDefCacheEntry>> outputRet = new HashMap<Stock, Map<EventInfo,EventDefCacheEntry>>();
		if (stockList.size() == 0) throw new NotEnoughDataException(null,"No stock selected. Select a stock or a list of stocks to analyse before running this.", new Throwable());
		
		for (int i = 0; i < analysers.length; i++) {

			LOGGER.guiInfo("Running task : Analysing from "+datedeb+" to "+datefin);
			
			IndicatorsCalculationService analyzer = (IndicatorsCalculationService) SpringContext.getSingleton().getBean(analysers[i]);

			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());

			IndicatorAnalysisCalculationRunnableMessage actionThread = new IndicatorAnalysisCalculationRunnableMessage(
					SpringContext.getSingleton(), 
					analyzer, IndicatorCalculationServiceMain.UI_ANALYSIS, periodType, 
					stockList, datedeb, datefin, engineObservers.toArray(new Observer[0]));

			Integer maxPass = new Integer(MainPMScmd.getMyPrefs().get("event.nbPassMax", "1"));

			//Pass one
			Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne = new HashMap<Stock, Map<EventInfo,EventDefCacheEntry>>();
			try {
				runPassOne = runPassOne(actionThread, datedeb, datefin);
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
			if (runPassOne != null) outputRet.putAll(runPassOne);

			//Pass two
			if (maxPass == 2) {
				Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo = new HashMap<Stock, Map<EventInfo,EventDefCacheEntry>>();
				try {
					runPassTwo = runPassTwo(actionThread, datedeb, datefin);
				} catch (Exception e) {
					LOGGER.error(e,e);
				}
				if (runPassTwo != null) {
					for (Stock stock : runPassTwo.keySet()) {
						Map<EventInfo, EventDefCacheEntry> map4Stock = outputRet.get(stock);
						if (map4Stock == null) {
							outputRet.put(stock, runPassTwo.get(stock));
						} else {
							map4Stock.putAll(runPassTwo.get(stock));
						}
					}
				}
			} 
		}

		postCallBackForAnalysis(outputRet);
		
	}
	
	protected abstract List<Stock> buildStockListFrom(X rootParam);

	public void callbackForReco(Set<Observer> engineObservers) {
		throw new NotImplementedException();
	}

	
	public Date setLastListFetch(Date newLastListFetch, Date oldLastListFetch) {
		return newLastListFetch;
	}

	
	public Date setLastQuotationFetch(Date newLastQuotationFetch, Date oldLastQuotationFetch) {
		return newLastQuotationFetch;
	}

	
	public Date setLastAnalyse(Date newLastAnalyse, Date oldLastAnalyse) {
		return newLastAnalyse;
	}

	
	public Date getLastListFetch(Date oldLastListFetch) {
		return (oldLastListFetch == null)?EventModel.DEFAULT_DATE:oldLastListFetch;
	}

	
	public Date getLastQuotationFetch(Date oldLastQuotationFetch) {
		return (oldLastQuotationFetch == null)?EventModel.DEFAULT_DATE:oldLastQuotationFetch;
	}

	
	public Date getLastAnalyse(Date oldLastAnalyse) {
		return (oldLastAnalyse == null)?EventModel.DEFAULT_DATE:oldLastAnalyse;
	}


	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, X rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>... viewStateParams) {
		
		List<Stock> builtStockList = buildStockListFrom(rootParam);
		for (Observer observer : engineObservers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, builtStockList.size()));
		}
	
		EventInfo[] eventDefsArray = EventDefinition.loadMaxPassPrefsEventInfo().toArray(new EventInfo[0]);
		for (Stock stock : builtStockList) {
			
			LOGGER.guiInfo("Running task : Cleaning previous "+((Stock)stock).getFriendlyName()+" sets of events.");
			
			EventsResources.getInstance().crudDeleteEventsForStock((Stock)stock, IndicatorCalculationServiceMain.UI_ANALYSIS, EventModel.DEFAULT_DATE, EventSignalConfig.getNewDate(), true, eventDefsArray);
			TunedConfMgr.getInstance().getTunedConfDAO().resetTunedConfsFor((Stock) stock);
			
			for (Observer observer : engineObservers) {
				observer.update(null, new ObserverMsg((Stock) stock, ObserverMsg.ObsKey.NONE));
			}
			
		}

		postCallBackForClean(false, builtStockList.toArray(new Stock[0]));
		
	}

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread, Date start, Date end) throws InterruptedException {
		
		Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passTwoOutput;
		try {
			passTwoOutput = actionThread.runIndicatorsCalculationPassTwo(true);
		} catch (IncompleteDataSetException e1) {
			passTwoOutput = e1.getCalculatedOutput();
		}
	
		return finalising(actionThread, passTwoOutput, start, end);
	}

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread, Date start, Date end) throws InterruptedException {
		
		Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passOneOutput;
		try {
			passOneOutput = actionThread.runIndicatorsCalculationPassOne(true , passOneOverwriteMode());
		} catch (IncompleteDataSetException e1) {
			passOneOutput = e1.getCalculatedOutput();
		}
	
		return finalising(actionThread, passOneOutput, start, end);
		
	}

	protected abstract String passOneOverwriteMode();
	
	Map<Stock, Map<EventInfo, EventDefCacheEntry>> finalising(IndicatorAnalysisCalculationRunnableMessage actionThread, Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passOutput, Date start, Date end) {

		Map<Stock, Map<EventInfo, EventDefCacheEntry>> ret = new HashMap<Stock, Map<EventInfo,EventDefCacheEntry>>();
		for (Stock stock : passOutput.keySet()) {
			Map<EventInfo, SortedMap<Date, double[]>> map4Stock = passOutput.get(stock);
			if (map4Stock != null) {
				ret.put(stock, new HashMap<EventInfo, EventModel.EventDefCacheEntry>());
				for (EventInfo evtDef : map4Stock.keySet()) {
					SortedMap<Date, double[]> map4EvtDef = map4Stock.get(evtDef);
					if (map4EvtDef != null) {
						ret.get(stock).put(evtDef, new EventDefCacheEntry(map4EvtDef, new UpdateStamp(start, end, false)));
					} else {
						ret.get(stock).put(evtDef, new EventDefCacheEntry(map4EvtDef, new UpdateStamp(start, end, true)));
					}
				}
			}
		}
		return ret;
	}

}
