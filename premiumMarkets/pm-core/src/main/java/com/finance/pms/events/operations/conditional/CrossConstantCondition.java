package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.scoring.functions.LeftShifter;

@XmlSeeAlso({CrossUpConstantCondition.class, CrossDownConstantCondition.class})
public abstract class CrossConstantCondition extends Condition<Double> {
	
	public CrossConstantCondition() {
		super();
	}

	public CrossConstantCondition(String reference, String description) {
		super(reference, description, new DoubleOperation("threshold"), new DoubleOperation("date shift"), new DoubleMapOperation("historical data input"));
	}
	
	public CrossConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double constant = ((DoubleValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Integer shift = ((DoubleValue) inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-shift.intValue(), false, false);
		SortedMap<Date, Double> rightShiftedData = rightShifter.shift(data);
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : data.keySet()) {
			Double current = data.get(date);
			Double previous = rightShiftedData.get(date);
			if (previous != null && !previous.isNaN()) {
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(previous, current, constant);
				if (conditionCheck != null) {
					outputs.getValue(targetStock).put(date, conditionCheck);
				}
			}
		}
		
		return outputs;
	}
}
