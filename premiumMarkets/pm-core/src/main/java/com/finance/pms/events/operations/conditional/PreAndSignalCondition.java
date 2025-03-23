package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.Value;

public class PreAndSignalCondition extends Condition<Boolean[]> {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(PreAndSignalCondition.class);

	private PreAndSignalCondition() {
		super("with", "Is true when the boolean series becomes true as the boolean series in the 'with' conjonction have been fulfilled.",
				new NumberOperation("number","signalCount", "Number of signal series.", new NumberValue(1.0)),
				new Condition<Boolean>("Signal condition and Precondition. Multiple boolean data Series")
		);
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public PreAndSignalCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}
	
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Signals
		int signalsCount = ((NumberValue) inputs.get(0)).getNumberValue().intValue();
		@SuppressWarnings("rawtypes")
		List<? extends Value> conditions = inputs.subList(1, inputs.size());
		
		List<BooleanMapValue> signals = conditions.subList(0, signalsCount).stream().map(v -> ((BooleanMapValue) v)).collect(Collectors.toList());
		
		//Unary check
		if (conditions.size() == signalsCount) {
			return returnType(signalsCount, signals);
		}
		
		//Preconditions
		@SuppressWarnings("unchecked")
		List<? extends BooleanMapValue> preConditions = (List<? extends BooleanMapValue>) conditions.subList(signalsCount, conditions.size());
		
		//Calculation
		List<BooleanMapValue> outputs = innerCalculate(signals, preConditions, targetStock);

		if (LOGGER.isDebugEnabled()) {
			outputs.forEach(o -> {
				SortedMap<Date, Boolean> oValues = o.getValue(targetStock);
				LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
					oValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
			});
		}
		
		return returnType(signalsCount, outputs);
	}

	private BooleanMapValue returnType(int signalsCount, List<BooleanMapValue> signals) {
		if (signals.size() == 1) {
		    return signals.get(0);
		} else {
			Map<String, BooleanMapValue> signalsMap = new HashMap<String, BooleanMapValue>();
			IntStream.range(0, signalsCount).forEach(i -> signalsMap.put("signal_" + i, signals.get(i)));
			BooleanMultiBooleanMapValue multiMapValue = new BooleanMultiBooleanMapValue(signalsMap, -1);
			return multiMapValue;
		}
	}

	protected List<BooleanMapValue> innerCalculate(List<? extends BooleanMapValue> signals, List<? extends BooleanMapValue> preConditions, TargetStockInfo targetStock) {
		
		List<BooleanMapValue> outputs = signals.stream().map(s -> (BooleanMapValue) s.clone()).collect(Collectors.toList());
		
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (BooleanMapValue preCondition: preConditions) {
			fullKeySet.addAll(preCondition.getValue(targetStock).keySet());
		}
		for (BooleanMapValue signal: signals) {
			fullKeySet.addAll(signal.getValue(targetStock).keySet());
		}

		List<Boolean> isPreConditions = new ArrayList<Boolean>(Arrays.asList(new Boolean[preConditions.size()]));
		Collections.fill(isPreConditions, Boolean.FALSE);
		for (Date date : fullKeySet) {
			for (int i=0; i < isPreConditions.size(); i++) {
				Boolean previousIthPrecondition = isPreConditions.get(i);
				Boolean currentIthPreCondition = preConditions.get(i).getValue(targetStock).get(date);
				isPreConditions.set(i, previousIthPrecondition || (currentIthPreCondition != null && currentIthPreCondition));
			}
			outputs.stream().forEach(o -> o.getValue(targetStock).put(date, isPreConditions.stream().reduce(true, (a, e) -> a && e) && o.getValue(targetStock).get(date)));
			Boolean isSignal = signals.stream().map(s -> s.getValue(targetStock).get(date)).reduce(false, (a, e) -> a || e);
			if (isSignal) Collections.fill(isPreConditions, Boolean.FALSE);
		}
		
		return outputs;
	}

}
