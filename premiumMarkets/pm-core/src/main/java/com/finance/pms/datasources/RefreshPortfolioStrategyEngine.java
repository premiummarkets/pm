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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.AlertCalculationRunnableMessage;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.portfolio.UserPortfolio;



public class RefreshPortfolioStrategyEngine extends UserContentStrategyEngine<Collection<Stock>> {
	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<Stock> rootParam, Collection<? extends Object>... viewStateParams) throws InterruptedException {
		
		Date endDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
		
		UserPortfolio[] userPortfolios = viewStateParams[0].toArray(new UserPortfolio[0]);
		AlertCalculationRunnableMessage alertOnThresholdAnalyser = new AlertCalculationRunnableMessage(SpringContext.getSingleton(), IndicatorCalculationServiceMain.UI_ANALYSIS, endDate, userPortfolios);
		alertOnThresholdAnalyser.runAlertsOnThresholdCalculation();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate , Date endAnalysisDate, Set<Observer> engineObservers, Collection<Stock> rootParam,Collection<? extends Object>... viewStateParams) throws NotEnoughDataException {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		super.callbackForlastAnalyse(analysisList, startAnalyseDate, endAnalysisDate, engineObservers, rootParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Collection<Stock> rootParam, Collection<? extends Object>... viewStateParams) {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		super.callbackForAnalysisClean(engineObservers,rootParam);
	}
	
	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
		switch (taskId) {
		case Analysis:
		case Clean :
			return new int[]{1};
		case Alerts :
			return new int[]{0};
		default :
			return new int[]{};
		}
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}

	@Override
	public int otherViewParamLength() {
		return 2;
	}

	@Override
	protected void updateQuotations(QuotationUpdate quotationUpdate, Collection<Stock> rootParam) throws StockNotFoundException {
		quotationUpdate.getQuotesFor(rootParam);
		
	}

	@Override
	protected List<Stock> buildStockListFrom(Collection<Stock> rootParam) {
		return new ArrayList<Stock>(rootParam);
	}

}
