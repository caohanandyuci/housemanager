package com.ch.commutils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;


public class DataLoaderEngine {

	public DataLoaderEngine(){
		taskExecutor = createTaskExecutor();
	}
	
	private final int THREAD_POOL_SIZE = 5;
	
	private final int TREAD_PRIORITY = Thread.NORM_PRIORITY;
	
	
	private Executor taskExecutor;
	private Executor taskExecutorForCachedImages;
	private Executor taskDistributor;
	
	public void submit(final Runnable task){
		initExecutorsIfNeed();
		taskExecutor.execute(task);
	}
	
	private void initExecutorsIfNeed() {
		if (((ExecutorService) taskExecutor).isShutdown()) {
			taskExecutor = createTaskExecutor();
		}
//		if (!configuration.customExecutorForCachedImages && ((ExecutorService) taskExecutorForCachedImages)
//				.isShutdown()) {
//			taskExecutorForCachedImages = createTaskExecutor();
//		}
	}

	private Executor createTaskExecutor() {
		return DefaultConfigurationFactory
				.createExecutor(THREAD_POOL_SIZE, TREAD_PRIORITY,null);
	}
}
