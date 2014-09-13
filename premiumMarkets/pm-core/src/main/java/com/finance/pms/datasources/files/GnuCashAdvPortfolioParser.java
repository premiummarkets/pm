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
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.xml.sax.InputSource;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;

public class GnuCashAdvPortfolioParser {
	
//	private static final int REALIZED_GAIN = 10;
//	private static final int INCOME_COLUMN = 9;
//	private static final int MONEYOUT_COLUMN = 8;
//	private static final int MONEYIN_COLUMN = 7;
//	@SuppressWarnings("unused") private static final int VALUE_COLUMN = 6;
//	private static final int BASIS_COLUMN = 5;
//	@SuppressWarnings("unused") private static final int UNKNOWN_COLUMN = 4;
//	private static final int PRICE_COLUMN = 3;
//	private static final int NBSHARES_COLUMN = 2;
//	private static final int SYMBOL_COLUMN = 1;
//	private static final int ACCOUNT_COLUMN = 0;
	
	private static final String[] expectedTitles = new String[]{"Account", "Symbol", "Basis", "Shares", "Price", "Money In", "Money Out", "Income", "Realized Gain"};
	@SuppressWarnings("unused") private static final int REALIZED_GAIN = 8;
	@SuppressWarnings("unused") private static final int INCOME_COLUMN = 7;
	@SuppressWarnings("unused") private static final int MONEYOUT_COLUMN = 6;
	@SuppressWarnings("unused") private static final int MONEYIN_COLUMN = 5;
	private static final int PRICE_COLUMN = 4;
	@SuppressWarnings("unused") private static final int NBSHARES_COLUMN = 3;
	private static final int BASIS_COLUMN = 2;
	private static final int SYMBOL_COLUMN = 1;
	private static final int ACCOUNT_COLUMN = 0;

	private static MyLogger LOGGER = MyLogger.getLogger(GnuCashAdvPortfolioParser.class);
	

	private Map<String, Integer> titlesPositions = new HashMap<String, Integer>();
	{
		for (String expTitle : expectedTitles) {
			titlesPositions.put(expTitle, -1);
		}
	}
	
	ShareDAO shareDAO;
	CurrencyConverter currencyConverter;
	GnuCashParserHelper gnuCashParserHelper;
	
	private List<String> notFoundStocks;
	

	public List<String> getNotFoundStocks() {
		return notFoundStocks;
	}

	public GnuCashAdvPortfolioParser(ShareDAO shareDAO, CurrencyConverter currencyConverter) {
		super();
		this.shareDAO = shareDAO;
		this.currencyConverter = currencyConverter;
		this.gnuCashParserHelper = new GnuCashParserHelper();
		this.notFoundStocks = new ArrayList<String>();
	}

	public Portfolio parse(String filePath, String newPortfolioName, UserPortfolio oldPortfolio) throws NoResultException {
		
		//UserPortfolio newPortfolio = new UserPortfolio(newPortfolioName, null);
		UserPortfolio newPortfolio = null;
		
		try {
			
			//Delete first line
			StringWriter outputWriter = gnuCashParserHelper.deleteDocType(filePath);
			
			//Tidy
			ByteArrayInputStream inputStream = gnuCashParserHelper.tidyDocument(new StringInputStream(outputWriter.toString()));
			
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
			
		} catch (Exception exception) {
			LOGGER.error(exception, exception);
			throw new NoResultException("Invalid file : "+filePath+ ". "+exception);
		}
		
		if (newPortfolio == null) {
			throw new NoResultException("Could not instantiate Portfolio.");
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
	
		NodeList thNodes = extractNodes(row,"th");
//		if ("Account".equals(((Element) thNodes.item(ACCOUNT_COLUMN)).getTextContent().trim()) && 
//		"Symbol".equals(((Element) thNodes.item(SYMBOL_COLUMN)).getTextContent().trim()) && 
//		"Basis".equals(((Element) thNodes.item(BASIS_COLUMN)).getTextContent().trim()) && 
//		"Shares".equals(((Element) thNodes.item(NBSHARES_COLUMN)).getTextContent().trim()) &&
//		"Price".equals(((Element) thNodes.item(PRICE_COLUMN)).getTextContent().trim()) &&
//		"Money In".equals(((Element) thNodes.item(MONEYIN_COLUMN)).getTextContent().trim()) &&
//		"Money Out".equals(((Element) thNodes.item(MONEYOUT_COLUMN)).getTextContent().trim()) &&
//		"Income".equals(((Element) thNodes.item(INCOME_COLUMN)).getTextContent().trim()) &&
//		(((Element) thNodes.item(REALIZED_GAIN)).getTextContent().trim().replaceAll("EUR( |\n)*", "")).matches("Reali.ed Gain"))
//			return;
		
 		for (int i = 0; i < thNodes.getLength(); i++) {
			for (String expectedTitle : expectedTitles) {
				if (expectedTitle.equals(((Element) thNodes.item(i)).getTextContent().trim())) {
					titlesPositions.put(expectedTitle, i);
				}
			}
		}
 		
 		List<String> unfoundTitles = new ArrayList<String>();
 		for (String expceteTitle : expectedTitles) {
			Integer titlePosition = titlesPositions.get(expceteTitle);
			if (titlePosition == -1) {
				unfoundTitles.add(expceteTitle);
			}
		}
 		
 		LOGGER.info("Titles found : "+titlesPositions);
			
		if (!unfoundTitles.isEmpty())throw new RuntimeException("Invalid file or GNUCASH export. Missing columns : "+unfoundTitles);
	}

	private PortfolioShare addPortfolioShareFor(UserPortfolio initOrUsedPorfolio, String newPortfolioName, Element row) throws XPathExpressionException, DOMException, ParseException, NoResultException {
	
		NodeList portfolioNodes = extractNodes(row,"td");
	
		String symbolContent = ((Element) portfolioNodes.item(columnPositionFor(SYMBOL_COLUMN))).getTextContent();
		String accountTestContent = ((Element) portfolioNodes.item(columnPositionFor(ACCOUNT_COLUMN))).getTextContent();
		
		String symbol = symbolContent.trim().replaceAll("( |\n)+", "");
		String name = accountTestContent.replaceAll("[ \n]+"," ").trim();
		String account = accountTestContent.trim().replaceAll("[ \n]+","_");
		Stock stock;
		try {
			stock = getStockFor(symbol, name);
			LOGGER.info("Parsing row for "+stock);
			
			SortedSet<TransactionElement> transactionsForStock = PortfolioMgr.getInstance().getPortfolioDAO().loadOrphanTransactionReportFor(stock, account, EventSignalConfig.getNewDate());
			if (transactionsForStock.size() == 0) {
				throw new NoResultException(
						""+stock+"\n is in Portfolio "+newPortfolioName +" but no transaction was found in the database regarding the former.\n" +
						"\tPlease check and update the GNUCASH transaction report.\n");
			}
			
			PortfolioShare updatedPortfolioShare = addPortfolioShare(initOrUsedPorfolio, newPortfolioName, portfolioNodes, stock, account, transactionsForStock);
			//PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdateTransactionReports(new ArrayList<TransactionElement>(transactionsForStock));
		
			return updatedPortfolioShare;
			
		} catch (ParseException e) {
			throw new NoResultException(e.getMessage()+". For Name : "+name);
		} catch (NoResultException e) {
			throw e;
		} catch (InvalidAlgorithmParameterException e) {
			String message = e.getMessage()+". For Name : "+name;
			LOGGER.warn(message,e);
			throw new NoResultException(message);
		}
	
	}

	protected Integer columnPositionFor(int columnRefIdx) {
		return titlesPositions.get(expectedTitles[columnRefIdx]);
	}

	private PortfolioShare addPortfolioShare(UserPortfolio portfolio, String newPortfolioName, NodeList portfolioNodes, Stock stock, String account, SortedSet<TransactionElement> transactionsForStock) throws ParseException, InvalidAlgorithmParameterException {
		
		Currency reportQuotationCurrency = gnuCashParserHelper.extractCurrency(((Element) portfolioNodes.item(columnPositionFor(PRICE_COLUMN))).getTextContent());
		Currency portfolioReportCurrency = gnuCashParserHelper.extractCurrency(((Element) portfolioNodes.item(columnPositionFor(BASIS_COLUMN))).getTextContent());
		
		//Debug
//			BigDecimal quantity = gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(NBSHARES_COLUMN)).getTextContent());		
//			//Sum from other Asset account
//			BigDecimal cashin = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(MONEYIN_COLUMN)).getTextContent()),portfolioReportCurrency);
//			//Sum from Reinvested associated Income account
//			BigDecimal income = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(INCOME_COLUMN)).getTextContent()),portfolioReportCurrency);
//			//Sum to other Asset account
//			BigDecimal cashout = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(MONEYOUT_COLUMN)).getTextContent()),portfolioReportCurrency);
		//End Debug
		
//		BigDecimal calcCashin = BigDecimal.ZERO;
//		BigDecimal calcCashout = BigDecimal.ZERO;
//		BigDecimal quantity = BigDecimal.ZERO;
		for (TransactionElement transactionElement : transactionsForStock) {
			Currency transactionCurrency = reportQuotationCurrency;

			//Fix erroneous GNUCASH transactions currency
			transactionElement.setCurrency(transactionCurrency);
			//End fix
			
//			BigDecimal price = currencyConverter.convert(transactionCurrency, portfolioReportCurrency, transactionElement.getPrice(), transactionElement.getDate());
//			BigDecimal amount = price.multiply(transactionElement.getQuantity()).setScale(10, BigDecimal.ROUND_HALF_EVEN);
//			if (amount.compareTo(BigDecimal.ZERO) > 0) {
//				calcCashin = calcCashin.add(amount);
//			} else {
//				calcCashout = calcCashout.add(amount.abs());
//			}
//			quantity = quantity.add(transactionElement.getQuantity());
		}
		
		if (portfolio == null) portfolio = new UserPortfolio(newPortfolioName, portfolioReportCurrency);
		for (TransactionElement transactionElement : transactionsForStock) {
			portfolio.rawAddTransaction(transactionElement);
		}
		return portfolio.addOrUpdateShareWithoutTransaction(stock, account, portfolioReportCurrency, EventSignalConfig.getNewDate());
		
	}


	private Stock getStockFor(String ref, String name) {
		
		try {
			Stock matchingStock = gnuCashParserHelper.findMatchingStock(ref);
			if (matchingStock != null) return matchingStock;
		} catch (DataAccessException e) {
			LOGGER.error("Inconsistencies in database for "+ref, e);
		}
		
		LOGGER.info("The following stock was not found in the database. Reference : "+ref+", name : "+name);
		
		String urls = "No stock found in the database for "+name+"/"+ref+".\n";
//		
//		if (ref.startsWith("QS")) {
//			urls = urls + ref +" is a temporary code.\n";
//		} else {
//			urls = urls + "Possible feeds :\n";
//			for (MarketQuotationProviders quotationsProvider : MarketQuotationProviders.values()) {
//	
//				try {
//					String cmdParam = quotationsProvider.getCmdParam();
//					Providers providerInstance = Providers.getInstance(cmdParam);
//					MyUrl url = null;
//					String feed = "%s,%s,%s,TRACKERS,%s,%s\n";
//					String urlMsg = "Url : %s\n";
//					if (providerInstance instanceof QuotationProvider) {
//						try {
//							for (Market market : Market.values()) {
//								
//								Stock stock = new Stock(ref, ref, name, true, 
//										StockCategories.DEFAULT_CATEGORY, 
//										new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOfCmd(cmdParam), SymbolNameResolver.UNKNOWNEXTENSIONCLUE), 
//										new MarketValuation(market), 
//										"",TradingMode.CONTINUOUS,0l);
//								
//								Date end = EventSignalConfig.getNewDate();
//								Date fiveCalDaysBeforeEnd = new Date(end.getTime()-60*60*1000*24*5);
//								url = ((QuotationProvider) providerInstance).resolveUrlFor(stock, fiveCalDaysBeforeEnd, end);
//							
//								if (url != null) {
//									List<Validatable> pageRes = null;
//									try {
//										pageRes = ((QuotationProvider) providerInstance).readPage(stock, url, fiveCalDaysBeforeEnd);
//									} catch (Exception e) {
//										LOGGER.info("Trying "+url.getUrl()+"\n\t gives : "+e);
//									}
//									if (pageRes != null && !pageRes.isEmpty()) {
//										urls = urls + String.format(feed, ref,ref,name, market.name(), cmdParam);
//										urls = urls + String.format(urlMsg, url.getUrl());	
//									}
//								}
//								
//							}
//							
//						} catch (Exception e1) {
//							LOGGER.debug("No url can be resolved for "+ref+" "+name+" with "+providerInstance.getClass().getSimpleName());
//						}
//	
//					}
//				} catch (Exception e) {
//					LOGGER.error(e);
//				}
//			}
//		}

		throw new NoResultException(urls);
		
	}

	private NodeList extractRows(InputStream inputStream) throws XPathExpressionException {
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();  
		InputSource inputSource=new InputSource(inputStream);  
		String expression = "//tr";  
		
		NodeList rows = (NodeList) xPath.evaluate(expression, inputSource, XPathConstants.NODESET);
		return rows;
	}
	
	private NodeList extractNodes(Element row,String expression) throws XPathExpressionException {
		
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();   
		NodeList nodeList =  (NodeList) xPath.evaluate(expression, row, XPathConstants.NODESET);
		
		return nodeList;
	}


}
