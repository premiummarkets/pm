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
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.threads.ConfigThreadLocal;

public abstract class UserContentStrategyEngine implements EventModelStrategyEngine {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(UserContentStrategyEngine.class);

	public void callbackForlastListFetch(Set<Observer> engineObservers) {
		LOGGER.debug("No list update for monitored shares.");
	}

	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Object...viewStateParams) {
		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		LOGGER.debug("Fetching monitored quotations");
		quotationUpdate.addObservers(engineObservers);
		
		updateQuotations(quotationUpdate, viewStateParams);
	}

	protected abstract void updateQuotations(QuotationUpdate quotationUpdate, Object...viewStateParams);

	public void callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Set<Observer> engineObservers, Object...viewStateParams) {
		
		Date datefin = EventSignalConfig.getNewDate();
		Date datedeb = startAnalyseDate;
		
		String[] analysers = new String[analisysList.size()];
		for (int j = 0; j < analysers.length; j++) {
			analysers[j] = analisysList.get(j);
		}
		
		String periodType = MainPMScmd.getPrefs().get("events.periodtype", "daily");
		//Collection<Stock> shareList = Arrays.asList((Stock[])viewStateParams);
		@SuppressWarnings("rawtypes")
		List shareList = Arrays.asList(viewStateParams);
		
		for (int i = 0; i < analysers.length; i++) {
			
			LOGGER.debug("running analysis for " + analysers[i]);
			IndicatorsCalculationService analyzer = (IndicatorsCalculationService) SpringContext.getSingleton().getBean(analysers[i]);
			//analyzer.addObservers(engineObservers);
			
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());
			
			@SuppressWarnings("unchecked")
			IndicatorAnalysisCalculationRunnableMessage actionThread = new IndicatorAnalysisCalculationRunnableMessage(
					SpringContext.getSingleton(), 
					analyzer, IndicatorCalculationServiceMain.UI_ANALYSIS, periodType, 
					shareList, datedeb, datefin, false, engineObservers.toArray(new Observer[0]));
			
			Integer maxPass = new Integer(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
			try {
				actionThread.runIndicatorsCalculation(maxPass,true);
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
		}
	}

	//protected abstract Collection<Stock> listOfShares();


	
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
