package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class UnarySum extends ArithmeticUnaryOperation {
	

	public UnarySum() {
		super("sumUnary_", "Summation of a series with a number", new DoubleMapOperation(), new NumberOperation());
	}

	public UnarySum(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Double twoOperandsOp(Double op0, Double op1) {
		return op0+op1;
	}

}
