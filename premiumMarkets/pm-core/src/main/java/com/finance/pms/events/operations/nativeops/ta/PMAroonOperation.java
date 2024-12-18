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
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.TalibException;

@XmlRootElement
public class PMAroonOperation extends PMDataFreeOperation {

    protected static MyLogger LOGGER = MyLogger.getLogger(PMAroonOperation.class);

    public PMAroonOperation() {
        super("aroon_", "Aroon indicator house made", new NumberOperation("number","aroonPeriod","Aroon period", new NumberValue(25.0)));
        setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"down","up","oscillator"})));
    }

    public PMAroonOperation(ArrayList<Operation> operands, String outputSelector) {
        this();
        this.setOperands(operands);
        this.setOutputSelector(outputSelector);
    }

    @Override
    public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        //Param check
        Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();

        NumericableMapValue ret = new DoubleMapValue();
        try {

            //HouseAroon aroon = new HouseAroon(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), null, period);
            HouseAroon aroon = new HouseAroon(period);
            Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(
                    targetStock.getStock(), targetStock.getStartDate(thisStartShift), targetStock.getEndDate(),
                    true, targetStock.getStock().getMarketValuation().getCurrency(), 
                    aroon.getStartShift(), aroon.quotationValidity());
            aroon.calculateIndicator(quotations);

            if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("down")) {
                return doubleArrayMapToDoubleMap(quotations, targetStock, aroon, aroon.getOutAroonDown());
            }
            else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("up")) {
                return doubleArrayMapToDoubleMap(quotations, targetStock, aroon, aroon.getOutAroonUp());
            }
            else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("oscillator")) {
                return doubleArrayMapToDoubleMap(quotations, targetStock, aroon, aroon.getOutAroonOsc());
            } else {
                //error
                return doubleArrayMapToDoubleMap(quotations, targetStock, aroon, aroon.getOutputData());
            }

        } catch (NoQuotationsException e) {
            LOGGER.warn(e);
        } catch (TalibException e) {
            LOGGER.warn(e);
        } catch (Exception e) {
            LOGGER.error(e,e);
        }
        return ret;
    }

}
