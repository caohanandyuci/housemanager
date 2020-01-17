package com.ch.commutils;


public class ThreadPoolTools {
	
	private volatile static ThreadPoolTools instance;
	
	private DataLoaderEngine mDataLoaderEngine = null;
	
	public synchronized void init() {
		if(mDataLoaderEngine == null){
			mDataLoaderEngine = new DataLoaderEngine();
		}
	}

	public static ThreadPoolTools getInstance() {
		if (instance == null) {
			synchronized (ThreadPoolTools.class) {
				if (instance == null) {
					instance = new ThreadPoolTools();
				}
			}
		}
		return instance;
	}

	public void SubmitTask(Runnable task){
		if(mDataLoaderEngine!=null){
			mDataLoaderEngine.submit(task);
		}
	}
	
	protected ThreadPoolTools() {
	}
}
