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
package com.finance.pms.datasources.web;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.AssVieFormater;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.threads.ConfigThreadLocal;

public class AssVieScanner implements MyBeanFactoryAware {
	
	private final MyLogger LOGGER = MyLogger.getLogger(AssVieScanner.class);
	
	private HttpSourceAssVie httpSource;
	private BeanFactory beanFactory;

	private AssVieScanner(String pathToprops, MyBeanFactoryAware beanFactory) {
		super();
		httpSource = new HttpSourceAssVie(pathToprops, beanFactory);
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Set<Stock> fetchAssVieStocks() throws HttpException {
		
		final String url = httpSource.getUrl();
		LOGGER.debug("Url : "+url);
	
		Set assVieStocks = new HashSet<Stock>();
		assVieStocks.addAll(httpSource.readURL(new AssVieFormater(url)));
		
		return assVieStocks;
			
	}
	
	public void getQuotesForAssVieStocks(StockList assVieStockList) {
		
		StockList dbStockList = new StockList();
		String sharesListName = "euronext";
		Providers.getInstance(sharesListName).retrieveStockListFromBase(dbStockList);
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		try {
			quotationUpdate.getQuotes(assVieStockList);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
		
	}
	
	public void resetAssViePortfolio(StockList assVieStockList) throws InvalidAlgorithmParameterException {
		Portfolio portfolio = new UserPortfolio("CeAssVie", Currency.EUR);
		PortfolioMgr.getInstance().removePortfolio(portfolio);

		for (Stock stock : assVieStockList) {

			try {
				portfolio.addOrUpdateShareForQuantity(stock, BigDecimal.ONE, EventSignalConfig.getNewDate(), MonitorLevel.NONE, Currency.EUR);
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.error(e,e);
			} catch (InvalidQuantityException e) {
				LOGGER.error(e,e);
			} catch (NoQuotationsException e) {
				LOGGER.error(e,e);
			}

		}

		PortfolioMgr.getInstance().addPortfolio(portfolio);
	}
	
	public static void main(String... args) throws HttpException, InvalidAlgorithmParameterException {

		String pathToprops = args[0];

		SpringContext springContext = null;
		try {
			springContext = new SpringContext(pathToprops);
			springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
			springContext.refresh();
			
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());

			AssVieScanner assVieScaner = new AssVieScanner(pathToprops, (MyBeanFactoryAware) springContext.getBean("yahooProviderSource"));
			Set<Stock> assVieStocks = assVieScaner.fetchAssVieStocks();
			StockList assVieStockList = new StockList(assVieStocks);
			assVieScaner.getQuotesForAssVieStocks(assVieStockList);
			assVieScaner.resetAssViePortfolio(assVieStockList);

		} finally {

			if (springContext != null) springContext.close();
		}

	}
	
	

	
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;
		
	}

	
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	
}
