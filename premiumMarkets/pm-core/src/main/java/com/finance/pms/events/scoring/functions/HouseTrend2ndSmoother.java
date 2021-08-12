package com.finance.pms.events.scoring.functions;

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
    protected double[] function(double[][] values, int i) {
    	
    	if (values[i].length != 1) throw new UnsupportedOperationException("Plz fix.");

        if (i < ynCount*period) return new double[] {Double.NaN};
        double xi = values[i][0];
        double xi_1 = values[i-1*period][0];
        double xi_N_1 = values[i-(ynCount-1)*period][0];
        double xi_N_2 = values[i-ynCount*period][0];

        double yi = Math.log(( LIFTER_ABOVE_0 + Math.log(xi)-Math.log(xi_N_1) )/( LIFTER_ABOVE_0 + Math.log(xi_1)- Math.log(xi_N_2) ));
        return new double[] {yi};

    }

}
