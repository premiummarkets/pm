package com.finance.pms.events.operations.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
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
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
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
	
	public static List<String> extractOperandFormulaeShort(TargetStockInfo targetStock,  List<StackElement> thisCallStack, List<Operation> operands, List<? extends NumericableMapValue> developpedInputs) {
		List<String> inputsOperandsRefs = new ArrayList<String>();
		IntStream.range(0, operands.size())
				.forEach(i -> {
					String fShort = operands.get(i).toFormulaeShort(targetStock, thisCallStack);
					if (developpedInputs.get(i) instanceof DoubleArrayMapValue) { //ArrayMap multi output refs
						((DoubleArrayMapValue) developpedInputs.get(i)).getColumnsReferences().stream()
							.forEach(cRef -> inputsOperandsRefs.add(fShort + "_" + cRef + ((inputsOperandsRefs.contains(cRef))? "_N" + Integer.toString(i):"")));
					} else {
						inputsOperandsRefs.add(fShort + ((inputsOperandsRefs.contains(fShort))? "_N" + Integer.toString(i):""));
					}
				});
		return inputsOperandsRefs;
	}


	public enum InputToArrayReturn {
		RESULTS, HEADINGNANS, TRAILINGNANS, OTHERUNEXPECTEDNANS
	}
	public static Map<InputToArrayReturn, SortedMap<Date, double[]>> inputListToArray(
			TargetStockInfo targetStock, Collection<? extends NumericableMapValue> developpedInputs, 
			Boolean keepAnyNaN, Boolean keepAllTrailingNaN, Integer... trailingNaNsColIdsToKeep) {
		
		ConcurrentSkipListSet<Date> allDates = developpedInputs.stream()
				.map(di -> new ConcurrentSkipListSet<>(di.getDateKeys()))
				.reduce(new ConcurrentSkipListSet<>(), (a, e) -> { a.addAll(e); return a; });
		if (allDates.size() > 0) {
			LOGGER.info("Transforming NumMap list to ArrayMap. NumMap input is from " + allDates.first() + " to " + allDates.last());
		} else {
			LOGGER.info("Transforming NumMap list to ArrayMap. NumMap input is empty ");
		}
		
		SortedMap<Date, double[]> factorisedInput = new TreeMap<>();
		NavigableMap<Date, double[]> notHeadingNaNs = new TreeMap<>();
		SortedMap<Date, double[]> headingNaNs = new TreeMap<>();
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

			if (!keepAnyNaN && factorisedInput.isEmpty() && valuesAtDate.stream().anyMatch( //This is heading NaNs as factorisedInput.isEmpty()
						v -> v == null ||
						(v instanceof Double && Double.isNaN((double) v)) ||
						(v instanceof double[] && Arrays.stream((double[]) v).anyMatch(vPrim -> Double.isNaN((double) vPrim)))
					)
				) { //Heading NaN
				headingNaNs.put(date, array); //Keeping this in case all is NaN ie trailing NaNs are also heading NaNs..
			}
			else if (keepAnyNaN || valuesAtDate.stream().noneMatch( //This not heading as !factorisedInput.isEmpty(): could be trailing or in the middle?
						v -> v == null || 
						(v instanceof Double && Double.isNaN((double) v)) ||
						(v instanceof double[] && Arrays.stream((double[]) v).anyMatch(vPrim -> Double.isNaN((double) vPrim)))
					) 
				) { //Don't add NaN in this loop if allowAnyNaN is false
				factorisedInput.put(date, array);
			} 
			else { //Only for trailingNaN (this is useful to create dataSet where future training targets are not present).
				notHeadingNaNs.put(date, array);
			}

		}
		if (factorisedInput.size() > 0) {
			LOGGER.info("Transforming NumMap list to ArrayMap. ArrayMap output before trailing addition is from " + factorisedInput.firstKey() + " to " + factorisedInput.lastKey());
		} else {
			LOGGER.info("Transforming NumMap list to ArrayMap. ArrayMap output before trailing addition is empyt");
		}
		
		Map<InputToArrayReturn, SortedMap<Date, double[]>> result = new HashMap<>();
		result.put(InputToArrayReturn.OTHERUNEXPECTEDNANS, new TreeMap<>());
		
		//Heading
		result.put(InputToArrayReturn.HEADINGNANS, headingNaNs);
		if (allDates.size() > 0 && factorisedInput.size() > 0 && allDates.first().before(factorisedInput.firstKey())) {
			LOGGER.warn("Transforming NumMap list to ArrayMap. The output head has been cut from " + allDates.first() + " to " + factorisedInput.lastKey());
		}
		
		//Not Heading
		Boolean keepTrailingNaN = keepAllTrailingNaN || trailingNaNsColIdsToKeep.length != 0;

		SortedMap<Date, double[]> trailingNaNs;
		if (!factorisedInput.isEmpty()) {
			Date factorisedLastKey = factorisedInput.lastKey();
			trailingNaNs = notHeadingNaNs.tailMap(factorisedLastKey, false);
			result.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).putAll(notHeadingNaNs.headMap(factorisedLastKey, true)); //Unexpected NaNs not trailing nor heading!!
		} 
		else if (headingNaNs.size() == allDates.size()) { //factorisedInput.isEmpty(): all data contain NaN and trailingNaN is authorised so we add them.
			LOGGER.warn("All data is NaN in this series or the series is empty: " + headingNaNs);
			trailingNaNs = headingNaNs;
		} 
		else {
			throw new RuntimeException("Should not be here!! Either we have factorised data or all data is NaN");
		}
		
		if (!keepAllTrailingNaN) {//Check trailing NaNs columns to keep
			List<Integer> allowedCIndexesList = Arrays.asList(trailingNaNsColIdsToKeep);
			SortedMap<Date, double[]> unExpectedTrailingNaNs = trailingNaNs.entrySet().stream() //Unexpected NaNs trailing!!
					.filter(e -> IntStream.range(0, e.getValue().length).anyMatch(i -> !allowedCIndexesList.contains(i) && Double.isNaN(e.getValue()[i])))
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
			unExpectedTrailingNaNs.keySet().stream().forEach(u -> trailingNaNs.remove(u));
			result.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).putAll(unExpectedTrailingNaNs);  //Unexpected NaNs (unauthorised column) trailing!!
		}
		
		result.put(InputToArrayReturn.TRAILINGNANS, trailingNaNs);
		if (keepTrailingNaN) factorisedInput.putAll(trailingNaNs); //Adding potential trailing with NaN in the result
		
		//Return
		if (factorisedInput.size() > 0) {
			LOGGER.info("Transforming NumMap list to ArrayMap. ArrayMap output is from " + factorisedInput.firstKey() + " to " + factorisedInput.lastKey());
		}
//		//DEBUG
//		try {
//			Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-30");
//			if (factorisedInput.firstKey().after(parse)) {
//				LOGGER.error("First output is after " + parse + " for " + targetStock + ": " + factorisedInput.get(parse));
//			}
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//DEBUG
		result.put(InputToArrayReturn.RESULTS, factorisedInput);
		return result;
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
	
	/**
	 * Flattens the numericableMapValues parameter into two lists: one of all the input data (unary and additional data) and one of their respective headers
	 * Applies the innerCalcFunc on each of the input data in the list
	 * Returns an DoubleArrayMapValue containing the initial headers and the results (in the form double[]) of the applied, as additional data.
	 * @param operation
	 * @param dataInputIdx
	 * @param targetStock
	 * @param parentRequiredStartShift
	 * @param innerCalcFunc
	 * @param numericableMapValues
	 * @return
	 */
	public static NumericableMapValue doubleArrayExpender(
			Operation operation, 
			int dataInputIdx, TargetStockInfo targetStock, int parentRequiredStartShift, 
			InnerCalcFunc innerCalcFunc, List<NumericableMapValue> numericableMapValues) {
	
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
		List<List<NumericableMapValue>> combinationsAcc = allInputs.get(0).values().stream().map(e -> Lists.newArrayList(e)).collect(Collectors.toList());//init first comb
		List<String> hCombinationsAcc = allInputs.get(0).keySet().stream().map(e -> e).collect(Collectors.toList()); //init first hcomb
		for (int i = 1; i < allInputs.size(); i++) {
			List<List<NumericableMapValue>> combinationsAccPrim = new ArrayList<List<NumericableMapValue>>();
			List<String> hCombinationsAccPrim = new ArrayList<String>();
			for (int j = 0; j < combinationsAcc.size(); j++) {
				for (String nmvk : allInputs.get(i).keySet()) {
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
		ExecutorService executor = CalculateThreadExecutor.getRandomInfiniteExecutorInstance();
		List<Future<Map<String, NumericableMapValue>>> futures = new ArrayList<>();
		final List<String> fHCombinationsAcc = hCombinationsAcc;
		final List<List<NumericableMapValue>> fCombinationsAcc = combinationsAcc;
		
		for (int i = 0; i < combinationsAcc.size(); i++) {
			int fI = i;
			Future<Map<String, NumericableMapValue>> iterationFuture = executor.submit(new Callable<Map<String, NumericableMapValue>>() {
				@Override
				public Map<String, NumericableMapValue> call() throws NotEnoughDataException, Exception {
					
					String outputsOperandsRef = fHCombinationsAcc.get(fI);
					
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//					LOGGER.info("Running: " + operation.getReference() + " with params " + outputsOperandsRef + ". From " + df.format(targetStock.getStartDate(parentRequiredStartShift)) + " to " + df.format(targetStock.getEndDate()));
					NumericableMapValue innerCalcFuncRes = innerCalcFunc.apply(fCombinationsAcc.get(fI));
//					LOGGER.info("Yield: " +  operation.getReference() + " with params " + outputsOperandsRef + ": " + innerCalcFuncRes);
					
					if (innerCalcFuncRes.getValue(targetStock).size() == 0) {
						throw new NotEnoughDataException(
								targetStock, parentRequiredStartShift, 
								"Empty results for "  + operation.getReference() + " with params " + outputsOperandsRef + " and " + targetStock + ". " +
								"Input boundaries: " + fCombinationsAcc.get(fI), new Exception());
					}
					
					Map<String, NumericableMapValue> applyRes = new TreeMap<>();
					if (innerCalcFuncRes instanceof DoubleArrayMapValue && ((DoubleArrayMapValue) innerCalcFuncRes).getColumnsReferences().size() > 1) { //Again the application of innerCalcFunc can return several outputs
						((DoubleArrayMapValue) innerCalcFuncRes).getAdditionalOutputs().entrySet().forEach(e -> {
							applyRes.put(outputsOperandsRef + "." + e.getKey(), e.getValue());
						});
					} else {
						applyRes.put(outputsOperandsRef, innerCalcFuncRes);
					}
					
					return applyRes;
				}
			});
			futures.add(iterationFuture);
		}
		
		List<NumericableMapValue> allOutputs = new ArrayList<NumericableMapValue>();
		List<String> outputsOperandsRefs = new ArrayList<String>();
		
		for(Future<Map<String, NumericableMapValue>> f:futures) {
			try {
				Map<String, NumericableMapValue> objects = f.get();
				outputsOperandsRefs.addAll(objects.keySet());
				allOutputs.addAll(objects.values());
			} catch (Exception e) {
				if (e.getCause() instanceof NotEnoughDataException) {
					LOGGER.warn(e);
				} else {
					LOGGER.error(e, e);
				}
			}
		}
		
		//To Array
		try {
			SortedMap<Date, double[]> factorisedOuputs = ValueManipulator.inputListToArray(targetStock, allOutputs, true, true).get(InputToArrayReturn.RESULTS);
			return new DoubleArrayMapValue(factorisedOuputs, outputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(operation.getReference() + ": " + e, e);
		}
		
		return new DoubleArrayMapValue();
	}


}
