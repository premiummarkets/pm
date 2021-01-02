package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class OneInputAssemblerOperation extends ArrayMapOperation {

	private static final int FIRST_INPUT = 1;
	private static MyLogger LOGGER = MyLogger.getLogger(OneInputAssemblerOperation.class);
	
	
	public OneInputAssemblerOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public OneInputAssemblerOperation() {
		this("oneInputAssembler", "Assembles several inputs into one input into one output array.",
				new StringOperation("boolean", "isExportToFile", "If true, exports the result to a file.", new StringValue("FALSE")),
				new DoubleMapOperation("data", "inputs", "Inputs to assemble in one", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public OneInputAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Boolean isExport = Boolean.valueOf(((StringValue) inputs.get(0)).getValue(targetStock));

		@SuppressWarnings("rawtypes")
		List<? extends Value> developpedInputs = inputs.subList(FIRST_INPUT, inputs.size());

		//Build one inputs
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

		//Build operands ref
		List<Operation> operands = getOperands().subList(FIRST_INPUT, getOperands().size());
		List<String> inputsOperandsRefs = IntStream.range(0, inputs.size() - 1)
				.mapToObj(i -> {
					if (developpedInputs.get(i) instanceof DoubleArrayMapValue) { //Dynamic refs
						return ((DoubleArrayMapValue) developpedInputs.get(i)).getColumnsReferences();
					} else { //Static ops refs
						return Arrays.asList(operands.get(i).getReference());
					}
				})
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		try {
			if (isExport) {
				LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
				String key = UUID.randomUUID() + "_" + this.getReference();
				series.put(key, factorisedInput);
				LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
				headersPrefixes.put(key, inputsOperandsRefs);
				SeriesPrinter.printo(key, "tmp", headersPrefixes, series);
			}
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
	}

	private double[] valueToDoubleArray(Object value) {
		if (value instanceof Double) {
			return new double[]{((Double) value).doubleValue()};
		} else if (value instanceof double[]) {
			return (double[]) value;
		} else throw new RuntimeException("Map value not supported for " + value + " of type " + value.getClass());
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}

	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		//Nothing specific to this operation
	}

	@Override
	public int mainInputPosition() {
		return FIRST_INPUT;
	}

}
