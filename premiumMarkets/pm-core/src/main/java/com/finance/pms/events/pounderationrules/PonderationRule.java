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

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;

/**
 * The Interface PonderationRule.
 * 
 * @author Guillaume Thoreton
 */
public abstract class PonderationRule implements Comparator<SymbolEvents>, Serializable {

	private static final long serialVersionUID = -5794918385779129800L;
	private static MyLogger LOGGER = MyLogger.getLogger(PonderationRule.class);

	public PonderationRule() {
		super();
	}

	private void calculateSignal(SymbolEvents events) {

		SortedMap<EventKey, EventValue> sortedBackwardsEventMap = eventResultsSortedBackward(events);
		Integer isAddingUp = 0;

		for (EventKey eventKey : sortedBackwardsEventMap.keySet()) {

			LOGGER.trace("Processing event : "+ sortedBackwardsEventMap.get(eventKey) +" at date "+ new SimpleDateFormat("yyyy/MM/dd").format(eventKey.getDate()));

			if (isAddingUp != -1) {
				isAddingUp = events.getSignal().addEvent(eventKey, sortedBackwardsEventMap.get(eventKey));
			} else {
				//Check remaining alerts as all alerts must be checked and prevail
				if (isNonCumulativeEvent(sortedBackwardsEventMap.get(eventKey))) events.getSignal().addEvent(eventKey, sortedBackwardsEventMap.get(eventKey));
			}

			if (shallExit()) {
				break;
			}
		}

		postCondition(events.getSignal());
	}

	protected abstract void postCondition(Signal signal);

	private boolean isNonCumulativeEvent(EventValue eventValue) {
		return (eventValue.getEventDef().equals(EventDefinition.ALERTTHRESHOLD) || eventValue.getEventDef().equals(EventDefinition.SCREENER));
	}

	public Float finalWeight(SymbolEvents symbolEvents) {
		symbolEvents.setSignal(initSignal(symbolEvents));
		calculateSignal(symbolEvents);
		return symbolEvents.getSignal().getSignalWeight().floatValue();
	}

	private SortedMap<EventKey,EventValue> eventResultsSortedBackward(final SymbolEvents symbolEvents) {

		//Descending order sorted map
		SortedMap<EventKey,EventValue> sortedMap = 
				new TreeMap<EventKey, EventValue>(new Comparator<EventKey>() {

					public int compare(EventKey o1, EventKey o2) {

						if (o1.equals(o2)) {
							return 0;
						}

						int date = o2.getDate().compareTo(o1.getDate());

						if (date == 0) {
							int eventDef = o2.getEventInfo().compareTo(o1.getEventInfo());
							if (eventDef == 0) {
								if (EventDefinition.SCREENER.equals(o1.getEventInfo())) {
									throw new InvalidParameterException("Can't have two screener events at the same date : "+o2+ " and "+o1+" for "+symbolEvents.toString());
								}
								return o2.getEventType().compareTo(o1.getEventType());
							}
							return eventDef;
						}
						return date;
					}

				});
		sortedMap.putAll(symbolEvents.getDataResultMap());

		return sortedMap;
	}

	protected abstract Boolean shallExit();

	protected abstract Signal initSignal(SymbolEvents symbolEvents);


	protected int compareCal(SymbolEvents se1, SymbolEvents se2, PonderationRule p1, PonderationRule p2) {
		int retour;
		//inverse order (for data base access purpose??)
		Float se2Weight = new Float(se2.getWeight(p2));
		Float se1Weight = new Float(se1.getWeight(p1));
		int weigth = se2Weight.compareTo(se1Weight);
		if (weigth != 0) {
			retour = weigth;
		} else {
			retour = se1.getStock().compareTo(se2.getStock());
		}
		return retour;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
