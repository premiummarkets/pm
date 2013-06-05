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
package com.finance.pms.admin.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.pounderationrules.LatestEventsIndicatorOnlyPonderationRule;
import com.finance.pms.events.pounderationrules.LatestEventsPonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.SilentPonderationRule;

public class EventSignalConfig extends Config implements Cloneable {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -6626853648071949858L;
	protected static MyLogger LOGGER = MyLogger.getLogger(EventSignalConfig.class);
	
	//Retro tests
	public static Date ENDDATE; 
	static {
		 initEndDate();
	}
	
	private static void initEndDate() {
		String endDateStr = MainPMScmd.getPrefs().get("test.endDate",null);
		if (endDateStr != null && !endDateStr.isEmpty()) {
			try {
				ENDDATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);
			} catch (ParseException e) {
				LOGGER.error(e);
			}
		}
	}
	
	public static Date getNewDate() {
		if (EventSignalConfig.ENDDATE != null) {
			return EventSignalConfig.ENDDATE;
		} else {
			return new Date();
		}
	}
	
	private static ParameterizedIndicatorsBuilder PARAMETERIZEDINDICATORSBUILDER;
	
	
	private String analysis = "";
	
	private Double infVolatility = 0d;
	private Double supVolatility = 0d;
	private Integer volatilityPeriod = 20;

	private BigDecimal limitPriceAbove = new BigDecimal(MainPMScmd.getPrefs().get("event.stoploss", "0.04")).setScale(2);
	private BigDecimal limitPriceBelow = new BigDecimal(MainPMScmd.getPrefs().get("event.maxloss", "0.05")).setScale(2);
	private BigDecimal sellLimitToPrice = new BigDecimal(MainPMScmd.getPrefs().get("event.sellalert", "0.2")).setScale(2);
	private BigDecimal sellLimitGuardPrice = new BigDecimal(MainPMScmd.getPrefs().get("event.sellalertguard", "0.1")).setScale(2);
	private BigDecimal expectedRate = new BigDecimal(MainPMScmd.getPrefs().get("event.expectedrate", "0.05")).setScale(2);
	//private Boolean sendAnalysisEventMsg = new Boolean(MainPMScmd.getPrefs().get("event.sendAnalysisEventMsg", "false"));
	
	private List<String> indicators = Arrays.asList(MainPMScmd.getPrefs().get("event.indicators", EventDefinition.getPMEventDefinitionsString()).split(","));
	private SortedSet<EventInfo> indicatorSortedCache = null;
	private List<String> indepIndicators = Arrays.asList(MainPMScmd.getPrefs().get("event.indepIndicators", EventDefinition.getIndepEventDefinitionsString()).split(","));
	private SortedSet<EventInfo> allTechIndicatorsSortedCache= null;
	private SortedSet<EventInfo> allEventInfos = null;

	
	private Integer buyEventTriggerThreshold =  new Integer(MainPMScmd.getPrefs().get("event.buytrigger", "3"));
	private Integer sellEventTriggerThreshold = new Integer(MainPMScmd.getPrefs().get("event.selltrigger", "-1"));
	private List<String> sellIndicators = Arrays.asList(MainPMScmd.getPrefs().get("event.sellindicators", EventDefinition.getPMEventDefinitionsString()).split(","));
	private List<String> buyIndicators =   Arrays.asList(MainPMScmd.getPrefs().get("event.buyindicators", EventDefinition.getPMEventDefinitionsString()).split(","));
	
	//TODO : different span and event thresholds for first and second pass ...
	private Integer backwardDaySpan = new Integer(MainPMScmd.getPrefs().get("event.backwarddayspan", "40"));
	//private Integer secondPassbackwardDaySpan = new Integer(MainPMScmd.prefs.get("event.backwarddayspan", "45"));
	
	private String buyPonderationRule = MainPMScmd.getPrefs().get("event.buyponderationrule", LatestEventsIndicatorOnlyPonderationRule.class.getSimpleName());
	private String sellPonderationRule = MainPMScmd.getPrefs().get("event.sellponderationrule", LatestEventsPonderationRule.class.getSimpleName());
	private String configListFileName = IndicatorCalculationServiceMain.UI_ANALYSIS;
	
	//Roc
	private int rocNNeuralHouseTrendPeriod = new Integer(MainPMScmd.getPrefs().get("rocnneural.houseTrendPeriod", "21"));
	private int rocNNeuralQuoteSmthPeriod = new Integer(MainPMScmd.getPrefs().get("rocnneural.quoteSmthPeriod", "1"));
	
	

	public EventSignalConfig() {
		super();
	}
	
	@Deprecated
	public EventSignalConfig(
			String analyse,
			String buyEventTriggerThreshold,String sellEventTriggerThreshold,
			String stopLossRate, String sellAlertRate, String maxLoss,
			String indicators, String buyIndicators, String sellIndicators) {
		this();
		this.analysis = analyse;
		
		this.buyEventTriggerThreshold = new Integer(buyEventTriggerThreshold);
		this.sellEventTriggerThreshold = new Integer(sellEventTriggerThreshold);
		
		this.limitPriceAbove = new BigDecimal(stopLossRate).setScale(2);
		this.sellLimitToPrice = new BigDecimal(sellAlertRate).setScale(2);
		this.limitPriceBelow = new BigDecimal(maxLoss).setScale(2);
		
		this.indicators = Arrays.asList(indicators.split(","));
		this.buyIndicators =  Arrays.asList(buyIndicators.split(","));
		this.sellIndicators =  Arrays.asList(sellIndicators.split(","));
		
	}
	
	protected EventSignalConfig(
				String analyse, 
				String buyPonderationRule, String sellPonderationRule, String backDaysSpan, 
				String buyThreshold, String sellThreshold, String indicators, String configListFileName) {
		this();
		this.analysis = analyse;
		this.buyPonderationRule = buyPonderationRule;
		this.sellPonderationRule = sellPonderationRule;
		this.backwardDaySpan = new Integer(backDaysSpan);
		this.buyEventTriggerThreshold = new Integer(buyThreshold);
		this.sellEventTriggerThreshold = new Integer(sellThreshold);
		
		String[] indicatorsSplit = indicators.split(",");
		this.indicators = Arrays.asList(indicatorsSplit);
		this.buyIndicators =  Arrays.asList(indicatorsSplit);
		this.sellIndicators =  Arrays.asList(indicatorsSplit);
		this.configListFileName = configListFileName;
		
	}

	public List<EventInfo> indicatorsStringToEventDefs(List<String> eventDefinitionsNames) {
		SortedSet<EventInfo> eventDefinitions = new  TreeSet<EventInfo>(new Comparator<EventInfo>() {
			
			public int compare(EventInfo o1, EventInfo o2) {
				return o1.getEventDefId().compareTo(o2.getEventDefId());
			}
		});
		for (String eventDefinitionsName : eventDefinitionsNames) {
			eventDefinitions.add(EventDefinition.valueOf(eventDefinitionsName));
		}
		return new ArrayList<EventInfo>(eventDefinitions);
	}

	public List<EventInfo> getIndicators() {
		return this.indicatorsStringToEventDefs(this.indicators);
	}
	
	public SortedSet<EventInfo> getIndicatorsSorted() {
		
		if (indicatorSortedCache == null) {
			
			SortedSet<EventInfo> indicatorSortedCacheTmp = new TreeSet<EventInfo>(new Comparator<EventInfo>() {
				@Override
				public int compare(EventInfo o1, EventInfo o2) {
					return o1.getEventDefinitionRef().compareTo(o2.getEventDefinitionRef());
				}
			});
			indicatorSortedCacheTmp.addAll(this.getIndicators());
			
			indicatorSortedCache = Collections.unmodifiableSortedSet(indicatorSortedCacheTmp);
			
		}
		
		return indicatorSortedCache;
		
	}
	
	public List<EventInfo> getIndepIndicators() {
		return this.indicatorsStringToEventDefs(this.indepIndicators);
	}
	
	@SuppressWarnings("unchecked")
	public SortedSet<EventInfo> getAllTechIndicatorsSorted(Boolean refreshCache) {
		
		if (allTechIndicatorsSortedCache == null || refreshCache) {
		
			synchronized (LOGGER) {
				
				this.allEventInfos = null;
				
				
				SortedSet<EventInfo> allTechIndicatorsSortedCacheTmp = new TreeSet<EventInfo>(new Comparator<EventInfo>() {
					@Override
					public int compare(EventInfo o1, EventInfo o2) {
						return o1.getEventDefinitionRef().compareTo(o2.getEventDefinitionRef());
					}
				});

				allTechIndicatorsSortedCacheTmp.addAll(EventDefinition.loadFirstPassPrefEventDefinitions());

				Integer maxPass = Integer.valueOf(MainPMScmd.getPrefs().get("event.nbPassMax", "1"));
				if (maxPass > 1) {

					List<EventInfo> indepIndicators = this.getIndepIndicators();
					if (PARAMETERIZEDINDICATORSBUILDER == null) PARAMETERIZEDINDICATORSBUILDER = (ParameterizedIndicatorsBuilder) SpringContext.getSingleton().getBean("parameterizedIndicatorsBuilder");
					for (EventInfo eventDefinition : indepIndicators) {

						if (eventDefinition.getEventDefinitionRef().equals(EventDefinition.PARAMETERIZED.name())) {
							@SuppressWarnings("rawtypes")
							Collection values = PARAMETERIZEDINDICATORSBUILDER.getUserEnabledOperations().values();
							allTechIndicatorsSortedCacheTmp.remove(EventDefinition.PARAMETERIZED);
							allTechIndicatorsSortedCacheTmp.addAll(values);

						} else {
							allTechIndicatorsSortedCacheTmp.add(eventDefinition);
						}

					}
				}
				allTechIndicatorsSortedCache = Collections.unmodifiableSortedSet(allTechIndicatorsSortedCacheTmp);
			}
		}
		
		return allTechIndicatorsSortedCache;
	}
	
	public SortedSet<EventInfo> getAllEventInfos() {

		if (allEventInfos == null) {
			
			synchronized (LOGGER) {
				
				SortedSet<EventInfo> allEventInfotmp = new TreeSet<EventInfo>(new Comparator<EventInfo>() {
					@Override
					public int compare(EventInfo o1, EventInfo o2) {
						return o1.getEventDefinitionRef().compareTo(o2.getEventDefinitionRef());
					}
				});
				allEventInfotmp.addAll(getAllTechIndicatorsSorted(false));
				allEventInfotmp.addAll(EventDefinition.nonTechEventDef());
				allEventInfos = Collections.unmodifiableSortedSet(allEventInfotmp);
				
			}
		}
		
		return allEventInfos;
	}

	public Integer getBuyEventTriggerThreshold() {
		return buyEventTriggerThreshold;
	}

	public Integer getSellEventTriggerThreshold() {
		return sellEventTriggerThreshold;
	}

	public BigDecimal getLimitPriceAbove() {
		return limitPriceAbove;
	}

	public BigDecimal getSellLimitToPrice() {
		return sellLimitToPrice;
	}


	public BigDecimal getLimitPriceBelow() {
		return limitPriceBelow;
	}


	public List<String> getSellIndicators() {
		return sellIndicators;
	}


	public List<String> getBuyIndicators() {
		return buyIndicators;
	}

	public String getAnalysis() {
		return analysis;
	}

	public Integer getBackwardDaySpan() {
		return backwardDaySpan;
	}

	public void setBackwardDaySpan(Integer backwardDaySpan) {
		this.backwardDaySpan = backwardDaySpan;
	}

	@Override
	public String getName() {
		return EVENT_SIGNAL_NAME;
	}

	public PonderationRule getBuyPonderationRule(Object... constructParam) {
		return instanciateClass(buyPonderationRule, constructParam);
	}
	
	public String getBuyPonderationRuleString() {
		return buyPonderationRule;
	}
	
	public String getSellPonderationRuleString() {
		return sellPonderationRule;
	}

	public PonderationRule getSellPonderationRule(Object... constructParam) {
		return instanciateClass(sellPonderationRule, constructParam);
	}


	@SuppressWarnings("unchecked")
	private PonderationRule instanciateClass(String ponderationRuleClassName, Object... constructParam) {
		try {
			Class<PonderationRule> ponderationRuleClass =  (Class<PonderationRule> ) Class.forName("com.finance.pms.events.pounderationrules."+ponderationRuleClassName);
			try {
				//Constructor<PonderationRule> constructor = ponderationRuleClass.getConstructor(String.class);
				Class<?>[] paramTypes = new Class<?>[constructParam.length];
				for (int i = 0; i < constructParam.length; i++) {
					paramTypes[i] = constructParam[i].getClass();
				}
				Constructor<PonderationRule> constructor = ponderationRuleClass.getConstructor(paramTypes);
				PonderationRule newInstance = constructor.newInstance(constructParam);
				LOGGER.debug("Found constructor with param for : "+newInstance.getClass().getName());
				return newInstance;
			} catch (SecurityException e) {
				LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
				return ponderationRuleClass.newInstance();
			} catch (NoSuchMethodException e) {
				PonderationRule newInstance = ponderationRuleClass.newInstance();
				LOGGER.debug("Found constructor with NO param for : "+newInstance.getClass().getName());
				return newInstance;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
				return ponderationRuleClass.newInstance();
			} catch (InvocationTargetException e) {
				LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
				return ponderationRuleClass.newInstance();
			}
		} catch (InstantiationException e) {
			LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
			return new SilentPonderationRule();
		} catch (IllegalAccessException e) {
			LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
			return new SilentPonderationRule();
		} catch (ClassNotFoundException e) {
			LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName,e);
			return new SilentPonderationRule();
		}
	}

	public void setBuyEventTriggerThreshold(Integer buyEventTriggerThreshold) {
		this.buyEventTriggerThreshold = buyEventTriggerThreshold;
	}

	public void setSellEventTriggerThreshold(Integer sellEventTriggerThreshold) {
		this.sellEventTriggerThreshold = sellEventTriggerThreshold;
	}

	public void setSellIndicators(List<String> sellIndicators) {
		this.sellIndicators = sellIndicators;
	}

	public void setBuyIndicators(List<String> buyIndicators) {
		this.buyIndicators = buyIndicators;
	}

	public BigDecimal getSellLimitGuardPrice() {
		return sellLimitGuardPrice;
	}

	public void setSellLimitGuardPrice(BigDecimal sellLimitGuardPrice) {
		this.sellLimitGuardPrice = sellLimitGuardPrice;
	}

	public BigDecimal getExpectedRate() {
		return expectedRate;
	}

	public void setExpectedRate(BigDecimal expectedRate) {
		this.expectedRate = expectedRate;
	}

	public void setIndicators(List<String> indicators) {
		this.indicators = indicators;
		this.indicatorSortedCache = null;
		this.allTechIndicatorsSortedCache = null;
		this.allEventInfos = null;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public void setBuyPonderationRule(String buyPonderationRule) {
		this.buyPonderationRule = buyPonderationRule;
	}

	public void setSellPonderationRule(String sellPonderationRule) {
		this.sellPonderationRule = sellPonderationRule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backwardDaySpan == null) ? 0 : backwardDaySpan.hashCode());
		result = prime * result + ((buyEventTriggerThreshold == null) ? 0 : buyEventTriggerThreshold.hashCode());
		result = prime * result + ((buyIndicators == null) ? 0 : buyIndicators.size());
		result = prime * result + ((buyPonderationRule == null) ? 0 : buyPonderationRule.hashCode());
		result = prime * result + ((expectedRate == null) ? 0 : expectedRate.hashCode());
		result = prime * result + ((indepIndicators == null) ? 0 : indepIndicators.size());
		result = prime * result + ((indicators == null) ? 0 : indicators.size());
		result = prime * result + ((limitPriceAbove == null) ? 0 : limitPriceAbove.hashCode());
		result = prime * result + ((limitPriceBelow == null) ? 0 : limitPriceBelow.hashCode());
		result = prime * result + ((sellEventTriggerThreshold == null) ? 0 : sellEventTriggerThreshold.hashCode());
		result = prime * result + ((sellIndicators == null) ? 0 : sellIndicators.size());
		result = prime * result + ((sellLimitGuardPrice == null) ? 0 : sellLimitGuardPrice.hashCode());
		result = prime * result + ((sellLimitToPrice == null) ? 0 : sellLimitToPrice.hashCode());
		result = prime * result + ((sellPonderationRule == null) ? 0 : sellPonderationRule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventSignalConfig other = (EventSignalConfig) obj;
		if (backwardDaySpan == null) {
			if (other.backwardDaySpan != null)
				return false;
		} else if (!backwardDaySpan.equals(other.backwardDaySpan))
			return false;
		if (buyEventTriggerThreshold == null) {
			if (other.buyEventTriggerThreshold != null)
				return false;
		} else if (!buyEventTriggerThreshold.equals(other.buyEventTriggerThreshold))
			return false;
		if (buyIndicators == null) {
			if (other.buyIndicators != null)
				return false;
		} else if (!compareLists(buyIndicators,other.buyIndicators))
			return false;
		if (buyPonderationRule == null) {
			if (other.buyPonderationRule != null)
				return false;
		} else if (!buyPonderationRule.equals(other.buyPonderationRule))
			return false;
		if (expectedRate == null) {
			if (other.expectedRate != null)
				return false;
		} else if (!expectedRate.equals(other.expectedRate))
			return false;
		if (indepIndicators == null) {
			if (other.indepIndicators != null)
				return false;
		} else if (!compareLists(indepIndicators,indepIndicators))
			return false;
		if (indicators == null) {
			if (other.indicators != null)
				return false;
		} else if (!compareLists(indicators,other.indicators))
			return false;
		if (limitPriceAbove == null) {
			if (other.limitPriceAbove != null)
				return false;
		} else if (!limitPriceAbove.equals(other.limitPriceAbove))
			return false;
		if (limitPriceBelow == null) {
			if (other.limitPriceBelow != null)
				return false;
		} else if (!limitPriceBelow.equals(other.limitPriceBelow))
			return false;
		if (sellEventTriggerThreshold == null) {
			if (other.sellEventTriggerThreshold != null)
				return false;
		} else if (!sellEventTriggerThreshold.equals(other.sellEventTriggerThreshold))
			return false;
		if (sellIndicators == null) {
			if (other.sellIndicators != null)
				return false;
		} else if (!compareLists(sellIndicators,other.sellIndicators))
			return false;
		if (sellLimitGuardPrice == null) {
			if (other.sellLimitGuardPrice != null)
				return false;
		} else if (!sellLimitGuardPrice.equals(other.sellLimitGuardPrice))
			return false;
		if (sellLimitToPrice == null) {
			if (other.sellLimitToPrice != null)
				return false;
		} else if (!sellLimitToPrice.equals(other.sellLimitToPrice))
			return false;
		if (sellPonderationRule == null) {
			if (other.sellPonderationRule != null)
				return false;
		} else if (!sellPonderationRule.equals(other.sellPonderationRule))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "EventSignalConfig [analyse=" + analysis + "]";
	}

	public void setIndepIndicators(List<String> indepIndicators) {
		this.indepIndicators = indepIndicators;
		this.allTechIndicatorsSortedCache = null;
		this.allEventInfos = null;
	}
	
	public void setIndepIndicators(String... indepIndicators) {
		this.setIndepIndicators(Arrays.asList(indepIndicators));
	}

	protected Boolean compareLists(List<? extends Object> list1, List<? extends Object> list2) {
		for (Object string2 : list2) {
			if (!list1.contains(string2)) return false;
		}
		for (Object string1 : list1) {
			if (!list2.contains(string1)) return false;
		}
		return true;
	}

	public Double getInfVolatility() {
		return infVolatility;
	}

	public void setInfVolatility(Double volatilityCheck) {
		this.infVolatility = volatilityCheck;
	}

	public Integer getVolatilityPeriod() {
		return volatilityPeriod;
	}

	public Double getSupVolatility() {
		return supVolatility;
	}

	public void setSupVolatility(Double supVolatility) {
		this.supVolatility = supVolatility;
	}
	
	@Override
	public Object clone() {
		EventSignalConfig clone = new EventSignalConfig();
		try {
			clone = (EventSignalConfig) super.clone();
			clone.indicators = new ArrayList<String>(this.indicators);
			clone.indepIndicators = new ArrayList<String>(this.indepIndicators);
			clone.sellIndicators = new ArrayList<String>(this.sellIndicators);
			clone.buyIndicators = new ArrayList<String>(this.buyIndicators);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return clone;
	}

	public String getConfigListFileName() {
		return configListFileName;
	}

	public int getRocNNeuralHouseTrendPeriod() {
		return rocNNeuralHouseTrendPeriod;
	}

	public int getRocNNeuralQuoteSmthPeriod() {
		return rocNNeuralQuoteSmthPeriod;
	}

	public void setRocNNeuralHouseTrendPeriod(int rocNNeuralHouseTrendPeriod) {
		this.rocNNeuralHouseTrendPeriod = rocNNeuralHouseTrendPeriod;
	}

	public void setRocNNeuralQuoteSmthPeriod(int rocNNeuralQuoteSmthPeriod) {
		this.rocNNeuralQuoteSmthPeriod = rocNNeuralQuoteSmthPeriod;
	}

}
