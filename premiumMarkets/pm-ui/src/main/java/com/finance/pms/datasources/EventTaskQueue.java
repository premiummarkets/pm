package com.finance.pms.datasources;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventRefreshController.EventRefreshTask;
import com.finance.pms.datasources.EventRefreshController.TaskId;
import com.finance.pms.threads.ConfigThreadLocal;

public class EventTaskQueue {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventTaskQueue.class);
	
	private static EventTaskQueue singleton;

	private LinkedBlockingQueue<EventRefreshTask> tasks;
	private Thread queueScanner;
	private EventRefreshTask lastRunningTask;
	

	private EventTaskQueue() {
		super();
		
		tasks = new LinkedBlockingQueue<EventRefreshTask>();
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				try {
					
					EventRefreshTask task;
					while ( true ) {
						
						task = tasks.take();
						
						try {		
							
							if (!task.getTaskId().equals(TaskId.ViewRefresh)) lastRunningTask = task;
							ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, task.getConfig());
							task.run();	
							
						} catch (Throwable e) {
							LOGGER.error(e,e);
							//task.getExceptions().add(e);
						}
						
					}
					
				} catch (InterruptedException e) {
					LOGGER.info(e);
				}
				
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
			boolean hasItJustRunOrIsRunning = (lastRunningTask != null) && lastRunningTask.equals(task);
			boolean isItScheduledLast = !tasks.isEmpty() && tasks.toArray()[0].equals(task);
			
			LOGGER.info("\n\tTask request :\n"+task+".\n\tLast Task :\n"+lastRunningTask+".\nValidity : has it just been or is running : "+hasItJustRunOrIsRunning+", is it queued to run last : "+isItScheduledLast);
			return hasItJustRunOrIsRunning || isItScheduledLast;
		}
	}


}
