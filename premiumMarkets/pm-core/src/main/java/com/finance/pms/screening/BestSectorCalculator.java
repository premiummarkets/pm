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
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.finance.pms.events.calculation.InvalidParameterException;

public class BestSectorCalculator implements ScreenerCalculator<NavigableSet<Sector>> {
	
	Collection<ScreeningSupplementedStock> listOShares;

	public BestSectorCalculator(Collection<ScreeningSupplementedStock> listOShares) {
		super();
		this.listOShares = listOShares;
	}

	public NavigableSet<Sector> calculate() {
		
		NavigableSet<Sector> ordered = new TreeSet<Sector>(new Comparator<Sector>() {

			public int compare(Sector o2, Sector o1) {
				BigDecimal o1r = o1.calculateFullRating();
				BigDecimal o2r = o2.calculateFullRating();
				int ret =  (o1r).compareTo(o2r);
				if (ret == 0) return o1.getSectorName().compareTo(o2.getSectorName());
			
				return ret;
			}
			
		});
		
		
		//TODO integrate with SectorKey Comparator
		Map<String,Sector>  mapped = new HashMap<String, Sector>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean containsKey(Object key) {
				return grabMappedKey((String)key) != null;
			}

			private String grabMappedKey(String key) {
				for (String keyPart : stripKey(key)) {
					 for (String mappedKey : this.keySet()) {
						 for (String mappedKeyPart : stripKey(mappedKey)){
							 if (mappedKeyPart.contains(keyPart) || keyPart.contains(mappedKeyPart)) {
								 return mappedKey;
							 }
						 }
					}
				}
				return null;
			}

			/**
			 * @param key
			 * @return
			 */
			private String[] stripKey(String key) {
				
				String transformedKey = key.replace("&amp;", "&").toLowerCase();
				transformedKey = transformedKey.replace("asset", "investment");
				transformedKey = transformedKey.replace("financial","investement");
				transformedKey = transformedKey.replace("insurance","investment");
				transformedKey = transformedKey.replace("banks", "investment");
				transformedKey = transformedKey.replace("finance", "investment");
				transformedKey = transformedKey.replace("trucking", "transport");
				transformedKey = transformedKey.replace("internet", "computer");
				transformedKey = transformedKey.replace("software", "computer");
				transformedKey = transformedKey.replace("toys", "computer");
				
				if (transformedKey.matches("^it$"))transformedKey = transformedKey.replace("it", "computer");
				if (transformedKey.matches(".* tires( |$)*")) transformedKey = transformedKey.replace("tires", "transportation");
				transformedKey = transformedKey.replace("transports", "transportation");
				transformedKey = transformedKey.replace("railroads", "transportation");
				transformedKey = transformedKey.replace("airlines", "transportation");
				
				transformedKey = transformedKey.replace("agencies", "");
				transformedKey = transformedKey.replace("services", "");
				transformedKey = transformedKey.replace("equipment", "");
				transformedKey = transformedKey.replace("product", "");
				transformedKey = transformedKey.replace("specialty", "");
				transformedKey = transformedKey.replace("administration", "");
	
				
				return transformedKey.split("( |&|and)");
				
			}

			@Override
			public Sector get(Object key) {
				String mappedKey = grabMappedKey((String) key);
				if (mappedKey != null) {
					return super.get(mappedKey);
				}
				throw new InvalidParameterException();
			}		
		};
		
		for (ScreeningSupplementedStock stockPerf : listOShares) {
			if (mapped.containsKey(stockPerf.getSectorHint())) {
				mapped.get(stockPerf.getSectorHint()).addStock(stockPerf);
			} else {
				Sector sector = new Sector(stockPerf.getSectorHint());
				sector.addStock(stockPerf);
				mapped.put(stockPerf.getSectorHint(), sector);
			}
		}
		
		ordered.addAll(mapped.values());
		
		return ordered;
	}
	
}
