package com.ch.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ch.commutils.LogTools;
import com.ch.entity.CategoryBean;
import com.example.housemananger.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView.HitTestResult;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class CategoryInfoView extends LinearLayout {

	private final String TAG = "CategoryInfoView";
	
	private ListView mCategoryListView = null;
	private TextView mPrePageView = null;
	private TextView mNextPageView = null;
	private CategoryInfoAdapter mCategoryInfoAdapter = null;
	private Context mContext = null;
	private List<CategoryBean> mCategoryBeans = new ArrayList<CategoryBean>();
	private CategoryItemChangedListener mListener = null;
	
	public CategoryInfoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public CategoryInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public void addCategoryItemChangedListener(CategoryItemChangedListener listener){
		mListener = listener;
	}
	
	private void init(Context context){
		mContext = context;
	}
	
	
	public void updateData(List<CategoryBean> lists){
		mCategoryBeans.clear();
		mCategoryBeans.addAll(lists);
		mCategoryInfoAdapter.setSelectedItem(0);
		mCategoryInfoAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		mCategoryListView = (ListView) this.findViewById(R.id.categorylistview);
		mCategoryInfoAdapter = new CategoryInfoAdapter(mContext, R.layout.categroyitemview, mCategoryBeans);
		mCategoryListView.setAdapter(mCategoryInfoAdapter);
		mCategoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				mCategoryInfoAdapter.setSelectedItem(position);
				mCategoryInfoAdapter.notifyDataSetInvalidated();
				CategoryBean categoryBean = mCategoryInfoAdapter.getItem(position);
				if(mListener!=null){
					mListener.onCategoryItemChanged(categoryBean.getCategoryID());
				}
			}
		});
		mCategoryListView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
	            case MotionEvent.ACTION_MOVE:
	                return true;
	            default:
	                break;
	            }
	            return false;
			}
		});
		
		LogTools.logger(TAG, "mCategoryListView:"+mCategoryListView);
		mPrePageView = (TextView) findViewById(R.id.prepageview);
		mPrePageView.setOnClickListener(mPrePageOnClickListener);
		mNextPageView = (TextView) findViewById(R.id.nextpageview);
		mNextPageView.setOnClickListener(mNextPageOnClickListener);
	}
	
	
	private View.OnClickListener mPrePageOnClickListener  = new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			LogTools.logger(TAG, "prepageonclicklistener");
			int position = getPagePositon(PRE_PAGE);
			if(position>=0){
				mCategoryListView.setSelection(position);
				mCategoryInfoAdapter.setSelectedItem(position);
				mCategoryInfoAdapter.notifyDataSetInvalidated();
			}
			
		}
	};
	
	private View.OnClickListener mNextPageOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			LogTools.logger(TAG, "nextpageonclicklistener");
			int position = getPagePositon(NEXT_PAGE);
			LogTools.logger(TAG, "nextpageonclicklistener position:"+position);
			if(position>0){
			   mCategoryListView.setSelection(position);
			   mCategoryInfoAdapter.setSelectedItem(position);
			   mCategoryInfoAdapter.notifyDataSetInvalidated();
			}
		}
	};
	
	private final int PRE_PAGE = 0;
	private final int NEXT_PAGE = 1;
	
	private int getPagePositon(int direction){
		LogTools.logger(TAG, "getPagePositon");
		int sumcount = mCategoryListView.getAdapter().getCount();
		int visiablecount = mCategoryListView.getChildCount();
		View firstView = mCategoryListView.getChildAt(0);
		int firstposition = mCategoryListView.getPositionForView(firstView);
		int lastposition = firstposition+visiablecount-1;
		LogTools.logger(TAG, "getPagePositon sumcount:"+sumcount+"visiablecount:"+visiablecount+"firstposition:"+firstposition+"lastposition:"+lastposition);
		int position = -2;
		if(direction == NEXT_PAGE){
			if(lastposition<sumcount){
				int diff_last_sum = sumcount-lastposition;
				position = firstposition+Math.min(diff_last_sum, visiablecount);
			}
		}else if(direction == PRE_PAGE){
			if(firstposition>0){
				int diff_first_zero = firstposition-visiablecount;
				position=diff_first_zero>0?diff_first_zero:0;
			}
		}else{
			position = -1;
		}
		LogTools.logger(TAG, "getPagePositon position:"+position);
		return position;
	}

}
