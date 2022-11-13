package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.google.common.collect.Lists;

public class TalibAssemblerOperation extends ArrayMapOperation {
	
	private static final int OPS_INDEX = 1;
	private static final int PARAMS_START_IDX = 2;
	
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
				new StringOperation("string", "assemblerGroupName", "ta- Assembler group name", new StringValue("")),
				new OperationReferenceOperation("operationReference", "operation reference", "Operation to iterate upon", null),
				new ListOperation("parameters", "parameters and parameters slices", "[start, end, step] or [x, factor] or [\"abc\"] or [any]. "
						+ "Use [x, factor] to make this parameter a factor of an other parameter indexed at x. "
						+ "Use [\"abc\"] for constant string parameters. [any] for any other static parameter", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String assemblerGroupName = ((StringValue) inputs.get(0)).getValue(targetStock);
		assemblerGroupName = ("NONE".equals(assemblerGroupName))?"":"ta-" + assemblerGroupName + "_";
		
		Operation assembledOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(OPS_INDEX)).getValue(targetStock).clone();
		
		@SuppressWarnings("unchecked")
		List<List<Value<?>>> parameters = inputs.subList(PARAMS_START_IDX, inputs.size()).stream().map(v -> ((AnyValueListValue<Value<?>>) v).getValue(targetStock)).collect(Collectors.toList());

		SortedMap<Integer, List<Double>> slices = new TreeMap<>();
		ArrayList<Operation> assembledOpOperands = new ArrayList<>(assembledOperationClone.getOperands());
		Operation operand = assembledOpOperands.get(0); //Safe init
		for (int parametersSlicePos = 0; parametersSlicePos < parameters.size(); parametersSlicePos++) {
			if (parametersSlicePos < assembledOperationClone.getOperands().size()) {
				operand = assembledOperationClone.getOperands().get(parametersSlicePos);
			} 
			if (operand instanceof NumberOperation) {
				slices.put(parametersSlicePos, parameters.get(parametersSlicePos).stream().map(v -> ((NumberValue)v).getValue(targetStock).doubleValue()).collect(Collectors.toList()));
			} else if (operand instanceof MapOperation) {
				// MapOperation) {//XXX maps passed in ListOperation have already been calculated up flow but with inaccurate time boundaries
				//FIXME could use OperationRefernces/MetaOperations??
				//operand.setParameter(getOperands().get(parametersSlicePos +1).getOperands().get(0).run(targetStock,"squashed  => " + this.shortOutputReference(),thisStartShift));
				assembledOpOperands.set(parametersSlicePos, getOperands().get(parametersSlicePos + PARAMS_START_IDX).getOperands().get(0)); //XXX We get reset the initial operation as it was before up flow inputs calculation
			} else {
				operand.setParameter(parameters.get(parametersSlicePos).get(0));
			}
		}
		assembledOperationClone.getOperands().clear();
		assembledOperationClone.setOperands(assembledOpOperands);
		
		List<Future<NumericableMapValue>> futures = new ArrayList<>();
		List<String> inputsOperandsRefs = new ArrayList<String>();
		List<Double> pivotParamSlices = slices.values().stream().filter(s -> s.size() == 3).findFirst().orElse(Lists.newArrayList(0.0,1.0,1.0));
		Double start = pivotParamSlices.get(0);
		Double end = pivotParamSlices.get(1);
		Double step = pivotParamSlices.get(2);
		int paddingSize = String.format("%d", (int) ((end-start)/(step))).length();
		
		ExecutorService executor = Executors.newCachedThreadPool(); 
		for (double i = 0; i*step < (end-start); i++) {
			Operation iterationOperationClone = (Operation) assembledOperationClone.clone();
			String paramsStringInfo = "";
			for(Integer operandPos: slices.keySet()) {
				Value<?> itParameter = null;
				if (slices.get(operandPos).size() == 3) { //actual slice
					itParameter = new NumberValue(slices.get(operandPos).get(0) + i*slices.get(operandPos).get(2));
				} else if (slices.get(operandPos).size() == 2) { //factor
					int otherOperandParamPos = slices.get(operandPos).get(0).intValue();
					if (!slices.containsKey(otherOperandParamPos)) throw new RuntimeException("Invalid parameter index for this operation: " + iterationOperationClone.getReference());
					Double otherOperandParamValue = slices.get(otherOperandParamPos).get(0) + i*slices.get(otherOperandParamPos).get(2);
					Double factor = slices.get(operandPos).get(1);
					itParameter = new NumberValue(otherOperandParamValue * factor);
				} else if (slices.get(operandPos).size() == 1) { //single static value
					itParameter = new NumberValue(slices.get(operandPos).get(0));
				}
				paramsStringInfo = paramsStringInfo + "," + itParameter.getValue(targetStock);
				if (operandPos >= iterationOperationClone.getOperands().size()) {
					Operation lastOperand = iterationOperationClone.getOperands().get(iterationOperationClone.getOperands().size()-1);
					if (!lastOperand.getIsVarArgs()) throw new RuntimeException();
					Operation newVarArgOperand = (Operation) lastOperand.clone();
					iterationOperationClone.getOperands().add(newVarArgOperand);
				}
				iterationOperationClone.getOperands().get(operandPos).setParameter(itParameter);
			}
			//LOGGER.info("Running: " + iterationOperationClone.getReference() + " with params " + paramsStringInfo);
			//runsOutputs.add((NumericableMapValue) iterationOperationClone.run(targetStock, targetStock.getEventInfoOpsCompoOperation().getReference(), thisStartShift));
			final String fParamsStringInfo = paramsStringInfo;
			Future<NumericableMapValue> iterationFuture = executor.submit(new Callable<NumericableMapValue>() {
				@Override
				public NumericableMapValue call() throws Exception {
					LOGGER.info("Running: " + iterationOperationClone.getReference() + " with params " + fParamsStringInfo);
					NumericableMapValue run = (NumericableMapValue) iterationOperationClone.run(targetStock, targetStock.getEventInfoOpsCompoOperation().getReference(), thisStartShift);
					LOGGER.info("Done: " + iterationOperationClone.getReference() + " with params " + fParamsStringInfo);
					return run;
				}
			});
			futures.add(iterationFuture);
			inputsOperandsRefs.add(assemblerGroupName + String.format("%0" + paddingSize + "d", (int)i) + "_" + iterationOperationClone.toFormulaeShort());
		}
		
		executor.shutdown();
		List<NumericableMapValue> runsOutputs = futures.stream().map(f -> {
			try {
				return f.get();
			} catch (Exception e) {
				LOGGER.error(e, e);
				return new DoubleMapValue();
			}
		}).collect(Collectors.toList());
		
		try {
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, runsOutputs, false, true);
			return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}
		
		return new DoubleArrayMapValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
		
	}

	@Override
	public String toFormulaeShort() {
		String valueAsString = ((OperationReferenceValue<?>)this.getOperands().get(OPS_INDEX).getParameter()).getValueAsString();
		return valueAsString.substring(0, Math.min(5, valueAsString.length()));
	}
	
	

}
