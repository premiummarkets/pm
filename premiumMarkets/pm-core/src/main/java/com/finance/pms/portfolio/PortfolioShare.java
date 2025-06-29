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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.quotation.GetInflation;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * FIXME: Unused columns : QUANTITY | BUYDATE | AVGBUYPRICE | CASHIN | CASHOUT 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name = "PORTFOLIO")
public class PortfolioShare implements Serializable, Comparable<PortfolioShare> {

	private static final long serialVersionUID = 2121776460759183840L;
	private static MyLogger LOGGER = MyLogger.getLogger(PortfolioShare.class);

	public static BigDecimal TRANSACTION_FEE = new BigDecimal(MainPMScmd.getMyPrefs().get("portfolio.fee", "0.01")).setScale(2);

	//@Autowired
	private GetInflation getInflation = GetInflation.geInstance();

	private Stock stock;
	private MonitorLevel monitorLevel;
	private Currency transactionCurrency;
	private AbstractSharesList portfolio;
	private Set<AlertOnThreshold> alertsOnThreshold;
	private Set<AlertOnEvent> alertsOnEvent;
	private String externalAccount;
	private Double weight;

	//Hib
	public PortfolioShare() {
		super();
	}

	public PortfolioShare(PortfolioShare portfolioShare) {

		this.stock = portfolioShare.getStock();
		this.monitorLevel = portfolioShare.getMonitorLevel();
		this.transactionCurrency = portfolioShare.getTransactionCurrency();
		this.portfolio = portfolioShare.getPortfolio();
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			this.alertsOnThreshold.add(new AlertOnThreshold(this, alert));
		}
		this.alertsOnEvent = new HashSet<AlertOnEvent>();
		for (AlertOnEvent alert : portfolioShare.getAlertsOnEvent()) {
			this.alertsOnEvent.add(new AlertOnEvent(this, alert));
		}
		this.externalAccount = portfolioShare.getExternalAccount();

	}
	
	public PortfolioShare(AbstractSharesList abstractSharesList, PortfolioShare portfolioShare) {
		this.stock = portfolioShare.getStock();
		this.monitorLevel = portfolioShare.getMonitorLevel();
		this.transactionCurrency = portfolioShare.getTransactionCurrency();
		this.portfolio = abstractSharesList;
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			this.alertsOnThreshold.add(new AlertOnThreshold(this, alert));
		}
		this.alertsOnEvent = new HashSet<AlertOnEvent>();
		for (AlertOnEvent alert : portfolioShare.getAlertsOnEvent()) {
			this.alertsOnEvent.add(new AlertOnEvent(this, alert));
		}
		this.externalAccount = portfolioShare.getExternalAccount();

	}

	PortfolioShare(AbstractSharesList sharesList, Stock stock, MonitorLevel monitor, Currency transactionCurrency) {

		super();
		this.stock = stock;
		this.monitorLevel = monitor;
		this.transactionCurrency = transactionCurrency;
		this.portfolio = sharesList;
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		this.alertsOnEvent = new HashSet<AlertOnEvent>();

	}

	private BigDecimal closeQuotationFor(Stock stock, Currency transactionCurrency, Date currentDate) {
		try {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			return quotations.getClosestCloseForDate(currentDate);
		} catch (NoQuotationsException e) {
			LOGGER.warn("No quotations for " + stock);
		}
		return BigDecimal.ZERO;
	}

	@Transient
	public Date getLastTransactionDate() {
		return getPortfolio().getLastDateTransactionFor(this, null, DateFactory.getNowEndDate(), false);
	}

	@Transient
	public SortedSet<TransactionElement> getTransactions(Boolean isLatestOnly) {
		return getPortfolio().getTransactionsFor(this, null, DateFactory.getNowEndDate(), isLatestOnly);
	}
	
	@Transient
	public SortedSet<TransactionElement> getTransactions(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return getPortfolio().getTransactionsFor(this, currentStartDate, currentEndDate, isLatestOnly);
	}
	
	public BigDecimal getCashin(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return getPortfolio().getCashInFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, isRealisedOnly);
	}

	public BigDecimal getCashin(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getCashInFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, false);
	}
	
	public BigDecimal getCashout(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return getPortfolio().getCashOutFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, isRealisedOnly);
	}

	public BigDecimal getCashout(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getCashOutFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, false);
	}

	public BigDecimal getQuantity(Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return getPortfolio().getQuantityFor(this, currentStartDate, currentEndDate, isLatestOnly);
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumns( { @JoinColumn(name = "isin", referencedColumnName = "isin"), @JoinColumn(name = "symbol", referencedColumnName = "symbol") })
	@ForeignKey(name = "FK_STOCK")
	@Id
	public Stock getStock() {
		return stock;
	}

	@Transient
	public String getSymbol() {
		return stock.getSymbol();
	}

	@Transient
	public String getIsin() {
		return stock.getIsin();
	}

	@Transient
	public String getName() {
		return stock.getName();
	}

	@SuppressWarnings("unused")
	private void setStock(Stock stock) {
		this.stock = stock;
	}

	@Transient
	public BigDecimal getPriceClose(Date currentDate, Currency currency) {
		return closeQuotationFor(stock, currency, currentDate);
	}

	/**
	 * ((Value + out) - in) / in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param currency
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainTotalPercent(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency, isLatestOnly);
		if (cashin.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal gainTotal = getGainTotal(currentStartDate, currentEndDate, currency, isLatestOnly);
		return gainTotal.divide(cashin, 10, RoundingMode.HALF_EVEN);
	}

	/**
	 *  (Value + out) - in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param currency
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainTotal(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency, isLatestOnly);
		BigDecimal value = getValue(currentStartDate, currentEndDate, currency, isLatestOnly);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency, isLatestOnly);
		return value.add(cashout).subtract(cashin);
	}
	
	/**
	 * For realised transactions only: (avg sell price - avg buy price) / avg buy price
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param currency
	 * @return
	 */
	@Transient
	public BigDecimal getGainRealisedPercent(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		BigDecimal avgBuyPrice = getPriceAvgBuy(currentStartDate, currentEndDate, currency, isLatestOnly, true);
		if (avgBuyPrice.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal avgSellPrice = getPriceAvgSell(currentStartDate, currentEndDate, currency, isLatestOnly, true);
		if (avgSellPrice.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		return avgSellPrice.subtract(avgBuyPrice).divide(avgBuyPrice, 10, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * (1+Cumulative Return)^(365/Days Held) -1
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param currency
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainAnnualisedPercent(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getGainAnnualisedPercentFor(this, currentStartDate, currentEndDate, isLatestOnly);
	}
	
	@Transient
	public BigDecimal getGainReinvestedPercent(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getGainReinvestedPercentFor(this, currentStartDate, currentEndDate, isLatestOnly);
	}
	
	@Transient
	public BigDecimal getGainBuyNHoldPercentFor(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getGainBuyNHoldPercentFor(this, currentStartDate, currentEndDate, isLatestOnly);
	}

	/**
	 * For latest transactions only: (value + out - in) / in
	 * @param currentStartDate
	 * @param currentEndDate
	 * @param currency
	 * @param isLatestOnly
	 * @return
	 */
	@Transient
	public BigDecimal getGainRemaingPotentialReturn(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency, true);
		if (cashin.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal value = getValue(currentStartDate, currentEndDate, currency, true);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency, true);
		return value.add(cashout).subtract(cashin).divide(cashin, 10, RoundingMode.HALF_EVEN);
	}

	@Transient
	public BigDecimal getGainTotalWeightedPercent(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		try {
			InOutWeighted cashInOutWeighted = getInflatWeightedInvested(currentStartDate, currentEndDate, currency, isLatestOnly);
			BigDecimal cashInSubtractOutWeighted = cashInOutWeighted.getWeightedInvestedStillIn();
			BigDecimal cashinWeighted = cashInOutWeighted.getIn();
			BigDecimal value = getValue(currentStartDate, currentEndDate, currency, isLatestOnly);
			return 	value.subtract(cashInSubtractOutWeighted).divide(cashinWeighted, 10, RoundingMode.HALF_EVEN); 
		} catch (ArithmeticException e) {
			return BigDecimal.ZERO;
		}
	}

	@Transient
	public BigDecimal getPriceUnitCost(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return portfolio.getPriceUnitCostFor(this, currentStartDate, currentEndDate, currency, isLatestOnly);
	}

	@Transient
	public BigDecimal getPriceUnitCost(Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPriceUnitCost(null, currentEndDate, currency, isLatestOnly);
	}

	@Transient
	public BigDecimal getPriceAvgBuy(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return getPortfolio().getPriceAvgBuyFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, isRealisedOnly);
	}
	
	@Transient
	public BigDecimal getPriceAvgSell(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return getPortfolio().getPriceAvgSellFor(this, currentStartDate, currentEndDate, currency, isLatestOnly, isRealisedOnly);
	}

	@Transient
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		BigDecimal quantity = this.getQuantity(currentStartDate, currentEndDate, isLatestOnly);
		BigDecimal priceClose = getPriceClose(currentEndDate, currency);
		return quantity.multiply(priceClose).setScale(10, RoundingMode.HALF_EVEN);
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "MONITOR", nullable = false)
	public MonitorLevel getMonitorLevel() {
		return monitorLevel;
	}

	public void setMonitorLevel(MonitorLevel monitorLevel) {
		this.monitorLevel = monitorLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((portfolio == null) ? 0 : portfolio.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PortfolioShare other = (PortfolioShare) obj;
		if (portfolio == null) {
			if (other.portfolio != null)
				return false;
		} else if (!portfolio.getName().trim().equals(other.portfolio.getName().trim()))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(name = "name", nullable = false)
	@ForeignKey(name = "FK_PORTFOLIO_TO_PORTFOLIO_NAME")
	@Id
	public AbstractSharesList getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(AbstractSharesList portfolio) {
		this.portfolio = portfolio;
	}

	@Override
	public String toString() {
		return "PortfolioShare [portfolio = "+ getPortfolio().getName()+", stock=" + stock.getFriendlyName() + ", monitorLevel=" + monitorLevel + ", transactionCurrency=" + transactionCurrency + ", externalAccount="+ externalAccount + "]";
	}


	public TransactionElement createTransactionElement(Transaction transaction) throws InvalidQuantityException {

		BigDecimal quantity = transaction.getQuantity();
		if (transaction.getModtype().equals(TransactionType.AOUT)) {
			if (quantity.compareTo(this.getQuantity(transaction.getDate(), false)) > 0) {
				throw new InvalidQuantityException("Trying to sell more than available. The quantity can't be negative!", null);
			}
			quantity = quantity.multiply(new BigDecimal(-1));
		}

		return new TransactionElement(stock, (Portfolio) getPortfolio(), this.externalAccount, transaction.getDate(), transaction.getTransactionSharePrice(), quantity, transactionCurrency);

	}


	BigDecimal getCashout(Date currentDate, Currency transactionCurrency, Boolean isLatestOnly) {
		return getCashout(null, currentDate, transactionCurrency, isLatestOnly);
	}

	BigDecimal getCashin(Date currentDate, Currency transactionCurrency, Boolean isLatestOnly) {
		return getCashin(null, currentDate, transactionCurrency, isLatestOnly);
	}

	@Transient
	private EventSignalConfig getEventsConfig() {
		return ((EventSignalConfig) ConfigThreadLocal.get("eventSignal"));
	}


	@OneToMany(mappedBy = "portfolioShare", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(FetchMode.SELECT)
	public Set<AlertOnThreshold> getAlertsOnThreshold() {
		return alertsOnThreshold;
	}

	@SuppressWarnings("unused")
	private void setAlertsOnThreshold(Set<AlertOnThreshold> alerts) {
		this.alertsOnThreshold = alerts;
	}

	@Enumerated(EnumType.STRING)
	public Currency getTransactionCurrency() {
		return transactionCurrency;
	}

	@SuppressWarnings("unused")
	private void setTransactionCurrency(Currency transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public int compareTo(PortfolioShare o) {
		int stock = this.getStock().compareTo(o.getStock());
		if (stock == 0) return this.getPortfolio().getName().compareTo(o.getPortfolio().getName());
		return stock;
	}

	@Transient
	public InOutWeighted getInflatWeightedInvested(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return getPortfolio().getInflatWeightedInvestedFor(this, currentEndDate, currency, isLatestOnly);
	}

	InOutWeighted calculateInflationAndExpectationWeightedInvestedCash(Date currentEndDate, SortedSet<TransactionElement> transactionsForStock, Currency currency)  throws InvalidAlgorithmParameterException {

		if (transactionsForStock.isEmpty()) {
			throw new InvalidAlgorithmParameterException("No transaction data for " + this);
		}

		CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();
		BigDecimal weightedCashin = BigDecimal.ZERO;
		BigDecimal weightedCashout = BigDecimal.ZERO;

		//Between transactions
		Queue<TransactionElement> transactionsForStockQ = new ArrayDeque<TransactionElement>(transactionsForStock);

		TransactionElement inPreviousTransaction = transactionsForStockQ.poll();
		TransactionElement outPreviousTransaction = null;

		BigDecimal inPrevPrice = currencyConverter.convert(inPreviousTransaction.getCurrency(), currency, inPreviousTransaction.getPrice(), inPreviousTransaction.getDate());
		weightedCashin = inPreviousTransaction.getQuantity().multiply(inPrevPrice).setScale(10, RoundingMode.HALF_EVEN);

		while (!transactionsForStockQ.isEmpty()) {

			TransactionElement transaction = transactionsForStockQ.poll();
			BigDecimal price = currencyConverter.convert(transaction.getCurrency(), currency, transaction.getPrice(), transaction.getDate());

			//in
			if (transaction.transactionType().equals(TransactionType.AIN)) {

				//Weighting previous invest
				BigDecimal inflationRate = getInflationAndAddedExpectedRate(inPreviousTransaction.getDate(), transaction.getDate(), false);
				BigDecimal inflationRateFactor =  BigDecimal.ONE.add(inflationRate);
				weightedCashin = weightedCashin.multiply(inflationRateFactor).setScale(10, RoundingMode.HALF_EVEN);

				//Adding transaction
				BigDecimal transactionValue = transaction.getQuantity().multiply(price).setScale(10, RoundingMode.HALF_EVEN);
				weightedCashin = weightedCashin.add(transactionValue);

				inPreviousTransaction = transaction;

				if (LOGGER.isDebugEnabled()) LOGGER.debug("In Weighted value : " + weightedCashin);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("In Added quantity : " + transaction.getQuantity());
			} 

			//out
			if (transaction.transactionType().equals(TransactionType.AOUT)) {

				//Weighting previous invest
				if (outPreviousTransaction != null) {

					BigDecimal inflationRate = getInflationAndAddedExpectedRate(outPreviousTransaction.getDate(), transaction.getDate(), false);
					BigDecimal inflationRateFactor =  BigDecimal.ONE.add(inflationRate);
					weightedCashout = weightedCashout.multiply(inflationRateFactor).setScale(10, RoundingMode.HALF_EVEN);

				}

				//Adding transaction
				BigDecimal transactionValue = transaction.getQuantity().abs().multiply(price).setScale(10, RoundingMode.HALF_EVEN);
				weightedCashout = weightedCashout.add(transactionValue);

				outPreviousTransaction = transaction;

				if (LOGGER.isDebugEnabled()) LOGGER.debug("Out Weighted value : " + weightedCashout);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Out Added quantity : " + transaction.getQuantity().abs());

			}
		}

		//To Current date
		//in
		BigDecimal toDateRateIn = getInflationAndAddedExpectedRate(inPreviousTransaction.getDate(), currentEndDate, false);
		BigDecimal toDateRateInFactor = BigDecimal.ONE.add(toDateRateIn);
		//Double compoundRate = compoundRate(inPreviousTransaction.getDate(), currentEndDate, r.doubleValue());
		weightedCashin = weightedCashin.multiply(toDateRateInFactor).setScale(10, RoundingMode.HALF_EVEN);
		//out
		if (outPreviousTransaction != null) {
			BigDecimal toDateRateOut = getInflationAndAddedExpectedRate(outPreviousTransaction.getDate(), currentEndDate, false);
			BigDecimal toDateRateOutFactor = BigDecimal.ONE.add(toDateRateOut);
			//Double compoundRateOut = compoundRate(inPreviousTransaction.getDate(), currentEndDate, rOut.doubleValue());
			weightedCashout = weightedCashout.multiply(toDateRateOutFactor).setScale(10, RoundingMode.HALF_EVEN);
		}

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Weighted invested value for " + this.stock.getIsin() + " is " + weightedCashin);

		return new InOutWeighted(weightedCashin, weightedCashout, currentEndDate);
	}

	private BigDecimal getInflationAndAddedExpectedRate(Date firstDate, Date secondDate, Boolean addExpectationRate)  {

		BigDecimal inflationRateForDate = getInflationRateBetweenDates(firstDate, secondDate);
		if (addExpectationRate) {
			BigDecimal expectedRate = getExpectationRateForDate(firstDate, secondDate);
			inflationRateForDate = inflationRateForDate.add(expectedRate);
		}
		return inflationRateForDate;

	}

	private BigDecimal getExpectationRateForDate(Date firstDate, Date secondDate) {

		double yearlyRate = getEventsConfig().getExpectedRate().doubleValue();
		Double compoundRate = compoundRate(firstDate, secondDate, yearlyRate);
		return new BigDecimal(compoundRate.toString()).subtract(BigDecimal.ONE);

	}

	protected Double compoundRate(Date firstDate, Date secondDate, double yearlyRate) {
		long firstMilli = firstDate.getTime(); 
		long secondMilli = secondDate.getTime();
		long diffMilli = secondMilli - firstMilli;
		Double nbyears = Double.valueOf(diffMilli) / 1000 / 60 / 60 / 24 / 365;
		Double compoundRate = Math.pow((1 + yearlyRate), nbyears); // fv = pv * (1 + r) ^ np where r is the rate over 1 p == pv * Math.exp(r*np)
		return compoundRate;
	}

	private BigDecimal getInflationRateBetweenDates(Date firstDate, Date secondDate) {

		BigDecimal inflationRate = BigDecimal.ZERO;
		try {
			inflationRate = getInflation.inflationRateWithinDateRange(firstDate, secondDate);
		} catch (Exception e) {
			LOGGER.warn(e,e); 
		}

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Inflation rate between "+firstDate+" and "+secondDate+" is : "+inflationRate);
		return inflationRate;

	}
	
	/**
	 * isLatestOnly == false implies was owned included even if not owned any more.
	 * @param end
	 * @param isLatestOnly
	 * @return
	 */
	public Boolean isOwned(Date end, Boolean isLatestOnly) {
		return getPriceAvgBuy(end, getTransactionCurrency(), isLatestOnly, false).compareTo(BigDecimal.ZERO) > 0;
	}
	
	public String getExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(String externalAccount) {
		this.externalAccount = externalAccount;
	}

	/**
	 * Gets the sell price for zero loss if cash in and out are weighted with inflation ( = inflation weighted avg buy price) : (Ow-Iw)/quantity
	 * @param isLatestOnly 
	 */
	@Transient
	public BigDecimal getZeroPriceWeighted(Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {

		InOutWeighted cashInOutWeighted = getInflatWeightedInvested(currentStartDate, currentEndDate, currency, isLatestOnly);
		BigDecimal cashInSubtractOutWeighted = cashInOutWeighted.getWeightedInvestedStillIn();
		BigDecimal quantity = this.getQuantity(currentStartDate, currentEndDate, isLatestOnly);
		if (quantity.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal costPerUnitWeighted = cashInSubtractOutWeighted.divide(quantity, 10, RoundingMode.HALF_EVEN);
		return costPerUnitWeighted;

	}

	@OneToMany(mappedBy = "portfolioShare", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(FetchMode.SELECT)
	public Set<AlertOnEvent> getAlertsOnEvent() {
		return alertsOnEvent;
	}

	@SuppressWarnings("unused")
	private void setAlertsOnEvent(Set<AlertOnEvent> alertsOnEvent) {
		this.alertsOnEvent = alertsOnEvent;
	}

	public BigDecimal getPriceAvgBuy(Date currentDate, Currency transactionCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return getPriceAvgBuy(null, currentDate, transactionCurrency, isLatestOnly, isRealisedOnly);
	}

	public BigDecimal getQuantity(Date currentDate, Boolean isLatestOnly) {
		return getQuantity(null, currentDate, isLatestOnly);
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Double getWeight() {
		return weight;
	}


}
