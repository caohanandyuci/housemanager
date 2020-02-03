package com.ch.view;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.example.housemananger.R;

public class CategoryInfoAdapter extends ArrayAdapter<CategoryBean>{
private int mResourceId = 0;
	private Context mContext = null;
	public CategoryInfoAdapter(Context context, int resource, List<CategoryBean> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mResourceId = resource;
		mContext = context;
	}
	private int mSelectedItem = -1;
	
	public void setSelectedItem(int position){
		mSelectedItem = position;
	}
	
	static class ViewHolder{
		TextView mCategoryNameTextView = null;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		CategoryBean categoryBean = getItem(position);//获取当前项的实例
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(mResourceId, parent, false);
			holder.mCategoryNameTextView = (TextView) convertView.findViewById(R.id.categoryitem);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.mCategoryNameTextView.setText(categoryBean.getName());
		if (position == mSelectedItem) {
			convertView.setBackgroundResource(R.color.categoryitem_background_selected_color);
			holder.mCategoryNameTextView.setTextColor(ContextCompat.getColor(mContext,
							R.color.categoryitem_text_selected_color));
		}else{
			convertView.setBackgroundResource(R.color.categoryitem_background_normal_color);
			holder.mCategoryNameTextView.setTextColor(ContextCompat.getColor(mContext,
							R.color.categoryitem_text_normal_color));
		}
        return convertView;
	}
}
