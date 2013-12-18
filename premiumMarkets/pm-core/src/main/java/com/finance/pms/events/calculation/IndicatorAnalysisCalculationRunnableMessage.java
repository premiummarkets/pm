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
package com.finance.pms.events.calculation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
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

	private final String periodType;
	private final IndicatorsCalculationService analyzer;
	private final Date datefin;
	private final Date datedeb;
	
	private Collection<Stock> shareList;
	
	private Integer passNumber;
	private String passOneRecalculationMode;

	private Currency calculationCurrency;
	private Boolean persistEvents;
	private Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculationRes;
	private Observer[] observers;


	private IncompleteDataSetException exception;

	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													IndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Collection<Stock> shareList, Date datedeb, Date datefin, Observer... observers) {
		super(999, springContext, eventListName);
		this.periodType = periodType;
		this.analyzer = analyzer;
		this.datefin = datefin;
		this.datedeb = datedeb;
		this.shareList = shareList;
		//calculationCurrency is null the stock currency will be used
		this.observers = observers;
		
	}
	
	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													IndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Stock oneStock, Date datedeb, Date datefin, Currency calculationCurrency, Observer... observers) {
		this(springContext, analyzer, eventListName, periodType, Arrays.asList(new Stock[]{oneStock}), datedeb, datefin);
		this.calculationCurrency = calculationCurrency;
		this.observers = observers;
	}
	
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculationPassOne(Boolean persistEvents, String passOneRecalculationMode) throws InterruptedException, IncompleteDataSetException {
		return this.runIndicatorsCalculation(1, persistEvents, passOneRecalculationMode);
	}
	
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculationPassTwo(Boolean persistEvents) throws InterruptedException, IncompleteDataSetException {
		return this.runIndicatorsCalculation(2, persistEvents, null);
	}
	
	private Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> runIndicatorsCalculation(Integer passNumber, Boolean persistEvents, String passOneRecalculationMode) throws InterruptedException, IncompleteDataSetException {
		
		this.passNumber = passNumber;
		this.persistEvents = persistEvents;
		this.passOneRecalculationMode = passOneRecalculationMode;
		
		this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		synchronized (syncObject) {
			syncObject.wait();
		}
		
		if (exception != null) {
			if (runIndicatorsCalculationRes != null && runIndicatorsCalculationRes != null) {
				if (exception.getCalculatedOutput() == null) {
					exception.setCalculatedOutput(runIndicatorsCalculationRes);
				} else {
					exception.getCalculatedOutput().putAll(runIndicatorsCalculationRes);
				}
			}
			throw exception;
		}
		
		return runIndicatorsCalculationRes;
	}

	public void run() {

		try {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,getConfigs().get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,getConfigs().get(Config.INDICATOR_PARAMS_NAME));

			runIndicatorsCalculationRes = analyzer.runIndicatorsCalculation(shareList, getAnalysisName(), datedeb, datefin, calculationCurrency, periodType, passNumber, persistEvents, passOneRecalculationMode, observers);
			
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