/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MATypeValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumbererOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.TalibOperation;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator.ConstantNameNType;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibGenericOperation extends TalibOperation {

	/**
	 * TalibGenericOperation are not state less as instantiated on the fly when parsing the ta_lib
	 * This state is the reflection of their definition, not their values
	 */
	private final List<ConstantNameNType> inConstantsNames;
	private final List<String> inDataNames;
	private final List<String> outDataNames;

	private Method method;

	public TalibGenericOperation(String reference, String description, Method method, List<ConstantNameNType> inConstantsNames, List<String> inDataNames, ArrayList<String> outDataNames) {
		super(reference, description);
		this.inConstantsNames = inConstantsNames;
		this.inDataNames = inDataNames;
		this.outDataNames = outDataNames;
		this.method = method;

		ArrayList<Operation> overridingOperands = new ArrayList<Operation>();
		for (ConstantNameNType inConstantName : inConstantsNames) {
			if (inConstantName.type.equals(MAType.class)) {
				MATypeOperation maType = new MATypeOperation("moving average type", inConstantName.name, inConstantName.name + ". One of " + EnumSet.allOf(MAType.class), null);
				overridingOperands.add(maType);
			} else {
				NumberOperation constant = new NumberOperation("number", inConstantName.name, inConstantName.name + ". " + inConstantName.type.getSimpleName(), null);
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
		int constantArgIdx = inDataNamesArgsIdx + inDataNames.size();
		int outEleIdx = constantArgIdx + inConstantsNames.size();
		int outDataArgIdx = outEleIdx + 2;
		int outSize = outDataNames.size();

		Object[] args = new Object[outDataArgIdx + outSize];

		//Constants
		List<Object> inConstants = new ArrayList<Object>();

		for (int i = 0; i < inConstantsNames.size(); i++) {
			Value<?> value = inputs.get(i);
			if (value instanceof NumberValue) {
				if (inConstantsNames.get(i).type.equals(Double.TYPE)) {
					Double doubleValue = ((NumberValue) value).getValue(targetStock).doubleValue();
					inConstants.add(doubleValue);
				} else {
					Number numberValue = ((NumberValue) value).getValue(targetStock);
					Integer intValue = intRounding(numberValue);
					inConstants.add(intValue);
				}
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
		//		int startIdx = 0;
		//		int endIdx = Integer.MAX_VALUE;
		//		Set<Date> dateKeySet = new TreeSet<Date>();
		//		List<double[]> inDatas = new ArrayList<double[]>();
		//		for (int i = 0; i < inDataNames.size(); i++) {
		//			SortedMap<Date, Double> inData = ((NumericableMapValue) inputs.get(inConstantsNames.size()+i)).getValue(targetStock);
		//			endIdx  = Math.min(endIdx, inData.size()-1);
		//			dateKeySet.addAll(inData.keySet());
		//			inDatas.add(mapToArray(inData));
		//			args[i+inDataNamesArgsIdx]=inDatas.get(i);
		//		}
		//
		//		args[0] = startIdx;
		//		args[1] = endIdx;

		List<SortedMap<Date, Double>> inDataMaps = new ArrayList<>();
		List<Date> smallestDateKeySet = new ArrayList<Date>();
		{
			//Grab all
			Set<Date> fullDateKeySet = new TreeSet<Date>();
			for (int i = 0; i < inDataNames.size(); i++) {
				SortedMap<Date, Double> inDataMap = ((NumericableMapValue) inputs.get(inConstantsNames.size() + i)).getValue(targetStock);
				fullDateKeySet.addAll(inDataMap.keySet());
				inDataMaps.add(inDataMap);
			}

			//Smallest discriminatory
			for(Date date : fullDateKeySet) {
				Boolean add = true;
				for (int i = 0; i < inDataNames.size(); i++) {
					SortedMap<Date, Double> sortedMap = inDataMaps.get(i);
					Double doubleAtDate = sortedMap.get(date);
					if (doubleAtDate == null || doubleAtDate.isNaN()) {
						add = false;
						continue;
					}
				}
				if (add) {
					smallestDateKeySet.add(date);
				}
			}
		}

		//Map to double[] only the smallest date key set
		List<double[]> inDatas = new ArrayList<double[]>();
		for (int i = 0; i < inDataNames.size(); i++) {
			SortedMap<Date, Double> inData = inDataMaps.get(i);
			inDatas.add(mapToArray(smallestDateKeySet, inData));
			args[i+inDataNamesArgsIdx] = inDatas.get(i);
		}

		int startIdx = 0;
		int endIdx = smallestDateKeySet.size()-1;

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

		if (!rc.equals(RetCode.Success)) throw new TalibException(this.getReference() + ": " + this.toFormulae(), new Exception());

		//N selector ~ N outputs
		if (getAvailableOutputSelectors().isEmpty()) {

			if (outDataNames.get(0).contains("Integer")) {
				return arrayToMap(smallestDateKeySet, (int[]) outDatas.get(0), outBegIdx.value);
			} else {
				return arrayToMap(smallestDateKeySet, (double[]) outDatas.get(0), outBegIdx.value);
			}

		} else {
			for (int i = 0; i < outSize; i++) {//Several outputs available (we assume double[] ...)//XXX
				if (getOutputSelector().equals(outDataNames.get(i))){
					return arrayToMap(smallestDateKeySet, (double[]) outDatas.get(i), outBegIdx.value);
				}
			}
		}

		throw new TalibException("Ooops", new Exception());
	}

	private Integer intRounding(Number numberValue) {
		return (int) Math.round(numberValue.doubleValue());
	}
	
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {

		int thisOperationStartShift = 0;
		for (int i = 0; i < inConstantsNames.size(); i++) {
			Operation numberOperand = getOperands().get(i);
			Value<?> value = numberOperand.getOrRunParameter(targetStock).orElse(new NumberValue(0.0));
			if (value instanceof NumberValue) {
				int constant = ((NumberValue)value).getValue(targetStock).intValue();
				thisOperationStartShift = thisOperationStartShift + constant;
			}
		}

		return thisOperationStartShift+1;
	}
	
	@Override
	public String toFormulaeShort() {
		String refa24z = getReference().substring(0,1) + (getReference().length() -2) + getReference().substring(getReference().length() -1); 
//		String contants = getOperands().subList(0, inConstantsNames.size()).stream()
//				.map(c -> ((StringableValue) c.getParameter()).getValueAsString())
//				.reduce("", (r, e) -> r + "_" + e, (a, b) -> a + "_" + b);
		List<Operation> subList = getOperands().subList(0, inConstantsNames.size());
		String contants = "";
		for (int i = 0; i < subList.size(); i++) {
			Value<?> optEle = subList.get(i).getOrRunParameter(null).orElseThrow();
			String ele = ((StringableValue) optEle).getValueAsString();
			if (inConstantsNames.get(i).type.equals(Integer.TYPE)) {
				ele = intRounding(((NumberValue) optEle).getNumberValue()).toString();
			}
			contants = contants + "_" + ele;
		}
		List<Operation> ops = getOperands().subList(inConstantsNames.size(), inConstantsNames.size() + inDataNames.size());
		String opsFormulaeShort = toFormulaeShort(ops);
		return refa24z + contants + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

}
