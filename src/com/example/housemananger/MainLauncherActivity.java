package com.example.housemananger;

import com.ch.commutils.LogTools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainLauncherActivity extends Activity implements View.OnClickListener{

	private final String TAG = "MainLauncherActivity";
	private int[] mButtons = new int[]{R.id.button1,R.id.button2};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlauncheractivity);
		for(int i=0;i<mButtons.length;i++){
			findViewById(mButtons[i]).setOnClickListener(this);
		}
		
		
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		LogTools.logger(TAG, "view id:"+view.getId());
		switch (view.getId()) {
		case R.id.button1:
			onButton1_onClick(view);
			break;
		case R.id.button2:
			onButton2_onClick(view);
		default:
			break;
		}
	}

	private void onButton1_onClick(View view){
		ComponentName cn = new ComponentName(this.getPackageName(),this.getPackageName()+".MainActivity") ;
		Intent intent = new Intent() ;
		intent.setComponent(cn) ;
		startActivity(intent) ;
	}
	
	private void onButton2_onClick(View view){
		
	}
}
