package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;

public abstract class CmpConstantCondition extends Condition<Double> implements OnThresholdCondition {
	

	protected CmpConstantCondition(String reference, String description) {
		super(reference, description, new DoubleOperation("threshold"), new DoubleMapOperation("historical data input"));
	}

	public CmpConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double constant = ((DoubleValue) inputs.get(0)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : data.keySet()) {
			Double current = data.get(date);
			@SuppressWarnings("unchecked")
			Boolean conditionCheck = conditionCheck(current, constant);
			if (conditionCheck != null) {
				outputs.getValue(targetStock).put(date, conditionCheck);
			}
		}
		
		return outputs;
	}

	@Override
	public int thresholdPosition() {
		return 0;
	}
	
	@Override
	public int mainPosition() {
		return 1;
	}
	
	

}
