package com.finance.pms.events.pounderationrules;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.threads.ConfigThreadLocal;

public class SimpleScreenerSignal extends LatestEventsSignal {

	private AbstractSharesList portfolio;
	private Stock stock;


	public SimpleScreenerSignal(Stock stock, String portfolioName) {
		super(false, false);
		this.portfolio =  PortfolioMgr.getInstance().getPortfolio(portfolioName);
		this.stock = stock;
	}

	
	@Override
	public Integer addEvent(EventKey eventKey, EventValue eventValue) {
		
		if (isFilteredEvent(eventValue)) {
			
			//Bearish event occurred
			if (eventValue.getEventType().equals(EventType.BEARISH) && portfolio.getListShares().containsKey(this.stock)) { 
				listTriggeringEvent(eventValue, EventType.BEARISH, EventDefinition.SCREENER);
				this.signalWeight = ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold();
				return -1;
			} 
			
			//Bullish event occurred
			if (eventValue.getEventType().equals(EventType.BULLISH) || eventValue.getEventType().equals(EventType.NONE)){
				listTriggeringEvent(eventValue, EventType.BULLISH, EventDefinition.SCREENER);
				this.signalWeight = ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold();
				return 1;
			} 
			
			return 0;
			
		} else { // Not filtered
			this.lastParsedEventType = EventType.NONE;
			return 0;
		}
	}



	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {
		return eventValue.getEventDef().equals(EventDefinition.SCREENER);
	}

}
