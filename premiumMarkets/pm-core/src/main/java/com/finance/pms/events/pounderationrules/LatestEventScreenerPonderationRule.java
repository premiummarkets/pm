package com.finance.pms.events.pounderationrules;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.ConfigThreadLocal;

public class LatestEventScreenerPonderationRule extends LatestEventsPonderationRule {

	private static final long serialVersionUID = 703449932021041942L;
	private String portfolioName;

	
	public LatestEventScreenerPonderationRule(String portfolioName) {
		super(
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(), 
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold());
		this.portfolioName = portfolioName;
	}
	
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		LatestEventScreenerPonderationRule p1 = new LatestEventScreenerPonderationRule(portfolioName);
		LatestEventScreenerPonderationRule p2 = new LatestEventScreenerPonderationRule(portfolioName);
		
		return  compareCal(se1, se2, p1, p2);
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new SimpleScreenerSignal(symbolEvents.getStock(), portfolioName);
	}

}
