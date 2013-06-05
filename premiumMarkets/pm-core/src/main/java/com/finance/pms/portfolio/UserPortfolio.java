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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class UserPortfolio.
 * 
 * @author Guillaume Thoreton
 * AutoPortfolio based on real portfolio content used for virtual portfolios buy daily buy and sell
 * These portfolios host one target currency and calculation needs to be done using that currency
 * No need to convert the total amounts as all calculation should use the target currency
 * 
 */
@Entity
@DiscriminatorValue("default")
public class UserPortfolio extends Portfolio implements AutoPortfolioWays {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(UserPortfolio.class);
	
	private UserPortfolioDelegate userPortfolioDelegate;
	

	@SuppressWarnings("unused")
	private UserPortfolio() {
		//hib
		super();
		userPortfolioDelegate = new UserPortfolioDelegate(this);
	}

	/**
	 * Instantiates a new portfolio.
	 * 
	 * @param name the name
	 * 
	 * @author Guillaume Thoreton
	 * @param portfolioCurrency 
	 */
	public UserPortfolio(String name, Currency portfolioCurrency) {
		super(name, null, null, portfolioCurrency);
		userPortfolioDelegate = new UserPortfolioDelegate(this);
	}
	
	
	public UserPortfolio(UserPortfolio portfolio) {
		super(portfolio);
		userPortfolioDelegate = new UserPortfolioDelegate(this);
	}


	
	public TransactionHistory calculate(Date currentDate, String... eventListName) {
		
//		//Test
//		try {
//			if (currentDate.after(new SimpleDateFormat("yyyyMMdd").parse("20120930")) && this.name.equals("APEG") ) {
//				System.out.println("I am here");
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//Test
		
		List<SymbolEvents> listEvents = loadEventsForCalculation(currentDate, eventListName);
		return userPortfolioDelegate.calculate(listEvents, currentDate, getNonNullBuyPonderationRule(), getNonNullSellPonderationRule());
		
	}

	private List<SymbolEvents> loadEventsForCalculation(Date currentDate, String... eventListName) {
		
		//Date dateStart = IndicatorCalculationServiceMain.getDateMoinsNJours(currentDate,((EventSignalConfig) ConfigThreadLocal.get("eventSignal")).getBackwardDaySpan());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		Calendar nDaysAgoAtCurrentDate = QuotationsFactories.getFactory().incrementDate(calendar, -((EventSignalConfig) ConfigThreadLocal.get("eventSignal")).getBackwardDaySpan());
		Date dateStart = nDaysAgoAtCurrentDate.getTime();
		
		String[] fullEventListNames = Arrays.copyOf(eventListName, eventListName.length+1);
		fullEventListNames[eventListName.length] = this.getName();
		return EventsResources.getInstance().crudReadEvents(dateStart, currentDate, true, null, fullEventListNames);
	}

	
	@Transient
	public TransactionHistory getTransactionHistory() {
		return userPortfolioDelegate.getTransactionHistory();
	}

	
	public void notifyObservers(ObserverMsg string) {
		super.notifyObservers(string);
	}

	
	public BigDecimal withdrawCash(Date currentDate, Currency currency) throws NoCashAvailableException {
		throw new NotImplementedException();
	}
	
	
	@Transient
	public BigDecimal getAvailableCash() {
		throw new NotImplementedException();
	}
	
	
	public void exportAutoportfolioContent(Date date) {
		throw new NotImplementedException();
	}
	
	
	public void setChanged() {
		super.setChanged();
	}
	
	
	public void log(TransactionRecord transactionRecord) {
		this.userPortfolioDelegate.log(transactionRecord);
	}

}
