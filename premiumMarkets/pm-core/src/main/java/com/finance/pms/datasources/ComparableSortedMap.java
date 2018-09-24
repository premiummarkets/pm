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
package com.finance.pms.datasources;

import java.util.SortedMap;
import java.util.TreeMap;

public class ComparableSortedMap<K extends Comparable<K>, V extends Comparable<V> > extends TreeMap<K,V> implements Comparable<TreeMap<K,V>> {

	private static final long serialVersionUID = -363699288012620328L;

	public ComparableSortedMap(SortedMap<K,V> values) {
		super(values);
	}

	public ComparableSortedMap() {
		super();
	}

	@Override
	//XXX
	public int compareTo(TreeMap<K,V> o) {
		int size = new Integer(this.size()).compareTo(new Integer(o.size()));
		if (size != 0) {
			return size;
		} else {
			boolean equals = this.equals(o);
			if (equals) {
				return 0;
			} else {
				TreeMap<K,V> left = new TreeMap<K,V>(this);
				TreeMap<K,V> right = new TreeMap<K,V>(o);
				for(K k : left.keySet()) {
					V ov = right.get(k);
					if (ov == null) return 1;
					int compareTo = left.get(k).compareTo(ov);
					if (compareTo != 0) return compareTo;
				}
				return 0;
			}
		}
	}
}