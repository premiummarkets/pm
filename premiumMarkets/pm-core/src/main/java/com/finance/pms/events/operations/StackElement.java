package com.finance.pms.events.operations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StackElement {
	
	public static String toString(List<StackElement> callStack) {
		return callStack.stream().map(s -> s.toString()).reduce((a,s) -> a + "\n" + s).orElse("Empty stack");
	}
	
	public static String toShortString(List<StackElement> callStack) {
		return (callStack.size() > 0)? callStack.get(0) + " ... " + callStack.get(callStack.size()-1):"None";
	}

	private int stackDepth;

	private String stockSymbol;
	private int parentRequiredStartShift;
	private Date calcFrom;
	private Date calcTo;
	
	private String opReference;
	private String opOperationReference;
	private String opOutputSelector;
	private String opReferenceAsOperand;
	
	public StackElement(
			int stackDepth, 
			String stockSymbol, int parentRequiredStartShift, Date calcFrom, Date calcTo, 
			String opReference, String opOperationReference, String opOutputSelector, String opReferenceAsOperand) {
		super();
		this.stackDepth = stackDepth;
		this.stockSymbol = stockSymbol;
		this.parentRequiredStartShift = parentRequiredStartShift;
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
	
	public Boolean isMainConditionElement() {
		return opReferenceAsOperand != null && (opReferenceAsOperand.equals("bearishCondition") || opReferenceAsOperand.equals("bullishCondition"));
	}
	
	public int getStackDepth() {
		return stackDepth;
	}
	
	public String getOpReference() {
		return opReference;
	}
	
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		//String tabs = IntStream.range(0, stackDepth).mapToObj(i -> "\t").reduce("", (r,e) -> r + e);
		String shortOutputReference = "[" + opReference + " (is " + opOperationReference + ":" + opOutputSelector + ") as " + opReferenceAsOperand + "]";
		return "Call Stack (" + stockSymbol + "): => s" + parentRequiredStartShift + ": " + shortOutputReference + 
				" with d" + stackDepth + 
				" from " + df.format(calcFrom) + " to " + df.format(calcTo);
	}

}
