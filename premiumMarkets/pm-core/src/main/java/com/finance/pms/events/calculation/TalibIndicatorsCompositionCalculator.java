/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibIndicator;

public abstract class TalibIndicatorsCompositionCalculator extends EventCompostionCalculator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsCompositionCalculator.class);
	
	 SortedMap<Date, double[]>  calculationOutput;
	
	public TalibIndicatorsCompositionCalculator(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		this.calculationOutput = new TreeMap<Date, double[]>();
	}

	public TalibIndicatorsCompositionCalculator(Stock stock, Date startDate, Date endDate, Currency transactionCurrency, int calculatorIndexShift) throws NotEnoughDataException {
		super(stock, startDate, endDate, transactionCurrency, calculatorIndexShift);
		this.calculationOutput = new TreeMap<Date, double[]>();
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(String eventListName) {
		
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
		
		FormulatRes res;
		try {
			for (int quotationIndex = calculationStartIdx; quotationIndex <= calculationEndIdx ; quotationIndex++) {
				res = eventFormulaCalculation(quotationIndex);
				res.setCurrentDate(this.getCalculatorQuotationData().getDate(quotationIndex));
				
				if (res.formulaTrend() != 0) {
					Date current = res.getCurrentDate();
					EventType eventType = EventType.valueOf(res.formulaTrend() + 1);
					StandardEventKey iek = new StandardEventKey(current, res.getEventDefinition(), eventType);
					EventValue iev = new StandardEventValue(current, res.getEventDefinition(), eventType, eventListName);
					edata.put(iek, iev);
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("",e);
		} finally {
			if (LOGGER.isTraceEnabled()) {
				exportToCSV(edata, eventListName);
			}
		}
		
		calculationOutput = buildOutput();
		

		return edata;

	}

	protected abstract FormulatRes eventFormulaCalculation(Integer quotationIdx) throws InvalidAlgorithmParameterException;
	
	
	/**
	 * @param stock
	 * @param indicator
	 * @param startDate
	 * @param indicatorQuotationEndDateIdx
	 * @throws NotEnoughDataException
	 */
	protected void isValidData(Stock stock, TalibIndicator indicator, Date startDate, Integer indicatorQuotationStartDateIdx, Integer indicatorQuotationEndDateIdx) throws NotEnoughDataException {
		
		if (indicator.getOutBegDate() == null) {
			throw new NotEnoughDataException(stock, indicator.toString(),new Throwable());
		}
		if ((indicatorQuotationEndDateIdx - indicatorQuotationStartDateIdx) > indicator.getOutNBElement().value 
				|| indicator.getOutBegDate().after(startDate) 
				|| !isInDataRange(indicator,getIndicatorIndexFromCalculatorQuotationIndex(indicator, calculationStartIdx, indicatorQuotationStartDateIdx)) 
				|| !isInDataRange(indicator,getIndicatorIndexFromCalculatorQuotationIndex(indicator, calculationEndIdx, indicatorQuotationStartDateIdx))) {
			String message = "Not enought quotations for calculating indicator "+ indicator.getClass().getSimpleName() +" for calculator "+ this.getClass().getSimpleName() + " for " + stock.getName() + "!\n "
					+ " \t\tCalculation start date requested from " + new SimpleDateFormat("yyyy-MM-dd").format(startDate)+ ". Indicator first date availabe : "+indicator.getOutBegDate() + ".\n"
					+ " \t\tIndicator indexes may be out of range : from " + this.getIndicatorIndexFromCalculatorQuotationIndex(indicator, calculationStartIdx, indicatorQuotationStartDateIdx) + " to "+ this.getIndicatorIndexFromCalculatorQuotationIndex(indicator, calculationEndIdx, indicatorQuotationStartDateIdx) + ".\n"
					+ " \t\tNb indicator quotations requested : " + (indicatorQuotationEndDateIdx - indicatorQuotationStartDateIdx)+" And calculated " + indicator.getOutNBElement().value + ".\n"
					+ " \t\tAvailable quotations for indicator init : "+  getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculationStartIdx, indicatorQuotationStartDateIdx) + ".\n"
					+ " \t\tIf you realy want that one calculated, quotation data investigation is needed as there may be some gaps in the quotations data leading to insufficient amount of quotations for the indicator calculation.";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(indicator.getOutBegDate());
			QuotationsFactories.getFactory().incrementDate(calendar, getDaysSpan());
			throw new NotEnoughDataException(stock, calendar.getTime(), stock.getLastQuote(), message, new Throwable());
		}
		
	}
	
	
	protected abstract Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex);

	/**
	 * @param calculatorQuotationIndex
	 * @param indicatorQuotationStartIdx 
	 * @return
	 */
	protected Integer getIndicatorIndexFromCalculatorQuotationIndex(TalibIndicator indicator, Integer calculatorQuotationIndex, Integer indicatorQuotationStartIdx) {
		int indicatorQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorQuotationIndex, indicatorQuotationStartIdx);
		Integer indicatorIndex = indicatorQuotationIndex - indicator.getOutBegIdx().value;
		return indicatorIndex;
	}
	
	/**
	 * @param calculatorQuotastrionIndex
	 * @return
	 */
	protected int getIndicatorQuotationIndexFromCalculatorQuotationIndex(Integer calculatorQuotationIndex, Integer indicatorQuotationStartIdx) {
		int deltaFromCalulatorStartIdx = calculatorQuotationIndex - calculationStartIdx;
		int indicatorQuotationIndex = deltaFromCalulatorStartIdx + indicatorQuotationStartIdx;
		return indicatorQuotationIndex;
	}
	
	/**
	 * Export mac dto csv.
	 * 
	 * @param filename the filename
	 * 
	 * @author Guillaume Thoreton
	 * @param edata 
	 * @param eventListName 
	 */
	public void exportToCSV(Map<EventKey, EventValue> edata, String eventListName) {
		
		File export = 
				new File(
						System.getProperty("installdir") + File.separator + "tmp" + File.separator +
						this.stock.getName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) ]", "_") + "_"+ this.getClass().getSimpleName() + "_" + eventListName +".csv");

		FileWriter fos = null;
		try {
			fos = new FileWriter(export);
			String header = getHeader(null);
			fos.write(header);
			for (int i = calculationStartIdx; i <= calculationEndIdx; i++) {
				String line = buildLine(i, edata, null);
				fos.write(line);
			}
			fos.flush();
			
		} catch (Exception e) {
			LOGGER.error("", e);
		}  finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error("", e);
				}
		}
		
	}
	
	protected String addScoringHeader(String head, List<Integer> scoringSmas) {
		if (scoringSmas == null) return head;
		for (Integer integer : scoringSmas) {
			head = head + ", sma "+integer;
		}
		return head;
	}

	
	protected String addScoringLinesElement(String line, Date date, List<SortedMap<Date, double[]>> linearsExpects) {
		
		if (linearsExpects == null) return line;
		
		for (SortedMap<Date, double[]> linear : linearsExpects) {
			double[] ds = linear.get(date);
			if (ds != null) {
				line = line + ds[0]+ ",";
			} else {
				line = line + "0,";
			}
		}
		
		return line;
	}

	protected  abstract String getHeader(List<Integer> scoringSmas);

	protected abstract String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects);
	

	protected Boolean lowerLow(double[] data, double[] threshCurve) {
		
		//build low through array
		List<Double> lowThrough = lowThrough(data, threshCurve);
		
		//linear reg of the curve
		double dataSlope = dataSlope(lowThrough);
		
		return dataSlope < 0;

	}

	private List<Double> lowThrough(double[] data, double[] threshCurve) {
		List<Double> lowThrough = new ArrayList<Double>();
		for (int i = 1 ; i < data.length-1; i++) {
			if (data[i-1] > data[i] && data[i] < data[i+1] && data[i] < threshCurve[i]) {// low through
				lowThrough.add(data[i]);
			}
		}
		return lowThrough;
	}


	protected Boolean higherHigh(double[] data, double[] threshCurve) {

		//build high peaks array
		List<Double> highPeaks = highPeaks(data, threshCurve);

		//linear reg of the curve
		double dataSlope = dataSlope(highPeaks);

		return dataSlope > 0;

	}

	private List<Double> highPeaks(double[] data, double[] threshCurve) {
		List<Double> highPeaks = new ArrayList<Double>();
		for (int i = 1 ; i < data.length-1; i++) {
			if (data[i-1] < data[i] && data[i] > data[i+1] && data[i] > threshCurve[i]) {
				highPeaks.add(data[i]);
			}
		}
		return highPeaks;
	}
	

	protected Boolean higherLow(double[] data, double[] threshCurve) {
		
		//build low through array
		List<Double> lowThrough = lowThrough(data, threshCurve);
		
		//linear reg of the curve
		double dataSlope = dataSlope(lowThrough);
		
		return dataSlope > 0;
		
		
	}
	

	protected Boolean lowerHigh(double[] data, double[] threshCurve) {
		
		//build high peaks array
		List<Double> highPeaks = highPeaks(data, threshCurve);

		//linear reg of the curve
		double dataSlope = dataSlope(highPeaks);

		return dataSlope < 0;
		
	}
	
	private double dataSlope(List<Double> data) {
		double[] dataArr = new double[data.size()];
		for (int i = 0; i < dataArr.length; i++) {
			dataArr[i] = data.get(i);
		}
		return dataSlope(dataArr);
	}

	protected double dataSlope(double[] data) {
		
		//linear reg of the curve
		double[][] regInput = new double[data.length][];
		for (int i = 0; i < data.length; i++) {
			regInput[i] = new double[]{i,data[i]};
		}
		SimpleRegression regression = new SimpleRegression();
		regression.addData(regInput);
		double slope = regression.getSlope();
		
		return slope;
	
	}
	
	public  SortedMap<Date, double[]> calculationOutput() {
		return calculationOutput;
	}

	protected SortedMap<Date, double[]> buildOutput() {
		
		Boolean returnOutput = Boolean.valueOf(MainPMScmd.getPrefs().get("indicators.returnoutput", "false"));

		SortedMap<Date, double[]> outputMap = new TreeMap<Date, double[]>();
		if (returnOutput) {
			for (int i = calculationStartIdx; i <= calculationEndIdx; i++) {
				Date calculatorDate = this.getCalculatorQuotationData().get(i).getDate();
				double[] output = buildOneOutput(i);
				outputMap.put(calculatorDate, output);
			}
		}

		return outputMap;
	}


	protected abstract double[] buildOneOutput(int calculatorIndex);

	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

	@Override
	protected Date rawDataStartDate(Date startDate, Date endDate) {
		return startDate;
	}


}
