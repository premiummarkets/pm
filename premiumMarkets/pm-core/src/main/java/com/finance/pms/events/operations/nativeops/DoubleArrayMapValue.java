package com.finance.pms.events.operations.nativeops;

import java.util.*;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TimedMapValue;
import com.finance.pms.events.operations.Value;

public class DoubleArrayMapValue extends Value<java.util.SortedMap<Date, double[]>> implements TimedMapValue<double[]>, Cloneable {

	protected static MyLogger LOGGER = MyLogger.getLogger(DoubleArrayMapValue.class);

	private SortedMap<Date, double[]> map;
	private List<String> columnsReferences;

	public DoubleArrayMapValue(SortedMap<Date, double[]> map, List<String> columnsReferences) {
		super();
		this.map = map;
		this.columnsReferences = columnsReferences;
	}

	public DoubleArrayMapValue() {
		super();
		this.map = new TreeMap<>();
		this.columnsReferences = new ArrayList<>();
	}

	@Override
	public SortedMap<Date, double[]> getValue(TargetStockInfo targetStock) {
		return map;
	}

	//The particularity of DoubleArrayMaps is that the column amount and refs is know only at runtime.
	//Hence, this has to be held in the value not the operation.
	public List<String> getColumnsReferences() {
		return this.columnsReferences;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+map.size() + ((map.size() > 0)?", first key "+map.firstKey()+ ", last key "+map.lastKey():"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			DoubleArrayMapValue clone = (DoubleArrayMapValue) super.clone();
			clone.map = (SortedMap<Date, double[]>) ((TreeMap<Date, double[]>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
