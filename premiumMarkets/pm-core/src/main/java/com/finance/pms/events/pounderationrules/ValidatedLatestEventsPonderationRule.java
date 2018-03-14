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

import java.util.Map;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.Validity;

public class ValidatedLatestEventsPonderationRule extends LatestEventsPonderationRule {

	private static final long serialVersionUID = 573802685420097964L;
	private static MyLogger LOGGER = MyLogger.getLogger(ValidatedLatestEventsPonderationRule.class);
	
	private Map<Stock, Validity> tuningValidityList;
	private EventInfo filteredEventDef;

	public ValidatedLatestEventsPonderationRule(Integer sellThreshold, Integer buyThreshold, Map<Stock, Validity> tuningValidityList, EventInfo evtDef) {
		super(sellThreshold, buyThreshold);
		this.tuningValidityList = tuningValidityList;
		this.filteredEventDef = evtDef;
	}
	
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		PonderationRule p1 = new ValidatedLatestEventsPonderationRule(sellThreshold, buyThreshold, tuningValidityList, filteredEventDef);
		PonderationRule p2 = new ValidatedLatestEventsPonderationRule(sellThreshold, buyThreshold, tuningValidityList, filteredEventDef);
		
		return compareCal(se1, se2, p1, p2);
	}

	@Override
	public Float finalWeight(SymbolEvents symbolEvents) {
		
		Validity validity = tuningValidityList.get(symbolEvents.getStock());
		
		boolean isValid = false;
		if (validity != null) {
			isValid = validity.equals(Validity.SUCCESS);
		} else {
			LOGGER.warn("No validity information found for " +symbolEvents.getStock()+ " while parsing events "+symbolEvents+ ". Neural trend was not calculated for that stock.");
		}
		
		if (isValid) {
			return super.finalWeight(symbolEvents);
		} else {
			return 0.0f;
		}
		
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new ValidatedLatestEventsSignal(filteredEventDef);
	}
	
}
