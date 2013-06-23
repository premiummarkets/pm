package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

@XmlRootElement
public class TalibMacdOperation extends TalibOperation {
	
	public TalibMacdOperation() {
		super(
				"macd__", "Macd on any historical data series.", 
				new NumberOperation("number","macdFastPeriod","macd Fast period", new NumberValue(12.0)), 
				new NumberOperation("number","macdSlowPeriod","macd Slow period",  new NumberValue(26.0)), 
				new NumberOperation("number", "macdSignalPeriod", "macd Signal period",  new NumberValue(9.0)),
				new DoubleMapOperation()
			 );
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"macd","signal"})));
	}
	
	public TalibMacdOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {

		//Param check
		Integer fastPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer signalPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);

		//Calc
		int endIdx = data.size()-1;
		int startIdx = 0;

		double[] inReal = mapToArray(data);
		double[] macd = new double[inReal.length - slowPeriod +1];
		double[] signal = new double[inReal.length - slowPeriod +1];
		
		double[] history = new double[inReal.length - slowPeriod +1];
		RetCode rc = TalibCoreService.getCore().macd(startIdx, endIdx, inReal, fastPeriod, slowPeriod, signalPeriod, outBegIdx, outNBElement, macd, signal, history);
		if (!rc.equals(RetCode.Success)) throw new TalibException("", new Exception());

		Set<Date> dateKeySet = data.keySet();
	
		if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("signal")) {
			return arrayToMap(dateKeySet, signal, outBegIdx.value);
		} else {
			return arrayToMap(dateKeySet, macd, outBegIdx.value);
		}
		
	}

}
