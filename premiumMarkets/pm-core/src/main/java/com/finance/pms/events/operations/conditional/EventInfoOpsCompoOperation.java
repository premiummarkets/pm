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
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.FormulaUtils;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.EventDefDescriptorDynamic;
import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.EventsAnalyser;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
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
	private final EventDefDescriptorDynamic eventDefDescriptor;

	public EventInfoOpsCompoOperation() {
		super("operationscompositionner","operationscompositionner", 
				new ArrayList<Operation>(
						Arrays.asList(new Operation[]{new Condition<Object>("bullishCondition"), new Condition<Object>("bearishCondition"), new Condition<Object>("alsoDisplay"), new NumberOperation("startShiftOverride"), new StringOperation("eventListName")})));
		this.eventDefDescriptor = new EventDefDescriptorDynamic();
	}

	public EventInfoOpsCompoOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}

	@Override
	public EventDataValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		BooleanMapValue bullishMap = ((BooleanMapValue)inputs.get(0));
		BooleanMapValue bearishMap = ((BooleanMapValue)inputs.get(1));
		//BooleanMapValue alsoDisplay = ((BooleanMapValue)inputs.get(2));
		//NumberValue startShiftOverride = ((NumberValue)inputs.get(3));
		StringValue eventListName = ((StringValue) inputs.get(4));

		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
		for (Date date : bullishMap.getValue(targetStock).keySet()) {
			if (bullishMap.getValue(targetStock).get(date)) {
				ParameterizedEventKey iek = new ParameterizedEventKey(date, this, EventType.BULLISH);
				EventValue iev = new StandardEventValue(date, this, EventType.BULLISH, eventListName.getValue(targetStock));
				edata.put(iek, iev);
			}
		}
		for (Date date : bearishMap.getValue(targetStock).keySet()) {
			if (bearishMap.getValue(targetStock).get(date)) {
				ParameterizedEventKey iek = new ParameterizedEventKey(date, this, EventType.BEARISH);
				EventValue iev = new StandardEventValue(date, this, EventType.BEARISH, eventListName.getValue(targetStock));
				edata.put(iek, iev);
			}
		}

		///Finalizing this IndicatorOperator using its operands calculations
		try {
			//Analysis of above event in light of previously calculated ops
			if (edata.isEmpty()) {
				LOGGER.warn("No event data found. The up stream main operation has failed for "+targetStock.getStock()+" in "+this.getReference()+"/"+this.getOperationReference());
			} else {
				edata = targetStock.analyseEvents(edata);
			}

			//Retrieve exportBaseFileName of the main operation
			List<ChartedOutputGroup> chartedOutputGroups = targetStock.getChartedOutputGroups();
			if (chartedOutputGroups.isEmpty()) {
				LOGGER.warn("No charted group found. The up stream main operation has failed for "+targetStock.getStock()+" in "+this.getReference()+"/"+this.getOperationReference());
			} else {
				EventsAnalyser eventsAnalyser = targetStock.getOutputAnalysers().get(chartedOutputGroups.get(0).getThisReference());
				if (eventsAnalyser != null) this.eventDefDescriptor.setExportBaseFileName(eventsAnalyser.getEgFileBaseName());
			}
		} catch (Exception e) {
			LOGGER.warn(e, e);
		}

		return new EventDataValue(edata);
	}

	@Override
	public String info() {
		return getEventReadableDef();
	}

	// TODO 
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
	public String tootTip() {
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
	public int operationStartDateShift() {
		//return Math.max(getOperands().get(0).operationStartDateShift(), getOperands().get(1).operationStartDateShift());
		int maxDateShift = 0;
		for (Operation operand : getOperands()) {
			maxDateShift = Math.max(maxDateShift, operand.operationStartDateShift());
		}
		return maxDateShift;
	}

	@Override
	public Integer getEventOccWeight() {
		return 1;
	}

	@Override
	public void invalidateOperation(String analysisName) {
		TunedConfMgr.getInstance().deleteEventsDirtyConfs(analysisName, this);
	}

}