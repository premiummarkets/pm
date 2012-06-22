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
package com.finance.pms.datasources;

import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class EventRefreshModel.
 * 
 * @author Guillaume Thoreton
 */
public class EventModel<T extends EventModelStrategyEngine> {

	protected static MyLogger LOGGER = MyLogger.getLogger(PropertyChangeListener.class);
	
	private ArrayList<String> analysisList;
	private T eventRefreshStrategyEngine;
	private Set<Observer> engineObservers;
	private Object[] viewStateParams;


	/** The DEFAUL t_ date. */
	public static Date DEFAULT_DATE;
	static {
		DEFAULT_DATE = new Date();
		try {
			DEFAULT_DATE = new SimpleDateFormat("yyyy/MM/dd").parse("1970/01/01");
		} catch (ParseException e1) {
			LOGGER.error("Shouldn't be here",e1);
		}
	}
	
	public static Map<Class<? extends EventModelStrategyEngine>, EventModel<? extends EventModelStrategyEngine>> 
													models = new HashMap<Class<? extends EventModelStrategyEngine>, EventModel<? extends EventModelStrategyEngine>>();
	
	@SuppressWarnings("unchecked")
	public static <T extends EventModelStrategyEngine> EventModel<T> getInstance(T modelStrategyEngine, Observer... modelObserver)  {
		
		try {
			EventModel<T> eventModel = (EventModel<T>) models.get(modelStrategyEngine.getClass());
			if (eventModel == null) {
				eventModel = new EventModel<T>(modelStrategyEngine);	
				models.put(modelStrategyEngine.getClass(), eventModel);
			}
			
			if (modelObserver != null) {
				for (Observer observer : modelObserver) {
					eventModel.addEngineObserver(observer);
				}
			}
			
			return eventModel;
			
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Instantiates a new event refresh model.
	 * 
	 * @param startAnalyseDate the start analyse date
	 * 
	 * @author Guillaume Thoreton
	 * @param modelStrategyEngine 
	 */
	private EventModel(T modelStrategyEngine) {

		this.analysisList = new ArrayList<String>();	
		this.eventRefreshStrategyEngine = modelStrategyEngine;
		this.engineObservers = new HashSet<Observer>();
		
	}
	
	/**
	 * Hard reset last quotation date.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void hardResetLastQuotationDate() {
		this.setLastAnalyse(EventModel.DEFAULT_DATE);
		this.setLastQuotationFetch(EventModel.DEFAULT_DATE);
	
	}


	/**
	 * Callback forlast analyse.
	 * 		
		//quotationUpdate.getQuotesForAllUserPortfoliosAndMonitored();
	 * @author Guillaume Thoreton
	 */
	public void callbackForlastAnalyse(Date startAnalyseDate) {
		eventRefreshStrategyEngine.callbackForlastAnalyse(analysisList, startAnalyseDate, engineObservers, viewStateParams);

	}

	/**
	 * Callback forlast list fetch.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void callbackForlastListFetch() {
		eventRefreshStrategyEngine.callbackForlastListFetch(engineObservers);
	}
	
	public void callbackForReco() {
		eventRefreshStrategyEngine.callbackForReco(engineObservers);

	}

	/**
	 * Callback forlast quotation fetch.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void callbackForlastQuotationFetch() {
		eventRefreshStrategyEngine.callbackForlastQuotationFetch(engineObservers, viewStateParams);

	}

	/**
	 * Gets the last analyse.
	 * 
	 * @return the last analyse
	 */
	public Date getLastAnalyse() {
		return eventRefreshStrategyEngine.getLastAnalyse(null);
	}

	/**
	 * Gets the last list fetch.
	 * 
	 * @return the last list fetch
	 */
	public Date getLastListFetch() {
		return eventRefreshStrategyEngine.getLastListFetch(null);
	}
	
	/**
	 * Gets the last quotation fetch.
	 * 
	 * @return the last quotation fetch
	 */
	public Date getLastQuotationFetch() {
		return eventRefreshStrategyEngine.getLastQuotationFetch(null);
	}

	/**
	 * Sets the last analyse.
	 * 
	 * @param lastAnalyse the new last analyse
	 */
	public void setLastAnalyse(Date lastAnalyse) {
		eventRefreshStrategyEngine.setLastAnalyse(lastAnalyse, null);
	}

	/**
	 * Sets the last list fetch.
	 * 
	 * @param lastListFetch the new last list fetch
	 */
	public void setLastListFetch(Date lastListFetch) {
		eventRefreshStrategyEngine.setLastListFetch(lastListFetch, null);
	}

	/**
	 * Sets the last quotation fetch.
	 * 
	 * @param lastQuotationFetch the new last quotation fetch
	 */
	public void setLastQuotationFetch(Date lastQuotationFetch) {
		eventRefreshStrategyEngine.setLastQuotationFetch(lastQuotationFetch, null);
	}

	
	/**
	 * Reset analisys list.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void resetAnalisysList() {
		analysisList.clear();
	}

	
	/**
	 * Gets the analisys list.
	 * 
	 * @return the analisys list
	 */
	public ArrayList<String> getAnalysisList() {
		return analysisList;
	}
	

	/**
	 * Reset last analyse.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void resetLastAnalyse() {
		eventRefreshStrategyEngine.setLastAnalyse(EventModel.DEFAULT_DATE, null);
	}
	
	
	/**
	 * Sets the model action engine.
	 * 
	 * @param eventModelEngine the new model action engine
	 */
	public void setModelActionEngine(T eventModelEngine) {
		this.eventRefreshStrategyEngine = eventModelEngine;
	}
	
	void addEngineObserver(Observer engineObserver) {
		this.engineObservers.add(engineObserver);
	}

	Set<Observer> getEngineObservers() {
		return engineObservers;
	}

	public void setStateParams(Object... viewStateParams) {
		this.viewStateParams = viewStateParams;	
	}
	
}
