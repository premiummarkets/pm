/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
package com.finance.pms.portfolio.gui.charts;

import javax.swing.JFrame;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ChartWindow.
 * 
 * @author Guillaume Thoreton
 */
public class ChartWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3443467111308599193L;
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(ChartWindow.class);
	
	
	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		
		String dbfile = null;
		
		switch (args.length) {
		case 1:
			dbfile = args[0];
			break;
		default:
			LOGGER.info("Usage : MainGui <path>/db.properties");
			System.exit(0);
		}
		SpringContext ctx = new SpringContext(dbfile);
		//ctx.setDataSource(dbfile);
		//ctx.setProvidersSource(dbfile);
		ctx.setMasSource(dbfile, "false");
		ctx.loadBeans(new String[] { "/connexions.xml", "/swtclients.xml", "talibanalysisservices.xml","masanalysisservices.xml" });
		ctx.refresh();
		
		//"FTE.PA";"FRANCE TELECOM";"FR0000133308";"ALL";1;"2008-04-25";"YAHOO  
//		Stock s1 =new Stock("FR0000133308","FTE.PA",StockCategories.DEFAULT_CATEGORY,
//				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
//				Market.EURONEXT);
//		PortfolioShare ps1 = new PortfolioShare(s1.getSymbol(),10f,new Date(),220f,0f,MonitorLevel.values()[0]);
//		//PAJ.PA";"PAGESJAUNES";"FR0010096354";"ALL";1;"2008-04-25";"YAHOO 
//		Stock s2 =new Stock("FR0010096354","PAJ.PA",StockCategories.DEFAULT_CATEGORY,
//				new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
//				Market.EURONEXT);
//		PortfolioShare ps2 = new PortfolioShare(s2.getSymbol(),10f,new Date(),140f,0f,MonitorLevel.values()[0]);
//		ArrayList<PortfolioShare> listS = new ArrayList<PortfolioShare>();
//		listS.add(ps1);
//		listS.add(ps2);
		//QuotationData lqs = new QuotationData(DataSource.getInstance().loadQuotations(listS));
		
//		Charts chart = new Charts(listS,IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),200),JFreeChartTimePeriod.DAY);
		//FIXME
//		JFreeChart freeChart = chart.initChart();
//		
//		ChartWindow chartWindow = new ChartWindow();
//		ChartPanel  cPanel = new ChartPanel(freeChart);
//		chartWindow.getContentPane().add(cPanel);
//		chartWindow.setSize(1400, 900);
//		chartWindow.setVisible(true);
		
		
	}
}
