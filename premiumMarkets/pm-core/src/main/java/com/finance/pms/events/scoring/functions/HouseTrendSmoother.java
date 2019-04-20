
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


package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;

public class HouseTrendSmoother extends Smoother implements SSmoother {

    protected static MyLogger LOGGER = MyLogger.getLogger(HouseTrendSmoother.class);

    protected int period;
    protected int ynCount;

    protected HouseTrendSmoother(int period, int ynCount) {
        super();
        this.period = period;
        this.ynCount = ynCount;
    }

    public HouseTrendSmoother(int period) {
        super();
        this.period = period;
        this.ynCount = 1;
    }

    public HouseTrendSmoother() {
        this.period = 1;
        this.ynCount = 1;
    }


    @Override
    public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {

        double[][] smoothedArray = smooth(data.values().toArray(new double[data.size()][]));

        SortedMap<Date, double[]> ys = new TreeMap<>();
        List<Date> keys = new ArrayList<>(data.keySet());
        for (int i = period; i < keys.size(); i++) {
            if (smoothedArray[i-period] != null)  ys.put(keys.get(i), smoothedArray[i-period]);
        }

        return ys;
    }

    public double[][] smooth(double[][] xs) {
        double[][] ysArray = new double[xs.length - period][];
        for (int i = period; i < xs.length; i++) {
            double yi = function(xs, i);
            if (!Double.isNaN(yi)) {
                ysArray[i-period] = new double[]{yi};
            } else if (i >= ynCount*period) {
                String message = "NaN at index " + i + ", with " + Arrays.asList(xs).subList(i-ynCount*period, i+1).stream().map(Arrays::toString).reduce((r, e) -> r+e);
                throw new RuntimeException(message);
            }
        }
        return ysArray;
    }

    protected double function(double[][] values, int i) {
        double xi = values[i][0];
        double xi_1 = values[i-period][0];
        if (xi <= 0 || xi_1 <= 0 ) throw new NotImplementedException("currentValue : " + xi + ", previousValue " + xi_1);
        return Math.log(xi/xi_1);
    }

    @Override
    public SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, Boolean fixLag) {

        SortedMap<Date, Double> ret = new TreeMap<>();

        List<Double> values = new ArrayList<>(data.values());
        List<Date> keys = new ArrayList<>(data.keySet());
        for (int i = period; i < values.size(); i++) {
            double currentValue = values.get(i);
            double previousValue = values.get(i-period);

            if (currentValue <= 0 || previousValue <= 0 ) throw new NotImplementedException("currentValue : " + currentValue + ", previousValue " + previousValue);

            double value = Math.log(currentValue/previousValue);
            ret.put(keys.get(i), value);
        }

        return ret;
    }

}
