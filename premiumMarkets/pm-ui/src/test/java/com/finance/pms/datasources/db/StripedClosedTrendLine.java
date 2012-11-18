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
package com.finance.pms.datasources.db;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.ExponentialSmoother;
import com.finance.pms.portfolio.PortfolioShare;

public class StripedClosedTrendLine extends StripedCloseFunction {
	 
	private double alpha = 0.024;
	private double beta = 0.05;
	private SortedMap<Date, double[]> trendLine;

	@Override
	public void targetShareData(PortfolioShare ps, Quotations stockQuotations) {
		
		ExponentialSmoother exponentialSmoother = new ExponentialSmoother(alpha, beta);
		
		try {
			SortedMap<Date, double[]> data =  QuotationsFactories.getFactory().buildMapFromQuotations(stockQuotations);
			exponentialSmoother.smooth(data, false);
			trendLine = exponentialSmoother.getTrendLine();
			
		} catch (NotEnoughDataException e) {
			trendLine = new TreeMap<Date, double[]>();
			LOGGER.warn(e,e);
		}

	}

	@Override
	public Number[] relativeCloses() {
		// TODO Auto-generated method stub
		return null;
	}

}
