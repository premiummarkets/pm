package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
public class AndDoubleMapCondition extends BooleanDoubleMapCondition {
	
	private Boolean exactDataSet;

	private AndDoubleMapCondition() {
		super("and", "Compare two boolean time series over time and is true when all are true and present.");
	}
	
	public AndDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}
	
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		Boolean lenient = Boolean.valueOf(((StringValue)inputs.get(0)).getValue(targetStock));
		exactDataSet = !lenient;
		return super.calculate(targetStock, inputs.subList(1, inputs.size()));
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Boolean>... ops) {
		for (Comparable<Boolean> op : ops) {
			if (op.compareTo(Boolean.TRUE) != 0) return false;
		}
		return true;
	}
	
	

	@Override
	protected Boolean shortcutUnary() {
		return true;
	}

	@Override
	protected Boolean exactDataSet() {
		return exactDataSet;
	}

}
