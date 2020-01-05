package com.ch.control;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.sqlitehelper.RecordsDatabaseHelper;

import android.content.Context;
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
	
	public static void insertTestData(Context context){
		RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
		databasehelper.insertCategoryBean(new CategoryBean(1,"category1"));
		databasehelper.insertCategoryBean(new CategoryBean(2,"category2"));
		databasehelper.insertCategoryBean(new CategoryBean(3,"category3"));
		databasehelper.insertCategoryBean(new CategoryBean(4,"category4"));
		
		databasehelper.insertGoodsBean(new GoodsBean("goods1",1,101.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2",1,102.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3",1,103.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods4",1,104.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods5",1,105.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods6",1,106.0f));
		
	}
	
}
