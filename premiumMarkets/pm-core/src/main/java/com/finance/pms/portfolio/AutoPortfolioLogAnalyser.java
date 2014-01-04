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
package com.finance.pms.portfolio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.QuotationUnit;

public class AutoPortfolioLogAnalyser {
	
	
	File log;
	private BufferedReader fileReader;
	private String separator;
	
	//private int isinCol;
	private int symbolCol;
	private int quantityCol;
	private int priceCol;
	private int mvntCol;
	private int dateCol;


	public AutoPortfolioLogAnalyser(String log, String separator) throws FileNotFoundException {
		super();
		this.log = new File(log);
		this.fileReader = new BufferedReader(new FileReader(this.log));
		this.separator = separator;
	}
	
	public static void main(String... args) throws IOException, ParseException {
		
		SpringContext springContext = new SpringContext(args[0]);
		
		try {

			//springContext.setDataSource(args[0]);
			springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
			springContext.refresh();
			
			AutoPortfolioLogAnalyser analyser = new AutoPortfolioLogAnalyser("/usr/local/opt/Prgs/PremiumMarkets/autoPortfolioLogs/ScreenUSNEURAL_Log.csv", ",");
			Map<Stock, Gain> profitsPerLine = analyser.calculateProfitsPerLine();
			StringBuffer printGains = analyser.printGains(profitsPerLine);
			
			System.out.println(printGains);
			
		} finally {
			
			springContext.close();
		}
		
	}
	
	//Real and Unreal profits per line
	public Map<Stock, Gain> calculateProfitsPerLine() throws IOException, ParseException {
		Map<Stock, Gain> ret = new HashMap<Stock, Gain>();
		
		String line;
		while ((line = fileReader.readLine() ) != null) {
			String[] splitedLine = line.split(separator);

			if (line.contains("date")) {//header
				for (int i = 0; i < splitedLine.length; i++) {
					if (splitedLine[i].contains("date")) dateCol = i;
					if (splitedLine[i].contains("symbol")) symbolCol = i;
					//if (splitedLine[i].contains("isin")) isinCol = i;
					if (splitedLine[i].contains("quantity")) quantityCol = i;
					if (splitedLine[i].contains("price")) priceCol = i;
					if (splitedLine[i].contains("movement")) mvntCol = i;
				}

			} else {
				
				String symbol = splitedLine[symbolCol];
				Stock stock = DataSource.getInstance().loadStockBySymbol(symbol);
				Date date = new SimpleDateFormat("yyyy/MM/dd").parse(splitedLine[dateCol]);
				Gain stockGain = ret.get(stock);
				if (stockGain == null) {
					stockGain = new Gain();
					ret.put(stock, stockGain);
				}
				String mvt = splitedLine[mvntCol];
				BigDecimal quantity = new BigDecimal(splitedLine[quantityCol]);
				BigDecimal price = new BigDecimal(splitedLine[priceCol]);
				if (mvt.equals("buy")) {
					stockGain.quantity = stockGain.quantity.add(quantity);
					stockGain.addIn(quantity.multiply(price).setScale(2, BigDecimal.ROUND_DOWN), stock, date);
					stockGain.quantityIn = stockGain.quantityIn.add(quantity);
				} else 
					if (mvt.equals("sell")) {
						BigDecimal transactionAmount = quantity.multiply(price);
						stockGain.quantity = stockGain.quantity.subtract(quantity);
						stockGain.quantityOut = stockGain.quantityOut.add(quantity);
						stockGain.addOut(transactionAmount.setScale(2, BigDecimal.ROUND_DOWN), stock, date);
						
					}
			}

		}
		
		for (Stock stock : ret.keySet()) {
			ArrayList<QuotationUnit> lastQUnit = DataSource.getInstance().loadStripedQuotationsAfter(stock, stock.getLastQuote());
			BigDecimal close = lastQUnit.get(0).getClose();
			ret.get(stock).lastQuote = close;
			BigDecimal closeEUR = PortfolioMgr.getInstance().getCurrencyConverter().convert(stock.getMarketValuation().getCurrency(), Currency.EUR, close, stock.getLastQuote());
			ret.get(stock).lastQuoteEUR = closeEUR;
			
		}
		
		return ret;
	}
	
	public StringBuffer printGains(Map<Stock, Gain> gainPerLine) {
		
		if (gainPerLine.size() == 0) return new StringBuffer();
		
		StringBuffer buffer = new StringBuffer();
		SortedMap<Currency, BigDecimal[]> totalsPerCurrency = new TreeMap<Currency, BigDecimal[]>();
		
		buffer.append("stock"+separator+"\t\tReal Amount"+separator+"\t\tReal A EUR"+separator+"\t\tunReal Prof"+separator+"\t\tunReal Profit EUR\n");
		BigDecimal totalInEUR = BigDecimal.ZERO;
		BigDecimal totalOutEUR= BigDecimal.ZERO;
		BigDecimal amountStillInEUR = BigDecimal.ZERO;
		BigDecimal totRealAmntEUR= BigDecimal.ZERO;
		NumberFormat nf = new DecimalFormat("#0.0000");
		for (Stock stock : gainPerLine.keySet()) {
			Gain gain = gainPerLine.get(stock);
			Currency currency = stock.getMarketValuation().getCurrency();
			buffer.append(
					stock.getSymbol() + separator + "\t\t" +
					nf.format(gain.realizedAmount())+ " "+currency+ separator +  "\t\t" +nf.format(gain.realizedAmountEUR())+" "+Currency.EUR+ separator +  "\t\t" +
					nf.format(gain.unRealizedProfit()) + " "+currency+ separator + "\t\t" +nf.format(gain.unRealizedProfitEUR())+" "+Currency.EUR+ separator + "\n"
			);
			
			//Totals EUR
			totalInEUR = totalInEUR.add(gain.inEUR);
			totalOutEUR = totalOutEUR.add(gain.outEUR);
			totRealAmntEUR = totRealAmntEUR.add(gain.realizedAmountEUR());
			amountStillInEUR = amountStillInEUR.add(gain.quantity.multiply(gain.lastQuoteEUR));
			
			//Total Other Currencies
			BigDecimal[] totalsForCurrency = totalsPerCurrency.get(currency);
			if (totalsForCurrency == null) {
				totalsForCurrency = new BigDecimal[]{BigDecimal.ZERO,BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
				totalsPerCurrency.put(currency, totalsForCurrency);
			}
			//in for currency
			totalsForCurrency[0] = totalsForCurrency[0].add(gain.in);
			//out for currency
			totalsForCurrency[1] = totalsForCurrency[1].add(gain.out);
			//realized amount for currency
			totalsForCurrency[2] = totalsForCurrency[2].add(gain.realizedAmount());
			//amout still in for currency
			totalsForCurrency[3] = totalsForCurrency[3].add(gain.quantity.multiply(gain.lastQuote));
		}
		buffer.append("\n");
		buffer.append("Sums"+separator+"\t\tReal Amount"+separator+"\t\tunReal Amount"+separator+"\t\tunReal Profit\n");
		BigDecimal totUnRealAmntEUR = totalOutEUR.add(amountStillInEUR).subtract(totalInEUR).setScale(4, BigDecimal.ROUND_DOWN);
		buffer.append("Totals "+ Currency.EUR + separator +  "\t\t" +nf.format(totRealAmntEUR) + separator +  "\t\t" +nf.format(totUnRealAmntEUR) + separator +  "\t\t" +nf.format(totUnRealAmntEUR.divide(totalInEUR, 4 ,BigDecimal.ROUND_DOWN))+"\n");
		for (Currency currency : totalsPerCurrency.keySet()) {
			BigDecimal[] totals = totalsPerCurrency.get(currency);
			BigDecimal totalUnRealAmount = totals[1].add(totals[3]).subtract(totals[0]).setScale(4, BigDecimal.ROUND_DOWN);
			buffer.append("Part "+ currency + separator +  "\t\t" +nf.format(totals[2]) + separator +  "\t\t" +nf.format(totalUnRealAmount) + separator + "\t\t" + nf.format(totalUnRealAmount.divide(totals[0], 4 ,BigDecimal.ROUND_DOWN))+"\n");
		}
		
				
		return buffer;
	}

	
	class Gain {
		
		private BigDecimal quantity;
		private BigDecimal in;
		private BigDecimal inEUR;
		private BigDecimal out;
		private BigDecimal outEUR;
		private BigDecimal lastQuote;
		private BigDecimal lastQuoteEUR;
		
		private BigDecimal quantityIn;
		private BigDecimal quantityOut;
		private BigDecimal realGainAmount;
		private BigDecimal realGainAmountEUR;
		
		public Gain() {
			this.in = BigDecimal.ZERO;
			this.inEUR = BigDecimal.ZERO;
			this.out = BigDecimal.ZERO;
			this.outEUR = BigDecimal.ZERO;
			this.quantity = BigDecimal.ZERO;
			this.lastQuote = BigDecimal.ZERO;
			this.lastQuoteEUR = BigDecimal.ZERO;
			
			this.quantityIn = BigDecimal.ZERO;
			this.quantityOut = BigDecimal.ZERO;
			this.realGainAmount = BigDecimal.ZERO;
			this.realGainAmountEUR = BigDecimal.ZERO;
		}
		
		public void addOut(BigDecimal amount, Stock stock, Date date) {
			this.out = this.out.add(amount);
			BigDecimal amountEUR = PortfolioMgr.getInstance().getCurrencyConverter().convert(stock.getMarketValuation().getCurrency(), Currency.EUR, amount, date);
			this.outEUR = this.outEUR.add(amountEUR);
			
			updateRealGainAmount();
			
		}

		public void addIn(BigDecimal amount, Stock stock, Date date) {
			this.in = this.in.add(amount);
			BigDecimal amountEUR = PortfolioMgr.getInstance().getCurrencyConverter().convert(stock.getMarketValuation().getCurrency(), Currency.EUR, amount, date);
			this.inEUR = this.inEUR.add(amountEUR);
		}
		
		private void updateRealGainAmount() {
			
			BigDecimal avgBuyPrice = this.in.divide(this.quantityIn, 4, BigDecimal.ROUND_DOWN);
			BigDecimal avgSellPrice = this.out.divide(this.quantityOut, 4, BigDecimal.ROUND_DOWN);
			this.realGainAmount = avgSellPrice.subtract(avgBuyPrice).multiply(this.quantityOut).setScale(4,BigDecimal.ROUND_DOWN);
			
			BigDecimal avgBuyPriceEUR = this.inEUR.divide(this.quantityIn, 4, BigDecimal.ROUND_DOWN);
			BigDecimal avgSellPriceEUR = this.outEUR.divide(this.quantityOut, 4, BigDecimal.ROUND_DOWN);
			this.realGainAmountEUR = avgSellPriceEUR.subtract(avgBuyPriceEUR).multiply(this.quantityOut).setScale(4,BigDecimal.ROUND_DOWN);
		}

		public BigDecimal unRealizedProfit() {
			BigDecimal unRealizedAmount = unRealizedAmount();
			return unRealizedAmount.divide(in,4,BigDecimal.ROUND_DOWN);
		}
		
		public BigDecimal unRealizedProfitEUR() {
			BigDecimal unRealizedAmountEUR = unRealizedAmountEUR();
			return unRealizedAmountEUR.divide(inEUR,4,BigDecimal.ROUND_DOWN);
		}

		private BigDecimal unRealizedAmount() {
			BigDecimal stillIn = lastQuote.multiply(quantity);
			BigDecimal unRealizedAmount = stillIn.add(out).subtract(in);
			return unRealizedAmount;
		}
		
		private BigDecimal unRealizedAmountEUR() {
			
			BigDecimal stillIn = lastQuoteEUR.multiply(quantity);
			BigDecimal unRealizedAmountEUR = stillIn.add(outEUR).subtract(inEUR);
			return unRealizedAmountEUR;
		}
		
		public BigDecimal realizedAmount() {
			return realGainAmount;
		}
		
		public BigDecimal realizedAmountEUR() {
			return realGainAmountEUR;
		}
		
	}


	public String message()  throws Exception {
		
		Map<Stock, Gain> calculatedProfitsPerLine = this.calculateProfitsPerLine();
		StringBuffer printGains = this.printGains(calculatedProfitsPerLine);
		
		return printGains.toString();
	
	}

}
