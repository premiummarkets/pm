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
package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class SmaTransformator<T> {
	
	private Class<T> genType;

	private int destPeriod;
	private int originPeriod;	

	public SmaTransformator(Class<T> genType, int originPeriod, int destPeriod) {
		super();
		this.genType = genType;
		
		this.destPeriod = destPeriod;
		this.originPeriod = originPeriod;
		
		if (destPeriod < originPeriod || destPeriod%originPeriod != 0) {
			throw new NotImplementedException("We need : destPeriod >= originPeriod && destPeriod%originPeriod == 0 ");
		}
			
	}

	public SortedMap<Date, T> transform(SortedMap<Date, T>  smaOrigin) {

		List<T> originValues = new ArrayList<T>();
		originValues.addAll(smaOrigin.values());

		List<T> destValues = new ArrayList<T>();
		for (int i = destPeriod-1; i < originValues.size(); i++) {

			Double destValueAti = 0d;
			for (int j = i-destPeriod + originPeriod -1; j < i; j = j + originPeriod) {
				destValueAti = destValueAti + valueOf(originValues.get(j));
			}

			destValues.add(tOf(destValueAti/(destPeriod/originPeriod)));
		}

		SortedMap<Date, T> destSma = new TreeMap<Date, T>();
		int cpt = 0;
		for (Date date : smaOrigin.keySet()) {
			if (cpt >= destPeriod) {
				destSma.put(date, destValues.get(cpt-destPeriod));
			}
			cpt++;
		}
		
		return destSma;

	}



	@SuppressWarnings("unchecked")
	private T tOf(Double destValueAti) {
		
		if (genType.isAssignableFrom(Double.class)) {
			return (T) destValueAti;
		} else if (genType.isArray() && genType.getComponentType().equals(Double.TYPE)) {
			return (T) new double[]{destValueAti};
		} else throw new NotImplementedException();
		
 	}



	private Double valueOf(T t) {
		
		if (t instanceof Double) {
			return (Double) t;
		} else if (t.getClass().isArray() &&t.getClass().getComponentType().equals(Double.TYPE)){
			return ((double[]) t)[0];
		} else throw new NotImplementedException();
		
	}


}
