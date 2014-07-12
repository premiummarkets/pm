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
package com.finance.pms;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;



// TODO: Auto-generated Javadoc
/**
 * The Class MainLauncher.
 * 
 * @author Guillaume Thoreton
 */
public class MainLauncher {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MainLauncher.class);

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException, IOException, InterruptedException {
		
		String lastListFetch = MainPMScmd.getMyPrefs().get("quotes.lastlistfetch", "1970/01/01");
		String lastFetch = MainPMScmd.getMyPrefs().get("quotes.lastfetch", "1970/01/01");
		String lastAnalyse = MainPMScmd.getMyPrefs().get("quotes.lastanalyse","1970/01/01");
		//String indiceList = MainPMScmd.prefs.get("quotes.listproviderindices", "FCHI:EURONEXT");
		
		//-cmd or -all
		String qmParam = args[0];
		String bdProps = args[1];
		
		if (qmParam.equals("all")) {
			
    		String shareListP =  args[2];
    		String quotationP =  args[3];
    		String monitoredOnlyQuotations = args[4];
    		String monitoredOnlyEvents = args[5];
			
			//DB ${projet_quotes}/db.properties euronext -quoteProvider yahoo -fileStocks|-monitoredOnly [/path/to/share/file] -majStocks
			String[] PMScmdArgrs = new String[9];
			PMScmdArgrs[0] = "DB";
			PMScmdArgrs[1] = bdProps;
			PMScmdArgrs[2] = shareListP; //"euronext";
			PMScmdArgrs[3] = "-quoteProvider";
			PMScmdArgrs[4] = quotationP; //"yahoo";
			//PMScmdArgrs[5] = "-fileStocks";
			PMScmdArgrs[5] = ("true".equals(monitoredOnlyQuotations))?"-monitoredOnly":"-fileStocks";
			PMScmdArgrs[6] = (args.length > 6)?args[6]:"./list_test.txt";
			
			String updateDate = new SimpleDateFormat("yyyy/MM/dd").format(EventSignalConfig.getNewDate());
			LOGGER.info("Last quotations update : "+lastFetch);
			if (lastFetch.compareTo(new SimpleDateFormat("yyyy/MM/dd").format(EventSignalConfig.getNewDate())) < 0) {
				LOGGER.info("Quotation needs update.");
				MainPMScmd.getMyPrefs().put("quotes.lastfetch", updateDate);
				PMScmdArgrs[7] = "-updateQuotations";
			}
			LOGGER.info("Last list of shares update : "+lastListFetch);
			if (lastListFetch.compareTo(new SimpleDateFormat("yyyy/MM/dd").format(EventSignalConfig.getNewDate())) < 0) {
				LOGGER.info("Share list needs update.");
				MainPMScmd.getMyPrefs().put("quotes.lastlistfetch", updateDate);
				PMScmdArgrs[8] = "-updateStocks";
			}
			
			if ("1970/01/01".equals(lastFetch) && !"true".equals(monitoredOnlyQuotations)) {
				LOGGER.info("You are using a sample data base! Please update from the UI -> Options -> Choose a Market");
			} else {
				MainPMScmd.main(PMScmdArgrs);
			}
			
//			//Screening
//			String[] screenerArgs = new String[4];
//			screenerArgs[0] = bdProps;
//			screenerArgs[1] = indiceList;
//			screenerArgs[2] = cmdDateFormat.format(getDateMinus3Months());
//			Screener.main(screenerArgs);
	
			//Analyse -default talib 
			String[] ICalargs = new String[5];
			ICalargs[0] = bdProps;
			ICalargs[1] = "-default";
			ICalargs[2] = "talib";	
			ICalargs[3] = ("true".equals(monitoredOnlyEvents))?"-updateMonitoredOnly":"";
			ICalargs[4] = "-autoPortfolio";
			
			LOGGER.info("Last analyse : "+lastAnalyse);
			if (lastAnalyse.compareTo(new SimpleDateFormat("yyyy/MM/dd").format(EventSignalConfig.getNewDate())) < 0) {
				LOGGER.info("Analyse needs update.");
				IndicatorCalculationServiceMain.main(ICalargs);
				MainPMScmd.getMyPrefs().put("quotes.lastanalyse", updateDate);
			}
		}
		
		if (qmParam.equals("cmd")) {
			
			//DB ${projet_quotes}/db.properties boursorama -quoteProvider yahoo -cmdStocks FTE IBM
			String datedeb =  args[2];
    		String datefin =  args[3];
    		
			String[] PMScmdArgrs = new String[args.length];
			PMScmdArgrs[0] = "DB";
			PMScmdArgrs[1] = bdProps;
			PMScmdArgrs[2] = "euronext";
			PMScmdArgrs[3] = "-quoteProvider";
			PMScmdArgrs[4] = "yahoo";
			PMScmdArgrs[5] = "-cmdStocks";
			for (int i = 6; i < args.length;i++) {
				PMScmdArgrs[i]=args[i];
			}
    		
			MainPMScmd.main(PMScmdArgrs);
			
			//talib -specificdates 2007/04/01 2007/07/01 fte paj fp
			String[] ICalargs = new String[args.length+1];
			ICalargs[0] = bdProps;
			ICalargs[1] = "talib";
			ICalargs[2] = "-specificdates";
			ICalargs[3] = datedeb;
			ICalargs[4] = datefin;
			for (int i=4; i < args.length;i++) {
				ICalargs[i+1]=args[i];
			}
			
			IndicatorCalculationServiceMain.main(ICalargs);
			
		}
	
	}

//	/**
//	 * @return
//	 */
//	private static Date getDateMinus3Months() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, -3);
//		return calendar.getTime();
//	}

}
