package com.finance.pms.datasources;

import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;


public class RefreshFourToutStrategyEngine extends UserContentStrategyEngine {

//	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
//		 try {
//			actionThread.runIndicatorsCalculationPassTwo(true);
//		} catch (IncompleteDataSetException e) {
//			
//		}
//		 return null;
//	}
//
//	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
//		try {
//			actionThread.runIndicatorsCalculationPassOne(true , "auto");
//		} catch (IncompleteDataSetException e) {
//			
//		}
//		return null;
//	}
	
	@Override
	protected String passOneOverwriteMode() {
		return "auto";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object... viewStateParams) {
		throw new NotImplementedException();
	}


}
