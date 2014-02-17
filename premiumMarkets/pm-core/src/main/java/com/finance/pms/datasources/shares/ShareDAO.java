/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.screening.ScreeningSupplementedStock;

public interface ShareDAO {
	
	
	public List<Stock> loadShares(ShareFilter shareFilter);
	
	public Stock loadStockBy(String symbol, String isin);
	public Stock loadStockByIsin(String isin);
	public Stock loadStockByIsinOrSymbol(String ref);

	public void saveOrUpdateStockTrendInfo(Set<ScreeningSupplementedStock> listTrends);
	public ScreeningSupplementedStock loadTrendForStock(Stock stock);

	public void saveOrUpdateStock(Stock stock);
	public void saveOrUpdateStocks(Set<Stock> listStocks);
	public void saveOrUpdateStocks(List<Validatable> shares);

	
	public Collection<Stock> loadAllUserPortoflioStocks();
	public Collection<Stock> loadAllPortoflioStocks();
	public List<Stock> loadMonitoredStocks();
	public List<Stock> loadAllStocks();
	
	public List<PortfolioShare> loadMonitoredPortfolioShares();
	
	public List<String> sectorHintList();

	public List<Stock> loadSharesLike(String like, int maxResults);

	public List<PortfolioShare> loadMonitoredWithAOE(Stock stock, SortedMap<EventKey, EventValue> sortedDataResultMap);

	void saveOrUpdateQuotationUnit(QuotationUnit quotationUnit);
	void saveOrUpdateQuotationUnits(List<QuotationUnit> quotationUnits);
	
}
