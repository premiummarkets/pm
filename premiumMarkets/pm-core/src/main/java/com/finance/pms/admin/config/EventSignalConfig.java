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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PostInitMonitor;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.conditional.OperationsCompositioner;
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
        String endDateStr = MainPMScmd.getMyPrefs().get("test.endDate",null);
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
            return new Date(EventSignalConfig.ENDDATE.getTime());
        } else {
            return DateFactory.midnithDate(new Date());
        }
    }

    private static ParameterizedIndicatorsBuilder PARAMETERIZEDINDICATORSBUILDER;


    private String analysis = "";

    private Double infVolatility = 0d;
    private Double supVolatility = 0d;
    private Integer volatilityPeriod = 20;

    private BigDecimal limitPriceAbove = new BigDecimal(MainPMScmd.getMyPrefs().get("event.stoploss", "0.04")).setScale(2);
    private BigDecimal limitPriceBelow = new BigDecimal(MainPMScmd.getMyPrefs().get("event.maxloss", "0.05")).setScale(2);
    private BigDecimal sellLimitToPrice = new BigDecimal(MainPMScmd.getMyPrefs().get("event.sellalert", "0.2")).setScale(2);
    private BigDecimal sellLimitGuardPrice = new BigDecimal(MainPMScmd.getMyPrefs().get("event.sellalertguard", "0.1")).setScale(2);
    private BigDecimal expectedRate = new BigDecimal(MainPMScmd.getMyPrefs().get("event.expectedrate", "0.05")).setScale(2);

    private List<String> indicators = Arrays.asList(MainPMScmd.getMyPrefs().get("event.indicators", EventDefinition.getPMEventDefinitionsString()).split(","));//Indicators used in pass one (as in db.properties)
    private SortedSet<EventInfo> indicatorSortedCache = null;
    private List<String> indepIndicators = Arrays.asList(MainPMScmd.getMyPrefs().get("event.indepIndicators", EventDefinition.getIndepEventDefinitionsString()).split(","));//Pass two indicators + Parent Parameterised event (as in db.properties)
    private SortedSet<EventInfo> allTechIndicatorsSortedCache= null; //First and second pass indicators + Sub parameterised events
    private SortedSet<EventInfo> allEventInfos = null; //First and second pass indicators + Sub parameterised events + alerts, screener events and 'constant' events.
    private SortedSet<EventInfo> allParameterized = null;
    private SortedSet<OperationsCompositioner> filteredParameterised = null;


    private Integer buyEventTriggerThreshold =  new Integer(MainPMScmd.getMyPrefs().get("event.buytrigger", "1"));
    private Integer sellEventTriggerThreshold = new Integer(MainPMScmd.getMyPrefs().get("event.selltrigger", "-1"));
    private List<String> sellIndicators = Arrays.asList(MainPMScmd.getMyPrefs().get("event.sellindicators", EventDefinition.getPMEventDefinitionsString()).split(","));
    private List<String> buyIndicators =   Arrays.asList(MainPMScmd.getMyPrefs().get("event.buyindicators", EventDefinition.getPMEventDefinitionsString()).split(","));

    //TODO : different span and event thresholds for first and second pass ...
    private Integer backwardDaySpan = new Integer(MainPMScmd.getMyPrefs().get("event.backwarddayspan", "40"));
    //private Integer secondPassbackwardDaySpan = new Integer(MainPMScmd.prefs.get("event.backwarddayspan", "45"));

    private String buyPonderationRule = MainPMScmd.getMyPrefs().get("event.buyponderationrule", LatestEventsIndicatorOnlyPonderationRule.class.getSimpleName());
    private String sellPonderationRule = MainPMScmd.getMyPrefs().get("event.sellponderationrule", LatestEventsPonderationRule.class.getSimpleName());
    private String configListFileName = IndicatorCalculationServiceMain.UI_ANALYSIS;

    //Roc
    private int rocNNeuralHouseTrendPeriod = new Integer(MainPMScmd.getMyPrefs().get("rocnneural.houseTrendPeriod", "21"));
    private int rocNNeuralQuoteSmthPeriod = new Integer(MainPMScmd.getMyPrefs().get("rocnneural.quoteSmthPeriod", "1"));

    //Neural and Bar chart event occ span
    private int perceptronTrainingPMEventOccLowerSpan = new Integer(MainPMScmd.getMyPrefs().get("perceptron.trainingPMEventOccLowerSpan", "12"));



    public EventSignalConfig() {
        super();
    }

    @Deprecated
    public EventSignalConfig(
            String analyse,
            String buyEventTriggerThreshold, String sellEventTriggerThreshold,
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

    public EventSignalConfig(
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

    public List<EventInfo> getIndicators() {
        return EventDefinition.indicatorsStringToEventDefs(this.indicators);
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
        return EventDefinition.indicatorsStringToEventDefs(this.indepIndicators);
    }
    
    public List<EventInfo> getIndepsAndParameterised() {
        List<EventInfo> indepIndicators = getIndepIndicators();
        if (indepIndicators.contains(EventDefinition.PARAMETERIZED)) {
            indepIndicators.remove(EventDefinition.PARAMETERIZED);
            indepIndicators.addAll(getAllParameterized());
        }
        return indepIndicators;
    }

    public void tamperIndepAndParameterizedEventInfoList(Collection<EventInfo> overridingEventInfos) {

        Set<String> indepIndicators = new HashSet<String>();
        SortedSet<OperationsCompositioner> filteredParameterised = new TreeSet<OperationsCompositioner>();
        for (EventInfo eventInfo : overridingEventInfos) {
            if (this.getIndepIndicators().contains(eventInfo)) {
                indepIndicators.add(eventInfo.getEventDefinitionRef());
            }
            if (eventInfo.getEventDefId().equals(EventDefinition.PARAMETERIZED.getEventDefId())) {
                indepIndicators.add(EventDefinition.PARAMETERIZED.name());
                filteredParameterised.add((OperationsCompositioner) eventInfo);
            }
        }

        this.setIndepIndicators(new ArrayList<String>(indepIndicators));
        if (!filteredParameterised.isEmpty()) this.setFilteredParameterised(filteredParameterised);

    }

    @SuppressWarnings("unchecked")
    public SortedSet<EventInfo> getAllTechIndicatorsSorted(Boolean refreshCache) {

        PostInitMonitor.waitForOptPostInitEnd();

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

                Integer maxPass = Integer.valueOf(MainPMScmd.getMyPrefs().get("event.nbPassMax", "1"));
                if (maxPass > 1) {

                    List<EventInfo> indepIndicators = this.getIndepIndicators();

                    for (EventInfo eventDefinition : indepIndicators) {

                        if (eventDefinition.getEventDefinitionRef().equals(EventDefinition.PARAMETERIZED.name())) {

                            if (PARAMETERIZEDINDICATORSBUILDER == null && SpringContext.getSingleton().containsBean("parameterizedIndicatorsBuilder")) {
                                PARAMETERIZEDINDICATORSBUILDER = (ParameterizedIndicatorsBuilder) SpringContext.getSingleton().getBean("parameterizedIndicatorsBuilder");
                            }

                            TreeSet<EventInfo> allParameterizedTmp = new TreeSet<EventInfo>(new Comparator<EventInfo>() {
                                @Override
                                public int compare(EventInfo o1, EventInfo o2) {
                                    return o1.getEventDefinitionRef().compareTo(o2.getEventDefinitionRef());
                                }
                            });

                            if (PARAMETERIZEDINDICATORSBUILDER != null) {

                                @SuppressWarnings("rawtypes")
                                Collection values;
                                if (this.filteredParameterised == null) {
                                    values = PARAMETERIZEDINDICATORSBUILDER.getUserEnabledOperations().values();
                                } else {
                                    values = this.filteredParameterised;
                                }
                                allTechIndicatorsSortedCacheTmp.remove(EventDefinition.PARAMETERIZED);
                                allTechIndicatorsSortedCacheTmp.addAll(values);
                                allParameterizedTmp.addAll(values);
                            }

                            this.allParameterized = Collections.unmodifiableSortedSet(allParameterizedTmp);

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


    public SortedSet<EventInfo> getAllParameterized() {

        if (allParameterized == null) {
            getAllTechIndicatorsSorted(false);
        }

        return allParameterized;
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

    public PonderationRule getNewBuyPonderationRule(Object... constructParam) {
        return instanciateClass(buyPonderationRule, constructParam);
    }

    public String getBuyPonderationRuleString() {
        return buyPonderationRule;
    }

    public String getSellPonderationRuleString() {
        return sellPonderationRule;
    }

    public PonderationRule geNewtSellPonderationRule(Object... constructParam) {
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
                LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName + " with params " + Arrays.toString(constructParam), e);
                return ponderationRuleClass.newInstance();
            } catch (NoSuchMethodException e) {
                PonderationRule newInstance = ponderationRuleClass.newInstance();
                LOGGER.debug("Found constructor with NO param for : "+newInstance.getClass().getName());
                return newInstance;
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName  + " with params " + Arrays.toString(constructParam), e);
                return ponderationRuleClass.newInstance();
            } catch (InvocationTargetException e) {
                LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName  + " with params " + Arrays.toString(constructParam), e);
                return ponderationRuleClass.newInstance();
            }
        } catch (Exception e) {
            LOGGER.error("Unknown Ponderation Rule "+ponderationRuleClassName + " with params " + Arrays.toString(constructParam), e);
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
        this.allParameterized = null;

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
        result = prime * result + rocNNeuralHouseTrendPeriod;
        result = prime * result + rocNNeuralQuoteSmthPeriod;
        result = prime * result + perceptronTrainingPMEventOccLowerSpan;
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
        if (rocNNeuralHouseTrendPeriod != other.rocNNeuralHouseTrendPeriod)
            return false;
        if (rocNNeuralQuoteSmthPeriod != other.rocNNeuralQuoteSmthPeriod)
            return false;
        if (perceptronTrainingPMEventOccLowerSpan != other.perceptronTrainingPMEventOccLowerSpan)
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
        this.allParameterized = null;

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
            clone.allEventInfos = (this.allEventInfos != null)?new TreeSet<EventInfo>(this.allEventInfos):null;
            clone.allParameterized = (this.allParameterized != null)?new TreeSet<EventInfo>(this.allParameterized):null;
            clone.allTechIndicatorsSortedCache = (this.allTechIndicatorsSortedCache != null)?new TreeSet<EventInfo>(this.allTechIndicatorsSortedCache):null;
            clone.buyIndicators = new ArrayList<String>(this.buyIndicators);
            clone.indepIndicators = new ArrayList<String>(this.indepIndicators);
            clone.indicators = new ArrayList<String>(this.indicators);
            clone.indicatorSortedCache =  (this.indicatorSortedCache != null)?new TreeSet<EventInfo>(this.indicatorSortedCache):null;
            clone.sellIndicators = new ArrayList<String>(this.sellIndicators);
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

    public int getPerceptronTrainingPMEventOccLowerSpan() {
        return perceptronTrainingPMEventOccLowerSpan;
    }
    public void setPerceptronTrainingPMEventOccLowerSpan(int trainingPMEventOccLowerSpan) {
        this.perceptronTrainingPMEventOccLowerSpan = trainingPMEventOccLowerSpan;
    }

    public void setFilteredParameterised(SortedSet<OperationsCompositioner> filteredParameterised) {
        this.filteredParameterised = filteredParameterised;
    }

}
