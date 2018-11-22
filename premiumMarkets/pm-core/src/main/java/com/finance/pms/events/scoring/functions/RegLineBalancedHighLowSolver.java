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
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;


/**
 * @author Guillaume Thoreton
 *
 */

public class RegLineBalancedHighLowSolver implements HighLowSolver {

	private static MyLogger LOGGER = MyLogger.getLogger(RegLineBalancedHighLowSolver.class);

	private static final double HIGHPOSMIN = .8;
	private static final double LOWPOSMAX = .2;

	private static final double REGLINE_TOLERANCE = 0.01;
	private static final double REGLINE_SLOPEMIN = 0.0; //0.03;

	public Boolean higherHigh(
			Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Long, Double> _higherHighs, Line<Long, Double> __expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		//		double[] sSmooth = new double[0];
		//		if (smoothingPeriod != -1) {
		//			double[][] arrayArrayData = Arrays.stream(data).map(d -> new double[]{d}).toArray(double[][]::new);
		//			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(smoothingPeriod);
		//			sSmooth = smaSmoother.smooth(arrayArrayData);
		//		} //FIXME java.lang.ArrayIndexOutOfBoundsException on sSmooth array
		//
		//		MutableDouble amountBelowSmoothingCeiling = new MutableDouble(0);
		//		MutableInt firstPeakIdx = new MutableInt(-1);
		//		MutableInt lastPeakIdx = new MutableInt(-1);
		//
		//		//Double[] highPeaks = highPeaks(firstPeakIdx, lastPeakIdx, amountBelowSmoothingCeiling, periodData, periodSmoothedCeiling).values().toArray(new Double[0]);
		//		Double[] highPeaks = bestHighTangente(data, sSmooth, firstPeakIdx, lastPeakIdx, amountBelowSmoothingCeiling).values().toArray(new Double[0]);
		//		if (firstPeakIdx.intValue() == -1) return false;
		//		if (isNotBalanced(amountBelowSmoothingCeiling, firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), data.length, minimumNbDaysBetweenExtremes)) return false;
		//
		//		Double slopeCoef = dataSlope(firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), highPeaks);
		//		if (goesDownOrFlat(slopeCoef)) return false;
		//
		//		boolean isTrue = isDataBelowRegLine(data, firstPeakIdx.intValue(), slopeCoef, _expertTangent);
		//
		//		if (isTrue) {
		//			printRes("hh", data, sSmooth, highPeaks, slopeCoef, _expertTangent);
		//		}
		//
		//		higherHighs.put(firstPeakIdx.toInteger(),_expertTangent.get(0));
		//		higherHighs.put(lastPeakIdx.toInteger(), _expertTangent.get(_expertTangent.size()-1));
		//		return isTrue;

		//FIXME
		return null;

	}

	protected boolean isDataBelowRegLine(Double[] periodData, int firstIntersctionIdx, Double slopeCoef, ArrayList<Double> regLine) {
		boolean isTrue = true;
		int regLineStartIdx = (int) (Math.max(firstIntersctionIdx - ((double)periodData.length)*.2, 0));

		int x1 = firstIntersctionIdx;
		Double y1 = periodData[(int) x1];
		int x0 = regLineStartIdx;
		double y0 = y1 + slopeCoef*(x0 - x1);
		for (int i = 0; i < x0; i++) {
			regLine.add(Double.NaN);
		}
		regLine.add(y0); //i == x0
		for (int i = x0 + 1; i < periodData.length; i++) {
			y0 = (slopeCoef * 1) +  y0;
			regLine.add(y0);
			if ( y0 < periodData[i] - Math.abs(periodData[i]*REGLINE_TOLERANCE) ) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesDownOrFlat(Double slopeCoef) {
		return slopeCoef <= REGLINE_SLOPEMIN || slopeCoef.isInfinite() || slopeCoef.isNaN();
	}

	private void printRes(String source, Double[] periodData, double[] periodSmoothedThresh, Double[] extrems, double slopeCoef, ArrayList<Double> regLine) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("source : "+ source+ ", regLine coefficient "+slopeCoef);
			LOGGER.debug("periodData,periodThreshCurve,extremes,dataSlope");
			for (int i = 0; i < extrems.length; i++) {
				LOGGER.debug(periodData[i]+","+((periodSmoothedThresh != null && periodSmoothedThresh.length >0)?periodSmoothedThresh[i]:Double.NaN)+","+extrems[i]+","+regLine.get(i)); //((i < regLine.size())?regLine.get(i):Double.NaN));
			}
		}
	}

	public Boolean lowerHigh(
			Double[] periodData, double[] periodSmoothedCeiling, Double alphaBalance, ArrayList<Double> regLine, MutableInt firstPeakIdx, MutableInt lastPeakIdx) {

		MutableDouble amountBelowSmoothingCeiling = new MutableDouble(0);

		Double[] highPeaks = bestHighTangente(periodData, periodSmoothedCeiling, firstPeakIdx, lastPeakIdx, amountBelowSmoothingCeiling).values().toArray(new Double[0]);
		if (firstPeakIdx.intValue() == -1) return false;
		if (isNotBalanced(amountBelowSmoothingCeiling, firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), periodData.length, alphaBalance)) return false;

		Double slopeCoef = dataSlope(firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), highPeaks);
		if (goesUpOrFlat(slopeCoef)) return false;

		boolean isTrue = isDataBelowRegLine(periodData, firstPeakIdx.intValue(), slopeCoef, regLine);

		if (isTrue) {
			printRes("lh",periodData, periodSmoothedCeiling, highPeaks, slopeCoef, regLine);
		}

		return isTrue;

	}

	private Map<Integer, Double> highPeaks(MutableInt firstPeakIdx, MutableInt lastPeakIdx, MutableDouble amountBelowSmoothingCeiling, Double[] periodData, Double[] periodSmoothedCeiling) {

		boolean noSmoothedConstraint = periodSmoothedCeiling.length == 0;

		Double max = -1d;
		SortedMap<Integer, Double> highPeaks = new TreeMap<Integer, Double>();
		highPeaks.put(0, 0d);
		for (int i = 1 ; i < periodData.length -1; i++) {

			if (noSmoothedConstraint || periodSmoothedCeiling[i] > periodData[i]) {
				amountBelowSmoothingCeiling.increment();
			}

			boolean isIdxAbovePrev = periodData[i-1] < periodData[i];
			boolean isIdxAboveSucc = periodData[i+1] < periodData[i];
			boolean isIdxAboveSmoothed = noSmoothedConstraint || periodSmoothedCeiling[i] <= periodData[i];
			if (isIdxAbovePrev && isIdxAboveSucc && isIdxAboveSmoothed) {
				highPeaks.put(i,periodData[i]);
				double idxBalance = ((double)i)/(double)periodData.length;
				if (firstPeakIdx.intValue() == -1 || (idxBalance < LOWPOSMAX && periodData[i] >= periodData[firstPeakIdx.intValue()])) firstPeakIdx.setValue(i);
				if (lastPeakIdx.intValue() == -1 || idxBalance <= HIGHPOSMIN || (idxBalance > HIGHPOSMIN && periodData[i] >  periodData[lastPeakIdx.intValue()])) lastPeakIdx.setValue(i);
			} else {
				highPeaks.put(i,0d);
			}

			if (periodData[i]  > max) max = periodData[i];
		}

		if (periodData[periodData.length-1] >= max && (noSmoothedConstraint || periodSmoothedCeiling[periodData.length-1] <= periodData[periodData.length-1])) {
			highPeaks.put(periodData.length-1, periodData[periodData.length-1]);
			lastPeakIdx.setValue(periodData.length-1);
		} else {
			highPeaks.put(periodData.length-1, 0d);
		}

		amountBelowSmoothingCeiling.setValue(amountBelowSmoothingCeiling.doubleValue()/periodData.length);

		return highPeaks;
	}

	private Map<Integer, Double> simpleHighPeaks(Double[] periodData, double[] periodSmoothedCeiling, MutableDouble amountBelowSmoothingCeiling) {

		boolean noSmoothedConstraint = periodSmoothedCeiling.length == 0;

		Double max = -1d;
		SortedMap<Integer, Double> highPeaks = new TreeMap<Integer, Double>();
		highPeaks.put(0, 0d);
		for (int i = 1 ; i < periodData.length -1; i++) {

			if (noSmoothedConstraint || periodSmoothedCeiling[i] > periodData[i]) {
				amountBelowSmoothingCeiling.increment();
			}

			boolean isIdxAbovePrev = periodData[i-1] < periodData[i];
			boolean isIdxAboveSucc = periodData[i+1] < periodData[i];
			boolean isIdxAboveSmoothed = noSmoothedConstraint || periodSmoothedCeiling[i] <= periodData[i];
			if (isIdxAbovePrev && isIdxAboveSucc && isIdxAboveSmoothed) {
				highPeaks.put(i,periodData[i]);
			} else {
				highPeaks.put(i,0d);
			}

			if (periodData[i]  > max) max = periodData[i];
		}

		if (periodData[periodData.length-1] >= max && (noSmoothedConstraint || periodSmoothedCeiling[periodData.length-1] <= periodData[periodData.length-1])) {
			highPeaks.put(periodData.length-1, periodData[periodData.length-1]);
		} else {
			highPeaks.put(periodData.length-1, 0d);
		}

		amountBelowSmoothingCeiling.setValue(amountBelowSmoothingCeiling.doubleValue()/periodData.length);

		return highPeaks;
	}

	public Boolean higherLow(
			Double[] periodData, double[] periodSmoothedFloor, Double alphaBalance, ArrayList<Double> regLine, MutableInt firstTroughIdx, MutableInt lastTroughIdx,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd,
			Double minSlope, Double maxSlope) {

		MutableDouble amountAboveSmoothingFloor = new MutableDouble(0);

		Double[] lowTroughs =  bestLowTangent(periodData,  periodSmoothedFloor, firstTroughIdx, lastTroughIdx, amountAboveSmoothingFloor).values().toArray(new Double[0]);
		if (firstTroughIdx.intValue() == -1) return false;
		if (isNotBalanced(amountAboveSmoothingFloor,firstTroughIdx.doubleValue(), lastTroughIdx.doubleValue(), periodData.length, alphaBalance)) return false;

		Double slopeCoef = dataSlope(firstTroughIdx.doubleValue(), lastTroughIdx.doubleValue(), lowTroughs);
		if (goesDownOrFlat(slopeCoef)) return false;

		boolean isTrue = isDataAboveRegLine(periodData, firstTroughIdx.intValue(), slopeCoef, regLine);

		if (isTrue) {
			printRes("hl",periodData, periodSmoothedFloor, lowTroughs, slopeCoef, regLine);
		}

		return isTrue;

	}

	public Boolean lowerLow(
			Double[] periodData, double[] periodSmoothedFloor, Double alphaBalance, ArrayList<Double> regLine, MutableInt firstTroughIdx, MutableInt lastTroughIdx,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd) {

		MutableDouble amountAboveSmoothingFloor = new MutableDouble(0);

		Double[] lowTroughs =  bestLowTangent(periodData, periodSmoothedFloor, firstTroughIdx, lastTroughIdx, amountAboveSmoothingFloor).values().toArray(new Double[0]);
		if (firstTroughIdx.intValue() == -1) return false;
		if (isNotBalanced(amountAboveSmoothingFloor, firstTroughIdx.doubleValue(), lastTroughIdx.doubleValue(), periodData.length, alphaBalance)) return false;

		Double slopeCoef = dataSlope(firstTroughIdx.doubleValue(), lastTroughIdx.doubleValue(), lowTroughs);
		if (goesUpOrFlat(slopeCoef)) return false;

		boolean isTrue = isDataAboveRegLine(periodData, firstTroughIdx.intValue(), slopeCoef, regLine);

		if (isTrue) {
			printRes("ll",periodData, periodSmoothedFloor, lowTroughs, slopeCoef, regLine);
		}

		return isTrue;


	}

	protected boolean isNotBalanced(MutableDouble amountInSmoothed, double firstExtremIdx, double lastExtremIdx, double dataLength, double alphaBalance) {
		//return (firstExtrem/dataLength > LOWER_EXTREM_BALANCE || lastExtrem/dataLength < UPPER_EXTREM_BALANCE);
		//return lastExtremIdx-firstExtremIdx < dataLength/2;
		return lastExtremIdx-firstExtremIdx < alphaBalance;
	}

	protected boolean isDataAboveRegLine(Double[] periodData, int firstIntersctionIdx, Double slopeCoef, ArrayList<Double> regLine) {
		boolean isTrue = true;
		int regLineStartIdx = (int) (Math.max(firstIntersctionIdx - ((double)periodData.length)*.2, 0));

		int x1 = firstIntersctionIdx;
		Double y1 = periodData[(int) x1];
		int x0 = regLineStartIdx;
		double y0 = y1 + slopeCoef*(x0 - x1);
		for (int i = 0; i < x0; i++) {
			regLine.add(Double.NaN);
		}
		regLine.add(y0); //i == x0
		for (int i = x0+1; i < periodData.length; i++) {
			y0 = (slopeCoef * 1) +  y0;
			regLine.add(y0);
			if ( y0 > periodData[i] + Math.abs(periodData[i]*REGLINE_TOLERANCE) ) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesUpOrFlat(Double slopeCoef) {
		return slopeCoef >= -REGLINE_SLOPEMIN || slopeCoef.isInfinite() || slopeCoef.isNaN();
	}

	private Map<Integer, Double> lowTroughs(Double[] pData, Double[] periodSmoothedFloor, MutableInt fTrouIdx, MutableInt lTrouIdx, MutableDouble amountAboveSmoothingFloor) {

		boolean noSmoothedConstraint = periodSmoothedFloor.length == 0;

		Double min = Double.MAX_VALUE;
		SortedMap<Integer, Double> lowTroughs = new TreeMap<Integer, Double>();
		lowTroughs.put(0,0d);
		for (int i = 1 ; i < pData.length-1; i++) {

			if (noSmoothedConstraint || periodSmoothedFloor[i] < pData[i]) {
				amountAboveSmoothingFloor.increment();
			}

			boolean isIdxBelowPrev = pData[i-1] > pData[i];
			boolean isIdxBelowSucc = pData[i+1] > pData[i];
			boolean isIdxBelowSmothed = noSmoothedConstraint || periodSmoothedFloor[i] >= pData[i];
			if ( isIdxBelowPrev && isIdxBelowSucc && isIdxBelowSmothed) {
				lowTroughs.put(i,pData[i]);
				double idxPos = ((double)i)/(double)pData.length;
				if (fTrouIdx.intValue() == -1 || (idxPos < LOWPOSMAX && pData[i] <=  pData[fTrouIdx.intValue()])) fTrouIdx.setValue(i);
				if (lTrouIdx.intValue() == -1 ||  idxPos <= HIGHPOSMIN || (idxPos > HIGHPOSMIN && pData[i] <  pData[lTrouIdx.intValue()])) lTrouIdx.setValue(i);
			} else {
				lowTroughs.put(i,0d);
			}

			if (pData[i] < min) min = pData[i];
		}

		if (pData[pData.length-1] < min  && (noSmoothedConstraint || periodSmoothedFloor[pData.length-1] >= pData[pData.length-1])) {
			lowTroughs.put(pData.length-1,pData[pData.length-1]);
			lTrouIdx.setValue(pData.length-1);
		} else {
			lowTroughs.put(pData.length-1,0d);
		}

		amountAboveSmoothingFloor.setValue(amountAboveSmoothingFloor.doubleValue()/pData.length);

		return lowTroughs;
	}

	private Map<Integer, Double> simpleLowTroughs(Double[] pData, double[] periodSmoothedFloor, MutableDouble amountAboveSmoothingFloor) {

		boolean noSmoothedConstraint = periodSmoothedFloor.length == 0;

		Double min = Double.MAX_VALUE;
		SortedMap<Integer, Double> lowTroughs = new TreeMap<Integer, Double>();
		lowTroughs.put(0,0d);
		for (int i = 1 ; i < pData.length-1; i++) {

			if (noSmoothedConstraint || periodSmoothedFloor[i] < pData[i]) {
				amountAboveSmoothingFloor.increment();
			}

			boolean isIdxBelowPrev = pData[i-1] > pData[i];
			boolean isIdxBelowSucc = pData[i+1] > pData[i];
			boolean isIdxBelowSmothed = noSmoothedConstraint || periodSmoothedFloor[i] >= pData[i];
			if ( isIdxBelowPrev && isIdxBelowSucc && isIdxBelowSmothed) {
				lowTroughs.put(i,pData[i]);
			} else {
				lowTroughs.put(i,0d);
			}

			if (pData[i] < min) min = pData[i];
		}

		if (pData[pData.length-1] < min  && (noSmoothedConstraint || periodSmoothedFloor[pData.length-1] >= pData[pData.length-1])) {
			lowTroughs.put(pData.length-1,pData[pData.length-1]);
		} else {
			lowTroughs.put(pData.length-1,0d);
		}

		amountAboveSmoothingFloor.setValue(amountAboveSmoothingFloor.doubleValue()/pData.length);

		return lowTroughs;
	}

	private Map<Integer, Double> bestLowTangent(Double[] pData, double[] periodSmoothedFloor, MutableInt fTrouIdx, MutableInt lTrouIdx, MutableDouble amountAboveSmoothingFloor) {

		Map<Integer, Double> lowTroughsMap = simpleLowTroughs(pData, periodSmoothedFloor, amountAboveSmoothingFloor);
		Double[] troughs = lowTroughsMap.values().toArray(new Double[0]);

		Double previousLeftTrough = null;
		int previousLeftTroughIdx = -1;
		double previousSlope = 0;

		Double rightTrough = null;
		int rightTroughIdx = troughs.length-1;

		for (; rightTroughIdx >=0 ; rightTroughIdx--) {
			if (troughs[rightTroughIdx] != 0) {
				rightTrough = troughs[rightTroughIdx];
				break;
			} 
		}
		if (rightTrough == null) return lowTroughsMap;

		for (int leftTroughIdx = rightTroughIdx-1; leftTroughIdx >=0 ; leftTroughIdx--) {
			if (troughs[leftTroughIdx] != 0) {
				if (previousLeftTrough == null) {
					previousLeftTrough = troughs[leftTroughIdx];
					previousLeftTroughIdx = leftTroughIdx;
					previousSlope = dataSlope((double)previousLeftTroughIdx, (double)rightTroughIdx, troughs);
				} else {
					//Check troughs[leftTroughIdx];
					Boolean currentIsBelowPrevRegLine = troughs[leftTroughIdx] <= (leftTroughIdx-rightTroughIdx)*previousSlope + rightTrough;
					if (currentIsBelowPrevRegLine) {//=> current is Below previous RegLine : current includes previous or is in opposite trend (up). We keep current
						previousLeftTrough = troughs[leftTroughIdx];
						previousLeftTroughIdx = leftTroughIdx;
						previousSlope = dataSlope((double)previousLeftTroughIdx, (double)rightTroughIdx, troughs);
					} else {//=> current is Above previous RegLine : current is cutting through previous regline or is in opposite trend (down). We keep previous.
						continue;
					}
				}
			}
		}

		fTrouIdx.setValue(previousLeftTroughIdx);
		lTrouIdx.setValue(rightTroughIdx);

		return lowTroughsMap;
	}

	private Map<Integer, Double> bestHighTangente(Double[] pData, double[] periodSmoothedFloor, MutableInt fPeakIdx, MutableInt lPeakIdx, MutableDouble amountBelowSmoothingCeiling) {

		Map<Integer, Double> highPeaksMap = simpleHighPeaks(pData, periodSmoothedFloor, amountBelowSmoothingCeiling);
		Double[] peaks = highPeaksMap.values().toArray(new Double[0]);

		Double previousLeftPeak = null;
		int previousLeftPeakIdx = -1;
		double previousSlope = 0;

		Double rightPeak = null;
		int rightPeakIdx = peaks.length-1;

		for (; rightPeakIdx >=0 ; rightPeakIdx--) {
			if (peaks[rightPeakIdx] != 0) {
				rightPeak = peaks[rightPeakIdx];
				break;
			} 
		}
		if (rightPeak == null) return highPeaksMap;

		for (int leftPeakIdx = rightPeakIdx-1; leftPeakIdx >=0 ; leftPeakIdx--) {
			if (peaks[leftPeakIdx] != 0) {
				if (previousLeftPeak == null) {
					previousLeftPeak = peaks[leftPeakIdx];
					previousLeftPeakIdx = leftPeakIdx;
					previousSlope = dataSlope( (double)previousLeftPeakIdx, (double)rightPeakIdx, peaks);
				} else {
					//Check troughs[leftTroughIdx];
					Boolean currentIsAbovePrevRegLine = peaks[leftPeakIdx] >= (leftPeakIdx-rightPeakIdx)*previousSlope + rightPeak;
					if (currentIsAbovePrevRegLine) {//=> current is Above previous RegLine : current includes previous or is in opposite trend (down). We keep current
						previousLeftPeak = peaks[leftPeakIdx];
						previousLeftPeakIdx = leftPeakIdx;
						previousSlope = dataSlope( (double)previousLeftPeakIdx, (double)rightPeakIdx, peaks);
					} else {//=> current is Above previous RegLine : current is cutting through previous regline or is in opposite trend (up). We keep previous.
						continue;
					}
				}
			}
		}

		fPeakIdx.setValue(previousLeftPeakIdx);
		lPeakIdx.setValue(rightPeakIdx);

		return highPeaksMap;
	}

	private double dataSlope(Double firstExtIdx, Double lastExtIdx, Double[] extrems) {
		double slope = (extrems[lastExtIdx.intValue()]-extrems[firstExtIdx.intValue()])/(lastExtIdx-firstExtIdx);
		return slope;
	}


	private double dataRegressionSlope(Double[] extrems) {

		List<double[]> regInput = new ArrayList<double[]>();
		int j = 0;
		for (int i = 0; i < extrems.length; i++) {
			if (extrems[i] != 0d) {
				regInput.add(new double[]{j,extrems[i]});
			}
			j++;
		}
		SimpleRegression regression = new SimpleRegression();
		regression.addData(regInput.toArray(new double[0][]));
		double slope = regression.getSlope();

		return slope;

	}

	@Override
	public Boolean higherHigh(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed, SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd, Double minSlope,
			Double maxSlope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean lowerHigh(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed, SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd, Double minSlope,
			Double maxSlope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean higherLow(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed, SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd, Double minSlope,
			Double maxSlope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean lowerLow(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			Greed greed, SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent,
			Double lowestStart, Double highestStart, Double lowestEnd, Double highestEnd, Double minSlope,
			Double maxSlope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean flatHigh(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent, Double lowestStart,
			Double highestStart, Double tolerance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean flatLow(SortedMap<Integer, Double> data, int smoothingPeriod, double minimumSurfaceOfChange,
			SortedMap<Integer, Double> _higherHighs, List<Line<Integer, Double>> _expertTangent, Double lowestStart,
			Double highestStart, Double tolerance) {
		// TODO Auto-generated method stub
		return null;
	}
}
