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


// TODO: Auto-generated Javadoc
/**
 * The Interface PonderationRule.
 * 
 * @author Guillaume Thoreton
 */
public abstract class PonderationRule implements Comparator<SymbolEvents>, Serializable {
	
	private static final long serialVersionUID = -5794918385779129800L;
	private static MyLogger LOGGER = MyLogger.getLogger(PonderationRule.class);
	
	protected Signal signal;
	private SortedMap<EventKey,EventValue> sortedEventMap;
	
	public PonderationRule() {
		super();
	}

	private Signal calculateSignal() {
		
		Integer isAddingUp = 0;
		
		for (EventValue eventValue : sortedEventMap.values()) {
			
			LOGGER.trace("Processing event : "+ eventValue +" at date "+ new SimpleDateFormat("yyyy/MM/dd").format(eventValue.getDate()));
			
			if (isAddingUp != -1) {
				isAddingUp = this.signal.addEvent(eventValue);
			} else {
				//check remaining alerts as all alerts must be checked and prevail
				if (isNonCumulativeEvent(eventValue)) this.signal.addEvent(eventValue);
			}
			
			if (shallExit()) {
				break;
			}
		}
		
		postCondition();
		
		return signal;
	}
	
	protected abstract void postCondition();

	private boolean isNonCumulativeEvent(EventValue eventValue) {
		return (eventValue.getEventDef().equals(EventDefinition.ALERTTHRESHOLD) || eventValue.getEventDef().equals(EventDefinition.SCREENER));
	}

	public Float finalWeight(SymbolEvents symbolEvents) {
		
		this.signal = initSignal(symbolEvents);
		this.sortedEventMap = sortEventResults(symbolEvents);
		this.signal = this.calculateSignal();
		return new Float(this.signal.getSignalWeight());
		
	}
	
	private SortedMap<EventKey,EventValue> sortEventResults(final SymbolEvents symbolEvents) {
		
		//Descending order sorted map
		SortedMap<EventKey,EventValue> sortedMap = 
			new TreeMap<EventKey, EventValue>(new Comparator<EventKey>() {
				
				@SuppressWarnings("unchecked")
				public int compare(EventKey o1, EventKey o2) {
					
					if (o1.equals(o2)) {
						return 0;
					}
					
					int date = o2.getDate().compareTo(o1.getDate());
					
					if (date == 0) {
						int eventDef = o2.getEventDefId().compareTo(o1.getEventDefId());
						if (eventDef == 0) {
							if (EventDefinition.SCREENER.equals(o1.getEventDefId())) {
								throw new InvalidParameterException("Can't have two screener events at the same date : "+o2+ " and "+o1+" for "+symbolEvents.toString());
							}
							return o2.getEvenType().compareTo(o1.getEvenType());
						}
						return eventDef;
					}
					return date;
				}

			});
		sortedMap.putAll(symbolEvents.getDataResultList());
	
		return sortedMap;
	}

	protected abstract Boolean shallExit();

	protected abstract Signal initSignal(SymbolEvents symbolEvents);

	/**
	 * Compare cal.
	 * 
	 * @param se1 the se1
	 * @param se2 the se2
	 * @param p1 the p1
	 * @param p2 the p2
	 * 
	 * @return the int
	 * 
	 * @author Guillaume Thoreton
	 */
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
