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

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class IClalcThread.
 * 
 * @author Guillaume Thoreton
 */
public class IndicatorAnalysisCalculationRunnableMessage extends AbstractAnalysisClientRunnableMessage {
	
	private static final long serialVersionUID = 1L;
	private static MyLogger LOGGER = MyLogger.getLogger(IndicatorAnalysisCalculationRunnableMessage.class);

	//private final String periodType;
	private final SelectedIndicatorsCalculationService analyzer;
	private final Date datefin;
	private final Date datedeb;
	private Collection<Stock> shareList;
	private Observer[] observers;
	//private Currency calculationCurrency;

	private Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculationRes;
	private IncompleteDataSetException exception;

	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													SelectedIndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Collection<Stock> shareList, Date datedeb, Date datefin, Observer... observers) {
		super(999, springContext, eventListName);
		//this.periodType = periodType; //FIXME Not used
		this.analyzer = analyzer;
		this.datefin = datefin;
		this.datedeb = datedeb;
		this.shareList = shareList;
		//calculationCurrency is null : the stock currency will be used
		this.observers = observers;
		
	}
	
	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													SelectedIndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Stock oneStock, Date datedeb, Date datefin, Currency calculationCurrency, Observer... observers) {
		this(springContext, analyzer, eventListName, periodType, Arrays.asList(new Stock[]{oneStock}), datedeb, datefin, observers);
		//this.calculationCurrency = calculationCurrency; //FIXME Not used
	}
	
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculation() throws InterruptedException, IncompleteDataSetException {
		
		this.exception = null;
		this.runIndicatorsCalculationRes = null;
		
		this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		synchronized (syncObject) {
			syncObject.wait();
		}
		
		if (exception != null) {
			throw exception;
		}
		
		return runIndicatorsCalculationRes;
	}

	public void run() {

		try {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,getConfigs().get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,getConfigs().get(Config.INDICATOR_PARAMS_NAME));
			
			Map<Stock, List<EventInfo>> stocksEventInfos = shareList.stream().collect(Collectors.toMap(s -> s, s-> ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getIndepsAndParameterised()));
			List<SymbolEvents> calculated = analyzer.calculate(datedeb, datefin, getAnalysisName(), stocksEventInfos, observers);
			runIndicatorsCalculationRes = calculated.stream().collect(Collectors.toMap(se -> se.getStock(), se -> se.getCalculationOutputs()));
			
		} catch (IncompleteDataSetException e) {
			exception = e;

		} catch (Throwable e) {
			LOGGER.error("Error in "+this.toString(), e);

		} finally {
			synchronized (syncObject) {
				syncObject.notify();
			}
		}
	}

	@Override
	public String toString() {
		return "IndicatorAnalysisCalculationRunnableMessage [datedeb=" + datedeb + ", datefin=" + datefin + ", shareList=" + shareList + ", toString()=" + super.toString() + "]";
	}

	public Collection<Stock> getShareList() {
		return shareList;
	}
	
	
}