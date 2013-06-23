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
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MATypeValue;
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
		
		this.setAvailableOutputSelectors(outDataNames);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {
		
		int outSize = (getAvailableOutputSelectors().isEmpty())?1:outDataNames.size();
		Object[] args = new Object[2+inDataNames.size()+inConstantsNames.size()+2+outSize];
		int argShift = 2;
			
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
			args[i+argShift]=inDatas.get(i);
		}
		argShift = argShift + inDataNames.size();
		
		args[0] = startIdx;
		args[1] = endIdx;
		
		//Param check
		List<Object> inConstants = new ArrayList<Object>();
		for (int i = 0; i < inConstantsNames.size(); i++) {
			Value<?> value = inputs.get(i);
			if (value instanceof NumberValue) {
				inConstants.add(((NumberValue) value).getValue(targetStock).intValue());
				args[i+argShift] = inConstants.get(i);
			}
			else if (value instanceof MATypeValue) {
				inConstants.add(((MATypeValue) value).getValue(targetStock));
				args[i+argShift] = inConstants.get(i);
			}
			else {
				throw new TalibException("In constant type not supported :" + value, new Throwable());
			}
		}
		argShift = argShift + inConstantsNames.size();
		
		args[argShift] = outBegIdx;
		args[argShift+1] = outNBElement;
		argShift = argShift + 2;
				
		//N outputs
		List<double[]> outDatas = new ArrayList<double[]>();
		if (getAvailableOutputSelectors().isEmpty()) { //only one output available
			outDatas.add(new double[endIdx-startIdx]);
			args[argShift] = outDatas.get(0);
		} else {
			for (int i = 0; i < outSize; i++) { //several outputs available
				outDatas.add(new double[endIdx-startIdx]);
				args[i+argShift] = outDatas.get(i);
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
			return arrayToMap(dateKeySet, outDatas.get(0), outBegIdx.value);
		} else {
			for (int i = 0; i < outSize; i++) {
				if (getOutputSelector().equals(outDataNames.get(i))){
					return arrayToMap(dateKeySet, outDatas.get(i), outBegIdx.value);
				}
			}
		}
		
		throw new TalibException("Ooops", new Exception());
	}

}
