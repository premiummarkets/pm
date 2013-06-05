package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.scoring.functions.LeftShifter;

@XmlSeeAlso({CrossUpConstantCondition.class, CrossDownConstantCondition.class})
public abstract class CrossDoubleMapCondition extends Condition<Double> {
	
	
	public CrossDoubleMapCondition(String reference, String description) {
		super(reference, description, new DoubleOperation("date shift"), new DoubleMapOperation(reference+ " left operand"), new DoubleMapOperation(reference+ " right operand"));
	}
	
	public CrossDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		this(reference, description);
		this.setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Integer shift = ((DoubleValue) inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> firstOp = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);
		SortedMap<Date, Double> secondOp = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		fullKeySet.addAll(firstOp.keySet());
		fullKeySet.addAll(secondOp.keySet());
		
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-shift.intValue(), false, false);
		SortedMap<Date, Double> rightShiftedFirstOp = rightShifter.shift(firstOp);
		SortedMap<Date, Double> rightShiftedSecondOp = rightShifter.shift(secondOp);
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : fullKeySet) {
			Double firstV = firstOp.get(date);
			Double secondV = secondOp.get(date);
			Double previousFirstOp = rightShiftedFirstOp.get(date);
			Double previousSecondOp = rightShiftedSecondOp.get(date);
			if (previousFirstOp != null && !previousFirstOp.isNaN() && previousSecondOp != null && !previousSecondOp.isNaN())  {
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(previousFirstOp, firstV, previousSecondOp, secondV);
				if (conditionCheck != null) {
					outputs.getValue(targetStock).put(date, conditionCheck);
				}
			}
		}
		
		return outputs;
	}
}
