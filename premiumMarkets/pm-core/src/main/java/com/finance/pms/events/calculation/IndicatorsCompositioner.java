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

import java.util.Date;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsCompositioner;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;

/**
 * In principle the IndicatorsCompositioner generates events (SortedMap<EventKey, EventValue>).
 * 
 * TalibIndicatorsCompositioner is one of IndicatorsCompositioner and therefore can be used by the mean of a new EventDefinition (also originally called second pass operations) for each of its implementations, but is not modifiable.
 * If we need to parameterise TalibIndicatorsCompositioner and its operands, we need an operation that is not an OperationsCompositioner. Also, as its operand are actually hard coded, this will be a PMDataFreeOperation.
 * The idea here is hence to integrate the TalibIndicatorsCompositioner into an operation of type DoubleMapOperation (here PMDataFreeOperation) and then make it potentially available to used in an OperationsCompositioner through the UI.
 * The operation is making it parametrisable and usable as operand of an OperationsCompositioner.
 * The events output of the calculator (or any other output) can be the input (for event calculation or for display) of the OperationsCompositioner.
 * This is for instance done that way for IndicatorsCompositioners such as Perceptron and Encog calculators.
 * 
 * An other approach is too create an operation for all TalibIndicator used as subjacent indicators of each TalibIndicatorsCompositioner.
 * Then replicate every TalibIndicatorsCompositioner calculation by creating OperationsCompositioners through the UI.
 * 
 * Reviewed terminology : Indicator(s)/IndicatorsCompositionner (former EventCompositionCalculator) -> Operation(s) -> OperationsCompositionner (former EventConditionHolder)
 */
public abstract class IndicatorsCompositioner {

    protected Observer[] observers;

    public IndicatorsCompositioner(Observer... observers) {
        this.observers = observers;
    }


    /**
     * Number of days of input (quotation) needed before for the first output calculation
     * It usually as to be relative to the moving average smoothing period when the data is lagged fixed.
     */
    public abstract Integer getStartShift();

    public abstract SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) throws Exception;

    protected void addEvent(Map<EventKey, EventValue> eventData, Date currentDate, EventDefinition eventDefinition, EventType eventType, String message, String eventListName) {
        StandardEventKey iek = new StandardEventKey(currentDate, eventDefinition, eventType);
        EventValue iev = new StandardEventValue(currentDate, eventType, eventDefinition, message, eventListName);
        eventData.put(iek, iev);
    }

    /**
     * Period of the calculator. Usually a period number of days of input is needed before the first output calculation.
     * This is usually used in the calculation of the {@link #getStartShift()}.
     */
    protected abstract int getDaysSpan();

    /**
     * Used for charting when using static {@link EventDefinition}.
     * Each index in the returned arrays corresponds to a matching entry in the static description of the EventDefinition associated here via {@link #getEventDefinition()}.
     * The exhaustive supported list includes : String mainIndicator, String secondIndicator, String thirdIndicator, String signalLine, String lowerThreshold, String upperThreshold.
     * PARAMERIZED Events have a special implementation : {@link ParameterizedIndicatorsCompositioner}
     */
    public abstract SortedMap<Date, double[]> calculationOutput();

    public abstract EventInfo getEventDefinition();

    public abstract EmailFilterEventSource getSource();

    public abstract ValidityFilter quotationsValidity();


    /**
     * If output is NaN this means that the output is not available and should not be displayed at that date on chart.
     * If output is null this means that there is no data for that date but points should still be drawn on chart at that date.
     * We put Double.NEGATIVE_INFINITY as a marker. This should be 'null' but would need a conversion from double[] to Double[] => impact too big
     * @return
     */
    protected Double translateOutputForCharting(Double ds2) {
        if (ds2 != null) {
            if (!ds2.isNaN()) {
                return ds2;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        } else {
            return Double.NaN;
        }
    }

}
