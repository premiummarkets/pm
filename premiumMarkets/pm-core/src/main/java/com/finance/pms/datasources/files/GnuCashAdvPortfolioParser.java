/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import javax.persistence.NoResultException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.springframework.dao.DataAccessException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Converter;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.QuotationProvider;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;

public class GnuCashAdvPortfolioParser {
	
	private static final int REALIZED_GAIN = 10;
	
	private static final int INCOME_COLUMN = 9;

	private static final int MONEYOUT_COLUMN = 8;

	private static final int MONEYIN_COLUMN = 7;
	
	private static final int BASIS_COLUMN = 5;
	
	private static final int PRICE_COLUMN = 3;

	private static final int NBSHARES_COLUMN = 2;

	private static final int SYMBOL_COLUMN = 1;
	
	private static final int ACCOUNT_COLUMN = 0;

	protected static MyLogger LOGGER = MyLogger.getLogger(GnuCashAdvPortfolioParser.class);
	
	ShareDAO shareDAO;
	Converter currencyConverter;
	GnuCashParserHelper gnuCashParserHelper;
	
	private List<String> notFoundStocks;
	

	public List<String> getNotFoundStocks() {
		return notFoundStocks;
	}

	public GnuCashAdvPortfolioParser(ShareDAO shareDAO, Converter currencyConverter) {
		super();
		this.shareDAO = shareDAO;
		this.currencyConverter = currencyConverter;
		this.gnuCashParserHelper = new GnuCashParserHelper();
		this.notFoundStocks = new ArrayList<String>();
	}

	/**
	 * @param filePath
	 * @param oldPortfolio
	 * @param newPortfolio
	 * @return
	 * @throws IOException
	 * Create a new portfolio from the gnucash export html file.
	 * The symbol stored in the file is used to retrieve or create the portfolio shares. 
	 * An extra extension is added to the symbol for use in the designated quotation provider.
	 * The isin will be picked up from db (not form the export).
	 * If the stock is not in db, a warning will be raised for the user to update the db using import stock file feature.
	 */
	public Portfolio parse(String filePath, String newPortfolioName, UserPortfolio oldPortfolio) throws IOException {
		
		//UserPortfolio newPortfolio = new UserPortfolio(newPortfolioName, null);
		UserPortfolio newPortfolio = null;
		
		try {
			
			//Delete first line
			StringWriter outputWriter = deleteDocType(filePath);
			
			//Tidy
			ByteArrayInputStream inputStream = tidyDocument(new StringInputStream(outputWriter.toString()));
			
			//LOG
			if (LOGGER.isTraceEnabled()) {
				BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				while((line = bufferedInputStream.readLine()) != null) {
					System.out.println(line);
				}
				inputStream.reset();
			}
			//LOG
	
			//XPath
			NodeList rows = extractRows(inputStream);  
			
			//Check titles
			checkTitles((Element) rows.item(0));
			
			//build list
			for (int i = 1; i < rows.getLength()-2; i++)  
			{  
				Element row = (Element) rows.item(i);  
				try {
							
					PortfolioShare portfolioShareUpdated = addPortfolioShareFor(newPortfolio, newPortfolioName, row);
					if (newPortfolio == null) newPortfolio = ((UserPortfolio) portfolioShareUpdated.getPortfolio());
					newPortfolio.resetManualAlerts(portfolioShareUpdated, oldPortfolio);
					
				} catch (NoResultException e) {
					LOGGER.info(e);
					notFoundStocks.add(e.getMessage());
				}
			}
			
		} catch (FileNotFoundException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} catch (XPathExpressionException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} catch (DOMException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} catch (IOException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} catch (ParseException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} 
		
		return newPortfolio;

	}

	public String extractName(String filePath) {
		String portfolioName = (new File(filePath)).getName().replaceAll("\\..*","");
		return portfolioName;
	}

	private void checkTitles(Element row) throws XPathExpressionException {
		//Account	Symbol 	Listing Shares Price _ 
		//Basis(Total in (income + asset) - Money out + Realized Gain) 
		//Value(Nb shares x current price)
		//Money In Money (Sum money from other Asset account)
		//Money Out (Sum money to other Asset account)
		//Income (Sum from reinvested Income account)
		//Realized Gain (??)
		//Unrealized Gain (Value-Basis)
		//Total Gain (Money out - Money in + Value - Income) 
		//Total Return (Money out - Money in + Value) /Money in)
		//Brokerage Fees
		//vs
		//Stock stock; Date  buyDate; Float quantity; Float cashin; Float cashout; lastDayCloseValue;
	
		NodeList rowAtts = extractAtts(row,"th");
		if ("Account".equals(((Element) rowAtts.item(ACCOUNT_COLUMN)).getTextContent().trim()) && 
		"Symbol".equals(((Element) rowAtts.item(SYMBOL_COLUMN)).getTextContent().trim()) && 
		"Basis".equals(((Element) rowAtts.item(BASIS_COLUMN)).getTextContent().trim()) && 
		"Shares".equals(((Element) rowAtts.item(NBSHARES_COLUMN)).getTextContent().trim()) &&
		"Price".equals(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent().trim()) &&
		"Money In".equals(((Element) rowAtts.item(MONEYIN_COLUMN)).getTextContent().trim()) &&
		"Money Out".equals(((Element) rowAtts.item(MONEYOUT_COLUMN)).getTextContent().trim()) &&
		"Income".equals(((Element) rowAtts.item(INCOME_COLUMN)).getTextContent().trim()) &&
		"Realised Gain".equals(((Element) rowAtts.item(REALIZED_GAIN)).getTextContent().trim().replaceAll("EUR( |\n)*", "")))
			return;
			
		throw new RuntimeException("Invalid file or gnucash export");
	}

	/**
	 * @param newPortfolioName 
	 * @param row
	 * @return 
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	private PortfolioShare addPortfolioShareFor(UserPortfolio initOrUsedPorfolio, String newPortfolioName, Element row) throws XPathExpressionException, DOMException, ParseException {
	
		NodeList rowAtts = extractAtts(row,"td");
	
		String symbol = ((Element) rowAtts.item(SYMBOL_COLUMN)).getTextContent();
		String accountTestContent = ((Element) rowAtts.item(ACCOUNT_COLUMN)).getTextContent();
		
		String name = accountTestContent.replaceAll("( |\n)*", "");
		String account = accountTestContent.trim().replaceAll("[ \n]+","_");
		Stock stock;
		try {
			stock = getStockFor(symbol, name);
			LOGGER.info("Parsing row for "+stock);
			
			SortedSet<TransactionElement> transactionsForStock = PortfolioMgr.getInstance().getPortfolioDAO().loadTransactionReportFor(stock, account, EventSignalConfig.getNewDate());
			if (transactionsForStock.size() == 0) {
				throw new NoResultException(stock+" is in portoflio "+newPortfolioName+"\n" +
						" but no transaction was found in the gnucash transaction report regarding the former.\n" +
						"\tPlease check the gnucash transaction report.\n");
			}
			
			PortfolioShare updatedPortfolioShare = addPortfolioShare(initOrUsedPorfolio, newPortfolioName, rowAtts, stock, account, transactionsForStock);
			PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdateTransactionReports(new ArrayList<TransactionElement>(transactionsForStock));
		
			return updatedPortfolioShare;
			
		} catch (ParseException e) {
			throw new NoResultException(e.getMessage()+". For Name : "+name);
		} catch (NoResultException e) {
			//throw new NoResultException("Possible file feed : \n "+symbol+","+symbol+","+name+",TRACKERS,EURONEXT,investir .\n"+e.getMessage());
			throw e;
		} catch (InvalidAlgorithmParameterException e) {
			String message = e.getMessage()+". For Name : "+name;
			LOGGER.warn(message,e);
			throw new NoResultException(message);
		}
	
	}

	/**
	 * @param portfolio
	 * @param newPortfolioName 
	 * @param rowAtts
	 * @param stock
	 * @param account
	 * @param transactionsForStock
	 * @return
	 * @throws ParseException
	 * @throws InvalidAlgorithmParameterException
	 */
	private PortfolioShare addPortfolioShare(UserPortfolio portfolio, String newPortfolioName, NodeList rowAtts, Stock stock, String account, SortedSet<TransactionElement> transactionsForStock) 
																															throws ParseException, InvalidAlgorithmParameterException {
		Currency reportQuotationCurrency = gnuCashParserHelper.extractCurrency(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent());
		Currency portfolioReportCurrency = gnuCashParserHelper.extractCurrency(((Element) rowAtts.item(BASIS_COLUMN)).getTextContent());
		
//			BigDecimal quantity = gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(NBSHARES_COLUMN)).getTextContent());		
//			//Sum from other Asset account
//			BigDecimal cashin = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(MONEYIN_COLUMN)).getTextContent()),portfolioReportCurrency);
//			//Sum from Reinvested associated Income account
//			BigDecimal income = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(INCOME_COLUMN)).getTextContent()),portfolioReportCurrency);
//			//Sum to other Asset account
//			BigDecimal cashout = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(MONEYOUT_COLUMN)).getTextContent()),portfolioReportCurrency);
		
		BigDecimal calcCashin = BigDecimal.ZERO;
		BigDecimal calcCashout = BigDecimal.ZERO;
		BigDecimal quantity = BigDecimal.ZERO;
		for (TransactionElement transactionElement : transactionsForStock) {
			//Currency transactionCurrency = transactionElement.getCurrency();
			//Currency transactionCurrency = stock.getMarket().getCurrency();
			Currency transactionCurrency = reportQuotationCurrency;
			
			//Fix erroneous gnucash transactions
			transactionElement.setCurrency(transactionCurrency);
			//End fix
			
			BigDecimal price = currencyConverter.convert(transactionCurrency, portfolioReportCurrency, transactionElement.getPrice(), transactionElement.getDate());
			BigDecimal amount = price.multiply(transactionElement.getQuantity()).setScale(2, BigDecimal.ROUND_DOWN);
			if (amount.compareTo(BigDecimal.ZERO) > 0) {
				calcCashin = calcCashin.add(amount);
			} else {
				calcCashout = calcCashout.add(amount.abs());
			}
			quantity = quantity.add(transactionElement.getQuantity());
		}
		
		if (portfolio == null) portfolio = new UserPortfolio(newPortfolioName, portfolioReportCurrency);
		return portfolio.addOrUpdateShareWithNewAmounts(stock, account, quantity, EventSignalConfig.getNewDate(), calcCashin, calcCashout, MonitorLevel.ANY, portfolioReportCurrency);
	}


	private Stock getStockFor(String ref, String name) {
		
		try {
			Stock stock;
			if ((stock = shareDAO.loadShareByIsinOrSymbol(ref)) != null ) {
				return  stock;
			}
		} catch (DataAccessException e) {
			LOGGER.error("Inconsitencies in DB for "+ref,e);
		}
		
		LOGGER.info("The following stock was not found in db : "+ref+", "+name);
		String urls = "No quotation found in database for "+name+" "+ref+".\nPossible file feeds :\n";
		for (MarketQuotationProviders quotationsProvider : MarketQuotationProviders.values()) {

			try {
				String cmdParam = quotationsProvider.getCmdParam();
				Providers providerInstance = Providers.getInstance(cmdParam);
				MyUrl url = null;
				String feed = "%s,%s,%s,TRACKERS,%s,%s\n";
				String urlMsg = "Url : %s\n";
				if (providerInstance instanceof QuotationProvider) {
					try {
						for (Market market : Market.values()) {
							
							Stock stock = new Stock(ref, ref, name, true, 
									StockCategories.DEFAULT_CATEGORY, 
									new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOfCmd(cmdParam), SymbolNameResolver.UNKNOWNEXTENSIONCLUE), 
									market, 
									"",TradingMode.CONTINUOUS,0l);
							
							Date end = EventSignalConfig.getNewDate();
							url = ((QuotationProvider) providerInstance).resolveUrlFor(stock, new Date(end.getTime()-60*60*1000*24*5), end);
						
							List<Validatable> pageRes = null;
							try {
								pageRes = ((QuotationProvider) providerInstance).readPage(stock, url);
							} catch (Exception e) {
								
							}
							if (pageRes != null && !pageRes.isEmpty()) {
								urls = urls + "VALID -> ";
							}
							urls = urls + String.format(feed, ref,ref,name, market.name(), cmdParam);
							urls = urls + String.format(urlMsg, url.getUrl());	
						}
						
					} catch (Exception e1) {
						LOGGER.debug("No url can be resovled for "+ref+" "+name+" with "+providerInstance.getClass().getSimpleName());
					}

				}
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		throw new NoResultException(urls); 
		
		
	}

	/**
	 * @param inputStream
	 * @return
	 * @throws XPathExpressionException
	 */
	private NodeList extractRows(InputStream inputStream) throws XPathExpressionException {
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();  
		InputSource inputSource=new InputSource(inputStream);  
		String expression = "//tr";  
		
		NodeList rows = (NodeList) xPath.evaluate(expression, inputSource, XPathConstants.NODESET);
		return rows;
	}
	
	private NodeList extractAtts(Element row,String expression) throws XPathExpressionException {
		
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();   
		NodeList nodeList =  (NodeList) xPath.evaluate(expression, row, XPathConstants.NODESET);
		
		return nodeList;
	}

	/**
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private StringWriter deleteDocType(String filePath) throws FileNotFoundException, IOException {
		//"http://www.w3.org/TR/2000/REC-xhtml1-20000126/DTD/xhtml1-strict.dtd"
		//change html to xml
		String firstLine = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 TRANSITIONAL//EN\">";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringWriter outputWriter = new StringWriter();

		String line = null;

		//Read from the original file and write to the new
		//unless content matches data to be removed.
		while ((line = br.readLine()) != null) {

			if (!line.trim().equals(firstLine)) {
				outputWriter.write(line);
				outputWriter.flush();
			}
		}
		outputWriter.close();
		br.close();
		return outputWriter;
	}

	/**
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ByteArrayInputStream tidyDocument(InputStream inputStream) throws FileNotFoundException, IOException {
		Tidy tidy = new Tidy();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.setTrimEmptyElements(true);
		tidy.setMakeClean(true);
		tidy.setQuoteNbsp(true);
		tidy.setXmlOut(true);
		tidy.parseDOM(inputStream,outputStream);
		
		LOGGER.trace(outputStream.toString());
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

}
