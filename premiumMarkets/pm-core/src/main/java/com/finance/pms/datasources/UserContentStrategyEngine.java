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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;

public abstract class UserContentStrategyEngine implements EventModelStrategyEngine {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(UserContentStrategyEngine.class);

	public void callbackForlastListFetch(Set<Observer> engineObservers, Object...viewStateParams) {
		LOGGER.debug("No list update available for this model.");
	}

	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Object...viewStateParams) throws StockNotFoundException {
		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		LOGGER.debug("Fetching monitored quotations");
		quotationUpdate.addObservers(engineObservers);
		
		updateQuotations(quotationUpdate, viewStateParams);
	}

	@SuppressWarnings("unchecked")
	protected void updateQuotations(QuotationUpdate quotationUpdate, Object... viewStateParams) throws StockNotFoundException {
		@SuppressWarnings("rawtypes")
		List stockList = Arrays.asList(viewStateParams);
		quotationUpdate.getQuotesFor(stockList);
	}

	public Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Set<Observer> engineObservers, Object...viewStateParams) {
		
		String periodType = MainPMScmd.getPrefs().get("events.periodtype", "daily");
		String[] analysers = new String[analisysList.size()];
		for (int j = 0; j < analysers.length; j++) {
			analysers[j] = analisysList.get(j);
		}

		Date datefin = DateFactory.midnithDate(EventSignalConfig.getNewDate());
		Date datedeb = DateFactory.midnithDate(startAnalyseDate);
		
		@SuppressWarnings("rawtypes")
		List stockList = Arrays.asList(viewStateParams);
		for (Object stock : stockList) {
			Date firstQuotationDateFromQuotations = DataSource.getInstance().getFirstQuotationDateFromQuotations((Stock) stock);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(firstQuotationDateFromQuotations);
			QuotationsFactories.getFactory().incrementDate(calendar, 50);
			datedeb = (calendar.getTime().after(datedeb))?calendar.getTime():datedeb;
		}
		
		Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> outputRet = new HashMap<Stock, Map<EventDefinition,SortedMap<Date,double[]>>>();
		for (int i = 0; i < analysers.length; i++) {
			
			LOGGER.debug("running analysis for " + analysers[i]);
			IndicatorsCalculationService analyzer = (IndicatorsCalculationService) SpringContext.getSingleton().getBean(analysers[i]);
			
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());
			
			Boolean export = MainPMScmd.getPrefs().getBoolean("perceptron.exportoutput", false);
			@SuppressWarnings("unchecked")
			IndicatorAnalysisCalculationRunnableMessage actionThread = new IndicatorAnalysisCalculationRunnableMessage(
					SpringContext.getSingleton(), 
					analyzer, IndicatorCalculationServiceMain.UI_ANALYSIS, periodType, 
					stockList, datedeb, datefin, export, engineObservers.toArray(new Observer[0]));
			
			Integer maxPass = new Integer(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
	
			//Pass one
			Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassOne = new HashMap<Stock, Map<EventDefinition,SortedMap<Date,double[]>>>();
			try {
				runPassOne = runPassOne(actionThread);
			} catch (IncompleteDataSetException e) {
				runPassOne = e.getCalculatedOutput();
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
			if (runPassOne != null) outputRet.putAll(runPassOne);
			
			//Pass two
			if (maxPass == 2) {
				Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassTwo = new HashMap<Stock, Map<EventDefinition,SortedMap<Date,double[]>>>();
				try {
					runPassTwo = runPassTwo(actionThread);
				} catch (IncompleteDataSetException e) {
					runPassTwo = e.getCalculatedOutput();
				} catch (Exception e) {
					LOGGER.error(e,e);
				}
				if (runPassTwo != null) {
					for (Stock stock : runPassTwo.keySet()) {
						Map<EventDefinition, SortedMap<Date, double[]>> map4Stock = outputRet.get(stock);
						if (map4Stock == null) {
							outputRet.put(stock, runPassTwo.get(stock));
						} else {
							map4Stock.putAll(runPassTwo.get(stock));
						}
					}
				}
			} 
		}
		
		return outputRet;
	}
	
	protected abstract Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException, IncompleteDataSetException;
	protected abstract Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException, IncompleteDataSetException;
	
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

}
