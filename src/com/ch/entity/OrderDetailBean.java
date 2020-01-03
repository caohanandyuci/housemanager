package com.ch.entity;

public class OrderDetailBean {

	public static final String KEY_STRING_GOODID = "goodid";
	
	public static final String KEY_STRING_ORDERID = "orderid";
	
	public static final String KEY_INT_NUMBER= "number";
	
	public static final String KEY_LONG_RECORDTIME = "recordtime";
	
	private String mGoodID = "";
	
	private String mOrderID = "";
	
	private int mNumber = 0;
	
	private long mRecordTime = 0L;

	public String getGoodID() {
		return mGoodID;
	}

	public void setGoodID(String goodID) {
		mGoodID = goodID;
	}

	public String getOrderID() {
		return mOrderID;
	}

	public void setOrderID(String orderID) {
		mOrderID = orderID;
	}

	public int getNumber() {
		return mNumber;
	}

	public void setNumber(int number) {
		mNumber = number;
	}

	public long getRecordTime() {
		return mRecordTime;
	}

	public void setRecordTime(long recordTime) {
		mRecordTime = recordTime;
	}
	
	
}
