package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
import com.finance.pms.events.quotations.NoQuotationsException;

public class IOsLooseAssemblerOperation extends IOsAssemblerOperation implements MultiValuesOutput {
	
	public IOsLooseAssemblerOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public IOsLooseAssemblerOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsLooseAssemblerOperation() {
		this("iosLooseAssembler", "Assembles several inputs into one inputable array. Allowing any NaNs.",
				new StringOperation("string", "assemblerGroupName", "ios- Assembler group name", new StringValue("")),
				new StringOperation("boolean", "isExportToFile", "If true, exports the result to a file.", new StringValue("FALSE")),
				new DoubleMapOperation("data", "datasets", "Datasets to assemble in one", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsLooseAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	protected int firstInputIdx() {
		return 2;
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String assemblerGroupName = ((StringValue) inputs.get(0)).getValue(targetStock);
		assemblerGroupName = ("NONE".equals(assemblerGroupName))?"":"ios-" + assemblerGroupName;
		
		Boolean isExport = Boolean.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));
		
		return innerCalculation(targetStock, inputs, assemblerGroupName, isExport, true);
	}

	@Override
	protected SortedMap<Date, double[]> factoriseInput(
			TargetStockInfo targetStock, 
			List<String> inputsOperandsRefs, List<? extends NumericableMapValue> developpedInputs, Boolean allowTrailingNaN)
			throws NoQuotationsException, NotEnoughDataException {
		return ValueManipulator.inputListToArray(targetStock, developpedInputs, true, true).get(InputToArrayReturn.RESULTS);
	}
	
	@Override
	public int mainInputPosition() {
		return 1;
	}

}