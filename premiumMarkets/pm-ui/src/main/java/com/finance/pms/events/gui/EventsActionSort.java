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
package com.finance.pms.events.gui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.finance.pms.MainGui;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.pounderationrules.PonderationRule;

// TODO: Auto-generated Javadoc
/**
 * The Enum EventsActionSort.
 * 
 * @author Guillaume Thoreton
 */
public class EventsActionSort {

	private PonderationRule ponderationRule;
	private Set<EventInfo> indicators;
	
	
	public static enum EventsActionFilter {
		ALLEVENTS ("All Events"),
		TALIBEVENTS (MainGui.APP_NAME+" Indicators");
		
		/** The text. */
		private String text;

		private EventsActionFilter(String text) {
			this.text = text;
		}

		protected String getText() {
			return text;
		}
		
		
	}

	/**
	 * Instantiates a new events action sort.
	 * 
	 * @param text the text
	 * @param ponderationRule the ponderation rule
	 * 
	 * @author Guillaume Thoreton
	 */
	public EventsActionSort(PonderationRule ponderationRule, EventInfo ...definitions) {
		this.ponderationRule = ponderationRule;
		if (definitions != null && definitions.length > 0) {
			indicators = new HashSet<EventInfo>();
			indicators.addAll(Arrays.asList(definitions));
		} else {
			indicators = null;
		}
	}

	/**
	 * Gets the ponderation rule.
	 * 
	 * @return the ponderation rule
	 */
	public PonderationRule getPonderationRule() {
		return ponderationRule;
	}

	public Set<EventInfo> getIndicators() {
		return indicators;
	}
	

}
