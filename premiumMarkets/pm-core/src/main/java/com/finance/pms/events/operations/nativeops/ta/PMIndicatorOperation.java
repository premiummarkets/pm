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
package com.finance.pms.events.operations.nativeops.ta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.calc.BuyAndHoldOperation;
import com.finance.pms.events.operations.nativeops.calc.MathOperation;
import com.finance.pms.events.operations.nativeops.calc.StatsOperation;
import com.finance.pms.events.operations.nativeops.calc.VolatilityOperation;
import com.finance.pms.events.operations.nativeops.calc.VolatilityOtherOperation;
import com.finance.pms.events.operations.nativeops.trans.BandNormalizerOperation;
import com.finance.pms.events.operations.nativeops.trans.BandRatioNormalizerOperation;
import com.finance.pms.events.operations.nativeops.trans.FlipOperation;
import com.finance.pms.events.operations.nativeops.trans.InverseOperation;
import com.finance.pms.events.operations.nativeops.trans.LeftShifterOperation;
import com.finance.pms.events.operations.nativeops.trans.TriggerPointJoiner;
import com.finance.pms.events.operations.nativeops.trans.TrimOperation;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.talib.indicators.TalibIndicator;

@XmlSeeAlso({
    PMAroonOperation.class, PMMACDOperation.class, PMLogRocOperation.class, PMMightyChaikinOperation.class, PMBollingerOperation.class,
    LeftShifterOperation.class, BandNormalizerOperation.class, BandRatioNormalizerOperation.class, ZeroLagEMAOperation.class, TrimOperation.class,
    StatsOperation.class, MathOperation.class, TriggerPointJoiner.class,
    FlipOperation.class, InverseOperation.class,
    VolatilityOperation.class, VolatilityOtherOperation.class,
    BuyAndHoldOperation.class
})
public abstract class PMIndicatorOperation extends DoubleMapOperation {

    protected PMIndicatorOperation() {
        super();
    }

    public PMIndicatorOperation(String reference, String description, ArrayList<? extends Operation> operands) {
        super(reference, description, operands);
    }

    public PMIndicatorOperation(String reference, String description, Operation ... operands) {
        this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
    }

    protected NumericableMapValue doubleArrayMapToDoubleMap(Quotations quotations, TargetStockInfo targetStock, TalibIndicator talibIndicator, double[] outputs) {

        NumericableMapValue dateDoubleMap = new DoubleMapValue();
        for (int i = 0; i < talibIndicator.getOutNBElement().value; i++) {
            Date calculatorDate = quotations.getDate(i + talibIndicator.getOutBegIdx().value);
            Double output = outputs[i];
            dateDoubleMap.getValue(targetStock).put(calculatorDate, output);
        }

        return dateDoubleMap;

    }

    protected SortedMap<Date, double[]> doubleMapToDoubleArrayMap(TargetStockInfo targetStock, NumericableMapValue input) {

        SortedMap<Date, double[]> doubleArrayMap = new TreeMap<Date, double[]>();
        SortedMap<Date, Double> inputValue = input.getValue(targetStock);

        for (Date date : inputValue.keySet()) {
            doubleArrayMap.put(date, new double[]{inputValue.get(date)});
        }

        return doubleArrayMap;
    }

    protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest, int originIdx) {
        for (Date date : origin.keySet()) {
            dest.put(date, origin.get(date)[originIdx]);
        }
    }

    protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest) {
        transOutput(origin, dest, 0);
    }

    protected void transInput(SortedMap<Date, Double> origin, SortedMap<Date, double[]> dest) {
        for (Date date : origin.keySet()) {
            dest.put(date, new double[]{origin.get(date)});
        }
    }

}
