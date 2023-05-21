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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.http.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.ScreeningSupplementedStock;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProvidersStockSymbolApi extends ProvidersList {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersStockSymbolApi.class);

	private SortedSet<Indice> indices;

	private Path python_py;

	protected ProvidersStockSymbolApi() {
		super();
		this.indices = new TreeSet<Indice>();
	}

	public ProvidersStockSymbolApi(String pathToProps) {
		super();
		this.httpSource = new HttpSourceYahooIndices(pathToProps, this);
		this.indices = new TreeSet<Indice>();
		
		try {
			python_py = Files.createTempFile("stock-symbol", "py");
			try (InputStream stream = ProvidersStockSymbolApi.class.getResourceAsStream("/stockSymbolApi/stock-symbol.py")) {
				Files.copy(stream, python_py, StandardCopyOption.REPLACE_EXISTING);
				PosixFileAttributeView view = Files.getFileAttributeView(python_py, PosixFileAttributeView.class);
				if (view != null) {
					Set<PosixFilePermission> perms = view.readAttributes().permissions();
					if (perms.add(PosixFilePermission.OWNER_EXECUTE)) {
						view.setPermissions(perms);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
	
	public ProvidersStockSymbolApi(String pathToProps, List<Indice> indices) {
		this(pathToProps);
		this.indices = new TreeSet<Indice>(indices);
	}
	
	public void close() {
    	try {
			Files.delete(python_py);
		} catch (IOException e) {
			System.out.println("Error closing ProvidersStockSymbolApi: " + e);
			e.printStackTrace();
		}
    }
	
	@Override
	public MarketQuotationProviders defaultMarketQuotationProviders() {
		return MarketQuotationProviders.YAHOO;
	}
	
	@Override
	public SharesList loadSharesListForThisListProvider() {
		String extention = Indice.formatSet(indices);
		return super.initSharesList(this.getSharesListIdEnum().name(),extention);
	}

	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return null;
	}


	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	public Stock supplement(Stock stock) {
		return stock;
	}
	
	@Override
	protected Comparator<Stock> getNewStockComparator() {
		return new Comparator<Stock>() {
			
			public int compare(Stock o1, Stock o2) {
				return o1.getSymbol().compareTo(o2.getSymbol());
			}
			
		};
	}
	
	protected Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
		
		Set<Stock> listFromWeb = new TreeSet<Stock>(getNewStockComparator());
		
		for (Indice indice : indices) {
				
			String indiceStr = indice.getName(); //if All => None ??
			String countryStr = indice.getMarket().getCountry();
				
			try {
				String pythonExec = MainPMScmd.getMyPrefs().get("quotes.pythonPath", "python3");
				ProcessBuilder pb = new ProcessBuilder(pythonExec, python_py.toString(), 
						URLEncoder.encode(countryStr, StandardCharsets.UTF_8.toString()), URLEncoder.encode(indiceStr, StandardCharsets.UTF_8.toString()));
				Process p = pb.start();
			
				try (Reader in = new InputStreamReader(p.getInputStream());) {
					JsonArray symbolJsons = new JsonParser().parse(in).getAsJsonArray();
					for (JsonElement symbolJsonElement : symbolJsons) {
						JsonObject symbolJson = symbolJsonElement.getAsJsonObject();
						listFromWeb.add(
							new Stock(symbolJson.get("symbol").getAsString(), symbolJson.get("symbol").getAsString(), symbolJson.get("longName").getAsString(), true, StockCategories.DEFAULT_CATEGORY,
							new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO, SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
							new MarketValuation(indice.getMarket()), "", TradingMode.CONTINUOUS, 0L));
					}
				} catch (Exception e) {
					LOGGER.error("No data for " + indice + " from " + python_py + ". Is '" + indice.getMarket().getCountry() + "' a valid country?", e);
				}
				
			} catch (Exception e) {
				LOGGER.error(e, e);
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
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		ProvidersStockSymbolApi clone = (ProvidersStockSymbolApi) super.clone();
		clone.python_py = this.python_py;
		return clone;
	}
}
