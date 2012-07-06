/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.finance.pms.datasources.shares.Stock;

/**
 * @author Guillaume Thoreton
 * Used for market share list storage
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
	
	public void addShares(Collection<Stock> listStocks) {
		for (Stock stock:listStocks) {
			this.rawAddShare(stock);
		}
	}
	
	public void addShare(Stock stock) {
			this.rawAddShare(stock);
	}
	
	public Set<Stock> toStocksSet() {
		Set<Stock> retSet = new HashSet<Stock>();
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			retSet.add(portfolioShare.getStock());
		}
		return retSet;
	}
	
	public SortedSet<Stock> toSortedStocksSet() {
		SortedSet<Stock> retSet = new TreeSet<Stock>(new Comparator<Stock>() {
			public int compare(Stock o1, Stock o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		for (PortfolioShare portfolioShare : this.getListShares().values()) {
			retSet.add(portfolioShare.getStock());
		}
		
		return retSet;
	}

	private PortfolioShare rawAddShare(Stock stock) {
		PortfolioShare portfolioShare = new PortfolioShare(this, stock, BigDecimal.ONE, new Date(0), BigDecimal.ONE, BigDecimal.ZERO, MonitorLevel.NONE, stock.getMarket().getCurrency());
		listShares.put(stock,portfolioShare);
		return portfolioShare;
	}

}
