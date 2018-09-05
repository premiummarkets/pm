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
    /**
     * TODO Test these cases (also applies to other conditions using over and for) :
     * ex1 : overPeriod == 0, forPeriod == 0
     * 	it0 :
     * 		outputs.get() == null && overPeriod == 0 && realRowOutputs isEmpty()
     * 		=> reduce for
     * 			=> realRowOutputs.put(date, dateCc);
     * 			=> outputs.put(date, dateCc);
     * 		overPeriod == 0
     * 		=> no over filling
     * 	itn : idem
     *
     * ex2 : overPeriod > 0, forPeriod == 0
     * 	it0 :
     * 		outputs.get() == null && realRowOutputs isEmpty()
     * 		=> reduce for
     * 			=> realRowOutputs.put(date, dateCc);
     * 			=> outputs.put(date, dateCc);
     * 		=> over filling if dateCc
     * 			=> from actual date to actual + overP
     * 	itn :
     * 		outputs.get() != null if previous dateCc was true and hence filled in
     * 		=> No reduce
     * 		=> over filling if dateCc
     * 			=> from actual date to actual + overP
     *
     * ex3 : overPeriod == 0, forPeriod > 0
     * 	it0 :
     * 		outputs.get() == null && overPeriod == 0 && realRowOutputs isEmpty()
     * 		=> reduce for
     * 			=> realRowOutputs.put(date, dateCc);
     * 			=> outputs.put(date, reduceDateCc);
     * 		overPeriod == 0
     * 		=> no over filling
     * 	itn: idem
     *
     * 	ex4 : overPeriod > 0, forPeriod > 0
     * 	    => not implemented
     */
    public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        String constant = ((StringValue) inputs.get(0)).getValue(targetStock);
		Integer overPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer forPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
        SortedMap<Date, String> data = ((StringableMapValue) inputs.get(3)).getValueAsStringMap();

		if (overPeriod > 0 && forPeriod > 0)
		    throw new UnsupportedOperationException("Setting both Over Period " + overPeriod + " and For Period " + forPeriod + " is not supported.");

		BooleanMapValue outputs = new BooleanMapValue();

		SortedSet<Date> fullKeySet = new TreeSet<>(data.keySet());
		BooleanMapValue realRowOutputs = new BooleanMapValue();

        for (Date date : fullKeySet) {
        	String current = data.get(date);

			@SuppressWarnings("unchecked")
			Boolean conditionCheck = conditionCheck(current, constant);
			if (conditionCheck != null) {

				if ((overPeriod == 0 || outputs.getValue(targetStock).get(date) == null)) {
					conditionCheck = forPeriodReduction(targetStock, forPeriod, fullKeySet, realRowOutputs, date, conditionCheck, realRowOutputs);
				}

				overPeriodFilling(targetStock, overPeriod, fullKeySet, date, conditionCheck, outputs);

			}
        }

        return outputs;
    }

    @Override
    public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<String> ... ops) {
        return  ((String)ops[0]).compareToIgnoreCase((String) ops[1]) == 0;
    }

}
