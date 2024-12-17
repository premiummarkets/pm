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
package com.finance.pms.events.operations.nativeops.ta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.EventDefDescriptorStatic;
import com.finance.pms.events.calculation.TalibIndicatorsOperator;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.EventMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumbererOperation;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.TalibException;

/**
 * A like the EventInfoOpsCompoOperation, the TalibIndicatorsCompositionerGenericOperation is a specific type of operation that generates bullish and bearish events to be used in the UI.
 * The difference is that instead of relying on a formula defined by the user, it finds its calculation definition within classes descendant of TalibIndicatorsOperator.
 * However, as this is not an Event Info itself (FIXME??), it need to be wrapped within a formula and hence an EventInfoOpsCompoOperation in order to be processed eventually using the ParameterizedIndicatorsOperator.
 * The wrapping EventInfoOpsCompoOperation must be eponym for this to fully work.
 * //FIXME??? The problem is that this kind of 'EventInfo' is actually an operation (with variable operands) and this causes problems because of the indicator V operations segregation (chartable V composed)
 * //FIXME indeed, indicator definition doesn't support variables operands.
 * @author guil
 */
//FIXME rename TalibIndicatorsCompositionerBullBearSwitchOperation
public class TalibIndicatorsCompositionerGenericOperation extends EventMapOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicatorsCompositionerGenericOperation.class);

	private final Class<? extends TalibIndicatorsOperator> calculatorClass;

	public TalibIndicatorsCompositionerGenericOperation(
			String reference, String description,
			Class<? extends TalibIndicatorsOperator> calculatorClass, List<String> inConstantsNames) throws Exception {
		super(reference, description);

		this.calculatorClass = calculatorClass;

		ArrayList<Operation> overridingOperands = new ArrayList<Operation>();
		for (String inConstantName : inConstantsNames) {
			NumberOperation constant = new NumberOperation("number", inConstantName, inConstantName, null);
			overridingOperands.add(constant);
		}
		this.setOperands(overridingOperands);

	}

	/**
	 * @return True when bullish and False when bearish
	 */
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		EventMapValue buySellEventsMainOutput = new EventMapValue();
		try {

			//XXX getting back the eponym EventInfoOpsCompoOperation indicator from the operation name (@see TalibIndicatorsCompositionerGenericOperation.class above)
			EventInfo thisEventInfoOpsCompoOperation = (EventInfo) ((ParameterizedIndicatorsBuilder) SpringContext.getSingleton().getBean("parameterizedIndicatorsBuilder")).getThisParserCompliantUserEnabledOperations().get(this.getReference());
			if (thisEventInfoOpsCompoOperation == null) 
				throw new Exception(this.getReference() + " is a paremterized operation wich outputs events (like an indicator) and has for input the generic indicator composionner: " + this.getOperationReference() +
						". However, it needs to be backed up by your own EPONYM indicator as the latter can't be paramterized through the editor. " +
						this.getReference() + " is not defined as an indicator. Make sure operation and indicator have the same name.");
			TalibIndicatorsOperator calculator = calculatorClass.getConstructor(EventInfo.class).newInstance(thisEventInfoOpsCompoOperation);

			List<Integer> inputConstants = inputs.stream().map(i -> ((NumberValue) i).getValue(targetStock).intValue()).collect(Collectors.toList());
			calculator.genericInit(inputConstants.toArray(new Integer[inputConstants.size()]));

			Date shiftedStartDate = targetStock.getStartDate(thisStartShift);
			Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(
					targetStock.getStock(), shiftedStartDate, targetStock.getEndDate(), 
					true, targetStock.getStock().getMarketValuation().getCurrency(), 
					calculator.getStartShift(), calculator.quotationsValidity());

			//Events is the only functional output for this operation => boolean
			SortedMap<EventKey, EventValue> eventsFor = calculator.calculateEventsFor(quotations, targetStock.getAnalysisName());
			buySellEventsMainOutput = new EventMapValue(eventsFor, true);

			//Adding indicator outputs for charting
			EventDefinition eventDefinition = (EventDefinition) calculator.getEventDefinition();
			EventDefDescriptorStatic eventDefDescriptor = eventDefinition.getEventDefDescriptor();
			if (eventDefDescriptor == null) {
				throw new NotImplementedException(
						"No static descriptor implemented for " + eventDefinition + ". "
						+ "This operation compositioner can't be reflected. Please implement.");
			}

			SortedMap<Date, double[]> calculationOutput = calculator.calculationOutput();

			int i = 0;
			for (String outputName : eventDefDescriptor.descriptionMap().keySet()) {
				SortedMap<Date, Double> output = new TreeMap<>();
				transOutput(calculationOutput, output, i);

				Type outputType = eventDefDescriptor.descriptionMap().get(outputName);
				String fullOutputName = outputName + " of " + targetStock.getStock().getFriendlyName();
				buySellEventsMainOutput.getAdditionalOutputs().put(fullOutputName, new DoubleMapValue(output));
				buySellEventsMainOutput.getAdditionalOutputsTypes().put(fullOutputName, (outputType.equals(Type.CONSTANT))?Type.MULTISIGNAL:outputType);

				i++;
			}

		}
		catch (TalibException | NoQuotationsException e) {
			LOGGER.warn(this.getReference() + " : " + e, true);
		}
		catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return buySellEventsMainOutput;
	}

	protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest, int originIdx) {
		for (Date date : origin.keySet()) {
			dest.put(date, origin.get(date)[originIdx]);
		}
	}

	@Override
	//Approximate calculation based on the different parameters assumed as periods passed to this operation.
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		Integer thisOperationStartShift = getOperands().stream()
				.filter(o -> o instanceof NumberOperation || o instanceof NumbererOperation)
				.map(o -> ((NumberValue)o.getOrRunParameter(targetStock, thisCallStack).orElse(new NumberValue(0.0))).getValue(targetStock).intValue())
				.reduce(0, (r, e) -> r + e);

		return (thisOperationStartShift);
	}

	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
		//Nothing
		//  This is not an EventInfo but an operation although it can be composed within one of Parameterized Indicator -> Event to delete
		//  And this doesn't have any storage calculation result to wipe out.
	}

}
