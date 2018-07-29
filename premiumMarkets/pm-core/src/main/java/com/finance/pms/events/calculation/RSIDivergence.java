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
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;

public class RSIDivergence extends OscillatorDivergenceCalculator {
	
	RSI rsi;
	private Integer rsiQuotationStartDateIdx;
	
	public RSIDivergence(Integer rsiTimePeriod, Integer rsiUpperThreshold, Integer rsiLowerThreshold, Observer... observers) {
		super(EventDefinition.PMRSIDIVERGENCE, observers);
		this.rsi = new RSI(rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold);
	}
	
    public RSIDivergence(EventInfo reference) {
        //Reflective ops generator
    	super(reference);
    }
    
    protected void init(Integer rsiTimePeriod, Integer rsiUpperThreshold, Integer rsiLowerThreshold) {
        this.rsi = new RSI(rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold);
    }
    
    @Override
    public void genericInit(Integer... constants) {
        init(constants[0], constants[1], constants[2]);
    }

	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, index);
		if (indicator instanceof RSI) return this.isInDataRange((RSI)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return (getDaysSpan() <= index && index < sma.getSma().length);
	}
	
	public Boolean isInDataRange(RSI rsi, Integer index) {
		return (getDaysSpan() <= index && index < rsi.getRsi().length);
	}
	
	@Override
	protected int getDaysSpan() {
		return 42;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, RSI DATE, RSI QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
			
		return new double[]
				{
				this.rsi.getRsi()[getIndicatorIndexFromQuotationIndex(this.rsi, idx)],
				translateOutputForCharting(this.higherLows.get(idx)),
				translateOutputForCharting(this.lowerHighs.get(idx)),
				this.rsi.getLowerThreshold(),
				this.rsi.getUpperThreshold(),
				};
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMRSIDIVERGENCE;
	}


	@Override
	protected double getOscillatorLowerThreshold() {
		return this.rsi.getLowerThreshold();
	}

	@Override
	protected double getOscillatorUpperThreshold() {
		return this.rsi.getUpperThreshold();
	}


	@Override
	protected Integer getOscillatorQuotationStartDateIdx() {
		return rsiQuotationStartDateIdx;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return this.rsi.getRsi();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return this.rsi;
	}

	@Override
	protected Double getAlphaBalance() {
		return (double) (getDaysSpan()/4);
	}

}
