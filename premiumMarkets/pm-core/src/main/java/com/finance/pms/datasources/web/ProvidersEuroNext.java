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
package com.finance.pms.datasources.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.StockListEuroNextFormater;
import com.finance.pms.portfolio.SharesList;


/**
 * The Class ProvidersEuroNext.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class ProvidersEuroNext extends Providers implements MarketListProvider {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersEuroNext.class);
	
	public ProvidersEuroNext(String pathToProps) {
		super();
		this.httpSource = new HttpSourceEuroNext(pathToProps, this);
	}
	
	@Override
	public String getStockRefName(Stock stock) {
		return stock.getIsin();
	}
	
	@Override
	public SortedSet<Indice> getIndices() {
		return new TreeSet<Indice>();
	}
	
	@Override
	public void addIndice(Indice indice) {
		// Nothing
	}

	@Override
	public void getQuotes(Stock ticker, Date start, Date end) throws SQLException {
		throw new UnsupportedOperationException("Please use another provider then a share list holder for that.");
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) { 
		
		//No check available for Euro Next
		if (!s.isFieldSet("isin") || !s.isFieldSet("symbol") || !s.isFieldSet("name")) {
			LOGGER.warn("No check available for Euro Next provider. Please provide full info.");
		} else {
			List<Validatable> listReq = new ArrayList<Validatable>();
			if (!stockList.contains(s)) { //not already in base	
				
				//check for last former quotation
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(s, false);
				s.setLastQuote(formerQuotationDate);
				
				LOGGER.info("New ticker : "+s.toString()+" and will be added with last quote : "+ formerQuotationDate);
				
				listReq.add(s);
				stockList.add(s);
				
			} else { //already in base : update name
				stockList.get(stockList.indexOf(s)).setName(s.getName());
			}
			try {
				DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReq);
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}

	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks,StockList stockList, String quotationsProvider) {
		throw new UnsupportedOperationException("Please use another provider the share list holder for that.");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	//TODO Merge. One and only methods for all providers
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) throws HttpException {
		LOGGER.info("From Web : ");
		
		//Share list
		SharesList shareList = loadSharesListForThisListProvider();
		Set<Stock> sharesListStocks = new ConcurrentSkipListSet<Stock>(); 
		
		List<Validatable> listNew = new ArrayList<Validatable>();
		List<Validatable> listReqIns = new ArrayList<Validatable>();
		List<Validatable> listReqDel = new ArrayList<Validatable>();
//		List<Validatable> listReqDelS = new ArrayList<Validatable>();
		
		String url = ((HttpSourceEuroNext) this.httpSource).getCategoryStockListURL(StockCategories.DEFAULT_CATEGORY);
		LOGGER.info("Euronext Url : " + url);
		StockListEuroNextFormater lsf = new StockListEuroNextFormater(url, StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
		List<? extends Validatable> ltmp  = new ArrayList<Validatable>();
		try {
			ltmp = ((HttpSourceEuroNext)this.httpSource).readURL(lsf);
		} catch (HttpException e) {
			LOGGER.error(e,e);
			throw e;
		}
		listNew.addAll(ltmp);
		
		LOGGER.guiInfo("Number of stocks retreived from web on for the " + new Date() + " : " + listNew.size());
		
		//share list addition
		sharesListStocks.addAll((List)listNew);
		
//		//Suppression des tickers non mis � jour depuis x temps
//		ListIterator<Stock> oldStockList = stockList.listIterator();
//		while (oldStockList.hasNext()) {
//			Stock stockBase = oldStockList.next();
//			if (!listNew.contains(stockBase) && stockBase.toBeRemoved(SharesListId.EURONEXT)) {
//				LOGGER.info("Ticker " + stockBase.toString() + " is obsolete and will be removed");
//				buildLookupDeleteReq(listReqDelS, listReqDel, stockBase);
//				oldStockList.remove();
//			}
//		}
		
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
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations((Stock)stockWeb, false);
				((Stock)stockWeb).setLastQuote(formerQuotationDate);
				
				LOGGER.info("Ticker " + stockWeb.toString() + " is new and will be added with last quote : "+ formerQuotationDate);
				LOGGER.guiInfo("Ticker " + stockWeb.toString() + " is new and will be added with last quote : "+ formerQuotationDate);
				
				listReqIns.add(stockWeb);
				stockList.add((Stock)stockWeb);
				nbNew++;
			}
		}
		
		LOGGER.guiInfo("Number of tickers to be added : " + nbNew);
		LOGGER.info("Tickers to be added : " + listReqIns);
		
		this.shareDAO.saveOrUpdateStocks(listNew);
	
		//Deletion of old stocks in DB
		LOGGER.guiInfo("Number of tickers to be removed : " + listReqDel.size());
		LOGGER.info("Tickers to be removed : " + listReqDel);
//		try {
			//FIXME : consistency pb when deleting shares cause no data available from the web ....
			//DataSource.getInstance().executeBlock(listReqDel, DataSource.SHARES.getDELETE());
			//FIXME : Performance pb when deleting quotations ....
			//DataSource.getInstance().executeBlock(listReqDelS, DataSource.QUOTATIONS.getDELETE());
//		} catch (SQLException e) {
//			LOGGER.error("ERROR : Deleting ticker for ticker list : shares " + listReqDel + ", quotations " + listReqDelS, e);
//		}
		
		//Share list
		updatingShareListInDB(shareList, sharesListStocks);
		
		return stockList;
	}
	
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void addIndices(SortedSet<Indice> indices, Boolean replace) {
		// TODO Auto-generated method stub
		
	}
}
