package com.ch.control;

import java.util.List;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.sqlitehelper.RecordsDatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

public class DataManager {

	private SparseArray<List<GoodsBean>> mSparseArray = new SparseArray<List<GoodsBean>>();
	
	private void loaddata(Context context){
		RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
		
		List<CategoryBean> categoryBeans = databasehelper.queryCategoryBeans();
		int categorycount = categoryBeans.size();
		for(CategoryBean categoryBean:categoryBeans){
			
		}
		
		List<GoodsBean> goodsBeans = databasehelper.querGoodsBeans();
		
	}
}
