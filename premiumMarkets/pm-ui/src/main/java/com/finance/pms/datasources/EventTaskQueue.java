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
			
			LOGGER.info("\n\tTask request :\n"+task+".\n\tLast Task :\n"+lastRunningTasks+".\n" +
					"Validity : has it just been or is running : "+hasItJustRunOrIsRunning+", is it queued to run last : "+isItScheduledLast+".\n" +
					"Tasks queue : "+tasks.toString());
			return hasItJustRunOrIsRunning || isItScheduledLast;
		}
	}
	
	public void invalidateTasksCreationDates(TaskId ...taskIds) {
		for (TaskId taskToReset : taskIds) {
			for (TaskId supOrEqualTaskId : TaskId.values()) {
				if (supOrEqualTaskId.ordinal() >= taskToReset.ordinal()) {
					EventRefreshTask eventRefreshTask = lastRunningTasks.get(supOrEqualTaskId);
					if (eventRefreshTask != null) eventRefreshTask.setTaskCreationStamp(0l);
				}
			}
		}
	}


}
