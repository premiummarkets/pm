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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
	
	private final BigDecimal nominalTransactionAmount; // = new BigDecimal(2000).setScale(4);
	private final BigDecimal minimumTransactionAmount; //= BigDecimal.ONE;
	private final int maximumNumberOfLines; // = 1;
	protected final BigDecimal initialCash; //= minTransactionAmount.multiply(new BigDecimal(maximumNumberOfLines));
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	
	private TransactionHistory transactionHistory;
	
	public enum BuyStrategy { 
		REINVEST, //The profit is reinvested in new transactions. Starting with nominalTransactionAmount*maximumNumberOfLines of cash: Buy only if not owned. Sell the full line.
		LIMITROWS, //Buys of nominalTransactionAmount are maid until the maximumNumberOfLines is reached: Buy only if not owned. Sell the full line.
		INFINITCASH, //Buys of nominalTransactionAmount are maid on buy events if not already bought: Buy only if not owned. Sell the full line.
		INFINITQUANTITY //Buys of nominalTransactionAmount are maid on buy events disregarding everything else: Buy even if owned. Sells for the nominalTransactionAmount. 
		};

	protected AutoPortfolioWays thisPortfolio;

	public AutoPortfolioDelegate(AutoPortfolioWays autoPortfolio, Boolean isFileLogged) {
		
		nominalTransactionAmount = new BigDecimal(MainPMScmd.getMyPrefs().get("autoporfolio.nominal.transaction.amount", "2000")).setScale(4);
		minimumTransactionAmount = new BigDecimal(MainPMScmd.getMyPrefs().get("autoporfolio.min.transaction.amount", "1")).setScale(4);
		maximumNumberOfLines = Integer.valueOf(MainPMScmd.getMyPrefs().get("autoporfolio.max.lines", "5"));
		initialCash = nominalTransactionAmount.multiply(new BigDecimal(maximumNumberOfLines));
		
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

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking events for AutoPortfolio: " + thisPortfolio.getName() + " and date " + currentDate);
		thisCalculationHistory.addAll(checkSellSignals(buyStrategy, currentDate, listEvents, sellComparator));
		BigDecimal canBuy = canBuy(buyStrategy);
		if (0 > BigDecimal.ZERO.compareTo(canBuy)) {
			thisCalculationHistory.addAll(checkBuySignals(buyStrategy, currentDate, listEvents, buyComparator));
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
				if (ownedSharesSize == maximumNumberOfLines) {
					LOGGER.info("Strategy " + buyStrategy + ", Maximum number of lines reached.");
					availableCash = BigDecimal.ZERO;
				} else if (ownedSharesSize > maximumNumberOfLines) {
					throw new RuntimeException("Strategy " + buyStrategy + ", Maximum number of lines over taken");
				} else {
					availableCash = nominalTransactionAmount;
				}
				break;
			case INFINITCASH:
			case INFINITQUANTITY:
				availableCash = nominalTransactionAmount;
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

		LOGGER.info("Buy event: Ponderation rule " + symbolEventComparator.getClass().getSimpleName() + ", threshold " + symbolEventsThreshold);

		SortedSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Total bullish events: " + sortedSymbolEvents);

		SortedSet<SymbolEvents> sortedSymbolEventsHead = sortedSymbolEvents.headSet(symbolEventsThreshold); //XXX PonderationRule.compare is reversed!!
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Filtered bullish events tail: " + sortedSymbolEventsHead);
		
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
		PortfolioShare alreadyBoughtShare = thisPortfolio.getListShares().get(symbolEvents.getStock());
		if (
				!BuyStrategy.INFINITQUANTITY.equals(buyStrategy) && (alreadyBoughtShare != null && alreadyBoughtShare.isOwned(DateFactory.getNowEndDate(), true))
		) {
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

		} catch (NoQuotationsException e) {
			LOGGER.warn("Can't buy " + symbolEvents.getStock() + " in AutoPortfolio - no quotations: " + thisPortfolio.getName());
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't buy " + symbolEvents.getStock() + " in AutoPortfolio - invalid quantity: " + thisPortfolio.getName(), e);
		}

		return null;
	}

	/**
	 * To be valid the event date as to be at most on currentDate - 1 
	 * @param currentDate
	 * @param latestEventDateAndNewBuyDate
	 * @throws IgnoredEventDateException
	 */
	private void isValidEventDate(Date currentDate, Date latestEventDateAndNewBuyDate) throws IgnoredEventDateException {
		boolean isInvalidEvent = (latestEventDateAndNewBuyDate == null) || latestEventDateAndNewBuyDate.compareTo(currentDate) >= 0;
		LOGGER.info("Is valid event date: is event date: " + df.format(latestEventDateAndNewBuyDate) + " (strictly before?) iteration current date: " + df.format(currentDate) + ": " + !isInvalidEvent);
		if (isInvalidEvent) {
			throw new IgnoredEventDateException(
					"Event date: " + df.format(latestEventDateAndNewBuyDate) + " and current iteration date: " + df.format(currentDate) + ". " +
					"Event should be processed later: ", new Throwable());
		}
	}

	protected void isValidDateForLine(Date latestEventDateAndNewBuyDate, PortfolioShare existingPs) throws IgnoredEventDateException {
		LOGGER.info("Checking existing transactions: " + this.thisPortfolio.getTransactions());
		if (existingPs != null) {
			Date lastTransactionDate = existingPs.getLastTransactionDate();
			LOGGER.info("Event validity for line: is event date: " + latestEventDateAndNewBuyDate + " (after or equal?) last transaction: " + lastTransactionDate);
			if (latestEventDateAndNewBuyDate.compareTo(lastTransactionDate) < 0) {
				throw new IgnoredEventDateException("Event (" + latestEventDateAndNewBuyDate + ") already processed: " + 
					  existingPs + " last transaction on the " + lastTransactionDate, new Throwable());
			}
		}
	}

	protected TransactionRecord buy(BuyStrategy buyStrategy, SymbolEvents symbolEvents, Date currentDate) throws InvalidQuantityException, NoCashAvailableException, NoQuotationsException {

		Stock stock = symbolEvents.getStock();
		// XXX what if we buy twice the same share but with a different currency?
		Currency transactionCurrency = (this.thisPortfolio.inferPortfolioCurrency() == null) ? stock.getMarketValuation().getCurrency() : this.thisPortfolio.inferPortfolioCurrency();

		Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
		BigDecimal closePrice = quotations.get(quotations.size()-1).getCloseSplit();
		double fee = 0.01;
		BigDecimal buyPrice = closePrice.multiply(new BigDecimal(1 + fee)).setScale(10, RoundingMode.HALF_EVEN);
		
//		QuotationUnit hackLastQuotationUnitUnconstrained = DataSource.getInstance().hackQuotationUnitAtCurrentDateUnconstrained(stock, currentDate);
//		BigDecimal openPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getOpenSplit();
//		BigDecimal highPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getHighSplit();
//		BigDecimal lowPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getLowSplit();
//		BigDecimal closePrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getCloseSplit();
//		double fee = 0.0; //0.01;
//		BigDecimal buyPrice = openPrice.add(highPrice).add(lowPrice).add(closePrice)
//								.divide(new BigDecimal(4), 10, RoundingMode.HALF_EVEN)
//								.multiply(new BigDecimal(1 + fee)).setScale(10, RoundingMode.HALF_EVEN);


		BigDecimal availableCash = canBuy(buyStrategy);
		if (availableCash.compareTo(minimumTransactionAmount) >= 0) {
			BigDecimal requestedAmount = (availableCash.compareTo(nominalTransactionAmount) >= 0)? nominalTransactionAmount : availableCash;
			BigDecimal effectiveAmountWithDrawn = thisPortfolio.withdrawCash(currentDate, requestedAmount, transactionCurrency);
			BigDecimal quantity = effectiveAmountWithDrawn.divide(buyPrice, 10, RoundingMode.HALF_EVEN);
			LOGGER.info("Buying: " + quantity + " of " + stock + " on the " + currentDate + ". " +
						"Requested " + transactionCurrency + " amount: " + requestedAmount + ". " +
						"Effective " + thisPortfolio.inferPortfolioCurrency() + " amount: " + effectiveAmountWithDrawn + ". " +
						"Triggering event " + symbolEvents);

			if (buyPrice.compareTo(BigDecimal.ZERO) == 0) {
				throw new NoQuotationsException("Invalid stock " + stock + " with price " + buyPrice + " on " + currentDate + ". Can't be bought");
			}
			PortfolioShare portfolioShare = thisPortfolio.addOrUpdateShare(stock, quantity, currentDate, buyPrice, MonitorLevel.ANY, transactionCurrency, TransactionType.AIN);
			
			LOGGER.info("Bought: " + quantity + " of " + portfolioShare + ", quantity left: " + portfolioShare.getQuantity(DateFactory.getNowEndDate(), false));

			// Log
			return log("buy", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantity, buyPrice, currentDate);
			
		} else {
			throw new NoCashAvailableException("Cash left : " + availableCash + ", buyStrategy: " + buyStrategy + ", minimum required: " + minimumTransactionAmount + ". Can't buy: " + symbolEvents);
		}
			
	}

	private TransactionHistory checkSellSignals(BuyStrategy buyStrategy, Date currentDate, List<SymbolEvents> listEvents, PonderationRule symbolEventComparator) {
		
		LOGGER.info("Checking sell signals at " + currentDate + " with " + listEvents);

		SymbolEvents symbolEventsThreshold = new SymbolEvents(
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
		LOGGER.info("Sell event: Ponderation rule " + symbolEventComparator.getClass().getSimpleName() + ", threshold " + symbolEventsThreshold);

		NavigableSet<SymbolEvents> sortedSymbolEvents = new TreeSet<SymbolEvents>(symbolEventComparator);
		sortedSymbolEvents.addAll(listEvents);
		LOGGER.trace("Total bearish events: " + sortedSymbolEvents);

		NavigableSet<SymbolEvents> sortedSymbolEventsHead = (NavigableSet<SymbolEvents>) sortedSymbolEvents.tailSet(symbolEventsThreshold); //XXX PonderationRule.compare is reversed!!
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Filtered bearish events head: " + sortedSymbolEventsHead);
		
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
		if ( alreadyBoughtShare == null || !alreadyBoughtShare.isOwned(DateFactory.getNowEndDate(), true) ) {
			LOGGER.info("Won't sell at " + currentDate + " with " + symbolEvents.getSymbol() + ": is not in owned at present.");
			return null;
		}
		
		LOGGER.info("Portfolio share is owned: " + alreadyBoughtShare);

		isValidDateForLine(latestEventDateAndNewBuyDate, alreadyBoughtShare);

		try {

			LOGGER.info("Selling at " + currentDate + " with " + symbolEvents.getSymbol());
			BigDecimal amount = (BuyStrategy.INFINITQUANTITY.equals(buyStrategy))?
					BigDecimal.valueOf((Math.max(alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate(), false).doubleValue(), nominalTransactionAmount.doubleValue()))):
					null;
			TransactionRecord sellTransactionRecord = sell(symbolEvents, currentDate, amount, alreadyBoughtShare);
			thisPortfolio.setChanged();
			return sellTransactionRecord;

		} catch (NoQuotationsException e) 	{
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

	protected TransactionRecord sell(SymbolEvents symbolEvents, Date currentDate, BigDecimal sellAmount, PortfolioShare portfolioShare) throws InvalidQuantityException, NoQuotationsException {

		Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(portfolioShare.getStock(), currentDate, true, portfolioShare.getTransactionCurrency(), ValidityFilter.CLOSE);
		BigDecimal closePrice = quotations.get(quotations.size()-1).getCloseSplit();
		double fee = 0.01;
		BigDecimal sellPrice = closePrice.multiply(new BigDecimal(1 - (fee/(1+fee)))).setScale(10, RoundingMode.HALF_EVEN); //In reality the price is unknown at that point of iteration as this is the next day market price ..
		
//		QuotationUnit hackLastQuotationUnitUnconstrained = DataSource.getInstance().hackQuotationUnitAtCurrentDateUnconstrained(stock, currentDate);
//		BigDecimal openPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getOpenSplit();
//		BigDecimal highPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getHighSplit();
//		BigDecimal lowPrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getLowSplit();
//		BigDecimal closePrice = (BigDecimal) hackLastQuotationUnitUnconstrained.getCloseSplit();
//		double fee = 0.0; //0.01;
//		BigDecimal sellPrice = openPrice.add(highPrice).add(lowPrice).add(closePrice)
//								.divide(new BigDecimal(4), 10, RoundingMode.HALF_EVEN)
//								.multiply(new BigDecimal(1 - (fee/(1+fee)) )).setScale(10, RoundingMode.HALF_EVEN);

		BigDecimal quantityProrata;
		BigDecimal quantity = portfolioShare.getQuantity(DateFactory.getNowEndDate(), false);
		if (sellAmount != null) {
			quantityProrata = sellAmount.divide(sellPrice, 10, RoundingMode.HALF_EVEN);
			quantityProrata = quantityProrata.min(quantity);
		} else {
			quantityProrata = quantity;
		}

		thisPortfolio.updateShare(portfolioShare, quantityProrata, currentDate, sellPrice, TransactionType.AOUT);
		LOGGER.info("Sold: " + quantityProrata + " of " + portfolioShare + ", quantity left: " + portfolioShare.getQuantity(DateFactory.getNowEndDate(), false));

		//log
		return log("sell", thisPortfolio, portfolioShare.getStock(), symbolEvents, quantityProrata, sellPrice, currentDate);

	}

	public void log(TransactionRecord transactionRecord) {

		BigDecimal amount = transactionRecord.getTransactionQuantity().multiply(transactionRecord.getTransactionPrice()).setScale(10, RoundingMode.HALF_EVEN);
		String eventList = (transactionRecord.getEventList() == null)?"No Event":transactionRecord.getEventList().toAutoPortfolioLog();

		log(
				transactionRecord.getAvailableCash().toString(), new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(transactionRecord.getDate()),
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

		log(transactionRecord);

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
			if (LOGGER.isDebugEnabled()) LOGGER.debug(line);

			fos.write(line.toString() + "\n");
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
