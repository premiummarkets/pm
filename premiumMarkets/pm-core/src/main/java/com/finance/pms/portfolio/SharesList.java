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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

/**
 * Used for market share list storage. Every stock should only be in one of these at a time.
 * The 'UNKNOW' SharesList being used to store shares not in a known market.
 * 
 * @author Guillaume Thoreton
 *
 */
@Entity
@DiscriminatorValue("ShareList")
public class SharesList extends AbstractSharesList {

	@SuppressWarnings("unused")
	private SharesList() {
		super();
	}

	public SharesList(String name) {
		super(name);
	}

	public void addShares(Collection<Stock> pssToAdd) {
		for (Stock stock : pssToAdd) {
			addShare(stock);
		}
	}

	public void addShare(Stock stock) {
		PortfolioShare newPortfolioShare = new PortfolioShare(this, stock, MonitorLevel.NONE, stock.getMarketValuation().getCurrency());
		addShareToList(newPortfolioShare);
		ShareListMgr.getInstance().addForeignKeysUpdate(newPortfolioShare);
	}

	public void removeShare(PortfolioShare portfolioShare) {
		ShareListMgr.getInstance().removeForeignKeysUpdate(portfolioShare);
		removeShareFromList(portfolioShare);
	}

	public void removeShares(Set<PortfolioShare> pssToRemove) {
		for (PortfolioShare ps : pssToRemove) {
			removeShare(ps);
		}
	}

	public Set<Stock> toStocksSet() {
		Set<Stock> retSet = new HashSet<Stock>();
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			retSet.add(portfolioShare.getStock());
		}
		return retSet;
	}

	@Override
	public Date getLastDateTransactionFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return new Date(0);
	}

	@Override
	public BigDecimal getQuantityFor(PortfolioShare portfolioShare,Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getBasisFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getPriceAvgBuyFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return BigDecimal.ONE;
	}
	
	@Override
	public BigDecimal getPriceAvgSellFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return BigDecimal.ZERO;
	}

	@Override
	public InOutWeighted getInflatWeightedInvestedFor(PortfolioShare portfolioShare, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return new InOutWeighted(BigDecimal.ONE, BigDecimal.ZERO, currentEndDate);
	}

	@Override
	public SortedSet<TransactionElement> getTransactionsFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return new TreeSet<TransactionElement>();
	}

	@Override
	public BigDecimal getPriceUnitCostFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency, Boolean isLatestOnly) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getCashInFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getCashOutFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency targetCurrency, Boolean isLatestOnly, Boolean isRealisedOnly) {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getGainAnnualisedPercentFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getGainReinvestedPercentFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return BigDecimal.ZERO;
	}

	@Override
	protected BigDecimal getGainBuyNHoldPercentFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Boolean isLatestOnly) {
		return BigDecimal.ZERO;
	}

}
