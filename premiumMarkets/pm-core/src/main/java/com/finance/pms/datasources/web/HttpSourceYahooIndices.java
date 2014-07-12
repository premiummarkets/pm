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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.SimpleHttpClient;

public class HttpSourceYahooIndices extends HttpSourceMarket {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceYahooIndices.class);
	
	
	private final String PARIS="1rP";
	private final String LONDON="1u";
	
	////http://uk.old.finance.yahoo.com/d/quotes.csv?s=%40%5ENDX&f=sl1d1t1c1ohgv&e=.csv
	//private final String YAHOO_UK = "http://uk.old.finance.yahoo.com/d/quotes.csv?s=";
	
	//http://download.finance.yahoo.com/d/quotes.csv?s=%5EFTSE&f=sl1d1t1c1ohgv&e=.csv
	//private final String YAHOO_UK = "http://download.finance.yahoo.com/d/quotes.csv?s=";
	
	//http://finance.yahoo.com/q/cp?s=^FTSE
	private final String YAHOO_UK = "http://finance.yahoo.com/q/cp?s=";
	
	//private final String CSV_PARAM = "&f=sl1d1t1c1ohgv&e=.csv";
	private final String CSV_PARAM = "&c=%s";

	//private final String OTHER="1mC";
	
	//http://uk.old.finance.yahoo.com/d/quotes.csv?s=@%5EFCHI&f=sl1d1t1c1ohgv&e=.csv
	//	private final String CACsParam = "@^FCHI";
	//	private final String FTSEsParam = "@^FTLC";
	
	
	public HttpSourceYahooIndices(String pathToprops, Providers beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	protected MyHttpClient myHttpConnect() throws HttpException, IOException {
		return new SimpleHttpClient();
	}

	@Override
	public String getCategoryStockListURL(StockCategories stockCategory, String...params) {

		String urlRet = YAHOO_UK;

		try {
			urlRet = urlRet + URLEncoder.encode(params[0],"UTF-8") + String.format(CSV_PARAM, params[1]);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		}

		return urlRet;
	}

	@Override
	public String getStockInfoPageURL(String refName) throws UnsupportedEncodingException {
		return "http://uk.finance.yahoo.com/q?s="+ URLEncoder.encode(refName,"UTF-8");
	}
	
	public String getStockInfoPageProfilURL(String isin) throws UnsupportedEncodingException {
		return "http://uk.finance.yahoo.com/q/pr?s="+ URLEncoder.encode(isin,"UTF-8");
	}
	
	private String boParams(String eUrl) {
		String param = "";
		if (eUrl.substring(eUrl.length() - 3).equals(".PA")) {
			param = PARIS + eUrl.substring(0, eUrl.length() - 3);
		} else
			if (eUrl.substring(eUrl.length() - 2).equals(".L")) {
				param = LONDON + eUrl;
			} else {
				param = eUrl;
			}
		return param;
	}
	
	
	@Override
	protected HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		GetMethod getMethod = new GetMethod(url.getUrl());
		return getMethod;
	}
	
	/////////////////Opinions
	
	//Yahoo
	public String getStockInfoPageOpinionsURL(String isin) throws UnsupportedEncodingException {		
		return "http://uk.finance.yahoo.com/q/ao?s="+ URLEncoder.encode(isin,"UTF-8");
	}

	public String getStockSummaryPageURL(Stock stock) throws UnsupportedEncodingException {	
		
		String symbol;
		String thisMethodCallProvider = "yahoo";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, true);
		
		return "http://uk.finance.yahoo.com/q?s="+ URLEncoder.encode(symbol,"UTF-8");
	}
	
	public String getStockInfoPageEstimatesURL(Stock stock) throws UnsupportedEncodingException {
		
		//No extension
//		String symbol;
//		String thisMethodCallProvider = "yahoo";
//		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
//		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, true);
		String symbol = stock.getSymbol();
		
		return "http://finance.yahoo.com/q/ae?s="+ URLEncoder.encode(symbol.replaceAll("\\..*", ""),"UTF-8");
	}
	
	public String getStockInfoPageUKEstimatesURL(Stock stock) throws UnsupportedEncodingException {
		
		String symbol;
		String thisMethodCallProvider = "yahoo";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, true);
		
		return "http://uk.finance.yahoo.com/q/ae?s="+ URLEncoder.encode(symbol,"UTF-8");
	}

	//Reuters //Nb : there is no Provider for Reuters => we translate to yahoo
	public String getStockInfoPageReutersFinancialsURL(Stock stock) throws UnsupportedEncodingException {
		
		String symbol;
		String thisMethodCallProvider = "any";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, false);
		
		return "http://www.reuters.com/finance/stocks/financialHighlights?symbol="+ URLEncoder.encode(symbol,"UTF-8");
	}
	
	public String getStockInfoPageReutersOverViewURL(Stock stock) throws UnsupportedEncodingException {
		
		String symbol;
		String thisMethodCallProvider = "any";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, false);
		
		return "http://www.reuters.com/finance/stocks/overview?symbol="+ URLEncoder.encode(symbol,"UTF-8");
	}
	
	//Bourso //Nb : there is no specific extension for Bourso => we translate to yahoo
	public String getStockInfoPageBOResumeURL(Stock stock) throws UnsupportedEncodingException {
		//return "http://www.boursorama.com/cours.phtml?code=" + URLEncoder.encode(isin,"UTF-8") + "&choix_bourse=pays%3D33&categorie=";
		//return "http://www.boursorama.com/cours.phtml?symbole="+boParams(URLEncoder.encode(symbol,"UTF-8"));

		//return "http://www.boursorama.com/bourse/profil/resume_societe.phtml?symbole="+boParams(URLEncoder.encode(symbol,"UTF-8"));
		String symbol;
		String thisMethodCallProvider = "boursorama";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, false);
		
		return "http://www.boursorama.com/bourse/profil/resume_societe.phtml?symbole="+boParams(URLEncoder.encode(symbol,"UTF-8"));
	}

	protected String extentionTwist(Stock stock, String thisMethodCallProvider, String thisMethodCallExtension, boolean lenient) {
		String symbol;
		String cmdParam = stock.getSymbolMarketQuotationProvider().getCmdParam();
		if (cmdParam.equals(thisMethodCallProvider) || (lenient && cmdParam.contains(thisMethodCallProvider))) {
			symbol = stock.getSymbol();
		} else {
			if (thisMethodCallExtension.isEmpty()) {
				symbol = stock.getSymbolRoot();
			} else {
				symbol = stock.getSymbolRoot() + "." + thisMethodCallExtension;
			}
		}
		return symbol;
	}
	
	public String  getStockInfoPageBOEstimatesURL(Stock stock) throws UnsupportedEncodingException  {
		
		String symbol;
		String thisMethodCallProvider = "boursorama";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, false);
		
		return "http://www.boursorama.com/bourse/actions/conseils/consensus/consensus_previsions.phtml?symbole="+boParams(URLEncoder.encode(symbol,"UTF-8"));
	}
	
	public String getStockInfoPageBOpinionsURL(Stock stock) throws UnsupportedEncodingException {
		
		String symbol;
		String thisMethodCallProvider = "boursorama";
		String thisMethodCallExtension = stock.getMarket().getYahooExtension().getSpecificMarketExtension();
		symbol = extentionTwist(stock, thisMethodCallProvider, thisMethodCallExtension, false);
		
		String eUrl = URLEncoder.encode(symbol,"UTF-8");
		String param = boParams(eUrl);
		//return "http://www.boursorama.com/infos/consensus/consensus_analystes.phtml?symbole="+param;
		return "http://www.boursorama.com/bourse/actions/conseils/consensus/consensus_analystes.phtml?symbole="+param;
	}	
	
}
