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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventRefreshController.EventRefreshTask;
import com.finance.pms.threads.ConfigThreadLocal;

public class EventTaskQueue {

	protected static MyLogger LOGGER = MyLogger.getLogger(EventTaskQueue.class);

	private static EventTaskQueue singleton;

	private LinkedBlockingQueue<EventRefreshTask> tasks;
	private Thread queueScanner;
	private Map<TaskId,EventRefreshTask> lastRunningTasks;


	private EventTaskQueue() {
		super();

		tasks = new LinkedBlockingQueue<EventRefreshTask>();
		lastRunningTasks = new HashMap<TaskId, EventRefreshTask>();

		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				try {

					EventRefreshTask task;
					while ( true ) {

						task = tasks.take();

						try {

							if (!task.getTaskId().equals(TaskId.ViewRefresh)) setLastTaskFor(task);
							ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, task.getConfig());
							task.run();	

						} catch (Throwable e) {
							LOGGER.error(e,e);
						}

					}

				} catch (InterruptedException e) {
					LOGGER.info(e);
				}

			}

			protected void setLastTaskFor(EventRefreshTask task) {
				invalidateTasksCreationDates(task.getTaskId());
				lastRunningTasks.put(task.getTaskId(), task);
			}
		};

		queueScanner = new Thread(runnable);
		queueScanner.start(); 
	}

	public static EventTaskQueue getSingleton() {
		if (EventTaskQueue.singleton == null) {
			singleton = new  EventTaskQueue();
		}
		return singleton;
	}

	public void offerTask(EventRefreshTask task) {
		synchronized (queueScanner) {
			tasks.offer(task);
		}
	}

	public void offerTasks(List<EventRefreshTask> eventRefreshTasks) {
		synchronized (queueScanner) {
			tasks.addAll(eventRefreshTasks);
		}
	}


	public Boolean contains(EventRefreshTask task) {
		synchronized (queueScanner) {
			return tasks.contains(task);
		}
	}

	public Boolean isLastTask(EventRefreshTask task) {
		synchronized (queueScanner) {

			Boolean hasItJustRunOrIsRunning = ( lastRunningTasks.get(task.getTaskId()) != null ) && lastRunningTasks.get(task.getTaskId()).contains(task);

			EventRefreshTask[] array = tasks.toArray(new EventRefreshTask[0]);
			int i=array.length-1;
			while (i >= 0 && array[i].getTaskId().equals(TaskId.ViewRefresh)) {
				i--;
			}
			Boolean isItScheduledLast = i>=0 && array[i].contains(task);

			LOGGER.info("\n\tTask request:\n" + task + ".\n\tLast Task:\n" + lastRunningTasks + ".\n" +
					"Validity: has it just been or is running: " + hasItJustRunOrIsRunning + ", is it queued to run last: " + isItScheduledLast + ".\n" +
					"Tasks queue: " + tasks.toString());
			return hasItJustRunOrIsRunning || isItScheduledLast;
		}
	}

	public void invalidateTasksCreationDates(TaskId ...taskIds) {
		for (TaskId taskToReset : taskIds) {
			for (TaskId supOrEqualTaskId : TaskId.values()) {
				if (supOrEqualTaskId.ordinal() >= taskToReset.ordinal()) {
					EventRefreshTask eventRefreshTask = lastRunningTasks.get(supOrEqualTaskId);
					if (eventRefreshTask != null) eventRefreshTask.setTaskCreationDate(0l);
				}
			}
		}
	}

	public void close() {
		queueScanner.interrupt();
	}


}
