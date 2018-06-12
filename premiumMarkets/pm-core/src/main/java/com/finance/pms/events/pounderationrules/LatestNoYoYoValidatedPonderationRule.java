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

import java.util.HashMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.Validity;

public class LatestNoYoYoValidatedPonderationRule extends LatestEventsPonderationRule {

	private static final long serialVersionUID = 573802685420097964L;
	private static MyLogger LOGGER = MyLogger.getLogger(LatestNoYoYoValidatedPonderationRule.class);

	private HashMap<Stock, Validity> tuningValidityList;

	public LatestNoYoYoValidatedPonderationRule(Integer sellThreshold, Integer buyThreshold, HashMap<Stock, Validity> tuningValidityList) {
		super(sellThreshold, buyThreshold);
		this.tuningValidityList = tuningValidityList;
	}

	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {

		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		PonderationRule p1 = new LatestNoYoYoValidatedPonderationRule(sellThreshold, buyThreshold, tuningValidityList);
		PonderationRule p2 = new LatestNoYoYoValidatedPonderationRule(sellThreshold, buyThreshold, tuningValidityList);

		return compareCal(se1, se2, p1, p2);
	}

	@Override
	public Float finalWeight(SymbolEvents symbolEvents) {

		Validity validity = tuningValidityList.get(symbolEvents.getStock());

		boolean isValid = false;
		if (validity != null) {
			isValid = validity.equals(Validity.SUCCESS);
		} else {
			LOGGER.warn("No validity information found for " + symbolEvents.getStock() + " while parsing events " + symbolEvents + ". Neural trend was not calculated for that stock.");
		}

		if (isValid) {
			return super.finalWeight(symbolEvents);
		} else {
			return 0.0f;
		}

	}

	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new LatestNoYoYoValidatedSignal(symbolEvents.getEventDefList());
	}

	@Override
	protected void postCondition(Signal signal) {
		LatestNoYoYoValidatedSignal LVSignal = (LatestNoYoYoValidatedSignal) signal;
		//Updating cumulative event signal with Bull Bear neural events.
		switch (LVSignal.getNbTrendChange()) {
			case 0 : //No event detected
				break;
			case 1 : //One Type of Event, no yoyo : weight = sum(cumulative events) + bullish - bearish
				signal.setSignalWeight(signal.getSignalWeight() +  LVSignal.getNbBullish() - LVSignal.getNbBearish());
				break;
			default : //Yoyo, Bull/Bear, we ignore the signal
				LOGGER.warn("Yo yo trend detected.");
				break;
		}
	}

}
