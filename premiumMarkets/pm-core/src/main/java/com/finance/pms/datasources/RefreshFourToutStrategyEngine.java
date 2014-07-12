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
package com.finance.pms.datasources;

import java.util.Collection;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.shares.Stock;


public class RefreshFourToutStrategyEngine extends UserContentStrategyEngine<Object> {

	
	@Override
	protected String passOneOverwriteMode() {
		return "auto";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object rootParam, @SuppressWarnings("unchecked") Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
			return new int[]{};
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}

	@Override
	public int otherViewParamLength() {
		return -1;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<Stock> buildStockListFrom(Object rootParam) {
		return (List<Stock>) rootParam;
	}

}
