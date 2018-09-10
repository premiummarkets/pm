package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;

import java.util.ArrayList;

/**
 * 'spanning' : does not make sense. As this condition is a status check in time not an event check (change of status) in time.
 * 'over'
 * 'for'
 */
public class TrendsCondition extends Condition<Comparable> implements OnSignalCondition {

    @SuppressWarnings("unused")
    private TrendsCondition() {
        super();
    }

    public TrendsCondition(String reference, String description) {
        super(reference, description,
                new NumberOperation("look back period over which the condition has to happened"),
                new NumberOperation("length of time for which the condition has to true"),
                new DoubleMapOperation("'"+reference+ "' left operand (data)"),
                new DoubleMapOperation("'"+reference+ "' right operand (data)"));
    }

    public TrendsCondition(String reference, String description, ArrayList<Operation> operands) {
        this(reference, description);
        this.setOperands(operands);
    }


    @Override
    public int inputSignalPosition() {
        //TODO
        return 0;
    }

    @Override
    public int mainInputPosition() {
        //TODO
        return 0;
    }
}
