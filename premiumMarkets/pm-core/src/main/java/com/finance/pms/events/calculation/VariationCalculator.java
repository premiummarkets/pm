/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;

public class VariationCalculator extends EventCompostionCalculator {

	private int timePeriod;
	private Integer devSpanDiff;
	
	private FileWriter fos;
	private String analysisName;

	public VariationCalculator(Stock stock, Integer timePeriod, Integer devSpanDiff, Date startDate, Date endDate, Currency transactionCurrency, String analysisName) throws NotEnoughDataException {
		super(stock, startDate, endDate, transactionCurrency, timePeriod);
		this.analysisName = analysisName;
		this.timePeriod = timePeriod;
		this.devSpanDiff=devSpanDiff;
		
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
	public Map<EventKey, EventValue> calculateEventsFor(String eventListName) {
		Map<EventKey, EventValue> eventData = new HashMap<EventKey, EventValue>();

		EventType resType;
		for (int quotationIndex = calculationStartIdx; quotationIndex <= calculationEndIdx ; quotationIndex++) {
				resType = this.periodType(quotationIndex - timePeriod, quotationIndex);

				Date date = this.getCalculatorQuotationData().getDate(quotationIndex);
				if (!resType.equals(EventType.NONE)) {
					addEvent(eventData, date, EventDefinition.VARIATION, resType,"",eventListName);
				}
				
				exportLineToFile(date, this.getCalculatorQuotationData().get(quotationIndex).getClose(), resType);
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
	
	public EventType periodType(Integer calculationStartIdx, Integer calculationEndIdx) {
		
		//Avg previous variations
		double[] quotationCloseValues = this.getCalculatorQuotationData().getCloseValues();
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

	/**
	 * @param calculationStartIdx
	 * @param calculationEndIdx
	 * @param quotationCloseValues
	 * @param avgPreviousVariation
	 * @return
	 */
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


}
