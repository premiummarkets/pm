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
package com.finance.pms;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.threads.ConfigThreadLocal;


// TODO: Auto-generated Javadoc
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
	private Boolean export;
	private Currency calculationCurrency;
	private Boolean persistEvents;

	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													IndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Collection<Stock> shareList, Date datedeb, Date datefin, Boolean export) {
		super(999, springContext, eventListName);
		this.periodType = periodType;
		this.analyzer = analyzer;
		this.datefin = datefin;
		this.datedeb = datedeb;
		this.shareList = shareList;
		this.export = export;
		//calculationCurrency is null the stock currency will be used
	}
	
	public IndicatorAnalysisCalculationRunnableMessage(SpringContext springContext, 
													IndicatorsCalculationService analyzer, String eventListName, String periodType, 
													Stock oneStock, Date datedeb, Date datefin, Boolean export, Currency calculationCurrency) {
		this(springContext, analyzer, eventListName, periodType, Arrays.asList(new Stock[]{oneStock}), datedeb, datefin, export);
		this.calculationCurrency = calculationCurrency;
	}

	public void runIndicatorsCalculation(Integer passNumber, Boolean persistEvents) throws InterruptedException {
		
		this.passNumber = passNumber;
		this.persistEvents = persistEvents;
		
		this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		synchronized (syncObject) {
			syncObject.wait();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		try {
			
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));
			
			analyzer.runIndicatorsCalculation(shareList, getAnalysisName(), datedeb, datefin, calculationCurrency, periodType, passNumber, export, persistEvents);
			
		} catch (Throwable e) {
			LOGGER.error("Error in "+this.toString(),e);

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
	
	
}