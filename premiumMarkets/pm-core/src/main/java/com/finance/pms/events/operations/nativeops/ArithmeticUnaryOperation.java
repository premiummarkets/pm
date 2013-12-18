package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({UnaryProduct.class, UnarySum.class, UnaryDivision.class})
public abstract class ArithmeticUnaryOperation extends DoubleMapOperation {
	
	protected ArithmeticUnaryOperation() {
		super();
	}

	public ArithmeticUnaryOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public ArithmeticUnaryOperation(String reference, String description, Operation ...operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(0)).getValue(targetStock);
		Double unaryOperand = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();
		
		DoubleMapValue outputs = new  DoubleMapValue();
		for (Date date : data.keySet()) {
			outputs.getValue(targetStock).put(date, twoOperandsOp(data.get(date), unaryOperand));
		}
		
		return outputs;
	}
	
	public abstract Double twoOperandsOp(Double op0, Double op1);
	
	@Override
	public int operationStartDateShift() {
		return getOperands().get(0).operationStartDateShift();
	}

}
