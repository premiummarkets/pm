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
package com.finance.pms.events.calculation.parametrizedindicators;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.finance.pms.events.calculation.IndicatorsOperator;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TargetStockInfo.Output;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.UnarableMapValue;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
/**
 * 
 * @author guil
 * The ParameterizedIndicatorsCompositioner is a specific IndicatorsCompositioner calculator parameterized through the mean of an OperationCompositionner and its user formulae.
 * It is the core calculator that is used to initiates all EventInfo in the calculations loops.
 */
public class ParameterizedIndicatorsOperator extends IndicatorsOperator {

	private static MyLogger LOGGER = MyLogger.getLogger(ParameterizedIndicatorsOperator.class);

	private TargetStockInfo targetStock;
	private EventInfoOpsCompoOperation eventInfoOpsCompoOperationHolder;

	public ParameterizedIndicatorsOperator(EventInfo eventInfo, Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String analyseName, Observer... observers)
			throws WarningException  {

		super(observers);
		//XXX TODO should the event info be cloned here or this would assume that only one calculation can run at a time for a given event info??
		//XXX or made stateless??
		this.eventInfoOpsCompoOperationHolder = (EventInfoOpsCompoOperation) eventInfo;

		//Adjust start
		Integer startShiftOverrideValue = ((NumberValue) this.eventInfoOpsCompoOperationHolder.getOperands().get(3).getParameter()).getNumberValue().intValue();
		int operationStartDateShift = 0;
		if (!startShiftOverrideValue.equals(-1)) {
			operationStartDateShift = startShiftOverrideValue;
		} else {
			operationStartDateShift = this.eventInfoOpsCompoOperationHolder.operationStartDateShift();
		}
		Calendar adjustedStartCal = Calendar.getInstance();
		adjustedStartCal.setTime(startDate);

		//Adjust end
		Date lastQuote = stock.getLastQuote();
		Date adjustedEndDate;
		if (lastQuote.before(endDate)) {
			adjustedEndDate = lastQuote;
			LOGGER.info(this.eventInfoOpsCompoOperationHolder.getReference()+" end date shift to : "+operationStartDateShift+". Requested end : "+endDate+", calculated end : "+adjustedEndDate);
		} else {
			adjustedEndDate = endDate;
		}

		//Target stock instance
		this.targetStock = new TargetStockInfo(analyseName, this.eventInfoOpsCompoOperationHolder, stock, adjustedStartCal.getTime(), adjustedEndDate);

	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) {

		SortedMap<EventKey, EventValue> eData = new TreeMap<>();

		if (eventInfoOpsCompoOperationHolder.getFormulae() != null) {

			eventInfoOpsCompoOperationHolder.setOperandsParams(null, null, null, null, new StringValue(eventListName));
			EventMapValue eventMapValue = (EventMapValue) eventInfoOpsCompoOperationHolder.run(targetStock, 0);

			SortedMap<EventKey, EventValue> returnedEvents = eventMapValue.getEventMap();

			//Finding duplicates
			EventKey previousKey = null;
			SortedSet<EventKey> toRemove = new TreeSet<EventKey>();
			for (EventKey currentKey : returnedEvents.keySet()) {

				Date previousKeyDate = (previousKey == null)? null : previousKey.getDate();
				Date currentKeyDate = currentKey.getDate();

				if (previousKeyDate != null && previousKeyDate.compareTo(currentKeyDate) == 0) {
					toRemove.add(currentKey);
					toRemove.add(previousKey);
				}

				previousKey = currentKey;
			}
			LOGGER.warn("Opposite simultaneous event values for customised calculator '" + this.getEventDefinition().getEventReadableDef()+"' : "+toRemove);

			//Removing duplicates
			for (EventKey eventKey : toRemove) {
				EventValue eventValue = returnedEvents.get(eventKey);
				returnedEvents.remove(eventKey);

				EventKey noneEventKey = new ParameterizedEventKey(eventKey.getDate(), eventKey.getEventInfo(), EventType.NONE);
				eventValue.setEventType(EventType.NONE);
				returnedEvents.put(noneEventKey, eventValue);
			}

			eData.putAll(returnedEvents);

		}

		return eData;
	}

	/**
	 * Here, outputs are gathered from the results of the calculation stored into TargetStockInfo.gatheredChartableOutputs
	 * @see com.finance.pms.events.calculation.IndicatorsOperator#calculationOutput()
	 */
	@Override
	public SortedMap<Date, double[]> calculationOutput() {
		return buildCalculationOutput();
	}


	private SortedMap<Date, double[]> buildCalculationOutput() {

		try {

			List<Output> gatheredOutputs = targetStock.getGatheredChartableOutputs();

			List<Object> normOutputs = new ArrayList<>();
			SortedSet<Date> fullDateSet = new TreeSet<>();

			//Add Double outputs
			for (Output output : gatheredOutputs) {
				Value<?> outputData = output.getOutputData();
				if (outputData != null) {
					SortedMap<Date, Double> data = ((UnarableMapValue) outputData).getValue(targetStock);
					normOutputs.add(data);
					fullDateSet.addAll(data.keySet());
				}
			}

			//Add Constants
			List<ChartedOutputGroup> chartedOutputGroups = targetStock.getChartedOutputGroups();
			for (ChartedOutputGroup chartedOutputGroup : chartedOutputGroups) {
				Collection<OutputDescr> values = chartedOutputGroup.getComponents().values();
				for (OutputDescr outputDescr : values) {
					if (outputDescr.getType().equals(Type.CONSTANT)) {
						normOutputs.add(outputDescr.getConstant().getValue(targetStock));
						outputDescr.setOutputIndex(normOutputs.size()-1);
					}
				}
			}

			((EventDefDescriptorDynamic) eventInfoOpsCompoOperationHolder.getEventDefDescriptor()).setChartedOutputGroups(chartedOutputGroups, null);

			//Build
			return buildSortedMap(normOutputs, fullDateSet);

		} catch (Exception e) {
			LOGGER.warn(e,e);
		}

		return new TreeMap<>();
	}

	private SortedMap<Date, double[]> buildSortedMap(List<Object> normOutputs, SortedSet<Date> fullDateSet) {
		SortedMap<Date, double[]> calculationOutput = new TreeMap<>();
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
		return eventInfoOpsCompoOperationHolder;
	}

	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

	@Override
	public Integer getStartShift() {
		return 0;
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Boolean isIdemPotent() {
		return eventInfoOpsCompoOperationHolder.isIdemPotent();
	}

}
