package com.ch.entity;

import java.util.UUID;

import com.ch.commutils.Utils;

public class CategoryBean {
	
	public static final String KEY_STRING_UUID="uuid";
	
	public static final String KEY_INT_CATEGORYID = "categoryid";
	
	public static final String KEY_STRING_NAME=  "name";
	
	private String mUUID = "";
	
	private int mCategoryID = 0;
	
	private String mName = "";

	public CategoryBean(){
		mUUID = Utils.createUUID();
	}
	
	public CategoryBean(int categoryid,String categoryname){
		mUUID = Utils.createUUID();
		this.mCategoryID = categoryid;
		this.mName = categoryname;
	}
	
	public String getUUID() {
		return mUUID;
	}

	public void setUUID(String uUID) {
		mUUID = uUID;
	}

	public int getCategoryID() {
		return mCategoryID;
	}

	public void setCategoryID(int categoryID) {
		mCategoryID = categoryID;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	
}
