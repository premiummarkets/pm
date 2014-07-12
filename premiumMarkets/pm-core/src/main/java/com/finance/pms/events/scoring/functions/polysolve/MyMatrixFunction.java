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
package com.finance.pms.events.scoring.functions.polysolve;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyMatrixFunction {

	public SortedMap<Date,double[]> polynomialReg(SortedMap<Date,double[]> data) {


		ArrayList<Pair> pairs = new ArrayList<Pair>();
		int j = 0;
		for (double[] value : data.values()) {
			pairs.add(new Pair(j,value[0]));
			j++;

		}

		ArrayList<Double> terms = MatrixFunctions.compute_coefficients(pairs, 2);
		
		SortedMap<Date,double[]> polReg = new TreeMap<Date, double[]>();
		Iterator<Date> dateIterator = data.keySet().iterator();
		for (Pair pair : pairs) {
			double regress = MatrixFunctions.regress(pair.x, terms);
			polReg.put(dateIterator.next(),new double[]{regress});
		}
		
		
		return polReg;
		
	}


}




