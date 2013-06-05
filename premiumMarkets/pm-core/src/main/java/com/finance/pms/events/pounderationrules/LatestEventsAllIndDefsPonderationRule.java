/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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

public class LatestEventsAllIndDefsPonderationRule extends LatestEventsPonderationRule {

	private static final long serialVersionUID = -1852887356861603194L;

	public LatestEventsAllIndDefsPonderationRule() {
		super(
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(), 
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold());
	}
	
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		LatestEventsAllIndDefsPonderationRule p1 = new LatestEventsAllIndDefsPonderationRule();
		LatestEventsAllIndDefsPonderationRule p2 = new LatestEventsAllIndDefsPonderationRule();
		
		return  compareCal(se1, se2, p1, p2);
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new LatestEventsAllIndDefsSignal();
	}
	
}
