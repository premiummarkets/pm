package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO period
public class RocSmoother extends Smoother implements SSmoother {
	
	@Deprecated
	Integer reSeedingPeriod = null; //254; //Span after which the roc is recalculated from current date??
	
	private Stock stock;
	private Collection<QuotationDataType> quotationsDataTypes;
	
	public RocSmoother(Stock stock, Collection<QuotationDataType> quotationsDataTypes) {
		super();
		this.stock = stock;
		this.quotationsDataTypes = quotationsDataTypes;
	}

	@Override
	public SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, Boolean fixBias) {

		Date startDate = data.firstKey();
		Date endDate = data.lastKey();
		
		SortedMap<Date,Double> iterationRocs = rocsFor(MapUtils.subMapInclusive(data, startDate, endDate), fixBias);
		
		return iterationRocs;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private Date getNextReSeedingDate(Date currentDate) {
		try {
			Calendar reSeedDateCal = Calendar.getInstance();
			reSeedDateCal.setTime(currentDate);
			QuotationsFactories.getFactory().incrementDate(stock, quotationsDataTypes, reSeedDateCal, reSeedingPeriod);
			Date reSeedDate = reSeedDateCal.getTime();
			return reSeedDate;
		} catch (NotEnoughDataException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		SortedMap<Date, Double> doubleMap = data.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue()[0], (a, b) -> a, TreeMap::new));
		SortedMap<Date, Double> result = sSmooth(doubleMap, fixLag);
		return result.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> new double[] {e.getValue()}, (a, b) -> a, TreeMap::new));
	}

	private SortedMap<Date, Double> rocsFor(SortedMap<Date, Double> data, Boolean fixBias) {
		
		SortedMap<Date, Double> rocs = new TreeMap<>();
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for(int i = 1; i < keys.size(); i++) {
			double prev = data.get(keys.get(i-1));
			Date date = keys.get(i);
			double roc = (data.get(date) - prev)/prev;
			rocs.put(date, roc);
		}

		if (fixBias) {
			MyApacheStats meanFunc = new MyApacheStats(new Mean());
			double mean = meanFunc.dEvaluateCd(rocs.values());
			MyApacheStats stdFunc = new MyApacheStats(new StandardDeviation());
			double stdev = stdFunc.dEvaluateCd(rocs.values());
			
			rocs = rocs.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> (e.getValue()-mean)/stdev, (a, b) -> a, TreeMap::new));	
		} 
		
		return rocs;
	}

}
