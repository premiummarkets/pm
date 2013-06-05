package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.SMA;

@XmlRootElement
public class PMSMAOperation extends PMIndicatorOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(PMSMAOperation.class);
	
	public PMSMAOperation() {
		super("sma_", "House made SMA on close historical data", new DoubleOperation("number","smaPeriod","Sma period", new DoubleValue(200.0)));
	}
	
	public PMSMAOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	
	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Integer period = ((DoubleValue) inputs.get(0)).getValue(targetStock).intValue();

		DoubleMapValue ret = new DoubleMapValue();
		try {
			SMA sma = new SMA(targetStock.getStock(), period, targetStock.getStartDate(), targetStock.getEndDate(), null);
			return outputToMap(targetStock, sma, sma.getOutputData());

		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
