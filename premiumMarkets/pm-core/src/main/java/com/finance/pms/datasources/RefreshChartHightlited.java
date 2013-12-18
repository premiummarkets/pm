package com.finance.pms.datasources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.NotEnoughDataException;


public class RefreshChartHightlited extends UserContentStrategyEngine<Stock> {

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Stock rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate, Date endAnalysisDate, Set<Observer> engineObservers, Stock rootParam, Collection<? extends Object>... viewStateParams) throws NotEnoughDataException {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[0]);
		super.callbackForlastAnalyse(analysisList, startAnalyseDate, endAnalysisDate, engineObservers, rootParam);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastQuotationFetch(Set<Observer> engineObservers, Stock rootParam, Collection<? extends Object>... viewStateParams) throws StockNotFoundException {

		tamperEventConfig((Collection<EventInfo>) viewStateParams[0]);
		super.callbackForlastQuotationFetch(engineObservers, rootParam);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Stock rootParam, Collection<? extends Object>... viewStateParams) {
		
		tamperEventConfig((Collection<EventInfo>) viewStateParams[0]);
		super.callbackForAnalysisClean(engineObservers, rootParam);

	}

	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
		switch (taskId) {
		case Analysis:
		case Clean :
			return new int[]{0};
		default:
			return new int[]{};
		}
	}

	@Override
	public boolean allowsTaskReset() {
		return false;
	}

	@Override
	public int otherViewParamLength() {
		return 1;
	}

//	@Override
//	protected void updateQuotations(QuotationUpdate quotationUpdate, Stock rootParam) throws StockNotFoundException {
//		quotationUpdate.getQuotesFor(Arrays.asList(new Stock[]{rootParam}));
//	}

	@Override
	protected List<Stock> buildStockListFrom(Stock rootParam) {
		if (rootParam == null) return new ArrayList<Stock>();
		return Arrays.asList(new Stock[]{rootParam});
	}

}
