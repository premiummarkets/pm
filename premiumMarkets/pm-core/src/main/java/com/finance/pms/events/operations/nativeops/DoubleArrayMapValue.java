package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;

public class DoubleArrayMapValue extends NumericableMapValue implements MultiMapValue {

	private static MyLogger LOGGER = MyLogger.getLogger(DoubleArrayMapValue.class);

	private SortedMap<Date, double[]> map;
	private List<String> columnsReferences;
	private int mainIdx;

	//Cache
	private SortedMap<Date, Double> collectedUnaryMapValue;
	private Map<String, NumericableMapValue> collectAdditionalOutputs;

	public DoubleArrayMapValue(SortedMap<Date, double[]> map, List<String> columnsReferences, int mainIdx) {
		super();
		this.map = map;
		this.columnsReferences = columnsReferences;
		this.mainIdx = mainIdx;
	}

	public DoubleArrayMapValue() {
		super();
		this.map = new TreeMap<>();
		this.columnsReferences = new ArrayList<>();
		this.mainIdx = 0;
	}

	@Override
	public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {
		if (collectedUnaryMapValue == null) {
			if (isMainSet()) {
				collectedUnaryMapValue = map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()[mainIdx], (a,b) -> a, TreeMap::new));
			} else { //As we don't know which column to return, we return NaN
				collectedUnaryMapValue = map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Double.NaN, (a,b) -> a, TreeMap::new));
			}
		}
		return collectedUnaryMapValue;
	}

	public SortedMap<Date, double[]> getDoubleArrayValue() {
		return map;
	}

	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return IntStream.range(0, this.columnsReferences.size())
				.boxed()
				.collect(Collectors.toMap(i -> columnsReferences.get(i), c -> Type.MULTI));
	}

	@Override
	public Map<String, NumericableMapValue> getAdditionalOutputs() {
		if (collectAdditionalOutputs == null) {
			collectAdditionalOutputs = IntStream.range(0, this.columnsReferences.size())
					.boxed()
					.collect(Collectors.toMap(
							i -> columnsReferences.get(i),
							i -> {
								TreeMap<Date, Double> collect = map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()[i], (a,b) -> a, TreeMap::new));
								return new DoubleMapValue(collect);
							},
							(a,b) -> a, 
							() -> new TreeMap<String, NumericableMapValue>(new Comparator<String>() {
								@Override
								public int compare(String o1, String o2) {
									return Integer.valueOf(columnsReferences.indexOf(o1)).compareTo(Integer.valueOf(columnsReferences.indexOf(o2)));
								}
							}))
							);
		}
		return collectAdditionalOutputs;
	}

	//The peculiarity of DoubleArrayMaps is that the column amount and refs is known only at runtime.
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
		return this.getClass().getSimpleName() + ": size is " + map.size() + ((map.size() > 0)?", first key " + map.firstKey() + ", last key " + map.lastKey():"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			DoubleArrayMapValue clone = (DoubleArrayMapValue) super.clone();
			clone.columnsReferences = new ArrayList<>(this.columnsReferences);
			clone.map = (SortedMap<Date, double[]>) ((TreeMap<Date, double[]>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public List<Date> getDateKeys() {
		return new ArrayList<>(map.keySet());
	}

	@Override
	public List<String> getReferences() {
		return getColumnsReferences();
	}

}
