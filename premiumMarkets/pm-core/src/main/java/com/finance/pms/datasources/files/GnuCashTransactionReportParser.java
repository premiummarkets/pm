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
import org.w3c.dom.DOMException;
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
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.portfolio.PortfolioMgr;

public class GnuCashTransactionReportParser {
	
	private static final int AMOUNT_COLUMN = 5;
	private static final int PRICE_COLUMN = 4;
	private static final int SHARES_COLUMN = 3;
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
			StringWriter outputWriter = deleteDocType(filePath);

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
			
			System.out.println(reportElements);
			PortfolioMgr.getInstance().getPortfolioDAO().deleteTransactionReports();
			PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdateTransactionReports(reportElements);

		} catch (XPathExpressionException e) {
			throw new IOException("Invalid file "+e.getMessage(),e);
		} 

	}

	public String extractName(String filePath) {
		String portfolioName = (new File(filePath)).getName().replaceAll("\\..*","");
		return portfolioName;
	}

	private void checkTitles(Element row) throws XPathExpressionException {
//		<B>Date</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Num</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Description</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Memo/Notes</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Shares</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Price</B>
//		</TH>
//		<TH ALIGN="center">
//		<B>Amount</B>
	
		NodeList rowAtts = extractAtts(row,"th");
		if ("Date".equals(((Element) rowAtts.item(DATE_COLUMN)).getTextContent().trim()) && 
		"Account".equals(((Element) rowAtts.item(ACCOUNT_COLUMN)).getTextContent().trim()) && 
		"Description".equals(((Element) rowAtts.item(DESCRIPTION_COLUMN)).getTextContent().trim()) &&
		"Shares".equals(((Element) rowAtts.item(SHARES_COLUMN)).getTextContent().trim()) &&
		"Price".equals(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent().trim()) &&
		"Amount".equals(((Element) rowAtts.item(AMOUNT_COLUMN)).getTextContent().trim()))
			return;
			
		throw new RuntimeException("Invalid file or gnucash export");
	}

	/**
	 * @param row
	 * @return 
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	private TransactionElement addReportElement(Element row)  {

		try {
			
			NodeList rowAtts = extractAtts(row,"td");
			
			DateFormat sdf = new SimpleDateFormat(MainPMScmd.getPrefs().get("gnurepport.dateformat", "yyyy-MM-dd")); // SimpleDateFormat("dd/MM/yy");
			Date date = sdf.parse(((Element) rowAtts.item(DATE_COLUMN)).getTextContent().trim());
			
			String[] accountPath = ((Element) rowAtts.item(ACCOUNT_COLUMN)).getTextContent().trim().split(":");
			String account = accountPath[accountPath.length-1].replaceAll("[ \n]+","_");
			
			Currency transactionCurrency = gnuCashParserHelper.extractCurrency(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent());
			BigDecimal price = gnuCashParserHelper.unitConvertion(gnuCashParserHelper.calculateBigDecimal(((Element) rowAtts.item(PRICE_COLUMN)).getTextContent()),transactionCurrency);
			
			String[] amountString = ((Element) rowAtts.item(AMOUNT_COLUMN)).getTextContent().trim().split(" +");
			
			BigDecimal amount =  gnuCashParserHelper.calculateBigDecimal(amountString[0].trim());
			String symbol = amountString[1].trim();
			Stock stock = DataSource.getInstance().getShareDAO().loadShareByIsinOrSymbol(symbol);
			
			if (stock == null) {
				LOGGER.warn("No stock for symbol or isin : "+symbol);
				throw new InvalidAlgorithmParameterException("No stock for symbol or isin : "+symbol);
			}
		
			return new TransactionElement(stock, account, date, price, amount, transactionCurrency);
			
		} catch (Exception e) { //ignore
			//System.out.println(row.getTextContent());
		}
		
		return null;
		
	}

	/**
	 * @param inputStream
	 * @return
	 * @throws XPathExpressionException
	 */
	private NodeList extractRows(InputStream inputStream) throws XPathExpressionException {
		XPathFactory factory=XPathFactory.newInstance();  
		XPath xPath=factory.newXPath();  
		InputSource inputSource = new InputSource(inputStream);  
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

	public static void main(String... arg) throws IOException, InvalidAlgorithmParameterException {
		
		SpringContext springContext = new SpringContext();
		springContext.setDataSource(arg[0]);
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		
		GnuCashTransactionReportParser cashTransactionReportParser = new GnuCashTransactionReportParser();
		cashTransactionReportParser.parse("/home/guil/Documents/Comptes/Gestion/PMS/transactionReport.html");
		
		Stock stock = new Stock("LU0294219869","LU0294219869","",false,
				StockCategories.DEFAULT_CATEGORY,EventSignalConfig.getNewDate(),
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		SortedSet<TransactionElement> fteReports = PortfolioMgr.getInstance().getPortfolioDAO().loadTransactionReportFor(stock, "TEMPLETON_GLOBAL_BOND", EventSignalConfig.getNewDate());
		StringBuffer printReportTransactions = cashTransactionReportParser.printReportTransactions(fteReports);
		System.out.println(printReportTransactions);
	}

	/**
	 * @param fteReports
	 * @return 
	 */
	private StringBuffer printReportTransactions(SortedSet<TransactionElement> fteReports) {
		
		StringBuffer reportPrint = new StringBuffer("SortedSet<ReportElement> elements = new TreeSet<ReportElement>();\n");
		for (TransactionElement reportElement : fteReports) {
			//elements.add(new ReportElement("FR0010096354", simpleDateFormat.parse("11/07/04"), new BigDecimal(11.2894), new BigDecimal(246.6478)));
			reportPrint.append(reportElement.printTestElement());
		}
		
		return reportPrint;
	}

}
