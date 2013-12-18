package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class UnaryDivision extends ArithmeticUnaryOperation {

	public UnaryDivision() {
		super("divisionUnary_", "Division of a series by a number", new DoubleMapOperation("dividend"), new NumberOperation("divisor"));
	}
	
	public UnaryDivision(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Double twoOperandsOp(Double op0, Double op1) {
		if (op1 == 0) return Double.NaN;
		return op0/op1;
	}

}
