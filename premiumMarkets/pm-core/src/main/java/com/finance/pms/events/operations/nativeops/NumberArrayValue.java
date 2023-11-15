package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;

public class NumberArrayValue extends NumberValue implements MultiValue {

	private static MyLogger LOGGER = MyLogger.getLogger(NumberArrayValue.class);

	private double[] values;
	private List<String> columnsReferences;
	private int mainIdx;

	public NumberArrayValue(double[] values, List<String> columnsReferences, int mainIdx) {
		super(0 <= mainIdx && mainIdx < columnsReferences.size()?values[mainIdx]:Double.NaN);
		this.values = values;
		this.columnsReferences = columnsReferences;
		this.mainIdx = mainIdx;
	}

	public NumberArrayValue() {
		super();
		this.values = new double[] {};
		this.columnsReferences = new ArrayList<>();
		this.mainIdx = 0;
	}

	@Override
	public Number getValue(TargetStockInfo targetStockInfo) {
		return getNumberValue();
	}

	public double[] getDoubleArrayValue() {
		return values;
	}

	public Map<String, NumberValue> getAdditionalOutputs() {
		return IntStream.range(0, this.columnsReferences.size())
					.boxed()
					.collect(Collectors.toMap(
							i -> columnsReferences.get(i),
							i -> new NumberValue(values[i]),
							(a,b) -> a, 
							() -> new TreeMap<String, NumberValue>(new Comparator<String>() {
								@Override
								public int compare(String o1, String o2) {
									return Integer.valueOf(columnsReferences.indexOf(o1)).compareTo(Integer.valueOf(columnsReferences.indexOf(o2)));
								}
							}))
							);
	}

	//The peculiarity is that the column amount and refs is known only at runtime.
	//Hence, this has to be held in the value not the operation.
	public List<String> getColumnsReferences() {
		return this.columnsReferences;
	}
	
	public String getMainColumnsReferences() {
		if (isMainSet()) {
			return this.columnsReferences.get(mainIdx);
		}
		return null;
	}
	
	private boolean isMainSet() {
		return 0 <= mainIdx && mainIdx < this.columnsReferences.size();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": keys " + columnsReferences + ", values " + Arrays.toString(values);
	}

	@Override
	public Object clone() {
		try {
			NumberArrayValue clone = (NumberArrayValue) super.clone();
			clone.columnsReferences = new ArrayList<>(this.columnsReferences);
			clone.values = this.values.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
