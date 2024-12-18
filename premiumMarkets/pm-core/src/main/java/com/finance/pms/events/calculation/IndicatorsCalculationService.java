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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.portfolio.PortfolioDAO;


/**
 * The Class IndicatorCalculationService.
 * 
 * @author Guillaume Thoreton
 */
public abstract class IndicatorsCalculationService {
	
	//private static MyLogger LOGGER = MyLogger.getLogger(IndicatorsCalculationService.class);
	
	protected Queue eventQueue;
	protected JmsTemplate jmsTemplate;
	
	protected ShareDAO shareDAO;
	protected PortfolioDAO portfolioDAO;
	
	public IndicatorsCalculationService() {
		super();
	}
	

	/**
	 * A bit of theory :)
	 * 
	 * What is the datedeb?
	 * This is a reference for date when the event calculation will start.
	 * In case of a daily batch :
	 * - this date is the latest event date in the data base.
	 * In case of a call from the UI :
	 * - this date is the date display in the event window.
	 * 
	 * Which events will be emailed?
	 * Only the new events should be email.
	 * It means only in theory that the ones that are later then the previous latest event.
	 * --> But in practice all the calculated events will be resent. <-- TODO : filter
	 * The difference is particularly visible when the update comes from the UI with a random date.
	 * 
	 * Note : 
	 * - It is important to notice that, the calculations of indicators starts 
	 * 	before (~ 100 quotations) the calculation of the events them selves.
	 * - The amount before the start reference date the calculation (daysbackwardday) depends on the indicators.
	 * It will be forced to 10 days in the case of the PMS indicator (and was 30 days for MAS indicators).
	 * - The events are calculated using d-1, d and d+1. This means that the latest calculated event will be on today-1.
	 * In the case of the daily calculation, the next iteration will then start on today-1 (lets call it �)
	 * The first calculation for the next daily iteration will then be on � - daysbackwardday + 1.
	 */
	@Deprecated
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> analyze(
			Collection<Stock> symbols, Date dateDeb, Date dateFin, Currency calculationCurrency, String eventListName, 
			String periodType, Boolean kc, Integer passNumber, String passOneCalcMode, Observer... observers) throws InvalidAlgorithmParameterException, IncompleteDataSetException {
		return analyseSymbolCollection(symbols, dateDeb, dateFin, calculationCurrency, eventListName, periodType, kc, passNumber, passOneCalcMode, observers);		
	}
	
	@Deprecated
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> partialAnalyze(
			Collection<Stock> symbols, Date dateDeb, Date dateFin, Currency calculationCurrency, String eventListName, 
			String periodType, Boolean keepCache, Integer passNumer, String passOneCalcMode, Observer... observers) throws InvalidAlgorithmParameterException, IncompleteDataSetException {
		return analyze(symbols, dateDeb, dateFin, calculationCurrency, eventListName,periodType , keepCache, passNumer, passOneCalcMode, observers);
	}
	
	public void setShareDAO(ShareDAO shareDAO) {
		this.shareDAO = shareDAO;
	}
	
	public void setPortfolioDAO(PortfolioDAO portfolioDAO) {
		this.portfolioDAO = portfolioDAO;
	}

	protected abstract Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> analyseSymbolCollection(
			Collection<Stock> symbols, Date datedeb, Date datefin, Currency calculationCurrency, String eventListName, 
			String periodType, Boolean keepCache, Integer passNumber, String passOneCalcMode, Observer... observers) 
			throws InvalidAlgorithmParameterException, IncompleteDataSetException;
	
	@Deprecated
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculation(
			Collection<Stock> shareList, String eventListName, Date startDate, Date endDate, Currency calculationCurrency, 
			String periodType, Integer passNumber, String passOneCalcMode, Observer... observers)
			throws InvalidAlgorithmParameterException, IncompleteDataSetException {
		
		Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> calcRes = null;
		if (shareList.size() > 0) {
				calcRes = partialAnalyze(shareList, startDate, endDate, calculationCurrency, eventListName, periodType, true, passNumber, passOneCalcMode, observers);
		} else {
				//calcRes = fullAnalyze(startDate, endDate, calculationCurrency, eventListName, periodType, true, passNumber, passOneCalcMode, observers);
				throw new UnsupportedOperationException();
		}
		
		return calcRes;
	}
	
}
	