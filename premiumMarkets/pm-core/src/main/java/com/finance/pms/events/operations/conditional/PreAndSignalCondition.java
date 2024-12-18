package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.Value;

public class PreAndSignalCondition extends Condition<Boolean> {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(PreAndSignalCondition.class);

	private PreAndSignalCondition() {
		super("with", "Is true when the boolean series becomes true as the boolean series in the 'with' conjonction have been fulfilled.");
//				new Condition<Boolean>("Signal condition. One boolean data Series"),
//				new Condition<Boolean>("Preconditions. Multiple boolean data Series"));
//		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true); //FIXME not supported
	}

	public PreAndSignalCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}
	
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Unary check
		if (inputs.size() == 1) return ((BooleanMapValue) inputs.get(0));
		
		//Params check
		SortedMap<Date, Boolean> signal = ((BooleanMapValue) inputs.get(0)).getValue(targetStock);
		@SuppressWarnings("unchecked")
		List<? extends BooleanMapValue> preConditions = (List<? extends BooleanMapValue>) inputs.subList(1, inputs.size());

		//Calculation
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (BooleanMapValue preCondition: preConditions) {
			fullKeySet.addAll(preCondition.getValue(targetStock).keySet());
		}
		fullKeySet.addAll(signal.keySet());

		BooleanMapValue outputs = new BooleanMapValue();

		List<Boolean> isPreConditions = new ArrayList<Boolean>(Arrays.asList(new Boolean[preConditions.size()]));
		Collections.fill(isPreConditions, Boolean.FALSE);
		for (Date date : fullKeySet) {
			for (int i=0; i < isPreConditions.size(); i++) {
				Boolean currentIthPreCondition = preConditions.get(i).getValue(targetStock).get(date);
				isPreConditions.set(i, isPreConditions.get(i) || (currentIthPreCondition != null && currentIthPreCondition));
			}
			Boolean currentSignal = signal.get(date);
			Boolean isSignal = currentSignal != null && currentSignal;
			outputs.getValue(targetStock).put(date, isPreConditions.stream().reduce(true, (a, e) -> a && e) && isSignal);
			if (isSignal) Collections.fill(isPreConditions, Boolean.FALSE);
		}

		if (LOGGER.isDebugEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
					outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
		}

		return outputs;
	}

}
