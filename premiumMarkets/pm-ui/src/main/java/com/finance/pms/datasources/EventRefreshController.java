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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Observer;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.ObserverMsg;


/**
 * The Class EventRefreshController.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshController implements MouseListener, SelectionListener {
	
	private static MyLogger LOGGER = MyLogger.getLogger(EventRefreshController.class);
	
	public enum TaskId {FetchLists,FetchQuotations,FetchRecos,Clean,Alerts,Analysis,ViewRefresh};
	
	protected EventModel<? extends EventModelStrategyEngine> eventModel;
	private RefreshableView view;
	Config config;


	private Date currentDate;
	
//	private Boolean doFetchListStocks;
//	private Boolean doFetchQuotes;
//	private Boolean doAnalyse;
//	private Boolean doReco;
//	private Boolean doAnalysisClean;
//	private Boolean doAlerts;
	
	private Long taskKey;
	private List<TaskId> taskIds;

	
	/**
	 * Instantiates a new event refresh controller.
	 * 
	 * @param refreshModel the refresh model
	 * @param view the view
	 * 
	 * @author Guillaume Thoreton
	 */
	public EventRefreshController(EventModel<? extends EventModelStrategyEngine> refreshModel, RefreshableView view, Config config) {
		
		this.config = config;
		this.view = view;
		this.eventModel = refreshModel;
		try {
			currentDate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format((EventSignalConfig.getNewDate())));
		} catch (ParseException e) {
			LOGGER.error("Can't parse "+new SimpleDateFormat("yyyy/MM/dd"),e);
		}
		
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent arg0) {
	}

	@Override
    public void mouseUp(MouseEvent arg0) {
	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}
	
	
	/**
	 * Update event refresh model state.
	 * 
	 * @param eventModelEngine the event model engine
	 * @param talibSetected the talib setected
	 * @param masSelected the mas selected
	 * @param startAnalyseDate the start analyse date
	 * @param dofetchListOfQuotes the dofetch list of quotes
	 * @param dofetchQuotes the dofetch quotestaskKey
	 * @param doAnalyse the do analyse
	 * 
	 * @author Guillaume Thoreton
	 * @param viewStateParams 
	 */
	//public void updateEventRefreshModelState(Boolean dofetchListOfQuotes, Boolean dofetchQuotes, Boolean doAnalyse, Boolean doReco, Boolean doAnalysisClean, Boolean doAlerts, Long taskKey) {
	//The order of the tasks is not important but the last one!
	public void updateEventRefreshModelState(Long taskKey, TaskId ... taskIds) {
		
//		this.doFetchListStocks = doFetchListStocks;
//		this.doFetchQuotes = doFetchQuotes;
//		this.doAnalyse = doAnalyse;
//		this.doReco = doReco;
//		this.doAnalysisClean = doAnalysisClean;
//		this.doAlerts = doAlerts;
		this.taskKey = taskKey;
		this.taskIds = Arrays.asList(taskIds);

	}

	/**
	 * Refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	private synchronized void refreshAction(final Date startAnalyseDate) {
		
			if (taskIds == null || taskIds.isEmpty()) return;
			TaskId lastTaskOfThisGroup = taskIds.get(taskIds.size() -1);
	
			final List<Exception> exceptions = new ArrayList<Exception>();
			List<EventRefreshTask> tasksGroup = new ArrayList<EventRefreshController.EventRefreshTask>();
		 	
			if (taskIds.contains(TaskId.FetchLists) && isFetchListStocks() ) {	

				//Add Fetch List to the queue
				if (!lastTaskOfThisGroup.equals(TaskId.FetchLists) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams())) {

					eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);

					tasksGroup.add(new EventRefreshTask(TaskId.FetchLists, eventModel.getViewStateParams()) {
						public void run() {
							try {
								eventModel.callbackForlastListFetch();
								eventModel.setLastListFetch(currentDate);
							} catch (Throwable e) { 
								exceptions.add(new StockListRefreshException(e));
							}
						}
					});
				} else {
					return;
				}

			}
			
			if (taskIds.contains(TaskId.FetchRecos)) {
				//Add Fetch Reco to the queue
				if (!lastTaskOfThisGroup.equals(TaskId.FetchRecos) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams())) {
					tasksGroup.add(new EventRefreshTask(TaskId.FetchRecos, eventModel.getViewStateParams()) {
						public void run() {
							try {
								eventModel.callbackForReco();
							} catch (Throwable e) {
								exceptions.add(new RecoRefreshException(e));
							}
						}
					});
				} else {
					return;
				}
				
			}
			
			if (taskIds.contains(TaskId.FetchQuotations) && isFetchQuotes()) {
				
				//Add Fetch Quotations
				if (!lastTaskOfThisGroup.equals(TaskId.FetchQuotations) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams())) {
					tasksGroup.add(new EventRefreshTask(TaskId.FetchQuotations, eventModel.getViewStateParams()) {
						public void run() {
							try {
								eventModel.callbackForlastQuotationFetch();
								eventModel.setLastQuotationFetch(currentDate);
								eventModel.resetLastAnalyse();
							} catch (Throwable e) {
								exceptions.add(new QuotatationRefreshException(e));
							}
						}
					});
				} else {
					return;
				}
			
			}
			
			if (taskIds.contains(TaskId.Clean)) {
			
				//Add clear Analysis
				if (!lastTaskOfThisGroup.equals(TaskId.Clean) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams())) {

					eventModel.resetAnalisysList();
					eventModel.getAnalysisList().add("talib");
					
					tasksGroup.add(new EventRefreshTask(TaskId.Clean, eventModel.getViewStateParams()) {
						public void run() {
							try {
								eventModel.callbackForAnalysisClean();
								eventModel.resetLastAnalyse();
							} catch (Throwable e) {
								exceptions.add(new EventRefreshException(e));
							}
						}

					});
				} else {
					return;
				}
				
			}
			
			if (taskIds.contains(TaskId.Analysis)) {
				
				//Add Analysis
				if (!lastTaskOfThisGroup.equals(TaskId.Analysis) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams(), startAnalyseDate)) {
					
					eventModel.resetAnalisysList();
					eventModel.getAnalysisList().add("talib");
					
					tasksGroup.add(new EventRefreshTask(TaskId.Analysis, eventModel.getViewStateParams(), startAnalyseDate) {
						public void run() {
							try {
								eventModel.callbackForlastAnalyse(startAnalyseDate);
								eventModel.setLastAnalyse(currentDate);
							} catch (Throwable e) {
								exceptions.add(new EventRefreshException(e));
							}
						}

					});
				} else {
					return;
				}
			}
			
			if (taskIds.contains(TaskId.Alerts)) {
				
				//Add Alerts calc
				if (!lastTaskOfThisGroup.equals(TaskId.Alerts) || isValidTask(lastTaskOfThisGroup, eventModel.getViewStateParams(), startAnalyseDate)) {
					tasksGroup.add(new EventRefreshTask(TaskId.Alerts, eventModel.getViewStateParams()) {
						public void run() {
							try {
								eventModel.callbackForAlerts();
							} catch (Throwable e) {
								exceptions.add(new EventRefreshException(e));
							}
						}
					});
				} else {
					return;
				}
			}
			
			
			EventTaskQueue.getSingleton().offerTasks(tasksGroup);
			
			//Refresh view task
			EventTaskQueue.getSingleton().offerTask(new EventRefreshTask(TaskId.ViewRefresh, eventModel.getViewStateParams()) {
				
				public void run() {
					
					try {
						//Notifiy observers
						for (Observer obs : eventModel.getEngineObservers()) {
							obs.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.DONE, taskKey));
						}

						//Update potential registered view when finished
						view.getDisplay().syncExec(new Runnable() {
							public void run() {
								view.endRefreshAction(exceptions);
							}

						});
						
					} catch (Exception e) {
						LOGGER.error(e,e);
					}
				}
				
			});
		
	}
	


	/**
	 * Fetch list of quotes.
	 * 
	 * @return true, if isfetch list of quotes
	 * 
	 * @author Guillaume Thoreton
	 */
	private Boolean isFetchListStocks() {
	
		Date lastListFetch = eventModel.getLastListFetch();
		LOGGER.debug("Last list update asked from event composite window was on the : "+lastListFetch);
		return lastListFetch.before(currentDate);
		
	}
	
	/**
	 * Fetch quotes.
	 * 
	 * @return true, if checks if is fetch quotes
	 * 
	 * @author Guillaume Thoreton
	 */
	private Boolean isFetchQuotes() {
		
		Date lastFetch = eventModel.getLastQuotationFetch();
		LOGGER.debug("Last quotation asked from event composite window was on the : " + lastFetch);
		return lastFetch.before(currentDate);

	}
	
	public boolean isValidTask(TaskId taskId, Object... addParams) {
		
		EventRefreshTask requestedTask = new EventRefreshTask(taskId, eventModel.getViewStateParams(), addParams) {
			@Override
			public void run() {	
			}
		};
		
		Boolean isLastRunTheSame = EventTaskQueue.getSingleton().isLastTask(requestedTask);
		
		return !isLastRunTheSame;
	}
	
	
	abstract class EventRefreshTask implements Runnable {
		
		private TaskId taskId;
		private int paramSign;
		
		public EventRefreshTask(TaskId taskId, Object[] viewParams, Object ... addParams) {
			super();
			this.taskId = taskId;
			this.paramSign = 0;
			if (viewParams != null) {
				for (Object object : viewParams) {
					this.paramSign = this.paramSign + object.hashCode();
				}
			}
			if (addParams != null) {
				for (Object object : addParams) {
					this.paramSign = this.paramSign + object.hashCode();
				}
			}
		}
		
		public TaskId getTaskId() {
			return taskId;
		}

		public Config getConfig() {
			return config;
		}
		
		@Override
		public String toString() {
			return taskId.name();
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + paramSign;
			result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof EventRefreshTask))
				return false;
			EventRefreshTask other = (EventRefreshTask) obj;
			if (paramSign != other.paramSign)
				return false;
			if (taskId != other.taskId)
				return false;
			return true;
		}

	}
	
	
}
