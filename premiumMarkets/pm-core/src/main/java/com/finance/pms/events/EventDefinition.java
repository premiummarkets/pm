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
package com.finance.pms.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


// TODO: Auto-generated Javadoc
/**
 * The Enum EventDefinition.
 * 
 * @author Guillaume Thoreton
 */
public enum EventDefinition implements Serializable {
	
	ZERO(0,"Zero"),

	MACDBUY(2, "MAS MACD Crossover (Buy)"),
	MACDSELL (3,"MAS MACD Crossover (Sell)"),
	STOCHASTICBUY(4,"MAS Stochastic %D Crossover (Buy)"),
	STOCHASTICSELL (5,"MAS Stochastic %D Crossover (Sell)"),
	SLOPEMACDBUY (6,"MAS Slope of MACD Signal Line Cross Above 0 (Buy)"),
	SLOPEMACDSELL (7,"MAS Slope of MACD Signal Line Cross Above 0 (Sell)"),
	SLOPESLOPEMACDBUY (8,"MAS Slope of Slope of MACD Signal Line Cross Above 0 (Buy)"),
	SLOPESLOPEMACDSELL (9,"MAS Slope of Slope of MACD Signal Line Cross Below 0 (Sell)"),
	VOLUMEINCREASE (10,"MAS Volume > Yesterday's Volume EMA (5) * 3.5"),
	MACDCROSSOVERSTOCHBUY (11,"MAS MACD Crossover and Stochastic %D Crossover (Buy)"),
	MACDCROSSOVERSTOCHSELL (12,"MAS MACD Crossover and Stochastic %D Crossover (Sell)"),
	SLOPEMACDDOWNTREND (13,"MAS Slope of MACD Signal Line Downtrend"),
	SLOPEMACDUPTREND (14,"MAS Slope of MACD Signal Line Uptrend"),
	SLOPEMACDSIDEWAYSPOS (15,"MAS Slope of MACD Signal Line Trend Sideways: 1 to 0"),
	SLOPEMACDSIDEWAYSNEG (16,"MAS Slope of MACD Signal Line Trend Sideways: 1 to 0"),
	CCIABOVE (17,"MAS CCI Crossed above x"),
	CCIBELOW (18,"MAS CCI Crossed below -x"),
	UNKNOWN99 (99,"Miscellaneous"),
	
	PMSMAREVERSAL (101,"PM SMA Reversal"),//
	PMMACDZEROCROSS (102,"PM MACD Cross Zero"), //
	PMMACDSIGNALCROSS (103,"PM MACD Cross Signal"),//
	PMAROONTREND (104,"PM Aroon Divergence"), //
	PMZLAGMACDZCROSS(155,"PM Zero Lag MACD Cross Signal"),
	
	PMRSITHRESHOLD (110,"PM RSI Threshold Cross"),//
	PMMFITHRESHOLD (111,"PM MFI Threshold Cross"),//
	PMSSTOCHTHRESHOLD (112,"PM Stochastic Threshold Cross"),//
	PMCHAIKINOSCTHRESHOLD (113,"PM Chaikin Oscillator Threshold"),//

	PMRSIDIVERGENCE (120,"PM RSI Divergence"), //
	PMMFIDIVERGENCE (121,"PM MFI Divergence"), //
	PMSSTOCHDIVERGENCE (122,"PM Stochastic Divergence"), // 
	PMCHAIKINOSCDIVERGENCE (123,"PM Chaikin Oscillator Divergence"), //
	
	PMOBVDIVERGENCE (151,"PM OBV Divergence"),
	PMACCDISTDIVERGENCE (152,"PM Acc Dist Divergence"),
	STDDEV (154,"PM Standard Deviation"),
	
	//Attention!! Event of type threshold crossing or Forced Sell must be cleaned (or invalidated) after each signal check?? ToTest.
	ALERTTHRESHOLD (201,"Alert on Threshold cross"),//Not discardable??
	
	SCREENER (302,"Screener Alert"), //Not discardable
	WEATHER (401,"Temperature"),  //Not discardable
	
	CRASHGUARD (502,"Crash"),
	NEURAL (503,"Neural"),
	VARIATION (504,"Variation"),
	VARIANCE (505,"Variance"),
	HOUSETREND(506,"HouseTrend"),
	SECTOR(507,"SectorRanksTrend"),
	
	INFINITE (999,"Infinite");

	private static final int FIRSTPMTECHEVENT = 100;
	private static final int LASTPMTECHEVENT = 150;
	private static final Map<Integer,String> EVENTDEFLIST;
	static {
		EventDefinition edVals[] = EventDefinition.values();
		EVENTDEFLIST = new HashMap<Integer,String>();
		for (Integer i =0; i < edVals.length; i++) {
			EVENTDEFLIST.put(i, edVals[i].getEventDef());
		}
	}

	//private static MyLogger LOGGER = MyLogger.getLogger(EventDefinition.class);
	
	/** The event def. */
	private final String eventDef;
	
	/** The event def id. */
	private final Integer eventDefId;
	
	/**
	 * Instantiates a new event definition.
	 * 
	 * @param order the order
	 * @param value the value
	 * 
	 * @author Guillaume Thoreton
	 */
	private EventDefinition(Integer order,String value) {
		this.eventDef = value;
		this.eventDefId = order;
	}
	
	/**
	 * Value of.
	 * 
	 * @param ordinal the ordinal
	 * 
	 * @return the event definition
	 * 
	 * @author Guillaume Thoreton
	 */
	public static EventDefinition valueOf(Integer evDefId){
		EventDefinition retour = EventDefinition.UNKNOWN99;
		for (EventDefinition e: EventDefinition.values()) {
			if (e.getEventDefId().equals(evDefId)) return e;
		}
		return retour;
	}
	
	public static EventDefinition valueOfEventDef(String eventDef){
		EventDefinition retour = EventDefinition.UNKNOWN99;
		for (EventDefinition e: EventDefinition.values()) {
			if (e.getEventDef().equals(eventDef)) return e;
		}
		return retour;
	}

	/**
	 * Gets the event def.
	 * 
	 * @return the event def
	 */
	public String getEventDef() {
		return eventDef;
	}
	
	/**
	 * Gets the event def id.
	 * 
	 * @return the event def id
	 */
	public Integer getEventDefId() {
		return this.eventDefId;
	}
	
	/**
	 * Gets the event def list.
	 * 
	 * @return the event def list
	 */
	public static Map<Integer,String> getEventDefList() {
//		EventDefinition edVals[] = EventDefinition.values();
//		Map<Integer,String> r = new HashMap<Integer,String>();
//		for (Integer i =0; i < edVals.length; i++) {
//			r.put(i, edVals[i].getEventDef());
//		}
//		return r;
		return EVENTDEFLIST;
	}

	public static EventDefinition[] tAIndicators() {
		return subEventArray(0,200);
	}
	
	public static EventDefinition[] allDiscardableEvents() {
		EventDefinition[] taEvts = tAIndicators();
		EventDefinition[] otherEvts = subEventArray(500, EventDefinition.INFINITE.eventDefId);
		EventDefinition[] ret = new EventDefinition[taEvts.length+otherEvts.length];
		for (int i = 0; i < ret.length; i++) {		
			if (i < taEvts.length) {
				ret[i] = taEvts[i];
			} else {
				ret[i]=otherEvts[i-taEvts.length];
			}
		}
		return ret;
	}

	/**
	 * @param ordinal 
	 * @return
	 */
	private static EventDefinition[] subEventArray(int ordinalLow, int ordinalHigh) {
		ArrayList<EventDefinition> retVal = subEventList(ordinalLow, ordinalHigh);
		EventDefinition[] eventDefinitions = new EventDefinition[retVal.size()] ;
		return retVal.toArray(eventDefinitions);
	}

	/**
	 * @param ordinalLow
	 * @param ordinalHigh
	 * @return
	 */
	private static ArrayList<EventDefinition> subEventList(int ordinalLow, int ordinalHigh) {
		ArrayList<EventDefinition> retVal = new ArrayList<EventDefinition>();
		for (EventDefinition eventDefinition : EventDefinition.values()) {
			if (eventDefinition.getEventDefId() > ordinalLow && eventDefinition.getEventDefId() < ordinalHigh)
				retVal.add(eventDefinition);
		}
		return retVal;
	}

	public static EventDefinition[] alertsOnThresholds() {
		return subEventArray(200,300);
	}
	
	private static String getEventDefinitionsString(int first, int last) {
		EventDefinition[] eventDefinitions = subEventArray(first, last);
		String retVal = "";
		String sep = "";
		for(int i=0; i < eventDefinitions.length; i++) {
			retVal = retVal + sep + eventDefinitions[i].name();
			sep = ",";
		}
		return retVal;
	}
	
	public static String getPMEventDefinitionsString() {
		return getEventDefinitionsString(FIRSTPMTECHEVENT, LASTPMTECHEVENT);
	}
	
	public static String getIndepEventDefinitionsString() {
		return getEventDefinitionsString(400, 999);
	}

	public static List<EventDefinition> getPMEventDefinitionsList() {
		return subEventList(FIRSTPMTECHEVENT, LASTPMTECHEVENT);
	}
	
	public static List<EventDefinition> getEventDefinitionsListFor(EventDefinition eventDef) {
		return subEventList(eventDef.eventDefId-1, eventDef.eventDefId+1);
	}
	
	public static String getEvtDefsCfgStr(List<EventDefinition> eventDefinitions) {
		
		SortedSet<EventDefinition> sortedEvtDefs = new  TreeSet<EventDefinition>(new Comparator<EventDefinition>() {
			
			public int compare(EventDefinition o1, EventDefinition o2) {
				return o1.getEventDefId().compareTo(o2.getEventDefId());
			}
		});
		sortedEvtDefs.addAll(eventDefinitions);
		
		String ret = "";
		for (EventDefinition eventDefinition : sortedEvtDefs) {
			ret = ret + "-" + eventDefinition.getEventDefId();
		}
		
		return ret;
	}
	
}
