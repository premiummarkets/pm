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
package com.finance.pms.events.gui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.pounderationrules.PonderationRule;

// TODO: Auto-generated Javadoc
/**
 * The Enum EventsActionSort.
 * 
 * @author Guillaume Thoreton
 */
public class EventsActionSort {

	private PonderationRule ponderationRule;
	private EventsActionSortEnum actionSortEnum;
	private Set<EventDefinition> indicators;
	
	
	public static enum EventsActionSortEnum {
		DEFAULTWEIGTHTXT ("All Events"),
		TALIBWEIGHTTXT ("Premium Markets Indicators"),
		LATESTEVENTSTXT ("Latest Events and Alerts"),
		LATESTNOALERTSEVENTSTXT ("Latest Trend");
		
		/** The text. */
		private String text;

		private EventsActionSortEnum(String text) {
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
	public EventsActionSort(EventsActionSortEnum actionSortEnum, PonderationRule ponderationRule, EventDefinition ...definitions) {
		this.actionSortEnum = actionSortEnum;
		this.ponderationRule = ponderationRule;
		if (definitions != null && definitions.length > 0) {
			indicators = new HashSet<EventDefinition>();
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

	protected EventsActionSortEnum getActionSortEnum() {
		return actionSortEnum;
	}

	public Set<EventDefinition> getIndicators() {
		return indicators;
	}
	

}
