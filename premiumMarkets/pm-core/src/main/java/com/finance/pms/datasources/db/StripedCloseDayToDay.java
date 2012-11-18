package com.finance.pms.datasources.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.portfolio.PortfolioShare;

public class StripedCloseDayToDay extends StripedCloseFunction {
	
	private SortedMap<Date, double[]> houseDerivation;
	private Date startDate;
	private Date endDate;

	@Override
	public void targetShareData(PortfolioShare ps, Quotations stockQuotations) {

		startDate = getStartDate(stockQuotations);
		endDate = getEndDate(stockQuotations);

		startDateQuotationIndex = stockQuotations.getClosestIndexForDate(0,startDate);
		endDateQuotationIndex = stockQuotations.getClosestIndexForDate(startDateQuotationIndex, endDate);

		SortedMap<Date, double[]> data = new TreeMap<Date, double[]>();
		List<QuotationUnit> quotationUnits = stockQuotations.getQuotationUnits(startDateQuotationIndex, endDateQuotationIndex);
		for (QuotationUnit quotationUnit : quotationUnits) {
			data.put(quotationUnit.getDate(), new double[]{quotationUnit.getClose().doubleValue()});
		}

		TalibSmaSmoother smaSmoother = new TalibSmaSmoother(84);
		SortedMap<Date, double[]> smoothed = smaSmoother.smooth(data, false);

		HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother();
		houseDerivation = houseTrendSmoother.smooth(smoothed, false);

	}

	@Override
	public Double[] relativeCloses() {
		
		List<Double> ret = new ArrayList<Double>();
		SortedMap<Date, double[]> tailMap = houseDerivation.tailMap(startDate);
		
		double root = tailMap.get(tailMap.firstKey())[0];
		Set<Date> keySet = tailMap.keySet();
		for (Iterator<Date> iterator = keySet.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			if (date.after(endDate)) break;
			ret.add(houseDerivation.get(date)[0] - root);
		}
		
		return ret.toArray(new Double[0]);
	}

}
