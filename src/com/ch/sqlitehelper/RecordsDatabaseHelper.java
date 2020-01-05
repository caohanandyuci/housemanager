package com.ch.sqlitehelper;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.entity.OrderBean;
import com.ch.entity.OrderDetailBean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordsDatabaseHelper extends SQLiteOpenHelper{
	
	private final static String TAG = "RecordsDatabaseHelper";
	
	private static final String DATABASE_NAME = "RecordsLocalData.db";
	private static final int DATABASE_VERSION = 1;
	public static final String GOODS_TABLE_NAME = "goodstablename";
	
	private static final String CREAT_GOODSTABLE_COLUMN = "(id integer primary key,"
			+ GoodsBean.KEY_STRING_UUID +" VARCHAR(255),"
			+ GoodsBean.KEY_STRING_GOODSNAME +" VARCHAR(255),"
			+ GoodsBean.KEY_FLOAT_DISCOUNT +" FLOAT,"
			+ GoodsBean.KEY_FLOAT_PRICE + " FLOAT,"
			+ GoodsBean.KEY_INT_GOODSCATEGROY+" INTEGER,"
	        + GoodsBean.KEY_INT_MEMBERID+" INTEGER,"
	        + GoodsBean.KEY_INT_NUMBER+" INTEGER,"
	        + GoodsBean.KEY_LONG_RECORDTIME+" DOUBLE)";
	
	private static final String GOODSTABLE_COLUMN[] = {
			GoodsBean.KEY_STRING_UUID,
			GoodsBean.KEY_STRING_GOODSNAME,
			GoodsBean.KEY_FLOAT_DISCOUNT,
			GoodsBean.KEY_FLOAT_PRICE ,
			GoodsBean.KEY_INT_GOODSCATEGROY,
			GoodsBean.KEY_INT_MEMBERID,
			GoodsBean.KEY_INT_NUMBER,
			GoodsBean.KEY_LONG_RECORDTIME	
	};
	
	public static final String CATEGORY_TABLE_NAME = "categorytablename";
	
	
	private static final String CREAT_CATEGORYTABLE_COLUMN = "(id integer primary key,"
			+ CategoryBean.KEY_STRING_UUID +" VARCHAR(255),"
			+ CategoryBean.KEY_INT_CATEGORYID +" INTEGER,"
			+ CategoryBean.KEY_STRING_NAME +" VARCHAR(255))";
	
	private static final String CATEGORYTABLE_COLUMN[] = {
		    CategoryBean.KEY_STRING_UUID,
			CategoryBean.KEY_INT_CATEGORYID,
			CategoryBean.KEY_STRING_NAME	
	};
	
	
	public static final String ORDER_DETAIL_TABLE_NAME = "orderdetailtablename";
	
	
	private static final String CREAT_ORDERDETAILTABLE_COLUMN = "(id integer primary key,"
			+ OrderDetailBean.KEY_INT_NUMBER +" INTEGER,"
			+ OrderDetailBean.KEY_LONG_RECORDTIME +" FLOAT,"
			+ OrderDetailBean.KEY_STRING_GOODID +" INTEGER,"
			+ OrderDetailBean.KEY_STRING_ORDERID +" VARCHAR(255))";
	
	private static final String ORDERDETAILTABLE_COLUMN[] = {
			OrderDetailBean.KEY_INT_NUMBER,
			OrderDetailBean.KEY_LONG_RECORDTIME,
			OrderDetailBean.KEY_STRING_GOODID,
			OrderDetailBean.KEY_STRING_ORDERID
	};
	
	
	public static final String ORDER_TABLE_NAME = "ordertablename";
	
	
	private static final String CREAT_ORDERTABLE_COLUMN = "(id integer primary key,"
			+ OrderBean.KEY_FLOAT_DISCOUNT +" FLOAT,"
			+ OrderBean.KEY_FLOAT_REALITYSUMPRICE +" FLOAT,"
			+ OrderBean.KEY_FLOAT_SUMPRICE +" FLOAT,"
			+ OrderBean.KEY_INT_MEMBERID +" INTEGER,"
			+ OrderBean.KEY_INT_ORDERSTATE +" INTEGER,"
			+ OrderBean.KEY_LONG_RECORDTIME +" FLOAT,"
			+ OrderBean.KEY_STRING_DETAIL +" VARCHAR(255),"
			+ OrderBean.KEY_STRING_UUID +" VARCHAR(255))";
	
	private static final String ORDERTABLE_COLUMN[] = {
			OrderBean.KEY_FLOAT_DISCOUNT,
			OrderBean.KEY_FLOAT_REALITYSUMPRICE,
			OrderBean.KEY_FLOAT_SUMPRICE,
			OrderBean.KEY_INT_MEMBERID,
			OrderBean.KEY_INT_ORDERSTATE,
			OrderBean.KEY_LONG_RECORDTIME,
			OrderBean.KEY_STRING_DETAIL,
			OrderBean.KEY_STRING_UUID
	};
	
	
	public RecordsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//LogTool.logger(TAG, "currentTime=" + System.currentTimeMillis());
		db.execSQL("CREATE TABLE " + GOODS_TABLE_NAME + CREAT_GOODSTABLE_COLUMN);
		db.execSQL("CREATE TABLE " + CATEGORY_TABLE_NAME + CREAT_CATEGORYTABLE_COLUMN);
		db.execSQL("CREATE TABLE " + ORDER_DETAIL_TABLE_NAME + CREAT_ORDERDETAILTABLE_COLUMN);
		db.execSQL("CREATE TABLE " + ORDER_TABLE_NAME + CREAT_ORDERTABLE_COLUMN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertGoodsBean(GoodsBean goodsbean) {
		if (goodsbean != null) {
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(GoodsBean.KEY_FLOAT_DISCOUNT, goodsbean.getDiscount());
			values.put(GoodsBean.KEY_FLOAT_PRICE, goodsbean.getPrice());
			values.put(GoodsBean.KEY_INT_GOODSCATEGROY,goodsbean.getGoodsCategroy());
			values.put(GoodsBean.KEY_INT_MEMBERID, goodsbean.getMemberID());
			values.put(GoodsBean.KEY_INT_NUMBER, goodsbean.getNumber());
			values.put(GoodsBean.KEY_LONG_RECORDTIME, goodsbean.getRecordTime());
			values.put(GoodsBean.KEY_STRING_GOODSNAME, goodsbean.getGoodsName());
			values.put(GoodsBean.KEY_STRING_UUID, goodsbean.getUUID());
			database.insert(GOODS_TABLE_NAME, null, values);
		}
	}

	public void insertCategoryBean(CategoryBean categorybean){
		if(categorybean!=null){
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(CategoryBean.KEY_STRING_UUID, categorybean.getUUID());
			values.put(CategoryBean.KEY_STRING_NAME, categorybean.getName());
			values.put(CategoryBean.KEY_INT_CATEGORYID,categorybean.getCategoryID());
			database.insert(CATEGORY_TABLE_NAME, null, values);
		}
	}
	
	public void insertOrderDetailBean(OrderDetailBean orderdetailbean){
		if(orderdetailbean!=null){
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(OrderDetailBean.KEY_INT_NUMBER, orderdetailbean.getNumber());
			values.put(OrderDetailBean.KEY_LONG_RECORDTIME, orderdetailbean.getRecordTime());
			values.put(OrderDetailBean.KEY_STRING_GOODID, orderdetailbean.getGoodID());
			values.put(OrderDetailBean.KEY_STRING_ORDERID,orderdetailbean.getOrderID());
			database.insert(ORDER_DETAIL_TABLE_NAME, null, values);
		}
	}
	
	
	public void insertOrderBean(OrderBean orderbean){
		if(orderbean!=null){
			SQLiteDatabase database = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(OrderBean.KEY_FLOAT_DISCOUNT, orderbean.getDiscount());
			values.put(OrderBean.KEY_FLOAT_REALITYSUMPRICE, orderbean.getRealitySumPrice());
			values.put(OrderBean.KEY_FLOAT_SUMPRICE, orderbean.getSumPrice());
			values.put(OrderBean.KEY_INT_MEMBERID,orderbean.getMemberID());
			values.put(OrderBean.KEY_INT_ORDERSTATE, orderbean.getOrderState());
			values.put(OrderBean.KEY_LONG_RECORDTIME, orderbean.getRecordTime());
			values.put(OrderBean.KEY_STRING_DETAIL, orderbean.getDetail());
			values.put(OrderBean.KEY_STRING_UUID,orderbean.getUUID());
			database.insert(ORDER_TABLE_NAME, null, values);
		}
	}
	
	public List<OrderBean> queryOrderBeans(){
		SQLiteDatabase database = getWritableDatabase();
		List<OrderBean> lists =new ArrayList<OrderBean>();
		Cursor cursor = null;
		cursor = database.query(ORDER_TABLE_NAME, ORDERTABLE_COLUMN, null, null, null, null, null);
		try {
			while (cursor != null && cursor.moveToNext()) {
				OrderBean orderbean = new OrderBean();
				orderbean.setDiscount(cursor.getFloat(cursor.getColumnIndex(OrderBean.KEY_FLOAT_DISCOUNT)));
				orderbean.setRealitySumPrice(cursor.getFloat(cursor.getColumnIndex(OrderBean.KEY_FLOAT_REALITYSUMPRICE)));
				orderbean.setSumPrice(cursor.getFloat(cursor.getColumnIndex(OrderBean.KEY_FLOAT_SUMPRICE)));
				orderbean.setMemberID(cursor.getInt(cursor.getColumnIndex(OrderBean.KEY_INT_MEMBERID)));
				orderbean.setOrderState(cursor.getInt(cursor.getColumnIndex(OrderBean.KEY_INT_ORDERSTATE)));
				orderbean.setRecordTime(cursor.getLong(cursor.getColumnIndex(OrderBean.KEY_LONG_RECORDTIME)));
				orderbean.setDetail(cursor.getString(cursor.getColumnIndex(OrderBean.KEY_STRING_DETAIL)));
				orderbean.setUUID(cursor.getString(cursor.getColumnIndex(OrderBean.KEY_STRING_UUID)));
				lists.add(orderbean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lists;
	}
	
	public List<OrderDetailBean> queryOrderDetailBeans(){
		SQLiteDatabase database = getWritableDatabase();
		List<OrderDetailBean> lists =new ArrayList<OrderDetailBean>();
		Cursor cursor = null;
		cursor = database.query(ORDER_DETAIL_TABLE_NAME, ORDERDETAILTABLE_COLUMN, null, null, null, null, null);
		try {
			while (cursor != null && cursor.moveToNext()) {
				OrderDetailBean orderdetailbean = new OrderDetailBean();
				orderdetailbean.setNumber(cursor.getInt(cursor.getColumnIndex(OrderDetailBean.KEY_INT_NUMBER)));
				orderdetailbean.setRecordTime(cursor.getLong(cursor.getColumnIndex(OrderDetailBean.KEY_LONG_RECORDTIME)));
				orderdetailbean.setGoodID(cursor.getString(cursor.getColumnIndex(OrderDetailBean.KEY_STRING_GOODID)));
				orderdetailbean.setOrderID(cursor.getString(cursor.getColumnIndex(OrderDetailBean.KEY_STRING_ORDERID)));
				lists.add(orderdetailbean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lists;
	}
	
	public List<CategoryBean> queryCategoryBeans(){
		SQLiteDatabase database = getWritableDatabase();
		List<CategoryBean> lists =new ArrayList<CategoryBean>();
		Cursor cursor = null;
		cursor = database.query(CATEGORY_TABLE_NAME, CATEGORYTABLE_COLUMN, null, null, null, null, null);
		try {
			while (cursor != null && cursor.moveToNext()) {
				CategoryBean categorybean = new CategoryBean();
				categorybean.setCategoryID(cursor.getInt(cursor.getColumnIndex(CategoryBean.KEY_INT_CATEGORYID)));
				categorybean.setName(cursor.getString(cursor.getColumnIndex(CategoryBean.KEY_STRING_NAME)));
				categorybean.setUUID(cursor.getString(cursor.getColumnIndex(CategoryBean.KEY_STRING_UUID)));
				lists.add(categorybean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lists;
	}
	
	public List<GoodsBean> querGoodsBeans() {
		SQLiteDatabase database = getWritableDatabase();
		List<GoodsBean> lists = new ArrayList<GoodsBean>();

		Cursor cursor = null;
		cursor = database.query(GOODS_TABLE_NAME, GOODSTABLE_COLUMN, null, null, null, null, null);
		try {
			while (cursor != null && cursor.moveToNext()) {
				GoodsBean goodsbean = new GoodsBean();
				goodsbean.setDiscount(cursor.getFloat(cursor.getColumnIndex(GoodsBean.KEY_FLOAT_DISCOUNT)));
				goodsbean.setPrice(cursor.getFloat(cursor.getColumnIndex(GoodsBean.KEY_FLOAT_PRICE)));
				goodsbean.setGoodsCategroy(cursor.getInt(cursor.getColumnIndex(GoodsBean.KEY_INT_GOODSCATEGROY)));
				goodsbean.setMemberID(cursor.getInt(cursor.getColumnIndex(GoodsBean.KEY_INT_MEMBERID)));
				goodsbean.setNumber(cursor.getInt(cursor.getColumnIndex(GoodsBean.KEY_INT_NUMBER)));
				goodsbean.setmRecordTime(cursor.getLong(cursor.getColumnIndex(GoodsBean.KEY_LONG_RECORDTIME)));
				goodsbean.setGoodsName(cursor.getString(cursor.getColumnIndex(GoodsBean.KEY_STRING_GOODSNAME)));
				goodsbean.setUUID(cursor.getString(cursor.getColumnIndex(GoodsBean.KEY_STRING_UUID)));
				lists.add(goodsbean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lists;
	}
	
}
