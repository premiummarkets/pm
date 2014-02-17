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
package com.finance.pms.datasources.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Query {

	private  List<Object>  vl;
	private  String query;
	

	public Query() {
	    vl = new ArrayList<Object>();
	}

	public Query(String query) {
	    vl = new ArrayList<Object>();
	    this.query= query;
	    
	}

	public Query(List<Object> params) {
	    vl = params;
	}

	public void addValuesList(List<Comparable<?>> valeurs) {
	    vl.addAll(valeurs);
	}

	public void addValue(Object valeur) {
	    vl.add(valeur);
	}

	public List<Object> getParameterValues() {
		return vl;
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
    	Iterator<Object> vlIt = vl.iterator();
    	while (vlIt.hasNext()) {
    		retour.append(vlIt.next());
    		if (vlIt.hasNext()) {
    			retour.append(";");
    		}	
    	}
    	return retour.toString();
    }
}