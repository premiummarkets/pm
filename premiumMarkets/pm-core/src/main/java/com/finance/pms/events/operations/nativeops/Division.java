package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class Division extends ArithmeticOperation {

	public Division() {
		super("division_", "Division of two historical data series", new DoubleMapOperation("dividende"), new DoubleMapOperation("divisor"));
	}
	
	public Division(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Double twoOperandsOp(Double op0, Double op1) {
		if (op1 == 0) return Double.NaN;
		return op0/op1;
	}

}
