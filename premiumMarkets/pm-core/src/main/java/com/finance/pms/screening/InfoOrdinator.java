package com.finance.pms.screening;

import java.util.Collection;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.finance.pms.screening.TrendSupplementedStock.MyBigDec;

public class InfoOrdinator extends FullRatingOrdinator {

	public InfoOrdinator(Collection<TrendSupplementedStock> listOfShares, Date end) {
		super(listOfShares, end);
	}
	
	@Override
	public NavigableSet<TrendSupplementedStock> filterCredible() {
		
		NavigableSet<TrendSupplementedStock> ret = new TreeSet<TrendSupplementedStock>(new FullRatingCompartor());
		
		for (TrendSupplementedStock trendedStock : listOfShares) {
			MyBigDec fullRating = trendedStock.fullRating();
			if (fullRating.isValid() && fullRating.getValue().compareTo(CREDIBLE_SELL_THRESHOLD) < 0) {
				ret.add(trendedStock);
			}
		}
		
		return ret;
	}

}
