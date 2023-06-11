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
package com.finance.pms.datasources.web.google;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;

public class MarketList {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(MarketList.class);
	
//	"start" : 0,
//	"num_company_results" : "3589",
//	"num_mf_results" : "24225",
//	"num_all_results" : "",
//	"original_query" : "((exchange:NYSE) OR (exchange:NASDAQ) OR (exchange:AMEX)) [(MarketCap \x3E 4989 | MarketCap = 4989) \x26 (MarketCap \x3C 2270000000000 | MarketCap = 2270000000000) \x26 (PE \x3E 0.21 | PE = 0.21) \x26 (PE \x3C 4663 | PE = 4663) \x26 (DividendYield \x3E 0 | DividendYield = 0) \x26 (DividendYield \x3C 328 | DividendYield = 328) \x26 (Price52WeekPercChange \x3E -99.81 | Price52WeekPercChange = -99.81) \x26 (Price52WeekPercChange \x3C 781 | Price52WeekPercChange = 781)]",
//	"query_for_display" : "((exchange:NYSE) OR (exchange:NASDAQ) OR (exchange:AMEX)) [(MarketCap &gt; 4989 | MarketCap = 4989) &amp; (MarketCap &lt; 2270000000000 | MarketCap = 2270000000000) &amp; (PE &gt; 0.21 | PE = 0.21) &amp; (PE &lt; 4663 | PE = 4663) &amp; (DividendYield &gt; 0 | DividendYield = 0) &amp; (DividendYield &lt; 328 | DividendYield = 328) &amp; (Price52WeekPercChange &gt; -99.81 | Price52WeekPercChange = -99.81) &amp; (Price52WeekPercChange &lt; 781 | Price52WeekPercChange = 781)]",
//	"results_type" : "COMPANY",
//	"results_display_type" : "TABLE",
//	"searchresults" : [
	
	Integer start;
	Integer num_company_results;
	Integer num_mf_results;
	String num_all_results;
	String original_query;
	String query_for_display;
	String results_type;
	String results_display_type;
	Collection<GoogleJSonStock> searchresults;
	
	@Override
	public String toString() {
		return start+":"+num_company_results+":"+num_all_results+":"+original_query+":"+query_for_display+":"+results_type+":"+results_display_type+":"+searchresults;
	}
	
	public List<Validatable> getStockList(Market market,StockCategories stockCategories,MarketQuotationProviders marketQuotationsProviders) {
		List<Validatable> list = new ArrayList<Validatable>();
		for (GoogleJSonStock googS : this.searchresults) {
			
			String cap = googS.columns.get(googS.columns.indexOf(new StockProps("MarketCap",null))).value;
			Pattern p = Pattern.compile("(\\d+\\.?\\d*)(\\w?)");
			Matcher matcher = p.matcher(cap);
			matcher.matches();
			BigDecimal capD;
			capD = new BigDecimal(matcher.group(1));
			if (matcher.groupCount() == 2) {
				switch(matcher.group(2).charAt(0)) {
				case 'T': capD = capD.multiply(new BigDecimal(1000));
				case 'B': capD = capD.multiply(new BigDecimal(1000));
				case 'M': capD = capD.multiply(new BigDecimal(1000000));
				break;
				default : if (LOGGER.isDebugEnabled()) LOGGER.debug("Oops");
				}
			}

			//String sector = googS.columns.get(googS.columns.indexOf(new StockProps("Sector",null))).value;

			try {
				Stock s = new Stock(
						googS.ticker,
						googS.ticker,
						googS.title,
						true,
						stockCategories, 
						new SymbolMarketQuotationProvider(marketQuotationsProviders,SymbolNameResolver.UNKNOWNEXTENSIONCLUE), 
						new MarketValuation(market),
						"",TradingMode.CONTINUOUS, capD.longValue());

				list.add(s);
			} catch (InvalidAlgorithmParameterException e) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("ReOops",e);
			}
		}
		
		return list;
	}	
	
	
	static class GoogleJSonStock {
		
//		"title" : "1-800-FLOWERS.COM, Inc.",
//		"id" : "663427",
//		"is_active" : "",
//		"ticker" : "FLWS",
//		"exchange" : "NASDAQ",
//		"is_supported_exchange" : "",
//		"columns" : [
		
		String title;
		Integer id;
		String is_active;
		String ticker;
		String exchange;
		String is_supported_exchange;
		List<StockProps> columns;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getIs_active() {
			return is_active;
		}
		public void setIs_active(String is_active) {
			this.is_active = is_active;
		}
		public String getTicker() {
			return ticker;
		}
		public void setTicker(String ticker) {
			this.ticker = ticker;
		}
		public String getExchange() {
			return exchange;
		}
		public void setExchange(String exchange) {
			this.exchange = exchange;
		}
		public String getIs_supported_exchange() {
			return is_supported_exchange;
		}
		public void setIs_supported_exchange(String is_supported_exchange) {
			this.is_supported_exchange = is_supported_exchange;
		}
		public Collection<StockProps> getColumns() {
			return columns;
		}
		public void setColumns(List<StockProps> columns) {
			this.columns = columns;
		}
		
		
	}
	
	static class StockProps implements Comparable<StockProps>{
		
		public StockProps() {
			
		}
		
		
		private StockProps(String field, String value) {
						super();
						this.field = field;
						this.value = value;
		}

//		"display_name": "",
//		"value" : "327.81M",
//		"field" : "MarketCap",
//		"sort_order" : ""
		String display_name;
		String value;
		String field;
		String sort_order;
		
		public String getDisplay_name() {
			return display_name;
		}
		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getSort_order() {
			return sort_order;
		}
		public void setSort_order(String sort_order) {
			this.sort_order = sort_order;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((field == null) ? 0 : field.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StockProps other = (StockProps) obj;
			if (field == null) {
				if (other.field != null)
					return false;
			} else if (!field.equals(other.field))
				return false;
			return true;
		}
		
		public int compareTo(StockProps o) {
			return this.field.compareTo(o.field);
		}
		
		
		
		
		
		
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getNum_company_results() {
		return num_company_results;
	}

	public void setNum_company_results(Integer num_company_results) {
		this.num_company_results = num_company_results;
	}

	public Integer getNum_mf_results() {
		return num_mf_results;
	}

	public void setNum_mf_results(Integer num_mf_results) {
		this.num_mf_results = num_mf_results;
	}

	public String getNum_all_results() {
		return num_all_results;
	}

	public void setNum_all_results(String num_all_results) {
		this.num_all_results = num_all_results;
	}

	public String getOriginal_query() {
		return original_query;
	}

	public void setOriginal_query(String original_query) {
		this.original_query = original_query;
	}

	public String getQuery_for_display() {
		return query_for_display;
	}

	public void setQuery_for_display(String query_for_display) {
		this.query_for_display = query_for_display;
	}

	public String getResults_type() {
		return results_type;
	}

	public void setResults_type(String results_type) {
		this.results_type = results_type;
	}

	public String getResults_display_type() {
		return results_display_type;
	}

	public void setResults_display_type(String results_display_type) {
		this.results_display_type = results_display_type;
	}

	public Collection<GoogleJSonStock> getSearchresults() {
		return searchresults;
	}

	public void setSearchresults(Collection<GoogleJSonStock> searchresults) {
		this.searchresults = searchresults;
	}
	
	
}
