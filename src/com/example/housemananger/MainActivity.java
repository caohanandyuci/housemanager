package com.example.housemananger;

import java.util.ArrayList;
import java.util.List;

import com.ch.control.DataManager;
import com.ch.entity.CategoryBean;
import com.ch.entity.GoodsBean;
import com.ch.sqlitehelper.RecordsDatabaseHelper;
import com.ch.view.CategoryInfoAdapter;
import com.ch.view.GoodsInfoAdapter;
import com.ch.view.OrderAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DataManager.insertTestData(this);
        
        initUi(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private List<GoodsBean> goodsBeans = new ArrayList<GoodsBean>();
    private List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();
    private void initUi(Context context){
//    	    goodsBeans.add(new GoodsBean("goods1",1,101.0f));
//    	    goodsBeans.add(new GoodsBean("goods2",1,102.0f));
//    	    goodsBeans.add(new GoodsBean("goods3",1,103.0f));
//    	    goodsBeans.add(new GoodsBean("goods4",1,104.0f));
//    	    goodsBeans.add(new GoodsBean("goods5",1,105.0f));
//    	    goodsBeans.add(new GoodsBean("goods6",1,106.0f));
    	    
    	    RecordsDatabaseHelper databasehelper = new RecordsDatabaseHelper(context);
    	    goodsBeans.addAll(databasehelper.querGoodsBeans());
    	    OrderAdapter orderAdapter = new OrderAdapter(context, R.layout.orderitemview, goodsBeans);
    	    ((ListView) findViewById(R.id.orderlistview)).setAdapter(orderAdapter);
    	    
    	    categoryBeans.addAll(databasehelper.queryCategoryBeans());
    	    CategoryInfoAdapter categoryInfoAdapter = new CategoryInfoAdapter(context, R.layout.categroyitemview, categoryBeans);
    	    ((ListView) findViewById(R.id.categorylistview)).setAdapter(categoryInfoAdapter);
    	    
    	    
    	    GridView gridView = (GridView) findViewById(R.id.goodsgirdview);
        gridView.setAdapter(new GoodsInfoAdapter(this,goodsBeans));
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
