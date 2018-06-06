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
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsOperator;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;

//FIXME rename/replace the terms Compositionner and Calculator with Operator
/**
 * Calculators or IndicatorsCompositioners are actually operators or functions taking operations as arguments.
 * In particular, the IndicatorsCompositioner generates events (SortedMap<EventKey, EventValue>) and hence can be used as operator for an EventDefinition.
 * It can be used for the mean of defining a new EventDefinition (also originally called second pass operations) through its implementation, but is then not modifiable/parameterizable.
 * For instance, TalibIndicatorsCompositioner is one specific IndicatorsCompositioner combining Talib operations.
 * For the TalibIndicatorsCompositioner to be available as EventInfo, we needed an operation that is NOT an EventInfoOpsCompoOperation. 
 * As its operand are actually hard coded, this first is a PMDataFreeOperation.
 * The approach was to create an operation (TalibIndicatorsCompositionerGenericOperation) with each TalibIndicatorsCompositioner as its operator.
 * It then needs to be wrapped within an EventInfoOpsCompoOperations through a UI defined formulae. {@link com.finance.pms.events.operations.nativeops.pm.TalibIndicatorsCompositionerGenericOperation}.
 * 
 * Reviewed terminology :
 * 	OperationsCompositionner (former EventConditionHolder) => which should be renamed to IndicatorsBullBearSwitchOperation or EventInfoBullBearSwitchOperation
 *	-- takes operands -> Operation(s)
 *  -- takes an operator -> Indicator(s)/Calculator(s)/IndicatorsCompositionner (former EventCompositionCalculator) => which should be renamed to IndicatorOperator, xxxOperator
 */
public abstract class IndicatorsOperator {

    protected Observer[] observers;

    public IndicatorsOperator(Observer... observers) {
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
     * PARAMERIZED Events have a special implementation : {@link ParameterizedIndicatorsOperator}
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

    //FIXME temporary fix before getting rid of First and Second pass
    public Boolean isIdemPotent() {
        return true;
    }

}
