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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
/**
 * 
 * @author Guillaume Thoreton
 * TODO Main indexes sma 10 negative/positive for 15 days in a row
 *
 */
public class CrashGuard extends TalibIndicatorsOperator {
	
	private static final int DAYS_SPAN = 10;
	
	private SMA sma;
//	private Integer smaQuotationStartDateIdx;
	
	public CrashGuard(Integer signalSmaPeriod) {
		super(EventDefinition.CRASHGUARD);
        init(signalSmaPeriod);
    }

	public CrashGuard(Observer... observers) {
		super(EventDefinition.CRASHGUARD, observers);
		this.sma = new SMA(0); //?
	}

	public CrashGuard(EventInfo reference) {
	    //Reflective ops generator
		super(reference);
	}

	protected void init(Integer signalSmaPeriod) {
	    this.sma = new SMA(signalSmaPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
	    init(constants[0]);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		
		Integer smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		
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
	public EventDefinition getEventDefinition() {
		return EventDefinition.CRASHGUARD;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.sma.calculateIndicator(quotations);
		
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getStartShift() {
		return 0 + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return 0 + getDaysSpan();
	}

	
	
	
}

