package com.finance.pms.events.operations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

public class StackElement {

	private int stackDepth;
	
	private String stockSymbol;
	private int parentRequiredStartShift;
	private int operationOperandsRequiredStartShift;
	private Date calcFrom;
	private Date calcTo;
	
	private String opReference;
	private String opOperationReference;
	private String opOutputSelector;
	private String opReferenceAsOperand;
	
	public StackElement(
			int stackDepth, 
			String stockSymbol, int parentRequiredStartShift, int operationOperandsRequiredStartShift, Date calcFrom, Date calcTo, 
			String opReference, String opOperationReference, String opOutputSelector, String opReferenceAsOperand) {
		super();
		this.stackDepth = stackDepth;
		this.stockSymbol = stockSymbol;
		this.parentRequiredStartShift = parentRequiredStartShift;
		this.operationOperandsRequiredStartShift = operationOperandsRequiredStartShift;
		this.calcFrom = calcFrom;
		this.calcTo = calcTo;
		this.opReference = opReference;
		this.opOperationReference = opOperationReference;
		this.opOutputSelector = opOutputSelector;
		this.opReferenceAsOperand = opReferenceAsOperand;
	}
	
	public Boolean isUserOp() {
		return !opReference.equals(opOperationReference);
	}
	
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String tabs = IntStream.range(0, stackDepth).mapToObj(i -> "\t").reduce("", (r,e) -> r + e);
		String shortOutputReference = "[" + opReference + " (is " + opOperationReference + ":" + opOutputSelector + ") as " + opReferenceAsOperand + "]";
		return "Call Stack (" + stockSymbol + "): " + tabs + "=> s" + parentRequiredStartShift + ": " + shortOutputReference + 
				" with s" +  operationOperandsRequiredStartShift + " and d" + stackDepth + 
				" from " + df.format(calcFrom) + " to " + df.format(calcTo);
	}

}
