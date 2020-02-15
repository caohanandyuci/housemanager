package com.ch.view;

import com.ch.commutils.LogTools;
import com.example.housemananger.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class PayDialog extends Dialog implements View.OnClickListener {

	private final String TAG = "PayDialog";
	
	public PayDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public PayDialog(Context context, int themeResId) {
		super(context, themeResId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.payview);
		this.setTitle("fdsafdaseaw");
		initnumlayout(getContext());
	}

	@SuppressLint("NewApi")
	private void initnumlayout(Context context) {
		GridLayout gridLayout = (GridLayout) findViewById(R.id.payview_dnum_gridlayout);
		String[] mStrings = { "7", "8", "9", 
				              "4", "5", "6", 
				              "1", "2", "3", 
				              ".", "0", "<-","结算"};
		// 6行 4列
		gridLayout.setColumnCount(4);
		gridLayout.setRowCount(4);
		for (int i = 0; i < mStrings.length; i++) {
			TextView textView = new TextView(context);
			textView.setTag(mStrings[i]);
			textView.setOnClickListener(this);
			GridLayout.LayoutParams params = new GridLayout.LayoutParams();
			params.width = 0;
			params.height = 0;
			// 设置行列下标，和比重
			params.rowSpec = GridLayout.spec(i/3, 1f);
			params.columnSpec = GridLayout.spec(i%3, 1f);
			// 背景
			textView.setBackgroundColor(Color.WHITE);
			if ("结算".equals(mStrings[i])) {
				textView.setBackgroundColor(Color.parseColor("#f68904"));
				textView.setTextColor(Color.WHITE);
				params.rowSpec = GridLayout.spec(0, 4, 1f);
				params.columnSpec = GridLayout.spec(3, 1f);
			}
			// 居中显示
			textView.setGravity(Gravity.CENTER);
			// 设置边距
			params.setMargins(2, 2, 2, 2);
			// 设置文字
			textView.setText(mStrings[i]);
			// 添加item
			gridLayout.addView(textView, params);
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		LogTools.logger(TAG, "view gettag:"+view.getTag().toString());
		if(("结算").equals(view.getTag().toString())){
			this.dismiss();
		}
	}

}
