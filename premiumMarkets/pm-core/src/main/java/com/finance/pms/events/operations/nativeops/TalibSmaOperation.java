package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;

@XmlRootElement
public class TalibSmaOperation extends TalibOperation {
	
	public TalibSmaOperation() {
		super("sma__", "SMA on any on any historical data series.",  
				new NumberOperation("number","smaPeriod","SMA period", new NumberValue(200.0)), 
				new StringOperation("boolean","doFixlag","artificially fix the SMA lag", new StringValue("FALSE")), 
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
		Boolean fixLag = Boolean.valueOf(((StringValue)inputs.get(1)).getValue(targetStock));
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		SortedMap<Date, Double> smoothed = null;
		if (data.size() > period) {
			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(period);
			smoothed = smaSmoother.sSmooth(data, fixLag);
		} else {
			smoothed = new TreeMap<Date, Double>();
		}

		return smoothed;
	}
	
	@Override
	public int operationStartDateShift() {
		return (((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue() + getOperands().get(2).operationStartDateShift())*7/5;
	}

}
