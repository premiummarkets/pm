package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({Product.class, Sum.class, Division.class, Subtraction.class})
public abstract class ArithmeticOperation extends DoubleMapOperation {
	
	protected ArithmeticOperation() {
		super();
	}

	public ArithmeticOperation(String reference, String description, ArrayList<DoubleMapOperation> operands) {
		super(reference, description, operands);
	}

	public ArithmeticOperation(String reference, String description, DoubleMapOperation ...operands) {
		this(reference, description, new ArrayList<DoubleMapOperation>(Arrays.asList(operands)));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		if (inputs.size() == 0) return new DoubleMapValue();
		if (inputs.size() == 1) return (DoubleMapValue) inputs.get(0);
		@SuppressWarnings("unchecked") List<Value<SortedMap<Date, Double>>> checkedInputs = (List<Value<SortedMap<Date, Double>>>) inputs;
		
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (Value<SortedMap<Date, Double>> input : checkedInputs) {
			fullKeySet.addAll(input.getValue(targetStock).keySet());
		}
		
		DoubleMapValue outputs = new  DoubleMapValue();
		for (Date date : fullKeySet) {
			Double leftOperand = checkedInputs.get(0).getValue(targetStock).get(date);
			for (int i=1; i < checkedInputs.size(); i++) {
				Value<SortedMap<Date, Double>> input = checkedInputs.get(i);
				Double rightOperand = input.getValue(targetStock).get(date);
				leftOperand = twoOperandsOp(leftOperand, rightOperand);
			}
			outputs.getValue(targetStock).put(date, leftOperand);
		}
		
		return outputs;
	}
	
	public abstract Double twoOperandsOp(Double op0, Double op1);
	
	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (Operation operand : getOperands()) {
			maxDateShift = Math.max(maxDateShift, operand.operationStartDateShift());
		}
		return maxDateShift;
	}

}
