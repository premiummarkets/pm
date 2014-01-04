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
package com.finance.pms.datasources.shares;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.finance.pms.admin.install.logging.MyLogger;

/**
 * The Class StockList.
 * 
 * @author Guillaume Thoreton
 */
public class StockList extends ArrayList<Stock> {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(StockList.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2886371019007893961L;

	/**
	 * Instantiates a new stock list.
	 * 
	 * @author Guillaume Thoreton
	 */
	public StockList() {
	}

	/**
	 * Instantiates a new stock list.
	 * 
	 * @param pathToList the path to list
	 * 
	 * @author Guillaume Thoreton
	 */
	public StockList(String pathToList) throws InputMismatchException {
		super();
		try {
			File f = new File(pathToList);
			FileInputStream fileStream = new FileInputStream(f);
			InputStreamReader inputStream = new InputStreamReader(fileStream);
			BufferedReader fileReader = new BufferedReader(inputStream);
			
			String line = fileReader.readLine();
			while (line != null) {
				if (line.length() > 0 && !line.startsWith("#")) {
					StringTokenizer st = new StringTokenizer(line,",",true);
					String[] toktbl = { 
							Stock.MISSINGCODE, Stock.MISSINGCODE, "No Name",
							StockCategories.DEFAULT_CATEGORY.getCategory(),
							SharesListId.UNKNOWN.getSharesListCmdParam(), "1",
							MarketQuotationProviders.YAHOO.getCmdParam(),
							 };
					//TODO Gestion des ticker provenant d'un fichier : cat�gorie??
					Stock newStock;
					int cptr = 0;
					while (st.hasMoreTokens() && cptr < 2) { //symbol, isin
						String value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? Stock.MISSINGCODE : value4col.trim();
						if (!value4col.equals(",")) st.nextToken();
						cptr++;
					}
					String value4col;
					if (st.hasMoreTokens()) { //name
						value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? "" : value4col.trim();
						if (!value4col.equals(",")) st.nextToken();
						cptr++;
					}
					if (st.hasMoreTokens()) { //category
						value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? StockCategories.DEFAULT_CATEGORY.getCategory() : value4col.trim();
						if (!value4col.equals(",")) st.nextToken();
						cptr++;
					}
					if (st.hasMoreTokens()) {//market
						value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? Market.UNKNOWN.getMarketName() : value4col.trim();
						if (!value4col.equals(",")) st.nextToken();
						cptr++;
					}
					if (st.hasMoreTokens()) {//market currency factor
						value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? BigDecimal.ONE.toString(): value4col.trim();
						if (!value4col.equals(",")) st.nextToken();
						cptr++;
					}
					if (st.hasMoreTokens()) {//quotation provider
						value4col = st.nextToken();
						toktbl[cptr] = (value4col.equals(",")) ? MarketQuotationProviders.DEFAULT.getCmdParam() : value4col.trim();
					}
					newStock = new Stock(
							toktbl[1], toktbl[0], toktbl[2], false,
							StockCategories.valueOfString(toktbl[3]),
							new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOfCmd(toktbl[6]),SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
							new MarketValuation(Market.valueOf(toktbl[4]),new BigDecimal(toktbl[5])),
							"",TradingMode.CONTINUOUS,0l);
					this.add(newStock);
				}
				line = fileReader.readLine();
				
			}
			fileReader.close();
		} catch (Exception e) {
			LOGGER.warn("ERROR, your ticker file properties is not valid : " + e,e);
			throw new InputMismatchException();
		}
	}

	/**
	 * Instantiates a new stock list.
	 * 
	 * @param marketListProvider the provider
	 * @param listStock the list stock
	 * 
	 * @author Guillaume Thoreton
	 */
	public StockList(SymbolMarketQuotationProvider marketListProvider, List<String> listStock) {
		super();
		Iterator<String> listIt = listStock.iterator();
		while (listIt.hasNext()) {
			try {
				Stock newStock = new Stock(listIt.next().toUpperCase(),null,null,false,StockCategories.DEFAULT_CATEGORY,
						new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
						new MarketValuation(Market.UNKNOWN),"",TradingMode.CONTINUOUS,0l);
				newStock.setSymbolMarketQuotationProvider(marketListProvider);
				this.add(newStock);
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.debug(e);
			}
		}
	}

	public StockList(Set<Stock> existingStocksSet) {
		super();
		this.addAll(existingStocksSet);
	}
	
	public StockList(List<Stock> existingStocksSet) {
		super();
		this.addAll(existingStocksSet);
	}

	public Stock findLenientRefs(String ... refs) {
		for (Stock stock : this) {
			for (String ref : refs ) {
				if (ref != null && (stock.getSymbol().equals(ref) || stock.getIsin().equals(ref))) {
					return stock;
				}
			}
		}
		return null;
	}
		

}
