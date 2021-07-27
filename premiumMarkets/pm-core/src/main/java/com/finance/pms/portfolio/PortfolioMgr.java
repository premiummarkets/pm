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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.datasources.web.currency.CurrencyConverterImpl;
import com.finance.pms.events.AlertEventKey;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.SilentPonderationRule;
import com.finance.pms.threads.ConfigThreadLocal;


/**
 * The Class PortfolioMgr.
 * 
 * @author Guillaume Thoreton
 */
public class PortfolioMgr implements ApplicationContextAware {

	protected static MyLogger LOGGER = MyLogger.getLogger(PortfolioMgr.class);

	ApplicationContext applicationContext;
	private static PortfolioMgr singleton;

	private List<Portfolio> portfolios;
	private List<Portfolio> oldPortfolios;

	private PortfolioDAO portfolioDAO;
	private CurrencyConverterImpl currencyConverter;


	public PortfolioMgr(PortfolioDAO portfolioDAO, CurrencyConverterImpl currencyConverter) {

		this.portfolioDAO = portfolioDAO;
		this.portfolios = portfolioDAO.loadVisiblePortfolios();
		this.currencyConverter=currencyConverter;
		resetOldPortfolioList();

		singleton = this;
	}

	//For Test ...
	public static void setInit(PortfolioMgr singleton) {
		PortfolioMgr.singleton = singleton;
		PortfolioMgr.singleton.oldPortfolios  = new ArrayList<Portfolio>();
		PortfolioMgr.singleton.portfolios   = new ArrayList<Portfolio>();
	}

	private void resetOldPortfolioList() {

		this.oldPortfolios = new ArrayList<Portfolio>();
		for (Portfolio portfolio: portfolios) {
			copyPortfolioToList(portfolio, oldPortfolios);
		}
	}

	public AutoPortfolio getOrCreateAutoPortfolio(String analyseName, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, Currency currency) {

		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);

		AutoPortfolio autoPortfolio =  new AutoPortfolio(analyseName, buyPonderationRule, sellPonderationRule, currency, eventSignalConfig);
		int index = this.portfolios.indexOf(autoPortfolio);
		if (index == -1) {	

			this.portfolios.add(autoPortfolio);
			portfolioDAO.saveOrUpdatePortfolio(autoPortfolio);
			return autoPortfolio;

		} else {

			AutoPortfolio existingAutoPortfolio = (AutoPortfolio) this.portfolios.get(index);
			existingAutoPortfolio.setSellPonderationRule(sellPonderationRule);
			existingAutoPortfolio.setBuyPonderationRule(buyPonderationRule);
			existingAutoPortfolio.setEventSignalConfig(eventSignalConfig);
			return existingAutoPortfolio;

		}
	}

	
	public AutoPortfolio createOverwriteAutoPortfolio(String analyseName, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, Currency currency) {

		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);

		AutoPortfolio autoPortfolio =  new AutoPortfolio(analyseName, buyPonderationRule, sellPonderationRule, currency, eventSignalConfig);
		int index = this.portfolios.indexOf(autoPortfolio);
		if (index != -1) {
			removePortfolio(this.portfolios.get(index));
		}

		this.portfolios.add(autoPortfolio);
		portfolioDAO.saveOrUpdatePortfolio(autoPortfolio);
		return autoPortfolio;
	}

	public AbstractSharesList getPortfolio(String portfolioName) {

		int index = this.portfolios.indexOf(new Portfolio(portfolioName,new SilentPonderationRule(),new SilentPonderationRule(),null));

		if (index == -1) {		
			throw new IllegalArgumentException("Portfolio "+portfolioName+" doesn't exist");
		} else {
			return (AbstractSharesList) this.portfolios.get(index);
		}

	}


	public static PortfolioMgr getInstance() {
		if (singleton != null) {
			return singleton;
		} else {
			throw new NotImplementedException("Portfolio manager should have bean injected by constructor");
		}
	}

	public  void addPortfolio(Portfolio portfolio) throws InvalidAlgorithmParameterException {
		if (portfolios.contains(portfolio)) {
			throw new InvalidAlgorithmParameterException("Portfolio "+portfolio.getName()+" already exists. Please delete");
		}
		this.portfolios.add(portfolio);
	}


	public void removePortfolio(AbstractSharesList portfolioToRm) {
		this.portfolios.remove(portfolioToRm);
		this.portfolioDAO.delete(portfolioToRm);
	}

	public void hibStorePortfolio() {

		for (AbstractSharesList portfolio: this.portfolios) {
			LOGGER.debug("saving : "+portfolio.getName());
			try {			
				this.portfolioDAO.saveOrUpdatePortfolio(portfolio);
			} catch (Exception e) {
				LOGGER.error("Portfolio : "+portfolio,e);
			}
		}

		resetOldPortfolioList();
	}

	public List<Portfolio> getVisiblePortfolios() {

		List<Portfolio> visiblePortfolios = new ArrayList<Portfolio>();
		for (Portfolio portfolio : portfolios) {
			if (!(portfolio instanceof Hidden)) {
				visiblePortfolios.add(portfolio);
			}
		}

		return visiblePortfolios;
	}


	public void setPortfolioDAO(PortfolioDAO portfolioDAO) {
		this.portfolioDAO = portfolioDAO;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	private PortfolioShare getPortfolioShareForSymbolInPortfolio(String stockSymbol,String portfolioName) {

		for (AbstractSharesList portfolio : this.portfolios) {
			if (portfolio.getName().equals(portfolioName)) {
				for(PortfolioShare portfolioShare : portfolio.getListShares().values()) {
					LOGGER.debug("Nb of shares for "+portfolio.getName()+" not empty : "+portfolioShare.getStock());
					if (portfolioShare.getStock().getSymbol().equals(stockSymbol)) {
						LOGGER.debug("Share matching : "+stockSymbol+" with "+portfolioShare.getStock().getName());
						return portfolioShare;
					}
				}
			}
		}
		return null;

	}

	public List<PortfolioShare> getRecordedPortfolioShareForStockInAllPortfolios(Stock stock) {

		List<PortfolioShare> portfolioShareList = new ArrayList<PortfolioShare>();
		for (AbstractSharesList portfolio : this.portfolios) {

			for(PortfolioShare portfolioShare : portfolio.getListShares().values()) {
				if (portfolioShare.getStock().equals(stock)) {
					portfolioShareList.add(portfolioShare);
				}					
			}
		}
		return portfolioShareList;
	}

	public List<PortfolioShare> getRecordedPortfolioShareForStockInPortfolio(Stock stock, String portfolioName) {
		List<PortfolioShare> portfolioShareList = new ArrayList<PortfolioShare>();
		PortfolioShare portfolioShare = this.getPortfolioShareForSymbolInPortfolio(stock.getSymbol(), portfolioName);
		if (null != portfolioShare) portfolioShareList.add(portfolioShare);
		return portfolioShareList;
	}

	public List<Portfolio> cancelModifications() {
		this.portfolios = new ArrayList<Portfolio>();	
		for (Portfolio portfolio: oldPortfolios) {
			copyPortfolioToList(portfolio, portfolios);
		}
		return this.portfolios;
	}


	private void copyPortfolioToList(Portfolio portfolio, List<Portfolio> list) throws InstantiationError {
		if (portfolio instanceof AutoPortfolio) {
			list.add(new AutoPortfolio((AutoPortfolio)portfolio));
		} 
		else if (portfolio instanceof UserPortfolio) {
			list.add(new UserPortfolio((UserPortfolio)portfolio));
		}
		else {
			throw new InstantiationError();
		}
	}


	public CurrencyConverter getCurrencyConverter() {
		return currencyConverter;
	}

	public PortfolioDAO getPortfolioDAO() {
		return this.portfolioDAO;	
	}

	public List<AutoPortfolio> getAutoPortfolios() {
		List<AutoPortfolio> retVal = new ArrayList<AutoPortfolio>();
		for (Portfolio portfolio : this.portfolios) {
			if (portfolio instanceof AutoPortfolio) {
				retVal.add((AutoPortfolio)portfolio);
			}
		}
		return retVal;

	}

	public List<String> getUserPortfolioNames() {
		return portfolioDAO.loadUserPortfolioNames();
	}

	public List<UserPortfolio> getUserPortfolios() {
		List<UserPortfolio> userPortfolios = new ArrayList<UserPortfolio>();
		for (Portfolio portfolio : portfolios) {
			if (portfolio instanceof UserPortfolio) {
				userPortfolios.add((UserPortfolio) portfolio);
			}
		}
		return userPortfolios;
	}

	public Collection<Stock> getUserMonitoredStocks() {
		Set<Stock> stocks = new TreeSet<Stock>();
		for (Portfolio portfolio : portfolios) {
			if (portfolio instanceof UserPortfolio) {
				for (PortfolioShare ps : portfolio.getListShares().values()) {
					if (!ps.getMonitorLevel().equals(MonitorLevel.NONE)) stocks.add(ps.getStock());
				}
			}
		}
		return stocks;
	}

	public Collection<Stock> getPortfolioStocks() {
		Set<Stock> stocks = new TreeSet<Stock>();
		for (Portfolio portfolio : portfolios) {
			if (!(portfolio instanceof Hidden)) {
				for (PortfolioShare ps : portfolio.getListShares().values()) {
					stocks.add(ps.getStock());
				}
			}
		}
		return stocks;
	}

	public Boolean isMonitoredFor(SymbolEvents symbolEvents, String eventListName) {

		SortedMap<EventKey, EventValue> sortedDataResultMap = symbolEvents.getSortedDataResultMap();
		if (sortedDataResultMap == null || sortedDataResultMap.isEmpty()) return false;

		//Alerts on Threshold. eventListName == portfolioName.
		Boolean isMonitoredForAlertsOnThreshold = sortedDataResultMap.keySet().stream()
				.filter(k -> k instanceof AlertEventKey)
				.map(k -> isMonitoredForAlerts(symbolEvents.getStock(), eventListName, (AlertEventKey) k))
				.reduce(false, (r,e)-> r || e);
		LOGGER.info("SymbolEvents is "+symbolEvents+" is monitored for alerts: "+isMonitoredForAlertsOnThreshold);
		if (isMonitoredForAlertsOnThreshold) return true;

		//Alerts on Events. The portfolioName is here not passed but the real eventListName hence not relevant
		Boolean isMonitoredForAlertsOnEvents = sortedDataResultMap.keySet().stream()
				.filter(k -> k instanceof ParameterizedEventKey)
				.map(k -> isMonitoredForEvents(symbolEvents.getStock(), (ParameterizedEventKey) k))
				.reduce(false, (r,e)-> r || e);
		LOGGER.info("SymbolEvents "+symbolEvents+" is monitored for events: "+isMonitoredForAlertsOnThreshold);
		if (isMonitoredForAlertsOnEvents) return true;

		//Other ..

		return false;

	}
	
	public Boolean isMonitoredForEvents(Stock stock, ParameterizedEventKey alertKey) {
		for (AbstractSharesList portfolio : this.portfolios) {
			for(PortfolioShare portfolioShare : portfolio.getListShares().values()) {
				if (portfolioShare.getStock().lenientSymbolEquals(stock)) {
					if (!portfolioShare.getMonitorLevel().equals(MonitorLevel.NONE)) {//Found monitored in Portfolio
							if (portfolioShare.getAlertsOnEvent().isEmpty()) return true; //The default is pass through
							Optional<AlertOnEvent> findFirst = portfolioShare.getAlertsOnEvent().stream()
							.filter(aoe -> aoe.getEventInfoReference().equals(alertKey.getEventInfo().getEventDefinitionRef()))
							.findFirst();
							return findFirst.isPresent();
						}
					}
				}
			}
		return false;
	}

	public Boolean isMonitoredForAlerts(Stock stock, String portfolioName, AlertEventKey alertKey) {

		LOGGER.info("Checking monitor on: "+stock+" in "+portfolioName+" for "+alertKey);

		AbstractSharesList portfolio = getPortfolio(portfolioName);
		for(PortfolioShare portfolioShare : portfolio.getListShares().values()) {
			if (portfolioShare.getStock().equals(stock)) {

				LOGGER.info("Checking "+portfolioShare+" against "+stock+" in "+portfolioName+" for "+alertKey);
				MonitorLevel monitorLevel = portfolioShare.getMonitorLevel();

				if (monitorLevel.equals(MonitorLevel.ANY)) return true;
				if (monitorLevel.equals(MonitorLevel.NONE)) return false;

				if (alertKey == null) return true; //Any alerts on threshold

				//MonitorLevel == BEARISH || BULLISH
				switch(alertKey.getAlertType()) {
				case ABOVE_PRICE_CHANNEL:
				case AVG_BUY_PRICE:
				case BELOW_PRICE_CHANNEL:
				case BELOW_ZERO_WEIGHTED_PROFIT_LIMIT:
				case MANUALDOWN:
				case MANUALUP:
					return true;
				case ABOVE_TAKE_PROFIT_LIMIT:
				case BELOW_MAX_LOSS_LIMIT:
					return monitorLevel.equals(MonitorLevel.BEARISH);
				}

			}
		}
		LOGGER.info("No stock found against "+stock+" in "+portfolioName+" for "+alertKey);
		return false;

	}


}
