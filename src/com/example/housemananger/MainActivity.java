package com.example.housemananger;

import java.util.List;
import com.ch.commutils.DataLoadingListener;
import com.ch.commutils.LogTools;
import com.ch.control.DataManager;
import com.ch.control.OrderManager;
import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.entity.OrderBean;
import com.ch.entity.OrderItem;
import com.ch.view.CategoryInfoView;
import com.ch.view.GoodsInfoGridView;
import com.ch.view.MenuPopupWindow;
import com.ch.view.OrderDetailPopupView;
import com.ch.view.OrderListView;
import com.ch.view.PayDialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener,DataLoadingListener{

	private final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(this);
    }
    
    private DataManager mDataManager = null;
    private OrderManager mOrderManager = null;
    private Handler mHandler = null;
    private OrderBean mCurrentOrderBean = null;
    private GoodsInfoGridView mGoodsGridView = null;
    private CategoryInfoView mCategoryInfoView = null;
    
    private OrderListView mOrderListView = null;
    private OrderDetailPopupView mPopupView = null;
    
    private int[] mButton = new int[]{R.id.paybutton,R.id.deletebutton,R.id.menubutton};
    
    private void init(Context context){
    	    //DataManager.insertTestData(context);
    	    for(int i=0;i<mButton.length;i++){
    	      	findViewById(mButton[i]).setOnClickListener(this);
    	    }
    	    mHandler = new Handler();
    	    mDataManager = new DataManager(context);
    	    mDataManager.addDataChangedListener(this);
    	    
    	    mOrderManager = new OrderManager();
    	    mCurrentOrderBean = mOrderManager.CreateOrder();
    	    
        mGoodsGridView = (GoodsInfoGridView) findViewById(R.id.goodsgirdview);
        mGoodsGridView.setOnItemClickListener(mGoodsGridviewOnItemClickListener);
        mGoodsGridView.attchDataManager(mDataManager);
        
        mCategoryInfoView = (CategoryInfoView) findViewById(R.id.categoryinfoview);
        mCategoryInfoView.addCategoryItemChangedListener(mGoodsGridView);
        mOrderListView = (OrderListView) findViewById(R.id.orderlistview);
        mOrderListView.addOnItemSelectedListener(mOrderItemSelectedListener);
        mPopupView = new OrderDetailPopupView(this);
        
        mDataManager.LoadDataFromDBOnThread();
    }
    
    private OnItemClickListener mGoodsGridviewOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			LogTools.logger(TAG, "view:"+view+"id:"+id+"position:"+position);
			int count = parent.getChildCount();
			int index = parent.indexOfChild(view);
			Log.v(TAG, "index:"+index);
			for(int i=0;i<count;i++){
				if(index==i){
					parent.getChildAt(i).setBackgroundResource(R.drawable.goods_background_selected);
				}else{
					parent.getChildAt(i).setBackgroundResource(R.drawable.goods_background);
				}
			}
			GoodsBean goodsBean = (GoodsBean) parent.getAdapter().getItem(position);
			boolean isExist = mOrderManager.InsertGoodsOfOrder(mCurrentOrderBean.getOrderId(), goodsBean);
			List<OrderItem> orderItems = mCurrentOrderBean.getOrderItems();
			mOrderListView.updateData(orderItems);
			UpdateSumPrice();
		}
	};
	
	private void UpdateSumPrice(){
		float sumprice = mOrderManager.CalculateOrderSumPrice(mCurrentOrderBean.getOrderId());
		TextView sumpricetextView = (TextView) findViewById(R.id.sumpricetextview);
		sumpricetextView.setText("￥"+String.valueOf(sumprice));
		LogTools.logger(TAG, "sumprice:"+sumprice);
	}
	
	private OrderListView.OnItemSelectedListener mOrderItemSelectedListener = new OrderListView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
			LogTools.logger(TAG, "mOrderItemClickListener position:" + position);
			OrderItem orderItem = (OrderItem) parent.getAdapter().getItem(position);
			showdialog(orderItem);
		}
	};
	
	private void showdialog(final OrderItem orderItem){
		mPopupView.showPopupWindow(mOrderListView,mOrderListView.getWidth(),-mOrderListView.getHeight(),orderItem);
		mPopupView.setonOkListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int num = mPopupView.getGoodsnum();
				orderItem.setNumber(num);
				LogTools.logger(TAG, "orderItem.setNumber:"+num);
				mPopupView.dismissview();
				List<OrderItem> orderItems = mCurrentOrderBean.getOrderItems();
				mOrderListView.updateData(orderItems);
				UpdateSumPrice();
			}
		});
		mPopupView.setonDelListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mPopupView.dismissview();
				List<OrderItem> orderItems = mCurrentOrderBean.getOrderItems();
				orderItems.remove(orderItem);
				mOrderListView.updateData(orderItems);
				UpdateSumPrice();
			}
		});
	}
	
    private View.OnClickListener mDeleteButtOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			LogTools.logger(TAG, "deletebuttononclicklistener");
		}
	};

	@Override
	public void onLoadingStarted() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLoadingFailed(int failReason) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLoadingComplete() {
		// TODO Auto-generated method stub
		if (mDataManager != null) {
			final List<CategoryBean> categoryBeans = mDataManager.getCategoryBean();
			if(categoryBeans ==null){
				LogTools.logger(TAG, "categorybeans is null");
				return;
			}
			if(categoryBeans.size()==0){
				LogTools.logger(TAG, "categorybeans size is zero");
				return;
			}
			final CategoryBean categorybean  = categoryBeans.get(0);
			if(categorybean == null){
				LogTools.logger(TAG, "categorybean[0] is null");
				return;
			}
			final List<GoodsBean> goodsBeans = mDataManager.getGoodsBeanByCategory(categorybean.getCategoryID());
			mHandler.post(new Runnable(){
				@Override
				public void run() {
					LogTools.logger(TAG, "update ui");
					mCategoryInfoView.updateData(categoryBeans);
					mGoodsGridView.UpdateData(goodsBeans);
				}
			});
			
		}
	}


	@Override
	public void onLoadingCancelled() {
		// TODO Auto-generated method stub
		
	}


	private void onDeleteButton_onClick(View view){
		
	}
	
	private void onPayButton_onClick(View view){
		PayDialog payDialog = new PayDialog(this,R.style.PayDialog);
		payDialog.show();
	}
	
	private MenuPopupWindow menuPopupWindow = null;
	private void onMenuButton_onClick(View view){
		if(menuPopupWindow!=null){
			if(menuPopupWindow.isShowing()){
				menuPopupWindow.dismiss();
			}else{
				menuPopupWindow.showAsDropDown(findViewById(R.id.rootrelativeview), 0,
						-findViewById(R.id.rootrelativeview).getHeight());
			}
		} else {
			menuPopupWindow = new MenuPopupWindow(this);
			menuPopupWindow.showAsDropDown(findViewById(R.id.rootrelativeview), 0,
					-findViewById(R.id.rootrelativeview).getHeight());
		}
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		LogTools.logger(TAG, "view id:"+view.getId());
		switch (view.getId()) {
		case R.id.deletebutton:
			onDeleteButton_onClick(view);
			break;
		case R.id.paybutton:
			onPayButton_onClick(view);
			break;
		case R.id.menubutton:
			onMenuButton_onClick(view);
			break;
		default:
			break;
		}
	}
}
