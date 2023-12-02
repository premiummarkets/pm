package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMDataFreeOperation;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

@XmlRootElement
public class VolatilityOtherOperation extends PMDataFreeOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityOtherOperation.class);

	public VolatilityOtherOperation() {
		super("volatilityOtherCalculator", "Calculate the close volatility using home made algoritmics.",
				new NumberOperation("number", "Basic Period", "Basic period", new NumberValue(21.0)));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"stdevLnVolatility","stdevVolatility"})));
	}

	public VolatilityOtherOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int basicPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();

		//Calc
		try {

			Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(
					targetStock.getStock(), targetStock.getStartDate(thisStartShift), targetStock.getEndDate(),
					true, targetStock.getStock().getMarketValuation().getCurrency(), basicPeriod, ValidityFilter.CLOSE);
			SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
			ArrayList<Date> keys = new ArrayList<>(closeQuotations.keySet());

			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("stdevLnVolatility")) {
				Double stdevLnVolatility = stdevLnVolatility(quotations, basicPeriod);
				TreeMap<Date, Double> line = keys.stream().collect(Collectors.toMap(k -> k, k -> stdevLnVolatility, (v1, v2) -> v1, TreeMap::new));
				return new DoubleMapValue(line);
			}
			if (outputSelector != null && outputSelector.equalsIgnoreCase("stdevVolatility")) {
				Double stdevVolatility = stdevVolatility(quotations, basicPeriod);
				TreeMap<Date, Double> line = keys.stream().collect(Collectors.toMap(k -> k, k -> stdevVolatility, (v1, v2) -> v1, TreeMap::new));
				return new DoubleMapValue(line);
			}

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " calculating " + getReference() + " : " + e, e);
		}

		return new DoubleMapValue();
	}

	//Other volatility related calculations
	private Double stdevLnVolatility(Quotations quotations, Integer period) throws NoQuotationsException {
		return quotations.stdevLnVolatility(quotations.getDate(0), quotations.getDate(quotations.size()-1), period);
	}

	private Double stdevVolatility(Quotations quotations, Integer period) throws NoQuotationsException {
		return quotations.stdevVolatility(quotations.getDate(0), quotations.getDate(quotations.size()-1), period);
	}

}
