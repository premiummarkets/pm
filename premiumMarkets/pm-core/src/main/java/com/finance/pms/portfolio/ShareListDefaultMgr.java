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

import org.springframework.beans.factory.annotation.Autowired;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.threads.ConfigThreadLocal;

public class ShareListDefaultMgr extends ShareListMgr {
	
	@Autowired
	PortfolioDAO portfolioDAO;
	
	@Override
	protected boolean removeForeignKeysUpdate(PortfolioShare toRemovePortfolioShare) {

		//Adding to the 'Unknown List' if not removing from there.
		SharesList unknownShareList = portfolioDAO.loadShareList(SharesListId.UNKNOWN.name());
		AbstractSharesList portfolioWeRemoveFrom = toRemovePortfolioShare.getPortfolio();
		if (!portfolioWeRemoveFrom.equals(unknownShareList)) {
			Stock stock = toRemovePortfolioShare.getStock();
			PortfolioShare unknownCounterpartPortfolioShare = unknownShareList.getListShares().get(stock);
			if (unknownCounterpartPortfolioShare == null) {
				unknownShareList.addShare(stock);
				unknownCounterpartPortfolioShare = unknownShareList.getListShares().get(stock);
				portfolioDAO.saveOrUpdatePortfolioShare(unknownCounterpartPortfolioShare);
			}
			return true;
		}
		return false;
	
	}
	
	@Override
	protected boolean addForeignKeysUpdate(PortfolioShare toAddPortfolioShare) {
		
		//Removing from 'Unknown List' if we are not adding there
		SharesList unknownShareList = portfolioDAO.loadShareList(SharesListId.UNKNOWN.name());
		AbstractSharesList portfolioWeAddTo = toAddPortfolioShare.getPortfolio();
		if (!portfolioWeAddTo.equals(unknownShareList)) {
			Stock stock = toAddPortfolioShare.getStock();
			PortfolioShare unknownCounterpartPortfolioShare = unknownShareList.getListShares().get(stock);
			if (unknownCounterpartPortfolioShare != null) {
				//This is may be a new 'Share list' ?
				portfolioDAO.saveOrUpdatePortfolio(portfolioWeAddTo);
				unknownShareList.removeShare(unknownCounterpartPortfolioShare);
			}
			return true;
		}
		return false;
	}

	@Override
	public EventSignalConfig initPkgDependentConfig() {
		
		EventSignalConfig config;
		try {
			config = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		} catch (IllegalArgumentException e) {
			config = new EventSignalConfig();
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);
		}
		
		return config;
	}



}
