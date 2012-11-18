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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.CurrencyConverter;
import com.finance.pms.portfolio.Transaction.TransactionType;



/**
 * The Class Portfolio.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name="PORTFOLIO_NAME")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
public abstract class AbstractSharesList extends Observable {

	protected static MyLogger LOGGER = MyLogger.getLogger(AbstractSharesList.class);

	/** The name. */
	protected String name;
	private BigDecimal totalInAmountEver;
	private BigDecimal totalOutAmountEver;

	protected Map<Stock, PortfolioShare> listShares;

	
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
	
	/**
	 * Removes the share.
	 * 
	 * @param portfolioShare the ps
	 * 
	 * @author Guillaume Thoreton
	 */
	private void removeShare(PortfolioShare portfolioShare) {
		listShares.remove(portfolioShare.getStock());
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
	 

	/**
	 * @param recipientPortfolioShare
	 * @param quantity
	 * @param buyDate
	 * @param lastQuotation
	 * @param movement 
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidQuantityException 
	 */
	private void shareTransaction(PortfolioShare recipientPortfolioShare, BigDecimal quantity, Date buyDate, BigDecimal lastQuotation, TransactionType movement) throws InvalidQuantityException {
		Transaction transaction = new Transaction(recipientPortfolioShare.getCashin(), recipientPortfolioShare.getCashout(), quantity, lastQuotation, movement, buyDate);
		recipientPortfolioShare.applyTransaction(transaction, true);
		recipientPortfolioShare.setBuyDate(buyDate);
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

	/**
	 * @param stock
	 * @param currentDate
	 * @param mLevel
	 * @param transactionCurrency
	 * @return
	 */
	protected PortfolioShare getOrCreatePortfolioShare(Stock stock, Date currentDate, MonitorLevel mLevel, Currency transactionCurrency) {
		
		PortfolioShare portfolioShare = getShareForSymbolAndIsin(stock.getSymbol(), stock.getIsin());
		if (portfolioShare == null) {
			portfolioShare = new PortfolioShare(this, stock, currentDate, mLevel, transactionCurrency);
			listShares.put(stock, portfolioShare);
		}
		return portfolioShare;
	}


	public void removeOrUpdateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException {
		
		if (this.getShareForSymbolAndIsin(portfolioShare.getSymbol(), portfolioShare.getIsin()) == null) {
			throw new InvalidQuantityException("The share "+portfolioShare+" is not in the portfolio "+this, new Throwable());
		}
		
		shareTransaction(portfolioShare, quantity, currentDate, trPrice, trType);
		
		if (portfolioShare.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
			this.removeShare(portfolioShare);
		} else {
			portfolioShare.addBuyAlerts(trPrice, currentDate);
		}
		
	}
	

	public PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date currentDate, BigDecimal buyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException {
		
		if (quantity.compareTo(BigDecimal.ZERO) == 0 || buyPrice.compareTo(BigDecimal.ZERO) == 0) {
			throw new InvalidQuantityException("Invalid Quantity : "+quantity+"; or buy price : "+buyPrice+" for "+stock, new Exception());
		}
		PortfolioShare portfolioShare = getOrCreatePortfolioShare(stock, currentDate, mLevel, trCurrency);
		shareTransaction(portfolioShare, quantity, currentDate, buyPrice, trType);
		portfolioShare.addBuyAlerts(buyPrice, currentDate);
		
		return portfolioShare;

	}

}

