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
package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartBarUtils {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ChartBarUtils.class);
	
	public static SortedMap<DataSetBarDescr, SortedMap<Date, Double>> buildBarsData(
			Stock selectedShare, Set<EventInfo> chartedEvtDefsTrends, 
			Date start, Date end, SymbolEvents eventsForStock, Map<EventInfo, TuningResDTO> tuningRess, 
			BarSettings barSettings) {

		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barData = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
		
		try {
			
			double yValueFactor = 1d;
			int chartedTrendsEvtDefsSize = chartedEvtDefsTrends.size();
			int serieIdx = chartedTrendsEvtDefsSize*3;

			for (EventInfo eventInfo : chartedEvtDefsTrends) {

				SortedMap<Date, Double> sellS = new TreeMap<Date,Double>();
				SortedMap<Date, Double> buyS = new TreeMap<Date,Double>();
				SortedMap<Date, Double> indeterS = new TreeMap<Date,Double>();
				EventValue prevEventValue = null;

				SortedMap<EventKey, EventValue> sortedDataResultMap = eventsForStock.getSortedDataResultMap();
				for (EventKey eventKey : sortedDataResultMap.keySet()) {

					if (eventKey.getEventInfo().equals(eventInfo)) {
						if (prevEventValue != null) {
							
							Date currEvtDate = eventKey.getDate();
							if (eventInfo.getIsContinous()) {
								cheesyFillBarChart(yValueFactor, sellS, buyS, indeterS, prevEventValue, currEvtDate, 1);
							} else {
								cheesyFillBarChart(yValueFactor, sellS, buyS, indeterS, prevEventValue, currEvtDate, barSettings.getMaxFill());
							}
			
						}
						prevEventValue = sortedDataResultMap.get(eventKey);
					}

				} 

				//Filling up to the end only for non continuous events
				if (prevEventValue != null && !eventInfo.getIsContinous()) {
					cheesyFillBarChart(yValueFactor, sellS, buyS, indeterS, prevEventValue, end,  barSettings.getMaxFill());
				}

				int gradiant = ( barSettings.getIsGradiant())?serieIdx/3:1;
				int alpha = 255 / (int) Math.ceil(  barSettings.getAlphaDividend()*gradiant );
				TuningResDTO tuningResDTO = tuningRess.get(eventInfo);
				
				DataSetBarDescr buyKey = 
						new DataSetBarDescr(
							serieIdx, 
							eventInfo.info()+" buy", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
							//0, new java.awt.Color(189,249,189, alpha), 10f);
							//0, new java.awt.Color(0,255,0, alpha), 10f);
							0, new java.awt.Color(0,255,100, alpha), 10f);
				DataSetBarDescr sellKey = 
						new DataSetBarDescr(
							serieIdx-1, 
							eventInfo.info()+" sell", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
							//0, new java.awt.Color(246,173,173, alpha), 10f);
							//0, new java.awt.Color(255,0,0, alpha), 10f);
							0, new java.awt.Color(240,72,20, alpha), 10f);
				DataSetBarDescr indeterKey = 
						new DataSetBarDescr(
							serieIdx-2, 
							eventInfo.info()+" indeterministic", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
							0, new java.awt.Color(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue(), alpha), 10f);
				
				
				if (!buyS.isEmpty()) {
					buyKey.setLabeled(true);
				} else if (!sellS.isEmpty()) {
					sellKey.setLabeled(true);
				} else if (!indeterS.isEmpty()) {
					indeterKey.setLabeled(true);
				}

				barData.put(buyKey, buyS.tailMap(start));
				barData.put(sellKey, sellS.tailMap(start));
				barData.put(indeterKey, indeterS.tailMap(start));

				//if (!barSettings.getSideBySide()) {
				if (!barSettings.getIsReachTop()) {
					yValueFactor = yValueFactor - 1d/chartedTrendsEvtDefsSize;
				}
				
				serieIdx = serieIdx - 3;
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	
		return barData;
	}
	
	//nbMaxFill = 0 means no filling limit
	private static void cheesyFillBarChart(double yValue, SortedMap<Date, Double> sellS, SortedMap<Date, Double> buyS, SortedMap<Date, Double> undeterS, EventValue prevEventValue, Date currEvtDate, int nbMaxFill) {
		
		Calendar prevDateCal = Calendar.getInstance();
		prevDateCal.setTime(prevEventValue.getDate());
	
		double value = yValue;
		int nbFill = 0;
	
		if ( prevEventValue.getEventType().equals(EventType.BULLISH)) {
			while (prevDateCal.getTime().before(currEvtDate) && (nbMaxFill == 0 || nbFill < nbMaxFill)) {
				buyS.put(prevDateCal.getTime(), value);
				QuotationsFactories.getFactory().incrementDate(prevDateCal, +1);
				nbFill++;
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.BEARISH)) {
			while (prevDateCal.getTime().before(currEvtDate) && (nbMaxFill == 0 || nbFill < nbMaxFill)) {
				sellS.put(prevDateCal.getTime(), value);
				QuotationsFactories.getFactory().incrementDate(prevDateCal, +1);
				nbFill++;
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.NONE)) {
			undeterS.put(prevDateCal.getTime(), value);
//			while (prevDateCal.getTime().before(currEvtDate) && (nbMaxFill == 0 || nbFill < nbMaxFill)) {
//				undeterS.put(prevDateCal.getTime(), value);
//				QuotationsFactories.getFactory().incrementDate(prevDateCal, +1);
//				nbFill++;
//			}
		}
		
	}

}
