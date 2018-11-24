package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.GetInflation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;

public class InflationRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(InflationRationaliserOperation.class);

	//@Autowired
	private GetInflation getInflation = GetInflation.geInstance();

	public InflationRationaliserOperation() {
		super("inflationRationaliser", "Applies a reversed inflation rate to the data in an attempt to reduce the inflation bias", new DoubleMapOperation("data to rationalize"));
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"rationalizedData","dailyRate"})));
	}

	public InflationRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);

		try {

			SortedMap<Date, Double> resultMap = new TreeMap<>();
			SortedMap<Date, Double> dailyRateMap = new TreeMap<>();
			Date firstKey = data.firstKey();
			for (Date date : data.keySet()) { 
				Double inflationRate = getInflation.inflationRateWithinDateRange(firstKey, date).doubleValue();
				Double valueAtDate = data.get(date);
				Double temperedValue = (valueAtDate) / (1 + inflationRate);
				resultMap.put(date, temperedValue);
				dailyRateMap.put(date, inflationRate);
			}

			Map<String, NumericableMapValue> selectorOutputs = new HashMap<String, NumericableMapValue>();
			for (String outputSelector : getAvailableOutputSelectors()) {
				if (outputSelector != null && outputSelector.equalsIgnoreCase("rationalizedData")) {
					selectorOutputs.put("rationalizedData", new DoubleMapValue(resultMap));
				}
				if (outputSelector != null && outputSelector.equalsIgnoreCase("dailyRate")) {
					selectorOutputs.put("dailyRate", new DoubleMapValue(dailyRateMap));
				}
			}

			return new MultiSelectorsValue(selectorOutputs, getOutputSelector());

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : "  + e, e);
		}

		return new MultiSelectorsValue(getAvailableOutputSelectors(), getOutputSelector());
	}

}
