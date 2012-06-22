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
package com.finance.pms.events;

// TODO: Auto-generated Javadoc
/**
 * The Enum EventState.
 * 
 * @author Guillaume Thoreton
 */
public enum EventState {
	
	/** The STAT e_ notstarted. */
	STATE_NOTSTARTED("Not Started"), 
	
	/** The STAT e_ running. */
	STATE_RUNNING("Running"), 
	
	/** The STAT e_ toretry. */
	STATE_TORETRY("To be retried"),
	
	/** The STAT e_ terminated. */
	STATE_TERMINATED("Terminated"),
	
	/** The STAT e_ abortedretiedmas. */
	STATE_ABORTEDRETIEDMAS("Aborted - mas"),
	
	/** The STAT e_ abortedretried. */
	STATE_ABORTEDRETRIED("Retried"),
	
	/** The STAT e_ aborted. */
	STATE_ABORTED("Aborted");
	
	/** The event state. */
	private String eventState;

	/**
	 * Instantiates a new event state.
	 * 
	 * @param eventState the event state
	 * 
	 * @author Guillaume Thoreton
	 */
	private EventState(String eventState) {
		this.eventState = eventState;
	}

	/**
	 * Gets the event state.
	 * 
	 * @return the event state
	 */
	public String getEventState() {
		return eventState;
	}

	/**
	 * Value of.
	 * 
	 * @param ordinal the ordinal
	 * 
	 * @return the event state
	 * 
	 * @author Guillaume Thoreton
	 */
	public static EventState valueOf(Integer ordinal){
		return EventState.values()[ordinal];
	}

}
