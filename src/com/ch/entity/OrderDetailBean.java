package com.ch.entity;

public class OrderItem {

	public static final String KEY_STRING_GOODID = "goodsid";
	
	public static final String KEY_STRING_ORDERID = "orderid";
	
	public static final String KEY_INT_NUMBER= "number";
	
	public static final String KEY_LONG_RECORDTIME = "recordtime";
	
	private String mGoodsID = "";
	
	private String mOrderID = "";
	
	private int mNumber = 0;
	
	private transient long mRecordTime = 0L;

	private transient GoodsBean mGoodsBean = null;
	
	public OrderItem(){
		
	}
	
	public OrderItem(String orderid,String goodsid){
		mOrderID = orderid;
		mGoodsID = goodsid;
	}
	
	public OrderItem(String orderid,GoodsBean goodsbean){
		mOrderID = orderid;
		mGoodsBean = goodsbean;
		mGoodsID = goodsbean.getGoodsID();
	}
	
	public float getGoodsSumPrice(){
		return mGoodsBean.getPrice()*mNumber;
	}
	
	public String getGoodsName(){
		if(mGoodsBean!=null){
			return mGoodsBean.getGoodsName();
		}
		return null;
	}
	
	public GoodsBean getGoodsBean(){
		return mGoodsBean;
	}
	
	public void setGoodsBean(GoodsBean goodsbean){
		mGoodsBean = goodsbean;
	}
	
	public String getGoodsID() {
		return mGoodsID;
	}

	public void setGoodsID(String goodsID) {
		mGoodsID = goodsID;
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
