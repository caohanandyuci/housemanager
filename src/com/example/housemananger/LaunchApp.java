package com.example.housemananger;

import com.ch.commutils.ThreadPoolTools;

import android.app.Application;
import android.content.Context;


public class LaunchApp extends Application {

	private static Context mContext;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = getApplicationContext();
		ThreadPoolTools.getInstance().init();
	}

	public static Context getInstance() {
        return mContext;
    }
}
