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
package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.portfolio.PortfolioMgr;

public class GnuCashTransactionReportParser {
	
	private static final int AMOUNT_COLUMN = 5; //Quantity + Stock Symbol
	private static final int PRICE_COLUMN = 4;	//Share unit price
	private static final int SHARES_COLUMN = 3; //Quantity
	private static final int ACCOUNT_COLUMN = 2;
	private static final int DESCRIPTION_COLUMN = 1;
	private static final int DATE_COLUMN = 0;

	protected static MyLogger LOGGER = MyLogger.getLogger(GnuCashTransactionReportParser.class);
	
	GnuCashParserHelper gnuCashParserHelper;
	private ArrayList<TransactionElement> reportElements;


	public GnuCashTransactionReportParser() {
		super();
		gnuCashParserHelper = new GnuCashParserHelper();
		reportElements = new ArrayList<TransactionElement>();
	}

	public void parse(String filePath) throws IOException {

		try {

			//Delete first line
			StringWriter outputWriter = gnuCashParserHelper.deleteDocType(filePath);

			//Tidy
			ByteArrayInputStream inputStream = tidyDocument(new StringInputStream(outputWriter.toString()));

			//LOG
			if (LOGGER.isDebugEnabled()) {
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
				TransactionElement reportElement = addReportElement(row);
				if (reportElement != null) {
					reportElements.add(reportElement);
				}
			}
			
			LOGGER.info("Finish parsing : "+reportElements);
			if (reportElements.size() == 0) throw new IOException("Invalid file.\nNo transaction found.");
			
			LOGGER.info("Storing transactions");
			for (TransactionElement transactionElement : reportElements) {
				PortfolioMgr.getInstance().getPortfolioDAO().deleteOrphanTransactionReportsFor(transactionElement.getExternalAccount());
			}
			PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdateTransactionReports(reportElements);
			LOGGER.info("Finished storing transactions");
			
		} catch (XPathExpressionException e) {
			LOGGER.error(e,e);
			throw new IOException("Invalid file.", e);
		} 

	}

	public String extractName(String filePath) {
		String portfolioName = (new File(filePath)).getName().replaceAll("\\..*","");
		return portfolioName;
	}

	private void checkTitles(Element row) throws XPathExpressionException {
		
//		<TH ALIGN="center">
//		<B>Date</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Description</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Account</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Shares</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Price</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Amount</B>
//		</TH>
	
		NodeList rowAtts = extractAtts(row, "th");
		Element dateItem = (Element) rowAtts.item(DATE_COLUMN);
		boolean hasDate = dateItem != null && "Date".equals(dateItem.getTextContent().trim());
		Element accountItem = (Element) rowAtts.item(ACCOUNT_COLUMN);
		boolean hasAccount = accountItem != null && "Account".equals(accountItem.getTextContent().trim());
		Element descrItem = (Element) rowAtts.item(DESCRIPTION_COLUMN);
		boolean hasDescription = descrItem != null && "Description".equals(descrItem.getTextContent().trim());
		Element sharesItem = (Element) rowAtts.item(SHARES_COLUMN);
		boolean hasShares = sharesItem != null && "Shares".equals(sharesItem.getTextContent().trim());
		Element priceItem = (Element) rowAtts.item(PRICE_COLUMN);
		boolean hasPrice = priceItem != null && "Price".equals(priceItem.getTextContent().trim());
		Element amountItem = (Element) rowAtts.item(AMOUNT_COLUMN);
		boolean hasAmount = amountItem != null && "Amount".equals(amountItem.getTextContent().trim());
		
		if (hasDate && hasAccount && hasDescription && hasShares && hasPrice && hasAmount) return;
			
		throw new RuntimeException(String.format("Invalid file or Gnucash report : hasDate %b, hasAccount %b, hasDescription %b, hasShares %b, hasPrice %b, hasAmount %b", hasDate, hasAccount, hasDescription, hasShares, hasPrice, hasAmount));
	}

	private TransactionElement addReportElement(Element row)  {

		try {
			
			NodeList rowAtts = extractAtts(row,"td");
			
			Date date;
			try {
				DateFormat sdf = new SimpleDateFormat(MainPMScmd.getPrefs().get("gnurepport.dateformat", "yyyy-MM-dd")); // SimpleDateFormat("dd/MM/yy");
				String dateContent = ((Element) rowAtts.item(DATE_COLUMN)).getTextContent();
				date = sdf.parse(dateContent.trim());
			} catch (ParseException e) {
				//Ignoring lines not starting with a date
				return null;
			}
			
			String[] accountPath = ((Element) rowAtts.item(ACCOUNT_COLUMN)).getTextContent().trim().split(":");
			String gnucashAccount = accountPath[accountPath.length-1].trim().replaceAll("[ \n]+","_");
			
			Currency transactionCurrency = gnuCashParserHelper.extractCurrency(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent());
			BigDecimal price = gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent());
			
			String[] amountString = ((Element) rowAtts.item(AMOUNT_COLUMN)).getTextContent().trim().split(" +");
			

			BigDecimal quantity;
			String symbol;
			if (amountString.length == 2) {
				try {
					quantity = gnuCashParserHelper.calculateBigDecimal(amountString[0].trim());
					symbol = amountString[1].trim();
				} catch (ParseException e) {
					//Ignoring lines that are not stocks transactions
					return null;
				}
			} else {
				//Ignoring lines that are not stocks transactions
				return null;
			}
			
			Stock stock = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(symbol);
			if (stock == null) {
				//throw new InvalidAlgorithmParameterException("No stock for symbol or isin : "+symbol+ ". In account "+gnucashAccount+" at "+date+" using "+transactionCurrency);
				LOGGER.warn("No stock for symbol or isin : "+symbol+ ". In account "+gnucashAccount+" at "+date+" using "+transactionCurrency);
				return null;
			}
		
			return new TransactionElement(stock, null, gnucashAccount, date, price, quantity, transactionCurrency);
			
		} catch (Exception e) { //Error
			LOGGER.error("Unparsable line :"+row.getTextContent()+" with error : "+e);
		}
		
		return null;
		
	}

	private NodeList extractRows(InputStream inputStream) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();  
		XPath xPath = factory.newXPath();  
		InputSource inputSource = new InputSource(inputStream);  
		String expression = "//tr";  
		
		NodeList rows = (NodeList) xPath.evaluate(expression, inputSource, XPathConstants.NODESET);
		return rows;
	}
	
	private NodeList extractAtts(Element row, String expression) throws XPathExpressionException {
		
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();   
		NodeList nodeList =  (NodeList) xPath.evaluate(expression, row, XPathConstants.NODESET);
		
		return nodeList;
	}

	private ByteArrayInputStream tidyDocument(InputStream inputStream) throws FileNotFoundException, IOException {
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

	public static void main(String... arg) throws IOException, InvalidAlgorithmParameterException {
		
		SpringContext springContext = new SpringContext(arg[0]);
		//springContext.setDataSource(arg[0]);
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		
		GnuCashTransactionReportParser cashTransactionReportParser = new GnuCashTransactionReportParser();
		cashTransactionReportParser.parse("/home/guil/Documents/Comptes/Gestion/PMS/transactionReport.html");
		
		Stock stock = new Stock("LU0294219869","LU0294219869","",true,
				StockCategories.DEFAULT_CATEGORY,EventSignalConfig.getNewDate(),
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				new MarketValuation(Market.PARIS),
				"",TradingMode.CONTINUOUS,0l);
		SortedSet<TransactionElement> fteReports = PortfolioMgr.getInstance().getPortfolioDAO().loadOrphanTransactionReportFor(stock, "TEMPLETON_GLOBAL_BOND", EventSignalConfig.getNewDate());
		StringBuffer printReportTransactions = cashTransactionReportParser.printReportTransactions(fteReports);
		System.out.println(printReportTransactions);
	}

	private StringBuffer printReportTransactions(SortedSet<TransactionElement> fteReports) {
		
		StringBuffer reportPrint = new StringBuffer("SortedSet<ReportElement> elements = new TreeSet<ReportElement>();\n");
		for (TransactionElement reportElement : fteReports) {
			//elements.add(new ReportElement("FR0010096354", simpleDateFormat.parse("11/07/04"), new BigDecimal(11.2894), new BigDecimal(246.6478)));
			reportPrint.append(reportElement.printTestElement());
		}
		
		return reportPrint;
	}

}
