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
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;

/**
 * @author Guillaume Thoreton
 * AutoPortfolio based on Screening events and used for virtual portfolios buy daily buy and sell.
 * TODO These portfolios are multi currencies. The calculation is made using the stock currency.
 * TODO The total amounts will need conversion in a target currency.
 *
 */
@Entity
@DiscriminatorValue("AutoPortfolio")
public class AutoPortfolio extends Portfolio implements AutoPortfolioWays {

	protected static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolio.class);

	public static final String NAME  = "AutoPortfolio";

	private AutoPortfolioDelegate autoPortfolioDelegate;

	private String[] additionalPortfolioEventListNames = new String[0];

	private EventSignalConfig eventSignalConfig;

	@SuppressWarnings("unused")
	private AutoPortfolio() {
		//Hibernate
	}

	public AutoPortfolio(AutoPortfolio portfolio) {
		super(portfolio);
		additionalPortfolioEventListNames = portfolio.additionalPortfolioEventListNames;
		eventSignalConfig = portfolio.eventSignalConfig;
	}

	public AutoPortfolio(String name, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, Currency currency, EventSignalConfig eventSignalConfig) {
		super(name, buyPonderationRule, sellPonderationRule, currency);
		this.eventSignalConfig = eventSignalConfig;
	}

	public synchronized BigDecimal withdrawCash(Date currentDate, Currency transactionCurrency) throws NoCashAvailableException {

		BigDecimal ret = BigDecimal.ZERO;

		BigDecimal availableCash = getAvailableCash(currentDate);
		if (availableCash.compareTo(AutoPortfolioDelegate.DEFAULT_TRANSACTION_AMOUNT) >= 0) {
			ret = AutoPortfolioDelegate.DEFAULT_TRANSACTION_AMOUNT;
		}
		else {
			throw new NoCashAvailableException("Not enough cash left : " + availableCash);
		}

		return PortfolioMgr.getInstance().getCurrencyConverter().convert(Currency.EUR, transactionCurrency, ret, currentDate);
	}

	@Transient
	public BigDecimal getAvailableCash(Date currentDate) {
		BigDecimal inMinusOut = getTotalInAmountEver(null, currentDate).subtract(getTotalOutAmountEver(null, currentDate)).setScale(2, RoundingMode.HALF_EVEN);
		return AutoPortfolioDelegate.DEFAULT_INITIAL_CASH.subtract(inMinusOut);
	}

	public void notifyObservers(ObserverMsg string) {
		super.notifyObservers(string);
	}

	@Override
	public void setChanged() {
		super.setChanged();
	}

	public TransactionHistory calculate(List<SymbolEvents> listEvents, Date currentDate, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, String... eventListName) {

		ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, this.eventSignalConfig);
		return getAutoPortfolioDelegate().calculate(listEvents, currentDate, buyPonderationRule, sellPonderationRule);
	}

	@Override
	public void resetManualAlerts(PortfolioShare portfolioShare, AbstractSharesList sourcePortfolio) {
		//nothing as there should be no manual alerts in an Auto Portfolio
	}

	public void exportAutoportfolioContent(Date date) {
		//FIXME not in use and won't work
		//IndicatorTuning.exportPortfolioContentToTotalFile(this,date);
	}

	@Transient
	public TransactionHistory getTransactionHistory() {
		return getAutoPortfolioDelegate().getTransactionHistory();
	}

	public void setAdditionalPortfolioEventListNames(String[] additionalEventListNames) {
		this.additionalPortfolioEventListNames = additionalEventListNames;
	}

	public void setEventSignalConfig(EventSignalConfig eventSignalConfig) {
		this.eventSignalConfig = eventSignalConfig;
	}

	public void log(TransactionRecord transactionRecord) {
		this.getAutoPortfolioDelegate().log(transactionRecord);
	}

	@Transient
	private AutoPortfolioDelegate getAutoPortfolioDelegate() {
		if (autoPortfolioDelegate == null) {
			autoPortfolioDelegate = new AutoPortfolioDelegate(this, true);
		}
		return autoPortfolioDelegate;
	}

}
