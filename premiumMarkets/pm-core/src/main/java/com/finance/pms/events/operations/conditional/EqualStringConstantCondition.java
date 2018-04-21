package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.MapValue;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.StringValue;

public abstract class EqualStringConstantCondition<X, Y> extends Condition<String> {

    @SuppressWarnings("unused")
    private EqualStringConstantCondition() {
        super();
    }

    public EqualStringConstantCondition(String reference, String description, Operation... operands) {
        super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
    }
    
    @Override
    public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        String constant = ((StringValue) inputs.get(0)).getValue(targetStock);
        @SuppressWarnings("unchecked") MapValue<X, Y> value = (MapValue<X, Y>) inputs.get(1);
        SortedMap<X,Y> data = (SortedMap<X,Y>) value.getValue(targetStock);

        BooleanMapValue outputs = new  BooleanMapValue();

        for (X key : data.keySet()) {
            Y current = data.get(key);
            @SuppressWarnings("unchecked")
            Boolean conditionCheck = conditionCheck(mapValueToString(current), constant);
            outputs.getValue(targetStock).put(mapKeyToDate(key), conditionCheck);
        }

        return outputs;
    }

    protected abstract Date mapKeyToDate(X key);

    protected abstract Comparable<String> mapValueToString(Y value);

    @Override
    public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<String> ... ops) {
        return  ((String)ops[0]).compareToIgnoreCase((String) ops[1]) == 0;
    }


}
