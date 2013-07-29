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
package com.finance.pms.portfolio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.Transaction.TransactionType;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name = "PORTFOLIO")
public class PortfolioShare implements Serializable, Comparable<PortfolioShare> {
	
	private static final long serialVersionUID = 2121776460759183840L;
	private static MyLogger LOGGER = MyLogger.getLogger(PortfolioShare.class);
	
	public static BigDecimal TRANSACTION_FEE = new BigDecimal(MainPMScmd.getPrefs().get("portfolio.fee", "0.01")).setScale(2);
	
	private Stock stock;
	private Date buyDate;
	private BigDecimal quantity;
	private BigDecimal cashin;
	private BigDecimal cashout;
	private MonitorLevel monitorLevel;
	private Currency transactionCurrency;
	private AbstractSharesList portfolio;
	private Set<AlertOnThreshold> alertsOnThreshold;
	private Set<AlertOnEvent> alertsOnEvent;
	private String account;

	//hib
	public PortfolioShare() {
		super();
	}

	 public PortfolioShare(PortfolioShare portfolioShare) {

		this.stock = portfolioShare.getStock();
		this.buyDate = new Date(portfolioShare.buyDate.getTime());
		this.quantity = portfolioShare.getQuantity();
		this.cashin = portfolioShare.getCashin();
		this.cashout = portfolioShare.getCashout();
		this.monitorLevel = portfolioShare.getMonitorLevel();
		this.transactionCurrency = portfolioShare.getTransactionCurrency();
		this.portfolio = portfolioShare.getPortfolio();
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			this.alertsOnThreshold.add(new AlertOnThreshold(alert, this));
		}
		this.alertsOnEvent = new HashSet<AlertOnEvent>();
		for (AlertOnEvent alert : portfolioShare.getAlertsOnEvent()) {
			this.alertsOnEvent.add(new AlertOnEvent(alert, this));
		}
		this.account = portfolioShare.getAccount();

	}

	/**
	 * @param stock
	 * @param quantity
	 * @param cashin
	 */
	private void nullAmountsWarning(Stock stock, BigDecimal quantity, BigDecimal cashin) {
		if (cashin.compareTo(BigDecimal.ZERO) == 0 || quantity.compareTo(BigDecimal.ZERO) == 0) {
			LOGGER.warn("Zero values for " + stock.getSymbol() + " : cash in " + this.cashin + " quantity " + this.quantity);
		}
	}

	PortfolioShare(AbstractSharesList sharesList, Stock stock, Date currentDate, MonitorLevel monitor, Currency transactionCurrency) {

		super();
		this.stock = stock;
		this.quantity = BigDecimal.ZERO;
		this.buyDate = currentDate;
		this.cashin = BigDecimal.ZERO;
		this.cashout = BigDecimal.ZERO;
		this.monitorLevel = monitor;
		this.transactionCurrency = transactionCurrency;
		this.portfolio = sharesList;
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		this.alertsOnEvent = new HashSet<AlertOnEvent>();

	}

	/**
	 * @param stock
	 * @param startDate 
	 * @param transactionCurrency
	 */
	private BigDecimal closeQuotationFor(Stock stock, Currency transactionCurrency, Date currentDate) {
		try {			
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency);
			return quotations.getClosestCloseForDate(currentDate);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.warn("No quotations for " + stock);
		} catch (NoQuotationsException e) {
			LOGGER.warn("No quotations for " + stock);
		}
		return BigDecimal.ZERO;
	}

	PortfolioShare(AbstractSharesList shareList, Stock stock, BigDecimal quantity, Date buyDate, BigDecimal cashin, BigDecimal cashout, MonitorLevel monitor, Currency transactionCurrency) {
		this.stock = stock;
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.cashin = cashin;
		this.cashout = cashout;
		this.monitorLevel = monitor;
		this.transactionCurrency = transactionCurrency;
		this.portfolio = shareList;
		this.alertsOnThreshold = new HashSet<AlertOnThreshold>();
		this.alertsOnEvent = new HashSet<AlertOnEvent>();
		
		nullAmountsWarning(stock, quantity, cashin);

	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, columnDefinition = " DATE DEFAULT '01/01/1970' ")
	//TODO rename to lastTransactionDate
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * Gets the cashin.
	 * 
	 * @return the cashin
	 */
	@Column(nullable = false)
	public BigDecimal getCashin() {
		return cashin;
	}

	/**
	 * Sets the cashin.
	 * 
	 * @param cashin
	 *            the new cashin
	 * @throws Exception
	 */
	void setCashin(BigDecimal cashin) {
		this.cashin = cashin;
	}

	/**
	 * Gets the cashout.
	 * 
	 * @return the cashout
	 */
	@Column(nullable = false, columnDefinition = " NUMERIC(19,2) ")
	public BigDecimal getCashout() {
		return cashout;
	}

	/**
	 * Sets the cashout.
	 * 
	 * @param cashout
	 *            the new cashout
	 * @throws Exception
	 */
	void setCashout(BigDecimal cashout) {
		this.cashout = cashout;
	}

	/**
	 * Gets the quantity.
	 * 
	 * @return the quantity
	 */
	@Column(nullable = false)
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 * 
	 * @param quantity
	 *            the new quantity
	 */
	void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the stock.
	 * 
	 * @return the stock
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumns( { @JoinColumn(name = "isin", referencedColumnName = "isin"), @JoinColumn(name = "symbol", referencedColumnName = "symbol") })
	@ForeignKey(name = "FK_STOCK")
	@Id
	public Stock getStock() {
		return stock;
	}

	/**
	 * Gets the symbol.
	 * 
	 * @return the symbol
	 */
	@Transient
	public String getSymbol() {
		return stock.getSymbol();
	}

	/**
	 * Gets the isin.
	 * 
	 * @return the isin
	 */
	@Transient
	public String getIsin() {
		return stock.getIsin();
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	@Transient
	public String getName() {
		return stock.getName();
	}

	/**
	 * Sets the stock.
	 * 
	 * @param stock
	 *            the new stock
	 */
	@SuppressWarnings("unused")
	private void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * Gets the last day close value.
	 * 
	 * @return the last day close value
	 */
	@Transient
	public BigDecimal getCloseQuotationFor(Date currentDate) {
		return closeQuotationFor(stock, transactionCurrency, currentDate);
	}

	/**
	 * Gets the profit and loss. (v+o-i)/i
	 * @param currentDate 
	 * @return the profit and loss
	 */
	@Transient
	//TODO calculate stillIn price for currentDate using transactions history
	public BigDecimal getUnrealizedProfit(Date currentDate) {
		try {
			return this.getUnrealizedGain(currentDate).divide(this.getCashin(),10,BigDecimal.ROUND_DOWN); //.multiply(new BigDecimal(100)).setScale(10,BigDecimal.ROUND_DOWN);
		} catch (ArithmeticException e) {
			LOGGER.error("Cashin is 0 for "+this+" in "+this.portfolio);
			return BigDecimal.ZERO;
		}
	}
	
	@Transient
	//TODO calculate stillIn price for currentDate using transactions history
	public BigDecimal getUnrealizedGain(Date currentDate) {
		try {
			BigDecimal stillIn = this.getCashin().subtract(this.getCashout());
			if (stillIn.compareTo(BigDecimal.ZERO) < 0) stillIn = BigDecimal.ZERO;
			return (this.getValueForDate(currentDate).subtract(stillIn).setScale(10,BigDecimal.ROUND_DOWN));
		} catch (ArithmeticException e) {
			LOGGER.error(e,e);
			return BigDecimal.ZERO;
		}
	}
	
	public BigDecimal getProfit(Date currentDate) {
		try {
			return getCloseQuotationFor(currentDate).subtract(getAvgBuyPrice()).divide(getAvgBuyPrice(),10,BigDecimal.ROUND_DOWN);
		} catch (ArithmeticException e) {
			LOGGER.error(e,e);
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * Gets the profit and loss inflation weighted (v+Ow-Iw)/Iw
	 * @param currentDate 
	 * @return the profit and loss
	 */
	@Transient
	public BigDecimal getWeightedUnrealizedProfit(Date currentDate) {
		try {
			InOutWeighted weightedInOut = this.getWeightedInvested(currentDate);
			BigDecimal weightedInvestedStillIn = weightedInOut.getWeightedInvestedStillIn();
			return 	this.getValueForDate(currentDate).subtract(weightedInvestedStillIn)
					.divide(weightedInOut.getIn(),10,BigDecimal.ROUND_DOWN); //.multiply(new BigDecimal(100)).setScale(10,BigDecimal.ROUND_DOWN);
		} catch (ArithmeticException e) {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Gets the buying price.
	 * @return the buying price
	 */
	@Transient
	//TODO calculate using avg buy price for currentDate using transactions history
	public BigDecimal getAvgBuyPrice() {
		if (this.getQuantity().compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		return this.getCashin().subtract(this.getCashout()).divide(this.getQuantity(), 10, BigDecimal.ROUND_DOWN);
	}

	@Transient
	public BigDecimal getValueForDate(Date currentDate) {
		return this.getQuantity().multiply(getCloseQuotationFor(currentDate)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Gets the monitor level.
	 * 
	 * @return the monitor level
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "MONITOR", nullable = false)
	public MonitorLevel getMonitorLevel() {
		return monitorLevel;
	}

	/**
	 * Sets the monitor level.
	 * 
	 * @param monitorLevel
	 *            the new monitor level
	 */
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
		return "PortfolioShare [portfolio = "+portfolio.getName()+", stock=" + stock.getFriendlyName() + ", cashin=" + cashin + ", cashout=" + cashout + ", quantity=" + quantity + "]";
	}
	
	public void applyTransaction(Transaction transaction, boolean propagate) throws InvalidQuantityException {

		if (transaction.getModtype().equals(TransactionType.AIN)) {
			this.setQuantity(transaction.getQuantity().add(this.getQuantity()));
			this.setCashin(transaction.fullAmountIn());
			if (propagate) this.portfolio.addAmountToTotalAmountIn(transaction.amount(), transactionCurrency, transaction.getDate());
		}
		if (transaction.getModtype().equals(TransactionType.AOUT)) {
			BigDecimal newQuantity = transaction.getQuantity().subtract(this.getQuantity()).negate();
			if (newQuantity.compareTo(BigDecimal.ZERO) < 0) throw new InvalidQuantityException("Trying to sell more than available. The quantity can't be negative!", null);
			this.setQuantity(newQuantity);
			this.setCashout(transaction.fullAmountOut());
			if (propagate) this.portfolio.addAmountToTotalAmountOut(transaction.amount(), transactionCurrency, transaction.getDate());
		}
		if (transaction.getModtype().equals(TransactionType.NULL))
			throw new InvalidParameterException(" Transaction mode shouldn't be NULL : " + transaction);
	}
	
	public void addBuyAlerts(BigDecimal transcationPrice, Date currentDate) {
		addBuyPriceAlerts();
		addAboveTakeProfitAlert(this.getAvgBuyPrice());
		addWeightedZeroProfitAlertGuardSetter(this.getAvgBuyPrice(), currentDate);
		addChannelAlerts(transcationPrice);
	}

	private void addBuyPriceAlerts() {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
		addBuyPriceAlertAbove();
		addBuyPriceAlertBelow();	
	}

	private void addBuyPriceAlertBelow() {
		BigDecimal avgBuyPrice = this.getAvgBuyPrice();
		this.addAlertOnThreshold(ThresholdType.DOWN, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + avgBuyPrice+")");
	}

	private void addBuyPriceAlertAbove() {
		BigDecimal avgBuyPrice = this.getAvgBuyPrice();
		this.addAlertOnThreshold(ThresholdType.UP, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + avgBuyPrice+")");
	}

	/**
	 * If price is up n% above avg buy price = > sell  
	 * Shift up to current price if calculated threshold below current price
	 * @param transactionPrice
	 */
	private void addAboveTakeProfitAlert(BigDecimal calculationPrice) {
	
		try {
			this.removeAlertOnThresholdFor(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT);	

			BigDecimal sellLimitToPriceRate = getEventsConfig().getSellLimitToPrice();
			
			BigDecimal augmentedCashin = BigDecimal.ONE.add(sellLimitToPriceRate).multiply(this.getCashin());
			BigDecimal aboveSellLimit = augmentedCashin.subtract(this.getCashout()).divide(this.getQuantity(),4,BigDecimal.ROUND_CEILING);
			BigDecimal resultingPercentAboveAvgPrice = calculationPrice.divide(aboveSellLimit, 4, BigDecimal.ROUND_CEILING).subtract(BigDecimal.ONE.setScale(4));
			
			String aboveMessage = "("+readablePercentOf(sellLimitToPriceRate)+" profit. Price is "+ readablePercentOf(resultingPercentAboveAvgPrice) + " away from threshold)";
			
			addAboveTakeProfitAlert(aboveSellLimit,aboveMessage);
			
		} catch (RuntimeException e) {
			LOGGER.error("Failed to update portfolioshare :"+this,e);
			throw e;
		}
	}
	

	private void addWeightedZeroProfitAlertGuardSetter(BigDecimal avgBuyPrice, Date currentDate) {
		
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	
	
		BigDecimal sellLimitGuardPriceRate;
		InOutWeighted weightedInOut = this.getWeightedInvested(currentDate);
		BigDecimal sellLimitGuardPrice;
		if (!weightedInOut.isEmpty()) {
			sellLimitGuardPrice = weightedInOut.getWeightedInvestedStillIn().divide(this.getQuantity(), 10, BigDecimal.ROUND_CEILING);
			sellLimitGuardPriceRate =  (avgBuyPrice.compareTo(BigDecimal.ZERO) > 0)?
												sellLimitGuardPrice.divide(avgBuyPrice, 10, BigDecimal.ROUND_CEILING).subtract(BigDecimal.ONE.setScale(4)):
												BigDecimal.ZERO;
		} else {
			sellLimitGuardPriceRate = getEventsConfig().getSellLimitGuardPrice();
			sellLimitGuardPrice = addPercentage(avgBuyPrice, sellLimitGuardPriceRate);
		}
		
		if (sellLimitGuardPrice.compareTo(this.getCloseQuotationFor(currentDate)) <= 0) {
			addWeigthedZeroProfitAlertGuard(sellLimitGuardPrice);
		} else {
			String aboveMessage = "(Will set a guard when "+ readablePercentOf(sellLimitGuardPriceRate) + " above calc price " + avgBuyPrice +")";
			addWeigthedZeroProfitAlertGuardSetter(sellLimitGuardPrice, aboveMessage);
		}

	}

	
	public void addWeigthedZeroProfitAlertGuardSetter(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT, message);
	}
	
	private void addWeigthedZeroProfitAlertGuard(BigDecimal profitGuard) {
		
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	
		String belowGuardMessage = "";
		addWeigthedZeroProfitAlertGuard(profitGuard, belowGuardMessage);
	}

	/**
	 * @param sellLimitPriceRate
	 * @return
	 */
	private String readablePercentOf(BigDecimal sellLimitPriceRate) {
		//return sellLimitPriceRate.multiply(new BigDecimal(100)).setScale(10,BigDecimal.ROUND_DOWN).toString()+" %";
		NumberFormat format = new DecimalFormat("#0.00 %");
		return format.format(sellLimitPriceRate);
	}

	/**
	 * @param calculationPrice
	 * @param rateToCalculationPrice
	 * @return
	 */
	private BigDecimal addPercentage(BigDecimal calculationPrice, BigDecimal rateToCalculationPrice) {
		BigDecimal percentToBeAdded = calculationPrice.multiply(rateToCalculationPrice);
		BigDecimal aboveSellLimit = calculationPrice.add(percentToBeAdded);
		return aboveSellLimit;
	}

	/**
	 * @return
	 */
	@Transient
	private EventSignalConfig getEventsConfig() {
		return ((EventSignalConfig) ConfigThreadLocal.get("eventSignal"));
	}

	/**
	 * We don't want to sell more than n% below average buy price 
	 * If price is down n% below last transaction price and > avg buy price => sell ie stop loss. 
	 * If price more than n% below avg buy price  => nothing
	 * @param currentPrice
	 */
	private void addMovingLimitBelow(BigDecimal crossingPrice) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_PRICE_CHANNEL);

		BigDecimal limitPriceBelowRate = getEventsConfig().getLimitPriceBelow();
		BigDecimal belowSellLimit = addPercentage(crossingPrice, limitPriceBelowRate.negate());
		
		String belowMessage = "(" +readablePercentOf(limitPriceBelowRate) + " below calculation price " + crossingPrice+")";

		this.addAlertOnThreshold(ThresholdType.DOWN, belowSellLimit, AlertOnThresholdType.BELOW_PRICE_CHANNEL, belowMessage);
	}
	
	/**
	 * @param message 
	 * @param numberValue 
	 * 
	 */
	public void addAboveTakeProfitAlert(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT, message);
	}
	
	public void addWeigthedZeroProfitAlertGuard(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.DOWN, price, AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT, message);
	}

	public void addSimpleAlertOnThreshold(ThresholdType threshold, BigDecimal value, String message) {
		if (threshold.equals(ThresholdType.UP)) this.addAlertOnThreshold(threshold, value, AlertOnThresholdType.MANUALUP, message);
		if (threshold.equals(ThresholdType.DOWN)) this.addAlertOnThreshold(threshold, value, AlertOnThresholdType.MANUALDOWN, message);
	}

	public PortfolioShare resetCrossDown(AlertOnThreshold alert, BigDecimal crossingPrice) {
		LOGGER.debug("Params : " + alert);

		switch (alert.getAlertType()) {
			case BELOW_ZERO_WEIGHTED_PROFIT_LIMIT:
				//nothing as the alert (guard) will be repeated until further user action
				break;
			case AVG_BUY_PRICE:
				//remove and reset alert buy price up
				this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
				addBuyPriceAlertAbove();
				break;
			case BELOW_PRICE_CHANNEL:
				this.resetAlertLimitBelow(alert, crossingPrice);
				break;
			case MANUALUP:
			case MANUALDOWN:
				this.removeAlertOnThreshold(alert);
				break;
			default:
				LOGGER.error("Nothing to do for : " + alert);
		}

		return this;

	}

	public PortfolioShare resetCrossUp(AlertOnThreshold alert, BigDecimal crossingPrice) {
		LOGGER.debug("Params : " + alert);

		switch (alert.getAlertType()) {
			case BELOW_ZERO_WEIGHTED_PROFIT_LIMIT:
				//remove and set a take profit below alert guard
				addWeigthedZeroProfitAlertGuard(alert.getValue());
				break;
			case ABOVE_TAKE_PROFIT_LIMIT:
				//remove and reset a new take profit above alert
				addAboveTakeProfitAlert(crossingPrice);
				break;
			case AVG_BUY_PRICE:
				//remove and reset alert buy price down
				this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
				addBuyPriceAlertBelow();
				break;
			case ABOVE_PRICE_CHANNEL:
				this.resetAlertLimitAbove(alert, crossingPrice);
				break;
			case MANUALUP:
			case MANUALDOWN:
				this.removeAlertOnThreshold(alert);
				break;
			default:
				LOGGER.error("Nothing to do for : " + alert);
		}

		return this;

	}

	private void resetAlertLimitAbove(AlertOnThreshold alert, BigDecimal crossPrice) {
		this.removeAlertOnThreshold(alert);		
		addChannelAlerts(crossPrice);

	}

	/**
	 * @param alert
	 */
	private void addChannelAlerts(BigDecimal crossingPrice) {
			addMovingLimitAbove(crossingPrice);	
			addMovingLimitBelow(crossingPrice);
	}

	
	/**
	 * @param crossingPrice
	 */
	private void addMovingLimitAbove(BigDecimal crossingPrice) {

		this.removeAlertOnThresholdFor(AlertOnThresholdType.ABOVE_PRICE_CHANNEL);

		String message = "";
		BigDecimal limitPriceAboveRate = getEventsConfig().getLimitPriceAbove();
		BigDecimal limitAbovePrice = addPercentage(crossingPrice, limitPriceAboveRate);
		message = "(" +readablePercentOf(limitPriceAboveRate) + " above calculation price " + crossingPrice +")";

		this.addAlertOnThreshold(ThresholdType.UP, limitAbovePrice, AlertOnThresholdType.ABOVE_PRICE_CHANNEL, message);

	}

	private void resetAlertLimitBelow(AlertOnThreshold alert, BigDecimal crossPrice) {
		this.removeAlertOnThreshold(alert);
		addChannelAlerts(crossPrice);
	
	}

	public void addAlertOnThreshold(ThresholdType threshold, BigDecimal value, AlertOnThresholdType alertType, String message) {
		if (value != null) {
			this.alertsOnThreshold.add(new AlertOnThreshold(this, threshold, alertType, value, message));
		}
	}
	
	public void addAlertOnEvent(String eventInfoReference, MonitorLevel  monitorLevel, String optionalMessage) {
		this.alertsOnEvent.add(new AlertOnEvent(this, eventInfoReference, monitorLevel, optionalMessage));
	}

	public void removeAlertOnThreshold(AlertOnThreshold alert) {
		this.alertsOnThreshold.remove(alert);
	}
	
	private void removeAlertOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = getAlertsOnThresholdFor(alertType);
		this.alertsOnThreshold.removeAll(ret);		
	}
	
	public void clearAlertOnEvent() {
		this.alertsOnEvent.clear();
	}


	public HashSet<AlertOnThreshold> getAlertsOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : this.alertsOnThreshold) {
			if (alertType.equals(alert.getAlertType())) {
				ret.add(alert);
			}
		}
		return ret;
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

	@Transient
	public Set<AlertOnThreshold> getAlertsOnThresholdUp() {
		Set<AlertOnThreshold> alertsUp = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : alertsOnThreshold) {
			if (alert.getThresholdType().equals(ThresholdType.UP)) alertsUp.add(alert);
		}
		return alertsUp;
	}

	@Transient
	public Set<AlertOnThreshold> getAlertsOnThresholdDown() {
		Set<AlertOnThreshold> alertsDown = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : alertsOnThreshold) {
			if (alert.getThresholdType().equals(ThresholdType.DOWN)) alertsDown.add(alert);
		}
		return alertsDown;
	}

	@Enumerated(EnumType.STRING)
	public Currency getTransactionCurrency() {
		return transactionCurrency;
	}

	@SuppressWarnings("unused")
	private void setTransactionCurrency(Currency transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public void newAmounts(BigDecimal quantity, BigDecimal cashIn, BigDecimal cashOut, Date currentDate) {
		
		this.setQuantity(quantity.add(this.getQuantity()));
		this.setCashin(cashIn.add(this.getCashin()));
		this.setCashout(cashOut.add(this.getCashout()));
		this.portfolio.addAmountToTotalAmountIn(cashIn, transactionCurrency, currentDate);
		this.portfolio.addAmountToTotalAmountOut(cashOut, transactionCurrency, currentDate);
		
	}

	public int compareTo(PortfolioShare o) {
		int stock = this.getStock().compareTo(o.getStock());
		if (stock == 0) return this.getPortfolio().getName().compareTo(o.getPortfolio().getName());
		return stock;
	}
	
	@Transient
	public InOutWeighted getWeightedInvested(Date currentDate) {
		if (this.account != null) {
			try {
				SortedSet<TransactionElement> transactionsForStock = PortfolioMgr.getInstance().getPortfolioDAO().loadTransactionReportFor(stock, account, currentDate);
				return this.calculateInflationAndExpectationWeightedInvestedCash(currentDate, transactionsForStock);
			} catch (InvalidAlgorithmParameterException e) {
				return new InOutWeighted(this.getCashin(), this.getCashout(), currentDate);
			}
		}
		return new InOutWeighted(this.getCashin(), this.getCashout(), currentDate);
	}
	
	public BigDecimal calculateGain(Date currentDate) {
		
		BigDecimal profitAmount = BigDecimal.ZERO;
		SortedSet<TransactionElement> transactionsForStock;
		
		if (this.account != null) {//Gnucash portfolio share with transaction history
			try {
				transactionsForStock = PortfolioMgr.getInstance().getPortfolioDAO().loadTransactionReportFor(stock, account, currentDate);

				if (transactionsForStock.isEmpty()) {
					throw new InvalidAlgorithmParameterException("No transaction data for "+this);
				}

				CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();

				BigDecimal cashIn = BigDecimal.ZERO;
				BigDecimal currentQuantity = BigDecimal.ZERO;
				BigDecimal cashOut = BigDecimal.ZERO;
				
				for (TransactionElement transaction : transactionsForStock) {

					BigDecimal price = currencyConverter.convert(transaction.getCurrency(), this.transactionCurrency, transaction.getPrice(), transaction.getDate());
					if (price.compareTo(BigDecimal.ZERO) == 0) { //stok split or merge
						currentQuantity = currentQuantity.add(transaction.getQuantity());
					} else {
						BigDecimal amount = price.multiply(transaction.getQuantity());
	
						if (transaction.getQuantity().compareTo(BigDecimal.ZERO) < 0) {//sell transaction
							//Profit
							BigDecimal avgBuyPrice = cashIn.add(cashOut).divide(currentQuantity,10,BigDecimal.ROUND_DOWN);
							profitAmount = profitAmount.add(avgBuyPrice.subtract(price).multiply(transaction.getQuantity()).setScale(10,BigDecimal.ROUND_DOWN));
							
							currentQuantity = currentQuantity.add(transaction.getQuantity());
							cashOut = cashOut.add(amount).setScale(10,BigDecimal.ROUND_DOWN);
						} else { //buy transaction
							currentQuantity = currentQuantity.add(transaction.getQuantity());
							cashIn = cashIn.add(amount).setScale(10,BigDecimal.ROUND_DOWN);
						}
					}
				}

			} catch (InvalidAlgorithmParameterException e) {
				return BigDecimal.ZERO;
			}
			
		} else {
			//return this.getCashout().subtract(this.getCashin());
			return this.getCashout();
		}
		
		return profitAmount;
	}
	
	InOutWeighted calculateInflationAndExpectationWeightedInvestedCash(Date currentDate, SortedSet<TransactionElement> transactionsForStock) 
			throws InvalidAlgorithmParameterException {
		
		if (transactionsForStock.isEmpty()) {
			throw new InvalidAlgorithmParameterException("No transaction data for "+this);
		}
		
		CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();
		BigDecimal weightedCashin = BigDecimal.ZERO;
		BigDecimal weightedCashout = BigDecimal.ZERO;
		
		//Between transactions
		Queue<TransactionElement> transactionsForStockQ = new ArrayDeque<TransactionElement>(transactionsForStock);
		
		TransactionElement inPreviousTransaction = transactionsForStockQ.poll();
		TransactionElement outPreviousTransaction = null;
		
		BigDecimal inPrevPrice = currencyConverter.convert(inPreviousTransaction.getCurrency(), this.transactionCurrency, inPreviousTransaction.getPrice(), inPreviousTransaction.getDate());
		weightedCashin = inPreviousTransaction.getQuantity().multiply(inPrevPrice).setScale(10, BigDecimal.ROUND_DOWN);
		
		while (!transactionsForStockQ.isEmpty()) {
			TransactionElement transaction = transactionsForStockQ.poll();
			
			BigDecimal price = currencyConverter.convert(transaction.getCurrency(), this.transactionCurrency, transaction.getPrice(), transaction.getDate());
			
			//in
			if (transaction.getQuantity().compareTo(BigDecimal.ZERO) > 0 && price.compareTo(BigDecimal.ZERO) != 0) {//buy and not split
				
				//Weighting previous invest
				BigDecimal worseRate =  BigDecimal.ONE.add(getShouldWorseRateForDate(inPreviousTransaction.getDate(), transaction.getDate(), true));
				weightedCashin = weightedCashin.multiply(worseRate).setScale(10, BigDecimal.ROUND_DOWN);

				//Adding transaction
				BigDecimal transactionValue = transaction.getQuantity().multiply(price);
				weightedCashin = weightedCashin.add(transactionValue);
				
				inPreviousTransaction = transaction;
				
				LOGGER.debug("In Weighted value : "+weightedCashin);
				LOGGER.debug("In Added quantity : "+transaction.getQuantity());
			} 
			
			//out
			if (transaction.getQuantity().compareTo(BigDecimal.ZERO) < 0 && price.compareTo(BigDecimal.ZERO) != 0) {//buy and not split
				
				//Weighting previous invest
				if (outPreviousTransaction != null) {
					
					BigDecimal worseRate =  BigDecimal.ONE.add(getShouldWorseRateForDate(outPreviousTransaction.getDate(), transaction.getDate(), false));
					weightedCashout = weightedCashout.multiply(worseRate).setScale(10, BigDecimal.ROUND_DOWN);

					//Adding transaction
					BigDecimal transactionValue = transaction.getQuantity().abs().multiply(price);
					weightedCashout = weightedCashout.add(transactionValue);
					
					outPreviousTransaction = transaction;
					
					LOGGER.debug("Out Weighted value : "+weightedCashout);
					LOGGER.debug("Out Added quantity : "+transaction.getQuantity().abs());
					
				} else {
					BigDecimal outPrevPrice = currencyConverter.convert(transaction.getCurrency(), this.transactionCurrency, transaction.getPrice(), transaction.getDate());
					weightedCashout = transaction.getQuantity().abs().multiply(outPrevPrice).setScale(10, BigDecimal.ROUND_DOWN);
					outPreviousTransaction = transaction;
				}
	
			} 	
		}
		
		//to Current date
			//in
		BigDecimal inLastWorseRate = BigDecimal.ONE.add(getShouldWorseRateForDate(inPreviousTransaction.getDate(), currentDate, true));
		weightedCashin = weightedCashin.multiply(inLastWorseRate).setScale(10, BigDecimal.ROUND_DOWN);
			//out
		if (outPreviousTransaction != null) {
			BigDecimal outLastWorseRate = BigDecimal.ONE.add(getShouldWorseRateForDate(outPreviousTransaction.getDate(), currentDate, false));
			weightedCashout = weightedCashout.multiply(outLastWorseRate).setScale(10, BigDecimal.ROUND_DOWN);
		}
	
		LOGGER.debug("Weighted invested value for "+this.stock.getIsin()+" is "+weightedCashin);

		return new InOutWeighted(weightedCashin, weightedCashout, currentDate);
	}
	
	public class InOutWeighted {
		

		private BigDecimal in;
		private BigDecimal out;
		private Date date;

		private InOutWeighted(BigDecimal in, BigDecimal out, Date date) {
			super();
			this.in = in;
			this.out = out;
			this.date = date;
		}
		
		public Boolean isEmpty() {
			return in.compareTo(BigDecimal.ZERO) == 0;
		}

		public InOutWeighted(Date date) {
			this.in = BigDecimal.ZERO;
			this.out = BigDecimal.ZERO;
			this.date = date;
		}
		
		BigDecimal getWeightedInvestedStillIn() {
			BigDecimal weightedInvestedStillIn = in.subtract(out);
			if (weightedInvestedStillIn.compareTo(BigDecimal.ZERO) < 0) weightedInvestedStillIn = BigDecimal.ZERO;
			return weightedInvestedStillIn;
		}
		
		public BigDecimal getIn() {
			return in;
		}

		public BigDecimal getOut() {
			return out;
		}
		
		public Date getDate() {
			return date;
		}
		
	}
	
	private BigDecimal getShouldWorseRateForDate(Date firstDate, Date secondDate, Boolean addExpectationRate)  {
		BigDecimal inflationRateForDate;
		inflationRateForDate = getInflationRateBetweenDates(firstDate,secondDate);
		if (addExpectationRate) {
			BigDecimal expectedRate = getExpectationRateForDate(firstDate, secondDate);
			inflationRateForDate = inflationRateForDate.add(expectedRate);
		}
		return inflationRateForDate;
		
	}
	
	private BigDecimal getExpectationRateForDate(Date firstDate, Date secondDate) {
		long firstMilli = firstDate.getTime(); 
		long secondMilli = secondDate.getTime();
		long diffMilli = secondMilli - firstMilli;
		Double nbyears = new Double(diffMilli) / 1000 / 60 / 60 / 24 / 365;
		Double expectedRate = 0d;
		// fv= pv * (1 + r) ^ np
		expectedRate = Math.pow((1 + getEventsConfig().getExpectedRate().doubleValue()), nbyears);
		return new BigDecimal(expectedRate.toString()).subtract(BigDecimal.ONE);
	}
	
	private BigDecimal getInflationRateBetweenDates(Date fisrtDate,Date secondDate) {
		
		BigDecimal inflationRate = BigDecimal.ZERO;
		try {
			BigDecimal inflatAtFirst;
			BigDecimal inflatAtSecond;
			Stock stock =new Stock(
					ProvidersInflation.SYMBOL, ProvidersInflation.SYMBOL, ProvidersInflation.SYMBOL,
					new Boolean(false), StockCategories.INDICES_OTHER, DateFactory.dateAtZero(), new SymbolMarketQuotationProvider(),
					new MarketValuation(Market.NYSE), "None", TradingMode.UNKNOWN, 0L);
			Quotations inflationQuotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, fisrtDate, false, Currency.USD);
			inflatAtFirst = inflationQuotations.getClosestCloseForDate(fisrtDate);
			inflatAtSecond = inflationQuotations.getClosestCloseForDate(secondDate);
			inflationRate = inflatAtSecond.subtract(inflatAtFirst).divide(inflatAtFirst,10,BigDecimal.ROUND_DOWN);
		} catch (Exception e) {
			LOGGER.warn(e,e); 
		}
		
		LOGGER.debug("Inflation rate between "+fisrtDate+" and "+secondDate+" is : "+inflationRate);
		return inflationRate;
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Gets the sell price for zero loss if cash in and out are weighted with inflation ( = inflation weighted avg buy price) : (Ow-Iw)/quantity
	 * @param currentDate 
	 * @return the profit and loss
	 */
	@Transient
	public BigDecimal getPriceForZeroWeightedProfit(Date currentDate) {
		
		InOutWeighted weightedInOut = this.getWeightedInvested(currentDate);
		BigDecimal weightedInvested = weightedInOut.getWeightedInvestedStillIn();
		if (this.getQuantity().compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal sellLimitGuardPrice = weightedInvested.divide(this.getQuantity(),4,BigDecimal.ROUND_CEILING);
		return sellLimitGuardPrice;
		
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

}
