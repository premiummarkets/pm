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
import java.util.List;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.AlertCalculationRunnableMessage;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.UserPortfolio;


/**
 * 
 * @author guil
 * viewStateParams = {List<UserPortfolio>, ..}
 */
public class RefreshPortfolioStrategyEngine extends UserContentStrategyEngine<Collection<Stock>> {
	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<Stock> rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>... viewStateParams) throws InterruptedException {
		
		Date endDate = DateFactory.midnithDate(DateFactory.getNowEndDate());
		
		UserPortfolio[] userPortfolios = viewStateParams[0].toArray(new UserPortfolio[0]);
		AlertCalculationRunnableMessage alertOnThresholdAnalyser = new AlertCalculationRunnableMessage(SpringContext.getSingleton(), SelectedIndicatorsCalculationService.getAnalysisName(), endDate, userPortfolios);
		alertOnThresholdAnalyser.runAlertsOnThresholdCalculation();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysis(ArrayList<String> analysisList, Date startAnalyseDate , Date endAnalysisDate, Set<Observer> engineObservers, Collection<Stock> rootParam,Collection<? extends Object>... viewStateParams) throws NotEnoughDataException {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		super.callbackForAnalysis(analysisList, startAnalyseDate, endAnalysisDate, engineObservers, rootParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Collection<Stock> rootParam, Collection<? extends Object>... viewStateParams) {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		super.callbackForAnalysisClean(engineObservers, rootParam);
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
	//TODO use the portfolio list instead of the stocks list as param.
	protected List<Stock> buildStockListFrom(Collection<Stock> rootParam) {
		if (rootParam == null) {
			List<Stock> stocks = new ArrayList<Stock>();
			for (Portfolio uPortfolio : PortfolioMgr.getInstance().getVisiblePortfolios()) {//XXX I should use the param 1 of otherViewParams instead. 
				for (Stock stock : uPortfolio.getListShares().keySet()) {
					stocks.add(stock);
				}
			}
			return stocks;
		} else {
			return new ArrayList<Stock>(rootParam);
		}
	}

}
