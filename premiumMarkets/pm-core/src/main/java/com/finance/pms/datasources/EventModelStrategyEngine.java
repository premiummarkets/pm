/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
package com.finance.pms.datasources;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The Interface EventRefreshModelDelegate.
 * 
 * @author Guillaume Thoreton
 */
public interface EventModelStrategyEngine {

	/**
	 * Sets the last list fetch.
	 * 
	 * @param newLastListFetch the new last list fetch
	 * @param oldLastListFetch the old last list fetch
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastListFetch(Date newLastListFetch, Date oldLastListFetch);
	public abstract Date getLastListFetch(Date oldLastListFetch);

	/**
	 * Sets the last quotation fetch.
	 * 
	 * @param newLastQuotationFetch the new last quotation fetch
	 * @param oldLastQuotationFetch the old last quotation fetch
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastQuotationFetch(Date newLastQuotationFetch,Date oldLastQuotationFetch);
	public abstract Date getLastQuotationFetch(Date oldLastQuotaitonFetch);

	/**
	 * Sets the last analyse.
	 * 
	 * @param newLastAnalyse the new last analyse
	 * @param oldLastAnalyse the old last analyse
	 * 
	 * @return the date
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract Date setLastAnalyse(Date newLastAnalyse,Date oldLastAnalyse);
	public abstract Date getLastAnalyse(Date oldLastAnalyse);


	public abstract void callbackForlastListFetch(Set<Observer> engineObservers);
	
	public abstract void  callbackForReco(Set<Observer> engineObservers);

	public abstract void callbackForlastQuotationFetch(Set<Observer> engineObservers, Object...viewStateParams);

	public abstract void callbackForlastAnalyse(ArrayList<String> analisysList, Date startAnalyseDate, Set<Observer> engineObservers, Object...viewStateParams);

}