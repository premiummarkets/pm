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
import java.util.Set;
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
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.ErrorException;
import com.finance.pms.events.calculation.IndicatorsOperator;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TargetStockInfo.Output;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
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
			throws WarningException {

		super(observers);
		this.eventInfoOpsCompoOperationHolder = (EventInfoOpsCompoOperation) eventInfo;

		//Adjust start
		Integer startShiftOverrideValue = ((NumberValue) this.eventInfoOpsCompoOperationHolder.getOperands().get(3).getParameter()).getNumberValue().intValue();
		int operationStartDateShift = 0;
		if (!startShiftOverrideValue.equals(-1)) {
			operationStartDateShift = startShiftOverrideValue;
		} else {
			operationStartDateShift = 0; //this.eventInfoOpsCompoOperationHolder.operandsRequiredStartShift(targetSock, thisParentStartShift);
		}
		Calendar adjustedStartCal = Calendar.getInstance();
		adjustedStartCal.setTime(startDate);
		adjustedStartCal.add(Calendar.DAY_OF_YEAR, -operationStartDateShift);
		LOGGER.info(
				this.eventInfoOpsCompoOperationHolder.getReference() + ". " +
				"Requested start: " + startDate + ", adjusted start: " + adjustedStartCal.getTime() + " with shift: " + operationStartDateShift);

		//Adjust end
		Date lastQuote = stock.getLastQuote();
		Date adjustedEndDate;
		if (lastQuote.before(endDate)) {
			adjustedEndDate = lastQuote;
		} else {
			adjustedEndDate = endDate;
		}
		LOGGER.info(this.eventInfoOpsCompoOperationHolder.getReference() + ". Requested end: " + endDate + ", adjusted end: " + adjustedEndDate + ", last quote: " + lastQuote);

		//Target stock instance
		this.targetStock = new TargetStockInfo(analyseName, this.eventInfoOpsCompoOperationHolder, stock, adjustedStartCal.getTime(), adjustedEndDate);

	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) throws WarningException, ErrorException {

		SortedMap<EventKey, EventValue> eData = new TreeMap<>();
		if (eventInfoOpsCompoOperationHolder.getFormulae() == null) return eData;

		eventInfoOpsCompoOperationHolder.setOperandsParams(null, null, null, null, new StringValue(eventListName));
		
		try {
			
			CalculateThreadExecutor.getSemaphoreInstance().acquire();
			
			long startTime = new Date().getTime();
			LOGGER.info("STARTING: " + eventInfoOpsCompoOperationHolder.getReference() + " for " + targetStock + " starting at " + startTime);
			EventMapValue eventMapValue = (EventMapValue) ((EventInfoOpsCompoOperation) eventInfoOpsCompoOperationHolder.clone()).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") " + eventInfoOpsCompoOperationHolder.getReference(), 0);
			long finishTime = new Date().getTime();
			LOGGER.info("ENDING: " + eventInfoOpsCompoOperationHolder.getReference() + " for " + targetStock + " finishing at " + finishTime + ". Time elapsed: " + (finishTime-startTime)/1000 + " seconds.");
	
			SortedMap<EventKey, EventValue> returnedEvents = eventMapValue.getEventMap();

			//Finding duplicates and invalid dates
			List<Date> validQuotationsDates = new ArrayList<>(
					QuotationsFactories.getFactory()
					.buildExactSMapFromQuotationsClose(quotations, quotations.getFirstDateShiftedIdx(), quotations.getLastDateIdx()).keySet());
			EventKey previousKey = null;
			SortedSet<EventKey> duplicates = new TreeSet<EventKey>();
			SortedSet<EventKey> invalids = new TreeSet<EventKey>();
			for (EventKey currentKey : returnedEvents.keySet()) {

				Date previousKeyDate = (previousKey == null)? null : previousKey.getDate();
				Date currentKeyDate = currentKey.getDate();
				
				if (currentKeyDate.compareTo(validQuotationsDates.get(0)) >= 0 && !validQuotationsDates.contains(currentKeyDate)) {
					LOGGER.warn(currentKeyDate + " (" + currentKeyDate.getClass() + ") was not found in " + validQuotationsDates); 
					invalids.add(currentKey);
				} else {
					if (previousKeyDate != null && previousKeyDate.compareTo(currentKeyDate) == 0) {
						duplicates.add(currentKey);
						duplicates.add(previousKey);
					}
					previousKey = currentKey;
				}
				
			}
			if (!invalids.isEmpty()) {
				throw new WarningException("Invalid event dates for customised calculator '" + this.getEventDefinition().getEventReadableDef() + "': " + invalids);
			}
			if (!duplicates.isEmpty()) {
				throw new WarningException("Opposite simultaneous event values for customised calculator '" + this.getEventDefinition().getEventReadableDef() + "': " + duplicates);
			}
			
			eData.putAll(returnedEvents);
			
		} catch (Exception e) {
			throw new ErrorException(e.toString(), e);
		} finally {
			CalculateThreadExecutor.getSemaphoreInstance().release();
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

			List<Output> gatheredOutputs = targetStock.getGatheredChartableOutputsAsync();

			List<Object> normOutputs = new ArrayList<>();
			SortedSet<Date> fullDateSet = new TreeSet<>();

			//Add NumericableMapValue outputs
			for (Output output : gatheredOutputs) {
				Value<?> outputData = output.getOutputData();
				if (outputData != null) {
					SortedMap<Date, Double> data;
					if (outputData instanceof NumericableMapValue) {
						data = ((NumericableMapValue) outputData).getValue(targetStock);
					} else {
						data = new TreeMap<>();
					}
					normOutputs.add(data);
					fullDateSet.addAll(data.keySet());
				}
			}

			//Add Constants outputs
			List<ChartedOutputGroup> chartedOutputGroups = targetStock.getChartedOutputGroupsAsync();
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
		Set<QuotationDataType> requieredStockData = this.eventInfoOpsCompoOperationHolder.getRequiredStockData();
		return ValidityFilter.getFilterFor(requieredStockData);
	}

	@Override
	public Boolean isIdemPotent() {
		return eventInfoOpsCompoOperationHolder.isIdemPotent(targetStock);
	}

}
