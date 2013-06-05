package com.finance.pms.events.scoring.chartUtils;

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
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartBarUtils {
	
	
	public static SortedMap<DataSetBarDescr, SortedMap<Date, Double>> buildBarsData(
			Double alphaDividende, Stock selectedShare, 
			Set<EventInfo> chartedEvtDefsTrends,
			Date start, Date end, SymbolEvents eventsForStock, Map<EventInfo, TuningResDTO> tuningRess) {
		
		int chartedTrendsEvtDefsSize = chartedEvtDefsTrends.size();
		
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barData = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
	
		double factor = 1d; 
		int serieIdx = chartedTrendsEvtDefsSize*2;
	
		for (EventInfo eventInfo : chartedEvtDefsTrends) {
	
			SortedMap<Date, Double> sellS = new TreeMap<Date,Double>();
			SortedMap<Date, Double> buyS = new TreeMap<Date,Double>();
			EventValue prevEventValue = null;
	
			SortedMap<EventKey, EventValue> sortedDataResultMap = eventsForStock.getSortedDataResultMap();
			for (EventKey eventKey : sortedDataResultMap.keySet()) {
	
				if (eventKey.getEventInfo().equals(eventInfo)) {
					if (prevEventValue != null) {
						Date currEvtDate = eventKey.getDate();
						fillBarChart(factor, sellS, buyS, prevEventValue, currEvtDate);
					}
					prevEventValue = sortedDataResultMap.get(eventKey);
				}
	
			} 
	
			//Filling up to the end only for non continuous events
			if (prevEventValue != null && !eventInfo.getIsContinous()) {
				fillBarChart(factor, sellS, buyS, prevEventValue, end);
			}
	
			int alpha = 255 / (int) Math.ceil((chartedTrendsEvtDefsSize/alphaDividende));
			TuningResDTO tuningResDTO = tuningRess.get(eventInfo);
			DataSetBarDescr buyKey = new DataSetBarDescr(serieIdx, eventInfo.info()+" buy", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), new java.awt.Color(189,249,189, alpha), 10f, selectedShare.getFriendlyName(), tuningResDTO);
			DataSetBarDescr sellKey = new DataSetBarDescr(serieIdx-1, eventInfo.info()+" sell", eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), new java.awt.Color(246,173,173, alpha), 10f, selectedShare.getFriendlyName(), tuningResDTO);
			
			if (!buyS.isEmpty()) {
				buyKey.setLabeled(true);
			} else if (!sellS.isEmpty()) {
				sellKey.setLabeled(true);
			}
	
			barData.put(buyKey, buyS.tailMap(start));
			barData.put(sellKey, sellS.tailMap(start));
	
			factor = factor - .9d/chartedTrendsEvtDefsSize;
			serieIdx = serieIdx - 2;
		}
	
		return barData;
	}

	private static void fillBarChart(double factor, SortedMap<Date, Double> sellS, SortedMap<Date, Double> buyS, EventValue prevEventValue, Date currEvtDate) {
	
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
	}

}
