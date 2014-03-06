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
package com.finance.pms.datasources.shares;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;

/**
 * The Enum Market.
 * 
 * @author Guillaume Thoreton
 */
public enum Market implements Serializable {
	
	ASX ("ASX", "ASX", Currency.AUD, BigDecimal.ONE, YahooMarketExtentions.ASX, "UNKNOWN", "WMORN", true, new String[]{"AORD"}),
	BSE ("BSE", "BSE", Currency.INR, BigDecimal.ONE, YahooMarketExtentions.BSE, "UNKNOWN", "WMORN", false, new String[]{}),
	NSE ("NSE", "NSE", Currency.INR, BigDecimal.ONE, YahooMarketExtentions.BSE, "UNKNOWN", "WMORN", false, new String[]{"NSEI"}),
	
	NASDAQ ("NASDAQ", "Nasdaq", Currency.USD, BigDecimal.ONE, YahooMarketExtentions.NASDAQ, "NASDAQ", "WMORN", true, new String[]{"NDX", "IXIC"}),
	NYSE ("NYSE", "NYSE", Currency.USD, BigDecimal.ONE, YahooMarketExtentions.NYSE, "NSE", "WMORN", true, new String[]{"NYA"}),
	AMEX  ("AMEX", "AMEX", Currency.USD, BigDecimal.ONE, YahooMarketExtentions.AMEX, "UNKNOWN", "WMORN", true, new String[]{}),
	DJI  ("DJI", "DJI", Currency.USD, BigDecimal.ONE, YahooMarketExtentions.NYSE, "UNKNOWN", "WMORN", true, new String[]{"DJA", "DJI"}),

	NZSX ("NZSX", "NZSX", Currency.NZD, BigDecimal.ONE, YahooMarketExtentions.NZ, "UNKNOWN", "UNKNOWN", true, new String[]{"NZ50"}),
	NZAX ("NZAX", "NZAX", Currency.NZD, BigDecimal.ONE, YahooMarketExtentions.NZ, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	NZDX ("NZDX", "NZDX", Currency.NZD, BigDecimal.ONE, YahooMarketExtentions.NZ, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	
	LSE ("LON", "London", Currency.GBP, new BigDecimal(100), YahooMarketExtentions.LON, "UNKNOWN", "WMORN", true, new String[]{"FTSE", "FTAI", "FTAS", "FTMC", "FTT1X"}),
	EURONEXT ("EURONEXT", "Euronext", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.PAR, "EPA", "XPAR", false, new String[]{}),
	PARIS ("PARIS", "Paris", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.PAR, "EPA", "XPAR", true, new String[]{"FCHI", "SBF250", "MS190", "CM100", "CS90"}),
	VIENNA ("VIENNA", "Vienna", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.VI, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	MILAN ("MILAN", "Milan", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.MI, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	STOCKHOLM ("STOCKHOLM", "Stockholm", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.ST, "UNKNOWN", "UNKNOWN", true, new String[]{"OMX"}),
	COPENHAGEN ("COPENHAGEN", "Copenhagen", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.CO, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	
	HANNOVER ("HANNOVER", "Hannover", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.HA, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	DUSSELDORF ("DUSSELDORF", "Dusseldorf", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.DU, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	HAMBURG ("HAMBURG", "Hamburg", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.HM, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	FRANKFURT ("FRANKFURT", "Frankfurt", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.F, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	STUTTGART ("STUTTGART", "Stuttgart", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.SG, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	XETRA ("XETRA", "Xetra", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.DE, "UNKNOWN", "UNKNOWN", true, new String[]{"GDAXI"}),
	MUNICH ("MUNICH", "Munich", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.MU, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	BERLIN ("BERLIN", "Berlin", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.BE, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	
	MERCADOCONTINUO ("MERCADOCONTINUO", "MercadoContinuo", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.MC, "UNKNOWN", "UNKNOWN", true, new String[]{"IBEX"}),
	MADRID ("MADRID", "Madrid", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.MA, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	
	BRUSSELS ("BRUSSELS", "Brussels", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.BR, "UNKNOWN", "UNKNOWN", true, new String[]{"BFX"}),
	VIRT_X ("VIRT_X", "Virt_X", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.VX, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	AMSTERDAM ("AMSTERDAM", "Amsterdam", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.AS, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	LISBON ("LISBON", "Lisbon", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.LS, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	OSLO ("OSLO", "Oslo", Currency.EUR, BigDecimal.ONE, YahooMarketExtentions.OL, "UNKNOWN", "UNKNOWN", true, new String[]{"OSEAX"}),
	
	TSX ("TSX", "TSX", Currency.CAD, BigDecimal.ONE, YahooMarketExtentions.TSX, "UNKNOWN", "WMORN", false, new String[]{}),
	TORONTO ("TORONTO", "Toronto", Currency.CAD, BigDecimal.ONE, YahooMarketExtentions.TO, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	SWITZERLAND ("SWITZERLAND", "Switzerland", Currency.CHF, BigDecimal.ONE, YahooMarketExtentions.SW, "UNKNOWN", "UNKNOWN", true, new String[]{"SSMI"}),
	
	ARTA ("JAKARTA", "Jakarta", Currency.IDR, BigDecimal.ONE, YahooMarketExtentions.JK, "UNKNOWN", "UNKNOWN", true, new String[]{"JKSE"}),
	SES ("SES", "SES", Currency.SGD, BigDecimal.ONE, YahooMarketExtentions.SI, "UNKNOWN", "UNKNOWN", true, new String[]{"STI"}),
	KOSDAQ ("KOSDAQ", "KOSDAQ", Currency.KRW, BigDecimal.ONE, YahooMarketExtentions.KQ, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	TAIWAN ("TAIWAN", "Taiwan", Currency.TWD, BigDecimal.ONE, YahooMarketExtentions.TW, "UNKNOWN", "UNKNOWN", true, new String[]{}),
	TASE ("TASE", "TASE", Currency.ILS, BigDecimal.ONE, YahooMarketExtentions.TA, "UNKNOWN", "UNKNOWN", true, new String[]{"TA100"}),
	HKSE ("HKSE", "HKSE", Currency.HKD, BigDecimal.ONE, YahooMarketExtentions.HK, "UNKNOWN", "UNKNOWN", true, new String[]{"HSI"}),
	
	UNKNOWN ("UNKNOWN", "Unknown", Currency.NAN, BigDecimal.ONE, YahooMarketExtentions.NN, "UNKNOWN", "UNKNOWN", false, new String[]{});
	
	
	private static MyLogger LOGGER = MyLogger.getLogger(Market.class);
	
	private String marketName;
	private String friendlyName;
	private Currency defaultCurrency;
	private BigDecimal defaultCurrencyFactor;
	
	private YahooMarketExtentions yahooExtension;
	private String googleExtension;
	private String investirExtension;
	
	private Boolean hasStaticAllMarket;
	private String[] yahooIndices;

	private Market(
			String marketName, String friendlyName, Currency defaultCurrency , BigDecimal defaultCurrencyFactor, 
			YahooMarketExtentions marketExtentions, String googleMarketName, String investirExtension, Boolean hasStaticAllMarket, String[] yahooIndices) {
		this.marketName = marketName;
		this.friendlyName = friendlyName;
		this.defaultCurrency = defaultCurrency;
		this.defaultCurrencyFactor = defaultCurrencyFactor;
		this.yahooExtension = marketExtentions;
		this.googleExtension = googleMarketName;
		this.investirExtension = investirExtension;
		this.hasStaticAllMarket = hasStaticAllMarket;
		this.yahooIndices = yahooIndices;
	}

	public String getFriendlyName() {
		return friendlyName;
	}
	
	
	public String getMarketName() {
		return marketName;
	}

	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}

	public YahooMarketExtentions getYahooExtension() {
		return yahooExtension;
	}

	public String getGoogleExtension() {
		return googleExtension;
	}

	public String getInvestirExtension() {
		return investirExtension;
	}
	
	public static Set<String> getExtensionFor(MarketQuotationProviders provider) {
		Set<String> ret = new HashSet<String>();
		String cmdParam4Prov = provider.getCmdParam();
		
		try {
			for (Market market : values()) {
				//String ext = Market.class.getDeclaredField(cmdParam4Prov+"Extension").get(market).toString();
				String extStringGen = extStringGen(market, cmdParam4Prov);
				if (!extStringGen.isEmpty()) {
					ret.add(extStringGen);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	public static String getExtensionFor(MarketQuotationProviders provider, Market market) {
	
		String cmdParam4Prov = provider.getCmdParam();
		try {
			return extStringGen(market, cmdParam4Prov);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return "NONE";
	}

	protected static String extStringGen(Market market, String cmdParam4Prov) throws IllegalAccessException, NoSuchFieldException {
		
		Object objectExt = Market.class.getDeclaredField(cmdParam4Prov+"Extension").get(market);
		return  objectExt.toString();
		
	}

	public BigDecimal getDefaultCurrencyFactor() {
		return defaultCurrencyFactor;
	}

	public Boolean getHasStaticAllMarket() {
		return hasStaticAllMarket;
	}

	public String[] getYahooIndices() {
		return yahooIndices;
	}
	
	
	
}