package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.scoring.functions.HighLowSolver;

public class HigherLowCondition extends HighsAndLowsCondition {
	
	HighLowSolver highLowSolver = new HighLowSolver();

	public HigherLowCondition() {
		super("higher low",  "True when the time series is making a higher low.");
	}

	public HigherLowCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean conditionCheck(Comparable<ArrayList<Double>>... ops) {
		return highLowSolver.higherLow(((ArrayList<Double>)ops[0]).toArray(new Double[0]),((ArrayList<Double>)ops[1]).toArray(new Double[0]),(ArrayList<Double>)ops[2]);
	}
	
}
