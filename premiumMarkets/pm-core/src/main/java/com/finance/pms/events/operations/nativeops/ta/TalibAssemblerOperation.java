package com.finance.pms.events.operations.nativeops.ta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.AnyValueListValue;
import com.finance.pms.events.operations.nativeops.ArrayMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MATypeValue;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
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
				new ListOperation("parameters", "parameters and parameters slices", "[start, end, step[ or [x, factor] or [\"abc\"] or [any]. "
						+ "With inclusivity [start, end[ and step must be >= 1. "
						+ "Use [idx, factor] to make this parameter a factor of an other parameter indexed at idx."
						+ "Use [\"abc\"] for constant string parameters. [any] for any other constant parameter", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public void setOperands(ArrayList<Operation> overridingOperands) throws IllegalArgumentException {
		Value<?> parameter = overridingOperands.get(OPS_INDEX).getParameter();
		if (parameter != null) ((OperationReferenceValue<?>) parameter).setIsUsedAsClone(true);
		super.setOperands(overridingOperands);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int assemblerOutputStartShift, int assemblerInputStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String assemblerGroupName = ((StringValue) inputs.get(0)).getValue(targetStock);
		assemblerGroupName = ("NONE".equals(assemblerGroupName))?"":"ta-" + assemblerGroupName + "_";
		
		Operation assembledOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(OPS_INDEX)).getValue(targetStock).clone();
		
		@SuppressWarnings("unchecked")
		List<List<StringableValue>> parameters = inputs.subList(PARAMS_START_IDX, inputs.size()).stream().map(v -> ((AnyValueListValue<StringableValue>) v).getValue(targetStock)).collect(Collectors.toList());

		SortedMap<Integer, List<Double>> slices = new TreeMap<>();
		ArrayList<Operation> assembledOpOperands = new ArrayList<>(assembledOperationClone.getOperands());
		Operation operand = assembledOpOperands.get(0); //Safe init
		for (int parametersSlicePos = 0; parametersSlicePos < parameters.size(); parametersSlicePos++) {
			
			if (parametersSlicePos < assembledOperationClone.getOperands().size()) {
				operand = assembledOperationClone.getOperands().get(parametersSlicePos);
			} else { //varargs
				operand = (Operation) assembledOperationClone.getOperands().get(assembledOperationClone.getOperands().size()-1).clone();
				if (!operand.getIsVarArgs()) throw new RuntimeException("Number of parameters miss match. The last parameter should be varargs.");
				assembledOpOperands.add(operand);
			}
			
			//parameters.get(parametersSlicePos).size() > 1
			//number operand or undefined operand with a sliced parameter (which would mean a number is expected)
			//if ((operand instanceof NumberOperation || operand instanceof NumbererOperation || operand instanceof NullOperation) && parameters.get(parametersSlicePos).size() > 1) {
			if (parameters.get(parametersSlicePos).size() > 1) { //Number sliced
				slices.put(parametersSlicePos, parameters.get(parametersSlicePos).stream().map(v -> ((NumberValue)v).getValue(targetStock).doubleValue()).collect(Collectors.toList()));
			//parameters.get(parametersSlicePos).size() == 1
			} else if (operand instanceof NullOperation) {
				Operation typedOperand;
				Object parameterValue = parameters.get(parametersSlicePos).get(0);
				if (parameterValue instanceof NumberValue) {
					typedOperand = new NumberOperation();
				} 
				else if (parameterValue instanceof StringValue) {
					typedOperand = new StringOperation();
				}
				else if (parameterValue instanceof OperationReferenceValue) {
					typedOperand = new OperationReferenceOperation();
				}
				else if (parameterValue instanceof MATypeValue) {
					typedOperand = new MATypeOperation();
				}
				else { //Not supported as parameter will be reset in the cloning process at each iteration
					throw new NotImplementedException("FIXME: Parameter does not match a leaf operation: " + parameterValue);
				}
				typedOperand.setParameter((Value<?>) parameterValue);
				assembledOpOperands.set(parametersSlicePos, typedOperand);
			} else if (operand instanceof MapOperation) { //Map operand
				//XXX maps passed in ListOperation have already been calculated up flow but with inaccurate time boundaries //FIXME could use OperationReferences
				assembledOpOperands.set(parametersSlicePos, getOperands().get(parametersSlicePos + PARAMS_START_IDX).getOperands().get(0)); //XXX We reset the initial operation as it was before up flow inputs calculation
			} else if (operand instanceof LeafOperation) { //Any other leaf operand
				operand.setParameter((Value<?>) parameters.get(parametersSlicePos).get(0));
			} else { //Not supported as parameter will be reset in the cloning process at each iteration
				throw new NotImplementedException("FIXME: Parameter will be reset in the cloning process at each iteration: " + operand);
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
		
		ExecutorService executor = CalculateThreadExecutor.getRandomInfiniteExecutorInstance();
		for (double i = 0; i*step < (end-start); i++) {
			
			Operation iterationOperationClone = (Operation) assembledOperationClone.clone(); //Will clone operation and its operand as well as leaf parameters (String, Number ..).
			
			String paramsStringInfo = "";
			for(Integer operandPos: slices.keySet()) {
				Value<?> itParameter = null;
				if (slices.get(operandPos).size() == 3) { //actual slice
					itParameter = new NumberValue(slices.get(operandPos).get(0) + i*slices.get(operandPos).get(2));
				} else if (slices.get(operandPos).size() == 2) { //factor
					int otherOperandParamPos = slices.get(operandPos).get(0).intValue();
					if (!slices.containsKey(otherOperandParamPos)) {
						throw new RuntimeException("Invalid parameter index " + otherOperandParamPos + " in " + slices + ", for this operation: " + iterationOperationClone.getReference());
					}
					Double otherOperandParamValue = slices.get(otherOperandParamPos).get(0) + i*slices.get(otherOperandParamPos).get(2);
					Double factor = slices.get(operandPos).get(1);
					itParameter = new NumberValue(otherOperandParamValue * factor);
				} else if (slices.get(operandPos).size() == 1) { //single static value
					itParameter = new NumberValue(slices.get(operandPos).get(0));
				}
				paramsStringInfo = paramsStringInfo + "," + itParameter.getValue(targetStock);
//				if (operandPos >= iterationOperationClone.getOperands().size()) { //FIXME?? //XXX can I be here as per the assembledOpOperands init??
//					Operation lastOperand = iterationOperationClone.getOperands().get(iterationOperationClone.getOperands().size()-1);
//					if (!lastOperand.getIsVarArgs()) throw new RuntimeException();
//					Operation newVarArgOperand = (Operation) lastOperand.clone();
//					iterationOperationClone.getOperands().add(newVarArgOperand);
//				}
				iterationOperationClone.getOperands().get(operandPos).setParameter(itParameter);
			}
			//LOGGER.info("Running: " + iterationOperationClone.getReference() + " with params " + paramsStringInfo);
			//runsOutputs.add((NumericableMapValue) iterationOperationClone.run(targetStock, targetStock.getEventInfoOpsCompoOperation().getReference(), thisStartShift));
			final String fParamsStringInfo = paramsStringInfo;
			Future<NumericableMapValue> iterationFuture = executor.submit(new Callable<NumericableMapValue>() {
				@Override
				public NumericableMapValue call() throws Exception {
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					
					LOGGER.info(
							"Running assemblee: " + iterationOperationClone.getReference() + " with params " + fParamsStringInfo + " and assemblee output shift " + assemblerInputStartShift +
							". From " + df.format(targetStock.getStartDate(assemblerInputStartShift)) + " to " + df.format(targetStock.getEndDate()));
					
					NumericableMapValue run = (NumericableMapValue) iterationOperationClone.run(targetStock, thisCallStack, assemblerInputStartShift);
					
					LOGGER.info("Yield: " + iterationOperationClone.getReference() + " with params " + fParamsStringInfo + " and assemblee output shift " + assemblerInputStartShift + ": " + run);
					
					return run;
				}
			});
			futures.add(iterationFuture);
			inputsOperandsRefs.add(assemblerGroupName + String.format("%0" + paddingSize + "d", (int)i) + "_" + iterationOperationClone.toFormulaeShort(targetStock));
		}
		
		List<NumericableMapValue> runsOutputs = futures.stream().map(f -> {
			try {
				return f.get();
			} catch (Exception e) {
				LOGGER.error(e, e);
				return new DoubleMapValue();
			}
		}).collect(Collectors.toList());
		
		try {
			Map<InputToArrayReturn, SortedMap<Date, double[]>> inputListToArray = ValueManipulator.inputListToArray(targetStock, runsOutputs, false, true);
			SortedMap<Date, double[]> factorisedInput = inputListToArray.get(InputToArrayReturn.RESULTS);
			
			//Logs and errors
			if (!inputListToArray.get(InputToArrayReturn.TRAILINGNANS).isEmpty()) {
				String orElse = inputListToArray.get(InputToArrayReturn.TRAILINGNANS).entrySet().stream().map(e -> e.getKey() + ": " + Arrays.asList(Arrays.toString(e.getValue())).toString())
													.reduce((a, e) -> a + " " + e)
													.orElse("");
				LOGGER.warn("FIXME (trailing nans) " + this.getReference() + ": " + this.toFormulae(targetStock, thisCallStack) + " has trailing NaNs " + 
						inputListToArray.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().size())
															.reduce((a, e) -> a + " " + e)
															.orElse("") + ": " +
						orElse.substring(0, Math.min(orElse.length(), 15000)));
			}
			if (!inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).isEmpty()) {
				String orElse = inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS)
												.entrySet().stream().map(e -> e.getKey() + ": " + Arrays.asList(Arrays.toString(e.getValue())).toString())
																	.reduce((a, e) -> a + " " + e)
																	.orElse("");
				throw new Exception("FIXME (other nans) " + this.getReference() + ": " + this.toFormulae(targetStock, thisCallStack) + " has NaNs " + 
						inputListToArray.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().size())
															.reduce((a, e) -> a + " " + e)
															.orElse("") + ": " +
						orElse.substring(0, Math.min(orElse.length(), 15000)));
			}
			//
			
			return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		}
		
		return new DoubleArrayMapValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		Operation operandOpsIdx = this.getOperands().get(OPS_INDEX);
		String valueAsString = ((OperationReferenceValue<?>) operandOpsIdx.getOrRunParameter(targetStock).orElse(new StringValue(operandOpsIdx.toFormulaeShort(targetStock))))
								.getAsStringable().replaceAll("\\$", "");
		return valueAsString.substring(0, Math.min(5, valueAsString.length()));
	}

	@Override
	public void interrupt() throws Exception {
		CalculateThreadExecutor.getRandomInfiniteExecutorInstance().shutdownNow();
	}

}
