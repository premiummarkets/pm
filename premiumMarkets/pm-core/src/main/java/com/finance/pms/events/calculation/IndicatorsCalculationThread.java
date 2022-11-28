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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.apache.log4j.Level;
import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.CalculationBounds;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;

public abstract class IndicatorsCalculationThread extends EventsCalculationThread {

	private static MyLogger LOGGER = MyLogger.getLogger(IndicatorsCalculationThread.class);

	protected Stock stock;
	private String passOneCalcMode;

	protected IndicatorsCalculationThread(Stock stock, Date startDate, Date endDate, String eventListName, Currency  calculationCurrency, 
			Set<Observer> observers,
			String passOneCalcMode,
			Queue eventQueue, JmsTemplate jmsTemplate) throws NotEnoughDataException {
		super(startDate, endDate, eventListName, calculationCurrency, observers, eventQueue, jmsTemplate);
		this.stock = stock;
		this.passOneCalcMode = passOneCalcMode;
	}

	protected abstract void setCalculationParameters();


	public SymbolEvents call() throws IncompleteDataSetException {

		SymbolEvents symbolEventsForStock = new SymbolEvents(stock);
		List<IncompleteDataSetException> dataSetExceptions = new ArrayList<IncompleteDataSetException>();

		try {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Analysing events for "+stock+", starting at "+startDate);

			setCalculationParameters();
			calculate(symbolEventsForStock, dataSetExceptions);

			if (LOGGER.isDebugEnabled()) LOGGER.debug("End analyse "+stock+" from "+startDate+" to "+endDate);

		} catch (Exception e) {
			LOGGER.error("UnHandled error : While calculating Events for "+stock+", analysis "+eventListName+" and dates "+startDate+" to "+endDate, e);
			throw new IncompleteDataSetException(stock, symbolEventsForStock, "UnHandled error : " + e.getMessage());

		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}

		if (dataSetExceptions.size() > 0) {
			if (LOGGER.isEnabledFor(Level.ERROR)) {
				dataSetExceptions.stream().forEach(e ->  LOGGER.error(e,e));
			}
			throw new IncompleteDataSetException(stock, symbolEventsForStock, "Invalid data set for "+stock.getFriendlyName()+" may invalidate further usage.");
		}

		return symbolEventsForStock;

	}

	protected void calculate(SymbolEvents symbolEventsForStock, List<IncompleteDataSetException> dataSetExceptions) throws NotEnoughDataException, InvalidAlgorithmParameterException {

		Set<IndicatorsOperator> eventsCalculators;

		LOGGER.info("Effective recalculation (potentially incremental) for "+stock+" will occur from "+startDate+" to "+endDate);

		//Init calculators
		try {
			eventsCalculators = initIndicatorsAndCalculators(symbolEventsForStock, observers.toArray(new Observer[]{}));
		} catch (IncompleteDataSetException e) {
			dataSetExceptions.add(e);
			eventsCalculators = e.getValidEventCalculators();
		}

		//Run calculators
		try {
			calculateEventsForEachDateAndIndicatorComp(eventsCalculators, symbolEventsForStock, startDate, endDate, stock);
		} catch (IncompleteDataSetException e) {
			dataSetExceptions.add(e);
		}

	}

	abstract protected Set<IndicatorsOperator> initIndicatorsAndCalculators(SymbolEvents symbolEventsForStock, Observer... observers) throws IncompleteDataSetException;

	private void calculateEventsForEachDateAndIndicatorComp(Set<IndicatorsOperator> evtCalculators, final SymbolEvents symbolEventsForStock, final Date datedeb, final Date datefin, final Stock stock) throws IncompleteDataSetException { 

		ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(MainPMScmd.getMyPrefs().get("indicEventsCalculator.semaphore.eventthread","1")));
		final List<EventInfo> failing = new ArrayList<EventInfo>();

		for (final IndicatorsOperator evtCalculator: evtCalculators ) {

			Runnable runnable = new Runnable() {
				public void run() {

					ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, IndicatorsCalculationThread.this.configs.get(Config.EVENT_SIGNAL_NAME));
					ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, IndicatorsCalculationThread.this.configs.get(Config.INDICATOR_PARAMS_NAME));

					TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, evtCalculator.getEventDefinition().getEventDefinitionRef());
					try {
						//The check on dirty is just a safe check as making dirty should also have previously deleted the events.
						if (!evtCalculator.isIdemPotent() || tunedConf.getDirty()) cleanEventsFor(stock, evtCalculator.getEventDefinition(), eventListName);
					} catch (Exception e) {
						LOGGER.error(e,e);
					}

					try {

						synchronized (tunedConf) {

							CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.IGNORE, startDate, endDate, null, null);
							if (passOneCalcMode.equals("auto")) {
								endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
								calculationBounds = TunedConfMgr.getInstance().autoCalcAndSetDatesBounds(tunedConf, stock, startDate, endDate);
							}
							if (passOneCalcMode.equals("reset")) {
								endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
								tunedConf.setLastCalculationStart(startDate);
								tunedConf.setLastCalculationEnd(endDate);
								calculationBounds = new CalculationBounds(CalcStatus.RESET, startDate, endDate, null, null);
							}

							startDate = calculationBounds.getPmStart();
							endDate = calculationBounds.getPmEnd();

							if (!calculationBounds.getCalcStatus().equals(CalcStatus.NONE)) {

								try {

									Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, datedeb, datefin, true, stock.getMarketValuation().getCurrency(), evtCalculator.getStartShift(), evtCalculator.quotationsValidity());
									SortedMap<EventKey, EventValue> calculatedEventsForCalculator = evtCalculator.calculateEventsFor(quotations, IndicatorsCalculationThread.this.eventListName);

									if (calculatedEventsForCalculator != null && !calculatedEventsForCalculator.isEmpty()) {
										//Add events to total
										symbolEventsForStock.addEventResultElement(calculatedEventsForCalculator, evtCalculator.getEventDefinition());

										//Add events to composer and send
										SymbolEvents symbolEventsForStockAndCalculator = new SymbolEvents(stock);
										symbolEventsForStockAndCalculator.addEventResultElement(calculatedEventsForCalculator, evtCalculator.getEventDefinition());
										sendEvent(eventListName, symbolEventsForStockAndCalculator, evtCalculator.getSource(), calculatedEventsForCalculator.lastKey().getEventType(), evtCalculator.getEventDefinition());
									}
									//Add output to total
									symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), evtCalculator.calculationOutput());

								} catch (NoQuotationsException | TalibException e) {
									LOGGER.warn(e);
									failing.add(evtCalculator.getEventDefinition());
									symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), new TreeMap<Date, double[]>());
								} catch (Exception e) {
									LOGGER.error(e, e);
									failing.add(evtCalculator.getEventDefinition());
									symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), new TreeMap<Date, double[]>());
								}

							} else {
								LOGGER.info(
										"Events recalculation requested for "+stock.getSymbol()+" and "+evtCalculator.getEventDefinition().getEventDefinitionRef()+" using analysis "+eventListName+" from "+startDate+" to "+endDate+". "+
												"No recalculation needed calculation bound is "+ calculationBounds.toString());
							}

							if (!failing.isEmpty()) {//Error(s) as occurred. This should invalidate tuned conf
								if (LOGGER.isEnabledFor(Level.ERROR)) {
									failing.stream().forEach(e ->  LOGGER.error("Failing calculation : "+e));
								}
								//We will make the tunedConf clean assuming a subsequent calculation will also fail. FIXME calculation date have been changed however.
							}

						}//End synchronized

					} catch (InvalidAlgorithmParameterException e) {
						LOGGER.error(e);
					} finally {
						//We update the tunedConf
						//Dirty is set to false as we assume that no retry will occur in case of failure?
						TunedConfMgr.getInstance().updateConf(tunedConf, false);
					}
				}
			};

			executor.execute(runnable);

		}//End For calculators

		executor.shutdown();

		Boolean shutdownFailed = false;
		try {
			boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow, new Exception());
				shutdownFailed = true;
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow, e);
			shutdownFailed = true;
		}

		if (shutdownFailed || !failing.isEmpty()) throw new IncompleteDataSetException(stock, symbolEventsForStock, "Some calculations have failed! Are failing : "+failing);

	}

	protected String warnMessage(String calculatorName, Date startDate, Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("%s can't be calculated for %s between %s and %s", calculatorName, stock.getName(), simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
	}

	protected boolean checkWanted(EventDefinition eventDefinition) {
		List<EventInfo> wantedEventCalculators = getWantedEventCalculations();
		return (wantedEventCalculators != null && wantedEventCalculators.contains(eventDefinition));
	}

	protected abstract List<EventInfo> getWantedEventCalculations();

}
