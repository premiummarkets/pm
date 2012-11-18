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

import java.util.Map;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.SymbolEvents;


// TODO: Auto-generated Javadoc
/**
 * The Class TalibPonderationRule.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class TalibPonderationRule extends PonderationRule  { //Talib events to 1 others to 0
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8507211200831702881L;

	/**
  * Instantiates a new talib ponderation rule.
  * 
  * @param se the se
  * 
  * @author Guillaume Thoreton
  */
 public TalibPonderationRule(SymbolEvents se) {
	 	super();
	}
	
	/**
	 * Instantiates a new talib ponderation rule.
	 * 
	 * @author Guillaume Thoreton
	 */
	public TalibPonderationRule() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.events.pounderationrules.PonderationRule#eventWeigth(java.util.Map, java.lang.Integer)
	 */
	public Float eventWeigth(Map<Integer,String> eventList, Integer eventId, SymbolEvents symbolEvents){
		Float retour;
		if (EventDefinition.PMMACDZEROCROSS.getEventDefId().equals(eventId)){
		//if (EventDefinition.UNKNOWN99.getEventDefId().compareTo(eventId) >= 0){
			retour = new Float(1);
		} else {
			retour = new Float(0);
		}
		
		return retour;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.events.pounderationrules.PonderationRule#eventTypeWeigth(java.util.Map, char)
	 */
	public Float eventTypeWeigth(Map<Integer,String> eventList, char eventTypeId) {
		Float retour;
		switch(eventTypeId) {
			case 'b' : retour = new Float(1);
			break;
			case 's' : retour = new Float(-1);
			break;
			case 'n' : retour = new Float(0);
			break;
			default :
				retour = new Float(0);	
		}
		
		return retour;
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		TalibPonderationRule p1 = new TalibPonderationRule(se1);
		TalibPonderationRule p2 = new TalibPonderationRule(se2);
		int retour;
		
		int weigth = (new Float(se2.getWeight(p2))).compareTo((new Float(se1.getWeight(p1))));
		if (weigth != 0) {
			retour = weigth;
		} else {
			retour = se1.getSymbol().compareTo(se2.getSymbol());
		}
		return retour;
	}

	@Override
	public Float finalWeight(SymbolEvents symbolEvents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean shallExit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void postCondition() {
		// TODO Auto-generated method stub
		
	}
	
	
	


}
