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

import com.finance.pms.events.SymbolEvents;

public class ExclusiveTrendNeuralPonderationRule extends DefaultPonderationRule implements ConfigFreePonderationRule {

	private static final long serialVersionUID = -8592881368840384048L;

	public ExclusiveTrendNeuralPonderationRule(Integer sellThreshold, Integer buyThreshold) {
		super(sellThreshold, buyThreshold);
	}
	
	
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		SymbolEvents se1 = o1;
		SymbolEvents se2 = o2;
		PonderationRule p1 = new ExclusiveTrendNeuralPonderationRule(sellThreshold, buyThreshold);
		PonderationRule p2 = new ExclusiveTrendNeuralPonderationRule(sellThreshold, buyThreshold);
		
		return  compareCal(se1, se2, p1, p2);
	}
	
	@Override
	public Signal initSignal(SymbolEvents symbolEvents) {
		return new ExclusiveTrendNeuralSignal();
	}
	
	@Override
	protected void postCondition() {

		Integer nbBullish = ((ExclusiveTrendNeuralSignal) signal).getNbBullish();
		Integer nbBearish = ((ExclusiveTrendNeuralSignal) signal).getNbBearish();

		if (nbBullish >= buyThreshold && nbBearish == 0) {
			signal.setSignalWeight(buyThreshold);
		} else if (nbBearish <= sellThreshold) {
			signal.setSignalWeight(sellThreshold);
		} else {
			signal.setSignalWeight(0);
		}
	}
	
	public Integer getBuyThreshold() {
		return buyThreshold;
	}

	public Integer getSellThreshold() {
		return sellThreshold;
	}
}
