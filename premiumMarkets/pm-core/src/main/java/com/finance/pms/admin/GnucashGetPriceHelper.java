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
package com.finance.pms.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.files.GnuCashAdvPortfolioParser;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.threads.ConfigThreadLocal;

public class GnucashGetPriceHelper {
	
	public Set<StockWraper> getCmdShareList(List<String> symbols) {
		Set<StockWraper> ret = new HashSet<StockWraper>();
		
		for (final String symbol : symbols) {
			
			ret.add(new StockWraper() {

				public String getReference() {
					return symbol;
				}

				public String getName() {
					return symbol;
				}
				
				@Override
				public String toString() {
					return this.getReference();
				}
				
			});
		}
		
		return ret;
	}
	
	public Set<StockWraper> getMonitoredShareList() {
		Set<StockWraper> ret = new HashSet<StockWraper>();
		
		List<UserPortfolio> visiblePortfolios = PortfolioMgr.getInstance().getUserPortfolios();
		for (Portfolio portfolio : visiblePortfolios) {
			for (final PortfolioShare pShares : portfolio.getListShares().values()) {
				if (PortfolioMgr.getInstance().isMonitoredForAlerts(pShares.getStock(), portfolio.getName(), null))
				ret.add(new StockWraper() {

					public String getReference() {
						return pShares.getStock().getIsin();
					}

					public String getName() {
						return pShares.getStock().getName();
					}
					
					@Override
					public String toString() {
						return this.getReference();
					}

				});
			}
		}
		
		return ret;
	}
	
	public Set<StockWraper> getGnucashExportsShareList(List<String> filePaths) throws IOException {
		Set<StockWraper> ret = new HashSet<StockWraper>();
		
		GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser = new GnuCashAdvPortfolioParser(DataSource.getInstance().getShareDAO(), PortfolioMgr.getInstance().getCurrencyConverter());
		
		for (String filePath : filePaths) {
			
			Portfolio tmpPortfolio = gnuCashAdvPortfolioParser.parse(filePath, "tmp", null);
			
			for (final PortfolioShare pShares : tmpPortfolio.getListShares().values()) {
				ret.add(new StockWraper() {
					
					public String getReference() {
						String symbol = pShares.getStock().getSymbolRoot();
						String marketExtention = pShares.getStock().getMarketValuation().getMarket().getYahooExtension().getSpecificMarketExtension();
						String ext = "";
						if (!marketExtention.isEmpty() && !isIsin(symbol)) {
							ext = "." + marketExtention;
						}
						return symbol + ext;
					}

					public String getName() {
						return pShares.getStock().getName();
					}
					
					@Override
					public String toString() {
						return this.getReference();
					}
					
				});
			}
		}
		
		return ret;
	}
	
	public Boolean isIsin(String symbol) {
		//FR0000188625
		return symbol.matches("[A-Z]{2}[0-9]{10}");
	}
	
	public List<String> runGNCCheck() throws IOException, InterruptedException {
		
		ProcessBuilder gncFqCheck = new ProcessBuilder("/usr/local/bin/gnc-fq-check");
		Process gncFqCheckProcess = gncFqCheck.start();
		gncFqCheck.redirectErrorStream(true);
		gncFqCheckProcess.waitFor();
		int exitValue = gncFqCheckProcess.exitValue();

		
		if (exitValue == 0) {
			InputStream inputStream = gncFqCheckProcess.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			String[] gncFqCheckRes = new String[1];
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				gncFqCheckRes = line.replaceAll("[()\\\"]", "").split(" ");
			}
			
			System.out.println("Finance quote version :"+ gncFqCheckRes[0]);
			return Arrays.asList(Arrays.copyOfRange(gncFqCheckRes, 1, gncFqCheckRes.length));
		}
		
		return null;
	}
	
	public Map<StockWraper,Map<String,String>> checkPriceProvider(List<String> providers, Set<StockWraper> stocks) throws IOException, InterruptedException {
		
		Map<StockWraper,Map<String,String>> ret = new HashMap<StockWraper, Map<String,String>>();

		Process gncFqHelperProcess = initHelperProcess();
		OutputStream outputStream = gncFqHelperProcess.getOutputStream();
		OutputStreamWriter outWriter = new OutputStreamWriter(outputStream);

		for (StockWraper stock : stocks) {
		//Stock stock = stocks.get(0);
		
			HashMap<String,String> hashMap = new HashMap<String,String>();
			ret.put(stock, hashMap);
			String stockReference = stock.getReference();
			for (String provider : providers) {

				//(yahoo "CSCO")
				String input = "("+provider+" \""+stockReference+"\")\n";

				outWriter.write("("+provider+" ");
				outWriter.write('"');
				outWriter.write(stockReference);
				outWriter.write('"');
				outWriter.write(")\n");
				outWriter.flush();


				InputStream inputStream = gncFqHelperProcess.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				//String line = null;

				while(!inputStreamReader.ready()) {
					//System.out.println("Not ready!");
					Thread.sleep(10);
				}
				
				//System.out.println("Pipe is ready");
				System.out.println(input.replace("\n", "")+" : ");
				char[] singleChar = new char[1];
				String output = "";
				while(singleChar[0] != '\n') {
					inputStreamReader.read(singleChar);
					System.out.print(singleChar);
					output = output + singleChar[0];
				}
				hashMap.put(provider, output.replace("\n", ""));
				
				try {
					Thread.sleep(200);
					int exitValue = gncFqHelperProcess.exitValue();
					System.out.println("Exit value :"+exitValue);
				} catch (Exception e) {
					continue;
				}

				System.out.println("Oops. Re Init");
				gncFqHelperProcess = initHelperProcess();
				outputStream = gncFqHelperProcess.getOutputStream();
				outWriter = new OutputStreamWriter(outputStream);

			}

		}
		gncFqHelperProcess.destroy();
		
		return ret;

	}

	private Process initHelperProcess() throws IOException {
		ProcessBuilder gncFqHelper = new ProcessBuilder("/usr/local/bin/gnc-fq-helper");
		gncFqHelper.redirectErrorStream(true);

		Process gncFqHelperProcess = gncFqHelper.start();
		return gncFqHelperProcess;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {

		String pathToprops = args[0];
		List<String> argList = Arrays.asList(args);
		
		Boolean gnucashExports = argList.contains("-gnucashExports");
		List<String> gnucashExportsList  = new ArrayList<String>();
		if (gnucashExports) {
			int indexExport = argList.indexOf("-gnucashExports")+1;
			while (indexExport < argList.size()) {
				String export = argList.get(indexExport);
				if (export.contains("-")) break;
				gnucashExportsList.add(export);
				indexExport++;
			}
		}
		
		Boolean cmdlineSymbols = argList.contains("-symbols");
		List<String> symbolsList  = new ArrayList<String>();
		if (cmdlineSymbols) {
			int indexSymbol = argList.indexOf("-symbols")+1;
			while (indexSymbol < argList.size()) {
				String symbol = argList.get(indexSymbol);
				if (symbol.contains("-")) break;
				symbolsList.add(symbol);
				indexSymbol++;
			}
		}

		SpringContext springContext = null;
		try {
			springContext = new SpringContext(pathToprops);
			//springContext.setDataSource(pathToprops);
			springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
			springContext.refresh();

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());

			GnucashGetPriceHelper getPriceHelper = new GnucashGetPriceHelper();

			Set<StockWraper> stocks;
			if (gnucashExportsList.size() > 0) {
				stocks = getPriceHelper.getGnucashExportsShareList(gnucashExportsList);
			} else if (symbolsList.size() > 0) {
				stocks = getPriceHelper.getCmdShareList(symbolsList);
			} else {
				stocks = getPriceHelper.getMonitoredShareList();
			}
			
			System.out.println("Stocks : "+stocks);
			List<String> providers = getPriceHelper.runGNCCheck();
			System.out.println("Providers :" + providers);
			Map<StockWraper, Map<String, String>> checkPriceProvider = getPriceHelper.checkPriceProvider(providers, stocks);
			
			File file = new File(System.getProperty("installdir")+ File.separator + "gnucashGetPricesRes.csv");
			
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("isin,stock,provider,validity,res\n");
			
			for (StockWraper stock : checkPriceProvider.keySet()) {
				System.out.println("Res for "+stock);
				Map<String, String> map = checkPriceProvider.get(stock);
				for (String prov : map.keySet()) {
					String res = map.get(prov);
					if (res.contains("symbol")) {
						System.out.println(prov +" is valid : "+res);
						bufferedWriter.write(stock.getReference()+","+stock.getName()+","+prov+",valid,"+res.replace(",", "_")+"\n");
					} else if (!res.equals("(#f)") && !res.equals("#f")){
						System.out.println(prov +" is failing : "+res);
						bufferedWriter.write(stock.getReference()+","+stock.getName()+","+prov+",failed,"+res.replace(",", "_")+"\n");
					}
				}
			}
			
			bufferedWriter.close();

		} finally {

			if (springContext != null) springContext.close();
		}

	}
	
	interface StockWraper {

		String getReference();

		String getName();

	}
}
