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
}
