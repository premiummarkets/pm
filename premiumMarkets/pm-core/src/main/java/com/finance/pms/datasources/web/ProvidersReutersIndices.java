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
package com.finance.pms.datasources.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.http.HttpException;
import org.python.google.common.collect.Maps;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StockListReutersIndicesHtmlScrapFormater;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.ScreeningSupplementedStock;

public class ProvidersReutersIndices extends ProvidersList {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersReutersIndices.class);

	private SortedSet<Indice> indices;

	protected ProvidersReutersIndices() {
		super();
		this.indices = new TreeSet<Indice>();
	}

	public ProvidersReutersIndices(String pathToProps) {
		super();
		this.httpSource = new HttpSourceReutersIndices(pathToProps, this);
		this.indices = new TreeSet<Indice>();
	}

	public ProvidersReutersIndices(String pathToProps, List<Indice> indices) {
		this(pathToProps);
		this.indices = new TreeSet<Indice>(indices);
	}

	@Override
	public MarketQuotationProviders defaultMarketQuotationProviders() {
		return MarketQuotationProviders.YAHOO;
	}

	@Override
	public SharesList loadSharesListForThisListProvider() {
		String extention = Indice.formatSet(indices);
		return super.initSharesList(this.getSharesListIdEnum().name(), extention);
	}

	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return new StockListReutersIndicesHtmlScrapFormater(url, new MarketValuation(market));
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	public Stock supplement(Stock stock) {
		return stock; //FIXME => find data as Yahoo doesn't provide relevant info anymore eg isin??
	}

	@Override
	protected Comparator<Stock> getNewStockComparator() {
		return new Comparator<Stock>() {

			public int compare(Stock o1, Stock o2) {
				return o1.getSymbol().compareTo(o2.getSymbol());
			}

		};
	}

	@SuppressWarnings("unchecked")
	@Override//TODO merge with nse indices => create market indices related Provider : probably add an interface alike MarketListProvider
	protected Map<Stock, Double> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException {

		TreeMap<Stock, Double> listFromWeb = new TreeMap<Stock, Double>(getNewStockComparator());

		for (Indice indice : indices) {

			for (Integer i = 0; i < 100; i++) {

				String url = this.httpSource.getCategoryStockListURL(StockCategories.INDICES_OTHER, "." + indice.getName(), i.toString());
				LOGGER.info("Reuters Indice Url : " + url);

				@SuppressWarnings("rawtypes")
				List listOfIndiceStocks = new ArrayList();
				try {
					LineFormater reutersIndiceFormater = this.getFormater(url, indice.getMarket(), marketQuotationsProviders);
					listOfIndiceStocks = ((HttpSourceMarket) this.httpSource).readURL(reutersIndiceFormater);
				} catch (HttpException e) {
					LOGGER.error(e, e);
					throw e;
				} catch (Exception e) {
					LOGGER.error(e, e);
				}

				listFromWeb.putAll(Maps.asMap(new TreeSet<Stock>(listOfIndiceStocks), k -> Double.valueOf(0)));
				if (listOfIndiceStocks.size() <= 1) break;

			}
		}

		return listFromWeb;
	}

	@Override
	public void retrieveScreeningInfoForShare(ScreeningSupplementedStock trendSupStock) {
		//Nothing
	}

	public void setIndices(SortedSet<Indice> indices) {
		this.indices = indices;
	}

	@Override
	public SortedSet<Indice> getIndices() {
		return indices;
	}

	@Override
	public void addIndices(SortedSet<Indice> indices, Boolean replace) {
		if (replace) {
			this.indices=indices;
		} else {
			for (Indice indice : indices) {
				this.indices.add(indice);
			}
		}

		MainPMScmd.getMyPrefs().put("quotes.listproviderindices",Indice.formatSet(this.indices));
	}
}
