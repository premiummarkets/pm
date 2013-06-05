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



import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Transient;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.Transaction.TransactionType;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class Portfolio.
 * 
 * @author Guillaume Thoreton
 * Portfolio is a generic portfolio
 * TODO add a portfolio currency and convert portfolio shares value before adding to total amount
 * 
 */

@Entity
public class Portfolio extends AbstractSharesList {
	
	private PonderationRule buyPonderationRule;
	private PonderationRule sellPonderationRule;
	private Currency portfolioCurrency;

	
	protected Portfolio() {
		super();
	}
	

	public Portfolio(Portfolio portfolio) {
		super(portfolio);
		this.buyPonderationRule = portfolio.buyPonderationRule;
		this.sellPonderationRule = portfolio.sellPonderationRule;
		this.portfolioCurrency = portfolio.portfolioCurrency;
	}

	public Portfolio(String name, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, Currency portfolioCurrency) {
		super(name);
		
		this.buyPonderationRule =  buyPonderationRule;
		this.sellPonderationRule = sellPonderationRule;
		this.portfolioCurrency = portfolioCurrency;
	}


	/**
	 * @param portfolioShare TODO
	 * @param sourcePortfolio
	 * @return
	 */
	public void resetManualAlerts(PortfolioShare portfolioShare, AbstractSharesList sourcePortfolio) {
		
		PortfolioShare oldPortfolioShare;
		if (sourcePortfolio != null && (oldPortfolioShare = sourcePortfolio.getShareForSymbolAndIsin(portfolioShare.getSymbol(), portfolioShare.getIsin())) != null) {
			for (AlertOnThreshold alert: oldPortfolioShare.getAlertsOnThresholdFor(AlertOnThresholdType.MANUAL)) {
				portfolioShare.addAlertOnThreshold(alert.getThresholdType(), alert.getValue(), alert.getAlertType(), alert.getOptionalMessage());
			}
		}
		
	}
	
	
	public PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date currentDate, BigDecimal buyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException {
		
		if (quantity.compareTo(BigDecimal.ZERO) == 0 || buyPrice.compareTo(BigDecimal.ZERO) == 0) {
			throw new InvalidQuantityException("Invalid Quantity : "+quantity+"; or buy price : "+buyPrice+" for "+stock, new Exception());
		}
		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, currentDate, mLevel, trCurrency);
		shareTransaction(portfolioShare, quantity, currentDate, buyPrice, trType);
		portfolioShare.addBuyAlerts(buyPrice, currentDate);
		
		return portfolioShare;

	}
	

	private void shareTransaction(PortfolioShare recipientPortfolioShare, BigDecimal quantity, Date buyDate, BigDecimal lastQuotation, TransactionType movement) throws InvalidQuantityException {
		Transaction transaction = new Transaction(recipientPortfolioShare.getCashin(), recipientPortfolioShare.getCashout(), quantity, lastQuotation, movement, buyDate);
		if (transaction.amount().compareTo(BigDecimal.ZERO) == 0 && movement.equals(TransactionType.AIN)) throw new InvalidQuantityException("Amounts too small are not supported. Amount must be >= 0.01 ", new Throwable());
		recipientPortfolioShare.applyTransaction(transaction, true);
		recipientPortfolioShare.setBuyDate(buyDate);
	}


	public PortfolioShare addOrUpdateShareForQuantity(Stock stock, BigDecimal quantity, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException  {
		
		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate,true,transactionCurrency);
			BigDecimal valueAtDate = quotations.getCloseForDate(currentDate);
			
			return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
		} catch (NoQuotationsException e) {
			throw new InvalidAlgorithmParameterException(e);
		}
	}
	
	public PortfolioShare addOrUpdateShareForAmount(Stock stock, BigDecimal unitAmount, Date currentDate, MonitorLevel monitorLevel, Currency transactionCurrency) throws InvalidQuantityException, InvalidAlgorithmParameterException {
		
		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, currentDate, true, transactionCurrency);
			BigDecimal valueAtDate = quotations.getCloseForDate(currentDate);
			BigDecimal quantity = unitAmount.divide(valueAtDate, 4, BigDecimal.ROUND_DOWN);
			
			return addOrUpdateShare(stock, quantity, currentDate, valueAtDate, monitorLevel, transactionCurrency, TransactionType.AIN);
		} catch (NoQuotationsException e) {
			throw new InvalidAlgorithmParameterException(e);
		}
	}


	public PortfolioShare addOrUpdateShareWithNewAmounts(
			Stock stock, String account, 
			BigDecimal quantity, Date currentDate, BigDecimal cashin, BigDecimal cashout, 
			MonitorLevel monitorLevel, Currency transactionCurrency) 
			throws InvalidAlgorithmParameterException {
		
		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, currentDate, monitorLevel, transactionCurrency);
		
		//As per the new parsing, if the stock as already been parsed we ignore it
		if (portfolioShare.getQuantity().compareTo(BigDecimal.ZERO) > 0) {
			return portfolioShare;
		}
		
		addNewAmounts(portfolioShare, quantity, cashin, cashout, currentDate);
		portfolioShare.setAccount(account); 
		
		//TODO compare to avg buy price ?
		portfolioShare.addBuyAlerts(portfolioShare.getCloseQuotationFor(currentDate), currentDate);
		
		return portfolioShare;
	}
	
	private void addNewAmounts(PortfolioShare recipientPortfolioShare, BigDecimal quantity, BigDecimal cashIn , BigDecimal cashOut, Date currentDate) {
		recipientPortfolioShare.newAmounts(quantity,cashIn,cashOut,currentDate);
		recipientPortfolioShare.setBuyDate(currentDate);
	}
	

	public void removeOrUpdateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException {
		
		if (this.getShareForSymbolAndIsin(portfolioShare.getSymbol(), portfolioShare.getIsin()) == null) {
			throw new InvalidQuantityException("The share "+portfolioShare+" is not in the portfolio "+this, new Throwable());
		}
		
		shareTransaction(portfolioShare, quantity, currentDate, trPrice, trType);
		
		if (portfolioShare.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
			this.removeShareFromList(portfolioShare);
		} else {
			portfolioShare.addBuyAlerts(trPrice, currentDate);
		}
		
	}


	public void removeOrUpdateShareForQuantity(PortfolioShare portfolioShare, BigDecimal quantity, Date date) throws InvalidAlgorithmParameterException, InvalidQuantityException {
		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(portfolioShare.getStock(), date, true, portfolioShare.getTransactionCurrency());
			BigDecimal lastQuotation = quotations.getCloseForDate(date);
			
			removeOrUpdateShare(portfolioShare, quantity, date, lastQuotation, TransactionType.AOUT);
		} catch (NoQuotationsException e) {
			throw new InvalidAlgorithmParameterException(e);
		}
	}

	@SuppressWarnings("unused")
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
			PonderationRule defaultBuyPonderationRule = eventConfig.getBuyPonderationRule(sellEvtThresh,buyEvtThresh);
			LOGGER.warn("No buy ponderation rule for "+this.name+" the config rule will be used "+defaultBuyPonderationRule);
			return defaultBuyPonderationRule;
		}
		return buyPonderationRule;
	}

	public void setBuyPonderationRule(PonderationRule buyPonderationRule) {
		this.buyPonderationRule = buyPonderationRule;
	}

	@SuppressWarnings("unused")
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
			PonderationRule defaultSellPonderationRule = eventConfig.getSellPonderationRule(sellEvtThresh,buyEvtThresh);
			LOGGER.warn("No sell ponderation rule for "+this.name+" the config rule will be used "+defaultSellPonderationRule);
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
	
	protected PortfolioShare getOrCreatePortfolioShare(Stock stock, Date currentDate, MonitorLevel mLevel, Currency transactionCurrency) {
		if (this.portfolioCurrency != null && !this.portfolioCurrency.equals(transactionCurrency)) {
			throw new RuntimeException("Currency is inconsistent : portfolio currency is " + this.portfolioCurrency + " and " + stock.getSymbol() + " is " + transactionCurrency);
		}
		//return super.getOrCreatePortfolioShare(stock, currentDate, mLevel, transactionCurrency);
		PortfolioShare portfolioShare = getShareForSymbolAndIsin(stock.getSymbol(), stock.getIsin());
		if (portfolioShare == null) {
			portfolioShare = new PortfolioShare(this, stock, currentDate, mLevel, transactionCurrency);
			//listShares.put(stock, portfolioShare);
			addShareToList(portfolioShare);
		}
		return portfolioShare;
	}


	@Override
	public void addAmountToTotalAmountIn(BigDecimal newCashIn, Currency currency, Date date) {
		if (this.portfolioCurrency == null) {  //Portfolio potentially hosting multiple currency : we convert the total to EUR
			newCashIn = PortfolioMgr.getInstance().getCurrencyConverter().convert(currency, Currency.EUR, newCashIn, date);
		}
		super.addAmountToTotalAmountIn(newCashIn, currency, date);
	}


	@Override
	public BigDecimal addAmountToTotalAmountOut(BigDecimal newCashOut, Currency currency, Date date) {
		if (this.portfolioCurrency == null) {  //Portfolio potentially hosting multiple transaction : we convert the total to EUR
			newCashOut = PortfolioMgr.getInstance().getCurrencyConverter().convert(currency, Currency.EUR, newCashOut, date);
		} 
		return super.addAmountToTotalAmountOut(newCashOut, currency, date);
	}	
	
	public static String ACTUALYIELDBISHINT =  "(V-(I-O))/(I-O)";
	@Transient
	public BigDecimal getActualYieldBis(Date date) {
		if (BigDecimal.ZERO.compareTo(this.getTotalInAmountEver().subtract(this.getTotalOutAmountEver())) == 0){
			return BigDecimal.ZERO;
		}
		
		return this.getValueForDate(date)
					.divide(this.getTotalInAmountEver().subtract(this.getTotalOutAmountEver()),4,BigDecimal.ROUND_DOWN)
					.subtract(BigDecimal.ONE)
					.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
	}
	
	@Transient
	public BigDecimal getValueForDate(Date date) {

		Currency currency = inferPortfolioCurrency();

		BigDecimal valueForDate = BigDecimal.ZERO.setScale(2);
		for (PortfolioShare portfolioShare: this.getListShares().values()) {
			BigDecimal psValueForDate = portfolioShare.getValueForDate(date);
			psValueForDate = PortfolioMgr.getInstance().getCurrencyConverter().convert(portfolioShare.getTransactionCurrency(), currency, psValueForDate, date);
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
	public BigDecimal getGainAmountUnrealizedForDate(Date currentDate) { // = (Value + Out) - In 
		if (this.getTotalInAmountEver() == null || (this.getTotalInAmountEver().compareTo(BigDecimal.ZERO) == 0) )  { 
			if (this.getListShares().size() > 0) LOGGER.warn("Total amount is zero for portfolio "+this.name,new Throwable());
			return BigDecimal.ZERO ;
		}
		BigDecimal stillIn = getNotNullTotalInAmountEver().subtract(this.getNotNullTotalOutAmountEver());
		if (stillIn.compareTo(BigDecimal.ZERO) < 0) stillIn = BigDecimal.ZERO;
		return this.getValueForDate(currentDate).subtract(stillIn).setScale(2,BigDecimal.ROUND_DOWN);
	}

	public static String PROFITUNREALIZEDHINT = "((V+O)-I)/I";
	@Transient
	public BigDecimal getProfitUnrealizedForDate(Date date) {
		if (this.getTotalInAmountEver().compareTo(BigDecimal.ZERO) == 0) {
			if (this.getListShares().size() > 0) LOGGER.warn("Total amount in is zero for portfolio "+this.name,new Throwable());
			return BigDecimal.ZERO;
		}
		BigDecimal gainAmountForDate = this.getGainAmountUnrealizedForDate(date);
		return gainAmountForDate.divide(this.getTotalInAmountEver(),4,BigDecimal.ROUND_DOWN);
	}
	
	@Transient
	public BigDecimal getGainHistorical() {
		return this.getNotNullTotalOutAmountEver();
	}

	@Transient
	public BigDecimal getGain(Date currentDate) {
		BigDecimal gainAmount = BigDecimal.ZERO;
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			gainAmount = gainAmount.add(portfolioShare.calculateGain(currentDate));
		}
		return gainAmount;
	}

	public void rawRemoveShare(PortfolioShare portfolioShare) {
		this.removeAmountFromTotalAmount(portfolioShare);
		removeShareFromList(portfolioShare);
		
	}

}
