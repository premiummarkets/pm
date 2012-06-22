/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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


// TODO: Auto-generated Javadoc
/**
 * The Class IndicatorPonderationRule.
 * 
 * @author Guillaume Thoreton
 */
public class IndicatorPonderationRule extends DefaultPonderationRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9176346568918514376L;
	/** The indicator. */
	Integer indicator;

	/**
	 * Instantiates a new indicator ponderation rule.
	 * 
	 * @param indicator the indicator
	 * 
	 * @author Guillaume Thoreton
	 */
	public IndicatorPonderationRule(Integer indicator) {
		super(
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(), 
				((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold());
		this.indicator = indicator;
	}
	
	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new IndicatorSignal(this.indicator,symbolEvents.getEventDefList());
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.events.pounderationrules.DefaultPonderationRule#compare(com.finance.pms.events.SymbolEvents, com.finance.pms.events.SymbolEvents)
	 */
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		IndicatorPonderationRule p1 = new IndicatorPonderationRule(this.indicator);
		IndicatorPonderationRule p2 = new IndicatorPonderationRule(this.indicator);
		
		return  compareCal(se1, se2, p1, p2);
	}
}
