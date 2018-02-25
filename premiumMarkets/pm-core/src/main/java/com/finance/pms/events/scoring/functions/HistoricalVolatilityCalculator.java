package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
/**
 * 
 * @author guil
 * http://www.macroption.com/historical-volatility-calculation/
 */
public class HistoricalVolatilityCalculator {

    int basicPeriod; //1
    int returnCalculationNbPeriods; //21 or 63
    int yearNbPeriods; //252
    List<Double> closeValues;

    public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations) {
        this(closeQuotations, 1, 63, 252);
    }

    public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations, int basicPeriod, int returnCalculationNbPeriods, int yearNbPeriods) {
        super();
        this.basicPeriod = basicPeriod;
        this.returnCalculationNbPeriods = returnCalculationNbPeriods;
        this.yearNbPeriods = yearNbPeriods;
        this.closeValues = new ArrayList<>(closeQuotations.values());
    }

    public Double averageAnnualisedVolatility(int from, int to) throws IndexOutOfBoundsException {
        if (to < basicPeriod + returnCalculationNbPeriods) throw new IndexOutOfBoundsException(to + "!>=" + (basicPeriod + returnCalculationNbPeriods));
        return IntStream
                .rangeClosed(from + basicPeriod + returnCalculationNbPeriods, to)
                .mapToDouble(d -> annualisedVolatilityAt(d))
                .average()
                .getAsDouble();
    }

    public Double annualisedVolatilityAt(int d) {
        Double stdOfD2DReturns = stdOfReturnsAt(d);
        return stdOfD2DReturns * Math.sqrt(yearNbPeriods);
    }

    private Double stdOfReturnsAt(int d) {

        int d0 = d - returnCalculationNbPeriods - basicPeriod;
        List<Double> d2DRetruns = IntStream
                .range(0, returnCalculationNbPeriods)
                .mapToObj(i -> {
                    return periodReturn(closeValues.get(d0 + i + basicPeriod), closeValues.get(d0 + i));
                })
                .collect(Collectors.toList());

        ApacheStats stdev = new ApacheStats(new StandardDeviation());
        return stdev.sEvaluate(d2DRetruns);

    }

    private Double periodReturn(double d0, double dMp) {
        return Math.log(d0/dMp);
    }

}
