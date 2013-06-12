package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.quotations.QuotationsFactories;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * 'over'
 * not implemented : 'for'
 * does not make sense : 'spanning' . As the condition is a status in time not an event in time.
 */
public abstract class CmpConstantCondition extends Condition<Double> implements OnThresholdCondition {
	

	protected CmpConstantCondition(String reference, String description) {
		super(reference, description, new DoubleOperation("threshold"), new DoubleOperation("time period over which it happens"), new DoubleMapOperation("historical data input"));
	}

	public CmpConstantCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double constant = ((DoubleValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Integer overPeriod = ((DoubleValue) inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : data.keySet()) {
			Double current = data.get(date);
			
			@SuppressWarnings("unchecked")
			Boolean conditionCheck = conditionCheck(current, constant);
			if (conditionCheck != null) {
				
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
		
		return outputs;
	}

	@Override
	public int inputThresholdPosition() {
		return 0;
	}
	
	@Override
	public int mainInputPosition() {
		return 2;
	}
	
	

}
