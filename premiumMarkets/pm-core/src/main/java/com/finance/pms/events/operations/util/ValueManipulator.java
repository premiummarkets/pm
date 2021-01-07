package com.finance.pms.events.operations.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;

public class ValueManipulator {

	public static List<String> buildOperandReferences(int inputSize, List<Operation> operands, List<? extends NumericableMapValue> developpedInputs) {
		List<String> inputsOperandsRefs = new ArrayList<String>();
		IntStream.range(0, inputSize - 1)
				.forEach(i -> {
					if (developpedInputs.get(i) instanceof DoubleArrayMapValue) { //ArrayMap multi output refs
						((DoubleArrayMapValue) developpedInputs.get(i)).getColumnsReferences().stream()
						.forEach(cRef -> 
							inputsOperandsRefs
							.add(cRef+((inputsOperandsRefs.contains(cRef))?Integer.toString(i):""))
						);
					} else { //Ops refs
						inputsOperandsRefs
						.add(operands.get(i).getReference()+((inputsOperandsRefs.contains(operands.get(i).getReference()))?Integer.toString(i):""));
					}
				});
		return inputsOperandsRefs;
	}

	public static SortedMap<Date, double[]> buildOneInput(TargetStockInfo targetStock, List<? extends NumericableMapValue> developpedInputs) {
		SortedMap<Date, double[]> factorisedInput = new TreeMap<>();
		for (Date date : ((NumericableMapValue) developpedInputs.get(0)).getDateKeys()) {

			List<Object> valuesList = developpedInputs.stream().map(i -> {
				//TODO change this and use MultiMapValue features instead.
				if (i instanceof DoubleArrayMapValue) {
					return ((DoubleArrayMapValue)i).getDoubleArrayValue().get(date);
				} else {
					return ((NumericableMapValue)i).getValue(targetStock).get(date);
				}
			}).collect(Collectors.toList());

			if (valuesList.stream().noneMatch(v -> v == null)) {
				double[] array = valuesList.stream()
						.map(value -> valueToDoubleArray(value)) //Transforms Double to double[]
						.flatMapToDouble(Arrays::stream).toArray();
				factorisedInput.put(date, array);
			}

		}
		return factorisedInput;
	}
	
	public static double[] valueToDoubleArray(Object value) {
		if (value instanceof Double) {
			return new double[]{((Double) value).doubleValue()};
		} else if (value instanceof double[]) {
			return (double[]) value;
		} else throw new RuntimeException("Map value not supported for " + value + " of type " + value.getClass());
	}
	
	

}
