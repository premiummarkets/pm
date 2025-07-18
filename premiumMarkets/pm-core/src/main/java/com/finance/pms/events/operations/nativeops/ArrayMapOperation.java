package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.calc.IndicatorStatsOperation;
import com.finance.pms.events.operations.nativeops.calc.OProfitOperation;
import com.finance.pms.events.operations.nativeops.calc.PortfolioOperation;
import com.finance.pms.events.operations.nativeops.calc.ProfitOperation;
import com.finance.pms.events.operations.nativeops.data.IOsAssemblerOperation;
import com.finance.pms.events.operations.nativeops.data.IOsLooseAssemblerOperation;
import com.finance.pms.events.operations.nativeops.ta.TalibAssemblerOperation;

@XmlSeeAlso({
	IOsAssemblerOperation.class, IOsLooseAssemblerOperation.class, IndicatorStatsOperation.class, TalibAssemblerOperation.class, 
	OProfitOperation.class, ProfitOperation.class, PortfolioOperation.class
	})
public abstract class ArrayMapOperation extends MapOperation implements MultiValuesOutput {
	
	public ArrayMapOperation() {
		super("multi historical data", "Multiple Time series of real historical data or resulting of calculations");
	}

	public ArrayMapOperation(String reference) {
		super(reference, reference);
	}

	public ArrayMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public ArrayMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public ArrayMapOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisStartShift, int thisAndOperandsStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((DoubleArrayMapValue)inputs.get(0));
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		String thisShort = getOperationReference().substring(0,1) + getOperationReference().chars()
						.filter(c -> Character.isUpperCase(c))
						.mapToObj(cu -> (char) cu)
						.reduce("", (r, e) -> r + e, (a, b) -> a + b);
		//String opsFormulaeShort = super.toFormulaeShort(targetStock);
		return thisShort; // + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
	
	@Override
	public int mainInputPosition() {
		return 0;
	}
	
	@Override
	public DoubleArrayMapValue emptyValue() {
		return new DoubleArrayMapValue();
	}

}
