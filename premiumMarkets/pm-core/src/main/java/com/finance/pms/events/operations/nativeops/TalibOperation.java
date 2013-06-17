package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;

@XmlSeeAlso({TalibMacdOperation.class, TalibSmaOperation.class})
public abstract class TalibOperation extends DoubleMapOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(TalibOperation.class);
	
	@SuppressWarnings("unused")
	private TalibOperation() {
		super();
	}

	public TalibOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public TalibOperation(String reference, String description, Operation ... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		

		DoubleMapValue ret = new DoubleMapValue();
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		
		try {

			SortedMap<Date, Double> outputMap = innerCalculation(targetStock, outBegIdx, outNBElement, inputs);
			ret.getValue(targetStock).putAll(outputMap);
			return ret;

		} catch (TalibException e) {
			LOGGER.warn("Talib Operation calculation error "+this, e);
			throw new RuntimeException(e);
		}
	}

	protected abstract SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException;

	protected SortedMap<Date, Double> arrayToMap(Set<Date> dateKeySet, double[] array, int shift) {
		int j = 0;
		SortedMap<Date, Double> map = new TreeMap<Date, Double>();
		for (Date date : dateKeySet){
			if ((j >= shift) && (j- shift) < array.length) {
				map.put(date, array[j - shift]);
			}
			j++;
		}
		return map;
	}

	protected double[] mapToArray(SortedMap<Date, Double> map) {
		double[] array = new double[map.size()];
		int i=0;
		for (Double dv : map.values()) {
			array[i] = dv;
			i++;
		}
		return array;
	}
	
	

}
