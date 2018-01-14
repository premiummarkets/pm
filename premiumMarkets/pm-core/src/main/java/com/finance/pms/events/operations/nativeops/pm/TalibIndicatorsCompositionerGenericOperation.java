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
package com.finance.pms.events.operations.nativeops.pm;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.EventDefDescriptorStatic;
import com.finance.pms.events.calculation.TalibIndicatorsCompositioner;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.TalibException;

public class TalibIndicatorsCompositionerGenericOperation extends EventMapOperation {

    private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsCompositionerGenericOperation.class);

    private final Class<? extends TalibIndicatorsCompositioner> calculatorClass;

    public TalibIndicatorsCompositionerGenericOperation(
            String reference, String description, 
            Class<? extends TalibIndicatorsCompositioner> calculatorClass, List<String> inConstantsNames) throws Exception {
        super(reference, description);

        this.calculatorClass = calculatorClass;

        ArrayList<Operation> overridingOperands = new ArrayList<Operation>();
        for (String inConstantName : inConstantsNames) {
            NumberOperation constant = new NumberOperation("number", inConstantName, inConstantName, null);
            overridingOperands.add(constant);
        }
        this.setOperands(overridingOperands);

    }

    /**
     * @return True when bullish and False when bearish
     */
    @Override
    public EventDataValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        EventDataValue buySellEvents = new EventDataValue();
        try {

            TalibIndicatorsCompositioner calculator = calculatorClass.getConstructor().newInstance();

            List<Integer> inputConstants = inputs.stream().map( i -> ((NumberValue) i).getValue(targetStock).intValue()).collect(Collectors.toList());
            calculator.genericInit(inputConstants.toArray(new Integer[0]));

            Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(
                    targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), 
                    true, targetStock.getStock().getMarketValuation().getCurrency(), 
                    calculator.getStartShift(), calculator.quotationsValidity());

            //Events is the only functional output for this operation => boolean
            SortedMap<EventKey, EventValue> eventsFor = calculator.calculateEventsFor(quotations, "inMem"+this.getClass().getSimpleName()+"Operation");
            buySellEvents = new EventDataValue(eventsFor);

            //Adding indicator outputs for charting
            EventDefinition eventDefinition = (EventDefinition) calculator.getEventDefinition();
            EventDefDescriptorStatic eventDefDescriptor = eventDefinition.getEventDefDescriptor();
            if (eventDefDescriptor == null) {
                throw new NotImplementedException("No static descriptor implemented for "+eventDefinition+". This operation compositioner can't be reflected. Please implement.");
            }
            LinkedHashMap<String, Type> outputQualifiers = eventDefDescriptor.descriptionMap();

            SortedMap<Date, double[]> calculationOutput = calculator.calculationOutput();
            int i = 0;
            for (String outputName : outputQualifiers.keySet()) {
                SortedMap<Date, Double> output = new TreeMap<Date, Double>();
                transOutput(calculationOutput, output, i);

                //addAdditionalOutputs(TargetStockInfo targetStock, Operation operand, MultiMapValue operandsOutput)
                targetStock.addExtraneousChartableOutput(this, new DoubleMapValue(output), outputName);

                i++;
            }

            buildChartable(targetStock, outputQualifiers);

        } 
        catch (TalibException e) {
            LOGGER.warn(e);
        } 
        catch (Exception e) {
            LOGGER.error(e,e);
        }

        return buySellEvents;
    }

    private void buildChartable(TargetStockInfo targetStock, LinkedHashMap<String, Type> outputQualifiers) {

        //createChartedOutputGroups(targetStock, operandsOutputs)
        String mainOutputQualifier = outputQualifiers.keySet().stream().findFirst().get(); //first element ..
        targetStock.setMain(this, mainOutputQualifier);

        //addAdditionalOutputs(TargetStockInfo targetStock, Operation operand, MultiMapValue operandsOutput) tail
        LinkedHashMap<String, Type> filteredOuputQualifiers = new LinkedHashMap<>();
        outputQualifiers.entrySet().stream().forEach(e ->  {
            if (e.getValue().equals(Type.CONSTANT)) {
                filteredOuputQualifiers.put(e.getKey(), Type.MULTISIGNAL);
            } 
            else {
                filteredOuputQualifiers.put(e.getKey(), e.getValue());
            }
        });
        targetStock.addChartInfoForAdditonalOutputs(this, filteredOuputQualifiers, mainOutputQualifier);

    }

    protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest, int originIdx) {
        for (Date date : origin.keySet()) {
            dest.put(date, origin.get(date)[originIdx]);
        }
    }

    @Override
    public int operationStartDateShift() {
        Integer thisOperationStartShift = getOperands().stream()
                .filter(o -> o instanceof NumberOperation)
                .map(o -> ((NumberValue)o.getParameter()).getValue(null).intValue())
                .reduce(0, (r, e) -> r + e);

        return (thisOperationStartShift)*7/5;
    }

}
