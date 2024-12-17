package com.finance.pms.events.operations.nativeops;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

public class TargetStockInfoOperation extends StringerOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(TargetStockInfoOperation.class);
	
	public TargetStockInfoOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public TargetStockInfoOperation() {
		//FIXME we don't use output selector in order to select/switch the returned value but an operand instead
		//Operations with no operands are actually considered as operations with an indeterministic (undeterministic) N number of operands with N > 0 ..
		this("ctx", "Return informations about the parent operation targeted stock. Implemented only: the stock symbol.",
		new StringOperation("string", "stockInfoRetreived", "Info retreived from the target stock", new StringValue("sym")));
		//setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"symbol"})));
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		try {
			String selector = ((StringValue) inputs.get(0)).getValue(targetStock);
			
			//if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("symbol")) {
			if (selector.equals("sym")) {
			    return new StringValue(targetStock.getStock().getSymbol());
			}
			
			if (selector.equals("ana")) {
			    return new StringValue(targetStock.getAnalysisName());
			}
			
			if (selector.equals("ind")) {
			    return new StringValue(targetStock.getEventInfoOpsCompoOperation().getReference());
			}
			
			if (selector.equals("uop")) {
			    return new StringValue(getUserOperationReference(thisCallStack));
			}
			
			if (selector.equals("str")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			    return new StringValue(sdf.format(targetStock.getStartDate(thisStartShift)));
			}
			
			if (selector.equals("end")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			    return new StringValue(sdf.format(targetStock.getEndDate()));
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		return new StringValue("NONE");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}
	
//	@Override
//	public boolean isForbidThisParameterValue() {
//		return true;
//	}

}
