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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class AllEventRefreshModel.
 * 
 * @author Guillaume Thoreton
 */
public class RefreshAllEventStrategyEngine extends EventModelStrategyEngine<Collection<ShareListInfo>> {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(RefreshAllEventStrategyEngine.class);

	public RefreshAllEventStrategyEngine() {
		
	}

	public void callbackForlastListFetch(Set<Observer> engineObservers, Collection<ShareListInfo> rootParam, Collection<? extends Object>...viewStateParams) throws HttpException {

		LOGGER.debug("Updating list of shares  : "+rootParam);
		for (Object shareList : rootParam) {

			Providers provider = Providers.setupProvider(((ShareListInfo) shareList).info());
			String marketQuotationsProviders = MainPMScmd.getPrefs().get("quotes.provider","yahoo");
			MarketQuotationProviders marketQuotationProvider = MarketQuotationProviders.valueOfCmd(marketQuotationsProviders);

			provider.addObservers(engineObservers);
			provider.updateStockListFromWeb(marketQuotationProvider);
		}

	}

	
	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Collection<ShareListInfo> rootParam, Collection<? extends Object>...viewStateParams) {

		QuotationUpdate quotationUpdate = new QuotationUpdate();

		LOGGER.debug("Fetching all quotations");
		quotationUpdate.addObservers(engineObservers);
		for (Object shareList : rootParam) {
			Providers provider = Providers.setupProvider(((ShareListInfo) shareList).info());
			quotationUpdate.getQuotesForSharesListInDB(provider.getSharesListIdEnum().getSharesListCmdParam(), provider.getIndices());

		}
	}


	@SuppressWarnings("unchecked")
	public void callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Date endAnalysisDate, Set<Observer> engineObservers, Collection<ShareListInfo> rootParam, Collection<? extends Object>...viewStateParams) {

		tamperEventConfig((Collection<EventInfo>) viewStateParams[0]);

		for (Object shareList : rootParam) {
			Providers provider = Providers.setupProvider(((ShareListInfo) shareList).info());
			SharesList sharesListForThisListProvider = provider.loadSharesListForThisListProvider();

			//Dates check
			Date datefin = DateFactory.midnithDate(endAnalysisDate);
			Date datedeb = DateFactory.midnithDate(startAnalyseDate);

			//Init
			String periodType = MainPMScmd.getPrefs().get("events.periodtype", "daily");
			String[] analysers = new String[analisysList.size()];
			for (int j = 0; j < analysers.length; j++) {
				analysers[j] = analisysList.get(j);
			}
			for (int i = 0; i < analysers.length; i++) {

				LOGGER.debug("running analysis for " + analysers[i]);
				IndicatorsCalculationService analyzer = (IndicatorsCalculationService) SpringContext.getSingleton().getBean(analysers[i]);

				ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());

				//Calculations
				IndicatorAnalysisCalculationRunnableMessage actionThread = new IndicatorAnalysisCalculationRunnableMessage(
						SpringContext.getSingleton(),
						analyzer, IndicatorCalculationServiceMain.UI_ANALYSIS, periodType, 
						sharesListForThisListProvider.getListShares().keySet(), datedeb, datefin, engineObservers.toArray(new Observer[0]));

				Integer maxPass = new Integer(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
				try {
					actionThread.runIndicatorsCalculationPassOne(true, "auto");
					if (maxPass == 2) {
						actionThread.runIndicatorsCalculationPassTwo(true);
					}
				} catch (IncompleteDataSetException e) {
					LOGGER.warn(e,e);
				} catch (Exception e) {
					LOGGER.error(e,e);
				}
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Collection<ShareListInfo> rootParam, Collection<? extends Object>... viewStateParams) {

		tamperEventConfig((Collection<EventInfo>) viewStateParams[0]);

		EventInfo[] eventDefsArray = EventDefinition.loadMaxPassPrefsEventInfo().toArray(new EventInfo[0]);
		EventsResources.getInstance().crudDeleteEventsForIndicators(IndicatorCalculationServiceMain.UI_ANALYSIS, EventModel.DEFAULT_DATE, EventSignalConfig.getNewDate(), true, eventDefsArray);
		TunedConfMgr.getInstance().getTunedConfDAO().resetTunedConfs();

		//Delete all
		postCallBackForClean(true);

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

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<ShareListInfo> rootParam, Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}

	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
		switch (taskId) {
		case Analysis:
		case Clean :
			return new int[]{0};
		default :
			return new int[]{};
		}
	}
	
	@Override
	public int otherViewParamLength() {
		return 1;
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}

}
