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

import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.YahooMarketExtentions;
import com.finance.pms.datasources.web.formaters.DayQuoteYahooFormater;


/**
 * The Class ProvidersYahoo.
 * 
 * @author Guillaume Thoreton
 */
public class ProvidersYahoo extends Providers implements QuotationProvider, MarketListProvider {

	protected static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahoo.class);

	public ProvidersYahoo(String pathToProps) {
		super();
		this.httpSource = new HttpSourceYahoo(pathToProps, this);
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
	public void getQuotes(Stock stock, Date start, Date end) throws HttpException, SQLException {

		//TODO scrapeLast();

		if (stock.getSymbol() == null) throw new RuntimeException("Error : no Symbol for "+stock.toString());

		MyUrl url;
		try {
			url = resolveUrlFor(stock, start, end);
		} catch (InvalidAlgorithmParameterException e) {
			return;
		}

		TreeSet<Validatable> queries = initValidatableSet();
		queries.addAll(readPage(stock, url, start));

		LOGGER.guiInfo("Getting last quotes : Number of new quotations for "+stock.getSymbol()+" :"+queries.size());

		try {
			ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
			tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
		} catch (SQLException e) {
			
			LOGGER.error("Yahoo quotations sql error trying : "+url.getUrl(), e);
			throw e;
		}

	}

	public List<Validatable> readPage(Stock stock, MyUrl url, Date  start) throws HttpException {
		DayQuoteYahooFormater dsf = new DayQuoteYahooFormater(url, stock, stock.getMarketValuation().getCurrency().name());
		return this.httpSource.readURL(dsf);
	}

    @Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {
        // TODO Recuperation de la liste des stocks sur yahoo
        LOGGER.error("retreiving ticker list is not implemented for yahoo");
        throw new UnsupportedOperationException("Please use another provider the share list holder for that.");
    }
    
    
    @Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks,StockList stockList, String quotationsProvider) {
    	throw new UnsupportedOperationException("Please use another share list holder provider for that.");
    }

    
	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) {

		//No check available for Yahoo
		if (!s.isFieldSet("isin") || !s.isFieldSet("symbol") || !s.isFieldSet("name")) {
			LOGGER.warn("No completion check on symbol, isin, name is available for the Yahoo provider. Please provide the full info (symbol, isin, name) for each stock : "+s);
		} else {
			List<Validatable> listReq = new ArrayList<Validatable>();

			if (!stockList.contains(s)) { // not already in base
				
				//check for last former quotation
				Date pastLastQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(s, false);
				s.setLastQuote(pastLastQuotationDate);
				
				LOGGER.info("New ticker : "+s.toString()+" and will be added with last quote : "+ pastLastQuotationDate);
				
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
	public String getStockRefName(Stock ticker){
	    return ticker.getSymbol();
	}
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		//Nothing
	}

	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws InvalidAlgorithmParameterException {
		
		MyUrl url;
			
		Date today = EventSignalConfig.getNewDate();
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(today);
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		todayCal.set(Calendar.MILLISECOND, 0);
		today = todayCal.getTime();
		
		if (start.after(end) || ( start.compareTo(end) == 0 && end.compareTo(today) == 0 ) ) throw new InvalidAlgorithmParameterException();
		

		Calendar gcStart = Calendar.getInstance();
		gcStart.setTime(start);
		Calendar gcEnd = Calendar.getInstance();
		gcEnd.setTime(end);

		String symbol = stock.getSymbol();
		if (symbol.endsWith("."+YahooMarketExtentions.EURONEXT.getSpecificMarketExtension())) {
			String regex = "\\."+YahooMarketExtentions.EURONEXT.getSpecificMarketExtension()+"$";
			String replacement = "."+YahooMarketExtentions.PAR.getSpecificMarketExtension();
			symbol = symbol.replaceAll(regex, replacement);
		}
		if ('^' != symbol.charAt(0) && stock.getCategory().equals(StockCategories.INDICES_OTHER)) symbol = "^"+symbol;

		url = this.httpSource.getStockQuotationURL(symbol,
				(new Integer(gcStart.get(Calendar.YEAR))).toString(),
				(new Integer(gcStart.get(Calendar.MONTH))).toString(), 
				(new Integer(gcStart.get(Calendar.DAY_OF_MONTH))).toString(), 
				(new Integer(gcEnd.get(Calendar.YEAR))).toString(),
				(new Integer(gcEnd.get(Calendar.MONTH))).toString(),
				(new Integer(gcEnd.get(Calendar.DAY_OF_MONTH))).toString());
		
		return url;
	}

}
