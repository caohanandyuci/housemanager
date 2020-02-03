package com.ch.control;

import java.util.ArrayList;
import java.util.List;

import com.ch.commutils.DataLoadingListener;
import com.ch.commutils.LogTools;
import com.ch.commutils.ThreadPoolTools;
import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.sqlitehelper.RecordsDatabaseHelper;

import android.content.Context;
import android.util.SparseArray;

public class DataManager {

	private final String TAG = "DataManager";
	
	private SparseArray<List<GoodsBean>> mSparseArray = new SparseArray<List<GoodsBean>>();
	
	private SparseArray<CategoryBean> mCategoryMap = new SparseArray<CategoryBean>();
	
	private List<DataLoadingListener> mListeners = new ArrayList<DataLoadingListener>();
	
	private Context mContext = null;
	
	public void addDataChangedListener(DataLoadingListener listener) {
		if (!mListeners.contains(listener)) {
			mListeners.add(listener);
		}
	}
	
	public void removeDataChangedListener(DataLoadingListener listener){
		mListeners.remove(listener);
	}
	
	public DataManager(Context context){
		mContext = context;
	}
	
	private Runnable LoadDataFromDBRunnable  = new Runnable(){

		@Override
		public void run() {
			LogTools.logger(TAG, "loaddatafromdb");
			loaddatafromdb(mContext);
			for(DataLoadingListener listener:mListeners){
				listener.onLoadingComplete();
			}
		}
		
	};
	
	public void LoadDataFromDBOnThread(){
		ThreadPoolTools.getInstance().SubmitTask(LoadDataFromDBRunnable);
	}
	
	private void loaddatafromdb(Context context){
		RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
		
		List<CategoryBean> categoryBeans = databasehelper.queryCategoryBeans();
		LogTools.logger(TAG, "categoryBeans:" + categoryBeans);
		if (categoryBeans != null) {
			int categorycount = categoryBeans.size();
			LogTools.logger(TAG, "categorycount:" + categorycount);
		}
		for(CategoryBean categoryBean:categoryBeans){
			if(categoryBean!=null){
				int type = categoryBean.getCategoryID();
				mCategoryMap.put(type, categoryBean);
				LogTools.logger(TAG, "categoryid:"+categoryBean.getCategoryID()+",,name:"+categoryBean.getName());
			}
		}
		List<GoodsBean> goodsBeans = databasehelper.querGoodsBeans();
		LogTools.logger(TAG, "goodsBeans:"+goodsBeans);
		if(goodsBeans!=null){
			int goodscount = goodsBeans.size();
			LogTools.logger(TAG, "goodscount:" + goodscount);
		}
		for(GoodsBean goodsbean:goodsBeans){
			if(goodsbean!=null){
				int goodscategory = goodsbean.getGoodsCategroy();
				List<GoodsBean> tempGoodsBeans = mSparseArray.get(goodscategory);
				if(tempGoodsBeans == null){
					tempGoodsBeans = new ArrayList<GoodsBean>();
					tempGoodsBeans.add(goodsbean);
					mSparseArray.put(goodscategory, tempGoodsBeans);
				}else{
					tempGoodsBeans.add(goodsbean);
				}
				LogTools.logger(TAG, "categoryid:"+goodsbean.getGoodsCategroy()+",,name:"+goodsbean.getGoodsName());
			}
		}
	}
	
	public List<CategoryBean> getCategoryBean(){
		List<CategoryBean> categorylists  = new ArrayList<CategoryBean>();
		for(int i=0;i<mCategoryMap.size();i++){
			categorylists.add(mCategoryMap.valueAt(i));
		}
		return categorylists;
	}
	
	public List<GoodsBean> getGoodsBeanByCategory(int categoryid){
		List<GoodsBean> goodsBeans = null;
		goodsBeans = mSparseArray.get(categoryid);
		return goodsBeans;
	}
	
	public boolean insertCategoryToDatabase(Context context,CategoryBean categorybean){
		if(categorybean!=null){
			CategoryBean category = mCategoryMap.get(categorybean.getCategoryID());
			LogTools.logger(TAG, "category is null:"+category);
			if(category == null){
				mCategoryMap.put(categorybean.getCategoryID(), categorybean);
				RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
				databasehelper.insertCategoryBean(categorybean);
			}
		}
		return true;
	}
	
	public boolean insertGoodsBeanToDatabase(Context context,int category,GoodsBean goodsbean){
		List<GoodsBean> goodsbeans  = mSparseArray.get(category);
		LogTools.logger(TAG, "goodsbeans:"+goodsbeans);
		if(goodsbeans!=null){
			boolean isfound = false;
			for(GoodsBean goods:goodsbeans){
				if(goods.isEquals(goodsbean)){
					isfound = true;
					break;
				}
			}
			if(isfound == false){
				goodsbeans.add(goodsbean);
				RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
				databasehelper.insertGoodsBean(goodsbean);
			}
		}else{
			List<GoodsBean> goodsbeanstwo = new ArrayList<GoodsBean>();
			mSparseArray.put(category, goodsbeanstwo);
			RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
			databasehelper.insertGoodsBean(goodsbean);
		}
		return true;
	}
	
	
	public static void insertTestData(Context context){
		RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
		for(int i=1;i<53;i++){
			databasehelper.insertCategoryBean(new CategoryBean(i,"category"+i));
		}
		
		databasehelper.insertGoodsBean(new GoodsBean("goods1_1",1,101.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods1_2",1,102.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods1_3",1,103.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods1_4",1,104.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods1_5",1,105.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods1_6",1,106.0f));
		
		databasehelper.insertGoodsBean(new GoodsBean("goods2_1",2,101.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2_2",2,102.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2_3",2,103.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2_4",2,104.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2_5",2,105.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods2_6",2,106.0f));
		
		databasehelper.insertGoodsBean(new GoodsBean("goods3_1",3,101.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_2",3,102.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_3",3,103.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_4",3,104.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_5",3,105.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_6",3,106.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_7",3,107.0f));
		databasehelper.insertGoodsBean(new GoodsBean("goods3_8",3,108.0f));
	}
	
	public void inserttestcategory(Context context){
		insertCategoryToDatabase(context,new CategoryBean(1,"category1"));
		insertCategoryToDatabase(context,new CategoryBean(3,"category1"));
		insertCategoryToDatabase(context,new CategoryBean(5,"category5"));
		insertCategoryToDatabase(context,new CategoryBean(5,"category5"));
		insertCategoryToDatabase(context,new CategoryBean(1,"category1"));
		insertCategoryToDatabase(context,new CategoryBean(7,"category7"));
	}
	
	public void inserttestgoodsbean(Context context){
		insertGoodsBeanToDatabase(context,3,new GoodsBean("goods3_1",3,123.0f));
		insertGoodsBeanToDatabase(context,3,new GoodsBean("goods9_1",9,123.0f));
		insertGoodsBeanToDatabase(context,3,new GoodsBean("goods3_1",3,123.0f));
	}
	
}
