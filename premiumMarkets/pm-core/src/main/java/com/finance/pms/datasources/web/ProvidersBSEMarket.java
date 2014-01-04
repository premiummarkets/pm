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
package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StockComplementBSEFormater;
import com.finance.pms.datasources.web.formaters.StockListBSEFormater;
import com.finance.pms.screening.ScreeningSupplementedStock;

public class ProvidersBSEMarket extends ProvidersMarket {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersBSEMarket.class);
	
	private ProvidersBSEMarket(String pathToProps) {
		super();
		this.httpSource = new HttpSourceBSEMarket(pathToProps, this);
	}
	

	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return new StockListBSEFormater(httpSource.getCategoryStockListURL(StockCategories.DEFAULT_CATEGORY), StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}


	@Override
	protected Stock supplement(Stock stock) {

		List<? extends Validatable> ltmp = new ArrayList<Validatable>();
		
		try {
			ltmp = getSymbol(stock,stock.getName());
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn("@d. Invalid symbol format when complementing.",e);
		}
		
		if (ltmp.size() == 0) {
			String splat = stock.getName().split(" ")[0];
			LOGGER.warn("@d. Couldn't find stock symbol at first : "+ stock +"; changes  : "+ ltmp + ". Now trying : "+splat);
			if (!splat.equals(stock.getName()))
				try {
					ltmp = getSymbol(stock,splat);
				} catch (UnsupportedEncodingException e) {
					LOGGER.warn("@d. Invalid symbol format when complementing.",e);
				}

		}
		LOGGER.info("@d. Just fetched New ticker info : "+ stock +"; changes  : "+ ltmp);
	
		return (ltmp.size() != 0)?(Stock)ltmp.get(0):null;
	}


	/**
	 * @param stock
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private List<? extends Validatable> getSymbol(Validatable stock,String searchName) throws UnsupportedEncodingException {
		
		String url = this.httpSource.getStockInfoPageURL(searchName);
		StockComplementBSEFormater dsf = new StockComplementBSEFormater(url, (Stock)stock);
		List<? extends Validatable> ltmp = new ArrayList<Validatable>();
		try {
			ltmp = this.httpSource.readURL(dsf);
		} catch (HttpException e) {
			LOGGER.error("",e);
		}
		return ltmp;
		
	}
	
	@Override
	protected Comparator<Stock> getNewStockComparator() {
		return new Comparator<Stock>() {

			
			public int compare(Stock o1, Stock o2) {
				return o1.compareTo(o2);
			}
			
		};
	}
	

	protected Market getMarket() {
		return Market.BSE; 
	}


	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void retrieveScreeningInfoForShare(ScreeningSupplementedStock trendSupStock) {
		//throw new NotImplementedException();
		
	}
	
	
}
