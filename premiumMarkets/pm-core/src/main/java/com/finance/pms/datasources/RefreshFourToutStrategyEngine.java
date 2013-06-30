package com.finance.pms.datasources;

import java.util.Arrays;
import java.util.Collection;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;


public class RefreshFourToutStrategyEngine extends UserContentStrategyEngine {

	
	@Override
	protected String passOneOverwriteMode() {
		return "auto";
	}

	@Override
	public void callbackForAlerts(Set<Observer> engineObservers, Collection<? extends Object>... viewStateParams) {
		throw new NotImplementedException();
	}
	
	@Override
	public Object getViewParamRoot(Collection<? extends Object>... viewStateParams) {
		
		if (viewStateParams != null && viewStateParams.length != 0) {
			return viewStateParams[0];
		}
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<? extends Object>[] setViewStateParams(Object rootParam, Collection<? extends Object>... otherParams) {
		
		Collection<? extends Object>[] ret = new Collection[otherParams.length+1];
	
		if (rootParam == null) {
			ret[0] = null;
		} else {
			if (rootParam instanceof Collection) {
				ret[0] = (Collection<? extends Object>) rootParam;
			} else {
				ret[0] = Arrays.asList(new Object[]{rootParam});
			}
		}
		
		if (otherParams.length != 0) {
			for (int i = 1; i < ret.length; i++) {
				ret[i] = otherParams[i-1];
			}
		}
		
		return ret;
	}
	
	@Override
	public int[] otherViewParamPositionsFor(TaskId taskId) {
			return new int[]{};
	}

	@Override
	public boolean allowsTaskReset() {
		return true;
	}

}
