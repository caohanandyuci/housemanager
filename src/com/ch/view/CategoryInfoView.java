package com.ch.view;


import java.util.logging.Logger;

import com.ch.commutils.LogTools;
import com.example.housemananger.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;


public class CategoryInfoView extends LinearLayout {

	private final String TAG = "CategoryInfoView";
	
	private ListView mCategoryListView = null;
	
	public CategoryInfoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CategoryInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		mCategoryListView = (ListView) this.findViewById(R.id.categorylistview);
		LogTools.logger(TAG, "mCategoryListView:"+mCategoryListView);
	}
	
	

}
