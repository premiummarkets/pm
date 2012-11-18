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
package com.finance.pms.portfolio;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventSource;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.ConfigFreePonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.Transaction.TransactionType;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;

/**
 * @author Guillaume Thoreton
 * AutoPortfolio Delegate Implementation based on Technical Analysis events and Screening events
 *
 */
public class AutoPortfolioDelegate {
	
	public static final BigDecimal DEFAULT_TRANSACTION_AMOUNT = new BigDecimal(2000).setScale(2);
	public static final BigDecimal DEFAULT_INITIAL_CASH;
	
	private TransactionHistory transactionHistory;
	
	static {
		DEFAULT_INITIAL_CASH = DEFAULT_TRANSACTION_AMOUNT.multiply(new BigDecimal(10));
	}
	
	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolio.class);
	
	protected AutoPortfolioWays thisPortfolio;
	boolean initlog = true;


	public AutoPortfolioDelegate(AutoPortfolioWays autoPortfolio) {
		this.thisPortfolio = autoPortfolio;
	}

	public TransactionHistory getTransactionHistory() {
		if (transactionHistory == null) transactionHistory = new TransactionHistory(thisPortfolio.getName());
		return transactionHistory;
	}


	public void initLog() {
		if (initlog) {
			this.log("available cash","date", "symbol", "isin", "sharename", "movement", "quantity", "price", "amount", "eventList");
		}
		initlog = false;
	}


	public TransactionHistory calculate(List<SymbolEvents> listEvents, Date currentDate, PonderationRule buyComparator, PonderationRule sellComparator) {
	
			initLog();
			
			TransactionHistory thisCalculationHistory = new TransactionHistory(thisPortfolio.getName());
			
			LOGGER.debug("Checking events for AutoPortfolio : "+thisPortfolio.getName()+" and date "+currentDate);
			thisCalculationHistory.addAll(this.checkSellSignals(currentDate, listEvents, sellComparator));
			thisCalculationHistory.addAll(this.checkBuySignals(currentDate, listEvents, buyComparator));
			LOGGER.debug("Just checked events for AutoPortfolio : "+thisPortfolio.getName()+" and date "+currentDate);

			thisPortfolio.notifyObservers(new ObserverMsg(null, ObserverMsg.ObsKey.PRGSMSG, "Setting Quotations data"));
			
			return thisCalculationHistory;
	}
	
	
	private TransactionHistory checkBuySignals(Date currentDate, List<SymbolEvents> listEvents, PonderationRule symbolEventComparator) {

		SymbolEvents symbolEventsThreshold = 
			new SymbolEvents(
				new Stock() {

					private static final long serialVersionUID = 1L;
					
					public String getSymbol() {
						return "~";
					}
					public String getIsin() {
						return "~";
					}
				},
				new HashMap<EventKey, EventValue>(),
				new HashMap<Integer, String>(),
				EventState.STATE_TERMINATED) {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public float getWeight(PonderationRule pr) {
						if (pr instanceof ConfigFreePonderationRule) {
							return ((ConfigFreePonderationRule) pr).getBuyThreshold();
						}
						return new Float(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold());
					}
					
					@Override
					public String toString() {
						return "Event buy threshold = "+getWeight(null);
					}
				
			};
			
		LOGGER.debug("Threshold event : "+symbolEventsThreshold);
		
		SortedSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.debug("Total bullish events : " + sortedSymbolEvents);
		
		SortedSet<SymbolEvents> sortedSymbolEventsTail = sortedSymbolEvents.headSet(symbolEventsThreshold);
		LOGGER.debug("Filtered bullish events tail : "+sortedSymbolEventsTail);

		TransactionHistory transactionHistory = new TransactionHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents:sortedSymbolEventsTail) {
			try {
				 TransactionRecord buyTrans = buyShare(symbolEvents,currentDate);
				 if (buyTrans != null) {
					 transactionHistory.add(buyTrans);
				 }
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Date of event is after current date. "+e);
			}
		}
		
		return transactionHistory;
		
	}
	

	private TransactionRecord buyShare(SymbolEvents symbolEvents,Date currentDate) throws IgnoredEventDateException {
		return checkBuyability(symbolEvents, currentDate, null);
		
	}
	
	/**
	 * @param symbolEvents
	 * @param monitorLevel 
	 * @return 
	 * @throws IgnoredEventDateException 
	 */
	private TransactionRecord checkBuyability(SymbolEvents symbolEvents, Date currentDate, BigDecimal unitAmount) throws IgnoredEventDateException {

		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		LOGGER.debug("Last event date : "+latestEventDateAndNewBuyDate + " and current date : "+currentDate);
		if ((latestEventDateAndNewBuyDate == null) || latestEventDateAndNewBuyDate.after(currentDate)) {
			throw new IgnoredEventDateException("Last event date : "+latestEventDateAndNewBuyDate + " and current date : "+currentDate,new Throwable());
		}

		try {

			//check if already done 
			for (PortfolioShare alreadyBoughtShare : thisPortfolio.getListShares().values()) {
				if (alreadyBoughtShare.getStock().equals(symbolEvents.getStock()) && isInvalidBuyableDate(latestEventDateAndNewBuyDate, alreadyBoughtShare)) { 
					//already bought
					LOGGER.debug("Already bought : " + alreadyBoughtShare + " on the " + alreadyBoughtShare.getBuyDate());
					return null;
				}
			}
				
			return buy(symbolEvents, currentDate);

		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.warn("Can't buy "+symbolEvents.getStock()+" in Auto portfolio - no quotations : "+thisPortfolio.getName());
		} catch (NoCashAvailableException e) {
			LOGGER.info("Can't buy "+symbolEvents.getStock()+" in Auto portfolio - no cash: "+thisPortfolio.getName()+" cause "+e.getMessage());
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't buy "+symbolEvents.getStock()+" in Auto portfolio - invalid quantity : "+thisPortfolio.getName(),e);
		} catch (Exception e) {
			LOGGER.error("Can't buy share "+symbolEvents.getStock()+" in Auto portfolio : "+thisPortfolio.getName(),e);
		} 

		thisPortfolio.setChanged();
		
		return null;
	}

	protected boolean isInvalidBuyableDate(Date latestEventDateAndNewBuyDate, PortfolioShare alreadyBoughtShare) {
		return !latestEventDateAndNewBuyDate.before(alreadyBoughtShare.getBuyDate());
	}

	/**
	 * @param symbolEvents
	 * @param currentDate
	 * @param unitAmount 
	 * @param availableAmount
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidQuantityException
	 * @throws NoCashAvailableException 
	 */
	protected TransactionRecord buy(SymbolEvents symbolEvents, Date currentDate) throws InvalidAlgorithmParameterException, InvalidQuantityException, NoCashAvailableException {
		
			Stock stock = symbolEvents.getStock();
			//XXX what if we buy twice the same share but with a different currency?
			Currency transactionCurrency = (this.thisPortfolio.getPortfolioCurrency() == null)? stock.getMarket().getCurrency(): this.thisPortfolio.getPortfolioCurrency();
			
			synchronized (thisPortfolio) {
				
				BigDecimal availableAmount = thisPortfolio.withdrawCash(currentDate, transactionCurrency);
				
				LOGGER.debug("Buying : "+stock +" on event "+symbolEvents);	
						
				try {
					Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency);
					BigDecimal buyPrice = quotations.getCloseForDate(currentDate);		
					BigDecimal quantity = availableAmount.divide(buyPrice, 10, BigDecimal.ROUND_DOWN);
					
					PortfolioShare portfolioShare = thisPortfolio.addOrUpdateShare(stock, quantity, currentDate, buyPrice, MonitorLevel.NONE, transactionCurrency, TransactionType.AIN);
	
					//Log
					return log("buy", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantity, buyPrice, currentDate);
				} catch (NoQuotationsException e) {
					LOGGER.warn(e);
					throw new InvalidAlgorithmParameterException(e);
				}
			}
	}
	
	private TransactionHistory checkSellSignals(Date currentDate,List<SymbolEvents> listEvents, PonderationRule symbolEventComparator) {
		
		SymbolEvents symbolEventThreshold = new SymbolEvents(
					new Stock() {

						private static final long serialVersionUID = 1L;

						public String getSymbol() {
							return "0";
						}
						public String getIsin() {
							return "0";
						}
					},
					new HashMap<EventKey, EventValue>(),
					new HashMap<Integer,String>(),
					EventState.STATE_TERMINATED) {

						private static final long serialVersionUID = 1L;
						
						@Override
						public float getWeight(PonderationRule pr) {
							if (pr instanceof ConfigFreePonderationRule) {
								return ((ConfigFreePonderationRule) pr).getSellThreshold();
							}
							return new Float(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold());
						}
			
						@Override
						public String toString() {
							return "Event sell threshold = "+getWeight(null);
						}			
					};
		LOGGER.debug("Threshold event : "+symbolEventThreshold);
	
		NavigableSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.trace("Total bearish events : "+sortedSymbolEvents);
		
		NavigableSet<SymbolEvents> sortedSymbolEventsHead = (NavigableSet<SymbolEvents>) sortedSymbolEvents.tailSet(symbolEventThreshold);
		LOGGER.debug("Filtered bearish events head : "+sortedSymbolEventsHead);
		
		TransactionHistory transactionHistory = new TransactionHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents : sortedSymbolEventsHead.descendingSet()) {
			try {
				TransactionRecord sellTransaction = sellShare(symbolEvents, currentDate);
				if (sellTransaction != null) {
					transactionHistory.add(sellTransaction);
				}
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Date of event is after current date. "+e);
			}
		}
		
		return transactionHistory;
	}
	
	private TransactionRecord sellShare(SymbolEvents symbolEvents, Date currentDate) throws IgnoredEventDateException {
		return checkSellability(symbolEvents, currentDate, null);
	}


	/**
	 * @param sortedSet
	 * @param portfolioShare1
	 * @throws IgnoredEventDateException 
	 */
	private TransactionRecord checkSellability(SymbolEvents symbolEvents, Date currentDate, BigDecimal unitAmount) throws IgnoredEventDateException {

		//check if already done 
		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		if ((latestEventDateAndNewBuyDate == null) || latestEventDateAndNewBuyDate.after(currentDate)) 
			throw new IgnoredEventDateException("Last event date : "+latestEventDateAndNewBuyDate + " and current date : "+currentDate,new Throwable());

		try {
			
			if (thisPortfolio.getListShares().containsKey(symbolEvents.getStock())) {

				PortfolioShare portfolioShare = thisPortfolio.getListShares().get(symbolEvents.getStock());

				if (isInvalidSellableDate(latestEventDateAndNewBuyDate, portfolioShare)) { 
					//already sold with that signal
					LOGGER.debug("Already sold with that signal or not bought yet : " + portfolioShare + " last transaction on the " + portfolioShare.getBuyDate());
					return null;
				}

				return sell(symbolEvents, currentDate, unitAmount, portfolioShare);

			} else {
				LOGGER.debug("Nothing to sell, share "+symbolEvents.getSymbol()+ " on event "+symbolEvents+", is not in shareList.");
			}
			
		} catch (InvalidAlgorithmParameterException e) 	{
			LOGGER.warn("Can't sell "+symbolEvents.getStock()+" in Auto portfolio  - no quotations : "+thisPortfolio.getName());
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't sell "+symbolEvents.getStock()+" in Auto portfolio  - Invalid quantity : "+thisPortfolio.getName(),e);
		} catch (Exception e) {
			LOGGER.error("Can't sell share "+symbolEvents.getStock()+" in Auto portfolio : "+thisPortfolio.getName(),e);
		}
		
		thisPortfolio.setChanged();
		
		return null;

	}

	protected boolean isInvalidSellableDate(Date latestEventDateAndNewBuyDate, PortfolioShare portfolioShare) {
		return !latestEventDateAndNewBuyDate.after(portfolioShare.getBuyDate());
	}

	/**
	 * @param symbolEvents
	 * @param currentDate
	 * @param unitAmount
	 * @param portfolioShare
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidQuantityException 
	 */
	protected TransactionRecord sell(SymbolEvents symbolEvents, Date currentDate, BigDecimal unitAmount, PortfolioShare portfolioShare) throws InvalidAlgorithmParameterException, InvalidQuantityException {
	
			try {
				Quotations quotations =  QuotationsFactories.getFactory().getQuotationsInstance(symbolEvents.getStock(), currentDate, true, portfolioShare.getTransactionCurrency());
				BigDecimal lastPrice = quotations.getCloseForDate(currentDate);
				
				BigDecimal quantityProrata;
				if (unitAmount != null) {
					quantityProrata = unitAmount.divide(lastPrice, 5, BigDecimal.ROUND_DOWN);
					quantityProrata = quantityProrata.min(portfolioShare.getQuantity());	
				} else {
					quantityProrata = portfolioShare.getQuantity();
				}

				synchronized (this) {
					thisPortfolio.removeOrUpdateShare(portfolioShare, quantityProrata, currentDate, lastPrice, TransactionType.AOUT);
					LOGGER.debug("Share sold : "+portfolioShare+", quantity : "+quantityProrata+", quantity left : "+portfolioShare.getQuantity());

					//log
					return log("sell", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantityProrata, lastPrice, currentDate);
				}
			} catch (NoQuotationsException e) {
				LOGGER.warn(e);
				throw new InvalidAlgorithmParameterException(e);
			}

	}
	
	public void log(TransactionRecord transactionRecord) {
		
		BigDecimal amount = transactionRecord.getTransactionQuantity().multiply(transactionRecord.getTransactionPrice());
		String eventList = (transactionRecord.getEventList() == null)?"":transactionRecord.getEventList().toString();
		
		this.log(
				transactionRecord.getAvailableCash().toString(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(transactionRecord.getDate()),
				transactionRecord.getStock().getSymbol(), transactionRecord.getStock().getIsin(),transactionRecord.getStock().getName(), 
				transactionRecord.getMovement(), transactionRecord.getTransactionQuantity().toString(), transactionRecord.getTransactionPrice().toString(), amount.toString(), 
				eventList);
	}
	

	public TransactionRecord log(String movement, AutoPortfolioWays shareList, Stock stock, SymbolEvents symbolEvents, BigDecimal quantity, BigDecimal price, Date currentDate) {
		
		TransactionRecord transactionRecord = 
					new TransactionRecord(
							thisPortfolio.getName(), shareList.getAvailableCash(), currentDate, stock, 
							movement, quantity, price, symbolEvents, EventSource.PMAutoBuySell);
		
		getTransactionHistory().add(transactionRecord);
	
//		this.log(
//				shareList.getAvailableCash().toString(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate),
//				symbolEvents.getStock().getSymbol(),symbolEvents.getStock().getIsin(),
//				symbolEvents.getStock().getName(), movement,
//				portfolioShare.getQuantity().toString(), price.toString(), portfolioShare.getQuantity().multiply(price).toString(),
//				symbolEvents.toAutoPortfolioLog());
		this.log(transactionRecord);
		
		return transactionRecord;
		
	}
	
	protected void log(String availableCash, String calcDate,String symbol,String isin, String sharename, String movement, String quantity, String price, String amount, String eventList) {

		File log = new File(System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + thisPortfolio.getName()+"_Log.csv");
		FileWriter fos = null;
		try {
			fos = new FileWriter(log,true);

			StringBuilder line = new StringBuilder();
			String cleanEventList= "'"+eventList.replace(","," ").replace("\n"," ")+"'";
			String cleanSharename = sharename.replace(","," ");
			line.append(availableCash+",").append(calcDate+","+symbol+","+isin+",").append(cleanSharename+",").append(movement+",").append(quantity+",").append(price+",").append(amount+",").append(cleanEventList);
			LOGGER.debug(line);
			
			fos.write(line.toString()+"\n");
			fos.flush();

		} catch (Throwable e) {
			LOGGER.error(e, e);
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (Throwable e) {
				LOGGER.error(e, e);
			} finally {
				log = null;
				fos = null;
			}
		}

	}	
	
}
