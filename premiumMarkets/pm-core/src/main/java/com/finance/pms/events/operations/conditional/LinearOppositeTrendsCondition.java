package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class LinearOppositeTrendsCondition extends LinearTrendsCondition {

	public LinearOppositeTrendsCondition() {
		super("unlike trend regression", "Opposite linear regression of two inputs for a defined period.");
	}

	public LinearOppositeTrendsCondition(ArrayList<Operation> operands, String outputSelector) {
		super(operands, outputSelector);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {

		Double firstSlope = (Double) ops[0];
		Double secondSlope = (Double) ops[1];
		Direction direction = (Direction) ops[2];
		//Double epsilon = (Double) ops[3];

		switch (direction) {
		case up :
			if (firstSlope < 0) return false;
			break;
		case down :
			if (firstSlope > 0) return false;
			break;
		case both :
			break;
		}

		Double diff = Math.abs(firstSlope-secondSlope);
		return (diff >= Math.abs(firstSlope));

	}

}
