/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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

// TODO: Auto-generated Javadoc
/**
 * The Class Query.
 * 
 * @author Guillaume Thoreton
 */
public class Query {
	
	/** The vl. */
	private  List<Object>  vl;
	
	/** The query. */
	private  String query;
	
	/**
	 * Instantiates a new query.
	 * 
	 * @author Guillaume Thoreton
	 */
	public Query() {
	    vl = new ArrayList<Object>();
	}
	
	/**
	 * Instantiates a new query.
	 * 
	 * @param query the query
	 * 
	 * @author Guillaume Thoreton
	 */
	public Query(String query) {
	    vl = new ArrayList<Object>();
	    this.query= query;
	    
	}
	
	/**
	 * Instantiates a new query.
	 * 
	 * @param params the params
	 * 
	 * @author Guillaume Thoreton
	 */
	public Query(List<Object> params) {
	    vl = params;
	}
	
	/**
	 * Adds the values list.
	 * 
	 * @param valeurs the valeurs
	 * 
	 * @author Guillaume Thoreton
	 */
	public void addValuesList(List<Comparable<?>> valeurs) {
	    vl.addAll(valeurs);
	}
	
	/**
	 * Adds the value.
	 * 
	 * @param valeur the valeur
	 * 
	 * @author Guillaume Thoreton
	 */
	public void addValue(Object valeur) {
	    vl.add(valeur);
	}
	
	/**
	 * Gets the vl.
	 * 
	 * @return the vl
	 */
	public List<Object> getParameterValues() {
		return vl;
	}
    
    /**
     * Gets the query.
     * 
     * @return the query
     */
    public String getQuery() {
        return query;
    }
    
    /**
     * Sets the query.
     * 
     * @param query the new query
     */
    public void setQuery(String query) {
        this.query = query;
    }
    
    /**
     * Result parse.
     * 
     * @param retour the retour
     * @param rs the rs
     * 
     * @throws SQLException the SQL exception
     * 
     * @author Guillaume Thoreton
     */
    public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
    	throw new RuntimeException("resultParse must be overwritten");
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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