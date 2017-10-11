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
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedCalculator;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;

public abstract class EventCompostionCalculator {

    protected Observer[] observers;

    public EventCompostionCalculator(Observer... observers) {
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
     * PARAMERIZED Events have a special implementation : {@link ParameterizedCalculator}
     */
    public abstract SortedMap<Date, double[]> calculationOutput();

    public abstract EventInfo getEventDefinition();

    public abstract EmailFilterEventSource getSource();

    public abstract ValidityFilter quotationsValidity();


    //If output is NaN this means that the output is not available and should not be displayed at that date on chart.
    //If output is null this means that there is no data for that date but points should still be drawn on chart at that date.
    //We put Double.NEGATIVE_INFINITY as a marker. This should be 'null' but would need a conversion from double[] to Double[] => impact too big
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
