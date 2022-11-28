package com.finance.pms.events.operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.finance.pms.SpringContext;

public class CalculateThreadExecutor {
	
	public static ExecutorService getExecutorInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getExecutor();
	}
	
	ExecutorService executor;
	
	protected CalculateThreadExecutor() {
		super();
		executor = Executors.newCachedThreadPool();
	}

	public ExecutorService getExecutor() {
		return executor;
	}
	
	public void close() {
		executor.shutdown();
	}
	

}
