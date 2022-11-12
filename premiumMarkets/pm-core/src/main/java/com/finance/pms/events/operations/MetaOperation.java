package com.finance.pms.events.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.antlr.NextToken;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

public class MetaOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(MetaOperation.class);
	
	public MetaOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public MetaOperation() {
		this("meta", "Takes any function formulae, as prepared, with '?' in place of numeric parameters and its numeric parameters as input.", 
				new StringOperation("string", "formulae", "Prepared formulae. Ex sma(?, close)", new StringValue("sma(?, close)")),
				new NumberOperation("number", "parameter", "Parameter to put in palce in the formulae", new NumberValue(14.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String preparedFormula =  ((StringValue) inputs.get(0)).getValue(targetStock);
		List<Number> parametersInPlace = inputs.subList(1, inputs.size()).stream().map(n -> ((NumberValue) n).getValue(targetStock)).collect(Collectors.toList());
		
		String formula = preparedFormula;
		for(Number paramInPlace: parametersInPlace) {
			formula = formula.replaceFirst("\\?", paramInPlace.toString());
		}
		
		ParameterizedBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		String operationNewId = "meta_" + UUID.randomUUID();
		try {
			
			NextToken checkNextToken = parameterizedOperationBuilder.checkNextToken(formula);
			if (checkNextToken != null) throw new RuntimeException("Invalid formulae. " + operationNewId + ". Formula: " + formula + ". Error: " + checkNextToken);
			Operation existingOperation = parameterizedOperationBuilder.getCurrentOperations().get(operationNewId);
			if (existingOperation != null) throw new RuntimeException("Operation already exists. " + operationNewId + ": " + formula);
			
			LOGGER.info("Adding formulae. " + operationNewId + ": " + formula);
			parameterizedOperationBuilder.addFormula(operationNewId, formula);

			Operation operation = parameterizedOperationBuilder.getCurrentOperations().get(operationNewId);
			Value<?> output = operation.run(targetStock, targetStock.getEventInfoOpsCompoOperation().getReference(), thisStartShift);
			
			return output;
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		} finally {
			LOGGER.info("Destroying formulae. " + operationNewId + ": " + formula);
			try {
				parameterizedOperationBuilder.destroyFormula(operationNewId);
			} catch (IOException e) {
				LOGGER.error(e, e);
			}
		}
		
		return new DoubleMapValue();
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public String toFormulaeShort() {
		
		List<Operation> subList = getOperands().subList(1, getOperands().size()-1);
		String parameters = "";
		for (int i = 0; i < subList.size(); i++) {
			Value<?> parameterValue = subList.get(i).getParameter();
			String ele = ((StringableValue) parameterValue).getValueAsString();
			if (parameterValue instanceof NumberValue) {
				ele = Long.valueOf(Math.round(((NumberValue) parameterValue).getNumberValue().doubleValue())).toString();
			}
			parameters = parameters + "_" + ele;
		}
		
		return "meta" + parameters;
		
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

}
