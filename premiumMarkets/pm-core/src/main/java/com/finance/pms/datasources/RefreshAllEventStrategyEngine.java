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
package com.finance.pms.datasources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.threads.ConfigThreadLocal;


// TODO: Auto-generated Javadoc
/**
 * The Class AllEventRefreshModel.
 * 
 * @author Guillaume Thoreton
 */
public class RefreshAllEventStrategyEngine implements EventModelStrategyEngine {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(RefreshAllEventStrategyEngine.class);

	public RefreshAllEventStrategyEngine() {
		
	}

	public void callbackForlastListFetch(Set<Observer> engineObservers) {
		
		LOGGER.debug("Updating list of shares");
		String listStProvider = MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
		Providers provider = Providers.getInstance(listStProvider);
		String marketQuotationsProviders = MainPMScmd.getPrefs().get("quotes.provider","yahoo");
		MarketQuotationProviders marketQuotationProvider = MarketQuotationProviders.valueOfCmd(marketQuotationsProviders);
		
		provider.addObservers(engineObservers);
		provider.updateStockListFromWeb(marketQuotationProvider);
		
		
	}

	
	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Object...viewStateParams) {
		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		LOGGER.debug("Fetching all quotations");
		quotationUpdate.addObservers(engineObservers);

		String listStProvider = MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
		Providers provider = Providers.getInstance(listStProvider);
		quotationUpdate.getQuotesForSharesListInDB(listStProvider, provider.getIndices());
	}

	
	public void callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Set<Observer> engineObservers, Object...viewStateParams) {
		
		Date datefin = EventSignalConfig.getNewDate();
		Date datedeb = startAnalyseDate;
		
		String[] analysers = new String[analisysList.size()];
		for (int j = 0; j < analysers.length; j++) {
			analysers[j] = analisysList.get(j);
		}
		
		String periodType = MainPMScmd.getPrefs().get("events.periodtype", "daily");
		Providers provider = Providers.getInstance(MainPMScmd.getPrefs().get("quotes.listprovider", SharesListId.YAHOOINDICES.getSharesListCmdParam()));
		SharesList sharesListForThisListProvider = provider.loadSharesListForThisListProvider();
		for (int i = 0; i < analysers.length; i++) {
			
			LOGGER.debug("running analysis for " + analysers[i]);
			IndicatorsCalculationService analyzer = (IndicatorsCalculationService) SpringContext.getSingleton().getBean(analysers[i]);
			//analyzer.addObservers(engineObservers);

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());
			IndicatorAnalysisCalculationRunnableMessage actionThread = new IndicatorAnalysisCalculationRunnableMessage(
						SpringContext.getSingleton(),
						analyzer, IndicatorCalculationServiceMain.UI_ANALYSIS, periodType, 
						sharesListForThisListProvider.getListShares().keySet(), datedeb, datefin, false, engineObservers.toArray(new Observer[0]));
			
			Integer maxPass = new Integer(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
			try {
				actionThread.runIndicatorsCalculation(maxPass,true);
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			}
		}
	
	}

	
	public void callbackForReco(Set<Observer> engineObservers) {
		//TODO ??
//		LOGGER.debug("Updating recos");
//		String listStProvider = MainPMScmd.prefs.get("quotes.listprovider", "euronext");
//		Providers provider = Providers.getInstance(listStProvider);
//		provider.retrieveScreeningInfo(new ArrayList<Stock>());
		
	}

	
	public Date getLastListFetch(Date oldLastListFetch) {
		try {
			return new SimpleDateFormat("yyyy/MM/dd").parse(MainPMScmd.getPrefs().get("quotes.lastlistfetch", "1970/01/01"));
		} catch (ParseException e) {
			LOGGER.error("Wrong date format for last  shares list update, please check your settings. "+ MainPMScmd.getPrefs().get("quotes.lastlistfetch", "1970/01/01"),e);
			LOGGER.debug(e,e);
			return EventModel.DEFAULT_DATE;
		}
	}

	
	public Date getLastQuotationFetch(Date oldLastQuotaitonFetch) {
		try {
			return new SimpleDateFormat("yyyy/MM/dd").parse(MainPMScmd.getPrefs().get("quotes.lastfetch", "1970/01/01"));
		} catch (ParseException e) {
			LOGGER.error("Wrong date format for last quotation update, please check your settings. "+ MainPMScmd.getPrefs().get("quotes.lastfetch", "1970/01/01"),e);
			LOGGER.debug(e,e);
			return EventModel.DEFAULT_DATE;
		}
	}

	
	public Date getLastAnalyse(Date oldLastAnalyse) {
		try {
			return new SimpleDateFormat("yyyy/MM/dd").parse(MainPMScmd.getPrefs().get("quotes.lastanalyse", "1970/01/01"));
		} catch (ParseException e) {
			LOGGER.error("Wrong date format for last analyse, please check your settings. "+ MainPMScmd.getPrefs().get("quotes.lastanalyse", "1970/01/01"),e);
			LOGGER.debug(e,e);
			return EventModel.DEFAULT_DATE;
		}
	}	
	
	public Date setLastListFetch(Date newD, Date oldD) {
		MainPMScmd.getPrefs().put("quotes.lastlistfetch", new SimpleDateFormat("yyyy/MM/dd").format(newD));
		return newD;
	}

	public Date setLastQuotationFetch(Date newD, Date oldD) {
		MainPMScmd.getPrefs().put("quotes.lastfetch", new SimpleDateFormat("yyyy/MM/dd").format(newD));
		return newD;
	}

	public Date setLastAnalyse(Date newD, Date oldD) {
		MainPMScmd.getPrefs().put("quotes.lastanalyse", new SimpleDateFormat("yyyy/MM/dd").format(newD));
		return newD;
	}

}
