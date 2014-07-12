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
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.StockListNASDAQFormater;
import com.finance.pms.portfolio.SharesList;


// TODO: Auto-generated Javadoc
/**
 * The Class ProvidersNASDAQ.
 * 
 * @author Guillaume Thoreton
 */
public class ProvidersNASDAQ extends Providers implements MarketListProvider {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersNASDAQ.class);
	
	
	/**
	 * Instantiates a new providers nasdaq.
	 * 
	 * @param pathToProps the path to props
	 * 
	 * @author Guillaume Thoreton
	 */
	public ProvidersNASDAQ(String pathToProps) {
		super();
		this.httpSource = new HttpSourceNASDAQ(pathToProps, this);
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
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	public void getQuotes(Stock ticker, Date start, Date end) throws SQLException {
		throw new UnsupportedOperationException("Please use another provider then a share list holder for that.");
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) {
		
		//No check available for NASDAQ
		if (!s.isFieldSet("isin") || !s.isFieldSet("symbol") || !s.isFieldSet("name")) {
			LOGGER.warn("No check available for "+this.getClass().getSimpleName()+" provider. Please provide full info.");
		} else {
			List<Validatable> listReq = new ArrayList<Validatable>();
			if (!stockList.contains(s)) { // not already in base	
				
				//check for last former quotation
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(s, false);
				s.setLastQuote(formerQuotationDate);
				
				LOGGER.info("New ticker : "+s.toString()+" and will be added with last quote : "+ formerQuotationDate);
				
				listReq.add(s);
				stockList.add(s);
				
			} else { // already in base : update name
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
		throw new UnsupportedOperationException("Please use another provider then a share list holder for that.");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	//TODO Merge. One and only methods for all providers
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {
		LOGGER.info("From Web - "+this.getClass().getSimpleName()+" : ");
		
		//Share list
		SharesList shareList = loadSharesListForThisListProvider();
		Set<Stock> sharesListStocks = new ConcurrentSkipListSet<Stock>(); 
		
		List<Validatable> listNew = new ArrayList<Validatable>();
		List<Validatable> listReqIns = new ArrayList<Validatable>();
		List<Validatable> listReqDel = new ArrayList<Validatable>();
//		List<Validatable> listReqDelS = new ArrayList<Validatable>();
		
		String url = ((HttpSourceNASDAQ) this.httpSource).getCategoryStockListURL(StockCategories.DEFAULT_CATEGORY);
		LOGGER.info("NASDAQ Url : " + url);

		StockListNASDAQFormater lsf = new StockListNASDAQFormater(url, StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
		List<? extends Validatable> ltmp= new ArrayList<Validatable>();
		try {
			ltmp = ((HttpSourceNASDAQ)this.httpSource).readURL(lsf);
		} catch (HttpException e1) {
			LOGGER.error("",e1);
		}
		listNew.addAll(ltmp);
		
		LOGGER.guiInfo("Number of stocks retreived from web on for the " + new Date() + " : " + listNew.size());
		
		//share list addition
		sharesListStocks.addAll((List)listNew);
		
//		//Suppression des tickers non mis � jour depuis x temps
//		ListIterator<Stock> oldStockList = stockList.listIterator();
//		while (oldStockList.hasNext()) {
//			Stock stockBase = oldStockList.next();
//			if (!listNew.contains(stockBase) && stockBase.toBeRemoved(SharesListId.NASDAQ)) {
//				LOGGER.info("Ticker " + stockBase.toString() + " is obsolete and will be removed");
//				buildLookupDeleteReq(listReqDelS, listReqDel, stockBase);
//				oldStockList.remove();
//			}
//		}
		
		//Adding new from web
		ListIterator<Validatable> newStockList = listNew.listIterator();
		while (newStockList.hasNext()) {
			Validatable stockWeb = newStockList.next();
			if (!stockList.contains(stockWeb)) {
				
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations((Stock)stockWeb, false);
				((Stock)stockWeb).setLastQuote(formerQuotationDate);
				
				LOGGER.info("Ticker " + stockWeb.toString() + " is new and will be added with last quote : "+ formerQuotationDate);
				listReqIns.add(stockWeb);
				stockList.add((Stock)stockWeb);
			}
		}
		
		//No completion for NASDAQ needed //TODO merge with boursorama ...
		
		//Addition of new stocks in DB
		LOGGER.guiInfo("Number of tickers to be added : " + listReqIns.size());
		LOGGER.info("Tickers to be added : " + listReqIns);
		try {
			DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReqIns);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	
		//Deletion of old stocks in DB
		LOGGER.guiInfo("Number of tickers to be removed : " + listReqDel.size());
		LOGGER.info("Tickers to be removed : " + listReqDel);
		
		//Share list
		updatingShareListInDB(shareList, sharesListStocks);
		
		
		return stockList;
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
