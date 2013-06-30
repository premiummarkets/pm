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
package com.finance.pms.datasources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.NotEnoughDataException;


/**
 * The Class MonitoredEventRefreshModel.
 * 
 * @author Guillaume Thoreton
 */
public class RefreshMonitoredStrategyEngine extends UserContentStrategyEngine {

	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Stock> getViewParamRoot(Collection<? extends Object>... viewStateParams) {
		
		if (viewStateParams != null && viewStateParams.length != 0) {
			return (Collection<Stock>) viewStateParams[0];
		}
		
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Object>[] setViewStateParams(Object rootParam, Collection<? extends Object>... otherParams) {
		Collection<? extends Object>[] ret = new Collection[2];
		
		if (rootParam == null) {
			ret[0] = null;
		} else {
			if (rootParam instanceof Collection) {
				ret[0] = (Collection<Stock>) rootParam;
			} else {
				throw new IllegalArgumentException("Expecting Collection<Stock>");
			}
		}
		
		if (otherParams.length != 0) {
			for (int i = 1; i < ret.length; i++) {
				ret[i] = (i-1 < otherParams.length)?otherParams[i-1]:null;
			}
		}
		
		return ret;
		
	}
	
	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
		switch (taskId) {
		case Analysis:
		case Clean :
			return new int[]{1};
		default :
			return new int[]{};
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForlastAnalyse(ArrayList<String> analysisList, Date startAnalyseDate, Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) throws NotEnoughDataException {
		if (viewStateParams.length  == 2) {//Tampering the config to recalculate only independent indicators that need to.
			tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		} 
		super.callbackForlastAnalyse(analysisList, startAnalyseDate, engineObservers, viewStateParams);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callbackForAnalysisClean(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) {
		if (viewStateParams.length  == 2) {//Tampering the config to recalculate only independent indicators that need to.
			tamperEventConfig((Collection<EventInfo>) viewStateParams[1]);
		} 
		super.callbackForAnalysisClean(engineObservers, viewStateParams);
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}
	
}
