package com.finance.pms.events.operations.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.google.common.collect.Lists;

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
							.forEach(cRef -> inputsOperandsRefs.add(cRef + ((inputsOperandsRefs.contains(cRef))? "_N" + Integer.toString(i):"")));
					} else {
						String fShort = operands.get(i).toFormulaeShort();
						inputsOperandsRefs.add(fShort + ((inputsOperandsRefs.contains(fShort))? "_N" + Integer.toString(i):""));
					}
				});
		return inputsOperandsRefs;
	}


	public static SortedMap<Date, double[]> inputListToArray(TargetStockInfo targetStock, List<? extends NumericableMapValue> developpedInputs, Boolean allowAnyNaN, Boolean allowTrailingNaN) 
					throws NoQuotationsException, NotEnoughDataException {
		
		ConcurrentSkipListSet<Date> allDates = developpedInputs.stream()
				.map(di -> new ConcurrentSkipListSet<>(di.getDateKeys()))
				.reduce(new ConcurrentSkipListSet<>(), (a, e) -> { a.addAll(e); return a; });
		
		SortedMap<Date, double[]> factorisedInput = new TreeMap<>();
		SortedMap<Date, double[]> trailingNaNInput = new TreeMap<>();
		SortedMap<Date, double[]> headingNaNInput = new TreeMap<>();
		for (Date date : allDates) {

			//Values at date can neither be Double, double[] or null. There will be one value per input at date
			List<Object> valuesAtDate = developpedInputs.stream().map(developpedInput -> {
				//TODO change this and use MultiMapValue features instead or merge the two concepts???
				if (developpedInput instanceof DoubleArrayMapValue) {
					double[] ds = ((DoubleArrayMapValue)developpedInput).getDoubleArrayValue().get(date);
					if (ds == null) {
						int inputColumnsLength = ((DoubleArrayMapValue)developpedInput).getColumnsReferences().size();
						ds = new double[inputColumnsLength];
						Arrays.fill(ds, Double.NaN);
					}
					return ds;
				} else {
					return ((NumericableMapValue)developpedInput).getValue(targetStock).get(date);
				}	
			}).collect(Collectors.toList());
			
			double[] array = valuesAtDate.stream()
					.map(value -> valueToDoubleArray(value)) //Transforms Potential Doubles to double[]s
					.flatMapToDouble(Arrays::stream) //Transforms double[]s to DoubleStream
					.toArray();

			if (!allowAnyNaN && factorisedInput.isEmpty() && valuesAtDate.stream().anyMatch(
						v -> v == null ||
						(v instanceof Double && Double.isNaN((double) v)) ||
						(v instanceof double[] && Arrays.stream((double[]) v).anyMatch(vPrim -> Double.isNaN((double) vPrim)))
					)
				) { //Heading NaN
				headingNaNInput.put(date, array); //Keeping this in case all is NaN ie trailing are also heading ..
				continue;
			}
			
			if (allowAnyNaN || valuesAtDate.stream().noneMatch(
						v -> v == null || 
						(v instanceof Double && Double.isNaN((double) v)) ||
						(v instanceof double[] && Arrays.stream((double[]) v).anyMatch(vPrim -> Double.isNaN((double) vPrim)))
					) 
				) { //Don't add NaN in this loop if allowAnyNaN is false
				factorisedInput.put(date, array);
			} else { //Only for trailingNaN (this is useful to create dataSet where future training targets are not present).
				trailingNaNInput.put(date, array);
			}

		}
		
		Boolean hasTrailing = !trailingNaNInput.isEmpty();
		if (allowTrailingNaN) { //Adding potential trailing with NaN
			if (!factorisedInput.isEmpty()) {
				Date factorisedLastKey = factorisedInput.lastKey();
				factorisedInput.putAll(trailingNaNInput.tailMap(factorisedLastKey));
				hasTrailing = !trailingNaNInput.headMap(factorisedLastKey).isEmpty(); //Unexpected NaNs not trailing??
			} 
			else if (headingNaNInput.size() == allDates.size()) { //all data contain NaN and trailingNaN is authorised so we add them.
				LOGGER.warn("All data has NaN in series: " + headingNaNInput);
				factorisedInput.putAll(headingNaNInput);
			}
		}
		if (hasTrailing) {
			LOGGER.warn("Non unexpected NaN data in series: " + trailingNaNInput);
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
	
	public interface InnerCalcFunc {
		NumericableMapValue apply(List<NumericableMapValue> data);
	}
	
	public static NumericableMapValue doubleArrayExpender(Operation operation, int dataInputIdx, TargetStockInfo targetStock, int parentRequiredStartShift, InnerCalcFunc innerCalcFunc, List<NumericableMapValue> numericableMapValues) {
	
		List<Map<String, NumericableMapValue>> allInputs = new ArrayList<Map<String,NumericableMapValue>>();
		
		//There can be a mix of inputs with arrays and no arrays. We need to expend to the combination.
		//ex is a.shape(1) b.shape(2) c.shape(5). We need to combine a*b*c to get shape 1x2x5 = 10
		for (NumericableMapValue numericableMapValue: numericableMapValues) {
			Map<String, NumericableMapValue> nthInputs;
			if (numericableMapValue instanceof DoubleArrayMapValue) {
				nthInputs = ((DoubleArrayMapValue) numericableMapValue).getAdditionalOutputs();
			} else {
				nthInputs = new HashMap<String, NumericableMapValue>();
				nthInputs.put(operation.getOperands().get(dataInputIdx).getReference(), numericableMapValue);
			}
			allInputs.add(nthInputs);
		}

		//Combine
		List<List<NumericableMapValue>> combinationsAcc = allInputs.get(0).values().stream().map(e -> Lists.newArrayList(e)).collect(Collectors.toList());
		List<String> hCombinationsAcc = allInputs.get(0).keySet().stream().map(e -> e).collect(Collectors.toList());
		for (int i = 1; i < allInputs.size(); i++) {
			List<List<NumericableMapValue>> combinationsAccPrim = new ArrayList<List<NumericableMapValue>>();
			List<String> hCombinationsAccPrim = new ArrayList<String>();
			for (int j = 0; j < combinationsAcc.size(); j++) {
				for (String nmvk:allInputs.get(i).keySet()) {
					String hNewComb = hCombinationsAcc.get(j);
					hNewComb = hNewComb + "_" + nmvk;
 					hCombinationsAccPrim.add(hNewComb);
					
					ArrayList<NumericableMapValue> newComb = Lists.newArrayList(combinationsAcc.get(j));
					newComb.add(allInputs.get(i).get(nmvk));
					combinationsAccPrim.add(newComb);
				}
			}
			combinationsAcc = combinationsAccPrim;
			hCombinationsAcc = hCombinationsAccPrim;
		}
		

		//Calc
		ExecutorService executor = CalculateThreadExecutor.getExecutorInstance();
		List<Future<Object[]>> futures = new ArrayList<>();
		final List<String> fHCombinationsAcc = hCombinationsAcc;
		final List<List<NumericableMapValue>> fCombinationsAcc = combinationsAcc;
		
		for (int i = 0; i < combinationsAcc.size(); i++) {
			int fI = i;
			Future<Object[]> iterationFuture = executor.submit(new Callable<Object[]>() {
				@Override
				public Object[] call() throws Exception {
					
					String outputsOperandsRef = fHCombinationsAcc.get(fI);
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					LOGGER.info(
							"Running: " + operation.getReference() + " with params " + outputsOperandsRef + 
							". From " + df.format(targetStock.getStartDate(parentRequiredStartShift)) + " to " + df.format(targetStock.getEndDate()));
					
					NumericableMapValue doubleMapValue = innerCalcFunc.apply(fCombinationsAcc.get(fI));
					
					LOGGER.info("Done: " + operation.getReference() + " with params " + outputsOperandsRef);
					LOGGER.info("Yield: " +  operation.getReference() + " with params " + outputsOperandsRef + ": " + doubleMapValue);
					
					if (doubleMapValue.getValue(targetStock).size() == 0) {
						throw new RuntimeException(
								"Empty results for "  + operation.getReference() + " with params " + outputsOperandsRef + " and " + targetStock + ". " +
								"Input boundaries: " + fCombinationsAcc.get(fI));
					}
					return new Object[]{outputsOperandsRef, doubleMapValue};
				}
			});
			futures.add(iterationFuture);
		}
		
		List<NumericableMapValue> allOutputs = new ArrayList<NumericableMapValue>();
		List<String> outputsOperandsRefs = new ArrayList<String>();
		
		for(Future<Object[]> f:futures) {
			try {
				Object[] objects = f.get();
				outputsOperandsRefs.add((String) objects[0]);
				allOutputs.add((NumericableMapValue) objects[1]);
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
		}
		
		//To Array
		try {
			SortedMap<Date, double[]> factorisedOuputs = ValueManipulator.inputListToArray(targetStock, allOutputs, true, true);
			return new DoubleArrayMapValue(factorisedOuputs, outputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(operation.getReference() + " : " + e, e);
		}
		
		return new DoubleArrayMapValue();
	}


}
