/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.pounderationrules;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.ConfigThreadLocal;

public class LatestEventsScreenerBuyPonderationRule extends LatestEventsPonderationRule {
	private static final long serialVersionUID = 3478291411291712753L;
	protected String portfolioName;
	
	public LatestEventsScreenerBuyPonderationRule(String portfolioName) {
		super(
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(), 
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold());
		this.portfolioName = portfolioName;
	}

	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		PonderationRule p1 = new LatestEventsScreenerBuyPonderationRule(portfolioName);
		PonderationRule p2 = new LatestEventsScreenerBuyPonderationRule(portfolioName);
		
		return  compareCal(se1, se2, p1, p2);
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new LatestEventsScreenerBuySignal(symbolEvents.getStock(), portfolioName, true,true);
	}

	@Override
	public Boolean shallExit() {
		 return false;
	}

	@Override
	protected void postCondition() {
		if (	this.signal.signalWeight > 0 
				&& ((LatestEventsScreenerBuySignal)this.signal).isBullishTrend() && ((LatestEventsScreenerBuySignal)this.signal).additionalRankWeight > 0) {
			this.signal.signalWeight = this.signal.signalWeight + ((LatestEventsScreenerBuySignal)this.signal).additionalRankWeight ;
		} else {
			this.signal.signalWeight = 0;
		}
	}
	
}
