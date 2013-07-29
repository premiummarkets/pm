package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.talib.indicators.TalibIndicator;

@XmlSeeAlso({PMAroonOperation.class, PMMACDOperation.class, PMSMAOperation.class, PMHouseTrendOperation.class, PMMightyChaikinOperation.class})
public abstract class PMIndicatorOperation extends DoubleMapOperation {

	protected PMIndicatorOperation() {
		super();
	}

	public PMIndicatorOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public PMIndicatorOperation(String reference, String description, Operation ... operands) {
		this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	protected DoubleMapValue doubleArrayMapToDoubleMap(TargetStockInfo targetStock, TalibIndicator talibIndicator, double[] outputs) {
		
		DoubleMapValue dateDoubleMap = new DoubleMapValue();
		for (int i = talibIndicator.getOutBegIdx().value; i < talibIndicator.getOutBegIdx().value+talibIndicator.getOutNBElement().value; i++) {
			Date calculatorDate = talibIndicator.getIndicatorQuotationData().get(i).getDate();
			Double output = outputs[i - talibIndicator.getOutBegIdx().value];
			dateDoubleMap.getValue(targetStock).put(calculatorDate, output);
		}
		
		return dateDoubleMap;
	}
	
	protected SortedMap<Date, double[]> doubleMapToDoubleArrayMap(TargetStockInfo targetStock, DoubleMapValue input) {
		
		SortedMap<Date, double[]> doubleArrayMap = new TreeMap<Date, double[]>();
		SortedMap<Date, Double> inputValue = input.getValue(targetStock);
		
		for (Date date : inputValue.keySet()) {
			doubleArrayMap.put(date, new double[]{inputValue.get(date)});
		}
			
		return doubleArrayMap;
	}

	protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest) {
		for (Date date : origin.keySet()) {
			dest.put(date, origin.get(date)[0]);
		}
	}	

}
