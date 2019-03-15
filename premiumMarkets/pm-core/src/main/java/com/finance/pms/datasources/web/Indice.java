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

import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Market;

public class Indice implements Comparable<Indice> {

	private static MyLogger LOGGER = MyLogger.getLogger(Indice.class);

	private String name;
	private Market market;

	public Indice(String indice, Market market) {
		super();
		this.name = indice;
		this.market = market;
	}

	public Indice(String indice, String market) {
		super();
		this.name = indice;
		this.market = Market.valueOf(market);
	}

	public static String formatSet(SortedSet<Indice> indices) {
		String extention = "";
		for (Indice indice : indices) {
			extention = extention+","+indice.getName()+":"+indice.getMarket();
		}
		return extention;
	}

	public static SortedSet<Indice> parseString(String yahooIndices) {
		SortedSet<Indice> listIndice = new TreeSet<Indice>();
		if (yahooIndices.isEmpty()) return listIndice;

		String[] indices = yahooIndices.split(",");
		for (String indice : indices) {
			try {
				String[] indiceElems = indice.split(":");
				if (indiceElems.length == 2) {
					listIndice.add(new Indice(indiceElems[0], indiceElems[1]));
				}
			} catch (Exception e) {
				LOGGER.warn("Invalid index : " + indice + ". " + e);
			}
		}
		return listIndice;
	}

	public String getName() {
		return name;
	}

	public Market getMarket() {
		return market;
	}

	@Override
	public String toString() {
		return this.getName()+":"+this.getMarket()+",";
	}



	public int compareTo(Indice o) {
		int nameCmp = name.compareTo(o.name);
		if (nameCmp == 0) {
			return market.compareTo(o.market);
		}
		return nameCmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((market == null) ? 0 : market.hashCode());
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
		Indice other = (Indice) obj;
		if (market != other.market)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
