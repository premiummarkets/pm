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
package com.finance.pms.portfolio;

import java.io.File;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.portfolio.PortfolioShare.InOutWeighted;
import com.finance.pms.threads.ConfigThreadLocal;

public class PortfolioShareOtherTest extends TestCase {
	
	PortfolioShare testObject;
	private Date currentDate;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
	
	@Before
	public void setUp() {
		
		SpringContext springContext = new SpringContext();
		springContext.setDataSource(System.getProperty("installdir") + File.separator +"db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();
		
		EventSignalConfig config = new EventSignalConfig();
		config.setExpectedRate(BigDecimal.ZERO);
		ConfigThreadLocal.set("eventSignal", config);
		
		try {
			currentDate = simpleDateFormat.parse("30/08/11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForPAJ() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("FR0010096354","PAJ.PA","",false,
				StockCategories.DEFAULT_CATEGORY,currentDate,
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
		
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("11/07/04"), new BigDecimal(11.2894), new BigDecimal(246.6478), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("30/03/05"), new BigDecimal(17.8729), new BigDecimal(-100), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("31/03/05"), new BigDecimal(30.9978), new BigDecimal(7.296), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("29/03/06"), new BigDecimal(23.31), new BigDecimal(66.843), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("01/05/06"), new BigDecimal(22.1622), new BigDecimal(10.1592), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("24/11/06"), new BigDecimal(14.79), new BigDecimal(140.4764), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("01/06/07"), new BigDecimal(15.69), new BigDecimal(25.2218), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("09/05/08"), new BigDecimal(12.1197), new BigDecimal(30.887), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("23/06/09"), new BigDecimal(6.96), new BigDecimal(58.7289), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0010096354", simpleDateFormat.parse("06/07/10"), new BigDecimal(7.99001), new BigDecimal(33.821), Currency.EUR));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(9455.16, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		//(Vin Weighted - Vin) / (Vin - Vout)
		BigDecimal realInv = new BigDecimal(8314.74);
		BigDecimal realOut = new BigDecimal(1787.28);
		BigDecimal distanceToAvgBuyPrice = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.17, distanceToAvgBuyPrice.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForFTE() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("FR0000133308","FTE.PA","",false,
				StockCategories.DEFAULT_CATEGORY,currentDate,
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
		
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement( stock, "FR0000133308",simpleDateFormat.parse("16/06/98"), new BigDecimal(17.91), new BigDecimal(10.619), Currency.EUR));
		elements.add(new TransactionElement( stock, "FR0000133308",simpleDateFormat.parse("09/11/98"), new BigDecimal(18.27), new BigDecimal(0.267), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("06/05/99"), new BigDecimal(21.92), new BigDecimal(41.6407), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("16/06/99"), new BigDecimal(22.13), new BigDecimal(11.8564), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("22/04/00"), new BigDecimal(48.54), new BigDecimal(17.1454), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("21/06/00"), new BigDecimal(46.37), new BigDecimal(5.6598), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("22/04/01"), new BigDecimal(23.69), new BigDecimal(39.9848), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("13/06/01"), new BigDecimal(17.92), new BigDecimal(18.5722), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("27/12/01"), new BigDecimal(14.26), new BigDecimal(0.5608), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/12/01"), new BigDecimal(44.57), new BigDecimal(45.9544), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/12/01"), new BigDecimal(14.00), new BigDecimal(-146.3061), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("13/01/02"), new BigDecimal(41.75), new BigDecimal(0.5394), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("02/06/02"), new BigDecimal(15.58), new BigDecimal(23.0917), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("27/06/02"), new BigDecimal(11.15), new BigDecimal(45.461), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("27/06/02"), new BigDecimal(9.81), new BigDecimal(84.7085), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("29/06/02"), new BigDecimal(15.68), new BigDecimal(52.9962), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/02"), new BigDecimal(15.68), new BigDecimal(121.9866), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/02"), new BigDecimal(15.68), new BigDecimal(110.5825), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/02"), new BigDecimal(15.68), new BigDecimal(29.2447), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/02"), new BigDecimal(15.68), new BigDecimal(43.1836), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("15/08/02"), new BigDecimal(15.58), new BigDecimal(4.6293), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/12/02"), new BigDecimal(17.00), new BigDecimal(17.5687), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.23), new BigDecimal(55.8053), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.22), new BigDecimal(2.1645), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.22), new BigDecimal(0.9414), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.23), new BigDecimal(2.2418), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.22), new BigDecimal(1.5323), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("24/03/03"), new BigDecimal(17.22), new BigDecimal(24.9799), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("29/06/03"), new BigDecimal(21.30), new BigDecimal(75.2418), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/03"), new BigDecimal(15.46), new BigDecimal(258.7323), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/03"), new BigDecimal(22.83), new BigDecimal(-258.7323), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/07/03"), new BigDecimal(22.62), new BigDecimal(261.1335), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("04/08/03"), new BigDecimal(21.74), new BigDecimal(-60), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("06/08/03"), new BigDecimal(21.74), new BigDecimal(-30), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("12/08/03"), new BigDecimal(22.62), new BigDecimal(-30), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("13/08/03"), new BigDecimal(22.27), new BigDecimal(-60), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("19/08/03"), new BigDecimal(22.91), new BigDecimal(-50), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("21/09/03"), new BigDecimal(22.92), new BigDecimal(-47.1447), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("23/10/03"), new BigDecimal(21.19), new BigDecimal(118.9311), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("18/12/03"), new BigDecimal(22.71), new BigDecimal(-845.7735), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("18/12/03"), new BigDecimal(22.92), new BigDecimal(837.9847), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("13/05/04"), new BigDecimal(18.99), new BigDecimal(127.7407), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("13/05/04"), new BigDecimal(18.99), new BigDecimal(10.6109), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("11/07/04"), new BigDecimal(20.25), new BigDecimal(-137.5065), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("12/12/04"), new BigDecimal(24.21), new BigDecimal(-421.5596), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("30/12/04"), new BigDecimal(11.44), new BigDecimal(466), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("29/03/05"), new BigDecimal(23.21), new BigDecimal(39.0327), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("02/06/05"), new BigDecimal(22.97), new BigDecimal(19.2664), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("26/09/05"), new BigDecimal(22.63), new BigDecimal(-17.5449), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("29/09/05"), new BigDecimal(24.23), new BigDecimal(3.1086), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("26/10/05"), new BigDecimal(19.79), new BigDecimal(66), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("09/05/06"), new BigDecimal(18.22), new BigDecimal(29.1948), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("11/05/06"), new BigDecimal(18.22), new BigDecimal(25.3271), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("22/05/07"), new BigDecimal(21.38), new BigDecimal(-127.8429), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("10/06/07"), new BigDecimal(21.60), new BigDecimal(51.0521), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("28/01/08"), new BigDecimal(25.07), new BigDecimal(81), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("05/06/08"), new BigDecimal(19.66), new BigDecimal(4.6703), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("23/07/08"), new BigDecimal(19.66), new BigDecimal(66), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("23/12/08"), new BigDecimal(19.42), new BigDecimal(47.9593), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("01/07/09"), new BigDecimal(16.49), new BigDecimal(56.8196), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("31/08/09"), new BigDecimal(18.23), new BigDecimal(40.3944), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("19/06/10"), new BigDecimal(15.30), new BigDecimal(66.2856), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0000133308",simpleDateFormat.parse("06/09/10"), new BigDecimal(15.91), new BigDecimal(50.3074), Currency.EUR));
		
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(82966.82, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		BigDecimal realInv = new BigDecimal(68092.33);
		BigDecimal realOut = new BigDecimal(49485.98);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.8, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForIGLT() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("IGLT.L","IGLT.L","",false,
				StockCategories.DEFAULT_CATEGORY,currentDate,
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.GBP);
		
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement( stock,"IGLT.L",simpleDateFormat.parse("12/07/10"), new BigDecimal(1065.20), new BigDecimal(760), Currency.GBP));
		elements.add(new TransactionElement( stock,"IGLT.L",simpleDateFormat.parse("26/08/10"), new BigDecimal(1087.57), new BigDecimal(480), Currency.GBP));
		elements.add(new TransactionElement( stock,"IGLT.L",simpleDateFormat.parse("15/08/11"), new BigDecimal(1099.2), new BigDecimal(-270), Currency.GBP));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(1381402.23, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		BigDecimal realInv = new BigDecimal(1331588.00);
		BigDecimal realOut = new BigDecimal(296784.00);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.05, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForHorizon() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("FR0010091173","FR0010091173","",false,
				StockCategories.DEFAULT_CATEGORY,currentDate,
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
	
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement( stock,"FR0010091173",simpleDateFormat.parse("30/12/96"), new BigDecimal(356.34), new BigDecimal(57), Currency.EUR));
		elements.add(new TransactionElement( stock,"FR0010091173",simpleDateFormat.parse("11/06/07"), new BigDecimal(538.26), new BigDecimal(-20), Currency.EUR));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(28921.35, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		BigDecimal realInv = new BigDecimal(20311.75);
		BigDecimal realOut = new BigDecimal(10765.39);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.9, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForCRUDOil() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("GB00B0CTWC01","OILB.L","",false,
				StockCategories.DEFAULT_CATEGORY,new Date(),
				new SymbolMarketQuotationProvider(MarketQuotationProviders.INVESTIR,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.EURONEXT,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
	
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement(stock, "ETF_Securities_Brent_Oil", simpleDateFormat.parse("09/03/11"), new BigDecimal(63.28), new BigDecimal(129.00000), Currency.EUR));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(501535.56, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		//BigDecimal realInv = new BigDecimal(501400.00);
		BigDecimal realInv = new BigDecimal(8163.12);
		BigDecimal realOut = new BigDecimal(0);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(0.0, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForBPCE() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("FR0000188625","FR0000188625","",false,
				StockCategories.DEFAULT_CATEGORY,new Date(),
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
	
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement(stock, "BPCE_5.20%02-14_TSR(Euronext)", simpleDateFormat.parse("26/09/02"), new BigDecimal(103.26), new BigDecimal(50.00000), Currency.EUR));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(6451.16, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		BigDecimal realInv = new BigDecimal(5163.00);
		BigDecimal realOut = new BigDecimal(0);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.25, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
	
	@Test
	public void testInflationAndExpectationWeightedQuantityForTemple() throws ParseException, InvalidAlgorithmParameterException {
		
		Stock stock = new Stock("LU0294219869","LU0294219869","",false,
				StockCategories.DEFAULT_CATEGORY,new Date(),
				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				Market.PARIS,
				"",TradingMode.CONTINUOUS,0l);
		
		testObject = new PortfolioShare(null, stock, new Date(0), MonitorLevel.NONE, Currency.EUR);
	
		SortedSet<TransactionElement> elements = new TreeSet<TransactionElement>();
		elements.add(new TransactionElement(stock, "TEMPLETON_GLOBAL_BOND", simpleDateFormat.parse("01/05/10"), new BigDecimal(17.72), new BigDecimal(1410.63000), Currency.EUR));
		
		InOutWeighted weightedInvestedValue = testObject.new InOutWeighted(new Date());
		try {
			weightedInvestedValue = testObject.calculateInflationAndExpectationWeightedInvestedCash(currentDate, elements);
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assertEquals(25953.57, weightedInvestedValue.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		BigDecimal realInv = new BigDecimal(25000);
		BigDecimal realOut = new BigDecimal(0);
		BigDecimal distToAvgBuy = realInv.subtract(weightedInvestedValue.getIn()).divide(realInv.subtract(realOut),2,BigDecimal.ROUND_DOWN);
		assertEquals(-0.03, distToAvgBuy.setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
		
		
	}
}
