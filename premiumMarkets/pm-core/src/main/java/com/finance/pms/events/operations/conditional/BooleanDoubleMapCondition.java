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

@XmlSeeAlso({AndDoubleMapCondition.class, OrDoubleMapCondition.class, NotDoubleMapCondition.class})
public abstract class BooleanDoubleMapCondition extends Condition<Boolean> {
	
	protected BooleanDoubleMapCondition() {
		super();
	}

	public BooleanDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public BooleanDoubleMapCondition(String reference, String description, Operation... operands) {
		super(reference, description, operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		if (shortcutUnary() && inputs.size() == 1) return (BooleanMapValue) inputs.get(0);
		
		List<SortedMap<Date, Boolean>> maps = new ArrayList<SortedMap<Date,Boolean>>();
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (Value<SortedMap<Date, Boolean>> input : inputs) {
			fullKeySet.addAll(input.getValue(targetStock).keySet());
			maps.add(input.getValue(targetStock));
		}
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : fullKeySet) {
			
			Boolean gruyere = false;
			List<Boolean> currentOps = new ArrayList<Boolean>();
			for (SortedMap<Date, Boolean> map : maps) {
				Boolean currentOp = map.get(date);
				if (currentOp != null) {
					currentOps.add(currentOp);
				} else {
					gruyere = true;
					break;
				}
			}
			if (!gruyere) {
				Boolean conditionCheck = conditionCheck(currentOps.toArray(new Boolean[0]));
				if (conditionCheck != null) {
					outputs.getValue(targetStock).put(date, conditionCheck);
				}
			}
			
		}
		
		return outputs;
	}
	
	protected abstract Boolean shortcutUnary();

	
}
