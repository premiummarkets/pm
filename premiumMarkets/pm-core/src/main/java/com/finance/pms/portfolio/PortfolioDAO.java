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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Stock;


public interface PortfolioDAO {

	//All portfolios
	public void saveOrUpdatePortfolio(AbstractSharesList portfolio);

	//User portfolio
	public List<Portfolio> loadVisiblePortfolios();
	public List<String> loadUserPortfolioNames();
	AutoPortfolio loadAutoPortfolio(String autoPortfolioName);

	//Share lists
	public void delete(AbstractSharesList portfolio);
	public SharesList loadShareList(String listName);
	public List<String> loadValidShareListNames();
	public List<String> loadShareListNames(String[] include, String[] exclude);

	//Transaction reports
	public void saveOrUpdateTransactionReports(ArrayList<TransactionElement> reportElements);
	public SortedSet<TransactionElement> loadOrphanTransactionReportFor(Stock stock, String account, Date date);
	public void deleteALLTransactionReports();
	public void deleteOrphanTransactionReportsFor(String externalAccount);

	//PortfolioShare
	public void saveOrUpdatePortfolioShare(PortfolioShare portfolioShare);
	public PortfolioShare loadPortfolioShare(String symbol, String isin, String portfolioName);
	public List<PortfolioShare> loadPortfolioShareForStock(Stock stock);
	public Set<PortfolioShare> loadIndicesSharesListContentExUnknown();
	Set<PortfolioShare> loadSharesListContent(String[] include, String[] exclude);
	public void deletePortfolioShare(PortfolioShare portfolioShare);

	//Independent share lists
	public IndepShareList loadIndepShareList(String shareListName);
	public List<String> loadIndepShareListNames();

}