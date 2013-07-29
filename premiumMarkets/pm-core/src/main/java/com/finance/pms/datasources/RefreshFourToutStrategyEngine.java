package com.finance.pms.datasources;

import java.util.Collection;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Stock;


public class RefreshFourToutStrategyEngine extends UserContentStrategyEngine<Object> {

	
	@Override
	protected String passOneOverwriteMode() {
		return "auto";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object rootParam, Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
			return new int[]{};
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}

	@Override
	public int otherViewParamLength() {
		return -1;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	protected void updateQuotations(QuotationUpdate quotationUpdate, Object rootParam) throws StockNotFoundException {
		quotationUpdate.getQuotesFor((List<Stock>) rootParam);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Stock> buildStockListFrom(Object rootParam) {
		return (List<Stock>) rootParam;
	}

}
