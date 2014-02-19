/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.finance.pms.threads.ConfigThreadLocal;

public class VarianceCalculator extends TalibIndicatorsCompositionCalculator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(VarianceCalculator.class);

	private Integer timePeriod;
	private Integer devSpanDiff;
	private Integer minValid;

	
	private FileWriter fos;
	private String analysisName;
	
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public VarianceCalculator(Stock stock, Integer timePeriod, Integer devSpanDiff, Integer minValid, Date startDate, Date endDate, Currency transactionCurrency, String analysisName) 
																																										throws NotEnoughDataException {
		super(stock, startDate, endDate, transactionCurrency,  timePeriod);
		this.analysisName = analysisName;
		this.timePeriod = timePeriod;
		this.devSpanDiff = devSpanDiff;
		this.minValid = minValid;
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			//calendar.add(Calendar.DAY_OF_YEAR, - timePeriod);
			QuotationsFactories.getFactory().incrementDate(calendar, -timePeriod);
			Integer smaPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVarianceSmaPeriod();
			this.sma = new SMA(stock, smaPeriod, startDate, endDate, transactionCurrency);
		} catch (TalibException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		initExport();
		
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

	/**
	 * @param variations
	 * @param startAvgIdx
	 * @param calculationEndIdx
	 * @param meanPreviousVariation
	 * @return
	 */
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

	/**
	 * @param startAvgIdx
	 * @param calculationEndIdx
	 * @param variations
	 * @param avgPreviousVariation
	 * @return
	 */
	double mean(Integer startAvgIdx, Integer calculationEndIdx, double[] variations) {
		double avgPreviousVariation = 0;
		for (int i = startAvgIdx; i < calculationEndIdx ; i++) {
			avgPreviousVariation = avgPreviousVariation + variations[i];
		}
		avgPreviousVariation = avgPreviousVariation / (calculationEndIdx - startAvgIdx);
		return avgPreviousVariation;
	}

	/**
	 * @param quotationCloseValues
	 * @param iStart
	 * @return
	 */
	private double periodVariation(double[] quotationCloseValues, int iStart, int iEnd) {
		double closeAtI = quotationCloseValues[iEnd];
		double closeAtIPeriodStart = quotationCloseValues[iStart];
		return (closeAtI-closeAtIPeriodStart)/closeAtIPeriodStart;
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calcQuotationIdx) {
		FormulatRes res = new FormulatRes(EventDefinition.VARIANCE);
		Date date = this.getCalculatorQuotationData().getDate(calcQuotationIdx);
		res.setCurrentDate(date);
		
		Integer smaIndQuoteIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calcQuotationIdx, smaQuotationStartDateIdx);
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
		
		exportLineToFile(date, this.getCalculatorQuotationData().get(calcQuotationIdx).getClose(), smaData[smaIndQuoteIndex], resType);

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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.VARIANCE;
	}

	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		// TODO Auto-generated method stub
		return null;
	}


}
