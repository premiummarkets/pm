package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class PMAroonOperation extends PMIndicatorOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(PMMACDOperation.class);
	
	public PMAroonOperation() {
		super("aroon_", "Aroon indicator house made", new DoubleOperation("number","aroonPeriod","Aroon period", new DoubleValue(25.0)));
		setAvailableOutputSelectors(new ArrayList<String>( Arrays.asList(new String[]{"down","up"})));
	}
	
	public PMAroonOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer period = (Integer) ((DoubleValue)inputs.get(0)).getValue(targetStock);

		DoubleMapValue ret = new DoubleMapValue();
		try {
			HouseAroon arron = new HouseAroon(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), null, period);
			
			if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("down")) {
				return outputToMap(targetStock, arron, arron.getOutAroonDown());
			}
			else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("up")) {
				return outputToMap(targetStock, arron, arron.getOutAroonUp());
			} else {
				//error
				return outputToMap(targetStock, arron, arron.getOutputData());
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
