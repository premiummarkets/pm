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

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.MarketListProvider;
import com.finance.pms.datasources.web.ProvidersList;


public abstract class ShareListMgr {
	
	public static final double PERCENT_THRESHOLD = 30d;

	protected static MyLogger LOGGER = MyLogger.getLogger(ShareListMgr.class);
	
	
	@Autowired
	PortfolioDAO portfolioDAO;
	
	@Autowired
	ShareDAO shareDAO;
	
	public static ShareListMgr getInstance() {
		return (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
	}
	
	public synchronized void updateShareListsContent() throws Exception {
		
		try {
			List<String> shareListNames = portfolioDAO.loadShareListNames(null, new String[]{SharesListId.UNKNOWN.getSharesListCmdParam().toUpperCase()});

			for (String shareListName : shareListNames) {
				Set<Indice> indices = Indice.parseString(shareListName);
				if (indices.size() == 1 || indices.size() == 0) { //No composite yahoo share lists or other share lists
					SharesList sharesList = PortfolioMgr.getInstance().getPortfolioDAO().loadShareList(shareListName);
					updateShareListContent(sharesList);
				} 
			}
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;
		}
	}

	private StockList updateShareListContent(SharesList sharesList) {
		
		MarketListProvider provider = null;
		String[] shareListNameSplit = sharesList.getName().split(",");
		
		try {
			
			if (shareListNameSplit.length == 1) {// Yahoo Indices list
				provider = ProvidersList.getMarketListInstance(SharesListId.valueOf(sharesList.getName()).getSharesListCmdParam());
			} else {// No indices => Not yahooIndices
				SortedSet<Indice> indices = Indice.parseString(sharesList.getName());
				provider = ProvidersList.getInstance(SharesListId.valueOf(shareListNameSplit[0]).getSharesListCmdParam(), indices);
			}
			
			return ((MarketListProvider) provider).retrieveStockListFromWeb(MarketQuotationProviders.DEFAULT, new StockList(shareDAO.loadAllStocks()));
			
		} catch (IllegalArgumentException e) {
			LOGGER.warn(sharesList.getName()+ " is not associated with any MarketListProvider and won't be updated.", true);
			return null;
		} catch (Exception e) {
			LOGGER.error("Can't update "+sharesList.getName(),e);
			return null;
		}
		 
	}

	protected abstract boolean removeForeignKeysUpdate(PortfolioShare removedShare);

	protected abstract boolean addForeignKeysUpdate(PortfolioShare newPortfolioShare);
	
	//XXX this should be merge with the config loader in a separate bean  + creation of a factory factory for EventConfg and TuningConfg
	public abstract EventSignalConfig initPkgDependentConfig();


}
