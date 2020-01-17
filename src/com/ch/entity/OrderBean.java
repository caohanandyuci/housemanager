package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

import com.ch.commutil.LogTools;
import com.ch.commutils.JsonUtils;
import com.google.gson.reflect.TypeToken;

import android.text.TextUtils;

public class OrderBean {
	
	private final String TAG = "OrderBean";
	
	public static final int ORDER_STATE_UNPAID = 0;
	
	public static final int ORDER_STATE_PAID = 1;
	
	public static final String KEY_STRING_UUID = "uuid";
	
	public static final String KEY_STRING_DETAIL="orderitemstr";
	
	public static final String KEY_FLOAT_DISCOUNT = "discount";
	
	public static final String KEY_FLOAT_SUMPRICE = "sumprice";
	
	public static final String KEY_FLOAT_REALITYSUMPRICE = "realitysumprice";
	
	public static final String KEY_INT_ORDERSTATE = "orderstate";
	
	public static final String KEY_INT_MEMBERID = "memberid";
	
	public static final String KEY_LONG_RECORDTIME = "recordtime";
	
	private String mOrderId = "";
	
	private String mDetail = "";
	
	private List<OrderItem> mOrderItems = new ArrayList<OrderItem>();
	
	private float mDiscount = 0.0f;
	
	private float mSumPrice = 0.0f;
	
	private float mRealitySumPrice = 0.0f;
	
	private int mOrderState = 0;
	
	private int mMemberID = 0;
	
	private long mRecordTime = 0L;

	public OrderItem getOrderItemByGoods(GoodsBean goodsbean){
		if(goodsbean!=null){
			String goodsid = goodsbean.getGoodsID();
			for(OrderItem orderitem:mOrderItems){
				if(orderitem.getGoodsID()!=null && orderitem.getGoodsID().equals(goodsid)){
					return orderitem;
				}
			}
		}
		return null;
	}
	
	public void InsertGoodsBeanOfOrder(GoodsBean goodsbean){
		if(goodsbean!=null){
			boolean isfound = false;
			for(OrderItem orderitem:mOrderItems){
				if(orderitem.getGoodsBean()!=null && goodsbean.isEquals(orderitem.getGoodsBean())){
					int num = orderitem.getNumber();
					orderitem.setNumber(num+1);
					isfound = true;
					break;
				}
			}
			if(isfound==false){
				OrderItem orderitem = new OrderItem(this.mOrderId,goodsbean);
				orderitem.setNumber(1);
				mOrderItems.add(orderitem);
			}
			LogTools.logger(TAG, "mOrderItems.size:"+mOrderItems.size());
		}
	}
	
	public void DeleteGoodsBeanOfOrder(GoodsBean goodsbean){
		if(goodsbean!=null){
			OrderItem needremoveorderitem = null;
			for(OrderItem orderitem:mOrderItems){
				if(orderitem.getGoodsBean()!=null && goodsbean.isEquals(orderitem.getGoodsBean())){
					int num = orderitem.getNumber();
					if(num>1){
						orderitem.setNumber(num-1);
					}else{
						needremoveorderitem = orderitem;
					}
					break;
				}
			}
			if(needremoveorderitem!=null){
				mOrderItems.remove(needremoveorderitem);
			}
			LogTools.logger(TAG, "mOrderItems.size:"+mOrderItems.size());
		}
	}
	
	public List<OrderItem> getOrderItems(){
		return mOrderItems;
	}
	
	public String getOrderId() {
		return mOrderId;
	}

	public void setOrderId(String uUID) {
		mOrderId = uUID;
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
	
	public String OrderDetailToString(){
		if(mOrderItems!=null && mOrderItems.size()>0){
			return JsonUtils.ObjectToJson(mOrderItems);
		}
		return null;
	}
	
	public List<OrderItem> DetailStringToObject(){
		if(!TextUtils.isEmpty(mDetail)){
			JsonUtils.getResultFromJsonString(mDetail, new TypeToken<List<OrderItem>>(){}.getType());
		}
		return null;
	}
	
}
