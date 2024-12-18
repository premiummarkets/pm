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
package com.finance.pms.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.EventDefDescriptorStatic;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Enum EventDefinition.
 * 
 * @author Guillaume Thoreton
 */
public enum EventDefinition implements Serializable, EventInfo {

	//Start
	AAAZERO(0,"Zero", false, null),

	//Mas
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

	//PM (mostly Talib) 
	PMSMAREVERSAL (101,"SMA Reversal", false,  
			new EventDefDescriptorStatic("Close", null, null, "Sma", null, null, 
					"Sma%d is down over a 15 days span and Close < Sma", new String[]{Config.INDICATOR_PARAMS_NAME,"SmaReversalSmaPeriod"}, 
					"Sma%d is up over a 15 days span and Close > Sma", new String[]{Config.INDICATOR_PARAMS_NAME,"SmaReversalSmaPeriod"})),//
	PMMACDZEROCROSS (102,"MACD Cross Zero", false, 
			new  EventDefDescriptorStatic("Macd", null, null, "Signal line", "Zero line", null,
					"Macd(%d,%d,%d) < Signal and Macd crosses below 0", new String[]{Config.INDICATOR_PARAMS_NAME,"MacdFastPeriod","MacdSignal","MacdSlowPeriod"}, 
					"Macd(%d,%d,%d) > Signal and Macd crosses above 0", new String[]{Config.INDICATOR_PARAMS_NAME,"MacdFastPeriod","MacdSignal","MacdSlowPeriod"})), //
	PMMACDSIGNALCROSS (103,"MACD Signal Cross", false, 
			new  EventDefDescriptorStatic("Macd", null, null, "Signal line", null, null,
					"Macd(%d,%d,%d) > 0  and Macd crosses below Signal",new String[]{Config.INDICATOR_PARAMS_NAME,"MacdFastPeriod","MacdSignal","MacdSlowPeriod"}, 
					"Macd(%d,%d,%d) < 0  and Macd crosses above Signal", new String[]{Config.INDICATOR_PARAMS_NAME,"MacdFastPeriod","MacdSignal","MacdSlowPeriod"})),//
	PMAROONTREND (104,"Aroon Oscillator", false,
			new EventDefDescriptorStatic("Oscillator", "Down", "Up", "Middle line", "Lower threshold", "Upper threshold",
					//"Aroon down crosses above Aroon up and Aroon down > 90 and Aroon up < 30", null,
					//"Aroon up crosses above Aroon down and Aroon up > 90 and Aroon down < 30", null), 
					"Aroon oscillator (Up-Down) is above 90", null,
					"Aroon oscillator (Up-Down) is below -90", null)), //

	PMRSITHRESHOLD (110,"RSI Threshold Cross", false,
			new  EventDefDescriptorStatic("Rsi", null, null, null, "Lower threshold","Upper threshold",
					"Rsi crosses above Upper threshold", null, "Rsi crosses below Lower threshold", null)),//
	PMMFITHRESHOLD (111,"MFI Threshold Cross", false, 
			new  EventDefDescriptorStatic("Mfi", null, null, null,"Lower threshold","Upper threshold", 
					"Mfi14 crosses above 80", null, "Mfi14 crosses below 20", null)),//
	PMSSTOCHTHRESHOLD (112,"Stochastic Threshold Cross", false, 
			new  EventDefDescriptorStatic("Slow K", null, null, "Slow D", "Lower threshold", "Upper threshold",
					"Stoch 14 Slow D (Sma 3) crosses above 80", null, "Stoch 14 Slow D (Sma 3) crosses below 20", null)),//
	PMCHAIKINOSCTHRESHOLD (113,"Chaikin Oscillator Threshold", false, 
			new  EventDefDescriptorStatic("Oscillator", null, null, "Zero line", null, null,
					"Chaikin crosses below 0", null, "Chaikin crosses above 0", null)),//

	PMRSIDIVERGENCE (120,"RSI Divergence", false, //FIXME wrong bull, bear descriptions
			new  EventDefDescriptorStatic("Rsi", null , null, null, "Lower threshold", "Upper threshold",
					"Price is up and Rsi%d makes a lower high over 60 days and above %d", new String[]{Config.INDICATOR_PARAMS_NAME, "RsiTimePeriod", "RsiUpperThreshold"},
					"Price is down and Rsi%d makes a higher low over 60 days and below %d", new String[]{Config.INDICATOR_PARAMS_NAME, "RsiTimePeriod", "RsiLowerThreshold"},
					"Lower bullish band", "Upper bullish band", "Lower Bearish band", "Upper Bearish band")), //
	PMMFIDIVERGENCE (121,"MFI Divergence", false, 
			new  EventDefDescriptorStatic("Mfi", null, null, null,"Lower threshold","Upper threshold",
					"Price is up and Mfi makes a lower high over 40 days and above 80", null, 
					"Price is down and Mfi makes a higher low over 40 days and below 20", null)), //
	PMSSTOCHDIVERGENCE (122,"Stochastic Divergence", false,
			new  EventDefDescriptorStatic("Slow K", null, null, "Slow D", "Lower threshold", "Upper threshold",
					"Price is up and Stochastic(14,3,3) makes a lower high over 60 days and above 80", null, 
					"Price is down and Stochastic(14,3,3) makes a higher low over 60 days and below 20", null)), // 
	PMCHAIKINOSCDIVERGENCE (123,"Chaikin Oscillator Divergence", false, 
			new  EventDefDescriptorStatic("Chaikin", null, null, null, null, null,
					"Price is up and Chaikin makes a lower high over 60 days", null,
					"Price is down and Chaikin makes a higher low over 60 days", null)), //
	PMOBVDIVERGENCE (124,"OBV Divergence", false,
			new  EventDefDescriptorStatic("Obv", null, null, null, null, null,
					"Price is up and Obv makes a lower high", null, 
					"Price is down and bv makes a higher low", null)),

	PMMIGHTYCHAIKIN (130,"Mighty Chaikin", false, 
			new EventDefDescriptorStatic(
					"Mighty Chaikin", "Close", null, null, null, null, "Bearish", null, "Bullish", null,
					"Close lower low", "Chaikin higher low")),

	//PM not used
	PMOBVDIVERGENCEOLD (151,"OBV Divergence", false, null),
	PMACCDISTDIVERGENCE (152,"Acc Dist Divergence", false, null),
	STDDEV (154,"Standard Deviation", false, null),
	PMZLAGMACDZCROSS(155,"Zero Lag MACD Signal Cross", false,  null),

	//Alert
	//Attention!! Event of type threshold crossing or Forced Sell must be cleaned (or invalidated) after each signal check?? ToTest.
	ALERTTHRESHOLD (201,"Alert on Threshold cross", false, null),//Not discardable??

	//Screener
	SCREENER (302,"Screener Alert", false, null), //Not discardable

	//Indeps
	WEATHER (401,"Temperature", false, new EventDefDescriptorStatic(null, null, null, null, null, null,"Bearish Temperature reversal", null, "Bullish Temperature reversal", null)),//Not discardable
	
	RANDOM (402, "Random", false, null),

	CRASHGUARD (502,"Crash", false, null),
	
	//Neural related
	NEURALNEUROPH (503,"Neural", true, 
			new  EventDefDescriptorStatic("Second Neural output", "First Neural output", "Row output", null, null, null,
					"Neural(%d,%d) signal is down", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"}, 
					"Neural(%d,%d) signal is up", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"})),
	VARIATION (504,"Variation", false, null),
	VARIANCE (505,"Variance", false, null),
	HOUSETREND (506,"Logarithmic ROC", false, 
			new  EventDefDescriptorStatic("Logarithmic ROC", null, null, null, null, null,
					"Logarithmic ROC(%d,%d) crosses below 0", new String[]{Config.EVENT_SIGNAL_NAME, "RocNNeuralHouseTrendPeriod", "RocNNeuralQuoteSmthPeriod"}, 
					"Logarithmic ROC(%d,%d) crosses above 0", new String[]{Config.EVENT_SIGNAL_NAME, "RocNNeuralHouseTrendPeriod", "RocNNeuralQuoteSmthPeriod"})),
	SECTOR(507,"Sector Ranks Trend", true, 
			new  EventDefDescriptorStatic("Sector trend analysed prediction", "Sector row prediction", "Stock close", "Stock sma 100 lag fixed", null, null, "Sector trend is down", null, "Sector trend is up", null)),
	ROCANDNEURAL (508,"Roc 'n' Neural", false, 
			new  EventDefDescriptorStatic("First Neural output","Short Logarithmic ROC" , "Long Logarithmic ROC", "Second Neural output", "Short Roc Zero line", "Long Roc Zero line",
					"First Neural(%d,%d) reverses down and Roc(%d,%d) crosses below 0\nOr Neural is down and Roc < 0", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod","RocNNeuralHouseTrendPeriod", "RocNNeuralQuoteSmthPeriod"},
					"First Neural(%d,%d) reverses up and Roc(%d,%d) crosses above 0\nOr Neural is up and Roc > 0", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod","RocNNeuralHouseTrendPeriod", "RocNNeuralQuoteSmthPeriod"})),
	NEURALRONE (509,"Neural Reverse One", true, 
			new  EventDefDescriptorStatic("First Neural Smth output","First Neural output", "Row output", null, null, null,
					"First Neural(%d,%d) signal is down", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"}, 
					"First Neural(%d,%d) signal is up", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"})),

	ENCOG(520, "Encog Neural", true, new  EventDefDescriptorStatic("predictions","inversed", null, null, null, null, "Bearish", null, "Bullish", null)),

	//TODO dynamic event descriptor
	NEURALMIX (550,"Neural Mix", false, 
			new  EventDefDescriptorStatic("First output", "Second output", null, null, null, null, 
					"See source code. TODO : dynamic description", null, 
					"See source code. TODO : dynamic description", null)),


	PMTHRESHOLDTEST (600,"Threshold Test", false, new EventDefDescriptorStatic("Threshold Test", null, null, null, null, null, "Bearish", null, "Bullish", null)),


	//Parameterised
	PARAMETERIZED (900, "Parameterised Events", false, 
			new EventDefDescriptorStatic(null, null, null, null, null, null,"PARAMETERIZED", null,"PARAMETERIZED", null)),

	//End
	zzzINFINITE (999,"All", false, null), 


	PMRSIDIVERGENCEOLD (620,"Old RSI Divergence", false, new EventDefDescriptorStatic(null, null, null, null, null, null, "Bearish", null, "Bullish", null)),
	PMMFIDIVERGENCEOLD (621,"Old MFI Divergence", false, new EventDefDescriptorStatic(null, null, null, null, null, null, "Bearish", null, "Bullish", null)),
	PMSSTOCHDIVERGENCEOLD (622,"Old Stochastic Divergence", false, new EventDefDescriptorStatic(null, null, null, null, null, null,"Bearish", null, "Bullish", null)),
	//Moved to 130 PMCHAIKINOSCDIVERGENCEOLD (623,"Old Chaikin Divergence", false, new EventDefDescriptorStatic("Chaikin Osc", null, null, null, null, null,"Bearish", null, "Bullish", null)),

	NEURALNEUROPHOLD (703,"Old Neuroph Neural", true, 
			new  EventDefDescriptorStatic("Old Neuroph Neural output", null, null, null, null, null,
					"Neural(%d,%d) signal is down", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"},
					"Neural(%d,%d) signal is up", new String[]{Config.EVENT_SIGNAL_NAME, "PerceptronTrainingPMEventOccLowerSpan", "ExpectedSmothingSMAPeriod"}));


	private static MyLogger LOGGER = MyLogger.getLogger(EventDefinition.class);

	private static final int FIRSTPMTECHEVENT = 100;
	private static final int LASTPMTECHEVENT = 150;
	private static final int FIRSTINDEPTECHEVENT = 400;
	private static final int LASTINDEPTECHEVENT = 998;


	//Props
	private final String eventReadableDef;
	private final Integer eventDefId;
	private Boolean isContinous;
	private EventDefDescriptorStatic eventDefDescriptor;

	private EventDefinition(Integer eventDefId, String eventDef, Boolean isContinous, EventDefDescriptorStatic eventDefDescriptor) {

		this.eventReadableDef = eventDef;
		this.eventDefId = eventDefId;
		this.isContinous = isContinous;
		this.eventDefDescriptor = eventDefDescriptor;

	}

	public static EventDefinition valueOf(Integer evDefId){
		EventDefinition retour = EventDefinition.UNKNOWN99;
		for (EventDefinition eventDefinition : EventDefinition.values()) {
			if (eventDefinition.getEventDefId().equals(evDefId)) return eventDefinition;
		}
		return retour;
	}

	public static EventInfo valueOfEventInfo(String eventInfoReference) throws NoSuchFieldException {
		for (EventInfo eventInfo : allEventInfos()) {
			if (eventInfo.getEventDefinitionRef().equals(eventInfoReference)) return eventInfo;
		}
		String message = "Can't find EventInfo for " + eventInfoReference + ". It may have been deleted and was not in this config (inc. EventDefinition, db.properties or user parameterized indicators).";
		LOGGER.warn(message);
		throw new NoSuchFieldException(message);
	}

	public static EventInfo valueOfEventReadableDef(String eventReadableDef){
		EventDefinition retour = EventDefinition.UNKNOWN99;
		for (EventInfo e: allEventInfos()) {
			if (e.getEventReadableDef().equals(eventReadableDef)) return e;
		}
		return retour;
	}

	public String getEventReadableDef() {
		return eventReadableDef;
	}

	public Integer getEventDefId() {
		return this.eventDefId;
	}

	private static EventDefinition[] subEventArray(int ordinalLow, int ordinalHigh) {
		ArrayList<EventInfo> retVal = subEventList(ordinalLow, ordinalHigh);
		EventDefinition[] eventDefinitions = new EventDefinition[retVal.size()] ;
		return retVal.toArray(eventDefinitions);
	}

	private static ArrayList<EventInfo> subEventList(int ordinalLow, int ordinalHigh) {
		ArrayList<EventInfo> retVal = new ArrayList<EventInfo>();
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
		return getEventDefinitionsString(FIRSTINDEPTECHEVENT, LASTINDEPTECHEVENT);
	}

	public static List<EventInfo> getEventDefinitionsListFor(EventDefinition eventDef) {
		return subEventList(eventDef.getEventDefId()-1, eventDef.getEventDefId()+1);
	}

	public static String getEvtDefsCfgStr(List<EventInfo> eventDefinitions) {

		SortedSet<EventInfo> sortedEvtDefs = new  TreeSet<EventInfo>(new Comparator<EventInfo>() {

			public int compare(EventInfo o1, EventInfo o2) {
				int compareTo = o1.compareTo(o2);
				return compareTo;
			}
		});
		sortedEvtDefs.addAll(eventDefinitions);

		String ret = "";
		for (EventInfo eventDefinition : sortedEvtDefs) {
			ret = ret + "-" + eventDefinition.getEventDefId();
		}

		return ret;
	}


	public static List<EventInfo> indicatorsStringToEventDefs(List<String> eventDefinitionsNames) {
		SortedSet<EventInfo> eventDefinitions = new  TreeSet<EventInfo>(new Comparator<EventInfo>() {

			public int compare(EventInfo o1, EventInfo o2) {
				int compareTo = o1.compareTo(o2);
				return compareTo;
			}
		});
		for (String eventDefinitionsName : eventDefinitionsNames) {
			eventDefinitions.add(EventDefinition.valueOf(eventDefinitionsName));
		}
		return new ArrayList<EventInfo>(eventDefinitions);
	}

	public static String getEventDefArrayAsString(String sepParam, EventInfo... definitions) {
		String indicatorsStr = "";
		String sep="";
		for (EventInfo eventDefinition : definitions) {
			indicatorsStr = indicatorsStr + sep +eventDefinition.getEventDefinitionRef();
			sep = sepParam;
		}
		return indicatorsStr;
	}

	public static String getEventDefSetAsString(String sepParam, Set<EventInfo> definitions) {
		String indicatorsStr = "";
		String sep="";
		for (EventInfo eventDefinition : definitions) {
			indicatorsStr = indicatorsStr + sep +eventDefinition.getEventDefinitionRef();
			sep = sepParam;
		}
		return indicatorsStr;
	}

	public static String getReadableEventDefSetAsString(String sepParam, Set<EventInfo> definitions) {
		String indicatorsStr = "";
		String sep="";
		for (EventInfo eventDefinition : definitions) {
			indicatorsStr = indicatorsStr + sep +eventDefinition.getEventReadableDef();
			sep = sepParam;
		}
		return indicatorsStr;
	}

	public static  SortedSet<EventInfo> loadFirstPassPrefEventDefinitions() {
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		return eventSignalConfig.getIndicatorsSorted();
	}

	public static SortedSet<EventInfo> loadMaxPassPrefsEventInfo() {
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		return eventSignalConfig.getAllTechIndicatorsSorted(false);
	}

	public static SortedSet<EventInfo> loadAllParameterized() {
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		return eventSignalConfig.getAllParameterized();
	}

	public static synchronized SortedSet<EventInfo> refreshMaxPassPrefsEventInfo() {
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		return eventSignalConfig.getAllTechIndicatorsSorted(true);
	}

	private static SortedSet<EventInfo> allEventInfos() {
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		return eventSignalConfig.getAllEventInfos();
	}

	public static List<EventInfo> nonTechEventDef() {
		return Arrays.asList(new EventInfo[]{EventDefinition.ALERTTHRESHOLD, EventDefinition.SCREENER, EventDefinition.UNKNOWN99, EventDefinition.AAAZERO, EventDefinition.zzzINFINITE});
	}


	@Override
	public String info() {
		return getEventReadableDef();
	}

	@Override
	public Boolean getIsContinous() {
		return isContinous;
	}

	@Override
	public EventDefDescriptorStatic getEventDefDescriptor() {
		return eventDefDescriptor;
	}

	@Override
	public String getEventDefinitionRef() {
		return this.name();
	}

	@Override
	public boolean equals(EventInfo obj) {
		return this.getEventDefinitionRef().equals(obj.getEventDefinitionRef());
	}

	@Override
	public int compareTo(EventInfo eventInfo) {
		return this.getEventDefinitionRef().compareTo(eventInfo.getEventDefinitionRef());
	}

	@Override
	public String toolTip() {
		if (eventDefDescriptor != null) {
			return getEventReadableDef()+" :\n\n" +
					"is bullish when "+eventDefDescriptor.getBullishDescription()+";\n\n" +
					"is bearish when "+eventDefDescriptor.getBearishDescription()+";";
		} else {
			return eventReadableDef;
		}
	}

	@Override
	public void interruptEventInfo() {
		// TODO Auto-generated method stub
		
	}

}
