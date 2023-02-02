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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Query {

	private  List<Object> valueList;
	private  String query;
	

	public Query() {
	    valueList = new ArrayList<Object>();
	}

	public Query(String query) {
	    valueList = new ArrayList<Object>();
	    this.query = query;
	    
	}

	public Query(List<Object> params) {
	    valueList = params;
	}

	public void addValuesList(List<Comparable<?>> valeurs) {
	    valueList.addAll(valeurs);
	}

	public void addValue(Object valeur) {
	    valueList.add(valeur);
	}

	public List<Object> getParameterValues() {
		return valueList;
	}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
    	throw new RuntimeException("resultParse must be overwritten");
    }
    
    @Override
	public String toString() {
    	StringBuffer retour = new StringBuffer();
    	Iterator<Object> vlIt = valueList.iterator();
    	while (vlIt.hasNext()) {
    		retour.append(vlIt.next());
    		if (vlIt.hasNext()) {
    			retour.append(";");
    		}	
    	}
    	return retour.toString();
    }
}