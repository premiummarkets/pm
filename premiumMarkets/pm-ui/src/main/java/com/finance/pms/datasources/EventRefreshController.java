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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observer;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.threads.ObserverMsg;


/**
 * The Class EventRefreshController.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshController implements  SelectionListener { //MouseListener,
	
	private static MyLogger LOGGER = MyLogger.getLogger(EventRefreshController.class);
	
	protected EventModel<? extends EventModelStrategyEngine, ?> eventModel;
	private RefreshableView view;
	Config config;
	
	private Date currentDate;
	
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
	public EventRefreshController(EventModel<? extends EventModelStrategyEngine, ?> refreshModel, RefreshableView view, Config config) {
		
		this.config = config;
		this.view = view;
		this.eventModel = refreshModel;
		try {
			currentDate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format((EventSignalConfig.getNewDate())));
		} catch (ParseException e) {
			LOGGER.error("Can't parse "+new SimpleDateFormat("yyyy/MM/dd"),e);
		}
		
	}
	
//	@Override
//	public void mouseDoubleClick(MouseEvent arg0) {
//	}
//
//	@Override
//    public void mouseUp(MouseEvent arg0) {
//	}

//	@Override
//	public void mouseDown(MouseEvent arg0) {
//		this.refreshAction(view.getAnalysisStartDate());
//	}
	
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
	//The order of the tasks is not important but the last one!
	public void updateEventRefreshModelState(Long taskKey, TaskId ... taskIds) {

		this.taskKey = taskKey;
		this.taskIds = Arrays.asList(taskIds);

	}

	/**
	 * Refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	private synchronized void refreshAction(final Date startAnalyseDate) {
		
			final List<Exception> exceptions = new ArrayList<Exception>();
			Boolean taskIsValid = true;//Only on task is last of the group so this boolean should be affected once and only once.
			TaskId lastTaskOfThisGroup = null;
			
			try {
			
				if (taskIds != null && !taskIds.isEmpty()) {
					lastTaskOfThisGroup = taskIds.get(taskIds.size() -1);
				} else {
					
					return;
				}

				
				List<EventRefreshTask> tasksGroup = new ArrayList<EventRefreshController.EventRefreshTask>();
				
				if (taskIds.contains(TaskId.FetchLists) ) { // && isFetchListStocks() ) {	

					if (!lastTaskOfThisGroup.equals(TaskId.FetchLists) || (taskIsValid = isValidTask(lastTaskOfThisGroup)) ) {

						eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);

						tasksGroup.add(new EventRefreshTask(TaskId.FetchLists) {
							public void run() {
								try {
									eventModel.callbackForlastListFetch();
									eventModel.setLastListFetch(currentDate);
								} catch (Throwable e) { 
									LOGGER.warn(e,e);
									exceptions.add(new StockListRefreshException(e));
								}
							}
						});
					} else {
						
						return;
					}

				}
				
				if (taskIds.contains(TaskId.FetchRecos)) {
					
					if (!lastTaskOfThisGroup.equals(TaskId.FetchRecos) || (taskIsValid = isValidTask(lastTaskOfThisGroup)) ) {
						
						tasksGroup.add(new EventRefreshTask(TaskId.FetchRecos) {
							public void run() {
								try {
									eventModel.callbackForReco();
								} catch (Throwable e) {
									LOGGER.warn(e,e);
									exceptions.add(new RecoRefreshException(e));
								}
							}
						});
					} else {
						
						return;
					}
					
				}
				
				if (taskIds.contains(TaskId.FetchQuotations) ) { // && isFetchQuotes()) {
					
					if (!lastTaskOfThisGroup.equals(TaskId.FetchQuotations) || (taskIsValid = isValidTask(lastTaskOfThisGroup) )) {
						
						tasksGroup.add(new EventRefreshTask(TaskId.FetchQuotations) {
							public void run() {
								try {
									eventModel.callbackForlastQuotationFetch();
									eventModel.setLastQuotationFetch(currentDate);
									eventModel.resetLastAnalyse();
								} catch (Throwable e) {
									LOGGER.warn(e,e);
									exceptions.add(new QuotatationRefreshException(e));
								}
							}
						});
					} else {
						
						return;
					}
				
				}
				
				if (taskIds.contains(TaskId.Clean)) {
	
					if (!lastTaskOfThisGroup.equals(TaskId.Clean) || (taskIsValid = isValidTask(lastTaskOfThisGroup)) ) {

						eventModel.resetAnalisysList();
						eventModel.getAnalysisList().add("talib");
						
						tasksGroup.add(new EventRefreshTask(TaskId.Clean) {
							public void run() {
								try {
									eventModel.callbackForAnalysisClean();
									eventModel.resetLastAnalyse();
								} catch (Throwable e) {
									LOGGER.warn(e,e);
									exceptions.add(new EventRefreshException(e));
								}
							}

						});
					} else {
						
						return;
					}
					
				}
				
				if (taskIds.contains(TaskId.Analysis)) {
					
					if (!lastTaskOfThisGroup.equals(TaskId.Analysis) || (taskIsValid = isValidTask(lastTaskOfThisGroup, startAnalyseDate)) ) {
						
						eventModel.resetAnalisysList();
						eventModel.getAnalysisList().add("talib");
						
						tasksGroup.add(new EventRefreshTask(TaskId.Analysis, startAnalyseDate) {
							public void run() {
								try {
									eventModel.callbackForlastAnalyse(startAnalyseDate);
									eventModel.setLastAnalyse(currentDate);
								} catch (Throwable e) {
									LOGGER.warn(e,e);
									exceptions.add(new EventRefreshException(e));
								}
							}

						});
						
					} else {
						
						return;
					}
				}
				
				//TODO you need add params for alters as well or you can't run it twice ...
				if (taskIds.contains(TaskId.Alerts)) {
					
					if (!lastTaskOfThisGroup.equals(TaskId.Alerts) || (taskIsValid = isValidTask(lastTaskOfThisGroup)) ) {
						tasksGroup.add(new EventRefreshTask(TaskId.Alerts) {
							public void run() {
								try {
									eventModel.callbackForAlerts();
								} catch (Throwable e) {
									LOGGER.warn(e,e);
									exceptions.add(new EventRefreshException(e));
								}
							}
						});
					} else {
						
						return;
					}
				}
				
				EventTaskQueue.getSingleton().offerTasks(tasksGroup);
				
				
			} finally {
				
				if (!taskIsValid && eventModel.allowsTaskReset()) exceptions.add(new InvalidEventRefreshTask(lastTaskOfThisGroup));

				//Refresh view task
				EventTaskQueue.getSingleton().offerTask(new EventRefreshTask(TaskId.ViewRefresh) {
					
					public void run() {
						
						try {
							
							//Notify observers
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
			
	}
	


//	/**
//	 * Fetch list of quotes.
//	 * 
//	 * @return true, if isfetch list of quotes
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private Boolean isFetchListStocks() {
//	
//		Date lastListFetch = eventModel.getLastListFetch();
//		LOGGER.debug("Last list update asked from event composite window was on the : "+lastListFetch);
//		return lastListFetch.before(currentDate);
//		
//	}
//	
//	/**
//	 * Fetch quotes.
//	 * 
//	 * @return true, if checks if is fetch quotes
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private Boolean isFetchQuotes() {
//		
//		Date lastFetch = eventModel.getLastQuotationFetch();
//		LOGGER.debug("Last quotation asked from event composite window was on the : " + lastFetch);
//		return lastFetch.before(currentDate);
//
//	}
	
	public boolean isValidTask(TaskId taskId, Object... addParams) {
		
		EventRefreshTask requestedTask = new EventRefreshTask(taskId, addParams) {
			@Override
			public void run() {	
			}
		};
		
		Boolean isLastRunTheSame = EventTaskQueue.getSingleton().isLastTask(requestedTask);
		
		return !isLastRunTheSame;
	}
	
	abstract class EventRefreshTask implements Runnable {
		
		private TaskId taskId;
		private Long eventInfoChangeStamp;
		private Long taskCreationStamp;
		
		private List<List<? extends Object>> taskViewParams;
		private List<Object> addParams;
		private int[] viewParamPositions;

		
		private EventRefreshTask(TaskId taskId, Object... addParams) {
			
			super();
			this.taskId = taskId;
			this.eventInfoChangeStamp = EventModel.eventInfoChangeStamp;

			this.taskViewParams = new ArrayList<List<? extends Object>>();
			Collection<? extends Object>[] eMviewParams = eventModel.viewStateParams;
			if (eMviewParams != null) {
				for (Collection<? extends Object> collection : eMviewParams) {
					ArrayList<Object> collectionClone = new ArrayList<Object>();
					if (collection != null) {
						collectionClone.addAll(collection);
						this.taskViewParams.add(collectionClone);
					} else {
						collectionClone.add(null);
					}
				}
			} 
			this.taskCreationStamp = DateFactory.midnithDate(new Date()).getTime();

			this.addParams = new ArrayList<Object>();
			if (addParams != null) {
				this.addParams.addAll(Arrays.asList(addParams));
			}
			
			viewParamPositions = eventModel.viewParamPositionsFor(taskId);
			
		}
		
		public TaskId getTaskId() {
			return taskId;
		}

		public Config getConfig() {
			return config;
		}
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((addParams == null) ? 0 : addParams.hashCode());
			result = prime * result + ((eventInfoChangeStamp == null) ? 0 : eventInfoChangeStamp.hashCode());
			result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
			result = prime * result + ((taskViewParams == null) ? 0 : taskViewParams.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EventRefreshTask other = (EventRefreshTask) obj;
			if (addParams == null) {
				if (other.addParams != null)
					return false;
			} else if (!addParams.equals(other.addParams))
				return false;
			if (eventInfoChangeStamp == null) {
				if (other.eventInfoChangeStamp != null)
					return false;
			} else if (!eventInfoChangeStamp.equals(other.eventInfoChangeStamp))
				return false;
			if (taskId != other.taskId)
				return false;
			if (taskViewParams == null) {
				if (other.taskViewParams != null)
					return false;
			} else if (!taskViewParams.equals(other.taskViewParams))
				return false;
			return true;
		}


	

		public boolean contains(EventRefreshTask other) {
			
			try {
				
				if (this.taskCreationStamp < other.taskCreationStamp) return false;
				
				if (!eventInfoChangeStamp.equals(other.eventInfoChangeStamp))
					return false;
				if (taskId != other.taskId)
					return false;
				
				if (!taskViewParams.equals(other.taskViewParams)) {

					for (int i = 0; i < viewParamPositions.length; i++) {

						if (taskViewParams.get(viewParamPositions[i]) == null) {//A null array means all for the sub set. So it contains : we skip
							continue;
						} else if (i > other.viewParamPositions.length || other.taskViewParams.get(other.viewParamPositions[i]) == null) {//other presents a null or absent array means it wants all so cannot be contained => false.
							return false;
						} else {//we check element one by one
							for (Object viewParam : other.taskViewParams.get(other.viewParamPositions[i])) {
								if (!taskViewParams.get(viewParamPositions[i]).contains(viewParam)) return false;
							}
						}
						
					}
					
				}
					
				if (addParams.isEmpty() && other.addParams.isEmpty()) return true;
				if (addParams.size() != other.addParams.size()) return false;
				
				for (int i = 0; i < other.addParams.size(); i++) {
					if ( addParams.get(i) instanceof Date && other.addParams.get(i) instanceof Date && ((Date)addParams.get(i)).after((Date)other.addParams.get(i)) ) {
						return false;
					}
					else if (!addParams.contains(other.addParams.get(i))) {
						return false;
					}
				}
				
			} catch (Exception e) {
				LOGGER.error(e,e);
				return false;
			}
			
			return true;
		
		}

		@Override
		public String toString() {
			return "EventRefreshTask [taskId=" + taskId + ", eventInfoChangeStamp=" + eventInfoChangeStamp + ", taskCreationStamp=" + taskCreationStamp
					+ ", viewParamPositions=" + Arrays.toString(viewParamPositions) + ", taskViewParams=" + taskViewParams + ", addParams=" + addParams + "]";
		}

		public void setTaskCreationStamp(Long taskCreationStamp) {
			this.taskCreationStamp = taskCreationStamp;
		}


	}
	
	
}
