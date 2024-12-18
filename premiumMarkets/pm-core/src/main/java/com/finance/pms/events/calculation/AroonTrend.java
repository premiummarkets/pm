
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

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

//TODO this is not a TalibComposition if we use HouseAroon. Add an extra layer under EventCompositionCalulator to deal with house indicators
@Deprecated //Rewrite to match corresponding edited EventInfoOpsCompo 
public class AroonTrend extends TalibIndicatorsOperator {

    private int lowerThreshold;
    private int upperThreshold;
    private int middleThreshold;

    private HouseAroon aroon;

    public AroonTrend(Observer...observers) {
        super(EventDefinition.PMAROONTREND, observers);
        init(getDaysSpan(), -90, 90, 0);
    }

    public AroonTrend(EventInfo reference) {
        //Reflective ops generator
    	super(reference);
    }

    //TODO distinguish thresholds from periods if ever the thresholds can be output in the charts (using the target stock?)
    protected void init(Integer aroonPeriod, Integer lowerThreshold, Integer upperThreshold, Integer middleThreshold) {
        this.lowerThreshold = lowerThreshold;
        this.upperThreshold = upperThreshold;
        this.middleThreshold = middleThreshold;
        this.aroon = new HouseAroon(aroonPeriod);
    }

    @Override
    public void genericInit(Integer... constants) {
        init(constants[0], constants[1], constants[2], constants[3]);
    }

    @Override
    protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

        FormulatRes res = new FormulatRes(EventDefinition.PMAROONTREND);
        res.setCurrentDate(qU.getDate());

        int aroonIndex = getIndicatorIndexFromQuotationIndex(this.aroon, quotationIdx);

        {
            Boolean isArronOsccrossingUpLower = upperThreshold <= aroon.getOutAroonOsc()[aroonIndex];
            res.setBullishCrossOver(isArronOsccrossingUpLower); 
            if (res.getBullishCrossOver()) return res;

        }
        {
            Boolean isArronOscCrossingDownUpper =  lowerThreshold >= aroon.getOutAroonOsc()[aroonIndex];
            res.setBearishCrossBelow(isArronOscCrossingDownUpper); 
            return res;

        }
    }

    @Override
    protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
        if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
        if (indicator instanceof SMA) return this.isInDataRange((SMA) indicator, indicatorIndex);
        throw new RuntimeException("Booo",new Throwable());

    }

    private boolean isInDataRange(SMA sma, Integer index) {
        return getDaysSpan() <= index && index < sma.getSma().length;
    }

    private Boolean isInDataRange(HouseAroon aroon, Integer index) {
        return (getDaysSpan() < index) && (index < aroon.getOutAroonUp().length);
    }

    @Override
    protected String getHeader(List<Integer> scoringSmas) {
        String head = "CALCULATOR DATE, CALCULATOR QUOTE, Aroon DATE, Aroon Up, Aroon Down, bearish, bullish";
        head = addScoringHeader(head, scoringSmas);
        return head+"\n";	
    }

    @Override
    protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {

        Date calculatorDate = qU.getDate();
        EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BEARISH));
        EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BULLISH));
        BigDecimal calculatorClose = qU.getCloseSplit();

        int aroonIndex = getIndicatorIndexFromQuotationIndex(this.aroon, calculatorIndex);

        String line =
                new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
                        + this.aroon.getOutAroonUp()[aroonIndex] + "," + this.aroon.getOutAroonDown()[aroonIndex];

        if (bearishEventValue != null) {
            line = line + ","+calculatorClose+", 0,";
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

        Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromQuotationIndex(this.aroon, idx);
        return new double[]
                {
                        this.aroon.getOutAroonOsc()[indicatorIndexFromCalculatorQuotationIndex],
                        2*this.aroon.getOutAroonDown()[indicatorIndexFromCalculatorQuotationIndex]-100,
                        2*this.aroon.getOutAroonUp()[indicatorIndexFromCalculatorQuotationIndex]-100,
                        middleThreshold, lowerThreshold, upperThreshold
                };

    }


    @Override
    protected int getDaysSpan() {
        return 21;
    }

    @Override
    public EventDefinition getEventDefinition() {
        return EventDefinition.PMAROONTREND;
    }

    @Override
    protected void initIndicators(Quotations quotations) throws TalibException {
        try {
            this.aroon.calculateIndicator(quotations);
        } catch (Exception e) {
            throw new TalibException(e.toString(), e);
        }
    }

    @Override
    public Integer getStartShift() {
        return aroon.getStartShift() + getDaysSpan();
    }

    @Override
    public ValidityFilter quotationsValidity() {
        return aroon.quotationValidity();
    }

    @Override
    public Integer getOutputBeginIdx() {
        return aroon.getOutBegIdx().value + getDaysSpan();
    }

}
