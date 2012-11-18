/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
import com.finance.pms.datasources.web.Providers;

// TODO: Auto-generated Javadoc
/**
 * The Class Stock.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@IdClass(StockId.class)
@Table(name="SHARES")
public class Stock extends Validatable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(Stock.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2357651331343818162L;

	/** The Constant MISSINGCODE. */
	public static final String MISSINGCODE="NON_AVAILABLE";
	
    /** The symbol. */
    private String symbol;
    
    /** The isin. */
    private String isin;
   
    /** The name. */
    private String name;
    
    /** The removable. */
    private Boolean removable;
    
    /** The category. */
    private StockCategories category;
    
    /** The last quote. */
    private Date lastQuote;
    
    /** The provider type. */
    private  Market market;
    
    /** The market. */
    private  SymbolMarketQuotationProvider symbolMarketQuotationProvider;
    
    private String sectorHint;
    
    private TradingMode tradingMode;
    
    private Long capitalisation;
    
	/** The ref name. */
	private String refName;

	public Stock() {
		super();
		//TODO hibernate interceptor
		this.symbolMarketQuotationProvider = new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT,null);

	}
	
	public Stock(String symbol, String isin) {
		this.symbol=symbol;
		this.isin=isin;
		this.symbolMarketQuotationProvider = new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT,null);
	}

	/**
	 * Instantiates a new stock.
	 * 
	 * @param s the s
	 * 
	 * @author Guillaume Thoreton
	 */
	public Stock(Stock si) {
		super();
		Stock s = si;
		this.market = s.market;
		this.symbolMarketQuotationProvider = s.symbolMarketQuotationProvider;
		this.symbol = s.symbol;
		this.isin = s.isin;
		this.name = s.name;
		this.removable = s.removable;
		this.category = s.category;
		this.lastQuote = s.lastQuote;
		this.refName = s.refName;
		this.tradingMode = s.tradingMode;
		this.sectorHint = s.sectorHint;
		this.capitalisation = s.capitalisation;
	}
	
	/**
 * Instantiates a new stock.
 * 
 * @param isin the isin
 * @param symbol the symbol
 * @param stockCat the stock cat
 * @param marketQuotationsProvider the market quotations provider
 * @param market the market list provider
 * 
 * @author Guillaume Thoreton
 */
	@Deprecated
	public Stock(String isin, String symbol, StockCategories stockCat, SymbolMarketQuotationProvider marketQuotationsProvider, Market market) {
		this.market = market;
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
	    this.removable = true;
	    
	    this.tradingMode = TradingMode.CONTINUOUS;
	    this.sectorHint = "";
	    this.capitalisation = 0L;
	}
	
	/**
	 * Instantiates a new stock.
	 * 
	 * @param isin the isin
	 * @param symbol the symbol
	 * @param name the name
	 * @param removable the removable
	 * @param category the category
	 * @param marketQuotationsProvider the market quotations provider
	 * @param market the market list provider
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	public Stock( String isin, String symbol, String name, Boolean removable,
			StockCategories category, 
			SymbolMarketQuotationProvider marketQuotationsProvider, Market market) 
	throws InvalidAlgorithmParameterException {
		this.market = market;
		this.symbolMarketQuotationProvider = marketQuotationsProvider;
	    this.setIsin(isin);
	    this.setSymbol(symbol);
	    this.name = name;
	    this.removable = removable;
	    this.category = category;
	    
	    this.tradingMode = TradingMode.UNKNOWN;
	    this.sectorHint = "";
	    this.capitalisation = 0L;
	}
	
	public Stock(String isin, String symbol, String name, Boolean removable,
			StockCategories category, SymbolMarketQuotationProvider marketQuotationsProvider,
			Market  market,
			String sectorHint, TradingMode tradingMode, Long capitalisation) 
	throws InvalidAlgorithmParameterException {
		
		this.market = market;
		this.symbolMarketQuotationProvider = marketQuotationsProvider;
	    this.setIsin(isin);
	    this.setSymbol(symbol);
	    this.name = name;
	    this.removable = removable;
	    this.category = category;
	    
	    this.sectorHint = sectorHint;
	    this.tradingMode = tradingMode;
	    this.capitalisation = capitalisation;
	}

	/**
	 * Instantiates a new stock.
	 * 
	 * @param isin the isin
	 * @param symbol the symbol
	 * @param name the name
	 * @param removable the removable
	 * @param category the category
	 * @param lastquote the lastquote
	 * @param marketQuotationsProvider the market quotations provider
	 * @param market the market list provider
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public Stock(String isin, String symbol, String name, Boolean removable,
			StockCategories category, Date lastquote, 
			SymbolMarketQuotationProvider marketQuotationsProvider, 
			Market market,
			String sectorHint, TradingMode tradingMode, Long capitalisation) 
	throws InvalidAlgorithmParameterException {
	    this.market = market;
	    this.symbolMarketQuotationProvider = marketQuotationsProvider;
	    this.setIsin(isin);
	    this.setSymbol(symbol);
	    this.name = name;
	    this.removable = removable;
	    this.category = category;
	    this.lastQuote = lastquote;
	    
	    this.tradingMode = tradingMode;
	    this.sectorHint = sectorHint;
	    this.capitalisation = capitalisation;
	}

	public void resetStock(Stock stock) {
		this.market = stock.market;
		this.symbolMarketQuotationProvider = stock.symbolMarketQuotationProvider;
		this.symbol = stock.symbol;
		this.isin = stock.isin;
		this.name = stock.name;
		this.removable = stock.removable;
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
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Validatable o) {
		if (o instanceof com.finance.pms.datasources.shares.Stock) 
			return (this.getSymbol().compareTo(((Stock) o).getSymbol()) == 0)?
					this.getIsin().compareTo(((Stock) o).getIsin())
					:this.getSymbol().compareTo(((Stock) o).getSymbol());
					
		throw new RuntimeException("Can't compare Stock with ... "+o.getClass().getName());
	}
	
	
	
	/**
	 * Checks if is obsolete.
	 * 
	 * @return true, if is obsolete
	 */
	@Transient
	private boolean isObsolete() {
	    GregorianCalendar gcal = new GregorianCalendar();
	    gcal.setTime(this.lastQuote);
	    gcal.add(Calendar.MONTH,12);
	    Date obsolete = gcal.getTime();
	    return (obsolete.compareTo(new Date()) < 0);
	}


	/**
	 * Checks if is field set.
	 * 
	 * @param attribute the attribute
	 * 
	 * @return true, if is field set
	 */
	public boolean isFieldSet(String attribute) {
		try {
			String attValue = (String) this.getClass().getDeclaredField(attribute).get(this);
			return (attValue != null) && (attValue.length() != 0) && !(attValue.equals(Stock.MISSINGCODE));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			LOGGER.error("",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			LOGGER.error("",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			LOGGER.error("",e);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			LOGGER.error("",e);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.db.Validatable#toDataBase()
	 */
	@Override
	public Query toDataBase() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		
		Query iq = new Query();
		
		//Ajout d'une requete pour la base
		iq.addValue(this.getSymbol());
		iq.addValue(this.getIsin());
		iq.addValue(this.getName());
		iq.addValue(new Boolean(this.isRemovable()));
		iq.addValue(this.getCategory().name());
		try {
			iq.addValue((null != this.lastQuote)?this.lastQuote:sf.parse("1970/01/01"));
		} catch (ParseException e) {
			iq.addValue(new Date());
			LOGGER.error("",e);
		}
		iq.addValue(this.getSymbolMarketQuotationProvider().toString());
		iq.addValue(this.getMarket().toString());
		iq.addValue(this.getSectorHint());
		iq.addValue(this.getTradingMode().toString());
		iq.addValue(this.getCapitalisation());
		return iq;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		try {
			Currency currency = Currency.NAN;
			if (this.getMarket() != null) {
				currency = this.getMarket().getCurrency();
			}
			String lastDateQ = null;
			SimpleDateFormat dateFormat =new SimpleDateFormat("YYYY-MM-dd");
			if (this.lastQuote != null) {
				lastDateQ = dateFormat.format(this.lastQuote);
			}
			str = "Symbol :"+this.getSymbol()+"; Isin :"+this.getIsin()+"; Name :"+this.getName()+"; Currency :"+currency+"; last date Q :"+lastDateQ;
		} catch (RuntimeException e) {
			LOGGER.debug("Can't print stock : "+this.symbol+";"+this.isin+";"+this.name,e);
		}
		return str;
	}
	
	
	/**
	 * Retreive stock.
	 * 
	 * @param stockList the stock list
	 * 
	 * @author Guillaume Thoreton
	 */
	public void retrieveStock(StockList stockList,String shareListName) {
		Providers.getInstance(shareListName).retrieveAndCompleteStockInfo(this,stockList);
	}
	
	/**
	 * Gets the ref name.
	 * 
	 * @return the ref name
	 */
    @Deprecated
    @Transient
	public String getRefName() {
		if (this.refName == null) {
		    this.refName = Providers.getInstance(this.getSymbolMarketQuotationProvider().getCmdParam()).getStockRefName(this);
		}
		return this.refName;
	}
	
	/**
	 * Sets the ref name.
	 * 
	 * @param refName the new ref name
	 */
    @Deprecated
	public void setRefName(String refName) {
		this.refName = refName;
	}
    
    
    /**
     * Sets the isin.
     * 
     * @param isin the new isin
     * 
     * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
     */
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
    
    
    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = (name != null)?name.trim():null;
    }
    
    
    /**
     * Gets the symbol.
     * 
     * @return the symbol
     */
	@Id
	@Column(name="SYMBOL")
	public String getSymbol() {
		return (symbol != null)?symbol.trim():null;
	}
	
    /**
     * Gets the isin.
     * 
     * @return the isin
     */
	@Id
	@Column(name="ISIN")
	public String getIsin() {
        return (isin != null)?isin.trim():null;
    }
    
    
    /**
     * Gets the symbol root.
     * 
     * @return the symbol root
     */
	@Transient
    public String getSymbolRoot() {
		String fullSymbol = this.getSymbol();
		if (fullSymbol.contains(".")) {
			int indexOfDot = fullSymbol.indexOf(".");
			//return fullSymbol.substring(0, fullSymbol.length() - 3);
			return fullSymbol.substring(0, indexOfDot);
		} else {
			return fullSymbol;
		}
	}
    
   
    /**
     * Sets the symbol.
     * 
     * @param symbol the new symbol
     * 
     * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
     */
    public void setSymbol(String symbol) throws InvalidAlgorithmParameterException {
    	this.symbol = this.symbolMarketQuotationProvider.getFullSymbol((symbol != null)?symbol.trim():null);
	}
    
    
    /**
     * To be removed.
     * 
     * @param whichMarket the which market
     * 
     * @return the boolean
     * 
     * @author Guillaume Thoreton
     */
    public Boolean toBeRemoved(SharesListId whichMarket) {
    	return this.isRemovable() && this.isObsolete() 
				&& whichMarket.equals(this.getMarket());
    }
    
    /**
     * Checks if is removable.
     * 
     * @return the boolean
     * 
     * @author Guillaume Thoreton
     */
    public Boolean isRemovable() {
        return removable;
    }
	
    /**
     * Gets the category.
     * 
     * @return the category
     */
    @Enumerated(EnumType.STRING)
    public StockCategories getCategory() {
		return category;
	}

	/**
	 * Gets the provider type.
	 * 
	 * @return the provider type
	 */
    @Enumerated(EnumType.STRING)
    @Column(name="MARKETLISTPROVIDER")
	public Market getMarket() {
		return market;
	}

	/**
	 * Sets the provider type.
	 * 
	 * @param market the market list provider
	 */
	public void setMarket(Market market) {
		this.market = market;
	}

	/**
	 * Gets the symbol market quotation provider.
	 * 
	 * @return the symbol market quotation provider
	 */
    @Embedded
    @AttributeOverride(name="marketQuotationProvider", column = @Column(name="QUOTATIONPROVIDER"))
	public SymbolMarketQuotationProvider getSymbolMarketQuotationProvider() {
		return symbolMarketQuotationProvider;
	}

	/**
	 * Sets the symbol market quotation provider.
	 * 
	 * @param symbolMarketQuotationProvider the new symbol market quotation provider
	 */
	public void setSymbolMarketQuotationProvider(SymbolMarketQuotationProvider symbolMarketQuotationProvider) {
		this.symbolMarketQuotationProvider = symbolMarketQuotationProvider;
	}

	/**
	 * Sets the last quote.
	 * 
	 * @param lastQuote the new last quote
	 */
	public void setLastQuote(Date lastQuote) {
		this.lastQuote = lastQuote;
	}

	/**
	 * Gets the last quote.
	 * 
	 * @return the last quote
	 */
	//@Formula("(select max(quotations.date) from quotations where symbol = symbol and isin = isin)")
	@Temporal(TemporalType.DATE)
	public Date getLastQuote() {
		return lastQuote;
	}

	@SuppressWarnings("unused")
	private void setCategory(StockCategories category) {
		this.category = category;
	}

	@SuppressWarnings("unused")
	private void setRemovable(Boolean removable) {
		this.removable = removable;
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
	private TradingMode getTradingMode() {
		return tradingMode;
	}

	@SuppressWarnings("unused")
	private void setTradingMode(TradingMode tradingMode) {
		this.tradingMode = tradingMode;
	}
	
	private Long getCapitalisation() {
		return capitalisation;
	}

	@SuppressWarnings("unused")
	private void setCapitalisation(Long capitalisation) {
		this.capitalisation = capitalisation;
	}

	public boolean lenientEquals(Stock stock) {
		String thisSimplifiedSymbol = this.getSymbol().split("\\.")[0];
		String stockSimplifiedSymbol = stock.getSymbol().split("\\.")[0];
		return stockSimplifiedSymbol.equals(thisSimplifiedSymbol);
	}

//	@OneToMany(mappedBy="tunedConfId.stock",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
//	@MapKey
//	public Map<TunedConfId, TunedConf> getStockTunedConf() {
//		return stockTunedConf;
//	}
//
//	public void setStockTunedConf(Map<TunedConfId, TunedConf> stockTunedConf) {
//		this.stockTunedConf = stockTunedConf;
//	}

//	public TunedConf whichTunedConfigForThisRuleSet(String configListFileName) {
//		TunedConfId tunedConfId = new TunedConfId(this, configListFileName);
//		TunedConf stockPrevTunedConf = this.getStockTunedConf().get(tunedConfId);
//		
//		if (stockPrevTunedConf == null) { //No prev conf : first tune for this config file
//			stockPrevTunedConf = new TunedConf(this, configListFileName, "No config yet", DateFactory.dateAtZero(), false);
//			this.getStockTunedConf().put(tunedConfId, stockPrevTunedConf);
//		}
//		return stockPrevTunedConf;
//	}
	
}

