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
package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StockComplementBSEFormater;
import com.finance.pms.datasources.web.formaters.StockListNSEFormater;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.TrendSupplementedStock;

public class ProvidersNSEIndices extends ProvidersList {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersNSEIndices.class);
	
	private Set<Indice> indices;
	
	private ProvidersNSEIndices(String pathToProps) {
		super();
		this.httpSource = new HttpSourceNSEMarket(pathToProps, this);
		this.indices = new TreeSet<Indice>();
	}
	
	public ProvidersNSEIndices(String pathToProps,List<Indice> indices) {
		this(pathToProps);
		this.indices = new TreeSet<Indice>(indices);
	}
	
	@SuppressWarnings("unchecked") //TODO merge with yahoo indices => create market indices related Provider : probably add an interface alike MarketListProvider
	protected Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException {

		Set<Stock> listFromWeb = new TreeSet<Stock>(getNewStockComparator());

		for (Indice indice : indices) {

			String url = this.httpSource.getCategoryStockListURL(StockCategories.INDICES_OTHER, indice.getName());
			LOGGER.info("Indice Url : " + url);
			LineFormater nseFormater = this.getFormater(url, indice.getMarket(), marketQuotationsProviders);
			@SuppressWarnings("rawtypes")
			List listOfIndiceStocks = new ArrayList();
			try {
				listOfIndiceStocks = ((HttpSourceMarket) this.httpSource).readURL(nseFormater);
			} catch (HttpException e) {
				LOGGER.error(e, e);
				throw e;
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			listFromWeb.addAll(listOfIndiceStocks);
		}


		return listFromWeb;
	}
	

	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return new StockListNSEFormater(url, StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}


	@Override
	protected Stock supplement(Stock stock) {

		List<? extends Validatable> ltmp = new ArrayList<Validatable>();
		
		try {
			ltmp = getSymbol(stock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn("@d. Invalid symbol format when complementing.",e);
		}
		
		LOGGER.info("@d. Just fetched New ticker info : "+ stock +"; changes  : "+ ltmp);
	
		return (ltmp.size() != 0)?(Stock)ltmp.get(0):null;
	}


	/**
	 * @param stock
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private List<? extends Validatable> getSymbol(Validatable stock) throws UnsupportedEncodingException {
		
		String url = this.httpSource.getStockInfoPageURL(((Stock)stock).getIsin());
		StockComplementBSEFormater dsf = new StockComplementBSEFormater(url, (Stock) stock);
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
		return Market.NSE; 
	}

	@Override
	public SharesList loadSharesListForThisListProvider() {
		String extention = Indice.formatSet(indices);
		return super.initSharesList(this.getSharesListIdEnum().name(),extention);
	}

	@Override
	public void addIndice(Indice indice) {
		this.indices.add(indice);
		MainPMScmd.getPrefs().put("quotes.listproviderindices",Indice.formatSet(indices));
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		if (replace) {
			this.indices=indices;
		} else {
			for (Indice indice : indices) {
				this.indices.add(indice);
			}
		}
		
		MainPMScmd.getPrefs().put("quotes.listproviderindices",Indice.formatSet(this.indices));
	}
	
	@Override
	public Set<Indice> getIndices() {
		return indices;
	}


	@Override
	public void retrieveScreeningInfoForShare(TrendSupplementedStock trendSupStock) {
		//throw new NotImplementedException();
	}
	
	
}
