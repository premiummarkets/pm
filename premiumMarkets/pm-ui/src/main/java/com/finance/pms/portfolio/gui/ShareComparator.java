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
package com.finance.pms.portfolio.gui;

import java.util.Comparator;


// TODO: Auto-generated Javadoc
/**
 * The Class ShareComparator.
 * 
 * @author Guillaume Thoreton
 */
public class ShareComparator implements Comparator<SlidingPortfolioShare> {

	/**
	 * Instantiates a new share comparator.
	 * 
	 * @author Guillaume Thoreton
	 */
	public ShareComparator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(T, T)
	 */
	public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
		SlidingPortfolioShare p1 = o1;
		SlidingPortfolioShare p2 = o2;
		int retour = -(p1.getUnrealizedProfit().compareTo(p2.getUnrealizedProfit()));
		if (retour == 0) retour = -(p1.getQuantity().compareTo(p2.getQuantity()));
		return retour;
	}

}
