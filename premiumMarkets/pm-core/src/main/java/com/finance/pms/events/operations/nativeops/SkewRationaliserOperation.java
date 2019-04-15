package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
/**
 * @author guil
 * Fix the price using a regression (passed as parameter)
 * ~ subtraction_(close,linearReg(1250,close))
 */
public class SkewRationaliserOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(SkewRationaliserOperation.class);

	private static final int DATA_IDX = 0;

	public SkewRationaliserOperation() {
		super("skewRationaliser", "Apply an input skewness calculation fix to the input",
				new DoubleMapOperation("Fixer (typically a linear regression)"),
				new DoubleMapOperation("Input data for which the fix applies"));
	}

	public SkewRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Param check
		SortedMap<Date, Double> regression = ((NumericableMapValue) inputs.get(DATA_IDX)).getValue(targetStock);
		SortedMap<Date, Double> skewBase = ((NumericableMapValue) inputs.get(DATA_IDX + 1)).getValue(targetStock);

		//Calc
		try {

			SortedMap<Date, Double> skewFixedData = calculateOutputMap(skewBase, regression);
			return new DoubleMapValue(skewFixedData);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}

		return new DoubleMapValue();
	}

	private SortedMap<Date, Double> calculateOutputMap(SortedMap<Date, Double> q, SortedMap<Date, Double> regression) throws NotEnoughDataException {

		List<Date> qKeys = regression.keySet().stream().filter(vk -> q.containsKey(vk)).collect(Collectors.toList());

		TreeMap<Date, Double> rationalisedMap = qKeys.stream()
				.collect(Collectors.toMap(
						k -> k,
						k -> {
							double y = q.get(k) - regression.get(k);
							return y;
						},
						(a, b) -> a, TreeMap::new));

		return rationalisedMap;
	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < DATA_IDX; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift + 1;
	}

}
