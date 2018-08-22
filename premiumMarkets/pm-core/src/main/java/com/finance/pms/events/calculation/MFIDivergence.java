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

import java.util.List;
import java.util.Observer;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIDivergence extends OscillatorDivergenceCalculator {
	
	MFI mfi;

	public MFIDivergence(Integer timePeriod, Integer lowerThres, Integer upperThres, Observer... observers) {
		super(EventDefinition.PMMFIDIVERGENCE, observers);
		init(timePeriod, lowerThres, upperThres);
	}
	
    public MFIDivergence(EventInfo reference) {
        //Reflective ops generator
    	super(reference);
    }
    
    protected void init(Integer timePeriod, Integer lowerThres, Integer upperThres) {
        this.mfi = new MFI(timePeriod, lowerThres, upperThres);
    }
    
    @Override
    public void genericInit(Integer... constants) {
        init(constants[0], constants[1], constants[2]);
    }

	@Override
	protected double getOscillatorLowerThreshold() {
		return mfi.getLowerThreshold();
	}
	@Override
	protected double getOscillatorUpperThreshold() {
		return mfi.getUpperThreshold();
	}

	@Override
	protected double[] getOscillatorOutput() {
		return mfi.getMfi();
	}

	@Override
	protected MFI getOscillator() {
		return this.mfi;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MFI DATE, MFI QUOTE, MFI HigherLow, MFI LowerHigh, LOW TH, UP TH, MFI ,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
		
	}
	
	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		return new double[]
				{
					getOscillatorOutput()[getIndicatorIndexFromQuotationIndex(getOscillator(), idx)],
					getOscillatorLowerThreshold(),
					getOscillatorUpperThreshold()
				};
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof MFI) return this.isInDataRange((MFI)indicator, index);
		throw new RuntimeException("Boo",new Throwable());
	}
	
	public Boolean isInDataRange(MFI rsi, Integer index) {
		return getDaysSpan() <= index && index < rsi.getMfi().length;
	}

	@Override
	protected int getDaysSpan() {
		return 42;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMFIDIVERGENCE;
	}

	@Override
	protected Double getAlphaBalance() {
		return (double) (getDaysSpan()/4);
	}

}
