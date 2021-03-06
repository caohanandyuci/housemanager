package com.ch.view;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.GoodsBean;
import com.example.housemananger.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsInfoAdapter extends BaseAdapter{

	private List<GoodsBean> mGoodsBeansList = new ArrayList<GoodsBean>();
    private Context mContext = null;
    public GoodsInfoAdapter(Context context){
      	this.mContext = context;
    }
    
    public GoodsInfoAdapter( Context context,List<GoodsBean> list){
        this.mGoodsBeansList = list;
        this.mContext = context;
    }
    public void UpdateData(List<GoodsBean> list){
    	   mGoodsBeansList.clear();
    	   mGoodsBeansList.addAll(list);
    	   notifyDataSetChanged();
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mGoodsBeansList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mGoodsBeansList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        convertView = layoutInflater.inflate(R.layout.goodsitemview,null);
        TextView nametextView = (TextView) convertView.findViewById(R.id.goodsitem);
        nametextView.setText(mGoodsBeansList.get(position).getGoodsName());
        TextView priceTextView = (TextView) convertView.findViewById(R.id.goodsprice);
        priceTextView.setText(String.valueOf(mGoodsBeansList.get(position).getPrice()));
        return convertView;
	}

}
