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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Stock;


public interface PortfolioDAO {

	//User portfolio
	public List<PortfolioShare> loadPortfolioShareForStock(Stock stock);
	
	public void saveOrUpdatePortfolio(AbstractSharesList portfolio);
	
	public List<Portfolio> loadVisiblePortfolios();
	
	public List<String> loadUserPortfolioNames();
	
	//Share lists
	public void delete(AbstractSharesList portfolio);

	public SharesList loadShareList(String listName);

	public List<String> loadShareListNames();
	
	public List<String> loadShareListNames(String[] include, String[] exclude);
	
	public void deletePortfolioShare(PortfolioShare portfolioShare);

	
	//Transaction reports
	public void saveOrUpdateTransactionReports(ArrayList<TransactionElement> reportElements);

	public SortedSet<TransactionElement> loadTransactionReportFor(Stock stock, String account, Date date);

	public void deleteTransactionReports();

	public PortfolioShare loadPortfolioShare(String symbol, String isin, String portfolioName);

	List<SharesList> loadShareList();

}