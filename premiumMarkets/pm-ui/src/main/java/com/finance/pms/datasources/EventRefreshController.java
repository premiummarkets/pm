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
import org.eclipse.swt.widgets.Display;

import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.threads.ObserverMsg;


/**
 * The Class EventRefreshController.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshController implements SelectionListener { //MouseListener,

    private static MyLogger LOGGER = MyLogger.getLogger(EventRefreshController.class);

    protected EventModel<? extends EventModelStrategyEngine<?>, ?> eventModel;
    protected RefreshableView view;
    protected Config config;

    private Date currentDate;

    private Long taskKey;
    private List<TaskId> taskIds;

    public EventRefreshController(EventModel<? extends EventModelStrategyEngine<?>, ?> refreshModel, RefreshableView view, Config config) {

        this.config = config;
        this.view = view;
        this.eventModel = refreshModel;
        try {
            currentDate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format((DateFactory.getNowEndDate())));
        } catch (ParseException e) {
            LOGGER.error("Can't parse "+new SimpleDateFormat("yyyy/MM/dd"),e);
        }

    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        view.initRefreshAction();
        this.refreshAction(view.getAnalysisStartDate(), view.getAnalysisEndDate());
    }

    @Override
    public void widgetSelected(SelectionEvent arg0) {
        view.initRefreshAction();
        this.refreshAction(view.getAnalysisStartDate(), view.getAnalysisEndDate());
    }

    //The order of the tasks is not important but the last one!
    public void updateEventRefreshModelState(Long taskKey, TaskId ... taskIds) {

        this.taskKey = taskKey;
        this.taskIds = Arrays.asList(taskIds);

    }

    private synchronized void refreshAction(final Date startAnalyseDate , final Date endAnalysisDate) {

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

                if (!lastTaskOfThisGroup.equals(TaskId.FetchLists) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams)) ) {

                    eventModel.setLastQuotationFetch(DateFactory.DEFAULT_DATE);

                    tasksGroup.add(new EventRefreshTask(TaskId.FetchLists, eventModel.rootViewParam , eventModel.otherViewParams) {
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

                if (!lastTaskOfThisGroup.equals(TaskId.FetchRecos) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams)) ) {

                    tasksGroup.add(new EventRefreshTask(TaskId.FetchRecos, eventModel.rootViewParam , eventModel.otherViewParams) {
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

                if (!lastTaskOfThisGroup.equals(TaskId.FetchQuotations) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams) )) {

                    tasksGroup.add(new EventRefreshTask(TaskId.FetchQuotations, eventModel.rootViewParam , eventModel.otherViewParams) {
                        public void run() {
                            try {
                                eventModel.callbackForlastQuotationFetch(startAnalyseDate, endAnalysisDate);
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

                if (!lastTaskOfThisGroup.equals(TaskId.Clean) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams)) ) {

                    eventModel.resetAnalysisList();
                    eventModel.getAnalysisList().add("selectedIndsCalculator");

                    tasksGroup.add(new EventRefreshTask(TaskId.Clean, eventModel.rootViewParam , eventModel.otherViewParams) {
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

                if (!lastTaskOfThisGroup.equals(TaskId.Analysis) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams, startAnalyseDate, endAnalysisDate)) ) {

                    eventModel.resetAnalysisList();
                    eventModel.getAnalysisList().add("selectedIndsCalculator");

                    tasksGroup.add(new EventRefreshTask(TaskId.Analysis, eventModel.rootViewParam , eventModel.otherViewParams, startAnalyseDate, endAnalysisDate) {
                        public void run() {
                            try {
                                eventModel.callbackForlastAnalyse(startAnalyseDate, endAnalysisDate);
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

                if (!lastTaskOfThisGroup.equals(TaskId.Alerts) || (taskIsValid = isValidTask(lastTaskOfThisGroup, eventModel.rootViewParam , eventModel.otherViewParams)) ) {
                    tasksGroup.add(new EventRefreshTask(TaskId.Alerts, eventModel.rootViewParam , eventModel.otherViewParams) {
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

            if (!tasksGroup.isEmpty()) eventModel.checkNumberOfOtherParams(eventModel.otherViewParams);
            EventTaskQueue.getSingleton().offerTasks(tasksGroup);


        } finally {

            if (!taskIsValid && eventModel.allowsTaskReset()) exceptions.add(new InvalidEventRefreshTask(lastTaskOfThisGroup, eventModel.rootViewParam));

            //Refresh view task
            EventTaskQueue.getSingleton().offerTask(new EventRefreshTask(TaskId.ViewRefresh, eventModel.rootViewParam, eventModel.otherViewParams) {

                public void run() {

                    try {

                        //Notify observers
                        for (Observer obs : eventModel.getEngineObservers()) {
                            obs.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.DONE, taskKey));
                        }

                        //Update potential registered view when finished
                        Display.getDefault().asyncExec(new Runnable() {
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

    public boolean isValidTask(TaskId taskId, Object rootParam, Collection<? extends Object>[] otherParams, Object... addParams) {

        EventRefreshTask requestedTask = new EventRefreshTask(taskId, rootParam, otherParams, addParams) {
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
        private Long taskCreationDate;

        @SuppressWarnings("rawtypes")
        private List taskRootParam;
        private List<List<? extends Object>> taskOtherParams;
        private List<Object> addParams;
        private int[] viewParamPositionsForTaskId;


        @SuppressWarnings("unchecked")
        private EventRefreshTask(TaskId taskId, Object rootViewParam, Collection<? extends Object>[] otherViewParams, Object... addParams) {

            super();
            this.taskId = taskId;
            this.eventInfoChangeStamp = EventModel.eventInfoChangeStamp;

            if (rootViewParam != null) {
                if (rootViewParam instanceof Collection) {
                    this.taskRootParam = new ArrayList<Object>(((Collection<? extends Object>) rootViewParam));
                } else {
                    this.taskRootParam = new ArrayList<Object>();
                    this.taskRootParam.add(rootViewParam);
                }
            } else {
                this.taskRootParam = null;
            }

            this.taskOtherParams = new ArrayList<List<? extends Object>>();
            Collection<? extends Object>[] eMviewParams = otherViewParams;
            if (eMviewParams != null) {
                for (Collection<? extends Object> collection : eMviewParams) {
                    ArrayList<Object> collectionClone = new ArrayList<Object>();
                    if (collection != null) {
                        collectionClone.addAll(collection);
                        this.taskOtherParams.add(collectionClone);
                    } else {
                        collectionClone.add(null);
                    }
                }
            } 
            this.taskCreationDate = DateFactory.midnithDate(new Date()).getTime();

            this.addParams = new ArrayList<Object>();
            if (addParams != null) {
                this.addParams.addAll(Arrays.asList(addParams));
            }

            viewParamPositionsForTaskId = eventModel.viewParamPositionsFor(taskId);

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
            result = prime * result + ((taskRootParam == null) ? 0 : taskRootParam.hashCode());
            result = prime * result + ((taskOtherParams == null) ? 0 : taskOtherParams.hashCode());
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
            if (taskRootParam == null) {
                if (other.taskRootParam != null)
                    return false;
            } else if (!taskRootParam.equals(other.taskRootParam))
                return false;
            if (taskOtherParams == null) {
                if (other.taskOtherParams != null)
                    return false;
            } else if (!taskOtherParams.equals(other.taskOtherParams))
                return false;
            return true;
        }


        public boolean contains(EventRefreshTask other) {

            try {

                if (this.taskCreationDate < other.taskCreationDate) return false;

                if (!eventInfoChangeStamp.equals(other.eventInfoChangeStamp))
                    return false;
                if (taskId != other.taskId)
                    return false;

                if (taskRootParam == null || other.taskRootParam == null|| !taskRootParam.equals(other.taskRootParam)) {
                    if (taskRootParam == null) {
                        //null means all. So it contains : we skip
                    } 
                    else  if (other.taskRootParam == null) {
                        //other presents a null Collection means it wants all so cannot be contained => false.
                        return false;
                    } else {
                        for (Object viewParam : other.taskRootParam) {
                            if (!taskRootParam.contains(viewParam)) return false;
                        }
                    }
                }

                if (!taskOtherParams.equals(other.taskOtherParams)) {

                    if (viewParamPositionsForTaskId.length != other.viewParamPositionsForTaskId.length) throw new Exception("Tasks are not comparable : "+this+" and "+other);

                    for (int i = 0; i < viewParamPositionsForTaskId.length; i++) {

                        if (viewParamPositionsForTaskId[i] >= taskOtherParams.size() && other.viewParamPositionsForTaskId[i] >= other.taskOtherParams.size()) {//Both params required but not set? That should be a bug? We ignore?
                            LOGGER.warn("Other param required but not set? That should be a bug? We ignore and return true while comparing : "+this+ " and "+other, new Exception());
                            return true;
                        } else if (viewParamPositionsForTaskId[i] >= taskOtherParams.size()) {//This params required but not set we return false : this does not contains other
                            return false;
                        }  else if (other.viewParamPositionsForTaskId[i] >= other.taskOtherParams.size()) {//Other params required but not set we return true : this contains other
                            return true;
                        }

                        if (taskOtherParams.get(viewParamPositionsForTaskId[i]) == null) {//A null array means all for the sub set. So it contains : we skip
                            continue;
                        } else if (other.taskOtherParams.get(other.viewParamPositionsForTaskId[i]) == null) {//Other presents a null array means it wants all so cannot be contained => false.
                            return false;
                        } else {//we check element one by one
                            for (Object viewParam : other.taskOtherParams.get(other.viewParamPositionsForTaskId[i])) {
                                if (!taskOtherParams.get(viewParamPositionsForTaskId[i]).contains(viewParam)) return false;
                            }
                        }

                    }

                }

                if (addParams.isEmpty() && other.addParams.isEmpty()) return true;
                if (addParams.size() != other.addParams.size()) return false;

                for (int i = 0; i < other.addParams.size(); i++) {
                    int dateNum = 0;//TODO an encapsulating object
                    boolean isStartDate = dateNum == 0;
                    if ( addParams.get(i) instanceof Date && other.addParams.get(i) instanceof Date && isStartDate && ((Date)addParams.get(i)).after((Date)other.addParams.get(i))) {
                        dateNum ++;
                        return false;
                    }
                    boolean isEndDate = dateNum == 1;
                    if ( addParams.get(i) instanceof Date && other.addParams.get(i) instanceof Date && isEndDate && ((Date)addParams.get(i)).before((Date)other.addParams.get(i))) {
                        dateNum ++;
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
            return "EventRefreshTask [taskId=" + taskId + ", eventInfoChangeStamp=" + eventInfoChangeStamp + ", taskCreationDate=" + taskCreationDate
                    + ", viewParamPositions=" + Arrays.toString(viewParamPositionsForTaskId) + ", taskRootParam=" + taskRootParam  + ", taskOtherParams=" + taskOtherParams + ", addParams=" + addParams + "]";
        }

        public void setTaskCreationDate(Long taskCreationDate) {
            this.taskCreationDate = taskCreationDate;
        }

    }


}
