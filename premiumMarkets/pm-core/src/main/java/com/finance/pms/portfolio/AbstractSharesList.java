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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapKeyJoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyConverter;



/**
 * The Class Portfolio.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name="PORTFOLIO_NAME")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorOptions(force=true)
public abstract class AbstractSharesList extends Observable {

	protected static MyLogger LOGGER = MyLogger.getLogger(AbstractSharesList.class);

	/** The name. */
	protected String name;
	private BigDecimal totalInAmountEver;
	private BigDecimal totalOutAmountEver;

	private Map<Stock, PortfolioShare> listShares;

	
	protected AbstractSharesList() {
		super();
		this.totalInAmountEver = BigDecimal.ZERO;
		this.totalOutAmountEver = BigDecimal.ZERO;
		this.listShares = new ConcurrentHashMap<Stock, PortfolioShare>();
	}

	/**
	 * Instantiates a new portfolio.
	 * 
	 * @param name the name
	 * 
	 * @author Guillaume Thoreton
	 */
	public AbstractSharesList(String name) {
		this();
		this.name=name;
	}

	public AbstractSharesList(AbstractSharesList portfolio) {
		this();
		
		this.name = portfolio.name;
		if (portfolio.totalInAmountEver != null) {
			this.totalInAmountEver = portfolio.totalInAmountEver;
		}
		if (portfolio.totalOutAmountEver != null) {
			this.totalOutAmountEver = portfolio.totalOutAmountEver;
		}
		
		for (PortfolioShare portfolioShare: portfolio.getListShares().values()) {
			this.listShares.put(portfolioShare.getStock(),new PortfolioShare(portfolioShare));
		}
	}
	

	protected void removeShareFromList(PortfolioShare portfolioShare) {
		listShares.remove(portfolioShare.getStock());
	}
	
	protected void addShareToList(PortfolioShare portfolioShare) {
		listShares.put(portfolioShare.getStock(), portfolioShare);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( !(getClass().isAssignableFrom(obj.getClass()) || obj.getClass().isAssignableFrom(getClass())) )
			return false;
		final AbstractSharesList other = (AbstractSharesList) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.trim().equals(other.name.trim()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.trim().hashCode());
		return result;
	}


	/**
	 * Gets the list shares.
	 * 
	 * @return the list shares
	 */
	/** The list shares. */
	@OneToMany(mappedBy="portfolio",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(FetchMode.SUBSELECT)
	@MapKeyJoinColumns({
		@MapKeyJoinColumn(name="symbol",referencedColumnName="symbol"),
		@MapKeyJoinColumn(name="isin",referencedColumnName="isin")
	})
	public Map<Stock, PortfolioShare> getListShares() {
		//return Collections.unmodifiableMap(listShares);
		return listShares;
	}
	
	@Transient
	public PortfolioShare getShareForSymbolAndIsin(String symbol, String isin) {
		return this.getShareForStock(new Stock(symbol,isin));
	}
	
	@Transient
	public PortfolioShare getShareForLienientSymbol(String symbol) {
		for (PortfolioShare portfolioShare : this.listShares.values()) {
			if (portfolioShare.getSymbol().equals(symbol)) {
				return portfolioShare;
			}
		}
		return null;
	}
	
	@Transient
	public PortfolioShare getShareForLienientRefs(String... refs) {
		for (PortfolioShare portfolioShare : this.listShares.values()) {
			for (String ref : refs) {
				if (ref != null && (portfolioShare.getSymbol().equals(ref) || portfolioShare.getIsin().equals(ref))) {
					return portfolioShare;
				}
			}
		}
		return null;
	}

	@Transient 
	public PortfolioShare getShareForStock(Stock stock) {
		return listShares.get(stock);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	@Id
	public String getName() {
		return (name != null)?name.trim():null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.name+" : [ ");
		for (PortfolioShare portfolioShare : getListShares().values()) {
			sb.append(portfolioShare.toString() + " ; ");
		}
		sb.append(" ] ");
		return sb.toString();
	}


	@SuppressWarnings("unused")
	private void setListShares(Map<Stock, PortfolioShare> listShares) {
		this.listShares = listShares;
	}

	protected void setName(String name) {
		this.name = name;
	}

	 /**
	  * @return
	  */
	 @Transient
	 protected BigDecimal getNotNullTotalOutAmountEver() {
		 BigDecimal amountOut = (this.getTotalOutAmountEver() == null)? BigDecimal.ZERO:this.getTotalOutAmountEver();
		 return amountOut;
	 }

	 /**
	  * @return
	  */
	 @Transient
	 protected BigDecimal getNotNullTotalInAmountEver() {
		 return (this.getTotalInAmountEver() == null)?BigDecimal.ZERO:this.getTotalInAmountEver();
	 }

	 protected void addAmountToTotalAmountIn(BigDecimal newCashIn, Currency currency, Date currentDate) {
		 if (this.totalInAmountEver == null) this.totalInAmountEver = new BigDecimal(0).setScale(2);
		 this.totalInAmountEver = this.totalInAmountEver.add(newCashIn);
	 }

	 protected BigDecimal addAmountToTotalAmountOut(BigDecimal newCashOut, Currency currency, Date currentDate) {
		 if (this.totalOutAmountEver == null) this.totalOutAmountEver = new BigDecimal(0).setScale(2);
		 this.totalOutAmountEver = this.totalOutAmountEver.add(newCashOut);
		 return newCashOut;
	 }

	 protected void removeAmountFromTotalAmount(PortfolioShare portfolioShare) {
		 this.totalInAmountEver = this.totalInAmountEver.subtract(portfolioShare.getCashin());
		 this.totalOutAmountEver = this.totalOutAmountEver.subtract(portfolioShare.getCashout());
	 }

	 public BigDecimal getTotalInAmountEver() {
		 return totalInAmountEver;
	 }

	@SuppressWarnings("unused")
	private void setTotalInAmountEver(BigDecimal totalAmountInEver) {
		 this.totalInAmountEver = totalAmountInEver;
	 }

	 public BigDecimal getTotalOutAmountEver() {
		 return totalOutAmountEver;
	 }

	@SuppressWarnings("unused")
	private void setTotalOutAmountEver(BigDecimal totalOutAmountEver) {
		 this.totalOutAmountEver = totalOutAmountEver;
	 }
	 
	 @Transient
	 public CurrencyConverter getCurrencyConverter() {
		 return PortfolioMgr.getInstance().getCurrencyConverter();
	 }

}

