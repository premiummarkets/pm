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
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public abstract class TalibIndicatorsOperator extends IndicatorsOperator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsOperator.class);

	SortedMap<Date, double[]> calculationOutput;

	public TalibIndicatorsOperator(Observer... observers) {
		super(observers);
		this.calculationOutput = new TreeMap<Date, double[]>();
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) throws TalibException {
		
		initIndicators(quotations);
	
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
		FormulatRes res;
		
		try {
			
			for (int quotationIndex = quotations.getFirstDateShiftedIdx() + getOutputBeginIdx(); quotationIndex <= quotations.getLastDateIdx() ; quotationIndex++) {
				
				QuotationUnit qU = quotations.get(quotationIndex);
				res = eventFormulaCalculation(qU, quotationIndex);
				res.setCurrentDate(qU.getDate());
				
				if (res.formulaTrend() != 0) {
					Date current = res.getCurrentDate();
					EventType eventType = EventType.valueOf(res.formulaTrend() + 1);
					StandardEventKey iek = new StandardEventKey(current, res.getEventDefinition(), eventType);
					EventValue iev = new StandardEventValue(current, res.getEventDefinition(), eventType, eventListName);
					edata.put(iek, iev);
				}
				
			}
			
		} catch (Exception e) {
			String message = "Talib indicator failed :"+this.getClass().getSimpleName()+" for "+quotations.getStock();
			LOGGER.error(message,e);
			throw new TalibException(message, e);
		} finally {
			if (LOGGER.isTraceEnabled()) {
				exportToCSV(edata, quotations, eventListName);
			}
		}
		
		calculationOutput = buildOutput(quotations); //Here because divergences output can't be calculated in the loop
		
		return edata;

	}

	protected abstract void initIndicators(Quotations quotations) throws TalibException;

	protected abstract FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException;
	
	protected abstract Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex);

	protected Integer getIndicatorIndexFromQuotationIndex(TalibIndicator indicator, Integer quotationIndex) {
		Integer indicatorIndex = quotationIndex - indicator.getOutBegIdx().value;
		return indicatorIndex;
	}
	
	public void exportToCSV(Map<EventKey, EventValue> edata, Quotations quotations, String eventListName) {
		
		String stockName = quotations.getStock().getName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) ]", "_");
		File export = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + stockName + "_"+ this.getClass().getSimpleName() + "_" + eventListName +".csv");

		FileWriter fos = null;
		try {
			fos = new FileWriter(export);
			String header = getHeader(null);
			fos.write(header);
			for (int i = quotations.getFirstDateShiftedIdx(); i <= quotations.getLastDateIdx(); i++) {
				String line = buildLine(i, edata, quotations.get(i), null);
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

	protected abstract String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects);
	

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

	protected SortedMap<Date, double[]> buildOutput(Quotations quotations) {
		
		Boolean returnOutput = Boolean.valueOf(MainPMScmd.getMyPrefs().get("indicators.returnoutput", "false"));

		SortedMap<Date, double[]> outputMap = new TreeMap<Date, double[]>();
		if (returnOutput) {
			for (int i = quotations.getFirstDateShiftedIdx() + getOutputBeginIdx(); i <= quotations.getLastDateIdx(); i++) {
				QuotationUnit quotationUnit = quotations.get(i);
				Date calculatorDate = quotationUnit.getDate();
				double[] output = buildOneOutput(quotationUnit, i);
				outputMap.put(calculatorDate, output);
			}
		}

		return outputMap;
	}


	protected abstract double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx);

	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

	public abstract Integer getOutputBeginIdx();

    public abstract void genericInit(Integer... constants);

}
