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
package com.finance.pms.events.scoring.chartUtils;

import java.util.Arrays;
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
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartBarUtils {

	private static MyLogger LOGGER = MyLogger.getLogger(ChartBarUtils.class);

	public static SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> buildBarsData(
			Stock selectedShare, Set<EventInfo> chartedEvtDefsTrends,
			Date start, Date end, SymbolEvents eventsForStock, Map<EventInfo, TuningResDTO> tuningRess,
			BarSettings barSettings, DataSetBarDescrBuilder dataSetBarDescrBuilder, double yValueFactor) {

		SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barData = new TreeMap<DataSetBarDescr, SortedMap<Date, BarChart>>();

		try {
			int chartedTrendsEvtDefsSize = chartedEvtDefsTrends.size();
			int serieIdx = chartedTrendsEvtDefsSize * 3;

			for (EventInfo eventInfo : chartedEvtDefsTrends) {

				SortedMap<Date, BarChart> sellS = new TreeMap<Date, BarChart>();
				SortedMap<Date, BarChart> buyS = new TreeMap<Date, BarChart>();
				SortedMap<Date, BarChart> indeterS = new TreeMap<Date, BarChart>();
				EventValue prevEventValue = null;
				Quotations quotations = null;
				Date[] quotationsKeySet = null;
				int k = 0;

				SortedMap<EventKey, EventValue> sortedDataResultMap = eventsForStock.getSortedDataResultMap();
				for (EventKey eventKey : sortedDataResultMap.keySet()) {

					if (eventKey.getEventInfo().equals(eventInfo)) {
						if (prevEventValue != null) {
							
							Date currEvtDate = eventKey.getDate();
							if (barSettings.isToQuotations()) yValueFactor = quotations.getClosestCloseForDate(currEvtDate).doubleValue();
							if (eventInfo.getIsContinous()) {
								k = cheesyFillBarChart(selectedShare, yValueFactor, sellS, buyS, indeterS, quotationsKeySet, k, prevEventValue, currEvtDate, 1);
							} else {
								k = cheesyFillBarChart(selectedShare, yValueFactor, sellS, buyS, indeterS, quotationsKeySet, k, prevEventValue, currEvtDate, barSettings.getMaxFill());
							}
							prevEventValue = sortedDataResultMap.get(eventKey);

						} else {//Initialise the quotations on the first iteration
							
							prevEventValue = sortedDataResultMap.get(eventKey);
							quotations = QuotationsFactories.getFactory()
									.getSplitFreeQuotationsInstance(
											selectedShare, prevEventValue.getDate(), DateFactory.getNowEndDate(), true, 
											selectedShare.getMarketValuation().getCurrency(), 0, ValidityFilter.getFilterFor(Arrays.asList(QuotationDataType.CLOSE)));
							quotationsKeySet = quotations.getDates();
							
						}
						
					}

				}

				//Filling up to the end only for non continuous events
				if (prevEventValue != null && !eventInfo.getIsContinous()) {
					k = cheesyFillBarChart(selectedShare, yValueFactor, sellS, buyS, indeterS, quotationsKeySet, k, prevEventValue, end,  barSettings.getMaxFill());
				}

				int gradiant = (barSettings.getIsGradient()) ? serieIdx/3 : 1;
				int alpha = 255 / Math.max(1,(int) Math.ceil(barSettings.getAlphaDividend()*gradiant));
				TuningResDTO tuningResDTO = tuningRess.get(eventInfo);

				DataSetBarDescr buyKey = dataSetBarDescrBuilder.buildBuyDSBarDescr(serieIdx, alpha, eventInfo, selectedShare, tuningResDTO);
				DataSetBarDescr sellKey = dataSetBarDescrBuilder.buildSellDSBarDescr(serieIdx, alpha, eventInfo, selectedShare, tuningResDTO);
				DataSetBarDescr indeterKey = dataSetBarDescrBuilder.buildIndeterDSBarDescr(serieIdx, alpha, eventInfo, selectedShare, tuningResDTO);

				if (!buyS.isEmpty()) {
					buyKey.setLabeled(true);
				} else if (!sellS.isEmpty()) {
					sellKey.setLabeled(true);
				} else if (!indeterS.isEmpty() && indeterKey != null) {
					indeterKey.setLabeled(true);
				}

				barData.put(buyKey, buyS.tailMap(start));
				barData.put(sellKey, sellS.tailMap(start));
				if (indeterKey != null) barData.put(indeterKey, indeterS.tailMap(start));

				if (!barSettings.getIsReachTop() && !barSettings.isToQuotations()) {
					yValueFactor = yValueFactor - (1d/chartedTrendsEvtDefsSize);
				}

				serieIdx = serieIdx - 3;
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return barData;
	}

	//nbMaxFill = 0 means no filling limit
	private static int cheesyFillBarChart(
			Stock stock, double yValue, 
			SortedMap<Date, BarChart> sellS, SortedMap<Date, BarChart> buyS, SortedMap<Date, BarChart> undeterS, 
			Date[] quotationsKeySet, int k,
			EventValue prevEventValue, Date currEvtDate, int nbMaxFill) throws NotEnoughDataException {

		Calendar prevDateCal = Calendar.getInstance();
		prevDateCal.setTime(prevEventValue.getDate());

		double value = yValue;
		int nbFill = 0;

		if (prevEventValue.getEventType().equals(EventType.BULLISH)) {
			while (prevDateCal.getTime().before(currEvtDate) && (nbMaxFill == 0 || nbFill < nbMaxFill) && k < quotationsKeySet.length-1) {
				buyS.put(prevDateCal.getTime(), new BarChart(value, prevEventValue.getMessage()));
				if (quotationsKeySet[k+1].before(currEvtDate)) {
					prevDateCal.setTime(quotationsKeySet[++k]);
					nbFill++;
				} else {
					break;
				}
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.BEARISH)) {
			while (prevDateCal.getTime().before(currEvtDate) && (nbMaxFill == 0 || nbFill < nbMaxFill) && k < quotationsKeySet.length-1) {
				sellS.put(prevDateCal.getTime(), new BarChart(value, prevEventValue.getMessage()));
				if (quotationsKeySet[k+1].before(currEvtDate)) {
					prevDateCal.setTime(quotationsKeySet[++k]);
					nbFill++;
				} else {
					break;
				}
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.NONE)) {
			undeterS.put(prevDateCal.getTime(), new BarChart(value, prevEventValue.getMessage()));
		}

		return k;
	}

}
