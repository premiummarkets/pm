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
public class TalibSmaOperation extends TalibOperation {
	
	public TalibSmaOperation() {
		super("sma__", "Sma on any on any historical data series.",  
				new NumberOperation("number","smaPeriod","Sma period", new NumberValue(200.0)), 
			    new DoubleMapOperation());
	}
	
	public TalibSmaOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {

		//Param check
		Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);

		//Calc
		int lag = 0;//TODO ??
		int endIdx = data.size()-1;
		int startIdx = 0;

		double[] inReal = mapToArray(data);

		double[] sma;

		if (period == 1) {

			sma = Arrays.copyOfRange(inReal, startIdx, endIdx);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;

		} else {

			sma = new double[inReal.length - period +1];
			RetCode rc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, sma);
			if (!rc.equals(RetCode.Success)) throw new TalibException("", new Exception());

		}

		Set<Date> dateKeySet = data.keySet();
		int shift = outBegIdx.value - lag;
		SortedMap<Date, Double> smaMap = arrayToMap(dateKeySet, sma, shift);
		return smaMap;
	}
	

}
