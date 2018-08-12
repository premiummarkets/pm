package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableMapValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

/**
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'over'
 * 'for'
 * does not make sense : 'spanning'. As this condition is a status check in time not an event check (change of status) in time.
 */
@XmlRootElement
public class EqualStringConstantCondition extends Condition<String> {

    private EqualStringConstantCondition() {
        super(
                "trend string constant",  "True when the events time series render a string equals the trend string constant.",
                new StringOperation("trend"),
                new NumberOperation("time period over which it happens"),
                new NumberOperation("length of time over which it is true"),
                new EventMapOperation("historical data input"));
    }

    public EqualStringConstantCondition(ArrayList<Operation> operands, String outputSelector) {
        this();
        setOperands(operands);
    }

    @Override
    public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        String constant = ((StringValue) inputs.get(0)).getValue(targetStock);
		Integer overPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
        SortedMap<Date, String> data = ((StringableMapValue) inputs.get(3)).getValueAsStringMap();

		if (overPeriod > 0 && forPeriod > 0) throw new UnsupportedOperationException("Setting both Over Period "+overPeriod+" and For Period "+forPeriod+" is not supported.");

		BooleanMapValue outputs = new BooleanMapValue();

		SortedSet<Date> fullKeySet = new TreeSet<>(data.keySet());
		BooleanMapValue realRowOutputs = new BooleanMapValue();

        for (Date date : fullKeySet) {
        	String current = data.get(date);

			@SuppressWarnings("unchecked")
			Boolean conditionCheck = conditionCheck(current, constant);
			if (conditionCheck != null) {

				if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {

					realRowOutputs.getValue(targetStock).put(date, conditionCheck);

					conditionCheck = checkRawOutputAgainstForPeriod(targetStock, forPeriod, fullKeySet, realRowOutputs, date, conditionCheck);

					if (conditionCheck != null) outputs.getValue(targetStock).put(date, conditionCheck);

				}

				fillInOverPeriod(targetStock, overPeriod, fullKeySet, date, conditionCheck, outputs);

			}
        }

        return outputs;
    }

    @Override
    public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<String> ... ops) {
        return  ((String)ops[0]).compareToIgnoreCase((String) ops[1]) == 0;
    }

}
