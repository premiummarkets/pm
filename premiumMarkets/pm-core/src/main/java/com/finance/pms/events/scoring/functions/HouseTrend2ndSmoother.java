package com.finance.pms.events.scoring.functions;

import java.util.List;

public class HouseTrend2ndSmoother extends HouseTrendSmoother {

    public static final double LIFTER_ABOVE_0 = 1;

    protected HouseTrend2ndSmoother() {
        super(1, 2);
    }

    public HouseTrend2ndSmoother(int period, int ynCount) {
        super(period, ynCount);
    }

    protected HouseTrend2ndSmoother(int period) {
        super(period, 2);
    }

    @Override
    protected double function(List<double[]> values, int i) {

        if (i < ynCount*period) return Double.NaN;
        double xi = values.get(i)[0];
        double xi_1 = values.get(i-1*period)[0];
        double xi_N_1 = values.get(i-(ynCount-1)*period)[0];
        double xi_N_2 = values.get(i-ynCount*period)[0];

        double yi = Math.log10(( LIFTER_ABOVE_0 + Math.log10(xi)-Math.log10(xi_N_1) )/( LIFTER_ABOVE_0 + Math.log10(xi_1)- Math.log10(xi_N_2) ));
        return yi;

    }

}
