package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;
import org.apache.commons.math.stat.regression.SimpleRegression;

import com.finance.pms.admin.install.logging.MyLogger;


/**
 * 
 * @author Guillaume Thoreton
 * ll  :  
 * 		^^^^^
 * 		_____ and down
 * hl  :   
 *  	^^^^^
 * 		_____ and up
 * lh  : 
 * 		_____
 * 		^^^^^ and down
 * hh :
 * 		_____
 * 		^^^^^ and up
 *
 */

//TODO simplify this if we don't use the regression any more??
public class HighLowSolver {
	

	private static MyLogger LOGGER = MyLogger.getLogger(HighLowSolver.class);
	
	private static final double UPPER_EXTREM_BALANCE = .8;
	private static final double LOWER_EXTREM_BALANCE = .2;
	
	private static final double REGLINE_TOLERANCE = 0.01;
	private static final double REGLINE_SLOPEMIN = 0.03;
	
	//Peaks
	public Boolean higherHigh(Double[] periodData, Double[] periodSmoothedCeiling, ArrayList<Double> regLine) {
		
		MutableInt firstPeakIdx = new MutableInt(-1);
		MutableInt lastPeakIdx = new MutableInt(-1); 
		MutableDouble amountBelowSmoothingCeiling = new MutableDouble(0);
		
		//build high peaks array
		Double[] highPeaks = highPeaks(firstPeakIdx, lastPeakIdx, amountBelowSmoothingCeiling, periodData, periodSmoothedCeiling);
		if (firstPeakIdx.intValue() == -1) return false;
		if (isNotBalanced(amountBelowSmoothingCeiling, firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), periodData.length)) return false;
		
		//Check the peak slope
		Double slopeCoef = dataSlope(firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), highPeaks);
		if (goesDownOrFlat(slopeCoef)) return false;
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isBelowRegLine(periodData, lastPeakIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("hh",periodData, periodSmoothedCeiling, highPeaks, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		
		return isTrue;

	}

	protected boolean isBelowRegLine(Double[] periodData, MutableInt adjacentIdx, Double slopeCoef, Double[] regLine) {
		boolean isTrue = true;
		regLine[0] = periodData[adjacentIdx.intValue()] - adjacentIdx.intValue()*slopeCoef;
		for (int i = 1; i < periodData.length; i++) {
			regLine[i] = (slopeCoef * 1) +  regLine[i-1];
			if ( regLine[i] < periodData[i] - Math.abs(periodData[i]*REGLINE_TOLERANCE) ) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesDownOrFlat(Double slopeCoef) {
		return slopeCoef <= REGLINE_SLOPEMIN || slopeCoef.isInfinite() || slopeCoef.isNaN();
	}
	
	private void printRes(String source, Double[] periodData, Double[] periodSmoothedThresh, Double[] extrems, double slopeCoef, Double[] regLine) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("source : "+ source+ ", regLine coefficient "+slopeCoef);
			LOGGER.debug("periodData,periodThreshCurve,extremes,dataSlope");
			for (int i = 0; i < extrems.length; i++) {
				LOGGER.debug(periodData[i]+","+periodSmoothedThresh[i]+","+extrems[i]+","+regLine[i]);
				
			}
		}
	}

	public Boolean lowerHigh(Double[] periodData, Double[] periodSmoothedCeiling, ArrayList<Double> regLine) {

		MutableInt firstPeakIdx = new MutableInt(-1); 
		MutableInt lastPeakIdx = new MutableInt(-1); 
		MutableDouble amountBelowSmoothingCeiling = new MutableDouble(0);
		
		Double[] highPeaks = highPeaks(firstPeakIdx, lastPeakIdx, amountBelowSmoothingCeiling, periodData, periodSmoothedCeiling);
		if (firstPeakIdx.intValue() == -1) return false;
		if (isNotBalanced(amountBelowSmoothingCeiling,firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), periodData.length)) return false;
		
		Double slopeCoef = dataSlope(firstPeakIdx.doubleValue(), lastPeakIdx.doubleValue(), highPeaks);
		if (goesUpOrFlat(slopeCoef)) return false;
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isBelowRegLine(periodData, firstPeakIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("lh",periodData, periodSmoothedCeiling, highPeaks, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		return isTrue;

	}

	private Double[] highPeaks(MutableInt firstPeakIdx, MutableInt lastPeakIdx, MutableDouble amountBelowSmoothingCeiling, Double[] periodData, Double[] periodSmoothedCeiling) {
		
		boolean noSmoothedConstraint = periodSmoothedCeiling.length == 0;
		
		Double max = -1d;
		List<Double> highPeaks = new ArrayList<Double>();
		highPeaks.add(0d);
		for (int i = 1 ; i < periodData.length -1; i++) {
			
			if (noSmoothedConstraint || periodSmoothedCeiling[i] > periodData[i]) {
				amountBelowSmoothingCeiling.increment();
			}
			
			boolean isIdxAbovePrev = periodData[i-1] < periodData[i];
			boolean isIdxAboveSucc = periodData[i+1] < periodData[i];
			boolean isIdxAboveSmoothed = noSmoothedConstraint || periodSmoothedCeiling[i] <= periodData[i];
			if (isIdxAbovePrev && isIdxAboveSucc && isIdxAboveSmoothed) {
				highPeaks.add(periodData[i]);
				double idxBalance = ((double)i)/(double)periodData.length;
				if (firstPeakIdx.intValue() == -1 || (idxBalance < LOWER_EXTREM_BALANCE && periodData[i] >=  periodData[firstPeakIdx.intValue()])) firstPeakIdx.setValue(i);
				if (lastPeakIdx.intValue() == -1 || idxBalance <= UPPER_EXTREM_BALANCE || (idxBalance > UPPER_EXTREM_BALANCE && periodData[i] >  periodData[lastPeakIdx.intValue()])) lastPeakIdx.setValue(i);
			} else {
				highPeaks.add(0d);
			}
			
			if (periodData[i]  > max) max = periodData[i];
		}
		
		if (periodData[periodData.length-1] >= max && (noSmoothedConstraint || periodSmoothedCeiling[periodData.length-1] <= periodData[periodData.length-1])) {
			highPeaks.add(periodData[periodData.length-1]);
			lastPeakIdx.setValue(periodData.length-1);
		} else {
			highPeaks.add(0d);
		}
		
		amountBelowSmoothingCeiling.setValue(amountBelowSmoothingCeiling.doubleValue()/periodData.length);
		
		return highPeaks.toArray(new Double[0]);
	}
	
	//Lows
	public Boolean higherLow(Double[] periodData, Double[] periodSmotherFloor, ArrayList<Double> regLine) {
		
		MutableInt firstThroughIdx = new MutableInt(-1); 
		MutableInt lastThroughIdx = new MutableInt(-1); 
		MutableDouble amountAboveSmoothingFloor = new MutableDouble(0);
		
		Double[] lowThrough = lowThroughs(firstThroughIdx, lastThroughIdx, amountAboveSmoothingFloor, periodData, periodSmotherFloor);
		if (firstThroughIdx.intValue() == -1) return false;
		if (isNotBalanced(amountAboveSmoothingFloor,firstThroughIdx.doubleValue(), lastThroughIdx.doubleValue(), periodData.length)) return false;
		
		Double slopeCoef = dataSlope(firstThroughIdx.doubleValue(), lastThroughIdx.doubleValue(), lowThrough);
		if (goesDownOrFlat(slopeCoef)) return false;
		
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isAboveRegLine(periodData, firstThroughIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("hl",periodData, periodSmotherFloor, lowThrough, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		return isTrue;


	}
	
	public Boolean lowerLow(Double[] periodData, Double[] periodSmoothedFloor, ArrayList<Double> regLine) {
		
		MutableInt firstThroughIdx = new MutableInt(-1);
		MutableInt lastThroughIdx = new MutableInt(-1); 
		MutableDouble amountAboveSmoothingFloor = new MutableDouble(0);
		
		//build high peaks array
		Double[] lowThroughs = lowThroughs(firstThroughIdx, lastThroughIdx, amountAboveSmoothingFloor, periodData, periodSmoothedFloor);
		if (firstThroughIdx.intValue() == -1) return false;
		if (isNotBalanced(amountAboveSmoothingFloor,firstThroughIdx.doubleValue(), lastThroughIdx.doubleValue(), periodData.length)) return false;
	
		//Check the through slope
		Double slopeCoef = dataSlope(firstThroughIdx.doubleValue(), lastThroughIdx.doubleValue(), lowThroughs);
		if (goesUpOrFlat(slopeCoef)) return false;
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isAboveRegLine(periodData, lastThroughIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("ll",periodData, periodSmoothedFloor, lowThroughs, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		
		return isTrue;


	}

	protected boolean isNotBalanced(MutableDouble amountInSmoothed, double firstExtrem, double lastExtrem, double dataLength) {
		//return (amountInSmoothed.doubleValue() < .1 || firstExtrem/dataLength > .2 || lastExtrem/dataLength < .8);
		return (firstExtrem/dataLength > LOWER_EXTREM_BALANCE || lastExtrem/dataLength < UPPER_EXTREM_BALANCE);
	}

	protected boolean isAboveRegLine(Double[] periodData, MutableInt adjacentIdx, Double slopeCoef, Double[] regLine) {
		boolean isTrue = true;
		regLine[0] = periodData[adjacentIdx.intValue()] - adjacentIdx.intValue()*slopeCoef;
		for (int i = 1; i < periodData.length; i++) {
			regLine[i] = (slopeCoef * 1) +  regLine[i-1];
			if ( regLine[i] > periodData[i] + Math.abs(periodData[i]*REGLINE_TOLERANCE) ) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesUpOrFlat(Double slopeCoef) {
		return slopeCoef >= -REGLINE_SLOPEMIN || slopeCoef.isInfinite() || slopeCoef.isNaN();
	}
	
	private Double[] lowThroughs(MutableInt firstThroughIdx, MutableInt lastThroughIdx, MutableDouble amountAboveSmoothingFloor, Double[] periodData, Double[] periodSmoothedFloor) {
		
		boolean noSmoothedConstraint = periodSmoothedFloor.length == 0;
		
		Double min = Double.MAX_VALUE;
		List<Double> lowThroughs = new ArrayList<Double>();
		lowThroughs.add(0d);
		for (int i = 1 ; i < periodData.length-1; i++) {
			
			if (noSmoothedConstraint || periodSmoothedFloor[i] < periodData[i]) {
				amountAboveSmoothingFloor.increment();
			}
			
			boolean isIdxBelowPrev = periodData[i-1] > periodData[i];
			boolean isIdxBelowSucc = periodData[i+1] > periodData[i];
			boolean isIdxBelowSmothed = noSmoothedConstraint || periodSmoothedFloor[i] >= periodData[i];
			if ( isIdxBelowPrev && isIdxBelowSucc && isIdxBelowSmothed) {
				lowThroughs.add(periodData[i]);
				double idxBalance = ((double)i)/(double)periodData.length;
				if (firstThroughIdx.intValue() == -1 || (idxBalance < LOWER_EXTREM_BALANCE && periodData[i] <=  periodData[firstThroughIdx.intValue()])) firstThroughIdx.setValue(i);
				if (lastThroughIdx.intValue() == -1 ||  idxBalance <= UPPER_EXTREM_BALANCE || (idxBalance > UPPER_EXTREM_BALANCE && periodData[i] <  periodData[lastThroughIdx.intValue()])) lastThroughIdx.setValue(i);
			} else {
				lowThroughs.add(0d);
			}
			
			if (periodData[i] < min) min = periodData[i];
		}

		if (periodData[periodData.length-1] < min  && (noSmoothedConstraint || periodSmoothedFloor[periodData.length-1] >= periodData[periodData.length-1])) {
			lowThroughs.add(periodData[periodData.length-1]);
			lastThroughIdx.setValue(periodData.length-1);
		} else {
			lowThroughs.add(0d);
		}
		
		amountAboveSmoothingFloor.setValue(amountAboveSmoothingFloor.doubleValue()/periodData.length);
		
		return lowThroughs.toArray(new Double[0]);
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

}
