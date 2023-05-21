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
package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.tidy.Tidy;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MarketListProvider;
import com.finance.pms.datasources.web.ProvidersList;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.SharesList;


//TODO Merge the rest of transaction and Portfolio Gnu parser commons.
public class GnuCashParserHelper {
	
	private static MyLogger LOGGER = MyLogger.getLogger(GnuCashParserHelper.class);
	
	private Map<String, List<Stock>> matchingsCache;

	public GnuCashParserHelper() {
		super();
		matchingsCache = new HashMap<String, List<Stock>>();
	}
	
	protected ByteArrayInputStream tidyDocument(InputStream inputStream) throws FileNotFoundException, IOException {
		
		Tidy tidy = new Tidy();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.setTrimEmptyElements(true);
		tidy.setMakeClean(true);
		tidy.setQuoteNbsp(true);
		tidy.setXmlOut(true);
		tidy.setInputEncoding("UTF-8");
		tidy.parseDOM(inputStream, outputStream);
		
		
		LOGGER.trace(outputStream.toString("UTF-8"));
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	protected BigDecimal calculateBigDecimal(String textContent) throws ParseException {
		try {
			Number number = extractNumber(textContent);
			return new BigDecimal(number.doubleValue()).setScale(4, RoundingMode.HALF_EVEN);
		} catch (ParseException e) {
			throw e;
		}
	}

	protected Currency extractCurrency(String columnTxt) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking if :"+columnTxt+" contains $?");
		if (columnTxt.contains("$") || columnTxt.contains("\u0024")) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug(columnTxt+" contains $!");
			return Currency.USD;
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking if :"+columnTxt+" contains \u00A3?");
		if (columnTxt.contains("\u00A3")) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug(columnTxt+"contains \u00A3!");
			return Currency.GBP;
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking if :"+columnTxt+" contains \u20AC?");
		if (columnTxt.contains("\u20AC")) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug(columnTxt+"contains \u20AC!");
			return Currency.EUR;
		}
		return   Currency.valueOf(columnTxt.replace("\n","").trim().split("( |\n)")[0]);
	}
	
	protected Number extractNumber(String textContent) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getInstance();
		Number number = numberFormat.parse(textContent.replaceAll("(\\$|\u0024|\u00A3|\u20AC)","").replaceAll("[A-Z][A-Z][A-Z]( |\n)*", "").trim());
		return number;
	}

	public StringWriter deleteDocType(String filePath) throws FileNotFoundException, IOException {
		//"http://www.w3.org/TR/2000/REC-xhtml1-20000126/DTD/xhtml1-strict.dtd"
		//change html to xml
		String firstLine = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 TRANSITIONAL//EN\">";
		String firstLine2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"";
		String firstLine3 = "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		String firstLine4 = "xmlns=\"http://www.w3.org/1999/xhtml\"";
		String firstline5 = "<!DOCTYPE html>";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringWriter outputWriter = new StringWriter();

		String line = null;

		//Read from the original file and write to the new
		//unless content matches data to be removed.
		int startOfFile = 0;
		outputWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
		while ((line = br.readLine()) != null) {
			
			if (startOfFile < 10) {
				line = line.replaceAll(firstLine, "");
				line = line.replaceAll(firstLine2, "");
				line = line.replaceAll(firstLine3, "");
				line = line.replaceAll(firstLine4, "");
				line = line.replaceAll(firstline5, "");
			}
			
			//if (!line.trim().equals(firstLine)) {
			if (!line.trim().isEmpty()) {
				outputWriter.write(line);
				outputWriter.flush();
			}
			
			startOfFile++;
		}
		outputWriter.close();
		br.close();
		return outputWriter;
	}
	
	protected Stock findMatchingStock(String symbol) {

		List<Stock> matchingStocks = matchingsCache.get(symbol);
		if (matchingStocks == null) {
			matchingStocks = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(symbol);
			matchingsCache.put(symbol, matchingStocks);
		}
		
		Stock stock;
		int size = matchingStocks.size();
		switch(size) {
			case 0 : {
				LOGGER.warn("No stock for symbol or isin : "+symbol);
				return null;
			} 
			case 1 : {
				stock = matchingStocks.get(0);
				break;
			} 
			default : {// > 1
				LOGGER.warn("Multiple stocks for symbol or isin : "+symbol+" in matchings "+matchingStocks);
				stock = discriminateMatchingStocks(matchingStocks);
				break;
			}
		}

		if (stock == null) {
			LOGGER.warn("Although No valid match for symbol or isin : "+symbol+" in matchings "+matchingStocks);
		} else {
			matchingsCache.put(symbol, Arrays.asList(new Stock[]{stock}));
		}
		return stock;
		
	}

	private Stock discriminateMatchingStocks(List<Stock> matchingStocks) {
		
			for (Stock stock : matchingStocks) {
				List<PortfolioShare> portfolioShareInShareList = PortfolioMgr.getInstance().getPortfolioDAO().loadPortfolioShareForStock(stock);
				for (PortfolioShare portfolioShare : portfolioShareInShareList) {
					AbstractSharesList portfolio = portfolioShare.getPortfolio();
					if (portfolio instanceof SharesList) {
						MarketListProvider provider;
						if (portfolio.getName().equals(SharesListId.UNKNOWN.name())) {//TODO infer stock list from extension?
							provider = ProvidersList.getMarketListInstance(SharesListId.valueOf("YAHOOINDICES").name());
						} else {
							provider = ProvidersList.getMarketListInstance(portfolio.getName());
						}
						Stock supplementedStock = provider.supplement(stock);
						if (stock.equals(supplementedStock)) {
							LOGGER.warn("Best match : "+stock+" for "+matchingStocks);
							return stock;
						}
					}
				}
			}
			LOGGER.warn("No match for "+matchingStocks);
			return null;
			
	}
	
}
