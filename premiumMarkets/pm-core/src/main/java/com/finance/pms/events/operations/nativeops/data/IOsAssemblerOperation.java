package com.finance.pms.events.operations.nativeops.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.ArrayMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
import com.finance.pms.events.quotations.NoQuotationsException;

public class IOsAssemblerOperation extends ArrayMapOperation {

	private static final int FIRST_INPUT = 3;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsAssemblerOperation.class);
	

	public IOsAssemblerOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public IOsAssemblerOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsAssemblerOperation() {
		this("iosAssembler", "Assembles several inputs into one inputable array. Only trailing NaNs are optionnaly permitted.",
				new StringOperation("string", "assemblerGroupName", "ios- Assembler group name", new StringValue("")),
				new StringOperation("boolean", "isExportToFile", "If true, exports the result to a file.", new StringValue("FALSE")),
				new StringOperation("boolean", "allowLastColumnTrailingNaN", "If NaN are allowed in the last column of the data set (usefull for targets)", new StringValue("FALSE")),
				new DoubleMapOperation("data", "datasets", "Datasets to assemble in one", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisStartShift, int thisAndOperandsStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		String assemblerGroupName = ((StringValue) inputs.get(0)).getValue(targetStock);
		assemblerGroupName = "ios" + (("NONE".equals(assemblerGroupName))?"":"-" + assemblerGroupName) + "_";
		
		Boolean isExport = Boolean.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));
		Boolean allowLastColumnTrailingNaN = Boolean.valueOf(((StringValue) inputs.get(2)).getValue(targetStock));

		return innerCalculation(targetStock, inputs, assemblerGroupName, isExport, allowLastColumnTrailingNaN);
	}

	protected DoubleArrayMapValue innerCalculation(
			TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs, 
			String assemblerGroupName, Boolean isExport, Boolean allowLastColumnTrailingNaN) {
		
		try {
			
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(firstInputIdx(), inputs.size());
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(targetStock, getOperands().subList(firstInputIdx(), getOperands().size()), developpedInputs);
			
			SortedMap<Date, double[]> factorisedInput = factoriseInput(targetStock, inputsOperandsRefs, developpedInputs, allowLastColumnTrailingNaN);
			
			if (!factorisedInput.isEmpty()) {
				if (inputsOperandsRefs.size() != factorisedInput.get(factorisedInput.firstKey()).length) {
					throw new RuntimeException("The input assembler is missing assemblees.");
				}
			}
			
			inputsOperandsRefs = inputsOperandsRefs.stream().map(ior -> assemblerGroupName + ior).collect(Collectors.toList());
			
			if (isExport) {
				LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
				String fileName = "iosAssembler_" + this.getReference() + "_" + UUID.randomUUID();
				series.put(this.getReference(), factorisedInput);
				LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
				headersPrefixes.put(this.getReference(), inputsOperandsRefs);
				SeriesPrinter.printo(fileName, "tmp", headersPrefixes, series);
			}
			
			return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
			
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " failed: with " + targetStock + " and " + assemblerGroupName + " and " + allowLastColumnTrailingNaN + " and " + inputs, e);
		}
		
		return new DoubleArrayMapValue();
	}

	protected int firstInputIdx() {
		return FIRST_INPUT;
	}

	protected SortedMap<Date, double[]> factoriseInput(
			TargetStockInfo targetStock, 
			List<String> inputsOperandsRefs, List<? extends NumericableMapValue> developpedInputs, Boolean allowLastColumnTrailingNaN)
			throws NoQuotationsException, NotEnoughDataException {
		
		try {
			IntStream.range(0, developpedInputs.size()).forEach( index -> {
				if (developpedInputs.get(index).getDateKeys().isEmpty()) {
					throw new RuntimeException("No result yield: " + inputsOperandsRefs);
				}
			});
			return ValueManipulator.inputListToArray(targetStock, developpedInputs, false, false, inputsOperandsRefs.size()-1).get(InputToArrayReturn.RESULTS);
		} catch (Exception e) {
			throw new NotEnoughDataException(targetStock.getStock(), targetStock.getStartDate(0), targetStock.getEndDate(), e.toString(), e);
		}
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

}
