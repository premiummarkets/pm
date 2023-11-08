package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class RandomizeStringOperation extends StringerOperation {
	
	public RandomizeStringOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public RandomizeStringOperation() {
		this("randomizeString", "Add a random suffix to the passed string",
				new StringOperation("string", "anyString", "Any string to be suffixed by a random uuid", new StringValue("")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String param = ((StringValue) inputs.get(0)).getValue(targetStock);
		return new StringValue(param + "_" + UUID.randomUUID());
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
		String thisShortName = "rds";
		String opsFormulaeShort = super.toFormulaeShort();
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
	
	@Override
	public boolean isParameterDataSensitive() {
		return true;
	}

}
