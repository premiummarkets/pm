package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class HighLowSolver {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HighLowSolver.class);
	
	//Peaks
	public Boolean higherHigh(Double[] periodData, Double[] periodSmoothedCeiling, ArrayList<Double> regLine) {
		
		MutableInt firstPeakIdx = new MutableInt(-1);
		MutableInt lastPeakIdx = new MutableInt(-1); 
		
		//build high peaks array
		Double[] highPeaks = highPeaks(firstPeakIdx, lastPeakIdx, periodData, periodSmoothedCeiling);
		if (firstPeakIdx.intValue() == -1) return false;
		
		//Check the peak slope
		Double slopeCoef = dataSlope(highPeaks);
		if (goesLowerOrFlat(slopeCoef)) return false;
		
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
			if (regLine[i] < periodData[i]) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesLowerOrFlat(Double slopeCoef) {
		return slopeCoef <= 0 || slopeCoef.isInfinite() || slopeCoef.isNaN();
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
		Double[] highPeaks = highPeaks(firstPeakIdx, lastPeakIdx, periodData, periodSmoothedCeiling);
		if (firstPeakIdx.intValue() == -1) return false;
		
		Double slopeCoef = dataSlope(highPeaks);
		if (goesHigherOrFlat(slopeCoef)) return false;
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isBelowRegLine(periodData, firstPeakIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("lh",periodData, periodSmoothedCeiling, highPeaks, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		return isTrue;

	}

	private Double[] highPeaks(MutableInt firstPeakIdx, MutableInt lastPeakIdx, Double[] periodData, Double[] periodSmoothedCeiling) {
		
		Double max = -1d;
		List<Double> highPeaks = new ArrayList<Double>();
		highPeaks.add(0d);
		for (int i = 1 ; i < periodData.length-1; i++) {
			if (periodData[i-1] < periodData[i] && periodData[i+1] < periodData[i] && periodSmoothedCeiling[i] <= periodData[i]) {
				highPeaks.add(periodData[i]);
				if (firstPeakIdx.intValue() == -1) firstPeakIdx.setValue(i);
				lastPeakIdx.setValue(i);
			} else {
				highPeaks.add(0d);
			}
			if (periodData[i]  > max) max = periodData[i];
		}
		if (periodData[periodData.length-1] >= max) {
			highPeaks.add(periodData[periodData.length-1]);
			lastPeakIdx.setValue(periodData.length-1);
		} else {
			highPeaks.add(0d);
		}
		
		return highPeaks.toArray(new Double[0]);
	}
	
	//Lows
	public Boolean higherLow(Double[] periodData, Double[] periodSmotherFloor, ArrayList<Double> regLine) {

		MutableInt firstThroughIdx = new MutableInt(-1); 
		MutableInt lastThroughIdx = new MutableInt(-1); 
		Double[] lowThrough = lowThroughs(firstThroughIdx, lastThroughIdx, periodData, periodSmotherFloor);
		if (firstThroughIdx.intValue() == -1) return false;
		
		Double slopeCoef = dataSlope(lowThrough);
		if (goesLowerOrFlat(slopeCoef)) return false;
		
		
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
		
		//build high peaks array
		Double[] lowThroughs = lowThroughs(firstThroughIdx, lastThroughIdx, periodData, periodSmoothedFloor);
		if (firstThroughIdx.intValue() == -1) return false;
	
		//Check the through slope
		Double slopeCoef = dataSlope(lowThroughs);
		if (goesHigherOrFlat(slopeCoef)) return false;
		
		Double[] regLineArray = new Double[periodData.length];
		boolean isTrue = isAboveRegLine(periodData, lastThroughIdx, slopeCoef, regLineArray);
		
		if (isTrue) {
			printRes("ll",periodData, periodSmoothedFloor, lowThroughs, slopeCoef, regLineArray);
			regLine.addAll(Arrays.asList(regLineArray));
		}
		
		return isTrue;


	}

	protected boolean isAboveRegLine(Double[] periodData, MutableInt adjacentIdx, Double slopeCoef, Double[] regLine) {
		boolean isTrue = true;
		regLine[0] = periodData[adjacentIdx.intValue()] - adjacentIdx.intValue()*slopeCoef;
		for (int i = 1; i < periodData.length; i++) {
			regLine[i] = (slopeCoef * 1) +  regLine[i-1];
			if (regLine[i] > periodData[i]) {
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	protected boolean goesHigherOrFlat(Double slopeCoef) {
		return slopeCoef >= 0 || slopeCoef.isInfinite() || slopeCoef.isNaN();
	}
	
	private Double[] lowThroughs(MutableInt firstThroughIdx, MutableInt lastThroughIdx, Double[] periodData, Double[] periodSmoothedFloor) {
		
		Double min = Double.MAX_VALUE;
		List<Double> lowThroughs = new ArrayList<Double>();
		lowThroughs.add(0d);
		for (int i = 1 ; i < periodData.length-1; i++) {
			if (periodData[i-1] > periodData[i] && periodData[i+1] > periodData[i] && periodSmoothedFloor[i] >= periodData[i]) {
				lowThroughs.add(periodData[i]);
				if (firstThroughIdx.intValue() == -1) firstThroughIdx.setValue(i);
				lastThroughIdx.setValue(i);
			} else {
				lowThroughs.add(0d);
			}
			if (periodData[i] < min) min = periodData[i];
		}
		if (periodData[periodData.length-1] <= min) {
			lowThroughs.add(periodData[periodData.length-1]);
			lastThroughIdx.setValue(periodData.length-1);
		} else {
			lowThroughs.add(0d);
		}
		
		return lowThroughs.toArray(new Double[0]);
	}
	
	
	private double dataSlope(Double[] extrems) {

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
