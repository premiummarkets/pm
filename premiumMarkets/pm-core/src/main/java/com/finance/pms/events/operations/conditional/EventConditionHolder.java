package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.FormulaUtils;
import com.finance.pms.events.calculation.parametrizedindicators.EventDefDescriptorDynamic;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.talib.dataresults.StandardEventValue;

@XmlRootElement
public class EventConditionHolder extends Operation implements EventInfo {
	
	@XmlTransient
	private EventDefDescriptorDynamic eventDefDescriptor;

	public EventConditionHolder() {
		super("eventconditionholder","eventconditionholder", new ArrayList<Operation>(Arrays.asList(new Operation[]{new Condition<Object>("bullishCondition"), new Condition<Object>("bearishCondition"), new StringOperation("eventListName")})));
		eventDefDescriptor = new EventDefDescriptorDynamic();
	}

	public EventConditionHolder(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		eventDefDescriptor = new EventDefDescriptorDynamic();
	}

	@Override
	public EventDataValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		BooleanMapValue bullishMap = ((BooleanMapValue)inputs.get(0));
		BooleanMapValue bearishMap = ((BooleanMapValue)inputs.get(1));
		StringValue eventListName = ((StringValue) inputs.get(2));
		
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
		for (Date date :  bullishMap.getValue(targetStock).keySet()) {
			if (bullishMap.getValue(targetStock).get(date)) {
				ParameterizedEventKey iek = new ParameterizedEventKey(date, this, EventType.BULLISH);
				EventValue iev = new StandardEventValue(date, this, EventType.BULLISH, eventListName.getValue(targetStock));
				edata.put(iek, iev);
			}
		}
		for (Date date : bearishMap.getValue(targetStock).keySet()) {
			if (bearishMap.getValue(targetStock).get(date)) {
				ParameterizedEventKey iek = new ParameterizedEventKey(date, this, EventType.BEARISH);
				EventValue iev = new StandardEventValue(date, this, EventType.BEARISH,  eventListName.getValue(targetStock));
				edata.put(iek, iev);
			}
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
		if (this.getFormula() != null) {
			String indentedFormula = FormulaUtils.indentOperationFormula(this.getFormula(), 0)[1];
			this.eventDefDescriptor.setBullishDescription(FormulaUtils.bullishClause(indentedFormula));
			this.eventDefDescriptor.setBearishDescription(FormulaUtils.bearishClause(indentedFormula));
		} else {
			this.eventDefDescriptor.setBullishDescription("No description available");
			this.eventDefDescriptor.setBearishDescription("No description available");
		}
	} 

	@Override
	public String tootTip() {
		if (eventDefDescriptor != null) {
			return getEventReadableDef()+" :\n\n" +
					eventDefDescriptor.getBullishDescription()+"\n\n"+
					eventDefDescriptor.getBearishDescription();
		} else {
			return getEventReadableDef();
		}
	}

	@Override
	public int operationStartDateShift() {
		return Math.max(getOperands().get(0).operationStartDateShift(), getOperands().get(1).operationStartDateShift());
	}

	@Override
	public Integer getEventOccWeight() {
		return 1;
	}
	
}
