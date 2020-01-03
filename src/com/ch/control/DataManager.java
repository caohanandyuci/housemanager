package com.ch.control;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.sqlitehelper.RecordsDatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

public class DataManager {

	private SparseArray<List<GoodsBean>> mSparseArray = new SparseArray<List<GoodsBean>>();
	
	private SparseArray<CategoryBean> mCategoryMap = new SparseArray<CategoryBean>();
	
	private void loaddata(Context context){
		RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
		
		List<CategoryBean> categoryBeans = databasehelper.queryCategoryBeans();
		int categorycount = categoryBeans.size();
		for(CategoryBean categoryBean:categoryBeans){
			if(categoryBean!=null){
				int type = categoryBean.getCategoryID();
				mCategoryMap.put(type, categoryBean);
			}
		}
		List<GoodsBean> goodsBeans = databasehelper.querGoodsBeans();
		for(GoodsBean goodsbean:goodsBeans){
			if(goodsbean!=null){
				int goodscategory = goodsbean.getGoodsCategroy();
				mSparseArray.put(goodscategory, goodsBeans);
			}
		}
		for(int i=0;i<mCategoryMap.size();i++){
			
		}
	}
	
	public List<GoodsBean> getGoodsBeanByCategory(){
		List<GoodsBean> goodsBeans = new ArrayList<GoodsBean>();
		return goodsBeans;
	}
}
