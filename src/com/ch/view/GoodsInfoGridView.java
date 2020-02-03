package com.ch.view;

import java.util.ArrayList;
import java.util.List;

import com.ch.control.DataManager;
import com.ch.entity.GoodsBean;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class GoodsInfoGridView extends GridView implements CategoryItemChangedListener{

	private final String TAG = "GoodsInfoGridView";
	
	public GoodsInfoGridView(Context context) {
		super(context);
		init(context);
	}

	public GoodsInfoGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private GoodsInfoAdapter mGoodsInfoAdapter = null;
	private DataManager mDataManager = null;
	
	private void init(Context context){
		mGoodsInfoAdapter = new GoodsInfoAdapter(context);
		setAdapter(mGoodsInfoAdapter);
	}
	
	public void UpdateData(List<GoodsBean> goodsbeans){
		mGoodsInfoAdapter.UpdateData(goodsbeans);
	}
	
	public void attchDataManager(DataManager dataManager){
		mDataManager = dataManager;
	}

	@Override
	public void onCategoryItemChanged(int categoryid) {
		// TODO Auto-generated method stub
		List<GoodsBean> goodsBeans = mDataManager
				.getGoodsBeanByCategory(categoryid);
		if (goodsBeans != null) {
			mGoodsInfoAdapter.UpdateData(goodsBeans);
		}
	}

}
