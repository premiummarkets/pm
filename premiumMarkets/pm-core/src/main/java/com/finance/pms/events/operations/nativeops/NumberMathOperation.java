package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class NumberMathOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(NumberMathOperation.class);
	
	public NumberMathOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public NumberMathOperation() {
		this("nMath", "Math calculus for unary numbers.", 
				new NumberOperation("number", "number", "Input number", new NumberValue(0.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sqrt"})));
	}

	public NumberMathOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumberValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		List<Number> numbers = inputs.stream().map(n -> ((NumberValue) n).getValue(targetStock)).collect(Collectors.toList());

		try {

			Double result = 0.0;
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("sqrt")) {
				result = Math.sqrt(numbers.get(0).doubleValue());
			}
			return new NumberValue(result);

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new NumberValue(0.0);
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
	}

}