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
