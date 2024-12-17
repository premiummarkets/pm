package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

public class MetaOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(MetaOperation.class);
	
	public MetaOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public MetaOperation() {
		this("meta", "Takes any function formulae, as prepared, with '?' in place of numeric parameters and its numeric parameters as input.", 
				new StringOperation("string", "formulae", "Prepared formulae. Ex sma(?, close)", new StringValue("sma(?, close)")),
				new NullOperation("anyValue", "Parameter values for each ?. Should be a leaf operation with value representable as string like String, Number.."));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisOperandsStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String preparedFormula = ((StringValue) inputs.get(0)).getValue(targetStock);
		List<String> parametersInPlace = inputs.subList(1, inputs.size()).stream().map(n -> ((StringableValue)n).getAsStringable()).collect(Collectors.toList());
		
		String formula = preparedFormula;
		for(String paramInPlace: parametersInPlace) {
			formula = formula.replaceFirst("\\?", paramInPlace);
		}
		
		ParameterizedBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		
		Checksum crc32 = new CRC32();
		byte[] formulaeBytes = formula.getBytes();
		crc32.update(formulaeBytes, 0, formulaeBytes.length);
		long formulaeCheckSum = crc32.getValue();
		String operationNewId = "meta_" + formulaeCheckSum + "_" + UUID.randomUUID();
		
		try {
			
			Operation operation = parameterizedOperationBuilder.buildOneTimeOperation(operationNewId, formula);
			LOGGER.info("Running meta: " + operation.getReference() + " with formulea: " + formula + " and operand output shift: " + thisOperandsStartShift);
			Value<?> output = operation.run(targetStock, thisCallStack, thisOperandsStartShift);
			
			return output;
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		return new DoubleMapValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		
		List<Operation> subList = getOperands().subList(1, getOperands().size()-1);
		String parameters = "";
		for (int i = 0; i < subList.size(); i++) {
			Operation operandI = subList.get(i);
			Value<?> parameterValue = operandI.getOrRunParameter(targetStock, thisCallStack)
										.orElse(new StringValue(operandI.toFormulaeShort(targetStock, thisCallStack)));
			String ele = ((StringableValue) parameterValue).getValue(targetStock).toString();
			if (parameterValue instanceof NumberValue) {
				ele = Long.valueOf(Math.round(((NumberValue) parameterValue).getNumberValue().doubleValue())).toString();
			}
			if (parameterValue instanceof BooleanValue || (parameterValue instanceof StringValue && ((StringValue)parameterValue).isBoolean()) ) {
				ele = ele.substring(0,1);
			}
			parameters = parameters + "_" + ele;
		}
		
		return "meta" + parameters;
		
	}

	//XXX not exact
	@Override
	public Value<?> emptyValue() {
		return new DoubleMapValue();
	}
	
	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
	}

	@Override
	public void interrupt() throws Exception {
	}

	@Override
	public boolean isDataShiftSensitive() {
		return true;
	}

	@Override //This would apply only if the output Value is a MapValue
	public boolean isForbidThisParameterValue() {
		return true;
	}
	
	

}
