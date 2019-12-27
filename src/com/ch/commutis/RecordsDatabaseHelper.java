package com.ch.commutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordsDatabaseHelper extends SQLiteOpenHelper{

	private final static String TAG = "RecordsDatabaseHelper";
	
	private static final String DATABASE_NAME = "RecordsLocalData.db";
	private static final int DATABASE_VERSION = 1;
	public static final String GOODS_TABLE_NAME = "GoodTableName";
	
	private static final String CREAT_TABLE_COLUMN = "(_id integer primary key,"
			+ "goodname VARCHAR(255),"
			+ "goodprice VARCHAR(255),"
			+ "recordtime integer)";
	
	public RecordsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		LogTool.logger(TAG, "currentTime=" + System.currentTimeMillis());
		db.execSQL("CREATE TABLE " + GOODS_TABLE_NAME + CREAT_TABLE_COLUMN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
