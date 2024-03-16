/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.portfolio.gui.charts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;
import com.finance.pms.events.scoring.functions.InterpolatorSmoother;
import com.finance.pms.events.scoring.functions.LinearInterpolatorSmoother;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

public class StripedCloseLogRoc extends StripedCloseFunction {

	public static final int DEFAULTLOGROCSMTH = 50;

	private NumberFormat numberFormat = new DecimalFormat("0.############ \u2030");
	private Boolean rootAtZero;
	private int period;
	private Boolean normalize;

	public StripedCloseLogRoc(Date arbitraryStartDate, Date arbitraryEndDate, Boolean rootAtZero, int period, Boolean normalize) {
		super(arbitraryStartDate, arbitraryEndDate);
		this.rootAtZero = rootAtZero;
		this.period = period;
		this.normalize = normalize;
	}

	@Override
	public SortedMap<Date, Double> targetShareData(SlidingPortfolioShare ps, Quotations stockQuotations, MInteger startDateQuotationIndex, MInteger endDateQuotationIndex) {

		Date startDate = getStartDate(stockQuotations);
		Date endDate = getEndDate(stockQuotations);

		startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		endDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(startDateQuotationIndex.value, endDate);

		SortedMap<Date, double[]> data = new TreeMap<Date, double[]>();
		List<QuotationUnit> quotationUnits = stockQuotations.getQuotationUnits(startDateQuotationIndex.value, endDateQuotationIndex.value);
		for (QuotationUnit quotationUnit : quotationUnits) {
			data.put(quotationUnit.getDate(), new double[]{quotationUnit.getCloseSplit().doubleValue()});
		}

		try {
			
			InterpolatorSmoother interpolatorSmoother = new LinearInterpolatorSmoother();
			data = interpolatorSmoother.smooth(data, false);

			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(period);
			SortedMap<Date, double[]> smoothed = smaSmoother.smooth(data, false);

			HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother();
			SortedMap<Date, double[]> houseDerivation = houseTrendSmoother.smooth(smoothed, false);
			
			if (normalize) {
				Normalizer<double[]> normalizer = new Normalizer<>(double[].class, startDate, endDate, -1, 1, 0);
				houseDerivation = normalizer.normalised(houseDerivation);
			}

			startDate = (startDate.before(houseDerivation.firstKey()))? houseDerivation.firstKey() : startDate;
			startDateQuotationIndex.value = stockQuotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);

			return relativeCloses(startDate, endDate, houseDerivation);

		} catch (Exception e) {
			LOGGER.warn("Not enough data to calculate : " + stockQuotations.getStock());
			return new TreeMap<>();
		}

	}

	private SortedMap<Date, Double> relativeCloses(Date startDate, Date endDate, SortedMap<Date, double[]> houseDerivation) {

		SortedMap<Date, Double> ret = new TreeMap<>();

		SortedMap<Date, double[]> htTail = houseDerivation.tailMap(startDate);

		double root = (rootAtZero)? htTail.get(htTail.firstKey())[0] : 0d;
		Set<Date> keySet = htTail.keySet();
		for (Iterator<Date> iterator = keySet.iterator(); iterator.hasNext();) {
			Date date = iterator.next();
			if (date.after(endDate)) break;
			ret.put(date, houseDerivation.get(date)[0] - root);
		}

		return ret;
	}

	@Override
	public String lineToolTip() {
		return "change to previous day (log ROC)";
	}

	@Override
	public String formatYValue(Number yValue) {
		return numberFormat.format(yValue);
	}

	@Override
	public Boolean isRelative() {
		return true;
	}

	@Override
	public Date getArbitraryStartDateForCalculation(Stock stock) throws NoQuotationsException, NotEnoughDataException {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(arbitraryStartDate);
			return QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), calendar, -period).getTime();
		} catch (NotEnoughDataException | IllegalArgumentException e) { //IllegalArgumentException FIXME the date zero should be before 1950 not 1970
			LOGGER.warn(e);
			return DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
		}
	
	}
	
	@Override
	protected Date getStartDate(Quotations stockQuotations) {
		Date startDate = arbitraryEndDate;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(arbitraryStartDate);
			startDate = QuotationsFactories.getFactory().incrementDate(stockQuotations.getStock(), Arrays.asList(QuotationDataType.CLOSE), calendar, -period).getTime();
		} catch (NotEnoughDataException | IllegalArgumentException e) {
			LOGGER.warn(e);
			startDate = DataSource.getInstance().getFirstQuotationDateFromQuotations(stockQuotations.getStock());
		}
		startDate = (startDate.before(stockQuotations.getDate(0)))? stockQuotations.getDate(0) : startDate;
		if (LOGGER.isDebugEnabled()) LOGGER.debug("The start date is: " + startDate);
		return startDate;
	}
	
	

	@Override
	public NumberFormat getNumberFormat() {
		return numberFormat;
	}


}
