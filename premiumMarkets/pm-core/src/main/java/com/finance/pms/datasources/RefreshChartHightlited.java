package com.finance.pms.datasources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.EventModel.EventDefCacheEntry;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;


public class RefreshChartHightlited extends UserContentStrategyEngine {

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		
		
		Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passTwoOutput;
		try {
			passTwoOutput = actionThread.runIndicatorsCalculationPassTwo(true);
		} catch (IncompleteDataSetException e1) {
			passTwoOutput = e1.getCalculatedOutput();
		}
	
		return finalising(actionThread, passTwoOutput);
	}

	private Map<Stock, Map<EventInfo, EventDefCacheEntry>> finalising(IndicatorAnalysisCalculationRunnableMessage actionThread, Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passOutput) {

		Map<Stock, Map<EventInfo, EventDefCacheEntry>> ret = new HashMap<Stock, Map<EventInfo,EventDefCacheEntry>>();
		for (Stock stock : passOutput.keySet()) {
			Map<EventInfo, SortedMap<Date, double[]>> map4Stock = passOutput.get(stock);
			ret.put(stock, new HashMap<EventInfo, EventModel.EventDefCacheEntry>());
			for (EventInfo evtDef : map4Stock.keySet()) {
				SortedMap<Date, double[]> map4EvtDef = map4Stock.get(evtDef);
				ret.get(stock).put(evtDef, new EventDefCacheEntry(map4EvtDef));
			}
		}
		return ret;
	}

	protected Map<Stock, Map<EventInfo, EventDefCacheEntry>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException {
		
		Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> passOneOutput;
		try {
			passOneOutput = actionThread.runIndicatorsCalculationPassOne(true , "force");
		} catch (IncompleteDataSetException e1) {
			passOneOutput = e1.getCalculatedOutput();
		}

		return finalising(actionThread, passOneOutput);
		
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object... viewStateParams) {
		throw new NotImplementedException();
	}

}
