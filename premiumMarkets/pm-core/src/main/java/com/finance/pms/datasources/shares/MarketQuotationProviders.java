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
package com.finance.pms.datasources.shares;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

/**
 * The Enum MarketQuotationProviders.
 * 
 * @author Guillaume Thoreton
 */
@Embeddable
@AccessType("field") 
public class MarketQuotationProviders {

	private static MyLogger LOGGER = MyLogger.getLogger(MarketQuotationProviders.class);
	
	public static final MarketQuotationProviders DEFAULT = new MarketQuotationProviders("DEFAULT", ProvidersTypes.YAHOO, new YahooSymbolNameResolver(), "yahoo");
	public static final MarketQuotationProviders YAHOO = new MarketQuotationProviders("YAHOO", ProvidersTypes.YAHOO, new YahooSymbolNameResolver(), "yahoo");
	public static final MarketQuotationProviders GOOGLE = new MarketQuotationProviders("GOOGLE", ProvidersTypes.GOOGLE, new GoogleSymbolNameResolver(), "google");
	public static final MarketQuotationProviders INVESTIR = new MarketQuotationProviders("INVESTIR", ProvidersTypes.INVESTIR, new InvestirSymbolNameResolver(), "investir");
	public static final MarketQuotationProviders INFLATION = new MarketQuotationProviders("INFLATION", ProvidersTypes.INFLATION, new InvestirSymbolNameResolver(), "inflation");
	public static final MarketQuotationProviders CURRENCY = new MarketQuotationProviders("CURRENCY", ProvidersTypes.CURRENCY, new InvestirSymbolNameResolver(), "currency");
	public static final MarketQuotationProviders DIXHUITDIXHUIT = new MarketQuotationProviders("DIXHUITDIXHUIT", ProvidersTypes.DIXHUITDIXHUIT, new InvestirSymbolNameResolver(), "dixHuitDixHuit");
	
	private static List<MarketQuotationProviders> marketQuotationProviders = new ArrayList<MarketQuotationProviders>();
	static {
		MarketQuotationProviders.addMarketQuotationProvider(DEFAULT);
		MarketQuotationProviders.addMarketQuotationProvider(YAHOO);
		MarketQuotationProviders.addMarketQuotationProvider(GOOGLE);
		MarketQuotationProviders.addMarketQuotationProvider(INVESTIR);
		MarketQuotationProviders.addMarketQuotationProvider(INFLATION);
		MarketQuotationProviders.addMarketQuotationProvider(CURRENCY);
		MarketQuotationProviders.addMarketQuotationProvider(DIXHUITDIXHUIT);
	}
	
	private String name;
	
	@Transient
	private SymbolNameResolver symbolNameResolver;
	@Transient
	private String cmdParam;
	
	//Hib
	@SuppressWarnings("unused")
	private MarketQuotationProviders() {
		super();
	}

	public MarketQuotationProviders(String name, ProvidersTypes providersType, SymbolNameResolver marketQuotationProviderExtentions, String cmdParam) {
		this.name = name;
		this.symbolNameResolver = marketQuotationProviderExtentions;
		this.cmdParam = cmdParam;
	}
	
	public static MarketQuotationProviders[] values() {
		return MarketQuotationProviders.marketQuotationProviders.toArray(new MarketQuotationProviders[]{});
	}
	
	public static MarketQuotationProviders valueOf(String name) {
		for (MarketQuotationProviders marketQuotationProvider : MarketQuotationProviders.marketQuotationProviders) {
			if (marketQuotationProvider.name().equals(name)) return marketQuotationProvider;
		}
		//throw new IllegalArgumentException();
		LOGGER.warn("Unknown quotation provider : "+name);
		return MarketQuotationProviders.DEFAULT;
	}
	
	public static void addMarketQuotationProvider(MarketQuotationProviders marketListProvider) {
		if (marketListProvider != null && !MarketQuotationProviders.marketQuotationProviders.contains(marketListProvider)) {
			MarketQuotationProviders.marketQuotationProviders.add(marketListProvider);
		}
	}
	
	public static MarketQuotationProviders valueOfCmd(String cmdParam) {
		MarketQuotationProviders eValues[] = MarketQuotationProviders.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].cmdParam.equals(cmdParam)) return eValues[i];
		}
		throw new IllegalArgumentException("No enum const ProvidersTypes." + cmdParam);
	}
	
	public String name() {
		return this.name;
	}

	public static boolean checkProvider(String prov) {
		boolean contains = false;
		try {
			MarketQuotationProviders.valueOfCmd(prov);
			contains = true;
		} catch (IllegalArgumentException e) {
			LOGGER.debug("Wrong provider :" + prov, e);
			contains = false;
		}
		return contains;
	}

	public static String[] cmdsValues() {
		String[] retour = new String[MarketQuotationProviders.values().length];
		for (int i=0; i < MarketQuotationProviders.values().length; i++) {
			retour[i] = MarketQuotationProviders.values()[i].cmdParam;
		}
		return retour;	
	}

	public String getCmdParam() {
		if (cmdParam == null) {
			lateInitByCopy();
		}
		return cmdParam;
	}

	private void lateInitByCopy() {
		MarketQuotationProviders valueOf = MarketQuotationProviders.valueOf(this.name());
		cmdParam = valueOf.getCmdParam();
		symbolNameResolver = valueOf.getSymbolNameResolver();
	}

	public SymbolNameResolver getSymbolNameResolver() {
		if (symbolNameResolver == null) {
			lateInitByCopy();
		}
		return symbolNameResolver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MarketQuotationProviders other = (MarketQuotationProviders) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
