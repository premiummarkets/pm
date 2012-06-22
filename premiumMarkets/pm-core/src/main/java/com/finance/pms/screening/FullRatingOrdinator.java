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
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;

public class FullRatingOrdinator implements ScreenerCalculator<NavigableSet<TrendSupplementedStock>> {
	
	public static final int TREND_OUTDATE_LIMIT = 6;
	static final BigDecimal CREDIBLE_SELL_THRESHOLD = new BigDecimal(MainPMScmd.getPrefs().get("trend.sellthreshold","-1.00"));
	static final BigDecimal CREDIBLE_BUY_THRESHOLD = new BigDecimal(MainPMScmd.getPrefs().get("trend.buythreshold","0.50"));
	
	private static MyLogger LOGGER = MyLogger.getLogger(TrendSupplementStockExporter.class);
	
	private final class FullRatingCompartor implements Comparator<TrendSupplementedStock> {
		
		public int compare(TrendSupplementedStock o2, TrendSupplementedStock o1) {
			BigDecimal o1r = o1.fullRating();
			BigDecimal o2r = o2.fullRating();
			int ret =  (o1r).compareTo(o2r);
			if (ret == 0) return o1.getStock().getSymbol().compareTo(o2.getStock().getSymbol());
			return ret;
		}
	}

	Collection<TrendSupplementedStock> listOfShares;
	private Date endDate;
	private Double invalidPerCentage;
    private Collection<TrendSupplementedStock> invalidTrends;  
    private Double notToBefoundPerCentage;
    private Collection<TrendSupplementedStock> notToBeFoundTrends;
    private Double notCrediblePerCentage;
    private Collection<TrendSupplementedStock> notCredibleTrends;  
    private Double staledPerCentage;
    private Collection<TrendSupplementedStock> staledTrends;  
    private Double ignoredPerCentage;
    private Collection<TrendSupplementedStock> ignoredTrends;  

	public FullRatingOrdinator(Collection<TrendSupplementedStock> listOfShares, Date end) {
		super();
		this.listOfShares = listOfShares;
		this.endDate = end;
		this.invalidTrends = new ArrayList<TrendSupplementedStock>();
		this.notCredibleTrends = new ArrayList<TrendSupplementedStock>();
		this.notToBeFoundTrends = new ArrayList<TrendSupplementedStock>();
		this.staledTrends = new ArrayList<TrendSupplementedStock>();
		this.ignoredTrends = new ArrayList<TrendSupplementedStock>();
		
	}

	public NavigableSet<TrendSupplementedStock> calculate() {
		
		NavigableSet<TrendSupplementedStock> ordered = new TreeSet<TrendSupplementedStock>(new FullRatingCompartor());
		ordered.addAll(listOfShares);
		return ordered;
	}

	public NavigableSet<TrendSupplementedStock> filterCredible() {
		
		NavigableSet<TrendSupplementedStock> ret = new TreeSet<TrendSupplementedStock>(new FullRatingCompartor());
		
		Calendar staleDateLimit = Calendar.getInstance();
		staleDateLimit.setTime(endDate);
		staleDateLimit.add(Calendar.MONTH, -TREND_OUTDATE_LIMIT);
		
		for (TrendSupplementedStock trendedStock : listOfShares) {
			BigDecimal fullRating = trendedStock.fullRating();
			if (	
					fullRating.compareTo(BigDecimal.ZERO) != 0 
					&& fullRating.compareTo(CREDIBLE_BUY_THRESHOLD) < 0 && fullRating.compareTo(CREDIBLE_SELL_THRESHOLD) > 0 
					&& trendedStock.getTrendDate().after(staleDateLimit.getTime())
					&& !( fullRating.compareTo(BigDecimal.ZERO) > 0  && trendedStock.quotePerfOverPeriod().compareTo(new BigDecimal(-0.10)) <= 0 )
					
			) {
				ret.add(trendedStock);
			} else if (fullRating.compareTo(BigDecimal.ZERO) == 0 ) {
				LOGGER.warn("Trend info is not to be found "+trendedStock);
				this.notToBeFoundTrends.add(trendedStock);
			} else if (fullRating.compareTo(CREDIBLE_BUY_THRESHOLD) >= 0 || fullRating.compareTo(CREDIBLE_SELL_THRESHOLD) <= 0 ) {
				LOGGER.warn("Trend info is not credible for "+trendedStock);
				notCredibleTrends.add(trendedStock);
			} else if (!trendedStock.getTrendDate().after(staleDateLimit.getTime())) {
				LOGGER.warn("Trend info is staled for "+trendedStock);
				staledTrends.add(trendedStock);
			} else if (trendedStock.quotePerfOverPeriod().compareTo(new BigDecimal(-0.10)) <= 0) {
				LOGGER.warn("Trend info is ignored because of poor recent perfs for "+trendedStock);
				this.ignoredTrends.add(trendedStock);
			} else {
				LOGGER.warn("Trend info is not valid for other reasons for "+trendedStock);
				invalidTrends.add(trendedStock);
			}
		}
		Double totalTrends = new Double(listOfShares.size());
		notToBefoundPerCentage = new Double(notToBeFoundTrends.size()) / totalTrends;
		notCrediblePerCentage = new Double(notCredibleTrends.size()) / totalTrends;
		staledPerCentage = new Double(staledTrends.size()) / totalTrends;
		ignoredPerCentage = new Double(ignoredTrends.size()) / totalTrends;
		invalidPerCentage = new Double(invalidTrends.size()) / totalTrends;
		return ret;
	}
	
	public Double getInvalidPerCentage() {
		return invalidPerCentage;
	}

	public Collection<TrendSupplementedStock> getInvalidTrends() {
		return invalidTrends;
	}

	public Double getNotToBefoundPerCentage() {
		return notToBefoundPerCentage;
	}

	public Collection<TrendSupplementedStock> getNotToBeFoundTrends() {
		return notToBeFoundTrends;
	}

	public Double getNotCrediblePerCentage() {
		return notCrediblePerCentage;
	}

	public Collection<TrendSupplementedStock> getNotCredibleTrends() {
		return notCredibleTrends;
	}

	public Double getStaledPerCentage() {
		return staledPerCentage;
	}

	public Collection<TrendSupplementedStock> getStaledTrends() {
		return staledTrends;
	}

	public Double getIgnoredPerCentage() {
		return ignoredPerCentage;
	}

	public Collection<TrendSupplementedStock> getIgnoredTrends() {
		return ignoredTrends;
	}
	
	
	
}
