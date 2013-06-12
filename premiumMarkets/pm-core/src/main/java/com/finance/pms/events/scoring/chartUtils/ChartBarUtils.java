package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartBarUtils {
	
	
	public static SortedMap<DataSetBarDescr, SortedMap<Date, Double>> buildBarsData(
			Double alphaDividende, Stock selectedShare, 
			Set<EventInfo> chartedEvtDefsTrends,
			Date start, Date end, SymbolEvents eventsForStock, Map<EventInfo, TuningResDTO> tuningRess, 
			int maxFill, Boolean isZerobAsed) {
		
		int chartedTrendsEvtDefsSize = chartedEvtDefsTrends.size();
		
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barData = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
	
		double yValueFactor; 
		double yBase;
		
		if (isZerobAsed) {
			yValueFactor = 1d;
			yBase = 0d;
		} else {
			//yValueFactor = .9d/chartedTrendsEvtDefsSize;
			yValueFactor = 1d;
			yBase = 1d - 1d/chartedTrendsEvtDefsSize;
		}
		
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
							cheesyFillBarChart(yValueFactor, sellS, buyS, indeterS, prevEventValue, currEvtDate, maxFill);
						}
		
					}
					prevEventValue = sortedDataResultMap.get(eventKey);
				}
	
			} 
	
			//Filling up to the end only for non continuous events
			if (prevEventValue != null && !eventInfo.getIsContinous()) {
				cheesyFillBarChart(yValueFactor, sellS, buyS, indeterS, prevEventValue, end, maxFill);
			}
	
			//int alpha = 255 / (int) Math.ceil((chartedTrendsEvtDefsSize/alphaDividende));
			int alpha = 255 / (int) Math.ceil( alphaDividende*((serieIdx)/3) );
			TuningResDTO tuningResDTO = tuningRess.get(eventInfo);
			
			DataSetBarDescr buyKey = 
					new DataSetBarDescr(
						serieIdx, 
						eventInfo.info()+" buy", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
						yBase, new java.awt.Color(189,249,189, alpha), 10f);
			DataSetBarDescr sellKey = 
					new DataSetBarDescr(
						serieIdx-1, 
						eventInfo.info()+" sell", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
						yBase, new java.awt.Color(246,173,173, alpha), 10f);
			DataSetBarDescr indeterKey = 
					new DataSetBarDescr(
						serieIdx-2, 
						eventInfo.info()+" indeterministic", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO, selectedShare.getFriendlyName(),
						yBase, new java.awt.Color(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue(), alpha), 10f);
			
			
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
	
			if (isZerobAsed) {
				yValueFactor = yValueFactor - 1d/chartedTrendsEvtDefsSize;
			} else {
				yValueFactor = yValueFactor - .9d/chartedTrendsEvtDefsSize;
				yBase = yBase - 1d/chartedTrendsEvtDefsSize;
			}
	
			serieIdx = serieIdx - 3;
		}
	
		return barData;
	}

	private static void fillBarChart(double factor, SortedMap<Date, Double> sellS, SortedMap<Date, Double> buyS, SortedMap<Date, Double> undeterS, EventValue prevEventValue, Date currEvtDate) {
	
		Calendar prevDateCal = Calendar.getInstance();
		prevDateCal.setTime(prevEventValue.getDate());
	
		double value = factor;
	
		if ( prevEventValue.getEventType().equals(EventType.BULLISH)) {
			while (prevDateCal.getTime().before(currEvtDate)) {
				buyS.put(prevDateCal.getTime(), value);
				prevDateCal.add(Calendar.DAY_OF_YEAR, +1);
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.BEARISH)) {
			while (prevDateCal.getTime().before(currEvtDate)) {
				sellS.put(prevDateCal.getTime(), value);
				prevDateCal.add(Calendar.DAY_OF_YEAR, +1);
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.NONE)) {
			while (prevDateCal.getTime().before(currEvtDate)) {
				undeterS.put(prevDateCal.getTime(), value);
				prevDateCal.add(Calendar.DAY_OF_YEAR, +1);
			}
		}
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
