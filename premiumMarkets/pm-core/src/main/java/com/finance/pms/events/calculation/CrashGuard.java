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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;
/**
 * 
 * @author guil
 * TODO Main indexes sma 10 negative/positive for 15 days in a row
 *
 */
public class CrashGuard extends TalibIndicatorsCompositionCalculator {
	
	private static final int DAYS_SPAN = 10;
	
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public CrashGuard(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);

	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.CRASHGUARD);
		
		Integer smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		
		//BEAR : sma fell 10 % over the days span period  
		{	
			double diff = this.sma.getSma()[smaIndex] - this.sma.getSma()[smaIndex - getDaysSpan()];
			double rate = diff / this.sma.getSma()[smaIndex - getDaysSpan()];
			
			boolean sharpSMAFAll = (rate <= -0.15); 
			
			res.setBearishCrossBellow(sharpSMAFAll);	
			
		}
		
		return res;
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return getDaysSpan() <= indicatorIndex && indicatorIndex < (this.sma.getSma().length - getDaysSpan());
	}

	@Override
	protected int getDaysSpan() {
		return  DAYS_SPAN;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.CRASHGUARD;
	}

	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}

