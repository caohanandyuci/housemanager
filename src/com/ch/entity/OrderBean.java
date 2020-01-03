package com.ch.entity;

public class OrderBean {
	
	public static final String KEY_STRING_UUID = "uuid";
	
	public static final String KEY_STRING_DETAIL="detail";
	
	public static final String KEY_FLOAT_DISCOUNT = "discount";
	
	public static final String KEY_FLOAT_SUMPRICE = "sumprice";
	
	public static final String KEY_FLOAT_REALITYSUMPRICE = "realitysumprice";
	
	public static final String KEY_INT_ORDERSTATE = "orderstate";
	
	public static final String KEY_INT_MEMBERID = "memberid";
	
	public static final String KEY_LONG_RECORDTIME = "recordtime";
	
	private String UUID = "";
	
	private String mDetail = "";
	
	private float mDiscount = 0.0f;
	
	private float mSumPrice = 0.0f;
	
	private float mRealitySumPrice = 0.0f;
	
	private int mOrderState = 0;
	
	private int mMemberID = 0;
	
	private long mRecordTime = 0L;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getDetail() {
		return mDetail;
	}

	public void setDetail(String detail) {
		mDetail = detail;
	}

	public float getDiscount() {
		return mDiscount;
	}

	public void setDiscount(float discount) {
		mDiscount = discount;
	}

	public float getSumPrice() {
		return mSumPrice;
	}

	public void setSumPrice(float sumPrice) {
		mSumPrice = sumPrice;
	}

	public float getRealitySumPrice() {
		return mRealitySumPrice;
	}

	public void setRealitySumPrice(float realitySumPrice) {
		mRealitySumPrice = realitySumPrice;
	}

	public int getOrderState() {
		return mOrderState;
	}

	public void setOrderState(int orderState) {
		mOrderState = orderState;
	}

	public int getMemberID() {
		return mMemberID;
	}

	public void setMemberID(int memberID) {
		mMemberID = memberID;
	}

	public long getRecordTime() {
		return mRecordTime;
	}

	public void setRecordTime(long recordTime) {
		mRecordTime = recordTime;
	}
	
	
	
}
