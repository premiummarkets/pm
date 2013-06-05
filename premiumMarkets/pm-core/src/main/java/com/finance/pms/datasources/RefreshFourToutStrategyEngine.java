package com.finance.pms.datasources;

import java.util.Map;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.EventModel.EventDefCacheEntry;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;


public class RefreshFourToutStrategyEngine extends UserContentStrategyEngine {

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		 try {
			actionThread.runIndicatorsCalculationPassTwo(true);
		} catch (IncompleteDataSetException e) {
			
		}
		 return null;
	}

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		try {
			actionThread.runIndicatorsCalculationPassOne(true , "auto");
		} catch (IncompleteDataSetException e) {
			
		}
		return null;
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object... viewStateParams) {
		throw new NotImplementedException();
	}


}
