package com.finance.pms.events.operations.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.quotations.NoQuotationsException;

public class ValueManipulator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ValueManipulator.class);

	public static List<String> extractOperandReferences(List<Operation> operands, List<? extends NumericableMapValue> developpedInputs) {
		List<String> inputsOperandsRefs = new ArrayList<String>();
		IntStream.range(0, operands.size())
				.forEach(i -> {
					if (developpedInputs.get(i) instanceof DoubleArrayMapValue) { //ArrayMap multi output refs
						((DoubleArrayMapValue) developpedInputs.get(i)).getColumnsReferences().stream()
						.forEach(cRef -> 
							inputsOperandsRefs
							.add(cRef+((inputsOperandsRefs.contains(cRef))?Integer.toString(i):""))
						);
					} else { //Ops refs
						inputsOperandsRefs
						.add(operands.get(i).getReference() + ((inputsOperandsRefs.contains(operands.get(i).getReference()))?Integer.toString(i):""));
					}
				});
		return inputsOperandsRefs;
	}
	
	public static List<String> extractOperandFormulaeShort(List<Operation> operands, List<? extends NumericableMapValue> developpedInputs) {
		List<String> inputsOperandsRefs = new ArrayList<String>();
		IntStream.range(0, operands.size())
				.forEach(i -> {
					if (developpedInputs.get(i) instanceof DoubleArrayMapValue) { //ArrayMap multi output refs
						((DoubleArrayMapValue) developpedInputs.get(i)).getColumnsReferences().stream()
							.forEach(cRef -> inputsOperandsRefs.add(cRef + ((inputsOperandsRefs.contains(cRef))? "n" + Integer.toString(i):"")));
					} else {
						String fShort = operands.get(i).toFormulaeShort();
						inputsOperandsRefs.add(fShort + ((inputsOperandsRefs.contains(fShort))? "n" + Integer.toString(i):""));
					}
				});
		return inputsOperandsRefs;
	}


	public static SortedMap<Date, double[]> inputListToArray(TargetStockInfo targetStock, List<? extends NumericableMapValue> developpedInputs, Boolean allowTrailingNaN) throws NoQuotationsException, NotEnoughDataException {
		
		ConcurrentSkipListSet<Date> allDates = developpedInputs.stream()
				.map(di -> new ConcurrentSkipListSet<>(di.getDateKeys()))
				.reduce(new ConcurrentSkipListSet<>(), (a, e) -> { a.addAll(e); return a; });
		
		SortedMap<Date, double[]> factorisedInput = new TreeMap<>();
		SortedMap<Date, double[]> trailingNaNInput = new TreeMap<>();
		for (Date date : allDates) {

			//Values at date can neither be Double, double[] or null. There will be one value per input at date
			List<Object> valuesAtDate = developpedInputs.stream().map(i -> {
				//TODO change this and use MultiMapValue features instead or merge the two concepts???
				if (i instanceof DoubleArrayMapValue) {
					return ((DoubleArrayMapValue)i).getDoubleArrayValue().get(date);
				} else {
					return ((NumericableMapValue)i).getValue(targetStock).get(date);
				}
			}).collect(Collectors.toList());

			if (factorisedInput.isEmpty() && valuesAtDate.stream().anyMatch(v -> v == null)) //Heading NaN
				continue;
			
			double[] array = valuesAtDate.stream()
					.map(value -> valueToDoubleArray(value)) //Transforms Potential Doubles to double[]s
					.flatMapToDouble(Arrays::stream) //Transforms double[]s to DoubleStream
					.toArray();
			if (valuesAtDate.stream().noneMatch(v -> v == null)) { //Don't add NaN in this loop
				factorisedInput.put(date, array);
			} else { //Only for trailing NaN (this is useful to create dataSet where all targets are not present).
				trailingNaNInput.put(date, array);
			}

		}
		
		Boolean hasTrailing = !trailingNaNInput.isEmpty();
		if (allowTrailingNaN) { //Adding potential trailing with NaN
			Date factorisedLastKey = factorisedInput.lastKey();
			factorisedInput.putAll(trailingNaNInput.tailMap(factorisedLastKey));
			hasTrailing = !trailingNaNInput.headMap(factorisedLastKey).isEmpty();
		}
		if (hasTrailing) {
			LOGGER.warn("Non authorised NaN data in series: " + trailingNaNInput);
		}
		return factorisedInput;
	}
	
	public static double[] valueToDoubleArray(Object value) {
		if (value == null) value = Double.NaN;
		if (value instanceof Double) {
			return new double[]{((Double) value).doubleValue()};
		} else if (value instanceof double[]) {
			return (double[]) value;
		} else throw new RuntimeException("Map value not supported for " + value + " of type " + value.getClass());
	}


}
