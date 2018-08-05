package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableMapValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
public class EqualStringConstantCondition extends Condition<String> {

    private EqualStringConstantCondition() {
        super(
                "trend string constant",  "True when the events time series render a string equals the trend string constant.",
                new StringOperation("trend"),
                new EventMapOperation("historical data input"));
    }

    public EqualStringConstantCondition(ArrayList<Operation> operands, String outputSelector) {
        this();
        setOperands(operands);
    }

    @Override
    public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        String constant = ((StringValue) inputs.get(0)).getValue(targetStock);
        StringableMapValue value = (StringableMapValue) inputs.get(1);
        SortedMap<Date, String> data = value.getValueAsStringMap();

        BooleanMapValue outputs = new  BooleanMapValue();

        for (Date key : data.keySet()) {
            String current = data.get(key);
            @SuppressWarnings("unchecked")
            Boolean conditionCheck = conditionCheck(current, constant);
            outputs.getValue(targetStock).put(key, conditionCheck);
        }

        return outputs;
    }

    @Override
    public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<String> ... ops) {
        return  ((String)ops[0]).compareToIgnoreCase((String) ops[1]) == 0;
    }

}
