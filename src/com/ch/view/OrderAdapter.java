package com.ch.view;

import java.util.List;

import com.ch.entity.GoodsBean;
import com.example.housemananger.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdapter extends ArrayAdapter<GoodsBean>{

	private int mResourceId = 0;
	
	public OrderAdapter(Context context, int resource, List<GoodsBean> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mResourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GoodsBean goodsbean = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(mResourceId, parent, false);
        ((TextView) view.findViewById(R.id.orderitem)).setText(goodsbean.getGoodsName());
        return view;
	}

}
