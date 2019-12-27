package com.ch.commutils;

import android.util.Log;

public class LogTool {
	public static void logger(String TAG,String message){
		Log.v("ch"+TAG, message);
	}
}
