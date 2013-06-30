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
package com.finance.pms.events.calculation.parametrizedindicators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.EventCompostionCalculator;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TargetStockInfo.Output;
import com.finance.pms.events.operations.conditional.EventConditionHolder;
import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.StringValue;

public class ParameterizedCalculator extends EventCompostionCalculator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedCalculator.class);

	private TargetStockInfo targetStock;
	private EventConditionHolder conditionHolder;
	
	/**
	 * @param stock 
	 * @param startDate 
	 * @param endDate 
	 * @param calculationCurrency 
	 * @param analyseName 
	 * @param export 
	 * @param persistTrainingEvents 
	 * @param observers 
	 * @throws WarningException 
	 */
	public ParameterizedCalculator(EventInfo eventInfo, Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String analyseName, Boolean export, Boolean persistTrainingEvents, Observer... observers) 
			throws WarningException  {
		
		super(stock);		
		targetStock = new TargetStockInfo(analyseName, stock, startDate, endDate);
		this.conditionHolder = (EventConditionHolder) eventInfo;
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(String eventListName) {
		
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();

		if (conditionHolder.getFormula() != null) {
			
			conditionHolder.setOperandsParams(null, null, new StringValue(eventListName));
			EventDataValue run = (EventDataValue) conditionHolder.run(targetStock);

			SortedMap<EventKey, EventValue> returnedEvents = run.getValue(targetStock);
			
			Date currentKeyDate = null;
			EventKey previousKey = null;
			SortedSet<EventKey> toRemove = new TreeSet<EventKey>();
			for (EventKey currentKey : returnedEvents.keySet()) {
				if (currentKeyDate != null && currentKeyDate.compareTo(currentKey.getDate()) == 0) {
					toRemove.add(currentKey);
					toRemove.add(previousKey);
				}
				if (currentKeyDate == null || currentKeyDate.compareTo(currentKey.getDate()) != 0) {
					currentKeyDate = currentKey.getDate();
				}
				previousKey = currentKey;
			}
			
			LOGGER.warn("Indeterministic events for parameterised calculator '"+this.getEventDefinition().getEventReadableDef()+"' : "+toRemove);
			for (EventKey eventKey : toRemove) {
				EventValue eventValue = returnedEvents.get(eventKey);
				returnedEvents.remove(eventKey);
				
				EventKey noneEventKey = new ParameterizedEventKey(eventKey.getDate(), eventKey.getEventInfo(), EventType.NONE);
				eventValue.setEventType(EventType.NONE);
				returnedEvents.put(noneEventKey,eventValue);
			}
			
			
			edata.putAll(returnedEvents);
		}

		
		return edata;
	}

	@Override
	public SortedMap<Date, double[]> calculationOutput() {
		return buildCalculationOutput();
	}
	

	private SortedMap<Date, double[]> buildCalculationOutput() {

		SortedMap<Date, double[]> calculationOutput = new TreeMap<Date, double[]>();

		List<Output> gatheredOutputs = targetStock.getGatheredChartableOutputs();
		
		List<Object> normOutputs = new ArrayList<Object>();
		SortedSet<Date> fullDateSet = new TreeSet<Date>();
	
		//Add Double outputs
		for (Output output : gatheredOutputs) {
			SortedMap<Date, Double> data = ((DoubleMapValue) output.getOutputData()).getValue(targetStock);
			normOutputs.add(data);
			fullDateSet.addAll(data.keySet());
		}
		

		//Add Constants
		List<ChartedOutputGroup> chartedOutputGroups = targetStock.getChartedOutputGroups();
		for (ChartedOutputGroup chartedOutputGroup : chartedOutputGroups) {
			Collection<OutputDescr> values = chartedOutputGroup.getComponents().values();
			for (OutputDescr outputDescr : values) {
				if (outputDescr.getType().equals(Type.CONSTANT)) {
					normOutputs.add(outputDescr.getValue().getValue(targetStock));
					outputDescr.setOutputIndex(normOutputs.size()-1);
				}
			}
		}
		
		//Fill up outputs not attached
		ChartedOutputGroup invisibleGroup = null;
		for (int i = 0; i < gatheredOutputs.size(); i++) {
			if (gatheredOutputs.get(i).getChartedDescription() == null) {
				if (invisibleGroup == null ) invisibleGroup = new ChartedOutputGroup(gatheredOutputs.get(i).getOutputReference(), i);
				invisibleGroup.getComponents().put(gatheredOutputs.get(i).getOutputReference(), invisibleGroup.new OutputDescr(invisibleGroup, Type.INVISIBLE, i, null, null));
			}
		}
			
		((EventDefDescriptorDynamic) conditionHolder.getEventDefDescriptor()).setChartedOutputGroups(chartedOutputGroups, invisibleGroup);

		//Build
		for (Date date : fullDateSet) {

			double[] retOutput = new double[normOutputs.size()]; 

			int outputPosition = 0;
			for (Object normOutput : normOutputs) {

				if (normOutput instanceof SortedMap) {
					
					@SuppressWarnings("unchecked")
					Double ds2 = ((SortedMap<Date, Double>)normOutput).get(date);
					retOutput[outputPosition] = translateOutputForCharting(ds2);
				} 
				else if (normOutput instanceof Double) {
					retOutput[outputPosition] = (Double)normOutput;
				}
				outputPosition++;
			}
			calculationOutput.put(date, retOutput);
		}

		return calculationOutput;
	}

	@Override
	protected int getDaysSpan() {
		return 0;
	}

	@Override
	public EventInfo getEventDefinition() {
		return conditionHolder;
	}
	
	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

}
