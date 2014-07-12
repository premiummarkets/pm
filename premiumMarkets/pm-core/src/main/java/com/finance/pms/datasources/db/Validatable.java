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
package com.finance.pms.datasources.db;

import java.io.Serializable;


/**
 * The Class Validatable.
 * 
 * @author Guillaume Thoreton
 */
public abstract class Validatable implements Serializable, Comparable<Validatable> {
	

	private static final long serialVersionUID = 6963504463049602627L;
	

	public static final int VALID=0;
	public static final int TOUPDATENAME =1;
	public static final int TOUPDATESYMBOL =2;
	public static final int TOUPDATEISIN =3;
	public static final int INVALIDE=4;
	
	private int state;
	
	public Validatable() {
		super();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public abstract Query toDataBase();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);
	
}
