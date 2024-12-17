package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumbererOperation;
import com.finance.pms.events.operations.nativeops.Value;

@XmlRootElement
public class NumberMathOperation extends NumbererOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(NumberMathOperation.class);
	
	public NumberMathOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public NumberMathOperation() {
		this("nMath", "Math calculus for unary numbers.", new NumberOperation("number", "number", "Input number", new NumberValue(0.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sqrt","add","sub","mult","div"})));
	}

	public NumberMathOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumberValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		List<Number> numbers = inputs.stream().map(n -> ((NumberValue) n).getValue(targetStock)).collect(Collectors.toList());

		try {

			Double result = 0.0;
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("sqrt")) {
				result = Math.sqrt(numbers.get(0).doubleValue());
			}
			if (outputSelector != null && outputSelector.equalsIgnoreCase("add")) {
				result = numbers.stream().map(e -> e.doubleValue()).reduce(0.0, (a, e) -> a + e);
			}
			if (outputSelector != null && outputSelector.equalsIgnoreCase("sub")) {
				result = numbers.stream().skip(1).map(e -> e.doubleValue()).reduce(numbers.get(0).doubleValue(), (a, e) -> a - e);
			}
			if (outputSelector != null && outputSelector.equalsIgnoreCase("mult")) {
				result = numbers.stream().map(e -> e.doubleValue()).reduce(1.0, (a, e) -> a * e);
			}
			if (outputSelector != null && outputSelector.equalsIgnoreCase("div")) {
				result = numbers.stream().skip(1).map(e -> e.doubleValue()).reduce(numbers.get(0).doubleValue(), (a, e) -> a/e);
			}
			return new NumberValue(result);

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new NumberValue(0.0);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

}
