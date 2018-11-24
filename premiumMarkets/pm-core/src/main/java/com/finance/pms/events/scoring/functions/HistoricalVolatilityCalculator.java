package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
/**
 * 
 * @author guil
 * http://www.macroption.com/historical-volatility-calculation/
 */
public class HistoricalVolatilityCalculator {

	static int YEAR_LENGTH = 252;

	int basicPeriodLength; //Usually 1. i.e. on day
	int returnCalculationNbPeriods; //Usually 21 or 63
	List<Double> closeValues;

	public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations) {
		this(closeQuotations, 1, 63);
	}

	public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations, int basicPeriodLength, int returnCalculationNbPeriods) {
		super();
		this.basicPeriodLength = basicPeriodLength;
		this.returnCalculationNbPeriods = returnCalculationNbPeriods;
		this.closeValues = new ArrayList<>(closeQuotations.values());
	}

	public Double averageAnnualisedVolatility(int from, int to) throws IndexOutOfBoundsException {
		if (to < basicPeriodLength + returnCalculationNbPeriods) throw new IndexOutOfBoundsException(to + "!>=" + (basicPeriodLength + returnCalculationNbPeriods));
		return IntStream
				.range(from + basicPeriodLength + returnCalculationNbPeriods, to)
				.mapToDouble(d -> annualisedVolatilityAt(d))
				.average()
				.getAsDouble();
	}

	public Double annualisedVolatilityAt(int d) {
		Double stdOfD2DReturns = stdOfReturnsAt(d);
		return stdOfD2DReturns * Math.sqrt(YEAR_LENGTH/basicPeriodLength);
	}

	public Double volatilityLnSkewRatio(int from, int to) {
		if (to >= closeValues.size()) to = closeValues.size()-1;
		if (from +1 > to) throw new RuntimeException(from + " !< " + to);

		List<Double> positiveLns = IntStream
				.range(from +1, to)
				.mapToObj(i -> Math.log(closeValues.get(i)/closeValues.get(i-1)))
				.filter(ln -> ln > 0)
				.collect(Collectors.toList());
		ApacheStats stdev = new ApacheStats(new Mean());
		Double positiveLnMean = stdev.sEvaluate(positiveLns);

		List<Double> negativeLns = IntStream
				.range(from +1, to)
				.mapToObj(i -> Math.log(closeValues.get(i)/closeValues.get(i-1)))
				.filter(ln -> ln < 0)
				.collect(Collectors.toList());
		Double negativeLnMean = stdev.sEvaluate(negativeLns);

		return positiveLnMean/negativeLnMean;
	}

	private Double stdOfReturnsAt(int d) {

		int d0 = d - returnCalculationNbPeriods - basicPeriodLength;
		List<Double> d2DRetruns = IntStream
				.range(0, returnCalculationNbPeriods)
				.mapToObj(i -> {
					return periodReturn(closeValues.get(d0 + i), closeValues.get(d0 + i + basicPeriodLength));
				})
				.collect(Collectors.toList());

		ApacheStats stdev = new ApacheStats(new StandardDeviation());
		return stdev.sEvaluate(d2DRetruns);

	}

	private Double periodReturn(double dMp, double d) {
		return Math.log(d/dMp);
	}

}
