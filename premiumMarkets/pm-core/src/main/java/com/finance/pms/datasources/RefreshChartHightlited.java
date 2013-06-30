package com.finance.pms.datasources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.NotEnoughDataException;


public class RefreshChartHightlited extends UserContentStrategyEngine {

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate, Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) throws NotEnoughDataException {
		
		if (viewStateParams.length  == 2) {//Tampering the config to recalculate only independent indicators that need to.
			tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		} 
		super.callbackForlastAnalyse(analysisList, startAnalyseDate, engineObservers, viewStateParams[0]);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) throws StockNotFoundException {
		if (viewStateParams.length == 2) {	
			tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		} 
		super.callbackForlastQuotationFetch(engineObservers, viewStateParams[0]);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) {
		if (viewStateParams.length == 2) {	
			tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		} 
		super.callbackForAnalysisClean(engineObservers, viewStateParams[0]);


	}

	@Override
	public Stock getViewParamRoot(Collection<? extends Object>... viewStateParams) {
		
		if (viewStateParams != null && viewStateParams.length != 0 && viewStateParams[0].size() != 0) {
			return (Stock) viewStateParams[0].iterator().next();
		}
		
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Object>[] setViewStateParams(Object rootParam, Collection<? extends Object>... otherParams) {
		
		Collection<? extends Object>[] ret = new Collection[2];
		
		if (rootParam == null) {
			ret[0] = null;
		} else {
			if (rootParam instanceof Stock) {
				ret[0] = Arrays.asList(new Stock[]{(Stock) rootParam});
			} else {
				throw new IllegalArgumentException("Expecting Stock");
			}
		}
		
		if (otherParams.length != 0) {
			for (int i = 1; i < 2; i++) {
				ret[i] = (i-1 < otherParams.length)?otherParams[i-1]:null;
			}
		}
		
		return ret;
		
	}

	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
		switch (taskId) {
		case Analysis:
		case Clean :
			return new int[]{1};
		default:
			return new int[]{};
		}
	}

	@Override
	public boolean allowsTaskReset() {
		return false;
	}

}
