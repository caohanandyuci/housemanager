package com.ch.sqlitehelper;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordsDatabaseHelper extends SQLiteOpenHelper{

	private final static String TAG = "RecordsDatabaseHelper";
	
	private static final String DATABASE_NAME = "RecordsLocalData.db";
	private static final int DATABASE_VERSION = 1;
	public static final String GOODS_TABLE_NAME = "GoodsTableName";
	
	private static final String CREAT_TABLE_COLUMN = "(id integer primary key,"
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
		//LogTool.logger(TAG, "currentTime=" + System.currentTimeMillis());
		db.execSQL("CREATE TABLE " + GOODS_TABLE_NAME + CREAT_TABLE_COLUMN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertGoodsBean(GoodsBean goodsbean){
		SQLiteDatabase database = getWritableDatabase();
	    ContentValues values = new ContentValues();
	    //values.put(key, value)
		database.insert(GOODS_TABLE_NAME, null, values);
	}

	
	public List<CategoryBean> queryCategoryBeans(){
		List<CategoryBean> lists =new ArrayList<CategoryBean>();
		return lists;
	}
	
	public List<GoodsBean> querGoodsBeans(){
		List<GoodsBean> lists = new ArrayList<GoodsBean>();
		return lists;
	}
	
}
