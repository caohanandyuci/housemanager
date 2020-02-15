package com.ch.view;

import com.example.housemananger.R;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public class MenuPopupWindow extends PopupWindow implements View.OnClickListener{

	private LinearLayout mMenuPopupLinearLayout = null;
	
	private int[] mButton = new int[]{R.id.goodsettingbutton};
	private Context mContext = null;
	public MenuPopupWindow(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mMenuPopupLinearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.menu_popup_view, null);
		setContentView(mMenuPopupLinearLayout);
		setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(100);
        setClippingEnabled(false);
        for(int i=0;i<mButton.length;i++){
            mMenuPopupLinearLayout.findViewById(mButton[i]).setOnClickListener(this);
        }
	}

	private void onButton_GoodsSetting_onClick(View view){
		ComponentName cn = new ComponentName(mContext.getPackageName(),mContext.getPackageName()+".GoodsSettingActivity") ;
		Intent intent = new Intent() ;
		intent.setComponent(cn) ;
		mContext.startActivity(intent) ;
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.goodsettingbutton:
			onButton_GoodsSetting_onClick(view);
			break;

		default:
			break;
		}
	}

	
	
}
