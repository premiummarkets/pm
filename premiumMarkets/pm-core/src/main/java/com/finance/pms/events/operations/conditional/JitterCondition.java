package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;

//TODO ??
public class JitterCondition extends Condition<Boolean> {

	private JitterCondition() {
		super("Jitter detection", "Jitter??",
				new NumberOperation("direction"), new NumberOperation("change ratio"), new NumberOperation("dates comparison span"), new DoubleMapOperation("historical data input"));
	}

	public JitterCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}

}
