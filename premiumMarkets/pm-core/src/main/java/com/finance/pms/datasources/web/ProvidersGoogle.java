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
package com.finance.pms.datasources.web;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.GoogleSymbolNameResolver;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DayQuoteFormater;
import com.finance.pms.datasources.web.formaters.DayQuoteGoogleFormater;
import com.finance.pms.datasources.web.google.MarketList;
import com.finance.pms.portfolio.SharesList;

public class ProvidersGoogle extends Providers implements QuotationProvider {

	protected static MyLogger LOGGER = MyLogger.getLogger(ProvidersGoogle.class);

	
	private Market market;

	public ProvidersGoogle(String pathToProps) {
		super();
		this.httpSource = new HttpSourceGoogle(pathToProps, this);
	
	}
	
	@Override
	public Set<Indice> getIndices() {
		return new TreeSet<Indice>();
	}
	
	@Override
	public void addIndice(Indice indice) {
		// Nothing
	}

	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {
		
		MyUrl url;

		if (stock.getSymbol() == null) throw new RuntimeException("Error : no Symbol for "+stock.toString());
		
		if (isStartAfterTodaysClose(start)) return;
		
		url = resolveUrlFor(stock, start, end);
		TreeSet<Validatable> queries = initValidatableSet();
		queries.addAll(readPage(stock, url));

		LOGGER.guiInfo("Getting last quotes : Number of new quotations for "+stock.getSymbol()+" :"+queries.size());
		ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>() ;
		tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
		DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries),tablet2lock);
		
	}

	public List<Validatable> readPage(Stock stock, MyUrl url) throws HttpException {
		DayQuoteFormater dsf = new DayQuoteGoogleFormater(url,stock,stock.getMarketValuation().getCurrency().name());
		List<Validatable> readURL = this.httpSource.readURL(dsf);
		return readURL;
	}

	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) {
		
		MyUrl url;
		
		Calendar gcStart = Calendar.getInstance();
		gcStart.setTime(start);
		Calendar gcEnd = Calendar.getInstance();
		gcEnd.setTime(end);
		
		//specific
		GoogleSymbolNameResolver googleSymbolNameResolver = new GoogleSymbolNameResolver();
		String extension = googleSymbolNameResolver.getExtension(stock.getSymbol());
		
		//TODO solve market for extension
		Market market  = stock.getMarketValuation().getMarket();
		if ("L".equals(extension)) market =  Market.LSE;
		
		String ticker = market.getMarketName()+":"+stock.getSymbol().replaceAll("\\."+extension, "");
		DateFormat df = new SimpleDateFormat("MMM");
		
		url = this.httpSource.getStockQuotationURL(ticker,
				(new Integer(gcStart.get(Calendar.YEAR))).toString(),
				df.format(gcStart.getTime()), 
				(new Integer(gcStart.get(Calendar.DAY_OF_MONTH))).toString(), 
				(new Integer(gcEnd.get(Calendar.YEAR))).toString(),
				df.format(gcEnd.getTime()),
				(new Integer(gcEnd.get(Calendar.DAY_OF_MONTH))).toString());
		//end specific	
		
		return url;
	}

	@Override
	public String getStockRefName(Stock stock) {
		  return stock.getSymbol();
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) {
		throw new UnsupportedOperationException("Please use another share list holder provider for that.");
	}

	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		throw new UnsupportedOperationException("Please use another share list holder provider for that.");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {
		LOGGER.info("From Web : ");
		
		//Share list
		SharesList shareList = loadSharesListForThisListProvider();
		Set<Stock> sharesListStocks = new ConcurrentSkipListSet<Stock>(); 
		
		List<Validatable> listNew = new ArrayList<Validatable>();
		List<Validatable> listReqIns = new ArrayList<Validatable>();
		
		
		//specific
		GetMethod gmCheckNb = ((HttpSourceGoogle) this.httpSource).getStockListRequest(20,this.market);
		try {
			LOGGER.info("Google Url : " + gmCheckNb.getURI());
		} catch (URIException e) {
			LOGGER.error(e,e);
		}
		MarketList marketListCheckNb = ((HttpSourceGoogle) this.httpSource).readURL(gmCheckNb);
		
		Integer nbStocks = marketListCheckNb.getNum_company_results();
		LOGGER.guiInfo("Number of stocks retreived from web on for the " + new Date() + " : " + nbStocks );
		
		GetMethod gm = ((HttpSourceGoogle) this.httpSource).getStockListRequest(nbStocks,this.market);
		MarketList marketList = ((HttpSourceGoogle) this.httpSource).readURL(gm);
		
		List<? extends Validatable> ltmp = marketList.getStockList(market, StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
		//end specific
		
		listNew.addAll(ltmp);
		LOGGER.guiInfo("Number of tickers retrieved from web for the : " + new Date() + " : " + listNew.size());
		
		//share list addition
		sharesListStocks.addAll((List)listNew);
		
		//Update the last quote date and the stock list fetch from db
		ListIterator<Validatable> newStockList = listNew.listIterator();
		int nbNew = 0;
		while (newStockList.hasNext()) {
			Stock stockWeb = (Stock) newStockList.next();
			if (stockList.contains(stockWeb)) {
				int index = stockList.indexOf(stockWeb);
				stockWeb.setLastQuote(stockList.get(index).getLastQuote());
				stockList.set(index, stockWeb);
				
			} else {
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations((Stock)stockWeb);
				((Stock)stockWeb).setLastQuote(formerQuotationDate);
				
				LOGGER.info("Ticker " + stockWeb.toString() + " is new and will be added with last quote : "+ formerQuotationDate);
				listReqIns.add(stockWeb);
				stockList.add((Stock)stockWeb);
				nbNew++;
			}
		}
		
		LOGGER.guiInfo("Number of tickers to be added : " + nbNew);
		LOGGER.info("Tickers to be added : " + listReqIns);
		
		this.shareDAO.saveOrUpdateStocks(listNew);
		
		//Share list
		updatingShareListInDB(shareList, sharesListStocks);
		
		return stockList;
	}
	
	public void setMarket(Market market) {
		this.market = market;
	}
	
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		// TODO Auto-generated method stub
		
	}
}
