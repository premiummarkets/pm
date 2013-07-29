package com.finance.pms.events.operations.nativeops.talib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MATypeValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.TalibOperation;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibGenericOperation extends TalibOperation {
	
	private List<String> inConstantsNames;
	private List<String> inDataNames;
	private List<String> outDataNames;

	private Method method;

	public TalibGenericOperation(String reference, String description,  Method method, List<String> inConstantsNames, List<String> inDataNames, ArrayList<String> outDataNames) {
		super(reference, description);
		this.inConstantsNames = inConstantsNames;
		this.inDataNames = inDataNames;
		this.outDataNames = outDataNames;
		this.method = method;
		
		ArrayList<Operation> overridingOperands = new ArrayList<Operation>();
		for (String inConstantName : inConstantsNames) {
			if (inConstantName.contains("MAType")) {
				MATypeOperation maType = new MATypeOperation("moving average type", inConstantName, inConstantName + ". One of "+EnumSet.allOf(MAType.class), null);
				overridingOperands.add(maType);
			} else {
				NumberOperation constant = new NumberOperation("number", inConstantName, inConstantName, null);
				overridingOperands.add(constant);
			}
		}
		for (String inDataName : inDataNames) {
			DoubleMapOperation data = new DoubleMapOperation(inDataName);
			overridingOperands.add(data);
		}
		this.setOperands(overridingOperands);
		
		if (outDataNames.size() > 1) this.setAvailableOutputSelectors(outDataNames);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {
		
		int inDataNamesArgsIdx = 2;
		int constantArgIdx = inDataNamesArgsIdx+inDataNames.size();
		int outEleIdx = constantArgIdx + inConstantsNames.size();
		int outDataArgIdx = outEleIdx + 2;
		int outSize = outDataNames.size();
		
		Object[] args = new Object[outDataArgIdx + outSize];
		
		
		//Constants
		List<Object> inConstants = new ArrayList<Object>();

		for (int i = 0; i < inConstantsNames.size(); i++) {
			Value<?> value = inputs.get(i);
			if (value instanceof NumberValue) {
				int intValue = ((NumberValue) value).getValue(targetStock).intValue();
				inConstants.add(intValue);
				args[i + constantArgIdx] = inConstants.get(i);
			}
			else if (value instanceof MATypeValue) {
				inConstants.add(((MATypeValue) value).getValue(targetStock));
				args[i + constantArgIdx] = inConstants.get(i);
			}
			else {
				throw new TalibException("In constant type not supported :" + value, new Throwable());
			}
		}
		
		//N input data
		int startIdx = 0;
		int endIdx = Integer.MAX_VALUE;
		Set<Date> dateKeySet = new TreeSet<Date>();
		List<double[]> inDatas = new ArrayList<double[]>();
		for (int i = 0; i < inDataNames.size(); i++) {
			SortedMap<Date, Double> inData = ((DoubleMapValue) inputs.get(inConstantsNames.size()+i)).getValue(targetStock);
			endIdx  = Math.min(endIdx, inData.size()-1);
			dateKeySet.addAll(inData.keySet());
			inDatas.add(mapToArray(inData));
			args[i+inDataNamesArgsIdx]=inDatas.get(i);
		}
		
		args[0] = startIdx;
		args[1] = endIdx;
		
		//OutElements
		args[outEleIdx] = outBegIdx;
		args[outEleIdx+1] = outNBElement;
		
		//N outputs
		List<Object> outDatas = new ArrayList<Object>();
		if (getAvailableOutputSelectors().isEmpty()) { //only one output available (could be int[] or double[])
			
			if (outDataNames.get(0).contains("Integer")) {
				outDatas.add(new int[endIdx-startIdx+1]);
				args[outDataArgIdx] = outDatas.get(0);
			} else {
				outDatas.add(new double[endIdx-startIdx+1]);
				args[outDataArgIdx] = outDatas.get(0);
			}
			
		} else {
			for (int i = 0; i < outSize; i++) { //several outputs available (we assume double[] ...)//XXX
				outDatas.add(new double[endIdx-startIdx+1]);
				args[i+outDataArgIdx] = outDatas.get(i);
			}
		}

		RetCode rc;
		try {
			rc = (RetCode) method.invoke(TalibCoreService.getCore(), args);
		} catch (Exception e) {
			throw new TalibException("Ooops", e);
		} 
		
		if (!rc.equals(RetCode.Success)) throw new TalibException("", new Exception());

		//N selector ~ N outputs
		if (getAvailableOutputSelectors().isEmpty()) {
			
			if (outDataNames.get(0).contains("Integer")) {
				return arrayToMap(dateKeySet,  (int[]) outDatas.get(0), outBegIdx.value);
			} else {
				return arrayToMap(dateKeySet,  (double[]) outDatas.get(0), outBegIdx.value);
			}
			
		} else {
			for (int i = 0; i < outSize; i++) {//several outputs available (we assume double[] ...)//XXX
				if (getOutputSelector().equals(outDataNames.get(i))){
					return arrayToMap(dateKeySet, (double[]) outDatas.get(i), outBegIdx.value);
				}
			}
		}
		
		throw new TalibException("Ooops", new Exception());
	}

	@Override
	public int operationStartDateShift() {
		
		int thisOperationStartShift = 0;
		for (int i = 0; i < inConstantsNames.size(); i++) {
			Operation numberOperand = getOperands().get(i);
			if (numberOperand instanceof NumberOperation) {
				int constant = ((NumberValue)numberOperand.getParameter()).getValue(null).intValue();
				thisOperationStartShift = thisOperationStartShift + constant;
			}
		}
		
		int operandsOperationStartShift = 0;
		for (int i = inConstantsNames.size(); i < inDataNames.size() + inConstantsNames.size(); i++) {
			operandsOperationStartShift = Math.max(getOperands().get(i).operationStartDateShift(), operandsOperationStartShift);
		}
		
		return (thisOperationStartShift + operandsOperationStartShift)*7/5;
	}

}
