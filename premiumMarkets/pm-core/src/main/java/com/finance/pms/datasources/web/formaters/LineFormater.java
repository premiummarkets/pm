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
package com.finance.pms.datasources.web.formaters;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;




// TODO: Auto-generated Javadoc
/**
 * The Class LineFormater.
 * 
 * @author Guillaume Thoreton
 */
public abstract class LineFormater {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(LineFormater.class);
	
	
	/** The params. */
	protected List<Object> params;
	
	MyUrl myUrl;


	public LineFormater(MyUrl myUrl) {
		
		params = new ArrayList<Object>();
		this.myUrl = myUrl;
	}
	
	/**
	 * Format line.
	 * 
	 * @param line the line
	 * 
	 * @return the validatable
	 * 
	 * @throws StopParseException the stop parse exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract  List<Validatable> formatLine(String line) throws StopParseException;
	
    /**
     * Gets the url.
     * 
     * @return the url
     */
    public String getUrl() {
        return myUrl.getUrl();
    }
    
    /**
     * Gets the url.
     * 
     * @return the url
     */
    public MyUrl getMyUrl() {
        return myUrl;
    }
    
    public abstract Boolean canHaveEmptyResults();
    
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myUrl.getUrl()== null) ? 0 : myUrl.getUrl().hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LineFormater other = (LineFormater) obj;
		if (myUrl.getUrl() == null) {
			if (other.myUrl.getUrl() != null)
				return false;
		} else if (!myUrl.getUrl().equals(other.myUrl.getUrl()))
			return false;
		return true;
	}

	public List<Object> getParams() {
		return params;
	}

	//TODO use the Validatable state instead
	public Boolean isEmptyValue() {
		return false;
	}
    
    
}
