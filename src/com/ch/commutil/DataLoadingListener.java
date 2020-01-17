package com.ch.commutils;


public interface DataLoadingListener {
	
	void onLoadingStarted();

	void onLoadingFailed(int failReason);
	
	void onLoadingComplete();

	void onLoadingCancelled();
}
