package com.ch.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ch.commutils.LogTools;
import com.ch.entity.OrderItem;
import com.example.housemananger.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class OrderListView extends ListView implements android.widget.AdapterView.OnItemClickListener{

	private final String TAG = "OrderListView";
	
	public interface OnItemSelectedListener{
		void onItemSelected(AdapterView<?> parent, View view, int position,long id);
	}
	
	private OrderAdapter mOrderAdapter = null;
	private List<OrderItem> mOrderItems = Collections.synchronizedList(new ArrayList<OrderItem>());

	private OnItemSelectedListener mOnItemSelectedListener = null;
	
	public void addOnItemSelectedListener(OnItemSelectedListener listener){
		mOnItemSelectedListener = listener;
	}
	
	public OrderListView(Context context) {
		super(context);
		init(context);
	}

	public OrderListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void updateData(List<OrderItem> lists){
		LogTools.logger(TAG, "lists.size:"+lists.size()+",,:"+mOrderItems.size());
		if(lists.size()!=mOrderItems.size()){
			mOrderItems.clear();
			mOrderItems.addAll(lists);
			mOrderAdapter.setSelectedItem(mOrderAdapter.getCount() - 1);
			mOrderAdapter.notifyDataSetChanged();
			setSelection(mOrderAdapter.getCount() - 1);
		}else{
			int position = 0;
			for (OrderItem orderitem : mOrderItems) {
				if (orderitem.IsChanged) {
					mOrderAdapter.setSelectedItem(position);
					setSelection(position);
					mOrderAdapter.notifyDataSetInvalidated();
					orderitem.IsChanged =false;
					break;
				}
				position++;
			}
			
		}
	}
	
	private void init(Context context){
		setOnItemClickListener(this);
		mOrderAdapter = new OrderAdapter(context, R.layout.orderitemview, mOrderItems);
		this.setAdapter(mOrderAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mOrderAdapter.setSelectedItem(position);
		mOrderAdapter.notifyDataSetInvalidated();
		if(mOnItemSelectedListener!=null){
			mOnItemSelectedListener.onItemSelected(parent, view, position, id);
		}
	}
	
}
