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

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.CalculationQuotations;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.ChaikinLine;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.OBV;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

@Deprecated
public class ObvDivergence_old extends TalibIndicatorsOperator {
	
	private static final int SMOOTHING = 2;
	
	private OBV obv;
	private SMA obvSma;
	private ChaikinLine chaikinLine;
	private SMA sma;

	private Quotations quotationsCopy;
	
	public ObvDivergence_old(Observer... observers) {
		super(EventDefinition.PMOBVDIVERGENCEOLD, observers);
		init(SMOOTHING, SMOOTHING);
	}
	
    public ObvDivergence_old(EventInfo reference) {
        //Reflective ops generator
    	super(reference);
    }
    
    protected void init(Integer signalSmaPeriod, Integer smoothSmaPeriod) {
        this.obv = new OBV();
        this.chaikinLine = new ChaikinLine();
        this.sma = new SMA(signalSmaPeriod);
        this.obvSma = new SMA(smoothSmaPeriod);
    }
    
    @Override
    public void genericInit(Integer... constants) {
        init(constants[0], constants[1]);
    }

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());
		
		int obvIdx = getIndicatorIndexFromQuotationIndex(this.obv, quotationIdx);
		double[] obvLookBackP = Arrays.copyOfRange(this.obv.getObv(), obvIdx - getDaysSpan(), obvIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(quotationsCopy.getCloseValues(), quotationIdx - getDaysSpan(), quotationIdx);
		
		int smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		int obvSmaIndex = getIndicatorIndexFromQuotationIndex(this.obvSma, quotationIdx);
		double[] obvLookBackPThresh = Arrays.copyOfRange(this.obvSma.getSma(), obvSmaIndex - getDaysSpan(), obvSmaIndex);
		
		{
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isObvUp = higherLow(obvLookBackP, obvLookBackPThresh) > 0;
			res.setBullishCrossOver(isPriceDown && isObvUp); 
			
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isObvDown = lowerHigh(obvLookBackP, obvLookBackPThresh);
			res.setBearishCrossBellow(isPriceUp && isObvDown);
		
			return res;
		}
	}
	
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, index);
		if (indicator instanceof OBV) return this.isInDataRange((OBV)indicator, index);
		if (indicator instanceof ChaikinLine) return this.isInDataRange((ChaikinLine)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return getDaysSpan() <= index && index < sma.getSma().length;
	}
	
	public Boolean isInDataRange(OBV obv, Integer index) {
		return getDaysSpan() <= index && index < obv.getObv().length;
	}
	
	private boolean isInDataRange(ChaikinLine chaikinLine, Integer index) {
		return getDaysSpan() <= index && index < chaikinLine.getChaikinLine().length;
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMOBVDIVERGENCEOLD, EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMOBVDIVERGENCEOLD, EventType.BULLISH));
		BigDecimal calculatorClose = qU.getCloseSplit();

		int chaikinIndex = getIndicatorIndexFromQuotationIndex(this.chaikinLine, calculatorIndex);
//		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		int obvIndex = getIndicatorIndexFromQuotationIndex(this.obv, calculatorIndex);
//		int obvQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, obvQuotationStartDateIdx);
		int obvSmaIndex = getIndicatorIndexFromQuotationIndex(this.obvSma, calculatorIndex);
//		int obvSmaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(obvQuotationIndex, obvSmaQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," // + this.sma.getSma()[smaIndex] + "," 
//			+ this.chaikinLine.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + 
			+ "," + this.chaikinLine.getChaikinLine()[chaikinIndex] + ","
//			+ this.obvSma.getIndicatorQuotationData().get(obvSmaQuotationIndex).getDate()+ "," 
			+ this.obvSma.getSma()[obvSmaIndex] + "," 
//			+ this.obv.getIndicatorQuotationData().get(obvQuotationIndex).getDate() + "," 
			+ this.obv.getObv()[obvIndex];
		
		if (bearishEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null) {
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearExpects)+"\n";
		
		return line;
	}
	
	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		
		int obvIndex = getIndicatorIndexFromQuotationIndex(this.obv, idx);
		
		return new double[]
				{
					this.obv.getObv()[obvIndex]
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chaikin Date, Chaikin, OBV SMA DATE, OBV SMA, OBV DATE, OBV, bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chaikin, OBV SMA, OBV, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMOBVDIVERGENCEOLD;
	}

	@Override
	public Integer getStartShift() {
		return Math.max(chaikinLine.getStartShift(), Math.max(obv.getStartShift()+obvSma.getStartShift(), sma.getStartShift())) + getDaysSpan();
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		
		this.quotationsCopy = quotations;
		
		this.obv.calculateIndicator(quotations);
		this.sma.calculateIndicator(quotations);
		this.chaikinLine.calculateIndicator(quotations);
	
		//FIXME this.obvSma.calculateIndicator(new CalculationQuotations(quotations.getStock(), quotations.getTargetCurrency(), obv.indicatorStrip(quotations), obv.quotationValidity()));
		
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.OHLCV;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return  Math.max(chaikinLine.getOutBegIdx().value, Math.max(obv.getOutBegIdx().value + obvSma.getOutBegIdx().value, sma.getOutBegIdx().value)) + getDaysSpan();
	}

}
