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
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
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

	protected Portfolio() {
		super();
		this.transactions = new TreeSet<TransactionElement>();
	}

	public Portfolio(Portfolio portfolio, String newName) {
		super(portfolio, newName);
		this.buyPonderationRule = portfolio.buyPonderationRule;
		this.sellPonderationRule = portfolio.sellPonderationRule;
		this.portfolioCurrency = portfolio.portfolioCurrency;
		this.transactions = new TreeSet<TransactionElement>();
		for (TransactionElement transactionElement : portfolio.getTransactions()) {
			this.transactions.add(new TransactionElement(this, transactionElement));
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
			AlertsMgrDelegate alertsMgrDelegate = new AlertsMgrDelegate(oldPortfolioShare);
			AlertsMgrDelegate alertsMgrDelegateNew = new AlertsMgrDelegate(portfolioShare);
			for (AlertOnThreshold alert: alertsMgrDelegate.getAlertsOnThresholdFor(AlertOnThresholdType.MANUALUP)) {
				alertsMgrDelegateNew.addAlertOnThreshold(alert.getThresholdType(), alert.getValue(), alert.getAlertType(), alert.getOptionalMessage());
			}
			for (AlertOnThreshold alert: alertsMgrDelegate.getAlertsOnThresholdFor(AlertOnThresholdType.MANUALDOWN)) {
				alertsMgrDelegateNew.addAlertOnThreshold(alert.getThresholdType(), alert.getValue(), alert.getAlertType(), alert.getOptionalMessage());
			}
		}

	}

	public PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date currentDate, BigDecimal buyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException {

		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, trCurrency);
		
		if (quantity.compareTo(BigDecimal.ZERO) > 0 && buyPrice.compareTo(BigDecimal.ZERO) > 0) {//Non null transaction
//			registerTransaction(portfolioShare, quantity, currentDate, buyPrice, trType);
//			new AlertsMgrDelegate(portfolioShare).addBuyAlerts(buyPrice, currentDate);
			updateShare(portfolioShare, quantity, currentDate, buyPrice, trType);
			if (portfolioShare.isOwned(currentDate, true)) {
				portfolioShare.setMonitorLevel(mLevel);
			} else {
				portfolioShare.setMonitorLevel(MonitorLevel.NONE);
			}
		}
		
		return portfolioShare;

	}

	private void registerTransaction(PortfolioShare recipientPS, BigDecimal quantity, Date buyDate, BigDecimal lastQuotation, TransactionType movement) throws InvalidQuantityException {

		Transaction transaction = new Transaction(quantity, lastQuotation, movement, buyDate);
		if (transaction.getTransactionSharePrice().compareTo(BigDecimal.ZERO) == 0) {
			throw new InvalidQuantityException("The amount is too small. Amount must be >= 0.0001 in transaction " + transaction, new Throwable());
		}
		if (transaction.getQuantity().compareTo(BigDecimal.ZERO) == 0 ) {
			LOGGER.warn("Ignoring. Trying to sell with a quantity of zero: " + transaction + " and " + recipientPS);
		}

		TransactionElement transactionElement = recipientPS.createTransactionElement(transaction);
		this.transactions.add(transactionElement);

	}

	public PortfolioShare addOrUpdateShareAtDayCloseForQuantity(Stock stock, BigDecimal quantity, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException, NoQuotationsException  {

		BigDecimal valueAtDate = BigDecimal.ZERO;
		if (quantity.compareTo(BigDecimal.ZERO) > 0) {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			valueAtDate = quotations.getClosestCloseForDate(currentDate);
		}

		return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
	}

	public PortfolioShare addOrUpdateShareAtDayCloseForAmount(Stock stock, BigDecimal unitAmount, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException {

		try {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			BigDecimal valueAtDate = quotations.getClosestCloseForDate(currentDate);
			BigDecimal quantity = unitAmount.divide(valueAtDate, 10, RoundingMode.HALF_EVEN);

			return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
		} catch (NoQuotationsException e) {
			throw new InvalidAlgorithmParameterException(e);
		}

	}

	public PortfolioShare addOrUpdateShareAtDayClose(Stock stock, String account, Currency transactionCurrency, Date currentDate, MonitorLevel mLevel) {

		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, transactionCurrency);
		AlertsMgrDelegate alertsMgrDelegate = new AlertsMgrDelegate(portfolioShare);
		alertsMgrDelegate.addBuyAlerts(portfolioShare.getPriceClose(currentDate, transactionCurrency), currentDate);
		portfolioShare.setExternalAccount(account);
		if (!portfolioShare.isOwned(currentDate, true)) {
			portfolioShare.setMonitorLevel(MonitorLevel.NONE);
		} else {
			portfolioShare.setMonitorLevel(mLevel);
		}
		
		return portfolioShare;

	}

	public void updateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException {
		registerTransaction(portfolioShare, quantity, currentDate, trPrice, trType);
		AlertsMgrDelegate alertsMgrDelegate = new AlertsMgrDelegate(portfolioShare);
		alertsMgrDelegate.addBuyAlerts(trPrice, currentDate);
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
			LOGGER.warn("No buy weighting rule for " + this.name + " the config rule will be used " + defaultBuyPonderationRule);
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
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return getValue(currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly);
	}

	@Transient
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly) {

		BigDecimal valueForDate = BigDecimal.ZERO.setScale(4);
		for (PortfolioShare portfolioShare: this.getListShares().values()) {
			BigDecimal psValueForDate = portfolioShare.getValue(currentStartDate, currentEndDate, targetCurrency, isLatestOnly);
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

	/**
	 * (Value + out) - in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainTotal(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		BigDecimal value = this.getValue(currentStartDate, currentEndDate, isLatestOnly);
		BigDecimal cashin = getCashIn(currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, false);
		BigDecimal cashout = getCashOut(currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, false);
		return value.add(cashout).subtract(cashin);
	}

	/**
	 * ((Value + out) - in) / in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainTotalPercent(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		BigDecimal cashin = getCashIn(currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, false);
		if (cashin.compareTo(BigDecimal.ZERO) == 0) {
			if (this.getListShares().size() > 0) LOGGER.warn("getGainTotalPercent: Cashin is zero for Portfolio " + this.name + " using dates from " + currentStartDate + " to " + currentEndDate + ". Also not empty.");
			return BigDecimal.ZERO;
		}
		BigDecimal gainTotalForDate = this.getGainTotal(currentStartDate, currentEndDate, isLatestOnly);
		return gainTotalForDate.divide(cashin, 10, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * For realised transactions only: sum((avg sell price - avg buy price) * sold quantity) / sum(avg buy price * sold quantity)
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param isLatestOnly 
	 * @param isLatestOnly 
	 * @return
	 */
	@Transient
	public BigDecimal getGainRealisedPercent(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		BigDecimal realisedIn = getListShares().values().stream()
				.map(ps -> {
					BigDecimal avgBuyPrice = getPriceAvgBuyFor(ps, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
					BigDecimal quantitySell = getQuantitySellFor(ps, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
					return avgBuyPrice.multiply(quantitySell).setScale(10, RoundingMode.HALF_EVEN);
				})
				.reduce(BigDecimal.ZERO, (a, e) -> a.add(e));
		if (realisedIn.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal realisedGain = getListShares().values().stream()
			.map(ps -> {
				BigDecimal avgSellPrice = getPriceAvgSellFor(ps, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
				BigDecimal avgBuyPrice = getPriceAvgBuyFor(ps, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
				BigDecimal quantitySell = getQuantitySellFor(ps, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
				return avgSellPrice.subtract(avgBuyPrice).multiply(quantitySell).setScale(10, RoundingMode.HALF_EVEN);
			})
			.reduce(BigDecimal.ZERO, (a, e) -> a.add(e));
		return realisedGain.divide(realisedIn, 10, RoundingMode.HALF_EVEN);
	}
	
	//TODO: check gnucash (FIFO??)
//	public BigDecimal getGainRealisedTaxableFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly) {
//		BigDecimal quantitySold = getQuantitySellFor(portfolioShare, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly, true);
//		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestOnly, true);
//		Iterator<TransactionElement> transactionsIterator = headTransactionsTo.iterator();
//		for (TransactionElement te : headTransactionsTo) {
//			if (te.transactionType().equals(TransactionType.AIN) && te.getStock().equals(portfolioShare.getStock())) {
//				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
//				if (quantitySold.compareTo(BigDecimal.ZERO) > 0) {
//					
//				}
//				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
//			}
//		}
//		}
//		return null;
//	}

	/**
	 * For latest transactions only: (value + out - in) / in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @return
	 */
	@Transient
	public BigDecimal getGainRemaingPotential(Date currentStartDate, Date currentEndDate) {
		BigDecimal cashin = getCashIn(currentStartDate, currentEndDate, inferPortfolioCurrency(), true, false);
		if (cashin.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
		BigDecimal value = getValue(currentStartDate, currentEndDate, true);
		BigDecimal cashout = getCashOut(currentStartDate, currentEndDate, inferPortfolioCurrency(), true, false);
		return value.add(cashout).subtract(cashin).divide(cashin, 10, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * (1+Cumulative Return)^(365/Days Held) -1
	 * where Cumulative Return ~ Total gain percent
	 * @param currentStartDate
	 * @param currentEndDate
	 * @return
	 */
	@Transient
	public BigDecimal getGainAnnualisedPercent(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		double cummulativeReturn = getGainTotalPercent(currentStartDate, currentEndDate, isLatestOnly).doubleValue();
		double nbDays = TimeUnit.DAYS.convert(currentEndDate.getTime() - currentStartDate.getTime(), TimeUnit.MILLISECONDS);
		LOGGER.info("getGainAnnualised, nb days since first transaction: " + nbDays + " for " + this);
		if (nbDays == 0) return BigDecimal.ZERO;
		double annualReturn = Math.pow(1 + cummulativeReturn, 365d/nbDays) - 1;
		return BigDecimal.valueOf(annualReturn);
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
	public Date getLastDateTransactionFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		Date ret = new Date(0);
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestOnly, false);
		for (TransactionElement te : headTransactionsTo) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret = te.getDate();
			}
		}
		return ret;
	}

	@Transient
	public SortedSet<TransactionElement> getTransactionsFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		SortedSet<TransactionElement> ret = new TreeSet<TransactionElement>();
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestOnly, false);
		for (TransactionElement te : headTransactionsTo) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret.add(te);
			}
		}
		return ret;
	}

	@Transient
	public BigDecimal getCashIn(Date currentStartDate, Date currentEndDate, Currency targetCurrency,Boolean isLatestOnly, Boolean isRealisedOnly) {
		BigDecimal ret = BigDecimal.ZERO;
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AIN)) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			};
		}
		return ret;
	}

	@Transient
	public BigDecimal getCashOut(Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		BigDecimal ret = BigDecimal.ZERO;
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AOUT)) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			};
		}
		return ret.abs();
	}

	public BigDecimal getBasis(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {

		BigDecimal ret = BigDecimal.ZERO;
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			ret = ret.add(getBasisFor(portfolioShare, currentStartDate, currentEndDate, inferPortfolioCurrency(), isLatestOnly));
		}
		return ret;
	}
	
	public BigDecimal getCashInFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestTransactionOnly, Boolean isRealisedOnly) {
		BigDecimal ret = BigDecimal.ZERO;
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestTransactionOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AIN) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			}
		}
		return ret;
	}
	
	@Override
	public BigDecimal getCashOutFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestTransacitonOnly, Boolean isRealisedOnly) {
		BigDecimal ret = BigDecimal.ZERO;
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestTransacitonOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AOUT) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				ret = ret.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
			}
		}
		return ret.abs();
	}

	protected BigDecimal getQuantityFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, 
										Boolean isLatestTransactionOnly, Boolean isRealisedOnly) {
		BigDecimal ret = BigDecimal.ZERO;
		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate, isLatestTransactionOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.getStock().equals(portfolioShare.getStock())) {
				ret = ret.add(te.getQuantity());
			}
		}
		return ret;
	}
	
	@Override
	public BigDecimal getQuantityFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return this.getQuantityFor(portfolioShare, currentStartDate, currentEndDate, isLatestOnly, false);
	}

	public void rawAddTransaction(TransactionElement element) {
		element.setPortfolio(this);
		this.transactions.add(element);
	}

	@Override
	public BigDecimal getBasisFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly) {
		BigDecimal priceAvgBuyFor = getPriceAvgBuyFor(portfolioShare, currentStartDate, currentEndDate, targetCurrency, isLatestOnly, false);
		BigDecimal quantityFor = this.getQuantityFor(portfolioShare, currentStartDate, currentEndDate, isLatestOnly);
		return priceAvgBuyFor.multiply(quantityFor).setScale(10, RoundingMode.HALF_EVEN);
	}

	@Override
	public BigDecimal getPriceAvgBuyFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		BigDecimal totalMoneyInvested = BigDecimal.ZERO;
		BigDecimal totalQuantityBought = BigDecimal.ZERO;

		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate,  isLatestOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AIN) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				totalMoneyInvested = totalMoneyInvested.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
				totalQuantityBought = totalQuantityBought.add(te.getQuantity());
			}
		}

		if (totalQuantityBought.compareTo(BigDecimal.ZERO) == 0) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("getPriceAvgBuyFor : Bought Transaction sum to zero for " + portfolioShare);
			return BigDecimal.ZERO;
		} else {
			return totalMoneyInvested.divide(totalQuantityBought, 10, RoundingMode.HALF_EVEN);
		}
	}
	
	@Override
	public BigDecimal getPriceAvgSellFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		BigDecimal totalMoneyDevested = BigDecimal.ZERO;
		BigDecimal totalQuantitySold = BigDecimal.ZERO;

		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate,  isLatestOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AOUT) && te.getStock().equals(portfolioShare.getStock())) {
				BigDecimal convertedPrice = getCurrencyConverter().convert(te.getCurrency(), targetCurrency, te.getPrice(), te.getDate());
				totalMoneyDevested = totalMoneyDevested.add(convertedPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN));
				totalQuantitySold = totalQuantitySold.add(te.getQuantity());
			}
		}

		if (totalQuantitySold.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		} else {
			return totalMoneyDevested.divide(totalQuantitySold, 10, RoundingMode.HALF_EVEN);
		}
	}
	
	private BigDecimal getQuantitySellFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		BigDecimal totalQuantitySold = BigDecimal.ZERO;

		SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(currentStartDate, currentEndDate,  isLatestOnly, isRealisedOnly);
		for (TransactionElement te : headTransactionsTo) {
			if (te.transactionType().equals(TransactionType.AOUT) && te.getStock().equals(portfolioShare.getStock())) {
				totalQuantitySold = totalQuantitySold.add(te.getQuantity().abs());
			}
		}
		return totalQuantitySold;
	}

	private SortedSet<TransactionElement> headTransactionsTo(Date currentStartDate, Date currentEndDate, Boolean isLatestTransactionsOnly, Boolean isRealisedOnly) {
		SortedSet<TransactionElement> transactionSubSet = headTransactionsTo(transactions, currentStartDate, currentEndDate);
		if (isLatestTransactionsOnly) {
			transactionSubSet = latestTransactions(transactionSubSet);
		}
		if (isRealisedOnly) {
			transactionSubSet = realisedTransactions(transactionSubSet);
		}
		return transactionSubSet;
	}

	/**
	 * All inclusive
	 * @param inputTransactions
	 * @param currentStartDate
	 * @param currentEndDate
	 * @return
	 */
	private SortedSet<TransactionElement> headTransactionsTo(SortedSet<TransactionElement> inputTransactions, Date currentStartDate, Date currentEndDate) {
		//currentStartDate = DateFactory.midnithDate(currentStartDate);
		//currentEndDate = DateFactory.midnithDate(currentEndDate);
		if (currentStartDate == null || currentStartDate.equals(DateFactory.dateAtZero())) { //right bound only
			return inputTransactions.stream().filter(t -> t.getDate().compareTo(currentEndDate) <= 0).collect(Collectors.toCollection(TreeSet::new)); 
		} else { //right and left bound
			return inputTransactions.stream().filter( t -> {
				boolean b = currentStartDate.compareTo(t.getDate()) <= 0  && t.getDate().compareTo(currentEndDate) <= 0;
				return b;
			}).collect(Collectors.toCollection(TreeSet::new));
		}
	}

	/**
	 * Remove all transactions happening before the last time the quantity went down to zero (i.e. the last time the line was cleared)
	 * @param transactions
	 * @return
	 */
	public SortedSet<TransactionElement> latestTransactions(SortedSet<TransactionElement> transactions) {
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
		return stocksTransactions.values().stream()
				.reduce(new TreeSet<TransactionElement>(),
						(r, e) -> {
							r.addAll(e);
							return r;
						},
						(u, t) -> {
							u.addAll(t);
							return u;
						});
	}
	
	/**
	 * Trims away the potential buy transactions happening after the last sell transaction
	 * @param transactions
	 * @return
	 */
	public SortedSet<TransactionElement> realisedTransactions(SortedSet<TransactionElement> transactions) {
		Map<Stock, List<TransactionElement>> stocksTransactions = new HashMap<>();
		for(TransactionElement transaction: transactions) {
			Stock stock = transaction.getStock();
			List<TransactionElement> stockTransactions = stocksTransactions.get(stock);
			if (stockTransactions == null) {
				stocksTransactions.put(stock, new ArrayList<>());
			}
			stocksTransactions.get(stock).add(transaction);
		}
		return stocksTransactions.keySet().stream()
			.map(s -> {
				List<TransactionElement> stockTransactions = stocksTransactions.get(s);
				int i = stockTransactions.size();
				while (i > 0 && stockTransactions.get(i-1).transactionType().equals(TransactionType.AIN)) i--;
				return stockTransactions.subList(0, i);
			})
			.reduce(new TreeSet<TransactionElement>(),
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
	public InOutWeighted getInflatWeightedInvestedFor(PortfolioShare portfolioShare, Date currentEndDate, Currency currency, Boolean isLatestTransactionsOnly) {
		try {
			SortedSet<TransactionElement> transactionsForStock = new TreeSet<TransactionElement>();
			SortedSet<TransactionElement> headTransactionsTo = headTransactionsTo(null, currentEndDate, isLatestTransactionsOnly, false);
			for (TransactionElement te : headTransactionsTo) {
				if (te.getStock().equals(portfolioShare.getStock())) {
					transactionsForStock.add(te);
				}
			}
			return portfolioShare.calculateInflationAndExpectationWeightedInvestedCash(currentEndDate, transactionsForStock, currency);
		} catch (InvalidAlgorithmParameterException e) {
			BigDecimal cashin = this.getCashInFor(portfolioShare, null, currentEndDate, currency, isLatestTransactionsOnly, false);
			BigDecimal cashout = this.getCashOutFor(portfolioShare, null, currentEndDate, currency, isLatestTransactionsOnly, false);
			return new InOutWeighted(cashin, cashout, currentEndDate);
		}
	}

	public String extractPortfolioTransactionLog(Date startDate, Date endDate, Boolean isLatestTransactionOnly) throws Throwable {
		Currency portfolioCurrency = inferPortfolioCurrency();
		return extractTransactionLog(startDate, endDate, portfolioCurrency, startDate, endDate, BigDecimal.ZERO, BigDecimal.ZERO, isLatestTransactionOnly);
	}

	public String extractTransposedTransactionLog(Date startDate, Date endDate, Currency transpositionCurrency, Date cpgPeriodStart, Date cpgPeriodEnd, BigDecimal transactionFee, BigDecimal exchangeFee, Boolean isLatestTransactionOnly) throws Throwable {
		String extractTransactionLog = 
				extractTransactionLog(startDate, endDate, transpositionCurrency, cpgPeriodStart, cpgPeriodEnd, BigDecimal.ZERO, BigDecimal.ZERO, isLatestTransactionOnly) +
				"\n\n" +
				extractTransactionLog(startDate, endDate, transpositionCurrency, cpgPeriodStart, cpgPeriodEnd, transactionFee, exchangeFee, isLatestTransactionOnly);
		return extractTransactionLog;
	}

	//FIXME 
	//To correctly calculate the capital gain we would have to use a FIFO Method (check how it is done in gnucash) instead of the weighted buy price average (getPriceAvgBuy) used at the moment
	//In theory, the weighted price average gives only accurate results at the point when the line is cleared or if there only is one buy transaction
	private String extractTransactionLog(Date startDate, Date endDate, Currency targetCurrency, Date cpgPeriodStart, Date cpgPeriodEnd, BigDecimal transactionFee, BigDecimal exchangeFee, Boolean isLatestOnly) throws Throwable {

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SortedSet<TransactionElement> transactionsSortedByStock = transactionsSortedByStock(startDate, endDate, isLatestOnly);
			
			if (transactionsSortedByStock.isEmpty()) throw new Exception("The portfolio " + this.getName() + " does not have any transaction.");

			CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();

			String messagePortCurrency = "";
			String messageNoConvertion = "";
			Stock currentStock = null;

			//Transactions
			Map<Stock, BigDecimal[]> pss = new HashMap<Stock, BigDecimal[]>();
			TransactionElement prevTe = transactionsSortedByStock.first(); 
			for (TransactionElement te : transactionsSortedByStock) {
				
				if (currentStock == null || !currentStock.equals(te.getStock())) {//init stock
					currentStock = te.getStock();
					pss.put(currentStock, new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO});
				}
				Currency stockCurrency = currentStock.getMarketValuation().getCurrency();

				BigDecimal closePrice = BigDecimal.ZERO;
				BigDecimal convertedClosePrice = BigDecimal.ZERO;
				BigDecimal convertionRate = BigDecimal.ONE;
				try {
					Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(currentStock, te.getDate(), true, stockCurrency, ValidityFilter.CLOSE);
					closePrice = quotations.getClosestCloseForDate(te.getDate());
					Quotations convertedQuotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(currentStock, te.getDate(), true, targetCurrency, ValidityFilter.CLOSE);
					convertedClosePrice = convertedQuotations.getClosestCloseForDate(te.getDate());
					convertionRate = currencyConverter.convert(currentStock.getMarketValuation(), targetCurrency, BigDecimal.ONE, te.getDate());
				} catch (Exception e) {
					LOGGER.warn("Error loading stock prices for " + currentStock + " : " + e);
				}

				boolean buy = te.getQuantity().compareTo(BigDecimal.ZERO) > 0;

				BigDecimal transPrice = applyFee(buy, te.getPrice(), transactionFee);
				BigDecimal convertedTransPrice  = applyFee(buy, currencyConverter.convert(te.getCurrency(), targetCurrency, transPrice, te.getDate()), exchangeFee);
				BigDecimal transAmount = transPrice.multiply(te.getQuantity()).setScale(2, RoundingMode.HALF_EVEN);
				BigDecimal convertedTransAmount = convertedTransPrice.multiply(te.getQuantity()).setScale(2, RoundingMode.HALF_EVEN);

				if (buy) {//buy
					//Converted to target currency
					messagePortCurrency = 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + convertedTransPrice + "," + 
									te.getQuantity() + "," + convertedTransAmount + ",,,0.00," + targetCurrency + "," + convertedClosePrice + 
									"\n" + messagePortCurrency;
					
					//Original currencies
					messageNoConvertion = 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + transPrice + "," + 
									te.getQuantity() + "," + transAmount + ",,,0.00," + stockCurrency + "," + closePrice + "," + convertionRate +
									 "\n" + messageNoConvertion;
				} else {//sell
					Boolean isSellWithinCpgPeriod = te.getDate().compareTo(cpgPeriodStart) >= 0 && te.getDate().compareTo(cpgPeriodEnd) <= 0;
					
					//Converted to target currency
					BigDecimal realisedGainPortCur = BigDecimal.ZERO;
					PortfolioShare shareForStock = this.getShareForStock(currentStock);
					if (isSellWithinCpgPeriod) {
						BigDecimal avgBuyPrice = shareForStock.getPriceAvgBuy(startDate, prevTe.getDate(), targetCurrency, true, false); //isRealisedOnly is false to work around the first sell. Otherwise, isRealisedOnly value should not have any incidence here
						realisedGainPortCur = convertedTransAmount.subtract(avgBuyPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN)).negate();
						//pss.get(currentStock)[0] = realisedGainPortCur;
					}
					messagePortCurrency = 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + convertedTransPrice + ",,," + 
									te.getQuantity() + "," + convertedTransAmount + "," + realisedGainPortCur + "," + targetCurrency + "," + convertedClosePrice +
									"\n" + messagePortCurrency;
					
					//Original currencies - no exchange fee
					BigDecimal realisedGainNoConv = BigDecimal.ZERO;
					if (isSellWithinCpgPeriod) {
						BigDecimal avgBuyPrice = shareForStock.getPriceAvgBuy(startDate, prevTe.getDate(), stockCurrency, true, false); //isRealisedOnly is false to work around the first sell. Otherwise, isRealisedOnly value should not have any incidence here
						realisedGainNoConv = transAmount.subtract(avgBuyPrice.multiply(te.getQuantity()).setScale(10, RoundingMode.HALF_EVEN)).negate();
						//pss.get(currentStock)[1] = realisedGainNoConv;
					}
					messageNoConvertion = 
									currentStock.getFriendlyName() + "," + dateFormat.format(te.getDate()) + "," + transPrice + ",,," + 
									te.getQuantity() + "," + transAmount + "," + realisedGainNoConv + "," + stockCurrency + "," + closePrice + "," + convertionRate +
									"\n" + messageNoConvertion;
				}
				prevTe = te;

			}
			
			messagePortCurrency = "Transactions (" + targetCurrency + " - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + " - exchange fee " +  exchangeFee + ") in "  +  getName()  +  ":\n" +
					"stock, date, transaction price, quantity in, amount in, quantity out, amount out, realised capital gain at date for period, currency, close price" +
					"\n" + messagePortCurrency;
			messageNoConvertion = 
				"Transactions (Original currencies - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
				" - transaction fee " + transactionFee + ") in " + getName() + ":\n" +
				"stock, date, transaction price, quantity in, amount in, quantity out, amount out, realised capital gain at date for period, currency, close price, exchange rate" +
				"\n" + messageNoConvertion;

			messagePortCurrency = messagePortCurrency + "\n\n"
					+ "Totals for period (" + targetCurrency + " - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + " - exchange fee " + exchangeFee + ") in " + getName() + ":\n"
					+ "stock, on the, average buy price, quantity, invested (in-out), value, realised gain (taxable) at end of period, in for period, out for period, total gain, currency, last close";
			messageNoConvertion = messageNoConvertion + "\n\n"
					+ "Totals for period (Original currencies - " + dateFormat.format(cpgPeriodStart) + " -> " + dateFormat.format(cpgPeriodEnd) + 
					" - transaction fee " + transactionFee + ") in " + getName() + ":\n"
					+ "stock, on the, average buy price, quantity, invested (in-out), value, realised gain (taxable) at end of period, in for period, out for period, total gain, currency, last close, last exchange rate";

			//Totals
			for (Stock stock : pss.keySet()) {
				PortfolioShare ps = getShareForStock(stock);
				try {

					Currency stockCurrency = stock.getMarketValuation().getCurrency();
					Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, endDate, true, stockCurrency, ValidityFilter.CLOSE);
					BigDecimal lastClosePrice = quotations.getClosestCloseForDate(endDate);
					Quotations convertedQuotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, endDate, true, targetCurrency, ValidityFilter.CLOSE);
					BigDecimal lastConvertedClosePrice = convertedQuotations.getClosestCloseForDate(endDate);
					BigDecimal lastConvertionRate = currencyConverter.convert(stock.getMarketValuation(), targetCurrency, BigDecimal.ONE, endDate);

					BigDecimal quantity = getQuantityFor(ps, startDate, endDate, isLatestOnly);
					{ //Converted in targetCurrency
					BigDecimal inPortCur = applyFee(true, applyFee(true, ps.getCashin(startDate, endDate, targetCurrency, isLatestOnly), transactionFee), exchangeFee);
					BigDecimal outPortCur = applyFee(false, applyFee(false, ps.getCashout(startDate, endDate, targetCurrency, isLatestOnly), transactionFee), exchangeFee);
					BigDecimal investedPortCur = inPortCur.subtract(outPortCur);
					BigDecimal totalGainPortCur = ps.getGainTotal(startDate, endDate, targetCurrency, isLatestOnly);
					BigDecimal in4PeriodPortCur = applyFee(true, applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, targetCurrency, isLatestOnly), transactionFee), exchangeFee);
					BigDecimal out4PeriodPortCur = applyFee(false, applyFee(false, ps.getCashout(cpgPeriodStart, cpgPeriodEnd, targetCurrency, isLatestOnly), transactionFee), exchangeFee);
					BigDecimal valuePortCur = ps.getValue(startDate, endDate, targetCurrency, isLatestOnly);
					BigDecimal avgBuyPricePortCur = ps.getPriceAvgBuy(startDate, endDate, targetCurrency, isLatestOnly, true);
					BigDecimal realisedPortCur = 
							ps.getGainRealisedPercent(cpgPeriodStart, cpgPeriodEnd, targetCurrency, isLatestOnly)
							.multiply(applyFee(true, applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, targetCurrency, isLatestOnly, true), transactionFee), exchangeFee))
							.setScale(10, RoundingMode.HALF_EVEN);
					messagePortCurrency = messagePortCurrency + "\n" +
							ps.getStock().getFriendlyName() + ", " + dateFormat.format(endDate) + ", " + 
							avgBuyPricePortCur + ", " + quantity + "," + investedPortCur + ", " + valuePortCur + ", " + 
							realisedPortCur + "," + in4PeriodPortCur + "," + out4PeriodPortCur + "," + totalGainPortCur + ", " +
							targetCurrency + ", " + lastConvertedClosePrice;
					}
					
					{ //Original transactions currencies
					BigDecimal inNoConv = applyFee(true, ps.getCashin(startDate, endDate, stockCurrency, isLatestOnly), transactionFee);
					BigDecimal outNoConv = applyFee(false, ps.getCashout(startDate, endDate, stockCurrency, isLatestOnly), transactionFee);
					BigDecimal investedNoConv = inNoConv.subtract(outNoConv);
					BigDecimal totalGainNoConv = ps.getGainTotal(startDate, endDate, stockCurrency, isLatestOnly); //FIXME: we can't apply a transaction or exchange fee in this way
					BigDecimal in4PeriodNoConv = applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, stockCurrency, isLatestOnly), transactionFee);
					BigDecimal out4PeriodNoConv = applyFee(false, ps.getCashout(cpgPeriodStart, cpgPeriodEnd, stockCurrency, isLatestOnly), transactionFee);
					BigDecimal valueNoConv = ps.getValue(startDate, endDate, stockCurrency, isLatestOnly);
					BigDecimal avgBuyPriceNoConv = ps.getPriceAvgBuy(startDate, endDate, stockCurrency, isLatestOnly, true);
					BigDecimal realisedNoConv = 
							ps.getGainRealisedPercent(cpgPeriodStart, cpgPeriodEnd, stockCurrency, isLatestOnly)
							.multiply(applyFee(true, ps.getCashin(cpgPeriodStart, cpgPeriodEnd, stockCurrency, isLatestOnly, true), transactionFee))
							.setScale(10, RoundingMode.HALF_EVEN);
					messageNoConvertion = messageNoConvertion + "\n" +
							ps.getStock().getFriendlyName() + ", " + dateFormat.format(endDate) + ", " + 
							avgBuyPriceNoConv + ", " + quantity + "," + investedNoConv + ", " + valueNoConv + ", " + 
							realisedNoConv + "," + in4PeriodNoConv + "," + out4PeriodNoConv + "," + totalGainNoConv + ", " +
							stockCurrency + ", " + lastClosePrice + ", " +
							lastConvertionRate;
					}

				} catch (Exception e) {
					LOGGER.warn("Error loading last stock prices for " + stock + ": " + e);
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

	public SortedSet<TransactionElement> transactionsSortedByStock(Date startDate, Date endDate, Boolean isLatestTransactionOnly) {
		SortedSet<TransactionElement> sortedByStock = new TreeSet<TransactionElement>(new Comparator<TransactionElement>() {

			@Override
			public int compare(TransactionElement o1, TransactionElement o2) {
				int stock = o1.getStock().compareTo(o2.getStock());
				if (stock == 0) {
					int equalDate = o1.getDate().compareTo(o2.getDate());
					if (equalDate == 0) {
						int id = o2.getId().compareTo(o1.getId());
						return id;
					}
					return equalDate;
				}
				return stock;
			}
		});

		sortedByStock.addAll(headTransactionsTo(startDate, endDate, isLatestTransactionOnly, false));
		return sortedByStock;
	}
	
	public SortedSet<TransactionElement> transactionsSortedByDate(Date startDate, Date endDate, Boolean isLatestTransactionOnly) {
		return headTransactionsTo(startDate, endDate, isLatestTransactionOnly, false);
	}

	@Transient
	public List<PortfolioShare> getUnOwnedPorfolioShares() {
		return this.getListShares().values().stream().filter(ps -> ps.getQuantity(DateFactory.getNowEndDate(), false).compareTo(BigDecimal.ZERO) == 0).collect(Collectors.toList());
	}

	@Transient
	public List<PortfolioShare> getOwnedPorfolioShares() {
		return this.getListShares().values().stream().filter(ps -> ps.getQuantity(DateFactory.getNowEndDate(), false).compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
	}

	@Transient
	public Boolean isUiDirty() {
		return uiDirty;
	}

	public void setIsUiDirty(Boolean hasChanged) {
		this.uiDirty = hasChanged;
	}
	
	@Override
	public BigDecimal getPriceUnitCostFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestTransactionOnly) {
		BigDecimal quantity = this.getQuantityFor(portfolioShare, currentStartDate, currentEndDate, isLatestTransactionOnly);
		if (quantity.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal cashout = this.getCashOutFor(portfolioShare, currentStartDate, currentEndDate, currency, isLatestTransactionOnly, false);
		BigDecimal cashin = this.getCashInFor(portfolioShare, currentStartDate, currentEndDate, currency, isLatestTransactionOnly, false);
		return cashin.subtract(cashout).divide(quantity, 10, RoundingMode.HALF_EVEN);
	}

}
