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
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.screening.ScreeningSupplementedStock.MyBigDec;

public class FullRatingOrdinator implements ScreenerCalculator<NavigableSet<ScreeningSupplementedStock>> {
	
	public static final int TREND_OUTDATE_LIMIT = 6;
	//The lower the full rating the better
	static final BigDecimal CREDIBLE_SELL_THRESHOLD = new BigDecimal(MainPMScmd.getPrefs().get("trend.sellthreshold","100.00"));
	static final BigDecimal CREDIBLE_BUY_THRESHOLD = new BigDecimal(MainPMScmd.getPrefs().get("trend.buythreshold","0.00"));
	
	private static MyLogger LOGGER = MyLogger.getLogger(ScreeningSupplementStockExporter.class);
	
	protected final class FullRatingCompartor implements Comparator<ScreeningSupplementedStock> {
		
		public int compare(ScreeningSupplementedStock o2, ScreeningSupplementedStock o1) {
			MyBigDec o1r = o1.fullRating();
			MyBigDec o2r = o2.fullRating();
			int ret =  (o2r).compareTo(o1r);
			if (ret == 0) return o2.getStock().getSymbol().compareTo(o1.getStock().getSymbol());
			return ret;
		}
	}

	Collection<ScreeningSupplementedStock> listOfShares;
	private Date endDate;
	private Double invalidPerCentage;
    private Collection<ScreeningSupplementedStock> invalidTrends;  
    private Double notToBefoundPerCentage;
    private Collection<ScreeningSupplementedStock> notToBeFoundTrends;
    private Double notCrediblePerCentage;
    private Collection<ScreeningSupplementedStock> notCredibleTrends;  
    private Double staledPerCentage;
    private Collection<ScreeningSupplementedStock> staledTrends;  
    private Double ignoredPerCentage;
    private Collection<ScreeningSupplementedStock> ignoredTrends;
    
	private NavigableSet<ScreeningSupplementedStock> filtered;  

	public FullRatingOrdinator(Collection<ScreeningSupplementedStock> listOfShares, Date end) {
		super();
		this.listOfShares = listOfShares;
		this.endDate = end;
		this.invalidTrends = new ConcurrentSkipListSet<ScreeningSupplementedStock>();
		this.notCredibleTrends = new ConcurrentSkipListSet<ScreeningSupplementedStock>();;
		this.notToBeFoundTrends = new ConcurrentSkipListSet<ScreeningSupplementedStock>();;
		this.staledTrends = new ConcurrentSkipListSet<ScreeningSupplementedStock>();;
		this.ignoredTrends = new ConcurrentSkipListSet<ScreeningSupplementedStock>();;
		
	}

	public NavigableSet<ScreeningSupplementedStock> calculate() {
		filtered = filterCredible();
		return filtered;
	}

	private NavigableSet<ScreeningSupplementedStock> filterCredible() {
		
		final NavigableSet<ScreeningSupplementedStock> ret = new ConcurrentSkipListSet<ScreeningSupplementedStock>(new FullRatingCompartor());
		
		final Calendar staleDateLimit = Calendar.getInstance();
		staleDateLimit.setTime(endDate);
		staleDateLimit.add(Calendar.MONTH, -TREND_OUTDATE_LIMIT);
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("trendeventscalculation.semaphore.nbthread","20")));
		for (final ScreeningSupplementedStock trendedStock : listOfShares) {
			
			Runnable runnable = new Runnable() {
				public void run() {
					MyBigDec fullRating = trendedStock.fullRating();
					if (isCredibleTrend(staleDateLimit, trendedStock, fullRating)) {
						ret.add(trendedStock);
					} else if (!fullRating.isValid()) {
						LOGGER.warn("Trend info is not to be found " + trendedStock);
						FullRatingOrdinator.this.notToBeFoundTrends.add(trendedStock);
					} else if (fullRating.getValue().compareTo(CREDIBLE_BUY_THRESHOLD) <= 0 || fullRating.getValue().compareTo(CREDIBLE_SELL_THRESHOLD) >= 0) {
						LOGGER.warn("Trend info is not credible for " + trendedStock);
						notCredibleTrends.add(trendedStock);
					} else if (!trendedStock.getTrendDate().after(staleDateLimit.getTime())) {
						LOGGER.warn("Trend info is staled for " + trendedStock);
						staledTrends.add(trendedStock);
					} else if (!trendedStock.priceChangeTTM().isValid() || trendedStock.priceChangeTTM().getValue().compareTo(new BigDecimal(-0.10)) <= 0) {
						LOGGER.warn("Trend info is ignored because of poor recent perfs for " + trendedStock + " or no quotations.");
						FullRatingOrdinator.this.ignoredTrends.add(trendedStock);
					} else {
						LOGGER.warn("Trend info is not valid for other reasons for " + trendedStock);
						invalidTrends.add(trendedStock);
					}
				}
			};
			executor.submit(runnable);
			
		}
		executor.shutdown();
		
		try {
			boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow, new Exception());
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow, e);
		}
		
		Double totalTrends = new Double(listOfShares.size());
		notToBefoundPerCentage = new Double(notToBeFoundTrends.size()) / totalTrends;
		notCrediblePerCentage = new Double(notCredibleTrends.size()) / totalTrends;
		staledPerCentage = new Double(staledTrends.size()) / totalTrends;
		ignoredPerCentage = new Double(ignoredTrends.size()) / totalTrends;
		invalidPerCentage = new Double(invalidTrends.size()) / totalTrends;
		
		return ret;
	}

	protected boolean isCredibleTrend(Calendar staleDateLimit, ScreeningSupplementedStock trendedStock, MyBigDec fullRating) {
		
		return 
		fullRating.isValid() && trendedStock.priceChangeTTM().isValid()
		&& fullRating.getValue().compareTo(CREDIBLE_BUY_THRESHOLD) > 0 && fullRating.getValue().compareTo(CREDIBLE_SELL_THRESHOLD) < 0 
		&& trendedStock.getTrendDate().after(staleDateLimit.getTime())
		&& !( fullRating.getValue().compareTo(BigDecimal.ZERO) > 0  && trendedStock.priceChangeTTM().getValue().compareTo(new BigDecimal(-0.10)) <= 0 );
		
	}
	
	public Double getInvalidPerCentage() {
		return invalidPerCentage;
	}

	public Collection<ScreeningSupplementedStock> getInvalidTrends() {
		return invalidTrends;
	}

	public Double getNotToBefoundPerCentage() {
		return notToBefoundPerCentage;
	}

	public Collection<ScreeningSupplementedStock> getNotToBeFoundTrends() {
		return notToBeFoundTrends;
	}

	public Double getNotCrediblePerCentage() {
		return notCrediblePerCentage;
	}

	public Collection<ScreeningSupplementedStock> getNotCredibleTrends() {
		return notCredibleTrends;
	}

	public Double getStaledPerCentage() {
		return staledPerCentage;
	}

	public Collection<ScreeningSupplementedStock> getStaledTrends() {
		return staledTrends;
	}

	public Double getIgnoredPerCentage() {
		return ignoredPerCentage;
	}

	public Collection<ScreeningSupplementedStock> getIgnoredTrends() {
		return ignoredTrends;
	}
	
	
	
}
