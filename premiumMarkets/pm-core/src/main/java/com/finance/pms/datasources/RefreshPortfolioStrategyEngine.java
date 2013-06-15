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
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.EventModel.EventDefCacheEntry;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.AlertCalculationRunnableMessage;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.portfolio.UserPortfolio;



public class RefreshPortfolioStrategyEngine extends UserContentStrategyEngine {
	
	@Override
	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		 try {
			actionThread.runIndicatorsCalculationPassTwo(true);
		} catch (IncompleteDataSetException e) {
		}
		 return null;
	}

	@Override
	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		try {
			actionThread.runIndicatorsCalculationPassOne(true , passOneOverwriteMode());
		} catch (IncompleteDataSetException e) {
		}
		return null;
	}
	
	@Override
	protected String passOneOverwriteMode() {
		//return "auto";
		return "force";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object... viewStateParams) throws InterruptedException {
		
		Date endDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
		
		UserPortfolio[] userPortfolios = Arrays.copyOf(((Object[])viewStateParams[1]),((Object[])viewStateParams[1]).length,(new UserPortfolio[0]).getClass());
		AlertCalculationRunnableMessage alertOnThresholdAnalyser = new AlertCalculationRunnableMessage(SpringContext.getSingleton(), IndicatorCalculationServiceMain.UI_ANALYSIS, endDate, userPortfolios);
		alertOnThresholdAnalyser.runAlertsOnThresholdCalculation();
	}

	@Override
	public void callbackForlastListFetch(Set<Observer> engineObservers, Object... viewStateParams) {
		Object[] stocks = (Object[]) viewStateParams[0];
		super.callbackForlastListFetch(engineObservers, stocks);
	}

	@Override
	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Object... viewStateParams) throws StockNotFoundException {
		Object[] stocks = (Object[]) viewStateParams[0];
		super.callbackForlastQuotationFetch(engineObservers, stocks);
	}

	@Override
	public Map<Stock, Map<EventInfo, EventDefCacheEntry>> callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate, Set<Observer> engineObservers, Object... viewStateParams) throws NotEnoughDataException {
		Object[] stocks = (Object[]) viewStateParams[0];
		return super.callbackForlastAnalyse(analysisList, startAnalyseDate, engineObservers, stocks);
	}

	@Override
	public Boolean callbackForAnalysisClean(Set<Observer> engineObservers, Object... viewStateParams) {
		Object[] stocks = (Object[]) viewStateParams[0];
		return super.callbackForAnalysisClean(engineObservers, stocks);
	}

}
