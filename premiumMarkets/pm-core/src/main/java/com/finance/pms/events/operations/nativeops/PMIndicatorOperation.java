package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.talib.indicators.TalibIndicator;

@XmlSeeAlso({PMAroonOperation.class, PMMACDOperation.class, PMSMAOperation.class, PMHouseTrendOperation.class, PMInvHouseTrendOperation.class})
public abstract class PMIndicatorOperation extends DoubleMapOperation {

	@SuppressWarnings("unused")
	private PMIndicatorOperation() {
		super();
	}

	public PMIndicatorOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public PMIndicatorOperation(String reference, String description, Operation ... operands) {
		this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	protected DoubleMapValue outputToMap(TargetStockInfo targetStock, TalibIndicator talibIndicator, double[] outputs) {
		
		DoubleMapValue dateDoubleMap = new DoubleMapValue();
		for (int i = talibIndicator.getOutBegIdx().value; i < talibIndicator.getOutBegIdx().value+talibIndicator.getOutNBElement().value; i++) {
			Date calculatorDate = talibIndicator.getIndicatorQuotationData().get(i).getDate();
			Double output = outputs[i - talibIndicator.getOutBegIdx().value];
			dateDoubleMap.getValue(targetStock).put(calculatorDate, output);
		}
		
		return dateDoubleMap;
	}
	
	

}
