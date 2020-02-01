package com.ch.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.example.housemananger.R;

public class CategoryInfoAdapter extends ArrayAdapter<CategoryBean>{
private int mResourceId = 0;
	
	public CategoryInfoAdapter(Context context, int resource, List<CategoryBean> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mResourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CategoryBean categoryBean = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(mResourceId, parent, false);
        ((TextView) view.findViewById(R.id.categoryitem)).setText(categoryBean.getName());
        return view;
	}
}
