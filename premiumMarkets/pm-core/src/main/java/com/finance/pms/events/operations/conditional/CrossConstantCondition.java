package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.LeftShifter;

/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 * 'spanning'
 */

@XmlSeeAlso({CrossUpConstantCondition.class, CrossDownConstantCondition.class})
public abstract class CrossConstantCondition extends Condition<Double> {
	
	public CrossConstantCondition() {
		super();
	}

	public CrossConstantCondition(String reference, String description) {
		super(reference, description, new NumberOperation("threshold"), new NumberOperation("dates comparison span"), new NumberOperation("time period over which it happens"), new DoubleMapOperation("historical data input"));
	}
	
	public CrossConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double constant = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Integer spanningShift = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer overPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);
		
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-spanningShift.intValue(), false, false);
		SortedMap<Date, Double> rightShiftedData = rightShifter.shift(data);
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : data.keySet()) {
			Double current = data.get(date);
			Double previous = rightShiftedData.get(date);
			if (previous != null && !previous.isNaN()) {
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(previous, current, constant);
				if (conditionCheck != null) {
					//outputs.getValue(targetStock).put(date, conditionCheck);
					
					if (overPeriod == 0 || outputs.getValue(targetStock).get(date) == null) {
						outputs.getValue(targetStock).put(date, conditionCheck);
					}
					
					if (conditionCheck && overPeriod > 0) {
						Calendar endOverPeriodCal = Calendar.getInstance();
						endOverPeriodCal.setTime(date);
						QuotationsFactories.getFactory().incrementDate(endOverPeriodCal, +overPeriod+1);
						Date endOverPeriod = (endOverPeriodCal.after(data.lastKey()))?data.lastKey():endOverPeriodCal.getTime();
						SortedMap<Date, Double> overPeriodData = data.subMap(date, endOverPeriod);
						for (Date overPeriodDate : overPeriodData.keySet()) {
							outputs.getValue(targetStock).put(overPeriodDate, conditionCheck);
						}
					}
				}
			}
		}
		
		return outputs;
	}

	public int mainInputPosition() {
		return 3;
	}
}
