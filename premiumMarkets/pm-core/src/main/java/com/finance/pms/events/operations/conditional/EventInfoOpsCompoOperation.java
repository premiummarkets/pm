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
package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.FormulaUtils;
import com.finance.pms.events.calculation.parametrizedindicators.EventDefDescriptorDynamic;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.EventMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.talib.dataresults.StandardEventValue;
/**
 * This could be called IndicatorsCompositionnerBullBearSwitchOperation (or EventInfoBullBearSwitchOperation)
 * The EventInfoOpsCompoOperation is a specific type of operation that generates bullish and bearish events to be used in the UI.
 * As an operation, the EventInfoOpsCompoOperation takes other operations as operands and is calculated using a specific IndicatorsCompositionner calculator, the ParameterizedIndicatorsCompositioner.
 * An EventInfoOpsCompoOperation is defined through the UI via formula files.
 * EventInfoOpsCompoOperation unlike other operations can't be reused for further composition or parameterization.
 * Hence, every indicator created in the ./userparametrised/indicator folder will instantiate an EventInfoOpsCompoOperation which will be calculated (run) by the ParameterizedIndicatorsCompositioner.
 */
@XmlRootElement
public class EventInfoOpsCompoOperation extends EventMapOperation implements EventInfo {

	private static MyLogger LOGGER = MyLogger.getLogger(EventInfoOpsCompoOperation.class);
	
	@XmlTransient
	private EventDefDescriptorDynamic eventDefDescriptor; //It is assumed that this class is NOT a singleton.
	boolean isKeepEvents = true;

	public EventInfoOpsCompoOperation() {
		this("operationscompositionner", "operationscompositionner");
	}

	public EventInfoOpsCompoOperation(String reference, String description) {
		super(reference, description,
				new ArrayList<>(Arrays.asList(
						new Condition<>("bullishCondition"),
						new Condition<>("bearishCondition"),
						new Condition<>("alsoDisplay"),
						new NumberOperation("startShiftOverride"),
						new StringOperation("eventListName")))
				);
		this.eventDefDescriptor = new EventDefDescriptorDynamic();
	}

	public EventInfoOpsCompoOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}

	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
				
		BooleanMapValue bullishMapValue = ((BooleanMapValue)inputs.get(0));
		BooleanMapValue bearishMapValue = ((BooleanMapValue)inputs.get(1));
		//BooleanMapValue alsoDisplay = ((BooleanMapValue)inputs.get(2)); //not used for calculation
		//NumberValue startShiftOverride = ((NumberValue)inputs.get(3));  //not implemented
		StringValue eventListName = ((StringValue) inputs.get(4));

		SortedMap<Date, Boolean> bullishMap = bullishMapValue.getValue(targetStock);
		SortedMap<Date, Boolean> bearishMap = bearishMapValue.getValue(targetStock);

		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();

		String eventListNameValue = eventListName.getValue(targetStock);
		if (eventListNameValue.equals("FROM PARENT")) eventListNameValue = targetStock.getAnalysisName();

		List<Date> inconsistent = new ArrayList<>();
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		fullKeySet.addAll(bullishMap.keySet());
		fullKeySet.addAll(bearishMap.keySet());

		String resultHint = this.resultHint(targetStock, newCallerStack(targetStock));
		for (Date date : fullKeySet) {

			EventType dateEventType = EventType.NONE;
			Boolean isBullish = bullishMap.get(date);
			Boolean isBearish = bearishMap.get(date);

			if (isBullish != null && isBullish && isBearish != null && isBearish) {
				inconsistent.add(date);
			}
			if (isBullish != null && isBullish) dateEventType = EventType.BULLISH;
			if (isBearish != null && isBearish) dateEventType = EventType.BEARISH; //Bearish prevails in case of inconsistency.

			ParameterizedEventKey iek = new ParameterizedEventKey(date, this, dateEventType);
			EventValue iev = new StandardEventValue(date, this, dateEventType, resultHint, eventListNameValue);
			edata.put(iek, iev);

		}

		if (!inconsistent.isEmpty()) LOGGER.warn("Opposite simultaneous event values for customised calculator '" + this.getReference() + "' at: " + inconsistent);
		if (edata.isEmpty()) LOGGER.warn("No event data found. The up stream main operation has failed for " + targetStock.getStock() + " in " + this.getReference() + "/" + this.getOperationReference());
		if (targetStock.getChartedOutputGroups().isEmpty() && isDisplay) LOGGER.warn("No charted group found. The up stream main operation may have failed for " + targetStock.getStock() + " in " + this.getReference() + "/" + this.getOperationReference());
		
		return new EventMapValue(edata, false);
	}

	@Override
	public String info() {
		return getEventReadableDef();
	}

	//TODO 
	public Boolean getIsContinous() {
		return EventDefinition.PARAMETERIZED.getIsContinous();
	}

	public EventDefDescriptor getEventDefDescriptor() {
		return eventDefDescriptor;
	}

	@Override
	public String getEventReadableDef() {
		return getReference();
	}

	@Override
	public Integer getEventDefId() {
		return EventDefinition.PARAMETERIZED.getEventDefId();
	}

	@Override
	public String getEventDefinitionRef() {
		return getReference();
	}

	@Override
	public boolean equals(EventInfo obj) {
		return this.getEventDefinitionRef().equals(obj.getEventDefinitionRef());
	}

	public int hashCode() {
		return getEventDefinitionRef().hashCode();
	}

	public int compareTo(EventInfo o) {
		return this.getEventDefinitionRef().compareTo(o.getEventDefinitionRef());
	}

	@Override
	public void setReference(String reference) {
		super.setReference(reference);
		this.eventDefDescriptor.setDescriptorReference(reference);
		if (this.getFormulae() != null) {
			String indentedFormula = FormulaUtils.indentOperationFormula(this.getFormulae(), 0)[1];
			this.eventDefDescriptor.setBullishDescription(FormulaUtils.bullishClause(indentedFormula));
			this.eventDefDescriptor.setBearishDescription(FormulaUtils.bearishClause(indentedFormula));
			this.eventDefDescriptor.setAlsoDisplayDescription(FormulaUtils.alsoDisplayClause(indentedFormula));
		} else {
			this.eventDefDescriptor.setBullishDescription("No description available");
			this.eventDefDescriptor.setBearishDescription("No description available");
			this.eventDefDescriptor.setAlsoDisplayDescription("No description available");
		}
	} 

	@Override
	public String toolTip() {
		if (eventDefDescriptor != null) {
			return getEventReadableDef()+" :\n\n" +
					eventDefDescriptor.getBullishDescription()+"\n\n"+
					eventDefDescriptor.getBearishDescription()+"\n\n"+
					eventDefDescriptor.getAlsoDisplayDescription();
		} else {
			return getEventReadableDef();
		}
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		 
		Boolean isIdempotent = isIdemPotent(targetStock);
		Boolean forbidEventsOverride = isNoOverrideDeltaOnly(targetStock);
		
		boolean isAlterableOverridable = !isIdempotent && !forbidEventsOverride;
		
		if (isAlterableOverridable) {
			//EventsResources.getInstance().crudDeleteEventsForStock(targetStock.getStock(), targetStock.getAnalysisName(), new EventInfo[] {this});
			invalidateAllNonIdempotentOperands(targetStock.getAnalysisName(), targetStock, Optional.empty());
		}
		
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> optStock, Optional<String> userOperationName) {
		try {
			if (optStock.isPresent()) {
				Stock stock = optStock.get().getStock();
				EventsResources.getInstance().crudDeleteForciblyEventsForStock(stock, analysisName, this);
			} else {
				EventsResources.getInstance().crudDeleteForciblyEventsForIndicators(analysisName, this);
			}
		} catch (Exception e) {
			LOGGER.warn("Delete of events didn't proceed: " + e);
		}
	}
	
	@Override
	public Optional<String> calculationStatus(TargetStockInfo targetStock, List<StackElement> callStack) {
		Optional<TunedConf> tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(targetStock.getStock(), targetStock.getAnalysisName(), targetStock.getEventInfoOpsCompoOperation());
		return Optional.of(this.getReference() + ": " + tunedConf.map(tc -> tc.toMessage()).orElse("no events recorded"));
	}

	@Override
	public Object clone() {
		EventInfoOpsCompoOperation clone = (EventInfoOpsCompoOperation) super.clone();
		clone.eventDefDescriptor = this.eventDefDescriptor;
		return clone;
	}

	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		return getReference().substring(0,1) + getReference().chars()
				.filter(c -> Character.isUpperCase(c))
				.mapToObj(cu -> (char) cu)
				.reduce("", (r, e) -> r + e, (a, b) -> a + b);
	}
	
	@Override
	public String toFormulae(TargetStockInfo targetStock) {
		return getReference() + "()";
	}

	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

	@Override
	public void interruptEventInfo() throws Exception {
		this.interrupt();
	}
	
	public boolean isKeepEvents() {
		return isKeepEvents;
	}

	public void setKeepEvents(boolean isKeepEvents) {
		this.isKeepEvents = isKeepEvents;
	}

	@Override
	public String resultHint(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		callStack = addThisToStack(callStack, 0, targetStockInfo);
		return 
			"bull: " + getOperands().get(0).resultHint(targetStockInfo, callStack) + "\n" +
			"bear: " + getOperands().get(1).resultHint(targetStockInfo, callStack);
	}
	
	
	
}
