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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.ConfigFreePonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;

/**
 * @author Guillaume Thoreton
 * AutoPortfolio Delegate Implementation based on Technical Analysis events and Screening events
 * On replay: checks are in place and past events should not affect the portfolio status
 * Erratic forecast: 
 * 	- in case the past forecast changes, past changed event won't be accounted if outside the calculation span.
 * 	- hence, in this case, then final trend could be different from the portfolio status
 * 	- INFINITCASH strategy should be applied in this case.. 
 */
public class AutoPortfolioDelegate {

	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolio.class);
	
	private final BigDecimal nominalTransactionAmount; // = new BigDecimal(2000).setScale(4);
	private final BigDecimal minimumTransactionAmount; //= BigDecimal.ONE;
	private final int maximumNumberOfLines; // = 1;
	protected final BigDecimal initialCash; //= minTransactionAmount.multiply(new BigDecimal(maximumNumberOfLines));
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private SignalHistory signalHistory; //TODO Could be logged
		
	
	public enum InvestmentStrategy {
		REINVEST, //The profit is reinvested in new transactions. Starting with nominalTransactionAmount*maximumNumberOfLines of cash: Buy only if not owned. Sell the full line.
		LIMITROWS, //Buys of nominalTransactionAmount are made until the maximumNumberOfLines is reached: Buy only if not owned. Sell the full line.
		INFINITCASH, //Buys of nominalTransactionAmount are made on buy events if not already bought: Buy only if not owned. Sell the full line.
		INFINITQUANTITY //Buys of nominalTransactionAmount are made on buy events disregarding everything else: Buy even if owned. Sells for the nominalTransactionAmount. 
		};

	protected AutoPortfolio thisPortfolio;

	public AutoPortfolioDelegate(AutoPortfolioWays autoPortfolio) {
		
		nominalTransactionAmount = new BigDecimal(MainPMScmd.getMyPrefs().get("autoporfolio.nominal.transaction.amount", "2000")).setScale(4);
		minimumTransactionAmount = new BigDecimal(MainPMScmd.getMyPrefs().get("autoporfolio.min.transaction.amount", "1")).setScale(4);
		maximumNumberOfLines = Integer.valueOf(MainPMScmd.getMyPrefs().get("autoporfolio.max.lines", "5"));
		initialCash = nominalTransactionAmount.multiply(new BigDecimal(maximumNumberOfLines));
		
		this.thisPortfolio = (AutoPortfolio) autoPortfolio;
		
	}

	public SignalHistory getSignalHistory() {
		if (signalHistory == null) signalHistory = new SignalHistory(thisPortfolio.getName());
		return signalHistory;
	}

	public SignalHistory calculate(List<SymbolEvents> listEvents, Date currentDate) {

		SignalHistory thisCalculationHistory = new SignalHistory(thisPortfolio.getName());
		
		thisPortfolio.getTransactionScheduler().runScheduled(thisPortfolio, currentDate);
		
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking events for AutoPortfolio: " + thisPortfolio.getName() + " and date " + currentDate);
		thisCalculationHistory.addAll(checkSellSignals(currentDate, listEvents));
		thisCalculationHistory.addAll(checkBuySignals(currentDate, listEvents));
		
		thisPortfolio.notifyObservers(new ObserverMsg(null, ObserverMsg.ObsKey.PRGSMSG, "Setting Quotations data")); //???
		
		thisPortfolio.getTransactionScheduler().runScheduled(thisPortfolio, currentDate);

		return thisCalculationHistory;

	}

	private SignalHistory checkBuySignals(Date currentDate, List<SymbolEvents> listEvents) {
		
		LOGGER.info("Checking buy signals at " + currentDate + " for " + listEvents);

		SymbolEvents symbolEventsThreshold = 
				new SymbolEvents(
						new Stock() {
							private static final long serialVersionUID = 1L;
							@Override
							public String getName() {
								return "~";
							}
							@Override
							public String getSymbol() {
								return "~";
							}
							@Override
							public String getIsin() {
								return "~";
							}
						},
						new ConcurrentSkipListMap<EventKey, EventValue>(),
						new ArrayList<String>(),
						EventState.STATE_TERMINATED) {

			private static final long serialVersionUID = 1L;

			@Override
			public float getWeight(PonderationRule pr) {
				if (pr instanceof ConfigFreePonderationRule) {
					return ((ConfigFreePonderationRule) pr).getBuyThreshold();
				}
				return Float.valueOf(((EventSignalConfig) ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold());
			}

			@Override
			public String toString() {
				return "Event buy threshold = " + getWeight(null);
			}

		};

		LOGGER.info("Buy event: Ponderation rule " + thisPortfolio.getBuyPonderationRule().getClass().getSimpleName() + ", threshold " + symbolEventsThreshold);

		SortedSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(thisPortfolio.getBuyPonderationRule());
		sortedSymbolEvents.addAll(listEvents);
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Total bullish events: " + sortedSymbolEvents);

		SortedSet<SymbolEvents> sortedSymbolEventsHead = sortedSymbolEvents.headSet(symbolEventsThreshold); //XXX PonderationRule.compare is reversed!!
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Filtered bullish events tail: " + sortedSymbolEventsHead);
		
		if (sortedSymbolEventsHead.isEmpty()) {
			LOGGER.info("No buy signal at " + currentDate + " with " + listEvents);
		}

		SignalHistory transactionHistory = new SignalHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents:sortedSymbolEventsHead) {
			try {
				CalcSignalRecord buyTrans = checkNBuyPondaratedEvents(symbolEvents, currentDate);
				if (buyTrans != null) {
					transactionHistory.add(buyTrans);
				}
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Buy Signal: event invalid or already processed. " + e);
			} catch (Exception e) {
				LOGGER.error("Buy Signal: " + symbolEvents.getStock() + " in AutoPortfolio: " + thisPortfolio.getName(), e);
			}
		}

		return transactionHistory;

	}

	private CalcSignalRecord checkNBuyPondaratedEvents(SymbolEvents symbolEvents, Date currentDate) throws IgnoredEventDateException {

		LOGGER.info("Checking pondarated buy: " + symbolEvents);

		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		isValidEventDate(currentDate, latestEventDateAndNewBuyDate);
		
		LOGGER.info("Buying signal at " + currentDate + " with " + symbolEvents.getSymbol());
		CalcSignalRecord buyTransactionRecord = recordSignal(TransactionType.AIN, symbolEvents, currentDate);
		
		recordSchedule(buyTransactionRecord);
		
		thisPortfolio.setChanged();

		return buyTransactionRecord;
	}

	/**
	 * To be valid the event date as to be at most on currentDate - 1 
	 * @param currentDate
	 * @param latestEventDateAndNewBuyDate
	 * @throws IgnoredEventDateException
	 */
	private void isValidEventDate(Date currentDate, Date latestEventDateAndNewBuyDate) throws IgnoredEventDateException {
		boolean isValidEvent = (latestEventDateAndNewBuyDate != null) && latestEventDateAndNewBuyDate.compareTo(currentDate) < 0;
		LOGGER.info("Is valid event date: is event date: " + df.format(latestEventDateAndNewBuyDate) + " (strictly before?) iteration current date: " + df.format(currentDate) + ": " + isValidEvent);
		if (!isValidEvent) {
			throw new IgnoredEventDateException(
					"Event date: " + df.format(latestEventDateAndNewBuyDate) + " and current iteration date: " + df.format(currentDate) + ". " +
					"Event should be processed later: ", new Throwable());
		}
	}

	private SignalHistory checkSellSignals(Date currentDate, List<SymbolEvents> listEvents) {
		
		LOGGER.info("Checking sell signals at " + currentDate + " with " + listEvents);

		SymbolEvents symbolEventsThreshold = new SymbolEvents(
				new Stock() {
					private static final long serialVersionUID = 1L;
					@Override
					public String getName() {
						return "0";
					}
					@Override
					public String getSymbol() {
						return "0";
					}
					@Override
					public String getIsin() {
						return "0";
					}
				},
				new ConcurrentSkipListMap<EventKey, EventValue>(),
				new ArrayList<String>(),
				EventState.STATE_TERMINATED) {

			private static final long serialVersionUID = 1L;

			@Override
			public float getWeight(PonderationRule pr) {
				if (pr instanceof ConfigFreePonderationRule) {
					return ((ConfigFreePonderationRule) pr).getSellThreshold();
				}
				return Float.valueOf(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold());
			}

			@Override
			public String toString() {
				return "Event sell threshold = " + getWeight(null);
			}
		};
		LOGGER.info("Sell event: Ponderation rule " + thisPortfolio.getSellPonderationRule().getClass().getSimpleName() + ", threshold " + symbolEventsThreshold);

		NavigableSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(thisPortfolio.getSellPonderationRule());
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.trace("Total bearish events: " + sortedSymbolEvents);

		NavigableSet<SymbolEvents> sortedSymbolEventsHead = (NavigableSet<SymbolEvents>) sortedSymbolEvents.tailSet(symbolEventsThreshold); //XXX PonderationRule.compare is reversed!!
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Filtered bearish events head: " + sortedSymbolEventsHead);
		
		if (sortedSymbolEventsHead.isEmpty()) {
			LOGGER.info("No sell signal at " + currentDate + " with " + listEvents);
		}

		SignalHistory transactionHistory = new SignalHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents : sortedSymbolEventsHead.descendingSet()) {
			try {
				CalcSignalRecord sellTransaction = checkNSellPondaratedEvents(symbolEvents, currentDate);
				if (sellTransaction != null) {
					transactionHistory.add(sellTransaction);
				}
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Sell Signal: event invalid or already processed. " + e);
			} catch (Exception e) {
				LOGGER.error("Sell Signal: " + symbolEvents.getStock() + " in AutoPortfolio: " + thisPortfolio.getName(), e);
			}
		}

		return transactionHistory;
	}
 
	private CalcSignalRecord checkNSellPondaratedEvents(SymbolEvents symbolEvents, Date currentDate) throws IgnoredEventDateException {

		LOGGER.info("Checking pondarated sell: " + symbolEvents);

		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		isValidEventDate(currentDate, latestEventDateAndNewBuyDate);

		LOGGER.info("Selling signal at " + currentDate + " with " + symbolEvents.getSymbol());
		CalcSignalRecord sellTransactionRecord = recordSignal(TransactionType.AOUT, symbolEvents, currentDate);
		recordSchedule(sellTransactionRecord);
		
		thisPortfolio.setChanged();
		return sellTransactionRecord;

	}

	public void recordSchedule(CalcSignalRecord recordSignal) {
		thisPortfolio.getTransactionScheduler().add(recordSignal);
	}


	public CalcSignalRecord recordSignal(TransactionType movement, SymbolEvents symbolEvents, Date currentDate) {

		CalcSignalRecord calcSignalRecord = new CalcSignalRecord(thisPortfolio.getName(), currentDate, movement, symbolEvents, EmailFilterEventSource.PMAutoBuySell);
		getSignalHistory().add(calcSignalRecord);
		return calcSignalRecord;

	}
	
	public BigDecimal getNominalTransactionAmount() {
		return nominalTransactionAmount;
	}

	public BigDecimal getMinimumTransactionAmount() {
		return minimumTransactionAmount;
	}

	public int getMaximumNumberOfLines() {
		return maximumNumberOfLines;
	}

}
