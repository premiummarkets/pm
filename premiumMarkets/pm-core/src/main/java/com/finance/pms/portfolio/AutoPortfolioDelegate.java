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

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.ConfigFreePonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
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
	
	private static final BigDecimal DEFAULT_TRANSACTION_AMOUNT = new BigDecimal(2000).setScale(4);
	private static final BigDecimal MINIMUM_TRANSACTION_AMOUNT = BigDecimal.ONE;
	private static final int DEFAULT_MAXIMUM_LINES = 1;
	protected static final BigDecimal DEFAULT_INITIAL_CASH = DEFAULT_TRANSACTION_AMOUNT.multiply(new BigDecimal(DEFAULT_MAXIMUM_LINES));

	
	private TransactionHistory transactionHistory;
	
	public enum BuyStrategy { REINVEST, LIMITROWS, INFINITCASH, INFINITQUANTITY };

	protected AutoPortfolioWays thisPortfolio;

	public AutoPortfolioDelegate(AutoPortfolioWays autoPortfolio, Boolean isFileLogged) {
		this.thisPortfolio = autoPortfolio;
		Path logFilePath = Path.of(URI.create("file://" + System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + thisPortfolio.getName() + "_Log.csv"));
		if (isFileLogged && !Files.exists(logFilePath)) {
			this.log("available cash", "date", "symbol", "isin", "sharename", "currency", "movement", "quantity", "price", "amount", "events", "run timestamp");
		}
	}

	public TransactionHistory getTransactionHistory() {
		if (transactionHistory == null) transactionHistory = new TransactionHistory(thisPortfolio.getName());
		return transactionHistory;
	}

	public TransactionHistory calculate(List<SymbolEvents> listEvents, Date currentDate, BuyStrategy buyStrategy, PonderationRule buyComparator, PonderationRule sellComparator) {

		TransactionHistory thisCalculationHistory = new TransactionHistory(thisPortfolio.getName());

		LOGGER.debug("Checking events for AutoPortfolio: " + thisPortfolio.getName() + " and date " + currentDate);
		thisCalculationHistory.addAll(this.checkSellSignals(buyStrategy, currentDate, listEvents, sellComparator));
		BigDecimal canBuy = canBuy(buyStrategy);
		if (0 > BigDecimal.ZERO.compareTo(canBuy)) {
			thisCalculationHistory.addAll(this.checkBuySignals(buyStrategy, currentDate, listEvents, buyComparator));
		} else {
			LOGGER.info("Not checking buy signals because: Cash amount left: " + canBuy + " at " + currentDate + ". Buy strategy: " + buyStrategy + ", Events: " + listEvents);
		}
		
		thisPortfolio.notifyObservers(new ObserverMsg(null, ObserverMsg.ObsKey.PRGSMSG, "Setting Quotations data"));

		return thisCalculationHistory;

	}

	protected BigDecimal canBuy(BuyStrategy buyStrategy) {
		BigDecimal availableCash = BigDecimal.ZERO;
		switch (buyStrategy) {
			case LIMITROWS:
				int ownedSharesSize = thisPortfolio.getOwnedPorfolioShares().size();
				if (ownedSharesSize == DEFAULT_MAXIMUM_LINES) {
					LOGGER.info("Strategy " + buyStrategy + ", Maximum number of lines reached.");
					availableCash = BigDecimal.ZERO;
				} else if (ownedSharesSize > DEFAULT_MAXIMUM_LINES) {
					throw new RuntimeException("Strategy " + buyStrategy + ", Maximum number of lines over taken");
				} else {
					availableCash = AutoPortfolioDelegate.DEFAULT_TRANSACTION_AMOUNT;
				}
				break;
			case INFINITCASH:
			case INFINITQUANTITY:
				availableCash = AutoPortfolioDelegate.DEFAULT_TRANSACTION_AMOUNT;
				break;
			case REINVEST:
				availableCash = thisPortfolio.getAvailableCash();
				//availableCash = (availableCash.compareTo(defaultTransactionAmount) >= 0)? availableCash : BigDecimal.ZERO;
				break;
			default:
				throw new UnsupportedOperationException();
		}
		
		LOGGER.info("Strategy " + buyStrategy + ", available cash: " + availableCash);
		return availableCash;
	}

	private TransactionHistory checkBuySignals(BuyStrategy buyStrategy, Date currentDate, List<SymbolEvents> listEvents, PonderationRule symbolEventComparator) {
		
		LOGGER.info("Checking buy signals at " + currentDate + " for " + listEvents);

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

		LOGGER.debug("Threshold event: " + symbolEventsThreshold);

		SortedSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.debug("Total bullish events: " + sortedSymbolEvents);

		SortedSet<SymbolEvents> sortedSymbolEventsHead = sortedSymbolEvents.headSet(symbolEventsThreshold); //XXX PonderationRule.compare is reversed!!
		LOGGER.debug("Filtered bullish events tail: " + sortedSymbolEventsHead);
		
		if (sortedSymbolEventsHead.isEmpty()) {
			LOGGER.info("No buy signal at " + currentDate + " with " + listEvents);
		}

		TransactionHistory transactionHistory = new TransactionHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents:sortedSymbolEventsHead) {
			try {
				TransactionRecord buyTrans = checkNBuyPondaratedEvents(buyStrategy , symbolEvents, currentDate);
				if (buyTrans != null) {
					transactionHistory.add(buyTrans);
				}
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Can't buy: event invalid or already processed. " + e);
			} catch (NoCashAvailableException e) {
				LOGGER.info("Can't buy: " + symbolEvents.getStock() + " in AutoPortfolio - no cash: " + thisPortfolio.getName() + " cause " + e.getMessage());
				break;
			} catch (Exception e) {
				LOGGER.error("Can't buy: " + symbolEvents.getStock() + " in AutoPortfolio: " + thisPortfolio.getName(), e);
			}
		}

		return transactionHistory;

	}

	private TransactionRecord checkNBuyPondaratedEvents(BuyStrategy buyStrategy, SymbolEvents symbolEvents, Date currentDate) throws IgnoredEventDateException, NoCashAvailableException {

		LOGGER.info("Checking pondarated buy: " + symbolEvents);

		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		isValidEventDate(currentDate, latestEventDateAndNewBuyDate);

		//Check if already bought
		PortfolioShare alreadyBoughtShare  = thisPortfolio.getListShares().get(symbolEvents.getStock());
		if (	!BuyStrategy.INFINITQUANTITY.equals(buyStrategy) &&
				(alreadyBoughtShare != null && 
				 alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate()).compareTo(BigDecimal.ZERO) > 0)) {
			LOGGER.info("Won't buy at " + currentDate + " with " + symbolEvents.getSymbol() + ". Already bought (buy once policy).");
			return null;
		}
		
		LOGGER.info("Portfolio share is NOT owned or INFINITQUANTITY is granted: " + symbolEvents.getStock());

		isValidDateForLine(latestEventDateAndNewBuyDate, alreadyBoughtShare);
		
		try {
			LOGGER.info("Buying at " + currentDate + " with " + symbolEvents.getSymbol());
			TransactionRecord buyTransactionRecord = buy(buyStrategy, symbolEvents, currentDate);
			thisPortfolio.setChanged();

			return buyTransactionRecord;

		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.warn("Can't buy " + symbolEvents.getStock() + " in AutoPortfolio - no quotations: " + thisPortfolio.getName());
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't buy " + symbolEvents.getStock() + " in AutoPortfolio - invalid quantity: " + thisPortfolio.getName(), e);
		}

		return null;
	}

	private void isValidEventDate(Date currentDate, Date latestEventDateAndNewBuyDate) throws IgnoredEventDateException {
		LOGGER.info("Is valid event date: is event date: " + latestEventDateAndNewBuyDate + " (strictly before?) iteration current date: " + currentDate + ". ");
		if ((latestEventDateAndNewBuyDate == null) || latestEventDateAndNewBuyDate.compareTo(currentDate) >= 0) {
			throw new IgnoredEventDateException(
					"Event date: " + latestEventDateAndNewBuyDate + " and current iteration date: " + currentDate + ". " +
					"Event should be processed later: ", new Throwable());
		}
	}

	protected void isValidDateForLine(Date latestEventDateAndNewBuyDate, PortfolioShare existingPs) throws IgnoredEventDateException {
		LOGGER.info("Checking existing transactions: " + this.thisPortfolio.getTransactions());
		if (existingPs != null) {
			Date lastTransactionDate = existingPs.getLastTransactionDate();
			LOGGER.info("Event validity for line: is event date: " + latestEventDateAndNewBuyDate + " (strictly after?) last transaction: " + lastTransactionDate);
			if (!latestEventDateAndNewBuyDate.after(lastTransactionDate)) {
				throw new IgnoredEventDateException("Event (" + latestEventDateAndNewBuyDate + ") already processed: " + 
					  existingPs + " last transaction on the " + lastTransactionDate, new Throwable());
			}
		}
	}

	protected TransactionRecord buy(BuyStrategy buyStrategy, SymbolEvents symbolEvents, Date currentDate) throws InvalidAlgorithmParameterException, InvalidQuantityException, NoCashAvailableException {

		Stock stock = symbolEvents.getStock();
		// XXX what if we buy twice the same share but with a different currency?
		Currency transactionCurrency = (this.thisPortfolio.getPortfolioCurrency() == null) ? stock.getMarketValuation().getCurrency() : this.thisPortfolio.getPortfolioCurrency();

		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			BigDecimal openPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.OPEN);
			BigDecimal highPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.HIGH);
			BigDecimal lowPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.LOW);
			BigDecimal closePrice = quotations.getClosestCloseForDate(currentDate);
			double fee = 0.01;
			BigDecimal buyPrice = openPrice.add(highPrice).add(lowPrice).add(closePrice)
									.divide(new BigDecimal(4), 10, RoundingMode.HALF_EVEN)
									.multiply(new BigDecimal(1 + fee)).setScale(10, RoundingMode.HALF_EVEN);

			BigDecimal availableCash = canBuy(buyStrategy);
			if (availableCash.compareTo(MINIMUM_TRANSACTION_AMOUNT) >= 0) {
				BigDecimal requestedAmount = (availableCash.compareTo(DEFAULT_TRANSACTION_AMOUNT) >= 0)? DEFAULT_TRANSACTION_AMOUNT : availableCash;
				BigDecimal effectiveAmountWithDrawn = thisPortfolio.withdrawCash(currentDate, requestedAmount, transactionCurrency);
				BigDecimal quantity = effectiveAmountWithDrawn.divide(buyPrice, 10, RoundingMode.HALF_EVEN);
				LOGGER.info("Buying: " + quantity + " " + stock + " on the " + currentDate + ". " +
							"Requested " + transactionCurrency + " amount: " + requestedAmount + ". Effective " + thisPortfolio.getPortfolioCurrency() + "  amount: " + effectiveAmountWithDrawn + ". " +
							"Triggering event " + symbolEvents);

				if (buyPrice.compareTo(BigDecimal.ZERO) == 0) {
					throw new NoQuotationsException("Invalid stock " + stock + " with price " + buyPrice + " on " + currentDate + ". Can't be bought");
				}
				PortfolioShare portfolioShare = thisPortfolio.addOrUpdateShare(stock, quantity, currentDate, buyPrice, MonitorLevel.ANY, transactionCurrency, TransactionType.AIN);
				
				LOGGER.info("Bought: " + quantity + " of " + portfolioShare + ", quantity left : " + portfolioShare.getQuantity(DateFactory.getNowEndDate()));

				// Log
				return log("buy", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantity, buyPrice, currentDate);
				
			} else {
				throw new NoCashAvailableException("Cash left : " + availableCash + ", buyStrategy: " + buyStrategy + ", minimum required: " + MINIMUM_TRANSACTION_AMOUNT + ". Can't buy: " + symbolEvents);
			}

		} catch (NoQuotationsException e) {
			LOGGER.warn(e);
			throw new InvalidAlgorithmParameterException(e);
		}
	}

	private TransactionHistory checkSellSignals(BuyStrategy buyStrategy, Date currentDate, List<SymbolEvents> listEvents, PonderationRule symbolEventComparator) {
		
		LOGGER.info("Checking sell signals at " + currentDate + " with " + listEvents);

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
		LOGGER.debug("Threshold event: " + symbolEventThreshold);

		NavigableSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.trace("Total bearish events: " + sortedSymbolEvents);

		NavigableSet<SymbolEvents> sortedSymbolEventsHead = (NavigableSet<SymbolEvents>) sortedSymbolEvents.tailSet(symbolEventThreshold); //XXX PonderationRule.compare is reversed!!
		LOGGER.debug("Filtered bearish events head: " + sortedSymbolEventsHead);
		
		if (sortedSymbolEventsHead.isEmpty()) {
			LOGGER.info("No sell signal at " + currentDate + " with " + listEvents);
		}

		TransactionHistory transactionHistory = new TransactionHistory(this.thisPortfolio.getName());
		for (SymbolEvents symbolEvents : sortedSymbolEventsHead.descendingSet()) {
			try {
				TransactionRecord sellTransaction = checkNSellPondaratedEvents(buyStrategy, symbolEvents, currentDate);
				if (sellTransaction != null) {
					transactionHistory.add(sellTransaction);
				}
			} catch (IgnoredEventDateException e) {
				LOGGER.warn("Can't sell: event invalid or already processed. " + e);
			} catch (Exception e) {
				LOGGER.error("Can't sell: " + symbolEvents.getStock() + " in AutoPortfolio: " + thisPortfolio.getName(), e);
			}
		}

		return transactionHistory;
	}


	private TransactionRecord checkNSellPondaratedEvents(BuyStrategy buyStrategy, SymbolEvents symbolEvents, Date currentDate) throws IgnoredEventDateException {

		LOGGER.info("Checking pondarated sell: " + symbolEvents);

		Date latestEventDateAndNewBuyDate = symbolEvents.getLatestRelevantEventDate();
		isValidEventDate(currentDate, latestEventDateAndNewBuyDate);

		PortfolioShare alreadyBoughtShare = thisPortfolio.getListShares().get(symbolEvents.getStock());
		if ( alreadyBoughtShare == null || 
			  alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate()).compareTo(BigDecimal.ZERO) == 0 ) {
			LOGGER.info("Won't sell at " + currentDate + " with " + symbolEvents.getSymbol() + ": is not in owned at present.");
			return null;
		}
		
		LOGGER.info("Portfolio share is owned: " + alreadyBoughtShare);

		isValidDateForLine(latestEventDateAndNewBuyDate, alreadyBoughtShare);

		try {

			LOGGER.info("Selling at " + currentDate + " with " + symbolEvents.getSymbol());
			BigDecimal amount = (BuyStrategy.INFINITQUANTITY.equals(buyStrategy))?
					BigDecimal.valueOf((Math.max(alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate()).doubleValue(), DEFAULT_TRANSACTION_AMOUNT.doubleValue()))):
					null;
			TransactionRecord sellTransactionRecord = sell(symbolEvents, currentDate, amount, alreadyBoughtShare);
			thisPortfolio.setChanged();
			return sellTransactionRecord;

		} catch (InvalidAlgorithmParameterException e) 	{
			LOGGER.warn("Can't sell " + symbolEvents.getStock() + " from " + thisPortfolio.getName( ) + 
					" - no quotations on the " + currentDate + ": " + thisPortfolio.getListShares().get(symbolEvents.getStock()));
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't sell " + symbolEvents.getStock() + " from " + thisPortfolio.getName() + 
					" - invalid quantity on the " + currentDate + ": " + thisPortfolio.getListShares().get(symbolEvents.getStock()), e);
		} catch (Exception e) {
			LOGGER.error("Can't sell " + symbolEvents.getStock() + " from " + thisPortfolio.getName() + " on the " + currentDate + ": " + 
					thisPortfolio.getListShares().get(symbolEvents.getStock()), e);
		}

		return null;

	}

	protected TransactionRecord sell(SymbolEvents symbolEvents, Date currentDate, BigDecimal sellAmount, PortfolioShare portfolioShare) throws InvalidAlgorithmParameterException, InvalidQuantityException {

		try {
			Quotations quotations =  QuotationsFactories.getFactory().getQuotationsInstance(symbolEvents.getStock(), currentDate, true, portfolioShare.getTransactionCurrency(), ValidityFilter.CLOSE);

			BigDecimal openPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.OPEN);
			BigDecimal highPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.HIGH);
			BigDecimal lowPrice = (BigDecimal) quotations.getClosestFieldForDate(currentDate, QuotationDataType.LOW);
			BigDecimal closePrice = quotations.getClosestCloseForDate(currentDate);
			double fee = 0.01;
			BigDecimal sellPrice = openPrice.add(highPrice).add(lowPrice).add(closePrice)
									.divide(new BigDecimal(4), 10, RoundingMode.HALF_EVEN)
									.multiply(new BigDecimal(1 + fee)).setScale(10, RoundingMode.HALF_EVEN);

			BigDecimal quantityProrata;
			BigDecimal quantity = portfolioShare.getQuantity(DateFactory.getNowEndDate());
			if (sellAmount != null) {
				quantityProrata = sellAmount.divide(sellPrice, 10, RoundingMode.HALF_EVEN);
				quantityProrata = quantityProrata.min(quantity);
			} else {
				quantityProrata = quantity;
			}

			thisPortfolio.updateShare(portfolioShare, quantityProrata, currentDate, sellPrice, TransactionType.AOUT);
			LOGGER.info("Sold: " + quantityProrata + " of " + portfolioShare + ", quantity left : " + portfolioShare.getQuantity(DateFactory.getNowEndDate()));

			//log
			return log("sell", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantityProrata, sellPrice, currentDate);

		} catch (NoQuotationsException e) {
			LOGGER.warn(e);
			throw new InvalidAlgorithmParameterException(e);
		}

	}

	public void log(TransactionRecord transactionRecord) {

		BigDecimal amount = transactionRecord.getTransactionQuantity().multiply(transactionRecord.getTransactionPrice()).setScale(10, RoundingMode.HALF_EVEN);
		String eventList = (transactionRecord.getEventList() == null)?"No Event":transactionRecord.getEventList().toAutoPortfolioLog();

		this.log(
				transactionRecord.getAvailableCash().toString(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(transactionRecord.getDate()),
				transactionRecord.getStock().getSymbol(), transactionRecord.getStock().getIsin(), transactionRecord.getStock().getName(), transactionRecord.getStock().getMarketValuation().getCurrency().toString(),
				transactionRecord.getMovement(), transactionRecord.getTransactionQuantity().toString(), transactionRecord.getTransactionPrice().toString(), amount.toString(),
				eventList, LocalDateTime.now().toString());
	}


	public TransactionRecord log(String movement, AutoPortfolioWays shareList, Stock stock, SymbolEvents symbolEvents, BigDecimal quantity, BigDecimal price, Date currentDate) {

		TransactionRecord transactionRecord = 
				new TransactionRecord(
						thisPortfolio.getName(), shareList.getAvailableCash(), currentDate,
						stock, movement, quantity, price, symbolEvents, EmailFilterEventSource.PMAutoBuySell);
		getTransactionHistory().add(transactionRecord);

		this.log(transactionRecord);

		return transactionRecord;

	}

	protected void log(String availableCash, String calcDate,String symbol,String isin, String sharename, String currency, String movement, String quantity, String price, String amount, String eventList, String timeStamp) {

		File log = new File(System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + thisPortfolio.getName() + "_Log.csv");
		FileWriter fos = null;
		try {

			fos = new FileWriter(log, true);

			StringBuilder line = new StringBuilder();
			String cleanEventList= "\"" + eventList.replaceAll("\"", "'") + "\"";
			String cleanSharename = "\"" + sharename.replaceAll("\"", "'") + "\"";
			line
				.append(availableCash + ",").append(calcDate + "," + symbol + "," + isin + ",").append(cleanSharename + ",").append(currency + ",")
				.append(("sell".equals(movement)?"-":"") + quantity + ",").append(price + ",").append(("sell".equals(movement)?"-":"") + amount + ",")
				.append(timeStamp + ",").append(cleanEventList);
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
