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
package com.finance.pms.events.calculation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class VarianceCalculator extends TalibIndicatorsOperator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(VarianceCalculator.class);

	private Integer timePeriod;
	private Integer devSpanDiff;
	private Integer minValid;

	
	private FileWriter fos;

	private Stock stock;
	private String analysisName;
	
	private SMA sma;

	

	public VarianceCalculator(Stock stock, Integer timePeriod, Integer devSpanDiff, Integer minValid, Integer smaPeriod, String eventListName, Observer... observers) {
		super(observers);
		this.stock =stock;
		this.analysisName = eventListName;
		init(timePeriod, devSpanDiff, minValid, smaPeriod);
		
		if (LOGGER.isTraceEnabled()) initExport();
	}
	
	public VarianceCalculator() {
        //Reflective ops generator
    }

    protected void init(Integer timePeriod, Integer devSpanDiff, Integer minValid, Integer smaPeriod) {
        this.timePeriod = timePeriod;
        this.devSpanDiff = devSpanDiff;
        this.minValid = minValid;
        this.sma = new SMA(smaPeriod);
    }

    @Override
    public void genericInit(Integer... constants) {
        init(constants[0], constants[1], constants[2], constants[3]);
    }

	private void initExport() {
		String fileName = stock.getSymbol()+"_"+ this.analysisName+"_"+ EventDefinition.VARIANCE;
		File export = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + fileName + "_calculatorResults.csv");
		try {
			fos = new FileWriter(export, false);
			fos.write("date,calc quote, ind quote, bearsih,none,bullish\n");
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportLineToFile(Date date, BigDecimal quotation, double indQuotation, EventType eventType) {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fos.write(dateFormat.format(date)+",");
			fos.write(quotation+",");
			fos.write(indQuotation+",");
			if (eventType.equals(EventType.BEARISH)) {
				fos.write(quotation+",0,0");
			}
			if (eventType.equals(EventType.NONE)) {
				fos.write("0,"+quotation+",0");
			}
			if (eventType.equals(EventType.BULLISH)) {
				fos.write("0,0,"+quotation);
			}
			fos.write("\n");
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			
		}
	}

	double stdDev(double[] variations, int startAvgIdx, Integer calculationEndIdx, double meanPreviousVariation) {
		double variance = 0;
		for (int i = startAvgIdx; i < calculationEndIdx ; i++) {
			double diffToMean = variations[i] - meanPreviousVariation;
			double periodVariance = diffToMean*diffToMean;
			variance = variance + periodVariance;
		}
		variance = variance/(calculationEndIdx - startAvgIdx);
		double stdDev = Math.sqrt(variance);
		return stdDev;
	}

	double mean(Integer startAvgIdx, Integer calculationEndIdx, double[] variations) {
		double avgPreviousVariation = 0;
		for (int i = startAvgIdx; i < calculationEndIdx ; i++) {
			avgPreviousVariation = avgPreviousVariation + variations[i];
		}
		avgPreviousVariation = avgPreviousVariation / (calculationEndIdx - startAvgIdx);
		return avgPreviousVariation;
	}

	private double periodVariation(double[] quotationCloseValues, int iStart, int iEnd) {
		double closeAtI = quotationCloseValues[iEnd];
		double closeAtIPeriodStart = quotationCloseValues[iStart];
		return (closeAtI-closeAtIPeriodStart)/closeAtIPeriodStart;
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.VARIANCE);
		Date date = qU.getDate();
		res.setCurrentDate(date);
		
		Integer smaIndQuoteIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		double[] smaData = this.sma.getSma();

		double lastVariation = periodVariation(smaData, smaIndQuoteIndex - devSpanDiff, smaIndQuoteIndex);
		Boolean positiveVariation = lastVariation > 0;

		List<Double> variations = new ArrayList<Double>();
		for (int i = smaIndQuoteIndex - timePeriod + devSpanDiff; i < smaIndQuoteIndex ; i++) {
			double periodVariation = periodVariation(smaData, i - devSpanDiff, i);
			if (periodVariation <= 0 && !positiveVariation) {
				variations.add(periodVariation);
			}
			if (periodVariation > 0 & positiveVariation) {
				variations.add(periodVariation);
			}
		}
		int nbSignedVariations = variations.size();
		
		EventType resType = EventType.NONE;
		if (variations.size() > minValid) {
			double[] variationsArray = new double[nbSignedVariations];
			for (int i = 0; i < nbSignedVariations; i++) {
				variationsArray[i] = variations.get(i);
			}
	
			double meanPreviousVariation = mean(0, nbSignedVariations, variationsArray);	
			double stdDev = stdDev(variationsArray, 0, nbSignedVariations, meanPreviousVariation);
	
			//Last variation
			double lastDiffToMean = lastVariation - meanPreviousVariation;
	
			if (lastDiffToMean < -stdDev && !positiveVariation) {
				res.setBearishCrossBellow(true);
				resType = EventType.BEARISH;
			} else 
			if (stdDev < lastDiffToMean &&  positiveVariation){
				res.setBullishCrossOver(true);
				resType = EventType.BULLISH;
			}
		} else {
			LOGGER.warn("Variance can't be calculated for "+stock.getSymbol()+" at "+date);
		}
		
		if (LOGGER.isTraceEnabled()) exportLineToFile(date, qU.getClose(), smaData[smaIndQuoteIndex], resType);

		return res;
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return getDaysSpan() <= indicatorIndex && indicatorIndex < (this.sma.getSma().length);
	}

	@Override
	protected int getDaysSpan() {
		return timePeriod;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.VARIANCE;
	}

	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.sma.calculateIndicator(quotations);
		
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getStartShift() {
		return Math.max(timePeriod, this.sma.getStartShift()) + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return this.sma.getOutBegIdx().value + getDaysSpan();
	}


}
