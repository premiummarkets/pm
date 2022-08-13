package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.google.common.collect.Lists;

public class TalibAssemblerOperation extends ArrayMapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibAssemblerOperation.class);
	
	public TalibAssemblerOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public TalibAssemblerOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public TalibAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public TalibAssemblerOperation() {
		this("talibAssembler", "Assembles several periods iterations of one indicator into one inputable array.",
				new OperationReferenceOperation("operationReference", "operation reference", "Operation to iterate upon", null),
				new ListOperation("parameters", "parameters and parameters slices", "[start, end, step] or [x, factor] or [\"abc\"]. "
						+ "Use [x, factor] to make this parameter a factor of the parameter x. "
						+ "Use [\"abc\"] for constant string parameters. ", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Operation operation = (Operation) ((OperationReferenceValue<?>) inputs.get(0)).getValue(targetStock);
		@SuppressWarnings("unchecked")
		List<List<Value<?>>> parameters = inputs.subList(1, inputs.size()).stream().map(v -> ((AnyValueListValue<Value<?>>) v).getValue(targetStock)).collect(Collectors.toList());

		SortedMap<Integer, List<Double>> slices = new TreeMap<>();
		Operation operand = operation.getOperands().get(0);
		for (int parametersSlicePos = 0; parametersSlicePos < parameters.size(); parametersSlicePos++) {
			if (parametersSlicePos < operation.getOperands().size()) {
				operand = operation.getOperands().get(parametersSlicePos);
			} 
			if (operand instanceof NumberOperation) {
				slices.put(parametersSlicePos, parameters.get(parametersSlicePos).stream().map(v -> ((NumberValue)v).getValue(targetStock).doubleValue()).collect(Collectors.toList()));
			} else {
				operand.setParameter(parameters.get(parametersSlicePos).get(0));
			}
		}
		
		List<NumericableMapValue> runsOutputs = new ArrayList<NumericableMapValue>();
		List<String> inputsOperandsRefs = new ArrayList<String>();
		List<Double> pivotParamSlices = slices.values().stream().filter(s -> s.size() == 3).findFirst().orElse(Lists.newArrayList(0.0,1.0,1.0));
		Double start = pivotParamSlices.get(0);
		Double end = pivotParamSlices.get(1);
		Double step = pivotParamSlices.get(2);
		int paddingSize = String.format("%d", (int) ((end-start)/(step))).length();
		for (double i = 0; i*step < (end-start); i++) {
			for(Integer operandPos: slices.keySet()) {
				Value<?> itParameter = null;
				if (slices.get(operandPos).size() == 3) { //actual slice
					itParameter = new NumberValue(slices.get(operandPos).get(0) + i*slices.get(operandPos).get(2));
				} else if (slices.get(operandPos).size() == 2) { //factor
					int otherOperandParamPos = slices.get(operandPos).get(0).intValue();
					if (!slices.containsKey(otherOperandParamPos)) throw new RuntimeException("Invalid parameter index for this operation: " + operation.getReference());
					Double otherOperandParamValue = slices.get(otherOperandParamPos).get(0) + i*slices.get(otherOperandParamPos).get(2);
					Double factor = slices.get(operandPos).get(1);
					itParameter = new NumberValue(otherOperandParamValue * factor);
				} else if (slices.get(operandPos).size() == 1) { //single static value
					itParameter = new NumberValue(slices.get(operandPos).get(0));
				}
				if (operandPos >= operation.getOperands().size()) {
					Operation lastOperand = operation.getOperands().get(operation.getOperands().size()-1);
					if (!lastOperand.getIsVarArgs()) throw new RuntimeException();
					Operation newVarArgOperand = (Operation) lastOperand.clone();
					operation.getOperands().add(newVarArgOperand);
				}
				operation.getOperands().get(operandPos).setParameter(itParameter);
			}
			runsOutputs.add((NumericableMapValue) operation.run(targetStock, targetStock.getEventInfoOpsCompoOperation().getReference(), thisStartShift));
			inputsOperandsRefs.add(String.format("%0" + paddingSize + "d", (int)i) + "_" + operation.toFormulaeShort());
		}
		
		try {
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, runsOutputs, true, true);
			return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}
		
		return new DoubleArrayMapValue();
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		
	}

	@Override
	public String toFormulaeShort() {
		String valueAsString = ((OperationReferenceValue<?>)this.getOperands().get(0).getParameter()).getValueAsString();
		return valueAsString.substring(0, Math.min(5, valueAsString.length()));
	}
	
	

}
