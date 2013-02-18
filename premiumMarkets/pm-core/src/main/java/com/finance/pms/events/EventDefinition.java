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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.MainPMScmd;
import com.finance.pms.portfolio.InfoObject;


// TODO: Auto-generated Javadoc
/**
 * The Enum EventDefinition.
 * 
 * @author Guillaume Thoreton
 */
public enum EventDefinition implements Serializable, InfoObject {
	
	ZERO(0,"Zero", false, null),

	MACDBUY(2, "MAS MACD Crossover (Buy)", false, null),
	MACDSELL (3,"MAS MACD Crossover (Sell)", false, null),
	STOCHASTICBUY(4,"MAS Stochastic %D Crossover (Buy)", false, null),
	STOCHASTICSELL (5,"MAS Stochastic %D Crossover (Sell)", false, null),
	SLOPEMACDBUY (6,"MAS Slope of MACD Signal Line Cross Above 0 (Buy)", false, null),
	SLOPEMACDSELL (7,"MAS Slope of MACD Signal Line Cross Above 0 (Sell)", false, null),
	SLOPESLOPEMACDBUY (8,"MAS Slope of Slope of MACD Signal Line Cross Above 0 (Buy)", false, null),
	SLOPESLOPEMACDSELL (9,"MAS Slope of Slope of MACD Signal Line Cross Below 0 (Sell)", false, null),
	VOLUMEINCREASE (10,"MAS Volume > Yesterday's Volume EMA (5) * 3.5", false, null),
	MACDCROSSOVERSTOCHBUY (11,"MAS MACD Crossover and Stochastic %D Crossover (Buy)", false, null),
	MACDCROSSOVERSTOCHSELL (12,"MAS MACD Crossover and Stochastic %D Crossover (Sell)", false, null),
	SLOPEMACDDOWNTREND (13,"MAS Slope of MACD Signal Line Downtrend", false, null),
	SLOPEMACDUPTREND (14,"MAS Slope of MACD Signal Line Uptrend", false, null),
	SLOPEMACDSIDEWAYSPOS (15,"MAS Slope of MACD Signal Line Trend Sideways: 1 to 0", false, null),
	SLOPEMACDSIDEWAYSNEG (16,"MAS Slope of MACD Signal Line Trend Sideways: 1 to 0", false, null),
	CCIABOVE (17,"MAS CCI Crossed above x", false, null),
	CCIBELOW (18,"MAS CCI Crossed below -x", false, null),
	UNKNOWN99 (99,"Miscellaneous", false, null),
	
	PMSMAREVERSAL (101,"SMA Reversal", false, new  EventDefDescriptor("Close",null,null,"Sma",null,null, "Sma is down and Close < Sma", "Sma is up and Close > Sma")),//
	PMMACDZEROCROSS (102,"MACD Cross Zero", false, new  EventDefDescriptor("Macd",null,null,"Signal line","Zero line",null,"Macd < Signal and Macd crosses below 0","Macd > Signal and Macd crosses above 0")), //
	PMMACDSIGNALCROSS (103,"MACD Signal Cross", false, new  EventDefDescriptor("Macd",null,null,"Signal line",null,null,"Macd > 0  and Macd crosses below Signal","Macd < 0  and Macd crosses above Signal")),//
	PMAROONTREND (104,"Aroon Divergence", false, new  EventDefDescriptor(null,"Down","Up","Middle line","Lower threshold","Upper threshold","Aroon down crosses above Aroon up and Aroon down > Upper Threshold and Aroon up < Lower Threshold","Aroon up crosses above Aroon down and Aroon up > Upper Threshold and Aroon down < Lower Threshold")), //
	PMZLAGMACDZCROSS(155,"Zero Lag MACD Signal Cross", false,  null),
	
	PMRSITHRESHOLD (110,"RSI Threshold Cross", false,  new  EventDefDescriptor("Rsi",null,null,null,"Lower threshold","Upper threshold","Rsi crosses above Upper threshold","Rsi crosses below Lower threshold")),//
	PMMFITHRESHOLD (111,"MFI Threshold Cross", false, new  EventDefDescriptor("Mfi",null,null,null,"Lower threshold","Upper threshold","Mfi crosses above Upper threshold","Mfi crosses below Lower threshold")),//
	PMSSTOCHTHRESHOLD (112,"Stochastic Threshold Cross", false, new  EventDefDescriptor("Slow K",null,null,"Slow D","Lower threshold","Upper threshold","Slow D crosses above Upper threshold","Slow D crosses below Lower threshold")),//
	PMCHAIKINOSCTHRESHOLD (113,"Chaikin Oscillator Threshold", false, new  EventDefDescriptor("Oscillator",null,null,"Zero line",null,null,"Chaikin crosses below 0","Chaikin crosses above 0")),//

	PMRSIDIVERGENCE (120,"RSI Divergence", false, new  EventDefDescriptor("Rsi",null,null,null,"Lower threshold","Upper threshold","Price higher high and Rsi lower high","Price lower low and Rsi higher low")), //
	PMMFIDIVERGENCE (121,"MFI Divergence", false, new  EventDefDescriptor("Mfi",null,null,null,"Lower threshold","Upper threshold","Price higher high and Mfi lower high","Price lower low and Mfi higher low")), //
	PMSSTOCHDIVERGENCE (122,"Stochastic Divergence", false, new  EventDefDescriptor("Slow K",null,null,"Slow D","Lower threshold","Upper threshold","Price higher high and Stochastic lower high","Price lower low and Stochastic higher low")), // 
	PMCHAIKINOSCDIVERGENCE (123,"Chaikin Oscillator Divergence", false, new  EventDefDescriptor("Oscillator",null,null,null,null,null,"Price higher high and Chaikin lower high","Price lower low and Chaikin higher low")), //
	
	PMOBVDIVERGENCE (151,"OBV Divergence", false, null),
	PMACCDISTDIVERGENCE (152,"Acc Dist Divergence", false, null),
	STDDEV (154,"Standard Deviation", false, null),
	
	//Attention!! Event of type threshold crossing or Forced Sell must be cleaned (or invalidated) after each signal check?? ToTest.
	ALERTTHRESHOLD (201,"Alert on Threshold cross", false, null),//Not discardable??
	
	SCREENER (302,"Screener Alert", false, null), //Not discardable
	WEATHER (401,"Temperature", false, new EventDefDescriptor(null,null,null,null,null,null,"Bearish Temperature reversal","Bullish Temperature reversal")),  //Not discardable
	
	CRASHGUARD (502,"Crash", false, null),
	NEURAL (503,"Neural", true, new  EventDefDescriptor("Neural output", null, null, null, null, null,"Neural signal is down","Neural signal is up")),
	VARIATION (504,"Variation", false, null),
	VARIANCE (505,"Variance", false, null),
	HOUSETREND(506,"HouseTrend", false, null),
	SECTOR(507,"Sector Ranks Trend", true, new  EventDefDescriptor("Sector output", null, null, null, null, null,"Neural signal is down","Neural signal is up")),
	
	INFINITE (999,"Add All", false, null);

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


	private final String eventDef;
	private final Integer eventDefId;
	private Boolean isContinous;
	private EventDefDescriptor eventDefDescriptor;
	
	/**
	 * Instantiates a new event definition.
	 * 
	 * @param order the order
	 * @param value the value
	 * 
	 * @author Guillaume Thoreton
	 */
	private EventDefinition(Integer order,String value, Boolean isContinous, EventDefDescriptor eventDefDescriptor) {
		this.eventDef = value;
		this.eventDefId = order;
		this.isContinous = isContinous;
		this.eventDefDescriptor = eventDefDescriptor;
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
		return EVENTDEFLIST;
	}

	public static EventDefinition[] tAIndicators() {
		return subEventArray(0,200);
	}
	
	public static Set<EventDefinition> allIndicators() {
		return new HashSet<EventDefinition>(Arrays.asList(subEventArray(EventDefinition.ZERO.ordinal(),EventDefinition.INFINITE.ordinal())));
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
	
	public static Set<EventDefinition> getPMEventDefinitionsSet() {
		return new HashSet<EventDefinition>(subEventList(FIRSTPMTECHEVENT, LASTPMTECHEVENT));
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
	
	public static String getEventDefArrayAsString(EventDefinition[] definitions) {
		String indicatorsStr = "";
		for (EventDefinition eventDefinition : definitions) {
			indicatorsStr = indicatorsStr + " " +eventDefinition.name();
		}
		return indicatorsStr;
	}

	public static String getEventDefCollectionAsString(Set<EventDefinition> definitions) {
		return EventDefinition.getEventDefArrayAsString(definitions.toArray(new EventDefinition[]{}));
	}

	@Override
	public String info() {
		return getEventDef();
	}
	
	public static Set<EventDefinition> loadPassPrefsEventDefinitions() {
		
		List<String> indicators = Arrays.asList(MainPMScmd.getPrefs().get("event.indicators", EventDefinition.getPMEventDefinitionsString()).split(","));
		
		SortedSet<EventDefinition> eventDefinitions = new  TreeSet<EventDefinition>(new Comparator<EventDefinition>() {
			public int compare(EventDefinition o1, EventDefinition o2) {
				return o1.getEventDefId().compareTo(o2.getEventDefId());
			}
		});
		
		for (String eventDefinitionsName : indicators) {
			eventDefinitions.add(EventDefinition.valueOf(eventDefinitionsName));
		}

		Integer maxPass = Integer.valueOf(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
		if (maxPass > 1) {
			List<String> indepIndicators = Arrays.asList(MainPMScmd.getPrefs().get("event.indepIndicators", EventDefinition.getIndepEventDefinitionsString()).split(","));
			for (String eventDefinitionsName : indepIndicators) {
				eventDefinitions.add(EventDefinition.valueOf(eventDefinitionsName));
			}
		}
		
		
		return eventDefinitions;
	}

	public Boolean getIsContinous() {
		return isContinous;
	}

	public EventDefDescriptor getEventDefDescriptor() {
		return eventDefDescriptor;
	}
	
}
