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
package com.finance.pms.datasources.shares;

import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.ProvidersList;

/**
 * The Class Stock.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@IdClass(StockId.class)
@Table(name="SHARES")
public class Stock extends Validatable {

	private static MyLogger LOGGER = MyLogger.getLogger(Stock.class);
	private static final long serialVersionUID = 2357651331343818162L;

	public static final String MISSINGCODE="NON_AVAILABLE";

	private String symbol;
	private String isin;

	private String name;
	private StockCategories category;
	private Date lastQuote;
	private MarketValuation marketValuation;
	private SymbolMarketQuotationProvider symbolMarketQuotationProvider;
	private String sectorHint;
	private TradingMode tradingMode;
	private Long capitalisation;

	private Boolean overrideUserQuotes;
	private String refName;

	public Stock() {
		super();
		this.symbolMarketQuotationProvider = new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT, null);
	}

	public Stock(String symbol, String isin) {
		this.symbol=symbol;
		this.isin=isin;
		this.symbolMarketQuotationProvider = new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT, null);
	}

	public Stock(Stock si) {
		super();
		Stock s = si;
		this.marketValuation = s.marketValuation;
		this.symbolMarketQuotationProvider = s.symbolMarketQuotationProvider;
		this.symbol = s.symbol;
		this.isin = s.isin;
		this.name = s.name;
		this.overrideUserQuotes = s.overrideUserQuotes;
		this.category = s.category;
		this.lastQuote = s.lastQuote;
		this.refName = s.refName;
		this.tradingMode = s.tradingMode;
		this.sectorHint = s.sectorHint;
		this.capitalisation = s.capitalisation;
	}

	@Deprecated
	public Stock(String isin, String symbol, StockCategories stockCat, SymbolMarketQuotationProvider marketQuotationsProvider, MarketValuation market) {
		this.marketValuation = market;
		this.symbolMarketQuotationProvider = marketQuotationsProvider;
		try {
			this.setIsin(isin);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.debug(e);
		}
		try {
			this.setSymbol(symbol);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.debug(e);
		}
		this.category = stockCat;
		this.overrideUserQuotes = true;

		this.tradingMode = TradingMode.CONTINUOUS;
		this.sectorHint = "";
		this.capitalisation = 0L;
	}

	public Stock(String isin, String symbol, String name, Boolean removable,
			StockCategories category, SymbolMarketQuotationProvider marketQuotationsProvider,
			MarketValuation  market,
			String sectorHint, TradingMode tradingMode, Long capitalisation) throws InvalidAlgorithmParameterException {

		this.marketValuation = market;
		this.symbolMarketQuotationProvider = marketQuotationsProvider;
		this.setIsin(isin);
		this.setSymbol(symbol);
		this.name = name;
		this.overrideUserQuotes = removable;
		this.category = category;

		this.sectorHint = sectorHint;
		this.tradingMode = tradingMode;
		this.capitalisation = capitalisation;
	}

	public Stock(String isin, String symbol, String name, Boolean removable,
			StockCategories category, Date lastquote, 
			SymbolMarketQuotationProvider marketQuotationsProvider, 
			MarketValuation market,
			String sectorHint, TradingMode tradingMode, Long capitalisation)  throws InvalidAlgorithmParameterException {
		this.marketValuation = market;
		this.symbolMarketQuotationProvider = marketQuotationsProvider;
		this.setIsin(isin);
		this.setSymbol(symbol);
		this.name = name;
		this.overrideUserQuotes = removable;
		this.category = category;
		this.lastQuote = lastquote;

		this.tradingMode = tradingMode;
		this.sectorHint = sectorHint;
		this.capitalisation = capitalisation;
	}

	public void resetStock(Stock stock) {
		this.marketValuation = stock.marketValuation;
		this.symbolMarketQuotationProvider = stock.symbolMarketQuotationProvider;
		this.symbol = stock.symbol;
		this.isin = stock.isin;
		this.name = stock.name;
		this.overrideUserQuotes = stock.overrideUserQuotes;
		this.category = stock.category;
		this.lastQuote = stock.lastQuote;
		this.refName = stock.refName;
		this.tradingMode = stock.tradingMode;
		this.sectorHint = stock.sectorHint;
		this.capitalisation = stock.capitalisation;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final Stock other = (Stock) obj;
		if (isin == null || other.isin == null) {

		} else if (!isin.trim().equals(other.isin.trim())) 
			return false;
		if (symbol == null || other.symbol == null) {
			if (isin == null || other.isin == null)
				return false;
		} else if (!symbol.trim().equals(other.symbol.trim())) 
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isin == null) ? 0 : isin.trim().hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.trim().hashCode());
		return result;
	}

	public int compareTo(Validatable o) {
		if (o instanceof com.finance.pms.datasources.shares.Stock) 
			return (this.getSymbol().compareTo(((Stock) o).getSymbol()) == 0)?
					this.getIsin().compareTo(((Stock) o).getIsin())
					:this.getSymbol().compareTo(((Stock) o).getSymbol());

					throw new RuntimeException("Can't compare Stock with ... "+o.getClass().getName());
	}

	@Transient
	private boolean isObsolete() {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(this.lastQuote);
		gcal.add(Calendar.MONTH,12);
		Date obsolete = gcal.getTime();
		return (obsolete.compareTo(new Date()) < 0);
	}

	public boolean isFieldSet(String attribute) {
		try {
			String attValue = (String) this.getClass().getDeclaredField(attribute).get(this);
			return (attValue != null) && (attValue.length() != 0) && !(attValue.equals(Stock.MISSINGCODE));
		} catch (IllegalArgumentException e) {
			LOGGER.error("",e);
		} catch (SecurityException e) {
			LOGGER.error("",e);
		} catch (IllegalAccessException e) {
			LOGGER.error("",e);
		} catch (NoSuchFieldException e) {
			LOGGER.error("",e);
		}
		return false;
	}

	@Override
	public Query toDataBase() {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");

		Query iq = new Query();

		//Ajout d'une requete pour la base
		iq.addValue(this.getSymbol());
		iq.addValue(this.getIsin());
		iq.addValue(this.getName());
		iq.addValue(overrideUserQuotes);
		iq.addValue(this.getCategory().name());
		try {
			iq.addValue((null != this.lastQuote)?this.lastQuote:sf.parse("1970/01/01"));
		} catch (ParseException e) {
			iq.addValue(new Date());
			LOGGER.error("",e);
		}
		iq.addValue(this.getSymbolMarketQuotationProvider().toString());
		iq.addValue(this.getMarketValuation().toString());
		iq.addValue(this.getSectorHint());
		iq.addValue(this.getTradingMode().toString());
		iq.addValue(this.getCapitalisation());
		return iq;
	}


	@Override
	public String toString() {

		String str = "";

		try {
			Currency currency = Currency.NAN;
			if (this.getMarketValuation() != null) {
				currency = this.getMarketValuation().getCurrency();
			}
			String lastDateStr = "None";
			if (this.lastQuote != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				lastDateStr = dateFormat.format(this.lastQuote);
			}
			str = getFriendlyName().replace(")", " / ") + lastDateStr +" / "+currency + ")";

		} catch (RuntimeException e) {
			LOGGER.error("Can't print stock : "+this.symbol+";"+this.isin+";"+this.name+";"+lastQuote, e);
		}

		return str;
	}

	public void retrieveStock(StockList stockList, String shareListName) {
		ProvidersList.getMarketListInstance(shareListName).retrieveAndCompleteStockInfo(this, stockList);
	}

	public void setIsin(String isin) throws InvalidAlgorithmParameterException {

		this.isin = isin;
		String oldSymbol = this.symbol;
		try {
			if (this.symbol != null && !this.symbol.equals(Stock.MISSINGCODE) && this.isin != null && !this.isin.equals(Stock.MISSINGCODE)) {
				this.setSymbol(this.symbol);
			}
		} catch (InvalidAlgorithmParameterException e) {
			throw new InvalidAlgorithmParameterException("WARN : Invalid match between isin :"+isin+" and symbol :"+oldSymbol+" while setting isin");			
		} 

		if (this.isin == null || this.isin.equals(Stock.MISSINGCODE)) 
			throw new InvalidAlgorithmParameterException("WARN : No Isin set for this stock : "+ symbol +" . Be aware that the symbol might not be suffixed");

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = (name != null)?name.trim():null;
	}

	@Id
	@Column(name="SYMBOL")
	public String getSymbol() {
		return (symbol != null)?symbol.trim():null;
	}

	@Id
	@Column(name="ISIN")
	public String getIsin() {
		return (isin != null)?isin.trim():null;
	}

	@Transient
	public String getSymbolRoot() {
		String fullSymbol = this.getSymbol();
		if (fullSymbol.contains(".")) {
			int indexOfDot = fullSymbol.indexOf(".");
			return fullSymbol.substring(0, indexOfDot);
		} else {
			return fullSymbol;
		}
	}

	public void setSymbol(String symbol) throws InvalidAlgorithmParameterException {
		this.symbol = this.symbolMarketQuotationProvider.getFullSymbol((symbol != null)?symbol.trim():null);
	}

	/**
	 * Set to false to see your edited entries when there already are entries from downloaded quotations at the same date.
	 * When true, the downloaded quotations will be predominant if exist at the same date.
	 * @return
	 */
	@Column(name="REMOVABLE")
	public Boolean isOverrideUserQuotes() {
		return overrideUserQuotes;
	}

	@Enumerated(EnumType.STRING)
	public StockCategories getCategory() {
		return category;
	}

	@Embedded
	@AttributeOverride(name="market",column=@Column(name="MARKETLISTPROVIDER"))
	public MarketValuation getMarketValuation() {
		return marketValuation;
	}

	@Transient
	public Market getMarket() {
		return (marketValuation != null)?marketValuation.getMarket():Market.UNKNOWN;
	}

	public void setMarketValuation(MarketValuation market) {
		this.marketValuation = market;
	}

	@Embedded
	@AttributeOverride(name="marketQuotationProvider.name", column = @Column(name="QUOTATIONPROVIDER"))
	public SymbolMarketQuotationProvider getSymbolMarketQuotationProvider() {
		return symbolMarketQuotationProvider;
	}

	public void setSymbolMarketQuotationProvider(SymbolMarketQuotationProvider symbolMarketQuotationProvider) {
		this.symbolMarketQuotationProvider = symbolMarketQuotationProvider;
	}

	public void setLastQuote(Date lastQuote) {
		this.lastQuote = lastQuote;
	}

	@Temporal(TemporalType.DATE)
	public Date getLastQuote() {
		return lastQuote;
	}

	public void setCategory(StockCategories category) {
		this.category = category;
	}

	public void setOverrideUserQuotes(Boolean overrideUserQuotes) {
		this.overrideUserQuotes = overrideUserQuotes;
	}

	public void setSectorHint(String sectorHint) {
		this.sectorHint = sectorHint;
	}

	@Column(name="SECTOR_HINT")
	public String getSectorHint() {
		return sectorHint;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="TRADING_MODE")
	public TradingMode getTradingMode() {
		return tradingMode;
	}

	public void setTradingMode(TradingMode tradingMode) {
		this.tradingMode = tradingMode;
	}

	public Long getCapitalisation() {
		return capitalisation;
	}

	public void setCapitalisation(Long capitalisation) {
		this.capitalisation = capitalisation;
	}

	public boolean lenientSymbolEquals(Stock stock) {
		String thisSimplifiedSymbol = this.getSymbol().split("\\.")[0];
		String stockSimplifiedSymbol = stock.getSymbol().split("\\.")[0];
		return stockSimplifiedSymbol.equals(thisSimplifiedSymbol);
	}

	public boolean lenientEquals(String... refs) {
		for (String ref : refs) {
			if (ref != null &&
					(
							this.getSymbol().equals(ref) || 
							this.getIsin().equals(ref) || 
							this.getName().replaceAll("['. \\s()]", "").equalsIgnoreCase(ref.replaceAll("['. \\s()]", ""))
							)
					) {
				return true;
			}
		}
		return false;
	}

	@Transient
	public String getFriendlyName() {
		return this.getName().replaceAll("([,;/])"," ")+" ("+this.getSymbol()+" / "+this.getIsin()+")";
	}

}

