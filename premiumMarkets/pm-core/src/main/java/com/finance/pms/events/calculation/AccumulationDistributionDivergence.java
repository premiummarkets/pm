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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import org.apache.commons.math3.stat.descriptive.rank.Max;

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
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class AccumulationDistributionDivergence extends TalibIndicatorsOperator {

    ChaikinLine chaikin;

    SMA priceSma65;
    SMA priceSma20;
    SMA chaikinSma65;
    SMA chaikinSma20;

    public AccumulationDistributionDivergence(Observer... observers) {
        super(EventDefinition.PMACCDISTDIVERGENCE, observers);
        init(20, 65, 20, 65);
    }
    
    public AccumulationDistributionDivergence(EventInfo reference) {
      //Reflective ops generator
    	super(reference);
    }

    protected void init(Integer fastPriceMAPeriod, Integer slowPriceMAPeriod, Integer fastChainkinMAPeriod, Integer slowChainkinMAPeriod) {
        this.chaikin = new ChaikinLine();
        this.priceSma20 = new SMA(fastPriceMAPeriod);
        this.priceSma65 = new SMA(slowPriceMAPeriod);
        this.chaikinSma20 = new SMA(fastChainkinMAPeriod);
        this.chaikinSma65 = new SMA(slowChainkinMAPeriod);
    }


    @Override
    public void genericInit(Integer... periods) {
        init(periods[0], periods[1], periods[2], periods[3]);
    }

    @Override
    protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

        FormulatRes res = new FormulatRes(EventDefinition.PMACCDISTDIVERGENCE);
        res.setCurrentDate(qU.getDate());

        double close = qU.getClose().doubleValue();

        int chainkinIndex = getIndicatorIndexFromQuotationIndex(this.chaikin, quotationIdx);
        int chainkinSma20Index = getIndicatorIndexFromQuotationIndex(this.chaikinSma20, quotationIdx);
        int chainkinSma65Index = getIndicatorIndexFromQuotationIndex(this.chaikinSma65, quotationIdx);

        int sma20Index = getIndicatorIndexFromQuotationIndex(this.priceSma20, quotationIdx);
        int sma65Index = getIndicatorIndexFromQuotationIndex(this.priceSma20, quotationIdx);

        Boolean isPriceBelowSma65 = close < priceSma65.getSma()[sma65Index];
        Boolean isPriceBelowSma20 = close < priceSma20.getSma()[sma20Index];
        Boolean isChaikinBelowSma65 = chaikin.getChaikinLine()[chainkinIndex] < chaikinSma65.getSma()[chainkinSma65Index];
        Boolean isChaikinBelowSma20 = chaikin.getChaikinLine()[chainkinIndex] < chaikinSma20.getSma()[chainkinSma20Index];

        {
            res.setBullishCrossOver(isPriceBelowSma65 && isPriceBelowSma20 && !isChaikinBelowSma65 && !isChaikinBelowSma20); 
            if (res.getBullishCrossOver()) return res;

        }
        {
            res.setBearishCrossBellow(!isPriceBelowSma65 && !isPriceBelowSma20 && isChaikinBelowSma65 && isChaikinBelowSma20);
            return res;

        }

    }

    @Override
    protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
        if (indicator instanceof ChaikinLine) return this.isInDataRange((ChaikinLine)indicator, indicatorIndex);
        if (indicator instanceof SMA) return this.isInDataRange((SMA) indicator, indicatorIndex);
        throw new RuntimeException("Booo",new Throwable());

    }


    private boolean isInDataRange(SMA sma, Integer index) {
        return getDaysSpan() <= index && index < sma.getSma().length;
    }

    private Boolean isInDataRange(ChaikinLine chaikin, Integer index) {
        return (getDaysSpan() < index) && (index < chaikin.getChaikinLine().length);
    }

    @Override
    protected String getHeader(List<Integer> scoringSmas) {
        //		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin DATE, Chainkin, bearish, bullish";
        String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin, bearish, bullish";
        head = addScoringHeader(head, scoringSmas);
        return head+"\n";
    }

    @Override
    protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects){
        Date calculatorDate = qU.getDate();
        EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate, EventDefinition.PMACCDISTDIVERGENCE, EventType.BEARISH));
        EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate, EventDefinition.PMACCDISTDIVERGENCE, EventType.BULLISH));
        BigDecimal calculatorClose = qU.getClose();

        int chaikinIndex = getIndicatorIndexFromQuotationIndex(this.chaikin, calculatorIndex);
        //		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);

        String line =
                new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
                //			+ this.chaikin.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + ","
                + this.chaikin.getChaikinLine()[chaikinIndex];

        if (bearishEventValue != null) {
            line = line + ","+calculatorClose+",0,";
        } else if (bullishEventValue != null) {
            line = line + ",0,"+calculatorClose+",";
        } else {
            line = line + ",0,0,";
        }

        line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";

        return line;
    }

    @Override
    protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx){

        return new double[]
                {
                        this.chaikin.getChaikinLine()[getIndicatorIndexFromQuotationIndex(this.chaikin, idx)]
                };
    }

    @Override
    protected int getDaysSpan() {
        return 1;
    }


    @Override
    public EventDefinition getEventDefinition() {
        return EventDefinition.PMACCDISTDIVERGENCE;
    }


    @Override
    protected void initIndicators(Quotations quotations) throws TalibException {

        this.chaikin.calculateIndicator(quotations);

        this.priceSma65.calculateIndicator(quotations);
        this.priceSma20.calculateIndicator(quotations);

        this.chaikinSma65.calculateIndicator(new CalculationQuotations(quotations.getStock(), quotations.getTargetCurrency(), chaikin.indicatorStrip(quotations), chaikin.quotationValidity()));
        this.chaikinSma20.calculateIndicator(new CalculationQuotations(quotations.getStock(), quotations.getTargetCurrency(), chaikin.indicatorStrip(quotations), chaikin.quotationValidity()));

    }


    @Override
    public Integer getStartShift() {
        return chaikinSma65.getStartShift() + chaikin.getStartShift() + getDaysSpan();
    }

    @Override
    public ValidityFilter quotationsValidity() {
        return ValidityFilter.OHLCV;
    }

    @Override
    public Integer getOutputBeginIdx() {
        Max max = new Max();
        double[] ds = new double[]{(double)chaikin.getOutBegIdx().value, (double)priceSma20.getOutBegIdx().value, (double)priceSma65.getOutBegIdx().value, (double)chaikinSma20.getOutBegIdx().value, (double)chaikinSma65.getOutBegIdx().value};
        double maximum = max.evaluate(ds);
        return (int) maximum + getDaysSpan();
    }

}
