/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.pounderationrules;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * Filters specific Alerts for sell
 * @author Guillaume Thoreton
 */

public class LatestEventsAlertOnlyPonderationRule extends LatestEventsPonderationRule {

	private static final long serialVersionUID = 3593422562581182579L;

	public LatestEventsAlertOnlyPonderationRule() {
		super(
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(), 
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold());
	}

	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		PonderationRule p1 = new LatestEventsAlertOnlyPonderationRule();
		PonderationRule p2 = new LatestEventsAlertOnlyPonderationRule();
		
		return  compareCal(se1, se2, p1, p2);
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new LatestEventsSignal(true,false);
	}
	
	
}
