package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class Subtraction extends ArithmeticOperation {

	
	public Subtraction() {
		super("subtraction_", "Subtraction of two historical data series",new DoubleMapOperation("minuend"), new DoubleMapOperation("subtrahend"));
	}
	
	public Subtraction(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Double twoOperandsOp(Double op0, Double op1) {
		if ((op0 == null || op0.isNaN()) || (op1 == null || op1.isNaN())) return Double.NaN;
//		if  (op0 == null || op0.isNaN()) op0 = 0d;
//		if  (op1 == null || op1.isNaN()) op0 = 0d;
		return op0-op1;
	}

}
