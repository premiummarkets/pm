package com.finance.pms.datasources;

import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;


public class RefreshChartHightlited extends UserContentStrategyEngine {

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Object... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	protected String passOneOverwriteMode() {
		return "force";
	}

}
