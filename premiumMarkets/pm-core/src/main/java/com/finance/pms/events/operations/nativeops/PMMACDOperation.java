package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.MACD;

@XmlRootElement
public class PMMACDOperation extends PMIndicatorOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(PMMACDOperation.class);
	
	public PMMACDOperation() {
		super(
				"macd_", "House made MACD on close historical data",
				new NumberOperation("number","macdFastPeriod","Macd Fast period", new NumberValue(12.0)), 
				new NumberOperation("number","macdSlowPeriod","Macd Slow period",  new NumberValue(26.0)), 
				new NumberOperation("number", "macdSignalPeriod", "Macd Signal period",  new NumberValue(9.0))
			);
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"macd","history","signal"})));
	}
	
	public PMMACDOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	
	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer fastPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer signalPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		
		DoubleMapValue ret = new DoubleMapValue();
		try {
			MACD macd = new MACD(targetStock.getStock(), fastPeriod, slowPeriod, signalPeriod, targetStock.getStartDate(), targetStock.getEndDate(), null);
	
			if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("history")) {
				return outputToMap(targetStock, macd, macd.getHistory());
			}
			else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("signal")) {
				return outputToMap(targetStock, macd, macd.getSignal());
			} else {
				//macd
				return outputToMap(targetStock, macd, macd.getOutputData());
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
