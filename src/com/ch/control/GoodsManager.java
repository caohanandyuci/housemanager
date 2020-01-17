package com.ch.control;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.GoodsBean;

public class GoodsManager {
	private final static String TAG = "GoodsManager";
	
	private List<GoodsBean> mGoodsBeans = new ArrayList<GoodsBean>();
	
	public void LoadGoodsBean(List<GoodsBean> goodsbean){
		mGoodsBeans.addAll(goodsbean);
	}
	
	public void UpdateGoodsBean(List<GoodsBean> goodsbean){
		mGoodsBeans.clear();
		mGoodsBeans.addAll(goodsbean);
	}
	
	public void ClearGoodsBeans(){
		mGoodsBeans.clear();
	}
	
	public GoodsBean findGoodsBeanByID(String goodsid){
		for(GoodsBean goodsbean: mGoodsBeans){
			if(goodsbean!=null && goodsbean.getGoodsID()!=null){
				if(goodsbean.getGoodsID().equals(goodsid)){
					return goodsbean;
				}
			}
		}
		return null;
	}
	
	public GoodsBean findGoodsBeanByName(String name){
		for(GoodsBean goodsbean: mGoodsBeans){
			if(goodsbean!=null && goodsbean.getGoodsName()!=null){
				if(goodsbean.getGoodsName().equals(name)){
					return goodsbean;
				}
			}
		}
		return null;
	}
	
	public boolean hasGoodsName(GoodsBean goodsbean){
		for(GoodsBean goods:mGoodsBeans){
			if(goods!=null && goods.isEquals(goodsbean)){
				return true;
			}
		}
		return false;
	}
	
}
