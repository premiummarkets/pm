package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class DoubleMapValue extends NumericableMapValue implements MultiMapValue {

	private SortedMap<Date, Double> map;
	private Map<String, NumericableMapValue> additionalOutputs;
	private Map<String, Type> additionalOutputsTypes;

	public DoubleMapValue(SortedMap<Date, Double> map) {
		super();
		this.map = map;
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public DoubleMapValue() {
		super();
		this.map = new TreeMap<Date, Double>();
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {
		return map;
	}
	
	@Override
	public DoubleMapValue filterToParentRequirements(TargetStockInfo targetStock, int startShift, Operation parent) {
		try {
			Stock stock = targetStock.getStock();
			ValidityFilter filterFor = ValidityFilter.getFilterFor(parent.getRequiredStockData());
			Quotations quotations  = QuotationsFactories.getFactory()
					.getQuotationsInstance(stock, targetStock.getStartDate(startShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, filterFor);
			SortedMap<Date, Double> exactMapFromQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
			this.map = map.entrySet().stream().filter(e -> exactMapFromQuotations.containsKey(e.getKey())).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
			return this;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " : size is " + map.size() + ((map.size() > 0)?", first key " + map.firstKey() + ", last key " + map.lastKey():"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			DoubleMapValue clone = (DoubleMapValue) super.clone();
			clone.map = (SortedMap<Date, Double>) ((TreeMap<Date, Double>)this.map).clone();
			clone.additionalOutputs = new HashMap<String, NumericableMapValue>();
			for (String outputKey : additionalOutputs.keySet()) {
				NumericableMapValue  addOutputClone = (NumericableMapValue) (additionalOutputs.get(outputKey)).clone();
				clone.additionalOutputs.put(outputKey, addOutputClone);
				clone.additionalOutputsTypes.put(outputKey, additionalOutputsTypes.get(outputKey));
			}
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
	public Map<String, NumericableMapValue> getAdditionalOutputs() {
		return additionalOutputs;
	}

	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return additionalOutputsTypes;
	}

}
