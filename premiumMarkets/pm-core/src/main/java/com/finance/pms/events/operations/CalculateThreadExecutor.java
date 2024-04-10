package com.finance.pms.events.operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;

public class CalculateThreadExecutor {
	
	private static MyLogger LOGGER = MyLogger.getLogger(CalculateThreadExecutor.class);
	
	public static CalculateThreadExecutor getInstance() {
		return  SpringContext.getSingleton().getBean(CalculateThreadExecutor.class);
	}
	
	public static ExecutorService getRandomInfiniteExecutorInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getRandomInfiniteExecutor();
	}
	
	public static Boolean needsThrottling(ExecutorService executorService) {
		if (executorService instanceof ThreadPoolExecutor) {
			int activeCount = ((ThreadPoolExecutor) executorService).getActiveCount();
		    if (activeCount > Integer.valueOf(MainPMScmd.getMyPrefs().get("operandsloop.semaphore.nbthread","10000"))) return true; 
		}
		return false;
	}
	
	public static ExecutorService getJoinForkExecutorInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getJoinForkExecutor();
	}
	
	public static Semaphore getSemaphoreInstance() {
		return SpringContext.getSingleton().getBean(CalculateThreadExecutor.class).getSemaphore();
	}
	
	private ExecutorService randomInfiniteExecutor;
	private ExecutorService joinForkExecutor;
	private Semaphore semaphore;
	
	protected CalculateThreadExecutor() {
		super();
		randomInfiniteExecutor = initRandomInfiniteExecutor();
		joinForkExecutor = initJoinForkExecutor();
		semaphore = new Semaphore(Integer.valueOf(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","5")));
	}

	private ExecutorService getRandomInfiniteExecutor() {
		if (randomInfiniteExecutor.isShutdown()) {
			synchronized (randomInfiniteExecutor) {
				if (randomInfiniteExecutor.isShutdown()) {
					randomInfiniteExecutor = initRandomInfiniteExecutor();
				}
			}
		}
		return randomInfiniteExecutor;
	}
	
	private ExecutorService getJoinForkExecutor() {
		if (joinForkExecutor.isShutdown()) {
			synchronized (joinForkExecutor) {
				if (joinForkExecutor.isShutdown()) {
					joinForkExecutor = initRandomInfiniteExecutor();
				}
			}
		}
		return joinForkExecutor;
	}
	
	private ExecutorService initRandomInfiniteExecutor() {
		
//      new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
		return Executors.newCachedThreadPool();
		
//		ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
//		ExecutorService newWorkStealingPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()*2, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
//		LOGGER.info("Fork join pool: " + newWorkStealingPool);
//		return newWorkStealingPool;
//		
		//Do no use. This will block
//		new ThreadPoolExecutor(10000, 10000,
//                 0L, TimeUnit.MILLISECONDS,
//                 new LinkedBlockingQueue<Runnable>());
//		return Executors.newFixedThreadPool(10000);
	}
	
	private ExecutorService initJoinForkExecutor() {
//		ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
		ExecutorService newWorkStealingPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()*3, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
		LOGGER.info("Fork join pool: " + newWorkStealingPool);
		return newWorkStealingPool;
		
	}
	
	private Semaphore getSemaphore() {
		return semaphore;
	}
	
	public void close() {
		semaphore.drainPermits();
		randomInfiniteExecutor.shutdownNow();
		joinForkExecutor.shutdownNow();
		semaphore.release(Integer.valueOf(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","5")));
	}
	

}
