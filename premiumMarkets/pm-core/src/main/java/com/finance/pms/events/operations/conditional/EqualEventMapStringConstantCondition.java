package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.StringOperation;

@XmlRootElement
public class EqualEventMapStringConstantCondition extends EqualStringConstantCondition<EventKey, EventValue> {

    private EqualEventMapStringConstantCondition() {
        super(
                "trend string constant",  "True when the events time series render a string equals the trend string constant.", 
                new StringOperation("trend"),
                new EventMapOperation("historical data input"));
    }

    public EqualEventMapStringConstantCondition(ArrayList<Operation> operands, String outputSelector) {
        this();
        setOperands(operands);
    }

    @Override
    protected Date mapKeyToDate(EventKey key) {
        return key.getDate();
    }

    @Override
    protected Comparable<String> mapValueToString(EventValue value) {
        return value.getEventType().toString();
    }

}
