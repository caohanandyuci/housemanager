package com.ch.view;

import java.util.List;

import com.ch.entity.OrderItem;
import com.example.housemananger.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdapter extends ArrayAdapter<OrderItem>{

	private int mResourceId = 0;
	
	public OrderAdapter(Context context, int resource, List<OrderItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mResourceId = resource;
	}
	
	private int mSelectedItem = -1;
	public void setSelectedItem(int position){
		mSelectedItem = position;
	}
	static class ViewHolder{
		TextView mOrderItemTextView = null;
		TextView mGoodsNumTextView = null;
		TextView mPriceTextView = null;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.v("dddddddd", "positon:"+position+",,,"+convertView+",,"+parent.getChildCount());
		OrderItem orderitem = getItem(position);//获取当前项的实例
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(mResourceId, parent, false);
			holder.mOrderItemTextView = (TextView) convertView.findViewById(R.id.orderitem);
	        holder.mGoodsNumTextView = (TextView) convertView.findViewById(R.id.goods_num_of_order);
	        holder.mPriceTextView = (TextView) convertView.findViewById(R.id.pricetextview);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.mOrderItemTextView.setText(orderitem.getGoodsName());
		holder.mGoodsNumTextView.setText("x"+String.valueOf(orderitem.getNumber()));
		holder.mPriceTextView.setText("￥"+String.valueOf(orderitem.getGoodsSumPrice()));
        if(position == mSelectedItem ){
          	convertView.setBackgroundResource(R.color.orderitem_backgroud_selected_color);
        }else{
          	convertView.setBackgroundResource(R.color.orderitem_backgroud_normal_color);
        }
        return convertView;
	}

}
