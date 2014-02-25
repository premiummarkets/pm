/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
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
	private MonitorLevel monitorLevel;
	private Currency transactionCurrency;
	private AbstractSharesList portfolio;
	private Set<AlertOnThreshold> alertsOnThreshold;
	private Set<AlertOnEvent> alertsOnEvent;
	private String externalAccount;

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
			this.alertsOnThreshold.add(new AlertOnThreshold(alert, this));
		}
		this.alertsOnEvent = new HashSet<AlertOnEvent>();
		for (AlertOnEvent alert : portfolioShare.getAlertsOnEvent()) {
			this.alertsOnEvent.add(new AlertOnEvent(alert, this));
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
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency, ValidityFilter.CLOSE);
			return quotations.getClosestCloseForDate(currentDate);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.warn("No quotations for " + stock);
		} catch (NoQuotationsException e) {
			LOGGER.warn("No quotations for " + stock);
		}
		return BigDecimal.ZERO;
	}
	
	@Transient
	public Date getLastTransactionDate() {
		return this.portfolio.getLastTransactionFor(this, null, EventSignalConfig.getNewDate());
	}
 
	@Transient
	public SortedSet<TransactionElement> getTransactions() {
		return this.portfolio.getTransactionsFor(this, null, EventSignalConfig.getNewDate());
	}

	public BigDecimal getCashin(Date currentStartDate, Date currentEndDate, Currency currency) {
		return this.portfolio.getCashInFor(this, currentStartDate, currentEndDate, currency);
	}
	
	public BigDecimal getCashout(Date currentStartDate, Date currentEndDate, Currency currency) {
		return this.portfolio.getCashOutFor(this, currentStartDate, currentEndDate, currency);
	}

	public BigDecimal getQuantity(Date currentStartDate, Date currentEndDate) {
		return this.portfolio.getQuantityFor(this, currentStartDate, currentEndDate);
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
	
	@Transient
	public BigDecimal getGainTotalPercent(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency);
		BigDecimal value = getValue(currentStartDate, currentEndDate, currency);
		if (cashin.compareTo(BigDecimal.ZERO) == 0) {
			LOGGER.warn("Cash in is zero for "+this);
			return BigDecimal.ZERO;
		} else {
			return value.add(cashout).subtract(cashin).divide(cashin, 10, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	
	@Transient
	public BigDecimal getGainTotal(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal value = getValue(currentStartDate, currentEndDate, currency);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency);
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency);
		return value.add(cashout).subtract(cashin);
	}

	@Transient
	public BigDecimal getGainUnrealPercent(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal basis = getBasis(currentStartDate, currentEndDate, currency);
		if (basis.compareTo(BigDecimal.ZERO) == 0) {
			LOGGER.warn("Basis is zero for "+this);
			return BigDecimal.ZERO;
		} else {
			return getValue(currentStartDate, currentEndDate, currency).subtract(basis).divide(basis, 10, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	
	@Transient
	public BigDecimal getGainUnreal(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal value = getValue(currentStartDate, currentEndDate, currency);
		BigDecimal basis = getBasis(currentStartDate, currentEndDate, currency);
		return value.subtract(basis);
	}
	
	@Transient
	public BigDecimal geGainRealPercent(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency);
		BigDecimal basis = getBasis(currentStartDate, currentEndDate, currency);
		if (cashin.compareTo(BigDecimal.ZERO) == 0) {
			LOGGER.warn("Cash in is zero for "+this);
			return BigDecimal.ZERO;
		} else {
			return ( (basis.add(cashout)) .subtract(cashin) ) .divide(cashin, 10, BigDecimal.ROUND_HALF_EVEN);
		}
	}

	@Transient
	public BigDecimal getGainReal(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal basis = getBasis(currentStartDate, currentEndDate, currency);
		BigDecimal cashout = getCashout(currentStartDate, currentEndDate, currency);
		BigDecimal cashin = getCashin(currentStartDate, currentEndDate, currency);
		return (basis.add(cashout)).subtract(cashin);
	}
	
	@Transient
	public BigDecimal getGainTotalWeightedPercent(Date currentStartDate, Date currentEndDate, Currency currency) {
		try {
			InOutWeighted weightedInOut = getWeightedInvested(currentStartDate, currentEndDate, currency);
			BigDecimal weightedInvestedStillIn = weightedInOut.getWeightedInvestedStillIn();
			return 	getValue(currentStartDate, currentEndDate, currency).subtract(weightedInvestedStillIn).divide(weightedInOut.getIn(), 10, BigDecimal.ROUND_HALF_EVEN); 
		} catch (ArithmeticException e) {
			return BigDecimal.ZERO;
		}
	}

	@Transient
	public BigDecimal getPriceUnitCost(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal quantity = this.getQuantity(currentStartDate, currentEndDate);
		if (quantity.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal cashout = this.getCashout(currentStartDate, currentEndDate, currency);
		BigDecimal cashin = this.getCashin(currentStartDate, currentEndDate, currency);
		return cashin.subtract(cashout).divide(quantity, 10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	@Transient
	public BigDecimal getPriceUnitCost(Date currentEndDate, Currency currency) {
		return getPriceUnitCost(null, currentEndDate, currency);
	}
	
	@Transient
	public BigDecimal getPriceAvgBuy(Date currentStartDate, Date currentEndDate, Currency currency) {
		return this.portfolio.getPriceAvgBuyFor(this, currentStartDate, currentEndDate, currency);
	}
	
	@Transient
	public BigDecimal getBasis(Date currentStartDate, Date currentEndDate, Currency currency) {
		return this.portfolio.getBasisFor(this, currentStartDate, currentEndDate, currency);
	}

	@Transient
	public BigDecimal getValue(Date currentStartDate, Date currentEndDate, Currency currency) {
		BigDecimal quantity = this.getQuantity(currentStartDate, currentEndDate);
		BigDecimal priceClose = getPriceClose(currentEndDate, currency);
		return quantity.multiply(priceClose).setScale(10, BigDecimal.ROUND_HALF_EVEN);
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
		return "PortfolioShare [portfolio = "+portfolio.getName()+", stock=" + stock.getFriendlyName() + ", monitorLevel=" + monitorLevel + ", transactionCurrency=" + transactionCurrency + ", externalAccount="+ externalAccount + "]";
	}
	
	
	public TransactionElement createTransactionElement(Transaction transaction) throws InvalidQuantityException {

		BigDecimal quantity = transaction.getQuantity();
		if (transaction.getModtype().equals(TransactionType.AOUT)) {
			if (quantity.compareTo(this.getQuantity(transaction.getDate())) > 0) {
				throw new InvalidQuantityException("Trying to sell more than available. The quantity can't be negative!", null);
			}
			quantity = quantity.multiply(new BigDecimal(-1));
		}

		return new TransactionElement(stock, (Portfolio) this.portfolio, this.externalAccount, transaction.getDate(), transaction.getTransactionSharePrice(), quantity, transactionCurrency);
		
	}
	
	public void addBuyAlerts(BigDecimal transcationPrice, Date currentDate) {
		
		if (this.getQuantity(currentDate).compareTo(BigDecimal.ZERO) > 0) {

			addBuyPriceAlerts(transcationPrice, currentDate);
			BigDecimal avgCostPerUnit = this.getPriceUnitCost(currentDate, transactionCurrency);
			addAboveTakeProfitAlert(avgCostPerUnit, currentDate);
			addWeightedZeroProfitAlertGuardSetter(avgCostPerUnit, currentDate);
			addChannelAlerts(transcationPrice);

		}
		
	}

	private void addBuyPriceAlerts(BigDecimal currentPrice, Date currentDate) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
		if (currentPrice.compareTo(this.getPriceUnitCost(currentDate, transactionCurrency)) <= 0) addBuyPriceAlertAbove(currentDate);
		if (currentPrice.compareTo(this.getPriceUnitCost(currentDate, transactionCurrency)) >= 0) addBuyPriceAlertBelow(currentDate);	
	}

	private void addBuyPriceAlertBelow(Date currentDate) {
		BigDecimal avgBuyPrice = this.getPriceUnitCost(currentDate, transactionCurrency);
		this.addAlertOnThreshold(ThresholdType.DOWN, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + readableNumber(avgBuyPrice) +")");
	}

	private void addBuyPriceAlertAbove(Date currentDate) {
		BigDecimal avgBuyPrice = this.getPriceUnitCost(currentDate, transactionCurrency);
		this.addAlertOnThreshold(ThresholdType.UP, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + readableNumber(avgBuyPrice) +")");
	}

	/**
	 * If price is up n% above avg buy price = > sell  
	 * Shift up to current price if calculated threshold below current price
	 * @param currentDate 
	 * @param transactionPrice
	 */
	private void addAboveTakeProfitAlert(BigDecimal calculationPrice, Date currentDate) {
	
		try {
			this.removeAlertOnThresholdFor(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT);	

			BigDecimal sellLimitToPriceRate = getEventsConfig().getSellLimitToPrice();
			
			BigDecimal cashin = this.getCashin(currentDate, transactionCurrency);
			BigDecimal augmentedCashin = BigDecimal.ONE.add(sellLimitToPriceRate).multiply(cashin).setScale(10, BigDecimal.ROUND_HALF_EVEN);
			BigDecimal cashout = this.getCashout(currentDate, transactionCurrency);
			BigDecimal aboveSellLimit = augmentedCashin.subtract(cashout).divide(getQuantity(currentDate), 10,BigDecimal.ROUND_HALF_EVEN);
			BigDecimal resultingPercentAboveAvgPrice = calculationPrice.divide(aboveSellLimit, 10, BigDecimal.ROUND_HALF_EVEN).subtract(BigDecimal.ONE.setScale(4));
			
			String aboveMessage = "("+readablePercentOf(sellLimitToPriceRate)+" gain. Price is "+ readablePercentOf(resultingPercentAboveAvgPrice) + " away from threshold)";
			
			addAboveTakeProfitAlert(aboveSellLimit,aboveMessage);
			
		} catch (RuntimeException e) {
			LOGGER.error("Failed to update Portfolio share :"+this,e);
			throw e;
		}
	}
	

	private BigDecimal getCashout(Date currentDate, Currency transactionCurrency) {
		return getCashout(null, currentDate, transactionCurrency);
	}

	private BigDecimal getCashin(Date currentDate, Currency transactionCurrency) {
		return getCashin(null, currentDate, transactionCurrency);
	}

	void addWeightedZeroProfitAlertGuardSetter(BigDecimal avgBuyPrice, Date currentDate) {
		
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	
	
		BigDecimal sellLimitGuardPriceRate;
		InOutWeighted weightedInOut = getWeightedInvested(null, currentDate, transactionCurrency);
		BigDecimal sellLimitGuardPrice;
		if (!weightedInOut.isEmpty()) {
			sellLimitGuardPrice = weightedInOut.getWeightedInvestedStillIn().divide(this.getQuantity(currentDate), 10, BigDecimal.ROUND_HALF_EVEN);
			sellLimitGuardPriceRate =  (avgBuyPrice.compareTo(BigDecimal.ZERO) > 0)?
												sellLimitGuardPrice.divide(avgBuyPrice, 10, BigDecimal.ROUND_HALF_EVEN).subtract(BigDecimal.ONE.setScale(4)):
												BigDecimal.ZERO;
		} else {
			sellLimitGuardPriceRate = getEventsConfig().getSellLimitGuardPrice();
			sellLimitGuardPrice = addPercentage(avgBuyPrice, sellLimitGuardPriceRate);
		}
		
		if (sellLimitGuardPrice.compareTo(this.getPriceClose(currentDate, transactionCurrency)) <= 0) {
			addWeightedZeroProfitAlertGuard(sellLimitGuardPrice);
		} else {
			String aboveMessage = "(Will set a guard when "+ readablePercentOf(sellLimitGuardPriceRate) + " above calculation price " + readableNumber(avgBuyPrice) +")";
			addWeightedZeroProfitAlertGuardSetter(sellLimitGuardPrice, aboveMessage);
		}

	}

	
	public void addWeightedZeroProfitAlertGuardSetter(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT, message);
	}
	
	private void addWeightedZeroProfitAlertGuard(BigDecimal profitGuard) {
		
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	
		String belowGuardMessage = "";
		addWeightedZeroProfitAlertGuard(profitGuard, belowGuardMessage);
	}


	private String readablePercentOf(BigDecimal aPercentage) {
		NumberFormat format = new DecimalFormat("#0.00 %");
		return format.format(aPercentage);
	}
	
	private String readableNumber(BigDecimal aNumber) {
		NumberFormat format = new DecimalFormat("#0.0000000000");
		return format.format(aNumber);
	}

	private BigDecimal addPercentage(BigDecimal calculationPrice, BigDecimal rateToCalculationPrice) {
		BigDecimal percentToBeAdded = calculationPrice.multiply(rateToCalculationPrice).setScale(10, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal aboveSellLimit = calculationPrice.add(percentToBeAdded);
		return aboveSellLimit;
	}

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
		
		String belowMessage = "(" +readablePercentOf(limitPriceBelowRate) + " below calculation price " + readableNumber(crossingPrice) +")";

		this.addAlertOnThreshold(ThresholdType.DOWN, belowSellLimit, AlertOnThresholdType.BELOW_PRICE_CHANNEL, belowMessage);
	}
	
	public void addAboveTakeProfitAlert(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT, message);
	}
	
	public void addWeightedZeroProfitAlertGuard(BigDecimal price, String message) {
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
				//this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
				//addBuyPriceAlertAbove();
				addBuyPriceAlerts(crossingPrice, EventSignalConfig.getNewDate());
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
				addWeightedZeroProfitAlertGuard(alert.getValue());
				break;
			case ABOVE_TAKE_PROFIT_LIMIT:
				//remove and reset a new take profit above alert
				addAboveTakeProfitAlert(crossingPrice, EventSignalConfig.getNewDate());
				break;
			case AVG_BUY_PRICE:
				//remove and reset alert buy price down
				//this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
				//addBuyPriceAlertBelow();
				addBuyPriceAlerts(crossingPrice, EventSignalConfig.getNewDate());
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
		message = "(" +readablePercentOf(limitPriceAboveRate) + " above calculation price " + readableNumber(crossingPrice) +")";

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

	public int compareTo(PortfolioShare o) {
		int stock = this.getStock().compareTo(o.getStock());
		if (stock == 0) return this.getPortfolio().getName().compareTo(o.getPortfolio().getName());
		return stock;
	}
	
	@Transient
	public InOutWeighted getWeightedInvested(Date currentStartDate, Date currentEndDate, Currency currency) {
		return portfolio.getWeightedInvestedFor(this, currentEndDate, currency);
	}
	
	InOutWeighted calculateInflationAndExpectationWeightedInvestedCash(Date currentEndDate, SortedSet<TransactionElement> transactionsForStock, Currency currency)  throws InvalidAlgorithmParameterException {
		
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
		
		BigDecimal inPrevPrice = currencyConverter.convert(inPreviousTransaction.getCurrency(), currency, inPreviousTransaction.getPrice(), inPreviousTransaction.getDate());
		weightedCashin = inPreviousTransaction.getQuantity().multiply(inPrevPrice).setScale(10, BigDecimal.ROUND_HALF_EVEN);
		
		while (!transactionsForStockQ.isEmpty()) {
			
			TransactionElement transaction = transactionsForStockQ.poll();
			BigDecimal price = currencyConverter.convert(transaction.getCurrency(), currency, transaction.getPrice(), transaction.getDate());
			
			//in
			if (transaction.transactionType().equals(TransactionType.AIN)) {
				
				//Weighting previous invest
				BigDecimal r = getYearlyRateForDates(inPreviousTransaction.getDate(), transaction.getDate(), false);
				BigDecimal worseRate =  BigDecimal.ONE.add(r);
				weightedCashin = weightedCashin.multiply(worseRate).setScale(10, BigDecimal.ROUND_HALF_EVEN);

				//Adding transaction
				BigDecimal transactionValue = transaction.getQuantity().multiply(price).setScale(10, BigDecimal.ROUND_HALF_EVEN);
				weightedCashin = weightedCashin.add(transactionValue);
				
				inPreviousTransaction = transaction;
				
				LOGGER.debug("In Weighted value : "+weightedCashin);
				LOGGER.debug("In Added quantity : "+transaction.getQuantity());
			} 
			
			//out
			if (transaction.transactionType().equals(TransactionType.AOUT)) {
				
				//Weighting previous invest
				if (outPreviousTransaction != null) {
					
					BigDecimal r = getYearlyRateForDates(outPreviousTransaction.getDate(), transaction.getDate(), false);
					BigDecimal worseRate =  BigDecimal.ONE.add(r);
					weightedCashout = weightedCashout.multiply(worseRate).setScale(10, BigDecimal.ROUND_HALF_EVEN);

					//Adding transaction
					BigDecimal transactionValue = transaction.getQuantity().abs().multiply(price).setScale(10, BigDecimal.ROUND_HALF_EVEN);
					weightedCashout = weightedCashout.add(transactionValue);
					
					outPreviousTransaction = transaction;
					
					LOGGER.debug("Out Weighted value : "+weightedCashout);
					LOGGER.debug("Out Added quantity : "+transaction.getQuantity().abs());
					
				} else {
					
					weightedCashout = transaction.getQuantity().abs().multiply(price).setScale(10, BigDecimal.ROUND_HALF_EVEN);
					outPreviousTransaction = transaction;
					
				}
	
			} 	
		}
		
		//To Current date
		//in
		BigDecimal r = getYearlyRateForDates(inPreviousTransaction.getDate(), currentEndDate, false);
		BigDecimal inLastWorseRate = BigDecimal.ONE.add(r);
		//Double compoundRate = compoundRate(inPreviousTransaction.getDate(), currentEndDate, r.doubleValue());
		weightedCashin = weightedCashin.multiply(inLastWorseRate).setScale(10, BigDecimal.ROUND_HALF_EVEN);
		//out
		if (outPreviousTransaction != null) {
			BigDecimal rOut = getYearlyRateForDates(outPreviousTransaction.getDate(), currentEndDate, false);
			BigDecimal outLastWorseRate = BigDecimal.ONE.add(rOut);
			//Double compoundRateOut = compoundRate(inPreviousTransaction.getDate(), currentEndDate, rOut.doubleValue());
			weightedCashout = weightedCashout.multiply(outLastWorseRate).setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	
		LOGGER.debug("Weighted invested value for "+this.stock.getIsin()+" is "+weightedCashin);

		return new InOutWeighted(weightedCashin, weightedCashout, currentEndDate);
	}
	
	private BigDecimal getYearlyRateForDates(Date firstDate, Date secondDate, Boolean addExpectationRate)  {
		
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
		Double nbyears = new Double(diffMilli) / 1000 / 60 / 60 / 24 / 365;
		Double compoundRate = Math.pow((1 + yearlyRate), nbyears); // fv = pv * (1 + r) ^ np
		return compoundRate;
	}
	
	private BigDecimal getInflationRateBetweenDates(Date fisrtDate, Date secondDate) {
		
		BigDecimal inflationRate = BigDecimal.ZERO;
		try {
			BigDecimal inflatAtFirst;
			BigDecimal inflatAtSecond;
			Stock inflationStock = ProvidersInflation.inflationStock();
			Quotations inflationQuotations = QuotationsFactories.getFactory().getQuotationsInstance(inflationStock, fisrtDate, secondDate, true, Currency.USD, 1, ValidityFilter.CLOSE);
			inflatAtFirst = inflationQuotations.getClosestCloseForDate(fisrtDate);
			inflatAtSecond = inflationQuotations.getClosestCloseForDate(secondDate);
			inflationRate = inflatAtSecond.subtract(inflatAtFirst).divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_EVEN);
		} catch (Exception e) {
			LOGGER.warn(e,e); 
		}
		
		LOGGER.debug("Inflation rate between "+fisrtDate+" and "+secondDate+" is : "+inflationRate);
		return inflationRate;
		
	}

	public String getExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(String externalAccount) {
		this.externalAccount = externalAccount;
	}

	/**
	 * Gets the sell price for zero loss if cash in and out are weighted with inflation ( = inflation weighted avg buy price) : (Ow-Iw)/quantity
	 */
	@Transient
	public BigDecimal getPriceZeroGainWeighted(Date currentStartDate, Date currentEndDate, Currency currency) {
		
		InOutWeighted weightedInOut = getWeightedInvested(currentStartDate, currentEndDate, currency);
		BigDecimal weightedInvested = weightedInOut.getWeightedInvestedStillIn();
		BigDecimal quantity = this.getQuantity(currentStartDate, currentEndDate);
		if (quantity.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		BigDecimal sellLimitGuardPrice = weightedInvested.divide(quantity, 10, BigDecimal.ROUND_HALF_EVEN);
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

	public BigDecimal getPriceAvgBuy(Date currentDate, Currency transactionCurrency) {
		return getPriceAvgBuy(null, currentDate, transactionCurrency);
	}

	public BigDecimal getQuantity(Date currentDate) {
		return getQuantity(null, currentDate);
	}

}
