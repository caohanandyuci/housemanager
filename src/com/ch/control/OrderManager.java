package com.ch.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ch.commutils.Utils;
import com.ch.commutils.FloatCalculator;
import com.ch.entity.GoodsBean;
import com.ch.entity.OrderBean;
import com.ch.entity.OrderItem;
import com.ch.sqlitehelper.LogTools;

import android.content.Context;

public class OrderManager {
	
	private final String TAG = "OrderManager";
	
	private Map<String,OrderBean> mOrderMapper  =new HashMap<String,OrderBean>();

	public OrderBean CreateOrder(){
		OrderBean orderbean = new OrderBean();
		String orderid = Utils.createUUID();
		orderbean.setOrderId(orderid);
		orderbean.setOrderState(OrderBean.ORDER_STATE_UNPAID);
		mOrderMapper.put(orderid, orderbean);
		return orderbean;
	}
	
	
	public void InsertGoodsOfOrder(String orderid,GoodsBean goodsbean){
		OrderBean orderbean = mOrderMapper.get(orderid);
		if(orderbean!=null){
			orderbean.InsertGoodsBeanOfOrder(goodsbean);
		}
	}
	
	public void DeleteGoodsOfOrder(String orderid,GoodsBean goodsbean){
		OrderBean orderbean = mOrderMapper.get(orderid);
		if(orderbean!=null){
			orderbean.DeleteGoodsBeanOfOrder(goodsbean);
		}
	}
	
	public void DeleteOrder(String orderid){
		if (mOrderMapper.containsKey(orderid)) {
			mOrderMapper.remove(orderid);
		}
	}
	
	public float CalculateOrderSumPrice(String orderid) {
		float sumprice = 0.0f;
		if (orderid != null) {
			List<OrderItem> orderitems = mOrderMapper.get(orderid).getOrderItems();
			if (orderitems != null) {
				for (OrderItem orderitem : orderitems) {
					float orderitemprice = orderitem.getGoodsSumPrice();
					LogTools.logger(TAG, "orderitemprice:"+orderitemprice);
					sumprice = FloatCalculator.add(sumprice, orderitemprice);
					LogTools.logger(TAG, "sumprice:"+sumprice);
				}
			}
		}
		return sumprice;
	}
	
	public boolean setOrderState(String orderid,int state){
		if (mOrderMapper.containsKey(orderid)) {
			OrderBean order = mOrderMapper.get(orderid);
			order.setOrderState(state);
			return true;
		}
		return false;
	}
	
	
	public void saveOrderToDb(Context context,String orderid){
		
	}
	
}
