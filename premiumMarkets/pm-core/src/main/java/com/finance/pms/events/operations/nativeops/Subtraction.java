package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class Subtraction extends ArithmeticOperation {

	
	public Subtraction() {
		super("substraction_", "Substraction of two historical data series",new DoubleMapOperation("minuend"), new DoubleMapOperation("subtrahend"));
	}
	
	public Subtraction(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Double twoOperandsOp(Double op0, Double op1) {
		return op0-op1;
	}

}
