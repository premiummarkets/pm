package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
/**
 * 
 * @author guil
 * http://www.macroption.com/historical-volatility-calculation/
 */
public class HistoricalVolatilityCalculator {

	private static MyLogger LOGGER = MyLogger.getLogger(HistoricalVolatilityCalculator.class);

	private static int YEAR_LENGTH = 252;
	private boolean annualise;

	private int basicPeriodLength; //Usually 1. i.e. on day
	private int returnCalculationNbPeriods; //Usually 21 or 63
	private List<Double> closeValues;

	public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations) {
		this(closeQuotations, 1, 63, true);
	}

	public HistoricalVolatilityCalculator(SortedMap<Date, Double> closeQuotations, int basicPeriodLength, int returnCalculationNbPeriods, boolean annualise) {
		super();
		this.basicPeriodLength = basicPeriodLength;
		this.returnCalculationNbPeriods = returnCalculationNbPeriods;
		this.closeValues = new ArrayList<>(closeQuotations.values());
		this.annualise = annualise;
	}

	public Double averageAnnualisedVolatility(int from, int to) throws IndexOutOfBoundsException {
		if (to < basicPeriodLength + returnCalculationNbPeriods) throw new IndexOutOfBoundsException(to + "!>=" + (basicPeriodLength + returnCalculationNbPeriods));
		return IntStream
				.range(from + basicPeriodLength + returnCalculationNbPeriods, to)
				.mapToDouble(d -> movingVolatiltityAt(d))
				.average()
				.getAsDouble();
	}

	public Double averageAnnualisedSignedVolatility(int from, int to, int sign) throws IndexOutOfBoundsException {
		if (to < basicPeriodLength + returnCalculationNbPeriods) throw new IndexOutOfBoundsException(to + "!>=" + (basicPeriodLength + returnCalculationNbPeriods));
		return IntStream
				.range(from + basicPeriodLength + returnCalculationNbPeriods, to)
				.mapToDouble(d -> movingSignedVolatilityAt(d, sign))
				.average()
				.getAsDouble();
	}

	public Double movingVolatiltityAt(int d) {
		Double stdOfD2DReturns = stdOfReturnsAt(d);
		return stdOfD2DReturns * ((annualise)?Math.sqrt(YEAR_LENGTH/basicPeriodLength):1d);
	}
	
	public Double movingMeanAt(int d) {
		Double meanOfD2DReturns = meanOfReturnsAt(d);
		return meanOfD2DReturns * ((annualise)?Math.sqrt(YEAR_LENGTH/basicPeriodLength):1d);
	}

	public Double movingSignedVolatilityAt(int d, int sign) {
		Double stdOfD2DReturns = stdOfReturnsSignedAt(d, sign);
		return stdOfD2DReturns * ((annualise)?Math.sqrt(YEAR_LENGTH/basicPeriodLength):1d);
	}

	public Double movingSignedMeanAt(int d, int sign) {
		Double meanOfD2DReturns = meanOfReturnsSignedAt(d, sign);
		return meanOfD2DReturns * ((annualise)?Math.sqrt(YEAR_LENGTH/basicPeriodLength):1d);
	}

	private Double stdOfReturnsAt(int d) {

		int d0 = d - returnCalculationNbPeriods - basicPeriodLength;
		List<Double> d2DReturns = IntStream
				.range(0, returnCalculationNbPeriods)
				.mapToObj(i -> {
					return periodReturn(closeValues.get(d0 + i), closeValues.get(d0 + i + basicPeriodLength));
				})
				.collect(Collectors.toList());

		MyApacheStats stdev = new MyApacheStats(new StandardDeviation());
		return stdev.sEvaluate(d2DReturns);

	}
	
	private Double meanOfReturnsAt(int d) {

		int d0 = d - returnCalculationNbPeriods - basicPeriodLength;
		List<Double> d2DReturns = IntStream
				.range(0, returnCalculationNbPeriods)
				.mapToObj(i -> {
					return periodReturn(closeValues.get(d0 + i), closeValues.get(d0 + i + basicPeriodLength));
				})
				.collect(Collectors.toList());

		MyApacheStats stdev = new MyApacheStats(new Mean());
		return stdev.sEvaluate(d2DReturns);

	}

	private Double stdOfReturnsSignedAt(int d, int sign) {

		int d0 = d - returnCalculationNbPeriods - basicPeriodLength;
		List<Double> d2DReturns = IntStream
				.range(0, returnCalculationNbPeriods)
				.mapToObj(i -> {
					return periodReturn(closeValues.get(d0 + i), closeValues.get(d0 + i + basicPeriodLength));
				})
				.filter(v -> sign*v > 0)
				.collect(Collectors.toList());

		MyApacheStats stdev = new MyApacheStats(new StandardDeviation());
		return stdev.sEvaluate(d2DReturns);

	}

	private Double meanOfReturnsSignedAt(int d, int sign) {

		int d0 = d - returnCalculationNbPeriods - basicPeriodLength;
		List<Double> d2DReturns = IntStream
				.range(0, returnCalculationNbPeriods)
				.mapToObj(i -> {
					return periodReturn(closeValues.get(d0 + i), closeValues.get(d0 + i + basicPeriodLength));
				})
				.filter(v -> sign*v > 0)
				.collect(Collectors.toList());

		MyApacheStats stdev = new MyApacheStats(new Mean());
		return stdev.sEvaluate(d2DReturns);

	}

	private Double periodReturn(double dMp, double d) {
		return Math.log(d/dMp);
	}


	//Other
	public Double volatilityLnSkewRatio(int from, int to) {
		BiFunction<List<Double>, Integer, Double> f = (u,i) -> Math.log(u.get(i)/u.get(i-1));
		return innerSkewRatio(f, 0d, from, to);
	}

	public Double simpleRocSkewRatio(int from, int to) {
		BiFunction<List<Double>, Integer, Double> f = (u,i) -> u.get(i)/u.get(i-1);
		return innerSkewRatio(f, 1d, from, to);
	}

	/**
	 * < 1 means positive bias and > 1 mean negative bias
	 * @param threshold TODO
	 * @param from
	 * @param to
	 * @return
	 */
	private Double innerSkewRatio(BiFunction<List<Double>, Integer, Double> f, Double threshold, int from, int to) {
		if (to >= closeValues.size()) to = closeValues.size()-1;
		if (from +1 > to) throw new RuntimeException(from + " !< " + to);

		MyApacheStats mean = new MyApacheStats(new Mean());
		List<Double> positiveFns = IntStream
				.range(from +1, to)
				.mapToObj(i -> f.apply(closeValues, i))
				.filter(fn -> fn > threshold)
				.collect(Collectors.toList());
		Double positiveFnMean = Math.abs(mean.sEvaluate(positiveFns));

		List<Double> negativeFns = IntStream
				.range(from +1, to)
				.mapToObj(i -> f.apply(closeValues, i))
				.filter(fn -> fn < threshold)
				.collect(Collectors.toList());
		Double negativeFnMean = Math.abs(mean.sEvaluate(negativeFns));

		Double ratio = negativeFnMean/positiveFnMean;
		LOGGER.info("Skew : from " + from + " to " + to + " is "+ ratio);
		if (Double.isNaN(ratio)) return 1d;
		return ratio;
	}

}
