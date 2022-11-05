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
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionComparator;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class Portfolio.
 * 
 * @author Guillaume Thoreton
 * Portfolio is a generic Portfolio
 * 
 */
@Entity
public class Portfolio extends AbstractSharesList {

	private PonderationRule buyPonderationRule;
	private PonderationRule sellPonderationRule;
	private Currency portfolioCurrency;
	private SortedSet<TransactionElement> transactions;

	private Boolean uiDirty = true;
	private Boolean latestTransactionsOnly = false;

	protected Portfolio() {
		super();
		this.transactions = new TreeSet<TransactionElement>();
	}

	public Portfolio(Portfolio portfolio) {
		super(portfolio);
		this.buyPonderationRule = portfolio.buyPonderationRule;
		this.sellPonderationRule = portfolio.sellPonderationRule;
		this.portfolioCurrency = portfolio.portfolioCurrency;
		this.transactions = new TreeSet<TransactionElement>();
		for (TransactionElement transactionElement : portfolio.getTransactions()) {
			this.transactions.add(new TransactionElement(transactionElement));
		}
	}

	public Portfolio(String name, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, Currency portfolioCurrency) {
		super(name);
		this.transactions = new TreeSet<TransactionElement>();
		this.buyPonderationRule =  buyPonderationRule;
		this.sellPonderationRule = sellPonderationRule;
		this.portfolioCurrency = (portfolioCurrency == null || portfolioCurrency.equals(Currency.NAN))?null:portfolioCurrency;//TODO use NAN currency instead of null
	}

	public void resetManualAlerts(PortfolioShare portfolioShare, AbstractSharesList sourcePortfolio) {

		PortfolioShare oldPortfolioShare;
		if (sourcePortfolio != null && (oldPortfolioShare = sourcePortfolio.getShareForSymbolAndIsin(portfolioShare.getSymbol(), portfolioShare.getIsin())) != null) {
			for (AlertOnThreshold alert: new AlertsMgrDelegate(oldPortfolioShare).getAlertsOnThresholdFor(AlertOnThresholdType.MANUALUP)) {
				new AlertsMgrDelegate(portfolioShare).addAlertOnThreshold(alert.getThresholdType(), alert.getValue(), alert.getAlertType(), alert.getOptionalMessage());
			}
			for (AlertOnThreshold alert: new AlertsMgrDelegate(oldPortfolioShare).getAlertsOnThresholdFor(AlertOnThresholdType.MANUALDOWN)) {
				new AlertsMgrDelegate(portfolioShare).addAlertOnThreshold(alert.getThresholdType(), alert.getValue(), alert.getAlertType(), alert.getOptionalMessage());
			}
		}

	}

	public PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date currentDate, BigDecimal buyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException {

		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, trCurrency);
		if (quantity.compareTo(BigDecimal.ZERO) > 0 && buyPrice.compareTo(BigDecimal.ZERO) > 0) {
			shareTransaction(portfolioShare, quantity, currentDate, buyPrice, trType);
		}
		new AlertsMgrDelegate(portfolioShare).addBuyAlerts(buyPrice, currentDate);
		if (portfolioShare.getQuantity(currentDate).compareTo(BigDecimal.ZERO) > 0) portfolioShare.setMonitorLevel(mLevel);

		return portfolioShare;

	}

	private void shareTransaction(PortfolioShare recipientPS, BigDecimal quantity, Date buyDate, BigDecimal lastQuotation, TransactionType movement) throws InvalidQuantityException {

		Transaction transaction = new Transaction(quantity, lastQuotation, movement, buyDate);
		if (transaction.getTransactionSharePrice().compareTo(BigDecimal.ZERO) == 0) {
			throw new InvalidQuantityException("The amount is too small. Amount must be >= 0.0001 in transaction "+transaction, new Throwable());
		}
		if (transaction.getQuantity().compareTo(BigDecimal.ZERO) == 0 ) {
			LOGGER.warn("Ignoring. Trying to sell with a quantity of zero : "+transaction+" and "+recipientPS);
		}

		TransactionElement transactionElement = recipientPS.createTransactionElement(transaction);
		this.transactions.add(transactionElement);

	}

	public PortfolioShare addOrUpdateShareForQuantity(Stock stock, BigDecimal quantity, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException, NoQuotationsException  {

		BigDecimal valueAtDate = BigDecimal.ZERO;
		if (quantity.compareTo(BigDecimal.ZERO) > 0) {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			valueAtDate = quotations.getClosestCloseForDate(currentDate);
		}

		return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
	}

	public PortfolioShare addOrUpdateShareForAmount(Stock stock, BigDecimal unitAmount, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException {

		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			BigDecimal valueAtDate = quotations.getClosestCloseForDate(currentDate);
			BigDecimal quantity = unitAmount.divide(valueAtDate, 10, RoundingMode.HALF_EVEN);

			return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
		} catch (NoQuotationsException e) {
			throw new InvalidAlgorithmParameterException(e);
		}

	}


	public PortfolioShare addOrUpdateShareWithoutTransaction(Stock stock, String account, Currency transactionCurrency, Date currentDate) {

		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, transactionCurrency);
		new AlertsMgrDelegate(portfolioShare).addBuyAlerts(portfolioShare.getPriceClose(currentDate, transactionCurrency), currentDate);
		portfolioShare.setExternalAccount(account);
		if (portfolioShare.getQuantity(currentDate).compareTo(BigDecimal.ZERO) > 0) {
			portfolioShare.setMonitorLevel(MonitorLevel.BEARISH);
		} else {
			portfolioShare.setMonitorLevel(MonitorLevel.NONE);
		}

		return portfolioShare;

	}

	public void updateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException {
		shareTransaction(portfolioShare, quantity, currentDate, trPrice, trType);
		new AlertsMgrDelegate(portfolioShare).addBuyAlerts(trPrice, currentDate);
	}

	@Lob
	private PonderationRule getBuyPonderationRule() {
		return buyPonderationRule;
	}

	@Transient
	public PonderationRule getNonNullBuyPonderationRule() {
		if (buyPonderationRule == null) {
			EventSignalConfig eventConfig = (EventSignalConfig)ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);
			Integer sellEvtThresh = eventConfig.getSellEventTriggerThreshold();
			Integer buyEvtThresh = eventConfig.getBuyEventTriggerThreshold();
			PonderationRule defaultBuyPonderationRule = eventConfig.getNewBuyPonderationRule(sellEvtThresh,buyEvtThresh);
			LOGGER.warn("No buy weighting rule for "+this.name+" the config rule will be used "+defaultBuyPonderationRule);
			return defaultBuyPonderationRule;
		}
		return buyPonderationRule;
	}

	public void setBuyPonderationRule(PonderationRule buyPonderationRule) {
		this.buyPonderationRule = buyPonderationRule;
	}

	@Lob
	private PonderationRule getSellPonderationRule() {
		return sellPonderationRule;
	}

	@Transient
	public PonderationRule getNonNullSellPonderationRule() {
		if (sellPonderationRule == null) {
			EventSignalConfig eventConfig = (EventSignalConfig)ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);
			Integer sellEvtThresh = eventConfig.getSellEventTriggerThreshold();
			Integer buyEvtThresh = eventConfig.getBuyEventTriggerThreshold();
			PonderationRule defaultSellPonderationRule = eventConfig.geNewtSellPonderationRule(sellEvtThresh,buyEvtThresh);
			LOGGER.warn("No sell weighting rule for " + this.name + " the config rule will be used " + defaultSellPonderationRule);
			return defaultSellPonderationRule;
		}
		return sellPonderationRule;
	}

	public void setSellPonderationRule(PonderationRule sellPonderationRule) {
		this.sellPonderationRule = sellPonderationRule;
	}

	@Enumerated(EnumType.STRING)
	//TODO NAN currency
	public Currency getPortfolioCurrency() {
		return portfolioCurrency;
	}

	@SuppressWarnings("unused")
	private void setPortfolioCurrency(Currency portfolioCurrency) {
		this.portfolioCurrency = portfolioCurrency;
	}

	protected PortfolioShare getOrCreatePortfolioShare(Stock stock, Currency transactionCurrency) {

		PortfolioShare portfolioShare = getShareForSymbolAndIsin(stock.getSymbol(), stock.getIsin());
		if (portfolioShare == null) {
			portfolioShare = new PortfolioShare(this, stock, MonitorLevel.NONE, transactionCurrency);
			addShareToList(portfolioShare);
		}

		return portfolioShare;

	}

	@Transient
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate) {
		return getValue(currentStartDate, currentEndDate, inferPortfolioCurrency());
	}

	@Transient
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate, Currency targetCurrency) {

		BigDecimal valueForDate = BigDecimal.ZERO.setScale(4);
		for (PortfolioShare portfolioShare: this.getListShares().values()) {
			BigDecimal psValueForDate = portfolioShare.getValue(currentStartDate, currentEndDate, targetCurrency);
			valueForDate = valueForDate.add(psValueForDate);
		}
		return valueForDate;
	}


	public Currency inferPortfolioCurrency() {
		Currency currency;
		if (this.portfolioCurrency == null) {//Portfolio potentially hosting multiple transaction : we convert the total to EUR
			currency = Currency.EUR;
		} else {// One currency portfolio
			currency = this.portfolioCurrency;
		}
		return currency;
	}

	@Transient
	public BigDecimal getGainTotal(Date currentStartDate, Date currentEndDate) {
		BigDecimal value = this.getValue(currentStartDate, currentEndDate);
		BigDecimal totalOutAmountEver = getTotalOutAmountEver(currentStartDate, currentEndDate);
		BigDecimal totalInAmountEver = getTotalInAmountEver(currentStartDate, currentEndDate);
		return value.add(totalOutAmountEver).subtract(totalInAmountEver);
	}

	@Transient
	public BigDecimal getGainTotalPercent(Date currentStartDate, Date currentEndDate) {
		BigDecimal basis = this.getBasis(currentStartDate, currentEndDate);
		if (basis.compareTo(BigDecimal.ZERO) == 0) {
			if (this.getListShares().size() > 0) LOGGER.warn("getGainTotalPercent : Basis is zero for Portfolio "+this.name+" using dates from "+currentStartDate + " to " + currentEndDate+". Also not empty.");
			return BigDecimal.ZERO;
		}
		BigDecimal gainAmountForDate = this.getGainTotal(currentStartDate, currentEndDate);
		return gainAmountForDate.divide(basis, 10, RoundingMode.HALF_EVEN);
	}

	@Transient
	public BigDecimal getGainUnReal(Date currentStartDate, Date currentEndDate) {
		return this.getValue(currentStartDate, currentEndDate).subtract(this.getBasis(currentStartDate, currentEndDate));
	}

	@Transient
	public BigDecimal getGainUnRealPercent(Date currentStartDate, Date currentEndDate) {
		BigDecimal basis = this.getBasis(currentStartDate, currentEndDate);
		if (basis.compareTo(BigDecimal.ZERO) == 0) {
			if (this.getListShares().size() > 0) LOGGER.warn("getGainUnRealPercent : Total basis in is zero for Portfolio "+this.name+" using end dates "+currentStartDate + " to " + currentEndDate+". Also not empty.");
			return BigDecimal.ZERO;
		}
		BigDecimal gainAmountForDate = this.getGainUnReal(currentStartDate, currentEndDate);
		return gainAmountForDate.divide(basis, 10, RoundingMode.HALF_EVEN);
	}

	public void rawRemoveShare(PortfolioShare portfolioShare) {

		removeShareFromList(portfolioShare);

		Set<TransactionElement> toRemove = new HashSet<TransactionElement>();
		for (TransactionElement transaction : transactions) {
			if (transaction.getStock().equals(portfolioShare.getStock())) {
				toRemove.add(transaction);
			}
		}
		transactions.removeAll(toRemove);
	}

	@OneToMany(mappedBy = "portfolio", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Sort(type = SortType.COMPARATOR, comparator = TransactionComparator.class)
	@Fetch(FetchMode.SELECT)
	public SortedSet<TransactionElement> getTransactions() {
		return transactions;
	}

	@SuppressWarnings("unused")
	private void setTransactions(SortedSet<TransactionElement> transactions) {
		this.transactions = transactions;
	}

	@Transient
	public Date getLastDateTransactionFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate) {
		Date ret = new Date(0);
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret = te.getDate();
			}
		}
		return ret;
	}

	@Transient
	public SortedSet<TransactionElement> getTransactionsFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate) {
		SortedSet<TransactionElement> ret = new TreeSet<TransactionElement>();
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret.add(te);
			}
		}
		return ret;
	}

	@Transient
	public BigDecimal getTotalInAmountEver(Date currentStartDate, Date currentEndDate) {
		return getTotalInAmountEver(currentStartDate, currentEndDate, inferPortfolioCurrency());
	}

	@Transient
	public BigDecimal getTotalInAmountEver(Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal ret = BigDecimal.ZERO;
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.transactionType().equals(TransactionType.AIN)) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			};
		}
		return ret;
	}

	@Transient
	public BigDecimal getTotalOutAmountEver(Date currentStartDate, Date currentEndDate) {
		return getTotalOutAmountEver(currentStartDate, currentEndDate, inferPortfolioCurrency());
	}

	@Transient
	public BigDecimal getTotalOutAmountEver(Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal ret = BigDecimal.ZERO;
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.transactionType().equals(TransactionType.AOUT)) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			};
		}
		return ret.abs();
	}


	public BigDecimal getBasis(Date currentStartDate, Date currentEndDate) {

		BigDecimal ret = BigDecimal.ZERO;
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			ret = ret .add(getBasisFor(portfolioShare, currentStartDate, currentEndDate, inferPortfolioCurrency()));
		}
		return ret;
	}

	@Override
	public BigDecimal getCashInFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal ret = BigDecimal.ZERO;
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.transactionType().equals(TransactionType.AIN) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			}
		}
		return ret;
	}

	@Override
	public BigDecimal getCashOutFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal ret = BigDecimal.ZERO;
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.transactionType().equals(TransactionType.AOUT) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			}
		}
		return ret.abs();
	}

	@Override
	public BigDecimal getQuantityFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate) {
		BigDecimal ret = BigDecimal.ZERO;
		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret = ret.add(te.getQuantity());
			}
		}
		return ret;
	}

	public void rawAddTransaction(TransactionElement element) {
		element.setPortfolio(this);
		this.transactions.add(element);
	}

	@Override
	public BigDecimal getBasisFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal priceAvgBuyFor = getPriceAvgBuyFor(portfolioShare, currentStartDate, currentEndDate, targetCurrency);
		BigDecimal quantityFor = this.getQuantityFor(portfolioShare, currentStartDate, currentEndDate);
		return priceAvgBuyFor.multiply(quantityFor).setScale(10, RoundingMode.HALF_EVEN);

	}

	@Override
	public BigDecimal getPriceAvgBuyFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency) {
		BigDecimal totalMoneyInvested = BigDecimal.ZERO;
		BigDecimal totalQuantityBought = BigDecimal.ZERO;

		for (TransactionElement te : headTransactionsTo(currentStartDate, currentEndDate)) {
			if (te.transactionType().equals(TransactionType.AIN) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				totalMoneyInvested = totalMoneyInvested.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
				totalQuantityBought = totalQuantityBought.add(te.getQuantity());
			}
		}

		if (totalQuantityBought.compareTo(BigDecimal.ZERO) == 0) {
			LOGGER.debug("getPriceAvgBuyFor : Bought Transaction sum to zero for "+portfolioShare);
			return BigDecimal.ZERO;
		} else {
			return totalMoneyInvested.divide(totalQuantityBought, 10, RoundingMode.HALF_EVEN);
		}

	}

	public SortedSet<TransactionElement> headTransactionsTo(Date currentStartDate, Date currentEndDate) {
		if (isLatestTransactionsOnly()) {
			return headTransactionsTo(latestTransactions(), currentStartDate, currentEndDate);
		}
		return headTransactionsTo(transactions, currentStartDate, currentEndDate);
	}

	public SortedSet<TransactionElement> headTransactionsTo(SortedSet<TransactionElement> inputTransactions, Date currentStartDate, Date currentEndDate) {
		if (currentStartDate == null || currentStartDate.equals(DateFactory.dateAtZero())) {
			return inputTransactions.headSet(new TransactionElement(null,null, null, currentEndDate, null, null, null)); 
		} else {
			Calendar currentDateCal = Calendar.getInstance();
			currentDateCal.setTime(currentStartDate);
			currentDateCal.add(Calendar.DAY_OF_YEAR, -1);
			//The id (marker) has the last time stamp (milli since 1970) and hence is the last transaction for any days (it being start or end date)
			return inputTransactions.subSet(new TransactionElement(null,null, null, currentDateCal.getTime(), null, null, null), new TransactionElement(null,null, null, currentEndDate, null, null, null));
		}
	}

	public SortedSet<TransactionElement> latestTransactions( ) {
		Map<Stock, List<TransactionElement>> stocksTransactions = new HashMap<>();
		Map<Stock, BigDecimal> stocksQuantity = new HashMap<>();
		for(TransactionElement transaction: transactions) {
			Stock stock = transaction.getStock();
			List<TransactionElement> stockTransactions = stocksTransactions.get(stock);
			if (stockTransactions == null) {
				stocksTransactions.put(stock, new ArrayList<>());
				stocksQuantity.put(stock, BigDecimal.ZERO);
			}
			BigDecimal quantity = stocksQuantity.get(stock).add(transaction.getQuantity());
			stocksQuantity.put(stock, quantity);
			stocksTransactions.get(stock).add(transaction);
			if (quantity.compareTo(BigDecimal.ZERO) == 0) stocksTransactions.get(stock).clear();
		}
		return stocksTransactions.values().stream().reduce(
				new TreeSet<TransactionElement>(),
				(r, e) -> {
					r.addAll(e);
					return r;
				},
				(u, t) -> {
					u.addAll(t);
					return u;
				});
	}

	@Override
	public InOutWeighted getWeightedInvestedFor(PortfolioShare portfolioShare, Date currentEndDate, Currency currency) {

		try {
			SortedSet<TransactionElement> transactionsForStock = new TreeSet<TransactionElement>();
			for (TransactionElement te : headTransactionsTo(null, currentEndDate)) {
				if (te.getStock().equals(portfolioShare.getStock())) {
					transactionsForStock.add(te);
				}
			}
			return portfolioShare.calculateInflationAndExpectationWeightedInvestedCash(currentEndDate, transactionsForStock, currency);
		} catch (InvalidAlgorithmParameterException e) {
			BigDecimal cashin = portfolioShare.getCashin(null, currentEndDate, currency);
			BigDecimal cashout = portfolioShare.getCashout(null, currentEndDate, currency);
			return new InOutWeighted(cashin, cashout, currentEndDate);
		}

	}

	public String extractPortfolioTransactionLog(Date startDate, Date endDate) throws Throwable {
		Currency portfolioCurrency = inferPortfolioCurrency();
		return extractTransactionLog(startDate, endDate, portfolioCurrency, startDate, endDate, BigDecimal.ZERO, BigDecimal.ZERO);
	}

	public String extractTransposedTransactionLog(Date startDate, Date endDate, Currency transpositionCurrency, Date cpgPeriodStart, Date cpgPeriodEnd, BigDecimal transactionFee, BigDecimal exchangeFee) throws Throwable {
		String extractTransactionLog = 
				extractTransactionLog(startDate, endDate, transpositionCurrency, cpgPeriodStart, cpgPeriodEnd, BigDecimal.ZERO, BigDecimal.ZERO) +
				"\n\n" +
				extractTransactionLog(startDate, endDate, transpositionCurrency, cpgPeriodStart, cpgPeriodEnd, transactionFee, exchangeFee);
		return extractTransactionLog;
	}

	private String extractTransactionLog(Date startDate, Date endDate, Currency targetCurrency, Date cpgPeriodStart, Date cpgPeriodEnd, BigDecimal transactionFee, BigDecimal exchangeFee) throws Throwable {

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SortedSet<TransactionElement> sortedByStock = transactionsSortedByStock(startDate, endDate);

			CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();

			String messagePortCurrency = 
					"Transactions (" + targetCurrency + " - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + " - exchange fee " +  exchangeFee + ") in "  +  getName()  +  " :\n" +
					"stock, date, transaction price, quantity in, amount in, quantity out, amount out, realised capital gain, currency, close price";
			String messageNoConvertion = 
					"Transactions (Original currencies - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + ") in " + getName() + " :\n" +
					"stock, date, transaction price, quantity in, amount in, quantity out, amount out, realised capital gain, currency, close price, exchange rate";
			Stock currentStock = null;

			//Transactions
			Map<Stock, BigDecimal[]> pss = new HashMap<Stock, BigDecimal[]>();
			for (TransactionElement te : sortedByStock) {

				if (currentStock == null || !currentStock.equals(te.getStock())) {//init stock
					currentStock = te.getStock();
					pss.put(currentStock, new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO});
				}

				BigDecimal closePrice = BigDecimal.ZERO;
				BigDecimal convertedClosePrice = BigDecimal.ZERO;
				BigDecimal convertionRate = BigDecimal.ONE;
				try {
					Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(currentStock, te.getDate(), true, currentStock.getMarketValuation().getCurrency(), ValidityFilter.CLOSE);
					closePrice = quotations.getClosestCloseForDate(te.getDate());
					Quotations convertedQuotations = QuotationsFactories.getFactory().getQuotationsInstance(currentStock, te.getDate(), true, targetCurrency, ValidityFilter.CLOSE);
					convertedClosePrice = convertedQuotations.getClosestCloseForDate(te.getDate());
					convertionRate = currencyConverter.convert(currentStock.getMarketValuation(), targetCurrency, BigDecimal.ONE, te.getDate());
				} catch (Exception e) {
					LOGGER.warn("Error loading stock prices for "+currentStock+" : "+e);
				}

				boolean buy = te.getQuantity().compareTo(BigDecimal.ZERO) > 0;

				BigDecimal transPrice = applyFee(buy, te.getPrice(), transactionFee);
				BigDecimal convertedTransPrice  = applyFee(buy, currencyConverter.convert(te.getCurrency(), targetCurrency, transPrice, te.getDate()), exchangeFee);
				BigDecimal transAmount =  transPrice.multiply(te.getQuantity()).setScale(2, RoundingMode.HALF_EVEN);
				BigDecimal convertedTransAmount = convertedTransPrice.multiply(te.getQuantity()).setScale(2, RoundingMode.HALF_EVEN);

				if (buy) {
					messagePortCurrency = 
							messagePortCurrency + "\n" + 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + convertedTransPrice + "," + 
									te.getQuantity() + "," + convertedTransAmount + ",,,0.00," + targetCurrency + "," + convertedClosePrice;
					messageNoConvertion = 
							messageNoConvertion  +  "\n" + 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + transPrice + "," + 
									te.getQuantity() + "," + transAmount + ",,,0.00," + te.getCurrency() + "," + closePrice + "," + convertionRate;
				} else {//sell
					Boolean isSellWithinCpgPeriod = te.getDate().compareTo(cpgPeriodStart) >= 0 && te.getDate().compareTo(cpgPeriodEnd) <= 0;

					BigDecimal cpgPortCurrency = BigDecimal.ZERO;
					if (isSellWithinCpgPeriod) {
						BigDecimal priceAvgBuyPortCur = applyFee(true, applyFee(true, this.getShareForStock(currentStock).getPriceAvgBuy(startDate, te.getDate(), targetCurrency), transactionFee), exchangeFee);
						cpgPortCurrency = te.getQuantity().multiply(priceAvgBuyPortCur).setScale(2, RoundingMode.HALF_EVEN).subtract(convertedTransAmount);
						pss.get(currentStock)[0] = pss.get(currentStock)[0].add(cpgPortCurrency);
					}
					messagePortCurrency = 
							messagePortCurrency + "\n" +
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + convertedTransPrice + ",,," + 
									te.getQuantity() + "," + convertedTransAmount + "," + cpgPortCurrency + "," + targetCurrency + "," + convertedClosePrice;

					BigDecimal cpgNoConv = BigDecimal.ZERO;
					if (isSellWithinCpgPeriod) {
						BigDecimal priceAvgBuyNoConv = applyFee(true, this.getShareForStock(currentStock).getPriceAvgBuy(startDate, te.getDate(), currentStock.getMarketValuation().getCurrency()), transactionFee);
						cpgNoConv = te.getQuantity().multiply(priceAvgBuyNoConv).setScale(2, RoundingMode.HALF_EVEN).subtract(transAmount);
						pss.get(currentStock)[1] = pss.get(currentStock)[1].add(cpgNoConv);
					}
					messageNoConvertion = 
							messageNoConvertion + "\n" +
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + transPrice + ",,," + 
									te.getQuantity() + "," + transAmount + "," + cpgNoConv + "," + te.getCurrency() + "," + closePrice + "," + convertionRate;
				}

			}

			messagePortCurrency = messagePortCurrency + "\n\n"
					+ "Totals (" + targetCurrency + " - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + " - exchange fee " + exchangeFee + ") in " + getName() + " :\n"
					+ "stock, on the, average price, quantity, invested (in-out), value, capital gain for period, in for period, out for period, potential capital gain, currency, last close";
			messageNoConvertion = messageNoConvertion + "\n\n"
					+ "Totals (Original currencies - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + ") in " + getName() + " :\n"
					+ "stock, on the, average price, quantity, invested (in-out), value, capital gain for period, in for period, out for period, potential capital gain, currency, last close, last exchange rate";

			//Totals
			for (Stock stock : pss.keySet()) {
				PortfolioShare ps = getShareForStock(stock);
				try {

					Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, endDate, true, stock.getMarketValuation().getCurrency(), ValidityFilter.CLOSE);
					BigDecimal lastClosePrice = quotations.getClosestCloseForDate(endDate);
					Quotations convertedQuotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, endDate, true, targetCurrency, ValidityFilter.CLOSE);
					BigDecimal lastConvertedClosePrice = convertedQuotations.getClosestCloseForDate(endDate);
					BigDecimal lastConvertionRate = currencyConverter.convert(stock.getMarketValuation(), targetCurrency, BigDecimal.ONE, endDate);

					BigDecimal quantity = getQuantityFor(ps, startDate, endDate);
					boolean isQuantityPositiveAtEndPeriod = ps.getQuantity(startDate, cpgPeriodEnd).compareTo(BigDecimal.ZERO) > 0;

					BigDecimal inPortCur = applyFee(true, applyFee(true, ps.getCashin(startDate, endDate, targetCurrency), transactionFee), exchangeFee);
					BigDecimal outPortCur = applyFee(false, applyFee(false, ps.getCashout(startDate, endDate, targetCurrency), transactionFee), exchangeFee);
					BigDecimal investedPortCur = inPortCur.subtract(outPortCur);
					BigDecimal in4PeriodPortCur = applyFee(true, applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, targetCurrency), transactionFee), exchangeFee);
					BigDecimal out4PeriodPortCur = applyFee(false, applyFee(false, ps.getCashout(cpgPeriodStart, cpgPeriodEnd, targetCurrency), transactionFee), exchangeFee);
					BigDecimal valuePortCur = applyFee(false, applyFee(false, ps.getValue(startDate, endDate, targetCurrency), transactionFee), exchangeFee);

					messagePortCurrency = messagePortCurrency + "\n" +
							ps.getStock().getFriendlyName() + ", " + dateFormat.format(endDate) + ", " + 
							applyFee(true, applyFee(true, ps.getPriceAvgBuy(startDate, endDate, targetCurrency), transactionFee), exchangeFee) + ", "+
							quantity + "," + investedPortCur + ", " + valuePortCur + ", " + 
							pss.get(stock)[0] + "," + in4PeriodPortCur + "," + out4PeriodPortCur + "," + (isQuantityPositiveAtEndPeriod?valuePortCur.subtract(investedPortCur):BigDecimal.ZERO) + ", " +
							targetCurrency + ", " + lastConvertedClosePrice;

					BigDecimal inNoConv = applyFee(true, ps.getCashin(startDate, endDate, stock.getMarketValuation().getCurrency()), transactionFee);
					BigDecimal outNoConv = applyFee(false, ps.getCashout(startDate, endDate, stock.getMarketValuation().getCurrency()), transactionFee);
					BigDecimal investedNoConv = inNoConv.subtract(outNoConv);
					BigDecimal in4PeriodNoConv = applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, stock.getMarketValuation().getCurrency()), transactionFee);
					BigDecimal out4PeriodNoConv = applyFee(false, ps.getCashout(cpgPeriodStart, cpgPeriodEnd, stock.getMarketValuation().getCurrency()), transactionFee);
					BigDecimal valueNoConv = applyFee(false, ps.getValue(startDate, endDate, stock.getMarketValuation().getCurrency()), transactionFee);
					messageNoConvertion = messageNoConvertion + "\n" +
							ps.getStock().getFriendlyName() + ", " + dateFormat.format(endDate) + ", " + 
							applyFee(true, ps.getPriceAvgBuy(startDate, endDate, stock.getMarketValuation().getCurrency()), transactionFee) + ", " +
							quantity + "," + investedNoConv + ", " + valueNoConv + ", " + 
							pss.get(stock)[1] + "," + in4PeriodNoConv + "," + out4PeriodNoConv + "," + (isQuantityPositiveAtEndPeriod?valueNoConv.subtract(investedNoConv):BigDecimal.ZERO) + ", " +
							stock.getMarketValuation().getCurrency() + ", " + lastClosePrice + ", " +
							lastConvertionRate;

				} catch (Exception e) {
					LOGGER.warn("Error loading last stock prices for "+stock+" : "+e);
				}
			}

			return messagePortCurrency + "\n\n" + messageNoConvertion;

		} catch (Throwable e) {
			throw e;
		}
	}

	private BigDecimal applyFee(boolean isBuyTransaction, BigDecimal price, BigDecimal transactionFee) {
		return (transactionFee.compareTo(BigDecimal.ZERO) == 0)?
				price:
				price.multiply((isBuyTransaction)?
						BigDecimal.ONE.add(transactionFee):
						BigDecimal.ONE.subtract(transactionFee)).setScale(2, RoundingMode.HALF_EVEN);
	}

	protected SortedSet<TransactionElement> transactionsSortedByStock(Date startDate, Date endDate) {
		SortedSet<TransactionElement> sortedByStock = new TreeSet<TransactionElement>(new Comparator<TransactionElement>() {

			@Override
			public int compare(TransactionElement o1, TransactionElement o2) {
				int stock = o1.getStock().compareTo(o2.getStock());
				if (stock == 0) {
					int equalDate = o2.getDate().compareTo(o1.getDate());
					if (equalDate == 0) {
						int id = o2.getId().compareTo(o1.getId());
						return id;
					}
					return equalDate;
				}
				return stock;
			}
		});

		sortedByStock.addAll(headTransactionsTo(startDate, endDate));
		return sortedByStock;
	}

	@Transient
	public List<PortfolioShare> getUnOwnedPorfolioShares() {
		return this.getListShares().values().stream().filter(ps -> ps.getQuantity(DateFactory.getNowEndDate()).compareTo(BigDecimal.ZERO) == 0).collect(Collectors.toList());
	}

	@Transient
	public List<PortfolioShare> getOwnedPorfolioShares() {
		return this.getListShares().values().stream().filter(ps -> ps.getQuantity(DateFactory.getNowEndDate()).compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
	}

	@Transient
	public Boolean isUiDirty() {
		return uiDirty;
	}

	public void setIsUiDirty(Boolean hasChanged) {
		this.uiDirty = hasChanged;
	}

	@Transient
	public Boolean isLatestTransactionsOnly() {
		return latestTransactionsOnly;
	}

	public void setLatestTransactionsOnly(Boolean latestTransactionsOnly) {
		this.latestTransactionsOnly = latestTransactionsOnly;
	}

}
