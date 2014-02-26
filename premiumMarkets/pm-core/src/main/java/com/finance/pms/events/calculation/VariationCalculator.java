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
import java.util.Date;
import java.util.Observer;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.indicators.TalibException;

public class VariationCalculator extends EventCompostionCalculator {

	private int timePeriod;
	private Integer devSpanDiff;
	
	private FileWriter fos;
	private String analysisName;
	private Stock stock;

	public VariationCalculator(Stock stock, Integer variationTimePeriod, Integer variationSpanDiff, String eventListName, Observer[] observers) {
		super(observers);
		
		this.stock=stock;
		
		this.analysisName = eventListName;
		this.timePeriod = variationTimePeriod;
		this.devSpanDiff = variationSpanDiff;
		
		initExport();
	}

	private void initExport() {
		String fileName = stock.getSymbol()+"_"+ this.analysisName+"_"+ EventDefinition.VARIATION;
		File export = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + fileName + "_calculatorResults.csv");
		try {
			fos = new FileWriter(export, false);
			fos.write("date,quote,bearsih,none,bullish\n");
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName)  throws TalibException {
		SortedMap<EventKey, EventValue> eventData = new TreeMap<EventKey, EventValue>();

		EventType resType;
//		for (int quotationIndex = calculationStartIdx; quotationIndex <= calculationEndIdx ; quotationIndex++) {
		for (int quotationIndex = quotations.getFirstDateShiftedIdx(); quotationIndex <= quotations.getLastDateIdx() ; quotationIndex++) {
				resType = this.periodType(quotations, quotationIndex - timePeriod, quotationIndex);

				Date date = quotations.getDate(quotationIndex);
				if (!resType.equals(EventType.NONE)) {
					addEvent(eventData, date, EventDefinition.VARIATION, resType,"",eventListName);
				}
				
				exportLineToFile(date, quotations.get(quotationIndex).getClose(), resType);
		}

		return eventData;
	}
	
	public void exportLineToFile(Date date, BigDecimal quotation, EventType eventType) {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fos.write(dateFormat.format(date)+",");
			fos.write(quotation+",");
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
	
	public EventType periodType(Quotations quotations, Integer calculationStartIdx, Integer calculationEndIdx) {
		
		//Avg previous variations
		double[] quotationCloseValues = quotations.getCloseValues();
		double avgPreviousVariation = mean(calculationStartIdx, calculationEndIdx, quotationCloseValues);

		//Last variation
		int higherIndex = calculationEndIdx;
		int lowerIndex = calculationEndIdx-2*devSpanDiff;
		double closeAtEndOfNextPeriod = quotationCloseValues[higherIndex];
		double closeAtStartOfPeriod = quotationCloseValues[lowerIndex];
		double lastVariation = (closeAtEndOfNextPeriod  - closeAtStartOfPeriod) / closeAtStartOfPeriod;

		//Rate of last variation
		double variationRate = lastVariation/avgPreviousVariation;

		if (variationRate < -1) {
			return EventType.BEARISH;
		} 
		if (variationRate > 1){
			return EventType.BULLISH;
		}

		return EventType.NONE;
	}

	private double mean(Integer calculationStartIdx, Integer calculationEndIdx, double[] quotationCloseValues) {
		int startAvgindex = calculationStartIdx + devSpanDiff;
		double avgPreviousVariation = 0;
		for (int i = startAvgindex; i < calculationEndIdx ; i++) {
			double closeAtI = quotationCloseValues[i];
			double closeAtIPeriodStart = quotationCloseValues[i - devSpanDiff];
			double periodVariation = (closeAtI  - closeAtIPeriodStart) / closeAtIPeriodStart;
			avgPreviousVariation = avgPreviousVariation + Math.abs(periodVariation);
		}
		avgPreviousVariation = avgPreviousVariation / (calculationEndIdx -1 - startAvgindex);
		return avgPreviousVariation;
	}

	protected int getDaysSpan() {
		return 0;
	}

	@Override
	public  SortedMap<Date, double[]> calculationOutput() {
		return new TreeMap<Date, double[]>();
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.VARIATION;
	}
	
	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

	@Override
	public Integer getStartShift() {
		return timePeriod;
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}
}
