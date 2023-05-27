package com.finance.pms.events.operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;

public class CalculateThreadExecutor {
	
	public static ExecutorService getExecutorInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getExecutor();
	}
	
	public static Semaphore getSemaphoreInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getSemaphore();
	}
	
	private ExecutorService executor;
	private Semaphore semaphore;
	
	protected CalculateThreadExecutor() {
		super();
		executor = Executors.newCachedThreadPool();
		semaphore = new Semaphore(Integer.valueOf(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","10")));
	}

	private ExecutorService getExecutor() {
		if (executor.isShutdown()) executor = Executors.newCachedThreadPool();
		return executor;
	}
	
	private Semaphore getSemaphore() {
		return semaphore;
	}
	
	public void close() {
		semaphore.drainPermits();
		executor.shutdown();
	}
	

}
