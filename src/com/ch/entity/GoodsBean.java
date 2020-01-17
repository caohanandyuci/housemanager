package com.ch.entity;

import com.ch.commutils.Utils;

public class GoodsBean {
	
	public GoodsBean(){
		
	}
	
	public GoodsBean(String goodsname,int goodscategroy,float price){
		this.mGoodsID = Utils.createUUID();
		this.mGoodsName = goodsname;
		this.mGoodsCategroy = goodscategroy;
		this.mPrice = price;
	}
	
	public static final String KEY_STRING_UUID = "uuid";
	
	public static final String KEY_STRING_GOODSNAME = "goodsname";
	
	public static final String KEY_INT_GOODSCATEGROY = "goodscategroy";
	
	public static final String KEY_FLOAT_PRICE = "price";
	
	public static final String KEY_FLOAT_DISCOUNT = "discount";
	
	public static final String KEY_INT_NUMBER= "number";
	
	public static final String KEY_INT_MEMBERID = "memberid";
	
	public static final String KEY_LONG_RECORDTIME = "recordtime";
	
	private String mGoodsID = "";
	
	private String mGoodsName = "";
	
	public boolean isEquals(GoodsBean goodsbean) {
		if (goodsbean != null) {
			if (mGoodsName.equals(goodsbean.getGoodsName())) {
				return true;
			}
		}
		return false;

	}
	
	public String getGoodsID() {
		return mGoodsID;
	}

	public String getGoodsName() {
		return mGoodsName;
	}

	public int getGoodsCategroy() {
		return mGoodsCategroy;
	}

	public float getPrice() {
		return mPrice;
	}

	public float getDiscount() {
		return mDiscount;
	}

	public int getNumber() {
		return mNumber;
	}

	public int getMemberID() {
		return mMemberID;
	}

	public long getRecordTime() {
		return mRecordTime;
	}

	
	public void setGoodsID(String goodsid) {
		mGoodsID = goodsid;
	}
	
	public void setGoodsName(String goodsname) {
		mGoodsName = goodsname;
	}

	public void setGoodsCategroy(int goodsCategroy) {
		mGoodsCategroy = goodsCategroy;
	}

	public void setPrice(float price) {
		mPrice = price;
	}

	public void setDiscount(float discount) {
		mDiscount = discount;
	}

	public void setNumber(int number) {
		mNumber = number;
	}

	public void setMemberID(int memberID) {
		mMemberID = memberID;
	}

	public void setmRecordTime(long mRecordTime) {
		this.mRecordTime = mRecordTime;
	}
	private int mGoodsCategroy = -1;
	
	private float mPrice = 0.0f;
	
	private float mDiscount = 0.0f;
	
	private int mNumber = 0;
	
	private int mMemberID = 0;
	
	private long mRecordTime = 0L;
}
