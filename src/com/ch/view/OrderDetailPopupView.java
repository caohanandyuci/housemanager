package com.ch.view;

import com.ch.entity.OrderItem;
import com.example.housemananger.R;

import android.R.integer;
import android.R.raw;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class OrderDetailPopupView{
	
	private PopupWindow mPopupWindow = null;
	private LinearLayout mPopupViewLinearLayout = null;
	private TextView mGoodsNameTextView = null;
	private TextView mGoodsNumTextView = null;
	private TextView mNumSubTextView = null;
	private TextView mNumAddTextView = null;
	private Button mDelButton = null;
	private Button mOKButton = null;
	private Button mCancelButton =null;
	public OrderDetailPopupView(Context context) {
		mPopupViewLinearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.orderitemdialog, null);
		mPopupViewLinearLayout.setBackgroundResource(R.drawable.layerlist);
		mPopupWindow = new PopupWindow(mPopupViewLinearLayout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		mPopupWindow.setOutsideTouchable(true);
		mGoodsNameTextView = (TextView) mPopupViewLinearLayout.findViewById(R.id.order_goods_name);
		mGoodsNumTextView= (TextView) mPopupViewLinearLayout.findViewById(R.id.order_goods_num);
		mNumSubTextView = (TextView) mPopupViewLinearLayout.findViewById(R.id.num_sub);
		mNumSubTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int num =Integer.parseInt(mGoodsNumTextView.getText().toString());
				if(num-1>0){
					num--;
					mGoodsNumTextView.setText(String.valueOf(num));
				}
			}
		});
		mNumAddTextView= (TextView) mPopupViewLinearLayout.findViewById(R.id.num_add);
		mNumAddTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int num =Integer.parseInt(mGoodsNumTextView.getText().toString());
				num++;
				mGoodsNumTextView.setText(String.valueOf(num));
			}
		});
		
		mOKButton = (Button) mPopupViewLinearLayout.findViewById(R.id.orderitem_ok);
		mCancelButton = (Button) mPopupViewLinearLayout.findViewById(R.id.orderitem_cancel);
		mCancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mPopupWindow != null) {
					if (mPopupWindow.isShowing()) {
						mPopupWindow.dismiss();
					}
				}
			}
		});
		mDelButton = (Button) mPopupViewLinearLayout.findViewById(R.id.orderitem_del);
	}
	
	public void dismissview(){
		if (mPopupWindow != null) {
			if (mPopupWindow.isShowing()) {
				mPopupWindow.dismiss();
			}
		}
	}

	public void setonOkListener(OnClickListener listener){
		mOKButton.setOnClickListener(listener);
	}
	
	public void setonDelListener(OnClickListener listener){
		mDelButton.setOnClickListener(listener);
	}
	
	public int getGoodsnum(){
		return Integer.parseInt(mGoodsNumTextView.getText().toString());
	}
	
	public void showPopupWindow(View anchorview, int anchorX, int anchorY,
			OrderItem orderitem) {
		if (mPopupWindow != null) {
			if (mPopupWindow.isShowing()) {
				mPopupWindow.dismiss();
			} else {
				mGoodsNameTextView.setText(orderitem.getGoodsName());
				mGoodsNumTextView.setText(String.valueOf(orderitem.getNumber()));
				mPopupWindow.showAsDropDown(anchorview, anchorX+5, anchorY);
			}
		}
	}
	
}
